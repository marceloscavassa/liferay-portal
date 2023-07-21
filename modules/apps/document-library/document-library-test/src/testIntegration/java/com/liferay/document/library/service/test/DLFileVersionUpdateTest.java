/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.test.util.BaseDLAppTestCase;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Date;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@RunWith(Arquillian.class)
public class DLFileVersionUpdateTest extends BaseDLAppTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testWithExtensionWithContent() throws Exception {
		testVersionUpdate(
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN,
			_FULL_FILE_NAME, CONTENT.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithExtensionWithoutContent() throws Exception {
		testVersionUpdate(
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN,
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithoutExtensionWithContent() throws Exception {
		testVersionUpdate(
			_BASE_FILE_NAME, _ZERO_BYTES, ContentTypes.APPLICATION_OCTET_STREAM,
			_BASE_FILE_NAME, CONTENT.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithoutExtensionWithoutContent() throws Exception {
		testVersionUpdate(
			_BASE_FILE_NAME, _ZERO_BYTES, ContentTypes.APPLICATION_OCTET_STREAM,
			_BASE_FILE_NAME, _ZERO_BYTES,
			ContentTypes.APPLICATION_OCTET_STREAM);
	}

	protected void testVersionUpdate(
			String addFileName, byte[] addBytes, String addMimeType,
			String updateFileName, byte[] updateBytes, String updateMimeType)
		throws PortalException {

		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			null, group.getGroupId(), parentFolder.getFolderId(), addFileName,
			addMimeType, addFileName, null, description, changeLog, addBytes,
			null, null, serviceContext);

		fileEntry = DLAppServiceUtil.updateFileEntry(
			fileEntry.getFileEntryId(), updateFileName, updateMimeType,
			updateFileName, null, description, changeLog,
			DLVersionNumberIncrease.MINOR, updateBytes, new Date(), new Date(),
			serviceContext);

		FileVersion fileVersion = fileEntry.getFileVersion();

		Assert.assertEquals("1.1", fileVersion.getVersion());
		Assert.assertEquals(updateMimeType, fileVersion.getMimeType());
		Assert.assertEquals(updateBytes.length, fileVersion.getSize());
		Assert.assertEquals(
			fileVersion.getExtension(), fileEntry.getExtension());
		Assert.assertEquals(fileVersion.getMimeType(), fileEntry.getMimeType());
		Assert.assertEquals(fileVersion.getSize(), fileEntry.getSize());
		Assert.assertEquals(
			fileVersion.getExpirationDate(), fileEntry.getExpirationDate());
		Assert.assertEquals(
			fileVersion.getReviewDate(), fileEntry.getReviewDate());
	}

	private static final String _BASE_FILE_NAME = "Test";

	private static final String _FULL_FILE_NAME = "Test.txt";

	private static final byte[] _ZERO_BYTES = new byte[0];

}