/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.constants.CPMeasurementUnitConstants;
import com.liferay.commerce.product.exception.DuplicateCPMeasurementUnitExternalReferenceCodeException;
import com.liferay.commerce.product.exception.DuplicateCPMeasurementUnitKeyException;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.service.base.CPMeasurementUnitLocalServiceBaseImpl;
import com.liferay.commerce.product.util.comparator.CPMeasurementUnitPriorityComparator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPMeasurementUnit",
	service = AopService.class
)
public class CPMeasurementUnitLocalServiceImpl
	extends CPMeasurementUnitLocalServiceBaseImpl {

	@Override
	public CPMeasurementUnit addCPMeasurementUnit(
			String externalReferenceCode, Map<Locale, String> nameMap,
			String key, double rate, boolean primary, double priority, int type,
			ServiceContext serviceContext)
		throws PortalException {

		User user = _userLocalService.getUser(serviceContext.getUserId());

		if (primary) {
			rate = 1;
		}

		_validate(
			externalReferenceCode, 0, serviceContext.getCompanyId(), key,
			primary, type);

		long cpMeasurementUnitId = counterLocalService.increment();

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.create(cpMeasurementUnitId);

		cpMeasurementUnit.setExternalReferenceCode(externalReferenceCode);
		cpMeasurementUnit.setGroupId(serviceContext.getScopeGroupId());
		cpMeasurementUnit.setCompanyId(user.getCompanyId());
		cpMeasurementUnit.setUserId(user.getUserId());
		cpMeasurementUnit.setUserName(user.getFullName());
		cpMeasurementUnit.setNameMap(nameMap);
		cpMeasurementUnit.setKey(key);
		cpMeasurementUnit.setRate(rate);
		cpMeasurementUnit.setPrimary(primary);
		cpMeasurementUnit.setPriority(priority);
		cpMeasurementUnit.setType(type);

		return cpMeasurementUnitPersistence.update(cpMeasurementUnit);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPMeasurementUnit deleteCPMeasurementUnit(
		CPMeasurementUnit cpMeasurementUnit) {

		return cpMeasurementUnitPersistence.remove(cpMeasurementUnit);
	}

	@Override
	public CPMeasurementUnit deleteCPMeasurementUnit(long cpMeasurementUnitId)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.findByPrimaryKey(cpMeasurementUnitId);

		return cpMeasurementUnitLocalService.deleteCPMeasurementUnit(
			cpMeasurementUnit);
	}

	@Override
	public void deleteCPMeasurementUnits(long companyId) {
		cpMeasurementUnitPersistence.removeByCompanyId(companyId);
	}

	@Override
	public CPMeasurementUnit fetchCPMeasurementUnit(long cpMeasurementUnitId) {
		return cpMeasurementUnitPersistence.fetchByPrimaryKey(
			cpMeasurementUnitId);
	}

	@Override
	public CPMeasurementUnit fetchCPMeasurementUnitByExternalReferenceCode(
		long companyId, String externalReferenceCode) {

		return cpMeasurementUnitPersistence.fetchByERC_C(
			externalReferenceCode, companyId);
	}

	@Override
	public CPMeasurementUnit fetchCPMeasurementUnitByKey(
			long companyId, String key)
		throws PortalException {

		return cpMeasurementUnitPersistence.fetchByC_K(companyId, key);
	}

	@Override
	public CPMeasurementUnit fetchPrimaryCPMeasurementUnit(
		long companyId, int type) {

		return cpMeasurementUnitPersistence.fetchByC_P_T_First(
			companyId, true, type, new CPMeasurementUnitPriorityComparator());
	}

	@Override
	public CPMeasurementUnit fetchPrimaryCPMeasurementUnitByType(
		long companyId, int type) {

		return cpMeasurementUnitPersistence.fetchByC_P_T_First(
			companyId, true, type, new CPMeasurementUnitPriorityComparator());
	}

	@Override
	public CPMeasurementUnit getCPMeasurementUnit(long cpMeasurementUnitId)
		throws PortalException {

		return cpMeasurementUnitPersistence.findByPrimaryKey(
			cpMeasurementUnitId);
	}

	@Override
	public CPMeasurementUnit getCPMeasurementUnitByKey(
			long companyId, String key)
		throws PortalException {

		return cpMeasurementUnitPersistence.findByC_K(companyId, key);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(long companyId) {
		return cpMeasurementUnitPersistence.findByCompanyId(companyId);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(
		long companyId, int type, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return cpMeasurementUnitPersistence.findByC_T(
			companyId, type, start, end, orderByComparator);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(
		long companyId, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return cpMeasurementUnitPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnits(
		long companyId, String[] keys) {

		List<CPMeasurementUnit> cpMeasurementUnits = new ArrayList<>(
			keys.length);

		for (String key : keys) {
			CPMeasurementUnit cpMeasurementUnit =
				cpMeasurementUnitPersistence.fetchByC_K(companyId, key);

			if (cpMeasurementUnit != null) {
				cpMeasurementUnits.add(cpMeasurementUnit);
			}
		}

		return cpMeasurementUnits;
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnitsByType(
			long companyId, int type)
		throws PortalException {

		return cpMeasurementUnitPersistence.findByC_T(companyId, type);
	}

	@Override
	public List<CPMeasurementUnit> getCPMeasurementUnitsByType(
			long companyId, int type, int start, int end,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws PortalException {

		return cpMeasurementUnitPersistence.findByC_T(
			companyId, type, start, end, orderByComparator);
	}

	@Override
	public int getCPMeasurementUnitsCount(long companyId) {
		return cpMeasurementUnitPersistence.countByCompanyId(companyId);
	}

	@Override
	public int getCPMeasurementUnitsCount(long companyId, int type) {
		return cpMeasurementUnitPersistence.countByC_T(companyId, type);
	}

	@Override
	public void importDefaultValues(ServiceContext serviceContext)
		throws PortalException {

		_addCPMeasurementUnit(
			"inches", "in", 1, true, 1,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);
		_addCPMeasurementUnit(
			"feet", "ft", 0.08333333, false, 2,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);
		_addCPMeasurementUnit(
			"meters", "m", 0.0254, false, 3,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);
		_addCPMeasurementUnit(
			"millimeters", "mm", 25.4, false, 4,
			CPMeasurementUnitConstants.TYPE_DIMENSION, serviceContext);

		_addCPMeasurementUnit(
			"ounces", "oz", 16, false, 1,
			CPMeasurementUnitConstants.TYPE_WEIGHT, serviceContext);
		_addCPMeasurementUnit(
			"pounds", "lb", 1, true, 2, CPMeasurementUnitConstants.TYPE_WEIGHT,
			serviceContext);
		_addCPMeasurementUnit(
			"kilograms", "kg", 0.45359237, false, 3,
			CPMeasurementUnitConstants.TYPE_WEIGHT, serviceContext);
		_addCPMeasurementUnit(
			"grams", "g", 453.59237, false, 4,
			CPMeasurementUnitConstants.TYPE_WEIGHT, serviceContext);

		_addCPMeasurementUnit(
			"piece(s)", "pc", 1, true, 1, CPMeasurementUnitConstants.TYPE_UNIT,
			serviceContext);
	}

	@Override
	public CPMeasurementUnit setPrimary(
			long cpMeasurementUnitId, boolean primary)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.findByPrimaryKey(cpMeasurementUnitId);

		_validate(
			cpMeasurementUnit.getExternalReferenceCode(), cpMeasurementUnitId,
			cpMeasurementUnit.getCompanyId(), cpMeasurementUnit.getKey(),
			primary, cpMeasurementUnit.getType());

		cpMeasurementUnit.setPrimary(primary);

		return cpMeasurementUnitPersistence.update(cpMeasurementUnit);
	}

	@Override
	public CPMeasurementUnit updateCPMeasurementUnit(
			String externalReferenceCode, long cpMeasurementUnitId,
			Map<Locale, String> nameMap, String key, double rate,
			boolean primary, double priority, int type,
			ServiceContext serviceContext)
		throws PortalException {

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.findByPrimaryKey(cpMeasurementUnitId);

		if (primary) {
			rate = 1;
		}

		_validate(
			externalReferenceCode, cpMeasurementUnit.getCPMeasurementUnitId(),
			serviceContext.getCompanyId(), key, primary, type);

		if (!Validator.isBlank(externalReferenceCode)) {
			cpMeasurementUnit.setExternalReferenceCode(externalReferenceCode);
		}

		cpMeasurementUnit.setNameMap(nameMap);
		cpMeasurementUnit.setKey(key);
		cpMeasurementUnit.setRate(rate);
		cpMeasurementUnit.setPrimary(primary);
		cpMeasurementUnit.setPriority(priority);
		cpMeasurementUnit.setType(type);

		return cpMeasurementUnitPersistence.update(cpMeasurementUnit);
	}

	private void _addCPMeasurementUnit(
			String name, String key, double rate, boolean primary,
			double priority, int type, ServiceContext serviceContext)
		throws PortalException {

		Map<Locale, String> nameMap = HashMapBuilder.put(
			serviceContext.getLocale(), name
		).build();

		CPMeasurementUnit cpMeasurementUnit =
			cpMeasurementUnitPersistence.fetchByC_K(
				serviceContext.getCompanyId(), key);

		if (cpMeasurementUnit == null) {
			cpMeasurementUnitLocalService.addCPMeasurementUnit(
				null, nameMap, key, rate, primary, priority, type,
				serviceContext);
		}
	}

	private void _validate(
			String externalReferenceCode, long cpMeasurementUnitId,
			long companyId, String key, boolean primary, int type)
		throws PortalException {

		if (Validator.isNotNull(key)) {
			CPMeasurementUnit cpMeasurementUnit =
				cpMeasurementUnitPersistence.fetchByC_K(companyId, key);

			if ((cpMeasurementUnit != null) &&
				(cpMeasurementUnit.getCPMeasurementUnitId() !=
					cpMeasurementUnitId)) {

				throw new DuplicateCPMeasurementUnitKeyException(
					"There is another commerce product measurement unit with " +
						"key " + key);
			}
		}

		if (Validator.isNotNull(externalReferenceCode)) {
			CPMeasurementUnit cpMeasurementUnit =
				cpMeasurementUnitPersistence.fetchByERC_C(
					externalReferenceCode, companyId);

			if ((cpMeasurementUnit != null) &&
				(cpMeasurementUnit.getCPMeasurementUnitId() !=
					cpMeasurementUnitId)) {

				throw new DuplicateCPMeasurementUnitExternalReferenceCodeException(
					"There is another commerce product measurement unit with " +
						"external reference code " + externalReferenceCode);
			}
		}

		if (primary) {
			List<CPMeasurementUnit> cpMeasurementUnits =
				cpMeasurementUnitPersistence.findByC_P_T(
					companyId, primary, type);

			for (CPMeasurementUnit curCPMeasurementUnit : cpMeasurementUnits) {
				if (curCPMeasurementUnit.getCPMeasurementUnitId() !=
						cpMeasurementUnitId) {

					curCPMeasurementUnit.setPrimary(false);

					cpMeasurementUnitPersistence.update(curCPMeasurementUnit);
				}
			}
		}
	}

	@Reference
	private UserLocalService _userLocalService;

}