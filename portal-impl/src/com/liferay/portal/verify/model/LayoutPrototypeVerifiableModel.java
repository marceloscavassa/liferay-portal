/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.model;

import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.verify.model.VerifiableAuditedModel;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.portal.kernel.verify.model.VerifiableUUIDModel;

/**
 * @author Miguel Pastor
 */
public class LayoutPrototypeVerifiableModel
	implements VerifiableAuditedModel, VerifiableResourcedModel,
			   VerifiableUUIDModel {

	@Override
	public String getJoinByTableName() {
		return null;
	}

	@Override
	public String getModelName() {
		return LayoutPrototype.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "layoutPrototypeId";
	}

	@Override
	public String getRelatedModelName() {
		return null;
	}

	@Override
	public String getRelatedPKColumnName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "LayoutPrototype";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

	@Override
	public boolean isAnonymousUserAllowed() {
		return false;
	}

	@Override
	public boolean isUpdateDates() {
		return true;
	}

}