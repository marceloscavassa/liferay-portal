/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.util;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountRole;
import com.liferay.account.service.AccountRoleLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.DuplicateRoleException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.roles.admin.role.type.contributor.RoleTypeContributor;
import com.liferay.roles.admin.role.type.contributor.provider.RoleTypeContributorProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Michael C. Han
 */
public class RoleUtil {

	public static Role getRole(
			String name, int roleType, boolean autoCreate,
			ServiceContext serviceContext)
		throws PortalException {

		Role role = RoleLocalServiceUtil.fetchRole(
			serviceContext.getCompanyId(), name);

		if (role == null) {
			if (!autoCreate) {
				throw new NoSuchRoleException(
					StringBundler.concat(
						"No Role exists with the key {companyId=",
						serviceContext.getCompanyId(), ", name=", name, "}"));
			}

			Map<Locale, String> descriptionMap = HashMapBuilder.put(
				LocaleUtil.getDefault(),
				"This is an autogenerated role from the workflow definition."
			).build();

			if (roleType == RoleConstants.TYPE_ACCOUNT) {
				AccountRole accountRole =
					AccountRoleLocalServiceUtil.addAccountRole(
						serviceContext.getUserId(),
						AccountConstants.ACCOUNT_ENTRY_ID_DEFAULT, name, null,
						descriptionMap);

				role = accountRole.getRole();
			}
			else {
				role = RoleLocalServiceUtil.addRole(
					serviceContext.getUserId(), null, 0, name, null,
					descriptionMap, roleType, null, null);
			}
		}
		else if (role.getType() != roleType) {
			throw new DuplicateRoleException(
				"Role already exists with name " + name);
		}

		return role;
	}

	public static List<Long> getRoleIds(ServiceContext serviceContext) {
		List<Role> roles = RoleLocalServiceUtil.getUserRoles(
			serviceContext.getUserId());

		List<Long> roleIds = new ArrayList<>(roles.size());

		for (Role role : roles) {
			roleIds.add(role.getRoleId());
		}

		return roleIds;
	}

	public static int getRoleType(String roleType) {
		RoleTypeContributorProvider roleTypeContributorProvider =
			_serviceTracker.getService();

		for (RoleTypeContributor roleTypeContributor :
				roleTypeContributorProvider.getRoleTypeContributors()) {

			if (roleType.equals(roleTypeContributor.getTypeLabel())) {
				return roleTypeContributor.getType();
			}
		}

		if (roleType.equals(_LEGACY_TYPE_COMMUNITY_LABEL)) {
			return RoleConstants.TYPE_SITE;
		}

		return RoleConstants.TYPE_REGULAR;
	}

	private static final String _LEGACY_TYPE_COMMUNITY_LABEL = "community";

	private static final ServiceTracker
		<RoleTypeContributorProvider, RoleTypeContributorProvider>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			RoleTypeContributorProvider.class);

		ServiceTracker<RoleTypeContributorProvider, RoleTypeContributorProvider>
			serviceTracker = new ServiceTracker<>(
				bundle.getBundleContext(), RoleTypeContributorProvider.class,
				null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}