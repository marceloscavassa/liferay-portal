/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.io;

import com.liferay.petra.lang.ClassResolverUtil;
import com.liferay.petra.string.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mika Koivisto
 */
public class ProtectedObjectInputStream extends ObjectInputStream {

	public ProtectedObjectInputStream(InputStream inputStream)
		throws IOException {

		super(inputStream);
	}

	/**
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	protected Class<?> doResolveClass(ObjectStreamClass objectStreamClass)
		throws ClassNotFoundException, IOException {

		String name = objectStreamClass.getName();

		Thread thread = Thread.currentThread();

		return ClassResolverUtil.resolve(name, thread.getContextClassLoader());
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass objectStreamClass)
		throws ClassNotFoundException, IOException {

		if (_restrictedClassNames.contains(objectStreamClass.getName())) {
			throw new InvalidClassException(
				"Reject resolving of restricted class " +
					objectStreamClass.getName());
		}

		return doResolveClass(objectStreamClass);
	}

	private static final Set<String> _restrictedClassNames;

	static {
		List<String> restrictedClassNames = StringUtil.split(
			System.getProperty(
				ProtectedObjectInputStream.class.getName() +
					".restricted.class.names"));

		_restrictedClassNames = new HashSet<>(restrictedClassNames);
	}

}