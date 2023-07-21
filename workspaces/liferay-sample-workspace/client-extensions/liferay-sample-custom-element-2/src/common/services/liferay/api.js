/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Liferay} from './liferay';

const {REACT_APP_LIFERAY_HOST = window.location.origin} = process.env;

const baseFetch = async (url, options = {}) => {
	return fetch(REACT_APP_LIFERAY_HOST + '/' + url, {
		headers: {
			'Content-Type': 'application/json',
			'x-csrf-token': Liferay.authToken,
		},
		...options,
	});
};

export default baseFetch;
