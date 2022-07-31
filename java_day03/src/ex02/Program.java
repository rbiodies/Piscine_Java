package ex02;

public class Program {

    public static void  main(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.err.println("Usage: --arraySize=<array of size> --threadsCount=<count of threads>");
        } else {
            try {
                int arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
                int threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
                int[] array = new int[arraySize];
                int sum = 0;

                if (arraySize < 1 || arraySize > 2_000_000) {
                    System.err.println("Error: Size of array should be > 0 and <= 2_000_000!");
                } else if (threadsCount < 1 || threadsCount > arraySize) {
                    System.err.println("Error: Count of threads should be > 0 and <= size of array!");
                } else {
                    for (int i = 0; i < arraySize; i++) {
                        array[i] = (int) (Math.random() * 2001 - 1000);
                        sum += array[i];
                    }

                    System.out.printf("Sum: %d\n", sum);

                    Thread[] threads = new Thread[threadsCount];
                    int threadSize;

                    if (arraySize == threadsCount) {
                        threadSize = 1;
                    } else {
                        threadSize = arraySize / threadsCount + 1;
                    }

                    for (int i = 0; i < threadsCount; i++) {
                        threads[i] = new Thread(new CounterThread(array, threadSize));
                    }

                    for (Thread thread : threads) {
                        thread.start();
                    }

                    for (Thread thread : threads) {
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.printf("Sum by threads: %d\n", new CounterThread(array, threadSize).getGlobalSum());
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Arguments have not integer numbers!");
            }
        }
    }
}
