/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPInstanceOptionValueRel;
import com.liferay.commerce.product.model.CPInstanceOptionValueRelModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the CPInstanceOptionValueRel service. Represents a row in the &quot;CPInstanceOptionValueRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CPInstanceOptionValueRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPInstanceOptionValueRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPInstanceOptionValueRelImpl
 * @generated
 */
@JSON(strict = true)
public class CPInstanceOptionValueRelModelImpl
	extends BaseModelImpl<CPInstanceOptionValueRel>
	implements CPInstanceOptionValueRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp instance option value rel model instance should use the <code>CPInstanceOptionValueRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPInstanceOptionValueRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"CPInstanceOptionValueRelId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CPDefinitionOptionRelId", Types.BIGINT},
		{"CPDefinitionOptionValueRelId", Types.BIGINT},
		{"CPInstanceId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPInstanceOptionValueRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPDefinitionOptionRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPDefinitionOptionValueRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPInstanceId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPInstanceOptionValueRel (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,CPInstanceOptionValueRelId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPDefinitionOptionRelId LONG,CPDefinitionOptionValueRelId LONG,CPInstanceId LONG,primary key (CPInstanceOptionValueRelId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table CPInstanceOptionValueRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cpInstanceOptionValueRel.CPInstanceOptionValueRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPInstanceOptionValueRel.CPInstanceOptionValueRelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONOPTIONRELID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONOPTIONVALUERELID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPINSTANCEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPINSTANCEOPTIONVALUERELID_COLUMN_BITMASK = 64L;

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

	public CPInstanceOptionValueRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPInstanceOptionValueRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPInstanceOptionValueRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPInstanceOptionValueRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPInstanceOptionValueRel.class;
	}

	@Override
	public String getModelClassName() {
		return CPInstanceOptionValueRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPInstanceOptionValueRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPInstanceOptionValueRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPInstanceOptionValueRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CPInstanceOptionValueRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPInstanceOptionValueRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPInstanceOptionValueRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPInstanceOptionValueRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPInstanceOptionValueRel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPInstanceOptionValueRel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CPInstanceOptionValueRel, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CPInstanceOptionValueRel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CPInstanceOptionValueRel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CPInstanceOptionValueRel::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", CPInstanceOptionValueRel::getCtCollectionId);
			attributeGetterFunctions.put(
				"uuid", CPInstanceOptionValueRel::getUuid);
			attributeGetterFunctions.put(
				"CPInstanceOptionValueRelId",
				CPInstanceOptionValueRel::getCPInstanceOptionValueRelId);
			attributeGetterFunctions.put(
				"groupId", CPInstanceOptionValueRel::getGroupId);
			attributeGetterFunctions.put(
				"companyId", CPInstanceOptionValueRel::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CPInstanceOptionValueRel::getUserId);
			attributeGetterFunctions.put(
				"userName", CPInstanceOptionValueRel::getUserName);
			attributeGetterFunctions.put(
				"createDate", CPInstanceOptionValueRel::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CPInstanceOptionValueRel::getModifiedDate);
			attributeGetterFunctions.put(
				"CPDefinitionOptionRelId",
				CPInstanceOptionValueRel::getCPDefinitionOptionRelId);
			attributeGetterFunctions.put(
				"CPDefinitionOptionValueRelId",
				CPInstanceOptionValueRel::getCPDefinitionOptionValueRelId);
			attributeGetterFunctions.put(
				"CPInstanceId", CPInstanceOptionValueRel::getCPInstanceId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CPInstanceOptionValueRel, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CPInstanceOptionValueRel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CPInstanceOptionValueRel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CPInstanceOptionValueRel, String>)
					CPInstanceOptionValueRel::setUuid);
			attributeSetterBiConsumers.put(
				"CPInstanceOptionValueRelId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setCPInstanceOptionValueRelId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CPInstanceOptionValueRel, String>)
					CPInstanceOptionValueRel::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CPInstanceOptionValueRel, Date>)
					CPInstanceOptionValueRel::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CPInstanceOptionValueRel, Date>)
					CPInstanceOptionValueRel::setModifiedDate);
			attributeSetterBiConsumers.put(
				"CPDefinitionOptionRelId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setCPDefinitionOptionRelId);
			attributeSetterBiConsumers.put(
				"CPDefinitionOptionValueRelId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setCPDefinitionOptionValueRelId);
			attributeSetterBiConsumers.put(
				"CPInstanceId",
				(BiConsumer<CPInstanceOptionValueRel, Long>)
					CPInstanceOptionValueRel::setCPInstanceId);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getCPInstanceOptionValueRelId() {
		return _CPInstanceOptionValueRelId;
	}

	@Override
	public void setCPInstanceOptionValueRelId(long CPInstanceOptionValueRelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPInstanceOptionValueRelId = CPInstanceOptionValueRelId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getCPDefinitionOptionRelId() {
		return _CPDefinitionOptionRelId;
	}

	@Override
	public void setCPDefinitionOptionRelId(long CPDefinitionOptionRelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionOptionRelId = CPDefinitionOptionRelId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCPDefinitionOptionRelId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CPDefinitionOptionRelId"));
	}

	@JSON
	@Override
	public long getCPDefinitionOptionValueRelId() {
		return _CPDefinitionOptionValueRelId;
	}

	@Override
	public void setCPDefinitionOptionValueRelId(
		long CPDefinitionOptionValueRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionOptionValueRelId = CPDefinitionOptionValueRelId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCPDefinitionOptionValueRelId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CPDefinitionOptionValueRelId"));
	}

	@JSON
	@Override
	public long getCPInstanceId() {
		return _CPInstanceId;
	}

	@Override
	public void setCPInstanceId(long CPInstanceId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPInstanceId = CPInstanceId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCPInstanceId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CPInstanceId"));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CPInstanceOptionValueRel.class.getName()));
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
			getCompanyId(), CPInstanceOptionValueRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CPInstanceOptionValueRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPInstanceOptionValueRel>
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
		CPInstanceOptionValueRelImpl cpInstanceOptionValueRelImpl =
			new CPInstanceOptionValueRelImpl();

		cpInstanceOptionValueRelImpl.setMvccVersion(getMvccVersion());
		cpInstanceOptionValueRelImpl.setCtCollectionId(getCtCollectionId());
		cpInstanceOptionValueRelImpl.setUuid(getUuid());
		cpInstanceOptionValueRelImpl.setCPInstanceOptionValueRelId(
			getCPInstanceOptionValueRelId());
		cpInstanceOptionValueRelImpl.setGroupId(getGroupId());
		cpInstanceOptionValueRelImpl.setCompanyId(getCompanyId());
		cpInstanceOptionValueRelImpl.setUserId(getUserId());
		cpInstanceOptionValueRelImpl.setUserName(getUserName());
		cpInstanceOptionValueRelImpl.setCreateDate(getCreateDate());
		cpInstanceOptionValueRelImpl.setModifiedDate(getModifiedDate());
		cpInstanceOptionValueRelImpl.setCPDefinitionOptionRelId(
			getCPDefinitionOptionRelId());
		cpInstanceOptionValueRelImpl.setCPDefinitionOptionValueRelId(
			getCPDefinitionOptionValueRelId());
		cpInstanceOptionValueRelImpl.setCPInstanceId(getCPInstanceId());

		cpInstanceOptionValueRelImpl.resetOriginalValues();

		return cpInstanceOptionValueRelImpl;
	}

	@Override
	public CPInstanceOptionValueRel cloneWithOriginalValues() {
		CPInstanceOptionValueRelImpl cpInstanceOptionValueRelImpl =
			new CPInstanceOptionValueRelImpl();

		cpInstanceOptionValueRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		cpInstanceOptionValueRelImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		cpInstanceOptionValueRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		cpInstanceOptionValueRelImpl.setCPInstanceOptionValueRelId(
			this.<Long>getColumnOriginalValue("CPInstanceOptionValueRelId"));
		cpInstanceOptionValueRelImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		cpInstanceOptionValueRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		cpInstanceOptionValueRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		cpInstanceOptionValueRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		cpInstanceOptionValueRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		cpInstanceOptionValueRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		cpInstanceOptionValueRelImpl.setCPDefinitionOptionRelId(
			this.<Long>getColumnOriginalValue("CPDefinitionOptionRelId"));
		cpInstanceOptionValueRelImpl.setCPDefinitionOptionValueRelId(
			this.<Long>getColumnOriginalValue("CPDefinitionOptionValueRelId"));
		cpInstanceOptionValueRelImpl.setCPInstanceId(
			this.<Long>getColumnOriginalValue("CPInstanceId"));

		return cpInstanceOptionValueRelImpl;
	}

	@Override
	public int compareTo(CPInstanceOptionValueRel cpInstanceOptionValueRel) {
		long primaryKey = cpInstanceOptionValueRel.getPrimaryKey();

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

		if (!(object instanceof CPInstanceOptionValueRel)) {
			return false;
		}

		CPInstanceOptionValueRel cpInstanceOptionValueRel =
			(CPInstanceOptionValueRel)object;

		long primaryKey = cpInstanceOptionValueRel.getPrimaryKey();

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
	public CacheModel<CPInstanceOptionValueRel> toCacheModel() {
		CPInstanceOptionValueRelCacheModel cpInstanceOptionValueRelCacheModel =
			new CPInstanceOptionValueRelCacheModel();

		cpInstanceOptionValueRelCacheModel.mvccVersion = getMvccVersion();

		cpInstanceOptionValueRelCacheModel.ctCollectionId = getCtCollectionId();

		cpInstanceOptionValueRelCacheModel.uuid = getUuid();

		String uuid = cpInstanceOptionValueRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpInstanceOptionValueRelCacheModel.uuid = null;
		}

		cpInstanceOptionValueRelCacheModel.CPInstanceOptionValueRelId =
			getCPInstanceOptionValueRelId();

		cpInstanceOptionValueRelCacheModel.groupId = getGroupId();

		cpInstanceOptionValueRelCacheModel.companyId = getCompanyId();

		cpInstanceOptionValueRelCacheModel.userId = getUserId();

		cpInstanceOptionValueRelCacheModel.userName = getUserName();

		String userName = cpInstanceOptionValueRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpInstanceOptionValueRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpInstanceOptionValueRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			cpInstanceOptionValueRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpInstanceOptionValueRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			cpInstanceOptionValueRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpInstanceOptionValueRelCacheModel.CPDefinitionOptionRelId =
			getCPDefinitionOptionRelId();

		cpInstanceOptionValueRelCacheModel.CPDefinitionOptionValueRelId =
			getCPDefinitionOptionValueRelId();

		cpInstanceOptionValueRelCacheModel.CPInstanceId = getCPInstanceId();

		return cpInstanceOptionValueRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPInstanceOptionValueRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPInstanceOptionValueRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPInstanceOptionValueRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CPInstanceOptionValueRel)this);

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

		private static final Function
			<InvocationHandler, CPInstanceOptionValueRel>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CPInstanceOptionValueRel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _CPInstanceOptionValueRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPDefinitionOptionRelId;
	private long _CPDefinitionOptionValueRelId;
	private long _CPInstanceId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CPInstanceOptionValueRel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CPInstanceOptionValueRel)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"CPInstanceOptionValueRelId", _CPInstanceOptionValueRelId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"CPDefinitionOptionRelId", _CPDefinitionOptionRelId);
		_columnOriginalValues.put(
			"CPDefinitionOptionValueRelId", _CPDefinitionOptionValueRelId);
		_columnOriginalValues.put("CPInstanceId", _CPInstanceId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
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

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("CPInstanceOptionValueRelId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("CPDefinitionOptionRelId", 1024L);

		columnBitmasks.put("CPDefinitionOptionValueRelId", 2048L);

		columnBitmasks.put("CPInstanceId", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CPInstanceOptionValueRel _escapedModel;

}