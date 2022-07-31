package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--server-port=")) {
            System.out.println("Usage: --server-port=<port>");
        } else {
            try {
                int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
                Client client = new Client();

                client.run(port);
            } catch (NumberFormatException e) {
                System.err.println("Error: Argument has not type of integer!");
            }
        }
    }
}
