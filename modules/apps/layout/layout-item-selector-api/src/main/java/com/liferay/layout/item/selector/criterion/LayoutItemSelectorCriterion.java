/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.item.selector.criterion;

import com.liferay.item.selector.BaseItemSelectorCriterion;

/**
 * @author Sergio González
 */
public class LayoutItemSelectorCriterion extends BaseItemSelectorCriterion {

	public LayoutItemSelectorCriterion() {
		_showBreadcrumb = true;
		_showPrivatePages = true;
		_showPublicPages = true;
	}

	public boolean isCheckDisplayPage() {
		return _checkDisplayPage;
	}

	public boolean isEnableCurrentPage() {
		return _enableCurrentPage;
	}

	public boolean isFollowURLOnTitleClick() {
		return _followURLOnTitleClick;
	}

	public boolean isMultiSelection() {
		return _multiSelection;
	}

	public boolean isShowActionsMenu() {
		return _showActionsMenu;
	}

	public boolean isShowBreadcrumb() {
		return _showBreadcrumb;
	}

	public boolean isShowDraftPages() {
		return _showDraftPages;
	}

	public boolean isShowHiddenPages() {
		return _showHiddenPages;
	}

	public boolean isShowPrivatePages() {
		return _showPrivatePages;
	}

	public boolean isShowPublicPages() {
		return _showPublicPages;
	}

	public void setCheckDisplayPage(boolean checkDisplayPage) {
		_checkDisplayPage = checkDisplayPage;
	}

	public void setEnableCurrentPage(boolean enableCurrentPage) {
		_enableCurrentPage = enableCurrentPage;
	}

	public void setFollowURLOnTitleClick(boolean followURLOnTitleClick) {
		_followURLOnTitleClick = followURLOnTitleClick;
	}

	public void setMultiSelection(boolean multiSelection) {
		_multiSelection = multiSelection;
	}

	public void setShowActionsMenu(boolean showActionsMenu) {
		_showActionsMenu = showActionsMenu;
	}

	public void setShowBreadcrumb(boolean showBreadcrumb) {
		_showBreadcrumb = showBreadcrumb;
	}

	public void setShowDraftPages(boolean showDraftPages) {
		_showDraftPages = showDraftPages;
	}

	public void setShowHiddenPages(boolean showHiddenPages) {
		_showHiddenPages = showHiddenPages;
	}

	public void setShowPrivatePages(boolean showPrivatePages) {
		_showPrivatePages = showPrivatePages;
	}

	public void setShowPublicPages(boolean showPublicPages) {
		_showPublicPages = showPublicPages;
	}

	private boolean _checkDisplayPage;
	private boolean _enableCurrentPage;
	private boolean _followURLOnTitleClick;
	private boolean _multiSelection;
	private boolean _showActionsMenu;
	private boolean _showBreadcrumb;
	private boolean _showDraftPages;
	private boolean _showHiddenPages;
	private boolean _showPrivatePages;
	private boolean _showPublicPages;

}