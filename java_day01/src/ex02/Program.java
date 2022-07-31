package ex02;

public class Program {

    public static void main(String[] args) throws UserNotFoundException {
        UsersArrayList usersArrayList = new UsersArrayList();

        for (int i = 0; i < 20; i++) {
            User tmp;
            if (i % 2 == 0) {
                tmp = new User("Man", i);
            } else {
                tmp = new User("Woman", i);
            }
            usersArrayList.addUser(tmp);
        }

        System.out.println(usersArrayList.getUserByID(16).toString());
        System.out.println(usersArrayList.getUserByIndex(10).toString());
        System.out.println(usersArrayList.getNumberOfUsers());
    }
}
