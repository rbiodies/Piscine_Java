package ex00;

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
        List<String> values = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("42") || !isExist(input)) {
                break;
            }

            boolean wasProcessed = false;

            for (short[] key : map.keySet()) {
                FileInputStream fileScanner = new FileInputStream(input);
                short[] fileBytes = getBytes(key.length, fileScanner);

                fileScanner.close();
                if (isEqualBytes(key, fileBytes)) {
                    values.add(map.get(key));
                    wasProcessed = true;
                    break;
                }
            }
            System.out.println(wasProcessed ? "PROCESSED" : "UNDEFINED");
        }
        return values;
    }

    static boolean isExist(String input) {
        File path = new File(input);

        if (!path.isFile()) {
            System.err.println("Error: File not found!");
            return false;
        }
        return true;
    }

    private short[] getBytes(int length, FileInputStream fileScanner) throws IOException {
        short[] bytes = new short[length];

        for (int i = 0; i < length; i++) {
            bytes[i] = (short) fileScanner.read();
        }
        return bytes;
    }

    private boolean isEqualBytes(short[] key, short[] fileContent) {
        for (int i = 0; i < key.length; i++) {
            if (key[i] != fileContent[i]) {
                return false;
            }
        }
        return true;
    }
}