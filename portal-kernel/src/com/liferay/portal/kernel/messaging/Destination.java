/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface Destination {

	public boolean addDestinationEventListener(
		DestinationEventListener destinationEventListener);

	public void close();

	public void close(boolean force);

	public void copyDestinationEventListeners(Destination destination);

	public void copyMessageListeners(Destination destination);

	public void destroy();

	public DestinationStatistics getDestinationStatistics();

	public String getDestinationType();

	public int getMessageListenerCount();

	public Set<MessageListener> getMessageListeners();

	public String getName();

	public boolean isRegistered();

	public void open();

	public boolean register(MessageListener messageListener);

	public boolean register(
		MessageListener messageListener, ClassLoader classLoader);

	public boolean removeDestinationEventListener(
		DestinationEventListener destinationEventListener);

	public void removeDestinationEventListeners();

	public void send(Message message);

	public boolean unregister(MessageListener messageListener);

	public void unregisterMessageListeners();

}