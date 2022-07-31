package ex03;

public class Program {

    public static void main(String[] args) {
        TransactionsLinkedList transactions = new TransactionsLinkedList();
        User user1 = new User("Max", 5000);
        User user2 = new User("Alex", 3000);
        User user3 = new User("Sergey", 1500);

        Transaction tmp1 = new Transaction(user1, user2, -400);
        transactions.addTransaction(tmp1);

        Transaction tmp2 = new Transaction(user1, user3, 2500);
        transactions.addTransaction(tmp2);

        Transaction tmp3 = new Transaction(user2, user3, -700);
        transactions.addTransaction(tmp3);

//        transactions.removeTransactionByID(tmp1.getIdentifier());

        for (Transaction t : transactions.toArray()) {
            System.out.println(t.toString());
        }
    }
}
