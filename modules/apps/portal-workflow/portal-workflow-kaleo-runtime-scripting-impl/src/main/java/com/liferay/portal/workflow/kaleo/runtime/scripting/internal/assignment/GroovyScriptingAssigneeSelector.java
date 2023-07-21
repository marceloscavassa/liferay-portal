/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.scripting.internal.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.assignment.ScriptingAssigneeSelector;
import com.liferay.portal.workflow.kaleo.runtime.constants.AssigneeConstants;
import com.liferay.portal.workflow.kaleo.runtime.scripting.internal.util.KaleoScriptingEvaluator;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "scripting.language=groovy",
	service = ScriptingAssigneeSelector.class
)
public class GroovyScriptingAssigneeSelector
	implements ScriptingAssigneeSelector {

	@Override
	public Map<String, ?> getAssignees(
			ExecutionContext executionContext,
			KaleoTaskAssignment kaleoTaskAssignment)
		throws PortalException {

		return _kaleoScriptingEvaluator.execute(
			executionContext, _outputNames,
			kaleoTaskAssignment.getAssigneeScriptLanguage(),
			kaleoTaskAssignment.getAssigneeScript());
	}

	private static final Set<String> _outputNames = new HashSet<>(
		Arrays.asList(
			AssigneeConstants.ROLES, AssigneeConstants.USER,
			AssigneeConstants.USERS,
			WorkflowContextUtil.WORKFLOW_CONTEXT_NAME));

	@Reference
	private KaleoScriptingEvaluator _kaleoScriptingEvaluator;

}