package ex00;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Program {

    public static void main(String[] args) throws IOException {
        String signatures = "./src/ex00/signatures.txt";
        File path = new File(signatures);

        if (!path.isFile()) {
            System.err.println("Error: File not found!");
        } else {
            SignatureParser signatureParser = new SignatureParser(new File(signatures));
            Map<short[], String> parsedSignatures = signatureParser.parseKeyValues();
            List<String> filesTypes = new FileSignatureParser().getResultingTypes(parsedSignatures);

            writeResult(filesTypes);
        }
    }

    public static void  writeResult(List<String> filesTypes) throws IOException {
        String result = "./src/ex00/result.txt";
        FileOutputStream outputStream = new FileOutputStream(result);

        for (String type : filesTypes) {
            outputStream.write((type + '\n').getBytes(StandardCharsets.UTF_8));
        }
    }
}
