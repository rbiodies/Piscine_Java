package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;

public class Program {
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        private static final String DB_USER = "postgres";
        private static final String DB_PASSWORD = "";

        public static void  main(String[] args) {
            HikariDataSource ds = new HikariDataSource();

            ds.setJdbcUrl(DB_URL);
            ds.setUsername(DB_USER);
            ds.setPassword(DB_PASSWORD);

            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
            Optional<Message> messageOptional = messagesRepository.findById(4);

            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                message.setText("Bye");
                message.setDateTime(null);
                messagesRepository.update(message);
            } else {
                System.err.println("No such id of message!");
            }
        }
}
