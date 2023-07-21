/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.message.storage.service;

import com.liferay.analytics.message.storage.model.AnalyticsDeleteMessage;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AnalyticsDeleteMessage. This utility wraps
 * <code>com.liferay.analytics.message.storage.service.impl.AnalyticsDeleteMessageLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsDeleteMessageLocalService
 * @generated
 */
public class AnalyticsDeleteMessageLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.analytics.message.storage.service.impl.AnalyticsDeleteMessageLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the analytics delete message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsDeleteMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsDeleteMessage the analytics delete message
	 * @return the analytics delete message that was added
	 */
	public static AnalyticsDeleteMessage addAnalyticsDeleteMessage(
		AnalyticsDeleteMessage analyticsDeleteMessage) {

		return getService().addAnalyticsDeleteMessage(analyticsDeleteMessage);
	}

	public static AnalyticsDeleteMessage addAnalyticsDeleteMessage(
		long companyId, java.util.Date createDate, String className,
		long classPK, long userId) {

		return getService().addAnalyticsDeleteMessage(
			companyId, createDate, className, classPK, userId);
	}

	/**
	 * Creates a new analytics delete message with the primary key. Does not add the analytics delete message to the database.
	 *
	 * @param analyticsDeleteMessageId the primary key for the new analytics delete message
	 * @return the new analytics delete message
	 */
	public static AnalyticsDeleteMessage createAnalyticsDeleteMessage(
		long analyticsDeleteMessageId) {

		return getService().createAnalyticsDeleteMessage(
			analyticsDeleteMessageId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the analytics delete message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsDeleteMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsDeleteMessage the analytics delete message
	 * @return the analytics delete message that was removed
	 */
	public static AnalyticsDeleteMessage deleteAnalyticsDeleteMessage(
		AnalyticsDeleteMessage analyticsDeleteMessage) {

		return getService().deleteAnalyticsDeleteMessage(
			analyticsDeleteMessage);
	}

	/**
	 * Deletes the analytics delete message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsDeleteMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsDeleteMessageId the primary key of the analytics delete message
	 * @return the analytics delete message that was removed
	 * @throws PortalException if a analytics delete message with the primary key could not be found
	 */
	public static AnalyticsDeleteMessage deleteAnalyticsDeleteMessage(
			long analyticsDeleteMessageId)
		throws PortalException {

		return getService().deleteAnalyticsDeleteMessage(
			analyticsDeleteMessageId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.analytics.message.storage.model.impl.AnalyticsDeleteMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.analytics.message.storage.model.impl.AnalyticsDeleteMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AnalyticsDeleteMessage fetchAnalyticsDeleteMessage(
		long analyticsDeleteMessageId) {

		return getService().fetchAnalyticsDeleteMessage(
			analyticsDeleteMessageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the analytics delete message with the primary key.
	 *
	 * @param analyticsDeleteMessageId the primary key of the analytics delete message
	 * @return the analytics delete message
	 * @throws PortalException if a analytics delete message with the primary key could not be found
	 */
	public static AnalyticsDeleteMessage getAnalyticsDeleteMessage(
			long analyticsDeleteMessageId)
		throws PortalException {

		return getService().getAnalyticsDeleteMessage(analyticsDeleteMessageId);
	}

	/**
	 * Returns a range of all the analytics delete messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.analytics.message.storage.model.impl.AnalyticsDeleteMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of analytics delete messages
	 * @param end the upper bound of the range of analytics delete messages (not inclusive)
	 * @return the range of analytics delete messages
	 */
	public static List<AnalyticsDeleteMessage> getAnalyticsDeleteMessages(
		int start, int end) {

		return getService().getAnalyticsDeleteMessages(start, end);
	}

	public static List<AnalyticsDeleteMessage> getAnalyticsDeleteMessages(
		long companyId, java.util.Date modifiedDate, int start, int end) {

		return getService().getAnalyticsDeleteMessages(
			companyId, modifiedDate, start, end);
	}

	public static List<AnalyticsDeleteMessage> getAnalyticsDeleteMessages(
		long companyId, int start, int end) {

		return getService().getAnalyticsDeleteMessages(companyId, start, end);
	}

	/**
	 * Returns the number of analytics delete messages.
	 *
	 * @return the number of analytics delete messages
	 */
	public static int getAnalyticsDeleteMessagesCount() {
		return getService().getAnalyticsDeleteMessagesCount();
	}

	public static int getAnalyticsDeleteMessagesCount(long companyId) {
		return getService().getAnalyticsDeleteMessagesCount(companyId);
	}

	public static int getAnalyticsDeleteMessagesCount(
		long companyId, java.util.Date modifiedDate) {

		return getService().getAnalyticsDeleteMessagesCount(
			companyId, modifiedDate);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the analytics delete message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnalyticsDeleteMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param analyticsDeleteMessage the analytics delete message
	 * @return the analytics delete message that was updated
	 */
	public static AnalyticsDeleteMessage updateAnalyticsDeleteMessage(
		AnalyticsDeleteMessage analyticsDeleteMessage) {

		return getService().updateAnalyticsDeleteMessage(
			analyticsDeleteMessage);
	}

	public static AnalyticsDeleteMessageLocalService getService() {
		return _service;
	}

	public static void setService(AnalyticsDeleteMessageLocalService service) {
		_service = service;
	}

	private static volatile AnalyticsDeleteMessageLocalService _service;

}