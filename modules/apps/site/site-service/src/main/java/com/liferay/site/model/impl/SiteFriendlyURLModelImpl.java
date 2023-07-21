/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.site.model.SiteFriendlyURL;
import com.liferay.site.model.SiteFriendlyURLModel;

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
 * The base model implementation for the SiteFriendlyURL service. Represents a row in the &quot;SiteFriendlyURL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SiteFriendlyURLModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SiteFriendlyURLImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteFriendlyURLImpl
 * @generated
 */
public class SiteFriendlyURLModelImpl
	extends BaseModelImpl<SiteFriendlyURL> implements SiteFriendlyURLModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a site friendly url model instance should use the <code>SiteFriendlyURL</code> interface instead.
	 */
	public static final String TABLE_NAME = "SiteFriendlyURL";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"siteFriendlyURLId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"friendlyURL", Types.VARCHAR},
		{"languageId", Types.VARCHAR}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("siteFriendlyURLId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("friendlyURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SiteFriendlyURL (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,siteFriendlyURLId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,friendlyURL VARCHAR(75) null,languageId VARCHAR(75) null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table SiteFriendlyURL";

	public static final String ORDER_BY_JPQL =
		" ORDER BY siteFriendlyURL.siteFriendlyURLId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SiteFriendlyURL.siteFriendlyURLId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FRIENDLYURL_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LANGUAGEID_COLUMN_BITMASK = 8L;

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
	public static final long SITEFRIENDLYURLID_COLUMN_BITMASK = 32L;

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

	public SiteFriendlyURLModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _siteFriendlyURLId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSiteFriendlyURLId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _siteFriendlyURLId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SiteFriendlyURL.class;
	}

	@Override
	public String getModelClassName() {
		return SiteFriendlyURL.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SiteFriendlyURL, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SiteFriendlyURL, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SiteFriendlyURL, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SiteFriendlyURL)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SiteFriendlyURL, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SiteFriendlyURL, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SiteFriendlyURL)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SiteFriendlyURL, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SiteFriendlyURL, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<SiteFriendlyURL, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<SiteFriendlyURL, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<SiteFriendlyURL, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", SiteFriendlyURL::getMvccVersion);
			attributeGetterFunctions.put("uuid", SiteFriendlyURL::getUuid);
			attributeGetterFunctions.put(
				"siteFriendlyURLId", SiteFriendlyURL::getSiteFriendlyURLId);
			attributeGetterFunctions.put(
				"groupId", SiteFriendlyURL::getGroupId);
			attributeGetterFunctions.put(
				"companyId", SiteFriendlyURL::getCompanyId);
			attributeGetterFunctions.put("userId", SiteFriendlyURL::getUserId);
			attributeGetterFunctions.put(
				"userName", SiteFriendlyURL::getUserName);
			attributeGetterFunctions.put(
				"createDate", SiteFriendlyURL::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", SiteFriendlyURL::getModifiedDate);
			attributeGetterFunctions.put(
				"friendlyURL", SiteFriendlyURL::getFriendlyURL);
			attributeGetterFunctions.put(
				"languageId", SiteFriendlyURL::getLanguageId);
			attributeGetterFunctions.put(
				"lastPublishDate", SiteFriendlyURL::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<SiteFriendlyURL, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<SiteFriendlyURL, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<SiteFriendlyURL, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<SiteFriendlyURL, Long>)
					SiteFriendlyURL::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<SiteFriendlyURL, String>)SiteFriendlyURL::setUuid);
			attributeSetterBiConsumers.put(
				"siteFriendlyURLId",
				(BiConsumer<SiteFriendlyURL, Long>)
					SiteFriendlyURL::setSiteFriendlyURLId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<SiteFriendlyURL, Long>)SiteFriendlyURL::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<SiteFriendlyURL, Long>)
					SiteFriendlyURL::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<SiteFriendlyURL, Long>)SiteFriendlyURL::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<SiteFriendlyURL, String>)
					SiteFriendlyURL::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<SiteFriendlyURL, Date>)
					SiteFriendlyURL::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<SiteFriendlyURL, Date>)
					SiteFriendlyURL::setModifiedDate);
			attributeSetterBiConsumers.put(
				"friendlyURL",
				(BiConsumer<SiteFriendlyURL, String>)
					SiteFriendlyURL::setFriendlyURL);
			attributeSetterBiConsumers.put(
				"languageId",
				(BiConsumer<SiteFriendlyURL, String>)
					SiteFriendlyURL::setLanguageId);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<SiteFriendlyURL, Date>)
					SiteFriendlyURL::setLastPublishDate);

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

	@Override
	public long getSiteFriendlyURLId() {
		return _siteFriendlyURLId;
	}

	@Override
	public void setSiteFriendlyURLId(long siteFriendlyURLId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_siteFriendlyURLId = siteFriendlyURLId;
	}

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
	public String getFriendlyURL() {
		if (_friendlyURL == null) {
			return "";
		}
		else {
			return _friendlyURL;
		}
	}

	@Override
	public void setFriendlyURL(String friendlyURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friendlyURL = friendlyURL;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalFriendlyURL() {
		return getColumnOriginalValue("friendlyURL");
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_languageId = languageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLanguageId() {
		return getColumnOriginalValue("languageId");
	}

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
			PortalUtil.getClassNameId(SiteFriendlyURL.class.getName()));
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
			getCompanyId(), SiteFriendlyURL.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SiteFriendlyURL toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SiteFriendlyURL>
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
		SiteFriendlyURLImpl siteFriendlyURLImpl = new SiteFriendlyURLImpl();

		siteFriendlyURLImpl.setMvccVersion(getMvccVersion());
		siteFriendlyURLImpl.setUuid(getUuid());
		siteFriendlyURLImpl.setSiteFriendlyURLId(getSiteFriendlyURLId());
		siteFriendlyURLImpl.setGroupId(getGroupId());
		siteFriendlyURLImpl.setCompanyId(getCompanyId());
		siteFriendlyURLImpl.setUserId(getUserId());
		siteFriendlyURLImpl.setUserName(getUserName());
		siteFriendlyURLImpl.setCreateDate(getCreateDate());
		siteFriendlyURLImpl.setModifiedDate(getModifiedDate());
		siteFriendlyURLImpl.setFriendlyURL(getFriendlyURL());
		siteFriendlyURLImpl.setLanguageId(getLanguageId());
		siteFriendlyURLImpl.setLastPublishDate(getLastPublishDate());

		siteFriendlyURLImpl.resetOriginalValues();

		return siteFriendlyURLImpl;
	}

	@Override
	public SiteFriendlyURL cloneWithOriginalValues() {
		SiteFriendlyURLImpl siteFriendlyURLImpl = new SiteFriendlyURLImpl();

		siteFriendlyURLImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		siteFriendlyURLImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		siteFriendlyURLImpl.setSiteFriendlyURLId(
			this.<Long>getColumnOriginalValue("siteFriendlyURLId"));
		siteFriendlyURLImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		siteFriendlyURLImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		siteFriendlyURLImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		siteFriendlyURLImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		siteFriendlyURLImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		siteFriendlyURLImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		siteFriendlyURLImpl.setFriendlyURL(
			this.<String>getColumnOriginalValue("friendlyURL"));
		siteFriendlyURLImpl.setLanguageId(
			this.<String>getColumnOriginalValue("languageId"));
		siteFriendlyURLImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return siteFriendlyURLImpl;
	}

	@Override
	public int compareTo(SiteFriendlyURL siteFriendlyURL) {
		long primaryKey = siteFriendlyURL.getPrimaryKey();

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

		if (!(object instanceof SiteFriendlyURL)) {
			return false;
		}

		SiteFriendlyURL siteFriendlyURL = (SiteFriendlyURL)object;

		long primaryKey = siteFriendlyURL.getPrimaryKey();

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
	public CacheModel<SiteFriendlyURL> toCacheModel() {
		SiteFriendlyURLCacheModel siteFriendlyURLCacheModel =
			new SiteFriendlyURLCacheModel();

		siteFriendlyURLCacheModel.mvccVersion = getMvccVersion();

		siteFriendlyURLCacheModel.uuid = getUuid();

		String uuid = siteFriendlyURLCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			siteFriendlyURLCacheModel.uuid = null;
		}

		siteFriendlyURLCacheModel.siteFriendlyURLId = getSiteFriendlyURLId();

		siteFriendlyURLCacheModel.groupId = getGroupId();

		siteFriendlyURLCacheModel.companyId = getCompanyId();

		siteFriendlyURLCacheModel.userId = getUserId();

		siteFriendlyURLCacheModel.userName = getUserName();

		String userName = siteFriendlyURLCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			siteFriendlyURLCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			siteFriendlyURLCacheModel.createDate = createDate.getTime();
		}
		else {
			siteFriendlyURLCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			siteFriendlyURLCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			siteFriendlyURLCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		siteFriendlyURLCacheModel.friendlyURL = getFriendlyURL();

		String friendlyURL = siteFriendlyURLCacheModel.friendlyURL;

		if ((friendlyURL != null) && (friendlyURL.length() == 0)) {
			siteFriendlyURLCacheModel.friendlyURL = null;
		}

		siteFriendlyURLCacheModel.languageId = getLanguageId();

		String languageId = siteFriendlyURLCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			siteFriendlyURLCacheModel.languageId = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			siteFriendlyURLCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			siteFriendlyURLCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return siteFriendlyURLCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SiteFriendlyURL, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SiteFriendlyURL, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SiteFriendlyURL, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SiteFriendlyURL)this);

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

		private static final Function<InvocationHandler, SiteFriendlyURL>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					SiteFriendlyURL.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _siteFriendlyURLId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _friendlyURL;
	private String _languageId;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SiteFriendlyURL, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SiteFriendlyURL)this);
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
		_columnOriginalValues.put("siteFriendlyURLId", _siteFriendlyURLId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("friendlyURL", _friendlyURL);
		_columnOriginalValues.put("languageId", _languageId);
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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("siteFriendlyURLId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("friendlyURL", 512L);

		columnBitmasks.put("languageId", 1024L);

		columnBitmasks.put("lastPublishDate", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SiteFriendlyURL _escapedModel;

}