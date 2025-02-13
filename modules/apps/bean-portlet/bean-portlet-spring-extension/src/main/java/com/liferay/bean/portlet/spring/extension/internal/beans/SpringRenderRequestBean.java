/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bean.portlet.spring.extension.internal.beans;

import com.liferay.bean.portlet.spring.extension.internal.scope.SpringScopedBeanManager;
import com.liferay.bean.portlet.spring.extension.internal.scope.SpringScopedBeanManagerThreadLocal;

import java.security.Principal;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.Priority;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderState;
import javax.portlet.WindowState;
import javax.portlet.filter.RenderRequestWrapper;

import javax.servlet.http.Cookie;

/**
 * @author Neil Griffin
 */
@ManagedBean("renderRequest")

// When the developer uses "@Inject RenderRequest", Spring must be able to
// disambiguate between RenderRequest and HeaderRequest. This is accomplished
// with @Priority. However, Spring only knows how to apply the @Priority
// annotation at the class-level for a class that represents a single bean. In
// other words, Spring does not know how to apply the @Priority annotation for a
// class like JSR362SpringBeanProducer that produces multiple
// types of beans via producer methods annotated with @Bean.

@Priority(2)

// In order to support unwrapping, it is necessary for this bean to extend
// RenderRequestWrapper. However, RenderRequestWrapper is designed in
// such a way that it requires the wrapped instance to be specified via the
// constructor. Since the instance is obtained from a request-based ThreadLocal,
// it is not possible to pass the instance via the constructor. Therefore each
// of the methods of PortletRequestWrapper and RenderRequestWrapper are
// overridden in this class.
public class SpringRenderRequestBean extends RenderRequestWrapper {

	public SpringRenderRequestBean() {

		// The superclass constructor requires a non-null instance or else
		// it will throw IllegalArgumentException.

		super(DummyRenderRequest.INSTANCE);
	}

	@Override
	public Object getAttribute(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getAttributeNames();
	}

	@Override
	public String getAuthType() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getAuthType();
	}

	@Override
	public String getContextPath() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getCookies();
	}

	@Override
	public String getETag() {
		RenderRequest renderRequest = getRequest();

		return renderRequest.getETag();
	}

	@Override
	public Locale getLocale() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getLocales();
	}

	@Override
	@SuppressWarnings("deprecation")
	public String getParameter(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameter(name);
	}

	@Override
	@SuppressWarnings("deprecation")
	public Map<String, String[]> getParameterMap() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameterMap();
	}

	@Override
	@SuppressWarnings("deprecation")
	public Enumeration<String> getParameterNames() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameterNames();
	}

	@Override
	@SuppressWarnings("deprecation")
	public String[] getParameterValues(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getParameterValues(name);
	}

	@Override
	public PortalContext getPortalContext() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortalContext();
	}

	@Override
	public PortletContext getPortletContext() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletContext();
	}

	@Override
	public PortletMode getPortletMode() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletMode();
	}

	@Override
	public PortletSession getPortletSession() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletSession();
	}

	@Override
	public PortletSession getPortletSession(boolean create) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPortletSession(create);
	}

	@Override
	public PortletPreferences getPreferences() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPreferences();
	}

	@Override
	@SuppressWarnings("deprecation")
	public Map<String, String[]> getPrivateParameterMap() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPrivateParameterMap();
	}

	@Override
	public Enumeration<String> getProperties(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getProperties(name);
	}

	@Override
	public String getProperty(String name) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getProperty(name);
	}

	@Override
	public Enumeration<String> getPropertyNames() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPropertyNames();
	}

	@Override
	@SuppressWarnings("deprecation")
	public Map<String, String[]> getPublicParameterMap() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getPublicParameterMap();
	}

	@Override
	public String getRemoteUser() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getRemoteUser();
	}

	@Override
	public RenderParameters getRenderParameters() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getRenderParameters();
	}

	@Override
	public RenderRequest getRequest() {
		SpringScopedBeanManager springScopedBeanManager =
			SpringScopedBeanManagerThreadLocal.getCurrentScopedBeanManager();

		return (RenderRequest)springScopedBeanManager.getPortletRequest();
	}

	@Override
	public String getRequestedSessionId() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getRequestedSessionId();
	}

	@Override
	public String getResponseContentType() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getResponseContentType();
	}

	@Override
	public Enumeration<String> getResponseContentTypes() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getResponseContentTypes();
	}

	@Override
	public String getScheme() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getScheme();
	}

	@Override
	public String getServerName() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getServerName();
	}

	@Override
	public int getServerPort() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getServerPort();
	}

	@Override
	public String getUserAgent() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getUserAgent();
	}

	@Override
	public Principal getUserPrincipal() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getUserPrincipal();
	}

	@Override
	public String getWindowID() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getWindowID();
	}

	@Override
	public WindowState getWindowState() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.getWindowState();
	}

	@Override
	public RenderState getWrapped() {
		return getRequest();
	}

	@Override
	public boolean isPortletModeAllowed(PortletMode portletMode) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isPortletModeAllowed(portletMode);
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isRequestedSessionIdValid();
	}

	@Override
	public boolean isSecure() {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isUserInRole(role);
	}

	@Override
	public boolean isWindowStateAllowed(WindowState windowState) {
		PortletRequest portletRequest = getRequest();

		return portletRequest.isWindowStateAllowed(windowState);
	}

	@Override
	public void removeAttribute(String name) {
		PortletRequest portletRequest = getRequest();

		portletRequest.removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		PortletRequest portletRequest = getRequest();

		portletRequest.setAttribute(name, value);
	}

	@Override
	public void setRequest(RenderRequest renderRequest) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWrapped(RenderState renderState) {
		throw new UnsupportedOperationException();
	}

}