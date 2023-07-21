/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.model.impl;

import com.liferay.commerce.discount.model.CommerceDiscountUsageEntry;
import com.liferay.commerce.discount.model.CommerceDiscountUsageEntryModel;
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
import com.liferay.portal.kernel.util.DateUtil;
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
 * The base model implementation for the CommerceDiscountUsageEntry service. Represents a row in the &quot;CommerceDiscountUsageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceDiscountUsageEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceDiscountUsageEntryImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountUsageEntryImpl
 * @generated
 */
public class CommerceDiscountUsageEntryModelImpl
	extends BaseModelImpl<CommerceDiscountUsageEntry>
	implements CommerceDiscountUsageEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce discount usage entry model instance should use the <code>CommerceDiscountUsageEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceDiscountUsageEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"commerceDiscountUsageEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceAccountId", Types.BIGINT},
		{"commerceOrderId", Types.BIGINT}, {"commerceDiscountId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceDiscountUsageEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceOrderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceDiscountId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceDiscountUsageEntry (mvccVersion LONG default 0 not null,commerceDiscountUsageEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountId LONG,commerceOrderId LONG,commerceDiscountId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceDiscountUsageEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceDiscountUsageEntry.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceDiscountUsageEntry.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEDISCOUNTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEORDERID_COLUMN_BITMASK = 4L;

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

	public CommerceDiscountUsageEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceDiscountUsageEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceDiscountUsageEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceDiscountUsageEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceDiscountUsageEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceDiscountUsageEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceDiscountUsageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceDiscountUsageEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountUsageEntry, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceDiscountUsageEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceDiscountUsageEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceDiscountUsageEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceDiscountUsageEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceDiscountUsageEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceDiscountUsageEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CommerceDiscountUsageEntry, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceDiscountUsageEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<CommerceDiscountUsageEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceDiscountUsageEntry::getMvccVersion);
			attributeGetterFunctions.put(
				"commerceDiscountUsageEntryId",
				CommerceDiscountUsageEntry::getCommerceDiscountUsageEntryId);
			attributeGetterFunctions.put(
				"companyId", CommerceDiscountUsageEntry::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommerceDiscountUsageEntry::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceDiscountUsageEntry::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceDiscountUsageEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceDiscountUsageEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"commerceAccountId",
				CommerceDiscountUsageEntry::getCommerceAccountId);
			attributeGetterFunctions.put(
				"commerceOrderId",
				CommerceDiscountUsageEntry::getCommerceOrderId);
			attributeGetterFunctions.put(
				"commerceDiscountId",
				CommerceDiscountUsageEntry::getCommerceDiscountId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommerceDiscountUsageEntry, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceDiscountUsageEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceDiscountUsageEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"commerceDiscountUsageEntryId",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::
						setCommerceDiscountUsageEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceDiscountUsageEntry, String>)
					CommerceDiscountUsageEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceDiscountUsageEntry, Date>)
					CommerceDiscountUsageEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceDiscountUsageEntry, Date>)
					CommerceDiscountUsageEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"commerceAccountId",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::setCommerceAccountId);
			attributeSetterBiConsumers.put(
				"commerceOrderId",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::setCommerceOrderId);
			attributeSetterBiConsumers.put(
				"commerceDiscountId",
				(BiConsumer<CommerceDiscountUsageEntry, Long>)
					CommerceDiscountUsageEntry::setCommerceDiscountId);

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
	public long getCommerceDiscountUsageEntryId() {
		return _commerceDiscountUsageEntryId;
	}

	@Override
	public void setCommerceDiscountUsageEntryId(
		long commerceDiscountUsageEntryId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceDiscountUsageEntryId = commerceDiscountUsageEntryId;
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
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountId = commerceAccountId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAccountId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
	}

	@Override
	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	@Override
	public void setCommerceOrderId(long commerceOrderId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderId = commerceOrderId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceOrderId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceOrderId"));
	}

	@Override
	public long getCommerceDiscountId() {
		return _commerceDiscountId;
	}

	@Override
	public void setCommerceDiscountId(long commerceDiscountId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceDiscountId = commerceDiscountId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceDiscountId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceDiscountId"));
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
			getCompanyId(), CommerceDiscountUsageEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceDiscountUsageEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceDiscountUsageEntry>
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
		CommerceDiscountUsageEntryImpl commerceDiscountUsageEntryImpl =
			new CommerceDiscountUsageEntryImpl();

		commerceDiscountUsageEntryImpl.setMvccVersion(getMvccVersion());
		commerceDiscountUsageEntryImpl.setCommerceDiscountUsageEntryId(
			getCommerceDiscountUsageEntryId());
		commerceDiscountUsageEntryImpl.setCompanyId(getCompanyId());
		commerceDiscountUsageEntryImpl.setUserId(getUserId());
		commerceDiscountUsageEntryImpl.setUserName(getUserName());
		commerceDiscountUsageEntryImpl.setCreateDate(getCreateDate());
		commerceDiscountUsageEntryImpl.setModifiedDate(getModifiedDate());
		commerceDiscountUsageEntryImpl.setCommerceAccountId(
			getCommerceAccountId());
		commerceDiscountUsageEntryImpl.setCommerceOrderId(getCommerceOrderId());
		commerceDiscountUsageEntryImpl.setCommerceDiscountId(
			getCommerceDiscountId());

		commerceDiscountUsageEntryImpl.resetOriginalValues();

		return commerceDiscountUsageEntryImpl;
	}

	@Override
	public CommerceDiscountUsageEntry cloneWithOriginalValues() {
		CommerceDiscountUsageEntryImpl commerceDiscountUsageEntryImpl =
			new CommerceDiscountUsageEntryImpl();

		commerceDiscountUsageEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceDiscountUsageEntryImpl.setCommerceDiscountUsageEntryId(
			this.<Long>getColumnOriginalValue("commerceDiscountUsageEntryId"));
		commerceDiscountUsageEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceDiscountUsageEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceDiscountUsageEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceDiscountUsageEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceDiscountUsageEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceDiscountUsageEntryImpl.setCommerceAccountId(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
		commerceDiscountUsageEntryImpl.setCommerceOrderId(
			this.<Long>getColumnOriginalValue("commerceOrderId"));
		commerceDiscountUsageEntryImpl.setCommerceDiscountId(
			this.<Long>getColumnOriginalValue("commerceDiscountId"));

		return commerceDiscountUsageEntryImpl;
	}

	@Override
	public int compareTo(
		CommerceDiscountUsageEntry commerceDiscountUsageEntry) {

		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceDiscountUsageEntry.getCreateDate());

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

		if (!(object instanceof CommerceDiscountUsageEntry)) {
			return false;
		}

		CommerceDiscountUsageEntry commerceDiscountUsageEntry =
			(CommerceDiscountUsageEntry)object;

		long primaryKey = commerceDiscountUsageEntry.getPrimaryKey();

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
	public CacheModel<CommerceDiscountUsageEntry> toCacheModel() {
		CommerceDiscountUsageEntryCacheModel
			commerceDiscountUsageEntryCacheModel =
				new CommerceDiscountUsageEntryCacheModel();

		commerceDiscountUsageEntryCacheModel.mvccVersion = getMvccVersion();

		commerceDiscountUsageEntryCacheModel.commerceDiscountUsageEntryId =
			getCommerceDiscountUsageEntryId();

		commerceDiscountUsageEntryCacheModel.companyId = getCompanyId();

		commerceDiscountUsageEntryCacheModel.userId = getUserId();

		commerceDiscountUsageEntryCacheModel.userName = getUserName();

		String userName = commerceDiscountUsageEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceDiscountUsageEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceDiscountUsageEntryCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceDiscountUsageEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceDiscountUsageEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceDiscountUsageEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceDiscountUsageEntryCacheModel.commerceAccountId =
			getCommerceAccountId();

		commerceDiscountUsageEntryCacheModel.commerceOrderId =
			getCommerceOrderId();

		commerceDiscountUsageEntryCacheModel.commerceDiscountId =
			getCommerceDiscountId();

		return commerceDiscountUsageEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceDiscountUsageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceDiscountUsageEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountUsageEntry, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceDiscountUsageEntry)this);

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
			<InvocationHandler, CommerceDiscountUsageEntry>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CommerceDiscountUsageEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _commerceDiscountUsageEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountId;
	private long _commerceOrderId;
	private long _commerceDiscountId;

	public <T> T getColumnValue(String columnName) {
		Function<CommerceDiscountUsageEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceDiscountUsageEntry)this);
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
			"commerceDiscountUsageEntryId", _commerceDiscountUsageEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
		_columnOriginalValues.put("commerceOrderId", _commerceOrderId);
		_columnOriginalValues.put("commerceDiscountId", _commerceDiscountId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("commerceDiscountUsageEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("commerceAccountId", 128L);

		columnBitmasks.put("commerceOrderId", 256L);

		columnBitmasks.put("commerceDiscountId", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceDiscountUsageEntry _escapedModel;

}