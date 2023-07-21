/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import classNames from 'classnames';
import React from 'react';

import {ProgressRing} from '../ProgressRing';

export function StepItem({
	children,
	isMobile = false,
	onClick,
	percentage = 0,
	selected = false,
}) {
	const completed = percentage === 100;
	const partially = percentage !== 0;

	return (
		<div
			className={classNames(
				'align-items-center d-flex font-weight-bolder step-item text-paragraph-lg',
				{
					completed,
					partially,
					selected,
					'text-brand-primary': selected || partially,
				}
			)}
			onClick={partially && !isMobile ? onClick : undefined}
		>
			<i className="align-items-center justify-content-center position-relative">
				{partially && (
					<ProgressRing
						className="position-absolute progress-ring"
						diameter={32}
						percent={percentage}
						strokeWidth={3}
					/>
				)}
			</i>

			{completed && (
				<div className="align-items-center bg-brand-primary d-flex justify-content-center">
					<ClayIcon className="m-0 text-neutral-0" symbol="check" />
				</div>
			)}

			{children}
		</div>
	);
}
