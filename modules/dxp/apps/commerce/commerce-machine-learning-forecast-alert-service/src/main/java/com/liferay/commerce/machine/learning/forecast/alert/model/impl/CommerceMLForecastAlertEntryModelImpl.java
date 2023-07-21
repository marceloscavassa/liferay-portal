/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.forecast.alert.model.impl;

import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntryModel;
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
 * The base model implementation for the CommerceMLForecastAlertEntry service. Represents a row in the &quot;CommerceMLForecastAlertEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceMLForecastAlertEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceMLForecastAlertEntryImpl}.
 * </p>
 *
 * @author Riccardo Ferrari
 * @see CommerceMLForecastAlertEntryImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceMLForecastAlertEntryModelImpl
	extends BaseModelImpl<CommerceMLForecastAlertEntry>
	implements CommerceMLForecastAlertEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce ml forecast alert entry model instance should use the <code>CommerceMLForecastAlertEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceMLForecastAlertEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"commerceMLForecastAlertEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceAccountId", Types.BIGINT},
		{"actual", Types.DOUBLE}, {"forecast", Types.DOUBLE},
		{"timestamp", Types.TIMESTAMP}, {"relativeChange", Types.DOUBLE},
		{"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceMLForecastAlertEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("actual", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("forecast", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("timestamp", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("relativeChange", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceMLForecastAlertEntry (uuid_ VARCHAR(75) null,commerceMLForecastAlertEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountId LONG,actual DOUBLE,forecast DOUBLE,timestamp DATE null,relativeChange DOUBLE,status INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceMLForecastAlertEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceMLForecastAlertEntry.timestamp ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceMLForecastAlertEntry.timestamp ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RELATIVECHANGE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TIMESTAMP_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

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

	public CommerceMLForecastAlertEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceMLForecastAlertEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceMLForecastAlertEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceMLForecastAlertEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceMLForecastAlertEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceMLForecastAlertEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceMLForecastAlertEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceMLForecastAlertEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceMLForecastAlertEntry, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceMLForecastAlertEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceMLForecastAlertEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceMLForecastAlertEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceMLForecastAlertEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceMLForecastAlertEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceMLForecastAlertEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CommerceMLForecastAlertEntry, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceMLForecastAlertEntry, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String,
						 Function<CommerceMLForecastAlertEntry, Object>>();

			attributeGetterFunctions.put(
				"uuid", CommerceMLForecastAlertEntry::getUuid);
			attributeGetterFunctions.put(
				"commerceMLForecastAlertEntryId",
				CommerceMLForecastAlertEntry::
					getCommerceMLForecastAlertEntryId);
			attributeGetterFunctions.put(
				"companyId", CommerceMLForecastAlertEntry::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommerceMLForecastAlertEntry::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceMLForecastAlertEntry::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceMLForecastAlertEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceMLForecastAlertEntry::getModifiedDate);
			attributeGetterFunctions.put(
				"commerceAccountId",
				CommerceMLForecastAlertEntry::getCommerceAccountId);
			attributeGetterFunctions.put(
				"actual", CommerceMLForecastAlertEntry::getActual);
			attributeGetterFunctions.put(
				"forecast", CommerceMLForecastAlertEntry::getForecast);
			attributeGetterFunctions.put(
				"timestamp", CommerceMLForecastAlertEntry::getTimestamp);
			attributeGetterFunctions.put(
				"relativeChange",
				CommerceMLForecastAlertEntry::getRelativeChange);
			attributeGetterFunctions.put(
				"status", CommerceMLForecastAlertEntry::getStatus);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommerceMLForecastAlertEntry, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceMLForecastAlertEntry, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceMLForecastAlertEntry, ?>>();

			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommerceMLForecastAlertEntry, String>)
					CommerceMLForecastAlertEntry::setUuid);
			attributeSetterBiConsumers.put(
				"commerceMLForecastAlertEntryId",
				(BiConsumer<CommerceMLForecastAlertEntry, Long>)
					CommerceMLForecastAlertEntry::
						setCommerceMLForecastAlertEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceMLForecastAlertEntry, Long>)
					CommerceMLForecastAlertEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceMLForecastAlertEntry, Long>)
					CommerceMLForecastAlertEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceMLForecastAlertEntry, String>)
					CommerceMLForecastAlertEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceMLForecastAlertEntry, Date>)
					CommerceMLForecastAlertEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceMLForecastAlertEntry, Date>)
					CommerceMLForecastAlertEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"commerceAccountId",
				(BiConsumer<CommerceMLForecastAlertEntry, Long>)
					CommerceMLForecastAlertEntry::setCommerceAccountId);
			attributeSetterBiConsumers.put(
				"actual",
				(BiConsumer<CommerceMLForecastAlertEntry, Double>)
					CommerceMLForecastAlertEntry::setActual);
			attributeSetterBiConsumers.put(
				"forecast",
				(BiConsumer<CommerceMLForecastAlertEntry, Double>)
					CommerceMLForecastAlertEntry::setForecast);
			attributeSetterBiConsumers.put(
				"timestamp",
				(BiConsumer<CommerceMLForecastAlertEntry, Date>)
					CommerceMLForecastAlertEntry::setTimestamp);
			attributeSetterBiConsumers.put(
				"relativeChange",
				(BiConsumer<CommerceMLForecastAlertEntry, Double>)
					CommerceMLForecastAlertEntry::setRelativeChange);
			attributeSetterBiConsumers.put(
				"status",
				(BiConsumer<CommerceMLForecastAlertEntry, Integer>)
					CommerceMLForecastAlertEntry::setStatus);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

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
	public long getCommerceMLForecastAlertEntryId() {
		return _commerceMLForecastAlertEntryId;
	}

	@Override
	public void setCommerceMLForecastAlertEntryId(
		long commerceMLForecastAlertEntryId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceMLForecastAlertEntryId = commerceMLForecastAlertEntryId;
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

	@JSON
	@Override
	public double getActual() {
		return _actual;
	}

	@Override
	public void setActual(double actual) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_actual = actual;
	}

	@JSON
	@Override
	public double getForecast() {
		return _forecast;
	}

	@Override
	public void setForecast(double forecast) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_forecast = forecast;
	}

	@JSON
	@Override
	public Date getTimestamp() {
		return _timestamp;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_timestamp = timestamp;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalTimestamp() {
		return getColumnOriginalValue("timestamp");
	}

	@JSON
	@Override
	public double getRelativeChange() {
		return _relativeChange;
	}

	@Override
	public void setRelativeChange(double relativeChange) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_relativeChange = relativeChange;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public double getOriginalRelativeChange() {
		return GetterUtil.getDouble(
			this.<Double>getColumnOriginalValue("relativeChange"));
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CommerceMLForecastAlertEntry.class.getName()));
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
			getCompanyId(), CommerceMLForecastAlertEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceMLForecastAlertEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceMLForecastAlertEntry>
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
		CommerceMLForecastAlertEntryImpl commerceMLForecastAlertEntryImpl =
			new CommerceMLForecastAlertEntryImpl();

		commerceMLForecastAlertEntryImpl.setUuid(getUuid());
		commerceMLForecastAlertEntryImpl.setCommerceMLForecastAlertEntryId(
			getCommerceMLForecastAlertEntryId());
		commerceMLForecastAlertEntryImpl.setCompanyId(getCompanyId());
		commerceMLForecastAlertEntryImpl.setUserId(getUserId());
		commerceMLForecastAlertEntryImpl.setUserName(getUserName());
		commerceMLForecastAlertEntryImpl.setCreateDate(getCreateDate());
		commerceMLForecastAlertEntryImpl.setModifiedDate(getModifiedDate());
		commerceMLForecastAlertEntryImpl.setCommerceAccountId(
			getCommerceAccountId());
		commerceMLForecastAlertEntryImpl.setActual(getActual());
		commerceMLForecastAlertEntryImpl.setForecast(getForecast());
		commerceMLForecastAlertEntryImpl.setTimestamp(getTimestamp());
		commerceMLForecastAlertEntryImpl.setRelativeChange(getRelativeChange());
		commerceMLForecastAlertEntryImpl.setStatus(getStatus());

		commerceMLForecastAlertEntryImpl.resetOriginalValues();

		return commerceMLForecastAlertEntryImpl;
	}

	@Override
	public CommerceMLForecastAlertEntry cloneWithOriginalValues() {
		CommerceMLForecastAlertEntryImpl commerceMLForecastAlertEntryImpl =
			new CommerceMLForecastAlertEntryImpl();

		commerceMLForecastAlertEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceMLForecastAlertEntryImpl.setCommerceMLForecastAlertEntryId(
			this.<Long>getColumnOriginalValue(
				"commerceMLForecastAlertEntryId"));
		commerceMLForecastAlertEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceMLForecastAlertEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceMLForecastAlertEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceMLForecastAlertEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceMLForecastAlertEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceMLForecastAlertEntryImpl.setCommerceAccountId(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
		commerceMLForecastAlertEntryImpl.setActual(
			this.<Double>getColumnOriginalValue("actual"));
		commerceMLForecastAlertEntryImpl.setForecast(
			this.<Double>getColumnOriginalValue("forecast"));
		commerceMLForecastAlertEntryImpl.setTimestamp(
			this.<Date>getColumnOriginalValue("timestamp"));
		commerceMLForecastAlertEntryImpl.setRelativeChange(
			this.<Double>getColumnOriginalValue("relativeChange"));
		commerceMLForecastAlertEntryImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));

		return commerceMLForecastAlertEntryImpl;
	}

	@Override
	public int compareTo(
		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

		int value = 0;

		value = DateUtil.compareTo(
			getTimestamp(), commerceMLForecastAlertEntry.getTimestamp());

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

		if (!(object instanceof CommerceMLForecastAlertEntry)) {
			return false;
		}

		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry =
			(CommerceMLForecastAlertEntry)object;

		long primaryKey = commerceMLForecastAlertEntry.getPrimaryKey();

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
	public CacheModel<CommerceMLForecastAlertEntry> toCacheModel() {
		CommerceMLForecastAlertEntryCacheModel
			commerceMLForecastAlertEntryCacheModel =
				new CommerceMLForecastAlertEntryCacheModel();

		commerceMLForecastAlertEntryCacheModel.uuid = getUuid();

		String uuid = commerceMLForecastAlertEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceMLForecastAlertEntryCacheModel.uuid = null;
		}

		commerceMLForecastAlertEntryCacheModel.commerceMLForecastAlertEntryId =
			getCommerceMLForecastAlertEntryId();

		commerceMLForecastAlertEntryCacheModel.companyId = getCompanyId();

		commerceMLForecastAlertEntryCacheModel.userId = getUserId();

		commerceMLForecastAlertEntryCacheModel.userName = getUserName();

		String userName = commerceMLForecastAlertEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceMLForecastAlertEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceMLForecastAlertEntryCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceMLForecastAlertEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceMLForecastAlertEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceMLForecastAlertEntryCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceMLForecastAlertEntryCacheModel.commerceAccountId =
			getCommerceAccountId();

		commerceMLForecastAlertEntryCacheModel.actual = getActual();

		commerceMLForecastAlertEntryCacheModel.forecast = getForecast();

		Date timestamp = getTimestamp();

		if (timestamp != null) {
			commerceMLForecastAlertEntryCacheModel.timestamp =
				timestamp.getTime();
		}
		else {
			commerceMLForecastAlertEntryCacheModel.timestamp = Long.MIN_VALUE;
		}

		commerceMLForecastAlertEntryCacheModel.relativeChange =
			getRelativeChange();

		commerceMLForecastAlertEntryCacheModel.status = getStatus();

		return commerceMLForecastAlertEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceMLForecastAlertEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceMLForecastAlertEntry, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceMLForecastAlertEntry, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceMLForecastAlertEntry)this);

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
			<InvocationHandler, CommerceMLForecastAlertEntry>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CommerceMLForecastAlertEntry.class, ModelWrapper.class);

	}

	private String _uuid;
	private long _commerceMLForecastAlertEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountId;
	private double _actual;
	private double _forecast;
	private Date _timestamp;
	private double _relativeChange;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceMLForecastAlertEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceMLForecastAlertEntry)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"commerceMLForecastAlertEntryId", _commerceMLForecastAlertEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
		_columnOriginalValues.put("actual", _actual);
		_columnOriginalValues.put("forecast", _forecast);
		_columnOriginalValues.put("timestamp", _timestamp);
		_columnOriginalValues.put("relativeChange", _relativeChange);
		_columnOriginalValues.put("status", _status);
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

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("commerceMLForecastAlertEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("commerceAccountId", 128L);

		columnBitmasks.put("actual", 256L);

		columnBitmasks.put("forecast", 512L);

		columnBitmasks.put("timestamp", 1024L);

		columnBitmasks.put("relativeChange", 2048L);

		columnBitmasks.put("status", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceMLForecastAlertEntry _escapedModel;

}