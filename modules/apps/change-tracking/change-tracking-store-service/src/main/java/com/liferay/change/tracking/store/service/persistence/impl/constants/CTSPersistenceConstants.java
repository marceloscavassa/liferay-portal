/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.store.service.persistence.impl.constants;

/**
 * @author Shuyang Zhou
 * @generated
 */
public class CTSPersistenceConstants {

	public static final String BUNDLE_SYMBOLIC_NAME =
		"com.liferay.change.tracking.store.service";

	public static final String ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER =
		"(origin.bundle.symbolic.name=" + BUNDLE_SYMBOLIC_NAME + ")";

	public static final String SERVICE_CONFIGURATION_FILTER =
		"(&" + ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER + "(name=service))";

}