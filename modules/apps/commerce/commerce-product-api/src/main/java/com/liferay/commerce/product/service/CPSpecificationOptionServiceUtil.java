/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service;

import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Map;

/**
 * Provides the remote service utility for CPSpecificationOption. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPSpecificationOptionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPSpecificationOptionService
 * @generated
 */
public class CPSpecificationOptionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPSpecificationOptionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CPSpecificationOption addCPSpecificationOption(
			long cpOptionCategoryId, Map<java.util.Locale, String> titleMap,
			Map<java.util.Locale, String> descriptionMap, boolean facetable,
			String key,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPSpecificationOption(
			cpOptionCategoryId, titleMap, descriptionMap, facetable, key,
			serviceContext);
	}

	public static void deleteCPSpecificationOption(long cpSpecificationOptionId)
		throws PortalException {

		getService().deleteCPSpecificationOption(cpSpecificationOptionId);
	}

	public static CPSpecificationOption fetchCPSpecificationOption(
			long companyId, String key)
		throws PortalException {

		return getService().fetchCPSpecificationOption(companyId, key);
	}

	public static CPSpecificationOption getCPSpecificationOption(
			long cpSpecificationOptionId)
		throws PortalException {

		return getService().getCPSpecificationOption(cpSpecificationOptionId);
	}

	public static CPSpecificationOption getCPSpecificationOption(
			long companyId, String key)
		throws PortalException {

		return getService().getCPSpecificationOption(companyId, key);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CPSpecificationOption> searchCPSpecificationOptions(
				long companyId, Boolean facetable, String keywords, int start,
				int end, com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchCPSpecificationOptions(
			companyId, facetable, keywords, start, end, sort);
	}

	public static CPSpecificationOption updateCPSpecificationOption(
			long cpSpecificationOptionId, long cpOptionCategoryId,
			Map<java.util.Locale, String> titleMap,
			Map<java.util.Locale, String> descriptionMap, boolean facetable,
			String key,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPSpecificationOption(
			cpSpecificationOptionId, cpOptionCategoryId, titleMap,
			descriptionMap, facetable, key, serviceContext);
	}

	public static CPSpecificationOptionService getService() {
		return _service;
	}

	public static void setService(CPSpecificationOptionService service) {
		_service = service;
	}

	private static volatile CPSpecificationOptionService _service;

}