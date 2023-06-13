package de.firemage.autograder.core.check.api;

import de.firemage.autograder.core.LinterException;
import de.firemage.autograder.core.LocalizedMessage;
import de.firemage.autograder.core.Problem;
import de.firemage.autograder.core.ProblemType;
import de.firemage.autograder.core.StringSourceInfo;
import de.firemage.autograder.core.check.AbstractCheckTest;
import de.firemage.autograder.core.compiler.JavaVersion;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharRange extends AbstractCheckTest {
    private static final String LOCALIZED_MESSAGE_KEY = "char-range";

    @Test
    void testIsDigit() throws LinterException, IOException {
        List<Problem> problems = super.check(StringSourceInfo.fromSourceString(
            JavaVersion.JAVA_17,
            "Test",
            """
                public class Test {
                    public static boolean isNumber(char input) {
                        return input <= '9' && input >= '0';
                    }
                }
                """
        ), List.of(ProblemType.CHAR_RANGE));


        assertEquals(1, problems.size());
        assertEquals(ProblemType.CHAR_RANGE, problems.get(0).getProblemType());
        assertEquals(
            this.linter.translateMessage(
                new LocalizedMessage(
                    LOCALIZED_MESSAGE_KEY,
                    Map.of(
                        "suggestion", "Character.isDigit(input)"
                    )
                )),
            this.linter.translateMessage(problems.get(0).getExplanation())
        );
    }

    @Test
    void testIsLowerCase() throws LinterException, IOException {
        List<Problem> problems = super.check(StringSourceInfo.fromSourceString(
            JavaVersion.JAVA_17,
            "Test",
            """
                public class Test {
                    public static boolean isLowerCase(char c) {
                        return c >= 'a' && c <= 'z';
                    }
                }
                """
        ), List.of(ProblemType.CHAR_RANGE));


        assertEquals(1, problems.size());
        assertEquals(ProblemType.CHAR_RANGE, problems.get(0).getProblemType());
        assertEquals(
            this.linter.translateMessage(
                new LocalizedMessage(
                    LOCALIZED_MESSAGE_KEY,
                    Map.of(
                        "suggestion", "Character.isAlphabetic(c) && Character.isLowerCase(c)"
                    )
                )),
            this.linter.translateMessage(problems.get(0).getExplanation())
        );
    }

    @Test
    void testIsNotLowerCase() throws LinterException, IOException {
        List<Problem> problems = super.check(StringSourceInfo.fromSourceString(
            JavaVersion.JAVA_17,
            "Test",
            """
                public class Test {
                    public static boolean isNotLowerCase(char c) {
                        return c < 'a' || c > 'z';
                    }
                }
                """
        ), List.of(ProblemType.CHAR_RANGE));


        assertEquals(1, problems.size());
        assertEquals(ProblemType.CHAR_RANGE, problems.get(0).getProblemType());
        assertEquals(
            this.linter.translateMessage(
                new LocalizedMessage(
                    LOCALIZED_MESSAGE_KEY,
                    Map.of(
                        "suggestion", "(!Character.isAlphabetic(c)) || (!Character.isLowerCase(c))"
                    )
                )),
            this.linter.translateMessage(problems.get(0).getExplanation())
        );
    }

    @Test
    void testIsUpperCase() throws LinterException, IOException {
        List<Problem> problems = super.check(StringSourceInfo.fromSourceString(
            JavaVersion.JAVA_17,
            "Test",
            """
                public class Test {
                    public static boolean isUpperCase(char c) {
                        return c >= 'A' && 'Z' >= c;
                    }
                }
                """
        ), List.of(ProblemType.CHAR_RANGE));


        assertEquals(1, problems.size());
        assertEquals(ProblemType.CHAR_RANGE, problems.get(0).getProblemType());
        assertEquals(
            this.linter.translateMessage(
                new LocalizedMessage(
                    LOCALIZED_MESSAGE_KEY,
                    Map.of(
                        "suggestion", "Character.isAlphabetic(c) && Character.isUpperCase(c)"
                    )
                )),
            this.linter.translateMessage(problems.get(0).getExplanation())
        );
    }
}
