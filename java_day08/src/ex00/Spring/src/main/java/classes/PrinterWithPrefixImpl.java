package classes;

import interfaces.Printer;
import interfaces.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private final Renderer  renderer;
    private String          prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String str) {
        if (!prefix.isEmpty()) {
            prefix += " ";
        }
        renderer.render(prefix + str);
    }
}
