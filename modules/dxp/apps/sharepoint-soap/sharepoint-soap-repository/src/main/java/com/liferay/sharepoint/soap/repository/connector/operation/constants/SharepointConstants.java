/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharepoint.soap.repository.connector.operation.constants;

import com.liferay.petra.string.StringPool;

import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @author Iván Zaera
 */
public class SharepointConstants {

	public static final int BATCH_METHOD_ID_DEFAULT = 0;

	public static final String FS_OBJ_TYPE_FILE = "0";

	public static final String FS_OBJ_TYPE_FOLDER = "1";

	public static final String NUMERIC_STATUS_SUCCESS = "0x00000000";

	public static final String PATTERN_MULTI_VALUE_SEPARATOR = Pattern.quote(
		";#");

	public static final String ROW_LIMIT_DEFAULT = StringPool.BLANK;

	public static final String SHAREPOINT_OBJECT_DATE_FORMAT_PATTERN =
		"yyyy-MM-dd HH:mm:ss";

	public static final TimeZone SHAREPOINT_OBJECT_TIME_ZONE =
		TimeZone.getTimeZone("UTC");

	public static final String SYMBOLIC_STATUS_NO_RESULTS_FOUND =
		"ERROR_NO_RESULTS_FOUND";

	public static final String SYMBOLIC_STATUS_SUCCESS = "SUCCESS";

	public static final String URL_SOURCE_NONE = StringPool.SPACE;

	public static final String VIEW_DEFAULT = StringPool.BLANK;

}