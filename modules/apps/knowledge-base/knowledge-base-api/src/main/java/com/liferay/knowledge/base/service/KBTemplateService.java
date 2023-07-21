/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.model.KBTemplateSearchDisplay;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for KBTemplate. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateServiceUtil
 * @generated
 */
@AccessControlled
@CTAware
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface KBTemplateService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.knowledge.base.service.impl.KBTemplateServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the kb template remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link KBTemplateServiceUtil} if injection and service tracking are not available.
	 */
	public KBTemplate addKBTemplate(
			String portletId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException;

	public KBTemplate deleteKBTemplate(long kbTemplateId)
		throws PortalException;

	public void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBTemplate> getGroupKBTemplates(
		long groupId, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBTemplatesCount(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplate getKBTemplate(long kbTemplateId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBTemplateSearchDisplay getKBTemplateSearchDisplay(
			long groupId, String title, String content, Date startDate,
			Date endDate, boolean andOperator, int[] curStartValues, int cur,
			int delta, OrderByComparator<KBTemplate> orderByComparator)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public KBTemplate updateKBTemplate(
			long kbTemplateId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException;

}