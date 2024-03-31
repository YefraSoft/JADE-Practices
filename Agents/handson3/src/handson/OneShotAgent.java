package handson;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        addBehaviour(new MyOneShotBehaviour());
    }

    private class MyOneShotBehaviour extends OneShotBehaviour {

        @Override
        public void action() {
            double sales[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
            double advertising[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};
            double[] elements = {20, 56, 60};
            regresionGradient rG = new regresionGradient(advertising, sales, 0.003);
            rG.predictSout(elements);
            System.out.println("Correlation: " + rG.getCorrelation());
            System.out.println("Determination: " + rG.getrSquared());
        }

        @Override
        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }    // END of inner class ...Behaviour
}
