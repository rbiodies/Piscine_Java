package ex01;

public class MyThread extends Thread {
    private final static Printer printer = new Printer();
    private final String msg;
    private final int count;
    private final Type type;

    public MyThread(String msg, int count, Type type) {
        this.msg = msg;
        this.count = count;
        this.type = type;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            printer.printMessage(msg, type);
        }
    }
}
