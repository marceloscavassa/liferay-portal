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

package com.liferay.object.internal.info.collection.provider;

import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Jürgen Kappler
 */
public class OneToManyObjectRelationshipRelatedInfoCollectionProvider
	extends BaseObjectRelationshipRelatedInfoCollectionProvider {

	public OneToManyObjectRelationshipRelatedInfoCollectionProvider(
			ObjectDefinition objectDefinition,
			ObjectRelationship objectRelationship,
			ObjectDefinitionLocalService objectDefinitionLocalService,
			ObjectEntryLocalService objectEntryLocalService)
		throws PortalException {

		super(
			objectDefinition, objectDefinitionLocalService,
			objectEntryLocalService, objectRelationship);
	}

	@Override
	protected InfoPage<ObjectEntry> getCollectionInfoPage(
			ObjectEntry objectEntry, Pagination pagination)
		throws PortalException {

		return InfoPage.of(
			objectEntryLocalService.getOneToManyObjectEntries(
				objectEntry.getGroupId(),
				objectRelationship.getObjectRelationshipId(),
				objectEntry.getObjectEntryId(), true, pagination.getStart(),
				pagination.getEnd()),
			pagination,
			objectEntryLocalService.getOneToManyObjectEntriesCount(
				objectEntry.getGroupId(),
				objectRelationship.getObjectRelationshipId(),
				objectEntry.getObjectEntryId(), true));
	}

}