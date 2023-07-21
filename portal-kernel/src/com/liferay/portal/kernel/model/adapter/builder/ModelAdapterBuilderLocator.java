/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model.adapter.builder;

/**
 * @author Carlos Sierra Andrés
 */
public interface ModelAdapterBuilderLocator {

	public <T, V> ModelAdapterBuilder<T, V> locate(
		Class<T> adapteeModelClass, Class<V> adaptedModelClass);

}