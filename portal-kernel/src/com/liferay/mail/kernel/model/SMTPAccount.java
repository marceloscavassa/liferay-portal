/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.kernel.model;

/**
 * @author Thiago Moreira
 */
public class SMTPAccount extends Account {

	protected SMTPAccount(String protocol, boolean secure, int port) {
		super(protocol, secure, port);
	}

}