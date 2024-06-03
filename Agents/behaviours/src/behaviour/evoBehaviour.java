package behaviour;

import helperClasess.geneticAlgoritm;
import helperClasess.swingComponents;
import helperClasess.windowBuilder;
import jade.core.behaviours.Behaviour;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class evoBehaviour extends Behaviour {

    int population;
    int nBetas;
    double[] trainingValues;
    float cossoverRate;
    float mutationRate;
    int originRandom;
    int boundRandom;
    int typeOfRand;
    double[] xValues;

    public evoBehaviour(int population, int nBetas, double[] yValues, double[] xValues, float cossoverRate, float mutationRate, int originRandom, int boundRandom, int typeOfRand) {
        this.population = population;
        this.nBetas = nBetas;
        this.trainingValues = yValues;
        this.cossoverRate = cossoverRate;
        this.mutationRate = mutationRate;
        this.originRandom = originRandom;
        this.boundRandom = boundRandom;
        this.typeOfRand = typeOfRand;
        this.xValues = xValues;
    }

    @Override
    public void action() {
        JDialog dialog = new JDialog((Frame) null, "Parameters", true);
        geneticAlgoritm evo = new geneticAlgoritm(population, nBetas, trainingValues, cossoverRate, mutationRate, originRandom, boundRandom, typeOfRand);
        float[] betas = evo.getResult(xValues);
        if (betas != null) {
            System.out.println("B0: " + betas[0] + "\nB1: " + betas[1]);
            ArrayList<Object> components = new ArrayList<>();
            swingComponents comp = new swingComponents();
            components.add(comp.lavel("Insert the prediction value"));
            components.add(comp.numberTextField());
            components.add(comp.button("Predict", (ActionEvent e) -> {
                JTextField numberField = (JTextField) components.get(1);
                String text = numberField.getText();
                if (!text.isEmpty()) {
                    double result = Integer.parseInt(text);
                    JOptionPane.showMessageDialog(null, ": " + betas[0] + " + " + betas[1] + " * " + result + " = " + (betas[0] + betas[1] * result),
                            "Input Error", JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.dispose();
            }));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.add(comp.panelGroup(components), BorderLayout.CENTER);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }

    @Override
    public boolean done() {
        windowBuilder dialog = new windowBuilder("Continuar");
        return dialog.confirmPanel("Qestion", "Continue or exit") == 1;
    }

    @Override
    public int onEnd() {
        myAgent.doDelete();
        return super.onEnd();
    }
}
