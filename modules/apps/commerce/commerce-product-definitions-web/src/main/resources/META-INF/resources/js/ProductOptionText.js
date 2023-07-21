/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayForm, {ClayInput} from '@clayui/form';
import classnames from 'classnames';
import React, {useState} from 'react';

import Asterisk from './Asterisk';

const ProductOptionText = ({
	id,
	label,
	name,
	onChange,
	productOptionValues,
	required,
}) => {
	const [text, setText] = useState(productOptionValues[0].value);
	const [errors, setErrors] = useState({});

	const handleBlur = ({target: {value}}) => {
		if (required && value === '') {
			setErrors({emptyField: true});
		}
		else {
			setErrors({});
			onChange(text);
		}
	};

	return (
		<ClayForm.Group
			className={classnames({'has-error': errors.emptyField})}
		>
			<label htmlFor={id}>
				{label}

				<Asterisk required={required} />
			</label>

			<ClayInput
				id={id}
				name={name}
				onBlur={handleBlur}
				onChange={({target: {value}}) => {
					setText(value);
				}}
				value={text}
			/>

			{errors.emptyField && (
				<ClayForm.FeedbackItem>
					<ClayForm.FeedbackIndicator symbol="exclamation-full" />

					{Liferay.Language.get('this-field-is-required')}
				</ClayForm.FeedbackItem>
			)}
		</ClayForm.Group>
	);
};

export default ProductOptionText;
