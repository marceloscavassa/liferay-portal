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

package com.liferay.translation.web.internal.portlet.action;

import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.translation.constants.TranslationPortletKeys;
import com.liferay.translation.exporter.TranslationInfoItemFieldValuesExporterTracker;
import com.liferay.translation.web.internal.configuration.FFLayoutExperienceSelectorConfiguration;
import com.liferay.translation.web.internal.display.context.ExportTranslationDisplayContext;
import com.liferay.translation.web.internal.helper.TranslationRequestHelper;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	configurationPid = "com.liferay.translation.web.internal.configuration.FFLayoutExperienceSelectorConfiguration",
	property = {
		"javax.portlet.name=" + TranslationPortletKeys.TRANSLATION,
		"mvc.command.name=/translation/export_translation"
	},
	service = MVCRenderCommand.class
)
public class ExportTranslationMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long classNameId = ParamUtil.getLong(renderRequest, "classNameId");

			String className = _portal.getClassName(classNameId);

			long groupId = ParamUtil.getLong(renderRequest, "groupId");

			TranslationRequestHelper translationRequestHelper =
				new TranslationRequestHelper(
					_infoItemServiceTracker, renderRequest);

			long classPK = translationRequestHelper.getModelClassPK();

			Object model = _getModel(className, classPK);

			renderRequest.setAttribute(
				ExportTranslationDisplayContext.class.getName(),
				new ExportTranslationDisplayContext(
					classNameId, classPK,
					_ffLayoutExperienceSelectorConfiguration, groupId,
					_portal.getHttpServletRequest(renderRequest),
					_infoItemServiceTracker,
					_portal.getLiferayPortletRequest(renderRequest),
					_portal.getLiferayPortletResponse(renderResponse), model,
					_getTitle(className, model, themeDisplay.getLocale()),
					_translationInfoItemFieldValuesExporterTracker));

			return "/export_translation.jsp";
		}
		catch (PortalException portalException) {
			throw new PortletException(portalException);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_ffLayoutExperienceSelectorConfiguration =
			ConfigurableUtil.createConfigurable(
				FFLayoutExperienceSelectorConfiguration.class, properties);
	}

	private Object _getModel(String className, long classPK)
		throws PortalException {

		InfoItemObjectProvider<Object> infoItemObjectProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemObjectProvider.class, className);

		return infoItemObjectProvider.getInfoItem(classPK);
	}

	private String _getTitle(String className, Object model, Locale locale) {
		InfoItemFieldValuesProvider<Object> infoItemFieldValuesProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemFieldValuesProvider.class, className);

		InfoFieldValue<Object> infoFieldValue =
			infoItemFieldValuesProvider.getInfoFieldValue(model, "title");

		if (infoFieldValue == null) {
			return _language.get(locale, "export-translation");
		}

		return (String)infoFieldValue.getValue(locale);
	}

	private volatile FFLayoutExperienceSelectorConfiguration
		_ffLayoutExperienceSelectorConfiguration;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private TranslationInfoItemFieldValuesExporterTracker
		_translationInfoItemFieldValuesExporterTracker;

}