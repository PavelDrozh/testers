package ru.yandex.practicum.testers;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    private static final ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        int result = calculator.calculatePolishNotation("1 2 +");
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateDifference() {
        int result = calculator.calculatePolishNotation("1 2 -");
        int expected = -1;
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateMultiply() {
        int result = calculator.calculatePolishNotation("1 2 *");
        int expected = 2;
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateAllOperations() {
        int result = calculator.calculatePolishNotation("1 2 5 6 - + *");
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateAllOperationsWithNegate() {
        int result = calculator.calculatePolishNotation("-1 2 5 -6 - + *");
        int expected = -13;
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateAllOperationsWithDoubleBlanks() {
        int result = calculator.calculatePolishNotation("1  2  5  6  -  +  *");
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateAllOperationsWithTabs() {
        int result = calculator.calculatePolishNotation("1  2   5   6   -   +   *");
        int expected = 1;
        assertEquals(expected, result);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
