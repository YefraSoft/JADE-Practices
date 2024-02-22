package linearregresionagent;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.ArrayList;

public class OneShotAgent extends Agent {
    
    @Override
  protected void setup() {
    addBehaviour(new MyOneShotBehaviour());
  }

  private class MyOneShotBehaviour extends OneShotBehaviour implements Regresions {

    private double y[];
    private double x[];
    ArrayList<String> results = new ArrayList<>();

    @Override
    public void action() {
      x = new double[] { 23, 26, 30, 34, 43, 48, 52, 57, 58 };
      y = new double[] { 651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518 };
      double[] elementsToCalc = { 20, 56, 60 };
      linearRegresionsTest(x, y, elementsToCalc);
    }

    @Override
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    }

    @Override
    public void linearRegresionsTest(double[] x, double[] y, double[] elementsToCalc) {
      double bug = 0;
      System.out.println("You re using test data...\n");
      for (int i = 0; i < x.length; i++) {
        bug = Math.pow(y[i] - getYhat(x[i]), 2);
      }
      for (int i = 0; i < elementsToCalc.length; i++) {
        results.add(String.valueOf((getYhat(elementsToCalc[i]) + bug)));
        System.out.println("Prediccion in a poit (" + elementsToCalc[i] + "): " + results.get(i));
      }
    }

    @Override
    public double getYhat(double intersec) {
      double Ex = 0, Ey = 0, Exy = 0, Ex2 = 0, n, b0, b1;
      n = x.length;
      for (int i = 0; i < n; i++) {
        Ex += x[i];
        Ey += y[i];
        Exy += (x[i] * y[i]);
        Ex2 += (x[i] * x[i]);
      }
      b1 = ((n * Exy) - (Ex * Ey)) / ((n * Ex2) - Math.pow(Ex, 2));
      b0 = (Ey - (b1 * Ex)) / n;
      return b0 + (b1 * intersec);
    }
  }
}