<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "merge-tags"));
%>

<portlet:actionURL name="mergeTag" var="mergeURL">
	<portlet:param name="mvcPath" value="/merge_tag.jsp" />
</portlet:actionURL>

<liferay-frontend:edit-form
	action="<%= mergeURL %>"
	method="post"
	name="fm"
	onSubmit="event.preventDefault();"
>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= scopeGroupId %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset>
			<div class="button-holder">
				<liferay-asset:asset-tags-selector
					addCallback="onAddTag"
					allowAddEntry="<%= false %>"
					hiddenInput="mergeTagNames"
					id="assetTagsSelector"
					removeCallback="onRemoveTag"
					tagNames="<%= StringUtil.merge(assetTagsDisplayContext.getMergeTagNames()) %>"
				/>
			</div>

			<aui:select cssClass="target-tag" label="into-this-tag" name="targetTagName">

				<%
				for (String tagName : assetTagsDisplayContext.getMergeTagNames()) {
				%>

					<aui:option label="<%= tagName %>" />

				<%
				}
				%>

			</aui:select>
		</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			redirect="<%= redirect %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script sandbox="<%= true %>">
	var targetTagNameSelect = document.getElementById(
		'<portlet:namespace />targetTagName'
	);

	if (targetTagNameSelect) {
		window['<portlet:namespace />onAddTag'] = function (item) {
			var value = item.value;

			if (value !== undefined) {
				targetTagNameSelect.append(
					Liferay.Util.sub(
						'<option value="{0}">{1}</option>',
						value,
						value
					)
				);
			}
		};

		window['<portlet:namespace />onRemoveTag'] = function (item) {
			var value = item.value;

			if (value !== undefined) {
				var targetTagNameOption = targetTagNameSelect.querySelector(
					'option[value="' + value + '"]'
				);

				if (targetTagNameOption) {
					targetTagNameOption.remove();
				}
			}
		};
	}

	var form = document.<portlet:namespace />fm;
	var mergeTagNamesInputs = document.getElementsByName(
		'<portlet:namespace />mergeTagNames'
	);

	if (form && mergeTagNamesInputs && targetTagNameSelect) {
		form.addEventListener('submit', (event) => {
			var mergeTagNames = Array.from(mergeTagNamesInputs).map(
				(mergeTagNamesInput) => {
					return mergeTagNamesInput.value;
				}
			);

			if (mergeTagNames.length < 2) {
				Liferay.Util.openAlertModal({
					message:
						'<liferay-ui:message arguments="2" key="please-choose-at-least-x-tags" />',
				});

				return;
			}

			var mergeText = Liferay.Util.sub(
				'<liferay-ui:message key="are-you-sure-you-want-to-merge-x-into-x" />',
				mergeTagNames,
				targetTagNameSelect.value
			);

			Liferay.Util.openConfirmModal({
				message: mergeText,
				onConfirm: (isConfirmed) => {
					if (isConfirmed) {
						submitForm(form);
					}
				},
			});
		});
	}
</aui:script>