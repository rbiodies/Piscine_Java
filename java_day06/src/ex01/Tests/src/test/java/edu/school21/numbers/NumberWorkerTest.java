package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, Integer.MAX_VALUE})
    public void isPrimeForPrimes(int isPrime) {
        assertTrue(new NumberWorker().isPrime(isPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, Integer.MAX_VALUE - 1})
    public void isPrimeForNotPrimes(int notPrime) {
        assertFalse(new NumberWorker().isPrime(notPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -2, -1, 0, 1})
    public void isPrimeForIncorrectNumbers(int incorrectNum) {
        assertThrows(RuntimeException.class, () -> new NumberWorker().isPrime(incorrectNum));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void isSumCorrect(int input, int expected) {
        assertEquals(expected, new NumberWorker().digitsSum(input));
    }
}
