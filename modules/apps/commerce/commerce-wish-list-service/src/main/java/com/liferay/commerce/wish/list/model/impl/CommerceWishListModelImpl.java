/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.wish.list.model.impl;

import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.commerce.wish.list.model.CommerceWishListModel;
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
 * The base model implementation for the CommerceWishList service. Represents a row in the &quot;CommerceWishList&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceWishListModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceWishListImpl}.
 * </p>
 *
 * @author Andrea Di Giorgi
 * @see CommerceWishListImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceWishListModelImpl
	extends BaseModelImpl<CommerceWishList> implements CommerceWishListModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce wish list model instance should use the <code>CommerceWishList</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceWishList";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"commerceWishListId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"name", Types.VARCHAR},
		{"defaultWishList", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceWishListId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("defaultWishList", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceWishList (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,commerceWishListId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,defaultWishList BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CommerceWishList";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceWishList.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceWishList.name ASC";

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
	public static final long CREATEDATE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEFAULTWISHLIST_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 64L;

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

	public CommerceWishListModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceWishListId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceWishListId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceWishListId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceWishList.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceWishList.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceWishList, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceWishList, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceWishList, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceWishList)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceWishList, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceWishList, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceWishList)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceWishList, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceWishList, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CommerceWishList, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceWishList, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommerceWishList, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceWishList::getMvccVersion);
			attributeGetterFunctions.put("uuid", CommerceWishList::getUuid);
			attributeGetterFunctions.put(
				"commerceWishListId", CommerceWishList::getCommerceWishListId);
			attributeGetterFunctions.put(
				"groupId", CommerceWishList::getGroupId);
			attributeGetterFunctions.put(
				"companyId", CommerceWishList::getCompanyId);
			attributeGetterFunctions.put("userId", CommerceWishList::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceWishList::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceWishList::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceWishList::getModifiedDate);
			attributeGetterFunctions.put("name", CommerceWishList::getName);
			attributeGetterFunctions.put(
				"defaultWishList", CommerceWishList::getDefaultWishList);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<CommerceWishList, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceWishList, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceWishList, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceWishList, Long>)
					CommerceWishList::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommerceWishList, String>)
					CommerceWishList::setUuid);
			attributeSetterBiConsumers.put(
				"commerceWishListId",
				(BiConsumer<CommerceWishList, Long>)
					CommerceWishList::setCommerceWishListId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<CommerceWishList, Long>)
					CommerceWishList::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceWishList, Long>)
					CommerceWishList::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceWishList, Long>)
					CommerceWishList::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceWishList, String>)
					CommerceWishList::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceWishList, Date>)
					CommerceWishList::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceWishList, Date>)
					CommerceWishList::setModifiedDate);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<CommerceWishList, String>)
					CommerceWishList::setName);
			attributeSetterBiConsumers.put(
				"defaultWishList",
				(BiConsumer<CommerceWishList, Boolean>)
					CommerceWishList::setDefaultWishList);

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
	public long getCommerceWishListId() {
		return _commerceWishListId;
	}

	@Override
	public void setCommerceWishListId(long commerceWishListId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceWishListId = commerceWishListId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalCreateDate() {
		return getColumnOriginalValue("createDate");
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
	public boolean getDefaultWishList() {
		return _defaultWishList;
	}

	@JSON
	@Override
	public boolean isDefaultWishList() {
		return _defaultWishList;
	}

	@Override
	public void setDefaultWishList(boolean defaultWishList) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultWishList = defaultWishList;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalDefaultWishList() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("defaultWishList"));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CommerceWishList.class.getName()));
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
			getCompanyId(), CommerceWishList.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceWishList toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceWishList>
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
		CommerceWishListImpl commerceWishListImpl = new CommerceWishListImpl();

		commerceWishListImpl.setMvccVersion(getMvccVersion());
		commerceWishListImpl.setUuid(getUuid());
		commerceWishListImpl.setCommerceWishListId(getCommerceWishListId());
		commerceWishListImpl.setGroupId(getGroupId());
		commerceWishListImpl.setCompanyId(getCompanyId());
		commerceWishListImpl.setUserId(getUserId());
		commerceWishListImpl.setUserName(getUserName());
		commerceWishListImpl.setCreateDate(getCreateDate());
		commerceWishListImpl.setModifiedDate(getModifiedDate());
		commerceWishListImpl.setName(getName());
		commerceWishListImpl.setDefaultWishList(isDefaultWishList());

		commerceWishListImpl.resetOriginalValues();

		return commerceWishListImpl;
	}

	@Override
	public CommerceWishList cloneWithOriginalValues() {
		CommerceWishListImpl commerceWishListImpl = new CommerceWishListImpl();

		commerceWishListImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceWishListImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceWishListImpl.setCommerceWishListId(
			this.<Long>getColumnOriginalValue("commerceWishListId"));
		commerceWishListImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		commerceWishListImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceWishListImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceWishListImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceWishListImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceWishListImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceWishListImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		commerceWishListImpl.setDefaultWishList(
			this.<Boolean>getColumnOriginalValue("defaultWishList"));

		return commerceWishListImpl;
	}

	@Override
	public int compareTo(CommerceWishList commerceWishList) {
		int value = 0;

		value = getName().compareTo(commerceWishList.getName());

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

		if (!(object instanceof CommerceWishList)) {
			return false;
		}

		CommerceWishList commerceWishList = (CommerceWishList)object;

		long primaryKey = commerceWishList.getPrimaryKey();

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
	public CacheModel<CommerceWishList> toCacheModel() {
		CommerceWishListCacheModel commerceWishListCacheModel =
			new CommerceWishListCacheModel();

		commerceWishListCacheModel.mvccVersion = getMvccVersion();

		commerceWishListCacheModel.uuid = getUuid();

		String uuid = commerceWishListCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceWishListCacheModel.uuid = null;
		}

		commerceWishListCacheModel.commerceWishListId = getCommerceWishListId();

		commerceWishListCacheModel.groupId = getGroupId();

		commerceWishListCacheModel.companyId = getCompanyId();

		commerceWishListCacheModel.userId = getUserId();

		commerceWishListCacheModel.userName = getUserName();

		String userName = commerceWishListCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceWishListCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceWishListCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceWishListCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceWishListCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceWishListCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceWishListCacheModel.name = getName();

		String name = commerceWishListCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceWishListCacheModel.name = null;
		}

		commerceWishListCacheModel.defaultWishList = isDefaultWishList();

		return commerceWishListCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceWishList, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceWishList, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceWishList, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceWishList)this);

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

		private static final Function<InvocationHandler, CommerceWishList>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceWishList.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private long _commerceWishListId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private boolean _defaultWishList;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceWishList, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceWishList)this);
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
		_columnOriginalValues.put("commerceWishListId", _commerceWishListId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("defaultWishList", _defaultWishList);
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

		columnBitmasks.put("commerceWishListId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("name", 512L);

		columnBitmasks.put("defaultWishList", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceWishList _escapedModel;

}