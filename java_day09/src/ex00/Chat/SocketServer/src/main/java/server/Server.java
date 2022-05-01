package server;

import java.io.*;
import java.net.Socket;

public class Server extends Thread {
//
//    private Socket  socket;
//    private BufferedReader  in;
//    private BufferedWriter  out;
//
//    public Server(Socket socket) throws IOException {
//        this.socket = socket;
//        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        start();
//    }
//
//    public void run() {
//        String  word;
//        try {
//
//            while (true) {
//                word = in.readLine();
//                if (word.equals("exit")) {
//                    break;
//                }
//                System.out.println(word);
//                out.write("Server: \"You write: " + word + "\"\n");
//                out.flush();
//            }
//            clientSocket.close();
//            in.close();
//            out.close();
//            System.out.println("Server is closed!");
//            server.close();
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }
}
