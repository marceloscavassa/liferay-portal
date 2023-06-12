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

package com.liferay.jethr0.event.handler;

import com.liferay.jethr0.build.Build;
import com.liferay.jethr0.build.parameter.BuildParameter;
import com.liferay.jethr0.build.queue.BuildQueue;
import com.liferay.jethr0.build.repository.BuildParameterRepository;
import com.liferay.jethr0.build.repository.BuildRepository;
import com.liferay.jethr0.jenkins.JenkinsQueue;
import com.liferay.jethr0.project.Project;
import com.liferay.jethr0.project.repository.ProjectRepository;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public class CreateBuildEventHandler extends BaseEventHandler {

	@Override
	public String process(String body) throws Exception {
		JSONObject bodyJSONObject = new JSONObject(body);

		Project project = _getProject(bodyJSONObject);

		EventHandlerHelper eventHandlerHelper = getEventHandlerHelper();

		BuildRepository buildRepository =
			eventHandlerHelper.getBuildRepository();

		JSONObject buildJSONObject = _getBuildJSONObject(
			bodyJSONObject.optJSONObject("build"));

		Build build = buildRepository.add(project, buildJSONObject);

		JSONObject parametersJSONObject = buildJSONObject.optJSONObject(
			"parameters");

		if ((parametersJSONObject != null) && !parametersJSONObject.isEmpty()) {
			BuildParameterRepository buildParameterRepository =
				eventHandlerHelper.getBuildParameterRepository();

			for (String key : parametersJSONObject.keySet()) {
				BuildParameter buildParameter = buildParameterRepository.add(
					build, key, parametersJSONObject.getString(key));

				build.addBuildParameter(buildParameter);

				buildParameter.setBuild(build);
			}
		}

		if (project.getState() == Project.State.COMPLETED) {
			project.setState(Project.State.QUEUED);

			ProjectRepository projectRepository =
				eventHandlerHelper.getProjectRepository();

			projectRepository.update(project);

			BuildQueue buildQueue = eventHandlerHelper.getBuildQueue();

			buildQueue.addProject(project);

			JenkinsQueue jenkinsQueue = eventHandlerHelper.getJenkinsQueue();

			jenkinsQueue.invoke();
		}

		return project.toString();
	}

	protected CreateBuildEventHandler(EventHandlerHelper eventHandlerHelper) {
		super(eventHandlerHelper);
	}

	private JSONObject _getBuildJSONObject(JSONObject buildJSONObject)
		throws Exception {

		if (buildJSONObject == null) {
			throw new Exception("Invalid build JSON object");
		}

		String buildName = buildJSONObject.optString("buildName");

		if (buildName.isEmpty()) {
			throw new Exception("Invalid build 'buildName'");
		}

		String jobName = buildJSONObject.optString("jobName");

		if (jobName.isEmpty()) {
			throw new Exception("Invalid build 'jobName'");
		}

		buildJSONObject.put(
			"buildName", buildName
		).put(
			"jobName", jobName
		).put(
			"parameters", buildJSONObject.optJSONObject("parameters")
		).put(
			"state", Build.State.OPENED.getJSONObject()
		);

		return buildJSONObject;
	}

	private Project _getProject(JSONObject bodyJSONObject) throws Exception {
		JSONObject projectJSONObject = bodyJSONObject.optJSONObject("project");

		if (projectJSONObject == null) {
			throw new Exception("Missing 'project' JSON object");
		}

		if (!projectJSONObject.has("id")) {
			throw new Exception("Missing project 'id'");
		}

		EventHandlerHelper eventHandlerHelper = getEventHandlerHelper();

		ProjectRepository projectRepository =
			eventHandlerHelper.getProjectRepository();

		Project project = projectRepository.getById(
			projectJSONObject.getLong("id"));

		BuildRepository buildRepository =
			eventHandlerHelper.getBuildRepository();

		buildRepository.getAll(project);

		return project;
	}

}