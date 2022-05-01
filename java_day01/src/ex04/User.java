package ex04;

import java.util.UUID;

public class User {

    private Integer identifier;
    private String  name;
    private Integer balance;
    private final TransactionsLinkedList transactionsLinkedList;

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        setName(name);
        setBalance(balance);
        transactionsLinkedList = new TransactionsLinkedList();
    }

    public String   getName() {
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

    public Integer  getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public void addTransaction(Transaction tr) {
        transactionsLinkedList.addTransaction(tr);
    }

    public Transaction[]    getArray() {
        return transactionsLinkedList.toArray();
    }

    public String toString() {
        return "User { identifier: " + identifier + ", name: '" + name + "'" + ", balance: " + balance + " }";
    }

    public void removeTransaction(UUID id) {
        transactionsLinkedList.removeTransactionByID(id);
    }
}
