/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTSchemaVersion;
import com.liferay.change.tracking.model.CTSchemaVersionModel;
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

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The base model implementation for the CTSchemaVersion service. Represents a row in the &quot;CTSchemaVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CTSchemaVersionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTSchemaVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTSchemaVersionImpl
 * @generated
 */
public class CTSchemaVersionModelImpl
	extends BaseModelImpl<CTSchemaVersion> implements CTSchemaVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct schema version model instance should use the <code>CTSchemaVersion</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTSchemaVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"schemaVersionId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"schemaContext", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schemaVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("schemaContext", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTSchemaVersion (mvccVersion LONG default 0 not null,schemaVersionId LONG not null primary key,companyId LONG,schemaContext TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table CTSchemaVersion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctSchemaVersion.schemaVersionId DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CTSchemaVersion.schemaVersionId DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SCHEMAVERSIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public CTSchemaVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _schemaVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSchemaVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _schemaVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTSchemaVersion.class;
	}

	@Override
	public String getModelClassName() {
		return CTSchemaVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTSchemaVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTSchemaVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTSchemaVersion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CTSchemaVersion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTSchemaVersion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTSchemaVersion, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTSchemaVersion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTSchemaVersion, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTSchemaVersion, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CTSchemaVersion, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CTSchemaVersion, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CTSchemaVersion, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CTSchemaVersion::getMvccVersion);
			attributeGetterFunctions.put(
				"schemaVersionId", CTSchemaVersion::getSchemaVersionId);
			attributeGetterFunctions.put(
				"companyId", CTSchemaVersion::getCompanyId);
			attributeGetterFunctions.put(
				"schemaContext", CTSchemaVersion::getSchemaContext);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<CTSchemaVersion, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CTSchemaVersion, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<CTSchemaVersion, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CTSchemaVersion, Long>)
					CTSchemaVersion::setMvccVersion);
			attributeSetterBiConsumers.put(
				"schemaVersionId",
				(BiConsumer<CTSchemaVersion, Long>)
					CTSchemaVersion::setSchemaVersionId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CTSchemaVersion, Long>)
					CTSchemaVersion::setCompanyId);
			attributeSetterBiConsumers.put(
				"schemaContext",
				(BiConsumer<CTSchemaVersion, Map<String, Serializable>>)
					CTSchemaVersion::setSchemaContext);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@Override
	public long getSchemaVersionId() {
		return _schemaVersionId;
	}

	@Override
	public void setSchemaVersionId(long schemaVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schemaVersionId = schemaVersionId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@Override
	public Map<String, Serializable> getSchemaContext() {
		return _schemaContext;
	}

	@Override
	public void setSchemaContext(Map<String, Serializable> schemaContext) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_schemaContext = schemaContext;
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
			getCompanyId(), CTSchemaVersion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTSchemaVersion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTSchemaVersion>
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
		CTSchemaVersionImpl ctSchemaVersionImpl = new CTSchemaVersionImpl();

		ctSchemaVersionImpl.setMvccVersion(getMvccVersion());
		ctSchemaVersionImpl.setSchemaVersionId(getSchemaVersionId());
		ctSchemaVersionImpl.setCompanyId(getCompanyId());
		ctSchemaVersionImpl.setSchemaContext(getSchemaContext());

		ctSchemaVersionImpl.resetOriginalValues();

		return ctSchemaVersionImpl;
	}

	@Override
	public CTSchemaVersion cloneWithOriginalValues() {
		CTSchemaVersionImpl ctSchemaVersionImpl = new CTSchemaVersionImpl();

		ctSchemaVersionImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		ctSchemaVersionImpl.setSchemaVersionId(
			this.<Long>getColumnOriginalValue("schemaVersionId"));
		ctSchemaVersionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		ctSchemaVersionImpl.setSchemaContext(
			this.<Map>getColumnOriginalValue("schemaContext"));

		return ctSchemaVersionImpl;
	}

	@Override
	public int compareTo(CTSchemaVersion ctSchemaVersion) {
		int value = 0;

		if (getSchemaVersionId() < ctSchemaVersion.getSchemaVersionId()) {
			value = -1;
		}
		else if (getSchemaVersionId() > ctSchemaVersion.getSchemaVersionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

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

		if (!(object instanceof CTSchemaVersion)) {
			return false;
		}

		CTSchemaVersion ctSchemaVersion = (CTSchemaVersion)object;

		long primaryKey = ctSchemaVersion.getPrimaryKey();

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
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CTSchemaVersion> toCacheModel() {
		CTSchemaVersionCacheModel ctSchemaVersionCacheModel =
			new CTSchemaVersionCacheModel();

		ctSchemaVersionCacheModel.mvccVersion = getMvccVersion();

		ctSchemaVersionCacheModel.schemaVersionId = getSchemaVersionId();

		ctSchemaVersionCacheModel.companyId = getCompanyId();

		ctSchemaVersionCacheModel.schemaContext = getSchemaContext();

		return ctSchemaVersionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTSchemaVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTSchemaVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTSchemaVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CTSchemaVersion)this);

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

		private static final Function<InvocationHandler, CTSchemaVersion>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CTSchemaVersion.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _schemaVersionId;
	private long _companyId;
	private Map<String, Serializable> _schemaContext;

	public <T> T getColumnValue(String columnName) {
		Function<CTSchemaVersion, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CTSchemaVersion)this);
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

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("schemaVersionId", _schemaVersionId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("schemaContext", _schemaContext);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("schemaVersionId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("schemaContext", 8L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CTSchemaVersion _escapedModel;

}