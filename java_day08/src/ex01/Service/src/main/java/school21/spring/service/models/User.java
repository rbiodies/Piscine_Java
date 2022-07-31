package school21.spring.service.models;

public class User {
    private Long id;
    private String email;

    public User(Long identifier, String email) {
        this.id = identifier;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long identifier) {
        this.id = identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
