package ex01;

public class MyThread extends Thread {

    private final static Printer    printer = new Printer();
    private final String            msg;
    private final int               count;
    private final Type              type;

    public MyThread(String msg, int count, Type type) {
        this.msg = msg;
        this.count = count;
        this.type = type;
    }

    public void run() {
        for (int i = 0; i < this.count; i++) {
            try {
                printer.printMessage(msg, this.type);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
