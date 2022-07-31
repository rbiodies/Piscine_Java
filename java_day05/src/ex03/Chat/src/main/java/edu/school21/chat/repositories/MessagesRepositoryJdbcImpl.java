package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
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
    public Optional<Message> findById(int id) {
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

    @Override
    public void save(Message message) {
        String query = "INSERT INTO chat.messages (room_id, sender, text, time) VALUES (?, ?, ?, ?) RETURNING *";

        try(Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            UserRepositoryJdbcImpl userRep = new UserRepositoryJdbcImpl(connection);
            ChatroomRepositoryJdbcImpl chatRep = new ChatroomRepositoryJdbcImpl(connection, userRep);

            if (userRep.findById(message.getAuthor().getId()) != null
                    || chatRep.findById(message.getChatroom().getId()) != null) {
                statement.setLong(1, message.getChatroom().getId());
                statement.setLong(2, message.getAuthor().getId());
                statement.setString(3, message.getText());
                statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));

                ResultSet resultSet = statement.executeQuery();

                resultSet.next();
                message.setId(resultSet.getInt("id"));
            } else {
                throw new NotSavedSubEntityException();
            }
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public void update(Message message) {
        String query = "UPDATE chat.messages SET " +
                "room_id = ?, " +
                "sender = ?, " +
                "text = ?, " +
                "time = ? " +
                "WHERE id=?";

        try (Connection connection = ds.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, message.getChatroom().getId());
            statement.setLong(2, message.getAuthor().getId());
            statement.setString(3, message.getText());
            try {
                statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            } catch (NullPointerException e) {
                statement.setTimestamp(4, null);
            }
            statement.setLong(5, message.getId());
            statement.execute();

            System.out.println("The value of the column is updated successfully!");
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }
}
