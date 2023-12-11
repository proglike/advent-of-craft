import java.util.function.Predicate;

public class PasswordExtensions {
    public static boolean verifyThatAtLeastOne(String password, Predicate<Character> predicate) {
        return password.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(predicate);
    }

}
