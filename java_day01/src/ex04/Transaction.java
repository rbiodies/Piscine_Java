package ex04;

import java.util.UUID;

public class Transaction {

        private UUID        identifier;
        private User        recipient;
        private User        sender;
        private Integer     amount;
        private Category    category;

        private enum    Category {
            DEBIT,
            CREDIT
        }

        public Transaction(User sender, User recipient, Integer amount) {
            this.sender = sender;
            this.recipient = recipient;
            this.identifier = new UUID(123, 456);

            if (amount < 0) {
                setCategory(Category.CREDIT);
            } else {
                setCategory(Category.DEBIT);
            }

            this.amount = amount;
            if (getCategory() == Transaction.Category.DEBIT) {
                if (recipient.getBalance() < amount) {
                    throw new IIllegalTransactionException();
                } else {
                    sender.setBalance(sender.getBalance() + amount);
                    recipient.setBalance(recipient.getBalance() - amount);
                }
            }
        }

        public UUID getIdentifier() {
            return identifier;
        }

        public void setIdentifier(UUID identifier) {
            this.identifier = identifier;
        }

        public User getRecipient() {
            return recipient;
        }

        public void setRecipient(User recipient) {
            this.recipient = recipient;
        }

        public User getSender() {
            return sender;
        }

        public void setSender(User sender) {
            this.sender = sender;
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

        public String   toString() {
            return "Transaction { " +
                    "identifier: " + identifier +
                    ", recipient: " + recipient.getName() +
                    ", index: " + recipient.getIdentifier() +
                    ", sender: " + sender.getName() +
                    ", index: " + sender.getIdentifier() +
                    ", amount: " + amount +
                    ", category: " + category;
        }
}
