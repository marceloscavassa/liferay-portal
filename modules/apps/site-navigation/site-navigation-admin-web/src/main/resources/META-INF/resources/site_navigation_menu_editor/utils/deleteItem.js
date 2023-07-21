/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default function deleteItem(items, itemId) {
	const deletedItem = items.find(
		(item) => item.siteNavigationMenuItemId === itemId
	);

	return items
		.filter((item) => item !== deletedItem)
		.map((item) => {
			if (
				item.parentSiteNavigationMenuItemId ===
				deletedItem.siteNavigationMenuItemId
			) {
				return {
					...item,
					parentSiteNavigationMenuItemId:
						deletedItem.parentSiteNavigationMenuItemId,
				};
			}
			else {
				return item;
			}
		});
}
