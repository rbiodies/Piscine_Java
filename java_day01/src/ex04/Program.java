package ex04;

public class Program {

    public static void  main(String[] args) throws UserNotFoundException {
        User    user1 = new User("Max", 5000);
        User    user2 = new User("Sergey", 2500);

        TransactionsService transactionsService = new TransactionsService();

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);

        transactionsService.transferTransaction(user1.getIdentifier(), user2.getIdentifier(), 1000);

        Transaction[] trs = transactionsService.getTransfers(1);

        for (Transaction tr : trs) {
            System.out.println(tr.toString());
        }

        System.out.println(transactionsService.getBalanceByIndex(0));
        System.out.println(transactionsService.getBalanceById(user2.getIdentifier()));

        transactionsService.removeUserTransactionsById(trs[0].getIdentifier(), user1.getIdentifier());

        Transaction[] trs2 = transactionsService.getTransfers(1);

        for (Transaction tr : trs2) {
            System.out.println(tr.toString());
        }

        Transaction[] trs3 = transactionsService.checkTransactions();

        for (Transaction tr : trs3) {
            System.out.println(tr.toString());
        }
    }
}
