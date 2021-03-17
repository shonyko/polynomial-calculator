package models.utils;

import exceptions.PolynomialDivisionByZeroException;
import models.Monomial;
import models.Polynomial;

public class PolynomialCalculator {
    private PolynomialCalculator() {

    }

    public static Polynomial add(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        var result = firstPolynomial.clone();

        for(var monomial : secondPolynomial.getMonomialList()) {
            result.addMonomial(monomial);
        }

        return result.prune().orderByExponent();
    }

    public static Polynomial subtract(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        secondPolynomial = multiply(secondPolynomial, -1);

        return add(firstPolynomial, secondPolynomial);
    }

    public static Polynomial multiply(Polynomial polynomial, double value) {
        var result = polynomial.clone();

        for(var monomial : result.getMonomialList()) {
            var coefficient = monomial.getCoefficient();
            monomial.setCoefficient(coefficient * value);
        }

        return result.prune().orderByExponent();
    }

    public static Polynomial multiply(Polynomial polynomial, Monomial monomial) {
        var result = new Polynomial();

        for(var mono : polynomial.getMonomialList()) {
            var term = MonomialCalculator.multiply(mono, monomial);
            result.addMonomial(term);
        }

        return result.prune().orderByExponent();
    }

    public static Polynomial multiply(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        var result = new Polynomial();

        for(var monomial : secondPolynomial.getMonomialList()) {
            result = add(result, multiply(firstPolynomial, monomial));
        }

        return result;
    }

    private static Polynomial divide(Polynomial firstPolynomial, Polynomial secondPolynomial, boolean remainder) throws PolynomialDivisionByZeroException {
        firstPolynomial = firstPolynomial.clone().prune().orderByExponent();
        secondPolynomial = secondPolynomial.clone().prune().orderByExponent();

        if(secondPolynomial.isEmpty()) {
            throw new PolynomialDivisionByZeroException();
        }

        var result = new Polynomial();
        var secondPolynomialDegree = secondPolynomial.getDegree();
        while (!firstPolynomial.isEmpty() && secondPolynomialDegree <= firstPolynomial.getDegree()) {
            var firstMonomial = firstPolynomial.getMonomial(0);
            var secondMonomial = secondPolynomial.getMonomial(0);
            var monomial = MonomialCalculator.divide(firstMonomial, secondMonomial);
            result.addMonomial(monomial);

            var auxPolynomial = multiply(secondPolynomial, monomial);
            firstPolynomial = subtract(firstPolynomial, auxPolynomial);
        }

        return (remainder ? firstPolynomial : result).prune().orderByExponent();
    }

    public static Polynomial getDivisionResult(Polynomial firstPolynomial, Polynomial secondPolynomial) throws PolynomialDivisionByZeroException {
        return divide(firstPolynomial, secondPolynomial, false);
    }

    public static Polynomial getDivisionRemainder(Polynomial firstPolynomial, Polynomial secondPolynomial) throws PolynomialDivisionByZeroException {
        return divide(firstPolynomial, secondPolynomial, true);
    }

    public static Polynomial derive(Polynomial polynomial) {
        var result = new Polynomial();

        for(var monomial : polynomial.getMonomialList()) {
            var toAdd = MonomialCalculator.derive(monomial);
            result.addMonomial(toAdd);
        }

        return result.prune().orderByExponent();
    }

    public static Polynomial integrate(Polynomial polynomial) {
        var result = new Polynomial();

        for(var monomial : polynomial.getMonomialList()) {
            var toAdd = MonomialCalculator.integrate(monomial);
            result.addMonomial(toAdd);
        }

        result.addMonomial(new Monomial(true));
        return result.prune().orderByExponent();
    }
}
