
import java.util.Scanner;

public class Program {

    public static void  main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String  week = scanner.nextLine();
        int     min;
        int     weekNum = 1;
        int     allMin = 0;
        int     grade;

        while (!week.equals("42") && weekNum <= 18) {
            if (!week.equals("Week " + weekNum)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            min = inputInt();
            for (int i = 0; i < 4; i++) {
                grade = inputInt();
                if (min > grade) {
                    min = grade;
                }
            }
            scanner.nextLine();
            allMin = packGrade(min, allMin, weekNum);
            week = scanner.nextLine();
            weekNum++;
        }
        for (int i = 1; i < weekNum; i++) {
            System.out.printf("Week %d ", i);
            putGraph(unpackGrade(allMin, i));
        }
    }
    
    static int inputInt() {
        Scanner scanner = new Scanner(System.in);
        int     num = 0;
        
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
        }
        else {
            System.out.println("Input the number of integer type!");
            System.exit(-1);
        }
        if (num < 1 || num > 9) {
            System.out.println("Error: The number should be > 0 and < 10");
            System.exit(-1);
        }
        return num;
    }

    static int packGrade(int min, int allMin, int weekNum) {
        int powTen = 1;

        for (int i = 1; i < weekNum; i++) {
            powTen *= 10;
        }
        return allMin + min * powTen;
    }

    static int  unpackGrade(int allMin, int weekNum) {
        for (int i = 1; i < weekNum; i++) {
            allMin /= 10;
        }
        return allMin % 10;
    }

    static void putGraph(int min) {
        for (int i = 0; i < min; i++) {
            System.out.print("=");
        }
        System.out.println(">");
    }
}
