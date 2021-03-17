package exceptions;

public class PolynomialDivisionByZeroException extends Exception {
    public PolynomialDivisionByZeroException() {
        super("Division by 0 is not allowed.");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
