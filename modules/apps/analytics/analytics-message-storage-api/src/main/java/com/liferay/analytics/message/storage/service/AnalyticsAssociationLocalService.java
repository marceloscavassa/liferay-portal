/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.message.storage.service;

import com.liferay.analytics.message.storage.model.AnalyticsAssociation;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.change.tracking.CTAware;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for AnalyticsAssociation. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsAssociationLocalServiceUtil
 * @generated
 */
@CTAware
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AnalyticsAssociationLocalService
	extends BaseLocalService, CTService<AnalyticsAssociation>,
			PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.analytics.message.storage.service.impl.AnalyticsAssociationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the analytics association local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AnalyticsAssociationLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the analytics association to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsAssociationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsAssociation the analytics association
	 * @return the analytics association that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AnalyticsAssociation addAnalyticsAssociation(
		AnalyticsAssociation analyticsAssociation);

	public AnalyticsAssociation addAnalyticsAssociation(
		long companyId, Date createDate, long userId,
		String associationClassName, long associationClassPK, String className,
		long classPK);

	/**
	 * Creates a new analytics association with the primary key. Does not add the analytics association to the database.
	 *
	 * @param analyticsAssociationId the primary key for the new analytics association
	 * @return the new analytics association
	 */
	@Transactional(enabled = false)
	public AnalyticsAssociation createAnalyticsAssociation(
		long analyticsAssociationId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the analytics association from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsAssociationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsAssociation the analytics association
	 * @return the analytics association that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public AnalyticsAssociation deleteAnalyticsAssociation(
		AnalyticsAssociation analyticsAssociation);

	/**
	 * Deletes the analytics association with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsAssociationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsAssociationId the primary key of the analytics association
	 * @return the analytics association that was removed
	 * @throws PortalException if a analytics association with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public AnalyticsAssociation deleteAnalyticsAssociation(
			long analyticsAssociationId)
		throws PortalException;

	public void deleteAnalyticsAssociations(
		long companyId, String associationClassName, long associationClassPK);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.analytics.message.storage.model.impl.AnalyticsAssociationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.analytics.message.storage.model.impl.AnalyticsAssociationModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AnalyticsAssociation fetchAnalyticsAssociation(
		long analyticsAssociationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the analytics association with the primary key.
	 *
	 * @param analyticsAssociationId the primary key of the analytics association
	 * @return the analytics association
	 * @throws PortalException if a analytics association with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AnalyticsAssociation getAnalyticsAssociation(
			long analyticsAssociationId)
		throws PortalException;

	/**
	 * Returns a range of all the analytics associations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.analytics.message.storage.model.impl.AnalyticsAssociationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of analytics associations
	 * @param end the upper bound of the range of analytics associations (not inclusive)
	 * @return the range of analytics associations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AnalyticsAssociation> getAnalyticsAssociations(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AnalyticsAssociation> getAnalyticsAssociations(
		long companyId, Date modifiedDate, String associationClassName,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AnalyticsAssociation> getAnalyticsAssociations(
		long companyId, String associationClassName, int start, int end);

	/**
	 * Returns the number of analytics associations.
	 *
	 * @return the number of analytics associations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAnalyticsAssociationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAnalyticsAssociationsCount(
		long companyId, Date modifiedDate, String associationClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAnalyticsAssociationsCount(
		long companyId, String associationClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the analytics association in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsAssociationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsAssociation the analytics association
	 * @return the analytics association that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AnalyticsAssociation updateAnalyticsAssociation(
		AnalyticsAssociation analyticsAssociation);

	@Override
	@Transactional(enabled = false)
	public CTPersistence<AnalyticsAssociation> getCTPersistence();

	@Override
	@Transactional(enabled = false)
	public Class<AnalyticsAssociation> getModelClass();

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<AnalyticsAssociation>, R, E>
				updateUnsafeFunction)
		throws E;

}