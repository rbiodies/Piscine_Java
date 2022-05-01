
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean result = true;
        int     i;

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            System.err.println("Input the number of integer type for his check!");
            System.exit(-1);
        }
        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        for (i = 2; i <= mySqrt(number); i++) {
            if (number % i == 0) {
                result = false;
                break;
            }
        }
        System.out.println(result + " " + (i - 1));
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
