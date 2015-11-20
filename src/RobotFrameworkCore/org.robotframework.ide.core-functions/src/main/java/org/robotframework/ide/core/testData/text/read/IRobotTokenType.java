/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.robotframework.ide.core.testData.text.read;

import java.util.List;


public interface IRobotTokenType {

    List<String> getRepresentation();


    List<VersionAvailabilityInfo> getVersionAvailabilityInfos();


    VersionAvailabilityInfo findVersionAvailablilityInfo(final String text);
}
