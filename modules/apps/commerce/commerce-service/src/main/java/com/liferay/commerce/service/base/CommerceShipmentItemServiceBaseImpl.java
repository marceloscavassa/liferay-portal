/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.base;

import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.CommerceShipmentItemService;
import com.liferay.commerce.service.CommerceShipmentItemServiceUtil;
import com.liferay.commerce.service.persistence.CommerceShipmentItemFinder;
import com.liferay.commerce.service.persistence.CommerceShipmentItemPersistence;
import com.liferay.portal.aop.AopService;
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

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the commerce shipment item remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CommerceShipmentItemServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CommerceShipmentItemServiceImpl
 * @generated
 */
public abstract class CommerceShipmentItemServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, CommerceShipmentItemService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceShipmentItemService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceShipmentItemServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		CommerceShipmentItemServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CommerceShipmentItemService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		commerceShipmentItemService = (CommerceShipmentItemService)aopProxy;

		CommerceShipmentItemServiceUtil.setService(commerceShipmentItemService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceShipmentItemService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceShipmentItem.class;
	}

	protected String getModelClassName() {
		return CommerceShipmentItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceShipmentItemPersistence.getDataSource();

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

	@Reference
	protected com.liferay.commerce.service.CommerceShipmentItemLocalService
		commerceShipmentItemLocalService;

	protected CommerceShipmentItemService commerceShipmentItemService;

	@Reference
	protected CommerceShipmentItemPersistence commerceShipmentItemPersistence;

	@Reference
	protected CommerceShipmentItemFinder commerceShipmentItemFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShipmentItemServiceBaseImpl.class);

}