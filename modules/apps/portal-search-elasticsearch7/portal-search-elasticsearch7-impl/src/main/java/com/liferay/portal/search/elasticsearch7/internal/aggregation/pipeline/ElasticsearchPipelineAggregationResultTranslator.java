/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.aggregation.pipeline;

import com.liferay.portal.search.aggregation.AggregationResult;
import com.liferay.portal.search.aggregation.AggregationResults;
import com.liferay.portal.search.aggregation.pipeline.AvgBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.AvgBucketPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.BucketScriptPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.BucketScriptPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.BucketSelectorPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.BucketSortPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.CumulativeSumPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.CumulativeSumPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.DerivativePipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.DerivativePipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.ExtendedStatsBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.ExtendedStatsBucketPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.MaxBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.MaxBucketPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.MinBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.MinBucketPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.MovingFunctionPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.MovingFunctionPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.PercentilesBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.PercentilesBucketPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.PipelineAggregationResultTranslator;
import com.liferay.portal.search.aggregation.pipeline.SerialDiffPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.SerialDiffPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.StatsBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.StatsBucketPipelineAggregationResult;
import com.liferay.portal.search.aggregation.pipeline.SumBucketPipelineAggregation;
import com.liferay.portal.search.aggregation.pipeline.SumBucketPipelineAggregationResult;

import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.pipeline.BucketMetricValue;
import org.elasticsearch.search.aggregations.pipeline.Derivative;
import org.elasticsearch.search.aggregations.pipeline.ExtendedStatsBucket;
import org.elasticsearch.search.aggregations.pipeline.ParsedPercentilesBucket;
import org.elasticsearch.search.aggregations.pipeline.SimpleValue;
import org.elasticsearch.search.aggregations.pipeline.StatsBucket;

/**
 * @author Michael C. Han
 */
