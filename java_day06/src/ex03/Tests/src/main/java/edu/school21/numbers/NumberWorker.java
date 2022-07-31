package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException();
        }
        for (int i = 2; i * i <= number && i <= Math.sqrt(Integer.MAX_VALUE); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitSum(int number) {
        if (number == 0) {
            return 0;
        }
        return (number % 10 + digitSum(number / 10));
    }

    static class IllegalNumberException extends RuntimeException {

        public IllegalNumberException() {
            super("The number should be > 1");
        }
    }
}
