/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.service;

import com.liferay.object.model.ObjectValidationRule;
import com.liferay.object.model.ObjectValidationRuleSetting;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for ObjectValidationRule. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marco Leo
 * @see ObjectValidationRuleServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ObjectValidationRuleService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.object.service.impl.ObjectValidationRuleServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the object validation rule remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ObjectValidationRuleServiceUtil} if injection and service tracking are not available.
	 */
	public ObjectValidationRule addObjectValidationRule(
			long objectDefinitionId, boolean active, String engine,
			Map<Locale, String> errorLabelMap, Map<Locale, String> nameMap,
			String outputType, String script,
			List<ObjectValidationRuleSetting> objectValidationRuleSettings)
		throws PortalException;

	public ObjectValidationRule deleteObjectValidationRule(
			long objectValidationRuleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ObjectValidationRule getObjectValidationRule(
			long objectValidationRuleId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public ObjectValidationRule updateObjectValidationRule(
			long objectValidationRuleId, boolean active, String engine,
			Map<Locale, String> errorLabelMap, Map<Locale, String> nameMap,
			String outputType, String script,
			List<ObjectValidationRuleSetting> objectValidationRuleSettings)
		throws PortalException;

}