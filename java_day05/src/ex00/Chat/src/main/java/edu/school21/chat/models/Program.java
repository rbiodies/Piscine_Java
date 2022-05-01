package edu.school21.chat.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {

    public static void  main(String[] args) throws FileNotFoundException {
        Connection connection = connect();
        System.out.println("Creating tables...");
        runQueriesFromFile(connection, "main/resources/schema.sql");
        System.out.println("Tables are created!");
        System.out.println("Populating tables!");
        runQueriesFromFile(connection, "main/resources/data.sql");
        System.out.println("Data is inserted!");
    }

    private static Connection connect() {
        Connection  conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
            System.out.println("Connected to the PostgresSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e.getMessage());
        }
        return conn;
    }

    private static void runQueriesFromFile(Connection connection, String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new File(System.getProperty("user.dir") + "/src/ex00/Chat/src/" + filename))
                .useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch(SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        scanner.close();
    }
}
