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

package com.liferay.dynamic.data.mapping.web.internal.change.tracking.spi.display;

import com.liferay.change.tracking.spi.display.BaseCTDisplayRenderer;
import com.liferay.change.tracking.spi.display.CTDisplayRenderer;
import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Truong
 */
@Component(service = CTDisplayRenderer.class)
public class DDMStructureCTDisplayRenderer
	extends BaseCTDisplayRenderer<DDMStructure> {

	@Override
	public String[] getAvailableLanguageIds(DDMStructure ddmStructure) {
		return ddmStructure.getAvailableLanguageIds();
	}

	@Override
	public String getDefaultLanguageId(DDMStructure ddmStructure) {
		return ddmStructure.getDefaultLanguageId();
	}

	@Override
	public String getEditURL(
			HttpServletRequest httpServletRequest, DDMStructure ddmStructure)
		throws PortalException {

		Group group = _groupLocalService.getGroup(ddmStructure.getGroupId());

		if (group.isCompany()) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			group = themeDisplay.getScopeGroup();
		}

		return PortletURLBuilder.create(
			_portal.getControlPanelPortletURL(
				httpServletRequest, group, DDMPortletKeys.DYNAMIC_DATA_MAPPING,
				0, 0, PortletRequest.RENDER_PHASE)
		).setMVCPath(
			"/admin/edit_ddm_structure.jsp"
		).setRedirect(
			_portal.getCurrentURL(httpServletRequest)
		).setParameter(
			"ddmStructureId", ddmStructure.getStructureId()
		).setParameter(
			"groupId", ddmStructure.getGroupId()
		).buildString();
	}

	@Override
	public Class<DDMStructure> getModelClass() {
		return DDMStructure.class;
	}

	@Override
	public String getTitle(Locale locale, DDMStructure ddmStructure) {
		return ddmStructure.getName(locale);
	}

	@Override
	protected void buildDisplay(DisplayBuilder<DDMStructure> displayBuilder) {
		DDMStructure ddmStructure = displayBuilder.getModel();

		Locale locale = displayBuilder.getLocale();

		displayBuilder.display(
			"name", ddmStructure.getName(locale)
		).display(
			"created-by", ddmStructure.getUserName()
		).display(
			"create-date", ddmStructure.getCreateDate()
		).display(
			"last-modified", ddmStructure.getModifiedDate()
		).display(
			"version", ddmStructure.getVersion()
		).display(
			"description", ddmStructure.getDescription(locale)
		).display(
			"definition", ddmStructure.getDefinition()
		).display(
			"storage-type", ddmStructure.getStorageType()
		).display(
			"type", ddmStructure.getType()
		);
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

}