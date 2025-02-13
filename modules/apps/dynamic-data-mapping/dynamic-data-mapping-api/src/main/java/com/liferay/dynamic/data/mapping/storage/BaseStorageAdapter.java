/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.storage;

import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesTransformer;
import com.liferay.dynamic.data.mapping.util.DocumentLibraryDDMFormFieldValueTransformer;
import com.liferay.dynamic.data.mapping.util.HTMLSanitizerDDMFormFieldValueTransformer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author     Eduardo Lundgren
 * @author     Brian Wing Shun Chan
 * @author     Marcellus Tavares
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public abstract class BaseStorageAdapter implements StorageAdapter {

	@Override
	public long create(
			long companyId, long ddmStructureId, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws StorageException {

		try {
			transformDDMFormValues(ddmFormValues, serviceContext);

			return doCreate(
				companyId, ddmStructureId, ddmFormValues, serviceContext);
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public void deleteByClass(long classPK) throws StorageException {
		try {
			doDeleteByClass(classPK);
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public void deleteByDDMStructure(long ddmStructureId)
		throws StorageException {

		try {
			doDeleteByDDMStructure(ddmStructureId);
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public DDMFormValues getDDMFormValues(long classPK)
		throws StorageException {

		try {
			return doGetDDMFormValues(classPK);
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	@Override
	public void update(
			long classPK, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws StorageException {

		try {
			transformDDMFormValues(ddmFormValues, serviceContext);

			doUpdate(classPK, ddmFormValues, serviceContext);
		}
		catch (StorageException storageException) {
			throw storageException;
		}
		catch (Exception exception) {
			throw new StorageException(exception);
		}
	}

	protected abstract long doCreate(
			long companyId, long ddmStructureId, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws Exception;

	protected abstract void doDeleteByClass(long classPK) throws Exception;

	protected abstract void doDeleteByDDMStructure(long ddmStructureId)
		throws Exception;

	protected abstract DDMFormValues doGetDDMFormValues(long classPK)
		throws Exception;

	protected abstract void doUpdate(
			long classPK, DDMFormValues ddmFormValues,
			ServiceContext serviceContext)
		throws Exception;

	protected void transformDDMFormValues(
			DDMFormValues ddmFormValues, ServiceContext serviceContext)
		throws PortalException {

		DDMFormValuesTransformer ddmFormValuesTransformer =
			new DDMFormValuesTransformer(ddmFormValues);

		ddmFormValuesTransformer.addTransformer(
			new DocumentLibraryDDMFormFieldValueTransformer());

		ddmFormValuesTransformer.addTransformer(
			new HTMLSanitizerDDMFormFieldValueTransformer(
				serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
				serviceContext.getUserId()));

		ddmFormValuesTransformer.transform();
	}

}