/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.stats;

import com.liferay.portal.search.stats.StatsRequestBuilder;
import com.liferay.portal.search.stats.StatsRequestBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 */
@Component(service = StatsRequestBuilderFactory.class)
public class StatsRequestBuilderFactoryImpl
	implements StatsRequestBuilderFactory {

	@Override
	public StatsRequestBuilder getStatsRequestBuilder() {
		return new StatsRequestBuilderImpl();
	}

}