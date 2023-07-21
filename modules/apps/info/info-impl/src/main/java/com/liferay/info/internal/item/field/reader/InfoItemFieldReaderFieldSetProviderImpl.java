/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.item.field.reader;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.field.type.URLInfoFieldType;
import com.liferay.info.item.field.reader.InfoItemFieldReader;
import com.liferay.info.item.field.reader.InfoItemFieldReaderFieldSetProvider;
import com.liferay.info.item.field.reader.InfoItemFieldReaderRegistry;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ContentTypes;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 * @author Jorge Ferrer
 */
@Component(service = InfoItemFieldReaderFieldSetProvider.class)
public class InfoItemFieldReaderFieldSetProviderImpl
	implements InfoItemFieldReaderFieldSetProvider {

	@Override
	public InfoFieldSet getInfoFieldSet(String className) {
		return InfoFieldSet.builder(
		).infoFieldSetEntry(
			unsafeConsumer -> {
				List<InfoItemFieldReader> infoItemFieldReaders =
					_infoItemFieldReaderRegistry.getInfoItemFieldReaders(
						className);

				for (InfoItemFieldReader infoItemFieldReader :
						infoItemFieldReaders) {

					unsafeConsumer.accept(infoItemFieldReader.getInfoField());
				}
			}
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(getClass(), "fields")
		).name(
			"fields"
		).build();
	}

	@Override
	public List<InfoFieldValue<Object>> getInfoFieldValues(
		String className, Object itemObject) {

		List<InfoFieldValue<Object>> infoFieldValues = new ArrayList<>();

		List<InfoItemFieldReader> infoItemFieldReaders =
			_infoItemFieldReaderRegistry.getInfoItemFieldReaders(className);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		for (InfoItemFieldReader infoItemFieldReader : infoItemFieldReaders) {
			InfoField infoField = infoItemFieldReader.getInfoField();
			Object value = infoItemFieldReader.getValue(itemObject);

			if ((serviceContext != null) &&
				(infoField.getInfoFieldType() != URLInfoFieldType.INSTANCE) &&
				(value instanceof String)) {

				try {
					value = SanitizerUtil.sanitize(
						serviceContext.getCompanyId(),
						serviceContext.getScopeGroupId(),
						serviceContext.getUserId(), className, 0,
						ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						(String)value, null);
				}
				catch (SanitizerException sanitizerException) {
					throw new RuntimeException(sanitizerException);
				}
			}

			infoFieldValues.add(new InfoFieldValue<>(infoField, value));
		}

		return infoFieldValues;
	}

	@Reference
	private InfoItemFieldReaderRegistry _infoItemFieldReaderRegistry;

}