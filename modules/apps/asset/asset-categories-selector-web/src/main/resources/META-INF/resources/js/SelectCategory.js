/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {SelectAssetCategory} from '@liferay/asset-categories-item-selector-web';
import React from 'react';

export default function SelectCategory(props) {
	return <SelectAssetCategory {...props} />;
}
