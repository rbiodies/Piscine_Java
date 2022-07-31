package ex01;

public class Program {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            User tmp;
            if (i % 2 == 0) {
                tmp = new User("Man", i);
            } else {
                tmp = new User("Woman", i);
            }
            System.out.printf("User number: %d, id: %d\n", i, tmp.getIdentifier());
        }
    }
}
