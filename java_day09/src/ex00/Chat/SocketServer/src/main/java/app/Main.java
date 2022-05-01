package app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import server.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.LinkedList;

public class Main {

    public static void  main(String[] args) throws IOException {
        Connection  connection = connect();

        try {
            ServerSocket server = new ServerSocket(4004);

            System.out.println("Server is run!");

            Socket  clientSocket = server.accept();
            BufferedReader  in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter  out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            out.write("Hello from Server!\n");
            out.flush();

            String word = in.readLine();

            out.write("Enter username:\n");
            out.flush();

            String username = in.readLine();

            out.write("Enter password:\n");
            out.flush();

            String password = in.readLine();

            out.write("Successful!\n");
            out.flush();
            clientSocket.close();
            in.close();
            out.close();
            System.out.println("Server is closed!");
            server.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static Connection   connect() {
        Connection  connection = null;

        try {
            connection = HikariConnect().getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private static HikariDataSource HikariConnect() {
        HikariConfig    config = new HikariConfig();

        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
}
