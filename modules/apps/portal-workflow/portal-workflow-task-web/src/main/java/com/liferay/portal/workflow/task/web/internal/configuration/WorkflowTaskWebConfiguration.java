/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.task.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Inácio Nery
 */
@ExtendedObjectClassDefinition(category = "workflow")
@Meta.OCD(
	id = "com.liferay.portal.workflow.task.web.internal.configuration.WorkflowTaskWebConfiguration",
	localization = "content/Language",
	name = "workflow-task-web-configuration-name"
)
public interface WorkflowTaskWebConfiguration {

	@Meta.AD(
		deflt = "list", name = "default-display-view",
		optionLabels = {"%list", "%table"},
		optionValues = {"descriptive", "list"}, required = false
	)
	public String defaultDisplayView();

}