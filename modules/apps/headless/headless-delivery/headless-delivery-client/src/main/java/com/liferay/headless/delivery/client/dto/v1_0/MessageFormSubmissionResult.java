/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.client.dto.v1_0;

import com.liferay.headless.delivery.client.function.UnsafeSupplier;
import com.liferay.headless.delivery.client.serdes.v1_0.MessageFormSubmissionResultSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class MessageFormSubmissionResult implements Cloneable, Serializable {

	public static MessageFormSubmissionResult toDTO(String json) {
		return MessageFormSubmissionResultSerDes.toDTO(json);
	}

	public FragmentInlineValue getMessage() {
		return message;
	}

	public void setMessage(FragmentInlineValue message) {
		this.message = message;
	}

	public void setMessage(
		UnsafeSupplier<FragmentInlineValue, Exception> messageUnsafeSupplier) {

		try {
			message = messageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected FragmentInlineValue message;

	@Override
	public MessageFormSubmissionResult clone()
		throws CloneNotSupportedException {

		return (MessageFormSubmissionResult)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageFormSubmissionResult)) {
			return false;
		}

		MessageFormSubmissionResult messageFormSubmissionResult =
			(MessageFormSubmissionResult)object;

		return Objects.equals(
			toString(), messageFormSubmissionResult.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return MessageFormSubmissionResultSerDes.toJSON(this);
	}

}