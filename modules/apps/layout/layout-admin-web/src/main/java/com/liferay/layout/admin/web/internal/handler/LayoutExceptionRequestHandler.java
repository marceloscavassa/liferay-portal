/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.handler;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.friendly.url.exception.DuplicateFriendlyURLEntryException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.LayoutNameException;
import com.liferay.portal.kernel.exception.LayoutTypeException;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.LayoutTypeControllerTracker;

import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = LayoutExceptionRequestHandler.class)
public class LayoutExceptionRequestHandler {

	public void handleException(
			ActionRequest actionRequest, ActionResponse actionResponse,
			Exception exception)
		throws Exception {

		if ((exception instanceof ModelListenerException) &&
			(exception.getCause() instanceof PortalException)) {

			_handlePortalException(
				actionRequest, actionResponse,
				(PortalException)exception.getCause());
		}
		else if (exception instanceof PortalException) {
			_handlePortalException(
				actionRequest, actionResponse, (PortalException)exception);
		}

		throw exception;
	}

	private String _handleLayoutTypeException(
		ActionRequest actionRequest, int exceptionType) {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String errorMessage = "pages-of-type-x-cannot-be-selected";

		if (exceptionType == LayoutTypeException.FIRST_LAYOUT) {
			errorMessage = "the-first-page-cannot-be-of-type-x";
		}

		String type = ParamUtil.getString(actionRequest, "type");

		LayoutTypeController layoutTypeController =
			LayoutTypeControllerTracker.getLayoutTypeController(type);

		ResourceBundle layoutTypeResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(),
			layoutTypeController.getClass());

		String layoutTypeName = _language.get(
			layoutTypeResourceBundle, "layout.types." + type);

		return _language.format(
			themeDisplay.getRequest(), errorMessage, layoutTypeName);
	}

	private void _handlePortalException(
			ActionRequest actionRequest, ActionResponse actionResponse,
			PortalException portalException)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(portalException);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String errorMessage = null;

		if (portalException instanceof AssetCategoryException) {
			AssetCategoryException assetCategoryException =
				(AssetCategoryException)portalException;

			AssetVocabulary assetVocabulary =
				assetCategoryException.getVocabulary();

			String assetVocabularyTitle = StringPool.BLANK;

			if (assetVocabulary != null) {
				assetVocabularyTitle = assetVocabulary.getTitle(
					themeDisplay.getLocale());
			}

			if (assetCategoryException.getType() ==
					AssetCategoryException.AT_LEAST_ONE_CATEGORY) {

				errorMessage = _language.format(
					themeDisplay.getRequest(),
					"please-select-at-least-one-category-for-x",
					assetVocabularyTitle);
			}
			else if (assetCategoryException.getType() ==
						AssetCategoryException.TOO_MANY_CATEGORIES) {

				errorMessage = _language.format(
					themeDisplay.getRequest(),
					"you-cannot-select-more-than-one-category-for-x",
					assetVocabularyTitle);
			}
		}
		else if (portalException instanceof
					DuplicateFriendlyURLEntryException) {

			errorMessage = _language.get(
				themeDisplay.getRequest(),
				"the-friendly-url-is-already-in-use.-please-enter-a-unique-" +
					"friendly-url");
		}
		else if (portalException instanceof LayoutNameException) {
			LayoutNameException layoutNameException =
				(LayoutNameException)portalException;

			if (layoutNameException.getType() == LayoutNameException.TOO_LONG) {
				errorMessage = _language.format(
					themeDisplay.getRequest(),
					"page-name-cannot-exceed-x-characters",
					ModelHintsUtil.getMaxLength(
						Layout.class.getName(), "friendlyURL"));
			}
			else {
				errorMessage = _language.get(
					themeDisplay.getRequest(),
					"please-enter-a-valid-name-for-the-page");
			}
		}
		else if (portalException instanceof LayoutTypeException) {
			LayoutTypeException layoutTypeException =
				(LayoutTypeException)portalException;

			if ((layoutTypeException.getType() ==
					LayoutTypeException.FIRST_LAYOUT) ||
				(layoutTypeException.getType() ==
					LayoutTypeException.NOT_INSTANCEABLE)) {

				errorMessage = _handleLayoutTypeException(
					actionRequest, layoutTypeException.getType());
			}
		}
		else if (portalException instanceof PrincipalException) {
			errorMessage = _language.get(
				themeDisplay.getRequest(),
				"you-do-not-have-the-required-permissions");
		}

		if (Validator.isNull(errorMessage)) {
			errorMessage = _language.get(
				themeDisplay.getRequest(), "an-unexpected-error-occurred");

			_log.error(portalException);
		}

		JSONObject jsonObject = JSONUtil.put("errorMessage", errorMessage);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutExceptionRequestHandler.class);

	@Reference
	private Language _language;

}