package model;

import model.exceptii.SpaceException;
import model.exceptii.WrongFormatInputException;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reprezinta operatiile de determinare a polinoamelor introduse
 */
public class Model {
    private Polynomial p1;
    private Polynomial p2;

    public Model() {
        p1 = new Polynomial();
        p2 = new Polynomial();
    }

    /**
     * Arunca o exceptie daca nu sunt indeplinite anumite conditii in ceea ce priveste modul in care polinoamele au fost introduse.
     *
     * @param s String, reprezinta polinomul introdus
     * @throws SpaceException            exceptie aruncata daca polinomul a fost introdus cu spatii albe intre termeni
     * @throws WrongFormatInputException exceptie aruncata daca polinoamele contin alte caractere decat x,+,-,^
     */
    private void caractereNepermise(String s) throws SpaceException, WrongFormatInputException {
        boolean existsSpace = s.contains(" ");
        if (existsSpace) throw new SpaceException("Scrieti polinoamele fara spatii intre termeni!");
        String[] reg = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "c", "v", "b", "n", "m", ",", ".", "<", ">", ";", ":", "!", "@", "#", "%", "&"};
        for (String s1 : reg)
            if (s.contains(s1)) throw new WrongFormatInputException("Caractere permise doar: cifre, 'x', +, -, ^");
    }

    /**
     * Creeaza polinomul dintr-un string sub forma unui obiect Polynomial
     *
     * @param s String, care reprezinta polinomul introdus
     * @param i Integer, 1- a fost introdul polinomul 1, 2- a fost introdus polinomul 2
     */
    public void detPolinom(String s, int i) {
        try {
            caractereNepermise(s);
        } catch (SpaceException | WrongFormatInputException e) {
            e.printStackTrace();
        }
        String expr = updatePolinom(s);
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(expr);
        int x = 0;
        while (matcher.find()) {
            String[] termen = matcher.group(1).split("x");

            double coef = 1d;
            int exp = 0;

            if (termen[0] != null && termen[0].length() > 0) {
                coef = Double.parseDouble(termen[0]);
            }

            if (termen.length > 1) {
                if (termen[1] != null && termen[1].length() > 0) {
                    String t = termen[1].substring(1);
                    exp = Integer.parseInt(t);
                }
            } else if (matcher.group(1).contains("x")) exp = 1;

            Monomial m = new Monomial(coef, exp);
            if (i == 1) p1.addMonomial(m);
            else p2.addMonomial(m);
        }
        if (i == 1) Collections.sort(p1.getTermens());
        else Collections.sort(p2.getTermens());
    }

    /**
     * Actualizeaza forma polinomului. Daca polinomul este de forma x^2+x -> 1x^2+1x
     *
     * @param s String, reprezinta polinomul care va fi modificat
     * @return String, reprezinta polinomul modificat
     */
    private String updatePolinom(String s) {
        StringBuilder sb = new StringBuilder();
        char[] pol = s.toCharArray();
        int indx = 0;
        char last_char = ' ';
        for (char c : pol) {
            if (indx == 0 && c == 'x') {
                sb.append('1');
            } else if ((last_char == '+' || last_char == '-') && c == 'x') {
                sb.append('1');
            }
            indx++;
            last_char = c;
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Extrage polinomul 1
     *
     * @return Polynomial, polinomul 1
     */
    public Polynomial getP1() {
        return p1;
    }

    /**
     * Extrage polinomul 2
     *
     * @return Polynomial, polinomul 2
     */
    public Polynomial getP2() {
        return p2;
    }

    /**
     * Reseteaza polinoamele pentru introducerea de noi valori.
     */
    public void reset() {
        p1 = new Polynomial();
        p2 = new Polynomial();
    }
}