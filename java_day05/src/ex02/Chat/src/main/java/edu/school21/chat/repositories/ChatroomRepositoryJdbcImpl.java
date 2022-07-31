package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final Connection dataSource;
    private final UserRepositoryJdbcImpl userRepository;

    public  ChatroomRepositoryJdbcImpl(Connection dataSource, UserRepositoryJdbcImpl userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }

    @Override
    public Chatroom findById(Long id) {
        Chatroom ret = null;

        try (PreparedStatement query = dataSource.prepareStatement("SELECT * FROM chat.rooms WHERE id=?")) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                ret = new Chatroom(
                        resultSet.getLong("id"),
                        resultSet.getString("chat_name"),
                        userRepository.findById(resultSet.getLong("chat_owner")),
                        null
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
}
