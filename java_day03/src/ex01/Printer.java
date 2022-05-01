package ex01;

public class Printer {

    private boolean transfer = true;

    public synchronized void    printMessage(String message, Type type) throws InterruptedException {
        if (type == Type.CONSUMER) {
            while (transfer) {
                wait();
            }
            transfer = true;
        } else if (type == Type.PRODUCER) {
            while (!transfer) {
                wait();
            }
            transfer = false;
        }
        System.out.println(message);
        notify();
    }
}
