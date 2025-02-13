/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.lang;

/**
 * @author     Preston Crary
 * @deprecated As of Cavanaugh (7.4.x), replaced with {@link
 *             com.liferay.petra.lang.SafeCloseable}
 */
@Deprecated
@FunctionalInterface
public interface SafeClosable extends AutoCloseable {

	@Override
	public void close();

}