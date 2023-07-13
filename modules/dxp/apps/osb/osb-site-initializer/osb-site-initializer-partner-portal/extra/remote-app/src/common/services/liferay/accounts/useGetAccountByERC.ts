/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Liferay} from '..';
import useSWR from 'swr';

import {LiferayAPIs} from '../common/enums/apis';
import liferayFetcher from '../common/utils/fetcher';

interface Account {
	currency: string;
	externalReferenceCode: string;
	id: number;
	name: string;
	partnerCountry: string;
	r_prtLvlToAcc_c_partnerLevelERC: string;
}

export default function useGetAccountByERC(
	externalReferenceCode: string | undefined
) {
	return useSWR(
		externalReferenceCode
			? [
					`/o/${LiferayAPIs.HEADERLESS_ADMIN_USER}/accounts/by-external-reference-code/${externalReferenceCode}`,
					Liferay.authToken,
			  ]
			: null,
		(url, token) => liferayFetcher<Account>(url, token)
	);
}
