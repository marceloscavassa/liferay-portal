/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public interface BooleanClause<T> extends Serializable {

	public BooleanClauseOccur getBooleanClauseOccur();

	public T getClause();

}