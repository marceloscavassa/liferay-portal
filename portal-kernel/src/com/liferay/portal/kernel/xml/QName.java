/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.xml;

import java.io.Serializable;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface QName extends Serializable {

	public String getLocalPart();

	public String getName();

	public Namespace getNamespace();

	public String getNamespacePrefix();

	public String getNamespaceURI();

	public String getQualifiedName();

}