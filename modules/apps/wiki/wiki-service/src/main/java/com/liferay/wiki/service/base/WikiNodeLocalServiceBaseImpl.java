/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.service.base;

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
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.WikiNodeLocalService;
import com.liferay.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.wiki.service.persistence.WikiNodePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the wiki node local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.wiki.service.impl.WikiNodeLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wiki.service.impl.WikiNodeLocalServiceImpl
 * @generated
 */
public abstract class WikiNodeLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, WikiNodeLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WikiNodeLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>WikiNodeLocalServiceUtil</code>.
	 */

	/**
	 * Adds the wiki node to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WikiNodeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wikiNode the wiki node
	 * @return the wiki node that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WikiNode addWikiNode(WikiNode wikiNode) {
		wikiNode.setNew(true);

		return wikiNodePersistence.update(wikiNode);
	}

	/**
	 * Creates a new wiki node with the primary key. Does not add the wiki node to the database.
	 *
	 * @param nodeId the primary key for the new wiki node
	 * @return the new wiki node
	 */
	@Override
	@Transactional(enabled = false)
	public WikiNode createWikiNode(long nodeId) {
		return wikiNodePersistence.create(nodeId);
	}

	/**
	 * Deletes the wiki node with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WikiNodeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param nodeId the primary key of the wiki node
	 * @return the wiki node that was removed
	 * @throws PortalException if a wiki node with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WikiNode deleteWikiNode(long nodeId) throws PortalException {
		return wikiNodePersistence.remove(nodeId);
	}

	/**
	 * Deletes the wiki node from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WikiNodeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wikiNode the wiki node
	 * @return the wiki node that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WikiNode deleteWikiNode(WikiNode wikiNode) {
		return wikiNodePersistence.remove(wikiNode);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return wikiNodePersistence.dslQuery(dslQuery);
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
			WikiNode.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return wikiNodePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wiki.model.impl.WikiNodeModelImpl</code>.
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

		return wikiNodePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wiki.model.impl.WikiNodeModelImpl</code>.
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

		return wikiNodePersistence.findWithDynamicQuery(
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
		return wikiNodePersistence.countWithDynamicQuery(dynamicQuery);
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

		return wikiNodePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public WikiNode fetchWikiNode(long nodeId) {
		return wikiNodePersistence.fetchByPrimaryKey(nodeId);
	}

	/**
	 * Returns the wiki node matching the UUID and group.
	 *
	 * @param uuid the wiki node's UUID
	 * @param groupId the primary key of the group
	 * @return the matching wiki node, or <code>null</code> if a matching wiki node could not be found
	 */
	@Override
	public WikiNode fetchWikiNodeByUuidAndGroupId(String uuid, long groupId) {
		return wikiNodePersistence.fetchByUUID_G(uuid, groupId);
	}

	@Override
	public WikiNode fetchWikiNodeByExternalReferenceCode(
		String externalReferenceCode, long groupId) {

		return wikiNodePersistence.fetchByERC_G(externalReferenceCode, groupId);
	}

	@Override
	public WikiNode getWikiNodeByExternalReferenceCode(
			String externalReferenceCode, long groupId)
		throws PortalException {

		return wikiNodePersistence.findByERC_G(externalReferenceCode, groupId);
	}

	/**
	 * Returns the wiki node with the primary key.
	 *
	 * @param nodeId the primary key of the wiki node
	 * @return the wiki node
	 * @throws PortalException if a wiki node with the primary key could not be found
	 */
	@Override
	public WikiNode getWikiNode(long nodeId) throws PortalException {
		return wikiNodePersistence.findByPrimaryKey(nodeId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(wikiNodeLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WikiNode.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("nodeId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			wikiNodeLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(WikiNode.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("nodeId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(wikiNodeLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WikiNode.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("nodeId");
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
									WikiNode.class.getName());

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
			new ActionableDynamicQuery.PerformActionMethod<WikiNode>() {

				@Override
				public void performAction(WikiNode wikiNode)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, wikiNode);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(WikiNode.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return wikiNodePersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Implement WikiNodeLocalServiceImpl#deleteWikiNode(WikiNode) to avoid orphaned data");
		}

		return wikiNodeLocalService.deleteWikiNode((WikiNode)persistedModel);
	}

	@Override
	public BasePersistence<WikiNode> getBasePersistence() {
		return wikiNodePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return wikiNodePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the wiki nodes matching the UUID and company.
	 *
	 * @param uuid the UUID of the wiki nodes
	 * @param companyId the primary key of the company
	 * @return the matching wiki nodes, or an empty list if no matches were found
	 */
	@Override
	public List<WikiNode> getWikiNodesByUuidAndCompanyId(
		String uuid, long companyId) {

		return wikiNodePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of wiki nodes matching the UUID and company.
	 *
	 * @param uuid the UUID of the wiki nodes
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of wiki nodes
	 * @param end the upper bound of the range of wiki nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching wiki nodes, or an empty list if no matches were found
	 */
	@Override
	public List<WikiNode> getWikiNodesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<WikiNode> orderByComparator) {

		return wikiNodePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the wiki node matching the UUID and group.
	 *
	 * @param uuid the wiki node's UUID
	 * @param groupId the primary key of the group
	 * @return the matching wiki node
	 * @throws PortalException if a matching wiki node could not be found
	 */
	@Override
	public WikiNode getWikiNodeByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return wikiNodePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the wiki nodes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wiki.model.impl.WikiNodeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wiki nodes
	 * @param end the upper bound of the range of wiki nodes (not inclusive)
	 * @return the range of wiki nodes
	 */
	@Override
	public List<WikiNode> getWikiNodes(int start, int end) {
		return wikiNodePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of wiki nodes.
	 *
	 * @return the number of wiki nodes
	 */
	@Override
	public int getWikiNodesCount() {
		return wikiNodePersistence.countAll();
	}

	/**
	 * Updates the wiki node in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WikiNodeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param wikiNode the wiki node
	 * @return the wiki node that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WikiNode updateWikiNode(WikiNode wikiNode) {
		return wikiNodePersistence.update(wikiNode);
	}

	@Deactivate
	protected void deactivate() {
		WikiNodeLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			WikiNodeLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		wikiNodeLocalService = (WikiNodeLocalService)aopProxy;

		WikiNodeLocalServiceUtil.setService(wikiNodeLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WikiNodeLocalService.class.getName();
	}

	@Override
	public CTPersistence<WikiNode> getCTPersistence() {
		return wikiNodePersistence;
	}

	@Override
	public Class<WikiNode> getModelClass() {
		return WikiNode.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<WikiNode>, R, E> updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(wikiNodePersistence);
	}

	protected String getModelClassName() {
		return WikiNode.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = wikiNodePersistence.getDataSource();

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

	protected WikiNodeLocalService wikiNodeLocalService;

	@Reference
	protected WikiNodePersistence wikiNodePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		WikiNodeLocalServiceBaseImpl.class);

}