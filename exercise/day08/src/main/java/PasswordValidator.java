import lombok.experimental.ExtensionMethod;

@ExtensionMethod({PasswordExtensions.class})
public class PasswordValidator {
    public boolean verify(String password) {
        return (
                verifySizeMin(password) &&
                        verifyCapital(password) &&
                        verifyLowerCase(password) &&
                        verifyNumber(password)
        );
    }

    boolean verifySizeMin(String password) {
        return password.length() >= 8;
    }

    boolean verifyNumber(String password) {
        return password.verifyThatAtLeastOne(Character::isDigit);
    }

    boolean verifyLowerCase(String password) {
        return password.verifyThatAtLeastOne(Character::isLowerCase);
    }

    boolean verifyCapital(String password) {
        return password.verifyThatAtLeastOne(Character::isUpperCase);
    }
}
