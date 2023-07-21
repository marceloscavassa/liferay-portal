/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.tools.service.builder.test.model.CacheDisabledEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the cache disabled entry service. This utility wraps <code>com.liferay.portal.tools.service.builder.test.service.persistence.impl.CacheDisabledEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheDisabledEntryPersistence
 * @generated
 */
public class CacheDisabledEntryUtil {

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
	public static void clearCache(CacheDisabledEntry cacheDisabledEntry) {
		getPersistence().clearCache(cacheDisabledEntry);
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
	public static Map<Serializable, CacheDisabledEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CacheDisabledEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CacheDisabledEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CacheDisabledEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CacheDisabledEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CacheDisabledEntry update(
		CacheDisabledEntry cacheDisabledEntry) {

		return getPersistence().update(cacheDisabledEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CacheDisabledEntry update(
		CacheDisabledEntry cacheDisabledEntry, ServiceContext serviceContext) {

		return getPersistence().update(cacheDisabledEntry, serviceContext);
	}

	/**
	 * Returns the cache disabled entry where name = &#63; or throws a <code>NoSuchCacheDisabledEntryException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching cache disabled entry
	 * @throws NoSuchCacheDisabledEntryException if a matching cache disabled entry could not be found
	 */
	public static CacheDisabledEntry findByName(String name)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchCacheDisabledEntryException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the cache disabled entry where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching cache disabled entry, or <code>null</code> if a matching cache disabled entry could not be found
	 */
	public static CacheDisabledEntry fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the cache disabled entry where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cache disabled entry, or <code>null</code> if a matching cache disabled entry could not be found
	 */
	public static CacheDisabledEntry fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the cache disabled entry where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the cache disabled entry that was removed
	 */
	public static CacheDisabledEntry removeByName(String name)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchCacheDisabledEntryException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of cache disabled entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching cache disabled entries
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Caches the cache disabled entry in the entity cache if it is enabled.
	 *
	 * @param cacheDisabledEntry the cache disabled entry
	 */
	public static void cacheResult(CacheDisabledEntry cacheDisabledEntry) {
		getPersistence().cacheResult(cacheDisabledEntry);
	}

	/**
	 * Caches the cache disabled entries in the entity cache if it is enabled.
	 *
	 * @param cacheDisabledEntries the cache disabled entries
	 */
	public static void cacheResult(
		List<CacheDisabledEntry> cacheDisabledEntries) {

		getPersistence().cacheResult(cacheDisabledEntries);
	}

	/**
	 * Creates a new cache disabled entry with the primary key. Does not add the cache disabled entry to the database.
	 *
	 * @param cacheDisabledEntryId the primary key for the new cache disabled entry
	 * @return the new cache disabled entry
	 */
	public static CacheDisabledEntry create(long cacheDisabledEntryId) {
		return getPersistence().create(cacheDisabledEntryId);
	}

	/**
	 * Removes the cache disabled entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheDisabledEntryId the primary key of the cache disabled entry
	 * @return the cache disabled entry that was removed
	 * @throws NoSuchCacheDisabledEntryException if a cache disabled entry with the primary key could not be found
	 */
	public static CacheDisabledEntry remove(long cacheDisabledEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchCacheDisabledEntryException {

		return getPersistence().remove(cacheDisabledEntryId);
	}

	public static CacheDisabledEntry updateImpl(
		CacheDisabledEntry cacheDisabledEntry) {

		return getPersistence().updateImpl(cacheDisabledEntry);
	}

	/**
	 * Returns the cache disabled entry with the primary key or throws a <code>NoSuchCacheDisabledEntryException</code> if it could not be found.
	 *
	 * @param cacheDisabledEntryId the primary key of the cache disabled entry
	 * @return the cache disabled entry
	 * @throws NoSuchCacheDisabledEntryException if a cache disabled entry with the primary key could not be found
	 */
	public static CacheDisabledEntry findByPrimaryKey(long cacheDisabledEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchCacheDisabledEntryException {

		return getPersistence().findByPrimaryKey(cacheDisabledEntryId);
	}

	/**
	 * Returns the cache disabled entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheDisabledEntryId the primary key of the cache disabled entry
	 * @return the cache disabled entry, or <code>null</code> if a cache disabled entry with the primary key could not be found
	 */
	public static CacheDisabledEntry fetchByPrimaryKey(
		long cacheDisabledEntryId) {

		return getPersistence().fetchByPrimaryKey(cacheDisabledEntryId);
	}

	/**
	 * Returns all the cache disabled entries.
	 *
	 * @return the cache disabled entries
	 */
	public static List<CacheDisabledEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cache disabled entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheDisabledEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache disabled entries
	 * @param end the upper bound of the range of cache disabled entries (not inclusive)
	 * @return the range of cache disabled entries
	 */
	public static List<CacheDisabledEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cache disabled entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheDisabledEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache disabled entries
	 * @param end the upper bound of the range of cache disabled entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache disabled entries
	 */
	public static List<CacheDisabledEntry> findAll(
		int start, int end,
		OrderByComparator<CacheDisabledEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cache disabled entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheDisabledEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache disabled entries
	 * @param end the upper bound of the range of cache disabled entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cache disabled entries
	 */
	public static List<CacheDisabledEntry> findAll(
		int start, int end,
		OrderByComparator<CacheDisabledEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the cache disabled entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cache disabled entries.
	 *
	 * @return the number of cache disabled entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CacheDisabledEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		CacheDisabledEntryPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile CacheDisabledEntryPersistence _persistence;

}