public class ElasticsearchPipelineAggregationResultTranslator
	implements PipelineAggregationResultTranslator {

	public ElasticsearchPipelineAggregationResultTranslator(
		Aggregation elasticsearchAggregation,
		AggregationResults aggregationResults) {

		_elasticsearchAggregation = elasticsearchAggregation;
		_aggregationResults = aggregationResults;
	}

	@Override
	public AvgBucketPipelineAggregationResult visit(
		AvgBucketPipelineAggregation avgAggregation) {

		SimpleValue simpleValue = (SimpleValue)_elasticsearchAggregation;

		return _aggregationResults.avgBucket(
			simpleValue.getName(), simpleValue.value());
	}

	@Override
	public BucketScriptPipelineAggregationResult visit(
		BucketScriptPipelineAggregation bucketScriptPipelineAggregation) {

		SimpleValue simpleValue = (SimpleValue)_elasticsearchAggregation;

		return _aggregationResults.bucketScript(
			simpleValue.getName(), simpleValue.value());
	}

	@Override
	public AggregationResult visit(
		BucketSelectorPipelineAggregation bucketSelectorPipelineAggregation) {

		throw new UnsupportedOperationException(
			"BucketSelector does not return a separate AggregationResult");
	}

	@Override
	public AggregationResult visit(
		BucketSortPipelineAggregation bucketSortPipelineAggregation) {

		throw new UnsupportedOperationException(
			"BucketSort does not return a separate AggregationResult");
	}

	@Override
	public CumulativeSumPipelineAggregationResult visit(
		CumulativeSumPipelineAggregation cumulativeSumPipelineAggregation) {

		SimpleValue simpleValue = (SimpleValue)_elasticsearchAggregation;

		return _aggregationResults.cumulativeSum(
			simpleValue.getName(), simpleValue.value());
	}

	@Override
	public DerivativePipelineAggregationResult visit(
		DerivativePipelineAggregation derivativePipelineAggregation) {

		Derivative derivative = (Derivative)_elasticsearchAggregation;

		if (derivativePipelineAggregation.getUnit() != null) {
			return _aggregationResults.derivative(
				derivative.getName(), derivative.normalizedValue());
		}

		return _aggregationResults.derivative(
			derivative.getName(), derivative.value());
	}

	@Override
	public ExtendedStatsBucketPipelineAggregationResult visit(
		ExtendedStatsBucketPipelineAggregation
			extendedStatsBucketPipelineAggregation) {

		ExtendedStatsBucket extendedStatsBucket =
			(ExtendedStatsBucket)_elasticsearchAggregation;

		return _aggregationResults.extendedStatsBucket(
			extendedStatsBucket.getName(), extendedStatsBucket.getAvg(),
			extendedStatsBucket.getCount(), extendedStatsBucket.getMin(),
			extendedStatsBucket.getMax(), extendedStatsBucket.getSum(),
			extendedStatsBucket.getSumOfSquares(),
			extendedStatsBucket.getVariance(),
			extendedStatsBucket.getStdDeviation());
	}

	@Override
	public MaxBucketPipelineAggregationResult visit(
		MaxBucketPipelineAggregation maxBucketPipelineAggregation) {

		BucketMetricValue bucketMetricValue =
			(BucketMetricValue)_elasticsearchAggregation;

		MaxBucketPipelineAggregationResult maxBucketPipelineAggregationResult =
			_aggregationResults.maxBucket(
				bucketMetricValue.getName(), bucketMetricValue.value());

		maxBucketPipelineAggregationResult.setKeys(bucketMetricValue.keys());

		return maxBucketPipelineAggregationResult;
	}

	@Override
	public MinBucketPipelineAggregationResult visit(
		MinBucketPipelineAggregation minBucketPipelineAggregation) {

		BucketMetricValue bucketMetricValue =
			(BucketMetricValue)_elasticsearchAggregation;

		MinBucketPipelineAggregationResult minBucketPipelineAggregationResult =
			_aggregationResults.minBucket(
				bucketMetricValue.getName(), bucketMetricValue.value());

		minBucketPipelineAggregationResult.setKeys(bucketMetricValue.keys());

		return minBucketPipelineAggregationResult;
	}

	@Override
	public MovingFunctionPipelineAggregationResult visit(
		MovingFunctionPipelineAggregation movingFunctionPipelineAggregation) {

		SimpleValue simpleValue = (SimpleValue)_elasticsearchAggregation;

		return _aggregationResults.movingFunction(
			simpleValue.getName(), simpleValue.value());
	}

	@Override
	public PercentilesBucketPipelineAggregationResult visit(
		PercentilesBucketPipelineAggregation
			percentilesBucketPipelineAggregation) {

		ParsedPercentilesBucket parsedPercentilesBucket =
			(ParsedPercentilesBucket)_elasticsearchAggregation;

		PercentilesBucketPipelineAggregationResult
			percentilesBucketPipelineAggregationResult =
				_aggregationResults.percentilesBucket(
					parsedPercentilesBucket.getName());

		parsedPercentilesBucket.forEach(
			percentile ->
				percentilesBucketPipelineAggregationResult.addPercentile(
					percentile.getPercent(), percentile.getValue()));

		return percentilesBucketPipelineAggregationResult;
	}

	@Override
	public SerialDiffPipelineAggregationResult visit(
		SerialDiffPipelineAggregation serialDiffPipelineAggregation) {

		SimpleValue simpleValue = (SimpleValue)_elasticsearchAggregation;

		return _aggregationResults.serialDiff(
			simpleValue.getName(), simpleValue.value());
	}

	@Override
	public StatsBucketPipelineAggregationResult visit(
		StatsBucketPipelineAggregation statsBucketPipelineAggregation) {

		StatsBucket statsBucket = (StatsBucket)_elasticsearchAggregation;

		return _aggregationResults.statsBucket(
			statsBucket.getName(), statsBucket.getAvg(), statsBucket.getCount(),
			statsBucket.getMin(), statsBucket.getMax(), statsBucket.getSum());
	}

	@Override
	public SumBucketPipelineAggregationResult visit(
		SumBucketPipelineAggregation sumBucketPipelineAggregation) {

		SimpleValue simpleValue = (SimpleValue)_elasticsearchAggregation;

		return _aggregationResults.sumBucket(
			simpleValue.getName(), simpleValue.value());
	}

	private final AggregationResults _aggregationResults;
	private final Aggregation _elasticsearchAggregation;

}