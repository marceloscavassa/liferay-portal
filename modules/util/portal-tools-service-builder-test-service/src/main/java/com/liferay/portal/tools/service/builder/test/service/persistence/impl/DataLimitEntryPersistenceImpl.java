/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
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
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDataLimitEntryException;
import com.liferay.portal.tools.service.builder.test.model.DataLimitEntry;
import com.liferay.portal.tools.service.builder.test.model.DataLimitEntryTable;
import com.liferay.portal.tools.service.builder.test.model.impl.DataLimitEntryImpl;
import com.liferay.portal.tools.service.builder.test.model.impl.DataLimitEntryModelImpl;
import com.liferay.portal.tools.service.builder.test.service.persistence.DataLimitEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DataLimitEntryUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the data limit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DataLimitEntryPersistenceImpl
	extends BasePersistenceImpl<DataLimitEntry>
	implements DataLimitEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DataLimitEntryUtil</code> to access the data limit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DataLimitEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DataLimitEntryPersistenceImpl() {
		setModelClass(DataLimitEntry.class);

		setModelImplClass(DataLimitEntryImpl.class);
		setModelPKClass(long.class);

		setTable(DataLimitEntryTable.INSTANCE);
	}

	/**
	 * Caches the data limit entry in the entity cache if it is enabled.
	 *
	 * @param dataLimitEntry the data limit entry
	 */
	@Override
	public void cacheResult(DataLimitEntry dataLimitEntry) {
		entityCache.putResult(
			DataLimitEntryImpl.class, dataLimitEntry.getPrimaryKey(),
			dataLimitEntry);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the data limit entries in the entity cache if it is enabled.
	 *
	 * @param dataLimitEntries the data limit entries
	 */
	@Override
	public void cacheResult(List<DataLimitEntry> dataLimitEntries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (dataLimitEntries.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DataLimitEntry dataLimitEntry : dataLimitEntries) {
			if (entityCache.getResult(
					DataLimitEntryImpl.class, dataLimitEntry.getPrimaryKey()) ==
						null) {

				cacheResult(dataLimitEntry);
			}
		}
	}

	/**
	 * Clears the cache for all data limit entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DataLimitEntryImpl.class);

		finderCache.clearCache(DataLimitEntryImpl.class);
	}

	/**
	 * Clears the cache for the data limit entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataLimitEntry dataLimitEntry) {
		entityCache.removeResult(DataLimitEntryImpl.class, dataLimitEntry);
	}

	@Override
	public void clearCache(List<DataLimitEntry> dataLimitEntries) {
		for (DataLimitEntry dataLimitEntry : dataLimitEntries) {
			entityCache.removeResult(DataLimitEntryImpl.class, dataLimitEntry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DataLimitEntryImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DataLimitEntryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new data limit entry with the primary key. Does not add the data limit entry to the database.
	 *
	 * @param dataLimitEntryId the primary key for the new data limit entry
	 * @return the new data limit entry
	 */
	@Override
	public DataLimitEntry create(long dataLimitEntryId) {
		DataLimitEntry dataLimitEntry = new DataLimitEntryImpl();

		dataLimitEntry.setNew(true);
		dataLimitEntry.setPrimaryKey(dataLimitEntryId);

		dataLimitEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return dataLimitEntry;
	}

	/**
	 * Removes the data limit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataLimitEntryId the primary key of the data limit entry
	 * @return the data limit entry that was removed
	 * @throws NoSuchDataLimitEntryException if a data limit entry with the primary key could not be found
	 */
	@Override
	public DataLimitEntry remove(long dataLimitEntryId)
		throws NoSuchDataLimitEntryException {

		return remove((Serializable)dataLimitEntryId);
	}

	/**
	 * Removes the data limit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data limit entry
	 * @return the data limit entry that was removed
	 * @throws NoSuchDataLimitEntryException if a data limit entry with the primary key could not be found
	 */
	@Override
	public DataLimitEntry remove(Serializable primaryKey)
		throws NoSuchDataLimitEntryException {

		Session session = null;

		try {
			session = openSession();

			DataLimitEntry dataLimitEntry = (DataLimitEntry)session.get(
				DataLimitEntryImpl.class, primaryKey);

			if (dataLimitEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataLimitEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(dataLimitEntry);
		}
		catch (NoSuchDataLimitEntryException noSuchEntityException) {
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
	protected DataLimitEntry removeImpl(DataLimitEntry dataLimitEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataLimitEntry)) {
				dataLimitEntry = (DataLimitEntry)session.get(
					DataLimitEntryImpl.class,
					dataLimitEntry.getPrimaryKeyObj());
			}

			if (dataLimitEntry != null) {
				session.delete(dataLimitEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dataLimitEntry != null) {
			clearCache(dataLimitEntry);
		}

		return dataLimitEntry;
	}

	@Override
	public DataLimitEntry updateImpl(DataLimitEntry dataLimitEntry) {
		boolean isNew = dataLimitEntry.isNew();

		if (!(dataLimitEntry instanceof DataLimitEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dataLimitEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					dataLimitEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dataLimitEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DataLimitEntry implementation " +
					dataLimitEntry.getClass());
		}

		DataLimitEntryModelImpl dataLimitEntryModelImpl =
			(DataLimitEntryModelImpl)dataLimitEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (dataLimitEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				dataLimitEntry.setCreateDate(date);
			}
			else {
				dataLimitEntry.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!dataLimitEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dataLimitEntry.setModifiedDate(date);
			}
			else {
				dataLimitEntry.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(dataLimitEntry);
			}
			else {
				dataLimitEntry = (DataLimitEntry)session.merge(dataLimitEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DataLimitEntryImpl.class, dataLimitEntry, false, true);

		if (isNew) {
			dataLimitEntry.setNew(false);
		}

		dataLimitEntry.resetOriginalValues();

		return dataLimitEntry;
	}

	/**
	 * Returns the data limit entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data limit entry
	 * @return the data limit entry
	 * @throws NoSuchDataLimitEntryException if a data limit entry with the primary key could not be found
	 */
	@Override
	public DataLimitEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataLimitEntryException {

		DataLimitEntry dataLimitEntry = fetchByPrimaryKey(primaryKey);

		if (dataLimitEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataLimitEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return dataLimitEntry;
	}

	/**
	 * Returns the data limit entry with the primary key or throws a <code>NoSuchDataLimitEntryException</code> if it could not be found.
	 *
	 * @param dataLimitEntryId the primary key of the data limit entry
	 * @return the data limit entry
	 * @throws NoSuchDataLimitEntryException if a data limit entry with the primary key could not be found
	 */
	@Override
	public DataLimitEntry findByPrimaryKey(long dataLimitEntryId)
		throws NoSuchDataLimitEntryException {

		return findByPrimaryKey((Serializable)dataLimitEntryId);
	}

	/**
	 * Returns the data limit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dataLimitEntryId the primary key of the data limit entry
	 * @return the data limit entry, or <code>null</code> if a data limit entry with the primary key could not be found
	 */
	@Override
	public DataLimitEntry fetchByPrimaryKey(long dataLimitEntryId) {
		return fetchByPrimaryKey((Serializable)dataLimitEntryId);
	}

	/**
	 * Returns all the data limit entries.
	 *
	 * @return the data limit entries
	 */
	@Override
	public List<DataLimitEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the data limit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataLimitEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data limit entries
	 * @param end the upper bound of the range of data limit entries (not inclusive)
	 * @return the range of data limit entries
	 */
	@Override
	public List<DataLimitEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the data limit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataLimitEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data limit entries
	 * @param end the upper bound of the range of data limit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of data limit entries
	 */
	@Override
	public List<DataLimitEntry> findAll(
		int start, int end,
		OrderByComparator<DataLimitEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the data limit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DataLimitEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of data limit entries
	 * @param end the upper bound of the range of data limit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of data limit entries
	 */
	@Override
	public List<DataLimitEntry> findAll(
		int start, int end, OrderByComparator<DataLimitEntry> orderByComparator,
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

		List<DataLimitEntry> list = null;

		if (useFinderCache) {
			list = (List<DataLimitEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DATALIMITENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DATALIMITENTRY;

				sql = sql.concat(DataLimitEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DataLimitEntry>)QueryUtil.list(
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
	 * Removes all the data limit entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DataLimitEntry dataLimitEntry : findAll()) {
			remove(dataLimitEntry);
		}
	}

	/**
	 * Returns the number of data limit entries.
	 *
	 * @return the number of data limit entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DATALIMITENTRY);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "dataLimitEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DATALIMITENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DataLimitEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the data limit entry persistence.
	 */
	public void afterPropertiesSet() {
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

		DataLimitEntryUtil.setPersistence(this);
	}

	public void destroy() {
		DataLimitEntryUtil.setPersistence(null);

		entityCache.removeCache(DataLimitEntryImpl.class.getName());
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DATALIMITENTRY =
		"SELECT dataLimitEntry FROM DataLimitEntry dataLimitEntry";

	private static final String _SQL_COUNT_DATALIMITENTRY =
		"SELECT COUNT(dataLimitEntry) FROM DataLimitEntry dataLimitEntry";

	private static final String _ORDER_BY_ENTITY_ALIAS = "dataLimitEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DataLimitEntry exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DataLimitEntryPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}