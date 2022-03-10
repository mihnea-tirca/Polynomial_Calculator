package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import GUI.Controller;
import GUI.Polynomial;

public class AppTest 
{

    @Test
    public void addTest(){
        Polynomial firstPolynomial = Controller.stringToPolynomial("5x^11+7x^9+4x^5+5x^3+8x^0");
        Polynomial secondPolynomial = Controller.stringToPolynomial("10x^10-7x^9+4x^5+6x^4+3x^0");
        Polynomial result = Controller.stringToPolynomial("5x^11+10x^10+8x^5+6x^4+5x^3+11x^0");
        Assertions.assertEquals(result.toString(), firstPolynomial.add(secondPolynomial).toString());
    }

    @Test
    public void subtractTest(){
        Polynomial firstPolynomial = Controller.stringToPolynomial("7x^9+4x^5+5x^3+8x^0");
        Polynomial secondPolynomial = Controller.stringToPolynomial("4x^10+4x^5+6x^4+3x^0");
        Polynomial result = Controller.stringToPolynomial("-4x^10+7x^9-6x^4+5x^3+5x^0");
        Assertions.assertEquals(result.toString(), firstPolynomial.subtract(secondPolynomial).toString());
    }

    @Test
    public void multiplyTest(){
        Polynomial firstPolynomial = Controller.stringToPolynomial("7x^9+4x^5+5x^3+8x^0");
        Polynomial secondPolynomial = Controller.stringToPolynomial("4x^10+4x^5+6x^4+3x^0");
        Polynomial result = Controller.stringToPolynomial("28x^19+16x^15+28x^14+62x^13+48x^10+45x^9+20x^8+30x^7+44x^5+48x^4+15x^3+24x^0");
        Assertions.assertEquals(result.toString(), firstPolynomial.multiply(secondPolynomial).toString());
    }

    @Test
    public void divideTest(){
        Polynomial firstPolynomial = Controller.stringToPolynomial("1x^3-2x^2+6x^1-5x^0");
        Polynomial secondPolynomial = Controller.stringToPolynomial("1x^2-1x^0");
        Polynomial[] results = new Polynomial[2];
        Assertions.assertEquals("1x^1-2x^0", firstPolynomial.divide(secondPolynomial)[0].toString());
        Assertions.assertEquals("7x^1-7x^0", firstPolynomial.divide(secondPolynomial)[1].toString());
    }

    @Test
    public void deriveTest(){
        Polynomial firstPolynomial = Controller.stringToPolynomial("7x^9+4x^5+5x^3+8x^0");
        Polynomial result = Controller.stringToPolynomial("63x^8+20x^4+15x^2");
        Assertions.assertEquals(result.toString(), firstPolynomial.derive().toString());
    }

    @Test
    public void integrateTest() {
        Polynomial firstPolynomial = Controller.stringToPolynomial("7x^9+4x^5+5x^3+8x^0");
        Assertions.assertEquals("0.70x^10+0.67x^6+1.25x^4+8.00x^1", firstPolynomial.integrate().doubleToString());
    }
}
