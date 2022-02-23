package controller;

import model.exceptii.DivisionException;
import model.Model;
import model.Operator;
import model.Polynomial;
import model.exceptii.DivisionZeroException;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Reprezinta legatura intre model si view
 */
public class Controller {
    private final View theView;
    private final Model theModel;

    /**
     * Creeaza legatura intre model si view
     *
     * @param theView  interfata pe care se adauga functionalitatile butoanelor
     * @param theModel operatiile care se adauga la interfata in urma apasarii unor butoane
     */
    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addPressedButonListener(new CurrentOperation());
        this.theView.addDerivIntegMouseListener(new MouseOverListener());
        this.theView.addAddListener(new AddListener());
        this.theView.addSubListener(new SubListener());
        this.theView.addMulListener(new MulListener());
        this.theView.addDerivListener(new DerivListener());
        this.theView.addIntegrListener(new IntegrListener());
        this.theView.addDivListener(new DivListener());

    }

    /**
     * Reprezinta operatia care se executa cand mouse-ul este deasupra butoanelor de integrare si derivare
     */
    class MouseOverListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            theView.getPolinom2TxtField().setEnabled(false);
            theView.getPolinom2TxtField().setEditable(false);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            theView.getPolinom2TxtField().setEnabled(true);
            theView.getPolinom2TxtField().setEditable(true);
        }
    }

    /**
     * Reprezinta un ActionListener care modifica cuoarea butoanelor in functie de care buton este apasat. Butonul apsat
     * va avea o culoare albastra, iar celelalte o culoare gri.
     */
    class CurrentOperation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int indx = theView.getSelectedButton();
            List<JButton> buttons = new ArrayList<>();
            buttons.add(theView.getAddButton());
            buttons.add(theView.getSubButton());
            buttons.add(theView.getMulButton());
            buttons.add(theView.getDivButton());
            buttons.add(theView.getDerivButton());
            buttons.add(theView.getIntegrButton());
            int i = 0;
            for (JButton b : buttons) {
                if (i == indx) {
                    b.setBackground(theView.getPressedColorButton());
                } else {
                    b.setBackground(theView.getOriginalColorButton());
                }
                i++;
            }
        }
    }

    /**
     * Reprezinta un ActionListener care, la apasarea butonului de adunare, va calcula suma celor doua polinoame introduse
     * si va afisa rezultatul.
     */
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String p1 = theView.getPolinom1();
            String p2 = theView.getPolinom2();
            theView.setSelectedButton(0);
            try {
                theModel.detPolinom(p1, 1);
                theModel.detPolinom(p2, 2);
                Polynomial polio = Operator.add(theModel.getP1(), theModel.getP2());
                theView.setResult(" ");
                theView.setResult(polio.toString());
                theModel.reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(theView.getAddButton(), ex.getMessage());
            }
        }
    }

    /**
     * Reprezinta un ActionListener care, la apasarea butonului de scadere, va calcula diferenta celor doua polinoame
     * introduse si va afisa rezultatul.
     */
    class SubListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String p1 = theView.getPolinom1();
            String p2 = theView.getPolinom2();
            theView.setSelectedButton(1);
            try {
                theModel.detPolinom(p1, 1);
                theModel.detPolinom(p2, 2);
                Polynomial polio = Operator.sub(theModel.getP1(), theModel.getP2());
                theView.setResult(" ");
                theView.setResult(polio.toString());
                theModel.reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(theView.getSubButton(), ex.getMessage());
            }
        }
    }

    /**
     * Reprezinta un ActionListener care, la apasarea butonului de inmultimre, va calcula produsul celor doua polinoame
     * introduse si va afisa rezultatul.
     */
    class MulListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String p1 = theView.getPolinom1();
            String p2 = theView.getPolinom2();
            theView.setSelectedButton(2);
            try {
                theModel.detPolinom(p1, 1);
                theModel.detPolinom(p2, 2);
                Polynomial polio = Operator.mul(theModel.getP1(), theModel.getP2());
                theView.setResult(" ");
                theView.setResult(polio.toString());
                theModel.reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(theView.getMulButton(), ex.getMessage());
            }
        }
    }

    /**
     * Reprezinta un ActionListener care, la apasarea butonului pentru derivare, va calcula derivata primului polinom
     * si va afisa rezultatul
     */
    class DerivListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String p1 = theView.getPolinom1();
            theView.setSelectedButton(4);
            try {
                theModel.detPolinom(p1, 1);
                Polynomial polio = Operator.deriv(theModel.getP1());
                theView.setResult(" ");
                theView.setResult(polio.toString());
                theModel.reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(theView.getDerivButton(), ex.getMessage());
            }
        }
    }

    /**
     * Reprezinta un ActionListener care, la apasarea butonului pentru integrare, va calcula integrate primului polinom
     * si va afisa rezultatul
     */
    class IntegrListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String p1 = theView.getPolinom1();
            theView.setSelectedButton(5);
            try {
                theModel.detPolinom(p1, 1);
                Polynomial polio = Operator.integr(theModel.getP1());
                theView.setResult(" ");
                theView.setResult(polio.toString());
                theModel.reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(theView.getIntegrButton(), ex.getMessage());
            }
        }
    }

    /**
     * Reprezinta un ActionListener care, la apasarea butonului pentru impartire, va calcula catul si restul impartirii
     * si le va afisa.
     */
    class DivListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String p1 = theView.getPolinom1();
            String p2 = theView.getPolinom2();
            theView.setSelectedButton(3);
            try {

                theModel.detPolinom(p1, 1);
                theModel.detPolinom(p2, 2);
                if (theModel.getP1().getTermens().get(0).getExp() <= theModel.getP2().getTermens().get(0).getExp())
                    throw new DivisionException("Polinomul 1 trebuie sa aiba rangul mai mare decat polinomul 2!");
                if (theModel.getP2().getTermens().get(0).getExp() == 0 && theModel.getP2().getTermens().get(0).getCoef() == 0)
                    throw new DivisionZeroException("Polinomul impartitor trebuie sa fie diferit de zero!");
                Polynomial[] polio = Operator.div(theModel.getP1(), theModel.getP2());
                theView.setResult(" ");
                theView.setResult("Q=" + polio[0] + " ; R=" + polio[1]);
                theModel.reset();
            } catch (IndexOutOfBoundsException ex1) {
                JOptionPane.showMessageDialog(theView.getDivButton(), "Polinomul 2 trebuie specificat pentru aceasta operatie!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(theView.getDivButton(), ex.getMessage());
            }
        }
    }

}