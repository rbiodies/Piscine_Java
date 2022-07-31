package edu.school21.sockets.server;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandler implements Runnable {
    private Server server;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Server server, Socket client) {
        try {
            this.server = server;
            this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        User user;

        try {
            writer.write("Hello from Server!\n");
            writer.flush();
            while (true) {
                if (reader.ready()) {
                    String command = reader.readLine();

                    if ("signUp".equals(command)) {
                        if (isSignUp()) {
                            writer.write("Successful!\n");
                            writer.flush();
                        } else {
                            writer.write("This user is already authenticated!\n");
                            writer.flush();
                        }
                    } else if ("signIn".equals(command)) {
                        user = signIn();
                        if (user == null) {
                            writer.write("Bad credentials!\n");
                            writer.flush();
                        } else {
                            break;
                        }
                    } else {
                        writer.write("No such command!\n");
                        writer.flush();
                    }
                }
            }
            server.clients.add(this);

            writer.write("Start messaging\n");
            writer.flush();

            while (true) {
                if (reader.ready()) {
                    String text = reader.readLine();
                    Message message = new Message(user, text, LocalDateTime.now());

                    server.getMessagesRepository().save(message);
                    server.sendMessageToAllClients(user.getUsername() + ": " + text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    private boolean isSignUp() {
        try {
            writer.write("Enter username:\n");
            writer.flush();

            String username = reader.readLine();

            writer.write("Enter password:\n");
            writer.flush();

            String password = reader.readLine();

            return server.getUsersService().signUp(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User signIn() {
        try {
            writer.write("Enter username:\n");
            writer.flush();

            String username = reader.readLine();

            writer.write("Enter password:\n");
            writer.flush();

            String password = reader.readLine();

            return server.getUsersService().signIn(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMessage(String message) {
        try {
            writer.write(message + "\r");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        server.removeClient(this);
    }
}
