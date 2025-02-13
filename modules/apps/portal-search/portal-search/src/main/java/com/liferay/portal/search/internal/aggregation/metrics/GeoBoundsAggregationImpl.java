/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.aggregation.metrics;

import com.liferay.portal.search.aggregation.AggregationVisitor;
import com.liferay.portal.search.aggregation.metrics.GeoBoundsAggregation;
import com.liferay.portal.search.internal.aggregation.BaseFieldAggregation;

/**
 * @author Michael C. Han
 */
public class GeoBoundsAggregationImpl
	extends BaseFieldAggregation implements GeoBoundsAggregation {

	public GeoBoundsAggregationImpl(String name, String field) {
		super(name, field);
	}

	@Override
	public <T> T accept(AggregationVisitor<T> aggregationVisitor) {
		return aggregationVisitor.visit(this);
	}

	@Override
	public Boolean getWrapLongitude() {
		return _wrapLongitude;
	}

	@Override
	public void setWrapLongitude(Boolean wrapLongitude) {
		_wrapLongitude = wrapLongitude;
	}

	private Boolean _wrapLongitude;

}