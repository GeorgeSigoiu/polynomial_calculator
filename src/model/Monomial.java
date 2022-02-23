package model;

import java.text.DecimalFormat;

/**
 * Reprezinta un termen dintr-un polinom
 */
public class Monomial implements Comparable {
    /**
     * coeficientul termenului polinomului
     */
    private double coef;
    /**
     * exponentul termenului polinomului
     */
    private final int exp;

    /**
     * Creeaza un termen al polinomului
     *
     * @param coef Double, reprezinta coeficientul termenului
     * @param exp  Integer, reprezinta exponentul coeficientului
     */
    public Monomial(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    /**
     * Extrage coeficientul termenului polinomului
     *
     * @return Double, coeficientul
     */
    public double getCoef() {
        return coef;
    }

    /**
     * Creeaza o copie a termenului
     *
     * @param m Monomial, termenul ce trebuie copiat
     * @return Monomial, o copie a termenului
     */
    public static Monomial copy(Monomial m) {
        return new Monomial(m.getCoef(), m.getExp());
    }

    /**
     * Seteaza coeficientul termenului
     *
     * @param coef Double, coeficientul
     */
    public void setCoef(double coef) {
        this.coef = coef;
    }

    /**
     * Extrage exponentul termenului
     *
     * @return Integer, exponentul
     */
    public int getExp() {
        return exp;
    }

    /**
     * Inverseaza semnul termenului
     *
     * @return Monomial, un termen nou cu coeficientul opus
     */
    public Monomial invert() {
        return new Monomial(-this.coef, this.exp);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        if (coef == 0) return "";
        if (coef == 1) {
            if (exp == 0) return "+1";
            else if (exp == 1) return "+x";
            else return "+x^" + exp;
        } else if (coef == -1) {
            if (exp == 0) return "-1";
            else if (exp == 1) return "-x";
            else return "-x^" + exp;
        } else if (coef > 0) {
            if (coef - (int) coef < 0.00001) {
                if (exp == 0) return "+" + (int) coef;
                else if (exp == 1) return "+" + (int) coef + "x";
                else return "+" + (int) coef + "x^" + exp;
            } else {
                if (exp == 0) return "+" + df.format(coef);
                else if (exp == 1) return "+" + df.format(coef) + "x";
                else return "+" + df.format(coef) + "x^" + exp;
            }
        } else {
            if (coef - (int) coef < 0.00001) {
                if (exp == 0) return "" + (int) coef;
                else if (exp == 1) return (int) coef + "x";
                else return (int) coef + "x^" + exp;
            } else {
                if (exp == 0) return "" + df.format(coef);
                else if (exp == 1) return df.format(coef) + "x";
                else return df.format(coef) + "x^" + exp;
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        return ((Monomial) o).getExp() - this.getExp();
    }
}
