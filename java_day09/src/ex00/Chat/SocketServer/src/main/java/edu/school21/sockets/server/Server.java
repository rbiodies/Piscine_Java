package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        school21.spring.service.services.UsersService usersService = context.getBean("usersServiceImpl", school21.spring.service.services.UsersService.class);

        try (ServerSocket serverSocket = new ServerSocket(port);
            Socket client = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            writer.write("Hello from Server!\n");
            writer.flush();

            if ("signUp".equals(reader.readLine())) {
                writer.write("Enter username:\n");
                writer.flush();

                String username = reader.readLine();

                writer.write("Enter password:\n");
                writer.flush();

                String password = reader.readLine();

                writer.write("Successful!\n");
                writer.flush();

                usersService.signUp(username, password);
            } else {
                writer.write("No such command!\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
