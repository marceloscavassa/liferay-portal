/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry;
import com.liferay.portal.tools.service.builder.test.model.BigDecimalEntryModel;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.math.BigDecimal;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the BigDecimalEntry service. Represents a row in the &quot;BigDecimalEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>BigDecimalEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BigDecimalEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BigDecimalEntryImpl
 * @generated
 */
public class BigDecimalEntryModelImpl
	extends BaseModelImpl<BigDecimalEntry> implements BigDecimalEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a big decimal entry model instance should use the <code>BigDecimalEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "BigDecimalEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"bigDecimalEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"bigDecimalValue", Types.DECIMAL}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("bigDecimalEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("bigDecimalValue", Types.DECIMAL);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BigDecimalEntry (bigDecimalEntryId LONG not null primary key,companyId LONG,bigDecimalValue DECIMAL(30, 16) null)";

	public static final String TABLE_SQL_DROP = "drop table BigDecimalEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY bigDecimalEntry.bigDecimalValue ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BigDecimalEntry.bigDecimalValue ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long BIGDECIMALVALUE_COLUMN_BITMASK = 1L;

	public static final String MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_NAME =
		"BigDecimalEntries_LVEntries";

	public static final Object[][]
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"bigDecimalEntryId", Types.BIGINT},
			{"lvEntryId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_SQL_CREATE =
			"create table BigDecimalEntries_LVEntries (companyId LONG not null,bigDecimalEntryId LONG not null,lvEntryId LONG not null,primary key (bigDecimalEntryId, lvEntryId))";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean
		FINDER_CACHE_ENABLED_BIGDECIMALENTRIES_LVENTRIES = true;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry"));

	public BigDecimalEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _bigDecimalEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBigDecimalEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bigDecimalEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BigDecimalEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BigDecimalEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BigDecimalEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BigDecimalEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BigDecimalEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BigDecimalEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BigDecimalEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BigDecimalEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BigDecimalEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BigDecimalEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BigDecimalEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<BigDecimalEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<BigDecimalEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<BigDecimalEntry, Object>>();

			attributeGetterFunctions.put(
				"bigDecimalEntryId", BigDecimalEntry::getBigDecimalEntryId);
			attributeGetterFunctions.put(
				"companyId", BigDecimalEntry::getCompanyId);
			attributeGetterFunctions.put(
				"bigDecimalValue", BigDecimalEntry::getBigDecimalValue);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<BigDecimalEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<BigDecimalEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<BigDecimalEntry, ?>>();

			attributeSetterBiConsumers.put(
				"bigDecimalEntryId",
				(BiConsumer<BigDecimalEntry, Long>)
					BigDecimalEntry::setBigDecimalEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<BigDecimalEntry, Long>)
					BigDecimalEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"bigDecimalValue",
				(BiConsumer<BigDecimalEntry, BigDecimal>)
					BigDecimalEntry::setBigDecimalValue);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getBigDecimalEntryId() {
		return _bigDecimalEntryId;
	}

	@Override
	public void setBigDecimalEntryId(long bigDecimalEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bigDecimalEntryId = bigDecimalEntryId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@Override
	public BigDecimal getBigDecimalValue() {
		return _bigDecimalValue;
	}

	@Override
	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bigDecimalValue = bigDecimalValue;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public BigDecimal getOriginalBigDecimalValue() {
		return getColumnOriginalValue("bigDecimalValue");
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), BigDecimalEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BigDecimalEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, BigDecimalEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		BigDecimalEntryImpl bigDecimalEntryImpl = new BigDecimalEntryImpl();

		bigDecimalEntryImpl.setBigDecimalEntryId(getBigDecimalEntryId());
		bigDecimalEntryImpl.setCompanyId(getCompanyId());
		bigDecimalEntryImpl.setBigDecimalValue(getBigDecimalValue());

		bigDecimalEntryImpl.resetOriginalValues();

		return bigDecimalEntryImpl;
	}

	@Override
	public BigDecimalEntry cloneWithOriginalValues() {
		BigDecimalEntryImpl bigDecimalEntryImpl = new BigDecimalEntryImpl();

		bigDecimalEntryImpl.setBigDecimalEntryId(
			this.<Long>getColumnOriginalValue("bigDecimalEntryId"));
		bigDecimalEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		bigDecimalEntryImpl.setBigDecimalValue(
			this.<BigDecimal>getColumnOriginalValue("bigDecimalValue"));

		return bigDecimalEntryImpl;
	}

	@Override
	public int compareTo(BigDecimalEntry bigDecimalEntry) {
		int value = 0;

		value = getBigDecimalValue().compareTo(
			bigDecimalEntry.getBigDecimalValue());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BigDecimalEntry)) {
			return false;
		}

		BigDecimalEntry bigDecimalEntry = (BigDecimalEntry)object;

		long primaryKey = bigDecimalEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<BigDecimalEntry> toCacheModel() {
		BigDecimalEntryCacheModel bigDecimalEntryCacheModel =
			new BigDecimalEntryCacheModel();

		bigDecimalEntryCacheModel.bigDecimalEntryId = getBigDecimalEntryId();

		bigDecimalEntryCacheModel.companyId = getCompanyId();

		bigDecimalEntryCacheModel.bigDecimalValue = getBigDecimalValue();

		return bigDecimalEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<BigDecimalEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<BigDecimalEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BigDecimalEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((BigDecimalEntry)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, BigDecimalEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					BigDecimalEntry.class, ModelWrapper.class);

	}

	private long _bigDecimalEntryId;
	private long _companyId;
	private BigDecimal _bigDecimalValue;

	public <T> T getColumnValue(String columnName) {
		Function<BigDecimalEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((BigDecimalEntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("bigDecimalEntryId", _bigDecimalEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("bigDecimalValue", _bigDecimalValue);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("bigDecimalEntryId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("bigDecimalValue", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private BigDecimalEntry _escapedModel;

}