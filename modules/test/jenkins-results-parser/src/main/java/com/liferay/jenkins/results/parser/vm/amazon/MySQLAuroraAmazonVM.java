/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.vm.amazon;

/**
 * @author Kiyoshi Lee
 */
public class MySQLAuroraAmazonVM extends AuroraAmazonVM {

	protected MySQLAuroraAmazonVM(
		String awsAccessKeyId, String awsSecretAccessKey, String dbInstanceId) {

		super(awsAccessKeyId, awsSecretAccessKey, dbInstanceId);
	}

	protected MySQLAuroraAmazonVM(
		String awsAccessKeyId, String awsSecretAccessKey, String dbClusterId,
		String dbInstanceClass, String dbInstanceId) {

		super(
			awsAccessKeyId, awsSecretAccessKey, dbClusterId, "aurora",
			"5.6.10a", dbInstanceClass, dbInstanceId, "password", "root");
	}

	protected MySQLAuroraAmazonVM(
		String awsAccessKeyId, String awsSecretAccessKey, String dbClusterId,
		String dbEngine, String dbEngineVersion, String dbInstanceClass,
		String dbInstanceId) {

		super(
			awsAccessKeyId, awsSecretAccessKey, dbClusterId, dbEngine,
			dbEngineVersion, dbInstanceClass, dbInstanceId, "password", "root");
	}

	protected MySQLAuroraAmazonVM(
		String awsAccessKeyId, String awsSecretAccessKey, String dbClusterId,
		String dbEngine, String dbEngineVersion, String dbInstanceClass,
		String dbInstanceId, String dbPassword, String dbUsername) {

		super(
			awsAccessKeyId, awsSecretAccessKey, dbClusterId, dbEngine,
			dbEngineVersion, dbInstanceClass, dbInstanceId, dbPassword,
			dbUsername);
	}

}