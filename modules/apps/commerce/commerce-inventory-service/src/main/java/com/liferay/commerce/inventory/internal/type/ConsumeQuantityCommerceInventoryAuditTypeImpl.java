/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.internal.type;

import com.liferay.commerce.inventory.constants.CommerceInventoryConstants;
import com.liferay.commerce.inventory.type.CommerceInventoryAuditType;
import com.liferay.commerce.inventory.type.constants.CommerceInventoryAuditTypeConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "commerce.inventory.audit.type.key=" + CommerceInventoryConstants.AUDIT_TYPE_CONSUME_QUANTITY,
	service = CommerceInventoryAuditType.class
)
public class ConsumeQuantityCommerceInventoryAuditTypeImpl
	implements CommerceInventoryAuditType {

	@Override
	public String formatLog(long userId, String context, Locale locale)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject(context);

		User user = _userLocalService.getUserById(userId);

		return _language.format(
			locale, "x-created-shipment-x-consuming-quantity",
			new Object[] {
				user.getFullName(),
				jsonObject.get(CommerceInventoryAuditTypeConstants.SHIPMENT_ID)
			});
	}

	@Override
	public String formatQuantity(int quantity, Locale locale) {
		return StringPool.MINUS + StringPool.SPACE + quantity;
	}

	@Override
	public String getLog(Map<String, String> context) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		for (Map.Entry<String, String> entry : context.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}

		return jsonObject.toString();
	}

	@Override
	public String getType() {
		return CommerceInventoryConstants.AUDIT_TYPE_CONSUME_QUANTITY;
	}

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private UserLocalService _userLocalService;

}