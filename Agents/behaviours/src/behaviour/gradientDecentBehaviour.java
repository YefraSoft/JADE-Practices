package behaviour;

import helperClasess.regresionGradient;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author YefraSoft
 */
public class gradientDecentBehaviour extends OneShotBehaviour {

    double[] dependient;
    double[] coDependient;
    double alpha;

    public gradientDecentBehaviour(double[] dependient, double[] coDependient, double alpha) {
        this.dependient = dependient;
        this.coDependient = coDependient;
        this.alpha = alpha;
    }

    public gradientDecentBehaviour() {
    }

    @Override
    public void action() {
        double[] elements = {20, 56, 60};
        regresionGradient rG = new regresionGradient(dependient,coDependient, alpha);
        System.out.println("Correlation: " + rG.getCorrelation());
        System.out.println("Determination: " + rG.getrSquared());
        rG.predictSout(elements);
    }

    @Override
    public int onEnd() {
        myAgent.doDelete();
        return super.onEnd();
    }
}
