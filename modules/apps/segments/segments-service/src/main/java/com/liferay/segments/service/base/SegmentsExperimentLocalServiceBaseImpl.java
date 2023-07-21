/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.service.SegmentsExperimentLocalService;
import com.liferay.segments.service.SegmentsExperimentLocalServiceUtil;
import com.liferay.segments.service.persistence.SegmentsExperimentPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the segments experiment local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.segments.service.impl.SegmentsExperimentLocalServiceImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see com.liferay.segments.service.impl.SegmentsExperimentLocalServiceImpl
 * @generated
 */
public abstract class SegmentsExperimentLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   SegmentsExperimentLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SegmentsExperimentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SegmentsExperimentLocalServiceUtil</code>.
	 */

	/**
	 * Adds the segments experiment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SegmentsExperimentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param segmentsExperiment the segments experiment
	 * @return the segments experiment that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SegmentsExperiment addSegmentsExperiment(
		SegmentsExperiment segmentsExperiment) {

		segmentsExperiment.setNew(true);

		return segmentsExperimentPersistence.update(segmentsExperiment);
	}

	/**
	 * Creates a new segments experiment with the primary key. Does not add the segments experiment to the database.
	 *
	 * @param segmentsExperimentId the primary key for the new segments experiment
	 * @return the new segments experiment
	 */
	@Override
	@Transactional(enabled = false)
	public SegmentsExperiment createSegmentsExperiment(
		long segmentsExperimentId) {

		return segmentsExperimentPersistence.create(segmentsExperimentId);
	}

	/**
	 * Deletes the segments experiment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SegmentsExperimentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param segmentsExperimentId the primary key of the segments experiment
	 * @return the segments experiment that was removed
	 * @throws PortalException if a segments experiment with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SegmentsExperiment deleteSegmentsExperiment(
			long segmentsExperimentId)
		throws PortalException {

		return segmentsExperimentPersistence.remove(segmentsExperimentId);
	}

	/**
	 * Deletes the segments experiment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SegmentsExperimentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param segmentsExperiment the segments experiment
	 * @return the segments experiment that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SegmentsExperiment deleteSegmentsExperiment(
			SegmentsExperiment segmentsExperiment)
		throws PortalException {

		return segmentsExperimentPersistence.remove(segmentsExperiment);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return segmentsExperimentPersistence.dslQuery(dslQuery);
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
			SegmentsExperiment.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return segmentsExperimentPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.segments.model.impl.SegmentsExperimentModelImpl</code>.
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

		return segmentsExperimentPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.segments.model.impl.SegmentsExperimentModelImpl</code>.
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

		return segmentsExperimentPersistence.findWithDynamicQuery(
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
		return segmentsExperimentPersistence.countWithDynamicQuery(
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

		return segmentsExperimentPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SegmentsExperiment fetchSegmentsExperiment(
		long segmentsExperimentId) {

		return segmentsExperimentPersistence.fetchByPrimaryKey(
			segmentsExperimentId);
	}

	/**
	 * Returns the segments experiment matching the UUID and group.
	 *
	 * @param uuid the segments experiment's UUID
	 * @param groupId the primary key of the group
	 * @return the matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchSegmentsExperimentByUuidAndGroupId(
		String uuid, long groupId) {

		return segmentsExperimentPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the segments experiment with the primary key.
	 *
	 * @param segmentsExperimentId the primary key of the segments experiment
	 * @return the segments experiment
	 * @throws PortalException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment getSegmentsExperiment(long segmentsExperimentId)
		throws PortalException {

		return segmentsExperimentPersistence.findByPrimaryKey(
			segmentsExperimentId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			segmentsExperimentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SegmentsExperiment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"segmentsExperimentId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			segmentsExperimentLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SegmentsExperiment.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"segmentsExperimentId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			segmentsExperimentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SegmentsExperiment.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"segmentsExperimentId");
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
			new ActionableDynamicQuery.PerformActionMethod
				<SegmentsExperiment>() {

				@Override
				public void performAction(SegmentsExperiment segmentsExperiment)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, segmentsExperiment);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(SegmentsExperiment.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return segmentsExperimentPersistence.create(
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
				"Implement SegmentsExperimentLocalServiceImpl#deleteSegmentsExperiment(SegmentsExperiment) to avoid orphaned data");
		}

		return segmentsExperimentLocalService.deleteSegmentsExperiment(
			(SegmentsExperiment)persistedModel);
	}

	@Override
	public BasePersistence<SegmentsExperiment> getBasePersistence() {
		return segmentsExperimentPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return segmentsExperimentPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the segments experiments matching the UUID and company.
	 *
	 * @param uuid the UUID of the segments experiments
	 * @param companyId the primary key of the company
	 * @return the matching segments experiments, or an empty list if no matches were found
	 */
	@Override
	public List<SegmentsExperiment> getSegmentsExperimentsByUuidAndCompanyId(
		String uuid, long companyId) {

		return segmentsExperimentPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of segments experiments matching the UUID and company.
	 *
	 * @param uuid the UUID of the segments experiments
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching segments experiments, or an empty list if no matches were found
	 */
	@Override
	public List<SegmentsExperiment> getSegmentsExperimentsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		return segmentsExperimentPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiment matching the UUID and group.
	 *
	 * @param uuid the segments experiment's UUID
	 * @param groupId the primary key of the group
	 * @return the matching segments experiment
	 * @throws PortalException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment getSegmentsExperimentByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return segmentsExperimentPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the segments experiments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.segments.model.impl.SegmentsExperimentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @return the range of segments experiments
	 */
	@Override
	public List<SegmentsExperiment> getSegmentsExperiments(int start, int end) {
		return segmentsExperimentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of segments experiments.
	 *
	 * @return the number of segments experiments
	 */
	@Override
	public int getSegmentsExperimentsCount() {
		return segmentsExperimentPersistence.countAll();
	}

	/**
	 * Updates the segments experiment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SegmentsExperimentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param segmentsExperiment the segments experiment
	 * @return the segments experiment that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SegmentsExperiment updateSegmentsExperiment(
		SegmentsExperiment segmentsExperiment) {

		return segmentsExperimentPersistence.update(segmentsExperiment);
	}

	@Deactivate
	protected void deactivate() {
		SegmentsExperimentLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SegmentsExperimentLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		segmentsExperimentLocalService =
			(SegmentsExperimentLocalService)aopProxy;

		SegmentsExperimentLocalServiceUtil.setService(
			segmentsExperimentLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SegmentsExperimentLocalService.class.getName();
	}

	@Override
	public CTPersistence<SegmentsExperiment> getCTPersistence() {
		return segmentsExperimentPersistence;
	}

	@Override
	public Class<SegmentsExperiment> getModelClass() {
		return SegmentsExperiment.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<SegmentsExperiment>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(segmentsExperimentPersistence);
	}

	protected String getModelClassName() {
		return SegmentsExperiment.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				segmentsExperimentPersistence.getDataSource();

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

	protected SegmentsExperimentLocalService segmentsExperimentLocalService;

	@Reference
	protected SegmentsExperimentPersistence segmentsExperimentPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsExperimentLocalServiceBaseImpl.class);

}