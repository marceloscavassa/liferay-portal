/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jethr0.gitbranch.dalo;

import com.liferay.jethr0.entity.dalo.BaseEntityDALO;
import com.liferay.jethr0.entity.factory.EntityFactory;
import com.liferay.jethr0.gitbranch.GitBranch;
import com.liferay.jethr0.gitbranch.GitBranchFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class GitBranchDALO extends BaseEntityDALO<GitBranch> {

	@Override
	protected EntityFactory<GitBranch> getEntityFactory() {
		return _gitBranchFactory;
	}

	@Autowired
	private GitBranchFactory _gitBranchFactory;

}