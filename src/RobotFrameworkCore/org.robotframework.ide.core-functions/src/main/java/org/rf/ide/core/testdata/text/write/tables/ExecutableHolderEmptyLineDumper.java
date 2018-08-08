/*
 * Copyright 2017 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.rf.ide.core.testdata.text.write.tables;

import org.rf.ide.core.testdata.model.AModelElement;
import org.rf.ide.core.testdata.model.ModelType;
import org.rf.ide.core.testdata.model.table.IExecutableStepsHolder;
import org.rf.ide.core.testdata.model.table.RobotElementsComparatorWithPositionChangedPresave;
import org.rf.ide.core.testdata.text.write.DumperHelper;

class ExecutableHolderEmptyLineDumper extends AExecutableTableElementDumper {

    ExecutableHolderEmptyLineDumper(final DumperHelper helper, final ModelType modelType) {
        super(helper, modelType);
        addAfterSortTask(new ForContinueStartWithCommentFixer());
    }

    @Override
    public RobotElementsComparatorWithPositionChangedPresave getSorter(
            final AModelElement<? extends IExecutableStepsHolder<?>> currentElement) {
        return new RobotElementsComparatorWithPositionChangedPresave();
    }
}
