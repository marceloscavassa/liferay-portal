/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.web.internal.display.context.helper;

import com.liferay.dynamic.data.mapping.constants.DDMActionKeys;
import com.liferay.dynamic.data.mapping.form.web.internal.security.permission.resource.DDMFormInstancePermission;
import com.liferay.dynamic.data.mapping.form.web.internal.security.permission.resource.DDMFormPermission;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.Objects;

/**
 * @author Rafael Praxedes
 */
public class FormInstancePermissionCheckerHelper {

	public FormInstancePermissionCheckerHelper(
		DDMFormAdminRequestHelper formAdminRequestHelper) {

		_formAdminRequestHelper = formAdminRequestHelper;
	}

	public boolean isShowAddButton() {
		return DDMFormPermission.contains(
			_formAdminRequestHelper.getPermissionChecker(),
			_formAdminRequestHelper.getScopeGroupId(),
			DDMActionKeys.ADD_FORM_INSTANCE);
	}

	public boolean isShowDeleteIcon(DDMFormInstance formInstance)
		throws PortalException {

		return DDMFormInstancePermission.contains(
			_formAdminRequestHelper.getPermissionChecker(), formInstance,
			ActionKeys.DELETE);
	}

	public boolean isShowDuplicateIcon() {
		return isShowAddButton();
	}

	public boolean isShowEditIcon(DDMFormInstance formInstance)
		throws PortalException {

		return DDMFormInstancePermission.contains(
			_formAdminRequestHelper.getPermissionChecker(), formInstance,
			ActionKeys.UPDATE);
	}

	public boolean isShowExportIcon(DDMFormInstance formInstance)
		throws PortalException {

		return DDMFormInstancePermission.contains(
			_formAdminRequestHelper.getPermissionChecker(), formInstance,
			ActionKeys.VIEW);
	}

	public boolean isShowPermissionsIcon(DDMFormInstance formInstance)
		throws PortalException {

		return DDMFormInstancePermission.contains(
			_formAdminRequestHelper.getPermissionChecker(), formInstance,
			ActionKeys.PERMISSIONS);
	}

	public boolean isShowShareIcon(DDMFormInstance formInstance)
		throws PortalException {

		return DDMFormInstancePermission.contains(
			_formAdminRequestHelper.getPermissionChecker(), formInstance,
			ActionKeys.VIEW);
	}

	public boolean isShowViewEntriesIcon(DDMFormInstance formInstance)
		throws PortalException {

		if (!((formInstance != null) &&
			  Objects.equals(formInstance.getStorageType(), "object")) &&
			DDMFormInstancePermission.contains(
				_formAdminRequestHelper.getPermissionChecker(), formInstance,
				ActionKeys.VIEW)) {

			return true;
		}

		return false;
	}

	private final DDMFormAdminRequestHelper _formAdminRequestHelper;

}