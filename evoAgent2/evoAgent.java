package evoAgent;

import behaviour.evoBehaviour;
import helperClasess.datas;
import helperClasess.swingComponents;
import jade.core.Agent;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class evoAgent extends Agent {

    int minValue = 0, maxValue = 0, selectedType = 0;

    @Override
    protected void setup() {
        System.out.println("Agent Regresion started.");
        JDialog dialog = new JDialog((Frame) null, "Parameters", true);
        swingComponents comp = new swingComponents();
        ArrayList panelComponents = new ArrayList<>();
        panelComponents.add(
                comp.lavel("Parameters Evo Agent")
        );
        panelComponents.add(
                comp.lavel("Minimum Random Value")
        );
        panelComponents.add(
                comp.slider(-10, 0, 0)
        );
        panelComponents.add(
                comp.lavel("Maximum Random Value")
        );
        panelComponents.add(
                comp.slider(0, 100, 0)
        );
        panelComponents.add(
                comp.lavel("Select the type of random value")
        );
        panelComponents.add(
                comp.radioButton("Int")
        );
        panelComponents.add(
                comp.radioButton("Float")
        );
        panelComponents.add(
                comp.radioButton("Double")
        );
        panelComponents.add(
                comp.button("Start", (ActionEvent e) -> {
                    JSlider slider1 = (JSlider) panelComponents.get(2);
                    JSlider slider2 = (JSlider) panelComponents.get(4);
                    JRadioButton intRb = (JRadioButton) panelComponents.get(6);
                    JRadioButton floatRb = (JRadioButton) panelComponents.get(7);
                    JRadioButton doubleRb = (JRadioButton) panelComponents.get(8);
                    minValue = slider1.getValue();
                    maxValue = slider2.getValue();
                    if (intRb.isSelected()) {
                        selectedType = 1;
                    } else if (intRb.isSelected()) {
                        selectedType = 2;
                    } else {
                        selectedType = 3;
                    }
                    dialog.dispose();
                })
        );
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(comp.panelGroup(panelComponents), BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        addBehaviour(new evoBehaviour(
                100, 2, datas.getSalary(), datas.getYearsExperience(), 0.9f, 0.4f, minValue, maxValue, selectedType
        ));
    }
}
