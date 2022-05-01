package ex02;

public class UserNotFoundException extends Exception {

    public String toString() {
        return "User not found!";
    }
}
