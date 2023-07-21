/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.view.state.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.frontend.view.state.model.FVSEntry;
import com.liferay.frontend.view.state.model.FVSEntryModel;
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
 * The base model implementation for the FVSEntry service. Represents a row in the &quot;FVSEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FVSEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FVSEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FVSEntryImpl
 * @generated
 */
public class FVSEntryModelImpl
	extends BaseModelImpl<FVSEntry> implements FVSEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a fvs entry model instance should use the <code>FVSEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "FVSEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"fvsEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"viewState", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fvsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("viewState", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FVSEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,fvsEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,viewState TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table FVSEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY fvsEntry.fvsEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FVSEntry.fvsEntryId ASC";

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
	public static final long UUID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FVSENTRYID_COLUMN_BITMASK = 4L;

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

	public FVSEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fvsEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFvsEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fvsEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FVSEntry.class;
	}

	@Override
	public String getModelClassName() {
		return FVSEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FVSEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FVSEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((FVSEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FVSEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FVSEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FVSEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FVSEntry, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FVSEntry, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<FVSEntry, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<FVSEntry, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<FVSEntry, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", FVSEntry::getMvccVersion);
			attributeGetterFunctions.put("uuid", FVSEntry::getUuid);
			attributeGetterFunctions.put("fvsEntryId", FVSEntry::getFvsEntryId);
			attributeGetterFunctions.put("companyId", FVSEntry::getCompanyId);
			attributeGetterFunctions.put("userId", FVSEntry::getUserId);
			attributeGetterFunctions.put("userName", FVSEntry::getUserName);
			attributeGetterFunctions.put("createDate", FVSEntry::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", FVSEntry::getModifiedDate);
			attributeGetterFunctions.put("viewState", FVSEntry::getViewState);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<FVSEntry, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<FVSEntry, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<FVSEntry, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<FVSEntry, Long>)FVSEntry::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid", (BiConsumer<FVSEntry, String>)FVSEntry::setUuid);
			attributeSetterBiConsumers.put(
				"fvsEntryId",
				(BiConsumer<FVSEntry, Long>)FVSEntry::setFvsEntryId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<FVSEntry, Long>)FVSEntry::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<FVSEntry, Long>)FVSEntry::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<FVSEntry, String>)FVSEntry::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<FVSEntry, Date>)FVSEntry::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<FVSEntry, Date>)FVSEntry::setModifiedDate);
			attributeSetterBiConsumers.put(
				"viewState",
				(BiConsumer<FVSEntry, String>)FVSEntry::setViewState);

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
	public long getFvsEntryId() {
		return _fvsEntryId;
	}

	@Override
	public void setFvsEntryId(long fvsEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fvsEntryId = fvsEntryId;
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
	public String getViewState() {
		if (_viewState == null) {
			return "";
		}
		else {
			return _viewState;
		}
	}

	@Override
	public void setViewState(String viewState) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_viewState = viewState;
	}

	@Override
	public long getContainerModelId() {
		return getFvsEntryId();
	}

	@Override
	public void setContainerModelId(long containerModelId) {
		_fvsEntryId = containerModelId;
	}

	@Override
	public String getContainerModelName() {
		return String.valueOf(getContainerModelId());
	}

	@Override
	public long getParentContainerModelId() {
		return 0;
	}

	@Override
	public void setParentContainerModelId(long parentContainerModelId) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(FVSEntry.class.getName()));
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
			getCompanyId(), FVSEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FVSEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FVSEntry>
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
		FVSEntryImpl fvsEntryImpl = new FVSEntryImpl();

		fvsEntryImpl.setMvccVersion(getMvccVersion());
		fvsEntryImpl.setUuid(getUuid());
		fvsEntryImpl.setFvsEntryId(getFvsEntryId());
		fvsEntryImpl.setCompanyId(getCompanyId());
		fvsEntryImpl.setUserId(getUserId());
		fvsEntryImpl.setUserName(getUserName());
		fvsEntryImpl.setCreateDate(getCreateDate());
		fvsEntryImpl.setModifiedDate(getModifiedDate());
		fvsEntryImpl.setViewState(getViewState());

		fvsEntryImpl.resetOriginalValues();

		return fvsEntryImpl;
	}

	@Override
	public FVSEntry cloneWithOriginalValues() {
		FVSEntryImpl fvsEntryImpl = new FVSEntryImpl();

		fvsEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		fvsEntryImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		fvsEntryImpl.setFvsEntryId(
			this.<Long>getColumnOriginalValue("fvsEntryId"));
		fvsEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		fvsEntryImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		fvsEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		fvsEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		fvsEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		fvsEntryImpl.setViewState(
			this.<String>getColumnOriginalValue("viewState"));

		return fvsEntryImpl;
	}

	@Override
	public int compareTo(FVSEntry fvsEntry) {
		long primaryKey = fvsEntry.getPrimaryKey();

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

		if (!(object instanceof FVSEntry)) {
			return false;
		}

		FVSEntry fvsEntry = (FVSEntry)object;

		long primaryKey = fvsEntry.getPrimaryKey();

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
	public CacheModel<FVSEntry> toCacheModel() {
		FVSEntryCacheModel fvsEntryCacheModel = new FVSEntryCacheModel();

		fvsEntryCacheModel.mvccVersion = getMvccVersion();

		fvsEntryCacheModel.uuid = getUuid();

		String uuid = fvsEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			fvsEntryCacheModel.uuid = null;
		}

		fvsEntryCacheModel.fvsEntryId = getFvsEntryId();

		fvsEntryCacheModel.companyId = getCompanyId();

		fvsEntryCacheModel.userId = getUserId();

		fvsEntryCacheModel.userName = getUserName();

		String userName = fvsEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			fvsEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			fvsEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			fvsEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			fvsEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			fvsEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		fvsEntryCacheModel.viewState = getViewState();

		String viewState = fvsEntryCacheModel.viewState;

		if ((viewState != null) && (viewState.length() == 0)) {
			fvsEntryCacheModel.viewState = null;
		}

		return fvsEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FVSEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FVSEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((FVSEntry)this);

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

		private static final Function<InvocationHandler, FVSEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					FVSEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _fvsEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _viewState;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<FVSEntry, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FVSEntry)this);
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
		_columnOriginalValues.put("fvsEntryId", _fvsEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("viewState", _viewState);
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

		columnBitmasks.put("fvsEntryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("viewState", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FVSEntry _escapedModel;

}