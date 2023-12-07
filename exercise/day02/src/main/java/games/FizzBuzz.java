package games;

import lombok.experimental.ExtensionMethod;

@ExtensionMethod({FizzBuzzExtensions.class})
public class FizzBuzz {

    public static final int LOWER_BOUND = 0;
    public static final int HIGHER_BOUND = 100;

    private FizzBuzz() {
    }

    public static String convert(Integer input) {
        assertCorrectRange(input);

        if (input.isFizz() && input.isBuzz()) {
            return "FizzBuzz";
        }
        if (input.isFizz()) {
            return "Fizz";
        }
        if (input.isBuzz()) {
            return "Buzz";
        }
        return input.toString();
    }


    private static void assertCorrectRange(Integer input) {
        if (input <= LOWER_BOUND || input > HIGHER_BOUND) {
            throw new OutOfRangeException();
        }
    }
}
