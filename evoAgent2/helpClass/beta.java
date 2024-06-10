
package helperClasess;
public class beta {
    private float[] B;
    private float fitness;
    private boolean flag = true;

    public beta(float[] B) {
        this.B = B;
    }

    public float[] getB() {
        return B;
    }

    public void setB(float[] B) {
        this.B = B;
    }

    public boolean isFlag() {
        return flag;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
