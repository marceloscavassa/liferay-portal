/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.user.facet.portlet;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetDisplayContext;
import com.liferay.portal.search.web.internal.facet.display.context.builder.UserSearchFacetDisplayContextBuilder;
import com.liferay.portal.search.web.internal.user.facet.constants.UserFacetPortletKeys;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchRequest;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchResponse;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lino Alves
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-user-facet",
		"com.liferay.portlet.display-category=category.search",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.icon=/icons/search.png",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=User Facet",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/user/facet/view.jsp",
		"javax.portlet.name=" + UserFacetPortletKeys.USER_FACET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class UserFacetPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletSharedSearchResponse portletSharedSearchResponse =
			portletSharedSearchRequest.search(renderRequest);

		UserSearchFacetDisplayContext userSearchFacetDisplayContext =
			_buildDisplayContext(portletSharedSearchResponse, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, userSearchFacetDisplayContext);

		if (userSearchFacetDisplayContext.isRenderNothing()) {
			renderRequest.setAttribute(
				WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		}

		super.render(renderRequest, renderResponse);
	}

	@Reference
	protected Portal portal;

	@Reference
	protected PortletSharedSearchRequest portletSharedSearchRequest;

	private UserSearchFacetDisplayContext _buildDisplayContext(
		PortletSharedSearchResponse portletSharedSearchResponse,
		RenderRequest renderRequest) {

		UserSearchFacetDisplayContextBuilder
			userSearchFacetDisplayContextBuilder =
				_createUserSearchFacetDisplayContextBuilder(renderRequest);

		userSearchFacetDisplayContextBuilder.setFacet(
			portletSharedSearchResponse.getFacet(
				_getAggregationName(renderRequest)));

		UserFacetPortletPreferences userFacetPortletPreferences =
			new UserFacetPortletPreferencesImpl(
				portletSharedSearchResponse.getPortletPreferences(
					renderRequest));

		userSearchFacetDisplayContextBuilder.setFrequenciesVisible(
			userFacetPortletPreferences.isFrequenciesVisible());
		userSearchFacetDisplayContextBuilder.setFrequencyThreshold(
			userFacetPortletPreferences.getFrequencyThreshold());
		userSearchFacetDisplayContextBuilder.setMaxTerms(
			userFacetPortletPreferences.getMaxTerms());
		userSearchFacetDisplayContextBuilder.setOrder(
			userFacetPortletPreferences.getOrder());

		userSearchFacetDisplayContextBuilder.setPaginationStartParameterName(
			_getPaginationStartParameterName(portletSharedSearchResponse));

		String parameterName = userFacetPortletPreferences.getParameterName();

		userSearchFacetDisplayContextBuilder.setParamName(parameterName);
		userSearchFacetDisplayContextBuilder.setParamValues(
			portletSharedSearchResponse.getParameterValues(
				parameterName, renderRequest));

		return userSearchFacetDisplayContextBuilder.build();
	}

	private UserSearchFacetDisplayContextBuilder
		_createUserSearchFacetDisplayContextBuilder(
			RenderRequest renderRequest) {

		try {
			return new UserSearchFacetDisplayContextBuilder(renderRequest);
		}
		catch (ConfigurationException configurationException) {
			throw new RuntimeException(configurationException);
		}
	}

	private String _getAggregationName(RenderRequest renderRequest) {
		return portal.getPortletId(renderRequest);
	}

	private String _getPaginationStartParameterName(
		PortletSharedSearchResponse portletSharedSearchResponse) {

		SearchResponse searchResponse =
			portletSharedSearchResponse.getSearchResponse();

		SearchRequest searchRequest = searchResponse.getRequest();

		return searchRequest.getPaginationStartParameterName();
	}

}