/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the OAuth2ApplicationScopeAliases service. Represents a row in the &quot;OAuth2ApplicationScopeAliases&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.oauth2.provider.model.impl.OAuth2ApplicationScopeAliasesModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.oauth2.provider.model.impl.OAuth2ApplicationScopeAliasesImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuth2ApplicationScopeAliases
 * @generated
 */
@ProviderType
public interface OAuth2ApplicationScopeAliasesModel
	extends BaseModel<OAuth2ApplicationScopeAliases>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a o auth2 application scope aliases model instance should use the {@link OAuth2ApplicationScopeAliases} interface instead.
	 */

	/**
	 * Returns the primary key of this o auth2 application scope aliases.
	 *
	 * @return the primary key of this o auth2 application scope aliases
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this o auth2 application scope aliases.
	 *
	 * @param primaryKey the primary key of this o auth2 application scope aliases
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the o auth2 application scope aliases ID of this o auth2 application scope aliases.
	 *
	 * @return the o auth2 application scope aliases ID of this o auth2 application scope aliases
	 */
	public long getOAuth2ApplicationScopeAliasesId();

	/**
	 * Sets the o auth2 application scope aliases ID of this o auth2 application scope aliases.
	 *
	 * @param oAuth2ApplicationScopeAliasesId the o auth2 application scope aliases ID of this o auth2 application scope aliases
	 */
	public void setOAuth2ApplicationScopeAliasesId(
		long oAuth2ApplicationScopeAliasesId);

	/**
	 * Returns the company ID of this o auth2 application scope aliases.
	 *
	 * @return the company ID of this o auth2 application scope aliases
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this o auth2 application scope aliases.
	 *
	 * @param companyId the company ID of this o auth2 application scope aliases
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this o auth2 application scope aliases.
	 *
	 * @return the user ID of this o auth2 application scope aliases
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this o auth2 application scope aliases.
	 *
	 * @param userId the user ID of this o auth2 application scope aliases
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this o auth2 application scope aliases.
	 *
	 * @return the user uuid of this o auth2 application scope aliases
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this o auth2 application scope aliases.
	 *
	 * @param userUuid the user uuid of this o auth2 application scope aliases
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this o auth2 application scope aliases.
	 *
	 * @return the user name of this o auth2 application scope aliases
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this o auth2 application scope aliases.
	 *
	 * @param userName the user name of this o auth2 application scope aliases
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this o auth2 application scope aliases.
	 *
	 * @return the create date of this o auth2 application scope aliases
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this o auth2 application scope aliases.
	 *
	 * @param createDate the create date of this o auth2 application scope aliases
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the o auth2 application ID of this o auth2 application scope aliases.
	 *
	 * @return the o auth2 application ID of this o auth2 application scope aliases
	 */
	public long getOAuth2ApplicationId();

	/**
	 * Sets the o auth2 application ID of this o auth2 application scope aliases.
	 *
	 * @param oAuth2ApplicationId the o auth2 application ID of this o auth2 application scope aliases
	 */
	public void setOAuth2ApplicationId(long oAuth2ApplicationId);

	@Override
	public OAuth2ApplicationScopeAliases cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}