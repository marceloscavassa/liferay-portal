/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.model.CommerceShipmentItemModel;
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
 * The base model implementation for the CommerceShipmentItem service. Represents a row in the &quot;CommerceShipmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceShipmentItemModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShipmentItemImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentItemImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceShipmentItemModelImpl
	extends BaseModelImpl<CommerceShipmentItem>
	implements CommerceShipmentItemModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipment item model instance should use the <code>CommerceShipmentItem</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceShipmentItem";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"externalReferenceCode", Types.VARCHAR},
		{"commerceShipmentItemId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceShipmentId", Types.BIGINT},
		{"commerceOrderItemId", Types.BIGINT},
		{"commerceInventoryWarehouseId", Types.BIGINT},
		{"quantity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceShipmentItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceShipmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceOrderItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceInventoryWarehouseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceShipmentItem (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,commerceShipmentItemId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceShipmentId LONG,commerceOrderItemId LONG,commerceInventoryWarehouseId LONG,quantity INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceShipmentItem";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceShipmentItem.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceShipmentItem.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEINVENTORYWAREHOUSEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEORDERITEMID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCESHIPMENTID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 128L;

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

	public CommerceShipmentItemModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceShipmentItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceShipmentItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceShipmentItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceShipmentItem.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceShipmentItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceShipmentItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceShipmentItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipmentItem, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceShipmentItem)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceShipmentItem, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceShipmentItem, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceShipmentItem)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceShipmentItem, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceShipmentItem, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<CommerceShipmentItem, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<CommerceShipmentItem, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CommerceShipmentItem, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CommerceShipmentItem::getMvccVersion);
			attributeGetterFunctions.put("uuid", CommerceShipmentItem::getUuid);
			attributeGetterFunctions.put(
				"externalReferenceCode",
				CommerceShipmentItem::getExternalReferenceCode);
			attributeGetterFunctions.put(
				"commerceShipmentItemId",
				CommerceShipmentItem::getCommerceShipmentItemId);
			attributeGetterFunctions.put(
				"groupId", CommerceShipmentItem::getGroupId);
			attributeGetterFunctions.put(
				"companyId", CommerceShipmentItem::getCompanyId);
			attributeGetterFunctions.put(
				"userId", CommerceShipmentItem::getUserId);
			attributeGetterFunctions.put(
				"userName", CommerceShipmentItem::getUserName);
			attributeGetterFunctions.put(
				"createDate", CommerceShipmentItem::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", CommerceShipmentItem::getModifiedDate);
			attributeGetterFunctions.put(
				"commerceShipmentId",
				CommerceShipmentItem::getCommerceShipmentId);
			attributeGetterFunctions.put(
				"commerceOrderItemId",
				CommerceShipmentItem::getCommerceOrderItemId);
			attributeGetterFunctions.put(
				"commerceInventoryWarehouseId",
				CommerceShipmentItem::getCommerceInventoryWarehouseId);
			attributeGetterFunctions.put(
				"quantity", CommerceShipmentItem::getQuantity);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CommerceShipmentItem, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CommerceShipmentItem, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CommerceShipmentItem, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setMvccVersion);
			attributeSetterBiConsumers.put(
				"uuid",
				(BiConsumer<CommerceShipmentItem, String>)
					CommerceShipmentItem::setUuid);
			attributeSetterBiConsumers.put(
				"externalReferenceCode",
				(BiConsumer<CommerceShipmentItem, String>)
					CommerceShipmentItem::setExternalReferenceCode);
			attributeSetterBiConsumers.put(
				"commerceShipmentItemId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setCommerceShipmentItemId);
			attributeSetterBiConsumers.put(
				"groupId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setGroupId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setCompanyId);
			attributeSetterBiConsumers.put(
				"userId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setUserId);
			attributeSetterBiConsumers.put(
				"userName",
				(BiConsumer<CommerceShipmentItem, String>)
					CommerceShipmentItem::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<CommerceShipmentItem, Date>)
					CommerceShipmentItem::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<CommerceShipmentItem, Date>)
					CommerceShipmentItem::setModifiedDate);
			attributeSetterBiConsumers.put(
				"commerceShipmentId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setCommerceShipmentId);
			attributeSetterBiConsumers.put(
				"commerceOrderItemId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setCommerceOrderItemId);
			attributeSetterBiConsumers.put(
				"commerceInventoryWarehouseId",
				(BiConsumer<CommerceShipmentItem, Long>)
					CommerceShipmentItem::setCommerceInventoryWarehouseId);
			attributeSetterBiConsumers.put(
				"quantity",
				(BiConsumer<CommerceShipmentItem, Integer>)
					CommerceShipmentItem::setQuantity);

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
	public long getCommerceShipmentItemId() {
		return _commerceShipmentItemId;
	}

	@Override
	public void setCommerceShipmentItemId(long commerceShipmentItemId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShipmentItemId = commerceShipmentItemId;
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
	public long getCommerceShipmentId() {
		return _commerceShipmentId;
	}

	@Override
	public void setCommerceShipmentId(long commerceShipmentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShipmentId = commerceShipmentId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceShipmentId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceShipmentId"));
	}

	@JSON
	@Override
	public long getCommerceOrderItemId() {
		return _commerceOrderItemId;
	}

	@Override
	public void setCommerceOrderItemId(long commerceOrderItemId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderItemId = commerceOrderItemId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceOrderItemId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceOrderItemId"));
	}

	@JSON
	@Override
	public long getCommerceInventoryWarehouseId() {
		return _commerceInventoryWarehouseId;
	}

	@Override
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceInventoryWarehouseId = commerceInventoryWarehouseId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceInventoryWarehouseId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceInventoryWarehouseId"));
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_quantity = quantity;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CommerceShipmentItem.class.getName()));
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
			getCompanyId(), CommerceShipmentItem.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceShipmentItem toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceShipmentItem>
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
		CommerceShipmentItemImpl commerceShipmentItemImpl =
			new CommerceShipmentItemImpl();

		commerceShipmentItemImpl.setMvccVersion(getMvccVersion());
		commerceShipmentItemImpl.setUuid(getUuid());
		commerceShipmentItemImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceShipmentItemImpl.setCommerceShipmentItemId(
			getCommerceShipmentItemId());
		commerceShipmentItemImpl.setGroupId(getGroupId());
		commerceShipmentItemImpl.setCompanyId(getCompanyId());
		commerceShipmentItemImpl.setUserId(getUserId());
		commerceShipmentItemImpl.setUserName(getUserName());
		commerceShipmentItemImpl.setCreateDate(getCreateDate());
		commerceShipmentItemImpl.setModifiedDate(getModifiedDate());
		commerceShipmentItemImpl.setCommerceShipmentId(getCommerceShipmentId());
		commerceShipmentItemImpl.setCommerceOrderItemId(
			getCommerceOrderItemId());
		commerceShipmentItemImpl.setCommerceInventoryWarehouseId(
			getCommerceInventoryWarehouseId());
		commerceShipmentItemImpl.setQuantity(getQuantity());

		commerceShipmentItemImpl.resetOriginalValues();

		return commerceShipmentItemImpl;
	}

	@Override
	public CommerceShipmentItem cloneWithOriginalValues() {
		CommerceShipmentItemImpl commerceShipmentItemImpl =
			new CommerceShipmentItemImpl();

		commerceShipmentItemImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceShipmentItemImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		commerceShipmentItemImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		commerceShipmentItemImpl.setCommerceShipmentItemId(
			this.<Long>getColumnOriginalValue("commerceShipmentItemId"));
		commerceShipmentItemImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		commerceShipmentItemImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceShipmentItemImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceShipmentItemImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceShipmentItemImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceShipmentItemImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceShipmentItemImpl.setCommerceShipmentId(
			this.<Long>getColumnOriginalValue("commerceShipmentId"));
		commerceShipmentItemImpl.setCommerceOrderItemId(
			this.<Long>getColumnOriginalValue("commerceOrderItemId"));
		commerceShipmentItemImpl.setCommerceInventoryWarehouseId(
			this.<Long>getColumnOriginalValue("commerceInventoryWarehouseId"));
		commerceShipmentItemImpl.setQuantity(
			this.<Integer>getColumnOriginalValue("quantity"));

		return commerceShipmentItemImpl;
	}

	@Override
	public int compareTo(CommerceShipmentItem commerceShipmentItem) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceShipmentItem.getCreateDate());

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

		if (!(object instanceof CommerceShipmentItem)) {
			return false;
		}

		CommerceShipmentItem commerceShipmentItem =
			(CommerceShipmentItem)object;

		long primaryKey = commerceShipmentItem.getPrimaryKey();

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
	public CacheModel<CommerceShipmentItem> toCacheModel() {
		CommerceShipmentItemCacheModel commerceShipmentItemCacheModel =
			new CommerceShipmentItemCacheModel();

		commerceShipmentItemCacheModel.mvccVersion = getMvccVersion();

		commerceShipmentItemCacheModel.uuid = getUuid();

		String uuid = commerceShipmentItemCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceShipmentItemCacheModel.uuid = null;
		}

		commerceShipmentItemCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceShipmentItemCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceShipmentItemCacheModel.externalReferenceCode = null;
		}

		commerceShipmentItemCacheModel.commerceShipmentItemId =
			getCommerceShipmentItemId();

		commerceShipmentItemCacheModel.groupId = getGroupId();

		commerceShipmentItemCacheModel.companyId = getCompanyId();

		commerceShipmentItemCacheModel.userId = getUserId();

		commerceShipmentItemCacheModel.userName = getUserName();

		String userName = commerceShipmentItemCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceShipmentItemCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceShipmentItemCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceShipmentItemCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceShipmentItemCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceShipmentItemCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceShipmentItemCacheModel.commerceShipmentId =
			getCommerceShipmentId();

		commerceShipmentItemCacheModel.commerceOrderItemId =
			getCommerceOrderItemId();

		commerceShipmentItemCacheModel.commerceInventoryWarehouseId =
			getCommerceInventoryWarehouseId();

		commerceShipmentItemCacheModel.quantity = getQuantity();

		return commerceShipmentItemCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceShipmentItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceShipmentItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShipmentItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceShipmentItem)this);

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

		private static final Function<InvocationHandler, CommerceShipmentItem>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					CommerceShipmentItem.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private String _uuid;
	private String _externalReferenceCode;
	private long _commerceShipmentItemId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceShipmentId;
	private long _commerceOrderItemId;
	private long _commerceInventoryWarehouseId;
	private int _quantity;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceShipmentItem, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceShipmentItem)this);
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
		_columnOriginalValues.put(
			"commerceShipmentItemId", _commerceShipmentItemId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceShipmentId", _commerceShipmentId);
		_columnOriginalValues.put("commerceOrderItemId", _commerceOrderItemId);
		_columnOriginalValues.put(
			"commerceInventoryWarehouseId", _commerceInventoryWarehouseId);
		_columnOriginalValues.put("quantity", _quantity);
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

		columnBitmasks.put("externalReferenceCode", 4L);

		columnBitmasks.put("commerceShipmentItemId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("commerceShipmentId", 1024L);

		columnBitmasks.put("commerceOrderItemId", 2048L);

		columnBitmasks.put("commerceInventoryWarehouseId", 4096L);

		columnBitmasks.put("quantity", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceShipmentItem _escapedModel;

}