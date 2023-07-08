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

package com.liferay.portal.spring.hibernate;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.NewTransactionLifecycleListener;
import com.liferay.portal.kernel.transaction.TransactionAttribute;
import com.liferay.portal.kernel.transaction.TransactionLifecycleListener;
import com.liferay.portal.kernel.transaction.TransactionStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

/**
 * @author Shuyang Zhou
 */
public class LastSessionRecorderUtil {

	public static final TransactionLifecycleListener
		TRANSACTION_LIFECYCLE_LISTENER = new NewTransactionLifecycleListener() {

			@Override
			protected void doCreated(
				TransactionAttribute transactionAttribute,
				TransactionStatus transactionStatus) {

				syncLastSessionState(true);
			}

		};

	public static void syncLastSessionState(boolean portalSessionOnly) {
		Session session = _lastSessionThreadLocal.get();

		if (session != null) {
			_syncSessionState(session);
		}

		if (!portalSessionOnly) {
			List<Session> sessions = _portletSessionsThreadLocal.get();

			Iterator<Session> iterator = sessions.iterator();

			while (iterator.hasNext()) {
				_syncSessionState(iterator.next());
			}
		}
	}

	protected static void addPortletSession(Session session) {
		List<Session> sessions = _portletSessionsThreadLocal.get();

		sessions.add(session);
	}

	protected static void setLastSession(Session session) {
		_lastSessionThreadLocal.set(session);
	}

	private static void _syncSessionState(Session session) {
		if (session.isOpen()) {
			try {
				session.flush();

				session.clear();
			}
			catch (Exception exception) {
				throw new SystemException(exception);
			}
		}
	}

	private static final ThreadLocal<Session> _lastSessionThreadLocal =
		new CentralizedThreadLocal<>(
			LastSessionRecorderUtil.class.getName() +
				"._lastSessionThreadLocal");
	private static final ThreadLocal<List<Session>>
		_portletSessionsThreadLocal = new CentralizedThreadLocal<>(
			LastSessionRecorderUtil.class.getName() +
				"._portletSessionsThreadLocal",
			ArrayList::new);

}