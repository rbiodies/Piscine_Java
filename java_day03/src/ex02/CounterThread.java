package ex02;

public class CounterThread implements Runnable {
    private static int threadNum = 1;
    private final int threadIndex;
    private final int[] array;
    private final int startIndex;
    private final int endIndex;
    private int threadSum = 0;
    private static int arraySum = 0;

    public CounterThread(int[] array, int threadSize) {
        this.threadIndex = threadNum;
        this.array = array;
        this.startIndex = threadSize * (threadNum - 1);
        this.endIndex = Math.min(threadSize * threadNum, array.length);
        threadNum++;
    }

    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            threadSum += array[i];
        }
        arraySum += threadSum;

        System.out.println(this);
    }

    public String toString() {
        return "Thread " + threadIndex +
                ": from " + startIndex +
                " to " + (endIndex - 1) +
                " sum is " + threadSum;
    }

    public int  getGlobalSum() {
        return arraySum;
    }
}
