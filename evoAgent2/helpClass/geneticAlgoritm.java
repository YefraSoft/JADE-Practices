package helperClasess;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class geneticAlgoritm {

    private final Map<Integer, beta> Chromosome = new HashMap<>();
    private final Random getRand = new Random();
    private BigDecimal round;
    private float cossoverRate = (float) 0.95;
    private float mutationRate = (float) .3;
    private float[] values;
    private double[] expectedValues;

    /**
     * linear regression with genetic algorithm.
     *
     * @param population number of specimens to train.
     * @param nBetas number of data columns.
     * @param trainingValues expected values of " Y || B0 " for this exercise.
     * @param cossoverRate crossover rate
     * @param mutationRate Mutation rate
     * @param originRandom origin of random values.
     * @param boundRandom bound of random values.
     * @param typeOfRand type of variable of random values 1): int 2): float 3):
     * double
     */
    public geneticAlgoritm(int population, int nBetas, double[] trainingValues, float cossoverRate, float mutationRate, int originRandom, int boundRandom, int typeOfRand) {
        expectedValues = trainingValues;
        for (int i = 0; i < population; i++) {
            values = new float[nBetas];
            switch (typeOfRand) {
                case 1 -> {
                    for (int j = 0; j < nBetas; j++) {
                        round = new BigDecimal(getRand.nextInt(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                        values[j] = round.floatValue();
                    }
                }
                case 2 -> {
                    for (int j = 0; j < nBetas; j++) {
                        round = new BigDecimal(getRand.nextFloat(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                        values[j] = round.floatValue();
                    }
                }
                case 3 -> {
                    for (int j = 0; j < nBetas; j++) {
                        round = new BigDecimal(getRand.nextDouble(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                        values[j] = round.floatValue();
                    }
                }
                default ->
                    throw new AssertionError();
            }
            Chromosome.put(i, new beta(values));
        }
    }

    /**
     * linear regression with genetic algorithm.
     *
     * @param population number of specimens to train.
     * @param nBetas number of data columns.
     * @param trainingValues expected values of " Y || B0 " for this exercise.
     * @param originRandom origin of random values.
     * @param boundRandom bound of random values.
     * @param typeOfRand type of variable of random values 1): int 2): float 3):
     * double
     */
    public geneticAlgoritm(int population, int nBetas, double[] trainingValues, int originRandom, int boundRandom, int typeOfRand) {
        expectedValues = trainingValues;
        for (int i = 0; i < population; i++) {
            values = new float[nBetas];
            switch (typeOfRand) {
                case 1 -> {
                    for (int j = 0; j < nBetas; j++) {
                        round = new BigDecimal(getRand.nextInt(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                        values[j] = round.floatValue();
                    }
                }
                case 2 -> {
                    for (int j = 0; j < nBetas; j++) {
                        round = new BigDecimal(getRand.nextFloat(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                        values[j] = round.floatValue();
                    }
                }
                case 3 -> {
                    for (int j = 0; j < nBetas; j++) {
                        round = new BigDecimal(getRand.nextDouble(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                        values[j] = round.floatValue();
                    }
                }
                default ->
                    throw new AssertionError();
            }
            Chromosome.put(i, new beta(values));
        }
    }

    /**
     * linear regression with genetic algorithm.
     *
     * @param population number of specimens to train.
     * @param nBetas number of data columns.
     * @param trainingValues expected values of " Y || B0 " for this exercise.
     * @param originRandom
     * @param boundRandom
     */
    public geneticAlgoritm(int population, int nBetas, double[] trainingValues, int originRandom, int boundRandom
    ) {
        expectedValues = trainingValues;
        for (int i = 0; i < population; i++) {
            values = new float[nBetas];
            for (int j = 0; j < nBetas; j++) {
                round = new BigDecimal(getRand.nextFloat(originRandom, boundRandom), new MathContext(5, RoundingMode.HALF_UP));
                values[j] = round.floatValue();
            }
            Chromosome.put(i, new beta(values));
        }
    }

    /**
     * linear regression with genetic algorithm.
     *
     * @param population number of specimens to train.
     * @param nBetas number of data columns.
     * @param trainingValues expected values of " Y || B0 " for this exercise.
     */
    public geneticAlgoritm(int population, int nBetas, double[] trainingValues
    ) {
        expectedValues = trainingValues;
        for (int i = 0; i < population; i++) {
            values = new float[nBetas];
            for (int j = 0; j < nBetas; j++) {
                round = new BigDecimal(getRand.nextFloat(), new MathContext(5, RoundingMode.HALF_UP));
                values[j] = round.floatValue();
            }
            Chromosome.put(i, new beta(values));
        }
    }

    public float[] getResult(double[] xValues) {
        boolean done = true;
        int count = 0;
        do {
            fitness bestFitness = fitnessFuntion(xValues);
            if (bestFitness.getFitness() > 0.85 && bestFitness.getFitness() < 1) {
                return Chromosome.get(bestFitness.getChrom()).getB();
            } else if (done) {
                count++;
                if (count > 99) {
                    return null;
                }
                selection();
            }
        } while (done);
        return null;
    }

    private fitness fitnessFuntion(double[] xValues) {
        int bestBeta = 0;
        int index = 0;
        float yavg = 0;
        float bestF = 0.0f;
        float SST = 0;
        for (int i = 0; i < expectedValues.length; i++) {
            yavg += expectedValues[i];
        }
        round = new BigDecimal((yavg / expectedValues.length), new MathContext(3, RoundingMode.HALF_UP));
        yavg = round.floatValue();
        for (int i = 0; i < expectedValues.length; i++) {
            SST += Math.pow(
                    expectedValues[i] - yavg,
                    2);
        }
        for (beta chrom : Chromosome.values()) {
            float SSR = 0;
            for (int x = 0; x < expectedValues.length; x++) {
                SSR += Math.pow(expectedValues[x] - getY(chrom, xValues[x]), 2);
            }
            round = new BigDecimal((1 - (SSR / SST)), new MathContext(3, RoundingMode.HALF_UP));
            chrom.setFitness(
                    round.floatValue());
            if (index == 0) {
                bestF = chrom.getFitness();
            } else if (bestF < chrom.getFitness()) {
                bestF = chrom.getFitness();
                bestBeta = index;
            }
            index++;
        }
        return new fitness(bestBeta, bestF);
    }

    private void selection() {
        boolean done = true;
        do {
            int croSelection = crossoverSelecction();
            int rouSelection = roulette(sumRoulette(rouletteE()));
            if (croSelection == 0 && rouSelection == 0) {
                for (beta chrom : Chromosome.values()) {
                    chrom.setFlag(true);
                }
                done = false;
            } else {
                crossover(rouSelection, croSelection);
            }
        } while (done);
    }

    private int roulette(float[] roulette) {
        int selec = 0;
        int arrow = getRand.nextInt(0, 360);
        for (int i = 0; i < Chromosome.size(); i++) {
            if (arrow < roulette[i] && Chromosome.get(i).isFlag()) {
                Chromosome.get(i).setFlag(false);
                selec = i;
                break;
            }
        }
        return selec;
    }

    private int crossoverSelecction() {
        int position = 0;
        for (int i = 0; i < Chromosome.size(); i++) {
            float rand = getRand.nextFloat();
            if (Chromosome.get(i).isFlag()) {
                if (rand < cossoverRate) {
                    position = i;
                    Chromosome.get(i).setFlag(false);
                    break;
                }
                if (i == 99) {
                    i = 0;
                }
            }
        }
        return position;
    }

    private float rouletteE() {
        float E = 0;
        for (beta chrom : Chromosome.values()) {
            E += Math.abs(chrom.getFitness());
        }
        return E;
    }

    private void crossover(int dad, int mom) {
        int pointCross = getRand.nextInt(0, Chromosome.get(0).getB().length);
        float aux = Chromosome.get(dad).getB()[pointCross];
        Chromosome.get(dad).getB()[pointCross] = Chromosome.get(mom).getB()[pointCross];
        Chromosome.get(mom).getB()[pointCross] = aux;
        float mutPercent = getRand.nextFloat();
        if (mutPercent < mutationRate) {
            mutation(dad, mom);
        }
    }

    private void mutation(int chrom1, int chrom2) {
        int pointMut = getRand.nextInt(0, Chromosome.get(0).getB().length);
        Chromosome.get(chrom1).getB()[pointMut] = getRand.nextFloat();
        Chromosome.get(chrom2).getB()[pointMut] = getRand.nextFloat();
    }

    private double getY(beta chrom, double x) {
        return (chrom.getB()[0] + chrom.getB()[1] * x);
    }

    private float[] sumRoulette(float E) {
        float[] roulette = new float[Chromosome.size()];
        float sumRoulette = 0;
        for (int i = 0; i < Chromosome.size(); i++) {
            roulette[i] = sumRoulette;
            sumRoulette += (Math.abs(Chromosome.get(i).getFitness() / E) * 360);
        }
        return roulette;
    }
}
