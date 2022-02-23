package model;

import java.util.Collections;

/**
 * Reprezinta operatiile care se efectueaza pe polinoame
 */
public class Operator {
    /**
     * Realizeaza adunarea a doi termeni
     *
     * @param a Monomial, primul termen
     * @param b Monomial, al doilea termen
     * @return Monomial, termenul rezultat in urma adunarii celor 2 termeni
     */
    public static Monomial add(Monomial a, Monomial b) {
        if (a.getExp() == b.getExp()) return new Monomial(a.getCoef() + b.getCoef(), a.getExp());
        else return null;
    }

    /**
     * Realizeaza inmultirea a doi termeni
     *
     * @param a Monomial, primul termen
     * @param b Monomial, al doilea termen
     * @return Monomial, termenul rezultat in urma inmultirii
     */
    public static Monomial mul(Monomial a, Monomial b) {
        return new Monomial(a.getCoef() * b.getCoef(), a.getExp() + b.getExp());
    }

    /**
     * Realizeaza imparitrea a doi termeni
     *
     * @param a Monomial, primul termen
     * @param b Monomial, al doilea termen
     * @return Monomial, termenul rezultat in urma impartirii
     */
    public static Monomial div(Monomial a, Monomial b) {
        double c = a.getCoef() / b.getCoef();
        int e = a.getExp() - b.getExp();
        return new Monomial(c, e);
    }

    /**
     * Realizeaza adunarea a 2 polinoame
     *
     * @param p1 Polynomial, primul polinom
     * @param p2 Polynomial, al doilea polinom
     * @return Polynomial, rezultatul adunarii
     */
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial polinom = new Polynomial();
        p1 = Operator.mul(p1, new Polynomial(new Monomial(1, 0)));
        p2 = Operator.mul(p2, new Polynomial(new Monomial(1, 0)));

        for (Monomial m1 : p1.getTermens()) {
            boolean adaugat = false;
            for (Monomial m2 : p2.getTermens()) {
                if (m1.getExp() == m2.getExp()) {
                    polinom.addMonomial(Operator.add(m1, m2));
                    adaugat = true;
                }
            }
            if (!adaugat) polinom.addMonomial(m1);
        }

        Polynomial aux = new Polynomial();
        for (Monomial m2 : p2.getTermens()) {
            boolean adaugat = false;
            for (Monomial m1 : polinom.getTermens()) {
                if (m2.getExp() == m1.getExp()) adaugat = true;
            }
            if (!adaugat) aux.addMonomial(m2);
        }
        for (Monomial m : aux.getTermens())
            polinom.addMonomial(m);
        Collections.sort(polinom.getTermens());
        return polinom;
    }

    /**
     * Realizeaza scaderea a 2 polinoame
     *
     * @param p1 Polynomial, primul polinom
     * @param p2 Polynomial, al doilea polinom
     * @return Polynomial, rezultatul scaderii
     */
    public static Polynomial sub(Polynomial p1, Polynomial p2) {
        Polynomial polinom = Operator.add(p1, p2.invert());
        return polinom;
    }

    /**
     * Realizeaza inmultirea intre 2 polinoame
     *
     * @param p1 Polynomial, primul polinom
     * @param p2 Polynomial, al doilea polinom
     * @return Polynomial, reprezinta rezultatul inmultirii celor 2 polinoame
     */
    public static Polynomial mul(Polynomial p1, Polynomial p2) {
        Polynomial polinom = new Polynomial();
        for (Monomial m1 : p1.getTermens()) {
            for (Monomial m2 : p2.getTermens()) {
                Monomial m = Operator.mul(m1, m2);
                int gasit = polinom.searchExp(m.getExp());
                if (gasit == -1) polinom.addMonomial(m);
                else {
                    double c = m.getCoef() + polinom.getTermens().get(gasit).getCoef();
                    polinom.setMonomial(c, gasit);
                }
            }
        }
        Collections.sort(polinom.getTermens());
        return polinom;
    }

    /**
     * Reazlizeaza derivarea unui polinom
     *
     * @param p Polynomial, polinomul care va fi derivat
     * @return Polynomial, polinomul rezultat
     */
    public static Polynomial deriv(Polynomial p) {
        Polynomial polinom = new Polynomial();
        p = Operator.mul(p, new Polynomial(new Monomial(1, 0)));//ptr expr x+x+x+1
        for (Monomial m : p.getTermens()) {
            Monomial termen = new Monomial(m.getCoef() * m.getExp(), m.getExp() > 0 ? m.getExp() - 1 : 0);
            polinom.addMonomial(termen);
        }
        return polinom;
    }

    /**
     * Reazlizeaza integrarea unui polinom
     *
     * @param p Polynomial, polinomul care va fi integrat
     * @return Polynomial, polinomul rezultat
     */
    public static Polynomial integr(Polynomial p) {
        Polynomial polinom = new Polynomial();
        p = Operator.mul(p, new Polynomial(new Monomial(1, 0)));//ptr expr x+x+x+1
        for (Monomial m : p.getTermens()) {
            double c = (double) m.getCoef() * (double) ((double) 1 / (double) (m.getExp() + 1));
            Monomial termen = new Monomial(c, m.getExp() + 1);
            polinom.addMonomial(termen);
        }
        return polinom;
    }

    /**
     * Realizeaza impartirea intre 2 polinoame
     *
     * @param p1 Polynomial, deimpartitul
     * @param p2 Polunomial, impartitorul
     * @return vector de Polynomial, pe pozitia 0 se afla catul, pe pozitia 1, restul
     */
    public static Polynomial[] div(Polynomial p1, Polynomial p2) {
        Polynomial[] polinom = new Polynomial[2];

        Polynomial Q = new Polynomial();
        int rangP1 = p1.getTermens().get(0).getExp();
        int rangP2 = p2.getTermens().get(0).getExp();

        while (rangP1 >= rangP2) {
            Monomial m = Operator.div(p1.getTermens().get(0), p2.getTermens().get(0));
            Q.addMonomial(m);
            Polynomial p2m = Operator.mul(p2, new Polynomial(m));
            Polynomial result = Operator.sub(p1, p2m);
            p1 = Polynomial.copy(result);
            if (p1.getTermens().size() < 1)
                rangP1 = 0;//daca se impart si nu ramane rest o sa dea eroare cand face .getTermens.get(0) pentru ca nu va fi nimic pe pozitia aceea
            else rangP1 = p1.getTermens().get(0).getExp();
        }
        polinom[0] = Q;
        polinom[1] = p1;
        return polinom;
    }
}
