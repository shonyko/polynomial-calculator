package exceptions;

public class PolynomialFormatNotSupportedException extends Exception {
    public PolynomialFormatNotSupportedException() {
        super("The input string is not a supported polynomial format.");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
