package com.github.glo2003;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void whenEmptyString_thenReturnsZero() {
        int result = calculator.add("");

        assertEquals(0, result);
    }

    @Test
    void whenNonNumericValues_thenThrowInvalidInput() {
        assertThrows(InvalidNumberFormatException.class,
                () -> calculator.add("4,a"));
    }

    @Test
    void whenOneValue_thenReturnValue() {
        int result = calculator.add("1");

        assertEquals(1, result);
    }

    @Test
    void whenOneNumericalValueAndOneEmptyString_thenReturnValue() {
        int result = calculator.add("1,");

        assertEquals(1, result);
    }

    @Test
    void whenTwoNumericalValues_thenReturnSum() {
        int result = calculator.add("1,2");

        assertEquals(3, result);
    }

    @Test
    void whenTwoNumericalValuesAndOneEmptyString_thenReturnSum() {
        int result = calculator.add("1,,2");

        assertEquals(3, result);
    }

    @Test
    void whenThreeNumericalValues_thenReturnSum() {
        int result = calculator.add("1,2,3");

        assertEquals(6, result);
    }

    @Test
    void whenFiveNumericalValues_thenReturnSum() {
        int result = calculator.add("1,2,3,4,5");

        assertEquals(15, result);
    }

    @Test
    void whenNumericalValuesSeparatedByNewline_thenReturnSum() {
        int result = calculator.add("1\n2,3");

        assertEquals(6, result);
    }

    @Test
    void whenNumericalValueFollowedByTwoEmptyString_thenReturnValue() {
        int result = calculator.add("1,\n");

        assertEquals(1, result);
    }

    @Test
    void whenGivenDelimiter_thenReturnValidSum() {
        int result = calculator.add("//;\n1;2\n3");

        assertEquals(6, result);
    }

    @Test
    void whenNegativeNumber_thenThrowNegativeNumberException() {
        assertThrows(NegativeNumberException.class,
                () -> calculator.add("4,-2"));
    }

    @Test
    void whenNumberUnderOrEqual1000_thenReturnSum() {
        int result = calculator.add("2,1000");

        assertEquals(1002, result);
    }

    @Test
    void whenNumberAbove1000_thenIgnore() {
        int result = calculator.add("2,1001");

        assertEquals(2, result);
    }

    @Test
    void whenGivenLongDelimiter_thenReturnSum() {
        int result = calculator.add("//[***]\n1***2***3");

        assertEquals(6, result);
    }

    @Test
    void whenGivenMultipleSingleCharacterDelimiters_thenReturnSum() {
        int result = calculator.add("//[*][%]\n1*2%3");

        assertEquals(6, result);
    }

    @Test
    void whenGivenMultipleLongDelimiters_thenReturnSum() {
        int result = calculator.add("//[***][%%%]\n1***2%%%3");

        assertEquals(6, result);
    }
}