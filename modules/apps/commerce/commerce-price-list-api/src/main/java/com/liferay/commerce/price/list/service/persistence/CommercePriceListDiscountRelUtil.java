/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service.persistence;

import com.liferay.commerce.price.list.model.CommercePriceListDiscountRel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the commerce price list discount rel service. This utility wraps <code>com.liferay.commerce.price.list.service.persistence.impl.CommercePriceListDiscountRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListDiscountRelPersistence
 * @generated
 */
public class CommercePriceListDiscountRelUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		CommercePriceListDiscountRel commercePriceListDiscountRel) {

		getPersistence().clearCache(commercePriceListDiscountRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommercePriceListDiscountRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommercePriceListDiscountRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommercePriceListDiscountRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommercePriceListDiscountRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommercePriceListDiscountRel update(
		CommercePriceListDiscountRel commercePriceListDiscountRel) {

		return getPersistence().update(commercePriceListDiscountRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommercePriceListDiscountRel update(
		CommercePriceListDiscountRel commercePriceListDiscountRel,
		ServiceContext serviceContext) {

		return getPersistence().update(
			commercePriceListDiscountRel, serviceContext);
	}

	/**
	 * Returns all the commerce price list discount rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the commerce price list discount rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @return the range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price list discount rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByUuid_First(
			String uuid,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first commerce price list discount rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByUuid_First(
		String uuid,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce price list discount rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByUuid_Last(
			String uuid,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last commerce price list discount rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the commerce price list discount rels before and after the current commerce price list discount rel in the ordered set where uuid = &#63;.
	 *
	 * @param commercePriceListDiscountRelId the primary key of the current commerce price list discount rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a commerce price list discount rel with the primary key could not be found
	 */
	public static CommercePriceListDiscountRel[] findByUuid_PrevAndNext(
			long commercePriceListDiscountRelId, String uuid,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByUuid_PrevAndNext(
			commercePriceListDiscountRelId, uuid, orderByComparator);
	}

	/**
	 * Removes all the commerce price list discount rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of commerce price list discount rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce price list discount rels
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the commerce price list discount rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce price list discount rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @return the range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price list discount rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first commerce price list discount rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce price list discount rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last commerce price list discount rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the commerce price list discount rels before and after the current commerce price list discount rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commercePriceListDiscountRelId the primary key of the current commerce price list discount rel
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a commerce price list discount rel with the primary key could not be found
	 */
	public static CommercePriceListDiscountRel[] findByUuid_C_PrevAndNext(
			long commercePriceListDiscountRelId, String uuid, long companyId,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByUuid_C_PrevAndNext(
			commercePriceListDiscountRelId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the commerce price list discount rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of commerce price list discount rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce price list discount rels
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the commerce price list discount rels where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByCommercePriceListId(
		long commercePriceListId) {

		return getPersistence().findByCommercePriceListId(commercePriceListId);
	}

	/**
	 * Returns a range of all the commerce price list discount rels where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @return the range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByCommercePriceListId(
		long commercePriceListId, int start, int end) {

		return getPersistence().findByCommercePriceListId(
			commercePriceListId, start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByCommercePriceListId(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findByCommercePriceListId(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first commerce price list discount rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByCommercePriceListId_First(
			long commercePriceListId,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByCommercePriceListId_First(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the first commerce price list discount rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByCommercePriceListId_First(
		long commercePriceListId,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().fetchByCommercePriceListId_First(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the last commerce price list discount rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByCommercePriceListId_Last(
			long commercePriceListId,
			OrderByComparator<CommercePriceListDiscountRel> orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByCommercePriceListId_Last(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the last commerce price list discount rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByCommercePriceListId_Last(
		long commercePriceListId,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().fetchByCommercePriceListId_Last(
			commercePriceListId, orderByComparator);
	}

	/**
	 * Returns the commerce price list discount rels before and after the current commerce price list discount rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListDiscountRelId the primary key of the current commerce price list discount rel
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a commerce price list discount rel with the primary key could not be found
	 */
	public static CommercePriceListDiscountRel[]
			findByCommercePriceListId_PrevAndNext(
				long commercePriceListDiscountRelId, long commercePriceListId,
				OrderByComparator<CommercePriceListDiscountRel>
					orderByComparator)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByCommercePriceListId_PrevAndNext(
			commercePriceListDiscountRelId, commercePriceListId,
			orderByComparator);
	}

	/**
	 * Removes all the commerce price list discount rels where commercePriceListId = &#63; from the database.
	 *
	 * @param commercePriceListId the commerce price list ID
	 */
	public static void removeByCommercePriceListId(long commercePriceListId) {
		getPersistence().removeByCommercePriceListId(commercePriceListId);
	}

	/**
	 * Returns the number of commerce price list discount rels where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @return the number of matching commerce price list discount rels
	 */
	public static int countByCommercePriceListId(long commercePriceListId) {
		return getPersistence().countByCommercePriceListId(commercePriceListId);
	}

	/**
	 * Returns the commerce price list discount rel where commerceDiscountId = &#63; and commercePriceListId = &#63; or throws a <code>NoSuchPriceListDiscountRelException</code> if it could not be found.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel findByCDI_CPI(
			long commerceDiscountId, long commercePriceListId)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByCDI_CPI(
			commerceDiscountId, commercePriceListId);
	}

	/**
	 * Returns the commerce price list discount rel where commerceDiscountId = &#63; and commercePriceListId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByCDI_CPI(
		long commerceDiscountId, long commercePriceListId) {

		return getPersistence().fetchByCDI_CPI(
			commerceDiscountId, commercePriceListId);
	}

	/**
	 * Returns the commerce price list discount rel where commerceDiscountId = &#63; and commercePriceListId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commercePriceListId the commerce price list ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce price list discount rel, or <code>null</code> if a matching commerce price list discount rel could not be found
	 */
	public static CommercePriceListDiscountRel fetchByCDI_CPI(
		long commerceDiscountId, long commercePriceListId,
		boolean useFinderCache) {

		return getPersistence().fetchByCDI_CPI(
			commerceDiscountId, commercePriceListId, useFinderCache);
	}

	/**
	 * Removes the commerce price list discount rel where commerceDiscountId = &#63; and commercePriceListId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the commerce price list discount rel that was removed
	 */
	public static CommercePriceListDiscountRel removeByCDI_CPI(
			long commerceDiscountId, long commercePriceListId)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().removeByCDI_CPI(
			commerceDiscountId, commercePriceListId);
	}

	/**
	 * Returns the number of commerce price list discount rels where commerceDiscountId = &#63; and commercePriceListId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the number of matching commerce price list discount rels
	 */
	public static int countByCDI_CPI(
		long commerceDiscountId, long commercePriceListId) {

		return getPersistence().countByCDI_CPI(
			commerceDiscountId, commercePriceListId);
	}

	/**
	 * Caches the commerce price list discount rel in the entity cache if it is enabled.
	 *
	 * @param commercePriceListDiscountRel the commerce price list discount rel
	 */
	public static void cacheResult(
		CommercePriceListDiscountRel commercePriceListDiscountRel) {

		getPersistence().cacheResult(commercePriceListDiscountRel);
	}

	/**
	 * Caches the commerce price list discount rels in the entity cache if it is enabled.
	 *
	 * @param commercePriceListDiscountRels the commerce price list discount rels
	 */
	public static void cacheResult(
		List<CommercePriceListDiscountRel> commercePriceListDiscountRels) {

		getPersistence().cacheResult(commercePriceListDiscountRels);
	}

	/**
	 * Creates a new commerce price list discount rel with the primary key. Does not add the commerce price list discount rel to the database.
	 *
	 * @param commercePriceListDiscountRelId the primary key for the new commerce price list discount rel
	 * @return the new commerce price list discount rel
	 */
	public static CommercePriceListDiscountRel create(
		long commercePriceListDiscountRelId) {

		return getPersistence().create(commercePriceListDiscountRelId);
	}

	/**
	 * Removes the commerce price list discount rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListDiscountRelId the primary key of the commerce price list discount rel
	 * @return the commerce price list discount rel that was removed
	 * @throws NoSuchPriceListDiscountRelException if a commerce price list discount rel with the primary key could not be found
	 */
	public static CommercePriceListDiscountRel remove(
			long commercePriceListDiscountRelId)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().remove(commercePriceListDiscountRelId);
	}

	public static CommercePriceListDiscountRel updateImpl(
		CommercePriceListDiscountRel commercePriceListDiscountRel) {

		return getPersistence().updateImpl(commercePriceListDiscountRel);
	}

	/**
	 * Returns the commerce price list discount rel with the primary key or throws a <code>NoSuchPriceListDiscountRelException</code> if it could not be found.
	 *
	 * @param commercePriceListDiscountRelId the primary key of the commerce price list discount rel
	 * @return the commerce price list discount rel
	 * @throws NoSuchPriceListDiscountRelException if a commerce price list discount rel with the primary key could not be found
	 */
	public static CommercePriceListDiscountRel findByPrimaryKey(
			long commercePriceListDiscountRelId)
		throws com.liferay.commerce.price.list.exception.
			NoSuchPriceListDiscountRelException {

		return getPersistence().findByPrimaryKey(
			commercePriceListDiscountRelId);
	}

	/**
	 * Returns the commerce price list discount rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commercePriceListDiscountRelId the primary key of the commerce price list discount rel
	 * @return the commerce price list discount rel, or <code>null</code> if a commerce price list discount rel with the primary key could not be found
	 */
	public static CommercePriceListDiscountRel fetchByPrimaryKey(
		long commercePriceListDiscountRelId) {

		return getPersistence().fetchByPrimaryKey(
			commercePriceListDiscountRelId);
	}

	/**
	 * Returns all the commerce price list discount rels.
	 *
	 * @return the commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the commerce price list discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @return the range of commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findAll(
		int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the commerce price list discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommercePriceListDiscountRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list discount rels
	 * @param end the upper bound of the range of commerce price list discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce price list discount rels
	 */
	public static List<CommercePriceListDiscountRel> findAll(
		int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce price list discount rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce price list discount rels.
	 *
	 * @return the number of commerce price list discount rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommercePriceListDiscountRelPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		CommercePriceListDiscountRelPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile CommercePriceListDiscountRelPersistence
		_persistence;

}