/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.test.util.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author André de Oliveira
 */
public class JournalArticleBlueprint {

	public JournalArticleBlueprint() {
	}

	public JournalArticleBlueprint(
		JournalArticleBlueprint journalArticleBlueprint) {

		_assetCategoryIds = journalArticleBlueprint._assetCategoryIds;
		_draft = journalArticleBlueprint._draft;
		_expandoBridgeAttributes = getExpandoBridgeAttributes(
			journalArticleBlueprint._expandoBridgeAttributes);
		_groupId = journalArticleBlueprint._groupId;
		_journalArticleContent = getJournalArticleContent(
			journalArticleBlueprint._journalArticleContent);
		_journalArticleDescription = getJournalArticleDescription(
			journalArticleBlueprint._journalArticleDescription);
		_journalArticleTitle = getJournalArticleTitle(
			journalArticleBlueprint._journalArticleTitle);
		_serviceContext = journalArticleBlueprint._serviceContext;
		_userId = journalArticleBlueprint._userId;
		_workflowEnabled = journalArticleBlueprint._workflowEnabled;
	}

	public long[] getAssetCategoryIds() {
		return _assetCategoryIds;
	}

	public String getContentString() {
		return _journalArticleContent.getContentString();
	}

	public Map<Locale, String> getDescriptionMap() {
		if (_journalArticleDescription != null) {
			return _journalArticleDescription.getValues();
		}

		return null;
	}

	public Map<String, Serializable> getExpandoBridgeAttributes() {
		return _expandoBridgeAttributes;
	}

	public long getGroupId() {
		return _groupId;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public Map<Locale, String> getTitleMap() {
		return _journalArticleTitle.getValues();
	}

	public long getUserId() {
		if (_userId > 0) {
			return _userId;
		}

		try {
			return TestPropsValues.getUserId();
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	public int getWorkflowAction() {
		if (_draft) {
			return WorkflowConstants.ACTION_SAVE_DRAFT;
		}

		return WorkflowConstants.ACTION_PUBLISH;
	}

	public boolean isWorkflowEnabled() {
		return _workflowEnabled;
	}

	public void setAssetCategoryIds(long[] assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	public void setDraft(boolean draft) {
		_draft = draft;
	}

	public void setExpandoBridgeAttributes(
		Map<String, Serializable> expandoBridgeAttributes) {

		_expandoBridgeAttributes = expandoBridgeAttributes;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setJournalArticleContent(
		JournalArticleContent journalArticleContent) {

		_journalArticleContent = journalArticleContent;
	}

	public void setJournalArticleDescription(
		JournalArticleDescription journalArticleDescription) {

		_journalArticleDescription = journalArticleDescription;
	}

	public void setJournalArticleTitle(
		JournalArticleTitle journalArticleTitle) {

		_journalArticleTitle = journalArticleTitle;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		_serviceContext = serviceContext;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setWorkflowEnabled(boolean workflowEnabled) {
		_workflowEnabled = workflowEnabled;
	}

	protected static HashMap<String, Serializable> getExpandoBridgeAttributes(
		Map<String, Serializable> expandoBridgeAttributes) {

		if (expandoBridgeAttributes == null) {
			return null;
		}

		return HashMapBuilder.<String, Serializable>putAll(
			expandoBridgeAttributes
		).build();
	}

	protected static JournalArticleContent getJournalArticleContent(
		JournalArticleContent journalArticleContent) {

		if (journalArticleContent == null) {
			return null;
		}

		return new JournalArticleContent(journalArticleContent);
	}

	protected static JournalArticleDescription getJournalArticleDescription(
		JournalArticleDescription journalArticleDescription) {

		if (journalArticleDescription == null) {
			return null;
		}

		return new JournalArticleDescription(journalArticleDescription);
	}

	protected static JournalArticleTitle getJournalArticleTitle(
		JournalArticleTitle journalArticleTitle) {

		if (journalArticleTitle == null) {
			return null;
		}

		return new JournalArticleTitle(journalArticleTitle);
	}

	private long[] _assetCategoryIds;
	private boolean _draft;
	private Map<String, Serializable> _expandoBridgeAttributes;
	private long _groupId;
	private JournalArticleContent _journalArticleContent;
	private JournalArticleDescription _journalArticleDescription;
	private JournalArticleTitle _journalArticleTitle;
	private ServiceContext _serviceContext;
	private long _userId;
	private boolean _workflowEnabled;

}