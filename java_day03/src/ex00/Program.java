package ex00;

public class Program {

    public static void  main(String[] args) throws InterruptedException {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Usage: --count=<count of iterators>");
            return;
        }

        int count;

        try {
            count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        } catch (NumberFormatException e) {
            System.err.println("Error: The number has not type of integer!");
            return;
        }
        if (count < 0) {
            System.err.println("Error: Too few argument!");
            return;
        }

        int sleepTime = 50;
        MyThread henThread = new MyThread("Hen", count, sleepTime);
        MyThread eggThread = new MyThread("Egg", count, sleepTime);

        henThread.start();
        eggThread.start();

        henThread.join();
        eggThread.join();

        for (int i = 0; i < count; i++) {
            Thread.sleep(sleepTime);
            System.out.println("Human");
        }
    }
}
