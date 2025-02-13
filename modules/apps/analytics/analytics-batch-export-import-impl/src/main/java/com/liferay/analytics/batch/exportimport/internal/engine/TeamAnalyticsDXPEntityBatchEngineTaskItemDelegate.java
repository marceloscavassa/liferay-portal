/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.batch.exportimport.internal.engine;

import com.liferay.analytics.batch.exportimport.internal.dto.v1_0.converter.constants.DTOConverterConstants;
import com.liferay.analytics.dxp.entity.rest.dto.v1_0.DXPEntity;
import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.TeamLocalService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcos Martins
 */
@Component(
	property = "batch.engine.task.item.delegate.name=team-analytics-dxp-entities",
	service = BatchEngineTaskItemDelegate.class
)
public class TeamAnalyticsDXPEntityBatchEngineTaskItemDelegate
	extends BaseAnalyticsDXPEntityBatchEngineTaskItemDelegate<DXPEntity> {

	@Override
	public Page<DXPEntity> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		List<DXPEntity> dxpEntities = new ArrayList<>();

		DynamicQuery dynamicQuery = buildDynamicQuery(
			contextCompany.getCompanyId(), _teamLocalService.dynamicQuery(),
			filter);

		List<Team> teams = _teamLocalService.dynamicQuery(
			dynamicQuery, pagination.getStartPosition(),
			pagination.getEndPosition());

		for (Team team : teams) {
			dxpEntities.add(_dxpEntityDTOConverter.toDTO(team));
		}

		return Page.of(
			dxpEntities, pagination,
			_teamLocalService.dynamicQueryCount(dynamicQuery));
	}

	@Reference(target = DTOConverterConstants.DXP_ENTITY_DTO_CONVERTER)
	private DTOConverter<BaseModel<?>, DXPEntity> _dxpEntityDTOConverter;

	@Reference
	private TeamLocalService _teamLocalService;

}