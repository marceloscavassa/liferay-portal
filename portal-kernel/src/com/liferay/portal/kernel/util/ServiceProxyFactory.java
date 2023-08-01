/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.memory.FinalizeAction;
import com.liferay.petra.memory.FinalizeManager;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.util.ServiceTrackerFieldUpdaterCustomizer;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Tina Tian
 */
public class ServiceProxyFactory {

	public static <T> T newServiceTrackedInstance(
		Class<T> serviceClass, Class<?> declaringClass, String fieldName,
		boolean blocking) {

		return newServiceTrackedInstance(
			serviceClass, declaringClass, fieldName, null, blocking, false);
	}

	public static <T> T newServiceTrackedInstance(
		Class<T> serviceClass, Class<?> declaringClass, String fieldName,
		boolean blocking, boolean useNullAsDummyService) {

		return newServiceTrackedInstance(
			serviceClass, declaringClass, fieldName, null, blocking,
			useNullAsDummyService);
	}

	public static <T> T newServiceTrackedInstance(
		Class<T> serviceClass, Class<?> declaringClass, String fieldName,
		String filterString, boolean blocking) {

		return newServiceTrackedInstance(
			serviceClass, declaringClass, fieldName, filterString, blocking,
			false);
	}

	public static <T> T newServiceTrackedInstance(
		Class<T> serviceClass, Class<?> declaringClass, String fieldName,
		String filterString, boolean blocking, boolean useNullAsDummyService) {

		try {
			Field field = declaringClass.getDeclaredField(fieldName);

			if (!Modifier.isStatic(field.getModifiers())) {
				throw new IllegalArgumentException(field + " is not static");
			}

			field.setAccessible(true);

			return _newServiceTrackedInstance(
				serviceClass, null, field, filterString, blocking,
				useNullAsDummyService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			return ReflectionUtil.throwException(reflectiveOperationException);
		}
	}

	public static <T, V> T newServiceTrackedInstance(
		Class<T> serviceClass, Class<V> declaringClass, V declaringInstance,
		String fieldName, String filterString, boolean blocking) {

		if (declaringInstance == null) {
			return newServiceTrackedInstance(
				serviceClass, declaringClass, fieldName, filterString, blocking,
				false);
		}

		try {
			Field field = declaringClass.getDeclaredField(fieldName);

			if (Modifier.isStatic(field.getModifiers())) {
				throw new IllegalArgumentException(field + " is static");
			}

			field.setAccessible(true);

			T serviceInstance = null;

			synchronized (declaringInstance) {
				serviceInstance = (T)field.get(declaringInstance);

				if (serviceInstance == null) {
					return _newServiceTrackedInstance(
						serviceClass, declaringInstance, field, filterString,
						blocking, false);
				}
			}

			return serviceInstance;
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			return ReflectionUtil.throwException(reflectiveOperationException);
		}
	}

	private static <T, V> T _newServiceTrackedInstance(
			Class<T> serviceClass, V declaringInstance, Field field,
			String filterString, boolean blocking,
			boolean useNullAsDummyService)
		throws ReflectiveOperationException {

		ServiceTrackerCustomizer<T, T> serviceTrackerCustomizer = null;

		if (blocking) {
			ReentrantLock lock = new ReentrantLock();

			Condition realServiceSet = lock.newCondition();

			T awaitService = (T)ProxyUtil.newProxyInstance(
				serviceClass.getClassLoader(), new Class<?>[] {serviceClass},
				new AwaitServiceInvocationHandler(
					serviceClass, filterString, field, realServiceSet, lock));

			field.set(declaringInstance, awaitService);

			serviceTrackerCustomizer =
				new AwaitServiceTrackerFieldUpdaterCustomizer<>(
					field, declaringInstance, awaitService, realServiceSet,
					lock);
		}
		else {
			T dummyService = null;

			if (!useNullAsDummyService) {
				dummyService = ProxyFactory.newDummyInstance(serviceClass);

				field.set(declaringInstance, dummyService);
			}

			serviceTrackerCustomizer =
				new ServiceTrackerFieldUpdaterCustomizer<>(
					field, declaringInstance, dummyService);
		}

		ServiceTracker<T, T> serviceTracker = _openServiceTracker(
			serviceClass, filterString, serviceTrackerCustomizer);

		if (declaringInstance != null) {
			FinalizeManager.register(
				declaringInstance,
				new CloseServiceTrackerFinalizeAction(serviceTracker),
				FinalizeManager.PHANTOM_REFERENCE_FACTORY);
		}

		return (T)field.get(declaringInstance);
	}

	private static <T> ServiceTracker<T, T> _openServiceTracker(
		Class<T> serviceClass, String filterString,
		ServiceTrackerCustomizer<T, T> serviceTrackerCustomizer) {

		ServiceTracker<T, T> serviceTracker = null;

		String serviceName = serviceClass.getName();

		BundleContext bundleContext = SystemBundleUtil.getBundleContext();

		if (Validator.isNull(filterString)) {
			serviceTracker = new ServiceTracker<>(
				bundleContext, serviceClass, serviceTrackerCustomizer);
		}
		else {
			serviceTracker = new ServiceTracker<>(
				bundleContext,
				SystemBundleUtil.createFilter(
					StringBundler.concat(
						"(&(objectClass=", serviceName,
						StringPool.CLOSE_PARENTHESIS, filterString,
						StringPool.CLOSE_PARENTHESIS)),
				serviceTrackerCustomizer);
		}

		serviceTracker.open();

		return serviceTracker;
	}

	private static final long _TIMEOUT = GetterUtil.getLong(
		SystemProperties.get(ServiceProxyFactory.class.getName() + ".timeout"),
		60000);

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceProxyFactory.class);

