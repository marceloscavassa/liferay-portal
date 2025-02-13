/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function () {
	AUI().applyConfig({
		groups: {
			bookmarks: {
				base: MODULE_PATH + '/bookmarks/js/',
				combine: Liferay.AUI.getCombine(),
				filter: Liferay.AUI.getFilterConfig(),
				modules: {
					'liferay-bookmarks': {
						path: 'main.js',
						requires: ['liferay-portlet-base'],
					},
				},
				root: MODULE_PATH + '/bookmarks/js/',
			},
		},
	});
})();
