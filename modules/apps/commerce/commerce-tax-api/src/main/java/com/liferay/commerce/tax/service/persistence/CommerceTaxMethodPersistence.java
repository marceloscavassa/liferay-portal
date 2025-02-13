/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.service.persistence;

import com.liferay.commerce.tax.exception.NoSuchTaxMethodException;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the commerce tax method service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceTaxMethodUtil
 * @generated
 */
@ProviderType
public interface CommerceTaxMethodPersistence
	extends BasePersistence<CommerceTaxMethod> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceTaxMethodUtil} to access the commerce tax method persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the commerce tax methods where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByGroupId(long groupId);

	/**
	 * Returns a range of all the commerce tax methods where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @return the range of matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the commerce tax methods where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns an ordered range of all the commerce tax methods where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce tax method in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax method
	 * @throws NoSuchTaxMethodException if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
				orderByComparator)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the first commerce tax method in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax method, or <code>null</code> if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns the last commerce tax method in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax method
	 * @throws NoSuchTaxMethodException if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
				orderByComparator)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the last commerce tax method in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax method, or <code>null</code> if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns the commerce tax methods before and after the current commerce tax method in the ordered set where groupId = &#63;.
	 *
	 * @param commerceTaxMethodId the primary key of the current commerce tax method
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tax method
	 * @throws NoSuchTaxMethodException if a commerce tax method with the primary key could not be found
	 */
	public CommerceTaxMethod[] findByGroupId_PrevAndNext(
			long commerceTaxMethodId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
				orderByComparator)
		throws NoSuchTaxMethodException;

	/**
	 * Removes all the commerce tax methods where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of commerce tax methods where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce tax methods
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the commerce tax method where groupId = &#63; and engineKey = &#63; or throws a <code>NoSuchTaxMethodException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the matching commerce tax method
	 * @throws NoSuchTaxMethodException if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod findByG_E(long groupId, String engineKey)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the commerce tax method where groupId = &#63; and engineKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the matching commerce tax method, or <code>null</code> if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod fetchByG_E(long groupId, String engineKey);

	/**
	 * Returns the commerce tax method where groupId = &#63; and engineKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce tax method, or <code>null</code> if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod fetchByG_E(
		long groupId, String engineKey, boolean useFinderCache);

	/**
	 * Removes the commerce tax method where groupId = &#63; and engineKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the commerce tax method that was removed
	 */
	public CommerceTaxMethod removeByG_E(long groupId, String engineKey)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the number of commerce tax methods where groupId = &#63; and engineKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the number of matching commerce tax methods
	 */
	public int countByG_E(long groupId, String engineKey);

	/**
	 * Returns all the commerce tax methods where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByG_A(
		long groupId, boolean active);

	/**
	 * Returns a range of all the commerce tax methods where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @return the range of matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByG_A(
		long groupId, boolean active, int start, int end);

	/**
	 * Returns an ordered range of all the commerce tax methods where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns an ordered range of all the commerce tax methods where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findByG_A(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce tax method in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax method
	 * @throws NoSuchTaxMethodException if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod findByG_A_First(
			long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
				orderByComparator)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the first commerce tax method in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tax method, or <code>null</code> if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod fetchByG_A_First(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns the last commerce tax method in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax method
	 * @throws NoSuchTaxMethodException if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod findByG_A_Last(
			long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
				orderByComparator)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the last commerce tax method in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tax method, or <code>null</code> if a matching commerce tax method could not be found
	 */
	public CommerceTaxMethod fetchByG_A_Last(
		long groupId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns the commerce tax methods before and after the current commerce tax method in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param commerceTaxMethodId the primary key of the current commerce tax method
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tax method
	 * @throws NoSuchTaxMethodException if a commerce tax method with the primary key could not be found
	 */
	public CommerceTaxMethod[] findByG_A_PrevAndNext(
			long commerceTaxMethodId, long groupId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
				orderByComparator)
		throws NoSuchTaxMethodException;

	/**
	 * Removes all the commerce tax methods where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 */
	public void removeByG_A(long groupId, boolean active);

	/**
	 * Returns the number of commerce tax methods where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching commerce tax methods
	 */
	public int countByG_A(long groupId, boolean active);

	/**
	 * Caches the commerce tax method in the entity cache if it is enabled.
	 *
	 * @param commerceTaxMethod the commerce tax method
	 */
	public void cacheResult(CommerceTaxMethod commerceTaxMethod);

	/**
	 * Caches the commerce tax methods in the entity cache if it is enabled.
	 *
	 * @param commerceTaxMethods the commerce tax methods
	 */
	public void cacheResult(
		java.util.List<CommerceTaxMethod> commerceTaxMethods);

	/**
	 * Creates a new commerce tax method with the primary key. Does not add the commerce tax method to the database.
	 *
	 * @param commerceTaxMethodId the primary key for the new commerce tax method
	 * @return the new commerce tax method
	 */
	public CommerceTaxMethod create(long commerceTaxMethodId);

	/**
	 * Removes the commerce tax method with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceTaxMethodId the primary key of the commerce tax method
	 * @return the commerce tax method that was removed
	 * @throws NoSuchTaxMethodException if a commerce tax method with the primary key could not be found
	 */
	public CommerceTaxMethod remove(long commerceTaxMethodId)
		throws NoSuchTaxMethodException;

	public CommerceTaxMethod updateImpl(CommerceTaxMethod commerceTaxMethod);

	/**
	 * Returns the commerce tax method with the primary key or throws a <code>NoSuchTaxMethodException</code> if it could not be found.
	 *
	 * @param commerceTaxMethodId the primary key of the commerce tax method
	 * @return the commerce tax method
	 * @throws NoSuchTaxMethodException if a commerce tax method with the primary key could not be found
	 */
	public CommerceTaxMethod findByPrimaryKey(long commerceTaxMethodId)
		throws NoSuchTaxMethodException;

	/**
	 * Returns the commerce tax method with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceTaxMethodId the primary key of the commerce tax method
	 * @return the commerce tax method, or <code>null</code> if a commerce tax method with the primary key could not be found
	 */
	public CommerceTaxMethod fetchByPrimaryKey(long commerceTaxMethodId);

	/**
	 * Returns all the commerce tax methods.
	 *
	 * @return the commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findAll();

	/**
	 * Returns a range of all the commerce tax methods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @return the range of commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the commerce tax methods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator);

	/**
	 * Returns an ordered range of all the commerce tax methods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceTaxMethodModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tax methods
	 * @param end the upper bound of the range of commerce tax methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce tax methods
	 */
	public java.util.List<CommerceTaxMethod> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommerceTaxMethod>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce tax methods from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce tax methods.
	 *
	 * @return the number of commerce tax methods
	 */
	public int countAll();

}