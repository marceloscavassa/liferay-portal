/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.util;

import com.liferay.asset.kernel.model.AssetRendererFactory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Alejandro Tardín
 */
@ProviderType
public interface AssetRendererFactoryWrapper<T>
	extends AssetRendererFactory<T> {

	public Class<? extends AssetRendererFactory> getWrappedClass();

}