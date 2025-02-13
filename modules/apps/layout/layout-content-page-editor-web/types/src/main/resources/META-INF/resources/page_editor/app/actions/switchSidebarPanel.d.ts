/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default function switchSidebarPanel({
	hidden,
	itemConfigurationOpen,
	sidebarOpen,
	sidebarPanelId,
}: {
	hidden?: boolean;
	itemConfigurationOpen?: boolean;
	sidebarOpen: boolean;
	sidebarPanelId: string;
}): {
	readonly hidden: boolean;
	readonly itemConfigurationOpen: boolean;
	readonly sidebarOpen: boolean;
	readonly sidebarPanelId: string;
	readonly type: 'SWITCH_SIDEBAR_PANEL';
};
