package ex03;

import java.util.Scanner;

public class Program {
    static Scanner scanner = new Scanner(System.in);

    public static void  main(String[] args) {
        int weekNum = 0;
        long allMin = 0;
        int grade;
        int min;

        while (true) {
            String week = scanner.nextLine();
            weekNum++;
            if (week.equals("42") || weekNum > 18) {
                break;
            } else if (!week.equals("Week " + weekNum)) {
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
        }
        for (int i = 1; i < weekNum; i++) {
            System.out.printf("Week %d ", i);
            putGraph(unpackGrade(allMin, i));
        }
        scanner.close();
    }

    static int inputInt() {
        int num = 0;

        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
            if (num < 1 || num > 9) {
                System.err.println("The numbers should be > 0 and < 10");
                scanner.close();
                System.exit(-1);
            }
        } else {
            System.err.println("Not Integer Type");
            scanner.close();
            System.exit(-1);
        }
        return num;
    }

    static long packGrade(int min, long allMin, int weekNum) {
        long powTen = 1;

        for (int i = 1; i < weekNum; i++) {
            powTen *= 10;
        }
        return allMin + min * powTen;
    }

    static long  unpackGrade(long allMin, int weekNum) {
        for (int i = 1; i < weekNum; i++) {
            allMin /= 10;
        }
        return allMin % 10;
    }

    static void putGraph(long min) {
        for (int i = 0; i < min; i++) {
            System.out.print("=");
        }
        System.out.println(">");
    }
}
