/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shop.by.diagram.service;

import com.liferay.commerce.shop.by.diagram.model.CSDiagramPin;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CSDiagramPin. This utility wraps
 * <code>com.liferay.commerce.shop.by.diagram.service.impl.CSDiagramPinLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CSDiagramPinLocalService
 * @generated
 */
public class CSDiagramPinLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.shop.by.diagram.service.impl.CSDiagramPinLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cs diagram pin to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramPin the cs diagram pin
	 * @return the cs diagram pin that was added
	 */
	public static CSDiagramPin addCSDiagramPin(CSDiagramPin csDiagramPin) {
		return getService().addCSDiagramPin(csDiagramPin);
	}

	public static CSDiagramPin addCSDiagramPin(
			long userId, long cpDefinitionId, double positionX,
			double positionY, String sequence)
		throws PortalException {

		return getService().addCSDiagramPin(
			userId, cpDefinitionId, positionX, positionY, sequence);
	}

	/**
	 * Creates a new cs diagram pin with the primary key. Does not add the cs diagram pin to the database.
	 *
	 * @param CSDiagramPinId the primary key for the new cs diagram pin
	 * @return the new cs diagram pin
	 */
	public static CSDiagramPin createCSDiagramPin(long CSDiagramPinId) {
		return getService().createCSDiagramPin(CSDiagramPinId);
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
	 * Deletes the cs diagram pin from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramPin the cs diagram pin
	 * @return the cs diagram pin that was removed
	 */
	public static CSDiagramPin deleteCSDiagramPin(CSDiagramPin csDiagramPin) {
		return getService().deleteCSDiagramPin(csDiagramPin);
	}

	/**
	 * Deletes the cs diagram pin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CSDiagramPinId the primary key of the cs diagram pin
	 * @return the cs diagram pin that was removed
	 * @throws PortalException if a cs diagram pin with the primary key could not be found
	 */
	public static CSDiagramPin deleteCSDiagramPin(long CSDiagramPinId)
		throws PortalException {

		return getService().deleteCSDiagramPin(CSDiagramPinId);
	}

	public static void deleteCSDiagramPins(long cpDefinitionId) {
		getService().deleteCSDiagramPins(cpDefinitionId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code>.
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

	public static CSDiagramPin fetchCSDiagramPin(long CSDiagramPinId) {
		return getService().fetchCSDiagramPin(CSDiagramPinId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cs diagram pin with the primary key.
	 *
	 * @param CSDiagramPinId the primary key of the cs diagram pin
	 * @return the cs diagram pin
	 * @throws PortalException if a cs diagram pin with the primary key could not be found
	 */
	public static CSDiagramPin getCSDiagramPin(long CSDiagramPinId)
		throws PortalException {

		return getService().getCSDiagramPin(CSDiagramPinId);
	}

	/**
	 * Returns a range of all the cs diagram pins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cs diagram pins
	 * @param end the upper bound of the range of cs diagram pins (not inclusive)
	 * @return the range of cs diagram pins
	 */
	public static List<CSDiagramPin> getCSDiagramPins(int start, int end) {
		return getService().getCSDiagramPins(start, end);
	}

	public static List<CSDiagramPin> getCSDiagramPins(
		long cpDefinitionId, int start, int end) {

		return getService().getCSDiagramPins(cpDefinitionId, start, end);
	}

	/**
	 * Returns the number of cs diagram pins.
	 *
	 * @return the number of cs diagram pins
	 */
	public static int getCSDiagramPinsCount() {
		return getService().getCSDiagramPinsCount();
	}

	public static int getCSDiagramPinsCount(long cpDefinitionId) {
		return getService().getCSDiagramPinsCount(cpDefinitionId);
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
	 * Updates the cs diagram pin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CSDiagramPinLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csDiagramPin the cs diagram pin
	 * @return the cs diagram pin that was updated
	 */
	public static CSDiagramPin updateCSDiagramPin(CSDiagramPin csDiagramPin) {
		return getService().updateCSDiagramPin(csDiagramPin);
	}

	public static CSDiagramPin updateCSDiagramPin(
			long csDiagramPinId, double positionX, double positionY,
			String sequence)
		throws PortalException {

		return getService().updateCSDiagramPin(
			csDiagramPinId, positionX, positionY, sequence);
	}

	public static CSDiagramPinLocalService getService() {
		return _service;
	}

	public static void setService(CSDiagramPinLocalService service) {
		_service = service;
	}

	private static volatile CSDiagramPinLocalService _service;

}