/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.social.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.model.SocialRequestModel;

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
 * The base model implementation for the SocialRequest service. Represents a row in the &quot;SocialRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SocialRequestModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialRequestImpl
 * @generated
 */
@JSON(strict = true)
public class SocialRequestModelImpl
	extends BaseModelImpl<SocialRequest> implements SocialRequestModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social request model instance should use the <code>SocialRequest</code> interface instead.
	 */
	public static final String TABLE_NAME = "SocialRequest";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"requestId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.BIGINT},
		{"modifiedDate", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"type_", Types.INTEGER},
		{"extraData", Types.VARCHAR}, {"receiverUserId", Types.BIGINT},
		{"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("requestId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("extraData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("receiverUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SocialRequest (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,requestId LONG not null,groupId LONG,companyId LONG,userId LONG,createDate LONG,modifiedDate LONG,classNameId LONG,classPK LONG,type_ INTEGER,extraData STRING null,receiverUserId LONG,status INTEGER,primary key (requestId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table SocialRequest";

	public static final String ORDER_BY_JPQL =
		" ORDER BY socialRequest.requestId DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SocialRequest.requestId DESC";

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
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RECEIVERUSERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 128L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 256L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long REQUESTID_COLUMN_BITMASK = 512L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.social.kernel.model.SocialRequest"));

	public SocialRequestModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _requestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _requestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialRequest.class;
	}

	@Override
	public String getModelClassName() {
		return SocialRequest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SocialRequest, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SocialRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialRequest, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SocialRequest)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SocialRequest, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SocialRequest, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SocialRequest)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SocialRequest, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SocialRequest, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<SocialRequest, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<SocialRequest, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<SocialRequest, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", SocialRequest::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", SocialRequest::getCtCollectionId);
			attributeGetterFunctions.put("uuid", SocialRequest::getUuid);
			attributeGetterFunctions.put(
				"requestId", SocialRequest::getRequestId);
			attributeGetterFunctions.put("groupId", SocialRequest::getGroupId);
			attributeGetterFunctions.put(
				"companyId", SocialRequest::getCompanyId);
			attributeGetterFunctions.put("userId", SocialRequest::getUserId);
			attributeGetterFunctions.put(
				"createDate", SocialRequest::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", SocialRequest::getModifiedDate);
			attributeGetterFunctions.put(
				"classNameId", SocialRequest::getClassNameId);
			attributeGetterFunctions.put("classPK", SocialRequest::getClassPK);
			attributeGetterFunctions.put("type", SocialRequest::getType);
			attributeGetterFunctions.put(
				"extraData", SocialRequest::getExtraData);
			attributeGetterFunctions.put(
				"receiverUserId", SocialRequest::getReceiverUserId);
			attributeGetterFunctions.put("status", SocialRequest::getStatus);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<SocialRequest, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<SocialRequest, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<SocialRequest, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<SocialRequest, Long>)
					SocialRequest::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<SocialRequest, String>)SocialRequest::setUuid);
			attributeSetterBiConsumers.put(
				"requestId",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setRequestId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setUserId);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<SocialRequest, Long>)
					SocialRequest::setModifiedDate);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<SocialRequest, Long>)SocialRequest::setClassPK);
			attributeSetterBiConsumers.put(
				"type",
				(BiConsumer<SocialRequest, Integer>)SocialRequest::setType);
			attributeSetterBiConsumers.put(
				"extraData",
				(BiConsumer<SocialRequest, String>)SocialRequest::setExtraData);
			attributeSetterBiConsumers.put(
				"receiverUserId",
				(BiConsumer<SocialRequest, Long>)
					SocialRequest::setReceiverUserId);
			attributeSetterBiConsumers.put(
				"status",
				(BiConsumer<SocialRequest, Integer>)SocialRequest::setStatus);

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
	public long getRequestId() {
		return _requestId;
	}

	@Override
	public void setRequestId(long requestId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_requestId = requestId;
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
	public long getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(long createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public long getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(long modifiedDate) {
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
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
	}

	@JSON
	@Override
	public String getExtraData() {
		if (_extraData == null) {
			return "";
		}
		else {
			return _extraData;
		}
	}

	@Override
	public void setExtraData(String extraData) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_extraData = extraData;
	}

	@JSON
	@Override
	public long getReceiverUserId() {
		return _receiverUserId;
	}

	@Override
	public void setReceiverUserId(long receiverUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_receiverUserId = receiverUserId;
	}

	@Override
	public String getReceiverUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getReceiverUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setReceiverUserUuid(String receiverUserUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalReceiverUserId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("receiverUserId"));
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
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
			getCompanyId(), SocialRequest.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialRequest toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SocialRequest>
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
		SocialRequestImpl socialRequestImpl = new SocialRequestImpl();

		socialRequestImpl.setMvccVersion(getMvccVersion());
		socialRequestImpl.setCtCollectionId(getCtCollectionId());
		socialRequestImpl.setUuid(getUuid());
		socialRequestImpl.setRequestId(getRequestId());
		socialRequestImpl.setGroupId(getGroupId());
		socialRequestImpl.setCompanyId(getCompanyId());
		socialRequestImpl.setUserId(getUserId());
		socialRequestImpl.setCreateDate(getCreateDate());
		socialRequestImpl.setModifiedDate(getModifiedDate());
		socialRequestImpl.setClassNameId(getClassNameId());
		socialRequestImpl.setClassPK(getClassPK());
		socialRequestImpl.setType(getType());
		socialRequestImpl.setExtraData(getExtraData());
		socialRequestImpl.setReceiverUserId(getReceiverUserId());
		socialRequestImpl.setStatus(getStatus());

		socialRequestImpl.resetOriginalValues();

		return socialRequestImpl;
	}

	@Override
	public SocialRequest cloneWithOriginalValues() {
		SocialRequestImpl socialRequestImpl = new SocialRequestImpl();

		socialRequestImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		socialRequestImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		socialRequestImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		socialRequestImpl.setRequestId(
			this.<Long>getColumnOriginalValue("requestId"));
		socialRequestImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		socialRequestImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		socialRequestImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		socialRequestImpl.setCreateDate(
			this.<Long>getColumnOriginalValue("createDate"));
		socialRequestImpl.setModifiedDate(
			this.<Long>getColumnOriginalValue("modifiedDate"));
		socialRequestImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		socialRequestImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		socialRequestImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		socialRequestImpl.setExtraData(
			this.<String>getColumnOriginalValue("extraData"));
		socialRequestImpl.setReceiverUserId(
			this.<Long>getColumnOriginalValue("receiverUserId"));
		socialRequestImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));

		return socialRequestImpl;
	}

	@Override
	public int compareTo(SocialRequest socialRequest) {
		int value = 0;

		if (getRequestId() < socialRequest.getRequestId()) {
			value = -1;
		}
		else if (getRequestId() > socialRequest.getRequestId()) {
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

		if (!(object instanceof SocialRequest)) {
			return false;
		}

		SocialRequest socialRequest = (SocialRequest)object;

		long primaryKey = socialRequest.getPrimaryKey();

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
	public CacheModel<SocialRequest> toCacheModel() {
		SocialRequestCacheModel socialRequestCacheModel =
			new SocialRequestCacheModel();

		socialRequestCacheModel.mvccVersion = getMvccVersion();

		socialRequestCacheModel.ctCollectionId = getCtCollectionId();

		socialRequestCacheModel.uuid = getUuid();

		String uuid = socialRequestCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			socialRequestCacheModel.uuid = null;
		}

		socialRequestCacheModel.requestId = getRequestId();

		socialRequestCacheModel.groupId = getGroupId();

		socialRequestCacheModel.companyId = getCompanyId();

		socialRequestCacheModel.userId = getUserId();

		socialRequestCacheModel.createDate = getCreateDate();

		socialRequestCacheModel.modifiedDate = getModifiedDate();

		socialRequestCacheModel.classNameId = getClassNameId();

		socialRequestCacheModel.classPK = getClassPK();

		socialRequestCacheModel.type = getType();

		socialRequestCacheModel.extraData = getExtraData();

		String extraData = socialRequestCacheModel.extraData;

		if ((extraData != null) && (extraData.length() == 0)) {
			socialRequestCacheModel.extraData = null;
		}

		socialRequestCacheModel.receiverUserId = getReceiverUserId();

		socialRequestCacheModel.status = getStatus();

		return socialRequestCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SocialRequest, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SocialRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialRequest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SocialRequest)this);

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

		private static final Function<InvocationHandler, SocialRequest>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SocialRequest.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _requestId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _createDate;
	private long _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private String _extraData;
	private long _receiverUserId;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SocialRequest, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SocialRequest)this);
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
		_columnOriginalValues.put("requestId", _requestId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("extraData", _extraData);
		_columnOriginalValues.put("receiverUserId", _receiverUserId);
		_columnOriginalValues.put("status", _status);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("requestId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("type_", 2048L);

		columnBitmasks.put("extraData", 4096L);

		columnBitmasks.put("receiverUserId", 8192L);

		columnBitmasks.put("status", 16384L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SocialRequest _escapedModel;

}