import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Mlkhdslfkj {
    private static final String VALID_PASSWORD = "Azerty9%";

    // Contains at least 8 characters ✅
    // Contains at least one capital letter
    // Contains at least one lowercase letter
    // Contains at least a number
    // Contains at least a special character in this list . * # @ $ % &.
    // Any other characters are not authorized.


    @Test
    void valid_password() {
        assertThat(verify(VALID_PASSWORD)).isTrue();
    }

    @Test
    void must_be_valid_when_password_has_at_least_8_characters() {
        // given
        String password = "abcdefghij";

        // when
        boolean result = verifySizeMin(password);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_less_than_8_characters() {
        // given
        String password = "abc";

        // when
        boolean result = verifySizeMin(password);

        // then
        assertThat(result).isFalse();
    }

    private boolean verifySizeMin(String password) {
        return password.length() >= 8;
    }

    private boolean verify(String password) {
        return verifySizeMin(password);
    }

}
