/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ticket;

import com.liferay.portal.search.rest.client.dto.v1_0.Suggestion;
import com.liferay.portal.search.rest.client.dto.v1_0.SuggestionsContributorConfiguration;
import com.liferay.portal.search.rest.client.dto.v1_0.SuggestionsContributorResults;
import com.liferay.portal.search.rest.client.pagination.Page;
import com.liferay.portal.search.rest.client.resource.v1_0.SuggestionResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Raymond Augé
 * @author Gregory Amerson
 * @author Allen Ziegenfus
 */
@RestController
public class TicketRestController extends BaseRestController {

	@PostMapping("/ticket")
	public ResponseEntity<String> post(
			@AuthenticationPrincipal Jwt jwt, @RequestBody String json)
		throws Exception {

		log(jwt, _log, json);

		JSONObject jsonObject = new JSONObject(json);

		JSONObject objectEntryDTOTicketJSONObject = jsonObject.getJSONObject(
			"objectEntryDTOTicket");

		JSONObject propertiesJSONObject =
			objectEntryDTOTicketJSONObject.getJSONObject("properties");

		propertiesJSONObject.put(
			"suggestions",
			_getSuggestionsJSON(propertiesJSONObject.getString("subject")));

		JSONObject ticketStatusJSONObject = propertiesJSONObject.getJSONObject(
			"ticketStatus");

		ticketStatusJSONObject.put("key", "queued");
		ticketStatusJSONObject.remove("name");

		if (_log.isInfoEnabled()) {
			_log.info("Properties: " + propertiesJSONObject.toString(4));
		}

		WebClient.create(
			_lxcDXPServerProtocol + "://" + _lxcDXPMainDomain
		).patch(
		).uri(
			"/o/c/tickets/" + objectEntryDTOTicketJSONObject.getString("id")
		).accept(
			MediaType.APPLICATION_JSON
		).contentType(
			MediaType.APPLICATION_JSON
		).header(
			HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue()
		).bodyValue(
			propertiesJSONObject.toString()
		).retrieve(
		).bodyToMono(
			Void.class
		).block();

		return new ResponseEntity<>(json, HttpStatus.CREATED);
	}

	private String _getSuggestionsJSON(String subject) {
		JSONArray suggestionsJSONArray = new JSONArray();

		SuggestionsContributorConfiguration
			suggestionsContributorConfiguration =
				new SuggestionsContributorConfiguration();

		suggestionsContributorConfiguration.setContributorName("sxpBlueprint");

		suggestionsContributorConfiguration.setDisplayGroupName(
			"Public Nav Search Recommendations");

		suggestionsContributorConfiguration.setSize(3);

		JSONObject attributesJSONObject = new JSONObject();

		attributesJSONObject.put(
			"includeAssetSearchSummary", true
		).put(
			"includeassetURL", true
		).put(
			"sxpBlueprintId", 3628599
		);

		suggestionsContributorConfiguration.setAttributes(attributesJSONObject);

		SuggestionResource.Builder dataDefinitionResourceBuilder =
			SuggestionResource.builder();

		SuggestionResource suggestionResource =
			dataDefinitionResourceBuilder.header(
				HttpHeaders.USER_AGENT, TicketRestController.class.getName()
			).header(
				HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE
			).header(
				HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
			).endpoint(
				"learn.liferay.com", 443, "https"
			).build();

		try {
			Page<SuggestionsContributorResults>
				suggestionsContributorResultsPage =
					suggestionResource.postSuggestionsPage(
						"https://learn.liferay.com", "/search", 3190049L, "",
						1434L, "this-site", subject,
						new SuggestionsContributorConfiguration[] {
							suggestionsContributorConfiguration
						});

			for (SuggestionsContributorResults suggestionsContributorResults :
					suggestionsContributorResultsPage.getItems()) {

				Suggestion[] suggestions =
					suggestionsContributorResults.getSuggestions();

				for (Suggestion suggestion : suggestions) {
					String text = suggestion.getText();

					JSONObject suggestionAttributesJSONObject = new JSONObject(
						String.valueOf(suggestion.getAttributes()));

					String assetURL =
						(String)suggestionAttributesJSONObject.get("assetURL");

					JSONObject suggestionJSONObject = new JSONObject();

					suggestionJSONObject.put(
						"assetURL", "https://learn.liferay.com" + assetURL
					).put(
						"text", text
					);
					suggestionsJSONArray.put(suggestionJSONObject);
				}
			}
		}
		catch (Exception exception) {
			_log.error("Could not retrieve search suggestions", exception);

			// Always return something for the purposes of the workshop

			JSONObject suggestionJSONObject = new JSONObject();

			suggestionJSONObject.put(
				"assetURL", "https://learn.liferay.com"
			).put(
				"text", "learn.liferay.com"
			);

			suggestionsJSONArray.put(suggestionJSONObject);
		}

		return suggestionsJSONArray.toString();
	}

	private static final Log _log = LogFactory.getLog(
		TicketRestController.class);

	@Value("${com.liferay.lxc.dxp.mainDomain}")
	private String _lxcDXPMainDomain;

	@Value("${com.liferay.lxc.dxp.server.protocol}")
	private String _lxcDXPServerProtocol;

}