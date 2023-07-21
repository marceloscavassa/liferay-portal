/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import APIApplicationsTable from './FDS/APIApplicationsTable';

import '../../css/main.scss';

interface APIApplicationsProps {
	apiURLPaths: APIURLPaths;
	baseURL: string;
	editURL: string;
	portletId: string;
}

export default function ViewAPIApplications({
	apiURLPaths,
	baseURL,
	editURL,
	portletId,
}: APIApplicationsProps) {
	return (
		<APIApplicationsTable
			apiURLPaths={apiURLPaths}
			baseURL={baseURL}
			editURL={editURL}
			portletId={portletId}
			readOnly={false}
		/>
	);
}
