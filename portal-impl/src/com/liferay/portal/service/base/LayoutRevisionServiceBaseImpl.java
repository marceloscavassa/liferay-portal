/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.LayoutRevisionService;
import com.liferay.portal.kernel.service.LayoutRevisionServiceUtil;
import com.liferay.portal.kernel.service.persistence.LayoutRevisionPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the layout revision remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.LayoutRevisionServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.LayoutRevisionServiceImpl
 * @generated
 */
public abstract class LayoutRevisionServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, LayoutRevisionService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LayoutRevisionService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LayoutRevisionServiceUtil</code>.
	 */

	/**
	 * Returns the layout revision local service.
	 *
	 * @return the layout revision local service
	 */
	public com.liferay.portal.kernel.service.LayoutRevisionLocalService
		getLayoutRevisionLocalService() {

		return layoutRevisionLocalService;
	}

	/**
	 * Sets the layout revision local service.
	 *
	 * @param layoutRevisionLocalService the layout revision local service
	 */
	public void setLayoutRevisionLocalService(
		com.liferay.portal.kernel.service.LayoutRevisionLocalService
			layoutRevisionLocalService) {

		this.layoutRevisionLocalService = layoutRevisionLocalService;
	}

	/**
	 * Returns the layout revision remote service.
	 *
	 * @return the layout revision remote service
	 */
	public LayoutRevisionService getLayoutRevisionService() {
		return layoutRevisionService;
	}

	/**
	 * Sets the layout revision remote service.
	 *
	 * @param layoutRevisionService the layout revision remote service
	 */
	public void setLayoutRevisionService(
		LayoutRevisionService layoutRevisionService) {

		this.layoutRevisionService = layoutRevisionService;
	}

	/**
	 * Returns the layout revision persistence.
	 *
	 * @return the layout revision persistence
	 */
	public LayoutRevisionPersistence getLayoutRevisionPersistence() {
		return layoutRevisionPersistence;
	}

	/**
	 * Sets the layout revision persistence.
	 *
	 * @param layoutRevisionPersistence the layout revision persistence
	 */
	public void setLayoutRevisionPersistence(
		LayoutRevisionPersistence layoutRevisionPersistence) {

		this.layoutRevisionPersistence = layoutRevisionPersistence;
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
		LayoutRevisionServiceUtil.setService(layoutRevisionService);
	}

	public void destroy() {
		LayoutRevisionServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutRevisionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LayoutRevision.class;
	}

	protected String getModelClassName() {
		return LayoutRevision.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutRevisionPersistence.getDataSource();

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
		type = com.liferay.portal.kernel.service.LayoutRevisionLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutRevisionLocalService
		layoutRevisionLocalService;

	@BeanReference(type = LayoutRevisionService.class)
	protected LayoutRevisionService layoutRevisionService;

	@BeanReference(type = LayoutRevisionPersistence.class)
	protected LayoutRevisionPersistence layoutRevisionPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutRevisionServiceBaseImpl.class);

}