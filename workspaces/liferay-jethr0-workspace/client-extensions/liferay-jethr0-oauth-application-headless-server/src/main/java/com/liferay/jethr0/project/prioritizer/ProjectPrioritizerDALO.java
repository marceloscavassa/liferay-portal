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

package com.liferay.jethr0.project.prioritizer;

import com.liferay.jethr0.object.ObjectDALO;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class ProjectPrioritizerDALO extends ObjectDALO {

	public ProjectPrioritizer createProjectPrioritizer(String name) {
		JSONObject requestJSONObject = new JSONObject();

		requestJSONObject.put("name", name);

		JSONObject responseJSONObject = create(requestJSONObject);

		return _newProjectPrioritizer(responseJSONObject);
	}

	public void deleteProjectPrioritizer(
		ProjectPrioritizer projectPrioritizer) {

		if (projectPrioritizer == null) {
			return;
		}

		delete(projectPrioritizer.getID());
	}

	public List<ProjectPrioritizer> retrieveProjectPrioritizers() {
		List<ProjectPrioritizer> projectPrioritizers = new ArrayList<>();

		for (JSONObject jsonObject : retrieve()) {
			projectPrioritizers.add(_newProjectPrioritizer(jsonObject));
		}

		return projectPrioritizers;
	}

	public ProjectPrioritizer updateProjectPrioritizer(
		ProjectPrioritizer projectPrioritizer) {

		return _newProjectPrioritizer(
			update(projectPrioritizer.getJSONObject()));
	}

	@Override
	protected String getObjectName() {
		return "Project Prioritizer";
	}

	private ProjectPrioritizer _newProjectPrioritizer(JSONObject jsonObject) {
		return new DefaultProjectPrioritizer(jsonObject);
	}

}