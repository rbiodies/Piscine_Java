package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final Connection                dataSource;
    private final UserRepositoryJdbcImpl    userRepository;

    public  ChatroomRepositoryJdbcImpl(Connection dataSource, UserRepositoryJdbcImpl userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    public Optional<Chatroom> findById(Long id) {
        ResultSet   resultSet;
        Chatroom    ret = null;

        try {
            PreparedStatement query = dataSource.prepareStatement("SELECT * FROM chat.rooms WHERE id=?");
            query.setLong(1, id);
            resultSet = query.executeQuery();
            if (resultSet.next()) {
                ret = new Chatroom(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        userRepository.findById(resultSet.getLong("owner")).orElse(null),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(ret);
    }
}
