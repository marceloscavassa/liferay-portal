/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {UPDATE_FRAGMENTS} from './types';

import type {FragmentEntryType} from '../config/constants/fragmentEntryTypes';

export interface FragmentEntry {
	fragmentEntryKey: string;
	highlighted: boolean;
	icon: string;
	imagePreviewURL: string;
	name: string;
	type: FragmentEntryType;
}

export interface FragmentSet {
	fragmentCollectionId: string;
	fragmentEntries: FragmentEntry[];
	name: string;
}

export default function updateFragments({
	fragments,
}: {
	fragments: FragmentSet[];
}) {
	return {
		fragments,
		type: UPDATE_FRAGMENTS,
	} as const;
}
