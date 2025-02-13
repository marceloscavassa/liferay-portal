/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React, {Fragment} from 'react';

import {groupVocabulariesBy, sortByStrings} from './utils/taxonomiesUtils';

const ItemVocabularies = ({cssClassNames = '', title, vocabularies}) => {
	const [global, nonGlobal] = groupVocabulariesBy({
		array: vocabularies,
		key: 'groupName',
		value: 'global',
	});

	const globalSorted = sortByStrings({array: global, key: 'vocabularyName'});
	const nonGlobalSorted = sortByStrings({
		array: nonGlobal,
		key: 'vocabularyName',
	});

	const groupedAndSortedVocabularies = nonGlobalSorted.concat(globalSorted);

	return (
		<div
			className={`c-mb-4 item-vocabularies sidebar-section ${cssClassNames}`}
		>
			<h6 className="font-weight-semi-bold sidebar-section-subtitle-sm text-2 text-secondary text-uppercase">
				{title}
			</h6>

			<div>
				{groupedAndSortedVocabularies.map(
					({categories, groupName, vocabularyName}) => (
						<Fragment key={vocabularyName}>
							<h5 className="c-mb-2 font-weight-semi-bold">
								{vocabularyName}

								{groupName ? ` (${groupName})` : ''}
							</h5>

							<p>
								{sortByStrings({array: categories}).map(
									(category) => (
										<ClayLabel
											className="c-mb-2 c-mr-2"
											displayType="secondary"
											key={category}
											large
										>
											{category}
										</ClayLabel>
									)
								)}
							</p>
						</Fragment>
					)
				)}
			</div>
		</div>
	);
};
ItemVocabularies.defaultProps = {
	cssClassNames: '',
};

ItemVocabularies.propTypes = {
	cssClassNames: PropTypes.string,
	title: PropTypes.string.isRequired,
	vocabularies: PropTypes.array.isRequired,
};

export default ItemVocabularies;
