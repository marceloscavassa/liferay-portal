/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import './CustomSelect.scss';
export declare const CustomSelect: React.ForwardRefExoticComponent<
	ICustomSelectProps & React.RefAttributes<HTMLDivElement>
>;
interface ICustomSelectProps extends React.HTMLAttributes<HTMLDivElement> {
	contentRight?: React.ReactNode;
	disabled?: boolean;
	value?: string;
}
export {};
