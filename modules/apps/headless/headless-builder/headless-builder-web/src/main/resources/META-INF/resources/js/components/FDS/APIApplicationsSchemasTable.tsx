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

import {FrontendDataSet} from '@liferay/frontend-data-set-web';
import React from 'react';

import {getAPIApplicationsSchemasFDSProps} from './fdsUtils/schemasFDSProps.';

interface APIApplicationsTableProps {
	apiURLPaths: APIURLPaths;
	portletId: string;
	readOnly: boolean;
}

export default function APIApplicationsSchemasTable({
	apiURLPaths,
	portletId,
}: APIApplicationsTableProps) {
	const createAPIApplicationSchema = {
		label: Liferay.Language.get('add-new-schema'),
	};

	function onActionDropdownItemClick({
		action,
		itemData,
	}: FDSItem<APIApplicationEndpointItem>) {
		if (action.id === 'editAPIApplicationSchema') {
			return void itemData;
		}
	}

	return (
		<FrontendDataSet
			{...getAPIApplicationsSchemasFDSProps(
				apiURLPaths.schemas,
				portletId
			)}
			creationMenu={{
				primaryItems: [createAPIApplicationSchema],
			}}
			onActionDropdownItemClick={onActionDropdownItemClick}
		/>
	);
}
