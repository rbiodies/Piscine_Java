package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        private static final String DB_USER = "postgres";
        private static final String DB_PASSWORD = "";

        public static void  main(String[] args) {
            HikariDataSource ds = new HikariDataSource();

            ds.setJdbcUrl(DB_URL);
            ds.setUsername(DB_USER);
            ds.setPassword(DB_PASSWORD);

            MessagesRepositoryJdbcImpl msgRep = new MessagesRepositoryJdbcImpl(ds);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a message ID");

            if (scanner.hasNextLong()) {
                System.out.println(msgRep.findById(scanner.nextLong()).orElse(null));
            } else {
                System.err.println("Error: Input has not long type!");
            }
        }
}
