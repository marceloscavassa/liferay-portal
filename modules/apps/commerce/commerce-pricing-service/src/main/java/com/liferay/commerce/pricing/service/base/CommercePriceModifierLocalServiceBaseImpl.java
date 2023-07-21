/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.service.base;

import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.service.CommercePriceModifierLocalService;
import com.liferay.commerce.pricing.service.CommercePriceModifierLocalServiceUtil;
import com.liferay.commerce.pricing.service.persistence.CommercePriceModifierFinder;
import com.liferay.commerce.pricing.service.persistence.CommercePriceModifierPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
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
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the commerce price modifier local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.pricing.service.impl.CommercePriceModifierLocalServiceImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see com.liferay.commerce.pricing.service.impl.CommercePriceModifierLocalServiceImpl
 * @generated
 */
public abstract class CommercePriceModifierLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, CommercePriceModifierLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommercePriceModifierLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommercePriceModifierLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce price modifier to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommercePriceModifierLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commercePriceModifier the commerce price modifier
	 * @return the commerce price modifier that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceModifier addCommercePriceModifier(
		CommercePriceModifier commercePriceModifier) {

		commercePriceModifier.setNew(true);

		return commercePriceModifierPersistence.update(commercePriceModifier);
	}

	/**
	 * Creates a new commerce price modifier with the primary key. Does not add the commerce price modifier to the database.
	 *
	 * @param commercePriceModifierId the primary key for the new commerce price modifier
	 * @return the new commerce price modifier
	 */
	@Override
	@Transactional(enabled = false)
	public CommercePriceModifier createCommercePriceModifier(
		long commercePriceModifierId) {

		return commercePriceModifierPersistence.create(commercePriceModifierId);
	}

	/**
	 * Deletes the commerce price modifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommercePriceModifierLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commercePriceModifierId the primary key of the commerce price modifier
	 * @return the commerce price modifier that was removed
	 * @throws PortalException if a commerce price modifier with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommercePriceModifier deleteCommercePriceModifier(
			long commercePriceModifierId)
		throws PortalException {

		return commercePriceModifierPersistence.remove(commercePriceModifierId);
	}

	/**
	 * Deletes the commerce price modifier from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommercePriceModifierLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commercePriceModifier the commerce price modifier
	 * @return the commerce price modifier that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommercePriceModifier deleteCommercePriceModifier(
			CommercePriceModifier commercePriceModifier)
		throws PortalException {

		return commercePriceModifierPersistence.remove(commercePriceModifier);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commercePriceModifierPersistence.dslQuery(dslQuery);
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
			CommercePriceModifier.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commercePriceModifierPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePriceModifierModelImpl</code>.
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

		return commercePriceModifierPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePriceModifierModelImpl</code>.
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

		return commercePriceModifierPersistence.findWithDynamicQuery(
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
		return commercePriceModifierPersistence.countWithDynamicQuery(
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

		return commercePriceModifierPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommercePriceModifier fetchCommercePriceModifier(
		long commercePriceModifierId) {

		return commercePriceModifierPersistence.fetchByPrimaryKey(
			commercePriceModifierId);
	}

	/**
	 * Returns the commerce price modifier matching the UUID and group.
	 *
	 * @param uuid the commerce price modifier's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce price modifier, or <code>null</code> if a matching commerce price modifier could not be found
	 */
	@Override
	public CommercePriceModifier fetchCommercePriceModifierByUuidAndGroupId(
		String uuid, long groupId) {

		return commercePriceModifierPersistence.fetchByUUID_G(uuid, groupId);
	}

	@Override
	public CommercePriceModifier
		fetchCommercePriceModifierByExternalReferenceCode(
			String externalReferenceCode, long companyId) {

		return commercePriceModifierPersistence.fetchByERC_C(
			externalReferenceCode, companyId);
	}

	@Override
	public CommercePriceModifier
			getCommercePriceModifierByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws PortalException {

		return commercePriceModifierPersistence.findByERC_C(
			externalReferenceCode, companyId);
	}

	/**
	 * Returns the commerce price modifier with the primary key.
	 *
	 * @param commercePriceModifierId the primary key of the commerce price modifier
	 * @return the commerce price modifier
	 * @throws PortalException if a commerce price modifier with the primary key could not be found
	 */
	@Override
	public CommercePriceModifier getCommercePriceModifier(
			long commercePriceModifierId)
		throws PortalException {

		return commercePriceModifierPersistence.findByPrimaryKey(
			commercePriceModifierId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commercePriceModifierLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommercePriceModifier.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commercePriceModifierId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commercePriceModifierLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommercePriceModifier.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commercePriceModifierId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commercePriceModifierLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommercePriceModifier.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commercePriceModifierId");
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
					Criterion modifiedDateCriterion =
						portletDataContext.getDateRangeCriteria("modifiedDate");

					if (modifiedDateCriterion != null) {
						Conjunction conjunction =
							RestrictionsFactoryUtil.conjunction();

						conjunction.add(modifiedDateCriterion);

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(
							RestrictionsFactoryUtil.gtProperty(
								"modifiedDate", "lastPublishDate"));

						Property lastPublishDateProperty =
							PropertyFactoryUtil.forName("lastPublishDate");

						disjunction.add(lastPublishDateProperty.isNull());

						conjunction.add(disjunction);

						modifiedDateCriterion = conjunction;
					}

					Criterion statusDateCriterion =
						portletDataContext.getDateRangeCriteria("statusDate");

					if ((modifiedDateCriterion != null) &&
						(statusDateCriterion != null)) {

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(modifiedDateCriterion);
						disjunction.add(statusDateCriterion);

						dynamicQuery.add(disjunction);
					}

					Property workflowStatusProperty =
						PropertyFactoryUtil.forName("status");

					if (portletDataContext.isInitialPublication()) {
						dynamicQuery.add(
							workflowStatusProperty.ne(
								WorkflowConstants.STATUS_IN_TRASH));
					}
					else {
						StagedModelDataHandler<?> stagedModelDataHandler =
							StagedModelDataHandlerRegistryUtil.
								getStagedModelDataHandler(
									CommercePriceModifier.class.getName());

						dynamicQuery.add(
							workflowStatusProperty.in(
								stagedModelDataHandler.
									getExportableStatuses()));
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommercePriceModifier>() {

				@Override
				public void performAction(
						CommercePriceModifier commercePriceModifier)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, commercePriceModifier);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CommercePriceModifier.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commercePriceModifierPersistence.create(
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
				"Implement CommercePriceModifierLocalServiceImpl#deleteCommercePriceModifier(CommercePriceModifier) to avoid orphaned data");
		}

		return commercePriceModifierLocalService.deleteCommercePriceModifier(
			(CommercePriceModifier)persistedModel);
	}

	@Override
	public BasePersistence<CommercePriceModifier> getBasePersistence() {
		return commercePriceModifierPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commercePriceModifierPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the commerce price modifiers matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce price modifiers
	 * @param companyId the primary key of the company
	 * @return the matching commerce price modifiers, or an empty list if no matches were found
	 */
	@Override
	public List<CommercePriceModifier>
		getCommercePriceModifiersByUuidAndCompanyId(
			String uuid, long companyId) {

		return commercePriceModifierPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of commerce price modifiers matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce price modifiers
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce price modifiers, or an empty list if no matches were found
	 */
	@Override
	public List<CommercePriceModifier>
		getCommercePriceModifiersByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CommercePriceModifier> orderByComparator) {

		return commercePriceModifierPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the commerce price modifier matching the UUID and group.
	 *
	 * @param uuid the commerce price modifier's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce price modifier
	 * @throws PortalException if a matching commerce price modifier could not be found
	 */
	@Override
	public CommercePriceModifier getCommercePriceModifierByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return commercePriceModifierPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce price modifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePriceModifierModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price modifiers
	 * @param end the upper bound of the range of commerce price modifiers (not inclusive)
	 * @return the range of commerce price modifiers
	 */
	@Override
	public List<CommercePriceModifier> getCommercePriceModifiers(
		int start, int end) {

		return commercePriceModifierPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce price modifiers.
	 *
	 * @return the number of commerce price modifiers
	 */
	@Override
	public int getCommercePriceModifiersCount() {
		return commercePriceModifierPersistence.countAll();
	}

	/**
	 * Updates the commerce price modifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommercePriceModifierLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commercePriceModifier the commerce price modifier
	 * @return the commerce price modifier that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommercePriceModifier updateCommercePriceModifier(
		CommercePriceModifier commercePriceModifier) {

		return commercePriceModifierPersistence.update(commercePriceModifier);
	}

	@Deactivate
	protected void deactivate() {
		CommercePriceModifierLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CommercePriceModifierLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		commercePriceModifierLocalService =
			(CommercePriceModifierLocalService)aopProxy;

		CommercePriceModifierLocalServiceUtil.setService(
			commercePriceModifierLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommercePriceModifierLocalService.class.getName();
	}

	@Override
	public CTPersistence<CommercePriceModifier> getCTPersistence() {
		return commercePriceModifierPersistence;
	}

	@Override
	public Class<CommercePriceModifier> getModelClass() {
		return CommercePriceModifier.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<CommercePriceModifier>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(commercePriceModifierPersistence);
	}

	protected String getModelClassName() {
		return CommercePriceModifier.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commercePriceModifierPersistence.getDataSource();

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

	protected CommercePriceModifierLocalService
		commercePriceModifierLocalService;

	@Reference
	protected CommercePriceModifierPersistence commercePriceModifierPersistence;

	@Reference
	protected CommercePriceModifierFinder commercePriceModifierFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceModifierLocalServiceBaseImpl.class);

}