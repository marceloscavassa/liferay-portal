/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.task.repository;

import com.liferay.jethr0.entity.repository.BaseEntityRepository;
import com.liferay.jethr0.task.dalo.TaskRunDALO;
import com.liferay.jethr0.task.run.TaskRun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class TaskRunRepository extends BaseEntityRepository<TaskRun> {

	@Override
	public TaskRunDALO getEntityDALO() {
		return _taskRunDALO;
	}

	@Autowired
	private TaskRunDALO _taskRunDALO;

}