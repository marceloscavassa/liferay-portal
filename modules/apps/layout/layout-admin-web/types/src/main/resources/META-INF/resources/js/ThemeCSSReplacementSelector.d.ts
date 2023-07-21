/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/// <reference types="react" />

export default function ThemeCSSReplacementSelector({
	isReadOnly,
	placeholder,
	portletNamespace,
	selectThemeCSSClientExtensionEventName,
	selectThemeCSSClientExtensionURL,
	themeCSSCETExternalReferenceCode,
	themeCSSExtensionName,
}: IProps): JSX.Element;
interface IProps {
	isReadOnly: boolean;
	placeholder: string;
	portletNamespace: string;
	selectThemeCSSClientExtensionEventName: string;
	selectThemeCSSClientExtensionURL: string;
	themeCSSCETExternalReferenceCode: string;
	themeCSSExtensionName: string;
}
export {};
