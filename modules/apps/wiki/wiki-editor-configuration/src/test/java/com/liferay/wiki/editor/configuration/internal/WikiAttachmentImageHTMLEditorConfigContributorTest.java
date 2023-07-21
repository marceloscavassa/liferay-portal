/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.editor.configuration.internal;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLWrapper;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.language.LanguageImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.wiki.configuration.WikiFileUploadConfiguration;
import com.liferay.wiki.constants.WikiPortletKeys;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Sergio González
 */
public class WikiAttachmentImageHTMLEditorConfigContributorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() {
		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(new LanguageImpl());
	}

	@Before
	public void setUp() {
		_inputEditorTaglibAttributes.put(
			"liferay-ui:input-editor:name", "testEditor");

		_wikiAttachmentImageHTMLEditorConfigContributor =
			new WikiAttachmentImageHTMLEditorConfigContributor();

		ReflectionTestUtil.setFieldValue(
			_wikiAttachmentImageHTMLEditorConfigContributor, "_itemSelector",
			_itemSelector);
	}

	@Test
	public void testItemSelectorURLWhenAllowBrowseAndNullWikiPage()
		throws Exception {

		setAllowBrowseDocuments(true);
		setWikiPageResourcePrimKey(0);

		Mockito.when(
			_itemSelector.getItemSelectorURL(
				Mockito.nullable(RequestBackedPortletURLFactory.class),
				Mockito.nullable(String.class),
				Mockito.any(ItemSelectorCriterion.class),
				Mockito.any(ItemSelectorCriterion.class))
		).thenReturn(
			new PortletURLWrapper(null) {

				@Override
				public String toString() {
					return "itemSelectorPortletURLWithImageUrlSelectionViews";
				}

			}
		);

		JSONObject originalJSONObject =
			getJSONObjectWithDefaultItemSelectorURL();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		_wikiAttachmentImageHTMLEditorConfigContributor.
			populateConfigJSONObject(
				jsonObject, _inputEditorTaglibAttributes, null, null);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"filebrowserImageBrowseLinkUrl",
				"itemSelectorPortletURLWithImageUrlSelectionViews"
			).put(
				"filebrowserImageBrowseUrl",
				"itemSelectorPortletURLWithImageUrlSelectionViews"
			).put(
				"removePlugins", "plugin1,ae_addimages"
			).toString(),
			jsonObject.toString(), true);
	}

	@Test
	public void testItemSelectorURLWhenAllowBrowseAndValidWikiPage()
		throws Exception {

		setAllowBrowseDocuments(true);
		setWikiPageResourcePrimKey(1);

		Mockito.when(
			_itemSelector.getItemSelectorURL(
				Mockito.any(RequestBackedPortletURLFactory.class),
				Mockito.anyString(), Mockito.any(ItemSelectorCriterion.class),
				Mockito.any(ItemSelectorCriterion.class),
				Mockito.any(ItemSelectorCriterion.class),
				Mockito.any(ItemSelectorCriterion.class))
		).thenReturn(
			new PortletURLWrapper(null) {

				@Override
				public String toString() {
					return "itemSelectorPortletURLWithWikiImageUrl" +
						"AndUploadSelectionViews";
				}

			}
		);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			Mockito.mock(RequestBackedPortletURLFactory.class);

		Mockito.when(
			requestBackedPortletURLFactory.createActionURL(WikiPortletKeys.WIKI)
		).thenReturn(
			ProxyFactory.newDummyInstance(LiferayPortletURL.class)
		);

		JSONObject jsonObject = getJSONObjectWithDefaultItemSelectorURL();

		_wikiAttachmentImageHTMLEditorConfigContributor.
			setWikiFileUploadConfiguration(
				new WikiFileUploadConfiguration() {

					@Override
					public long attachmentMaxSize() {
						return 0;
					}

					@Override
					public String[] attachmentMimeTypes() {
						return new String[] {StringPool.STAR};
					}

				});

		_wikiAttachmentImageHTMLEditorConfigContributor.
			populateConfigJSONObject(
				jsonObject, _inputEditorTaglibAttributes, new ThemeDisplay(),
				requestBackedPortletURLFactory);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"filebrowserImageBrowseLinkUrl",
				"itemSelectorPortletURLWithWikiImageUrlAndUploadSelectionViews"
			).put(
				"filebrowserImageBrowseUrl",
				"itemSelectorPortletURLWithWikiImageUrlAndUploadSelectionViews"
			).put(
				"removePlugins", "plugin1"
			).toString(),
			jsonObject.toString(), true);
	}

	@Test
	public void testItemSelectorURLWhenNotAllowBrowseAndNullWikiPage()
		throws Exception {

		setAllowBrowseDocuments(false);
		setWikiPageResourcePrimKey(0);

		JSONObject originalJSONObject =
			getJSONObjectWithDefaultItemSelectorURL();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		_wikiAttachmentImageHTMLEditorConfigContributor.
			populateConfigJSONObject(
				jsonObject, _inputEditorTaglibAttributes, null, null);

		JSONObject expectedJSONObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		expectedJSONObject.put("removePlugins", "plugin1");

		JSONAssert.assertEquals(
			expectedJSONObject.toString(), jsonObject.toString(), true);
	}

	@Test
	public void testItemSelectorURLWhenNotAllowBrowseAndValidWikiPage()
		throws Exception {

		setAllowBrowseDocuments(false);
		setWikiPageResourcePrimKey(1);

		JSONObject originalJSONObject =
			getJSONObjectWithDefaultItemSelectorURL();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		_wikiAttachmentImageHTMLEditorConfigContributor.
			populateConfigJSONObject(
				jsonObject, _inputEditorTaglibAttributes, null, null);

		JSONObject expectedJSONObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		expectedJSONObject.put("removePlugins", "plugin1");

		JSONAssert.assertEquals(
			expectedJSONObject.toString(), jsonObject.toString(), true);
	}

	protected JSONObject getJSONObjectWithDefaultItemSelectorURL() {
		return JSONUtil.put(
			"filebrowserImageBrowseLinkUrl", "defaultItemSelectorPortletURL"
		).put(
			"filebrowserImageBrowseUrl", "defaultItemSelectorPortletURL"
		).put(
			"removePlugins", "plugin1"
		);
	}

	protected void setAllowBrowseDocuments(boolean allowBrowseDocuments) {
		_inputEditorTaglibAttributes.put(
			"liferay-ui:input-editor:allowBrowseDocuments",
			allowBrowseDocuments);
	}

	protected void setWikiPageResourcePrimKey(long primKey) {
		_inputEditorTaglibAttributes.put(
			"liferay-ui:input-editor:fileBrowserParams",
			HashMapBuilder.put(
				"wikiPageResourcePrimKey", String.valueOf(primKey)
			).build());
	}

	private final Map<String, Object> _inputEditorTaglibAttributes =
		new HashMap<>();
	private final ItemSelector _itemSelector = Mockito.mock(ItemSelector.class);
	private WikiAttachmentImageHTMLEditorConfigContributor
		_wikiAttachmentImageHTMLEditorConfigContributor;

}