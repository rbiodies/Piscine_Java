package ex04;

import java.util.UUID;

public class TransactionsService {
    private final UsersList usersList;
    private final TransactionsLinkedList transactionsLinkedList;

    public TransactionsService() {
        usersList = new UsersArrayList();
        transactionsLinkedList = new TransactionsLinkedList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public Integer getBalanceById(Integer id) throws UserNotFoundException {
        return usersList.getUserByID(id).getBalance();
    }

    public Integer getBalanceByIndex(Integer index) throws UserNotFoundException {
        return usersList.getUserByIndex(index).getBalance();
    }

    public void transferTransaction(Integer senderId, Integer recipientId, Integer amount) throws UserNotFoundException {
        User sender = usersList.getUserByID(senderId);
        User recipient = usersList.getUserByID(recipientId);

        Transaction transaction1 = new Transaction(sender, recipient, -amount);

        usersList.getUserByID(senderId).addTransaction(transaction1);
        usersList.getUserByID(recipientId).addTransaction(transaction1);
        transactionsLinkedList.addTransaction(transaction1);

        Transaction transaction2 = new Transaction(recipient, sender, amount);
        transaction2.setIdentifier(transaction1.getIdentifier());

        usersList.getUserByID(senderId).addTransaction(transaction2);
        usersList.getUserByID(recipientId).addTransaction(transaction2);
        transactionsLinkedList.addTransaction(transaction2);
    }

    public Transaction[] getTransfers(Integer id) throws UserNotFoundException {
        return usersList.getUserByID(id).getArray();
    }

    public Transaction[] checkTransactions() {
        Transaction[] transactions = transactionsLinkedList.toArray();
        int size = 0;

        for (int i = 0; i < transactions.length; i++) {
            if (!isDuplicate(transactions, i)) {
                size++;
            }
        }

        Transaction[] transactionsWithoutDuplicate = new Transaction[size];

        for (int i = 0, j = 0; i < transactions.length; i++) {
            if (!(isDuplicate(transactions, i))) {
                transactionsWithoutDuplicate[j++] = transactions[i];
            }
        }
        return transactionsWithoutDuplicate;
    }

    private boolean isDuplicate(Transaction[] transactions, int index) {
        for (int i = 0; i < transactions.length; i++) {
            if (index != i && transactions[index].getIdentifier() == transactions[i].getIdentifier()) {
                return true;
            }
        }
        return false;
    }

    public void removeUserTransactionsById(UUID identifier, Integer id) throws UserNotFoundException {
        usersList.getUserByID(id).removeTransaction(identifier);
        transactionsLinkedList.removeTransactionByID(identifier);
    }
}
