package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezinta un polinom
 */
public class Polynomial {
    /**
     * lista in care sunt memorati termenii polinomului
     */
    private final List<Monomial> termens = new ArrayList<>();

    public Polynomial() {
    }

    public Polynomial(Monomial m) {
        termens.add(m);
    }

    /**
     * Copiaza termenii unui polinom
     *
     * @param p Polynomial, polinomul care va fi copiat
     * @return Polynomial, copia polinomului (nu contine termenii cu coeficientul 0)
     */
    public static Polynomial copy(Polynomial p) {
        Polynomial a = new Polynomial();
        for (Monomial m : p.termens) {
            Monomial mp = Monomial.copy(m);
            if (mp.getCoef() != 0) a.addMonomial(mp);
        }
        return a;
    }

    /**
     * Adauga un termen in polinom
     *
     * @param a Monomial, termenul care va fi adaugat in polinom
     */
    public void addMonomial(Monomial a) {
        termens.add(a);
    }

    /**
     * Extrage termenii polinomului
     *
     * @return lista de Monomial, reprezinta termenii polinomului
     */
    public List<Monomial> getTermens() {
        return termens;
    }

    /**
     * Inverseaza semnele tuturor termenilor polinomului
     *
     * @return Polynomial, polinomul cu semnele inversate
     */
    public Polynomial invert() {
        Polynomial inverted = new Polynomial();
        for (Monomial m : this.termens) {
            inverted.addMonomial(m.invert());
        }
        return inverted;
    }

    /**
     * Cauta indicele termenului care are un anumit exponent
     *
     * @param exp Integer, exponentul cautat
     * @return Integer, pozitia la care se afla termenul respectiv sau -1 daca nu exista
     */
    public int searchExp(int exp) {
        int i = 0;
        for (Monomial m : this.termens) {
            if (m.getExp() == exp) return i;
            i++;
        }
        return -1;
    }

    /**
     * Modifica coeficientul unui termen din polinom
     *
     * @param coef Double, noul coeficient
     * @param indx Integer, pozitia termenului care va fi modificat
     */
    public void setMonomial(double coef, int indx) {
        termens.get(indx).setCoef(coef);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Monomial m : termens) {
            sb.append(m.toString());
        }
        if (sb.toString().length() < 1) return "0";
        return sb.toString();
    }

}
