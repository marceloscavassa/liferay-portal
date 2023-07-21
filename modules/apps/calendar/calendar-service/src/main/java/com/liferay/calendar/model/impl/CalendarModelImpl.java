/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.model.impl;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
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
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Calendar service. Represents a row in the &quot;Calendar&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CalendarModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CalendarImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarImpl
 * @generated
 */
@JSON(strict = true)
public class CalendarModelImpl
	extends BaseModelImpl<Calendar> implements CalendarModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a calendar model instance should use the <code>Calendar</code> interface instead.
	 */
	public static final String TABLE_NAME = "Calendar";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"calendarId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"calendarResourceId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"timeZoneId", Types.VARCHAR},
		{"color", Types.INTEGER}, {"defaultCalendar", Types.BOOLEAN},
		{"enableComments", Types.BOOLEAN}, {"enableRatings", Types.BOOLEAN},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("calendarId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("calendarResourceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("timeZoneId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("color", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("defaultCalendar", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("enableComments", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("enableRatings", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Calendar (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,calendarId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,calendarResourceId LONG,name STRING null,description STRING null,timeZoneId VARCHAR(75) null,color INTEGER,defaultCalendar BOOLEAN,enableComments BOOLEAN,enableRatings BOOLEAN,lastPublishDate DATE null,primary key (calendarId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table Calendar";

	public static final String ORDER_BY_JPQL = " ORDER BY calendar.name ASC";

	public static final String ORDER_BY_SQL = " ORDER BY Calendar.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CALENDARRESOURCEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEFAULTCALENDAR_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 32L;

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

	public CalendarModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _calendarId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCalendarId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _calendarId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Calendar.class;
	}

	@Override
	public String getModelClassName() {
		return Calendar.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Calendar, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Calendar, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Calendar, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Calendar)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Calendar, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Calendar, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Calendar)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Calendar, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Calendar, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<Calendar, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<Calendar, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<Calendar, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", Calendar::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", Calendar::getCtCollectionId);
			attributeGetterFunctions.put("uuid", Calendar::getUuid);
			attributeGetterFunctions.put("calendarId", Calendar::getCalendarId);
			attributeGetterFunctions.put("groupId", Calendar::getGroupId);
			attributeGetterFunctions.put("companyId", Calendar::getCompanyId);
			attributeGetterFunctions.put("userId", Calendar::getUserId);
			attributeGetterFunctions.put("userName", Calendar::getUserName);
			attributeGetterFunctions.put("createDate", Calendar::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", Calendar::getModifiedDate);
			attributeGetterFunctions.put(
				"calendarResourceId", Calendar::getCalendarResourceId);
			attributeGetterFunctions.put("name", Calendar::getName);
			attributeGetterFunctions.put(
				"description", Calendar::getDescription);
			attributeGetterFunctions.put("timeZoneId", Calendar::getTimeZoneId);
			attributeGetterFunctions.put("color", Calendar::getColor);
			attributeGetterFunctions.put(
				"defaultCalendar", Calendar::getDefaultCalendar);
			attributeGetterFunctions.put(
				"enableComments", Calendar::getEnableComments);
			attributeGetterFunctions.put(
				"enableRatings", Calendar::getEnableRatings);
			attributeGetterFunctions.put(
				"lastPublishDate", Calendar::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<Calendar, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<Calendar, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<Calendar, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<Calendar, Long>)Calendar::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<Calendar, Long>)Calendar::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid", (BiConsumer<Calendar, String>)Calendar::setUuid);
			attributeSetterBiConsumers.put(
				"calendarId",
				(BiConsumer<Calendar, Long>)Calendar::setCalendarId);
			attributeSetterBiConsumers.put(
				"groupId", (BiConsumer<Calendar, Long>)Calendar::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<Calendar, Long>)Calendar::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<Calendar, Long>)Calendar::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<Calendar, String>)Calendar::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<Calendar, Date>)Calendar::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<Calendar, Date>)Calendar::setModifiedDate);
			attributeSetterBiConsumers.put(
				"calendarResourceId",
				(BiConsumer<Calendar, Long>)Calendar::setCalendarResourceId);
			attributeSetterBiConsumers.put(
				"name", (BiConsumer<Calendar, String>)Calendar::setName);
			attributeSetterBiConsumers.put(
				"description",
				(BiConsumer<Calendar, String>)Calendar::setDescription);
			attributeSetterBiConsumers.put(
				"timeZoneId",
				(BiConsumer<Calendar, String>)Calendar::setTimeZoneId);
			attributeSetterBiConsumers.put(
				"color", (BiConsumer<Calendar, Integer>)Calendar::setColor);
			attributeSetterBiConsumers.put(
				"defaultCalendar",
				(BiConsumer<Calendar, Boolean>)Calendar::setDefaultCalendar);
			attributeSetterBiConsumers.put(
				"enableComments",
				(BiConsumer<Calendar, Boolean>)Calendar::setEnableComments);
			attributeSetterBiConsumers.put(
				"enableRatings",
				(BiConsumer<Calendar, Boolean>)Calendar::setEnableRatings);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<Calendar, Date>)Calendar::setLastPublishDate);

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
	public long getCalendarId() {
		return _calendarId;
	}

	@Override
	public void setCalendarId(long calendarId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_calendarId = calendarId;
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
	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	@Override
	public void setCalendarResourceId(long calendarResourceId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_calendarResourceId = calendarResourceId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCalendarResourceId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("calendarResourceId"));
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
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
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

	@JSON
	@Override
	public int getColor() {
		return _color;
	}

	@Override
	public void setColor(int color) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_color = color;
	}

	@JSON
	@Override
	public boolean getDefaultCalendar() {
		return _defaultCalendar;
	}

	@JSON
	@Override
	public boolean isDefaultCalendar() {
		return _defaultCalendar;
	}

	@Override
	public void setDefaultCalendar(boolean defaultCalendar) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultCalendar = defaultCalendar;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalDefaultCalendar() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("defaultCalendar"));
	}

	@JSON
	@Override
	public boolean getEnableComments() {
		return _enableComments;
	}

	@JSON
	@Override
	public boolean isEnableComments() {
		return _enableComments;
	}

	@Override
	public void setEnableComments(boolean enableComments) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_enableComments = enableComments;
	}

	@JSON
	@Override
	public boolean getEnableRatings() {
		return _enableRatings;
	}

	@JSON
	@Override
	public boolean isEnableRatings() {
		return _enableRatings;
	}

	@Override
	public void setEnableRatings(boolean enableRatings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_enableRatings = enableRatings;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Calendar.class.getName()));
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
			getCompanyId(), Calendar.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			Calendar.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public Calendar toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Calendar>
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
		CalendarImpl calendarImpl = new CalendarImpl();

		calendarImpl.setMvccVersion(getMvccVersion());
		calendarImpl.setCtCollectionId(getCtCollectionId());
		calendarImpl.setUuid(getUuid());
		calendarImpl.setCalendarId(getCalendarId());
		calendarImpl.setGroupId(getGroupId());
		calendarImpl.setCompanyId(getCompanyId());
		calendarImpl.setUserId(getUserId());
		calendarImpl.setUserName(getUserName());
		calendarImpl.setCreateDate(getCreateDate());
		calendarImpl.setModifiedDate(getModifiedDate());
		calendarImpl.setCalendarResourceId(getCalendarResourceId());
		calendarImpl.setName(getName());
		calendarImpl.setDescription(getDescription());
		calendarImpl.setTimeZoneId(getTimeZoneId());
		calendarImpl.setColor(getColor());
		calendarImpl.setDefaultCalendar(isDefaultCalendar());
		calendarImpl.setEnableComments(isEnableComments());
		calendarImpl.setEnableRatings(isEnableRatings());
		calendarImpl.setLastPublishDate(getLastPublishDate());

		calendarImpl.resetOriginalValues();

		return calendarImpl;
	}

	@Override
	public Calendar cloneWithOriginalValues() {
		CalendarImpl calendarImpl = new CalendarImpl();

		calendarImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		calendarImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		calendarImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		calendarImpl.setCalendarId(
			this.<Long>getColumnOriginalValue("calendarId"));
		calendarImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		calendarImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		calendarImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		calendarImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		calendarImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		calendarImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		calendarImpl.setCalendarResourceId(
			this.<Long>getColumnOriginalValue("calendarResourceId"));
		calendarImpl.setName(this.<String>getColumnOriginalValue("name"));
		calendarImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		calendarImpl.setTimeZoneId(
			this.<String>getColumnOriginalValue("timeZoneId"));
		calendarImpl.setColor(this.<Integer>getColumnOriginalValue("color"));
		calendarImpl.setDefaultCalendar(
			this.<Boolean>getColumnOriginalValue("defaultCalendar"));
		calendarImpl.setEnableComments(
			this.<Boolean>getColumnOriginalValue("enableComments"));
		calendarImpl.setEnableRatings(
			this.<Boolean>getColumnOriginalValue("enableRatings"));
		calendarImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return calendarImpl;
	}

	@Override
	public int compareTo(Calendar calendar) {
		int value = 0;

		value = getName().compareTo(calendar.getName());

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

		if (!(object instanceof Calendar)) {
			return false;
		}

		Calendar calendar = (Calendar)object;

		long primaryKey = calendar.getPrimaryKey();

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
	public CacheModel<Calendar> toCacheModel() {
		CalendarCacheModel calendarCacheModel = new CalendarCacheModel();

		calendarCacheModel.mvccVersion = getMvccVersion();

		calendarCacheModel.ctCollectionId = getCtCollectionId();

		calendarCacheModel.uuid = getUuid();

		String uuid = calendarCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			calendarCacheModel.uuid = null;
		}

		calendarCacheModel.calendarId = getCalendarId();

		calendarCacheModel.groupId = getGroupId();

		calendarCacheModel.companyId = getCompanyId();

		calendarCacheModel.userId = getUserId();

		calendarCacheModel.userName = getUserName();

		String userName = calendarCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			calendarCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			calendarCacheModel.createDate = createDate.getTime();
		}
		else {
			calendarCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			calendarCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			calendarCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		calendarCacheModel.calendarResourceId = getCalendarResourceId();

		calendarCacheModel.name = getName();

		String name = calendarCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			calendarCacheModel.name = null;
		}

		calendarCacheModel.description = getDescription();

		String description = calendarCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			calendarCacheModel.description = null;
		}

		calendarCacheModel.timeZoneId = getTimeZoneId();

		String timeZoneId = calendarCacheModel.timeZoneId;

		if ((timeZoneId != null) && (timeZoneId.length() == 0)) {
			calendarCacheModel.timeZoneId = null;
		}

		calendarCacheModel.color = getColor();

		calendarCacheModel.defaultCalendar = isDefaultCalendar();

		calendarCacheModel.enableComments = isEnableComments();

		calendarCacheModel.enableRatings = isEnableRatings();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			calendarCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			calendarCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return calendarCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Calendar, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Calendar, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Calendar, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Calendar)this);

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

		private static final Function<InvocationHandler, Calendar>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Calendar.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _calendarId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _calendarResourceId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _timeZoneId;
	private int _color;
	private boolean _defaultCalendar;
	private boolean _enableComments;
	private boolean _enableRatings;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Calendar, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Calendar)this);
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
		_columnOriginalValues.put("calendarId", _calendarId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("calendarResourceId", _calendarResourceId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("timeZoneId", _timeZoneId);
		_columnOriginalValues.put("color", _color);
		_columnOriginalValues.put("defaultCalendar", _defaultCalendar);
		_columnOriginalValues.put("enableComments", _enableComments);
		_columnOriginalValues.put("enableRatings", _enableRatings);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
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

		columnBitmasks.put("calendarId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("calendarResourceId", 1024L);

		columnBitmasks.put("name", 2048L);

		columnBitmasks.put("description", 4096L);

		columnBitmasks.put("timeZoneId", 8192L);

		columnBitmasks.put("color", 16384L);

		columnBitmasks.put("defaultCalendar", 32768L);

		columnBitmasks.put("enableComments", 65536L);

		columnBitmasks.put("enableRatings", 131072L);

		columnBitmasks.put("lastPublishDate", 262144L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Calendar _escapedModel;

}