/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.knowledge.base.model.KBTemplateModel;
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
 * The base model implementation for the KBTemplate service. Represents a row in the &quot;KBTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>KBTemplateModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KBTemplateImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplateImpl
 * @generated
 */
@JSON(strict = true)
public class KBTemplateModelImpl
	extends BaseModelImpl<KBTemplate> implements KBTemplateModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kb template model instance should use the <code>KBTemplate</code> interface instead.
	 */
	public static final String TABLE_NAME = "KBTemplate";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"kbTemplateId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"title", Types.VARCHAR}, {"content", Types.CLOB},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kbTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.CLOB);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KBTemplate (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,kbTemplateId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title STRING null,content TEXT null,lastPublishDate DATE null,primary key (kbTemplateId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table KBTemplate";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kbTemplate.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KBTemplate.modifiedDate DESC";

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
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 8L;

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

	public KBTemplateModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kbTemplateId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKbTemplateId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kbTemplateId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KBTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return KBTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KBTemplate, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KBTemplate, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KBTemplate, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((KBTemplate)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KBTemplate, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KBTemplate, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KBTemplate)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KBTemplate, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KBTemplate, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<KBTemplate, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<KBTemplate, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<KBTemplate, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", KBTemplate::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", KBTemplate::getCtCollectionId);
			attributeGetterFunctions.put("uuid", KBTemplate::getUuid);
			attributeGetterFunctions.put(
				"kbTemplateId", KBTemplate::getKbTemplateId);
			attributeGetterFunctions.put("groupId", KBTemplate::getGroupId);
			attributeGetterFunctions.put("companyId", KBTemplate::getCompanyId);
			attributeGetterFunctions.put("userId", KBTemplate::getUserId);
			attributeGetterFunctions.put("userName", KBTemplate::getUserName);
			attributeGetterFunctions.put(
				"createDate", KBTemplate::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", KBTemplate::getModifiedDate);
			attributeGetterFunctions.put("title", KBTemplate::getTitle);
			attributeGetterFunctions.put("content", KBTemplate::getContent);
			attributeGetterFunctions.put(
				"lastPublishDate", KBTemplate::getLastPublishDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<KBTemplate, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<KBTemplate, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<KBTemplate, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<KBTemplate, Long>)KBTemplate::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<KBTemplate, Long>)KBTemplate::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"uuid", (BiConsumer<KBTemplate, String>)KBTemplate::setUuid);
			attributeSetterBiConsumers.put(
				"kbTemplateId",
				(BiConsumer<KBTemplate, Long>)KBTemplate::setKbTemplateId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<KBTemplate, Long>)KBTemplate::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<KBTemplate, Long>)KBTemplate::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<KBTemplate, Long>)KBTemplate::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<KBTemplate, String>)KBTemplate::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<KBTemplate, Date>)KBTemplate::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<KBTemplate, Date>)KBTemplate::setModifiedDate);
			attributeSetterBiConsumers.put(
				"title", (BiConsumer<KBTemplate, String>)KBTemplate::setTitle);
			attributeSetterBiConsumers.put(
				"content",
				(BiConsumer<KBTemplate, String>)KBTemplate::setContent);
			attributeSetterBiConsumers.put(
				"lastPublishDate",
				(BiConsumer<KBTemplate, Date>)KBTemplate::setLastPublishDate);

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
	public long getKbTemplateId() {
		return _kbTemplateId;
	}

	@Override
	public void setKbTemplateId(long kbTemplateId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kbTemplateId = kbTemplateId;
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
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
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
			PortalUtil.getClassNameId(KBTemplate.class.getName()));
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
			getCompanyId(), KBTemplate.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KBTemplate toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KBTemplate>
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
		KBTemplateImpl kbTemplateImpl = new KBTemplateImpl();

		kbTemplateImpl.setMvccVersion(getMvccVersion());
		kbTemplateImpl.setCtCollectionId(getCtCollectionId());
		kbTemplateImpl.setUuid(getUuid());
		kbTemplateImpl.setKbTemplateId(getKbTemplateId());
		kbTemplateImpl.setGroupId(getGroupId());
		kbTemplateImpl.setCompanyId(getCompanyId());
		kbTemplateImpl.setUserId(getUserId());
		kbTemplateImpl.setUserName(getUserName());
		kbTemplateImpl.setCreateDate(getCreateDate());
		kbTemplateImpl.setModifiedDate(getModifiedDate());
		kbTemplateImpl.setTitle(getTitle());
		kbTemplateImpl.setContent(getContent());
		kbTemplateImpl.setLastPublishDate(getLastPublishDate());

		kbTemplateImpl.resetOriginalValues();

		return kbTemplateImpl;
	}

	@Override
	public KBTemplate cloneWithOriginalValues() {
		KBTemplateImpl kbTemplateImpl = new KBTemplateImpl();

		kbTemplateImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		kbTemplateImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		kbTemplateImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		kbTemplateImpl.setKbTemplateId(
			this.<Long>getColumnOriginalValue("kbTemplateId"));
		kbTemplateImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		kbTemplateImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		kbTemplateImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		kbTemplateImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		kbTemplateImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		kbTemplateImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		kbTemplateImpl.setTitle(this.<String>getColumnOriginalValue("title"));
		kbTemplateImpl.setContent(
			this.<String>getColumnOriginalValue("content"));
		kbTemplateImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return kbTemplateImpl;
	}

	@Override
	public int compareTo(KBTemplate kbTemplate) {
		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(), kbTemplate.getModifiedDate());

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

		if (!(object instanceof KBTemplate)) {
			return false;
		}

		KBTemplate kbTemplate = (KBTemplate)object;

		long primaryKey = kbTemplate.getPrimaryKey();

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
	public CacheModel<KBTemplate> toCacheModel() {
		KBTemplateCacheModel kbTemplateCacheModel = new KBTemplateCacheModel();

		kbTemplateCacheModel.mvccVersion = getMvccVersion();

		kbTemplateCacheModel.ctCollectionId = getCtCollectionId();

		kbTemplateCacheModel.uuid = getUuid();

		String uuid = kbTemplateCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			kbTemplateCacheModel.uuid = null;
		}

		kbTemplateCacheModel.kbTemplateId = getKbTemplateId();

		kbTemplateCacheModel.groupId = getGroupId();

		kbTemplateCacheModel.companyId = getCompanyId();

		kbTemplateCacheModel.userId = getUserId();

		kbTemplateCacheModel.userName = getUserName();

		String userName = kbTemplateCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kbTemplateCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kbTemplateCacheModel.createDate = createDate.getTime();
		}
		else {
			kbTemplateCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kbTemplateCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kbTemplateCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kbTemplateCacheModel.title = getTitle();

		String title = kbTemplateCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			kbTemplateCacheModel.title = null;
		}

		kbTemplateCacheModel.content = getContent();

		String content = kbTemplateCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			kbTemplateCacheModel.content = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			kbTemplateCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			kbTemplateCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return kbTemplateCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KBTemplate, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KBTemplate, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KBTemplate, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((KBTemplate)this);

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

		private static final Function<InvocationHandler, KBTemplate>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					KBTemplate.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _kbTemplateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _title;
	private String _content;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<KBTemplate, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((KBTemplate)this);
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
		_columnOriginalValues.put("kbTemplateId", _kbTemplateId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
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

		columnBitmasks.put("kbTemplateId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("title", 1024L);

		columnBitmasks.put("content", 2048L);

		columnBitmasks.put("lastPublishDate", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private KBTemplate _escapedModel;

}