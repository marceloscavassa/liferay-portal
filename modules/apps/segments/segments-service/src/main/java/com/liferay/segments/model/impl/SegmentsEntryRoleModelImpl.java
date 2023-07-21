/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.segments.model.SegmentsEntryRole;
import com.liferay.segments.model.SegmentsEntryRoleModel;

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
 * The base model implementation for the SegmentsEntryRole service. Represents a row in the &quot;SegmentsEntryRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SegmentsEntryRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SegmentsEntryRoleImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryRoleImpl
 * @generated
 */
public class SegmentsEntryRoleModelImpl
	extends BaseModelImpl<SegmentsEntryRole> implements SegmentsEntryRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a segments entry role model instance should use the <code>SegmentsEntryRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "SegmentsEntryRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"segmentsEntryRoleId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"segmentsEntryId", Types.BIGINT}, {"roleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("segmentsEntryRoleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("segmentsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SegmentsEntryRole (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,segmentsEntryRoleId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,segmentsEntryId LONG,roleId LONG,primary key (segmentsEntryRoleId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table SegmentsEntryRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY segmentsEntryRole.segmentsEntryRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SegmentsEntryRole.segmentsEntryRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ROLEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SEGMENTSENTRYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SEGMENTSENTRYROLEID_COLUMN_BITMASK = 4L;

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

	public SegmentsEntryRoleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _segmentsEntryRoleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSegmentsEntryRoleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _segmentsEntryRoleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SegmentsEntryRole.class;
	}

	@Override
	public String getModelClassName() {
		return SegmentsEntryRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SegmentsEntryRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SegmentsEntryRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsEntryRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SegmentsEntryRole)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SegmentsEntryRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SegmentsEntryRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SegmentsEntryRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SegmentsEntryRole, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SegmentsEntryRole, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<SegmentsEntryRole, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<SegmentsEntryRole, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<SegmentsEntryRole, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", SegmentsEntryRole::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", SegmentsEntryRole::getCtCollectionId);
			attributeGetterFunctions.put(
				"segmentsEntryRoleId",
				SegmentsEntryRole::getSegmentsEntryRoleId);
			attributeGetterFunctions.put(
				"companyId", SegmentsEntryRole::getCompanyId);
			attributeGetterFunctions.put(
				"userId", SegmentsEntryRole::getUserId);
			attributeGetterFunctions.put(
				"userName", SegmentsEntryRole::getUserName);
			attributeGetterFunctions.put(
				"createDate", SegmentsEntryRole::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", SegmentsEntryRole::getModifiedDate);
			attributeGetterFunctions.put(
				"segmentsEntryId", SegmentsEntryRole::getSegmentsEntryId);
			attributeGetterFunctions.put(
				"roleId", SegmentsEntryRole::getRoleId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<SegmentsEntryRole, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<SegmentsEntryRole, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<SegmentsEntryRole, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"segmentsEntryRoleId",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setSegmentsEntryRoleId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<SegmentsEntryRole, String>)
					SegmentsEntryRole::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<SegmentsEntryRole, Date>)
					SegmentsEntryRole::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<SegmentsEntryRole, Date>)
					SegmentsEntryRole::setModifiedDate);
			attributeSetterBiConsumers.put(
				"segmentsEntryId",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setSegmentsEntryId);
			attributeSetterBiConsumers.put(
				"roleId",
				(BiConsumer<SegmentsEntryRole, Long>)
					SegmentsEntryRole::setRoleId);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getSegmentsEntryRoleId() {
		return _segmentsEntryRoleId;
	}

	@Override
	public void setSegmentsEntryRoleId(long segmentsEntryRoleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_segmentsEntryRoleId = segmentsEntryRoleId;
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
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getSegmentsEntryId() {
		return _segmentsEntryId;
	}

	@Override
	public void setSegmentsEntryId(long segmentsEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_segmentsEntryId = segmentsEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSegmentsEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("segmentsEntryId"));
	}

	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_roleId = roleId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRoleId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("roleId"));
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
			getCompanyId(), SegmentsEntryRole.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SegmentsEntryRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SegmentsEntryRole>
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
		SegmentsEntryRoleImpl segmentsEntryRoleImpl =
			new SegmentsEntryRoleImpl();

		segmentsEntryRoleImpl.setMvccVersion(getMvccVersion());
		segmentsEntryRoleImpl.setCtCollectionId(getCtCollectionId());
		segmentsEntryRoleImpl.setSegmentsEntryRoleId(getSegmentsEntryRoleId());
		segmentsEntryRoleImpl.setCompanyId(getCompanyId());
		segmentsEntryRoleImpl.setUserId(getUserId());
		segmentsEntryRoleImpl.setUserName(getUserName());
		segmentsEntryRoleImpl.setCreateDate(getCreateDate());
		segmentsEntryRoleImpl.setModifiedDate(getModifiedDate());
		segmentsEntryRoleImpl.setSegmentsEntryId(getSegmentsEntryId());
		segmentsEntryRoleImpl.setRoleId(getRoleId());

		segmentsEntryRoleImpl.resetOriginalValues();

		return segmentsEntryRoleImpl;
	}

	@Override
	public SegmentsEntryRole cloneWithOriginalValues() {
		SegmentsEntryRoleImpl segmentsEntryRoleImpl =
			new SegmentsEntryRoleImpl();

		segmentsEntryRoleImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		segmentsEntryRoleImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		segmentsEntryRoleImpl.setSegmentsEntryRoleId(
			this.<Long>getColumnOriginalValue("segmentsEntryRoleId"));
		segmentsEntryRoleImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		segmentsEntryRoleImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		segmentsEntryRoleImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		segmentsEntryRoleImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		segmentsEntryRoleImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		segmentsEntryRoleImpl.setSegmentsEntryId(
			this.<Long>getColumnOriginalValue("segmentsEntryId"));
		segmentsEntryRoleImpl.setRoleId(
			this.<Long>getColumnOriginalValue("roleId"));

		return segmentsEntryRoleImpl;
	}

	@Override
	public int compareTo(SegmentsEntryRole segmentsEntryRole) {
		long primaryKey = segmentsEntryRole.getPrimaryKey();

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

		if (!(object instanceof SegmentsEntryRole)) {
			return false;
		}

		SegmentsEntryRole segmentsEntryRole = (SegmentsEntryRole)object;

		long primaryKey = segmentsEntryRole.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SegmentsEntryRole> toCacheModel() {
		SegmentsEntryRoleCacheModel segmentsEntryRoleCacheModel =
			new SegmentsEntryRoleCacheModel();

		segmentsEntryRoleCacheModel.mvccVersion = getMvccVersion();

		segmentsEntryRoleCacheModel.ctCollectionId = getCtCollectionId();

		segmentsEntryRoleCacheModel.segmentsEntryRoleId =
			getSegmentsEntryRoleId();

		segmentsEntryRoleCacheModel.companyId = getCompanyId();

		segmentsEntryRoleCacheModel.userId = getUserId();

		segmentsEntryRoleCacheModel.userName = getUserName();

		String userName = segmentsEntryRoleCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			segmentsEntryRoleCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			segmentsEntryRoleCacheModel.createDate = createDate.getTime();
		}
		else {
			segmentsEntryRoleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			segmentsEntryRoleCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			segmentsEntryRoleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		segmentsEntryRoleCacheModel.segmentsEntryId = getSegmentsEntryId();

		segmentsEntryRoleCacheModel.roleId = getRoleId();

		return segmentsEntryRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SegmentsEntryRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SegmentsEntryRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsEntryRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(SegmentsEntryRole)this);

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

		private static final Function<InvocationHandler, SegmentsEntryRole>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SegmentsEntryRole.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _segmentsEntryRoleId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _segmentsEntryId;
	private long _roleId;

	public <T> T getColumnValue(String columnName) {
		Function<SegmentsEntryRole, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SegmentsEntryRole)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("segmentsEntryRoleId", _segmentsEntryRoleId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("segmentsEntryId", _segmentsEntryId);
		_columnOriginalValues.put("roleId", _roleId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("segmentsEntryRoleId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("segmentsEntryId", 256L);

		columnBitmasks.put("roleId", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SegmentsEntryRole _escapedModel;

}