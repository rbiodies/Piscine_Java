package ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User sender;
    private User recipient;
    private Integer amount;
    private Category category;
    private Status status;

    private enum Category {
        DEBIT,
        CREDIT
    }

    private enum Status {
        FAIL,
        SUCCESS
    }

    public Transaction(User sender, User recipient, Integer amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.identifier = UUID.randomUUID();

        if (amount < 0) {
            setCategory(Category.CREDIT);
        } else {
            setCategory(Category.DEBIT);
        }

        this.amount = amount;

        if (getCategory() == Category.DEBIT && recipient.getBalance() < amount
                || getCategory() == Category.CREDIT && sender.getBalance() < Math.abs(amount)) {
            setStatus(Status.FAIL);
        } else {
            sender.setBalance(sender.getBalance() + amount);
            recipient.setBalance(recipient.getBalance() - amount);
            setStatus(Status.SUCCESS);
        }
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier=" + identifier +
                ", sender=" + sender.getName() +
                ", recipient=" + recipient.getName() +
                ", amount=" + amount +
                ", category=" + category +
                ", status=" + status +
                '}';
    }
}
