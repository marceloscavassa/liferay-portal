/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.account.admin.web.internal.security.permission.resource.AccountRolePermission;
import com.liferay.account.constants.AccountRoleConstants;
import com.liferay.account.model.AccountRole;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joao Victor Alves
 */
@Component(
	property = "screen.navigation.entry.order:Integer=10",
	service = ScreenNavigationEntry.class
)
public class AccountRoleDetailsScreenNavigationEntry
	extends AccountRoleDetailsScreenNavigationCategory
	implements ScreenNavigationEntry<AccountRole> {

	@Override
	public String getEntryKey() {
		return getCategoryKey();
	}

	@Override
	public boolean isVisible(User user, AccountRole accountRole) {
		if (accountRole == null) {
			return true;
		}

		Role role = _roleLocalService.fetchRole(accountRole.getRoleId());

		if ((role != null) && AccountRoleConstants.isSharedRole(role)) {
			return false;
		}

		return AccountRolePermission.contains(
			PermissionCheckerFactoryUtil.create(user),
			accountRole.getAccountRoleId(), ActionKeys.UPDATE);
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/account_entries_admin/account_role/details.jsp");
	}

	@Reference
	protected JSPRenderer jspRenderer;

	@Reference
	private RoleLocalService _roleLocalService;

}