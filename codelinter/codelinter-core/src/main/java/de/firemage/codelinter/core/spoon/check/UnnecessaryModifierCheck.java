package de.firemage.codelinter.core.spoon.check;

import de.firemage.codelinter.core.ProblemCategory;
import de.firemage.codelinter.core.ProblemPriority;
import de.firemage.codelinter.core.spoon.ProblemLogger;
import spoon.reflect.declaration.CtMethod;

public class UnnecessaryModifierCheck extends AbstractLoggingProcessor<CtMethod<?>> {
    public static final String SYNCHRONIZED_DESCRIPTION = "Used 'synchronized'";
    public static final String STRICTFP_DESCRIPTION = "Used 'strictfp'";
    public static final String EXPLANATION = """
            You don't need to add synchronized or strictfp.
            These modifiers are not needed in the context of programming exercises.""";

    public UnnecessaryModifierCheck(ProblemLogger logger) {
        super(logger);
    }

    @Override
    public void process(CtMethod<?> element) {
        if (element.isSynchronized()) {
            addProblem(element, SYNCHRONIZED_DESCRIPTION, ProblemCategory.JAVA_FEATURE, EXPLANATION, ProblemPriority.FIX_RECOMMENDED);
        }
        if (element.isStrictfp()) {
            addProblem(element, STRICTFP_DESCRIPTION, ProblemCategory.JAVA_FEATURE, EXPLANATION, ProblemPriority.FIX_RECOMMENDED);
        }
    }
}