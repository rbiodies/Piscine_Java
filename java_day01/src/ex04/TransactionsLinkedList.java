package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode first;
    private TransactionNode last;
    private Integer size;

    private static class TransactionNode {
        Transaction transaction;
        TransactionNode previous;
        TransactionNode next;

        public TransactionNode(Transaction transaction) {
            this.transaction = transaction;
        }
    }

    public TransactionsLinkedList() {
        this.size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        TransactionNode newNode = new TransactionNode(transaction);

        if (first == null) {
            first = newNode;
            last = newNode;
            newNode.previous = null;
            newNode.next = null;
        } else {
            last.next = newNode;
            newNode.previous = last;
            newNode.next = null;
            last = newNode;
        }
        size++;
    }

    @Override
    public void removeTransactionByID(UUID identifier) {
        TransactionNode node = getNodeById(identifier);

        if (first == node) {
            first = first.next;
            first.previous = null;
        } else if (last == node) {
            last = last.previous;
            last.next = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }
        node.transaction = null;
        node.previous = null;
        node.next = null;
        size--;
    }

    private TransactionNode getNodeById(UUID id) {
        if (size == 0) {
            throw new TransactionListEmptyException();
        } else if (first.transaction.getIdentifier() == id) {
            return first;
        } else if (last.transaction.getIdentifier() == id) {
            return last;
        } else {
            TransactionNode node = first;
            while (node != null) {
                if (node.transaction.getIdentifier() == id) {
                    return node;
                }
                node = node.next;
            }
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        if (size == 0) {
            throw new TransactionListEmptyException();
        }

        Transaction[] array = new Transaction[size];
        TransactionNode tmp = first;

        for (int i = 0; i < size; i++) {
            array[i] = tmp.transaction;
            tmp = tmp.next;
        }
        return array;
    }
}
