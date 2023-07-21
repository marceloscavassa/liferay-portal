/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.service.http;

import com.liferay.calendar.service.CalendarNotificationTemplateServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CalendarNotificationTemplateServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Eduardo Lundgren
 * @generated
 */
public class CalendarNotificationTemplateServiceHttp {

	public static com.liferay.calendar.model.CalendarNotificationTemplate
			addCalendarNotificationTemplate(
				HttpPrincipal httpPrincipal, long calendarId,
				com.liferay.calendar.notification.NotificationType
					notificationType,
				String notificationTypeSettings,
				com.liferay.calendar.notification.NotificationTemplateType
					notificationTemplateType,
				String subject, String body,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CalendarNotificationTemplateServiceUtil.class,
				"addCalendarNotificationTemplate",
				_addCalendarNotificationTemplateParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, calendarId, notificationType,
				notificationTypeSettings, notificationTemplateType, subject,
				body, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.calendar.model.CalendarNotificationTemplate)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.calendar.model.CalendarNotificationTemplate
			updateCalendarNotificationTemplate(
				HttpPrincipal httpPrincipal,
				long calendarNotificationTemplateId,
				String notificationTypeSettings, String subject, String body,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CalendarNotificationTemplateServiceUtil.class,
				"updateCalendarNotificationTemplate",
				_updateCalendarNotificationTemplateParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, calendarNotificationTemplateId,
				notificationTypeSettings, subject, body, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.calendar.model.CalendarNotificationTemplate)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarNotificationTemplateServiceHttp.class);

	private static final Class<?>[]
		_addCalendarNotificationTemplateParameterTypes0 = new Class[] {
			long.class,
			com.liferay.calendar.notification.NotificationType.class,
			String.class,
			com.liferay.calendar.notification.NotificationTemplateType.class,
			String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateCalendarNotificationTemplateParameterTypes1 = new Class[] {
			long.class, String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}