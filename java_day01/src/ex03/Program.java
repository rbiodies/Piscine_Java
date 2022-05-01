package ex03;

public class Program {

    public static void  main(String[] args) {
        TransactionsLinkedList  transactions = new TransactionsLinkedList();
        User                    user1 = new User("Man", 2000);
        User                    user2 = new User("Woman", 3000);
        Transaction             tmp;

        tmp = new Transaction(user1, user2, 400);
        transactions.addTransaction(tmp);

        tmp = new Transaction(user1, user2, -3500);
        transactions.addTransaction(tmp);
        transactions.removeTransactionByID(tmp.getIdentifier());

        for (Transaction t : transactions.toArray()) {
            System.out.println(t.toString());
        }
    }
}
