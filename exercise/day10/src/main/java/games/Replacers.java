package games;

import java.util.Optional;

enum Replacers {
    FIZZBUZZ(15, "FizzBuzz"),
    FIZZ(3, "Fizz"),
    BUZZ(5, "Buzz");

    private final int modulo;
    private final String value;

    Replacers(int modulo, String value) {

        this.modulo = modulo;
        this.value = value;
    }

    private boolean matches(int input) {
        return input % modulo == 0;
    }

    public Optional<String> replace(int input) {
        return Optional.of(input)
                .filter(this::matches)
                .map(i -> value);
    }
}
