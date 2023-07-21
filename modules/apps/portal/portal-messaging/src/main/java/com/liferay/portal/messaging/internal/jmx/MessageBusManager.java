/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal.jmx;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"jmx.objectname=com.liferay.portal.messaging:classification=message_bus,name=MessageBusManager",
		"jmx.objectname.cache.key=MessageBusManager"
	},
	service = DynamicMBean.class
)
public class MessageBusManager
	extends StandardMBean implements MessageBusManagerMBean {

	public MessageBusManager() throws NotCompliantMBeanException {
		super(MessageBusManagerMBean.class);
	}

	@Override
	public int getDestinationCount() {
		return _mbeanServiceRegistrations.size();
	}

	@Override
	public int getMessageListenerCount(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);

		if (destination == null) {
			return 0;
		}

		return destination.getMessageListenerCount();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		for (Destination destination : _queuedDestinations) {
			addDestination(destination);
		}

		_queuedDestinations.clear();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(destination.name=*)"
	)
	protected void addDestination(Destination destination) {
		if (_bundleContext == null) {
			_queuedDestinations.add(destination);

			return;
		}

		try {
			DestinationStatisticsManager destinationStatisticsManager =
				new DestinationStatisticsManager(destination);

			Dictionary<String, Object> mBeanProperties =
				HashMapDictionaryBuilder.<String, Object>put(
					"jmx.objectname",
					destinationStatisticsManager.getObjectName()
				).put(
					"jmx.objectname.cache.key",
					destinationStatisticsManager.getObjectNameCacheKey()
				).build();

			ServiceRegistration<DynamicMBean> serviceRegistration =
				_bundleContext.registerService(
					DynamicMBean.class, destinationStatisticsManager,
					mBeanProperties);

			_mbeanServiceRegistrations.put(
				destination.getName(), serviceRegistration);
		}
		catch (NotCompliantMBeanException notCompliantMBeanException) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Unable to register destination mbean",
					notCompliantMBeanException);
			}
		}
	}

	@Deactivate
	protected void deactivate() {
		_mbeanServiceRegistrations.clear();
	}

	protected void removeDestination(Destination destination) {
		ServiceRegistration<DynamicMBean> mbeanServiceRegistration =
			_mbeanServiceRegistrations.remove(destination.getName());

		if (mbeanServiceRegistration != null) {
			mbeanServiceRegistration.unregister();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageBusManager.class);

	private BundleContext _bundleContext;
	private final Map<String, ServiceRegistration<DynamicMBean>>
		_mbeanServiceRegistrations = new ConcurrentHashMap<>();

	@Reference
	private MessageBus _messageBus;

	private final Set<Destination> _queuedDestinations =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

}