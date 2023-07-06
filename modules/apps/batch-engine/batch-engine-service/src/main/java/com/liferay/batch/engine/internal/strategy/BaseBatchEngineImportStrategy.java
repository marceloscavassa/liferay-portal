/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.batch.engine.internal.strategy;

import com.liferay.batch.engine.action.ImportTaskPostAction;
import com.liferay.batch.engine.action.ImportTaskPreAction;
import com.liferay.batch.engine.model.BatchEngineImportTask;
import com.liferay.batch.engine.service.BatchEngineImportTaskErrorLocalServiceUtil;
import com.liferay.batch.engine.strategy.BatchEngineImportStrategy;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;

import java.util.Collection;
import java.util.List;

/**
 * @author Matija Petanjek
 * @author Brian Wing Shun Chan
 */
public abstract class BaseBatchEngineImportStrategy
	implements BatchEngineImportStrategy {

	public BaseBatchEngineImportStrategy(
		BatchEngineImportTask batchEngineImportTask,
		List<ImportTaskPostAction> importTaskPostActions,
		List<ImportTaskPreAction> importTaskPreActions) {

		this.batchEngineImportTask = batchEngineImportTask;
		this.importTaskPostActions = importTaskPostActions;
		this.importTaskPreActions = importTaskPreActions;
	}

	@Override
	public <T> void apply(
			Collection<T> collection,
			UnsafeFunction<T, T, Exception> unsafeFunction)
		throws Exception {

		for (T item : collection) {
			importItem(
				item,
				element -> {
					for (ImportTaskPreAction importTaskPreAction :
							importTaskPreActions) {

						importTaskPreAction.run(batchEngineImportTask, element);
					}

					T persistedItem = unsafeFunction.apply(element);

					if (persistedItem == null) {
						return null;
					}

					for (ImportTaskPostAction importTaskPostAction :
							importTaskPostActions) {

						importTaskPostAction.run(
							batchEngineImportTask, element, persistedItem);
					}

					return persistedItem;
				});
		}
	}

	protected void addBatchEngineImportTaskError(
		long companyId, long userId, long batchEngineImportTaskId, String item,
		int itemIndex, String message) {

		try {
			TransactionInvokerUtil.invoke(
				_transactionConfig,
				() -> {
					BatchEngineImportTaskErrorLocalServiceUtil.
						addBatchEngineImportTaskError(
							companyId, userId, batchEngineImportTaskId, item,
							itemIndex, message);

					return null;
				});
		}
		catch (Throwable throwable) {
			throw new RuntimeException(throwable);
		}
	}

	protected abstract <T> T importItem(
			T item, UnsafeFunction<T, T, Exception> unsafeFunction)
		throws Exception;

	protected final BatchEngineImportTask batchEngineImportTask;
	protected final List<ImportTaskPostAction> importTaskPostActions;
	protected final List<ImportTaskPreAction> importTaskPreActions;

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRES_NEW, new Class<?>[] {Exception.class});

}