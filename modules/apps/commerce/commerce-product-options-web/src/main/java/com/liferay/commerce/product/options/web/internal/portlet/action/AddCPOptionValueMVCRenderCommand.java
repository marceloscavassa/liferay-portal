/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.options.web.internal.display.context.CPOptionDisplayContext;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesRegistry;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_OPTIONS,
		"mvc.command.name=/cp_options/add_cp_option_value"
	},
	service = MVCRenderCommand.class
)
public class AddCPOptionValueMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			CPOptionDisplayContext cpOptionDisplayContext =
				new CPOptionDisplayContext(
					_configurationProvider, null,
					_ddmFormFieldTypeServicesRegistry,
					_portletResourcePermission,
					_portal.getHttpServletRequest(renderRequest));

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT, cpOptionDisplayContext);
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}

		return "/option/add_cp_option_value.jsp";
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private DDMFormFieldTypeServicesRegistry _ddmFormFieldTypeServicesRegistry;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(resource.name=" + CPConstants.RESOURCE_NAME_PRODUCT + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}