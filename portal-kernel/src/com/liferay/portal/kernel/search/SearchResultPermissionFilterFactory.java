/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Eric Yan
 */
public interface SearchResultPermissionFilterFactory {

	public SearchResultPermissionFilter create(
		SearchResultPermissionFilterSearcher
			searchResultPermissionFilterSearcher,
		PermissionChecker permissionChecker);

}