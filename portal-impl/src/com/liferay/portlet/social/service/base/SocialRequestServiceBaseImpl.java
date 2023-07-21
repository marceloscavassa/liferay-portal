/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.service.SocialRequestService;
import com.liferay.social.kernel.service.SocialRequestServiceUtil;
import com.liferay.social.kernel.service.persistence.SocialRequestPersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the social request remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.social.service.impl.SocialRequestServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.social.service.impl.SocialRequestServiceImpl
 * @generated
 */
public abstract class SocialRequestServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, SocialRequestService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SocialRequestService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SocialRequestServiceUtil</code>.
	 */

	/**
	 * Returns the social request local service.
	 *
	 * @return the social request local service
	 */
	public com.liferay.social.kernel.service.SocialRequestLocalService
		getSocialRequestLocalService() {

		return socialRequestLocalService;
	}

	/**
	 * Sets the social request local service.
	 *
	 * @param socialRequestLocalService the social request local service
	 */
	public void setSocialRequestLocalService(
		com.liferay.social.kernel.service.SocialRequestLocalService
			socialRequestLocalService) {

		this.socialRequestLocalService = socialRequestLocalService;
	}

	/**
	 * Returns the social request remote service.
	 *
	 * @return the social request remote service
	 */
	public SocialRequestService getSocialRequestService() {
		return socialRequestService;
	}

	/**
	 * Sets the social request remote service.
	 *
	 * @param socialRequestService the social request remote service
	 */
	public void setSocialRequestService(
		SocialRequestService socialRequestService) {

		this.socialRequestService = socialRequestService;
	}

	/**
	 * Returns the social request persistence.
	 *
	 * @return the social request persistence
	 */
	public SocialRequestPersistence getSocialRequestPersistence() {
		return socialRequestPersistence;
	}

	/**
	 * Sets the social request persistence.
	 *
	 * @param socialRequestPersistence the social request persistence
	 */
	public void setSocialRequestPersistence(
		SocialRequestPersistence socialRequestPersistence) {

		this.socialRequestPersistence = socialRequestPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		SocialRequestServiceUtil.setService(socialRequestService);
	}

	public void destroy() {
		SocialRequestServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SocialRequestService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SocialRequest.class;
	}

	protected String getModelClassName() {
		return SocialRequest.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = socialRequestPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@BeanReference(
		type = com.liferay.social.kernel.service.SocialRequestLocalService.class
	)
	protected com.liferay.social.kernel.service.SocialRequestLocalService
		socialRequestLocalService;

	@BeanReference(type = SocialRequestService.class)
	protected SocialRequestService socialRequestService;

	@BeanReference(type = SocialRequestPersistence.class)
	protected SocialRequestPersistence socialRequestPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		SocialRequestServiceBaseImpl.class);

}