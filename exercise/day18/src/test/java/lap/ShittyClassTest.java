package lap;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMembers;

@AnalyzeClasses(packages = "lap", importOptions = ImportOption.DoNotIncludeTests.class)
class ShittyClassTest {

    @ArchTest
    ArchRule fields_name_should_not_have_two_consecutives_underscores =
            noMembers().should().haveNameContaining("__");


    @ArchTest
    ArchRule fields_name_should_not_have_underscore_at_extremity =
            noMembers().should().haveNameStartingWith("_")
                    .orShould().haveNameEndingWith("_");

    @ArchTest
    ArchRule getters_should_return_something =
            methods().that().haveNameStartingWith("get")
                    .should().notHaveRawReturnType(void.class);

    @ArchTest
    ArchRule only_boolean_accessors_should_starts_with_is =
            methods().that().haveNameStartingWith("is")
                    .should().haveRawReturnType(boolean.class)
                    .orShould().haveRawReturnType(Boolean.class);

}