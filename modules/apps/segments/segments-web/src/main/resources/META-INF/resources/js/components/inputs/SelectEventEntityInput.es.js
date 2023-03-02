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

import ClayButton from '@clayui/button';
import classNames from 'classnames';
import {openSelectionModal} from 'frontend-js-web';
import propTypes from 'prop-types';
import React from 'react';

class SelectEventEntityInput extends React.Component {
	static propTypes = {
		disabled: propTypes.bool,
		displayValue: propTypes.oneOfType([propTypes.string, propTypes.number]),
		onChange: propTypes.func.isRequired,
		propertyLabel: propTypes.string.isRequired,
		renderEmptyValueErrors: propTypes.bool,
		selectEntity: propTypes.shape({
			id: propTypes.string,
			multiple: propTypes.bool,
			title: propTypes.string,
			uri: propTypes.string,
		}),
		value: propTypes.oneOfType([propTypes.string, propTypes.number]),
	};

	/**
	 * Opens a modal for selecting entities. Uses different methods for
	 * selecting multiple entities versus single because of the way the event
	 * and data is submitted.
	 */

	_handleSelectEntity = () => {
		const {
			onChange,
			selectEntity: {id, title, uri},
		} = this.props;

		openSelectionModal({
			onSelect: (event) => {
				const value = JSON.parse(event.value);

				onChange({
					assetId: value.fileEntryId,
					displayValue: value.title,
				});
			},
			selectEventName: id,
			title,
			url: uri,
		});
	};

	render() {
		const {
			disabled,
			displayValue,
			propertyLabel,
			renderEmptyValueErrors,
			value,
		} = this.props;

		return (
			<div className="criterion-input input-group select-entity-input">
				<div className="input-group-item input-group-prepend">
					<input
						data-testid="entity-select-input"
						disabled={disabled}
						type="hidden"
						value={value}
					/>

					<input
						aria-label={`${propertyLabel}: ${Liferay.Language.get(
							'select-option'
						)}`}
						className={classNames('form-control', {
							'criterion-input--error':
								!value && renderEmptyValueErrors,
						})}
						disabled={disabled}
						readOnly
						value={displayValue}
					/>
				</div>

				<span className="input-group-append input-group-item input-group-item-shrink">
					<ClayButton
						className={classNames(
							'input-group-append input-group-item input-group-item-shrink',
							{
								'criterion-input--error':
									!value && renderEmptyValueErrors,
							}
						)}
						disabled={disabled}
						displayType="secondary"
						onClick={this._handleSelectEntity}
					>
						{Liferay.Language.get('select')}
					</ClayButton>
				</span>
			</div>
		);
	}
}

export default SelectEventEntityInput;
