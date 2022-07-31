package ex04;

public class Program {

    public static void main(String[] args) throws UserNotFoundException {
        User user1 = new User("Max", 3000);
        User user2 = new User("Alex", 4000);
        User user3 = new User("Sergey", 5000);

        TransactionsService transactionsService = new TransactionsService();

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);
        transactionsService.addUser(user3);

        transactionsService.transferTransaction(user1.getIdentifier(), user2.getIdentifier(), 500);
        transactionsService.transferTransaction(user1.getIdentifier(), user3.getIdentifier(), 1000);

        System.out.println("\nAll transactions of user:\n");

        Transaction[] transactionsUser = transactionsService.getTransfers(user1.getIdentifier());

        for (Transaction transaction : transactionsUser) {
            System.out.println(transaction.toString());
        }

        System.out.printf("\nNew balance of user1: %d\n", transactionsService.getBalanceByIndex(0));
        System.out.printf("New balance of user2: %d\n", transactionsService.getBalanceById(user2.getIdentifier()));
        System.out.printf("New balance of user3: %d\n", transactionsService.getBalanceById(user3.getIdentifier()));

        System.out.println("\nTransactions of user after remove:\n");

        transactionsService.removeUserTransactionsById(transactionsUser[0].getIdentifier(), user1.getIdentifier());

        Transaction[] transactionsAfterRemove = transactionsService.getTransfers(user1.getIdentifier());

        for (Transaction transaction : transactionsAfterRemove) {
            System.out.println(transaction.toString());
        }

        System.out.println("\nTransactions without duplicate:\n");

        Transaction[] transactionsWithoutDuplicate = transactionsService.checkTransactions();

        for (Transaction transaction : transactionsWithoutDuplicate) {
            System.out.println(transaction.toString());
        }
    }
}
