/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KaleoProcessLink service. Represents a row in the &quot;KaleoProcessLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkImpl</code>.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLink
 * @generated
 */
@ProviderType
public interface KaleoProcessLinkModel
	extends BaseModel<KaleoProcessLink>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo process link model instance should use the {@link KaleoProcessLink} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo process link.
	 *
	 * @return the primary key of this kaleo process link
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo process link.
	 *
	 * @param primaryKey the primary key of this kaleo process link
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kaleo process link ID of this kaleo process link.
	 *
	 * @return the kaleo process link ID of this kaleo process link
	 */
	public long getKaleoProcessLinkId();

	/**
	 * Sets the kaleo process link ID of this kaleo process link.
	 *
	 * @param kaleoProcessLinkId the kaleo process link ID of this kaleo process link
	 */
	public void setKaleoProcessLinkId(long kaleoProcessLinkId);

	/**
	 * Returns the company ID of this kaleo process link.
	 *
	 * @return the company ID of this kaleo process link
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo process link.
	 *
	 * @param companyId the company ID of this kaleo process link
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the kaleo process ID of this kaleo process link.
	 *
	 * @return the kaleo process ID of this kaleo process link
	 */
	public long getKaleoProcessId();

	/**
	 * Sets the kaleo process ID of this kaleo process link.
	 *
	 * @param kaleoProcessId the kaleo process ID of this kaleo process link
	 */
	public void setKaleoProcessId(long kaleoProcessId);

	/**
	 * Returns the workflow task name of this kaleo process link.
	 *
	 * @return the workflow task name of this kaleo process link
	 */
	@AutoEscape
	public String getWorkflowTaskName();

	/**
	 * Sets the workflow task name of this kaleo process link.
	 *
	 * @param workflowTaskName the workflow task name of this kaleo process link
	 */
	public void setWorkflowTaskName(String workflowTaskName);

	/**
	 * Returns the ddm template ID of this kaleo process link.
	 *
	 * @return the ddm template ID of this kaleo process link
	 */
	public long getDDMTemplateId();

	/**
	 * Sets the ddm template ID of this kaleo process link.
	 *
	 * @param DDMTemplateId the ddm template ID of this kaleo process link
	 */
	public void setDDMTemplateId(long DDMTemplateId);

	@Override
	public KaleoProcessLink cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}