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

package com.liferay.source.formatter.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * @author Hugo Huijser
 */
public class TernaryOperatorCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.QUESTION};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		_checkTernaryExpression(detailAST, detailAST.getFirstChild());

		DetailAST colonDetailAST = detailAST.findFirstToken(TokenTypes.COLON);

		_checkTernaryExpression(detailAST, colonDetailAST.getNextSibling());
		_checkTernaryExpression(detailAST, colonDetailAST.getPreviousSibling());
	}

	private void _checkTernaryExpression(
		DetailAST questionDetailAST, DetailAST expressionDetailAST) {

		while (true) {
			if (expressionDetailAST.getType() == TokenTypes.LPAREN) {
				expressionDetailAST = expressionDetailAST.getNextSibling();
			}
			else if (expressionDetailAST.getType() == TokenTypes.RPAREN) {
				expressionDetailAST = expressionDetailAST.getPreviousSibling();
			}
			else {
				break;
			}
		}

		if (getStartLineNumber(expressionDetailAST) != getEndLineNumber(
				expressionDetailAST)) {

			log(questionDetailAST, _MSG_AVOID_TERNARY_OPERATOR);
		}
	}

	private static final String _MSG_AVOID_TERNARY_OPERATOR =
		"ternary.operator.avoid";

}