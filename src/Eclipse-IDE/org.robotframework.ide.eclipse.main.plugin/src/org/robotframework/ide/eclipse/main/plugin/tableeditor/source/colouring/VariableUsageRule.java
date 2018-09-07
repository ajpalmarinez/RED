/*
 * Copyright 2016 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.robotframework.ide.eclipse.main.plugin.tableeditor.source.colouring;

import java.util.List;
import java.util.Optional;

import org.eclipse.jface.text.rules.IToken;
import org.rf.ide.core.testdata.model.table.exec.descs.VariableExtractor;
import org.rf.ide.core.testdata.model.table.exec.descs.ast.mapping.IElementDeclaration;
import org.rf.ide.core.testdata.model.table.exec.descs.ast.mapping.MappingResult;
import org.rf.ide.core.testdata.text.read.IRobotLineElement;
import org.rf.ide.core.testdata.text.read.IRobotTokenType;
import org.rf.ide.core.testdata.text.read.RobotLine;
import org.rf.ide.core.testdata.text.read.recognizer.RobotToken;
import org.rf.ide.core.testdata.text.read.recognizer.RobotTokenType;

public class VariableUsageRule implements ISyntaxColouringRule {

    protected final IToken varToken;

    protected final IToken nonVarToken;

    public VariableUsageRule(final IToken varToken) {
        this(varToken, ISyntaxColouringRule.DEFAULT_TOKEN);
    }

    public VariableUsageRule(final IToken varToken, final IToken nonVarToken) {
        this.varToken = varToken;
        this.nonVarToken = nonVarToken;
    }

    @Override
    public boolean isApplicable(final IRobotLineElement token) {
        return token instanceof RobotToken;
    }

    @Override
    public Optional<PositionedTextToken> evaluate(final IRobotLineElement token, final int offsetInToken,
            final List<RobotLine> context) {
        final List<IRobotTokenType> tokenTypes = token.getTypes();

        if (tokenTypes.contains(RobotTokenType.VARIABLE_USAGE)) {
            final VariableExtractor extractor = createVariableExtractor();
            final MappingResult extract = extractor.extract((RobotToken) token, null);
            return evaluateVariables(token, offsetInToken, extract.getMappedElements());
        }
        return Optional.empty();
    }

    protected VariableExtractor createVariableExtractor() {
        return new VariableExtractor();
    }

    private Optional<PositionedTextToken> evaluateVariables(final IRobotLineElement token, final int offsetInToken,
            final List<IElementDeclaration> declarations) {
        for (int i = 0; i < declarations.size(); i++) {
            final IElementDeclaration declaration = declarations.get(i);
            final int startOffset = declaration.getStartFromFile().getOffset();
            final int endOffset = declaration.getEndFromFile().getOffset();
            final int currentOffset = token.getStartOffset() + offsetInToken;
            if (currentOffset <= startOffset || currentOffset <= endOffset) {
                final IToken tokenToUse = declaration.isComplex() ? varToken : nonVarToken;
                final int offsetToUse = i == 0 ? startOffset + offsetInToken : startOffset;
                return Optional.of(new PositionedTextToken(tokenToUse, offsetToUse, endOffset - offsetToUse + 1));
            }
        }
        return Optional.empty();
    }
}
