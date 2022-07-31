package edu.school21.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    NumberWorker nw;

    @BeforeEach
    void beforeEachMethod() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 95219, Integer.MAX_VALUE})
    public void isPrimeForPrimes(int isPrime) {
        assertTrue(nw.isPrime(isPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 91239, Integer.MAX_VALUE - 1})
    public void isPrimeForNotPrimes(int notPrime) {
        assertFalse(nw.isPrime(notPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -15, -1, 0, 1})
    public void isPrimeForIncorrectNumbers(int incorrectNum) {
        assertThrows(RuntimeException.class, () -> nw.isPrime(incorrectNum));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void checkDigitsSum(int input, int expected) {
        assertEquals(expected, nw.digitsSum(input));
    }
}
