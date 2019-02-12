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

package com.liferay.segments.upgrade.v_0_0_1.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portlet.expando.util.test.ExpandoTestUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo García
 */
@RunWith(Arquillian.class)
public class UpgradeContentTargetingTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_expandoTable = ExpandoTestUtil.addTable(
			PortalUtil.getClassNameId(User.class), "CUSTOM_FIELDS");

		_group = GroupTestUtil.addGroup();

		_db = DBManagerUtil.getDB();

		createContentTargetingTables();
		setUpUpgradeContentTargeting();
	}

	@After
	public void tearDown() throws Exception {
		dropContentTargetingTables();
	}

	@Test
	public void testUpgradeContentTargetingUserSegments() throws Exception {
		long contentTargetingUserSegmentId = -1L;

		Map<Locale, String> nameMap = RandomTestUtil.randomLocaleStringMap();
		Map<Locale, String> descriptionMap =
			RandomTestUtil.randomLocaleStringMap();

		insertContentTargetingUserSegment(
			contentTargetingUserSegmentId, nameMap, descriptionMap);

		_upgradeContentTargeting.upgrade();

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "CT." + contentTargetingUserSegmentId,
				false);

		Assert.assertNotNull(segmentsEntry);

		Assert.assertEquals(nameMap, segmentsEntry.getNameMap());
		Assert.assertEquals(descriptionMap, segmentsEntry.getDescriptionMap());
	}

	@Test
	public void testUpgradeContentTargetingUserSegmentsWithBrowserRule()
		throws Exception {

		long contentTargetingUserSegmentId = -1L;

		insertContentTargetingRuleInstance(
			contentTargetingUserSegmentId, "BrowserRule", "Chrome");

		insertContentTargetingUserSegment(
			contentTargetingUserSegmentId,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap());

		_upgradeContentTargeting.upgrade();

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "CT." + contentTargetingUserSegmentId,
				false);

		Assert.assertNotNull(segmentsEntry);

		Criteria criteriaObj = segmentsEntry.getCriteriaObj();

		Assert.assertNotNull(criteriaObj);

		Criteria.Criterion criterion = criteriaObj.getCriterion("context");

		Assert.assertNotNull(criterion);

		Assert.assertEquals(
			Criteria.Conjunction.AND,
			Criteria.Conjunction.parse(criterion.getConjunction()));
		Assert.assertEquals(
			"(browser eq 'Chrome')", criterion.getFilterString());
	}

	@Test
	public void testUpgradeContentTargetingUserSegmentsWithCustomFieldRule()
		throws Exception {

		long contentTargetingUserSegmentId = -1L;

		ExpandoColumn expandoColumn = ExpandoTestUtil.addColumn(
			_expandoTable, RandomTestUtil.randomString(),
			ExpandoColumnConstants.STRING);

		String expandoValue = RandomTestUtil.randomString();

		insertContentTargetingRuleInstance(
			contentTargetingUserSegmentId, "CustomFieldRule",
			_getCustomFieldRuleTypeSettings(expandoColumn, expandoValue));

		insertContentTargetingUserSegment(
			contentTargetingUserSegmentId,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap());

		_upgradeContentTargeting.upgrade();

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "CT." + contentTargetingUserSegmentId,
				false);

		Assert.assertNotNull(segmentsEntry);

		Criteria criteriaObj = segmentsEntry.getCriteriaObj();

		Assert.assertNotNull(criteriaObj);

		Criteria.Criterion criterion = criteriaObj.getCriterion("user");

		Assert.assertNotNull(criterion);

		Assert.assertEquals(
			Criteria.Conjunction.AND,
			Criteria.Conjunction.parse(criterion.getConjunction()));
		Assert.assertEquals(
			_getCustomFieldFilterString(expandoColumn, expandoValue),
			criterion.getFilterString());
	}

	@Test
	public void testUpgradeContentTargetingUserSegmentsWithLanguageRule()
		throws Exception {

		long contentTargetingUserSegmentId = -1L;

		insertContentTargetingRuleInstance(
			contentTargetingUserSegmentId, "LanguageRule", "es_ES");

		insertContentTargetingUserSegment(
			contentTargetingUserSegmentId,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap());

		_upgradeContentTargeting.upgrade();

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "CT." + contentTargetingUserSegmentId,
				false);

		Assert.assertNotNull(segmentsEntry);

		Criteria criteriaObj = segmentsEntry.getCriteriaObj();

		Assert.assertNotNull(criteriaObj);

		Criteria.Criterion criterion = criteriaObj.getCriterion("context");

		Assert.assertNotNull(criterion);

		Assert.assertEquals(
			Criteria.Conjunction.AND,
			Criteria.Conjunction.parse(criterion.getConjunction()));
		Assert.assertEquals(
			"(languageId eq 'es_ES')", criterion.getFilterString());
	}

	@Test
	public void testUpgradeContentTargetingUserSegmentsWithOrganizationMemberRule()
		throws Exception {

		long contentTargetingUserSegmentId = -1L;

		insertContentTargetingRuleInstance(
			contentTargetingUserSegmentId, "OrganizationMemberRule", "12345");

		insertContentTargetingUserSegment(
			contentTargetingUserSegmentId,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap());

		_upgradeContentTargeting.upgrade();

		SegmentsEntry segmentsEntry =
			_segmentsEntryLocalService.fetchSegmentsEntry(
				_group.getGroupId(), "CT." + contentTargetingUserSegmentId,
				false);

		Assert.assertNotNull(segmentsEntry);

		Criteria criteriaObj = segmentsEntry.getCriteriaObj();

		Assert.assertNotNull(criteriaObj);

		Criteria.Criterion criterion = criteriaObj.getCriterion(
			"user-organization");

		Assert.assertNotNull(criterion);

		Assert.assertEquals(
			Criteria.Conjunction.AND,
			Criteria.Conjunction.parse(criterion.getConjunction()));
		Assert.assertEquals(
			"(organizationId eq '12345')", criterion.getFilterString());
	}

	protected void createContentTargetingTables()
		throws IOException, SQLException {

		StringBundler sb = new StringBundler(6);

		sb.append("create table CT_RuleInstance (uuid_ VARCHAR (75) null, ");
		sb.append("ruleInstanceId LONG not null primary key, groupId LONG, ");
		sb.append("companyId LONG, userId LONG, userName VARCHAR(75) null, ");
		sb.append("createDate DATE null, modifiedDate DATE null, ");
		sb.append("userSegmentId LONG, ruleKey VARCHAR(75) null, ");
		sb.append("displayOrder INTEGER, typeSettings TEXT null)");

		_db.runSQL(sb.toString());

		sb = new StringBundler(6);

		sb.append("create table CT_UserSegment (uuid_ VARCHAR (75) null, ");
		sb.append("userSegmentId LONG not null primary key, groupId LONG, ");
		sb.append("companyId LONG, userId LONG, userName VARCHAR(75) null, ");
		sb.append("createDate DATE null, modifiedDate DATE null, ");
		sb.append("lastPublishDate DATE null, name STRING null, description ");
		sb.append("STRING null)");

		_db.runSQL(sb.toString());
	}

	protected void dropContentTargetingTables() throws Exception {
		try (Connection con = DataAccess.getConnection()) {
			DBInspector dbInspector = new DBInspector(con);

			if (dbInspector.hasTable("CT_RuleInstance")) {
				_db.runSQL("drop table CT_RuleInstance");
			}

			if (dbInspector.hasTable("CT_UserSegment")) {
				_db.runSQL("drop table CT_UserSegment");
			}
		}
	}

	protected void insertContentTargetingRuleInstance(
			long contentTargetingUserSegmentId, String ruleKey,
			String typeSettings)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append("insert into CT_RuleInstance(ruleInstanceId, groupId, ");
		sb.append("companyId, userId, userName, createDate, modifiedDate, ");
		sb.append("userSegmentId, ruleKey, typeSettings) values (?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?, ?)");

		String sql = sb.toString();

		try (Connection con = DataAccess.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, -1L);
			ps.setLong(2, _group.getGroupId());
			ps.setLong(3, _group.getCompanyId());
			ps.setLong(4, TestPropsValues.getUserId());
			ps.setString(5, TestPropsValues.getUser().getFullName());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			ps.setLong(8, contentTargetingUserSegmentId);
			ps.setString(9, ruleKey);
			ps.setString(10, typeSettings);

			ps.executeUpdate();
		}
	}

	protected void insertContentTargetingUserSegment(
			long contentTargetingUserSegmentId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("insert into CT_UserSegment(userSegmentId, groupId, ");
		sb.append("companyId, userId, userName, createDate, modifiedDate, ");
		sb.append("name, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		String sql = sb.toString();

		try (Connection con = DataAccess.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, contentTargetingUserSegmentId);
			ps.setLong(2, _group.getGroupId());
			ps.setLong(3, _group.getCompanyId());
			ps.setLong(4, TestPropsValues.getUserId());
			ps.setString(5, TestPropsValues.getUser().getFullName());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			ps.setString(
				8,
				LocalizationUtil.updateLocalization(
					nameMap, StringPool.BLANK, "Name",
					UpgradeProcessUtil.getDefaultLanguageId(
						_group.getCompanyId())));
			ps.setString(
				9,
				LocalizationUtil.updateLocalization(
					descriptionMap, StringPool.BLANK, "Description",
					UpgradeProcessUtil.getDefaultLanguageId(
						_group.getCompanyId())));

			ps.executeUpdate();
		}
	}

	protected void setUpUpgradeContentTargeting() {
		Registry registry = RegistryUtil.getRegistry();

		UpgradeStepRegistrator upgradeStepRegistror = registry.getService(
			"com.liferay.segments.internal.upgrade.SegmentsServiceUpgrade");

		upgradeStepRegistror.register(
			new UpgradeStepRegistrator.Registry() {

				@Override
				public void register(
					String bundleSymbolicName, String fromSchemaVersionString,
					String toSchemaVersionString, UpgradeStep... upgradeSteps) {

					register(
						fromSchemaVersionString, toSchemaVersionString,
						upgradeSteps);
				}

				@Override
				public void register(
					String fromSchemaVersionString,
					String toSchemaVersionString, UpgradeStep... upgradeSteps) {

					for (UpgradeStep upgradeStep : upgradeSteps) {
						Class<?> clazz = upgradeStep.getClass();

						if (Objects.equals(clazz.getName(), _CLASS_NAME)) {
							_upgradeContentTargeting =
								(UpgradeProcess)upgradeStep;
						}
					}
				}

			});
	}

	private String _getCustomFieldFilterString(
		ExpandoColumn expandoColumn, String expandoValue) {

		StringBundler sb = new StringBundler(7);

		sb.append("(customField/_");
		sb.append(expandoColumn.getColumnId());
		sb.append("_");
		sb.append(FriendlyURLNormalizerUtil.normalize(expandoColumn.getName()));
		sb.append(" eq '");
		sb.append(expandoValue);
		sb.append("')");

		return sb.toString();
	}

	private String _getCustomFieldRuleTypeSettings(
		ExpandoColumn expandoColumn, String expandoValue) {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("attributeName", expandoColumn.getName());
		jsonObj.put("value", expandoValue);

		return jsonObj.toString();
	}

	private static final String _CLASS_NAME =
		"com.liferay.segments.internal.upgrade.v0_0_1.UpgradeContentTargeting";

	private static DB _db;

	@Inject
	private static SegmentsEntryLocalService _segmentsEntryLocalService;

	@DeleteAfterTestRun
	private ExpandoTable _expandoTable;

	@DeleteAfterTestRun
	private Group _group;

	private UpgradeProcess _upgradeContentTargeting;

}