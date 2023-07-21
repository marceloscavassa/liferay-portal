/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.message.storage.model.impl;

import com.liferay.analytics.message.storage.model.AnalyticsAssociation;
import com.liferay.analytics.message.storage.model.AnalyticsAssociationModel;
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
 * The base model implementation for the AnalyticsAssociation service. Represents a row in the &quot;AnalyticsAssociation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AnalyticsAssociationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnalyticsAssociationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsAssociationImpl
 * @generated
 */
public class AnalyticsAssociationModelImpl
	extends BaseModelImpl<AnalyticsAssociation>
	implements AnalyticsAssociationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a analytics association model instance should use the <code>AnalyticsAssociation</code> interface instead.
	 */
	public static final String TABLE_NAME = "AnalyticsAssociation";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"analyticsAssociationId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"userId", Types.BIGINT}, {"associationClassName", Types.VARCHAR},
		{"associationClassPK", Types.BIGINT}, {"className", Types.VARCHAR},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("analyticsAssociationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("associationClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("associationClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AnalyticsAssociation (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,analyticsAssociationId LONG not null,companyId LONG,createDate DATE null,modifiedDate DATE null,userId LONG,associationClassName VARCHAR(75) null,associationClassPK LONG,className VARCHAR(75) null,classPK LONG,primary key (analyticsAssociationId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table AnalyticsAssociation";

	public static final String ORDER_BY_JPQL =
		" ORDER BY analyticsAssociation.analyticsAssociationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AnalyticsAssociation.analyticsAssociationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSOCIATIONCLASSNAME_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSOCIATIONCLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ANALYTICSASSOCIATIONID_COLUMN_BITMASK = 16L;

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

	public AnalyticsAssociationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _analyticsAssociationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAnalyticsAssociationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _analyticsAssociationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AnalyticsAssociation.class;
	}

	@Override
	public String getModelClassName() {
		return AnalyticsAssociation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AnalyticsAssociation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AnalyticsAssociation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnalyticsAssociation, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AnalyticsAssociation)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AnalyticsAssociation, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AnalyticsAssociation, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AnalyticsAssociation)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AnalyticsAssociation, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AnalyticsAssociation, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<AnalyticsAssociation, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<AnalyticsAssociation, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<AnalyticsAssociation, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", AnalyticsAssociation::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", AnalyticsAssociation::getCtCollectionId);
			attributeGetterFunctions.put(
				"analyticsAssociationId",
				AnalyticsAssociation::getAnalyticsAssociationId);
			attributeGetterFunctions.put(
				"companyId", AnalyticsAssociation::getCompanyId);
			attributeGetterFunctions.put(
				"createDate", AnalyticsAssociation::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", AnalyticsAssociation::getModifiedDate);
			attributeGetterFunctions.put(
				"userId", AnalyticsAssociation::getUserId);
			attributeGetterFunctions.put(
				"associationClassName",
				AnalyticsAssociation::getAssociationClassName);
			attributeGetterFunctions.put(
				"associationClassPK",
				AnalyticsAssociation::getAssociationClassPK);
			attributeGetterFunctions.put(
				"className", AnalyticsAssociation::getClassName);
			attributeGetterFunctions.put(
				"classPK", AnalyticsAssociation::getClassPK);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<AnalyticsAssociation, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<AnalyticsAssociation, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<AnalyticsAssociation, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"analyticsAssociationId",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setAnalyticsAssociationId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setCompanyId);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<AnalyticsAssociation, Date>)
					AnalyticsAssociation::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<AnalyticsAssociation, Date>)
					AnalyticsAssociation::setModifiedDate);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setUserId);
			attributeSetterBiConsumers.put(
				"associationClassName",
				(BiConsumer<AnalyticsAssociation, String>)
					AnalyticsAssociation::setAssociationClassName);
			attributeSetterBiConsumers.put(
				"associationClassPK",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setAssociationClassPK);
			attributeSetterBiConsumers.put(
				"className",
				(BiConsumer<AnalyticsAssociation, String>)
					AnalyticsAssociation::setClassName);
			attributeSetterBiConsumers.put(
				"classPK",
				(BiConsumer<AnalyticsAssociation, Long>)
					AnalyticsAssociation::setClassPK);

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
	public long getAnalyticsAssociationId() {
		return _analyticsAssociationId;
	}

	@Override
	public void setAnalyticsAssociationId(long analyticsAssociationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_analyticsAssociationId = analyticsAssociationId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalModifiedDate() {
		return getColumnOriginalValue("modifiedDate");
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
	public String getAssociationClassName() {
		if (_associationClassName == null) {
			return "";
		}
		else {
			return _associationClassName;
		}
	}

	@Override
	public void setAssociationClassName(String associationClassName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_associationClassName = associationClassName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalAssociationClassName() {
		return getColumnOriginalValue("associationClassName");
	}

	@Override
	public long getAssociationClassPK() {
		return _associationClassPK;
	}

	@Override
	public void setAssociationClassPK(long associationClassPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_associationClassPK = associationClassPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAssociationClassPK() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("associationClassPK"));
	}

	@Override
	public String getClassName() {
		if (_className == null) {
			return "";
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_className = className;
	}

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
			getCompanyId(), AnalyticsAssociation.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AnalyticsAssociation toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AnalyticsAssociation>
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
		AnalyticsAssociationImpl analyticsAssociationImpl =
			new AnalyticsAssociationImpl();

		analyticsAssociationImpl.setMvccVersion(getMvccVersion());
		analyticsAssociationImpl.setCtCollectionId(getCtCollectionId());
		analyticsAssociationImpl.setAnalyticsAssociationId(
			getAnalyticsAssociationId());
		analyticsAssociationImpl.setCompanyId(getCompanyId());
		analyticsAssociationImpl.setCreateDate(getCreateDate());
		analyticsAssociationImpl.setModifiedDate(getModifiedDate());
		analyticsAssociationImpl.setUserId(getUserId());
		analyticsAssociationImpl.setAssociationClassName(
			getAssociationClassName());
		analyticsAssociationImpl.setAssociationClassPK(getAssociationClassPK());
		analyticsAssociationImpl.setClassName(getClassName());
		analyticsAssociationImpl.setClassPK(getClassPK());

		analyticsAssociationImpl.resetOriginalValues();

		return analyticsAssociationImpl;
	}

	@Override
	public AnalyticsAssociation cloneWithOriginalValues() {
		AnalyticsAssociationImpl analyticsAssociationImpl =
			new AnalyticsAssociationImpl();

		analyticsAssociationImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		analyticsAssociationImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		analyticsAssociationImpl.setAnalyticsAssociationId(
			this.<Long>getColumnOriginalValue("analyticsAssociationId"));
		analyticsAssociationImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		analyticsAssociationImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		analyticsAssociationImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		analyticsAssociationImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		analyticsAssociationImpl.setAssociationClassName(
			this.<String>getColumnOriginalValue("associationClassName"));
		analyticsAssociationImpl.setAssociationClassPK(
			this.<Long>getColumnOriginalValue("associationClassPK"));
		analyticsAssociationImpl.setClassName(
			this.<String>getColumnOriginalValue("className"));
		analyticsAssociationImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));

		return analyticsAssociationImpl;
	}

	@Override
	public int compareTo(AnalyticsAssociation analyticsAssociation) {
		long primaryKey = analyticsAssociation.getPrimaryKey();

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

		if (!(object instanceof AnalyticsAssociation)) {
			return false;
		}

		AnalyticsAssociation analyticsAssociation =
			(AnalyticsAssociation)object;

		long primaryKey = analyticsAssociation.getPrimaryKey();

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
	public CacheModel<AnalyticsAssociation> toCacheModel() {
		AnalyticsAssociationCacheModel analyticsAssociationCacheModel =
			new AnalyticsAssociationCacheModel();

		analyticsAssociationCacheModel.mvccVersion = getMvccVersion();

		analyticsAssociationCacheModel.ctCollectionId = getCtCollectionId();

		analyticsAssociationCacheModel.analyticsAssociationId =
			getAnalyticsAssociationId();

		analyticsAssociationCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			analyticsAssociationCacheModel.createDate = createDate.getTime();
		}
		else {
			analyticsAssociationCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			analyticsAssociationCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			analyticsAssociationCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		analyticsAssociationCacheModel.userId = getUserId();

		analyticsAssociationCacheModel.associationClassName =
			getAssociationClassName();

		String associationClassName =
			analyticsAssociationCacheModel.associationClassName;

		if ((associationClassName != null) &&
			(associationClassName.length() == 0)) {

			analyticsAssociationCacheModel.associationClassName = null;
		}

		analyticsAssociationCacheModel.associationClassPK =
			getAssociationClassPK();

		analyticsAssociationCacheModel.className = getClassName();

		String className = analyticsAssociationCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			analyticsAssociationCacheModel.className = null;
		}

		analyticsAssociationCacheModel.classPK = getClassPK();

		return analyticsAssociationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AnalyticsAssociation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AnalyticsAssociation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnalyticsAssociation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(AnalyticsAssociation)this);

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

		private static final Function<InvocationHandler, AnalyticsAssociation>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					AnalyticsAssociation.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _analyticsAssociationId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _userId;
	private String _associationClassName;
	private long _associationClassPK;
	private String _className;
	private long _classPK;

	public <T> T getColumnValue(String columnName) {
		Function<AnalyticsAssociation, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AnalyticsAssociation)this);
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
		_columnOriginalValues.put(
			"analyticsAssociationId", _analyticsAssociationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put(
			"associationClassName", _associationClassName);
		_columnOriginalValues.put("associationClassPK", _associationClassPK);
		_columnOriginalValues.put("className", _className);
		_columnOriginalValues.put("classPK", _classPK);
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

		columnBitmasks.put("analyticsAssociationId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("associationClassName", 128L);

		columnBitmasks.put("associationClassPK", 256L);

		columnBitmasks.put("className", 512L);

		columnBitmasks.put("classPK", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AnalyticsAssociation _escapedModel;

}