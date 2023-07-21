/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.permission;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Alec Sloan
 */
public interface CommerceCatalogPermission {

	public void check(
			PermissionChecker permissionChecker,
			CommerceCatalog commerceCatalog, String actionId)
		throws PortalException;

	public void check(
			PermissionChecker permissionChecker, long commerceCatalogId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceCatalog commerceCatalog, String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long commerceCatalogId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceCatalogIds,
			String actionId)
		throws PortalException;

}