
import java.util.Scanner;

public class Program {

    public static void  main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int     number;
        int     count = 0;

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            System.err.println("Input the number of integer type for check his sum of digits!");
            return;
        }
        while (number != 42) {
            if (checkSum(countSum(number))) {
                count++;
            }
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
            } else {
                System.err.println("Input the number of integer type for check his sum of digits!");
                break;
            }
        }
        System.out.printf("Count of coffee-request – %d\n", count);
    }

    static int countSum(int number) {
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    static boolean checkSum(int sum) {
        int i;

        if (sum <= 1) {
            return false;
        } else if (sum == 2) {
            return true;
        }
        for (i = 2; i <= mySqrt(sum); i++) {
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
