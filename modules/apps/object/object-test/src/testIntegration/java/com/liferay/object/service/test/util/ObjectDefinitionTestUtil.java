/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.service.test.util;

import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Guilherme Camacho
 */
public class ObjectDefinitionTestUtil {

	public static ObjectDefinition addModifiableSystemObjectDefinition(
			long userId, String dbTableName, Map<Locale, String> labelMap,
			String name, String pkObjectFieldDBColumnName,
			String pkObjectFieldName, Map<Locale, String> pluralLabelMap,
			String scope, String titleObjectFieldName, int version,
			ObjectDefinitionLocalService objectDefinitionLocalService,
			List<ObjectField> objectFields)
		throws Exception {

		return objectDefinitionLocalService.addSystemObjectDefinition(
			null, userId, null, dbTableName, false, labelMap, true, name, null,
			null, pkObjectFieldDBColumnName, pkObjectFieldName, pluralLabelMap,
			scope, titleObjectFieldName, version,
			WorkflowConstants.STATUS_DRAFT, objectFields);
	}

	public static ObjectDefinition addObjectDefinition(
			boolean enableLocalization,
			ObjectDefinitionLocalService objectDefinitionLocalService,
			List<ObjectField> objectFields)
		throws Exception {

		return objectDefinitionLocalService.addCustomObjectDefinition(
			TestPropsValues.getUserId(), false, enableLocalization,
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString()),
			"A" + RandomTestUtil.randomString(), null, null,
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString()),
			true, ObjectDefinitionConstants.SCOPE_COMPANY,
			ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT, objectFields);
	}

	public static ObjectDefinition addObjectDefinition(
			ObjectDefinitionLocalService objectDefinitionLocalService)
		throws Exception {

		return addObjectDefinition(false, objectDefinitionLocalService, null);
	}

	public static ObjectDefinition addUnmodifiableSystemObjectDefinition(
			String externalReferenceCode, long userId, String className,
			String dbTableName, Map<Locale, String> labelMap, String name,
			String pkObjectFieldDBColumnName, String pkObjectFieldName,
			Map<Locale, String> pluralLabelMap, String scope,
			String titleObjectFieldName, int version,
			ObjectDefinitionLocalService objectDefinitionLocalService,
			List<ObjectField> objectFields)
		throws Exception {

		return objectDefinitionLocalService.addSystemObjectDefinition(
			externalReferenceCode, userId, className, dbTableName, false,
			labelMap, false, name, null, null, pkObjectFieldDBColumnName,
			pkObjectFieldName, pluralLabelMap, scope, titleObjectFieldName,
			version, WorkflowConstants.STATUS_APPROVED, objectFields);
	}

}