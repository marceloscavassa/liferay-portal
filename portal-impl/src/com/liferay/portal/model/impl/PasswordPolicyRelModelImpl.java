/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.PasswordPolicyRel;
import com.liferay.portal.kernel.model.PasswordPolicyRelModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the PasswordPolicyRel service. Represents a row in the &quot;PasswordPolicyRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PasswordPolicyRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PasswordPolicyRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRelImpl
 * @generated
 */
public class PasswordPolicyRelModelImpl
	extends BaseModelImpl<PasswordPolicyRel> implements PasswordPolicyRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a password policy rel model instance should use the <code>PasswordPolicyRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "PasswordPolicyRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"passwordPolicyRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"passwordPolicyId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("passwordPolicyRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("passwordPolicyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PasswordPolicyRel (mvccVersion LONG default 0 not null,passwordPolicyRelId LONG not null primary key,companyId LONG,passwordPolicyId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP = "drop table PasswordPolicyRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY passwordPolicyRel.passwordPolicyRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PasswordPolicyRel.passwordPolicyRelId ASC";

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
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PASSWORDPOLICYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PASSWORDPOLICYRELID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.PasswordPolicyRel"));

	public PasswordPolicyRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _passwordPolicyRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPasswordPolicyRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _passwordPolicyRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PasswordPolicyRel.class;
	}

	@Override
	public String getModelClassName() {
		return PasswordPolicyRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PasswordPolicyRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PasswordPolicyRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PasswordPolicyRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PasswordPolicyRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PasswordPolicyRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PasswordPolicyRel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PasswordPolicyRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PasswordPolicyRel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PasswordPolicyRel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<PasswordPolicyRel, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<PasswordPolicyRel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<PasswordPolicyRel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", PasswordPolicyRel::getMvccVersion);
			attributeGetterFunctions.put(
				"passwordPolicyRelId",
				PasswordPolicyRel::getPasswordPolicyRelId);
			attributeGetterFunctions.put(
				"companyId", PasswordPolicyRel::getCompanyId);
			attributeGetterFunctions.put(
				"passwordPolicyId", PasswordPolicyRel::getPasswordPolicyId);
			attributeGetterFunctions.put(
				"classNameId", PasswordPolicyRel::getClassNameId);
			attributeGetterFunctions.put(
				"classPK", PasswordPolicyRel::getClassPK);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<PasswordPolicyRel, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<PasswordPolicyRel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<PasswordPolicyRel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<PasswordPolicyRel, Long>)
					PasswordPolicyRel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"passwordPolicyRelId",
				(BiConsumer<PasswordPolicyRel, Long>)
					PasswordPolicyRel::setPasswordPolicyRelId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<PasswordPolicyRel, Long>)
					PasswordPolicyRel::setCompanyId);
			attributeSetterBiConsumers.put(
				"passwordPolicyId",
				(BiConsumer<PasswordPolicyRel, Long>)
					PasswordPolicyRel::setPasswordPolicyId);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<PasswordPolicyRel, Long>)
					PasswordPolicyRel::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<PasswordPolicyRel, Long>)
					PasswordPolicyRel::setClassPK);

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
	public long getPasswordPolicyRelId() {
		return _passwordPolicyRelId;
	}

	@Override
	public void setPasswordPolicyRelId(long passwordPolicyRelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_passwordPolicyRelId = passwordPolicyRelId;
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
	public long getPasswordPolicyId() {
		return _passwordPolicyId;
	}

	@Override
	public void setPasswordPolicyId(long passwordPolicyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_passwordPolicyId = passwordPolicyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalPasswordPolicyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("passwordPolicyId"));
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
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
			getCompanyId(), PasswordPolicyRel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PasswordPolicyRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PasswordPolicyRel>
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
		PasswordPolicyRelImpl passwordPolicyRelImpl =
			new PasswordPolicyRelImpl();

		passwordPolicyRelImpl.setMvccVersion(getMvccVersion());
		passwordPolicyRelImpl.setPasswordPolicyRelId(getPasswordPolicyRelId());
		passwordPolicyRelImpl.setCompanyId(getCompanyId());
		passwordPolicyRelImpl.setPasswordPolicyId(getPasswordPolicyId());
		passwordPolicyRelImpl.setClassNameId(getClassNameId());
		passwordPolicyRelImpl.setClassPK(getClassPK());

		passwordPolicyRelImpl.resetOriginalValues();

		return passwordPolicyRelImpl;
	}

	@Override
	public PasswordPolicyRel cloneWithOriginalValues() {
		PasswordPolicyRelImpl passwordPolicyRelImpl =
			new PasswordPolicyRelImpl();

		passwordPolicyRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		passwordPolicyRelImpl.setPasswordPolicyRelId(
			this.<Long>getColumnOriginalValue("passwordPolicyRelId"));
		passwordPolicyRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		passwordPolicyRelImpl.setPasswordPolicyId(
			this.<Long>getColumnOriginalValue("passwordPolicyId"));
		passwordPolicyRelImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		passwordPolicyRelImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));

		return passwordPolicyRelImpl;
	}

	@Override
	public int compareTo(PasswordPolicyRel passwordPolicyRel) {
		long primaryKey = passwordPolicyRel.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PasswordPolicyRel)) {
			return false;
		}

		PasswordPolicyRel passwordPolicyRel = (PasswordPolicyRel)object;

		long primaryKey = passwordPolicyRel.getPrimaryKey();

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
	public CacheModel<PasswordPolicyRel> toCacheModel() {
		PasswordPolicyRelCacheModel passwordPolicyRelCacheModel =
			new PasswordPolicyRelCacheModel();

		passwordPolicyRelCacheModel.mvccVersion = getMvccVersion();

		passwordPolicyRelCacheModel.passwordPolicyRelId =
			getPasswordPolicyRelId();

		passwordPolicyRelCacheModel.companyId = getCompanyId();

		passwordPolicyRelCacheModel.passwordPolicyId = getPasswordPolicyId();

		passwordPolicyRelCacheModel.classNameId = getClassNameId();

		passwordPolicyRelCacheModel.classPK = getClassPK();

		return passwordPolicyRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PasswordPolicyRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PasswordPolicyRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PasswordPolicyRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(PasswordPolicyRel)this);

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

		private static final Function<InvocationHandler, PasswordPolicyRel>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					PasswordPolicyRel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _passwordPolicyRelId;
	private long _companyId;
	private long _passwordPolicyId;
	private long _classNameId;
	private long _classPK;

	public <T> T getColumnValue(String columnName) {
		Function<PasswordPolicyRel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((PasswordPolicyRel)this);
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
		_columnOriginalValues.put("passwordPolicyRelId", _passwordPolicyRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("passwordPolicyId", _passwordPolicyId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("passwordPolicyRelId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("passwordPolicyId", 8L);

		columnBitmasks.put("classNameId", 16L);

		columnBitmasks.put("classPK", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private PasswordPolicyRel _escapedModel;

}