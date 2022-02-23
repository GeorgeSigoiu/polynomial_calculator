import model.*;
import model.exceptii.SpaceException;
import model.exceptii.WrongFormatInputException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testare {

    @Test
    public void testAdunare() throws SpaceException, WrongFormatInputException {
        Model theModel = new Model();
        theModel.detPolinom("x^2+1",1);
        theModel.detPolinom("-x+2",2);
        Polynomial polio= Operator.add(theModel.getP1(),theModel.getP2());
        assertEquals("+x^2-x+3",polio.toString());
        theModel.reset();

        theModel.detPolinom("3x^3+x^2+10",1);
        theModel.detPolinom("-3x^2+x-2",2);
        polio= Operator.add(theModel.getP1(),theModel.getP2());
        assertEquals("+3x^3-2x^2+x+8",polio.toString());
        theModel.reset();

        theModel.detPolinom("x^2-x-1",1);
        theModel.detPolinom("+1",2);
        polio= Operator.add(theModel.getP1(),theModel.getP2());
        assertEquals("+x^2-x",polio.toString());
        theModel.reset();

        theModel.detPolinom("x^20+x^10",1);
        theModel.detPolinom("-x^11-1",2);
        polio= Operator.add(theModel.getP1(),theModel.getP2());
        assertEquals("+x^20-x^11+x^10-1",polio.toString());
        theModel.reset();
    }

    @Test
    public void testScadere() throws SpaceException, WrongFormatInputException {

        Model theModel=new Model();
        theModel.detPolinom("4x^4-x^3+6x^2-3",1);
        theModel.detPolinom("5x^5+x^3-3",2);
        Polynomial polio=Operator.sub(theModel.getP1(),theModel.getP2());
        assertEquals("-5x^5+4x^4-2x^3+6x^2",polio.toString());
        theModel.reset();

        theModel.detPolinom("x+1",1);
        theModel.detPolinom("2x+1",2);
        polio=Operator.sub(theModel.getP1(),theModel.getP2());
        assertEquals("-x",polio.toString());
        theModel.reset();

        theModel.detPolinom("6x+2",1);
        theModel.detPolinom("4x^2",2);
        polio=Operator.sub(theModel.getP1(),theModel.getP2());
        assertEquals("-4x^2+6x+2",polio.toString());
        theModel.reset();

        theModel.detPolinom("1",1);
        theModel.detPolinom("x^20+x^10-1",2);
        polio=Operator.sub(theModel.getP1(),theModel.getP2());
        assertEquals("-x^20-x^10+2",polio.toString());
        theModel.reset();

        theModel.detPolinom("160x^2+140x+10",1);
        theModel.detPolinom("3x^3",2);
        polio=Operator.sub(theModel.getP1(),theModel.getP2());
        assertEquals("-3x^3+160x^2+140x+10",polio.toString());
        theModel.reset();
    }

    @Test
    public void testInmultire() throws SpaceException, WrongFormatInputException {
        Model theModel=new Model();
        theModel.detPolinom("x^2+1",1);
        theModel.detPolinom("x^3-x",2);
        Polynomial polio=Operator.mul(theModel.getP1(),theModel.getP2());
        assertEquals("+x^5-x",polio.toString());
        theModel.reset();

        theModel.detPolinom("x+1",1);
        theModel.detPolinom("x+1",2);
        polio=Operator.mul(theModel.getP1(),theModel.getP2());
        assertEquals("+x^2+2x+1",polio.toString());
        theModel.reset();

        theModel.detPolinom("x^4+x^3+x^2+x+1",1);
        theModel.detPolinom("x-1",2);
        polio=Operator.mul(theModel.getP1(),theModel.getP2());
        assertEquals("+x^5-1",polio.toString());
        theModel.reset();
    }

    @Test
    public void testDerivare() throws SpaceException, WrongFormatInputException {
        Model theModel=new Model();
        theModel.detPolinom("5x^3-4x^2+x-3",1);
        Polynomial polio=Operator.deriv(theModel.getP1());
        assertEquals("+15x^2-8x+1",polio.toString());
        theModel.reset();

        theModel.detPolinom("x+10",1);
        polio=Operator.deriv(theModel.getP1());
        assertEquals("+1",polio.toString());
        theModel.reset();

        theModel.detPolinom("-x^6+2x^5-x^4+1",1);
        polio=Operator.deriv(theModel.getP1());
        assertEquals("-6x^5+10x^4-4x^3",polio.toString());
        theModel.reset();
    }

    @Test
    public void testIntegrare() throws SpaceException, WrongFormatInputException {
        Model theModel=new Model();
        theModel.detPolinom("x^3+3x^2+x+1",1);
        Polynomial polio=Operator.integr(theModel.getP1());
        assertEquals("+0.25x^4+x^3+0.50x^2+x",polio.toString());
        theModel.reset();

        theModel.detPolinom("x^3",1);
        polio=Operator.integr(theModel.getP1());
        assertEquals("+0.25x^4",polio.toString());
        theModel.reset();

        theModel.detPolinom("7",1);
        polio=Operator.integr(theModel.getP1());
        assertEquals("+7x",polio.toString());
        theModel.reset();
    }

    @Test
    public void testImpartire() throws SpaceException, WrongFormatInputException {
        Model theModel=new Model();
        theModel.detPolinom("x^3-2x^2+6x-5",1);
        theModel.detPolinom("x^2-1",2);
        Polynomial[] polio=Operator.div(theModel.getP1(),theModel.getP2());
        assertEquals("Q=+x-2 ; R=+7x-7","Q="+polio[0]+" ; R="+polio[1]);
        theModel.reset();

        theModel.detPolinom("6x^4+3x^3+2x",1);
        theModel.detPolinom("x+3",2);
        polio=Operator.div(theModel.getP1(),theModel.getP2());
        assertEquals("Q=+6x^3-15x^2+45x-133 ; R=+399","Q="+polio[0]+" ; R="+polio[1]);
        theModel.reset();

        theModel.detPolinom("x^20-x^5+6x^2-3",1);
        theModel.detPolinom("x^10",2);
        polio=Operator.div(theModel.getP1(),theModel.getP2());
        assertEquals("Q=+x^10 ; R=-x^5+6x^2-3","Q="+polio[0]+" ; R="+polio[1]);
        theModel.reset();
    }
}
