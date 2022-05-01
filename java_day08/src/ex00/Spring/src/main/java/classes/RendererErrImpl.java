package classes;

import interfaces.PreProcessor;
import interfaces.Renderer;

public class RendererErrImpl implements Renderer {
    private final PreProcessor  preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String str) {
        System.err.println(preProcessor.process(str));
    }
}
