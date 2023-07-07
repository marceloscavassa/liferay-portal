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

package com.liferay.fragment.internal.exportimport.content.processor;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.fragment.entry.processor.constants.FragmentEntryProcessorConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.util.configuration.FragmentConfigurationField;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ricardo Couso
 */
@Component(
	property = "content.processor.type=FragmentEntryLinkEditableValues",
	service = ExportImportContentProcessor.class
)
public class EditableValuesURLLayoutMappingExportImportContentProcessor
	implements ExportImportContentProcessor<JSONObject> {

	@Override
	public JSONObject replaceExportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			JSONObject editableValuesJSONObject,
			boolean exportReferencedContent, boolean escapeContent)
		throws Exception {

		List<FragmentConfigurationField> fragmentConfigurationFields =
			_getFragmentConfigurationFields((FragmentEntryLink)stagedModel);

		if (ListUtil.isEmpty(fragmentConfigurationFields)) {
			return editableValuesJSONObject;
		}

		JSONObject editableProcessorJSONObject =
			editableValuesJSONObject.getJSONObject(
				FragmentEntryProcessorConstants.
					KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

		if (editableProcessorJSONObject == null) {
			return editableValuesJSONObject;
		}

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			JSONObject configJSONObject =
				editableProcessorJSONObject.getJSONObject(
					fragmentConfigurationField.getName());

			if ((configJSONObject != null) && configJSONObject.has("layout")) {
				_exportLayoutReferences(
					portletDataContext, stagedModel,
					configJSONObject.getJSONObject("layout"),
					exportReferencedContent);
			}
		}

		return editableValuesJSONObject;
	}

	@Override
	public JSONObject replaceImportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			JSONObject editableValuesJSONObject)
		throws Exception {

		List<FragmentConfigurationField> fragmentConfigurationFields =
			_getFragmentConfigurationFields((FragmentEntryLink)stagedModel);

		if (ListUtil.isEmpty(fragmentConfigurationFields)) {
			return editableValuesJSONObject;
		}

		JSONObject editableProcessorJSONObject =
			editableValuesJSONObject.getJSONObject(
				FragmentEntryProcessorConstants.
					KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

		if (editableProcessorJSONObject == null) {
			return editableValuesJSONObject;
		}

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			JSONObject configJSONObject =
				editableProcessorJSONObject.getJSONObject(
					fragmentConfigurationField.getName());

			if ((configJSONObject != null) && configJSONObject.has("layout")) {
				_replaceImportLayoutReferences(
					configJSONObject.getJSONObject("layout"),
					portletDataContext);
			}
		}

		return editableValuesJSONObject;
	}

	@Override
	public void validateContentReferences(long groupId, JSONObject jsonObject) {
	}

	private void _exportLayoutReferences(
			PortletDataContext portletDataContext,
			StagedModel referrerStagedModel, JSONObject layoutJSONObject,
			boolean exportReferencedContent)
		throws Exception {

		if (layoutJSONObject.length() == 0) {
			return;
		}

		Layout layout = _layoutLocalService.fetchLayout(
			layoutJSONObject.getLong("groupId"),
			layoutJSONObject.getBoolean("privateLayout"),
			layoutJSONObject.getLong("layoutId"));

		if (layout == null) {
			return;
		}

		layoutJSONObject.put("plid", layout.getPlid());

		if (exportReferencedContent) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, referrerStagedModel, layout,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
		}
		else {
			Element entityElement = portletDataContext.getExportDataElement(
				referrerStagedModel);

			portletDataContext.addReferenceElement(
				referrerStagedModel, entityElement, layout,
				PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
		}
	}

	private List<FragmentConfigurationField> _getFragmentConfigurationFields(
		FragmentEntryLink fragmentEntryLink) {

		return ListUtil.filter(
			_fragmentEntryConfigurationParser.getFragmentConfigurationFields(
				fragmentEntryLink.getConfiguration()),
			fragmentConfigurationField -> Objects.equals(
				fragmentConfigurationField.getType(), "url"));
	}

	private void _replaceImportLayoutReferences(
		JSONObject layoutJSONObject, PortletDataContext portletDataContext) {

		if (layoutJSONObject.length() == 0) {
			return;
		}

		long plid = GetterUtil.getLong(layoutJSONObject.remove("plid"));

		Map<Long, Long> layoutNewPrimaryKeys =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class.getName());

		Layout layout = _layoutLocalService.fetchLayout(
			layoutNewPrimaryKeys.getOrDefault(plid, 0L));

		if (layout == null) {
			return;
		}

		layoutJSONObject.put(
			"groupId", layout.getGroupId()
		).put(
			"layoutId", layout.getLayoutId()
		).put(
			"layoutUuid", layout.getUuid()
		).put(
			"privateLayout", layout.isPrivateLayout()
		);
	}

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private LayoutLocalService _layoutLocalService;

}