/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {TOGGLE_WIDGET_HIGHLIGHTED} from './types';

import type {Widget} from './updateWidgets';

export default function toggleWidgetHighlighted({
	highlighted,
	highlightedPortlets,
	initiallyHighlighted,
	portletId,
}: {
	highlighted: boolean;
	highlightedPortlets: Widget[];
	initiallyHighlighted?: boolean;
	portletId: string;
}) {
	return {
		highlighted,
		highlightedPortlets,
		initiallyHighlighted,
		portletId,
		type: TOGGLE_WIDGET_HIGHLIGHTED,
	} as const;
}
