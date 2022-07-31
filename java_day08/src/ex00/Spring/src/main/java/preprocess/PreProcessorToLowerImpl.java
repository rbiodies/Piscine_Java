package preprocess;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String changeRegister(String str) {
        return str.toLowerCase();
    }
}
