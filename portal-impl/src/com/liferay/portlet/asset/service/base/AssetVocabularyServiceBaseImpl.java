/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.service.base;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyService;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetVocabularyFinder;
import com.liferay.asset.kernel.service.persistence.AssetVocabularyPersistence;
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

import javax.sql.DataSource;

/**
 * Provides the base implementation for the asset vocabulary remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.asset.service.impl.AssetVocabularyServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.asset.service.impl.AssetVocabularyServiceImpl
 * @generated
 */
public abstract class AssetVocabularyServiceBaseImpl
	extends BaseServiceImpl
	implements AssetVocabularyService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetVocabularyService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>AssetVocabularyServiceUtil</code>.
	 */

	/**
	 * Returns the asset vocabulary local service.
	 *
	 * @return the asset vocabulary local service
	 */
	public com.liferay.asset.kernel.service.AssetVocabularyLocalService
		getAssetVocabularyLocalService() {

		return assetVocabularyLocalService;
	}

	/**
	 * Sets the asset vocabulary local service.
	 *
	 * @param assetVocabularyLocalService the asset vocabulary local service
	 */
	public void setAssetVocabularyLocalService(
		com.liferay.asset.kernel.service.AssetVocabularyLocalService
			assetVocabularyLocalService) {

		this.assetVocabularyLocalService = assetVocabularyLocalService;
	}

	/**
	 * Returns the asset vocabulary remote service.
	 *
	 * @return the asset vocabulary remote service
	 */
	public AssetVocabularyService getAssetVocabularyService() {
		return assetVocabularyService;
	}

	/**
	 * Sets the asset vocabulary remote service.
	 *
	 * @param assetVocabularyService the asset vocabulary remote service
	 */
	public void setAssetVocabularyService(
		AssetVocabularyService assetVocabularyService) {

		this.assetVocabularyService = assetVocabularyService;
	}

	/**
	 * Returns the asset vocabulary persistence.
	 *
	 * @return the asset vocabulary persistence
	 */
	public AssetVocabularyPersistence getAssetVocabularyPersistence() {
		return assetVocabularyPersistence;
	}

	/**
	 * Sets the asset vocabulary persistence.
	 *
	 * @param assetVocabularyPersistence the asset vocabulary persistence
	 */
	public void setAssetVocabularyPersistence(
		AssetVocabularyPersistence assetVocabularyPersistence) {

		this.assetVocabularyPersistence = assetVocabularyPersistence;
	}

	/**
	 * Returns the asset vocabulary finder.
	 *
	 * @return the asset vocabulary finder
	 */
	public AssetVocabularyFinder getAssetVocabularyFinder() {
		return assetVocabularyFinder;
	}

	/**
	 * Sets the asset vocabulary finder.
	 *
	 * @param assetVocabularyFinder the asset vocabulary finder
	 */
	public void setAssetVocabularyFinder(
		AssetVocabularyFinder assetVocabularyFinder) {

		this.assetVocabularyFinder = assetVocabularyFinder;
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
		AssetVocabularyServiceUtil.setService(assetVocabularyService);
	}

	public void destroy() {
		AssetVocabularyServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetVocabularyService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetVocabulary.class;
	}

	protected String getModelClassName() {
		return AssetVocabulary.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = assetVocabularyPersistence.getDataSource();

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
		type = com.liferay.asset.kernel.service.AssetVocabularyLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetVocabularyLocalService
		assetVocabularyLocalService;

	@BeanReference(type = AssetVocabularyService.class)
	protected AssetVocabularyService assetVocabularyService;

	@BeanReference(type = AssetVocabularyPersistence.class)
	protected AssetVocabularyPersistence assetVocabularyPersistence;

	@BeanReference(type = AssetVocabularyFinder.class)
	protected AssetVocabularyFinder assetVocabularyFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		AssetVocabularyServiceBaseImpl.class);

}