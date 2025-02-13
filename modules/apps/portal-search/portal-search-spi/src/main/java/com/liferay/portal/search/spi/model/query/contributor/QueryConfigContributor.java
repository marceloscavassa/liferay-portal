/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.spi.model.query.contributor.helper.QueryConfigContributorHelper;

/**
 * @author Michael C. Han
 */
public interface QueryConfigContributor {

	public void contributeQueryConfigurations(
		SearchContext searchContext,
		QueryConfigContributorHelper queryConfigContributorHelper);

}