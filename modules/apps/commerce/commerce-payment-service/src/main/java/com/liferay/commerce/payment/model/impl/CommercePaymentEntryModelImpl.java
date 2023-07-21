/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.model.impl;

import com.liferay.commerce.payment.model.CommercePaymentEntry;
import com.liferay.commerce.payment.model.CommercePaymentEntryModel;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the CommercePaymentEntry service. Represents a row in the &quot;CommercePaymentEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommercePaymentEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommercePaymentEntryImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommercePaymentEntryImpl
 * @generated
 */
@JSON(strict = true)
public class CommercePaymentEntryModelImpl
	extends BaseModelImpl<CommercePaymentEntry>
	implements CommercePaymentEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce payment entry model instance should use the <code>CommercePaymentEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommercePaymentEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"commercePaymentEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"commerceChannelId", Types.BIGINT},
		{"amount", Types.DECIMAL}, {"callbackURL", Types.CLOB},
		{"currencyCode", Types.VARCHAR},
		{"paymentIntegrationKey", Types.VARCHAR},
		{"paymentIntegrationType", Types.INTEGER},
		{"paymentStatus", Types.INTEGER}, {"redirectURL", Types.CLOB},
		{"transactionCode", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commercePaymentEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("amount", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("callbackURL", Types.CLOB);
		TABLE_COLUMNS_MAP.put("currencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentIntegrationKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("paymentIntegrationType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("paymentStatus", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("redirectURL", Types.CLOB);
		TABLE_COLUMNS_MAP.put("transactionCode", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommercePaymentEntry (mvccVersion LONG default 0 not null,commercePaymentEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,commerceChannelId LONG,amount DECIMAL(30, 16) null,callbackURL TEXT null,currencyCode VARCHAR(75) null,paymentIntegrationKey VARCHAR(75) null,paymentIntegrationType INTEGER,paymentStatus INTEGER,redirectURL TEXT null,transactionCode VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommercePaymentEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commercePaymentEntry.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommercePaymentEntry.createDate DESC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

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

	public CommercePaymentEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commercePaymentEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommercePaymentEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commercePaymentEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommercePaymentEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommercePaymentEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommercePaymentEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommercePaymentEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePaymentEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommercePaymentEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommercePaymentEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommercePaymentEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommercePaymentEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommercePaymentEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommercePaymentEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CommercePaymentEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CommercePaymentEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommercePaymentEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommercePaymentEntry::getMvccVersion);
			attributeGetterFunctions.put(
				"commercePaymentEntryId",
				CommercePaymentEntry::getCommercePaymentEntryId);
			attributeGetterFunctions.put(
				"companyId", CommercePaymentEntry::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommercePaymentEntry::getUserId);
			attributeGetterFunctions.put(
				"userName", CommercePaymentEntry::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommercePaymentEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommercePaymentEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"classNameId", CommercePaymentEntry::getClassNameId);
			attributeGetterFunctions.put(
				"classPK", CommercePaymentEntry::getClassPK);
			attributeGetterFunctions.put(
				"commerceChannelId",
				CommercePaymentEntry::getCommerceChannelId);
			attributeGetterFunctions.put(
				"amount", CommercePaymentEntry::getAmount);
			attributeGetterFunctions.put(
				"callbackURL", CommercePaymentEntry::getCallbackURL);
			attributeGetterFunctions.put(
				"currencyCode", CommercePaymentEntry::getCurrencyCode);
			attributeGetterFunctions.put(
				"paymentIntegrationKey",
				CommercePaymentEntry::getPaymentIntegrationKey);
			attributeGetterFunctions.put(
				"paymentIntegrationType",
				CommercePaymentEntry::getPaymentIntegrationType);
			attributeGetterFunctions.put(
				"paymentStatus", CommercePaymentEntry::getPaymentStatus);
			attributeGetterFunctions.put(
				"redirectURL", CommercePaymentEntry::getRedirectURL);
			attributeGetterFunctions.put(
				"transactionCode", CommercePaymentEntry::getTransactionCode);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommercePaymentEntry, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommercePaymentEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommercePaymentEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"commercePaymentEntryId",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setCommercePaymentEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommercePaymentEntry, String>)
					CommercePaymentEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommercePaymentEntry, Date>)
					CommercePaymentEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommercePaymentEntry, Date>)
					CommercePaymentEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"classNameId",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setClassNameId);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setClassPK);
			attributeSetterBiConsumers.put(
				"commerceChannelId",
				(BiConsumer<CommercePaymentEntry, Long>)
					CommercePaymentEntry::setCommerceChannelId);
			attributeSetterBiConsumers.put(
				"amount",
				(BiConsumer<CommercePaymentEntry, BigDecimal>)
					CommercePaymentEntry::setAmount);
			attributeSetterBiConsumers.put(
				"callbackURL",
				(BiConsumer<CommercePaymentEntry, String>)
					CommercePaymentEntry::setCallbackURL);
			attributeSetterBiConsumers.put(
				"currencyCode",
				(BiConsumer<CommercePaymentEntry, String>)
					CommercePaymentEntry::setCurrencyCode);
			attributeSetterBiConsumers.put(
				"paymentIntegrationKey",
				(BiConsumer<CommercePaymentEntry, String>)
					CommercePaymentEntry::setPaymentIntegrationKey);
			attributeSetterBiConsumers.put(
				"paymentIntegrationType",
				(BiConsumer<CommercePaymentEntry, Integer>)
					CommercePaymentEntry::setPaymentIntegrationType);
			attributeSetterBiConsumers.put(
				"paymentStatus",
				(BiConsumer<CommercePaymentEntry, Integer>)
					CommercePaymentEntry::setPaymentStatus);
			attributeSetterBiConsumers.put(
				"redirectURL",
				(BiConsumer<CommercePaymentEntry, String>)
					CommercePaymentEntry::setRedirectURL);
			attributeSetterBiConsumers.put(
				"transactionCode",
				(BiConsumer<CommercePaymentEntry, String>)
					CommercePaymentEntry::setTransactionCode);

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
	public long getCommercePaymentEntryId() {
		return _commercePaymentEntryId;
	}

	@Override
	public void setCommercePaymentEntryId(long commercePaymentEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commercePaymentEntryId = commercePaymentEntryId;
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
	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceChannelId = commerceChannelId;
	}

	@JSON
	@Override
	public BigDecimal getAmount() {
		return _amount;
	}

	@Override
	public void setAmount(BigDecimal amount) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_amount = amount;
	}

	@JSON
	@Override
	public String getCallbackURL() {
		if (_callbackURL == null) {
			return "";
		}
		else {
			return _callbackURL;
		}
	}

	@Override
	public void setCallbackURL(String callbackURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_callbackURL = callbackURL;
	}

	@JSON
	@Override
	public String getCurrencyCode() {
		if (_currencyCode == null) {
			return "";
		}
		else {
			return _currencyCode;
		}
	}

	@Override
	public void setCurrencyCode(String currencyCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_currencyCode = currencyCode;
	}

	@JSON
	@Override
	public String getPaymentIntegrationKey() {
		if (_paymentIntegrationKey == null) {
			return "";
		}
		else {
			return _paymentIntegrationKey;
		}
	}

	@Override
	public void setPaymentIntegrationKey(String paymentIntegrationKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_paymentIntegrationKey = paymentIntegrationKey;
	}

	@JSON
	@Override
	public int getPaymentIntegrationType() {
		return _paymentIntegrationType;
	}

	@Override
	public void setPaymentIntegrationType(int paymentIntegrationType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_paymentIntegrationType = paymentIntegrationType;
	}

	@JSON
	@Override
	public int getPaymentStatus() {
		return _paymentStatus;
	}

	@Override
	public void setPaymentStatus(int paymentStatus) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_paymentStatus = paymentStatus;
	}

	@JSON
	@Override
	public String getRedirectURL() {
		if (_redirectURL == null) {
			return "";
		}
		else {
			return _redirectURL;
		}
	}

	@Override
	public void setRedirectURL(String redirectURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_redirectURL = redirectURL;
	}

	@JSON
	@Override
	public String getTransactionCode() {
		if (_transactionCode == null) {
			return "";
		}
		else {
			return _transactionCode;
		}
	}

	@Override
	public void setTransactionCode(String transactionCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_transactionCode = transactionCode;
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
			getCompanyId(), CommercePaymentEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommercePaymentEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommercePaymentEntry>
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
		CommercePaymentEntryImpl commercePaymentEntryImpl =
			new CommercePaymentEntryImpl();

		commercePaymentEntryImpl.setMvccVersion(getMvccVersion());
		commercePaymentEntryImpl.setCommercePaymentEntryId(
			getCommercePaymentEntryId());
		commercePaymentEntryImpl.setCompanyId(getCompanyId());
		commercePaymentEntryImpl.setUserId(getUserId());
		commercePaymentEntryImpl.setUserName(getUserName());
		commercePaymentEntryImpl.setCreateDate(getCreateDate());
		commercePaymentEntryImpl.setModifiedDate(getModifiedDate());
		commercePaymentEntryImpl.setClassNameId(getClassNameId());
		commercePaymentEntryImpl.setClassPK(getClassPK());
		commercePaymentEntryImpl.setCommerceChannelId(getCommerceChannelId());
		commercePaymentEntryImpl.setAmount(getAmount());
		commercePaymentEntryImpl.setCallbackURL(getCallbackURL());
		commercePaymentEntryImpl.setCurrencyCode(getCurrencyCode());
		commercePaymentEntryImpl.setPaymentIntegrationKey(
			getPaymentIntegrationKey());
		commercePaymentEntryImpl.setPaymentIntegrationType(
			getPaymentIntegrationType());
		commercePaymentEntryImpl.setPaymentStatus(getPaymentStatus());
		commercePaymentEntryImpl.setRedirectURL(getRedirectURL());
		commercePaymentEntryImpl.setTransactionCode(getTransactionCode());

		commercePaymentEntryImpl.resetOriginalValues();

		return commercePaymentEntryImpl;
	}

	@Override
	public CommercePaymentEntry cloneWithOriginalValues() {
		CommercePaymentEntryImpl commercePaymentEntryImpl =
			new CommercePaymentEntryImpl();

		commercePaymentEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commercePaymentEntryImpl.setCommercePaymentEntryId(
			this.<Long>getColumnOriginalValue("commercePaymentEntryId"));
		commercePaymentEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commercePaymentEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commercePaymentEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commercePaymentEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commercePaymentEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commercePaymentEntryImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		commercePaymentEntryImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		commercePaymentEntryImpl.setCommerceChannelId(
			this.<Long>getColumnOriginalValue("commerceChannelId"));
		commercePaymentEntryImpl.setAmount(
			this.<BigDecimal>getColumnOriginalValue("amount"));
		commercePaymentEntryImpl.setCallbackURL(
			this.<String>getColumnOriginalValue("callbackURL"));
		commercePaymentEntryImpl.setCurrencyCode(
			this.<String>getColumnOriginalValue("currencyCode"));
		commercePaymentEntryImpl.setPaymentIntegrationKey(
			this.<String>getColumnOriginalValue("paymentIntegrationKey"));
		commercePaymentEntryImpl.setPaymentIntegrationType(
			this.<Integer>getColumnOriginalValue("paymentIntegrationType"));
		commercePaymentEntryImpl.setPaymentStatus(
			this.<Integer>getColumnOriginalValue("paymentStatus"));
		commercePaymentEntryImpl.setRedirectURL(
			this.<String>getColumnOriginalValue("redirectURL"));
		commercePaymentEntryImpl.setTransactionCode(
			this.<String>getColumnOriginalValue("transactionCode"));

		return commercePaymentEntryImpl;
	}

	@Override
	public int compareTo(CommercePaymentEntry commercePaymentEntry) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commercePaymentEntry.getCreateDate());

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

		if (!(object instanceof CommercePaymentEntry)) {
			return false;
		}

		CommercePaymentEntry commercePaymentEntry =
			(CommercePaymentEntry)object;

		long primaryKey = commercePaymentEntry.getPrimaryKey();

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
	public CacheModel<CommercePaymentEntry> toCacheModel() {
		CommercePaymentEntryCacheModel commercePaymentEntryCacheModel =
			new CommercePaymentEntryCacheModel();

		commercePaymentEntryCacheModel.mvccVersion = getMvccVersion();

		commercePaymentEntryCacheModel.commercePaymentEntryId =
			getCommercePaymentEntryId();

		commercePaymentEntryCacheModel.companyId = getCompanyId();

		commercePaymentEntryCacheModel.userId = getUserId();

		commercePaymentEntryCacheModel.userName = getUserName();

		String userName = commercePaymentEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commercePaymentEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commercePaymentEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			commercePaymentEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commercePaymentEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commercePaymentEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commercePaymentEntryCacheModel.classNameId = getClassNameId();

		commercePaymentEntryCacheModel.classPK = getClassPK();

		commercePaymentEntryCacheModel.commerceChannelId =
			getCommerceChannelId();

		commercePaymentEntryCacheModel.amount = getAmount();

		commercePaymentEntryCacheModel.callbackURL = getCallbackURL();

		String callbackURL = commercePaymentEntryCacheModel.callbackURL;

		if ((callbackURL != null) && (callbackURL.length() == 0)) {
			commercePaymentEntryCacheModel.callbackURL = null;
		}

		commercePaymentEntryCacheModel.currencyCode = getCurrencyCode();

		String currencyCode = commercePaymentEntryCacheModel.currencyCode;

		if ((currencyCode != null) && (currencyCode.length() == 0)) {
			commercePaymentEntryCacheModel.currencyCode = null;
		}

		commercePaymentEntryCacheModel.paymentIntegrationKey =
			getPaymentIntegrationKey();

		String paymentIntegrationKey =
			commercePaymentEntryCacheModel.paymentIntegrationKey;

		if ((paymentIntegrationKey != null) &&
			(paymentIntegrationKey.length() == 0)) {

			commercePaymentEntryCacheModel.paymentIntegrationKey = null;
		}

		commercePaymentEntryCacheModel.paymentIntegrationType =
			getPaymentIntegrationType();

		commercePaymentEntryCacheModel.paymentStatus = getPaymentStatus();

		commercePaymentEntryCacheModel.redirectURL = getRedirectURL();

		String redirectURL = commercePaymentEntryCacheModel.redirectURL;

		if ((redirectURL != null) && (redirectURL.length() == 0)) {
			commercePaymentEntryCacheModel.redirectURL = null;
		}

		commercePaymentEntryCacheModel.transactionCode = getTransactionCode();

		String transactionCode = commercePaymentEntryCacheModel.transactionCode;

		if ((transactionCode != null) && (transactionCode.length() == 0)) {
			commercePaymentEntryCacheModel.transactionCode = null;
		}

		return commercePaymentEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommercePaymentEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommercePaymentEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommercePaymentEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommercePaymentEntry)this);

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

		private static final Function<InvocationHandler, CommercePaymentEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommercePaymentEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _commercePaymentEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _commerceChannelId;
	private BigDecimal _amount;
	private String _callbackURL;
	private String _currencyCode;
	private String _paymentIntegrationKey;
	private int _paymentIntegrationType;
	private int _paymentStatus;
	private String _redirectURL;
	private String _transactionCode;

	public <T> T getColumnValue(String columnName) {
		Function<CommercePaymentEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommercePaymentEntry)this);
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
		_columnOriginalValues.put(
			"commercePaymentEntryId", _commercePaymentEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("commerceChannelId", _commerceChannelId);
		_columnOriginalValues.put("amount", _amount);
		_columnOriginalValues.put("callbackURL", _callbackURL);
		_columnOriginalValues.put("currencyCode", _currencyCode);
		_columnOriginalValues.put(
			"paymentIntegrationKey", _paymentIntegrationKey);
		_columnOriginalValues.put(
			"paymentIntegrationType", _paymentIntegrationType);
		_columnOriginalValues.put("paymentStatus", _paymentStatus);
		_columnOriginalValues.put("redirectURL", _redirectURL);
		_columnOriginalValues.put("transactionCode", _transactionCode);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("commercePaymentEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("classNameId", 128L);

		columnBitmasks.put("classPK", 256L);

		columnBitmasks.put("commerceChannelId", 512L);

		columnBitmasks.put("amount", 1024L);

		columnBitmasks.put("callbackURL", 2048L);

		columnBitmasks.put("currencyCode", 4096L);

		columnBitmasks.put("paymentIntegrationKey", 8192L);

		columnBitmasks.put("paymentIntegrationType", 16384L);

		columnBitmasks.put("paymentStatus", 32768L);

		columnBitmasks.put("redirectURL", 65536L);

		columnBitmasks.put("transactionCode", 131072L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommercePaymentEntry _escapedModel;

}