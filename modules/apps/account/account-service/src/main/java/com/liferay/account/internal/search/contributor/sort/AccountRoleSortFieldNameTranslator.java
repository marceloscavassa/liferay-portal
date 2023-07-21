/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.internal.search.contributor.sort;

import com.liferay.account.model.AccountRole;
import com.liferay.portal.search.contributor.sort.SortFieldNameTranslator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Drew Brokke
 */
@Component(service = SortFieldNameTranslator.class)
public class AccountRoleSortFieldNameTranslator
	implements SortFieldNameTranslator {

	@Override
	public Class<?> getEntityClass() {
		return AccountRole.class;
	}

	@Override
	public String getSortFieldName(String orderByCol) {
		return orderByCol;
	}

}