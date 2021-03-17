package models.utils;

import models.Monomial;

public class MonomialCalculator {
    private MonomialCalculator() {

    }

    public static Monomial multiply(Monomial monomial, double value) {
        var result = monomial.clone();

        var coefficient = monomial.getCoefficient();
        monomial.setCoefficient(coefficient * value);

        return result;
    }

    public static Monomial multiply(Monomial firstMonomial, Monomial secondMonomial) {
        var coefficient = firstMonomial.getCoefficient() * secondMonomial.getCoefficient();
        var exponent = firstMonomial.getExponent() + secondMonomial.getExponent();

        return new Monomial(coefficient, exponent);
    }

    public static Monomial divide(Monomial firstMonomial, Monomial secondMonomial) {
        var coefficient = firstMonomial.getCoefficient() / secondMonomial.getCoefficient();
        var exponent = firstMonomial.getExponent() - secondMonomial.getExponent();

        return new Monomial(coefficient, exponent);
    }

    public static Monomial derive(Monomial monomial) {
        var coefficient = monomial.getCoefficient();
        var exponent = monomial.getExponent();

        if(exponent == 0) {
            return null;
        }

        return new Monomial(coefficient * exponent, exponent - 1);
    }

    public static Monomial integrate(Monomial monomial) {
        var coefficient = monomial.getCoefficient();
        var exponent = monomial.getExponent() + 1;

        return new Monomial(coefficient / exponent, exponent);
    }
}
