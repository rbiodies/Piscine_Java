package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private TransactionNode first;
    private TransactionNode last;
    private Integer         size;

    private static class TransactionNode {
        Transaction     transaction;
        TransactionNode next;
        TransactionNode previous;

        public TransactionNode(Transaction transaction) {
            this.transaction = transaction;
        }
    }

    public TransactionsLinkedList() {
        this.size = 0;
    }

    @Override
    public void    addTransaction(Transaction transaction) {
        TransactionNode newNode = new TransactionNode(transaction);
        if (first == null) {
            newNode.next = null;
            first = newNode;
            last = newNode;
            newNode.previous = null;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
        size++;
    }

    @Override
    public void    removeTransactionByID(UUID identifier) {
        if (size == 0) {
            throw new TransactionNotFoundException();
        }
        TransactionNode nodeBefore = getNodeById(identifier);
        if (nodeBefore == null) {
            first = first.next;
        } else {
            if (last.transaction.getIdentifier() == identifier) {
                nodeBefore.next = null;
                last = nodeBefore;
            } else {
                nodeBefore.next = nodeBefore.next.next;
            }
        }
        size--;
    }

    private TransactionNode getNodeById(UUID id) {
        if (size == 0) {
            return null;
        }
        if (first.transaction.getIdentifier() == id) {
            return first;
        }
        TransactionNode node = first;
        while (node.next != null) {
            if (node.next.transaction.getIdentifier() == id) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public Transaction[]   toArray() {
        Integer arrSize;
        arrSize = size;

        if (size == 0) {
            throw new TransactionListEmptyException();
        }

        Transaction[]   array = new Transaction[arrSize];
        TransactionNode tmp = first;
        int             idx = 0;

        while (tmp != null) {
            array[idx] = tmp.transaction;
            tmp = tmp.next;
            idx++;
        }
        return array;
    }
}
