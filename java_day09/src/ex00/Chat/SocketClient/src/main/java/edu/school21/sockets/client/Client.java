package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void run(int port) {
        try (Socket clientSocket = new Socket("127.0.0.1", 8081);
             Scanner scanner = new Scanner(System.in);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            System.out.println(reader.readLine());

            String command = scanner.nextLine();

            writer.write(command+ "\r");
            writer.flush();

            System.out.println(reader.readLine());

            String username = scanner.nextLine();

            writer.write(username + "\r");
            writer.flush();

            System.out.println(reader.readLine());

            String password = scanner.nextLine();

            writer.write(password + "\r");
            writer.flush();

            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
