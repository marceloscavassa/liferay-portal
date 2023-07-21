/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.sort;

import com.liferay.portal.search.query.Query;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface NestedSort {

	public Query getFilterQuery();

	public int getMaxChildren();

	public NestedSort getNestedSort();

	public String getPath();

	public void setFilterQuery(Query filterQuery);

	public void setMaxChildren(int maxChildren);

	public void setNestedSort(NestedSort nestedSort);

}