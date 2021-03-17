package models.utils;

import exceptions.PolynomialDivisionByZeroException;
import exceptions.PolynomialFormatNotSupportedException;
import models.Monomial;
import models.Polynomial;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialCalculatorTest {
    private static int nbTests;
    private static int nbTestsPassed;

    @BeforeAll
    public static void init() {
        nbTests = nbTestsPassed = 0;
    }

    @BeforeEach
    public void next() {
        nbTests++;
    }

    @AfterAll
    public static void done() {
        System.out.println(nbTests + " tests done - " + nbTestsPassed + " tests passed");
    }

    @org.junit.jupiter.api.Test
    void add() throws PolynomialFormatNotSupportedException {
        var firstPolynomial = Polynomial.fromString("x^3 - 5x^2 + 7x +3");
        var secondPolynomial= Polynomial.fromString("x^2 - 3x + 1");
        var expected = Polynomial.fromString("x^3 - 4x^2 + 4x + 4");

        var result = PolynomialCalculator.add(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        firstPolynomial  = Polynomial.fromString("3x^2 + 2x + 1 + 4x^3");
        secondPolynomial = Polynomial.fromString("-x^3 +5x + 1 + 4x^4");
        expected         = Polynomial.fromString("4x^4 + 3x^3 + 3x^2 + 7x + 2");

        result = PolynomialCalculator.add(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }

    @org.junit.jupiter.api.Test
    void subtract() throws PolynomialFormatNotSupportedException {
        var firstPolynomial = Polynomial.fromString("x^2 - 1");
        var secondPolynomial= Polynomial.fromString("x^3 + 2x^2 - 1");
        var expected        = Polynomial.fromString("-x^3 - x^2");

        var result = PolynomialCalculator.subtract(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        firstPolynomial  = Polynomial.fromString("-7x^3 + 3x - 4 + 11x^4");
        secondPolynomial = Polynomial.fromString("5x^2 + 2x + 1 + 4x^3");
        expected         = Polynomial.fromString("11x^4 - 11x^3 - 5x^2 + x - 5");

        result = PolynomialCalculator.subtract(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }

    @org.junit.jupiter.api.Test
    void multiply() throws PolynomialFormatNotSupportedException {
        var firstPolynomial = Polynomial.fromString("x^4 - 7x^2 + 3");
        var secondPolynomial= Polynomial.fromString("x^3 - 2x");
        var expected        = Polynomial.fromString("x^7 - 9x^5 + 17x^3 - 6x");

        var result = PolynomialCalculator.multiply(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        firstPolynomial  = Polynomial.fromString("3x^2 - x^7 + 5");
        secondPolynomial = Polynomial.fromString("4 + 2x^2");
        expected         = Polynomial.fromString("-2x^9 - 4x^7 + 6x^4 + 22x^2 + 20");

        result = PolynomialCalculator.multiply(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }

    @org.junit.jupiter.api.Test
    void getDivisionResult() throws PolynomialFormatNotSupportedException, PolynomialDivisionByZeroException {
        var firstPolynomial = Polynomial.fromString("x^2 + 1");
        var secondPolynomial= Polynomial.fromString("x + 1");
        var expected        = Polynomial.fromString("x - 1");

        var result = PolynomialCalculator.getDivisionResult(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        firstPolynomial  = Polynomial.fromString("x^2 + 5");
        secondPolynomial = Polynomial.fromString("0");
        Polynomial finalFirstPolynomial = firstPolynomial;
        Polynomial finalSecondPolynomial = secondPolynomial;
        assertThrows(PolynomialDivisionByZeroException.class, () -> PolynomialCalculator.getDivisionResult(finalFirstPolynomial, finalSecondPolynomial));

        firstPolynomial  = Polynomial.fromString("x^2 + 7x - 11");
        secondPolynomial = Polynomial.fromString("x^7 + 5x^2 + 3x + 19");
        expected         = Polynomial.fromString("0");

        result = PolynomialCalculator.getDivisionResult(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }

    @org.junit.jupiter.api.Test
    void getDivisionRemainder() throws PolynomialFormatNotSupportedException, PolynomialDivisionByZeroException {
        var firstPolynomial = Polynomial.fromString("x^2 + 1");
        var secondPolynomial= Polynomial.fromString("x + 1");
        var expected        = Polynomial.fromString("2");

        var result = PolynomialCalculator.getDivisionRemainder(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);


        firstPolynomial  = Polynomial.fromString("x");
        secondPolynomial = Polynomial.fromString("0");
        Polynomial finalFirstPolynomial = firstPolynomial;
        Polynomial finalSecondPolynomial = secondPolynomial;
        assertThrows(PolynomialDivisionByZeroException.class, () -> PolynomialCalculator.getDivisionRemainder(finalFirstPolynomial, finalSecondPolynomial));

        firstPolynomial  = Polynomial.fromString("x^2 + 7x - 11");
        secondPolynomial = Polynomial.fromString("x^7 + 5x^2 + 3x + 19");
        expected         = Polynomial.fromString("x^2 + 7x - 11");

        result = PolynomialCalculator.getDivisionRemainder(firstPolynomial, secondPolynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }

    @org.junit.jupiter.api.Test
    void derive() throws PolynomialFormatNotSupportedException {
        var polynomial = Polynomial.fromString("x^5 - 12x^4 + 3x^3 - 7x^2 + 420");
        var expected   = Polynomial.fromString("5x^4 - 48x^3 + 9x^2 - 14x");

        var result = PolynomialCalculator.derive(polynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }

    @org.junit.jupiter.api.Test
    void integrate() throws PolynomialFormatNotSupportedException {
        var polynomial = Polynomial.fromString("4x^3 - x^2 + 4x - 11");
        var expected   = new Polynomial()
                                        .addMonomial(new Monomial(1, 4))
                                        .addMonomial(new Monomial(-1 / 3.0, 3))
                                        .addMonomial(new Monomial(2, 2))
                                        .addMonomial(new Monomial(-11, 1))
                                        .addMonomial(new Monomial(true));

        var result = PolynomialCalculator.integrate(polynomial);
        assertEquals(expected, result);

        nbTestsPassed++;
    }
}