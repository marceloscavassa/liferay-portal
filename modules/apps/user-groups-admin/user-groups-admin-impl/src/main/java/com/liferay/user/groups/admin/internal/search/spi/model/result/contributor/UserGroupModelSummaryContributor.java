/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.groups.admin.internal.search.spi.model.result.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luan Maoski
 */
@Component(
	property = "indexer.class.name=com.liferay.portal.kernel.model.UserGroup",
	service = ModelSummaryContributor.class
)
public class UserGroupModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		return new Summary(document.get(Field.NAME), null);
	}

}