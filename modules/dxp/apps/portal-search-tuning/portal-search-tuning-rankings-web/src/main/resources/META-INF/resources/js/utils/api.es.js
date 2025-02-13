/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {isNull} from './util.es';

/**
 * Fetches basic response given the url and parameters.
 * @param {string} url The base url to fetch.
 * @param {Object} params The url parameters to be included in the request.
 * @returns {Promise} The fetch request promise.
 */
export function fetchResponse(url, params) {
	const fetchUrl = new URL(url);

	Object.keys(params).forEach((property) => {
		if (!isNull(params[property])) {
			fetchUrl.searchParams.set(property, params[property]);
		}
	});

	return fetch(fetchUrl).then((response) => response.json());
}

/**
 * Fetches documents and maps the data response to the expected object shape
 * of {items: [{}, {}, ...], total: 10}.
 * @param {string} url The base url to fetch documents.
 * @param {Object} params The url parameters to be included in the request.
 * @returns {Promise} The fetch request promise.
 */
export function fetchDocuments(url, params) {
	return fetchResponse(url, params).then((data) => ({
		items: data.documents,
		total: data.total,
	}));
}
