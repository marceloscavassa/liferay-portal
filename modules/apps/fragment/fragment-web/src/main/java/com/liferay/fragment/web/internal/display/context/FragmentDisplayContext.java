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

package com.liferay.fragment.web.internal.display.context;

import com.liferay.fragment.configuration.FragmentServiceConfiguration;
import com.liferay.fragment.constants.FragmentActionKeys;
import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.processor.FragmentEntryProcessorRegistry;
import com.liferay.fragment.service.FragmentCollectionLocalServiceUtil;
import com.liferay.fragment.service.FragmentEntryLocalServiceUtil;
import com.liferay.fragment.service.FragmentEntryServiceUtil;
import com.liferay.fragment.web.internal.constants.FragmentWebKeys;
import com.liferay.fragment.web.internal.security.permission.resource.FragmentPermission;
import com.liferay.fragment.web.internal.util.FragmentPortletUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.template.soy.util.SoyContext;
import com.liferay.portal.template.soy.util.SoyContextFactoryUtil;

import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class FragmentDisplayContext {

	public FragmentDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;

		_fragmentEntryProcessorRegistry =
			(FragmentEntryProcessorRegistry)_httpServletRequest.getAttribute(
				FragmentWebKeys.FRAGMENT_ENTRY_PROCESSOR_REGISTRY);
		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							_renderResponse.createRenderURL(),
							"mvcRenderCommandName",
							"/fragment/edit_fragment_collection", "redirect",
							_themeDisplay.getURLCurrent());
						dropdownItem.setLabel(
							LanguageUtil.get(
								_httpServletRequest, "collection"));
					});

				add(
					dropdownItem -> {
						dropdownItem.putData("action", "openImportView");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "import"));
					});
			}
		};
	}

	public List<DropdownItem> getCollectionsDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData("action", "exportCollections");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "export"));
					});

				if (FragmentPermission.contains(
						_themeDisplay.getPermissionChecker(),
						_themeDisplay.getScopeGroupId(),
						FragmentActionKeys.MANAGE_FRAGMENT_ENTRIES)) {

					add(
						dropdownItem -> {
							dropdownItem.putData("action", "openImportView");
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "import"));
						});

					add(
						dropdownItem -> {
							dropdownItem.putData("action", "deleteCollections");
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "delete"));
						});
				}
			}
		};
	}

	public String getConfigurationContent() {
		if (Validator.isNotNull(_configurationContent)) {
			return _configurationContent;
		}

		_configurationContent = ParamUtil.getString(
			_httpServletRequest, "configurationContent");

		FragmentEntry fragmentEntry = getFragmentEntry();

		if ((fragmentEntry != null) &&
			Validator.isNull(_configurationContent)) {

			_configurationContent = fragmentEntry.getConfiguration();

			if (Validator.isNull(_configurationContent)) {
				_configurationContent = "{\n\t\"fieldSets\": [\n\t]\n}";
			}
		}

		return _configurationContent;
	}

	public String getCssContent() {
		if (Validator.isNotNull(_cssContent)) {
			return _cssContent;
		}

		_cssContent = ParamUtil.getString(_httpServletRequest, "cssContent");

		FragmentEntry fragmentEntry = getFragmentEntry();

		if ((fragmentEntry != null) && Validator.isNull(_cssContent)) {
			_cssContent = fragmentEntry.getCss();

			if (Validator.isNull(_cssContent)) {
				StringBundler sb = new StringBundler(3);

				sb.append(".fragment_");
				sb.append(fragmentEntry.getFragmentEntryId());
				sb.append(" {\n}");

				_cssContent = sb.toString();
			}
		}

		return _cssContent;
	}

	public FragmentCollection getFragmentCollection() {
		if (_fragmentCollection != null) {
			return _fragmentCollection;
		}

		_fragmentCollection =
			FragmentCollectionLocalServiceUtil.fetchFragmentCollection(
				getFragmentCollectionId());

		return _fragmentCollection;
	}

	public long getFragmentCollectionId() {
		if (Validator.isNotNull(_fragmentCollectionId)) {
			return _fragmentCollectionId;
		}

		long defaultFragmentCollectionId = 0;

		List<FragmentCollection> fragmentCollections =
			FragmentCollectionLocalServiceUtil.getFragmentCollections(
				_themeDisplay.getScopeGroupId(), 0, 1);

		if (ListUtil.isNotEmpty(fragmentCollections)) {
			FragmentCollection fragmentCollection = fragmentCollections.get(0);

			defaultFragmentCollectionId =
				fragmentCollection.getFragmentCollectionId();
		}

		_fragmentCollectionId = ParamUtil.getLong(
			_httpServletRequest, "fragmentCollectionId",
			defaultFragmentCollectionId);

		return _fragmentCollectionId;
	}

	public SoyContext getFragmentEditorDisplayContext() throws Exception {
		SoyContext soyContext = SoyContextFactoryUtil.createSoyContext();

		SoyContext allowedStatusSoyContext =
			SoyContextFactoryUtil.createSoyContext();

		allowedStatusSoyContext.put(
			"approved", String.valueOf(WorkflowConstants.STATUS_APPROVED)
		).put(
			"draft", String.valueOf(WorkflowConstants.STATUS_DRAFT)
		);

		FragmentServiceConfiguration fragmentServiceConfiguration =
			ConfigurationProviderUtil.getCompanyConfiguration(
				FragmentServiceConfiguration.class,
				_themeDisplay.getCompanyId());

		soyContext.put(
			"allowedStatus", allowedStatusSoyContext
		).put(
			"autocompleteTags",
			_fragmentEntryProcessorRegistry.getAvailableTagsJSONArray()
		).put(
			"fragmentCollectionId", getFragmentCollectionId()
		).put(
			"fragmentEntryId", getFragmentEntryId()
		).put(
			"initialConfiguration", getConfigurationContent()
		).put(
			"initialCSS", getCssContent()
		).put(
			"initialHTML", getHtmlContent()
		).put(
			"initialJS", getJsContent()
		).put(
			"name", getName()
		).put(
			"portletNamespace", _renderResponse.getNamespace()
		).put(
			"propagationEnabled",
			fragmentServiceConfiguration.propagateChanges()
		).put(
			"spritemap",
			_themeDisplay.getPathThemeImages() + "/lexicon/icons.svg"
		);

		FragmentEntry fragmentEntry = getFragmentEntry();

		soyContext.put("status", String.valueOf(fragmentEntry.getStatus()));

		SoyContext urlsSoycontext = SoyContextFactoryUtil.createSoyContext();

		urlsSoycontext.put("current", _themeDisplay.getURLCurrent());

		PortletURL editActionURL = _renderResponse.createActionURL();

		editActionURL.setParameter(
			ActionRequest.ACTION_NAME, "/fragment/edit_fragment_entry");

		urlsSoycontext.put(
			"edit", editActionURL.toString()
		).put(
			"preview",
			_getFragmentEntryRenderURL(
				fragmentEntry, "/fragment/preview_fragment_entry",
				LiferayWindowState.POP_UP)
		).put(
			"redirect", getRedirect()
		).put(
			"render",
			_getFragmentEntryRenderURL(
				fragmentEntry, "/fragment/render_fragment_entry",
				LiferayWindowState.POP_UP)
		);

		soyContext.put("urls", urlsSoycontext);

		return soyContext;
	}

	public SearchContainer getFragmentEntriesSearchContainer() {
		if (_fragmentEntriesSearchContainer != null) {
			return _fragmentEntriesSearchContainer;
		}

		SearchContainer fragmentEntriesSearchContainer = new SearchContainer(
			_renderRequest, _getPortletURL(), null, "there-are-no-fragments");

		fragmentEntriesSearchContainer.setId(
			"fragmentEntries" + getFragmentCollectionId());

		fragmentEntriesSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(_renderResponse));

		OrderByComparator<FragmentEntry> orderByComparator =
			FragmentPortletUtil.getFragmentEntryOrderByComparator(
				_getOrderByCol(), getOrderByType());

		fragmentEntriesSearchContainer.setOrderByCol(_getOrderByCol());
		fragmentEntriesSearchContainer.setOrderByComparator(orderByComparator);
		fragmentEntriesSearchContainer.setOrderByType(getOrderByType());

		List<FragmentEntry> fragmentEntries = null;
		int fragmentEntriesCount = 0;

		if (isNavigationComponents() || isNavigationSections()) {
			int type = FragmentConstants.TYPE_SECTION;

			if (isNavigationComponents()) {
				type = FragmentConstants.TYPE_COMPONENT;
			}

			fragmentEntries = FragmentEntryServiceUtil.getFragmentEntriesByType(
				_themeDisplay.getScopeGroupId(), getFragmentCollectionId(),
				type, fragmentEntriesSearchContainer.getStart(),
				fragmentEntriesSearchContainer.getEnd(), orderByComparator);

			fragmentEntriesCount =
				FragmentEntryServiceUtil.getFragmentEntriesCountByType(
					_themeDisplay.getScopeGroupId(), getFragmentCollectionId(),
					type);
		}
		else if (isSearch()) {
			fragmentEntries = FragmentEntryServiceUtil.getFragmentEntriesByName(
				_themeDisplay.getScopeGroupId(), getFragmentCollectionId(),
				_getKeywords(), fragmentEntriesSearchContainer.getStart(),
				fragmentEntriesSearchContainer.getEnd(), orderByComparator);

			fragmentEntriesCount =
				FragmentEntryServiceUtil.getFragmentEntriesCountByName(
					_themeDisplay.getScopeGroupId(), getFragmentCollectionId(),
					_getKeywords());
		}
		else {
			fragmentEntries = FragmentEntryServiceUtil.getFragmentEntries(
				_themeDisplay.getScopeGroupId(), getFragmentCollectionId(),
				fragmentEntriesSearchContainer.getStart(),
				fragmentEntriesSearchContainer.getEnd(), orderByComparator);

			fragmentEntriesCount =
				FragmentEntryServiceUtil.getFragmentEntriesCount(
					_themeDisplay.getScopeGroupId(), getFragmentCollectionId());
		}

		fragmentEntriesSearchContainer.setResults(fragmentEntries);
		fragmentEntriesSearchContainer.setTotal(fragmentEntriesCount);

		_fragmentEntriesSearchContainer = fragmentEntriesSearchContainer;

		return _fragmentEntriesSearchContainer;
	}

	public FragmentEntry getFragmentEntry() {
		if (_fragmentEntry != null) {
			return _fragmentEntry;
		}

		_fragmentEntry = FragmentEntryLocalServiceUtil.fetchFragmentEntry(
			getFragmentEntryId());

		return _fragmentEntry;
	}

	public long getFragmentEntryId() {
		if (Validator.isNotNull(_fragmentEntryId)) {
			return _fragmentEntryId;
		}

		_fragmentEntryId = ParamUtil.getLong(
			_httpServletRequest, "fragmentEntryId");

		return _fragmentEntryId;
	}

	public String getFragmentEntryTitle() {
		FragmentEntry fragmentEntry = getFragmentEntry();

		if (fragmentEntry == null) {
			return LanguageUtil.get(_httpServletRequest, "add-fragment");
		}

		return fragmentEntry.getName();
	}

	public String getHtmlContent() {
		if (Validator.isNotNull(_htmlContent)) {
			return _htmlContent;
		}

		_htmlContent = ParamUtil.getString(_httpServletRequest, "htmlContent");

		FragmentEntry fragmentEntry = getFragmentEntry();

		if ((fragmentEntry != null) && Validator.isNull(_htmlContent)) {
			_htmlContent = fragmentEntry.getHtml();

			if (Validator.isNull(_htmlContent)) {
				StringBundler sb = new StringBundler(3);

				sb.append("<div class=\"fragment_");
				sb.append(fragmentEntry.getFragmentEntryId());
				sb.append("\">\n</div>");

				_htmlContent = sb.toString();
			}
		}

		return _htmlContent;
	}

	public String getJsContent() {
		if (Validator.isNotNull(_jsContent)) {
			return _jsContent;
		}

		_jsContent = ParamUtil.getString(_httpServletRequest, "jsContent");

		FragmentEntry fragmentEntry = getFragmentEntry();

		if ((fragmentEntry != null) && Validator.isNull(_jsContent)) {
			_jsContent = fragmentEntry.getJs();
		}

		return _jsContent;
	}

	public String getName() {
		if (Validator.isNotNull(_name)) {
			return _name;
		}

		_name = ParamUtil.getString(_httpServletRequest, "name");

		FragmentEntry fragmentEntry = getFragmentEntry();

		if ((fragmentEntry != null) && Validator.isNull(_name)) {
			_name = fragmentEntry.getName();
		}

		return _name;
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		_navigation = ParamUtil.getString(
			_httpServletRequest, "navigation", "all");

		return _navigation;
	}

	public List<NavigationItem> getNavigationItems() {
		return new NavigationItemList() {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(
							Objects.equals(_getTabs1(), "fragments"));
						navigationItem.setHref(
							_getPortletURL(), "tabs1", "fragments");
						navigationItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "fragments"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(
							Objects.equals(_getTabs1(), "resources"));
						navigationItem.setHref(
							_getPortletURL(), "tabs1", "resources");
						navigationItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "resources"));
					});
			}
		};
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_httpServletRequest, "orderByType", "asc");

		return _orderByType;
	}

	public String getRedirect() {
		String redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		if (Validator.isNull(redirect)) {
			PortletURL portletURL = _renderResponse.createRenderURL();

			portletURL.setParameter("mvcRenderCommandName", "/fragment/view");

			if (getFragmentCollectionId() > 0) {
				portletURL.setParameter(
					"fragmentCollectionId",
					String.valueOf(getFragmentCollectionId()));
			}

			redirect = portletURL.toString();
		}

		return redirect;
	}

	public boolean isNavigationComponents() {
		if (Objects.equals(getNavigation(), "components")) {
			return true;
		}

		return false;
	}

	public boolean isNavigationSections() {
		if (Objects.equals(getNavigation(), "sections")) {
			return true;
		}

		return false;
	}

	public boolean isSearch() {
		if (Validator.isNotNull(_getKeywords())) {
			return true;
		}

		return false;
	}

	public boolean isViewResources() {
		if (Objects.equals(_getTabs1(), "resources")) {
			return true;
		}

		return false;
	}

	private String _getFragmentEntryRenderURL(
			FragmentEntry fragmentEntry, String mvcRenderCommandName,
			WindowState windowState)
		throws Exception {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			_httpServletRequest, FragmentPortletKeys.FRAGMENT,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
		portletURL.setParameter(
			"fragmentEntryId",
			String.valueOf(fragmentEntry.getFragmentEntryId()));
		portletURL.setWindowState(windowState);

		return portletURL.toString();
	}

	private String _getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	private String _getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_httpServletRequest, "orderByCol", "create-date");

		return _orderByCol;
	}

	private PortletURL _getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/fragment/view");
		portletURL.setParameter(
			"fragmentCollectionId", String.valueOf(getFragmentCollectionId()));

		String keywords = _getKeywords();

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		String orderByCol = _getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		return portletURL;
	}

	private String _getTabs1() {
		if (_tabs1 != null) {
			return _tabs1;
		}

		_tabs1 = ParamUtil.getString(_httpServletRequest, "tabs1", "fragments");

		return _tabs1;
	}

	private String _configurationContent;
	private String _cssContent;
	private FragmentCollection _fragmentCollection;
	private Long _fragmentCollectionId;
	private SearchContainer _fragmentEntriesSearchContainer;
	private FragmentEntry _fragmentEntry;
	private Long _fragmentEntryId;
	private final FragmentEntryProcessorRegistry
		_fragmentEntryProcessorRegistry;
	private String _htmlContent;
	private final HttpServletRequest _httpServletRequest;
	private String _jsContent;
	private String _keywords;
	private String _name;
	private String _navigation;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private String _tabs1;
	private final ThemeDisplay _themeDisplay;

}