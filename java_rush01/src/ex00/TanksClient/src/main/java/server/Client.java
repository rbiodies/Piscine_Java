package server;

public class Client {
    
//    private Protocol    protocol;

    private static Client   client;
    
    private Client() {
//        protocol = new Protocol();
    }

    public static Client getGameClient() {
        if (client == null) {
            client = new Client();
            return client;
        }
        return client;
    }
}
