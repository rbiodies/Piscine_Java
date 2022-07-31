package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final int port;
    private UsersService usersService;
    private MessagesRepositoryImpl messagesRepository;
    ArrayList<ClientHandler> clients = new ArrayList<>();

    public Server(int port) {
        this.port = port;

        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

        this.usersService = context.getBean("usersServiceImpl", UsersService.class);
        this.messagesRepository = context.getBean(MessagesRepositoryImpl.class);

        run();
    }

    public void run() {
        Socket clientSocket = null;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(this, clientSocket);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert clientSocket != null;
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public MessagesRepositoryImpl getMessagesRepository() {
        return messagesRepository;
    }

    public void setMessagesRepository(MessagesRepositoryImpl messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void sendMessageToAllClients(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
