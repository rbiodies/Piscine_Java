package classes;

import interfaces.Printer;
import interfaces.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private final Renderer  renderer;
    private LocalDateTime   time;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public void print(String str) {
        if (time != null) {
            renderer.render(time + str);
        }
        renderer.render(str);
    }
}
