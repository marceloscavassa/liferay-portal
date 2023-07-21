/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author André Miranda
 * @author Sarai Díaz
 * @author David Arques
 */
public class DXPVariant {

	public Integer getChanges() {
		return _changes;
	}

	@JsonProperty("dxpVariantId")
	public String getDXPVariantId() {
		return _dxpVariantId;
	}

	@JsonProperty("dxpVariantName")
	public String getDXPVariantName() {
		return _dxpVariantName;
	}

	public Double getTrafficSplit() {
		return _trafficSplit;
	}

	public Boolean isControl() {
		return _control;
	}

	public void setChanges(Integer changes) {
		_changes = changes;
	}

	public void setControl(Boolean control) {
		_control = control;
	}

	public void setDXPVariantId(String dxpVariantId) {
		_dxpVariantId = dxpVariantId;
	}

	public void setDXPVariantName(String dxpVariantName) {
		_dxpVariantName = dxpVariantName;
	}

	public void setTrafficSplit(Double trafficSplit) {
		_trafficSplit = trafficSplit;
	}

	private Integer _changes;
	private Boolean _control;
	private String _dxpVariantId;
	private String _dxpVariantName;
	private Double _trafficSplit;

}