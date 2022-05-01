package ex02;

public interface UsersList {

    Integer DEFAULT_SIZE = 10;

    void addUser(User user);

    User getUserByID(Integer id) throws UserNotFoundException;

    User getUserByIndex(Integer index) throws UserNotFoundException;

    Integer getNumberOfUsers();
}
