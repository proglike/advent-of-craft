import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {

    private static final String VALID_PASSWORD = "Azerty9%";

    // Contains at least 8 characters ✅
    // Contains at least one capital letter ✅
    // Contains at least one lowercase letter ✅
    // Contains at least a number ✅
    // Contains at least a special character in this list . * # @ $ % &.
    // Any other characters are not authorized.


    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    void valid_password() {
        assertThat(passwordValidator.verify(VALID_PASSWORD)).isTrue();
    }

    @Test
    void invalid_password() {
        assertThat(passwordValidator.verify("jZ")).isFalse();
        assertThat(passwordValidator.verify("fdjksghpzhvjz")).isFalse();
        assertThat(passwordValidator.verify("FDJKSGHPZHVJZ")).isFalse();
        assertThat(passwordValidator.verify("FDJKSGHPadgJZ")).isFalse();
    }

    @Test
    void must_be_valid_when_password_has_at_least_8_characters() {
        // given
        String password = "abcdefghij";

        // when
        boolean result = passwordValidator.verifySizeMin(password);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_less_than_8_characters() {
        // given
        String password = "abc";

        // when
        boolean result = passwordValidator.verifySizeMin(password);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void must_be_valid_when_has_a_capital_letter() {
        //given
        String password = "A";

        //when
        boolean result = passwordValidator.verifyCapital(password);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_no_capital_letter() {
        //given
        String password = "a";

        //when
        boolean result = passwordValidator.verifyCapital(password);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void must_be_valid_when_has_a_lower_case_letter() {
        //given
        String password = "a";

        //when
        boolean result = passwordValidator.verifyLowerCase(password);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_no_lower_case_letter() {
        //given
        String password = "A";

        //when
        boolean result = passwordValidator.verifyLowerCase(password);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void must_be_valid_when_has_a_number() {
        //given
        String password = "1a";

        //when

        boolean result = passwordValidator.verifyNumber(password);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_has_no_number() {
        //given
        String password = "a";

        //when
        boolean result = passwordValidator.verifyNumber(password);

        //then
        assertThat(result).isFalse();
    }

}
