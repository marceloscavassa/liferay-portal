/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

/**
 * @author Eduardo García
 */
public class MembershipPolicyErrorTag extends IncludeTag {

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/ui/membership_policy_error/page.jsp";

}