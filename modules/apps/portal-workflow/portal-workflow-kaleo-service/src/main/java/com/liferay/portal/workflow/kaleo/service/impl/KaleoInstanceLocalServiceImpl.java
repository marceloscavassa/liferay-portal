/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;
import com.liferay.portal.workflow.kaleo.service.base.KaleoInstanceLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceQuery;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class KaleoInstanceLocalServiceImpl
	extends KaleoInstanceLocalServiceBaseImpl {

	@Override
	public KaleoInstance addKaleoInstance(
			long kaleoDefinitionVersionId, String kaleoDefinitionName,
			int kaleoDefinitionVersion,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.fetchUser(serviceContext.getUserId());

		if (user == null) {
			user = userLocalService.getDefaultUser(
				serviceContext.getCompanyId());
		}

		Date now = new Date();

		long kaleoInstanceId = counterLocalService.increment();

		KaleoInstance kaleoInstance = kaleoInstancePersistence.create(
			kaleoInstanceId);

		long groupId = StagingUtil.getLiveGroupId(
			serviceContext.getScopeGroupId());

		kaleoInstance.setGroupId(groupId);

		kaleoInstance.setCompanyId(user.getCompanyId());
		kaleoInstance.setUserId(user.getUserId());
		kaleoInstance.setUserName(user.getFullName());
		kaleoInstance.setCreateDate(now);
		kaleoInstance.setModifiedDate(now);
		kaleoInstance.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);
		kaleoInstance.setKaleoDefinitionName(kaleoDefinitionName);
		kaleoInstance.setKaleoDefinitionVersion(kaleoDefinitionVersion);
		kaleoInstance.setClassName(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

		if (workflowContext.containsKey(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)) {

			kaleoInstance.setClassPK(
				GetterUtil.getLong(
					(String)workflowContext.get(
						WorkflowConstants.CONTEXT_ENTRY_CLASS_PK)));
		}

		kaleoInstance.setCompleted(false);
		kaleoInstance.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoInstancePersistence.update(kaleoInstance);

		return kaleoInstance;
	}

	@Override
	public KaleoInstance completeKaleoInstance(long kaleoInstanceId)
		throws PortalException {

		KaleoInstance kaleoInstance = kaleoInstancePersistence.findByPrimaryKey(
			kaleoInstanceId);

		kaleoInstance.setCompleted(true);
		kaleoInstance.setCompletionDate(new Date());

		kaleoInstancePersistence.update(kaleoInstance);

		return kaleoInstance;
	}

	@Override
	public void deleteCompanyKaleoInstances(long companyId) {

		// Kaleo instances

		kaleoInstancePersistence.removeByCompanyId(companyId);

		// Kaleo instance tokens

		kaleoInstanceTokenLocalService.deleteCompanyKaleoInstanceTokens(
			companyId);

		// Kaleo logs

		kaleoLogLocalService.deleteCompanyKaleoLogs(companyId);

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenLocalService.deleteCompanyKaleoTaskInstanceTokens(
			companyId);
	}

	@Override
	public void deleteKaleoDefinitionVersionKaleoInstances(
		long kaleoDefinitionVersionId) {

		// Kaleo instances

		kaleoInstancePersistence.removeByKaleoDefinitionVersionId(
			kaleoDefinitionVersionId);

		// Kaleo instance tokens

		kaleoInstanceTokenLocalService.
			deleteKaleoDefinitionVersionKaleoInstanceTokens(
				kaleoDefinitionVersionId);

		// Kaleo logs

		kaleoLogLocalService.deleteKaleoDefinitionVersionKaleoLogs(
			kaleoDefinitionVersionId);

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenLocalService.
			deleteKaleoDefinitionVersionKaleoTaskInstanceTokens(
				kaleoDefinitionVersionId);
	}

	@Override
	public KaleoInstance deleteKaleoInstance(long kaleoInstanceId) {
		KaleoInstance kaleoInstance = null;

		try {
			kaleoInstance = kaleoInstancePersistence.remove(kaleoInstanceId);
		}
		catch (NoSuchInstanceException nsie) {
			return null;
		}

		// Kaleo instance tokens

		kaleoInstanceTokenLocalService.deleteKaleoInstanceKaleoInstanceTokens(
			kaleoInstanceId);

		// Kaleo logs

		kaleoLogLocalService.deleteKaleoInstanceKaleoLogs(kaleoInstanceId);

		// Kaleo task instance tokens

		kaleoTaskInstanceTokenLocalService.
			deleteKaleoInstanceKaleoTaskInstanceTokens(kaleoInstanceId);

		// Kaleo timer instance tokens

		kaleoTimerInstanceTokenLocalService.deleteKaleoTimerInstanceTokens(
			kaleoInstanceId);

		return kaleoInstance;
	}

	@Override
	public List<KaleoInstance> getKaleoInstances(
		Long userId, String assetClassName, Long assetClassPK,
		Boolean completed, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			userId, assetClassName, assetClassPK, completed, serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public List<KaleoInstance> getKaleoInstances(
		Long userId, String[] assetClassNames, Boolean completed, int start,
		int end, OrderByComparator<KaleoInstance> orderByComparator,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			userId, assetClassNames, null, completed, serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public List<KaleoInstance> getKaleoInstances(
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoDefinitionName, kaleoDefinitionVersion, completed,
			serviceContext);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public int getKaleoInstancesCount(
		long kaleoDefinitionVersionId, boolean completed) {

		return kaleoInstancePersistence.countByKDVI_C(
			kaleoDefinitionVersionId, completed);
	}

	@Override
	public int getKaleoInstancesCount(
		Long userId, String assetClassName, Long assetClassPK,
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			userId, assetClassName, assetClassPK, completed, serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public int getKaleoInstancesCount(
		Long userId, String[] assetClassNames, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			userId, assetClassNames, null, completed, serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public int getKaleoInstancesCount(
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			kaleoDefinitionName, kaleoDefinitionVersion, completed,
			serviceContext);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public List<KaleoInstance> search(
		Long userId, String assetType, String nodeName,
		String kaleoDefinitionName, Boolean completed, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		ServiceContext serviceContext) {

		KaleoInstanceQuery kaleoInstanceQuery = new KaleoInstanceQuery(
			serviceContext);

		kaleoInstanceQuery.setClassName(assetType);
		kaleoInstanceQuery.setCompleted(completed);
		kaleoInstanceQuery.setEnd(end);
		kaleoInstanceQuery.setKaleoDefinitionName(kaleoDefinitionName);
		kaleoInstanceQuery.setOrderByComparator(orderByComparator);
		kaleoInstanceQuery.setStart(start);
		kaleoInstanceQuery.setStatus(nodeName);

		try {
			Indexer<KaleoInstance> indexer = IndexerRegistryUtil.getIndexer(
				KaleoInstance.class.getName());

			SearchContext searchContext = buildSearchContext(
				kaleoInstanceQuery, start, end, orderByComparator);

			List<KaleoInstance> kaleoInstances = new ArrayList<>();

			Hits hits = indexer.search(searchContext);

			for (Document document : hits.getDocs()) {
				long kaleoInstanceId = GetterUtil.getLong(
					document.get(Field.ENTRY_CLASS_PK));

				kaleoInstances.add(
					kaleoInstancePersistence.findByPrimaryKey(kaleoInstanceId));
			}

			return kaleoInstances;
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		return Collections.emptyList();
	}

	@Override
	public int searchCount(
		Long userId, String assetType, String nodeName,
		String kaleoDefinitionName, Boolean completed,
		ServiceContext serviceContext) {

		KaleoInstanceQuery kaleoInstanceQuery = new KaleoInstanceQuery(
			serviceContext);

		kaleoInstanceQuery.setClassName(assetType);
		kaleoInstanceQuery.setCompleted(completed);
		kaleoInstanceQuery.setKaleoDefinitionName(kaleoDefinitionName);
		kaleoInstanceQuery.setStatus(nodeName);

		try {
			Indexer<KaleoInstance> indexer = IndexerRegistryUtil.getIndexer(
				KaleoInstance.class.getName());

			SearchContext searchContext = buildSearchContext(
				kaleoInstanceQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			return (int)indexer.searchCount(searchContext);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		return 0;
	}

	@Override
	public KaleoInstance updateKaleoInstance(
			long kaleoInstanceId, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		KaleoInstance kaleoInstance = kaleoInstancePersistence.findByPrimaryKey(
			kaleoInstanceId);

		kaleoInstance.setWorkflowContext(
			WorkflowContextUtil.convert(workflowContext));

		kaleoInstancePersistence.update(kaleoInstance);

		return kaleoInstance;
	}

	protected DynamicQuery buildDynamicQuery(
		Long userId, String assetClassName, Long assetClassPK,
		Boolean completed, ServiceContext serviceContext) {

		String[] assetClassNames = null;

		if (Validator.isNotNull(assetClassName)) {
			assetClassNames = new String[] {assetClassName};
		}

		Long[] assetClassPKs = null;

		if (Validator.isNotNull(assetClassPK)) {
			assetClassPKs = new Long[] {assetClassPK};
		}

		return buildDynamicQuery(
			userId, assetClassNames, assetClassPKs, completed, serviceContext);
	}

	protected DynamicQuery buildDynamicQuery(
		Long userId, String assetType, String nodeName,
		String kaleoDefinitionName, Boolean completed,
		ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoInstance.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(serviceContext.getCompanyId()));

		if (Validator.isNotNull(userId)) {
			Property userIdProperty = PropertyFactoryUtil.forName("userId");

			dynamicQuery.add(userIdProperty.eq(userId));
		}

		if (completed != null) {
			if (completed) {
				Property completionDateProperty = PropertyFactoryUtil.forName(
					"completionDate");

				dynamicQuery.add(completionDateProperty.isNotNull());
			}
			else {
				Property completionDateProperty = PropertyFactoryUtil.forName(
					"completionDate");

				dynamicQuery.add(completionDateProperty.isNull());
			}
		}

		Junction junction = RestrictionsFactoryUtil.disjunction();

		if (Validator.isNotNull(assetType)) {
			Property classNameProperty = PropertyFactoryUtil.forName(
				"className");

			junction.add(classNameProperty.like(assetType));
		}

		if (Validator.isNotNull(kaleoDefinitionName)) {
			Property kaleoDefinitionNameProperty = PropertyFactoryUtil.forName(
				"kaleoDefinitionName");

			junction.add(kaleoDefinitionNameProperty.eq(kaleoDefinitionName));
		}

		if (Validator.isNotNull(nodeName)) {
			Property kaleoInstanceIdProperty = PropertyFactoryUtil.forName(
				"kaleoInstanceId");

			DynamicQuery subdynamicQuery = DynamicQueryFactoryUtil.forClass(
				KaleoInstanceToken.class, getClassLoader());

			subdynamicQuery = subdynamicQuery.setProjection(
				kaleoInstanceIdProperty);

			Property currentKaleoNodeNameProperty = PropertyFactoryUtil.forName(
				"currentKaleoNodeName");

			subdynamicQuery.add(currentKaleoNodeNameProperty.like(nodeName));

			junction.add(kaleoInstanceIdProperty.in(subdynamicQuery));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		Long userId, String[] assetClassNames, Long[] assetClassPKs,
		Boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoInstance.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(serviceContext.getCompanyId()));

		if (userId != null) {
			Property userIdProperty = PropertyFactoryUtil.forName("userId");

			dynamicQuery.add(userIdProperty.eq(userId));
		}

		if (ArrayUtil.isNotEmpty(assetClassNames)) {
			dynamicQuery.add(getAssetClassNames(assetClassNames));
		}

		if (ArrayUtil.isNotEmpty(assetClassPKs)) {
			dynamicQuery.add(getAssetClassPKs(assetClassPKs));
		}

		if (completed != null) {
			if (completed) {
				Property completionDateProperty = PropertyFactoryUtil.forName(
					"completionDate");

				dynamicQuery.add(completionDateProperty.isNotNull());
			}
			else {
				Property completionDateProperty = PropertyFactoryUtil.forName(
					"completionDate");

				dynamicQuery.add(completionDateProperty.isNull());
			}
		}

		return dynamicQuery;
	}

	protected DynamicQuery buildDynamicQuery(
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, ServiceContext serviceContext) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoInstance.class, getClassLoader());

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(companyIdProperty.eq(serviceContext.getCompanyId()));

		Property kaleoDefinitionNameProperty = PropertyFactoryUtil.forName(
			"kaleoDefinitionName");

		dynamicQuery.add(kaleoDefinitionNameProperty.eq(kaleoDefinitionName));

		Property kaleoDefinitionVersionProperty = PropertyFactoryUtil.forName(
			"kaleoDefinitionVersion");

		dynamicQuery.add(
			kaleoDefinitionVersionProperty.eq(kaleoDefinitionVersion));

		if (completed) {
			Property completionDateProperty = PropertyFactoryUtil.forName(
				"completionDate");

			dynamicQuery.add(completionDateProperty.isNotNull());
		}
		else {
			Property completionDateProperty = PropertyFactoryUtil.forName(
				"completionDate");

			dynamicQuery.add(completionDateProperty.isNull());
		}

		return dynamicQuery;
	}

	protected SearchContext buildSearchContext(
		KaleoInstanceQuery kaleoInstanceQuery, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {

		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute("kaleoInstanceQuery", kaleoInstanceQuery);
		searchContext.setCompanyId(kaleoInstanceQuery.getCompanyId());
		searchContext.setEnd(end);
		searchContext.setStart(start);

		if (orderByComparator != null) {
			searchContext.setSorts(getSortsFromComparator(orderByComparator));
		}

		searchContext.setUserId(kaleoInstanceQuery.getUserId());

		return searchContext;
	}

	protected Criterion getAssetClassNames(String[] assetClassNames) {
		Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

		for (String assetClassName : assetClassNames) {
			Property classNameProperty = PropertyFactoryUtil.forName(
				"className");

			disjunction.add(classNameProperty.like(assetClassName));
		}

		return disjunction;
	}

	protected Criterion getAssetClassPKs(Long[] assetClassPKs) {
		Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

		for (Long assetClassPK : assetClassPKs) {
			Property classPKProperty = PropertyFactoryUtil.forName("classPK");

			disjunction.add(classPKProperty.eq(assetClassPK));
		}

		return disjunction;
	}

	protected Sort[] getSortsFromComparator(
		OrderByComparator<KaleoInstance> orderByComparator) {

		Stream<String> stream = Arrays.stream(
			orderByComparator.getOrderByFields());

		return stream.map(
			field -> {
				if (StringUtil.endsWith(field, "date")) {
					return new Sort(
						field, Sort.LONG_TYPE,
						!orderByComparator.isAscending());
				}

				return new Sort(field, !orderByComparator.isAscending());
			}
		).toArray(
			Sort[]::new
		);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoInstanceLocalServiceImpl.class);

}