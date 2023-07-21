/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.odata.internal.filter.expression;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.filter.expression.BinaryExpression;
import com.liferay.portal.odata.filter.expression.Expression;
import com.liferay.portal.odata.filter.expression.LambdaFunctionExpression;
import com.liferay.portal.odata.filter.expression.ListExpression;
import com.liferay.portal.odata.filter.expression.LiteralExpression;
import com.liferay.portal.odata.filter.expression.MethodExpression;
import com.liferay.portal.odata.filter.expression.NavigationPropertyExpression;
import com.liferay.portal.odata.filter.expression.PropertyExpression;
import com.liferay.portal.odata.filter.expression.UnaryExpression;

import java.util.List;
import java.util.Objects;

import org.apache.olingo.commons.api.edm.EdmEnumType;
import org.apache.olingo.commons.api.edm.EdmType;
import org.apache.olingo.commons.core.edm.primitivetype.EdmBoolean;
import org.apache.olingo.commons.core.edm.primitivetype.EdmByte;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDate;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDateTimeOffset;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDecimal;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDouble;
import org.apache.olingo.commons.core.edm.primitivetype.EdmInt16;
import org.apache.olingo.commons.core.edm.primitivetype.EdmInt32;
import org.apache.olingo.commons.core.edm.primitivetype.EdmInt64;
import org.apache.olingo.commons.core.edm.primitivetype.EdmSByte;
import org.apache.olingo.commons.core.edm.primitivetype.EdmString;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriInfoResource;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceComplexProperty;
import org.apache.olingo.server.api.uri.UriResourceCount;
import org.apache.olingo.server.api.uri.UriResourceKind;
import org.apache.olingo.server.api.uri.UriResourceLambdaAny;
import org.apache.olingo.server.api.uri.UriResourceNavigation;
import org.apache.olingo.server.api.uri.UriResourcePartTyped;
import org.apache.olingo.server.api.uri.UriResourcePrimitiveProperty;
import org.apache.olingo.server.api.uri.queryoption.expression.BinaryOperatorKind;
import org.apache.olingo.server.api.uri.queryoption.expression.ExpressionVisitException;
import org.apache.olingo.server.api.uri.queryoption.expression.ExpressionVisitor;
import org.apache.olingo.server.api.uri.queryoption.expression.Literal;
import org.apache.olingo.server.api.uri.queryoption.expression.Member;
import org.apache.olingo.server.api.uri.queryoption.expression.MethodKind;
import org.apache.olingo.server.api.uri.queryoption.expression.UnaryOperatorKind;

/**
 * @author Cristina González
 */
public class ExpressionVisitorImpl implements ExpressionVisitor<Expression> {

	@Override
	public Expression visitAlias(String alias) {
		throw new UnsupportedOperationException("Alias: " + alias);
	}

	@Override
	public Expression visitBinaryOperator(
		BinaryOperatorKind binaryOperatorKind,
		Expression leftBinaryOperationExpression,
		Expression rightBinaryOperationExpression) {

		BinaryExpression.Operation binaryExpressionOperation = null;

		if (binaryOperatorKind == BinaryOperatorKind.AND) {
			binaryExpressionOperation = BinaryExpression.Operation.AND;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.EQ) {
			binaryExpressionOperation = BinaryExpression.Operation.EQ;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.GE) {
			binaryExpressionOperation = BinaryExpression.Operation.GE;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.GT) {
			binaryExpressionOperation = BinaryExpression.Operation.GT;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.LE) {
			binaryExpressionOperation = BinaryExpression.Operation.LE;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.LT) {
			binaryExpressionOperation = BinaryExpression.Operation.LT;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.NE) {
			binaryExpressionOperation = BinaryExpression.Operation.NE;
		}
		else if (binaryOperatorKind == BinaryOperatorKind.OR) {
			binaryExpressionOperation = BinaryExpression.Operation.OR;
		}
		else {
			throw new UnsupportedOperationException(
				"Binary operator: " + binaryOperatorKind);
		}

		return new BinaryExpressionImpl(
			leftBinaryOperationExpression, binaryExpressionOperation,
			rightBinaryOperationExpression);
	}

	@Override
	public Expression visitBinaryOperator(
		BinaryOperatorKind binaryOperatorKind,
		Expression leftListOperationExpression,
		List<Expression> rightListOperationExpressions) {

		if (binaryOperatorKind == BinaryOperatorKind.IN) {
			return new ListExpressionImpl(
				leftListOperationExpression, ListExpression.Operation.IN,
				rightListOperationExpressions) {
			};
		}

		throw new UnsupportedOperationException(
			"Binary operator: " + binaryOperatorKind);
	}

	@Override
	public Expression visitEnum(EdmEnumType edmEnumType, List<String> list) {
		throw new UnsupportedOperationException(
			"Enum: " + StringUtil.merge(edmEnumType.getMemberNames()));
	}

	@Override
	public Expression visitLambdaExpression(
			String lambdaFunction, String lambdaVariable,
			org.apache.olingo.server.api.uri.queryoption.expression.Expression
				expression)
		throws ExpressionVisitException, ODataApplicationException {

		if (Objects.equals(
				StringUtil.toUpperCase(lambdaFunction),
				LambdaFunctionExpression.Type.ANY.name())) {

			return new LambdaFunctionExpressionImpl(
				LambdaFunctionExpression.Type.ANY, lambdaVariable,
				expression.accept(this));
		}

		throw new UnsupportedOperationException(
			"Lambda expression: " + lambdaFunction);
	}

	@Override
	public Expression visitLambdaReference(String lambdaReference) {
		throw new UnsupportedOperationException(
			"Lambda reference: " + lambdaReference);
	}

