package helperClasess;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class swingComponents {
    
    Font font = new Font("Roboto", 1, 12);
    
    public JLabel lavel(String text) {
        JLabel genericLabel = new JLabel(text);
        genericLabel.setFont(font);
        return genericLabel;
    }
    
    public JSlider slider(int min, int max, int initialValue) {
        JSlider genericSlider = new JSlider(JSlider.HORIZONTAL, min, max, initialValue);
        genericSlider.setMajorTickSpacing(10);
        genericSlider.setMinorTickSpacing(5);
        genericSlider.setPaintTicks(true);
        genericSlider.setPaintLabels(true);
        genericSlider.setFont(font);
        return genericSlider;
    }
    
    public JRadioButton radioButton(String text) {
        JRadioButton genericRadio = new JRadioButton(text);
        genericRadio.setFont(font);
        return genericRadio;
    }
    
    public JPanel panelGroup(ArrayList<Object> components) {
        JPanel genericGroup = new JPanel();
        for (Object component : components) {
            genericGroup.add((Component) component);
        }
        return genericGroup;
    }
    
    public JButton button(String title, ActionListener action) {
        JButton genericButton = new JButton(title);
        genericButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        genericButton.addActionListener(action);
        return genericButton;
    }
    public JTextField numberTextField(){
        JTextField numberField = new JTextField(10);
        numberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
        return numberField;
    }
}
