/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.portlet;

import com.liferay.asset.display.page.configuration.AssetDisplayPageConfiguration;
import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.asset.util.LinkedAssetEntryIdsUtil;
import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.info.search.InfoSearchClassMapperRegistry;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProvider;
import com.liferay.layout.display.page.LayoutDisplayPageProviderRegistry;
import com.liferay.layout.display.page.constants.LayoutDisplayPageWebKeys;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.seo.template.LayoutSEOTemplateProcessor;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURLComposite;
import com.liferay.portal.kernel.model.LayoutQueryStringComposite;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.FriendlyURLResolver;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseAssetDisplayPageFriendlyURLResolver
	implements FriendlyURLResolver {

	@Override
	public String getActualURL(
			long companyId, long groupId, boolean privateLayout,
			String mainPath, String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		LayoutQueryStringComposite layoutQueryStringComposite =
			portal.getPortletFriendlyURLMapperLayoutQueryStringComposite(
				friendlyURL, params, requestContext);

		friendlyURL = layoutQueryStringComposite.getFriendlyURL();

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)requestContext.get("request");

		LayoutDisplayPageProvider<?> layoutDisplayPageProvider =
			getLayoutDisplayPageProvider(friendlyURL, params);

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			getLayoutDisplayPageObjectProvider(
				layoutDisplayPageProvider, groupId, friendlyURL, params);

		Object infoItem = _getInfoItem(layoutDisplayPageObjectProvider, params);

		httpServletRequest.setAttribute(InfoDisplayWebKeys.INFO_ITEM, infoItem);

		InfoItemDetailsProvider infoItemDetailsProvider =
			infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemDetailsProvider.class,
				layoutDisplayPageObjectProvider.getClassName());

		httpServletRequest.setAttribute(
			InfoDisplayWebKeys.INFO_ITEM_DETAILS,
			infoItemDetailsProvider.getInfoItemDetails(infoItem));

		httpServletRequest.setAttribute(
			LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_OBJECT_PROVIDER,
			layoutDisplayPageObjectProvider);
		httpServletRequest.setAttribute(
			LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_PROVIDER,
			layoutDisplayPageProvider);

		AssetEntry assetEntry = _getAssetEntry(layoutDisplayPageObjectProvider);

		httpServletRequest.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);

		if (assetEntry != null) {
			LinkedAssetEntryIdsUtil.addLinkedAssetEntryId(
				httpServletRequest, assetEntry.getEntryId());
		}

		Locale locale = portal.getLocale(httpServletRequest);
		Layout layout = getLayoutDisplayPageObjectProviderLayout(
			groupId, layoutDisplayPageObjectProvider, layoutDisplayPageProvider,
			params);

		InfoItemFieldValuesProvider<Object> infoItemFieldValuesProvider =
			infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemFieldValuesProvider.class,
				layoutDisplayPageObjectProvider.getClassName());

		InfoItemFieldValues infoItemFieldValues =
			infoItemFieldValuesProvider.getInfoItemFieldValues(
				layoutDisplayPageObjectProvider.getDisplayObject());

		String description = _getMappedValue(
			layout.getTypeSettingsProperty("mapped-description"),
			infoItemFieldValues, locale);

		if (description == null) {
			description = layoutDisplayPageObjectProvider.getDescription(
				locale);
		}

		portal.setPageDescription(
			HtmlUtil.unescape(HtmlUtil.stripHtml(description)),
			httpServletRequest);

		portal.setPageKeywords(
			layoutDisplayPageObjectProvider.getKeywords(locale),
			httpServletRequest);

		String title = _getMappedValue(
			layout.getTypeSettingsProperty("mapped-title"), infoItemFieldValues,
			locale);

		if (title == null) {
			title = layoutDisplayPageObjectProvider.getTitle(locale);
		}

		portal.setPageTitle(title, httpServletRequest);

		return portal.getLayoutActualURL(layout, mainPath) +
			layoutQueryStringComposite.getQueryString();
	}

	@Override
	public LayoutFriendlyURLComposite getLayoutFriendlyURLComposite(
			long companyId, long groupId, boolean privateLayout,
			String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		LayoutDisplayPageProvider<?> layoutDisplayPageProvider =
			getLayoutDisplayPageProvider(friendlyURL, params);

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			getLayoutDisplayPageObjectProvider(
				layoutDisplayPageProvider, groupId, friendlyURL, params);

		if (layoutDisplayPageObjectProvider == null) {
			throw new PortalException();
		}

		Layout layout = getLayoutDisplayPageObjectProviderLayout(
			groupId, layoutDisplayPageObjectProvider, layoutDisplayPageProvider,
			params);

		String originalFriendlyURL = _getOriginalFriendlyURL(friendlyURL);

		String localizedFriendlyURL = originalFriendlyURL;

		String urlTitle = layoutDisplayPageObjectProvider.getURLTitle(
			getLocale(requestContext));

		if (Validator.isNotNull(urlTitle)) {
			localizedFriendlyURL = getURLSeparator() + urlTitle;
		}

		if (!Objects.equals(originalFriendlyURL, localizedFriendlyURL)) {
			return new LayoutFriendlyURLComposite(
				layout, localizedFriendlyURL, true);
		}

		return new LayoutFriendlyURLComposite(layout, friendlyURL, false);
	}

	protected AssetDisplayPageEntry getAssetDisplayPageEntry(
		long groupId,
		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider) {

		return assetDisplayPageEntryLocalService.fetchAssetDisplayPageEntry(
			groupId, layoutDisplayPageObjectProvider.getClassNameId(),
			layoutDisplayPageObjectProvider.getClassPK());
	}

	protected LayoutDisplayPageObjectProvider<?>
		getLayoutDisplayPageObjectProvider(
			LayoutDisplayPageProvider<?> layoutDisplayPageProvider,
			long groupId, String friendlyURL, Map<String, String[]> params) {

		return _getLayoutDisplayPageObjectProvider(
			layoutDisplayPageProvider, groupId, friendlyURL,
			_getVersion(params));
	}

	protected Layout getLayoutDisplayPageObjectProviderLayout(
		long groupId,
		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider,
		LayoutDisplayPageProvider<?> layoutDisplayPageProvider,
		Map<String, String[]> params) {

		return _getLayoutDisplayPageObjectProviderLayout(
			groupId, layoutDisplayPageObjectProvider,
			layoutDisplayPageProvider);
	}

	protected LayoutDisplayPageProvider<?> getLayoutDisplayPageProvider(
			String friendlyURL, Map<String, String[]> params)
		throws PortalException {

		return _getLayoutDisplayPageProvider(friendlyURL);
	}

	protected Locale getLocale(Map<String, Object> requestContext) {
		Locale locale = (Locale)requestContext.get(WebKeys.LOCALE);

		if (locale == null) {
			HttpServletRequest httpServletRequest =
				(HttpServletRequest)requestContext.get("request");

			HttpSession httpSession = httpServletRequest.getSession();

			locale = (Locale)httpSession.getAttribute(WebKeys.LOCALE);

			if (locale == null) {
				locale = portal.getLocale(httpServletRequest);
			}
		}

		return locale;
	}

	@Reference
	protected AssetDisplayPageEntryLocalService
		assetDisplayPageEntryLocalService;

	@Reference
	protected AssetEntryService assetEntryLocalService;

	@Reference
	protected InfoItemServiceRegistry infoItemServiceRegistry;

	@Reference
	protected InfoSearchClassMapperRegistry infoSearchClassMapperRegistry;

	@Reference
	protected LayoutDisplayPageProviderRegistry
		layoutDisplayPageProviderRegistry;

	@Reference
	protected LayoutLocalService layoutLocalService;

	@Reference
	protected LayoutPageTemplateEntryService layoutPageTemplateEntryService;

	@Reference
	protected LayoutSEOTemplateProcessor layoutSEOTemplateProcessor;

	@Reference
	protected Portal portal;

	private AssetEntry _getAssetEntry(
		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider) {

		String className = infoSearchClassMapperRegistry.getSearchClassName(
			layoutDisplayPageObjectProvider.getClassName());

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory == null) {
			return null;
		}

		long classPK = layoutDisplayPageObjectProvider.getClassPK();

		try {
			AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
				className, classPK);

			AssetDisplayPageConfiguration assetDisplayPageConfiguration =
				ConfigurationProviderUtil.getSystemConfiguration(
					AssetDisplayPageConfiguration.class);

			if ((assetEntry != null) &&
				assetDisplayPageConfiguration.enableViewCountIncrement()) {

				assetEntryLocalService.incrementViewCounter(assetEntry);
			}

			return assetEntry;
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		return null;
	}

	private Object _getInfoItem(
			LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider,
			Map<String, String[]> params)
		throws NoSuchInfoItemException {

		String version = _getVersion(params);

		if (Validator.isNull(version)) {
			return layoutDisplayPageObjectProvider.getDisplayObject();
		}

		InfoItemIdentifier infoItemIdentifier = new ClassPKInfoItemIdentifier(
			layoutDisplayPageObjectProvider.getClassPK());

		InfoItemObjectProvider<Object> infoItemObjectProvider =
			(InfoItemObjectProvider<Object>)
				infoItemServiceRegistry.getFirstInfoItemService(
					InfoItemObjectProvider.class,
					layoutDisplayPageObjectProvider.getClassName(),
					infoItemIdentifier.getInfoItemServiceFilter());

		infoItemIdentifier.setVersion(version);

		return infoItemObjectProvider.getInfoItem(infoItemIdentifier);
	}

	private LayoutDisplayPageObjectProvider<?>
		_getLayoutDisplayPageObjectProvider(
			LayoutDisplayPageProvider<?> layoutDisplayPageProvider,
			long groupId, String friendlyURL, String version) {

		return layoutDisplayPageProvider.getLayoutDisplayPageObjectProvider(
			groupId, _getUrlTitle(friendlyURL), version);
	}

	private Layout _getLayoutDisplayPageObjectProviderLayout(
		long groupId,
		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider,
		LayoutDisplayPageProvider<?> layoutDisplayPageProvider) {

		AssetDisplayPageEntry assetDisplayPageEntry = getAssetDisplayPageEntry(
			groupId, layoutDisplayPageObjectProvider);

		if (assetDisplayPageEntry != null) {
			if (assetDisplayPageEntry.getType() ==
					AssetDisplayPageConstants.TYPE_NONE) {

				return null;
			}

			if (assetDisplayPageEntry.getType() ==
					AssetDisplayPageConstants.TYPE_SPECIFIC) {

				return layoutLocalService.fetchLayout(
					assetDisplayPageEntry.getPlid());
			}

			if (layoutDisplayPageProvider.inheritable() &&
				(assetDisplayPageEntry.getType() ==
					AssetDisplayPageConstants.TYPE_INHERITED)) {

				InfoItemReference infoItemReference = new InfoItemReference(
					layoutDisplayPageObjectProvider.getClassName(),
					layoutDisplayPageObjectProvider.getClassPK());

				LayoutDisplayPageObjectProvider<?>
					parentLayoutDisplayPageObjectProvider =
						layoutDisplayPageProvider.
							getParentLayoutDisplayPageObjectProvider(
								infoItemReference);

				if (parentLayoutDisplayPageObjectProvider != null) {
					return _getLayoutDisplayPageObjectProviderLayout(
						groupId, parentLayoutDisplayPageObjectProvider,
						layoutDisplayPageProvider);
				}
			}
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			layoutPageTemplateEntryService.fetchDefaultLayoutPageTemplateEntry(
				groupId, layoutDisplayPageObjectProvider.getClassNameId(),
				layoutDisplayPageObjectProvider.getClassTypeId());

		if (layoutPageTemplateEntry != null) {
			return layoutLocalService.fetchLayout(
				layoutPageTemplateEntry.getPlid());
		}

		return null;
	}

	private LayoutDisplayPageProvider<?> _getLayoutDisplayPageProvider(
			String friendlyURL)
		throws PortalException {

		String urlSeparator = _getURLSeparator(friendlyURL);

		LayoutDisplayPageProvider<?> layoutDisplayPageProvider =
			layoutDisplayPageProviderRegistry.
				getLayoutDisplayPageProviderByURLSeparator(urlSeparator);

		if (layoutDisplayPageProvider == null) {
			throw new PortalException(
				"Info display contributor is not available for " +
					urlSeparator);
		}

		return layoutDisplayPageProvider;
	}

	private String _getMappedValue(
		String template, InfoItemFieldValues infoItemFieldValues,
		Locale locale) {

		if ((infoItemFieldValues == null) || Validator.isNull(template)) {
			return null;
		}

		return layoutSEOTemplateProcessor.processTemplate(
			template, infoItemFieldValues, locale);
	}

	private String _getOriginalFriendlyURL(String friendlyURL) {
		int pos = friendlyURL.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if ((pos == -1) || (pos == 0)) {
			return friendlyURL;
		}

		return friendlyURL.substring(0, pos);
	}

	private String _getURLSeparator(String friendlyURL) {
		List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

		return CharPool.SLASH + paths.get(0) + CharPool.SLASH;
	}

	private String _getUrlTitle(String friendlyURL) {
		String urlSeparator = _getURLSeparator(friendlyURL);

		LayoutQueryStringComposite layoutQueryStringComposite =
			portal.getPortletFriendlyURLMapperLayoutQueryStringComposite(
				friendlyURL, new HashMap<>(), new HashMap<>());

		String newFriendlyURL = layoutQueryStringComposite.getFriendlyURL();

		if (newFriendlyURL.startsWith(urlSeparator)) {
			return newFriendlyURL.substring(urlSeparator.length());
		}

		if (friendlyURL.startsWith(urlSeparator)) {
			return friendlyURL.substring(urlSeparator.length());
		}

		return StringPool.BLANK;
	}

	private String _getVersion(Map<String, String[]> params) {
		String[] versions = params.get("version");

		if (ArrayUtil.isEmpty(versions)) {
			return StringPool.BLANK;
		}

		return versions[0];
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseAssetDisplayPageFriendlyURLResolver.class);

}