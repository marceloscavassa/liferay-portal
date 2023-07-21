/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.antivirus;

/**
 * @author Raymond Augé
 */
public class AntivirusVirusFoundException extends AntivirusScannerException {

	public AntivirusVirusFoundException(String msg, String virusName) {
		super(msg, AntivirusScannerException.VIRUS_DETECTED);

		_virusName = virusName;
	}

	public String getVirusName() {
		return _virusName;
	}

	private final String _virusName;

}