package classes;

import interfaces.PreProcessor;
import interfaces.Renderer;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor  preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String str) {
        System.out.println(preProcessor.process(str));
    }
}
