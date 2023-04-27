/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
 * The base model implementation for the Company service. Represents a row in the &quot;Company&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CompanyModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CompanyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CompanyImpl
 * @generated
 */
@JSON(strict = true)
public class CompanyModelImpl
	extends BaseModelImpl<Company> implements CompanyModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a company model instance should use the <code>Company</code> interface instead.
	 */
	public static final String TABLE_NAME = "Company";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"webId", Types.VARCHAR}, {"mx", Types.VARCHAR},
		{"homeURL", Types.VARCHAR}, {"logoId", Types.BIGINT},
		{"maxUsers", Types.INTEGER}, {"active_", Types.BOOLEAN},
		{"name", Types.VARCHAR}, {"legalName", Types.VARCHAR},
		{"legalId", Types.VARCHAR}, {"legalType", Types.VARCHAR},
		{"sicCode", Types.VARCHAR}, {"tickerSymbol", Types.VARCHAR},
		{"industry", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"size_", Types.VARCHAR}, {"indexNameCurrent", Types.VARCHAR},
		{"indexNameNext", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("webId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mx", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("homeURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("maxUsers", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("legalName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("legalId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("legalType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sicCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tickerSymbol", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("industry", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("size_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("indexNameCurrent", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("indexNameNext", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Company (mvccVersion LONG default 0 not null,companyId LONG not null primary key,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,webId VARCHAR(75) null,mx VARCHAR(200) null,homeURL STRING null,logoId LONG,maxUsers INTEGER,active_ BOOLEAN,name VARCHAR(75) null,legalName VARCHAR(75) null,legalId VARCHAR(75) null,legalType VARCHAR(75) null,sicCode VARCHAR(75) null,tickerSymbol VARCHAR(75) null,industry VARCHAR(75) null,type_ VARCHAR(75) null,size_ VARCHAR(75) null,indexNameCurrent VARCHAR(75) null,indexNameNext VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Company";

	public static final String ORDER_BY_JPQL =
		" ORDER BY company.companyId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY Company.companyId ASC";

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
	public static final long LOGOID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MX_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long WEBID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Company"));

	public CompanyModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _companyId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCompanyId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Company.class;
	}

	@Override
	public String getModelClassName() {
		return Company.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Company, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Company, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Company, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Company)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Company, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Company, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Company)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Company, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Company, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<Company, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<Company, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<Company, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", Company::getMvccVersion);
			attributeGetterFunctions.put("companyId", Company::getCompanyId);
			attributeGetterFunctions.put("userId", Company::getUserId);
			attributeGetterFunctions.put("userName", Company::getUserName);
			attributeGetterFunctions.put("createDate", Company::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", Company::getModifiedDate);
			attributeGetterFunctions.put("webId", Company::getWebId);
			attributeGetterFunctions.put("mx", Company::getMx);
			attributeGetterFunctions.put("homeURL", Company::getHomeURL);
			attributeGetterFunctions.put("logoId", Company::getLogoId);
			attributeGetterFunctions.put("maxUsers", Company::getMaxUsers);
			attributeGetterFunctions.put("active", Company::getActive);
			attributeGetterFunctions.put("name", Company::getName);
			attributeGetterFunctions.put("legalName", Company::getLegalName);
			attributeGetterFunctions.put("legalId", Company::getLegalId);
			attributeGetterFunctions.put("legalType", Company::getLegalType);
			attributeGetterFunctions.put("sicCode", Company::getSicCode);
			attributeGetterFunctions.put(
				"tickerSymbol", Company::getTickerSymbol);
			attributeGetterFunctions.put("industry", Company::getIndustry);
			attributeGetterFunctions.put("type", Company::getType);
			attributeGetterFunctions.put("size", Company::getSize);
			attributeGetterFunctions.put(
				"indexNameCurrent", Company::getIndexNameCurrent);
			attributeGetterFunctions.put(
				"indexNameNext", Company::getIndexNameNext);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<Company, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<Company, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<Company, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<Company, Long>)Company::setMvccVersion);
			attributeSetterBiConsumers.put(
				"companyId", (BiConsumer<Company, Long>)Company::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<Company, Long>)Company::setUserId);
			attributeSetterBiConsumers.put(
				"userName", (BiConsumer<Company, String>)Company::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<Company, Date>)Company::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<Company, Date>)Company::setModifiedDate);
			attributeSetterBiConsumers.put(
				"webId", (BiConsumer<Company, String>)Company::setWebId);
			attributeSetterBiConsumers.put(
				"mx", (BiConsumer<Company, String>)Company::setMx);
			attributeSetterBiConsumers.put(
				"homeURL", (BiConsumer<Company, String>)Company::setHomeURL);
			attributeSetterBiConsumers.put(
				"logoId", (BiConsumer<Company, Long>)Company::setLogoId);
			attributeSetterBiConsumers.put(
				"maxUsers", (BiConsumer<Company, Integer>)Company::setMaxUsers);
			attributeSetterBiConsumers.put(
				"active", (BiConsumer<Company, Boolean>)Company::setActive);
			attributeSetterBiConsumers.put(
				"name", (BiConsumer<Company, String>)Company::setName);
			attributeSetterBiConsumers.put(
				"legalName",
				(BiConsumer<Company, String>)Company::setLegalName);
			attributeSetterBiConsumers.put(
				"legalId", (BiConsumer<Company, String>)Company::setLegalId);
			attributeSetterBiConsumers.put(
				"legalType",
				(BiConsumer<Company, String>)Company::setLegalType);
			attributeSetterBiConsumers.put(
				"sicCode", (BiConsumer<Company, String>)Company::setSicCode);
			attributeSetterBiConsumers.put(
				"tickerSymbol",
				(BiConsumer<Company, String>)Company::setTickerSymbol);
			attributeSetterBiConsumers.put(
				"industry", (BiConsumer<Company, String>)Company::setIndustry);
			attributeSetterBiConsumers.put(
				"type", (BiConsumer<Company, String>)Company::setType);
			attributeSetterBiConsumers.put(
				"size", (BiConsumer<Company, String>)Company::setSize);
			attributeSetterBiConsumers.put(
				"indexNameCurrent",
				(BiConsumer<Company, String>)Company::setIndexNameCurrent);
			attributeSetterBiConsumers.put(
				"indexNameNext",
				(BiConsumer<Company, String>)Company::setIndexNameNext);

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
	public String getWebId() {
		if (_webId == null) {
			return "";
		}
		else {
			return _webId;
		}
	}

	@Override
	public void setWebId(String webId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_webId = webId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalWebId() {
		return getColumnOriginalValue("webId");
	}

	@JSON
	@Override
	public String getMx() {
		if (_mx == null) {
			return "";
		}
		else {
			return _mx;
		}
	}

	@Override
	public void setMx(String mx) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mx = mx;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalMx() {
		return getColumnOriginalValue("mx");
	}

	@JSON
	@Override
	public String getHomeURL() {
		if (_homeURL == null) {
			return "";
		}
		else {
			return _homeURL;
		}
	}

	@Override
	public void setHomeURL(String homeURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_homeURL = homeURL;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logoId = logoId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLogoId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("logoId"));
	}

	@JSON
	@Override
	public int getMaxUsers() {
		return _maxUsers;
	}

	@Override
	public void setMaxUsers(int maxUsers) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_maxUsers = maxUsers;
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

	@JSON
	@Override
	public String getLegalName() {
		if (_legalName == null) {
			return "";
		}
		else {
			return _legalName;
		}
	}

	@Override
	public void setLegalName(String legalName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_legalName = legalName;
	}

	@JSON
	@Override
	public String getLegalId() {
		if (_legalId == null) {
			return "";
		}
		else {
			return _legalId;
		}
	}

	@Override
	public void setLegalId(String legalId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_legalId = legalId;
	}

	@JSON
	@Override
	public String getLegalType() {
		if (_legalType == null) {
			return "";
		}
		else {
			return _legalType;
		}
	}

	@Override
	public void setLegalType(String legalType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_legalType = legalType;
	}

	@JSON
	@Override
	public String getSicCode() {
		if (_sicCode == null) {
			return "";
		}
		else {
			return _sicCode;
		}
	}

	@Override
	public void setSicCode(String sicCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sicCode = sicCode;
	}

	@JSON
	@Override
	public String getTickerSymbol() {
		if (_tickerSymbol == null) {
			return "";
		}
		else {
			return _tickerSymbol;
		}
	}

	@Override
	public void setTickerSymbol(String tickerSymbol) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tickerSymbol = tickerSymbol;
	}

	@JSON
	@Override
	public String getIndustry() {
		if (_industry == null) {
			return "";
		}
		else {
			return _industry;
		}
	}

	@Override
	public void setIndustry(String industry) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_industry = industry;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@JSON
	@Override
	public String getSize() {
		if (_size == null) {
			return "";
		}
		else {
			return _size;
		}
	}

	@Override
	public void setSize(String size) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_size = size;
	}

	@JSON
	@Override
	public String getIndexNameCurrent() {
		if (_indexNameCurrent == null) {
			return "";
		}
		else {
			return _indexNameCurrent;
		}
	}

	@Override
	public void setIndexNameCurrent(String indexNameCurrent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_indexNameCurrent = indexNameCurrent;
	}

	@JSON
	@Override
	public String getIndexNameNext() {
		if (_indexNameNext == null) {
			return "";
		}
		else {
			return _indexNameNext;
		}
	}

	@Override
	public void setIndexNameNext(String indexNameNext) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_indexNameNext = indexNameNext;
	}

	public CompanyImpl.CompanySecurityBag getCompanySecurityBag() {
		return null;
	}

	public void setCompanySecurityBag(
		CompanyImpl.CompanySecurityBag companySecurityBag) {
	}

	public String getVirtualHostname() {
		return null;
	}

	public void setVirtualHostname(String virtualHostname) {
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
			getCompanyId(), Company.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Company toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Company>
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
		CompanyImpl companyImpl = new CompanyImpl();

		companyImpl.setMvccVersion(getMvccVersion());
		companyImpl.setCompanyId(getCompanyId());
		companyImpl.setUserId(getUserId());
		companyImpl.setUserName(getUserName());
		companyImpl.setCreateDate(getCreateDate());
		companyImpl.setModifiedDate(getModifiedDate());
		companyImpl.setWebId(getWebId());
		companyImpl.setMx(getMx());
		companyImpl.setHomeURL(getHomeURL());
		companyImpl.setLogoId(getLogoId());
		companyImpl.setMaxUsers(getMaxUsers());
		companyImpl.setActive(isActive());
		companyImpl.setName(getName());
		companyImpl.setLegalName(getLegalName());
		companyImpl.setLegalId(getLegalId());
		companyImpl.setLegalType(getLegalType());
		companyImpl.setSicCode(getSicCode());
		companyImpl.setTickerSymbol(getTickerSymbol());
		companyImpl.setIndustry(getIndustry());
		companyImpl.setType(getType());
		companyImpl.setSize(getSize());
		companyImpl.setIndexNameCurrent(getIndexNameCurrent());
		companyImpl.setIndexNameNext(getIndexNameNext());

		companyImpl.resetOriginalValues();

		return companyImpl;
	}

	@Override
	public Company cloneWithOriginalValues() {
		CompanyImpl companyImpl = new CompanyImpl();

		companyImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		companyImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		companyImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		companyImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		companyImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		companyImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		companyImpl.setWebId(this.<String>getColumnOriginalValue("webId"));
		companyImpl.setMx(this.<String>getColumnOriginalValue("mx"));
		companyImpl.setHomeURL(this.<String>getColumnOriginalValue("homeURL"));
		companyImpl.setLogoId(this.<Long>getColumnOriginalValue("logoId"));
		companyImpl.setMaxUsers(
			this.<Integer>getColumnOriginalValue("maxUsers"));
		companyImpl.setActive(this.<Boolean>getColumnOriginalValue("active_"));
		companyImpl.setName(this.<String>getColumnOriginalValue("name"));
		companyImpl.setLegalName(
			this.<String>getColumnOriginalValue("legalName"));
		companyImpl.setLegalId(this.<String>getColumnOriginalValue("legalId"));
		companyImpl.setLegalType(
			this.<String>getColumnOriginalValue("legalType"));
		companyImpl.setSicCode(this.<String>getColumnOriginalValue("sicCode"));
		companyImpl.setTickerSymbol(
			this.<String>getColumnOriginalValue("tickerSymbol"));
		companyImpl.setIndustry(
			this.<String>getColumnOriginalValue("industry"));
		companyImpl.setType(this.<String>getColumnOriginalValue("type_"));
		companyImpl.setSize(this.<String>getColumnOriginalValue("size_"));
		companyImpl.setIndexNameCurrent(
			this.<String>getColumnOriginalValue("indexNameCurrent"));
		companyImpl.setIndexNameNext(
			this.<String>getColumnOriginalValue("indexNameNext"));

		return companyImpl;
	}

	@Override
	public int compareTo(Company company) {
		long primaryKey = company.getPrimaryKey();

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

		if (!(object instanceof Company)) {
			return false;
		}

		Company company = (Company)object;

		long primaryKey = company.getPrimaryKey();

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

		setCompanySecurityBag(null);

		setVirtualHostname(null);

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Company> toCacheModel() {
		CompanyCacheModel companyCacheModel = new CompanyCacheModel();

		companyCacheModel.mvccVersion = getMvccVersion();

		companyCacheModel.companyId = getCompanyId();

		companyCacheModel.userId = getUserId();

		companyCacheModel.userName = getUserName();

		String userName = companyCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			companyCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			companyCacheModel.createDate = createDate.getTime();
		}
		else {
			companyCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			companyCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			companyCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		companyCacheModel.webId = getWebId();

		String webId = companyCacheModel.webId;

		if ((webId != null) && (webId.length() == 0)) {
			companyCacheModel.webId = null;
		}

		companyCacheModel.mx = getMx();

		String mx = companyCacheModel.mx;

		if ((mx != null) && (mx.length() == 0)) {
			companyCacheModel.mx = null;
		}

		companyCacheModel.homeURL = getHomeURL();

		String homeURL = companyCacheModel.homeURL;

		if ((homeURL != null) && (homeURL.length() == 0)) {
			companyCacheModel.homeURL = null;
		}

		companyCacheModel.logoId = getLogoId();

		companyCacheModel.maxUsers = getMaxUsers();

		companyCacheModel.active = isActive();

		companyCacheModel.name = getName();

		String name = companyCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			companyCacheModel.name = null;
		}

		companyCacheModel.legalName = getLegalName();

		String legalName = companyCacheModel.legalName;

		if ((legalName != null) && (legalName.length() == 0)) {
			companyCacheModel.legalName = null;
		}

		companyCacheModel.legalId = getLegalId();

		String legalId = companyCacheModel.legalId;

		if ((legalId != null) && (legalId.length() == 0)) {
			companyCacheModel.legalId = null;
		}

		companyCacheModel.legalType = getLegalType();

		String legalType = companyCacheModel.legalType;

		if ((legalType != null) && (legalType.length() == 0)) {
			companyCacheModel.legalType = null;
		}

		companyCacheModel.sicCode = getSicCode();

		String sicCode = companyCacheModel.sicCode;

		if ((sicCode != null) && (sicCode.length() == 0)) {
			companyCacheModel.sicCode = null;
		}

		companyCacheModel.tickerSymbol = getTickerSymbol();

		String tickerSymbol = companyCacheModel.tickerSymbol;

		if ((tickerSymbol != null) && (tickerSymbol.length() == 0)) {
			companyCacheModel.tickerSymbol = null;
		}

		companyCacheModel.industry = getIndustry();

		String industry = companyCacheModel.industry;

		if ((industry != null) && (industry.length() == 0)) {
			companyCacheModel.industry = null;
		}

		companyCacheModel.type = getType();

		String type = companyCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			companyCacheModel.type = null;
		}

		companyCacheModel.size = getSize();

		String size = companyCacheModel.size;

		if ((size != null) && (size.length() == 0)) {
			companyCacheModel.size = null;
		}

		companyCacheModel.indexNameCurrent = getIndexNameCurrent();

		String indexNameCurrent = companyCacheModel.indexNameCurrent;

		if ((indexNameCurrent != null) && (indexNameCurrent.length() == 0)) {
			companyCacheModel.indexNameCurrent = null;
		}

		companyCacheModel.indexNameNext = getIndexNameNext();

		String indexNameNext = companyCacheModel.indexNameNext;

		if ((indexNameNext != null) && (indexNameNext.length() == 0)) {
			companyCacheModel.indexNameNext = null;
		}

		setCompanySecurityBag(null);

		companyCacheModel._companySecurityBag = getCompanySecurityBag();

		setVirtualHostname(null);

		companyCacheModel._virtualHostname = getVirtualHostname();

		return companyCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Company, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Company, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Company, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Company)this);

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

		private static final Function<InvocationHandler, Company>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Company.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _webId;
	private String _mx;
	private String _homeURL;
	private long _logoId;
	private int _maxUsers;
	private boolean _active;
	private String _name;
	private String _legalName;
	private String _legalId;
	private String _legalType;
	private String _sicCode;
	private String _tickerSymbol;
	private String _industry;
	private String _type;
	private String _size;
	private String _indexNameCurrent;
	private String _indexNameNext;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Company, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Company)this);
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
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("webId", _webId);
		_columnOriginalValues.put("mx", _mx);
		_columnOriginalValues.put("homeURL", _homeURL);
		_columnOriginalValues.put("logoId", _logoId);
		_columnOriginalValues.put("maxUsers", _maxUsers);
		_columnOriginalValues.put("active_", _active);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("legalName", _legalName);
		_columnOriginalValues.put("legalId", _legalId);
		_columnOriginalValues.put("legalType", _legalType);
		_columnOriginalValues.put("sicCode", _sicCode);
		_columnOriginalValues.put("tickerSymbol", _tickerSymbol);
		_columnOriginalValues.put("industry", _industry);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("size_", _size);
		_columnOriginalValues.put("indexNameCurrent", _indexNameCurrent);
		_columnOriginalValues.put("indexNameNext", _indexNameNext);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("active_", "active");
		attributeNames.put("type_", "type");
		attributeNames.put("size_", "size");

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

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("webId", 64L);

		columnBitmasks.put("mx", 128L);

		columnBitmasks.put("homeURL", 256L);

		columnBitmasks.put("logoId", 512L);

		columnBitmasks.put("maxUsers", 1024L);

		columnBitmasks.put("active_", 2048L);

		columnBitmasks.put("name", 4096L);

		columnBitmasks.put("legalName", 8192L);

		columnBitmasks.put("legalId", 16384L);

		columnBitmasks.put("legalType", 32768L);

		columnBitmasks.put("sicCode", 65536L);

		columnBitmasks.put("tickerSymbol", 131072L);

		columnBitmasks.put("industry", 262144L);

		columnBitmasks.put("type_", 524288L);

		columnBitmasks.put("size_", 1048576L);

		columnBitmasks.put("indexNameCurrent", 2097152L);

		columnBitmasks.put("indexNameNext", 4194304L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Company _escapedModel;

}