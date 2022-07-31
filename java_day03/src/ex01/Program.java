package ex01;

enum Type {
    CONSUMER,
    PRODUCER
}

public class Program {

    public static void  main(String[] args) throws InterruptedException {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Usage: --count=<count of iterators>");
        } else {
            try {
                int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));

                if (count < 0) {
                    System.err.println("Error: Too few argument!");
                } else {
                    MyThread henThread = new MyThread("Hen", count, Type.CONSUMER);
                    MyThread eggThread = new MyThread("Egg", count, Type.PRODUCER);

                    henThread.start();
                    eggThread.start();

                    henThread.join();
                    eggThread.join();
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Not integer type!");
            }
        }
    }
}
