/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayDropDown from '@clayui/drop-down';
import {openConfirmModal, sub} from 'frontend-js-web';
import React, {useContext} from 'react';

import ChartContext from '../ChartContext';
import {
	deleteUser,
	removeUserFromAccount,
	removeUserFromOrganization,
} from '../data/users';
import {ACTION_KEYS} from '../utils/constants';
import {hasPermission} from '../utils/index';

export default function AccountMenuContent({closeMenu, data, parentData}) {
	const {chartInstanceRef} = useContext(ChartContext);

	function handleDelete() {
		openConfirmModal({
			message: sub(Liferay.Language.get('x-will-be-deleted'), data.name),
			onConfirm: (isConfirmed) => {
				if (isConfirmed) {
					deleteUser(data.id).then(() => {
						chartInstanceRef.current.deleteNodes([data], true);

						closeMenu();
					});
				}
			},
		});
	}

	function handleRemove() {
		openConfirmModal({
			message: sub(
				Liferay.Language.get('x-will-be-removed-from-x'),
				data.name,
				parentData.name
			),
			onConfirm: (isConfirmed) => {
				if (isConfirmed) {
					const removeUser =
						parentData.type === 'organization'
							? removeUserFromOrganization
							: removeUserFromAccount;

					removeUser(data.emailAddress, parentData.id).then(() => {
						chartInstanceRef.current.deleteNodes([data], false);

						closeMenu();
					});
				}
			},
		});
	}

	const actions = [];

	if (hasPermission(data, ACTION_KEYS.user.REMOVE)) {
		actions.push(
			<ClayDropDown.Item key="remove" onClick={handleRemove}>
				{Liferay.Language.get('remove')}
			</ClayDropDown.Item>
		);
	}

	if (hasPermission(data, ACTION_KEYS.user.DELETE)) {
		actions.push(
			<ClayDropDown.Item key="delete" onClick={handleDelete}>
				{Liferay.Language.get('delete')}
			</ClayDropDown.Item>
		);
	}

	return actions;
}
