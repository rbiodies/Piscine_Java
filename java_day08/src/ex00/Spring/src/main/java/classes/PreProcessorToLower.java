package classes;

import interfaces.PreProcessor;

public class PreProcessorToLower implements PreProcessor {
    @Override
    public String process(String str) {
        return str.toLowerCase();
    }
}
