package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Reprezinta interfata grafica pentru calculatorul de polinoame
 */
public class View extends JFrame {
    private int selectedButton = 0;
    private final Color originalColorButton = new Color(210, 210, 255);
    private final Color pressedColorButton = new Color(148, 181, 250);

    private final JTextField polinom1TxtField = new JTextField(30);
    private final JTextField polinom2TxtField = new JTextField(30);
    private final JButton addButton = new JButton("+");
    private final JButton subButton = new JButton("-");
    private final JButton mulButton = new JButton("*");
    private final JButton divButton = new JButton("\\");
    private final JButton derivButton = new JButton("deriv");
    private final JButton integrButton = new JButton("integr");
    private final JTextField resultTxtField = new JTextField(30);

    /**
     * Creeaza interfata grafica
     */
    public View() {
        JPanel panelBox = new JPanel();
        panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.PAGE_AXIS));
        JPanel panelFlowP1 = new JPanel();
        JPanel panelFlowOp = new JPanel();
        JPanel panelFlowP2 = new JPanel();
        JPanel panelFlowResult = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 250);
        this.setLocation(500, 200);

        polinom1TxtField.setFont(new Font("Serif", Font.BOLD, 20));

        polinom2TxtField.setFont(new Font("Serif", Font.BOLD, 20));

        resultTxtField.setEditable(false);
        resultTxtField.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel polinom1Label = new JLabel("Polinomul 1");
        panelFlowP1.add(polinom1Label);
        panelFlowP1.add(polinom1TxtField);

        addButton.setBackground(originalColorButton);
        panelFlowOp.add(addButton);
        subButton.setBackground(originalColorButton);
        panelFlowOp.add(subButton);
        mulButton.setBackground(originalColorButton);
        panelFlowOp.add(mulButton);
        divButton.setBackground(originalColorButton);
        panelFlowOp.add(divButton);
        derivButton.setBackground(originalColorButton);
        panelFlowOp.add(derivButton);
        integrButton.setBackground(originalColorButton);
        panelFlowOp.add(integrButton);

        JLabel polinom2Label = new JLabel("Polinomul 2");
        panelFlowP2.add(polinom2Label);
        panelFlowP2.add(polinom2TxtField);

        JLabel resultLabel = new JLabel("Rezultat: ");
        panelFlowResult.add(resultLabel);
        panelFlowResult.add(resultTxtField);

        panelBox.add(panelFlowP1);
        panelBox.add(panelFlowOp);
        panelBox.add(panelFlowP2);
        panelBox.add(panelFlowResult);

        this.add(panelBox);
    }

    /**
     * Adauga un ActionListener pe butoane.
     *
     * @param ac ActionListener care modifica culoarea butoanelor in functie de care este apasat
     */
    public void addPressedButonListener(ActionListener ac) {
        addButton.addActionListener(ac);
        subButton.addActionListener(ac);
        mulButton.addActionListener(ac);
        divButton.addActionListener(ac);
        derivButton.addActionListener(ac);
        integrButton.addActionListener(ac);
    }

    /**
     * Adauga pe butoanele de integrare si derivare un mouse listener.
     *
     * @param listener MouseListener, cand mouse-ul este deasupra unuia dintre cele 2
     *                 butoane, campul cu al doilea polinom devine needitabil.
     */
    public void addDerivIntegMouseListener(MouseListener listener) {
        derivButton.addMouseListener(listener);
        integrButton.addMouseListener(listener);
    }

    /**
     * Adauga un ActionListener pe butonul de adunare.
     *
     * @param listener ActionListener, descrie functionalitatea operatiei de adunare
     */
    public void addAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    /**
     * Adauga un ActionListener pe butonul de scadere.
     *
     * @param listener ActionListener, descrie functionalitatea operatiei de scadere
     */
    public void addSubListener(ActionListener listener) {
        subButton.addActionListener(listener);
    }

    /**
     * Adauga un ActionListener pe butonul de inmultire.
     *
     * @param listener ActionListener, descrie functionalitatea operatiei de inmultire
     */
    public void addMulListener(ActionListener listener) {
        mulButton.addActionListener(listener);
    }

    /**
     * Adauga un ActionListener pe butonul de derivare.
     *
     * @param listener ActionListener, descrie functionalitatea operatiei de derivare
     */
    public void addDerivListener(ActionListener listener) {
        derivButton.addActionListener(listener);
    }

    /**
     * Adauga un ActionListener pe butonul de integrare.
     *
     * @param listener ActionListener, descrie functionalitatea operatiei de integrare
     */
    public void addIntegrListener(ActionListener listener) {
        integrButton.addActionListener(listener);
    }

    /**
     * Adauga un ActionListener pe butonul de impartire.
     *
     * @param listener ActionListener, descrie functionalitatea operatiei de impartire
     */
    public void addDivListener(ActionListener listener) {
        divButton.addActionListener(listener);
    }

    /**
     * Extrage primul polinom introdus.
     *
     * @return String care reprezinta polinomul 1
     */
    public String getPolinom1() {
        return polinom1TxtField.getText();
    }

    /**
     * Extrage al doilea polinom introdus.
     *
     * @return String care reprezinta polinomul 2
     */
    public String getPolinom2() {
        return polinom2TxtField.getText();
    }

    /**
     * Extrage componenta in care este scris al doilea polinom.
     *
     * @return JTextField, componenta in care se scrie al doilea polinom
     */
    public JTextField getPolinom2TxtField() {
        return polinom2TxtField;
    }

    /**
     * Seteaza rezultatul in campul pentru rezultat
     *
     * @param s String, rezultatul sub forma de string
     */
    public void setResult(String s) {
        resultTxtField.setText(s);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubButton() {
        return subButton;
    }

    public JButton getMulButton() {
        return mulButton;
    }

    public JButton getDivButton() {
        return divButton;
    }

    public JButton getDerivButton() {
        return derivButton;
    }

    public JButton getIntegrButton() {
        return integrButton;
    }

    public int getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton;
    }

    public Color getPressedColorButton() {
        return pressedColorButton;
    }

    public Color getOriginalColorButton() {
        return originalColorButton;
    }
}