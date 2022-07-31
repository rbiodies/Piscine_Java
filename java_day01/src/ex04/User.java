package ex04;

import java.util.UUID;

public class User {
    private final Integer id;
    private String  name;
    private Integer balance;
    private final TransactionsLinkedList transactionsLinkedList;

    public User(String name, Integer balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        setBalance(balance);
        transactionsLinkedList = new TransactionsLinkedList();
    }

    public Integer getIdentifier() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionsLinkedList.addTransaction(transaction);
    }

    public Transaction[] getArray() {
        return transactionsLinkedList.toArray();
    }

    public void removeTransaction(UUID identifier) {
        transactionsLinkedList.removeTransactionByID(identifier);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
