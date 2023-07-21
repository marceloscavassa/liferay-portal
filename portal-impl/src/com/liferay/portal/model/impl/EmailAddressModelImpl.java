/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.EmailAddressModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
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
 * The base model implementation for the EmailAddress service. Represents a row in the &quot;EmailAddress&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>EmailAddressModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EmailAddressImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailAddressImpl
 * @generated
 */
@JSON(strict = true)
public class EmailAddressModelImpl
	extends BaseModelImpl<EmailAddress> implements EmailAddressModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a email address model instance should use the <code>EmailAddress</code> interface instead.
	 */
	public static final String TABLE_NAME = "EmailAddress";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"emailAddressId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"address", Types.VARCHAR},
		{"listTypeId", Types.BIGINT}, {"primary_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailAddressId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("listTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("primary_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table EmailAddress (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,emailAddressId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,address VARCHAR(254) null,listTypeId LONG,primary_ BOOLEAN,primary key (emailAddressId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table EmailAddress";

	public static final String ORDER_BY_JPQL =
		" ORDER BY emailAddress.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY EmailAddress.createDate ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PRIMARY_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

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
	public static final long CREATEDATE_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.EmailAddress"));

	public EmailAddressModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _emailAddressId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEmailAddressId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _emailAddressId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return EmailAddress.class;
	}

	@Override
	public String getModelClassName() {
		return EmailAddress.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<EmailAddress, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<EmailAddress, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EmailAddress, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((EmailAddress)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<EmailAddress, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<EmailAddress, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(EmailAddress)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<EmailAddress, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<EmailAddress, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<EmailAddress, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<EmailAddress, Object>>
				attributeGetterFunctions =
					new LinkedHashMap<String, Function<EmailAddress, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", EmailAddress::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", EmailAddress::getCtCollectionId);
			attributeGetterFunctions.put("uuid", EmailAddress::getUuid);
			attributeGetterFunctions.put(
				"emailAddressId", EmailAddress::getEmailAddressId);
			attributeGetterFunctions.put(
				"companyId", EmailAddress::getCompanyId);
			attributeGetterFunctions.put("userId", EmailAddress::getUserId);
			attributeGetterFunctions.put("userName", EmailAddress::getUserName);
			attributeGetterFunctions.put(
				"createDate", EmailAddress::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", EmailAddress::getModifiedDate);
			attributeGetterFunctions.put(
				"classNameId", EmailAddress::getClassNameId);
			attributeGetterFunctions.put("classPK", EmailAddress::getClassPK);
			attributeGetterFunctions.put("address", EmailAddress::getAddress);
			attributeGetterFunctions.put(
				"listTypeId", EmailAddress::getListTypeId);
			attributeGetterFunctions.put("primary", EmailAddress::getPrimary);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<EmailAddress, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<EmailAddress, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<EmailAddress, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<EmailAddress, Long>)EmailAddress::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<EmailAddress, Long>)
					EmailAddress::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<EmailAddress, String>)EmailAddress::setUuid);
			attributeSetterBiConsumers.put(
				"emailAddressId",
				(BiConsumer<EmailAddress, Long>)
					EmailAddress::setEmailAddressId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<EmailAddress, Long>)EmailAddress::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<EmailAddress, Long>)EmailAddress::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<EmailAddress, String>)EmailAddress::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<EmailAddress, Date>)EmailAddress::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<EmailAddress, Date>)EmailAddress::setModifiedDate);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<EmailAddress, Long>)EmailAddress::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<EmailAddress, Long>)EmailAddress::setClassPK);
			attributeSetterBiConsumers.put(
				"address",
				(BiConsumer<EmailAddress, String>)EmailAddress::setAddress);
			attributeSetterBiConsumers.put(
				"listTypeId",
				(BiConsumer<EmailAddress, Long>)EmailAddress::setListTypeId);
			attributeSetterBiConsumers.put(
				"primary",
				(BiConsumer<EmailAddress, Boolean>)EmailAddress::setPrimary);

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
	public long getEmailAddressId() {
		return _emailAddressId;
	}

	@Override
	public void setEmailAddressId(long emailAddressId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_emailAddressId = emailAddressId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public String getAddress() {
		if (_address == null) {
			return "";
		}
		else {
			return _address;
		}
	}

	@Override
	public void setAddress(String address) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_address = address;
	}

	@JSON
	@Override
	public long getListTypeId() {
		return _listTypeId;
	}

	@Override
	public void setListTypeId(long listTypeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_listTypeId = listTypeId;
	}

	@JSON
	@Override
	public boolean getPrimary() {
		return _primary;
	}

	@JSON
	@Override
	public boolean isPrimary() {
		return _primary;
	}

	@Override
	public void setPrimary(boolean primary) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_primary = primary;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalPrimary() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("primary_"));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(EmailAddress.class.getName()),
			getClassNameId());
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
			getCompanyId(), EmailAddress.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public EmailAddress toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, EmailAddress>
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
		EmailAddressImpl emailAddressImpl = new EmailAddressImpl();

		emailAddressImpl.setMvccVersion(getMvccVersion());
		emailAddressImpl.setCtCollectionId(getCtCollectionId());
		emailAddressImpl.setUuid(getUuid());
		emailAddressImpl.setEmailAddressId(getEmailAddressId());
		emailAddressImpl.setCompanyId(getCompanyId());
		emailAddressImpl.setUserId(getUserId());
		emailAddressImpl.setUserName(getUserName());
		emailAddressImpl.setCreateDate(getCreateDate());
		emailAddressImpl.setModifiedDate(getModifiedDate());
		emailAddressImpl.setClassNameId(getClassNameId());
		emailAddressImpl.setClassPK(getClassPK());
		emailAddressImpl.setAddress(getAddress());
		emailAddressImpl.setListTypeId(getListTypeId());
		emailAddressImpl.setPrimary(isPrimary());

		emailAddressImpl.resetOriginalValues();

		return emailAddressImpl;
	}

	@Override
	public EmailAddress cloneWithOriginalValues() {
		EmailAddressImpl emailAddressImpl = new EmailAddressImpl();

		emailAddressImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		emailAddressImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		emailAddressImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		emailAddressImpl.setEmailAddressId(
			this.<Long>getColumnOriginalValue("emailAddressId"));
		emailAddressImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		emailAddressImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		emailAddressImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		emailAddressImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		emailAddressImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		emailAddressImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		emailAddressImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		emailAddressImpl.setAddress(
			this.<String>getColumnOriginalValue("address"));
		emailAddressImpl.setListTypeId(
			this.<Long>getColumnOriginalValue("listTypeId"));
		emailAddressImpl.setPrimary(
			this.<Boolean>getColumnOriginalValue("primary_"));

		return emailAddressImpl;
	}

	@Override
	public int compareTo(EmailAddress emailAddress) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), emailAddress.getCreateDate());

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

		if (!(object instanceof EmailAddress)) {
			return false;
		}

		EmailAddress emailAddress = (EmailAddress)object;

		long primaryKey = emailAddress.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<EmailAddress> toCacheModel() {
		EmailAddressCacheModel emailAddressCacheModel =
			new EmailAddressCacheModel();

		emailAddressCacheModel.mvccVersion = getMvccVersion();

		emailAddressCacheModel.ctCollectionId = getCtCollectionId();

		emailAddressCacheModel.uuid = getUuid();

		String uuid = emailAddressCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			emailAddressCacheModel.uuid = null;
		}

		emailAddressCacheModel.emailAddressId = getEmailAddressId();

		emailAddressCacheModel.companyId = getCompanyId();

		emailAddressCacheModel.userId = getUserId();

		emailAddressCacheModel.userName = getUserName();

		String userName = emailAddressCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			emailAddressCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			emailAddressCacheModel.createDate = createDate.getTime();
		}
		else {
			emailAddressCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			emailAddressCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			emailAddressCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		emailAddressCacheModel.classNameId = getClassNameId();

		emailAddressCacheModel.classPK = getClassPK();

		emailAddressCacheModel.address = getAddress();

		String address = emailAddressCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			emailAddressCacheModel.address = null;
		}

		emailAddressCacheModel.listTypeId = getListTypeId();

		emailAddressCacheModel.primary = isPrimary();

		return emailAddressCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<EmailAddress, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<EmailAddress, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<EmailAddress, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((EmailAddress)this);

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

		private static final Function<InvocationHandler, EmailAddress>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					EmailAddress.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _emailAddressId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _address;
	private long _listTypeId;
	private boolean _primary;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<EmailAddress, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((EmailAddress)this);
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
		_columnOriginalValues.put("emailAddressId", _emailAddressId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("address", _address);
		_columnOriginalValues.put("listTypeId", _listTypeId);
		_columnOriginalValues.put("primary_", _primary);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("primary_", "primary");

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

		columnBitmasks.put("emailAddressId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("address", 2048L);

		columnBitmasks.put("listTypeId", 4096L);

		columnBitmasks.put("primary_", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private EmailAddress _escapedModel;

}