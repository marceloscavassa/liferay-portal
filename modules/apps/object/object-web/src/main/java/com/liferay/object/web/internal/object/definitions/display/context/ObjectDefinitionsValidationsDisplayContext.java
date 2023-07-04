/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.object.web.internal.object.definitions.display.context;

import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.constants.ObjectValidationRuleConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectValidationRule;
import com.liferay.object.validation.rule.ObjectValidationRuleEngineRegistry;
import com.liferay.object.web.internal.object.definitions.display.context.util.ObjectCodeEditorUtil;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Selton Guedes
 */
public class ObjectDefinitionsValidationsDisplayContext
	extends BaseObjectDefinitionsDisplayContext {

	public ObjectDefinitionsValidationsDisplayContext(
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<ObjectDefinition>
			objectDefinitionModelResourcePermission,
		ObjectValidationRuleEngineRegistry objectValidationRuleEngineRegistry) {

		super(httpServletRequest, objectDefinitionModelResourcePermission);

		_objectValidationRuleEngineRegistry =
			objectValidationRuleEngineRegistry;
	}

	public String getEditObjectValidationURL() throws Exception {
		return PortletURLBuilder.create(
			getPortletURL()
		).setMVCRenderCommandName(
			"/object_definitions/edit_object_validation_rule"
		).setParameter(
			"objectValidationRuleId", "{id}"
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildString();
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems()
		throws Exception {

		boolean hasUpdatePermission = hasUpdateObjectDefinitionPermission();

		return Arrays.asList(
			new FDSActionDropdownItem(
				getEditObjectValidationURL(),
				hasUpdatePermission ? "pencil" : "view",
				hasUpdatePermission ? "edit" : "view",
				LanguageUtil.get(
					objectRequestHelper.getRequest(),
					hasUpdatePermission ? "edit" : "view"),
				"get", null, "sidePanel"),
			new FDSActionDropdownItem(
				"/o/object-admin/v1.0/object-validation-rules/{id}", "trash",
				"delete",
				LanguageUtil.get(objectRequestHelper.getRequest(), "delete"),
				"delete", "delete", "async"));
	}

	public List<Map<String, String>> getObjectValidationRuleEngines() {
		return ListUtil.sort(
			TransformUtil.transform(
				_objectValidationRuleEngineRegistry.
					getObjectValidationRuleEngines(),
				objectValidationRuleEngine -> HashMapBuilder.put(
					"label",
					LanguageUtil.get(
						objectRequestHelper.getLocale(),
						objectValidationRuleEngine.getName())
				).put(
					"name", objectValidationRuleEngine.getName()
				).build()),
			Comparator.comparing(item -> item.get("label")));
	}

	public Map<String, Object> getProps(
			ObjectValidationRule objectValidationRule)
		throws PortalException {

		return HashMapBuilder.<String, Object>put(
			"objectValidationRule",
			HashMapBuilder.<String, Object>put(
				"active", objectValidationRule.isActive()
			).put(
				"engine", objectValidationRule.getEngine()
			).put(
				"engineLabel",
				LanguageUtil.get(
					objectRequestHelper.getLocale(),
					objectValidationRule.getEngine())
			).put(
				"errorLabel",
				LocalizationUtil.getLocalizationMap(
					objectValidationRule.getErrorLabel())
			).put(
				"id", objectValidationRule.getObjectValidationRuleId()
			).put(
				"name",
				LocalizationUtil.getLocalizationMap(
					objectValidationRule.getName())
			).put(
				"script", objectValidationRule.getScript()
			).build()
		).put(
			"objectValidationRuleElements",
			_createObjectValidationRuleElements(
				objectValidationRule.getEngine())
		).put(
			"objectValidationRuleEngines", getObjectValidationRuleEngines()
		).put(
			"readOnly", !hasUpdateObjectDefinitionPermission()
		).build();
	}

	@Override
	protected String getAPIURI() {
		return "/object-validation-rules";
	}

	@Override
	protected UnsafeConsumer<DropdownItem, Exception>
		getCreationMenuDropdownItemUnsafeConsumer() {

		return dropdownItem -> {
			dropdownItem.setHref("addObjectValidation");
			dropdownItem.setLabel(
				LanguageUtil.get(
					objectRequestHelper.getRequest(), "add-object-validation"));
			dropdownItem.setTarget("event");
		};
	}

	private List<Map<String, Object>> _createObjectValidationRuleElements(
		String engine) {

		boolean includeDDMExpressionBuilderElements = false;

		if (engine.equals(ObjectValidationRuleConstants.ENGINE_TYPE_DDM)) {
			includeDDMExpressionBuilderElements = true;
		}

		return ObjectCodeEditorUtil.getCodeEditorElements(
			includeDDMExpressionBuilderElements, true,
			objectRequestHelper.getLocale(), getObjectDefinitionId(),
			objectField -> !objectField.compareBusinessType(
				ObjectFieldConstants.BUSINESS_TYPE_AGGREGATION));
	}

	private final ObjectValidationRuleEngineRegistry
		_objectValidationRuleEngineRegistry;

}