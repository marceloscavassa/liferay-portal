/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.petra.nio;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.UnmappableCharacterException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class CharsetEncoderUtilTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testConstructor() {
		new CharsetEncoderUtil();
	}

	@Test
	public void testEncode() throws Exception {
		Assert.assertEquals(
			ByteBuffer.wrap("abc".getBytes("US-ASCII")),
			CharsetEncoderUtil.encode(
				"US-ASCII", CodingErrorAction.REPORT, CharBuffer.wrap("abc")));

		try {
			CharsetEncoderUtil.encode(
				"US-ASCII", CodingErrorAction.REPORT, CharBuffer.wrap("测试"));

			Assert.fail();
		}
		catch (UnmappableCharacterException uce) {
			Assert.assertEquals(1, uce.getInputLength());
		}

		TestCharset fakeCharset = new TestCharset();

		Object cache1 = ReflectionTestUtil.getAndSetFieldValue(
			Charset.class, "cache1",
			new Object[] {fakeCharset.name(), fakeCharset});

		try {
			CharsetEncoderUtil.encode(
				fakeCharset.name(), CharBuffer.wrap(new char[0]));

			Assert.fail();
		}
		catch (Error e) {
			Assert.assertSame(
				fakeCharset.getCharacterCodingException(), e.getCause());
		}
		finally {
			ReflectionTestUtil.setFieldValue(Charset.class, "cache1", cache1);
		}
	}

	@Test
	public void testGetCharsetEncoder() {
		CharsetEncoder charsetEncoder = CharsetEncoderUtil.getCharsetEncoder(
			"UTF-8");

		Assert.assertEquals(Charset.forName("UTF-8"), charsetEncoder.charset());

		Assert.assertSame(
			CodingErrorAction.REPLACE, charsetEncoder.malformedInputAction());
		Assert.assertSame(
			CodingErrorAction.REPLACE,
			charsetEncoder.unmappableCharacterAction());
	}

}