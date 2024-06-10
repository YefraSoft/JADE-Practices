package helperClasess;
public  class fitness {
    public int getChrom() {
        return chrom;
    }

    public float getFitness() {
        return fitness;
    }
    private final int chrom;
    private final float fitness;

    public fitness(int chrom, float fitness) {
        this.chrom = chrom;
        this.fitness = fitness;
    }
}
