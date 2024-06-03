package helperClasess;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class windowBuilder extends JFrame {
    Color bgWindowColor;
    Font font;
    Color tetxColor;
    Color tableBg;
    String title;

    public windowBuilder(int[] bgcolor, String font, int styleFont, int textSize, int[] ftcolor, int[] bgTable, String title) {
        bgWindowColor = new Color(bgcolor[0], bgcolor[1], bgcolor[2]);
        tetxColor = new Color(ftcolor[0], ftcolor[1], ftcolor[2]);
        this.font = new Font(font, styleFont, textSize);
        tableBg = new Color(bgTable[0], bgTable[1], bgTable[2]);
    }

    public windowBuilder(String title, JPanel components) {
        try {
            font = new Font("Roboto", 1, 12);
            setTitle(title);
            //setSize(witdh, heith);  // Más ancho que alto
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            add(components);
            setLocationRelativeTo(null);
            pack();
        } catch (Exception e) {

        }
    }

    public windowBuilder(String title) {
        bgWindowColor = new Color(0, 0, 0);
        font = new Font("Roboto", 1, 12);
        tetxColor = new Color(255, 255, 255);
        tableBg = new Color(0, 0, 0);
        this.title = title;
    }

    public void tableView(JScrollPane table) {
        JFrame tableView = new JFrame();
        tableView.add(table);
        tableView.setTitle(title);
        tableView.setSize(400, 250);
        tableView.setVisible(true);
        tableView.setLayout(null);
        tableView.getContentPane().setBackground(bgWindowColor);
        tableView.setLocationRelativeTo(null);
        tableView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void resultView(JLabel determinationLebel, JLabel correlationLebel, JScrollPane table) {
        JFrame resultWindow = new JFrame();
        resultWindow.add(determinationLebel);
        resultWindow.add(correlationLebel);
        resultWindow.add(table);
        resultWindow.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        resultWindow.setSize(500, 147);
        resultWindow.setTitle(title);
        resultWindow.getContentPane().setBackground(bgWindowColor);
        resultWindow.setLocationRelativeTo(null);
        resultWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        resultWindow.setVisible(true);
    }

    public JLabel labelComponen(String labelContent) {
        JLabel genericLabel = new JLabel();
        genericLabel.setText(labelContent);
        genericLabel.setFont(font);
        genericLabel.setForeground(tetxColor);
        return genericLabel;
    }

    public JScrollPane tableComponent(String[] columnsName, String[][] data) {
        String[] columns = columnsName;
        String[][] dataTable = data;
        DefaultTableModel DTM;
        JTable Tabla;
        JScrollPane Barra;
        DTM = new DefaultTableModel(dataTable, columns);
        Tabla = new JTable(DTM);
        Barra = new JScrollPane(Tabla);
        Tabla.setEnabled(false);
        Tabla.setFont(font);
        Tabla.setForeground(tetxColor);
        Tabla.setBackground(tableBg);
        return Barra;
    }

    public int confirmPanel() {
        return JOptionPane.showConfirmDialog(null, "Show Windows",
                "SHOW IN WINDOW OR CONSOLE", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }
    public int confirmPanel(String tittle, String content) {
        return JOptionPane.showConfirmDialog(null, tittle,
                content, JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
