/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.machine.learning.internal.dispatch.executor;

import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.executor.DispatchTaskStatus;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.SkuForecast;
import com.liferay.headless.commerce.machine.learning.internal.batch.engine.v1_0.SkuForecastBatchEngineTaskItemDelegate;
import com.liferay.portal.kernel.util.HashMapBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	property = {
		"dispatch.task.executor.name=" + AnalyticsDownloadSkuCommerceMLForecastDispatchTaskExecutor.KEY,
		"dispatch.task.executor.type=" + AnalyticsDownloadSkuCommerceMLForecastDispatchTaskExecutor.KEY
	},
	service = DispatchTaskExecutor.class
)
public class AnalyticsDownloadSkuCommerceMLForecastDispatchTaskExecutor
	extends BaseDispatchTaskExecutor {

	public static final String KEY =
		"analytics-download-sku-commerce-ml-forecast";

	@Override
	public void doExecute(
			DispatchTrigger dispatchTrigger,
			DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws Exception {

		DispatchLog dispatchLog =
			dispatchLogLocalService.fetchLatestDispatchLog(
				dispatchTrigger.getDispatchTriggerId(),
				DispatchTaskStatus.IN_PROGRESS);

		analyticsBatchExportImportManager.importFromAnalyticsCloud(
			SkuForecastBatchEngineTaskItemDelegate.KEY,
			dispatchTrigger.getCompanyId(),
			HashMapBuilder.put(
				"actual", "actual"
			).put(
				"forecast", "forecast"
			).put(
				"forecastLowerBound", "forecastLowerBound"
			).put(
				"forecastUpperBound", "forecastUpperBound"
			).put(
				"sku", "sku"
			).put(
				"timestamp", "timestamp"
			).build(),
			message -> updateDispatchLog(
				dispatchLog.getDispatchLogId(), dispatchTaskExecutorOutput,
				message),
			getLatestSuccessfulDispatchLogEndDate(
				dispatchTrigger.getDispatchTriggerId()),
			SkuForecast.class.getName(), dispatchTrigger.getUserId());
	}

	@Override
	public String getName() {
		return KEY;
	}

}