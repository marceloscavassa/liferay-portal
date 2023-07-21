/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.set.prototype.helper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public interface LayoutSetPrototypeHelper {

	public List<Layout> getDuplicatedFriendlyURLLayouts(Layout layout)
		throws PortalException;

	public List<Long> getDuplicatedFriendlyURLPlids(LayoutSet layoutSet);

	public List<Long> getDuplicatedFriendlyURLPlids(
			LayoutSetPrototype layoutSetPrototype)
		throws PortalException;

	public boolean hasDuplicatedFriendlyURLs(
			String layoutUuid, long groupId, boolean privateLayout,
			String friendlyURL)
		throws PortalException;

}