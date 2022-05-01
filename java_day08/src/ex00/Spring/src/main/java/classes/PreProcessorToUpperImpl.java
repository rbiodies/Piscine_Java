package classes;

import interfaces.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String str) {
        return str.toUpperCase();
    }
}
