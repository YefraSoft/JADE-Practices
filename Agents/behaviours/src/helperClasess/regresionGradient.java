package helperClasess;

import java.util.Arrays;

public class regresionGradient {

    private final double[] x;
    private final double[] y;
    private double b0;
    private double b1;
    private final double alpha;
    private double rSquared;
    private double correlation;

    public regresionGradient(double[] x, double[] y, double alpha) {
        this.x = x;
        this.y = y;
        this.alpha = alpha;
    }

    private void fit() {
        int iterations = 0;
        double errorThreshold = 3;
        while (errorThreshold > 2 && iterations < 6) {
            double sumB0 = 0;
            double sumB1 = 0;
            for (int i = 0; i < x.length; i++) {
                double yPredicted = b0 + b1 * x[i];
                sumB0 += (yPredicted - y[i]);
                sumB1 += (yPredicted - y[i]) * x[i];
            }
            double newB0 = b0 - (alpha * (sumB0 / x.length));
            double newB1 = b1 - (alpha * (sumB1 / x.length));
            b0 = newB0;
            b1 = newB1;
            double[] predictions = predict(x);
            errorThreshold = calculateRMSE(y, predictions);
            iterations++;
            System.out.println("Iteration " + iterations + ", Error: " + errorThreshold);
        }
        this.rSquared = calculateRSquared(y, predict(x));
        this.correlation = calculateCorrelation(x, y);
    }

    private double[] predict(double[] x) {
        double[] predictions = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            predictions[i] = b0 + b1 * x[i];
        }
        return predictions;
    }

    private double calculateRMSE(double[] actual, double[] predicted) {
        double sum = 0.0;
        for (int i = 0; i < actual.length; i++) {
            double error = predicted[i] - actual[i];
            sum += Math.pow(error, 2);
        }
        return Math.sqrt(sum / actual.length);
    }

    private double calculateRSquared(double[] actual, double[] predicted) {
        double meanY = Arrays.stream(actual).average().orElse(0.0);
        double ssTotal = 0.0;
        double ssResidual = 0.0;
        for (int i = 0; i < actual.length; i++) {
            ssTotal += Math.pow(actual[i] - meanY, 2);
            ssResidual += Math.pow(actual[i] - predicted[i], 2);
        }
        return 1 - (ssResidual / ssTotal);
    }

    private double calculateCorrelation(double[] x, double[] y) {
        double sumX = 0.0;
        double sumY = 0.0;
        double sumXY = 0.0;
        double sumXSquared = 0.0;
        double sumYSquared = 0.0;
        for (int i = 0; i < x.length; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumXSquared += Math.pow(x[i], 2);
            sumYSquared += Math.pow(y[i], 2);
        }
        double numerator = x.length * sumXY - sumX * sumY;
        double denominator = Math.sqrt((x.length * sumXSquared - Math.pow(sumX, 2)) * (x.length * sumYSquared - Math.pow(sumY, 2)));
        return numerator / denominator;
    }

    public double getrSquared() {
        return rSquared;
    }

    public double getCorrelation() {
        return correlation;
    }

    public void predictSout(double[] x) {
        fit();
        for (int i = 0; i < x.length; i++) {
            System.out.println("Result [" + x[i] + "]: " + (b0 + b1 * x[i]));
        }
    }
}
