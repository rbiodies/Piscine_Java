package ex00;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Max", 5000);
        User user2 = new User("Alex", 3000);
        Transaction tr = new Transaction(user1, user2, -1500);

        System.out.println(tr);
        System.out.println(user1.getBalance());
        System.out.println(user2.getBalance());
    }
}
