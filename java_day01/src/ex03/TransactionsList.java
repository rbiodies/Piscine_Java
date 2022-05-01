package ex03;

import java.util.UUID;

public interface TransactionsList {

    void            addTransaction(Transaction transaction);
    void            removeTransactionByID(UUID identifier);
    Transaction[]   toArray();
}
