/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.account.service;

import com.liferay.account.model.AccountGroupRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for AccountGroupRel. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountGroupRelServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AccountGroupRelService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.account.service.impl.AccountGroupRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the account group rel remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AccountGroupRelServiceUtil} if injection and service tracking are not available.
	 */
	public AccountGroupRel addAccountGroupRel(
			long accountGroupId, String className, long classPK)
		throws PortalException;

	public void addAccountGroupRels(
			long accountGroupId, String className, long[] classPKs)
		throws PortalException;

	public AccountGroupRel deleteAccountGroupRel(long accountGroupRelId)
		throws PortalException;

	public void deleteAccountGroupRels(
			long accountGroupId, String className, long[] classPKs)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountGroupRel fetchAccountGroupRel(
			long accountGroupId, String className, long classPK)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

}