/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.service.base;

import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.batch.engine.service.BatchEngineImportTaskService;
import com.liferay.batch.engine.service.BatchEngineImportTaskServiceUtil;
import com.liferay.batch.engine.service.persistence.BatchEngineImportTaskPersistence;
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
 * Provides the base implementation for the batch engine import task remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.batch.engine.service.impl.BatchEngineImportTaskServiceImpl}.
 * </p>
 *
 * @author Shuyang Zhou
 * @see com.liferay.batch.engine.service.impl.BatchEngineImportTaskServiceImpl
 * @generated
 */
public abstract class BatchEngineImportTaskServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, BatchEngineImportTaskService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>BatchEngineImportTaskService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>BatchEngineImportTaskServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		BatchEngineImportTaskServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			BatchEngineImportTaskService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		batchEngineImportTaskService = (BatchEngineImportTaskService)aopProxy;

		BatchEngineImportTaskServiceUtil.setService(
			batchEngineImportTaskService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BatchEngineImportTaskService.class.getName();
	}

	protected Class<?> getModelClass() {
		return BatchEngineImportTask.class;
	}

	protected String getModelClassName() {
		return BatchEngineImportTask.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				batchEngineImportTaskPersistence.getDataSource();

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
	protected com.liferay.batch.engine.service.BatchEngineImportTaskLocalService
		batchEngineImportTaskLocalService;

	protected BatchEngineImportTaskService batchEngineImportTaskService;

	@Reference
	protected BatchEngineImportTaskPersistence batchEngineImportTaskPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BatchEngineImportTaskServiceBaseImpl.class);

}