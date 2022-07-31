package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        private static final String DB_USER = "postgres";
        private static final String DB_PASSWORD = "";

        public static void  main(String[] args) {
            HikariDataSource ds = new HikariDataSource();

            ds.setJdbcUrl(DB_URL);
            ds.setUsername(DB_USER);
            ds.setPassword(DB_PASSWORD);

            User creator = new User(5L, "user", "user", new ArrayList<>(), new ArrayList<>());
            Chatroom room = new Chatroom(5L, "room", creator, new ArrayList<>());
            Message message = new Message(null, creator, room, "Hello!", LocalDateTime.now());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
            messagesRepository.save(message);
            System.out.println(message.getId());
        }
}
