package ex00;

public class Program {

    public static void  main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Usage: --count=<count of iterators>");
        } else {
            try {
                int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));

                if (count < 0) {
                    System.err.println("Error: Too few argument!");
                } else {
                    MyThread henThread = new MyThread("Hen", count);
                    MyThread eggThread = new MyThread("Egg", count);

                    henThread.start();
                    eggThread.start();

                    try {
                        henThread.join();
                        eggThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < count; i++) {
                        System.out.println("Human");
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: The number has not type of integer!");
            }
        }
    }
}
