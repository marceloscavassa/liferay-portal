/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPDefinitionLocalization;
import com.liferay.commerce.product.model.CPDefinitionLocalizationModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the CPDefinitionLocalization service. Represents a row in the &quot;CPDefinitionLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CPDefinitionLocalizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPDefinitionLocalizationImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionLocalizationImpl
 * @generated
 */
public class CPDefinitionLocalizationModelImpl
	extends BaseModelImpl<CPDefinitionLocalization>
	implements CPDefinitionLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp definition localization model instance should use the <code>CPDefinitionLocalization</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPDefinitionLocalization";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"cpDefinitionLocalizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"CPDefinitionId", Types.BIGINT},
		{"languageId", Types.VARCHAR}, {"name", Types.VARCHAR},
		{"shortDescription", Types.VARCHAR}, {"description", Types.CLOB},
		{"metaTitle", Types.VARCHAR}, {"metaDescription", Types.VARCHAR},
		{"metaKeywords", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("cpDefinitionLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("shortDescription", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.CLOB);
		TABLE_COLUMNS_MAP.put("metaTitle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metaDescription", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metaKeywords", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPDefinitionLocalization (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,cpDefinitionLocalizationId LONG not null,companyId LONG,CPDefinitionId LONG,languageId VARCHAR(75) null,name STRING null,shortDescription STRING null,description TEXT null,metaTitle VARCHAR(255) null,metaDescription VARCHAR(255) null,metaKeywords VARCHAR(255) null,primary key (cpDefinitionLocalizationId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table CPDefinitionLocalization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cpDefinitionLocalization.cpDefinitionLocalizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPDefinitionLocalization.cpDefinitionLocalizationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LANGUAGEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CPDEFINITIONLOCALIZATIONID_COLUMN_BITMASK = 4L;

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

	public CPDefinitionLocalizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _cpDefinitionLocalizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCpDefinitionLocalizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cpDefinitionLocalizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPDefinitionLocalization.class;
	}

	@Override
	public String getModelClassName() {
		return CPDefinitionLocalization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPDefinitionLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPDefinitionLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionLocalization, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CPDefinitionLocalization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPDefinitionLocalization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPDefinitionLocalization, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPDefinitionLocalization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPDefinitionLocalization, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPDefinitionLocalization, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map
			<String, Function<CPDefinitionLocalization, Object>>
				_attributeGetterFunctions;

		static {
			Map<String, Function<CPDefinitionLocalization, Object>>
				attributeGetterFunctions =
					new LinkedHashMap
						<String, Function<CPDefinitionLocalization, Object>>();

			attributeGetterFunctions.put(
				"mvccVersion", CPDefinitionLocalization::getMvccVersion);
			attributeGetterFunctions.put(
				"ctCollectionId", CPDefinitionLocalization::getCtCollectionId);
			attributeGetterFunctions.put(
				"cpDefinitionLocalizationId",
				CPDefinitionLocalization::getCpDefinitionLocalizationId);
			attributeGetterFunctions.put(
				"companyId", CPDefinitionLocalization::getCompanyId);
			attributeGetterFunctions.put(
				"CPDefinitionId", CPDefinitionLocalization::getCPDefinitionId);
			attributeGetterFunctions.put(
				"languageId", CPDefinitionLocalization::getLanguageId);
			attributeGetterFunctions.put(
				"name", CPDefinitionLocalization::getName);
			attributeGetterFunctions.put(
				"shortDescription",
				CPDefinitionLocalization::getShortDescription);
			attributeGetterFunctions.put(
				"description", CPDefinitionLocalization::getDescription);
			attributeGetterFunctions.put(
				"metaTitle", CPDefinitionLocalization::getMetaTitle);
			attributeGetterFunctions.put(
				"metaDescription",
				CPDefinitionLocalization::getMetaDescription);
			attributeGetterFunctions.put(
				"metaKeywords", CPDefinitionLocalization::getMetaKeywords);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map
			<String, BiConsumer<CPDefinitionLocalization, Object>>
				_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<CPDefinitionLocalization, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap
						<String, BiConsumer<CPDefinitionLocalization, ?>>();

			attributeSetterBiConsumers.put(
				"mvccVersion",
				(BiConsumer<CPDefinitionLocalization, Long>)
					CPDefinitionLocalization::setMvccVersion);
			attributeSetterBiConsumers.put(
				"ctCollectionId",
				(BiConsumer<CPDefinitionLocalization, Long>)
					CPDefinitionLocalization::setCtCollectionId);
			attributeSetterBiConsumers.put(
				"cpDefinitionLocalizationId",
				(BiConsumer<CPDefinitionLocalization, Long>)
					CPDefinitionLocalization::setCpDefinitionLocalizationId);
			attributeSetterBiConsumers.put(
				"companyId",
				(BiConsumer<CPDefinitionLocalization, Long>)
					CPDefinitionLocalization::setCompanyId);
			attributeSetterBiConsumers.put(
				"CPDefinitionId",
				(BiConsumer<CPDefinitionLocalization, Long>)
					CPDefinitionLocalization::setCPDefinitionId);
			attributeSetterBiConsumers.put(
				"languageId",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setLanguageId);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setName);
			attributeSetterBiConsumers.put(
				"shortDescription",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setShortDescription);
			attributeSetterBiConsumers.put(
				"description",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setDescription);
			attributeSetterBiConsumers.put(
				"metaTitle",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setMetaTitle);
			attributeSetterBiConsumers.put(
				"metaDescription",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setMetaDescription);
			attributeSetterBiConsumers.put(
				"metaKeywords",
				(BiConsumer<CPDefinitionLocalization, String>)
					CPDefinitionLocalization::setMetaKeywords);

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
	public long getCpDefinitionLocalizationId() {
		return _cpDefinitionLocalizationId;
	}

	@Override
	public void setCpDefinitionLocalizationId(long cpDefinitionLocalizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_cpDefinitionLocalizationId = cpDefinitionLocalizationId;
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

	@Override
	public long getCPDefinitionId() {
		return _CPDefinitionId;
	}

	@Override
	public void setCPDefinitionId(long CPDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPDefinitionId = CPDefinitionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCPDefinitionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
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
	public String getShortDescription() {
		if (_shortDescription == null) {
			return "";
		}
		else {
			return _shortDescription;
		}
	}

	@Override
	public void setShortDescription(String shortDescription) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_shortDescription = shortDescription;
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
	public String getMetaTitle() {
		if (_metaTitle == null) {
			return "";
		}
		else {
			return _metaTitle;
		}
	}

	@Override
	public void setMetaTitle(String metaTitle) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_metaTitle = metaTitle;
	}

	@Override
	public String getMetaDescription() {
		if (_metaDescription == null) {
			return "";
		}
		else {
			return _metaDescription;
		}
	}

	@Override
	public void setMetaDescription(String metaDescription) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_metaDescription = metaDescription;
	}

	@Override
	public String getMetaKeywords() {
		if (_metaKeywords == null) {
			return "";
		}
		else {
			return _metaKeywords;
		}
	}

	@Override
	public void setMetaKeywords(String metaKeywords) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_metaKeywords = metaKeywords;
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
			getCompanyId(), CPDefinitionLocalization.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CPDefinitionLocalization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPDefinitionLocalization>
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
		CPDefinitionLocalizationImpl cpDefinitionLocalizationImpl =
			new CPDefinitionLocalizationImpl();

		cpDefinitionLocalizationImpl.setMvccVersion(getMvccVersion());
		cpDefinitionLocalizationImpl.setCtCollectionId(getCtCollectionId());
		cpDefinitionLocalizationImpl.setCpDefinitionLocalizationId(
			getCpDefinitionLocalizationId());
		cpDefinitionLocalizationImpl.setCompanyId(getCompanyId());
		cpDefinitionLocalizationImpl.setCPDefinitionId(getCPDefinitionId());
		cpDefinitionLocalizationImpl.setLanguageId(getLanguageId());
		cpDefinitionLocalizationImpl.setName(getName());
		cpDefinitionLocalizationImpl.setShortDescription(getShortDescription());
		cpDefinitionLocalizationImpl.setDescription(getDescription());
		cpDefinitionLocalizationImpl.setMetaTitle(getMetaTitle());
		cpDefinitionLocalizationImpl.setMetaDescription(getMetaDescription());
		cpDefinitionLocalizationImpl.setMetaKeywords(getMetaKeywords());

		cpDefinitionLocalizationImpl.resetOriginalValues();

		return cpDefinitionLocalizationImpl;
	}

	@Override
	public CPDefinitionLocalization cloneWithOriginalValues() {
		CPDefinitionLocalizationImpl cpDefinitionLocalizationImpl =
			new CPDefinitionLocalizationImpl();

		cpDefinitionLocalizationImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		cpDefinitionLocalizationImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		cpDefinitionLocalizationImpl.setCpDefinitionLocalizationId(
			this.<Long>getColumnOriginalValue("cpDefinitionLocalizationId"));
		cpDefinitionLocalizationImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		cpDefinitionLocalizationImpl.setCPDefinitionId(
			this.<Long>getColumnOriginalValue("CPDefinitionId"));
		cpDefinitionLocalizationImpl.setLanguageId(
			this.<String>getColumnOriginalValue("languageId"));
		cpDefinitionLocalizationImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		cpDefinitionLocalizationImpl.setShortDescription(
			this.<String>getColumnOriginalValue("shortDescription"));
		cpDefinitionLocalizationImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		cpDefinitionLocalizationImpl.setMetaTitle(
			this.<String>getColumnOriginalValue("metaTitle"));
		cpDefinitionLocalizationImpl.setMetaDescription(
			this.<String>getColumnOriginalValue("metaDescription"));
		cpDefinitionLocalizationImpl.setMetaKeywords(
			this.<String>getColumnOriginalValue("metaKeywords"));

		return cpDefinitionLocalizationImpl;
	}

	@Override
	public int compareTo(CPDefinitionLocalization cpDefinitionLocalization) {
		long primaryKey = cpDefinitionLocalization.getPrimaryKey();

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

		if (!(object instanceof CPDefinitionLocalization)) {
			return false;
		}

		CPDefinitionLocalization cpDefinitionLocalization =
			(CPDefinitionLocalization)object;

		long primaryKey = cpDefinitionLocalization.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CPDefinitionLocalization> toCacheModel() {
		CPDefinitionLocalizationCacheModel cpDefinitionLocalizationCacheModel =
			new CPDefinitionLocalizationCacheModel();

		cpDefinitionLocalizationCacheModel.mvccVersion = getMvccVersion();

		cpDefinitionLocalizationCacheModel.ctCollectionId = getCtCollectionId();

		cpDefinitionLocalizationCacheModel.cpDefinitionLocalizationId =
			getCpDefinitionLocalizationId();

		cpDefinitionLocalizationCacheModel.companyId = getCompanyId();

		cpDefinitionLocalizationCacheModel.CPDefinitionId = getCPDefinitionId();

		cpDefinitionLocalizationCacheModel.languageId = getLanguageId();

		String languageId = cpDefinitionLocalizationCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			cpDefinitionLocalizationCacheModel.languageId = null;
		}

		cpDefinitionLocalizationCacheModel.name = getName();

		String name = cpDefinitionLocalizationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			cpDefinitionLocalizationCacheModel.name = null;
		}

		cpDefinitionLocalizationCacheModel.shortDescription =
			getShortDescription();

		String shortDescription =
			cpDefinitionLocalizationCacheModel.shortDescription;

		if ((shortDescription != null) && (shortDescription.length() == 0)) {
			cpDefinitionLocalizationCacheModel.shortDescription = null;
		}

		cpDefinitionLocalizationCacheModel.description = getDescription();

		String description = cpDefinitionLocalizationCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			cpDefinitionLocalizationCacheModel.description = null;
		}

		cpDefinitionLocalizationCacheModel.metaTitle = getMetaTitle();

		String metaTitle = cpDefinitionLocalizationCacheModel.metaTitle;

		if ((metaTitle != null) && (metaTitle.length() == 0)) {
			cpDefinitionLocalizationCacheModel.metaTitle = null;
		}

		cpDefinitionLocalizationCacheModel.metaDescription =
			getMetaDescription();

		String metaDescription =
			cpDefinitionLocalizationCacheModel.metaDescription;

		if ((metaDescription != null) && (metaDescription.length() == 0)) {
			cpDefinitionLocalizationCacheModel.metaDescription = null;
		}

		cpDefinitionLocalizationCacheModel.metaKeywords = getMetaKeywords();

		String metaKeywords = cpDefinitionLocalizationCacheModel.metaKeywords;

		if ((metaKeywords != null) && (metaKeywords.length() == 0)) {
			cpDefinitionLocalizationCacheModel.metaKeywords = null;
		}

		return cpDefinitionLocalizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPDefinitionLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPDefinitionLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPDefinitionLocalization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CPDefinitionLocalization)this);

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

		private static final Function
			<InvocationHandler, CPDefinitionLocalization>
				_escapedModelProxyProviderFunction =
					ProxyUtil.getProxyProviderFunction(
						CPDefinitionLocalization.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _cpDefinitionLocalizationId;
	private long _companyId;
	private long _CPDefinitionId;
	private String _languageId;
	private String _name;
	private String _shortDescription;
	private String _description;
	private String _metaTitle;
	private String _metaDescription;
	private String _metaKeywords;

	public <T> T getColumnValue(String columnName) {
		Function<CPDefinitionLocalization, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CPDefinitionLocalization)this);
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
			"cpDefinitionLocalizationId", _cpDefinitionLocalizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("CPDefinitionId", _CPDefinitionId);
		_columnOriginalValues.put("languageId", _languageId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("shortDescription", _shortDescription);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("metaTitle", _metaTitle);
		_columnOriginalValues.put("metaDescription", _metaDescription);
		_columnOriginalValues.put("metaKeywords", _metaKeywords);
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

		columnBitmasks.put("cpDefinitionLocalizationId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("CPDefinitionId", 16L);

		columnBitmasks.put("languageId", 32L);

		columnBitmasks.put("name", 64L);

		columnBitmasks.put("shortDescription", 128L);

		columnBitmasks.put("description", 256L);

		columnBitmasks.put("metaTitle", 512L);

		columnBitmasks.put("metaDescription", 1024L);

		columnBitmasks.put("metaKeywords", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CPDefinitionLocalization _escapedModel;

}