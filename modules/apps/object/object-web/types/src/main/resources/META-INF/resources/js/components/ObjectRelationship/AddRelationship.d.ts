/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/// <reference types="react" />

import {Observer} from '@clayui/modal/lib/types';
export default function AddRelationship({
	objectDefinitionExternalReferenceCode,
	objectRelationshipTypes,
	parameterRequired,
}: IProps): JSX.Element;
interface IProps {
	objectDefinitionExternalReferenceCode: string;
	objectRelationshipTypes: string[];
	observer: Observer;
	onClose: () => void;
	parameterRequired: boolean;
}
export {};
