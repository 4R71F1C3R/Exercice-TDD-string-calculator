package com.github.glo2003;

import java.util.List;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(List<Integer> numbers) {
        super("Negative numbers were encountered: " + numbers.stream()
                                                        .map(String::valueOf)
                                                        .reduce("", (x, y) -> x + ", " + y));
    }
}
