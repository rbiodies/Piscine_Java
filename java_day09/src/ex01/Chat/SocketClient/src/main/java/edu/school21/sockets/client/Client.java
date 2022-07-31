package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    BufferedReader reader;
    BufferedWriter writer;

    public Client(int port) {
        try (Socket clientSocket = new Socket("127.0.0.1", port)) {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            listen();
            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (reader.ready()) {
                            System.out.println(reader.readLine());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNext()) {
                String str = scanner.nextLine();

                if ("Exit".equals(str)) {
                    System.out.println("You have left the chat.");
                    System.exit(0);
                }
                try {
                    writer.write(str + "\r");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
