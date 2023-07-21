/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.internal.test;

import com.liferay.oauth2.provider.internal.test.annotation.CUSTOM;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.core.Application;

/**
 * @author Carlos Sierra Andrés
 */
public class TestHeadHandlingApplication extends Application {

	@CUSTOM
	public String getCustomString() {
		return "custom";
	}

	@HEAD
	public String getHeadString() {
		return "head";
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	public String getString() {
		return "get";
	}

	@POST
	public String post(String post) {
		return "post";
	}

}