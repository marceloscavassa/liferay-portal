/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.segments.model.SegmentsExperience;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the segments experience service. This utility wraps <code>com.liferay.segments.service.persistence.impl.SegmentsExperiencePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsExperiencePersistence
 * @generated
 */
public class SegmentsExperienceUtil {

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
	public static void clearCache(SegmentsExperience segmentsExperience) {
		getPersistence().clearCache(segmentsExperience);
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
	public static Map<Serializable, SegmentsExperience> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SegmentsExperience> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SegmentsExperience> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SegmentsExperience> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SegmentsExperience update(
		SegmentsExperience segmentsExperience) {

		return getPersistence().update(segmentsExperience);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SegmentsExperience update(
		SegmentsExperience segmentsExperience, ServiceContext serviceContext) {

		return getPersistence().update(segmentsExperience, serviceContext);
	}

	/**
	 * Returns all the segments experiences where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the segments experiences where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByUuid_First(
			String uuid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByUuid_First(
		String uuid, OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByUuid_Last(
			String uuid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByUuid_Last(
		String uuid, OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where uuid = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByUuid_PrevAndNext(
			long segmentsExperienceId, String uuid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUuid_PrevAndNext(
			segmentsExperienceId, uuid, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of segments experiences where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching segments experiences
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the segments experience where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchExperienceException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByUUID_G(String uuid, long groupId)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the segments experience where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the segments experience where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the segments experience where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the segments experience that was removed
	 */
	public static SegmentsExperience removeByUUID_G(String uuid, long groupId)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of segments experiences where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching segments experiences
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the segments experiences where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the segments experiences where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByUuid_C_PrevAndNext(
			long segmentsExperienceId, String uuid, long companyId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByUuid_C_PrevAndNext(
			segmentsExperienceId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of segments experiences where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching segments experiences
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByGroupId_First(
			long groupId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByGroupId_First(
		long groupId, OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByGroupId_Last(
			long groupId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByGroupId_Last(
		long groupId, OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByGroupId_PrevAndNext(
			long segmentsExperienceId, long groupId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByGroupId_PrevAndNext(
			segmentsExperienceId, groupId, orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByGroupId_PrevAndNext(
			long segmentsExperienceId, long groupId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			segmentsExperienceId, groupId, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching segments experiences
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the segments experiences where segmentsEntryId = &#63;.
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findBySegmentsEntryId(
		long segmentsEntryId) {

		return getPersistence().findBySegmentsEntryId(segmentsEntryId);
	}

	/**
	 * Returns a range of all the segments experiences where segmentsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findBySegmentsEntryId(
		long segmentsEntryId, int start, int end) {

		return getPersistence().findBySegmentsEntryId(
			segmentsEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where segmentsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findBySegmentsEntryId(
		long segmentsEntryId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findBySegmentsEntryId(
			segmentsEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where segmentsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findBySegmentsEntryId(
		long segmentsEntryId, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBySegmentsEntryId(
			segmentsEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where segmentsEntryId = &#63;.
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findBySegmentsEntryId_First(
			long segmentsEntryId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findBySegmentsEntryId_First(
			segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where segmentsEntryId = &#63;.
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchBySegmentsEntryId_First(
		long segmentsEntryId,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchBySegmentsEntryId_First(
			segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where segmentsEntryId = &#63;.
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findBySegmentsEntryId_Last(
			long segmentsEntryId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findBySegmentsEntryId_Last(
			segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where segmentsEntryId = &#63;.
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchBySegmentsEntryId_Last(
		long segmentsEntryId,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchBySegmentsEntryId_Last(
			segmentsEntryId, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where segmentsEntryId = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param segmentsEntryId the segments entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findBySegmentsEntryId_PrevAndNext(
			long segmentsExperienceId, long segmentsEntryId,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findBySegmentsEntryId_PrevAndNext(
			segmentsExperienceId, segmentsEntryId, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where segmentsEntryId = &#63; from the database.
	 *
	 * @param segmentsEntryId the segments entry ID
	 */
	public static void removeBySegmentsEntryId(long segmentsEntryId) {
		getPersistence().removeBySegmentsEntryId(segmentsEntryId);
	}

	/**
	 * Returns the number of segments experiences where segmentsEntryId = &#63;.
	 *
	 * @param segmentsEntryId the segments entry ID
	 * @return the number of matching segments experiences
	 */
	public static int countBySegmentsEntryId(long segmentsEntryId) {
		return getPersistence().countBySegmentsEntryId(segmentsEntryId);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P(long groupId, long plid) {
		return getPersistence().findByG_P(groupId, plid);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P(
		long groupId, long plid, int start, int end) {

		return getPersistence().findByG_P(groupId, plid, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P(
		long groupId, long plid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_P(
			groupId, plid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P(
		long groupId, long plid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_P(
			groupId, plid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_First(
			long groupId, long plid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_First(
			groupId, plid, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_First(
		long groupId, long plid,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_First(
			groupId, plid, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_Last(
			long groupId, long plid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_Last(
			groupId, plid, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_Last(
		long groupId, long plid,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_Last(
			groupId, plid, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63; and plid = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByG_P_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_PrevAndNext(
			segmentsExperienceId, groupId, plid, orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P(
		long groupId, long plid) {

		return getPersistence().filterFindByG_P(groupId, plid);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P(
		long groupId, long plid, int start, int end) {

		return getPersistence().filterFindByG_P(groupId, plid, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P(
		long groupId, long plid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_P(
			groupId, plid, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByG_P_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByG_P_PrevAndNext(
			segmentsExperienceId, groupId, plid, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; and plid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 */
	public static void removeByG_P(long groupId, long plid) {
		getPersistence().removeByG_P(groupId, plid);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the number of matching segments experiences
	 */
	public static int countByG_P(long groupId, long plid) {
		return getPersistence().countByG_P(groupId, plid);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_P(long groupId, long plid) {
		return getPersistence().filterCountByG_P(groupId, plid);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P(
		long groupId, long segmentsEntryId, long plid) {

		return getPersistence().findByG_S_P(groupId, segmentsEntryId, plid);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P(
		long groupId, long segmentsEntryId, long plid, int start, int end) {

		return getPersistence().findByG_S_P(
			groupId, segmentsEntryId, plid, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P(
		long groupId, long segmentsEntryId, long plid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_S_P(
			groupId, segmentsEntryId, plid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P(
		long groupId, long segmentsEntryId, long plid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_S_P(
			groupId, segmentsEntryId, plid, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_S_P_First(
			long groupId, long segmentsEntryId, long plid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_S_P_First(
			groupId, segmentsEntryId, plid, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_S_P_First(
		long groupId, long segmentsEntryId, long plid,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_S_P_First(
			groupId, segmentsEntryId, plid, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_S_P_Last(
			long groupId, long segmentsEntryId, long plid,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_S_P_Last(
			groupId, segmentsEntryId, plid, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_S_P_Last(
		long groupId, long segmentsEntryId, long plid,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_S_P_Last(
			groupId, segmentsEntryId, plid, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByG_S_P_PrevAndNext(
			long segmentsExperienceId, long groupId, long segmentsEntryId,
			long plid, OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_S_P_PrevAndNext(
			segmentsExperienceId, groupId, segmentsEntryId, plid,
			orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P(
		long groupId, long segmentsEntryId, long plid) {

		return getPersistence().filterFindByG_S_P(
			groupId, segmentsEntryId, plid);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P(
		long groupId, long segmentsEntryId, long plid, int start, int end) {

		return getPersistence().filterFindByG_S_P(
			groupId, segmentsEntryId, plid, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P(
		long groupId, long segmentsEntryId, long plid, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_S_P(
			groupId, segmentsEntryId, plid, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByG_S_P_PrevAndNext(
			long segmentsExperienceId, long groupId, long segmentsEntryId,
			long plid, OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByG_S_P_PrevAndNext(
			segmentsExperienceId, groupId, segmentsEntryId, plid,
			orderByComparator);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 */
	public static void removeByG_S_P(
		long groupId, long segmentsEntryId, long plid) {

		getPersistence().removeByG_S_P(groupId, segmentsEntryId, plid);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @return the number of matching segments experiences
	 */
	public static int countByG_S_P(
		long groupId, long segmentsEntryId, long plid) {

		return getPersistence().countByG_S_P(groupId, segmentsEntryId, plid);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_S_P(
		long groupId, long segmentsEntryId, long plid) {

		return getPersistence().filterCountByG_S_P(
			groupId, segmentsEntryId, plid);
	}

	/**
	 * Returns the segments experience where groupId = &#63; and segmentsExperienceKey = &#63; and plid = &#63; or throws a <code>NoSuchExperienceException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperienceKey the segments experience key
	 * @param plid the plid
	 * @return the matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_SEK_P(
			long groupId, String segmentsExperienceKey, long plid)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_SEK_P(
			groupId, segmentsExperienceKey, plid);
	}

	/**
	 * Returns the segments experience where groupId = &#63; and segmentsExperienceKey = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperienceKey the segments experience key
	 * @param plid the plid
	 * @return the matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_SEK_P(
		long groupId, String segmentsExperienceKey, long plid) {

		return getPersistence().fetchByG_SEK_P(
			groupId, segmentsExperienceKey, plid);
	}

	/**
	 * Returns the segments experience where groupId = &#63; and segmentsExperienceKey = &#63; and plid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperienceKey the segments experience key
	 * @param plid the plid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_SEK_P(
		long groupId, String segmentsExperienceKey, long plid,
		boolean useFinderCache) {

		return getPersistence().fetchByG_SEK_P(
			groupId, segmentsExperienceKey, plid, useFinderCache);
	}

	/**
	 * Removes the segments experience where groupId = &#63; and segmentsExperienceKey = &#63; and plid = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperienceKey the segments experience key
	 * @param plid the plid
	 * @return the segments experience that was removed
	 */
	public static SegmentsExperience removeByG_SEK_P(
			long groupId, String segmentsExperienceKey, long plid)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().removeByG_SEK_P(
			groupId, segmentsExperienceKey, plid);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and segmentsExperienceKey = &#63; and plid = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperienceKey the segments experience key
	 * @param plid the plid
	 * @return the number of matching segments experiences
	 */
	public static int countByG_SEK_P(
		long groupId, String segmentsExperienceKey, long plid) {

		return getPersistence().countByG_SEK_P(
			groupId, segmentsExperienceKey, plid);
	}

	/**
	 * Returns the segments experience where groupId = &#63; and plid = &#63; and priority = &#63; or throws a <code>NoSuchExperienceException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_P(
			long groupId, long plid, int priority)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_P(groupId, plid, priority);
	}

	/**
	 * Returns the segments experience where groupId = &#63; and plid = &#63; and priority = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_P(
		long groupId, long plid, int priority) {

		return getPersistence().fetchByG_P_P(groupId, plid, priority);
	}

	/**
	 * Returns the segments experience where groupId = &#63; and plid = &#63; and priority = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_P(
		long groupId, long plid, int priority, boolean useFinderCache) {

		return getPersistence().fetchByG_P_P(
			groupId, plid, priority, useFinderCache);
	}

	/**
	 * Removes the segments experience where groupId = &#63; and plid = &#63; and priority = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the segments experience that was removed
	 */
	public static SegmentsExperience removeByG_P_P(
			long groupId, long plid, int priority)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().removeByG_P_P(groupId, plid, priority);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and plid = &#63; and priority = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the number of matching segments experiences
	 */
	public static int countByG_P_P(long groupId, long plid, int priority) {
		return getPersistence().countByG_P_P(groupId, plid, priority);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_GtP(
		long groupId, long plid, int priority) {

		return getPersistence().findByG_P_GtP(groupId, plid, priority);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_GtP(
		long groupId, long plid, int priority, int start, int end) {

		return getPersistence().findByG_P_GtP(
			groupId, plid, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_GtP(
		long groupId, long plid, int priority, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_P_GtP(
			groupId, plid, priority, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_GtP(
		long groupId, long plid, int priority, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_P_GtP(
			groupId, plid, priority, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_GtP_First(
			long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_GtP_First(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_GtP_First(
		long groupId, long plid, int priority,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_GtP_First(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_GtP_Last(
			long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_GtP_Last(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_GtP_Last(
		long groupId, long plid, int priority,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_GtP_Last(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByG_P_GtP_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_GtP_PrevAndNext(
			segmentsExperienceId, groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_GtP(
		long groupId, long plid, int priority) {

		return getPersistence().filterFindByG_P_GtP(groupId, plid, priority);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_GtP(
		long groupId, long plid, int priority, int start, int end) {

		return getPersistence().filterFindByG_P_GtP(
			groupId, plid, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_GtP(
		long groupId, long plid, int priority, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_P_GtP(
			groupId, plid, priority, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByG_P_GtP_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByG_P_GtP_PrevAndNext(
			segmentsExperienceId, groupId, plid, priority, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; and plid = &#63; and priority &gt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 */
	public static void removeByG_P_GtP(long groupId, long plid, int priority) {
		getPersistence().removeByG_P_GtP(groupId, plid, priority);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the number of matching segments experiences
	 */
	public static int countByG_P_GtP(long groupId, long plid, int priority) {
		return getPersistence().countByG_P_GtP(groupId, plid, priority);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_P_GtP(
		long groupId, long plid, int priority) {

		return getPersistence().filterCountByG_P_GtP(groupId, plid, priority);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_LtP(
		long groupId, long plid, int priority) {

		return getPersistence().findByG_P_LtP(groupId, plid, priority);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_LtP(
		long groupId, long plid, int priority, int start, int end) {

		return getPersistence().findByG_P_LtP(
			groupId, plid, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_LtP(
		long groupId, long plid, int priority, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_P_LtP(
			groupId, plid, priority, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_LtP(
		long groupId, long plid, int priority, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_P_LtP(
			groupId, plid, priority, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_LtP_First(
			long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_LtP_First(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_LtP_First(
		long groupId, long plid, int priority,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_LtP_First(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_LtP_Last(
			long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_LtP_Last(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_LtP_Last(
		long groupId, long plid, int priority,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_LtP_Last(
			groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByG_P_LtP_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_LtP_PrevAndNext(
			segmentsExperienceId, groupId, plid, priority, orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_LtP(
		long groupId, long plid, int priority) {

		return getPersistence().filterFindByG_P_LtP(groupId, plid, priority);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_LtP(
		long groupId, long plid, int priority, int start, int end) {

		return getPersistence().filterFindByG_P_LtP(
			groupId, plid, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_LtP(
		long groupId, long plid, int priority, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_P_LtP(
			groupId, plid, priority, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByG_P_LtP_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid, int priority,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByG_P_LtP_PrevAndNext(
			segmentsExperienceId, groupId, plid, priority, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; and plid = &#63; and priority &lt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 */
	public static void removeByG_P_LtP(long groupId, long plid, int priority) {
		getPersistence().removeByG_P_LtP(groupId, plid, priority);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the number of matching segments experiences
	 */
	public static int countByG_P_LtP(long groupId, long plid, int priority) {
		return getPersistence().countByG_P_LtP(groupId, plid, priority);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param priority the priority
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_P_LtP(
		long groupId, long plid, int priority) {

		return getPersistence().filterCountByG_P_LtP(groupId, plid, priority);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_A(
		long groupId, long plid, boolean active) {

		return getPersistence().findByG_P_A(groupId, plid, active);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_A(
		long groupId, long plid, boolean active, int start, int end) {

		return getPersistence().findByG_P_A(groupId, plid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_A(
		long groupId, long plid, boolean active, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_P_A(
			groupId, plid, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_P_A(
		long groupId, long plid, boolean active, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_P_A(
			groupId, plid, active, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_A_First(
			long groupId, long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_A_First(
			groupId, plid, active, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_A_First(
		long groupId, long plid, boolean active,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_A_First(
			groupId, plid, active, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_P_A_Last(
			long groupId, long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_A_Last(
			groupId, plid, active, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_P_A_Last(
		long groupId, long plid, boolean active,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_P_A_Last(
			groupId, plid, active, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByG_P_A_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_P_A_PrevAndNext(
			segmentsExperienceId, groupId, plid, active, orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_A(
		long groupId, long plid, boolean active) {

		return getPersistence().filterFindByG_P_A(groupId, plid, active);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_A(
		long groupId, long plid, boolean active, int start, int end) {

		return getPersistence().filterFindByG_P_A(
			groupId, plid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_P_A(
		long groupId, long plid, boolean active, int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_P_A(
			groupId, plid, active, start, end, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByG_P_A_PrevAndNext(
			long segmentsExperienceId, long groupId, long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByG_P_A_PrevAndNext(
			segmentsExperienceId, groupId, plid, active, orderByComparator);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; and plid = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 */
	public static void removeByG_P_A(long groupId, long plid, boolean active) {
		getPersistence().removeByG_P_A(groupId, plid, active);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @return the number of matching segments experiences
	 */
	public static int countByG_P_A(long groupId, long plid, boolean active) {
		return getPersistence().countByG_P_A(groupId, plid, active);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plid the plid
	 * @param active the active
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_P_A(
		long groupId, long plid, boolean active) {

		return getPersistence().filterCountByG_P_A(groupId, plid, active);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryId, plid, active);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active,
		int start, int end) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryId, plid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active,
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryId, plid, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active,
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryId, plid, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_S_P_A_First(
			long groupId, long segmentsEntryId, long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_S_P_A_First(
			groupId, segmentsEntryId, plid, active, orderByComparator);
	}

	/**
	 * Returns the first segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_S_P_A_First(
		long groupId, long segmentsEntryId, long plid, boolean active,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_S_P_A_First(
			groupId, segmentsEntryId, plid, active, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience
	 * @throws NoSuchExperienceException if a matching segments experience could not be found
	 */
	public static SegmentsExperience findByG_S_P_A_Last(
			long groupId, long segmentsEntryId, long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_S_P_A_Last(
			groupId, segmentsEntryId, plid, active, orderByComparator);
	}

	/**
	 * Returns the last segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experience, or <code>null</code> if a matching segments experience could not be found
	 */
	public static SegmentsExperience fetchByG_S_P_A_Last(
		long groupId, long segmentsEntryId, long plid, boolean active,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().fetchByG_S_P_A_Last(
			groupId, segmentsEntryId, plid, active, orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] findByG_S_P_A_PrevAndNext(
			long segmentsExperienceId, long groupId, long segmentsEntryId,
			long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByG_S_P_A_PrevAndNext(
			segmentsExperienceId, groupId, segmentsEntryId, plid, active,
			orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active) {

		return getPersistence().filterFindByG_S_P_A(
			groupId, segmentsEntryId, plid, active);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active,
		int start, int end) {

		return getPersistence().filterFindByG_S_P_A(
			groupId, segmentsEntryId, plid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permissions to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active,
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_S_P_A(
			groupId, segmentsEntryId, plid, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns the segments experiences before and after the current segments experience in the ordered set of segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param segmentsExperienceId the primary key of the current segments experience
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience[] filterFindByG_S_P_A_PrevAndNext(
			long segmentsExperienceId, long groupId, long segmentsEntryId,
			long plid, boolean active,
			OrderByComparator<SegmentsExperience> orderByComparator)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().filterFindByG_S_P_A_PrevAndNext(
			segmentsExperienceId, groupId, segmentsEntryId, plid, active,
			orderByComparator);
	}

	/**
	 * Returns all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @return the matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active) {

		return getPersistence().filterFindByG_S_P_A(
			groupId, segmentsEntryIds, plid, active);
	}

	/**
	 * Returns a range of all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active,
		int start, int end) {

		return getPersistence().filterFindByG_S_P_A(
			groupId, segmentsEntryIds, plid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences that the user has permission to view
	 */
	public static List<SegmentsExperience> filterFindByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active,
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().filterFindByG_S_P_A(
			groupId, segmentsEntryIds, plid, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns all the segments experiences where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @return the matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryIds, plid, active);
	}

	/**
	 * Returns a range of all the segments experiences where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active,
		int start, int end) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryIds, plid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active,
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryIds, plid, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching segments experiences
	 */
	public static List<SegmentsExperience> findByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active,
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_S_P_A(
			groupId, segmentsEntryIds, plid, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 */
	public static void removeByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active) {

		getPersistence().removeByG_S_P_A(
			groupId, segmentsEntryId, plid, active);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @return the number of matching segments experiences
	 */
	public static int countByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active) {

		return getPersistence().countByG_S_P_A(
			groupId, segmentsEntryId, plid, active);
	}

	/**
	 * Returns the number of segments experiences where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @return the number of matching segments experiences
	 */
	public static int countByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active) {

		return getPersistence().countByG_S_P_A(
			groupId, segmentsEntryIds, plid, active);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryId the segments entry ID
	 * @param plid the plid
	 * @param active the active
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_S_P_A(
		long groupId, long segmentsEntryId, long plid, boolean active) {

		return getPersistence().filterCountByG_S_P_A(
			groupId, segmentsEntryId, plid, active);
	}

	/**
	 * Returns the number of segments experiences that the user has permission to view where groupId = &#63; and segmentsEntryId = any &#63; and plid = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsEntryIds the segments entry IDs
	 * @param plid the plid
	 * @param active the active
	 * @return the number of matching segments experiences that the user has permission to view
	 */
	public static int filterCountByG_S_P_A(
		long groupId, long[] segmentsEntryIds, long plid, boolean active) {

		return getPersistence().filterCountByG_S_P_A(
			groupId, segmentsEntryIds, plid, active);
	}

	/**
	 * Caches the segments experience in the entity cache if it is enabled.
	 *
	 * @param segmentsExperience the segments experience
	 */
	public static void cacheResult(SegmentsExperience segmentsExperience) {
		getPersistence().cacheResult(segmentsExperience);
	}

	/**
	 * Caches the segments experiences in the entity cache if it is enabled.
	 *
	 * @param segmentsExperiences the segments experiences
	 */
	public static void cacheResult(
		List<SegmentsExperience> segmentsExperiences) {

		getPersistence().cacheResult(segmentsExperiences);
	}

	/**
	 * Creates a new segments experience with the primary key. Does not add the segments experience to the database.
	 *
	 * @param segmentsExperienceId the primary key for the new segments experience
	 * @return the new segments experience
	 */
	public static SegmentsExperience create(long segmentsExperienceId) {
		return getPersistence().create(segmentsExperienceId);
	}

	/**
	 * Removes the segments experience with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param segmentsExperienceId the primary key of the segments experience
	 * @return the segments experience that was removed
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience remove(long segmentsExperienceId)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().remove(segmentsExperienceId);
	}

	public static SegmentsExperience updateImpl(
		SegmentsExperience segmentsExperience) {

		return getPersistence().updateImpl(segmentsExperience);
	}

	/**
	 * Returns the segments experience with the primary key or throws a <code>NoSuchExperienceException</code> if it could not be found.
	 *
	 * @param segmentsExperienceId the primary key of the segments experience
	 * @return the segments experience
	 * @throws NoSuchExperienceException if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience findByPrimaryKey(long segmentsExperienceId)
		throws com.liferay.segments.exception.NoSuchExperienceException {

		return getPersistence().findByPrimaryKey(segmentsExperienceId);
	}

	/**
	 * Returns the segments experience with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param segmentsExperienceId the primary key of the segments experience
	 * @return the segments experience, or <code>null</code> if a segments experience with the primary key could not be found
	 */
	public static SegmentsExperience fetchByPrimaryKey(
		long segmentsExperienceId) {

		return getPersistence().fetchByPrimaryKey(segmentsExperienceId);
	}

	/**
	 * Returns all the segments experiences.
	 *
	 * @return the segments experiences
	 */
	public static List<SegmentsExperience> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the segments experiences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @return the range of segments experiences
	 */
	public static List<SegmentsExperience> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the segments experiences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of segments experiences
	 */
	public static List<SegmentsExperience> findAll(
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the segments experiences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SegmentsExperienceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiences
	 * @param end the upper bound of the range of segments experiences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of segments experiences
	 */
	public static List<SegmentsExperience> findAll(
		int start, int end,
		OrderByComparator<SegmentsExperience> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the segments experiences from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of segments experiences.
	 *
	 * @return the number of segments experiences
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SegmentsExperiencePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		SegmentsExperiencePersistence persistence) {

		_persistence = persistence;
	}

	private static volatile SegmentsExperiencePersistence _persistence;

}