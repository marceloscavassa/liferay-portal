/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.project.comparator;

import com.liferay.jethr0.project.Project;
import com.liferay.jethr0.project.prioritizer.ProjectPrioritizer;

import java.util.Date;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class StartDateProjectComparator extends BaseProjectComparator {

	public StartDateProjectComparator(JSONObject jsonObject) {
		super(jsonObject);
	}

	public StartDateProjectComparator(
		ProjectPrioritizer projectPrioritizer, JSONObject jsonObject) {

		super(projectPrioritizer, jsonObject);
	}

	@Override
	public int compare(Project project1, Project project2) {
		Date startDate1 = project1.getStartDate();
		Date startDate2 = project2.getStartDate();

		if ((startDate1 == null) && (startDate2 == null)) {
			return 0;
		}

		if (startDate1 == null) {
			return 1;
		}

		if (startDate2 == null) {
			return -1;
		}

		return startDate1.compareTo(startDate2);
	}

}