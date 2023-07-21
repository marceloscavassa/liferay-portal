/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dispatch.model.impl;

import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.model.DispatchTriggerModel;
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
 * The base model implementation for the DispatchTrigger service. Represents a row in the &quot;DispatchTrigger&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DispatchTriggerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DispatchTriggerImpl}.
 * </p>
 *
 * @author Matija Petanjek
 * @see DispatchTriggerImpl
 * @generated
 */
@JSON(strict = true)
public class DispatchTriggerModelImpl
	extends BaseModelImpl<DispatchTrigger> implements DispatchTriggerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dispatch trigger model instance should use the <code>DispatchTrigger</code> interface instead.
	 */
	public static final String TABLE_NAME = "DispatchTrigger";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR},
		{"dispatchTriggerId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"active_", Types.BOOLEAN}, {"cronExpression", Types.VARCHAR},
		{"dispatchTaskClusterMode", Types.INTEGER},
		{"dispatchTaskExecutorType", Types.VARCHAR},
		{"dispatchTaskSettings", Types.CLOB}, {"endDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"overlapAllowed", Types.BOOLEAN},
		{"startDate", Types.TIMESTAMP}, {"system_", Types.BOOLEAN},
		{"timeZoneId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dispatchTriggerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("cronExpression", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dispatchTaskClusterMode", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("dispatchTaskExecutorType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dispatchTaskSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("overlapAllowed", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("system_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("timeZoneId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DispatchTrigger (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,dispatchTriggerId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,active_ BOOLEAN,cronExpression VARCHAR(75) null,dispatchTaskClusterMode INTEGER,dispatchTaskExecutorType VARCHAR(75) null,dispatchTaskSettings TEXT null,endDate DATE null,name VARCHAR(75) null,overlapAllowed BOOLEAN,startDate DATE null,system_ BOOLEAN,timeZoneId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table DispatchTrigger";

	public static final String ORDER_BY_JPQL =
		" ORDER BY dispatchTrigger.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DispatchTrigger.modifiedDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DISPATCHTASKCLUSTERMODE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DISPATCHTASKEXECUTORTYPE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 128L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 256L;

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

	public DispatchTriggerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dispatchTriggerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDispatchTriggerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dispatchTriggerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DispatchTrigger.class;
	}

	@Override
	public String getModelClassName() {
		return DispatchTrigger.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DispatchTrigger, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DispatchTrigger, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DispatchTrigger, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DispatchTrigger)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DispatchTrigger, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DispatchTrigger, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DispatchTrigger)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DispatchTrigger, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DispatchTrigger, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<DispatchTrigger, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<DispatchTrigger, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<DispatchTrigger, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", DispatchTrigger::getMvccVersion);
			attributeGetterFunctions.put("uuid", DispatchTrigger::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				DispatchTrigger::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"dispatchTriggerId", DispatchTrigger::getDispatchTriggerId);
			attributeGetterFunctions.put(
				"companyId", DispatchTrigger::getCompanyId);
			attributeGetterFunctions.put("userId", DispatchTrigger::getUserId);
			attributeGetterFunctions.put(
				"userName", DispatchTrigger::getUserName);
			attributeGetterFunctions.put(
				"createDate", DispatchTrigger::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", DispatchTrigger::getModifiedDate);
			attributeGetterFunctions.put("active", DispatchTrigger::getActive);
			attributeGetterFunctions.put(
				"cronExpression", DispatchTrigger::getCronExpression);
			attributeGetterFunctions.put(
				"dispatchTaskClusterMode",
				DispatchTrigger::getDispatchTaskClusterMode);
			attributeGetterFunctions.put(
				"dispatchTaskExecutorType",
				DispatchTrigger::getDispatchTaskExecutorType);
			attributeGetterFunctions.put(
				"dispatchTaskSettings",
				DispatchTrigger::getDispatchTaskSettings);
			attributeGetterFunctions.put(
				"endDate", DispatchTrigger::getEndDate);
			attributeGetterFunctions.put("name", DispatchTrigger::getName);
			attributeGetterFunctions.put(
				"overlapAllowed", DispatchTrigger::getOverlapAllowed);
			attributeGetterFunctions.put(
				"startDate", DispatchTrigger::getStartDate);
			attributeGetterFunctions.put("system", DispatchTrigger::getSystem);
			attributeGetterFunctions.put(
				"timeZoneId", DispatchTrigger::getTimeZoneId);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<DispatchTrigger, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<DispatchTrigger, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<DispatchTrigger, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<DispatchTrigger, Long>)
					DispatchTrigger::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<DispatchTrigger, String>)DispatchTrigger::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<DispatchTrigger, String>)
					DispatchTrigger::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"dispatchTriggerId",
				(BiConsumer<DispatchTrigger, Long>)
					DispatchTrigger::setDispatchTriggerId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<DispatchTrigger, Long>)
					DispatchTrigger::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<DispatchTrigger, Long>)DispatchTrigger::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<DispatchTrigger, String>)
					DispatchTrigger::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<DispatchTrigger, Date>)
					DispatchTrigger::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<DispatchTrigger, Date>)
					DispatchTrigger::setModifiedDate);
			attributeSetterBiConsumers.put(
				"active",
				(BiConsumer<DispatchTrigger, Boolean>)
					DispatchTrigger::setActive);
			attributeSetterBiConsumers.put(
				"cronExpression",
				(BiConsumer<DispatchTrigger, String>)
					DispatchTrigger::setCronExpression);
			attributeSetterBiConsumers.put(
				"dispatchTaskClusterMode",
				(BiConsumer<DispatchTrigger, Integer>)
					DispatchTrigger::setDispatchTaskClusterMode);
			attributeSetterBiConsumers.put(
				"dispatchTaskExecutorType",
				(BiConsumer<DispatchTrigger, String>)
					DispatchTrigger::setDispatchTaskExecutorType);
			attributeSetterBiConsumers.put(
				"dispatchTaskSettings",
				(BiConsumer<DispatchTrigger, String>)
					DispatchTrigger::setDispatchTaskSettings);
			attributeSetterBiConsumers.put(
				"endDate",
				(BiConsumer<DispatchTrigger, Date>)DispatchTrigger::setEndDate);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<DispatchTrigger, String>)DispatchTrigger::setName);
			attributeSetterBiConsumers.put(
				"overlapAllowed",
				(BiConsumer<DispatchTrigger, Boolean>)
					DispatchTrigger::setOverlapAllowed);
			attributeSetterBiConsumers.put(
				"startDate",
				(BiConsumer<DispatchTrigger, Date>)
					DispatchTrigger::setStartDate);
			attributeSetterBiConsumers.put(
				"system",
				(BiConsumer<DispatchTrigger, Boolean>)
					DispatchTrigger::setSystem);
			attributeSetterBiConsumers.put(
				"timeZoneId",
				(BiConsumer<DispatchTrigger, String>)
					DispatchTrigger::setTimeZoneId);

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
	public long getDispatchTriggerId() {
		return _dispatchTriggerId;
	}

	@Override
	public void setDispatchTriggerId(long dispatchTriggerId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dispatchTriggerId = dispatchTriggerId;
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

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_active = active;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalActive() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("active_"));
	}

	@JSON
	@Override
	public String getCronExpression() {
		if (_cronExpression == null) {
			return "";
		}
		else {
			return _cronExpression;
		}
	}

	@Override
	public void setCronExpression(String cronExpression) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_cronExpression = cronExpression;
	}

	@JSON
	@Override
	public int getDispatchTaskClusterMode() {
		return _dispatchTaskClusterMode;
	}

	@Override
	public void setDispatchTaskClusterMode(int dispatchTaskClusterMode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dispatchTaskClusterMode = dispatchTaskClusterMode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalDispatchTaskClusterMode() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("dispatchTaskClusterMode"));
	}

	@JSON
	@Override
	public String getDispatchTaskExecutorType() {
		if (_dispatchTaskExecutorType == null) {
			return "";
		}
		else {
			return _dispatchTaskExecutorType;
		}
	}

	@Override
	public void setDispatchTaskExecutorType(String dispatchTaskExecutorType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dispatchTaskExecutorType = dispatchTaskExecutorType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalDispatchTaskExecutorType() {
		return getColumnOriginalValue("dispatchTaskExecutorType");
	}

	@JSON
	@Override
	public String getDispatchTaskSettings() {
		if (_dispatchTaskSettings == null) {
			return "";
		}
		else {
			return _dispatchTaskSettings;
		}
	}

	@Override
	public void setDispatchTaskSettings(String dispatchTaskSettings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dispatchTaskSettings = dispatchTaskSettings;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_endDate = endDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@JSON
	@Override
	public boolean getOverlapAllowed() {
		return _overlapAllowed;
	}

	@JSON
	@Override
	public boolean isOverlapAllowed() {
		return _overlapAllowed;
	}

	@Override
	public void setOverlapAllowed(boolean overlapAllowed) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_overlapAllowed = overlapAllowed;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_startDate = startDate;
	}

	@JSON
	@Override
	public boolean getSystem() {
		return _system;
	}

	@JSON
	@Override
	public boolean isSystem() {
		return _system;
	}

	@Override
	public void setSystem(boolean system) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_system = system;
	}

	@JSON
	@Override
	public String getTimeZoneId() {
		if (_timeZoneId == null) {
			return "";
		}
		else {
			return _timeZoneId;
		}
	}

	@Override
	public void setTimeZoneId(String timeZoneId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_timeZoneId = timeZoneId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(DispatchTrigger.class.getName()));
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
			getCompanyId(), DispatchTrigger.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DispatchTrigger toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DispatchTrigger>
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
		DispatchTriggerImpl dispatchTriggerImpl = new DispatchTriggerImpl();

		dispatchTriggerImpl.setMvccVersion(getMvccVersion());
		dispatchTriggerImpl.setUuid(getUuid());
		dispatchTriggerImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		dispatchTriggerImpl.setDispatchTriggerId(getDispatchTriggerId());
		dispatchTriggerImpl.setCompanyId(getCompanyId());
		dispatchTriggerImpl.setUserId(getUserId());
		dispatchTriggerImpl.setUserName(getUserName());
		dispatchTriggerImpl.setCreateDate(getCreateDate());
		dispatchTriggerImpl.setModifiedDate(getModifiedDate());
		dispatchTriggerImpl.setActive(isActive());
		dispatchTriggerImpl.setCronExpression(getCronExpression());
		dispatchTriggerImpl.setDispatchTaskClusterMode(
			getDispatchTaskClusterMode());
		dispatchTriggerImpl.setDispatchTaskExecutorType(
			getDispatchTaskExecutorType());
		dispatchTriggerImpl.setDispatchTaskSettings(getDispatchTaskSettings());
		dispatchTriggerImpl.setEndDate(getEndDate());
		dispatchTriggerImpl.setName(getName());
		dispatchTriggerImpl.setOverlapAllowed(isOverlapAllowed());
		dispatchTriggerImpl.setStartDate(getStartDate());
		dispatchTriggerImpl.setSystem(isSystem());
		dispatchTriggerImpl.setTimeZoneId(getTimeZoneId());

		dispatchTriggerImpl.resetOriginalValues();

		return dispatchTriggerImpl;
	}

	@Override
	public DispatchTrigger cloneWithOriginalValues() {
		DispatchTriggerImpl dispatchTriggerImpl = new DispatchTriggerImpl();

		dispatchTriggerImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		dispatchTriggerImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		dispatchTriggerImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		dispatchTriggerImpl.setDispatchTriggerId(
			this.<Long>getColumnOriginalValue("dispatchTriggerId"));
		dispatchTriggerImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		dispatchTriggerImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		dispatchTriggerImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		dispatchTriggerImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		dispatchTriggerImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		dispatchTriggerImpl.setActive(
			this.<Boolean>getColumnOriginalValue("active_"));
		dispatchTriggerImpl.setCronExpression(
			this.<String>getColumnOriginalValue("cronExpression"));
		dispatchTriggerImpl.setDispatchTaskClusterMode(
			this.<Integer>getColumnOriginalValue("dispatchTaskClusterMode"));
		dispatchTriggerImpl.setDispatchTaskExecutorType(
			this.<String>getColumnOriginalValue("dispatchTaskExecutorType"));
		dispatchTriggerImpl.setDispatchTaskSettings(
			this.<String>getColumnOriginalValue("dispatchTaskSettings"));
		dispatchTriggerImpl.setEndDate(
			this.<Date>getColumnOriginalValue("endDate"));
		dispatchTriggerImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		dispatchTriggerImpl.setOverlapAllowed(
			this.<Boolean>getColumnOriginalValue("overlapAllowed"));
		dispatchTriggerImpl.setStartDate(
			this.<Date>getColumnOriginalValue("startDate"));
		dispatchTriggerImpl.setSystem(
			this.<Boolean>getColumnOriginalValue("system_"));
		dispatchTriggerImpl.setTimeZoneId(
			this.<String>getColumnOriginalValue("timeZoneId"));

		return dispatchTriggerImpl;
	}

	@Override
	public int compareTo(DispatchTrigger dispatchTrigger) {
		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(), dispatchTrigger.getModifiedDate());

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

		if (!(object instanceof DispatchTrigger)) {
			return false;
		}

		DispatchTrigger dispatchTrigger = (DispatchTrigger)object;

		long primaryKey = dispatchTrigger.getPrimaryKey();

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
	public CacheModel<DispatchTrigger> toCacheModel() {
		DispatchTriggerCacheModel dispatchTriggerCacheModel =
			new DispatchTriggerCacheModel();

		dispatchTriggerCacheModel.mvccVersion = getMvccVersion();

		dispatchTriggerCacheModel.uuid = getUuid();

		String uuid = dispatchTriggerCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dispatchTriggerCacheModel.uuid = null;
		}

		dispatchTriggerCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			dispatchTriggerCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			dispatchTriggerCacheModel.externalReferenceCode = null;
		}

		dispatchTriggerCacheModel.dispatchTriggerId = getDispatchTriggerId();

		dispatchTriggerCacheModel.companyId = getCompanyId();

		dispatchTriggerCacheModel.userId = getUserId();

		dispatchTriggerCacheModel.userName = getUserName();

		String userName = dispatchTriggerCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			dispatchTriggerCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			dispatchTriggerCacheModel.createDate = createDate.getTime();
		}
		else {
			dispatchTriggerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			dispatchTriggerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			dispatchTriggerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		dispatchTriggerCacheModel.active = isActive();

		dispatchTriggerCacheModel.cronExpression = getCronExpression();

		String cronExpression = dispatchTriggerCacheModel.cronExpression;

		if ((cronExpression != null) && (cronExpression.length() == 0)) {
			dispatchTriggerCacheModel.cronExpression = null;
		}

		dispatchTriggerCacheModel.dispatchTaskClusterMode =
			getDispatchTaskClusterMode();

		dispatchTriggerCacheModel.dispatchTaskExecutorType =
			getDispatchTaskExecutorType();

		String dispatchTaskExecutorType =
			dispatchTriggerCacheModel.dispatchTaskExecutorType;

		if ((dispatchTaskExecutorType != null) &&
			(dispatchTaskExecutorType.length() == 0)) {

			dispatchTriggerCacheModel.dispatchTaskExecutorType = null;
		}

		dispatchTriggerCacheModel.dispatchTaskSettings =
			getDispatchTaskSettings();

		String dispatchTaskSettings =
			dispatchTriggerCacheModel.dispatchTaskSettings;

		if ((dispatchTaskSettings != null) &&
			(dispatchTaskSettings.length() == 0)) {

			dispatchTriggerCacheModel.dispatchTaskSettings = null;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			dispatchTriggerCacheModel.endDate = endDate.getTime();
		}
		else {
			dispatchTriggerCacheModel.endDate = Long.MIN_VALUE;
		}

		dispatchTriggerCacheModel.name = getName();

		String name = dispatchTriggerCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			dispatchTriggerCacheModel.name = null;
		}

		dispatchTriggerCacheModel.overlapAllowed = isOverlapAllowed();

		Date startDate = getStartDate();

		if (startDate != null) {
			dispatchTriggerCacheModel.startDate = startDate.getTime();
		}
		else {
			dispatchTriggerCacheModel.startDate = Long.MIN_VALUE;
		}

		dispatchTriggerCacheModel.system = isSystem();

		dispatchTriggerCacheModel.timeZoneId = getTimeZoneId();

		String timeZoneId = dispatchTriggerCacheModel.timeZoneId;

		if ((timeZoneId != null) && (timeZoneId.length() == 0)) {
			dispatchTriggerCacheModel.timeZoneId = null;
		}

		return dispatchTriggerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DispatchTrigger, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DispatchTrigger, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DispatchTrigger, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((DispatchTrigger)this);

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

		private static final Function<InvocationHandler, DispatchTrigger>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					DispatchTrigger.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private String _externalReferenceCode;
	private long _dispatchTriggerId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _active;
	private String _cronExpression;
	private int _dispatchTaskClusterMode;
	private String _dispatchTaskExecutorType;
	private String _dispatchTaskSettings;
	private Date _endDate;
	private String _name;
	private boolean _overlapAllowed;
	private Date _startDate;
	private boolean _system;
	private String _timeZoneId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DispatchTrigger, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DispatchTrigger)this);
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
		_columnOriginalValues.put("dispatchTriggerId", _dispatchTriggerId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("active_", _active);
		_columnOriginalValues.put("cronExpression", _cronExpression);
		_columnOriginalValues.put(
			"dispatchTaskClusterMode", _dispatchTaskClusterMode);
		_columnOriginalValues.put(
			"dispatchTaskExecutorType", _dispatchTaskExecutorType);
		_columnOriginalValues.put(
			"dispatchTaskSettings", _dispatchTaskSettings);
		_columnOriginalValues.put("endDate", _endDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("overlapAllowed", _overlapAllowed);
		_columnOriginalValues.put("startDate", _startDate);
		_columnOriginalValues.put("system_", _system);
		_columnOriginalValues.put("timeZoneId", _timeZoneId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("active_", "active");
		attributeNames.put("system_", "system");

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

		columnBitmasks.put("dispatchTriggerId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("active_", 512L);

		columnBitmasks.put("cronExpression", 1024L);

		columnBitmasks.put("dispatchTaskClusterMode", 2048L);

		columnBitmasks.put("dispatchTaskExecutorType", 4096L);

		columnBitmasks.put("dispatchTaskSettings", 8192L);

		columnBitmasks.put("endDate", 16384L);

		columnBitmasks.put("name", 32768L);

		columnBitmasks.put("overlapAllowed", 65536L);

		columnBitmasks.put("startDate", 131072L);

		columnBitmasks.put("system_", 262144L);

		columnBitmasks.put("timeZoneId", 524288L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DispatchTrigger _escapedModel;

}