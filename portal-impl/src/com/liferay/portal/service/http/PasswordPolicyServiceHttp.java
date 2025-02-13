/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.PasswordPolicyServiceUtil;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>PasswordPolicyServiceUtil</code> service
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
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PasswordPolicyServiceHttp {

	public static com.liferay.portal.kernel.model.PasswordPolicy
			addPasswordPolicy(
				HttpPrincipal httpPrincipal, String name, String description,
				boolean changeable, boolean changeRequired, long minAge,
				boolean checkSyntax, boolean allowDictionaryWords,
				int minAlphanumeric, int minLength, int minLowerCase,
				int minNumbers, int minSymbols, int minUpperCase, String regex,
				boolean history, int historyCount, boolean expireable,
				long maxAge, long warningTime, int graceLimit, boolean lockout,
				int maxFailure, long lockoutDuration, long resetFailureCount,
				long resetTicketMaxAge,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PasswordPolicyServiceUtil.class, "addPasswordPolicy",
				_addPasswordPolicyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, description, changeable, changeRequired,
				minAge, checkSyntax, allowDictionaryWords, minAlphanumeric,
				minLength, minLowerCase, minNumbers, minSymbols, minUpperCase,
				regex, history, historyCount, expireable, maxAge, warningTime,
				graceLimit, lockout, maxFailure, lockoutDuration,
				resetFailureCount, resetTicketMaxAge, serviceContext);

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

			return (com.liferay.portal.kernel.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deletePasswordPolicy(
			HttpPrincipal httpPrincipal, long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PasswordPolicyServiceUtil.class, "deletePasswordPolicy",
				_deletePasswordPolicyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, passwordPolicyId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.PasswordPolicy
			fetchPasswordPolicy(
				HttpPrincipal httpPrincipal, long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PasswordPolicyServiceUtil.class, "fetchPasswordPolicy",
				_fetchPasswordPolicyParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, passwordPolicyId);

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

			return (com.liferay.portal.kernel.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.PasswordPolicy>
		search(
			HttpPrincipal httpPrincipal, long companyId, String name, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.PasswordPolicy>
					orderByComparator) {

		try {
			MethodKey methodKey = new MethodKey(
				PasswordPolicyServiceUtil.class, "search",
				_searchParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, name, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.portal.kernel.model.PasswordPolicy>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int searchCount(
		HttpPrincipal httpPrincipal, long companyId, String name) {

		try {
			MethodKey methodKey = new MethodKey(
				PasswordPolicyServiceUtil.class, "searchCount",
				_searchCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.model.PasswordPolicy
			updatePasswordPolicy(
				HttpPrincipal httpPrincipal, long passwordPolicyId, String name,
				String description, boolean changeable, boolean changeRequired,
				long minAge, boolean checkSyntax, boolean allowDictionaryWords,
				int minAlphanumeric, int minLength, int minLowerCase,
				int minNumbers, int minSymbols, int minUpperCase, String regex,
				boolean history, int historyCount, boolean expireable,
				long maxAge, long warningTime, int graceLimit, boolean lockout,
				int maxFailure, long lockoutDuration, long resetFailureCount,
				long resetTicketMaxAge,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PasswordPolicyServiceUtil.class, "updatePasswordPolicy",
				_updatePasswordPolicyParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, passwordPolicyId, name, description, changeable,
				changeRequired, minAge, checkSyntax, allowDictionaryWords,
				minAlphanumeric, minLength, minLowerCase, minNumbers,
				minSymbols, minUpperCase, regex, history, historyCount,
				expireable, maxAge, warningTime, graceLimit, lockout,
				maxFailure, lockoutDuration, resetFailureCount,
				resetTicketMaxAge, serviceContext);

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

			return (com.liferay.portal.kernel.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PasswordPolicyServiceHttp.class);

	private static final Class<?>[] _addPasswordPolicyParameterTypes0 =
		new Class[] {
			String.class, String.class, boolean.class, boolean.class,
			long.class, boolean.class, boolean.class, int.class, int.class,
			int.class, int.class, int.class, int.class, String.class,
			boolean.class, int.class, boolean.class, long.class, long.class,
			int.class, boolean.class, int.class, long.class, long.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deletePasswordPolicyParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _fetchPasswordPolicyParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _searchParameterTypes3 = new Class[] {
		long.class, String.class, int.class, int.class,
		com.liferay.portal.kernel.util.OrderByComparator.class
	};
	private static final Class<?>[] _searchCountParameterTypes4 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _updatePasswordPolicyParameterTypes5 =
		new Class[] {
			long.class, String.class, String.class, boolean.class,
			boolean.class, long.class, boolean.class, boolean.class, int.class,
			int.class, int.class, int.class, int.class, int.class, String.class,
			boolean.class, int.class, boolean.class, long.class, long.class,
			int.class, boolean.class, int.class, long.class, long.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};

}