package ex01;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isPrime = true;
        int number;
        int i;

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
            if (number < 2) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            int sqrt = mySqrt(number);

            for (i = 2; i <= sqrt; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            System.out.println(isPrime + " " + (i - 1));
        } else {
            System.err.println("Not Integer Type");
        }
        scanner.close();
    }

    static int mySqrt(int number) {
        for (int i = 2; i < number; i++) {
            if (i * i == number) {
                return i;
            } else if (i * i > number) {
                return i - 1;
            }
        }
        return 0;
    }
}
