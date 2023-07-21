/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
interface ILoadingProps extends React.HTMLAttributes<HTMLSpanElement> {
	absolute?: boolean;
	inline?: boolean;
}
declare const Loading: React.FC<ILoadingProps>;
export default Loading;
