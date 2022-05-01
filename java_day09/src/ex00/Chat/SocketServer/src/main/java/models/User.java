package models;

import java.util.List;
import java.util.Objects;

public class User {
    private long    id;
    private String  login;
    private String  password;

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String   getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String   getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean  equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User    user = (User) o;

        return id == user.id && login.equals(user.login) && password.equals(user.password);
    }

    public int  hashCode() {
        return Objects.hash(id, login, password);
    }

    public String   toString() {
        return  "{id=" + id +
                ",login=\"" + login +
                "\",password=\"" + password + "}";
    }
}
