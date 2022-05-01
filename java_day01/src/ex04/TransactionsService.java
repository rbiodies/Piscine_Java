package ex04;

import java.util.UUID;

public class TransactionsService {
    private final UsersList   usersList;
    private final TransactionsLinkedList tr;

    public TransactionsService() {
        usersList = new UsersArrayList();
        tr = new TransactionsLinkedList();
    }

    public void    addUser(User user) {
        usersList.addUser(user);
    }

    public Integer     getBalanceById(Integer id) throws UserNotFoundException {
        return usersList.getUserByID(id).getBalance();
    }

    public Integer     getBalanceByIndex(Integer index) throws UserNotFoundException {
        return usersList.getUserByIndex(index).getBalance();
    }

    public void    transferTransaction(Integer recipientId, Integer senderId, Integer amount) throws UserNotFoundException {
        User    recipient = usersList.getUserByID(recipientId);
        User    sender = usersList.getUserByID(senderId);
        Transaction transaction1 = new Transaction(recipient, sender, -amount);

        usersList.getUserByID(recipientId).addTransaction(transaction1);
        usersList.getUserByID(senderId).addTransaction(transaction1);
        tr.addTransaction(transaction1);

        Transaction transaction2 = new Transaction(sender, recipient, amount);

        usersList.getUserByID(recipientId).addTransaction(transaction2);
        usersList.getUserByID(senderId).addTransaction(transaction2);
        tr.addTransaction(transaction2);
    }

    public Transaction[]    getTransfers(Integer id) throws UserNotFoundException {
        return usersList.getUserByID(id).getArray();
    }

    public Transaction[]  checkTransactions() {
        Transaction[]   allTr = tr.toArray();
        Integer         arrSize = 0;

        for (int i = 0; i < allTr.length; i++) {
            if (!(isDup(allTr, allTr[i]))) {
                arrSize++;
            }
        }

        Transaction[] retTr = new Transaction[arrSize];

        for (int i = 0,j = 0; i < allTr.length; i++) {
            if (!(isDup(allTr, allTr[i]))) {
                retTr[j++] = allTr[i];
            }
        }
        return retTr;
    }

    private boolean isDup(Transaction[] transactions, Transaction transaction) {
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i].getIdentifier() == transaction.getIdentifier()) {
                return true;
            }
        }
        return false;
    }

    public void removeUserTransactionsById(UUID trId, Integer id) throws UserNotFoundException {
        usersList.getUserByID(id).removeTransaction(trId);
    }
}
