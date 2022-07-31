package ex02;

import java.util.Scanner;

public class Program {

    public static void  main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        int count = 0;

        while (number != 42) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
            } else {
                System.err.println("Not Integer Type");
                System.exit(-1);
            }
            if (isPrime(countSum(number))) {
                count++;
            }
        }
        System.out.printf("Count of coffee-request – %d\n", count);
        scanner.close();
    }

    static int countSum(int number) {
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    static boolean isPrime(int sum) {
        int i;

        if (sum < 2) {
            return false;
        } else if (sum == 2) {
            return true;
        }
        int sqrt = mySqrt(sum);

        for (i = 2; i <= sqrt; i++) {
            if (sum % i == 0) {
                return false;
            }
        }
        return true;
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
