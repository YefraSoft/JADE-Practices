package handson;

public class regresionObject {

    private final double y[];
    private final double x[];
    private double b0, b1, correlation, TSS;
    private double[] results;
    private final int n;

    public regresionObject(double[] x, double[] y) {
        this.y = y;
        this.x = x;
        n = x.length;
        createRegresion();
    }

    public double[] multiPrediction(double[] elements) {
        results = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            results[i] = prediction(elements[i]);
        }
        return results;
    }

    public void multiPredictionConsole(double[] elements) {
        results = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            System.out.println("Result: " + prediction(elements[i]) + " in (" + elements[i] + ").");
        }
    }

    public double getDetermination() {
        double RSS = 0, R2;
        for (int i = 0; i < x.length; i++) {
            RSS += Math.pow(y[i] - prediction(x[i]), 2);
        }
        R2 = (1 - (RSS / TSS));
        return R2;
    }

    public double getCorrelation() {
        return correlation;
    }

    public double prediction(double intersec) {
        return b0 + (b1 * intersec);
    }

    /*private double SquareError() {
        double error = 0, aux, yha;
        for (int i = 0; i < x.length; i++) {
            yha = b0 + (b1 * x[i]);
            aux = y[i] - yha;
            error += Math.pow(aux, 2);
        }
        return (error / n);
    }*/
    private void createRegresion() {
        double Ex = 0, Ey = 0, Exy = 0, Ex2 = 0, Ey2 = 0, aux, avgY, aux2;
        for (int i = 0; i < n; i++) {
            Ex += x[i];
            Ey += y[i];
            Exy += (x[i] * y[i]);
            Ex2 += (x[i] * x[i]);
            Ey2 += (y[i] * y[i]);
        }
        avgY = (Ey / n);
        for (int i = 0; i < n; i++) {
            TSS += Math.pow((y[i] - avgY), 2);
        }
        b1 = ((n * Exy) - (Ex * Ey)) / ((n * Ex2) - Math.pow(Ex, 2));
        b0 = (Ey - (b1 * Ex)) / n;
        aux = (n * Exy) - (Ex * Ey);
        aux2 = Math.sqrt((n * Ex2) - (Ex * Ex)) * Math.sqrt((n * Ey2) - (Ey * Ey));
        correlation = aux / aux2;
    }
}
