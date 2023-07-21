/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.storage.helper;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.json.storage.service.JSONStorageEntryLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.search.tuning.rankings.web.internal.index.Ranking;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(service = RankingJSONStorageHelper.class)
public class RankingJSONStorageHelper {

	public void addJSONStorageEntry(
		long companyId, List<String> aliases, List<String> hiddenDocumentIds,
		boolean inactive, String indexName, String name, List<Ranking.Pin> pins,
		String queryString) {

		long classPK = counterLocalService.increment();

		JSONArray pinsJSONArray = _jsonFactory.createJSONArray();

		for (Ranking.Pin pin : pins) {
			pinsJSONArray.put(
				JSONUtil.put(
					"documentId", pin.getDocumentId()
				).put(
					"position", pin.getPosition()
				));
		}

		JSONObject jsonObject = JSONUtil.put(
			"aliases", _jsonFactory.createJSONArray(aliases)
		).put(
			"hiddenDocumentIds", _jsonFactory.createJSONArray(hiddenDocumentIds)
		).put(
			"inactive", inactive
		).put(
			"indexName", indexName
		).put(
			"name", name
		).put(
			"pins", pinsJSONArray
		).put(
			"queryString", queryString
		).put(
			"rankingDocumentId", Ranking.class.getName() + "_PORTLET_" + classPK
		);

		jsonStorageEntryLocalService.addJSONStorageEntries(
			companyId, classNameLocalService.getClassNameId(Ranking.class),
			classPK, jsonObject.toString());
	}

	public String addJSONStorageEntry(
		String indexName, String name, String queryString) {

		long classPK = counterLocalService.increment();

		String rankingDocumentId =
			Ranking.class.getName() + "_PORTLET_" + classPK;

		jsonStorageEntryLocalService.addJSONStorageEntries(
			CompanyThreadLocal.getCompanyId(),
			classNameLocalService.getClassNameId(Ranking.class), classPK,
			JSONUtil.put(
				"indexName", indexName
			).put(
				"name", name
			).put(
				"queryString", queryString
			).put(
				"rankingDocumentId", rankingDocumentId
			).toString());

		return rankingDocumentId;
	}

	public void deleteJSONStorageEntry(long classPK) {
		jsonStorageEntryLocalService.deleteJSONStorageEntries(
			classNameLocalService.getClassNameId(Ranking.class), classPK);
	}

	public void updateJSONStorageEntry(
		long classPK, List<String> aliases, List<String> hiddenDocumentIds,
		boolean inactive, String name, List<Ranking.Pin> pins) {

		JSONObject jsonObject = jsonStorageEntryLocalService.getJSONObject(
			classNameLocalService.getClassNameId(Ranking.class), classPK);

		JSONArray pinsJSONArray = _jsonFactory.createJSONArray();

		for (Ranking.Pin pin : pins) {
			pinsJSONArray.put(
				JSONUtil.put(
					"documentId", pin.getDocumentId()
				).put(
					"position", pin.getPosition()
				));
		}

		jsonObject.put(
			"aliases", _jsonFactory.createJSONArray(aliases)
		).put(
			"hiddenDocumentIds", _jsonFactory.createJSONArray(hiddenDocumentIds)
		).put(
			"inactive", inactive
		).put(
			"name", name
		).put(
			"pins", pinsJSONArray
		);

		jsonStorageEntryLocalService.updateJSONStorageEntries(
			CompanyThreadLocal.getCompanyId(),
			classNameLocalService.getClassNameId(Ranking.class), classPK,
			jsonObject.toString());
	}

	@Reference
	protected ClassNameLocalService classNameLocalService;

	@Reference
	protected CounterLocalService counterLocalService;

	@Reference
	protected JSONStorageEntryLocalService jsonStorageEntryLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}