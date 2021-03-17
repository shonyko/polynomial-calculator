package models;

import java.util.Objects;

public class Monomial implements Comparable<Monomial>, Cloneable {
    private double coefficient;
    private double exponent;
    private boolean isConstant;

    public Monomial(double coefficient, double exponent) {
        this.coefficient= coefficient;
        this.exponent   = exponent;
    }

    public Monomial(boolean isConstant) {
        this.isConstant = isConstant;
        this.coefficient= Double.MIN_VALUE;
        this.exponent   = Double.MIN_VALUE;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean isConstant) {
        this.isConstant = isConstant;
    }

    @Override
    public String toString() {
        if(isConstant) {
            return "+ C";
        }
        if(coefficient == 0) {
            return "+ 0";
        }

        var stringBuilder = new StringBuilder();
        stringBuilder.append(coefficient < 0 ? "- " : "+ ");

        var coefficient = Math.abs(this.coefficient);
        if(exponent == 0) {
            stringBuilder.append(coefficient);
            return stringBuilder.toString();
        }

        if(Math.abs(this.coefficient) != 1) {
            stringBuilder.append(String.format("%.2f", coefficient));
        }
        stringBuilder.append("x");
        if(exponent != 1) {
            stringBuilder.append("^").append(exponent);
        }

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Monomial o) {
        if(exponent < o.exponent) {
            return 1;
        }
        if(exponent > o.exponent) {
            return -1;
        }

        return Double.compare(o.coefficient, coefficient);
    }

    @Override
    public Monomial clone() {
        return new Monomial(coefficient, exponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monomial monomial = (Monomial) o;
        return Double.compare(monomial.coefficient, coefficient) == 0 && Double.compare(monomial.exponent, exponent) == 0 && isConstant == monomial.isConstant;
    }
}
