package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private ServerSocket    serverSocket;

    private DataInputStream reader;

    private boolean running = true;

    public Server() {
        try {
            serverSocket = new ServerSocket(11111);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        Socket  clientSocket = null;
        String  sentence = "";

        while (running) {
            try {
                clientSocket = serverSocket.accept();
                reader = new DataInputStream(clientSocket.getInputStream());
                sentence = reader.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sentence);
        }
    }

    public void stopServer() {
        running = false;
    }
}
