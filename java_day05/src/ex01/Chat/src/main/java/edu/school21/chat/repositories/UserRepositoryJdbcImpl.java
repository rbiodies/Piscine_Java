package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final Connection    dataSource;

    public  UserRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User>   findById(Long id) {
        ResultSet   resultSet;
        User        ret = null;

        try {
            PreparedStatement query = dataSource.prepareStatement("SELECT * FROM chat.users WHERE id=?");
            query.setLong(1, id);
            resultSet = query.executeQuery();
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
        return Optional.ofNullable(ret);
    }
}
