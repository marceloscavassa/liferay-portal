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

import type {FragmentEntryLink} from './addFragmentEntryLinks';
import type {PageContent} from './addItem';
export default function updateEditableValues({
	content,
	editableValues,
	fragmentEntryLinkId,
	pageContents,
	segmentsExperienceId,
}: {
	content: string;
	editableValues: FragmentEntryLink['editableValues'];
	fragmentEntryLinkId: string;
	pageContents: PageContent[];
	segmentsExperienceId: string;
}): {
	readonly content: string;
	readonly editableValues: {
		'com.liferay.fragment.entry.processor.editable.EditableFragmentEntryProcessor': {
			[x: string]: unknown;
		};
		'com.liferay.fragment.entry.processor.freemarker.FreeMarkerFragmentEntryProcessor': {
			[x: string]: unknown;
		};
	};
	readonly fragmentEntryLinkId: string;
	readonly pageContents: PageContent[];
	readonly segmentsExperienceId: string;
	readonly type: 'UPDATE_EDITABLE_VALUES';
};
