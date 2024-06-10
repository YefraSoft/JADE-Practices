package helperClasess;

import helperClasess.modelAnalysis;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author YefraSoft
 */
public class regresionTasks {

    public static float[] simpleLinearRegresion(float[] x, float[] y) {
        int n = x.length;
        float Ex = 0, Ey = 0, Exy = 0, Ex2 = 0, Ey2 = 0;
        float[] betas = new float[4];
        if (x.length > 0 && y.length == x.length) {
            for (int i = 0; i < n; i++) {
                Ex += x[i];
                Ey += y[i];
                Exy += roudNumber(
                        (x[i] * y[i]),
                        new MathContext(3, RoundingMode.HALF_UP));
                Ex2 += roudNumber(
                        (x[i] * x[i]),
                        new MathContext(3, RoundingMode.HALF_UP));
                Ey2 += roudNumber(
                        (y[i] * y[i]),
                        new MathContext(3, RoundingMode.HALF_UP));
            }
            betas[1] = ((n * Exy) - (Ex * Ey)) / roudNumber(
                    (n * Ex2) - Math.pow(Ex, 2),
                    new MathContext(3, RoundingMode.HALF_UP)
            );
            betas[0] = (Ey - (betas[1] * Ex)) / n;
            betas[2] = modelAnalysis.pearsonCorrelationCoefficient(n, Exy, Ex, Ey, Ex2, Ey2);
            betas[3] = modelAnalysis.determinationCoefficient(betas, x, y);
            return betas;
        }
        return null;
    }

    public static float[] multiLinearRegresion(float[] y, float[]... x) {
        float[] result = new float[Arrays.asList(x).size() + 2];
        int ind = 0;
        float[][] gauss = getMatrizX(y, Arrays.asList(x));
        float[] yMatriz = getMatrizY(y, Arrays.asList(x));
        for (float beta : getBetas(gauss, yMatriz)) {
            result[ind] = beta;
            ind++;
        }
        result[ind] = modelAnalysis.determinationCoefficientMulti(getBetas(gauss, yMatriz), y, Arrays.asList(x));
        return result;
    }

    public static float[] polinomialRegresion(float[] betas, float[]... vars) {
        ArrayList<float[]> xValues = new ArrayList<>();
        xValues.addAll(Arrays.asList(vars));
        float[] results = new float[xValues.get(0).length];
        for (int y = 0; y < xValues.get(0).length; y++) {
            results[y] = betas[0];
            for (int x = 1; x < betas.length; x++) {
                results[y] += betas[x] * Math.pow(xValues.get(x - 1)[y], x);
            }
        }
        return results;
    }

    public static float[] multiPolinomialRegresion() {
        return null;
    }

    private static float roudNumber(double n, MathContext c) {
        BigDecimal round = new BigDecimal(n, c);
        return round.floatValue();
    }

    private static float sigma(float[] n) {
        float E = 0;
        for (int i = 0; i < n.length; i++) {
            E += n[i];
        }
        return E;
    }

    private static float[] getBetas(float A[][], float[] y) {
        int n = A.length;
        float[][] augmentedMatrix = new float[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = A[i][j];
            }
            augmentedMatrix[i][i + n] = 1.0f;
        }
        for (int i = 0; i < n; i++) {
            double diagElement = augmentedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= diagElement;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmentedMatrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                    }
                }
            }
        }
        float[][] inverse = new float[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                inverse[row][col] = augmentedMatrix[row][col + n] * y[col];
            }
        }
        float[] result = new float[n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                result[row] += inverse[row][col];
            }
        }
        return result;
    }

    private static float[][] getMatrizX(float[] y, List<float[]> xValues) {
        float[][] gauss = new float[xValues.size() + 1][xValues.size() + 1];
        ArrayList<Float> xN = new ArrayList<>();
        ArrayList<Float> x2N = new ArrayList<>();
        ArrayList<Float> xNY = new ArrayList<>();
        float[] xXx = new float[xValues.get(0).length];
        float[] x2 = new float[xValues.get(0).length];
        float[] xXy = new float[xValues.get(0).length];
        float[] yMatriz = new float[xValues.size() + 1];
        float xXn = 0;
        for (int row = 0; row < xValues.get(0).length; row++) {
            for (int col = 0; col < xValues.size(); col++) {
                if (xXx[row] == 0) {
                    xXx[row] = (float) xValues.get(col)[row];
                } else {
                    xXx[row] *= (float) xValues.get(col)[row];
                }

            }
        }
        xXn = sigma(xXx);
        for (int cols = 0; cols < xValues.size(); cols++) {
            for (int rows = 0; rows < xValues.get(cols).length; rows++) {
                x2[rows] = xValues.get(cols)[rows] * xValues.get(cols)[rows];
                xXy[rows] = xValues.get(cols)[rows] * y[rows];
            }
            x2N.add(sigma(x2));
            xNY.add(sigma(xXy));
            xN.add(sigma(xValues.get(cols)));
        }
        for (int i = 0; i < xValues.size() + 1; i++) {
            if (i == 0) {
                yMatriz[i] = sigma(y);
            } else {
                yMatriz[i] = xNY.get(i - 1);
            }
        }
        for (int rows = 0; rows < xValues.size() + 1; rows++) {
            for (int cols = 0; cols < xValues.size() + 1; cols++) {
                if (rows == 0 && cols == 0) {
                    gauss[rows][cols] = y.length;
                } else if (rows == cols && rows != 0) {
                    gauss[rows][cols] = x2N.get(cols - 1);
                } else if (rows == 0) {
                    gauss[rows][cols] = xN.get(cols - 1);
                } else if (cols == 0) {
                    gauss[rows][cols] = gauss[cols][rows];
                } else {
                    gauss[rows][cols] = xXn;
                }
            }
        }
        return gauss;
    }

    private static float[] getMatrizY(float[] y, List<float[]> xValues) {
        ArrayList<Float> xNY = new ArrayList<>();
        float[] xXy = new float[xValues.get(0).length];
        float[] yMatriz = new float[xValues.size() + 1];
        for (int cols = 0; cols < xValues.size(); cols++) {
            for (int rows = 0; rows < xValues.get(cols).length; rows++) {
                xXy[rows] = xValues.get(cols)[rows] * y[rows];
            }
            xNY.add(sigma(xXy));
        }
        for (int i = 0; i < xValues.size() + 1; i++) {
            if (i == 0) {
                yMatriz[i] = sigma(y);
            } else {
                yMatriz[i] = xNY.get(i - 1);
            }
        }
        return yMatriz;
    }

    private static float[] multiLinearRegresion(float[] y, List<float[]> x) {
        float[][] gauss = getMatrizX(y, x);
        float[] yMatriz = getMatrizY(y, x);
        float[] result = getBetas(gauss, yMatriz);
        return result;
    }

    public static float[] multiLinearRegresionSimple(float[] y, float[]... x) {
        float[][] gauss = getMatrizX(y, Arrays.asList(x));
        float[] yMatriz = getMatrizY(y, Arrays.asList(x));
        float[] result = getBetas(gauss, yMatriz);
        return result;
    }
}
