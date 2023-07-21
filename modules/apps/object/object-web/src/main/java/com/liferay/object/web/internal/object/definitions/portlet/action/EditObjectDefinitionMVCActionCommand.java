/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.web.internal.object.definitions.portlet.action;

import com.liferay.object.constants.ObjectPortletKeys;
import com.liferay.object.exception.ObjectDefinitionAccountEntryRestrictedObjectFieldIdException;
import com.liferay.object.exception.ObjectDefinitionActiveException;
import com.liferay.object.exception.ObjectDefinitionLabelException;
import com.liferay.object.exception.ObjectDefinitionNameException;
import com.liferay.object.exception.ObjectDefinitionPluralLabelException;
import com.liferay.object.exception.ObjectDefinitionScopeException;
import com.liferay.object.exception.ObjectDefinitionStatusException;
import com.liferay.object.exception.RequiredObjectFieldException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	property = {
		"javax.portlet.name=" + ObjectPortletKeys.OBJECT_DEFINITIONS,
		"mvc.command.name=/object_definitions/edit_object_definition"
	},
	service = MVCActionCommand.class
)
public class EditObjectDefinitionMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String externalReferenceCode = ParamUtil.getString(
			actionRequest, "externalReferenceCode");
		long objectDefinitionId = ParamUtil.getLong(
			actionRequest, "objectDefinitionId");

		long accountEntryRestrictedObjectFieldId = ParamUtil.getLong(
			actionRequest, "accountEntryRestrictedObjectFieldId");
		long descriptionObjectFieldId = ParamUtil.getLong(
			actionRequest, "descriptionObjectFieldId");
		long titleObjectFieldId = ParamUtil.getLong(
			actionRequest, "titleObjectFieldId");
		boolean accountEntryRestricted = ParamUtil.getBoolean(
			actionRequest, "accountEntryRestricted");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");
		boolean enableCategorization = ParamUtil.getBoolean(
			actionRequest, "enableCategorization");
		boolean enableComments = ParamUtil.getBoolean(
			actionRequest, "enableComments");
		boolean enableLocalization = ParamUtil.getBoolean(
			actionRequest, "enableLocalization");
		boolean enableObjectEntryHistory = ParamUtil.getBoolean(
			actionRequest, "enableObjectEntryHistory");
		Map<Locale, String> labelMap = _localization.getLocalizationMap(
			actionRequest, "label");
		String name = ParamUtil.getString(actionRequest, "shortName");
		String panelCategoryOrder = ParamUtil.getString(
			actionRequest, "panelCategoryOrder");
		String panelCategoryKey = ParamUtil.getString(
			actionRequest, "panelCategoryKey");
		boolean portlet = ParamUtil.getBoolean(actionRequest, "portlet");
		Map<Locale, String> pluralLabelMap = _localization.getLocalizationMap(
			actionRequest, "pluralLabel");
		String scope = ParamUtil.getString(actionRequest, "scope");

		try {
			ObjectDefinition objectDefinition =
				_objectDefinitionService.getObjectDefinition(
					objectDefinitionId);

			if (objectDefinition.isUnmodifiableSystemObject()) {
				_objectDefinitionService.updateTitleObjectFieldId(
					objectDefinitionId, titleObjectFieldId);

				return;
			}

			if (objectDefinition.isEnableComments()) {
				enableComments = true;
			}

			_objectDefinitionService.updateCustomObjectDefinition(
				externalReferenceCode, objectDefinitionId,
				accountEntryRestrictedObjectFieldId, descriptionObjectFieldId,
				titleObjectFieldId, accountEntryRestricted, active,
				enableCategorization, enableComments, enableLocalization,
				enableObjectEntryHistory, labelMap, name, panelCategoryOrder,
				panelCategoryKey, portlet, pluralLabelMap, scope);

			if (StringUtil.equals(
					ParamUtil.getString(actionRequest, Constants.CMD),
					Constants.PUBLISH)) {

				_objectDefinitionService.publishCustomObjectDefinition(
					objectDefinitionId);
			}
		}
		catch (Exception exception) {
			if (exception instanceof
					ObjectDefinitionAccountEntryRestrictedObjectFieldIdException ||
				exception instanceof ObjectDefinitionActiveException ||
				exception instanceof ObjectDefinitionLabelException ||
				exception instanceof ObjectDefinitionNameException ||
				exception instanceof ObjectDefinitionPluralLabelException ||
				exception instanceof ObjectDefinitionScopeException ||
				exception instanceof ObjectDefinitionStatusException ||
				exception instanceof RequiredObjectFieldException) {

				SessionErrors.add(actionRequest, exception.getClass());

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else {
				throw exception;
			}
		}
	}

	@Reference
	private Localization _localization;

	@Reference
	private ObjectDefinitionService _objectDefinitionService;

}