/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model.impl;

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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoActionModel;

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
 * The base model implementation for the KaleoAction service. Represents a row in the &quot;KaleoAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>KaleoActionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoActionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoActionImpl
 * @generated
 */
public class KaleoActionModelImpl
	extends BaseModelImpl<KaleoAction> implements KaleoActionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo action model instance should use the <code>KaleoAction</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoAction";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"kaleoActionId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"kaleoClassName", Types.VARCHAR},
		{"kaleoClassPK", Types.BIGINT}, {"kaleoDefinitionId", Types.BIGINT},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoNodeName", Types.VARCHAR}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"executionType", Types.VARCHAR},
		{"script", Types.CLOB}, {"scriptLanguage", Types.VARCHAR},
		{"scriptRequiredContexts", Types.VARCHAR}, {"priority", Types.INTEGER},
		{"type_", Types.VARCHAR}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoActionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("executionType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("script", Types.CLOB);
		TABLE_COLUMNS_MAP.put("scriptLanguage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("scriptRequiredContexts", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoAction (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,kaleoActionId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoClassName VARCHAR(200) null,kaleoClassPK LONG,kaleoDefinitionId LONG,kaleoDefinitionVersionId LONG,kaleoNodeName VARCHAR(200) null,name VARCHAR(200) null,description STRING null,executionType VARCHAR(20) null,script TEXT null,scriptLanguage VARCHAR(75) null,scriptRequiredContexts STRING null,priority INTEGER,type_ VARCHAR(75) null,status INTEGER,primary key (kaleoActionId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table KaleoAction";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoAction.priority ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoAction.priority ASC";

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
	public static final long EXECUTIONTYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEOCLASSNAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEOCLASSPK_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PRIORITY_COLUMN_BITMASK = 32L;

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

	public KaleoActionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoActionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoActionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoActionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoAction.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoAction, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoAction)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoAction, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoAction, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoAction)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoAction, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoAction, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<KaleoAction, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<KaleoAction, Object>>
				attributeGetterFunctions =
					new LinkedHashMap<String, Function<KaleoAction, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", KaleoAction::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", KaleoAction::getCtCollectionId);
			attributeGetterFunctions.put(
				"kaleoActionId", KaleoAction::getKaleoActionId);
			attributeGetterFunctions.put("groupId", KaleoAction::getGroupId);
			attributeGetterFunctions.put(
				"companyId", KaleoAction::getCompanyId);
			attributeGetterFunctions.put("userId", KaleoAction::getUserId);
			attributeGetterFunctions.put("userName", KaleoAction::getUserName);
			attributeGetterFunctions.put(
				"createDate", KaleoAction::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", KaleoAction::getModifiedDate);
			attributeGetterFunctions.put(
				"kaleoClassName", KaleoAction::getKaleoClassName);
			attributeGetterFunctions.put(
				"kaleoClassPK", KaleoAction::getKaleoClassPK);
			attributeGetterFunctions.put(
				"kaleoDefinitionId", KaleoAction::getKaleoDefinitionId);
			attributeGetterFunctions.put(
				"kaleoDefinitionVersionId",
				KaleoAction::getKaleoDefinitionVersionId);
			attributeGetterFunctions.put(
				"kaleoNodeName", KaleoAction::getKaleoNodeName);
			attributeGetterFunctions.put("name", KaleoAction::getName);
			attributeGetterFunctions.put(
				"description", KaleoAction::getDescription);
			attributeGetterFunctions.put(
				"executionType", KaleoAction::getExecutionType);
			attributeGetterFunctions.put("script", KaleoAction::getScript);
			attributeGetterFunctions.put(
				"scriptLanguage", KaleoAction::getScriptLanguage);
			attributeGetterFunctions.put(
				"scriptRequiredContexts",
				KaleoAction::getScriptRequiredContexts);
			attributeGetterFunctions.put("priority", KaleoAction::getPriority);
			attributeGetterFunctions.put("type", KaleoAction::getType);
			attributeGetterFunctions.put("status", KaleoAction::getStatus);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<KaleoAction, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<KaleoAction, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<KaleoAction, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"kaleoActionId",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setKaleoActionId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<KaleoAction, String>)KaleoAction::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<KaleoAction, Date>)KaleoAction::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<KaleoAction, Date>)KaleoAction::setModifiedDate);
			attributeSetterBiConsumers.put(
				"kaleoClassName",
				(BiConsumer<KaleoAction, String>)
					KaleoAction::setKaleoClassName);
			attributeSetterBiConsumers.put(
				"kaleoClassPK",
				(BiConsumer<KaleoAction, Long>)KaleoAction::setKaleoClassPK);
			attributeSetterBiConsumers.put(
				"kaleoDefinitionId",
				(BiConsumer<KaleoAction, Long>)
					KaleoAction::setKaleoDefinitionId);
			attributeSetterBiConsumers.put(
				"kaleoDefinitionVersionId",
				(BiConsumer<KaleoAction, Long>)
					KaleoAction::setKaleoDefinitionVersionId);
			attributeSetterBiConsumers.put(
				"kaleoNodeName",
				(BiConsumer<KaleoAction, String>)KaleoAction::setKaleoNodeName);
			attributeSetterBiConsumers.put(
				"name", (BiConsumer<KaleoAction, String>)KaleoAction::setName);
			attributeSetterBiConsumers.put(
				"description",
				(BiConsumer<KaleoAction, String>)KaleoAction::setDescription);
			attributeSetterBiConsumers.put(
				"executionType",
				(BiConsumer<KaleoAction, String>)KaleoAction::setExecutionType);
			attributeSetterBiConsumers.put(
				"script",
				(BiConsumer<KaleoAction, String>)KaleoAction::setScript);
			attributeSetterBiConsumers.put(
				"scriptLanguage",
				(BiConsumer<KaleoAction, String>)
					KaleoAction::setScriptLanguage);
			attributeSetterBiConsumers.put(
				"scriptRequiredContexts",
				(BiConsumer<KaleoAction, String>)
					KaleoAction::setScriptRequiredContexts);
			attributeSetterBiConsumers.put(
				"priority",
				(BiConsumer<KaleoAction, Integer>)KaleoAction::setPriority);
			attributeSetterBiConsumers.put(
				"type", (BiConsumer<KaleoAction, String>)KaleoAction::setType);
			attributeSetterBiConsumers.put(
				"status",
				(BiConsumer<KaleoAction, Integer>)KaleoAction::setStatus);

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

	@Override
	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	@Override
	public void setKaleoActionId(long kaleoActionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoActionId = kaleoActionId;
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
	public String getKaleoClassName() {
		if (_kaleoClassName == null) {
			return "";
		}
		else {
			return _kaleoClassName;
		}
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoClassName = kaleoClassName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKaleoClassName() {
		return getColumnOriginalValue("kaleoClassName");
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoClassPK = kaleoClassPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalKaleoClassPK() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("kaleoClassPK"));
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoDefinitionId = kaleoDefinitionId;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalKaleoDefinitionVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("kaleoDefinitionVersionId"));
	}

	@Override
	public String getKaleoNodeName() {
		if (_kaleoNodeName == null) {
			return "";
		}
		else {
			return _kaleoNodeName;
		}
	}

	@Override
	public void setKaleoNodeName(String kaleoNodeName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoNodeName = kaleoNodeName;
	}

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
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public String getExecutionType() {
		if (_executionType == null) {
			return "";
		}
		else {
			return _executionType;
		}
	}

	@Override
	public void setExecutionType(String executionType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_executionType = executionType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExecutionType() {
		return getColumnOriginalValue("executionType");
	}

	@Override
	public String getScript() {
		if (_script == null) {
			return "";
		}
		else {
			return _script;
		}
	}

	@Override
	public void setScript(String script) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_script = script;
	}

	@Override
	public String getScriptLanguage() {
		if (_scriptLanguage == null) {
			return "";
		}
		else {
			return _scriptLanguage;
		}
	}

	@Override
	public void setScriptLanguage(String scriptLanguage) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_scriptLanguage = scriptLanguage;
	}

	@Override
	public String getScriptRequiredContexts() {
		if (_scriptRequiredContexts == null) {
			return "";
		}
		else {
			return _scriptRequiredContexts;
		}
	}

	@Override
	public void setScriptRequiredContexts(String scriptRequiredContexts) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_scriptRequiredContexts = scriptRequiredContexts;
	}

	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

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
			getCompanyId(), KaleoAction.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoAction toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KaleoAction>
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
		KaleoActionImpl kaleoActionImpl = new KaleoActionImpl();

		kaleoActionImpl.setMvccVersion(getMvccVersion());
		kaleoActionImpl.setCtCollectionId(getCtCollectionId());
		kaleoActionImpl.setKaleoActionId(getKaleoActionId());
		kaleoActionImpl.setGroupId(getGroupId());
		kaleoActionImpl.setCompanyId(getCompanyId());
		kaleoActionImpl.setUserId(getUserId());
		kaleoActionImpl.setUserName(getUserName());
		kaleoActionImpl.setCreateDate(getCreateDate());
		kaleoActionImpl.setModifiedDate(getModifiedDate());
		kaleoActionImpl.setKaleoClassName(getKaleoClassName());
		kaleoActionImpl.setKaleoClassPK(getKaleoClassPK());
		kaleoActionImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoActionImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoActionImpl.setKaleoNodeName(getKaleoNodeName());
		kaleoActionImpl.setName(getName());
		kaleoActionImpl.setDescription(getDescription());
		kaleoActionImpl.setExecutionType(getExecutionType());
		kaleoActionImpl.setScript(getScript());
		kaleoActionImpl.setScriptLanguage(getScriptLanguage());
		kaleoActionImpl.setScriptRequiredContexts(getScriptRequiredContexts());
		kaleoActionImpl.setPriority(getPriority());
		kaleoActionImpl.setType(getType());
		kaleoActionImpl.setStatus(getStatus());

		kaleoActionImpl.resetOriginalValues();

		return kaleoActionImpl;
	}

	@Override
	public KaleoAction cloneWithOriginalValues() {
		KaleoActionImpl kaleoActionImpl = new KaleoActionImpl();

		kaleoActionImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		kaleoActionImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		kaleoActionImpl.setKaleoActionId(
			this.<Long>getColumnOriginalValue("kaleoActionId"));
		kaleoActionImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		kaleoActionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		kaleoActionImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		kaleoActionImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		kaleoActionImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		kaleoActionImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		kaleoActionImpl.setKaleoClassName(
			this.<String>getColumnOriginalValue("kaleoClassName"));
		kaleoActionImpl.setKaleoClassPK(
			this.<Long>getColumnOriginalValue("kaleoClassPK"));
		kaleoActionImpl.setKaleoDefinitionId(
			this.<Long>getColumnOriginalValue("kaleoDefinitionId"));
		kaleoActionImpl.setKaleoDefinitionVersionId(
			this.<Long>getColumnOriginalValue("kaleoDefinitionVersionId"));
		kaleoActionImpl.setKaleoNodeName(
			this.<String>getColumnOriginalValue("kaleoNodeName"));
		kaleoActionImpl.setName(this.<String>getColumnOriginalValue("name"));
		kaleoActionImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		kaleoActionImpl.setExecutionType(
			this.<String>getColumnOriginalValue("executionType"));
		kaleoActionImpl.setScript(
			this.<String>getColumnOriginalValue("script"));
		kaleoActionImpl.setScriptLanguage(
			this.<String>getColumnOriginalValue("scriptLanguage"));
		kaleoActionImpl.setScriptRequiredContexts(
			this.<String>getColumnOriginalValue("scriptRequiredContexts"));
		kaleoActionImpl.setPriority(
			this.<Integer>getColumnOriginalValue("priority"));
		kaleoActionImpl.setType(this.<String>getColumnOriginalValue("type_"));
		kaleoActionImpl.setStatus(
			this.<Integer>getColumnOriginalValue("status"));

		return kaleoActionImpl;
	}

	@Override
	public int compareTo(KaleoAction kaleoAction) {
		int value = 0;

		if (getPriority() < kaleoAction.getPriority()) {
			value = -1;
		}
		else if (getPriority() > kaleoAction.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof KaleoAction)) {
			return false;
		}

		KaleoAction kaleoAction = (KaleoAction)object;

		long primaryKey = kaleoAction.getPrimaryKey();

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
	public CacheModel<KaleoAction> toCacheModel() {
		KaleoActionCacheModel kaleoActionCacheModel =
			new KaleoActionCacheModel();

		kaleoActionCacheModel.mvccVersion = getMvccVersion();

		kaleoActionCacheModel.ctCollectionId = getCtCollectionId();

		kaleoActionCacheModel.kaleoActionId = getKaleoActionId();

		kaleoActionCacheModel.groupId = getGroupId();

		kaleoActionCacheModel.companyId = getCompanyId();

		kaleoActionCacheModel.userId = getUserId();

		kaleoActionCacheModel.userName = getUserName();

		String userName = kaleoActionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoActionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoActionCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoActionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoActionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoActionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoActionCacheModel.kaleoClassName = getKaleoClassName();

		String kaleoClassName = kaleoActionCacheModel.kaleoClassName;

		if ((kaleoClassName != null) && (kaleoClassName.length() == 0)) {
			kaleoActionCacheModel.kaleoClassName = null;
		}

		kaleoActionCacheModel.kaleoClassPK = getKaleoClassPK();

		kaleoActionCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoActionCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoActionCacheModel.kaleoNodeName = getKaleoNodeName();

		String kaleoNodeName = kaleoActionCacheModel.kaleoNodeName;

		if ((kaleoNodeName != null) && (kaleoNodeName.length() == 0)) {
			kaleoActionCacheModel.kaleoNodeName = null;
		}

		kaleoActionCacheModel.name = getName();

		String name = kaleoActionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			kaleoActionCacheModel.name = null;
		}

		kaleoActionCacheModel.description = getDescription();

		String description = kaleoActionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			kaleoActionCacheModel.description = null;
		}

		kaleoActionCacheModel.executionType = getExecutionType();

		String executionType = kaleoActionCacheModel.executionType;

		if ((executionType != null) && (executionType.length() == 0)) {
			kaleoActionCacheModel.executionType = null;
		}

		kaleoActionCacheModel.script = getScript();

		String script = kaleoActionCacheModel.script;

		if ((script != null) && (script.length() == 0)) {
			kaleoActionCacheModel.script = null;
		}

		kaleoActionCacheModel.scriptLanguage = getScriptLanguage();

		String scriptLanguage = kaleoActionCacheModel.scriptLanguage;

		if ((scriptLanguage != null) && (scriptLanguage.length() == 0)) {
			kaleoActionCacheModel.scriptLanguage = null;
		}

		kaleoActionCacheModel.scriptRequiredContexts =
			getScriptRequiredContexts();

		String scriptRequiredContexts =
			kaleoActionCacheModel.scriptRequiredContexts;

		if ((scriptRequiredContexts != null) &&
			(scriptRequiredContexts.length() == 0)) {

			kaleoActionCacheModel.scriptRequiredContexts = null;
		}

		kaleoActionCacheModel.priority = getPriority();

		kaleoActionCacheModel.type = getType();

		String type = kaleoActionCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			kaleoActionCacheModel.type = null;
		}

		kaleoActionCacheModel.status = getStatus();

		return kaleoActionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoAction, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((KaleoAction)this);

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

		private static final Function<InvocationHandler, KaleoAction>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					KaleoAction.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _kaleoActionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoDefinitionVersionId;
	private String _kaleoNodeName;
	private String _name;
	private String _description;
	private String _executionType;
	private String _script;
	private String _scriptLanguage;
	private String _scriptRequiredContexts;
	private int _priority;
	private String _type;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<KaleoAction, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((KaleoAction)this);
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
		_columnOriginalValues.put("kaleoActionId", _kaleoActionId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("kaleoClassName", _kaleoClassName);
		_columnOriginalValues.put("kaleoClassPK", _kaleoClassPK);
		_columnOriginalValues.put("kaleoDefinitionId", _kaleoDefinitionId);
		_columnOriginalValues.put(
			"kaleoDefinitionVersionId", _kaleoDefinitionVersionId);
		_columnOriginalValues.put("kaleoNodeName", _kaleoNodeName);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("executionType", _executionType);
		_columnOriginalValues.put("script", _script);
		_columnOriginalValues.put("scriptLanguage", _scriptLanguage);
		_columnOriginalValues.put(
			"scriptRequiredContexts", _scriptRequiredContexts);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("status", _status);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

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

		columnBitmasks.put("kaleoActionId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("kaleoClassName", 512L);

		columnBitmasks.put("kaleoClassPK", 1024L);

		columnBitmasks.put("kaleoDefinitionId", 2048L);

		columnBitmasks.put("kaleoDefinitionVersionId", 4096L);

		columnBitmasks.put("kaleoNodeName", 8192L);

		columnBitmasks.put("name", 16384L);

		columnBitmasks.put("description", 32768L);

		columnBitmasks.put("executionType", 65536L);

		columnBitmasks.put("script", 131072L);

		columnBitmasks.put("scriptLanguage", 262144L);

		columnBitmasks.put("scriptRequiredContexts", 524288L);

		columnBitmasks.put("priority", 1048576L);

		columnBitmasks.put("type_", 2097152L);

		columnBitmasks.put("status", 4194304L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private KaleoAction _escapedModel;

}