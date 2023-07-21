/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry;
import com.liferay.portal.tools.service.builder.test.service.NestedSetsTreeEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.NestedSetsTreeEntryLocalServiceUtil;
import com.liferay.portal.tools.service.builder.test.service.persistence.NestedSetsTreeEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the nested sets tree entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.NestedSetsTreeEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.NestedSetsTreeEntryLocalServiceImpl
 * @generated
 */
public abstract class NestedSetsTreeEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, NestedSetsTreeEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>NestedSetsTreeEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>NestedSetsTreeEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the nested sets tree entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntry the nested sets tree entry
	 * @return the nested sets tree entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NestedSetsTreeEntry addNestedSetsTreeEntry(
		NestedSetsTreeEntry nestedSetsTreeEntry) {

		nestedSetsTreeEntry.setNew(true);

		return nestedSetsTreeEntryPersistence.update(nestedSetsTreeEntry);
	}

	/**
	 * Creates a new nested sets tree entry with the primary key. Does not add the nested sets tree entry to the database.
	 *
	 * @param nestedSetsTreeEntryId the primary key for the new nested sets tree entry
	 * @return the new nested sets tree entry
	 */
	@Override
	@Transactional(enabled = false)
	public NestedSetsTreeEntry createNestedSetsTreeEntry(
		long nestedSetsTreeEntryId) {

		return nestedSetsTreeEntryPersistence.create(nestedSetsTreeEntryId);
	}

	/**
	 * Deletes the nested sets tree entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntryId the primary key of the nested sets tree entry
	 * @return the nested sets tree entry that was removed
	 * @throws PortalException if a nested sets tree entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NestedSetsTreeEntry deleteNestedSetsTreeEntry(
			long nestedSetsTreeEntryId)
		throws PortalException {

		return nestedSetsTreeEntryPersistence.remove(nestedSetsTreeEntryId);
	}

	/**
	 * Deletes the nested sets tree entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntry the nested sets tree entry
	 * @return the nested sets tree entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public NestedSetsTreeEntry deleteNestedSetsTreeEntry(
		NestedSetsTreeEntry nestedSetsTreeEntry) {

		return nestedSetsTreeEntryPersistence.remove(nestedSetsTreeEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return nestedSetsTreeEntryPersistence.dslQuery(dslQuery);
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
			NestedSetsTreeEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return nestedSetsTreeEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.NestedSetsTreeEntryModelImpl</code>.
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

		return nestedSetsTreeEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.NestedSetsTreeEntryModelImpl</code>.
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

		return nestedSetsTreeEntryPersistence.findWithDynamicQuery(
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
		return nestedSetsTreeEntryPersistence.countWithDynamicQuery(
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

		return nestedSetsTreeEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public NestedSetsTreeEntry fetchNestedSetsTreeEntry(
		long nestedSetsTreeEntryId) {

		return nestedSetsTreeEntryPersistence.fetchByPrimaryKey(
			nestedSetsTreeEntryId);
	}

	/**
	 * Returns the nested sets tree entry with the primary key.
	 *
	 * @param nestedSetsTreeEntryId the primary key of the nested sets tree entry
	 * @return the nested sets tree entry
	 * @throws PortalException if a nested sets tree entry with the primary key could not be found
	 */
	@Override
	public NestedSetsTreeEntry getNestedSetsTreeEntry(
			long nestedSetsTreeEntryId)
		throws PortalException {

		return nestedSetsTreeEntryPersistence.findByPrimaryKey(
			nestedSetsTreeEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			nestedSetsTreeEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(NestedSetsTreeEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"nestedSetsTreeEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			nestedSetsTreeEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			NestedSetsTreeEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"nestedSetsTreeEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			nestedSetsTreeEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(NestedSetsTreeEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"nestedSetsTreeEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return nestedSetsTreeEntryPersistence.create(
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
				"Implement NestedSetsTreeEntryLocalServiceImpl#deleteNestedSetsTreeEntry(NestedSetsTreeEntry) to avoid orphaned data");
		}

		return nestedSetsTreeEntryLocalService.deleteNestedSetsTreeEntry(
			(NestedSetsTreeEntry)persistedModel);
	}

	@Override
	public BasePersistence<NestedSetsTreeEntry> getBasePersistence() {
		return nestedSetsTreeEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return nestedSetsTreeEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the nested sets tree entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.NestedSetsTreeEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nested sets tree entries
	 * @param end the upper bound of the range of nested sets tree entries (not inclusive)
	 * @return the range of nested sets tree entries
	 */
	@Override
	public List<NestedSetsTreeEntry> getNestedSetsTreeEntries(
		int start, int end) {

		return nestedSetsTreeEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of nested sets tree entries.
	 *
	 * @return the number of nested sets tree entries
	 */
	@Override
	public int getNestedSetsTreeEntriesCount() {
		return nestedSetsTreeEntryPersistence.countAll();
	}

	/**
	 * Updates the nested sets tree entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NestedSetsTreeEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nestedSetsTreeEntry the nested sets tree entry
	 * @return the nested sets tree entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public NestedSetsTreeEntry updateNestedSetsTreeEntry(
		NestedSetsTreeEntry nestedSetsTreeEntry) {

		return nestedSetsTreeEntryPersistence.update(nestedSetsTreeEntry);
	}

	/**
	 * Returns the nested sets tree entry local service.
	 *
	 * @return the nested sets tree entry local service
	 */
	public NestedSetsTreeEntryLocalService
		getNestedSetsTreeEntryLocalService() {

		return nestedSetsTreeEntryLocalService;
	}

	/**
	 * Sets the nested sets tree entry local service.
	 *
	 * @param nestedSetsTreeEntryLocalService the nested sets tree entry local service
	 */
	public void setNestedSetsTreeEntryLocalService(
		NestedSetsTreeEntryLocalService nestedSetsTreeEntryLocalService) {

		this.nestedSetsTreeEntryLocalService = nestedSetsTreeEntryLocalService;
	}

	/**
	 * Returns the nested sets tree entry persistence.
	 *
	 * @return the nested sets tree entry persistence
	 */
	public NestedSetsTreeEntryPersistence getNestedSetsTreeEntryPersistence() {
		return nestedSetsTreeEntryPersistence;
	}

	/**
	 * Sets the nested sets tree entry persistence.
	 *
	 * @param nestedSetsTreeEntryPersistence the nested sets tree entry persistence
	 */
	public void setNestedSetsTreeEntryPersistence(
		NestedSetsTreeEntryPersistence nestedSetsTreeEntryPersistence) {

		this.nestedSetsTreeEntryPersistence = nestedSetsTreeEntryPersistence;
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
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry",
			nestedSetsTreeEntryLocalService);

		NestedSetsTreeEntryLocalServiceUtil.setService(
			nestedSetsTreeEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.tools.service.builder.test.model.NestedSetsTreeEntry");

		NestedSetsTreeEntryLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return NestedSetsTreeEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return NestedSetsTreeEntry.class;
	}

	protected String getModelClassName() {
		return NestedSetsTreeEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				nestedSetsTreeEntryPersistence.getDataSource();

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

	@BeanReference(type = NestedSetsTreeEntryLocalService.class)
	protected NestedSetsTreeEntryLocalService nestedSetsTreeEntryLocalService;

	@BeanReference(type = NestedSetsTreeEntryPersistence.class)
	protected NestedSetsTreeEntryPersistence nestedSetsTreeEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		NestedSetsTreeEntryLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}