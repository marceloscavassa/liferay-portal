/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;

/**
 * @author Matija Petanjek
 */
public class CommerceProductOptionValueRelativePriceRequest {

	public CommerceContext getCommerceContext() {
		return _commerceContext;
	}

	public CPDefinitionOptionValueRel getCPDefinitionOptionValueRel() {
		return _cpDefinitionOptionValueRel;
	}

	public long getCPInstanceId() {
		return _cpInstanceId;
	}

	public int getCPInstanceMinQuantity() {
		return _cpInstanceMinQuantity;
	}

	public CPDefinitionOptionValueRel getSelectedCPDefinitionOptionValueRel() {
		return _selectedCPDefinitionOptionValueRel;
	}

	public long getSelectedCPInstanceId() {
		return _selectedCPInstanceId;
	}

	public int getSelectedCPInstanceMinQuantity() {
		return _selectedCPInstanceMinQuantity;
	}

	public static class Builder {

		public Builder(
			CommerceContext commerceContext,
			CPDefinitionOptionValueRel cpDefinitionOptionValueRel) {

			_commerceContext = commerceContext;
			_cpDefinitionOptionValueRel = cpDefinitionOptionValueRel;
		}

		public CommerceProductOptionValueRelativePriceRequest build() {
			CommerceProductOptionValueRelativePriceRequest
				commerceProductOptionValueRelativePriceRequest =
					new CommerceProductOptionValueRelativePriceRequest();

			commerceProductOptionValueRelativePriceRequest._commerceContext =
				_commerceContext;

			commerceProductOptionValueRelativePriceRequest.
				_cpDefinitionOptionValueRel = _cpDefinitionOptionValueRel;

			commerceProductOptionValueRelativePriceRequest._cpInstanceId =
				_cpInstanceId;

			commerceProductOptionValueRelativePriceRequest.
				_cpInstanceMinQuantity = _cpInstanceMinQuantity;

			commerceProductOptionValueRelativePriceRequest.
				_selectedCPDefinitionOptionValueRel =
					_selectedCPDefinitionOptionValueRel;

			commerceProductOptionValueRelativePriceRequest.
				_selectedCPInstanceId = _selectedCPInstanceId;

			commerceProductOptionValueRelativePriceRequest.
				_selectedCPInstanceMinQuantity = _selectedCPInstanceMinQuantity;

			return commerceProductOptionValueRelativePriceRequest;
		}

		public Builder cpInstanceId(long cpInstanceId) {
			_cpInstanceId = cpInstanceId;

			return this;
		}

		public Builder cpInstanceMinQuantity(int cpInstanceMinQuantity) {
			_cpInstanceMinQuantity = cpInstanceMinQuantity;

			return this;
		}

		public Builder selectedCPDefinitionOptionValueRel(
			CPDefinitionOptionValueRel selectedCPDefinitionOptionValueRel) {

			_selectedCPDefinitionOptionValueRel =
				selectedCPDefinitionOptionValueRel;

			return this;
		}

		public Builder selectedCPInstanceId(long selectedCPInstanceId) {
			_selectedCPInstanceId = selectedCPInstanceId;

			return this;
		}

		public Builder selectedCPInstanceMinQuantity(
			int selectedCPInstanceMinQuantity) {

			_selectedCPInstanceMinQuantity = selectedCPInstanceMinQuantity;

			return this;
		}

		private final CommerceContext _commerceContext;
		private final CPDefinitionOptionValueRel _cpDefinitionOptionValueRel;
		private long _cpInstanceId;
		private int _cpInstanceMinQuantity;
		private CPDefinitionOptionValueRel _selectedCPDefinitionOptionValueRel;
		private long _selectedCPInstanceId;
		private int _selectedCPInstanceMinQuantity;

	}

	private CommerceProductOptionValueRelativePriceRequest() {
	}

	private CommerceContext _commerceContext;
	private CPDefinitionOptionValueRel _cpDefinitionOptionValueRel;
	private long _cpInstanceId;
	private int _cpInstanceMinQuantity;
	private CPDefinitionOptionValueRel _selectedCPDefinitionOptionValueRel;
	private long _selectedCPInstanceId;
	private int _selectedCPInstanceMinQuantity;

}