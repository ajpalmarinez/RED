/*
 * Copyright 2018 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.rf.ide.core.testdata.text.read.recognizer.tasks;

import java.util.regex.Pattern;

import org.rf.ide.core.testdata.model.RobotVersion;
import org.rf.ide.core.testdata.text.read.recognizer.AExecutableElementSettingsRecognizer;
import org.rf.ide.core.testdata.text.read.recognizer.ATokenRecognizer;
import org.rf.ide.core.testdata.text.read.recognizer.RobotTokenType;

public class TaskTagsRecognizer extends AExecutableElementSettingsRecognizer {

    public static final Pattern EXPECTED = Pattern
            .compile("[ ]?((\\[\\s*" + createUpperLowerCaseWord("Tags") + "\\s*\\]))");

    public TaskTagsRecognizer() {
        super(RobotTokenType.TASK_SETTING_TAGS_DECLARATION);
    }

    @Override
    public boolean isApplicableFor(final RobotVersion robotVersion) {
        return robotVersion.isNewerOrEqualTo(new RobotVersion(3, 1));
    }

    @Override
    public ATokenRecognizer newInstance() {
        return new TaskTagsRecognizer();
    }
}
