/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.service.base;

import com.liferay.data.engine.model.DEDataDefinitionFieldLink;
import com.liferay.data.engine.service.DEDataDefinitionFieldLinkLocalService;
import com.liferay.data.engine.service.DEDataDefinitionFieldLinkLocalServiceUtil;
import com.liferay.data.engine.service.persistence.DEDataDefinitionFieldLinkPersistence;
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the de data definition field link local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.data.engine.service.impl.DEDataDefinitionFieldLinkLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.engine.service.impl.DEDataDefinitionFieldLinkLocalServiceImpl
 * @generated
 */
public abstract class DEDataDefinitionFieldLinkLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, DEDataDefinitionFieldLinkLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DEDataDefinitionFieldLinkLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>DEDataDefinitionFieldLinkLocalServiceUtil</code>.
	 */

	/**
	 * Adds the de data definition field link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DEDataDefinitionFieldLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deDataDefinitionFieldLink the de data definition field link
	 * @return the de data definition field link that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DEDataDefinitionFieldLink addDEDataDefinitionFieldLink(
		DEDataDefinitionFieldLink deDataDefinitionFieldLink) {

		deDataDefinitionFieldLink.setNew(true);

		return deDataDefinitionFieldLinkPersistence.update(
			deDataDefinitionFieldLink);
	}

	/**
	 * Creates a new de data definition field link with the primary key. Does not add the de data definition field link to the database.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key for the new de data definition field link
	 * @return the new de data definition field link
	 */
	@Override
	@Transactional(enabled = false)
	public DEDataDefinitionFieldLink createDEDataDefinitionFieldLink(
		long deDataDefinitionFieldLinkId) {

		return deDataDefinitionFieldLinkPersistence.create(
			deDataDefinitionFieldLinkId);
	}

	/**
	 * Deletes the de data definition field link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DEDataDefinitionFieldLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the de data definition field link
	 * @return the de data definition field link that was removed
	 * @throws PortalException if a de data definition field link with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DEDataDefinitionFieldLink deleteDEDataDefinitionFieldLink(
			long deDataDefinitionFieldLinkId)
		throws PortalException {

		return deDataDefinitionFieldLinkPersistence.remove(
			deDataDefinitionFieldLinkId);
	}

	/**
	 * Deletes the de data definition field link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DEDataDefinitionFieldLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deDataDefinitionFieldLink the de data definition field link
	 * @return the de data definition field link that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DEDataDefinitionFieldLink deleteDEDataDefinitionFieldLink(
		DEDataDefinitionFieldLink deDataDefinitionFieldLink) {

		return deDataDefinitionFieldLinkPersistence.remove(
			deDataDefinitionFieldLink);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return deDataDefinitionFieldLinkPersistence.dslQuery(dslQuery);
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
			DEDataDefinitionFieldLink.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return deDataDefinitionFieldLinkPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.data.engine.model.impl.DEDataDefinitionFieldLinkModelImpl</code>.
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

		return deDataDefinitionFieldLinkPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.data.engine.model.impl.DEDataDefinitionFieldLinkModelImpl</code>.
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

		return deDataDefinitionFieldLinkPersistence.findWithDynamicQuery(
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
		return deDataDefinitionFieldLinkPersistence.countWithDynamicQuery(
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

		return deDataDefinitionFieldLinkPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DEDataDefinitionFieldLink fetchDEDataDefinitionFieldLink(
		long deDataDefinitionFieldLinkId) {

		return deDataDefinitionFieldLinkPersistence.fetchByPrimaryKey(
			deDataDefinitionFieldLinkId);
	}

	/**
	 * Returns the de data definition field link matching the UUID and group.
	 *
	 * @param uuid the de data definition field link's UUID
	 * @param groupId the primary key of the group
	 * @return the matching de data definition field link, or <code>null</code> if a matching de data definition field link could not be found
	 */
	@Override
	public DEDataDefinitionFieldLink
		fetchDEDataDefinitionFieldLinkByUuidAndGroupId(
			String uuid, long groupId) {

		return deDataDefinitionFieldLinkPersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the de data definition field link with the primary key.
	 *
	 * @param deDataDefinitionFieldLinkId the primary key of the de data definition field link
	 * @return the de data definition field link
	 * @throws PortalException if a de data definition field link with the primary key could not be found
	 */
	@Override
	public DEDataDefinitionFieldLink getDEDataDefinitionFieldLink(
			long deDataDefinitionFieldLinkId)
		throws PortalException {

		return deDataDefinitionFieldLinkPersistence.findByPrimaryKey(
			deDataDefinitionFieldLinkId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			deDataDefinitionFieldLinkLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DEDataDefinitionFieldLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"deDataDefinitionFieldLinkId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			deDataDefinitionFieldLinkLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			DEDataDefinitionFieldLink.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"deDataDefinitionFieldLinkId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			deDataDefinitionFieldLinkLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DEDataDefinitionFieldLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"deDataDefinitionFieldLinkId");
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

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<DEDataDefinitionFieldLink>() {

				@Override
				public void performAction(
						DEDataDefinitionFieldLink deDataDefinitionFieldLink)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, deDataDefinitionFieldLink);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					DEDataDefinitionFieldLink.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return deDataDefinitionFieldLinkPersistence.create(
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
				"Implement DEDataDefinitionFieldLinkLocalServiceImpl#deleteDEDataDefinitionFieldLink(DEDataDefinitionFieldLink) to avoid orphaned data");
		}

		return deDataDefinitionFieldLinkLocalService.
			deleteDEDataDefinitionFieldLink(
				(DEDataDefinitionFieldLink)persistedModel);
	}

	@Override
	public BasePersistence<DEDataDefinitionFieldLink> getBasePersistence() {
		return deDataDefinitionFieldLinkPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return deDataDefinitionFieldLinkPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the de data definition field links matching the UUID and company.
	 *
	 * @param uuid the UUID of the de data definition field links
	 * @param companyId the primary key of the company
	 * @return the matching de data definition field links, or an empty list if no matches were found
	 */
	@Override
	public List<DEDataDefinitionFieldLink>
		getDEDataDefinitionFieldLinksByUuidAndCompanyId(
			String uuid, long companyId) {

		return deDataDefinitionFieldLinkPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of de data definition field links matching the UUID and company.
	 *
	 * @param uuid the UUID of the de data definition field links
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching de data definition field links, or an empty list if no matches were found
	 */
	@Override
	public List<DEDataDefinitionFieldLink>
		getDEDataDefinitionFieldLinksByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<DEDataDefinitionFieldLink> orderByComparator) {

		return deDataDefinitionFieldLinkPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the de data definition field link matching the UUID and group.
	 *
	 * @param uuid the de data definition field link's UUID
	 * @param groupId the primary key of the group
	 * @return the matching de data definition field link
	 * @throws PortalException if a matching de data definition field link could not be found
	 */
	@Override
	public DEDataDefinitionFieldLink
			getDEDataDefinitionFieldLinkByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return deDataDefinitionFieldLinkPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the de data definition field links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.data.engine.model.impl.DEDataDefinitionFieldLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of de data definition field links
	 * @param end the upper bound of the range of de data definition field links (not inclusive)
	 * @return the range of de data definition field links
	 */
	@Override
	public List<DEDataDefinitionFieldLink> getDEDataDefinitionFieldLinks(
		int start, int end) {

		return deDataDefinitionFieldLinkPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of de data definition field links.
	 *
	 * @return the number of de data definition field links
	 */
	@Override
	public int getDEDataDefinitionFieldLinksCount() {
		return deDataDefinitionFieldLinkPersistence.countAll();
	}

	/**
	 * Updates the de data definition field link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DEDataDefinitionFieldLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deDataDefinitionFieldLink the de data definition field link
	 * @return the de data definition field link that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DEDataDefinitionFieldLink updateDEDataDefinitionFieldLink(
		DEDataDefinitionFieldLink deDataDefinitionFieldLink) {

		return deDataDefinitionFieldLinkPersistence.update(
			deDataDefinitionFieldLink);
	}

	@Deactivate
	protected void deactivate() {
		DEDataDefinitionFieldLinkLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DEDataDefinitionFieldLinkLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		deDataDefinitionFieldLinkLocalService =
			(DEDataDefinitionFieldLinkLocalService)aopProxy;

		DEDataDefinitionFieldLinkLocalServiceUtil.setService(
			deDataDefinitionFieldLinkLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DEDataDefinitionFieldLinkLocalService.class.getName();
	}

	@Override
	public CTPersistence<DEDataDefinitionFieldLink> getCTPersistence() {
		return deDataDefinitionFieldLinkPersistence;
	}

	@Override
	public Class<DEDataDefinitionFieldLink> getModelClass() {
		return DEDataDefinitionFieldLink.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DEDataDefinitionFieldLink>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(deDataDefinitionFieldLinkPersistence);
	}

	protected String getModelClassName() {
		return DEDataDefinitionFieldLink.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				deDataDefinitionFieldLinkPersistence.getDataSource();

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

	protected DEDataDefinitionFieldLinkLocalService
		deDataDefinitionFieldLinkLocalService;

	@Reference
	protected DEDataDefinitionFieldLinkPersistence
		deDataDefinitionFieldLinkPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		DEDataDefinitionFieldLinkLocalServiceBaseImpl.class);

}