package de.firemage.autograder.core.check.complexity;

import de.firemage.autograder.core.LocalizedMessage;
import de.firemage.autograder.core.ProblemType;
import de.firemage.autograder.core.check.ExecutableCheck;
import de.firemage.autograder.core.integrated.FactoryUtil;
import de.firemage.autograder.core.integrated.IntegratedCheck;
import de.firemage.autograder.core.integrated.StatementUtil;
import de.firemage.autograder.core.integrated.StaticAnalysis;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtStatement;

import java.util.List;
import java.util.Map;

@ExecutableCheck(reportedProblems = { ProblemType.MERGE_NESTED_IF, ProblemType.UNMERGED_ELSE_IF })
public class ChainedIfCheck extends IntegratedCheck {
    @Override
    protected void check(StaticAnalysis staticAnalysis) {
        staticAnalysis.processWith(new AbstractProcessor<CtIf>() {
            @Override
            public void process(CtIf ctIf) {
                // skip `if (a);` (no block)
                if (ctIf.getThenStatement() == null) {
                    return;
                }

                // check if the if-statement has a nested if:
                List<CtStatement> thenStatements = StatementUtil.getEffectiveStatements(ctIf.getThenStatement());
                if (thenStatements.size() == 1
                    && thenStatements.get(0) instanceof CtIf nestedIf
                    && (nestedIf.getElseStatement() == null
                        || StatementUtil.getEffectiveStatements(nestedIf.getElseStatement()).isEmpty())) {
                    addLocalProblem(
                        ctIf.getCondition(),
                        new LocalizedMessage(
                            "merge-nested-if",
                            Map.of(
                                "suggestion", FactoryUtil.createBinaryOperator(
                                    ctIf.getCondition(),
                                    nestedIf.getCondition(),
                                    spoon.reflect.code.BinaryOperatorKind.AND
                                )
                            )
                        ),
                        ProblemType.MERGE_NESTED_IF
                    );
                }

                CtStatement elseStatement = ctIf.getElseStatement();
                if (!(elseStatement instanceof CtBlock<?> ctBlock) || ctBlock.getStatements().isEmpty()) {
                    return;
                }

                List<CtStatement> statements = StatementUtil.getEffectiveStatements(ctBlock);
                if (statements.size() != 1) {
                    return;
                }

                if (statements.get(0) instanceof CtIf ctElseIf && !elseStatement.isImplicit()) {
                    addLocalProblem(
                        ctElseIf.getCondition(),
                        new LocalizedMessage("suggest-replacement", Map.of(
                            "original", "else {\"{\"} if (...) {\"{\"} ... {\"}\"} {\"}\"}",
                            "suggestion", "else if (...) {\"{\"} ... {\"}\"}"
                        )),
                        ProblemType.UNMERGED_ELSE_IF
                    );
                }
            }
        });
    }
}
