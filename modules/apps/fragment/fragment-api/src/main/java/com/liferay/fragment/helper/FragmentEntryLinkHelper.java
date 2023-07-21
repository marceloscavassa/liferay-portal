/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.helper;

import com.liferay.fragment.model.FragmentEntryLink;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public interface FragmentEntryLinkHelper {

	public String getFragmentEntryName(
		FragmentEntryLink fragmentEntryLink, Locale locale);

}