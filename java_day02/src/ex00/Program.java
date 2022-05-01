import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Program {

    public static void main(String[] args) throws IOException {
        String signatures = System.getProperty("user.dir") + "/signatures.txt";
        File path = new File(signatures);

        if (!path.isFile() || !signatures.endsWith(".txt")) {
            System.out.println("Error: File not found!");
            return;
        }

        SignatureParser signatureParser = new SignatureParser(new File(signatures));
        Map<short[], String> parsedSignatures = signatureParser.parseKeyValues();
        List<String> res = new FileSignatureParser().getResultingTypes(parsedSignatures);

        saveResult(res);
    }

    public static void  saveResult(List<String> res) throws IOException {
        String  result = System.getProperty("user.dir") + "/result.txt";
        FileOutputStream    outputStream = new FileOutputStream(result);

        for (String line : res) {
            outputStream.write((line + '\n').getBytes(StandardCharsets.UTF_8));
        }
    }
}
