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

import {useRef} from 'react';
import {BuildStatuses} from '~/util/statuses';

import useFormActions from '../../../../../hooks/useFormActions';
import useFormModal from '../../../../../hooks/useFormModal';
import useMutate from '../../../../../hooks/useMutate';
import i18n from '../../../../../i18n';
import {TestrayBuild, testrayBuildImpl} from '../../../../../services/rest';
import {Action} from '../../../../../types';

const useBuildTemplateActions = () => {
	const formModal = useFormModal();
	const {form} = useFormActions();
	const {removeItemFromList, updateItemFromList} = useMutate();

	const actionsRef = useRef([
		{
			action: (build, mutate) => {
				testrayBuildImpl
					.update(build.id, {
						dueStatus:
							build.dueStatus.key === BuildStatuses.ACTIVATED
								? BuildStatuses.DEACTIVATED
								: BuildStatuses.ACTIVATED,
					})
					.then((templateResponse) =>
						updateItemFromList(mutate, build.id, {
							dueStatus: templateResponse.dueStatus,
						})
					)
					.then(form.onSuccess)
					.catch(form.onError);
			},
			icon: 'logout',
			name: ({dueStatus}) =>
				i18n.translate(
					dueStatus.key === BuildStatuses.ACTIVATED
						? 'deactivate'
						: 'activate'
				),
			permission: 'UPDATE',
		},
		{
			action: (build, mutate) => {
				testrayBuildImpl
					.remove(build.id)
					.then(() => removeItemFromList(mutate, build.id))
					.then(form.onSuccess)
					.catch(form.onError);
			},
			hidden: (build) => build.dueStatus.key === BuildStatuses.ACTIVATED,
			icon: 'trash',
			name: i18n.translate('delete'),
			permission: 'DELETE',
		},
	] as Action<TestrayBuild>[]);

	return {
		actions: actionsRef.current,
		formModal,
	};
};

export default useBuildTemplateActions;
