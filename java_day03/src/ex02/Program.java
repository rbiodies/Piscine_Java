package ex02;

public class Program {

    public static void  main(String[] args) throws InterruptedException {
         if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
             System.err.println("Usage: --arraySize=<array of size> --threadsCount=<count of threads>");
             return;
         }

         int    arraySize;
         int    threadsCount;

        try {
            arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
            threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
        } catch (NumberFormatException e) {
            System.err.println("Error: Arguments have not integer numbers!");
            return;
        }

         int[]  array = new int[arraySize];
         int    sum = 0;

         if (arraySize < 1 || arraySize > 2_000_000) {
             System.err.println("Size of array should be > 0 and <= 2_000_000!");
             return;
         }
         if (threadsCount < 1 || threadsCount > arraySize) {
             System.err.println("Count of threads should be > 0 and <= size of array!");
             return;
         }
         for (int i = 0; i < arraySize; i++) {
             array[i] = (int) (Math.random() * 2001 - 1000);
             sum += array[i];
         }

         Thread[]   threads = new Thread[threadsCount];
         int        threadSize;

         if (arraySize == threadsCount) {
             threadSize = 1;
         } else {
             threadSize = arraySize / threadsCount + 1;
         }

         for (int i = 0; i < threadsCount; i++) {
             threads[i] = new Thread(new CounterThread(array, threadSize));
         }

         System.out.printf("Sum: %d\n", sum);

         for (Thread thread : threads) {
             thread.start();
         }
         for (Thread thread : threads) {
            thread.join();
         }

         System.out.println("Sum by threads: " + new CounterThread(array, threadSize).getGlobalSum());
    }
}
