/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.service.base;

import com.liferay.petra.function.UnsafeFunction;
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
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the kaleo task assignment local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentLocalServiceImpl
 * @generated
 */
public abstract class KaleoTaskAssignmentLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   KaleoTaskAssignmentLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>KaleoTaskAssignmentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>KaleoTaskAssignmentLocalServiceUtil</code>.
	 */

	/**
	 * Adds the kaleo task assignment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoTaskAssignment addKaleoTaskAssignment(
		KaleoTaskAssignment kaleoTaskAssignment) {

		kaleoTaskAssignment.setNew(true);

		return kaleoTaskAssignmentPersistence.update(kaleoTaskAssignment);
	}

	/**
	 * Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	 *
	 * @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	 * @return the new kaleo task assignment
	 */
	@Override
	@Transactional(enabled = false)
	public KaleoTaskAssignment createKaleoTaskAssignment(
		long kaleoTaskAssignmentId) {

		return kaleoTaskAssignmentPersistence.create(kaleoTaskAssignmentId);
	}

	/**
	 * Deletes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws PortalException if a kaleo task assignment with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoTaskAssignment deleteKaleoTaskAssignment(
			long kaleoTaskAssignmentId)
		throws PortalException {

		return kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignmentId);
	}

	/**
	 * Deletes the kaleo task assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoTaskAssignment deleteKaleoTaskAssignment(
		KaleoTaskAssignment kaleoTaskAssignment) {

		return kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return kaleoTaskAssignmentPersistence.dslQuery(dslQuery);
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
			KaleoTaskAssignment.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return kaleoTaskAssignmentPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl</code>.
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

		return kaleoTaskAssignmentPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl</code>.
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

		return kaleoTaskAssignmentPersistence.findWithDynamicQuery(
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
		return kaleoTaskAssignmentPersistence.countWithDynamicQuery(
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

		return kaleoTaskAssignmentPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public KaleoTaskAssignment fetchKaleoTaskAssignment(
		long kaleoTaskAssignmentId) {

		return kaleoTaskAssignmentPersistence.fetchByPrimaryKey(
			kaleoTaskAssignmentId);
	}

	/**
	 * Returns the kaleo task assignment with the primary key.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment
	 * @throws PortalException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment getKaleoTaskAssignment(
			long kaleoTaskAssignmentId)
		throws PortalException {

		return kaleoTaskAssignmentPersistence.findByPrimaryKey(
			kaleoTaskAssignmentId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			kaleoTaskAssignmentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KaleoTaskAssignment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"kaleoTaskAssignmentId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			kaleoTaskAssignmentLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			KaleoTaskAssignment.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"kaleoTaskAssignmentId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			kaleoTaskAssignmentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(KaleoTaskAssignment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"kaleoTaskAssignmentId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return kaleoTaskAssignmentPersistence.create(
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
				"Implement KaleoTaskAssignmentLocalServiceImpl#deleteKaleoTaskAssignment(KaleoTaskAssignment) to avoid orphaned data");
		}

		return kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(
			(KaleoTaskAssignment)persistedModel);
	}

	@Override
	public BasePersistence<KaleoTaskAssignment> getBasePersistence() {
		return kaleoTaskAssignmentPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return kaleoTaskAssignmentPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the kaleo task assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> getKaleoTaskAssignments(
		int start, int end) {

		return kaleoTaskAssignmentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of kaleo task assignments.
	 *
	 * @return the number of kaleo task assignments
	 */
	@Override
	public int getKaleoTaskAssignmentsCount() {
		return kaleoTaskAssignmentPersistence.countAll();
	}

	/**
	 * Updates the kaleo task assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoTaskAssignment updateKaleoTaskAssignment(
		KaleoTaskAssignment kaleoTaskAssignment) {

		return kaleoTaskAssignmentPersistence.update(kaleoTaskAssignment);
	}

	@Deactivate
	protected void deactivate() {
		KaleoTaskAssignmentLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			KaleoTaskAssignmentLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		kaleoTaskAssignmentLocalService =
			(KaleoTaskAssignmentLocalService)aopProxy;

		KaleoTaskAssignmentLocalServiceUtil.setService(
			kaleoTaskAssignmentLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return KaleoTaskAssignmentLocalService.class.getName();
	}

	@Override
	public CTPersistence<KaleoTaskAssignment> getCTPersistence() {
		return kaleoTaskAssignmentPersistence;
	}

	@Override
	public Class<KaleoTaskAssignment> getModelClass() {
		return KaleoTaskAssignment.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<KaleoTaskAssignment>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(kaleoTaskAssignmentPersistence);
	}

	protected String getModelClassName() {
		return KaleoTaskAssignment.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				kaleoTaskAssignmentPersistence.getDataSource();

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

	protected KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService;

	@Reference
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoTaskAssignmentLocalServiceBaseImpl.class);

}