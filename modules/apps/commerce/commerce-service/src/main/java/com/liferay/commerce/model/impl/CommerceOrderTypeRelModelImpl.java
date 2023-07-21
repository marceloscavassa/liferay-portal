/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderTypeRel;
import com.liferay.commerce.model.CommerceOrderTypeRelModel;
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
 * The base model implementation for the CommerceOrderTypeRel service. Represents a row in the &quot;CommerceOrderTypeRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceOrderTypeRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderTypeRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderTypeRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceOrderTypeRelModelImpl
	extends BaseModelImpl<CommerceOrderTypeRel>
	implements CommerceOrderTypeRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order type rel model instance should use the <code>CommerceOrderTypeRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceOrderTypeRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR},
		{"commerceOrderTypeRelId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"commerceOrderTypeId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceOrderTypeRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceOrderTypeId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceOrderTypeRel (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,commerceOrderTypeRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,commerceOrderTypeId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceOrderTypeRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceOrderTypeRel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceOrderTypeRel.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

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
	public static final long COMMERCEORDERTYPEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 16L;

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

	public CommerceOrderTypeRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceOrderTypeRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceOrderTypeRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceOrderTypeRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceOrderTypeRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderTypeRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceOrderTypeRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceOrderTypeRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderTypeRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceOrderTypeRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceOrderTypeRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceOrderTypeRel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceOrderTypeRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceOrderTypeRel, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceOrderTypeRel, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CommerceOrderTypeRel, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceOrderTypeRel, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommerceOrderTypeRel, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceOrderTypeRel::getMvccVersion);
			attributeGetterFunctions.put("uuid", CommerceOrderTypeRel::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				CommerceOrderTypeRel::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"commerceOrderTypeRelId",
				CommerceOrderTypeRel::getCommerceOrderTypeRelId);
			attributeGetterFunctions.put(
				"companyId", CommerceOrderTypeRel::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommerceOrderTypeRel::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceOrderTypeRel::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceOrderTypeRel::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceOrderTypeRel::getModifiedDate);
			attributeGetterFunctions.put(
				"classNameId", CommerceOrderTypeRel::getClassNameId);
			attributeGetterFunctions.put(
				"classPK", CommerceOrderTypeRel::getClassPK);
			attributeGetterFunctions.put(
				"commerceOrderTypeId",
				CommerceOrderTypeRel::getCommerceOrderTypeId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommerceOrderTypeRel, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceOrderTypeRel, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceOrderTypeRel, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommerceOrderTypeRel, String>)
					CommerceOrderTypeRel::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<CommerceOrderTypeRel, String>)
					CommerceOrderTypeRel::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"commerceOrderTypeRelId",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setCommerceOrderTypeRelId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceOrderTypeRel, String>)
					CommerceOrderTypeRel::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceOrderTypeRel, Date>)
					CommerceOrderTypeRel::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceOrderTypeRel, Date>)
					CommerceOrderTypeRel::setModifiedDate);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setClassPK);
			attributeSetterBiConsumers.put(
				"commerceOrderTypeId",
				(BiConsumer<CommerceOrderTypeRel, Long>)
					CommerceOrderTypeRel::setCommerceOrderTypeId);

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
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getCommerceOrderTypeRelId() {
		return _commerceOrderTypeRelId;
	}

	@Override
	public void setCommerceOrderTypeRelId(long commerceOrderTypeRelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderTypeRelId = commerceOrderTypeRelId;
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
	public long getCommerceOrderTypeId() {
		return _commerceOrderTypeId;
	}

	@Override
	public void setCommerceOrderTypeId(long commerceOrderTypeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderTypeId = commerceOrderTypeId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceOrderTypeId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceOrderTypeId"));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CommerceOrderTypeRel.class.getName()),
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
			getCompanyId(), CommerceOrderTypeRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceOrderTypeRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceOrderTypeRel>
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
		CommerceOrderTypeRelImpl commerceOrderTypeRelImpl =
			new CommerceOrderTypeRelImpl();

		commerceOrderTypeRelImpl.setMvccVersion(getMvccVersion());
		commerceOrderTypeRelImpl.setUuid(getUuid());
		commerceOrderTypeRelImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceOrderTypeRelImpl.setCommerceOrderTypeRelId(
			getCommerceOrderTypeRelId());
		commerceOrderTypeRelImpl.setCompanyId(getCompanyId());
		commerceOrderTypeRelImpl.setUserId(getUserId());
		commerceOrderTypeRelImpl.setUserName(getUserName());
		commerceOrderTypeRelImpl.setCreateDate(getCreateDate());
		commerceOrderTypeRelImpl.setModifiedDate(getModifiedDate());
		commerceOrderTypeRelImpl.setClassNameId(getClassNameId());
		commerceOrderTypeRelImpl.setClassPK(getClassPK());
		commerceOrderTypeRelImpl.setCommerceOrderTypeId(
			getCommerceOrderTypeId());

		commerceOrderTypeRelImpl.resetOriginalValues();

		return commerceOrderTypeRelImpl;
	}

	@Override
	public CommerceOrderTypeRel cloneWithOriginalValues() {
		CommerceOrderTypeRelImpl commerceOrderTypeRelImpl =
			new CommerceOrderTypeRelImpl();

		commerceOrderTypeRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceOrderTypeRelImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceOrderTypeRelImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		commerceOrderTypeRelImpl.setCommerceOrderTypeRelId(
			this.<Long>getColumnOriginalValue("commerceOrderTypeRelId"));
		commerceOrderTypeRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceOrderTypeRelImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceOrderTypeRelImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceOrderTypeRelImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceOrderTypeRelImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceOrderTypeRelImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		commerceOrderTypeRelImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		commerceOrderTypeRelImpl.setCommerceOrderTypeId(
			this.<Long>getColumnOriginalValue("commerceOrderTypeId"));

		return commerceOrderTypeRelImpl;
	}

	@Override
	public int compareTo(CommerceOrderTypeRel commerceOrderTypeRel) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceOrderTypeRel.getCreateDate());

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

		if (!(object instanceof CommerceOrderTypeRel)) {
			return false;
		}

		CommerceOrderTypeRel commerceOrderTypeRel =
			(CommerceOrderTypeRel)object;

		long primaryKey = commerceOrderTypeRel.getPrimaryKey();

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
	public CacheModel<CommerceOrderTypeRel> toCacheModel() {
		CommerceOrderTypeRelCacheModel commerceOrderTypeRelCacheModel =
			new CommerceOrderTypeRelCacheModel();

		commerceOrderTypeRelCacheModel.mvccVersion = getMvccVersion();

		commerceOrderTypeRelCacheModel.uuid = getUuid();

		String uuid = commerceOrderTypeRelCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceOrderTypeRelCacheModel.uuid = null;
		}

		commerceOrderTypeRelCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceOrderTypeRelCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceOrderTypeRelCacheModel.externalReferenceCode = null;
		}

		commerceOrderTypeRelCacheModel.commerceOrderTypeRelId =
			getCommerceOrderTypeRelId();

		commerceOrderTypeRelCacheModel.companyId = getCompanyId();

		commerceOrderTypeRelCacheModel.userId = getUserId();

		commerceOrderTypeRelCacheModel.userName = getUserName();

		String userName = commerceOrderTypeRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceOrderTypeRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceOrderTypeRelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceOrderTypeRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceOrderTypeRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceOrderTypeRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceOrderTypeRelCacheModel.classNameId = getClassNameId();

		commerceOrderTypeRelCacheModel.classPK = getClassPK();

		commerceOrderTypeRelCacheModel.commerceOrderTypeId =
			getCommerceOrderTypeId();

		return commerceOrderTypeRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceOrderTypeRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceOrderTypeRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderTypeRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceOrderTypeRel)this);

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

		private static final Function<InvocationHandler, CommerceOrderTypeRel>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceOrderTypeRel.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private String _externalReferenceCode;
	private long _commerceOrderTypeRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _commerceOrderTypeId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceOrderTypeRel, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceOrderTypeRel)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put(
			"commerceOrderTypeRelId", _commerceOrderTypeRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("commerceOrderTypeId", _commerceOrderTypeId);
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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("externalReferenceCode", 4L);

		columnBitmasks.put("commerceOrderTypeRelId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("commerceOrderTypeId", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceOrderTypeRel _escapedModel;

}