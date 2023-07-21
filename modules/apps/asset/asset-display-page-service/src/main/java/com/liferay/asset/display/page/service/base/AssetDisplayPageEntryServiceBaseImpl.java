/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.service.base;

import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryService;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryServiceUtil;
import com.liferay.asset.display.page.service.persistence.AssetDisplayPageEntryPersistence;
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
 * Provides the base implementation for the asset display page entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.asset.display.page.service.impl.AssetDisplayPageEntryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.display.page.service.impl.AssetDisplayPageEntryServiceImpl
 * @generated
 */
public abstract class AssetDisplayPageEntryServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, AssetDisplayPageEntryService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetDisplayPageEntryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>AssetDisplayPageEntryServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		AssetDisplayPageEntryServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AssetDisplayPageEntryService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		assetDisplayPageEntryService = (AssetDisplayPageEntryService)aopProxy;

		AssetDisplayPageEntryServiceUtil.setService(
			assetDisplayPageEntryService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetDisplayPageEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetDisplayPageEntry.class;
	}

	protected String getModelClassName() {
		return AssetDisplayPageEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				assetDisplayPageEntryPersistence.getDataSource();

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
	protected
		com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService
			assetDisplayPageEntryLocalService;

	protected AssetDisplayPageEntryService assetDisplayPageEntryService;

	@Reference
	protected AssetDisplayPageEntryPersistence assetDisplayPageEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		AssetDisplayPageEntryServiceBaseImpl.class);

}