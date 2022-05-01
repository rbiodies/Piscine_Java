package ex04;

public class UsersArrayList implements UsersList {

    private Integer capacity = DEFAULT_SIZE;
    private Integer size = 0;

    private User[] userArrayList = new User[DEFAULT_SIZE];

    @Override
    public void addUser(User user) {
        if (size == capacity - 1) {
            userArrayList = reallocUserArrayList(userArrayList);
        }
        if (user != null) {
            userArrayList[size] = user;
            size++;
        } else {
            throw new NullPointerException();
        }
    }

    private User[] reallocUserArrayList(User[] userArrayList) {
        this.capacity *= 2;
        User[] ret = new User[this.capacity];

        for (int i = 0; i <= size; i++) {
            ret[i] = userArrayList[i];
        }
        return ret;
    }

    @Override
    public User getUserByID(Integer id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (userArrayList[i].getIdentifier().equals(id)) {
                return userArrayList[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(Integer index) throws UserNotFoundException {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (userArrayList[index] == null) {
            throw new UserNotFoundException();
        }
        return userArrayList[index];
    }

    @Override
    public Integer getNumberOfUsers() {
        return size;
    }
}