	@Override
	public Expression visitLiteral(Literal literal) {
		EdmType edmType = literal.getType();

		if (edmType instanceof EdmBoolean) {
			return new LiteralExpressionImpl(
				literal.getText(), LiteralExpression.Type.BOOLEAN);
		}
		else if (edmType instanceof EdmByte || edmType instanceof EdmInt16 ||
				 edmType instanceof EdmInt32 || edmType instanceof EdmInt64 ||
				 edmType instanceof EdmSByte) {

			return new LiteralExpressionImpl(
				literal.getText(), LiteralExpression.Type.INTEGER);
		}
		else if (edmType instanceof EdmDate) {
			return new LiteralExpressionImpl(
				literal.getText(), LiteralExpression.Type.DATE);
		}
		else if (edmType instanceof EdmDateTimeOffset) {
			return new LiteralExpressionImpl(
				literal.getText(), LiteralExpression.Type.DATE_TIME);
		}
		else if (edmType instanceof EdmDecimal ||
				 edmType instanceof EdmDouble) {

			return new LiteralExpressionImpl(
				literal.getText(), LiteralExpression.Type.DOUBLE);
		}
		else if (edmType instanceof EdmString) {
			return new LiteralExpressionImpl(
				literal.getText(), LiteralExpression.Type.STRING);
		}
		else if ((edmType == null) ||
				 Objects.equals(literal.getText(), "null")) {

			return new NullLiteralExpression();
		}

		throw new UnsupportedOperationException(
			"Literal: " + edmType.getFullQualifiedName());
	}

	@Override
	public Expression visitMember(Member member)
		throws ExpressionVisitException {

		UriInfoResource uriInfoResource = member.getResourcePath();

		return new MemberExpressionImpl(
			_getExpression(uriInfoResource.getUriResourceParts()));
	}

	@Override
	public Expression visitMethodCall(
		MethodKind methodKind, List<Expression> expressions) {

		if (methodKind == MethodKind.CONTAINS) {
			return new MethodExpressionImpl(
				expressions, MethodExpression.Type.CONTAINS);
		}
		else if (methodKind == MethodKind.NOW) {
			return new MethodExpressionImpl(
				expressions, MethodExpression.Type.NOW);
		}
		else if (methodKind == MethodKind.STARTSWITH) {
			return new MethodExpressionImpl(
				expressions, MethodExpression.Type.STARTS_WITH);
		}

		throw new UnsupportedOperationException("Method call: " + methodKind);
	}

	@Override
	public Expression visitTypeLiteral(EdmType edmType) {
		throw new UnsupportedOperationException(
			"Type literal: " + edmType.getKind());
	}

	@Override
	public Expression visitUnaryOperator(
		UnaryOperatorKind unaryOperatorKind, Expression expression) {

		if (unaryOperatorKind == UnaryOperatorKind.NOT) {
			return new UnaryExpressionImpl(
				expression, UnaryExpression.Operation.NOT);
		}

		throw new UnsupportedOperationException(
			"Unary operator: " + unaryOperatorKind);
	}

	private Expression _getExpression(List<UriResource> uriResources)
		throws ExpressionVisitException {

		UriResource uriResource = uriResources.get(0);

		if ((uriResources.size() == 1) &&
			(uriResource instanceof UriResourcePrimitiveProperty)) {

			return new PrimitivePropertyExpressionImpl(
				uriResource.getSegmentValue());
		}
		else if ((uriResources.size() > 1) &&
				 (uriResource instanceof UriResourceComplexProperty)) {

			return new ComplexPropertyExpressionImpl(
				uriResource.getSegmentValue(),
				(PropertyExpression)_getExpression(
					uriResources.subList(1, uriResources.size())));
		}
		else if ((uriResources.size() > 1) &&
				 (uriResource instanceof UriResourceNavigation)) {

			return new NavigationPropertyExpressionImpl(
				uriResource.getSegmentValue(), _getType(uriResources.get(1)));
		}
		else if ((uriResources.size() > 1) &&
				 (uriResource instanceof UriResourcePrimitiveProperty)) {

			UriResource lambdaUriResource = uriResources.get(
				uriResources.size() - 1);

			if (lambdaUriResource instanceof UriResourceLambdaAny) {
				UriResourceLambdaAny uriResourceLambdaAny =
					(UriResourceLambdaAny)lambdaUriResource;

				try {
					return new CollectionPropertyExpressionImpl(
						(PropertyExpression)_getExpression(
							uriResources.subList(0, uriResources.size() - 1)),
						(LambdaFunctionExpression)visitLambdaExpression(
							LambdaFunctionExpression.Type.ANY.name(),
							uriResourceLambdaAny.getLambdaVariable(),
							uriResourceLambdaAny.getExpression()));
				}
				catch (ODataApplicationException oDataApplicationException) {
					throw new ExpressionVisitException(
						oDataApplicationException);
				}
			}
		}
		else if (uriResource instanceof UriResourcePartTyped) {
			UriResourcePartTyped uriResourcePartTyped =
				(UriResourcePartTyped)uriResource;

			if (Objects.equals(
					uriResourcePartTyped.getKind(),
					UriResourceKind.lambdaVariable)) {

				return new LambdaVariableExpressionImpl(
					uriResource.getSegmentValue());
			}
		}

		throw new UnsupportedOperationException(
			"An expression cannot be obtained from URI resources " +
				uriResources);
	}

	private NavigationPropertyExpression.Type _getType(
		UriResource uriResource) {

		if (uriResource instanceof UriResourceCount) {
			return NavigationPropertyExpression.Type.COUNT;
		}

		return NavigationPropertyExpression.Type.SIMPLE;
	}

}