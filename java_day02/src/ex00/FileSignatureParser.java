import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileSignatureParser {

    public List<String> getResultingTypes(Map<short[], String> map) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<String> ret = new ArrayList<>();
        String  input = scanner.nextLine();
        while (!input.equals("42")) {
            checkPath(input);
            boolean wasProcessed = false;
            for (short[] key : map.keySet()) {
                FileInputStream fileScanner = new FileInputStream(input);
                short[] fileContent = getBytes(key.length, fileScanner);
                fileScanner.close();
                if (compareBytes(key, fileContent)) {
                    ret.add(map.get(key));
                    wasProcessed = true;
                    break;
                }
            }
            System.out.println(wasProcessed ? "PROCESSED" : "UNDEFINED");
            input = scanner.nextLine();
        }
        return ret;
    }

    static void checkPath(String input) {
        File path = new File(input);

        if (!path.isFile()) {
            System.out.println("Error: File not found!");
            System.exit(-1);
        }
    }

    private short[] getBytes(int length, FileInputStream fileScanner) throws IOException {
        short[] bytes = new short[length];

        for (int i = 0; i < length; i++) {
            bytes[i] = (short) fileScanner.read();
        }
        return bytes;
    }

    private boolean compareBytes(short[] key, short[] fileContent) {
        for (int i = 0; i < key.length; i++) {
            if (key[i] != fileContent[i]) {
                return false;
            }
        }
        return true;
    }
}
