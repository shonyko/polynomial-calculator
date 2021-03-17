package models;

import exceptions.PolynomialFormatNotSupportedException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial implements Cloneable {
    private final List<Monomial> monomialList;

    public Polynomial() {
        monomialList = new ArrayList<>();
    }

    public List<Monomial> getMonomialList() {
        return monomialList;
    }

    public Polynomial addMonomial(Monomial monomial) {
        if(monomial == null) {
            return this;
        }

        var match = monomialList.stream()
                .filter(x -> x.getExponent() == monomial.getExponent())
                .findFirst().orElse(null);

        if(match == null) {
            monomialList.add(monomial);
            return this;
        }

        match.setCoefficient(match.getCoefficient() + monomial.getCoefficient());
        return this;
    }

    public Polynomial orderByExponent() {
        monomialList.sort(Monomial::compareTo);
        return this;
    }

    public Polynomial prune() {
        monomialList.removeIf(x -> x.getCoefficient() == 0);
        return this;
    }

    public Polynomial clear() {
        monomialList.clear();
        return this;
    }

    public double getDegree() {
        var biggestMonomial = monomialList.stream().min(Monomial::compareTo).orElse(null);

        if(biggestMonomial == null) {
            return 0;
        }

        return biggestMonomial.getExponent();
    }

    public Monomial getMonomial(int index) {
        return monomialList.get(index);
    }

    public boolean isEmpty() {
        return monomialList.isEmpty();
    }

    private static String prepString(String str) {
        // Stergem toate caracterele *
        str = str.replaceAll("\\*", "");
        // x => x^1
        str = str.replaceAll("x(?! *\\^)","x^1");
        // x^e => 1x^e
        str = str.replaceAll("((?:[+-] *)|(?:^ *))(x *\\^ *\\d+ *(?! *\\d+))","$11$2");
        // c => cx^0 12x^2+5x-7
        str = str.replaceAll("((?:[+-] *\\d+)|(?:^ *\\d+))(?=(?: *[+-])|(?: *$))", "$1x^0");
        // Stergem toate caracterele albe
        str = str.replaceAll("\s", "");

        return str;
    }

    public static Polynomial fromString(String str) throws PolynomialFormatNotSupportedException {
        str = prepString(str);

        if(str.isEmpty()) {
            return null;
        }

        if(!str.matches("(?:[+-]?\\d+x\\^\\d+)+")) {
            throw new PolynomialFormatNotSupportedException();
        }

        // <coefficient>x^<exponent>
        Pattern regex = Pattern.compile("(?<coefficient>[+-]?\\d+)x\\^(?<exponent>\\d+)");
        Matcher matcher = regex.matcher(str);

        var pol = new Polynomial();
        while (matcher.find()) {
            var coefficient = Integer.parseInt(matcher.group("coefficient"));
            var exponent = Integer.parseInt(matcher.group("exponent"));
            pol.addMonomial(new Monomial(coefficient, exponent));
        }

        return pol.prune().orderByExponent();
    }

    @Override
    public String toString() {
        if(monomialList.size() == 0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(var monomial : monomialList) {
            stringBuilder.append(monomial).append(" ");
        }

        String string = stringBuilder.toString();
        string = string.replaceAll("(\\d+)\\.0*(?!\\d+)", "$1");
        string = string.replaceAll("^\\+ *", "");

        return string;
    }

    @Override
    public Polynomial clone() {
        var clone = new Polynomial();

        for (var monomial : monomialList) {
            clone.addMonomial(monomial.clone());
        }

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        var list = that.getMonomialList();
        for(var monomial : list) {
            var index = list.indexOf(monomial);
            if(!monomialList.get(index).equals(monomial)) {
                return false;
            }
        }

        return true;
    }
}
