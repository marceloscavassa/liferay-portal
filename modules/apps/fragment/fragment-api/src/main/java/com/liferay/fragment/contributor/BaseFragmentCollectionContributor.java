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

package com.liferay.fragment.contributor;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.constants.FragmentExportImportConstants;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseFragmentCollectionContributor
	implements FragmentCollectionContributor {

	@Override
	public List<FragmentEntry> getFragmentEntries(int type) {
		return _fragmentEntries.getOrDefault(type, Collections.emptyList());
	}

	@Override
	public List<FragmentEntry> getFragmentEntries(int type, Locale locale) {
		List<FragmentEntry> fragmentEntries = _fragmentEntries.getOrDefault(
			type, Collections.emptyList());

		Stream<FragmentEntry> stream = fragmentEntries.stream();

		return stream.map(
			fragmentEntry -> {
				Map<Locale, String> names = _fragmentEntryNames.getOrDefault(
					fragmentEntry.getFragmentEntryKey(),
					Collections.emptyMap());

				fragmentEntry.setName(
					names.getOrDefault(
						locale,
						names.getOrDefault(
							LocaleUtil.toLanguageId(LocaleUtil.getDefault()),
							fragmentEntry.getName())));

				return fragmentEntry;
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public String getName() {
		return _names.get(LocaleUtil.getDefault());
	}

	@Override
	public String getName(Locale locale) {
		String name = _names.get(locale);

		if (Validator.isNotNull(name)) {
			return name;
		}

		return getName();
	}

	public ResourceBundleLoader getResourceBundleLoader() {
		ServletContext servletContext = getServletContext();

		return Optional.ofNullable(
			ResourceBundleLoaderUtil.
				getResourceBundleLoaderByServletContextName(
					servletContext.getServletContextName())
		).orElse(
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader()
		);
	}

	public abstract ServletContext getServletContext();

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundle = bundleContext.getBundle();

		readAndCheckFragmentCollectionStructure();
	}

	protected void readAndCheckFragmentCollectionStructure() {
		try {
			Map<Locale, String> names = _getContributedCollectionNames();

			Enumeration<URL> enumeration = _bundle.findEntries(
				StringPool.BLANK,
				FragmentExportImportConstants.FILE_NAME_FRAGMENT_CONFIG, true);

			if (MapUtil.isEmpty(names) || !enumeration.hasMoreElements()) {
				return;
			}

			_names = names;

			while (enumeration.hasMoreElements()) {
				URL url = enumeration.nextElement();

				FragmentEntry fragmentEntry = _getFragmentEntry(url);

				_updateFragmentEntryLinks(fragmentEntry);

				List<FragmentEntry> fragmentEntryList =
					_fragmentEntries.computeIfAbsent(
						fragmentEntry.getType(), type -> new ArrayList<>());

				fragmentEntryList.add(fragmentEntry);
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Reference
	protected FragmentEntryLinkLocalService fragmentEntryLinkLocalService;

	@Reference
	protected FragmentEntryLocalService fragmentEntryLocalService;

	private Map<Locale, String> _getContributedCollectionNames()
		throws Exception {

		Class<?> clazz = getClass();

		String json = StreamUtil.toString(
			clazz.getResourceAsStream(
				"dependencies/" +
					FragmentExportImportConstants.FILE_NAME_COLLECTION_CONFIG));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

		String name = jsonObject.getString("name");

		Map<Locale, String> names = new HashMap<>();

		_setLocalizedNames(name, names, getResourceBundleLoader());

		return names;
	}

	private FragmentEntry _getFragmentEntry(URL url) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			StreamUtil.toString(url.openStream()));

		String fragmentEntryKey = StringBundler.concat(
			getFragmentCollectionKey(), StringPool.DASH,
			jsonObject.getString("fragmentEntryKey"));

		Map<Locale, String> names = _fragmentEntryNames.getOrDefault(
			fragmentEntryKey, new HashMap<>());

		ResourceBundleLoader resourceBundleLoader = getResourceBundleLoader();

		String name = jsonObject.getString("name");

		_setLocalizedNames(name, names, resourceBundleLoader);

		_fragmentEntryNames.put(fragmentEntryKey, names);

		String path = FileUtil.getPath(url.getPath());

		String css = _read(path, jsonObject.getString("cssPath"));
		String html = _read(path, jsonObject.getString("htmlPath"));
		String js = _read(path, jsonObject.getString("jsPath"));
		String configuration = _read(
			path, jsonObject.getString("configurationPath"));

		String thumbnailURL = _getImagePreviewURL(
			jsonObject.getString("thumbnail"));
		int type = FragmentConstants.getTypeFromLabel(
			jsonObject.getString("type"));

		FragmentEntry fragmentEntry =
			fragmentEntryLocalService.createFragmentEntry(0L);

		fragmentEntry.setFragmentEntryKey(fragmentEntryKey);
		fragmentEntry.setName(name);
		fragmentEntry.setCss(css);
		fragmentEntry.setHtml(html);
		fragmentEntry.setJs(js);
		fragmentEntry.setConfiguration(configuration);
		fragmentEntry.setType(type);
		fragmentEntry.setImagePreviewURL(thumbnailURL);

		return fragmentEntry;
	}

	private String _getImagePreviewURL(String fileName) {
		URL url = _bundle.getResource(
			"META-INF/resources/thumbnails/" + fileName);

		if (url == null) {
			return StringPool.BLANK;
		}

		ServletContext servletContext = getServletContext();

		return servletContext.getContextPath() + "/thumbnails/" + fileName;
	}

	private String _read(String path, String fileName) throws Exception {
		Class<?> clazz = getClass();

		StringBundler sb = new StringBundler(3);

		sb.append(path);
		sb.append("/");
		sb.append(fileName);

		return StringUtil.read(clazz.getResourceAsStream(sb.toString()));
	}

	private void _setLocalizedNames(
		String name, Map<Locale, String> names,
		ResourceBundleLoader resourceBundleLoader) {

		Set<Locale> availableLocales = new HashSet<>(
			LanguageUtil.getAvailableLocales());

		availableLocales.add(LocaleUtil.getDefault());

		for (Locale locale : availableLocales) {
			String languageId = LocaleUtil.toLanguageId(locale);

			ResourceBundle resourceBundle =
				resourceBundleLoader.loadResourceBundle(
					LocaleUtil.fromLanguageId(languageId));

			if (Validator.isNotNull(name)) {
				names.put(
					LocaleUtil.fromLanguageId(languageId),
					LanguageUtil.get(resourceBundle, name, name));
			}
		}
	}

	private void _updateFragmentEntryLinks(FragmentEntry fragmentEntry) {
		List<FragmentEntryLink> fragmentEntryLinks =
			fragmentEntryLinkLocalService.getFragmentEntryLinks(
				fragmentEntry.getFragmentEntryKey());

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			fragmentEntryLink.setCss(fragmentEntry.getCss());
			fragmentEntryLink.setHtml(fragmentEntry.getHtml());
			fragmentEntryLink.setJs(fragmentEntry.getJs());
			fragmentEntryLink.setConfiguration(
				fragmentEntry.getConfiguration());

			fragmentEntryLinkLocalService.updateFragmentEntryLink(
				fragmentEntryLink);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseFragmentCollectionContributor.class);

	private Bundle _bundle;
	private final Map<Integer, List<FragmentEntry>> _fragmentEntries =
		new HashMap<>();
	private final Map<String, Map<Locale, String>> _fragmentEntryNames =
		new HashMap<>();
	private Map<Locale, String> _names;

}