package ex00;

public class Program {

    public static void  main(String[] args) {
        User    user1 = new User("Max", 5000);
        User    user2 = new User("Alex", 2500);
        Transaction tr = new Transaction(user1, user2, 2000);

        System.out.println(tr);
    }
}
