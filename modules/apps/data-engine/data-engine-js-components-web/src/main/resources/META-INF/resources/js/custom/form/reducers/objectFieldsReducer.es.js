/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {EVENT_TYPES} from '../eventTypes';

export default function objectFieldsReducer(state, action) {
	switch (action.type) {
		case EVENT_TYPES.OBJECT.FIELDS_CHANGE: {
			const {objectFields} = action.payload;

			return {
				objectFields,
			};
		}
		default:
			return state;
	}
}
