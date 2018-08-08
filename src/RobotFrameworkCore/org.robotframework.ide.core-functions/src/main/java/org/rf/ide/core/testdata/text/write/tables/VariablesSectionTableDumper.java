/*
 * Copyright 2016 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.rf.ide.core.testdata.text.write.tables;

import java.util.ArrayList;
import java.util.List;

import org.rf.ide.core.testdata.model.table.VariableTable;
import org.rf.ide.core.testdata.text.write.DumperHelper;
import org.rf.ide.core.testdata.text.write.SectionBuilder.SectionType;
import org.rf.ide.core.testdata.text.write.tables.variables.DictionaryVariableDumper;
import org.rf.ide.core.testdata.text.write.tables.variables.ListVariableDumper;
import org.rf.ide.core.testdata.text.write.tables.variables.ScalarVariableDumper;
import org.rf.ide.core.testdata.text.write.tables.variables.UnknownVariableDumper;

public class VariablesSectionTableDumper extends ANotExecutableTableDumper<VariableTable> {

    public VariablesSectionTableDumper(final DumperHelper aDumpHelper) {
        super(aDumpHelper, getDumpers(aDumpHelper), false);
    }

    private static List<ISectionElementDumper<VariableTable>> getDumpers(final DumperHelper aDumpHelper) {
        final List<ISectionElementDumper<VariableTable>> dumpers = new ArrayList<>();
        dumpers.add(new ScalarVariableDumper(aDumpHelper));
        dumpers.add(new ListVariableDumper(aDumpHelper));
        dumpers.add(new DictionaryVariableDumper(aDumpHelper));
        dumpers.add(new UnknownVariableDumper(aDumpHelper));

        return dumpers;
    }

    @Override
    public SectionType getSectionType() {
        return SectionType.VARIABLES;
    }
}
