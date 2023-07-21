/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.list.type.service.base;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.service.ListTypeDefinitionService;
import com.liferay.list.type.service.ListTypeDefinitionServiceUtil;
import com.liferay.list.type.service.persistence.ListTypeDefinitionPersistence;
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
 * Provides the base implementation for the list type definition remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.list.type.service.impl.ListTypeDefinitionServiceImpl}.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see com.liferay.list.type.service.impl.ListTypeDefinitionServiceImpl
 * @generated
 */
public abstract class ListTypeDefinitionServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, ListTypeDefinitionService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ListTypeDefinitionService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ListTypeDefinitionServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		ListTypeDefinitionServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ListTypeDefinitionService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		listTypeDefinitionService = (ListTypeDefinitionService)aopProxy;

		ListTypeDefinitionServiceUtil.setService(listTypeDefinitionService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ListTypeDefinitionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ListTypeDefinition.class;
	}

	protected String getModelClassName() {
		return ListTypeDefinition.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				listTypeDefinitionPersistence.getDataSource();

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
	protected com.liferay.list.type.service.ListTypeDefinitionLocalService
		listTypeDefinitionLocalService;

	protected ListTypeDefinitionService listTypeDefinitionService;

	@Reference
	protected ListTypeDefinitionPersistence listTypeDefinitionPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		ListTypeDefinitionServiceBaseImpl.class);

}