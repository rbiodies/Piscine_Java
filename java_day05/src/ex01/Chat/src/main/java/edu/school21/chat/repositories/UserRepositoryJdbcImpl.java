package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final Connection dataSource;

    public  UserRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) {
        String query = "SELECT * FROM chat.users WHERE id=?";
        User ret = null;

        try (PreparedStatement statement = dataSource.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ret = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        null,
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
}
