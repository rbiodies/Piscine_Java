package ex00;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SignatureParser {
    private final Scanner scanner;

    public SignatureParser(File signatures) throws FileNotFoundException {
        this.scanner = new Scanner(signatures);
    }

    public Map<short[],String> parseKeyValues() {
        Map<short[], String> map = new HashMap<>();
        String line = readLine();

        while (!line.isEmpty()) {
            short[] key = convertKeyToBytes(getKey(line));
            String value = getValue(line);

            if (key.length > 0 && !value.isEmpty()) {
                map.put(key, value);
            }
            line = readLine();
        }
        scanner.close();
        return map;
    }

    private String readLine() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "";
        }
    }

    private String  getKey(String line) {
        return line.substring(line.indexOf(',') + 2);
    }

    private short[] convertKeyToBytes(String key) {
        int count = countBytes(key);
        short[] bytes = new short[count];
        Scanner keyParser = new Scanner(key).useRadix(16);

        for (int i = 0; i < count; i++) {
            bytes[i] = keyParser.nextShort();
        }
        return bytes;
    }

    private int countBytes(String key) {
        int count = 0;
        Scanner keyParser = new Scanner(key).useRadix(16);

        while (keyParser.hasNextLine()) {
            keyParser.nextShort();
            count++;
        }
        keyParser.close();
        return count;
    }

    private String getValue(String line) {
        return line.substring(0, line.indexOf(','));
    }
}
