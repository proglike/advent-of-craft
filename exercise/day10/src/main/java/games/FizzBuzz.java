package games;

import static games.Replacers.*;

public class FizzBuzz {

    public static final int MIN = 0;
    public static final int MAX = 100;

    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }
        return convertSafely(input);
    }

    private static String convertSafely(Integer input) {
        return FIZZBUZZ.replace(input)
                .or(() -> FIZZ.replace(input))
                .or(() -> BUZZ.replace(input))
                .orElseGet(input::toString);
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= MIN || input > MAX;
    }
}
