package de.firemage.autograder.core.check.api;

import de.firemage.autograder.core.LocalizedMessage;
import de.firemage.autograder.core.ProblemType;
import de.firemage.autograder.core.check.ExecutableCheck;
import de.firemage.autograder.core.dynamic.DynamicAnalysis;
import de.firemage.autograder.core.integrated.IntegratedCheck;
import de.firemage.autograder.core.integrated.SpoonUtil;
import de.firemage.autograder.core.integrated.StaticAnalysis;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtTypeInformation;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExecutableCheck(reportedProblems = { ProblemType.USE_FORMAT_STRING })
public class UseFormatString extends IntegratedCheck {
    private static final int MIN_NUMBER_CONCATENATIONS = 3;

    public UseFormatString() {
        super(new LocalizedMessage("use-format-string"));
    }


    private List<CtExpression<?>> getFormatArgs(CtBinaryOperator<?> ctBinaryOperator) {
        List<CtExpression<?>> result = new ArrayList<>();

        CtExpression<?> left = ctBinaryOperator.getLeftHandOperand();
        CtExpression<?> right = ctBinaryOperator.getRightHandOperand();

        result.add(right);

        while (left instanceof CtBinaryOperator<?> lhs) {
            result.add(lhs.getRightHandOperand());
            left = lhs.getLeftHandOperand();
        }

        result.add(left);

        Collections.reverse(result);

        return result;
    }

    private String getFormatPlaceholder(CtTypeReference<?> ctTypeReference) {
        // See https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html
        if (ctTypeReference.isPrimitive()) {
            return switch (ctTypeReference.getSimpleName()) {
                case "boolean" -> "%b";
                case "char" -> "%c";
                case "byte", "short", "int", "long" -> "%d";
                case "float", "double" -> "%f";
                default -> "%s";
            };
        }

        return "%s";
    }

    private String buildFormattedString(Iterable<? extends CtExpression<?>> ctExpressions) {
        StringBuilder formatString = new StringBuilder();
        Collection<String> args = new ArrayList<>();
        for (CtExpression<?> ctExpression : ctExpressions) {
            if (ctExpression instanceof CtLiteral<?> ctLiteral) {
                CtTypeInformation ctTypeInformation = ctLiteral.getType();
                if (ctLiteral.getValue() instanceof String value) {
                    if (value.equals("\n")) {
                        formatString.append("%n");
                    } else {
                        formatString.append(value);
                    }
                } else if (ctTypeInformation.isPrimitive() && !ctTypeInformation.isArray()) {
                    formatString.append(ctLiteral.getValue());
                }

                continue;
            }

            formatString.append(this.getFormatPlaceholder(ctExpression.getType()));
            args.add(ctExpression.prettyprint());
        }

        if (args.isEmpty()) {
            return "\"%s\"".formatted(formatString.toString());
        }

        return "\"%s\".formatted(%s)".formatted(formatString.toString(), String.join(", ", args));
    }

    private CtExpression<?> resolveExpression(StaticAnalysis staticAnalysis, CtExpression<?> ctExpression) {
        // convert System.lineSeparator() to "\n" which will later be converted to %n
        if (ctExpression instanceof CtInvocation<?> ctInvocation
            && ctInvocation.getTarget() instanceof CtTypeAccess<?> ctTypeAccess
            // ensure the method is called on java.lang.System
            && ctInvocation.getFactory().Type().createReference(java.lang.System.class)
                .equals(ctTypeAccess.getAccessedType())
            && ctInvocation.getExecutable().getSimpleName().equals("lineSeparator")) {
            return SpoonUtil.makeLiteral("\n");
        }

        if (ctExpression instanceof CtLiteral<?> ctLiteral
            && SpoonUtil.areLiteralsEqual(ctLiteral, SpoonUtil.makeLiteral('\n'))) {
            return SpoonUtil.makeLiteral("\n");
        }

        // inline constants:
        if (ctExpression instanceof CtVariableRead<?> ctVariableRead) {
            CtVariableReference<?> ctVariableReference = ctVariableRead.getVariable();

            Optional<CtExpression<?>> ctExpressionOptional = SpoonUtil.getEffectivelyFinalExpression(
                    staticAnalysis,
                    ctVariableReference
            );
            // only inline literals:
            if (ctExpressionOptional.isPresent() && ctExpressionOptional.get() instanceof CtLiteral<?> ctLiteral) {
                return ctLiteral;
            }
        }

        return ctExpression;
    }

