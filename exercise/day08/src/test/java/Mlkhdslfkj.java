import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ExtensionMethod({PasswordExtensions.class})
public class Mlkhdslfkj {

    private static final String VALID_PASSWORD = "Azerty9%";

    // Contains at least 8 characters ✅
    // Contains at least one capital letter ✅
    // Contains at least one lowercase letter ✅
    // Contains at least a number ✅
    // Contains at least a special character in this list . * # @ $ % &.
    // Any other characters are not authorized.

    @Test
    void valid_password() {
        assertThat(verify(VALID_PASSWORD)).isTrue();
    }

    @Test
    void invalid_password() {
        assertThat(verify("jZ")).isFalse();
        assertThat(verify("fdjksghpzhvjz")).isFalse();
        assertThat(verify("FDJKSGHPZHVJZ")).isFalse();
        assertThat(verify("FDJKSGHPadgJZ")).isFalse();
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

    @Test
    void must_be_valid_when_has_a_capital_letter() {
        //given
        String password = "A";

        //when
        boolean result = verifyCapital(password);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_no_capital_letter() {
        //given
        String password = "a";

        //when
        boolean result = verifyCapital(password);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void must_be_valid_when_has_a_lower_case_letter() {
        //given
        String password = "a";

        //when
        boolean result = verifyLowerCase(password);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_no_lower_case_letter() {
        //given
        String password = "A";

        //when
        boolean result = verifyLowerCase(password);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void must_be_valid_when_has_a_number() {
        //given
        String password = "1a";

        //when

        boolean result = verifyNumber(password);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void must_be_invalid_when_has_no_number() {
        //given
        String password = "a";

        //when
        boolean result = verifyNumber(password);

        //then
        assertThat(result).isFalse();
    }

    private boolean verifyNumber(String password) {
        return password.verifyThatAtLeastOne(Character::isDigit);
    }

    private boolean verifyLowerCase(String password) {
        return password.verifyThatAtLeastOne(Character::isLowerCase);
    }

    private boolean verifyCapital(String password) {
        return password.verifyThatAtLeastOne(Character::isUpperCase);
    }

    private boolean verifySizeMin(String password) {
        return password.length() >= 8;
    }

    private boolean verify(String password) {
        return (
                verifySizeMin(password) &&
                        verifyCapital(password) &&
                        verifyLowerCase(password) &&
                        verifyNumber(password)
        );
    }
}
