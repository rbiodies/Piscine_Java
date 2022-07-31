package ex00;

public class User {
    private Integer id;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        this.name = name;
        setBalance(balance);
    }

    public Integer getIdentifier() {
        return id;
    }

    public void setIdentifier(Integer identifier) {
        this.id = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }
}
