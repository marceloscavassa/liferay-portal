/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.example.sample.service.base;

import com.example.sample.model.Foo;
import com.example.sample.service.FooLocalService;
import com.example.sample.service.FooLocalServiceUtil;
import com.example.sample.service.persistence.FooPersistence;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the foo local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.example.sample.service.impl.FooLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.example.sample.service.impl.FooLocalServiceImpl
 * @generated
 */
public abstract class FooLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements FooLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FooLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>FooLocalServiceUtil</code>.
	 */

	/**
	 * Adds the foo to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FooLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param foo the foo
	 * @return the foo that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Foo addFoo(Foo foo) {
		foo.setNew(true);

		return fooPersistence.update(foo);
	}

	/**
	 * Creates a new foo with the primary key. Does not add the foo to the database.
	 *
	 * @param fooId the primary key for the new foo
	 * @return the new foo
	 */
	@Override
	@Transactional(enabled = false)
	public Foo createFoo(long fooId) {
		return fooPersistence.create(fooId);
	}

	/**
	 * Deletes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FooLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo that was removed
	 * @throws PortalException if a foo with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Foo deleteFoo(long fooId) throws PortalException {
		return fooPersistence.remove(fooId);
	}

	/**
	 * Deletes the foo from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FooLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param foo the foo
	 * @return the foo that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Foo deleteFoo(Foo foo) {
		return fooPersistence.remove(foo);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Foo.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return fooPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.example.sample.model.impl.FooModelImpl</code>.
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

		return fooPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.example.sample.model.impl.FooModelImpl</code>.
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

		return fooPersistence.findWithDynamicQuery(
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
		return fooPersistence.countWithDynamicQuery(dynamicQuery);
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

		return fooPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Foo fetchFoo(long fooId) {
		return fooPersistence.fetchByPrimaryKey(fooId);
	}

	/**
	 * Returns the foo matching the UUID and group.
	 *
	 * @param uuid the foo's UUID
	 * @param groupId the primary key of the group
	 * @return the matching foo, or <code>null</code> if a matching foo could not be found
	 */
	@Override
	public Foo fetchFooByUuidAndGroupId(String uuid, long groupId) {
		return fooPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the foo with the primary key.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo
	 * @throws PortalException if a foo with the primary key could not be found
	 */
	@Override
	public Foo getFoo(long fooId) throws PortalException {
		return fooPersistence.findByPrimaryKey(fooId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(fooLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Foo.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fooId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(fooLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Foo.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("fooId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(fooLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Foo.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fooId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Foo>() {

				@Override
				public void performAction(Foo foo) throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, foo);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Foo.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return fooLocalService.deleteFoo((Foo)persistedModel);
	}

	public BasePersistence<Foo> getBasePersistence() {
		return fooPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return fooPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the foos matching the UUID and company.
	 *
	 * @param uuid the UUID of the foos
	 * @param companyId the primary key of the company
	 * @return the matching foos, or an empty list if no matches were found
	 */
	@Override
	public List<Foo> getFoosByUuidAndCompanyId(String uuid, long companyId) {
		return fooPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of foos matching the UUID and company.
	 *
	 * @param uuid the UUID of the foos
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching foos, or an empty list if no matches were found
	 */
	@Override
	public List<Foo> getFoosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Foo> orderByComparator) {

		return fooPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the foo matching the UUID and group.
	 *
	 * @param uuid the foo's UUID
	 * @param groupId the primary key of the group
	 * @return the matching foo
	 * @throws PortalException if a matching foo could not be found
	 */
	@Override
	public Foo getFooByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return fooPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the foos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.example.sample.model.impl.FooModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of foos
	 */
	@Override
	public List<Foo> getFoos(int start, int end) {
		return fooPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of foos.
	 *
	 * @return the number of foos
	 */
	@Override
	public int getFoosCount() {
		return fooPersistence.countAll();
	}

	/**
	 * Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FooLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param foo the foo
	 * @return the foo that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Foo updateFoo(Foo foo) {
		return fooPersistence.update(foo);
	}

	/**
	 * Returns the foo local service.
	 *
	 * @return the foo local service
	 */
	public FooLocalService getFooLocalService() {
		return fooLocalService;
	}

	/**
	 * Sets the foo local service.
	 *
	 * @param fooLocalService the foo local service
	 */
	public void setFooLocalService(FooLocalService fooLocalService) {
		this.fooLocalService = fooLocalService;
	}

	/**
	 * Returns the foo persistence.
	 *
	 * @return the foo persistence
	 */
	public FooPersistence getFooPersistence() {
		return fooPersistence;
	}

	/**
	 * Sets the foo persistence.
	 *
	 * @param fooPersistence the foo persistence
	 */
	public void setFooPersistence(FooPersistence fooPersistence) {
		this.fooPersistence = fooPersistence;
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

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.example.sample.model.Foo", fooLocalService);

		FooLocalServiceUtil.setService(fooLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.example.sample.model.Foo");

		FooLocalServiceUtil.setService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FooLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Foo.class;
	}

	protected String getModelClassName() {
		return Foo.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = fooPersistence.getDataSource();

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

	@BeanReference(type = FooLocalService.class)
	protected FooLocalService fooLocalService;

	@BeanReference(type = FooPersistence.class)
	protected FooPersistence fooPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		FooLocalServiceBaseImpl.class);

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}