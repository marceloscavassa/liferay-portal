/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.aggregation.pipeline;

import com.liferay.portal.search.aggregation.pipeline.CumulativeSumPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationVisitor;

/**
 * @author Michael C. Han
 */
public class CumulativeSumPipelineAggregationImpl
	extends BasePipelineAggregation
	implements CumulativeSumPipelineAggregation {

	public CumulativeSumPipelineAggregationImpl(
		String name, String bucketsPath) {

		super(name);

		_bucketsPath = bucketsPath;
	}

	@Override
	public <T> T accept(
		PipelineAggregationVisitor<T> pipelineAggregationVisitor) {

		return pipelineAggregationVisitor.visit(this);
	}

	@Override
	public String getBucketsPath() {
		return _bucketsPath;
	}

	@Override
	public String getFormat() {
		return _format;
	}

	@Override
	public void setFormat(String format) {
		_format = format;
	}

	private final String _bucketsPath;
	private String _format;

}