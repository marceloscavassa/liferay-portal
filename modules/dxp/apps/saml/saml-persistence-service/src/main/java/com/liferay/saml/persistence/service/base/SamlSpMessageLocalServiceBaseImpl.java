/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.service.base;

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
import com.liferay.saml.persistence.model.SamlSpMessage;
import com.liferay.saml.persistence.service.SamlSpMessageLocalService;
import com.liferay.saml.persistence.service.SamlSpMessageLocalServiceUtil;
import com.liferay.saml.persistence.service.persistence.SamlSpMessagePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the saml sp message local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.saml.persistence.service.impl.SamlSpMessageLocalServiceImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see com.liferay.saml.persistence.service.impl.SamlSpMessageLocalServiceImpl
 * @generated
 */
public abstract class SamlSpMessageLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, SamlSpMessageLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SamlSpMessageLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>SamlSpMessageLocalServiceUtil</code>.
	 */

	/**
	 * Adds the saml sp message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SamlSpMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param samlSpMessage the saml sp message
	 * @return the saml sp message that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SamlSpMessage addSamlSpMessage(SamlSpMessage samlSpMessage) {
		samlSpMessage.setNew(true);

		return samlSpMessagePersistence.update(samlSpMessage);
	}

	/**
	 * Creates a new saml sp message with the primary key. Does not add the saml sp message to the database.
	 *
	 * @param samlSpMessageId the primary key for the new saml sp message
	 * @return the new saml sp message
	 */
	@Override
	@Transactional(enabled = false)
	public SamlSpMessage createSamlSpMessage(long samlSpMessageId) {
		return samlSpMessagePersistence.create(samlSpMessageId);
	}

	/**
	 * Deletes the saml sp message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SamlSpMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message that was removed
	 * @throws PortalException if a saml sp message with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SamlSpMessage deleteSamlSpMessage(long samlSpMessageId)
		throws PortalException {

		return samlSpMessagePersistence.remove(samlSpMessageId);
	}

	/**
	 * Deletes the saml sp message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SamlSpMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param samlSpMessage the saml sp message
	 * @return the saml sp message that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SamlSpMessage deleteSamlSpMessage(SamlSpMessage samlSpMessage) {
		return samlSpMessagePersistence.remove(samlSpMessage);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return samlSpMessagePersistence.dslQuery(dslQuery);
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
			SamlSpMessage.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return samlSpMessagePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.saml.persistence.model.impl.SamlSpMessageModelImpl</code>.
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

		return samlSpMessagePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.saml.persistence.model.impl.SamlSpMessageModelImpl</code>.
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

		return samlSpMessagePersistence.findWithDynamicQuery(
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
		return samlSpMessagePersistence.countWithDynamicQuery(dynamicQuery);
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

		return samlSpMessagePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SamlSpMessage fetchSamlSpMessage(long samlSpMessageId) {
		return samlSpMessagePersistence.fetchByPrimaryKey(samlSpMessageId);
	}

	/**
	 * Returns the saml sp message with the primary key.
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message
	 * @throws PortalException if a saml sp message with the primary key could not be found
	 */
	@Override
	public SamlSpMessage getSamlSpMessage(long samlSpMessageId)
		throws PortalException {

		return samlSpMessagePersistence.findByPrimaryKey(samlSpMessageId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(samlSpMessageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SamlSpMessage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("samlSpMessageId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			samlSpMessageLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SamlSpMessage.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"samlSpMessageId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(samlSpMessageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SamlSpMessage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("samlSpMessageId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return samlSpMessagePersistence.create(
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
				"Implement SamlSpMessageLocalServiceImpl#deleteSamlSpMessage(SamlSpMessage) to avoid orphaned data");
		}

		return samlSpMessageLocalService.deleteSamlSpMessage(
			(SamlSpMessage)persistedModel);
	}

	@Override
	public BasePersistence<SamlSpMessage> getBasePersistence() {
		return samlSpMessagePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return samlSpMessagePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the saml sp messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.saml.persistence.model.impl.SamlSpMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp messages
	 * @param end the upper bound of the range of saml sp messages (not inclusive)
	 * @return the range of saml sp messages
	 */
	@Override
	public List<SamlSpMessage> getSamlSpMessages(int start, int end) {
		return samlSpMessagePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of saml sp messages.
	 *
	 * @return the number of saml sp messages
	 */
	@Override
	public int getSamlSpMessagesCount() {
		return samlSpMessagePersistence.countAll();
	}

	/**
	 * Updates the saml sp message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SamlSpMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param samlSpMessage the saml sp message
	 * @return the saml sp message that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SamlSpMessage updateSamlSpMessage(SamlSpMessage samlSpMessage) {
		return samlSpMessagePersistence.update(samlSpMessage);
	}

	@Deactivate
	protected void deactivate() {
		SamlSpMessageLocalServiceUtil.setService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SamlSpMessageLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		samlSpMessageLocalService = (SamlSpMessageLocalService)aopProxy;

		SamlSpMessageLocalServiceUtil.setService(samlSpMessageLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SamlSpMessageLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SamlSpMessage.class;
	}

	protected String getModelClassName() {
		return SamlSpMessage.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = samlSpMessagePersistence.getDataSource();

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

	protected SamlSpMessageLocalService samlSpMessageLocalService;

	@Reference
	protected SamlSpMessagePersistence samlSpMessagePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		SamlSpMessageLocalServiceBaseImpl.class);

}