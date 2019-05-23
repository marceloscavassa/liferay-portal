/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.base.CommerceAccountGroupServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupServiceImpl
	extends CommerceAccountGroupServiceBaseImpl {

	@Override
	public CommerceAccountGroup addCommerceAccountGroup(
			String name, int type, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceAccountGroupLocalService.addCommerceAccountGroup(
			name, type, externalReferenceCode, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroup(long commerceAccountGroupId)
		throws PortalException {

		commerceAccountGroupLocalService.deleteCommerceAccountGroup(
			commerceAccountGroupId);
	}

	@Override
	public CommerceAccountGroup getCommerceAccountGroup(
			long commerceAccountGroupId)
		throws PortalException {

		return commerceAccountGroupLocalService.getCommerceAccountGroup(
			commerceAccountGroupId);
	}

	@Override
	public List<CommerceAccountGroup> getCommerceAccountGroups(
			long companyId, int start, int end,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws PortalException {

		return commerceAccountGroupLocalService.getCommerceAccountGroups(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAccountGroupsCount(long companyId)
		throws PortalException {

		return commerceAccountGroupLocalService.getCommerceAccountGroupsCount(
			companyId);
	}

	@Override
	public List<CommerceAccountGroup> searchCommerceAccountGroups(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		return commerceAccountGroupLocalService.searchCommerceAccountGroups(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceAccountsGroupCount(long companyId, String keywords)
		throws PortalException {

		return commerceAccountGroupLocalService.
			searchCommerceAccountsGroupCount(companyId, keywords);
	}

	@Override
	public CommerceAccountGroup updateCommerceAccountGroup(
			long commerceAccountGroupId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceAccountGroupLocalService.updateCommerceAccountGroup(
			commerceAccountGroupId, name, serviceContext);
	}

}