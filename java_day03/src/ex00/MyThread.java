package ex00;

public class MyThread extends Thread {

    private final String    msg;
    private final int       count;
    private final int       sleepTime;

    public MyThread(String msg, int count, int sleepTime) {
        this.msg = msg;
        this.count = count;
        this.sleepTime = sleepTime;
    }

    public void run() {
        for (int i = 0; i < this.count; i++) {
            try {
                Thread.sleep(sleepTime);
                System.out.println(this.msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
