/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.test.util;

import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMTemplateTestUtil;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
public class VirtualCPTypeTestUtil {

	public static CPDefinitionVirtualSetting addCPDefinitionVirtualSetting(
			long groupId, String className, long classPK, long fileEntryId,
			int activationStatus, long duration, long sampleFileEntryId,
			long termsOfUseJournalArticleResourcePrimKey)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		String url = null;

		if (fileEntryId <= 0) {
			url = "http://www.example.com/download";
		}

		String sampleURL = null;

		if (sampleFileEntryId <= 0) {
			sampleURL = "http://www.example.com/sample";
		}

		Map<Locale, String> termsOfUseContentMap = null;

		if (termsOfUseJournalArticleResourcePrimKey <= 0) {
			termsOfUseContentMap = RandomTestUtil.randomLocaleStringMap();
		}

		return CPDefinitionVirtualSettingLocalServiceUtil.
			addCPDefinitionVirtualSetting(
				className, classPK, fileEntryId, url, activationStatus,
				duration, RandomTestUtil.randomInt(), true, sampleFileEntryId,
				sampleURL, true, termsOfUseContentMap,
				termsOfUseJournalArticleResourcePrimKey, serviceContext);
	}

	public static JournalArticle addJournalArticle(long groupId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		Map<Locale, String> contentMap = RandomTestUtil.randomLocaleStringMap();

		Locale defaultLocale = LocaleUtil.getSiteDefault();
		Set<Locale> locales = contentMap.keySet();

		String content = DDMStructureTestUtil.getSampleStructuredContent(
			contentMap, LocaleUtil.toLanguageId(defaultLocale));

		DDMForm ddmForm = DDMStructureTestUtil.getSampleDDMForm(
			locales.toArray(new Locale[0]), defaultLocale);

		long ddmGroupId = GetterUtil.getLong(
			serviceContext.getAttribute("ddmGroupId"), groupId);

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			ddmGroupId, JournalArticle.class.getName(), ddmForm, defaultLocale);

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			ddmGroupId, ddmStructure.getStructureId(),
			PortalUtil.getClassNameId(JournalArticle.class));

		return JournalArticleLocalServiceUtil.addArticle(
			null, serviceContext.getUserId(), groupId,
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), content,
			ddmStructure.getStructureId(), ddmTemplate.getTemplateKey(),
			serviceContext);
	}

}