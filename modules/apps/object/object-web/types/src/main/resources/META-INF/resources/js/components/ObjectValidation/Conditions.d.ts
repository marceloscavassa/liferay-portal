/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/// <reference types="react" />

import 'codemirror/mode/groovy/groovy';
import {SidebarCategory} from '@liferay/object-js-components-web';
import {TabProps} from './useObjectValidationForm';
interface ConditionsProps extends TabProps {
	objectValidationRuleElements: SidebarCategory[];
}
export declare function Conditions({
	disabled,
	errors,
	objectValidationRuleElements,
	setValues,
	values,
}: ConditionsProps): JSX.Element;
export {};
