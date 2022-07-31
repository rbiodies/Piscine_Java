package preprocess;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String changeRegister(String str) {
        return str.toUpperCase();
    }
}
