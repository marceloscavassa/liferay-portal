/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.aggregation.pipeline;

import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationVisitor;
import com.liferay.portal.search.aggregation.pipeline.StatsBucketPipelineAggregation;

/**
 * @author Michael C. Han
 */
public class StatsBucketPipelineAggregationImpl
	extends BaseBucketMetricsPipelineAggregationImpl
	implements StatsBucketPipelineAggregation {

	public StatsBucketPipelineAggregationImpl(String name, String bucketsPath) {
		super(name, bucketsPath);
	}

	@Override
	public <T> T accept(
		PipelineAggregationVisitor<T> pipelineAggregationVisitor) {

		return pipelineAggregationVisitor.visit(this);
	}

}