	private static class AwaitServiceInvocationHandler
		implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments)
			throws Throwable {

			boolean calledSystemCheckers = false;

			while (true) {
				_lock.lock();

				try {
					Object service = _field.get(null);

					if (!ProxyUtil.isProxyClass(service.getClass()) ||
						(ProxyUtil.getInvocationHandler(service) != this)) {

						try {
							return method.invoke(service, arguments);
						}
						catch (InvocationTargetException
									invocationTargetException) {

							throw invocationTargetException.getCause();
						}
					}

					if (!_realServiceSet.await(
							_TIMEOUT, TimeUnit.MILLISECONDS)) {

						StringBundler sb = new StringBundler(12);

						sb.append("Service \"");
						sb.append(_serviceClass.getName());

						if (Validator.isNotNull(_filterString)) {
							sb.append("{");
							sb.append(_filterString);
							sb.append("}");
						}

						sb.append("\" is unavailable in ");
						sb.append(_TIMEOUT);
						sb.append(" milliseconds while setting field \"");
						sb.append(_field.getName());
						sb.append("\" for class \"");

						Class<?> declaringClass = _field.getDeclaringClass();

						sb.append(declaringClass.getName());

						sb.append("\", will retry...");

						_log.error(sb.toString());

						if (!calledSystemCheckers) {
							SystemCheckerUtil.runSystemCheckers(
								_log::info, _log::warn);

							calledSystemCheckers = true;
						}
					}
				}
				finally {
					_lock.unlock();
				}
			}
		}

		private AwaitServiceInvocationHandler(
			Class<?> serviceClass, String filterString, Field field,
			Condition realServiceSet, Lock lock) {

			_serviceClass = serviceClass;
			_filterString = filterString;
			_field = field;
			_realServiceSet = realServiceSet;
			_lock = lock;
		}

		private final Field _field;
		private final String _filterString;
		private final Lock _lock;
		private final Condition _realServiceSet;
		private final Class<?> _serviceClass;

	}

	private static class AwaitServiceTrackerFieldUpdaterCustomizer<S, T>
		extends ServiceTrackerFieldUpdaterCustomizer<S, T> {

		@Override
		protected void doServiceUpdate(T newService) {
			_lock.lock();

			try {
				super.doServiceUpdate(newService);

				if (newService != _awaitService) {
					_realServiceSet.signalAll();
				}
			}
			finally {
				_lock.unlock();
			}
		}

		private AwaitServiceTrackerFieldUpdaterCustomizer(
			Field serviceField, Object serviceHolder, T awaitService,
			Condition realServiceSet, Lock lock) {

			super(serviceField, serviceHolder, awaitService);

			_awaitService = awaitService;
			_realServiceSet = realServiceSet;
			_lock = lock;
		}

		private final T _awaitService;
		private final Lock _lock;
		private final Condition _realServiceSet;

	}

	private static class CloseServiceTrackerFinalizeAction
		implements FinalizeAction {

		@Override
		public void doFinalize(Reference<?> reference) {
			_serviceTracker.close();
		}

		private CloseServiceTrackerFinalizeAction(
			ServiceTracker<?, ?> serviceTracker) {

			_serviceTracker = serviceTracker;
		}

		private final ServiceTracker<?, ?> _serviceTracker;

	}

}