/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openModal, sub} from 'frontend-js-web';

export default function openDeleteTemplateModal({
	message,
	multiple = false,
	onDelete,
}) {
	openModal({
		bodyHTML: message,
		buttons: [
			{
				autoFocus: true,
				displayType: 'secondary',
				label: Liferay.Language.get('cancel'),
				type: 'cancel',
			},
			{
				displayType: 'danger',
				label: Liferay.Language.get('delete'),
				onClick: ({processClose}) => {
					processClose();

					onDelete();
				},
			},
		],
		status: 'danger',
		title: sub(
			Liferay.Language.get('delete-x'),
			multiple
				? Liferay.Language.get('templates')
				: Liferay.Language.get('template')
		),
	});
}
