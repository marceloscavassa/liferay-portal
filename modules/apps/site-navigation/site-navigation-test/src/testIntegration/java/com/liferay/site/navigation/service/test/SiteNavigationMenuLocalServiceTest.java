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

package com.liferay.site.navigation.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.exception.DuplicateSiteNavigationMenuException;
import com.liferay.site.navigation.exception.SiteNavigationMenuNameException;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;
import com.liferay.site.navigation.service.persistence.SiteNavigationMenuUtil;
import com.liferay.site.navigation.util.SiteNavigationMenuTestUtil;
import com.liferay.site.navigation.util.comparator.SiteNavigationMenuNameComparator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pavel Savinov
 */
@RunWith(Arquillian.class)
public class SiteNavigationMenuLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.site.navigation.service"));

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddAutoSiteNavigationMenu() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				SiteNavigationConstants.TYPE_DEFAULT, false, serviceContext);

		SiteNavigationMenu persistedSiteNavigationMenu =
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(siteNavigationMenu, persistedSiteNavigationMenu);
	}

	@Test(expected = DuplicateSiteNavigationMenuException.class)
	public void testAddDuplicateSiteNavigationMenu() throws Exception {
		String navigationMenuName = "Duplicate Name";

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, navigationMenuName);
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, navigationMenuName);
	}

	@Test
	public void testAddSiteNavigationMenu() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(), serviceContext);

		SiteNavigationMenu persistedSiteNavigationMenu =
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(siteNavigationMenu, persistedSiteNavigationMenu);
	}

	@Test
	public void testAddSiteNavigationMenuByType() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				SiteNavigationConstants.TYPE_DEFAULT, serviceContext);

		SiteNavigationMenu persistedSiteNavigationMenu =
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(siteNavigationMenu, persistedSiteNavigationMenu);
	}

	@Test(expected = SiteNavigationMenuNameException.class)
	public void testAddSiteNavigationMenuWithEmptyName() throws Exception {
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, StringPool.BLANK);
	}

	@Test(expected = SiteNavigationMenuNameException.class)
	public void testAddSiteNavigationMenuWithMaxLengthName() throws Exception {
		int nameMaxLength = ModelHintsUtil.getMaxLength(
			SiteNavigationMenu.class.getName(), "name");

		String nameExceedingMaxLength = RandomTestUtil.randomString(
			nameMaxLength + 1);

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, nameExceedingMaxLength);
	}

	@Test(expected = SiteNavigationMenuNameException.class)
	public void testAddSiteNavigationMenuWithNullName() throws Exception {
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, null);
	}

	@Test
	public void testDeleteSiteNavigationMenu() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		SiteNavigationMenuLocalServiceUtil.deleteSiteNavigationMenu(
			siteNavigationMenu);

		Assert.assertNull(
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				siteNavigationMenu.getSiteNavigationMenuId()));
	}

	@Test
	public void testDeleteSiteNavigationMenuBySiteNavigationMenuId()
		throws Exception {

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		SiteNavigationMenuLocalServiceUtil.deleteSiteNavigationMenu(
			siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertNull(
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				siteNavigationMenu.getSiteNavigationMenuId()));
	}

	@Test
	public void testDeleteSiteNavigationMenus() throws Exception {
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		List<SiteNavigationMenu> originalSiteNavigationMenus =
			SiteNavigationMenuUtil.findByGroupId(_group.getGroupId());

		SiteNavigationMenuLocalServiceUtil.deleteSiteNavigationMenus(
			_group.getGroupId());

		List<SiteNavigationMenu> actualSiteNavigationMenus =
			SiteNavigationMenuUtil.findByGroupId(_group.getGroupId());

		Assert.assertEquals(
			actualSiteNavigationMenus.toString(),
			originalSiteNavigationMenus.size() - 2,
			actualSiteNavigationMenus.size());
	}

	@Test
	public void testFetchPrimarySiteNavigationMenu() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchPrimarySiteNavigationMenu(
				_group.getGroupId());

		Assert.assertNull(siteNavigationMenu);

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, SiteNavigationConstants.TYPE_PRIMARY);

		siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchPrimarySiteNavigationMenu(
				_group.getGroupId());

		Assert.assertNotNull(siteNavigationMenu);
	}

	@Test
	public void testFetchSiteNavigationMenuByTypeSecondary() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
				_group.getGroupId(), SiteNavigationConstants.TYPE_SECONDARY);

		Assert.assertNull(siteNavigationMenu);

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, SiteNavigationConstants.TYPE_SECONDARY);

		siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
				_group.getGroupId(), SiteNavigationConstants.TYPE_SECONDARY);

		Assert.assertNotNull(siteNavigationMenu);
	}

	@Test
	public void testFetchSiteNavigationMenuByTypeSocial() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
				_group.getGroupId(), SiteNavigationConstants.TYPE_SOCIAL);

		Assert.assertNull(siteNavigationMenu);

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, SiteNavigationConstants.TYPE_SOCIAL);

		siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
				_group.getGroupId(), SiteNavigationConstants.TYPE_SOCIAL);

		Assert.assertNotNull(siteNavigationMenu);
	}

	@Test
	public void testGetAutoTrueSiteNavigationMenus() throws Exception {
		List<SiteNavigationMenu> originalSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getAutoSiteNavigationMenus(
				_group.getGroupId());

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, true);
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, true);
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, false);

		List<SiteNavigationMenu> actualSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getAutoSiteNavigationMenus(
				_group.getGroupId());

		Assert.assertEquals(
			actualSiteNavigationMenus.toString(),
			originalSiteNavigationMenus.size() + 2,
			actualSiteNavigationMenus.size());
	}

	@Test
	public void testGetSiteNavigationMenus() throws Exception {
		List<SiteNavigationMenu> originalSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenus(
				_group.getGroupId());

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		List<SiteNavigationMenu> actualSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenus(
				_group.getGroupId());

		Assert.assertEquals(
			actualSiteNavigationMenus.toString(),
			originalSiteNavigationMenus.size() + 2,
			actualSiteNavigationMenus.size());
	}

	@Test
	public void testGetSiteNavigationMenusCount() throws Exception {
		int startCount =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenusCount(
				_group.getGroupId());

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		int endCount =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenusCount(
				_group.getGroupId());

		Assert.assertEquals(startCount + 2, endCount);
	}

	@Test
	public void testGetSiteNavigationMenusCountWithKeywords() throws Exception {
		int startCount =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenusCount(
				_group.getGroupId(), "Menu");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "Menu 1");
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "Menu 2");
		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "Test Name");

		int endCount =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenusCount(
				_group.getGroupId(), "Menu");

		Assert.assertEquals(startCount + 2, endCount);
	}

	@Test
	public void testGetSiteNavigationMenusWithOrderByComparatorAndKeywordAsc()
		throws Exception {

		SiteNavigationMenu bbSiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, "bb Menu Name");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, "cc Menu Name");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "aa");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "dd");

		OrderByComparator ascendingComparator =
			new SiteNavigationMenuNameComparator(true);

		List<SiteNavigationMenu> ascSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenus(
				_group.getGroupId(), "Menu Name", QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, ascendingComparator);

		SiteNavigationMenu topSiteNavigationMenu = ascSiteNavigationMenus.get(
			0);

		Assert.assertEquals(topSiteNavigationMenu, bbSiteNavigationMenu);
	}

	@Test
	public void testGetSiteNavigationMenusWithOrderByComparatorAndKeywordDesc()
		throws Exception {

		SiteNavigationMenu bbSiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, "bb Menu Name");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, "cc Menu Name");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "aa");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, "dd");

		OrderByComparator descendingComparator =
			new SiteNavigationMenuNameComparator(false);

		List<SiteNavigationMenu> descSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenus(
				_group.getGroupId(), "Menu Name", QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, descendingComparator);

		SiteNavigationMenu bottomSiteNavigationMenu =
			descSiteNavigationMenus.get(descSiteNavigationMenus.size() - 1);

		Assert.assertEquals(bottomSiteNavigationMenu, bbSiteNavigationMenu);
	}

	@Test
	public void testGetSiteNavigationMenusWithOrderByComparatorAsc()
		throws Exception {

		SiteNavigationMenu aaSiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, "aa Menu Name");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, "bb Menu Name");

		OrderByComparator ascendingComparator =
			new SiteNavigationMenuNameComparator(true);

		List<SiteNavigationMenu> ascSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenus(
				_group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				ascendingComparator);

		SiteNavigationMenu topSiteNavigationMenu = ascSiteNavigationMenus.get(
			0);

		Assert.assertEquals(topSiteNavigationMenu, aaSiteNavigationMenu);
	}

	@Test
	public void testGetSiteNavigationMenusWithOrderByComparatorDesc()
		throws Exception {

		SiteNavigationMenu aasiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, "aa Menu Name");

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, "bb Menu Name");

		OrderByComparator descendingComparator =
			new SiteNavigationMenuNameComparator(false);

		List<SiteNavigationMenu> descSiteNavigationMenus =
			SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenus(
				_group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				descendingComparator);

		SiteNavigationMenu bottomSiteNavigationMenu =
			descSiteNavigationMenus.get(descSiteNavigationMenus.size() - 1);

		Assert.assertEquals(bottomSiteNavigationMenu, aasiteNavigationMenu);
	}

	@Test
	public void testSiteNavigationMenuAutoFalseByDefault() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		siteNavigationMenu = SiteNavigationMenuUtil.fetchByPrimaryKey(
			siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(siteNavigationMenu.isAuto(), false);
	}

	@Test
	public void testSiteNavigationMenuDefaultType() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group);

		siteNavigationMenu = SiteNavigationMenuUtil.fetchByPrimaryKey(
			siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(
			siteNavigationMenu.getType(), SiteNavigationConstants.TYPE_DEFAULT);
	}

	@Test
	public void testUpdateOldSiteNavigationMenuType() throws Exception {
		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, SiteNavigationConstants.TYPE_PRIMARY);

		SiteNavigationMenuTestUtil.addSiteNavigationMenu(
			_group, SiteNavigationConstants.TYPE_PRIMARY);

		siteNavigationMenu = SiteNavigationMenuUtil.fetchByPrimaryKey(
			siteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(
			SiteNavigationConstants.TYPE_DEFAULT, siteNavigationMenu.getType());
	}

	@Test
	public void testUpdateSiteNavigationMenuAuto() throws Exception {
		SiteNavigationMenu originalSiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(_group, false);

		SiteNavigationMenuLocalServiceUtil.updateSiteNavigationMenu(
			originalSiteNavigationMenu.getUserId(),
			originalSiteNavigationMenu.getSiteNavigationMenuId(),
			originalSiteNavigationMenu.getGroupId(),
			originalSiteNavigationMenu.getName(),
			originalSiteNavigationMenu.getType(), true);

		SiteNavigationMenu updatedSiteNavigationMenu =
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				originalSiteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertTrue(updatedSiteNavigationMenu.isAuto());
	}

	@Test
	public void testUpdateSiteNavigationMenuName() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenu originalSiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, "Original Name");

		SiteNavigationMenuLocalServiceUtil.updateSiteNavigationMenu(
			originalSiteNavigationMenu.getUserId(),
			originalSiteNavigationMenu.getSiteNavigationMenuId(),
			"Updated Name", serviceContext);

		SiteNavigationMenu updatedSiteNavigationMenu =
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				originalSiteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(
			"Updated Name", updatedSiteNavigationMenu.getName());
	}

	@Test
	public void testUpdateSiteNavigationMenuType() throws Exception {
		SiteNavigationMenu originalSiteNavigationMenu =
			SiteNavigationMenuTestUtil.addSiteNavigationMenu(
				_group, SiteNavigationConstants.TYPE_DEFAULT);

		SiteNavigationMenuLocalServiceUtil.updateSiteNavigationMenu(
			originalSiteNavigationMenu.getUserId(),
			originalSiteNavigationMenu.getSiteNavigationMenuId(),
			originalSiteNavigationMenu.getGroupId(),
			originalSiteNavigationMenu.getName(),
			SiteNavigationConstants.TYPE_SECONDARY,
			originalSiteNavigationMenu.isAuto());

		SiteNavigationMenu updatedSiteNavigationMenu =
			SiteNavigationMenuUtil.fetchByPrimaryKey(
				originalSiteNavigationMenu.getSiteNavigationMenuId());

		Assert.assertEquals(
			SiteNavigationConstants.TYPE_SECONDARY,
			updatedSiteNavigationMenu.getType());
	}

	@DeleteAfterTestRun
	private Group _group;

}