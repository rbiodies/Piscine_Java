package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public User findById(Long id) {
        User user = null;

        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM roomdb.users WHERE id=?")) {
            query.setLong(1, id);

            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        final String query = "SELECT * FROM roomdb.users";
        List<User> userList = new LinkedList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        final String query = "INSERT INTO roomdb.users (id, email) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getEmail());
            statement.execute();

            ResultSet resultSet = connection.createStatement().executeQuery("CALL IDENTITY()");

            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        final String query = "UPDATE roomdb.users SET "
                + "email=? "
                + "WHERE id=?";

        if (findById(entity.getId()) != null) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, entity.getEmail());
                statement.setLong(2, entity.getId());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        final String query = "DELETE FROM roomdb.users WHERE id=?";

        if (findById(id) != null) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;

        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM roomdb.users WHERE email=?")) {
            query.setString(1, email);

            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }
}
