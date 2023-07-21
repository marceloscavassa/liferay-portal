/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index.creation.activator;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.uuid.PortalUUID;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.tuning.rankings.web.internal.background.task.RankingIndexCreationBackgroundTaskExecutor;
import com.liferay.portal.search.tuning.rankings.web.internal.index.importer.SingleIndexToMultipleIndexImporter;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Wade Cao
 */
public class RankingIndexCreationBundleActivatorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_rankingIndexCreationBundleActivator =
			new RankingIndexCreationBundleActivator();

		ReflectionTestUtil.setFieldValue(
			_rankingIndexCreationBundleActivator, "_backgroundTaskManager",
			_backgroundTaskManager);
		ReflectionTestUtil.setFieldValue(
			_rankingIndexCreationBundleActivator, "_portalUUID", _portalUUID);
		ReflectionTestUtil.setFieldValue(
			_rankingIndexCreationBundleActivator,
			"_rankingIndexRenameBackgroundTaskExecutor",
			_rankingIndexRenameBackgroundTaskExecutor);
		ReflectionTestUtil.setFieldValue(
			_rankingIndexCreationBundleActivator, "_searchEngineInformation",
			_searchEngineInformation);
		ReflectionTestUtil.setFieldValue(
			_rankingIndexCreationBundleActivator,
			"_singleIndexToMultipleIndexImporter",
			_singleIndexToMultipleIndexImporter);
	}

	@Test
	public void testActivatorSingleIndexToMultipleIndexImporterFalse()
		throws Exception {

		_setUpSingleIndexToMultipleIndexImporter(false);

		_rankingIndexCreationBundleActivator.activate();

		Mockito.verify(
			_singleIndexToMultipleIndexImporter, Mockito.times(1)
		).needImport();
		Mockito.verify(
			_backgroundTaskManager, Mockito.times(0)
		).addBackgroundTask(
			Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(),
			Mockito.anyString(), Mockito.anyMap(), Mockito.any()
		);
	}

	@Test
	public void testActivatorSingleIndexToMultipleIndexImporterTrue()
		throws Exception {

		_setUpSingleIndexToMultipleIndexImporter(true);

		_rankingIndexCreationBundleActivator.activate();

		Mockito.verify(
			_singleIndexToMultipleIndexImporter, Mockito.times(1)
		).needImport();
		Mockito.verify(
			_backgroundTaskManager, Mockito.times(1)
		).addBackgroundTask(
			Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(),
			Mockito.anyString(), Mockito.anyMap(), Mockito.any()
		);
	}

	private void _setUpSingleIndexToMultipleIndexImporter(boolean exist) {
		Mockito.doReturn(
			exist
		).when(
			_singleIndexToMultipleIndexImporter
		).needImport();
	}

	private final BackgroundTaskManager _backgroundTaskManager = Mockito.mock(
		BackgroundTaskManager.class);
	private final PortalUUID _portalUUID = Mockito.mock(PortalUUID.class);
	private RankingIndexCreationBundleActivator
		_rankingIndexCreationBundleActivator;
	private final RankingIndexCreationBackgroundTaskExecutor
		_rankingIndexRenameBackgroundTaskExecutor = Mockito.mock(
			RankingIndexCreationBackgroundTaskExecutor.class);
	private final SearchEngineInformation _searchEngineInformation =
		Mockito.mock(SearchEngineInformation.class);
	private final SingleIndexToMultipleIndexImporter
		_singleIndexToMultipleIndexImporter = Mockito.mock(
			SingleIndexToMultipleIndexImporter.class);

}