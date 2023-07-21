/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import react from '@vitejs/plugin-react';
import {defineConfig} from 'vite';

// https://vitejs.dev/config/

export default defineConfig({
	build: {assetsDir: 'static', outDir: 'build'},
	esbuild: {
		logOverride: {'this-is-undefined-in-esm': 'silent'},
	},
	plugins: [react()],
});
