/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.exception.NoSuchAvailabilityEstimateException;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.model.CommerceAvailabilityEstimateTable;
import com.liferay.commerce.model.impl.CommerceAvailabilityEstimateImpl;
import com.liferay.commerce.model.impl.CommerceAvailabilityEstimateModelImpl;
import com.liferay.commerce.service.persistence.CommerceAvailabilityEstimatePersistence;
import com.liferay.commerce.service.persistence.CommerceAvailabilityEstimateUtil;
import com.liferay.commerce.service.persistence.impl.constants.CommercePersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the commerce availability estimate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Component(service = CommerceAvailabilityEstimatePersistence.class)
public class CommerceAvailabilityEstimatePersistenceImpl
	extends BasePersistenceImpl<CommerceAvailabilityEstimate>
	implements CommerceAvailabilityEstimatePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceAvailabilityEstimateUtil</code> to access the commerce availability estimate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceAvailabilityEstimateImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the commerce availability estimates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce availability estimates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @return the range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CommerceAvailabilityEstimate> list = null;

		if (useFinderCache) {
			list = (List<CommerceAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
						list) {

					if (!uuid.equals(commerceAvailabilityEstimate.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<CommerceAvailabilityEstimate>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByUuid_First(
			String uuid,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByUuid_First(uuid, orderByComparator);

		if (commerceAvailabilityEstimate != null) {
			return commerceAvailabilityEstimate;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAvailabilityEstimateException(sb.toString());
	}

	/**
	 * Returns the first commerce availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce availability estimate, or <code>null</code> if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByUuid_First(
		String uuid,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		List<CommerceAvailabilityEstimate> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByUuid_Last(
			String uuid,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByUuid_Last(uuid, orderByComparator);

		if (commerceAvailabilityEstimate != null) {
			return commerceAvailabilityEstimate;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAvailabilityEstimateException(sb.toString());
	}

	/**
	 * Returns the last commerce availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce availability estimate, or <code>null</code> if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceAvailabilityEstimate> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce availability estimates before and after the current commerce availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the primary key of the current commerce availability estimate
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate[] findByUuid_PrevAndNext(
			long commerceAvailabilityEstimateId, String uuid,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		uuid = Objects.toString(uuid, "");

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			findByPrimaryKey(commerceAvailabilityEstimateId);

		Session session = null;

		try {
			session = openSession();

			CommerceAvailabilityEstimate[] array =
				new CommerceAvailabilityEstimateImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceAvailabilityEstimate, uuid, orderByComparator,
				true);

			array[1] = commerceAvailabilityEstimate;

			array[2] = getByUuid_PrevAndNext(
				session, commerceAvailabilityEstimate, uuid, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAvailabilityEstimate getByUuid_PrevAndNext(
		Session session,
		CommerceAvailabilityEstimate commerceAvailabilityEstimate, String uuid,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAvailabilityEstimate)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceAvailabilityEstimate> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce availability estimates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of commerce availability estimates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce availability estimates
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCEAVAILABILITYESTIMATE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"commerceAvailabilityEstimate.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceAvailabilityEstimate.uuid IS NULL OR commerceAvailabilityEstimate.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @return the range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<CommerceAvailabilityEstimate> list = null;

		if (useFinderCache) {
			list = (List<CommerceAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
						list) {

					if (!uuid.equals(commerceAvailabilityEstimate.getUuid()) ||
						(companyId !=
							commerceAvailabilityEstimate.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<CommerceAvailabilityEstimate>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (commerceAvailabilityEstimate != null) {
			return commerceAvailabilityEstimate;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAvailabilityEstimateException(sb.toString());
	}

	/**
	 * Returns the first commerce availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce availability estimate, or <code>null</code> if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		List<CommerceAvailabilityEstimate> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (commerceAvailabilityEstimate != null) {
			return commerceAvailabilityEstimate;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAvailabilityEstimateException(sb.toString());
	}

	/**
	 * Returns the last commerce availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce availability estimate, or <code>null</code> if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceAvailabilityEstimate> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce availability estimates before and after the current commerce availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the primary key of the current commerce availability estimate
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate[] findByUuid_C_PrevAndNext(
			long commerceAvailabilityEstimateId, String uuid, long companyId,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		uuid = Objects.toString(uuid, "");

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			findByPrimaryKey(commerceAvailabilityEstimateId);

		Session session = null;

		try {
			session = openSession();

			CommerceAvailabilityEstimate[] array =
				new CommerceAvailabilityEstimateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceAvailabilityEstimate, uuid, companyId,
				orderByComparator, true);

			array[1] = commerceAvailabilityEstimate;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceAvailabilityEstimate, uuid, companyId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAvailabilityEstimate getByUuid_C_PrevAndNext(
		Session session,
		CommerceAvailabilityEstimate commerceAvailabilityEstimate, String uuid,
		long companyId,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAvailabilityEstimate)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceAvailabilityEstimate> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce availability estimates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of commerce availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce availability estimates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCEAVAILABILITYESTIMATE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"commerceAvailabilityEstimate.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceAvailabilityEstimate.uuid IS NULL OR commerceAvailabilityEstimate.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceAvailabilityEstimate.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce availability estimates where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce availability estimates where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @return the range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceAvailabilityEstimate> list = null;

		if (useFinderCache) {
			list = (List<CommerceAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
						list) {

					if (companyId !=
							commerceAvailabilityEstimate.getCompanyId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<CommerceAvailabilityEstimate>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce availability estimate in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByCompanyId_First(companyId, orderByComparator);

		if (commerceAvailabilityEstimate != null) {
			return commerceAvailabilityEstimate;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAvailabilityEstimateException(sb.toString());
	}

	/**
	 * Returns the first commerce availability estimate in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce availability estimate, or <code>null</code> if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		List<CommerceAvailabilityEstimate> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce availability estimate in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByCompanyId_Last(companyId, orderByComparator);

		if (commerceAvailabilityEstimate != null) {
			return commerceAvailabilityEstimate;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAvailabilityEstimateException(sb.toString());
	}

	/**
	 * Returns the last commerce availability estimate in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce availability estimate, or <code>null</code> if a matching commerce availability estimate could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceAvailabilityEstimate> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce availability estimates before and after the current commerce availability estimate in the ordered set where companyId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the primary key of the current commerce availability estimate
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate[] findByCompanyId_PrevAndNext(
			long commerceAvailabilityEstimateId, long companyId,
			OrderByComparator<CommerceAvailabilityEstimate> orderByComparator)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			findByPrimaryKey(commerceAvailabilityEstimateId);

		Session session = null;

		try {
			session = openSession();

			CommerceAvailabilityEstimate[] array =
				new CommerceAvailabilityEstimateImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceAvailabilityEstimate, companyId,
				orderByComparator, true);

			array[1] = commerceAvailabilityEstimate;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceAvailabilityEstimate, companyId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAvailabilityEstimate getByCompanyId_PrevAndNext(
		Session session,
		CommerceAvailabilityEstimate commerceAvailabilityEstimate,
		long companyId,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAvailabilityEstimate)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceAvailabilityEstimate> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce availability estimates where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of commerce availability estimates where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce availability estimates
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCEAVAILABILITYESTIMATE_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceAvailabilityEstimate.companyId = ?";

	public CommerceAvailabilityEstimatePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(CommerceAvailabilityEstimate.class);

		setModelImplClass(CommerceAvailabilityEstimateImpl.class);
		setModelPKClass(long.class);

		setTable(CommerceAvailabilityEstimateTable.INSTANCE);
	}

	/**
	 * Caches the commerce availability estimate in the entity cache if it is enabled.
	 *
	 * @param commerceAvailabilityEstimate the commerce availability estimate
	 */
	@Override
	public void cacheResult(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		entityCache.putResult(
			CommerceAvailabilityEstimateImpl.class,
			commerceAvailabilityEstimate.getPrimaryKey(),
			commerceAvailabilityEstimate);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the commerce availability estimates in the entity cache if it is enabled.
	 *
	 * @param commerceAvailabilityEstimates the commerce availability estimates
	 */
	@Override
	public void cacheResult(
		List<CommerceAvailabilityEstimate> commerceAvailabilityEstimates) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commerceAvailabilityEstimates.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				commerceAvailabilityEstimates) {

			if (entityCache.getResult(
					CommerceAvailabilityEstimateImpl.class,
					commerceAvailabilityEstimate.getPrimaryKey()) == null) {

				cacheResult(commerceAvailabilityEstimate);
			}
		}
	}

	/**
	 * Clears the cache for all commerce availability estimates.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceAvailabilityEstimateImpl.class);

		finderCache.clearCache(CommerceAvailabilityEstimateImpl.class);
	}

	/**
	 * Clears the cache for the commerce availability estimate.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		entityCache.removeResult(
			CommerceAvailabilityEstimateImpl.class,
			commerceAvailabilityEstimate);
	}

	@Override
	public void clearCache(
		List<CommerceAvailabilityEstimate> commerceAvailabilityEstimates) {

		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				commerceAvailabilityEstimates) {

			entityCache.removeResult(
				CommerceAvailabilityEstimateImpl.class,
				commerceAvailabilityEstimate);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommerceAvailabilityEstimateImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceAvailabilityEstimateImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new commerce availability estimate with the primary key. Does not add the commerce availability estimate to the database.
	 *
	 * @param commerceAvailabilityEstimateId the primary key for the new commerce availability estimate
	 * @return the new commerce availability estimate
	 */
	@Override
	public CommerceAvailabilityEstimate create(
		long commerceAvailabilityEstimateId) {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			new CommerceAvailabilityEstimateImpl();

		commerceAvailabilityEstimate.setNew(true);
		commerceAvailabilityEstimate.setPrimaryKey(
			commerceAvailabilityEstimateId);

		String uuid = _portalUUID.generate();

		commerceAvailabilityEstimate.setUuid(uuid);

		commerceAvailabilityEstimate.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceAvailabilityEstimate;
	}

	/**
	 * Removes the commerce availability estimate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAvailabilityEstimateId the primary key of the commerce availability estimate
	 * @return the commerce availability estimate that was removed
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate remove(
			long commerceAvailabilityEstimateId)
		throws NoSuchAvailabilityEstimateException {

		return remove((Serializable)commerceAvailabilityEstimateId);
	}

	/**
	 * Removes the commerce availability estimate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce availability estimate
	 * @return the commerce availability estimate that was removed
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate remove(Serializable primaryKey)
		throws NoSuchAvailabilityEstimateException {

		Session session = null;

		try {
			session = openSession();

			CommerceAvailabilityEstimate commerceAvailabilityEstimate =
				(CommerceAvailabilityEstimate)session.get(
					CommerceAvailabilityEstimateImpl.class, primaryKey);

			if (commerceAvailabilityEstimate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAvailabilityEstimateException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceAvailabilityEstimate);
		}
		catch (NoSuchAvailabilityEstimateException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CommerceAvailabilityEstimate removeImpl(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceAvailabilityEstimate)) {
				commerceAvailabilityEstimate =
					(CommerceAvailabilityEstimate)session.get(
						CommerceAvailabilityEstimateImpl.class,
						commerceAvailabilityEstimate.getPrimaryKeyObj());
			}

			if (commerceAvailabilityEstimate != null) {
				session.delete(commerceAvailabilityEstimate);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceAvailabilityEstimate != null) {
			clearCache(commerceAvailabilityEstimate);
		}

		return commerceAvailabilityEstimate;
	}

	@Override
	public CommerceAvailabilityEstimate updateImpl(
		CommerceAvailabilityEstimate commerceAvailabilityEstimate) {

		boolean isNew = commerceAvailabilityEstimate.isNew();

		if (!(commerceAvailabilityEstimate instanceof
				CommerceAvailabilityEstimateModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceAvailabilityEstimate.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceAvailabilityEstimate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAvailabilityEstimate proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAvailabilityEstimate implementation " +
					commerceAvailabilityEstimate.getClass());
		}

		CommerceAvailabilityEstimateModelImpl
			commerceAvailabilityEstimateModelImpl =
				(CommerceAvailabilityEstimateModelImpl)
					commerceAvailabilityEstimate;

		if (Validator.isNull(commerceAvailabilityEstimate.getUuid())) {
			String uuid = _portalUUID.generate();

			commerceAvailabilityEstimate.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (commerceAvailabilityEstimate.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceAvailabilityEstimate.setCreateDate(date);
			}
			else {
				commerceAvailabilityEstimate.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!commerceAvailabilityEstimateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceAvailabilityEstimate.setModifiedDate(date);
			}
			else {
				commerceAvailabilityEstimate.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceAvailabilityEstimate);
			}
			else {
				commerceAvailabilityEstimate =
					(CommerceAvailabilityEstimate)session.merge(
						commerceAvailabilityEstimate);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommerceAvailabilityEstimateImpl.class,
			commerceAvailabilityEstimateModelImpl, false, true);

		if (isNew) {
			commerceAvailabilityEstimate.setNew(false);
		}

		commerceAvailabilityEstimate.resetOriginalValues();

		return commerceAvailabilityEstimate;
	}

	/**
	 * Returns the commerce availability estimate with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce availability estimate
	 * @return the commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchAvailabilityEstimateException {

		CommerceAvailabilityEstimate commerceAvailabilityEstimate =
			fetchByPrimaryKey(primaryKey);

		if (commerceAvailabilityEstimate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAvailabilityEstimateException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceAvailabilityEstimate;
	}

	/**
	 * Returns the commerce availability estimate with the primary key or throws a <code>NoSuchAvailabilityEstimateException</code> if it could not be found.
	 *
	 * @param commerceAvailabilityEstimateId the primary key of the commerce availability estimate
	 * @return the commerce availability estimate
	 * @throws NoSuchAvailabilityEstimateException if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate findByPrimaryKey(
			long commerceAvailabilityEstimateId)
		throws NoSuchAvailabilityEstimateException {

		return findByPrimaryKey((Serializable)commerceAvailabilityEstimateId);
	}

	/**
	 * Returns the commerce availability estimate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceAvailabilityEstimateId the primary key of the commerce availability estimate
	 * @return the commerce availability estimate, or <code>null</code> if a commerce availability estimate with the primary key could not be found
	 */
	@Override
	public CommerceAvailabilityEstimate fetchByPrimaryKey(
		long commerceAvailabilityEstimateId) {

		return fetchByPrimaryKey((Serializable)commerceAvailabilityEstimateId);
	}

	/**
	 * Returns all the commerce availability estimates.
	 *
	 * @return the commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @return the range of commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findAll(
		int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce availability estimates
	 * @param end the upper bound of the range of commerce availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce availability estimates
	 */
	@Override
	public List<CommerceAvailabilityEstimate> findAll(
		int start, int end,
		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceAvailabilityEstimate> list = null;

		if (useFinderCache) {
			list = (List<CommerceAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCEAVAILABILITYESTIMATE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEAVAILABILITYESTIMATE;

				sql = sql.concat(
					CommerceAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommerceAvailabilityEstimate>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce availability estimates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate :
				findAll()) {

			remove(commerceAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of commerce availability estimates.
	 *
	 * @return the number of commerce availability estimates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_COMMERCEAVAILABILITYESTIMATE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "commerceAvailabilityEstimateId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMERCEAVAILABILITYESTIMATE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceAvailabilityEstimateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce availability estimate persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		CommerceAvailabilityEstimateUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CommerceAvailabilityEstimateUtil.setPersistence(null);

		entityCache.removeCache(
			CommerceAvailabilityEstimateImpl.class.getName());
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEAVAILABILITYESTIMATE =
		"SELECT commerceAvailabilityEstimate FROM CommerceAvailabilityEstimate commerceAvailabilityEstimate";

	private static final String _SQL_SELECT_COMMERCEAVAILABILITYESTIMATE_WHERE =
		"SELECT commerceAvailabilityEstimate FROM CommerceAvailabilityEstimate commerceAvailabilityEstimate WHERE ";

	private static final String _SQL_COUNT_COMMERCEAVAILABILITYESTIMATE =
		"SELECT COUNT(commerceAvailabilityEstimate) FROM CommerceAvailabilityEstimate commerceAvailabilityEstimate";

	private static final String _SQL_COUNT_COMMERCEAVAILABILITYESTIMATE_WHERE =
		"SELECT COUNT(commerceAvailabilityEstimate) FROM CommerceAvailabilityEstimate commerceAvailabilityEstimate WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceAvailabilityEstimate.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceAvailabilityEstimate exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceAvailabilityEstimate exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAvailabilityEstimatePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private PortalUUID _portalUUID;

}