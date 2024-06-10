package helperClasess;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class modelAnalysis {

    /**
     * Returns the correlation coefficient representation of the
     * {@code double[]} argument.
     *
     * @param x an {@code double[]}.
     * @param y an {@code double[]}.
     * @return if the x or y is {@code < 0}, then a double equal to {@code 0.0};
     * otherwise, the value of {@code correlationValue} is returned.
     */
    public static double pearsonCorrelationCoefficient(double[] x, double[] y) {
        if (x.length > 0 && y.length == x.length) {
            double Ex = 0, Ey = 0, Exy = 0, Ex2 = 0, Ey2 = 0, aux, aux2;
            int n = x.length;
            for (int i = 0; i < n; i++) {
                Ex += x[i];
                Ey += y[i];
                Exy += (x[i] * y[i]);
                Ex2 += (x[i] * x[i]);
                Ey2 += (y[i] * y[i]);
            }
            aux = (n * Exy) - (Ex * Ey);
            aux2 = Math.sqrt((n * Ex2) - (Ex * Ex)) * Math.sqrt((n * Ey2) - (Ey * Ey));
            return (aux / aux2);
        }
        return 0.0;
    }

    /**
     * Returns the correlation coefficient representation of the {@code float[]}
     * argument.
     *
     * @param x an {@code float[]}.
     * @param y an {@code float[]}.
     * @return if the x or y is {@code <0}, then a float equal to {@code 0.0};
     * otherwise, the value of {@code correlationValue} is returned.
     */
    public static float pearsonCorrelationCoefficient(float[] x, float[] y) {
        if (x.length > 0 && y.length > 0) {
            double Ex = 0, Ey = 0, Exy = 0, Ex2 = 0, Ey2 = 0, aux, aux2;
            int n = x.length;
            for (int i = 0; i < n; i++) {
                Ex += x[i];
                Ey += y[i];
                Exy += (x[i] * y[i]);
                Ex2 += (x[i] * x[i]);
                Ey2 += (y[i] * y[i]);
            }
            aux = (n * Exy) - (Ex * Ey);
            aux2 = Math.sqrt((n * Ex2) - (Ex * Ex)) * Math.sqrt((n * Ey2) - (Ey * Ey));
            return (float) (aux / aux2);
        }
        return 0.0f;
    }

    /**
     * Returns the correlation coefficient representation of the
     * {@code double[]} argument.
     *
     * @param x an {@code double[]}.
     * @param y an {@code double[]}.
     * @return if the x or y is {@code < 0}, then a double equal to {@code 0.0};
     * otherwise, the value of {@code correlationValue} is returned.
     */
    public static float pearsonCorrelationCoefficient(int n, float Exy, float Ex, float Ey, float Ex2, float Ey2) {
        return (float) (((n * Exy) - (Ex * Ey)) / (Math.sqrt((n * Ex2) - (Ex * Ex)) * Math.sqrt((n * Ey2) - (Ey * Ey))));
    }

    public static float determinationCoefficient(float[] betas, float[] x, float[] y) {
        float yAvg = 0, SST = 0, SSR = 0;
        if (y.length > 0) {
            for (int i = 0; i < y.length; i++) {
                yAvg += y[i];
            }
            for (int i = 0; i < y.length; i++) {
                SST += Math.pow(
                        y[i] - yAvg,
                        2);
            }
            for (int i = 0; i < y.length; i++) {
                SSR += roudNumber(
                        Math.pow(y[i] - (betas[0] + (betas[1] * x[i])), 2),
                        new MathContext(3, RoundingMode.HALF_UP));
            }
            return (1 - (SSR / SST));
        }
        return 0.0f;
    }

    public static float determinationCoefficientMulti(float[] betas, float[] y, List<float[]> xValue) {
        float yAvg = 0;
        float[] result = new float[y.length];
        float[] SCE = new float[y.length];
        float[] SCR = new float[y.length];
        float eSCE = 0;
        float eSCR = 0;
        if (y.length > 0) {
            for (int i = 0; i < y.length; i++) {
                yAvg += y[i];
            }
            yAvg = yAvg / y.length;
            for (int i = 0; i < y.length; i++) {
                result[i] = betas[0];
                for (int beta = 1; beta < betas.length; beta++) {
                    result[i] += betas[beta] * xValue.get(beta - 1)[i];
                }
                SCE[i] = (float) Math.pow(y[i] - result[i], 2);
                SCR[i] = (float) Math.pow(result[i] - yAvg, 2);
                eSCE += SCE[i];
                eSCR += SCR[i];
            }
            return (eSCR / (eSCR + eSCE));
        }
        return 0.0f;
    }

    private static float roudNumber(double n, MathContext c) {
        BigDecimal round = new BigDecimal(n, c);
        return round.floatValue();
    }
}
