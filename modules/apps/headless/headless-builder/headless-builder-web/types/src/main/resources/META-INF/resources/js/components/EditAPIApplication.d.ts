/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
/// <reference types="react" />

import '../../css/main.scss';
interface EditAPIApplicationProps {
	apiURLPaths: APIURLPaths;
	baseURL: string;
	portletId: string;
}
export default function EditAPIApplication({
	apiURLPaths,
	baseURL,
	portletId,
}: EditAPIApplicationProps): JSX.Element | null;
export {};
