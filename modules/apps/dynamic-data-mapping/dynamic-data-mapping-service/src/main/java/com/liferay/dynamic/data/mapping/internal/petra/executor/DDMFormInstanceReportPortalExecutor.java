/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.petra.executor;

import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.concurrent.ThreadPoolHandlerAdapter;
import com.liferay.petra.executor.PortalExecutorConfig;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.NamedThreadFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalRunMode;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcos Martins
 */
@Component(service = DDMFormInstanceReportPortalExecutor.class)
public class DDMFormInstanceReportPortalExecutor {

	public <T extends Throwable> void execute(
		UnsafeRunnable<T> unsafeRunnable) {

		if (PortalRunMode.isTestMode()) {
			try {
				unsafeRunnable.run();
			}
			catch (Throwable throwable) {
				_log.error(throwable, throwable);
			}
		}
		else {
			_noticeableExecutorService.submit(
				() -> {
					try {
						unsafeRunnable.run();
					}
					catch (Throwable throwable) {
						_log.error(throwable, throwable);
					}
				});
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_registerPortalExecutorConfig(bundleContext);

		_noticeableExecutorService = _portalExecutorManager.getPortalExecutor(
			DDMFormInstanceReportPortalExecutor.class.getName());
	}

	@Deactivate
	protected void deactivate() {
		_noticeableExecutorService.shutdown();

		_serviceRegistration.unregister();
	}

	private void _registerPortalExecutorConfig(BundleContext bundleContext) {
		PortalExecutorConfig portalExecutorConfig = new PortalExecutorConfig(
			DDMFormInstanceReportPortalExecutor.class.getName(), 1, 1, 60,
			TimeUnit.SECONDS, Integer.MAX_VALUE,
			new NamedThreadFactory(
				DDMFormInstanceReportPortalExecutor.class.getName(),
				Thread.NORM_PRIORITY, PortalClassLoaderUtil.getClassLoader()),
			new ThreadPoolExecutor.AbortPolicy(),
			new ThreadPoolHandlerAdapter() {

				@Override
				public void afterExecute(
					Runnable runnable, Throwable throwable) {

					CentralizedThreadLocal.clearShortLivedThreadLocals();
				}

			});

		_serviceRegistration = bundleContext.registerService(
			PortalExecutorConfig.class, portalExecutorConfig, null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormInstanceReportPortalExecutor.class);

	private NoticeableExecutorService _noticeableExecutorService;

	@Reference
	private PortalExecutorManager _portalExecutorManager;

	private ServiceRegistration<PortalExecutorConfig> _serviceRegistration;

}