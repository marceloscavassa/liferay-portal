/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.gitbranch.repository;

import com.liferay.jethr0.entity.repository.BaseEntityRepository;
import com.liferay.jethr0.gitbranch.GitBranch;
import com.liferay.jethr0.gitbranch.dalo.GitBranchDALO;
import com.liferay.jethr0.project.Project;
import com.liferay.jethr0.project.dalo.ProjectsToGitBranchesDALO;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class GitBranchRepository extends BaseEntityRepository<GitBranch> {

	public Set<GitBranch> getAll(Project project) {
		Set<GitBranch> projectGitBranches = new HashSet<>();

		Set<Long> gitBranchIds = _projectsToGitBranchesDALO.getChildEntityIds(
			project);

		for (GitBranch gitBranch : getAll()) {
			if (!gitBranchIds.contains(gitBranch.getId())) {
				continue;
			}

			gitBranch.addProject(project);

			project.addGitBranch(gitBranch);

			projectGitBranches.add(gitBranch);
		}

		return projectGitBranches;
	}

	@Override
	public GitBranchDALO getEntityDALO() {
		return _gitBranchDALO;
	}

	@Autowired
	private GitBranchDALO _gitBranchDALO;

	@Autowired
	private ProjectsToGitBranchesDALO _projectsToGitBranchesDALO;

}