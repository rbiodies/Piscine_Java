package ex01;

public class Printer {
    private boolean transfer = true;

    public synchronized void printMessage(String message, Type type) {
        if (type == Type.CONSUMER) {
            if (transfer) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            transfer = true;
        } else if (type == Type.PRODUCER) {
            if (!transfer) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            transfer = false;
        }
        System.out.println(message);
        notify();
    }
}
