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
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.persistence.LayoutFinder;
import com.liferay.portal.kernel.service.persistence.LayoutPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the layout remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.LayoutServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.LayoutServiceImpl
 * @generated
 */
public abstract class LayoutServiceBaseImpl
	extends BaseServiceImpl implements IdentifiableOSGiService, LayoutService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LayoutService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LayoutServiceUtil</code>.
	 */

	/**
	 * Returns the layout local service.
	 *
	 * @return the layout local service
	 */
	public com.liferay.portal.kernel.service.LayoutLocalService
		getLayoutLocalService() {

		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(
		com.liferay.portal.kernel.service.LayoutLocalService
			layoutLocalService) {

		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Returns the layout remote service.
	 *
	 * @return the layout remote service
	 */
	public LayoutService getLayoutService() {
		return layoutService;
	}

	/**
	 * Sets the layout remote service.
	 *
	 * @param layoutService the layout remote service
	 */
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}

	/**
	 * Returns the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Returns the layout finder.
	 *
	 * @return the layout finder
	 */
	public LayoutFinder getLayoutFinder() {
		return layoutFinder;
	}

	/**
	 * Sets the layout finder.
	 *
	 * @param layoutFinder the layout finder
	 */
	public void setLayoutFinder(LayoutFinder layoutFinder) {
		this.layoutFinder = layoutFinder;
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
		LayoutServiceUtil.setService(layoutService);
	}

	public void destroy() {
		LayoutServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Layout.class;
	}

	protected String getModelClassName() {
		return Layout.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutPersistence.getDataSource();

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
		type = com.liferay.portal.kernel.service.LayoutLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutLocalService
		layoutLocalService;

	@BeanReference(type = LayoutService.class)
	protected LayoutService layoutService;

	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;

	@BeanReference(type = LayoutFinder.class)
	protected LayoutFinder layoutFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutServiceBaseImpl.class);

}