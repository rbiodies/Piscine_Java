package edu.school21.sockets.app;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void  main(String[] args) {
        try {
            Socket          clientSocket = new Socket("localhost", 4004);
            BufferedReader  reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader  in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter  out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            out.write("");
            while (true) {
                String serverWord = in.readLine();
                System.out.println(serverWord);
                if (serverWord.equals("Successful!")) {
                    break;
                }
                System.out.print("> ");
                String word = reader.readLine();
                out.write(word + "\n");
                out.flush();
            }
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
