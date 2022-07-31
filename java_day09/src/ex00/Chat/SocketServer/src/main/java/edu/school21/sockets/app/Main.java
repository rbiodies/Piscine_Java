package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--port=")) {
            System.out.println("Usage: --port=<port>");
        } else {
            try {
                int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
                Server server = new Server(port);

                server.run();
            } catch (NumberFormatException e) {
                System.err.println("Error: Argument has not type of integer!");
            }
        }
    }
}
