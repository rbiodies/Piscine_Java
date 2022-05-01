import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Program {

    public static void  main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
            System.err.println("Usage: --current-folder=<path of folder>");
            return;
        }

        try {
            String path = args[0].substring(args[0].indexOf('=') + 1);
            startPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  startPath(String path) throws IOException {
        File    file = new File(path);

        if (!file.isDirectory()) {
            System.err.println("Error: File is not directory!");
            return;
        }

        System.out.println(path);

        Scanner scanner = new Scanner(System.in);
        String  input = scanner.nextLine();

        while (!input.equals("exit")) {
            if (input. equals("ls")) {
                inputLs(file);
            } else if (input.startsWith("cd ")) {
                if (path.endsWith("/")) {
                    path += input.substring(input.indexOf(' ') + 1);
                } else {
                    path += "/" + input.substring(input.indexOf(' ') + 1);
                }
                startPath(path);
                break;
            } else if (input.startsWith("mv ")) {
                String[] inputArgs = input.split(" ");
                if (inputArgs.length == 3) {
                    inputMv(file, inputArgs[1].trim(), inputArgs[2].trim());
                } else {
                    System.err.println("Error: Invalid number of arguments!");
                }
            } else {
                System.err.println("Error: Command not found!");
            }
            input = scanner.nextLine();
        }
    }

    public static void inputLs(File dir) {
        if (!dir.isDirectory()) {
            System.err.println("Error: Directory not found!");
            return;
        }
        for (File item : Objects.requireNonNull(dir.listFiles())) {
            if (item.isDirectory()) {
                System.out.println(item.getName() + " " + folderSize(item) / 1000 + " KB");
            } else {
                System.out.println(item.getName() + " " + item.length() / 1000 + " KB");
            }
        }
    }

    public static long folderSize(File dir) {
        long length = 0;

        for (File item : Objects.requireNonNull(dir.listFiles())) {
            if (item.isFile()) {
                length += item.length();
            } else {
                length += folderSize(item);
            }
        }
        return length;
    }

    public static void inputMv(File path, String pureFileName, String mvFolder) throws IOException {
        String  fileName = path + "/" + pureFileName;
        Path    filePath = Paths.get(fileName);

        String  mvFolderName = path + "/" + mvFolder;
        Path    mvFolderPath = Paths.get(mvFolderName);

        if (Files.isDirectory(mvFolderPath)) {
            mvFolderName += "/" + pureFileName;
            mvFolderPath = Paths.get(mvFolderName);
        }
        if (Files.exists(filePath)) {
            Files.move(filePath, mvFolderPath, REPLACE_EXISTING);
        } else {
            System.err.println("Error: File not found!");
        }
    }
}
