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

package com.liferay.object.admin.rest.internal.dto.v1_0.util;

import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectState;
import com.liferay.object.model.ObjectStateFlow;
import com.liferay.object.model.ObjectStateTransition;
import com.liferay.object.service.ObjectFieldLocalServiceUtil;
import com.liferay.object.service.ObjectStateFlowLocalServiceUtil;
import com.liferay.object.service.ObjectStateLocalServiceUtil;
import com.liferay.object.service.ObjectStateTransitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Feliphe Marinho
 */
public class ObjectStateFlowUtil {

	public static ObjectStateFlow toObjectStateFlow(
			long objectFieldId,
			com.liferay.object.admin.rest.dto.v1_0.ObjectStateFlow
				objectStateFlow)
		throws PortalException {

		if (objectStateFlow == null) {
			return null;
		}

		ObjectStateFlow serviceBuilderObjectStateFlow =
			ObjectStateFlowLocalServiceUtil.createObjectStateFlow(0L);

		serviceBuilderObjectStateFlow.setObjectStateFlowId(
			objectStateFlow.getId());
		serviceBuilderObjectStateFlow.setObjectFieldId(objectFieldId);

		ObjectField objectField = ObjectFieldLocalServiceUtil.getObjectField(
			objectFieldId);

		serviceBuilderObjectStateFlow.setObjectStates(
			TransformUtil.transformToList(
				objectStateFlow.getObjectStates(),
				objectState -> _toObjectState(
					objectField.getListTypeDefinitionId(), objectState,
					objectStateFlow.getId())));

		return serviceBuilderObjectStateFlow;
	}

	private static ObjectState _toObjectState(
			long listTypeDefinitionId,
			com.liferay.object.admin.rest.dto.v1_0.ObjectState objectState,
			long objectStateFlowId)
		throws PortalException {

		ObjectState serviceBuilderObjectState =
			ObjectStateLocalServiceUtil.createObjectState(0L);

		serviceBuilderObjectState.setObjectStateId(objectState.getId());

		ListTypeEntry listTypeEntry =
			ListTypeEntryLocalServiceUtil.getListTypeEntry(
				listTypeDefinitionId, objectState.getKey());

		serviceBuilderObjectState.setListTypeEntryId(
			listTypeEntry.getListTypeEntryId());

		serviceBuilderObjectState.setObjectStateFlowId(objectStateFlowId);
		serviceBuilderObjectState.setObjectStateTransitions(
			TransformUtil.transformToList(
				objectState.getNextObjectStates(),
				nextObjectStateJSON -> _toObjectStateTransition(
					listTypeDefinitionId, nextObjectStateJSON,
					objectStateFlowId, objectState.getId())));

		return serviceBuilderObjectState;
	}

	private static ObjectStateTransition _toObjectStateTransition(
			long listTypeDefinitionId, String nextObjectStateJSON,
			long objectStateFlowId, long sourceObjectStateId)
		throws PortalException {

		ObjectStateTransition objectStateTransition =
			ObjectStateTransitionLocalServiceUtil.createObjectStateTransition(
				0L);

		objectStateTransition.setObjectStateFlowId(objectStateFlowId);
		objectStateTransition.setSourceObjectStateId(sourceObjectStateId);

		ListTypeEntry listTypeEntry =
			ListTypeEntryLocalServiceUtil.fetchListTypeEntry(
				listTypeDefinitionId, nextObjectStateJSON);

		ObjectState targetObjectState =
			ObjectStateLocalServiceUtil.getObjectStateFlowObjectState(
				listTypeEntry.getListTypeEntryId(), objectStateFlowId);

		objectStateTransition.setTargetObjectStateId(
			targetObjectState.getObjectStateId());

		return objectStateTransition;
	}

}