/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.fido2.credential.service.base;

import com.liferay.multi.factor.authentication.fido2.credential.model.MFAFIDO2CredentialEntry;
import com.liferay.multi.factor.authentication.fido2.credential.service.MFAFIDO2CredentialEntryLocalService;
import com.liferay.multi.factor.authentication.fido2.credential.service.MFAFIDO2CredentialEntryLocalServiceUtil;
import com.liferay.multi.factor.authentication.fido2.credential.service.persistence.MFAFIDO2CredentialEntryPersistence;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the mfafido2 credential entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.multi.factor.authentication.fido2.credential.service.impl.MFAFIDO2CredentialEntryLocalServiceImpl}.
 * </p>
 *
 * @author Arthur Chan
 * @see com.liferay.multi.factor.authentication.fido2.credential.service.impl.MFAFIDO2CredentialEntryLocalServiceImpl
 * @generated
 */
public abstract class MFAFIDO2CredentialEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   MFAFIDO2CredentialEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MFAFIDO2CredentialEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MFAFIDO2CredentialEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the mfafido2 credential entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAFIDO2CredentialEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaFIDO2CredentialEntry the mfafido2 credential entry
	 * @return the mfafido2 credential entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MFAFIDO2CredentialEntry addMFAFIDO2CredentialEntry(
		MFAFIDO2CredentialEntry mfaFIDO2CredentialEntry) {

		mfaFIDO2CredentialEntry.setNew(true);

		return mfaFIDO2CredentialEntryPersistence.update(
			mfaFIDO2CredentialEntry);
	}

	/**
	 * Creates a new mfafido2 credential entry with the primary key. Does not add the mfafido2 credential entry to the database.
	 *
	 * @param mfaFIDO2CredentialEntryId the primary key for the new mfafido2 credential entry
	 * @return the new mfafido2 credential entry
	 */
	@Override
	@Transactional(enabled = false)
	public MFAFIDO2CredentialEntry createMFAFIDO2CredentialEntry(
		long mfaFIDO2CredentialEntryId) {

		return mfaFIDO2CredentialEntryPersistence.create(
			mfaFIDO2CredentialEntryId);
	}

	/**
	 * Deletes the mfafido2 credential entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAFIDO2CredentialEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaFIDO2CredentialEntryId the primary key of the mfafido2 credential entry
	 * @return the mfafido2 credential entry that was removed
	 * @throws PortalException if a mfafido2 credential entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MFAFIDO2CredentialEntry deleteMFAFIDO2CredentialEntry(
			long mfaFIDO2CredentialEntryId)
		throws PortalException {

		return mfaFIDO2CredentialEntryPersistence.remove(
			mfaFIDO2CredentialEntryId);
	}

	/**
	 * Deletes the mfafido2 credential entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAFIDO2CredentialEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaFIDO2CredentialEntry the mfafido2 credential entry
	 * @return the mfafido2 credential entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MFAFIDO2CredentialEntry deleteMFAFIDO2CredentialEntry(
		MFAFIDO2CredentialEntry mfaFIDO2CredentialEntry) {

		return mfaFIDO2CredentialEntryPersistence.remove(
			mfaFIDO2CredentialEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return mfaFIDO2CredentialEntryPersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			MFAFIDO2CredentialEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return mfaFIDO2CredentialEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.fido2.credential.model.impl.MFAFIDO2CredentialEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return mfaFIDO2CredentialEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.fido2.credential.model.impl.MFAFIDO2CredentialEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return mfaFIDO2CredentialEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return mfaFIDO2CredentialEntryPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return mfaFIDO2CredentialEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MFAFIDO2CredentialEntry fetchMFAFIDO2CredentialEntry(
		long mfaFIDO2CredentialEntryId) {

		return mfaFIDO2CredentialEntryPersistence.fetchByPrimaryKey(
			mfaFIDO2CredentialEntryId);
	}

	/**
	 * Returns the mfafido2 credential entry with the primary key.
	 *
	 * @param mfaFIDO2CredentialEntryId the primary key of the mfafido2 credential entry
	 * @return the mfafido2 credential entry
	 * @throws PortalException if a mfafido2 credential entry with the primary key could not be found
	 */
	@Override
	public MFAFIDO2CredentialEntry getMFAFIDO2CredentialEntry(
			long mfaFIDO2CredentialEntryId)
		throws PortalException {

		return mfaFIDO2CredentialEntryPersistence.findByPrimaryKey(
			mfaFIDO2CredentialEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			mfaFIDO2CredentialEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MFAFIDO2CredentialEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaFIDO2CredentialEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			mfaFIDO2CredentialEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			MFAFIDO2CredentialEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaFIDO2CredentialEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			mfaFIDO2CredentialEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MFAFIDO2CredentialEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaFIDO2CredentialEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mfaFIDO2CredentialEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement MFAFIDO2CredentialEntryLocalServiceImpl#deleteMFAFIDO2CredentialEntry(MFAFIDO2CredentialEntry) to avoid orphaned data");
		}

		return mfaFIDO2CredentialEntryLocalService.
			deleteMFAFIDO2CredentialEntry(
				(MFAFIDO2CredentialEntry)persistedModel);
	}

	@Override
	public BasePersistence<MFAFIDO2CredentialEntry> getBasePersistence() {
		return mfaFIDO2CredentialEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mfaFIDO2CredentialEntryPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the mfafido2 credential entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.fido2.credential.model.impl.MFAFIDO2CredentialEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfafido2 credential entries
	 * @param end the upper bound of the range of mfafido2 credential entries (not inclusive)
	 * @return the range of mfafido2 credential entries
	 */
	@Override
	public List<MFAFIDO2CredentialEntry> getMFAFIDO2CredentialEntries(
		int start, int end) {

		return mfaFIDO2CredentialEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of mfafido2 credential entries.
	 *
	 * @return the number of mfafido2 credential entries
	 */
	@Override
	public int getMFAFIDO2CredentialEntriesCount() {
		return mfaFIDO2CredentialEntryPersistence.countAll();
	}

	/**
	 * Updates the mfafido2 credential entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAFIDO2CredentialEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaFIDO2CredentialEntry the mfafido2 credential entry
	 * @return the mfafido2 credential entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MFAFIDO2CredentialEntry updateMFAFIDO2CredentialEntry(
		MFAFIDO2CredentialEntry mfaFIDO2CredentialEntry) {

		return mfaFIDO2CredentialEntryPersistence.update(
			mfaFIDO2CredentialEntry);
	}

	@Deactivate
	protected void deactivate() {
		MFAFIDO2CredentialEntryLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MFAFIDO2CredentialEntryLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		mfaFIDO2CredentialEntryLocalService =
			(MFAFIDO2CredentialEntryLocalService)aopProxy;

		MFAFIDO2CredentialEntryLocalServiceUtil.setService(
			mfaFIDO2CredentialEntryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MFAFIDO2CredentialEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MFAFIDO2CredentialEntry.class;
	}

	protected String getModelClassName() {
		return MFAFIDO2CredentialEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				mfaFIDO2CredentialEntryPersistence.getDataSource();

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

	protected MFAFIDO2CredentialEntryLocalService
		mfaFIDO2CredentialEntryLocalService;

	@Reference
	protected MFAFIDO2CredentialEntryPersistence
		mfaFIDO2CredentialEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		MFAFIDO2CredentialEntryLocalServiceBaseImpl.class);

}