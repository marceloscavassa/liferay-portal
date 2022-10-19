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

package com.liferay.oauth2.provider.rest.internal.endpoint.jwks;

import com.liferay.oauth2.provider.rest.internal.configuration.OAuth2AuthorizationServerConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Raymond Augé
 */
@Component(
	configurationPid = "com.liferay.oauth2.provider.rest.internal.configuration.OAuth2AuthorizationServerConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.OAuth2.Application)",
		"osgi.jaxrs.name=Liferay.Authorization.JWKS", "osgi.jaxrs.resource=true"
	},
	service = OAuth2ProviderJWKSResource.class
)
@Path("/jwks")
public class OAuth2ProviderJWKSResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response jwks() {
		return Response.ok(
			_jwks
		).build();
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		OAuth2AuthorizationServerConfiguration
			oAuth2AuthorizationServerConfiguration =
				ConfigurableUtil.createConfigurable(
					OAuth2AuthorizationServerConfiguration.class, properties);

		JSONObject jwkObject = _jsonFactory.createJSONObject(
			oAuth2AuthorizationServerConfiguration.
				jwtAccessTokenSigningJSONWebKey());

		_jwks = _jsonFactory.createJSONObject(
		).put(
			"keys",
			_jsonFactory.createJSONArray(
			).put(
				_jsonFactory.createJSONObject(
				).put(
					"alg", jwkObject.get("alg")
				).put(
					"e", jwkObject.get("e")
				).put(
					"kid", jwkObject.get("kid")
				).put(
					"kty", jwkObject.get("kty")
				).put(
					"n", jwkObject.get("n")
				).put(
					"use", jwkObject.get("use")
				)
			)
		).toJSONString();
	}

	@Reference
	private JSONFactory _jsonFactory;

	private String _jwks;

}