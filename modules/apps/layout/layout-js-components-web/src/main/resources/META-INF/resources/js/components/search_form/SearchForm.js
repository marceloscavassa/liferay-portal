/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayForm, {ClayInput} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import {debounce} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

let nextInputId = 0;

export default function SearchForm({className, label, onChange}) {
	const id = `pageEditorSearchFormInput${nextInputId++}`;
	const onChangeDebounceRef = useRef(
		debounce((value) => onChange(value), 100)
	);
	const [searchValue, setSearchValue] = useState('');

	return (
		<ClayForm.Group className={className} role="search">
			<label className="sr-only" htmlFor={id}>
				{label || Liferay.Language.get('search-form')}
			</label>

			<ClayInput.Group>
				<ClayInput.GroupItem>
					<ClayInput
						id={id}
						insetAfter
						onChange={(event) => {
							setSearchValue(event.target.value);
							onChangeDebounceRef.current(event.target.value);
						}}
						placeholder={`${Liferay.Language.get('search')}...`}
						sizing="sm"
						spellCheck="false"
						value={searchValue}
					/>

					<ClayInput.GroupInsetItem after tag="span">
						{searchValue ? (
							<ClayButtonWithIcon
								aria-label={Liferay.Language.get(
									'clear-search'
								)}
								borderless
								displayType="secondary"
								monospaced={false}
								onClick={() => {
									setSearchValue('');
									onChangeDebounceRef.current('');
								}}
								size="sm"
								symbol={searchValue ? 'times' : 'search'}
								title={Liferay.Language.get('clear-search')}
							/>
						) : (
							<ClayIcon
								className="mt-0 search-icon"
								symbol="search"
							/>
						)}
					</ClayInput.GroupInsetItem>
				</ClayInput.GroupItem>
			</ClayInput.Group>
		</ClayForm.Group>
	);
}

SearchForm.propTypes = {
	onChange: PropTypes.func.isRequired,
};
