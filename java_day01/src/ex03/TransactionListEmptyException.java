package ex03;

public class TransactionListEmptyException extends RuntimeException {

    public TransactionListEmptyException() {
        super("Transaction list is empty!");
    }
}
