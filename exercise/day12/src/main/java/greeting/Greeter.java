package greeting;

import static java.util.Objects.requireNonNullElse;

public class Greeter {
    private final Formality formality;

    public Greeter() {
        this.formality = Formality.NONE;
    }

    public Greeter(Formality formality) {
        this.formality = requireNonNullElse(formality, Formality.NONE);
    }

    public String greet() {
        return switch (this.formality) {
            case FORMAL -> "Good evening, sir.";
            case CASUAL -> "Sup bro?";
            case INTIMATE -> "Hello Darling!";
            case NONE -> "Hello.";
        };
    }
}
