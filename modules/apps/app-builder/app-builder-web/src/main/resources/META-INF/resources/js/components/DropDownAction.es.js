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

import ClayDropDown from '@clayui/drop-down';
import React from 'react';

const {Item} = ClayDropDown;

export default function DropDownAction(props) {
	const {action, row, setActive} = props;

	return (
		<Item
			onClick={() => {
				setActive(false);

				const confirmed = confirm(
					Liferay.Language.get(
						'are-you-sure-you-want-to-delete-it-permanently'
					)
				);

				if (confirmed) {
					action.callback(row);
				}
			}}
		>
			{action.name}
		</Item>
	);
}
