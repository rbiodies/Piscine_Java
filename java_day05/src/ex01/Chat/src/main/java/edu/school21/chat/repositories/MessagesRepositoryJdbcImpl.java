package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Connection    dataSource;
    UserRepositoryJdbcImpl      userRepository;
    ChatroomRepositoryJdbcImpl  chatroomRepository;

    public  MessagesRepositoryJdbcImpl(Connection dataSource, UserRepositoryJdbcImpl userRepository, ChatroomRepositoryJdbcImpl chatroomRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
        this.chatroomRepository = chatroomRepository;
    }

    public Optional<Message> findById(Long id) {
        ResultSet   resultSet;
        Message ret = null;

        try {
            PreparedStatement query = dataSource.prepareStatement("SELECT * FROM chat.messages WHERE id=?");
            query.setLong(1, id);
            resultSet = query.executeQuery();
            if (resultSet.next()) {
                ret = new Message(
                        resultSet.getLong("id"),
                        userRepository.findById(resultSet.getLong("author")).orElse(null),
                        chatroomRepository.findById(resultSet.getLong("room")).orElse(null),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("timestamp").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(ret);
    }
}
