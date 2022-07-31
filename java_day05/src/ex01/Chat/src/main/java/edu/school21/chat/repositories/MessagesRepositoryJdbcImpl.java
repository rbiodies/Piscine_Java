package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class  MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource ds;

    public  MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> optionalMessage = Optional.empty();
        String query = "SELECT * FROM chat.messages WHERE id=?";

        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            UserRepositoryJdbcImpl userRep = new UserRepositoryJdbcImpl(connection);
            ChatroomRepositoryJdbcImpl chatRep = new ChatroomRepositoryJdbcImpl(connection, userRep);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                optionalMessage = Optional.of(new Message(
                        resultSet.getInt("id"),
                        userRep.findById(resultSet.getLong("sender")),
                        chatRep.findById(resultSet.getLong("room_id")),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("time").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return optionalMessage;
    }
}
