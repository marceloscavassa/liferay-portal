/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.preview.pdf.internal.configuration.admin.display;

import com.liferay.configuration.admin.display.ConfigurationScreen;
import com.liferay.document.library.preview.pdf.internal.configuration.admin.service.PDFPreviewManagedServiceFactory;
import com.liferay.document.library.preview.pdf.internal.portlet.action.PDFPreviewConfigurationDisplayContext;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
public abstract class BasePDFPreviewConfigurationScreen
	implements ConfigurationScreen {

	@Override
	public String getCategoryKey() {
		return "documents-and-media";
	}

	@Override
	public String getKey() {
		return "pdf-preview-configuration-" + getScope();
	}

	@Override
	public String getName(Locale locale) {
		return language.get(locale, "pdf-preview-configuration-name");
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			httpServletRequest.setAttribute(
				PDFPreviewConfigurationDisplayContext.class.getName(),
				new PDFPreviewConfigurationDisplayContext(
					httpServletRequest,
					portal.getLiferayPortletResponse(
						(PortletResponse)httpServletRequest.getAttribute(
							JavaConstants.JAVAX_PORTLET_RESPONSE)),
					pdfPreviewManagedServiceFactory, getScope(),
					_getScopePk(httpServletRequest)));

			RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(
					"/document_library_preview_pdf_settings" +
						"/pdf_preview_limit.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new IOException(
				"Unable to render pdf_preview_limit.jsp", exception);
		}
	}

	@Reference
	protected Language language;

	@Reference
	protected PDFPreviewManagedServiceFactory pdfPreviewManagedServiceFactory;

	@Reference
	protected Portal portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.document.library.preview.pdf)"
	)
	protected ServletContext servletContext;

	private long _getScopePk(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (Objects.equals(
				getScope(),
				ExtendedObjectClassDefinition.Scope.COMPANY.getValue())) {

			return themeDisplay.getCompanyId();
		}
		else if (Objects.equals(
					getScope(),
					ExtendedObjectClassDefinition.Scope.GROUP.getValue())) {

			return themeDisplay.getScopeGroupId();
		}
		else if (Objects.equals(
					getScope(),
					ExtendedObjectClassDefinition.Scope.SYSTEM.getValue())) {

			return 0L;
		}

		throw new IllegalArgumentException("Unsupported scope: " + getScope());
	}

}