    private void checkArgs(StaticAnalysis staticAnalysis, CtElement ctElement, Iterable<? extends CtExpression<?>> formatArgs) {
        Collection<CtExpression<?>> args = new ArrayList<>();
        for (var expression : formatArgs) {
            args.add(this.resolveExpression(staticAnalysis, expression));
        }

        // skip concatenations with less than 3 arguments
        if (args.size() < MIN_NUMBER_CONCATENATIONS) {
            return;
        }

        String formattedString = this.buildFormattedString(args);

        this.addLocalProblem(
                ctElement,
                new LocalizedMessage("use-format-string", Map.of("formatted", formattedString)),
                ProblemType.USE_FORMAT_STRING
        );
    }

    private void checkCtBinaryOperator(StaticAnalysis staticAnalysis, CtBinaryOperator<?> ctBinaryOperator) {
        // Do not visit nested binary operators
        //
        // For example this expression:
        // "a" + "b" + "c"
        //
        // will be represented in spoon as:
        // (("a" + "b") + "c")
        //
        // so we only want to visit the outermost binary operator
        if (ctBinaryOperator.getParent(CtBinaryOperator.class) != null) {
            return;
        }

        if (ctBinaryOperator.getKind() != BinaryOperatorKind.PLUS) {
            return;
        }

        // only visit binary operators that evaluate to a String
        // (should be guaranteed by the visitor)
        CtTypeReference<?> ctStringType = ctBinaryOperator.getFactory().Type().createReference(java.lang.String.class);
        if (!(ctStringType.equals(ctBinaryOperator.getType()))) {
            throw new IllegalStateException("Binary operator does not evaluate to a String");
        }

        this.checkArgs(staticAnalysis, ctBinaryOperator, this.getFormatArgs(ctBinaryOperator));
    }

    private void checkCtInvocation(StaticAnalysis staticAnalysis, CtInvocation<?> ctInvocation) {
        // sb.append("a").append("b").append("c") instead of sb.append("abc")
        // same for sb.append("a").append(someVar) instead of sb.append("a%s", someVar)
        CtTypeReference<?> stringBuilderType = ctInvocation.getFactory().Type().createReference(java.lang.StringBuilder.class);
        if (!ctInvocation.getType().equals(stringBuilderType)) {
            return;
        }

        if (!ctInvocation.getExecutable().getSimpleName().equals("append")) {
            return;
        }

        // only visit the outermost invocations
        if (ctInvocation.getParent(CtInvocation.class) != null) return;

        List<CtExpression<?>> formatArgs = new ArrayList<>();
        CtInvocation<?> currentInvocation = ctInvocation;
        // traverse the chain of append calls
        while (currentInvocation != null) {
            // if one of the calls is not an append call, early exit (e.g. sb.append("a").toString())
            if (!currentInvocation.getExecutable().getSimpleName().equals("append")) {
                return;
            }

            List<CtExpression<?>> arguments = currentInvocation.getArguments();
            // only one argument should be passed to append
            if (arguments.size() != 1) {
                return;
            }

            formatArgs.addAll(arguments);

            if (currentInvocation.getTarget() instanceof CtInvocation<?> ctInvocationTarget) {
                currentInvocation = ctInvocationTarget;
            // the last part of the chain should be an expression of type StringBuilder
            } else if (!stringBuilderType.equals(currentInvocation.getTarget().getType())) {
                return;
            } else {
                currentInvocation = null;
            }
        }

        Collections.reverse(formatArgs);

        // skip concatenations with less than 3 arguments
        if (formatArgs.size() < MIN_NUMBER_CONCATENATIONS) {
            return;
        }

        this.checkArgs(staticAnalysis, ctInvocation, formatArgs);
    }

    @Override
    protected void check(StaticAnalysis staticAnalysis, DynamicAnalysis dynamicAnalysis) {
        staticAnalysis.processWith(new AbstractProcessor<CtExpression<String>>() {
            @Override
            public void process(CtExpression<String> ctExpression) {
                if (ctExpression instanceof CtBinaryOperator<?> ctBinaryOperator) {
                    checkCtBinaryOperator(staticAnalysis, ctBinaryOperator);
                } else if (ctExpression instanceof CtInvocation<?> ctInvocation) {
                    checkCtInvocation(staticAnalysis, ctInvocation);
                }
            }
        });
    }
}
