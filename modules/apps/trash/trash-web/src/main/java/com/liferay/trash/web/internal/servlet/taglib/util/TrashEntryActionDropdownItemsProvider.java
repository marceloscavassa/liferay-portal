/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.model.TrashEntry;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class TrashEntryActionDropdownItemsProvider {

	public TrashEntryActionDropdownItemsProvider(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, TrashEntry trashEntry) {

		_liferayPortletResponse = liferayPortletResponse;
		_trashEntry = trashEntry;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			trashEntry.getClassName());
	}

	public List<DropdownItem> getActionDropdownItems() throws Exception {
		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						() -> _trashHandler.isRestorable(
							_trashEntry.getClassPK()),
						_getRestoreActionDropdownItem()
					).add(
						() ->
							!_trashHandler.isRestorable(
								_trashEntry.getClassPK()) &&
							_trashHandler.isMovable(_trashEntry.getClassPK()),
						_getMoveActionDropdownItem()
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						() ->
							CTCollectionThreadLocal.isProductionMode() &&
							_trashHandler.isDeletable(_trashEntry.getClassPK()),
						_getDeleteActionDropdownItem()
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).build();
	}

	private DropdownItem _getDeleteActionDropdownItem() {
		return DropdownItemBuilder.putData(
			"action", "deleteEntry"
		).putData(
			"deleteEntryURL",
			PortletURLBuilder.createActionURL(
				_liferayPortletResponse
			).setActionName(
				"deleteEntries"
			).setRedirect(
				_themeDisplay.getURLCurrent()
			).setParameter(
				"trashEntryId", _trashEntry.getEntryId()
			).buildString()
		).setIcon(
			"trash"
		).setLabel(
			LanguageUtil.get(_themeDisplay.getLocale(), "delete")
		).build();
	}

	private DropdownItem _getMoveActionDropdownItem() throws Exception {
		return DropdownItemBuilder.putData(
			"action", "moveEntry"
		).putData(
			"moveEntryURL",
			PortletURLBuilder.createRenderURL(
				_liferayPortletResponse
			).setMVCPath(
				"/view_container_model.jsp"
			).setParameter(
				"classNameId", _trashEntry.getClassNameId()
			).setParameter(
				"classPK", _trashEntry.getClassPK()
			).setParameter(
				"containerModelClassNameId",
				PortalUtil.getClassNameId(
					_trashHandler.getContainerModelClassName(
						_trashEntry.getClassPK()))
			).setWindowState(
				LiferayWindowState.POP_UP
			).buildString()
		).setIcon(
			"restore"
		).setLabel(
			LanguageUtil.get(_themeDisplay.getLocale(), "restore")
		).build();
	}

	private DropdownItem _getRestoreActionDropdownItem() {
		return DropdownItemBuilder.putData(
			"action", "restoreEntry"
		).putData(
			"restoreEntryURL",
			PortletURLBuilder.createActionURL(
				_liferayPortletResponse
			).setActionName(
				"restoreEntries"
			).setRedirect(
				_themeDisplay.getURLCurrent()
			).setParameter(
				"trashEntryId", _trashEntry.getEntryId()
			).buildString()
		).setIcon(
			"restore"
		).setLabel(
			LanguageUtil.get(_themeDisplay.getLocale(), "restore")
		).build();
	}

	private final LiferayPortletResponse _liferayPortletResponse;
	private final ThemeDisplay _themeDisplay;
	private final TrashEntry _trashEntry;
	private final TrashHandler _trashHandler;

}