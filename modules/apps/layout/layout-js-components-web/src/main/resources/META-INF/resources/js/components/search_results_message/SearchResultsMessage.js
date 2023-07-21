/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {sub} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import isNullOrUndefined from '../../utils/isNullOrUndefined';

export default function SearchResultsMessage({
	resultType = Liferay.Language.get('results'),
	numberOfResults = null,
}) {
	const [text, setText] = useState('');

	useEffect(() => {
		if (!isNullOrUndefined(numberOfResults)) {
			const timeout = setTimeout(() => {
				const message = numberOfResults
					? sub(Liferay.Language.get('showing-x-x'), [
							numberOfResults,
							resultType,
					  ])
					: Liferay.Language.get('no-results-found');

				setText(message);
			}, 500);

			return () => {
				clearTimeout(timeout);
			};
		}
	}, [resultType, numberOfResults]);

	return (
		<span className="sr-only" role="status">
			{text}
		</span>
	);
}

SearchResultsMessage.propTypes = {
	numberOfResults: PropTypes.number,
	resultType: PropTypes.string,
};
