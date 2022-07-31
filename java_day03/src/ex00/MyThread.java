package ex00;

public class MyThread extends Thread {
    private final String msg;
    private final int count;

    public MyThread(String msg, int count) {
        this.msg = msg;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(msg);
        }
    }
}
