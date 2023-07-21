<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "addDomains");
%>

<liferay-frontend:edit-form
	action="javascript:void(0);"
	onSubmit='<%= liferayPortletResponse.getNamespace() + "addDomains();" %>'
>
	<liferay-frontend:edit-form-body>
		<div class="hide" id="<portlet:namespace />domainAlert">
			<clay:alert
				displayType="danger"
				message="please-enter-valid-mail-domains-separated-by-commas"
			/>
		</div>

		<aui:field-wrapper cssClass="form-group">
			<aui:input label="domain" name="domain" />

			<div class="form-text">
				<liferay-ui:message key="for-multiple-domains,-separate-each-domain-by-a-comma" />
			</div>
		</aui:field-wrapper>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			submitLabel="save"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script>
	function <portlet:namespace />addDomains() {
		var domainsInput = document.getElementById('<portlet:namespace />domain');

		var domains = domainsInput.value.split(',');

		// Email domain regex from aui-form-validator.js

		var pattern = new RegExp(
			'^((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$',
			'i'
		);

		for (var i = 0; i < domains.length; i++) {
			var domain = domains[i];

			if (!pattern.test(domain.trim())) {
				var domainAlert = document.getElementById(
					'<portlet:namespace />domainAlert'
				);

				domainAlert.classList.remove('hide');

				domainsInput.focus();

				return;
			}
		}

		var openingLiferay = Liferay.Util.getOpener().Liferay;

		openingLiferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', {
			data: document.getElementById('<portlet:namespace />domain').value,
		});

		openingLiferay.fire('closeModal');
	}
</aui:script>