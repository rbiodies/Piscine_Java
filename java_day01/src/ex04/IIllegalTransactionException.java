package ex04;

public class IIllegalTransactionException extends RuntimeException {
    public IIllegalTransactionException() {
        super("IIllegal transaction");
    }
}
