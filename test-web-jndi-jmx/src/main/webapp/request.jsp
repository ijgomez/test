<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Request Info.</title>
</head>
	<body>
		Request Info: <br/>
		HttpServletRequest-------------------------------------------------------------<br/>
		request.getAuthType(): <%= request.getAuthType() %><br/>
		request.getContextPath(): <%= request.getContextPath() %><br/>
		request.getMethod(): <%= request.getMethod() %><br/>
		request.getPathInfo(): <%= request.getPathInfo() %><br/>
		request.getPathTranslated(): <%= request.getPathTranslated() %><br/>
		request.getQueryString(): <%= request.getQueryString() %><br/>
		request.getRemotePort(): <%= request.getRemoteUser() %><br/>
		request.getRequestedSessionId(): <%= request.getRequestedSessionId() %><br/>
		request.getRequestURI(): <%= request.getRequestURI() %><br/>
		request.getServletPath(): <%= request.getServletPath() %><br/>
		request.getCookies(): <%= Arrays.toString(request.getCookies()) %> <br/>
		<%-- 
		<% if (request.getCookies() != null) for (Cookie cookie : request.getCookies()) { %>
		&nbsp;cookie.getComment(): <%=cookie.getComment() %><br/>
		&nbsp;cookie.getDomain(): <%=cookie.getDomain() %><br/>
		&nbsp;cookie.getMaxAge(): <%=cookie.getMaxAge() %><br/>
		&nbsp;cookie.getName(): <%=cookie.getName() %><br/>
		&nbsp;cookie.getPath(): <%=cookie.getPath() %><br/>
		&nbsp;cookie.getSecure(): <%=cookie.getSecure() %><br/>
		&nbsp;cookie.getValue(): <%=cookie.getValue() %><br/>
		&nbsp;cookie.getVersion(): <%=cookie.getVersion() %><br/>
		<% } %>
		--%>
		request.getHeaderNames(): <%= request.getHeaderNames() %><br/>
		request.getParts(): <%= request.getParts() %><br/>
		request.getRequestURL(): <%= request.getRequestURL() %><br/>

		request.getServletContext(): <%= request.getServletContext() %><br/>
		request.getServletContext().getContextPath(): <%= request.getServletContext().getContextPath() %><br/>
		request.getServletContext().getEffectiveMajorVersion(): <%= request.getServletContext().getEffectiveMajorVersion() %><br/>
		request.getServletContext().getEffectiveMinorVersion(): <%= request.getServletContext().getEffectiveMinorVersion() %><br/>
		request.getServletContext().getMajorVersion(): <%= request.getServletContext().getMajorVersion() %><br/>
		request.getServletContext().getMinorVersion(): <%= request.getServletContext().getMinorVersion() %><br/>
		request.getServletContext().getServerInfo(): <%= request.getServletContext().getServerInfo() %><br/>
		request.getServletContext().getServletContextName(): <%= request.getServletContext().getServletContextName() %><br/>
		request.getServletContext().getAttributeNames(): <%= request.getServletContext().getAttributeNames() %><br/>
		request.getServletContext().getClass(): <%= request.getServletContext().getClass() %><br/>
		
		request.getSession(): <%= request.getSession() %><br/>
		request.getSession().getId(): <%= request.getSession().getId() %><br/>
		request.getSession().getCreationTime(): <%= request.getSession().getCreationTime() %><br/>
		request.getSession().getLastAccessedTime(): <%= request.getSession().getLastAccessedTime() %><br/>
		request.getSession().getMaxInactiveInterval(): <%= request.getSession().getMaxInactiveInterval() %><br/>
		request.getSession().getAttributeNames(): <%= request.getSession().getAttributeNames() %><br/>
		request.getSession().getClass(): <%= request.getSession().getClass() %><br/>
		
		request.getUserPrincipal(): <%= request.getUserPrincipal() %><br/>
		
		<br/>
		ServletRequest-----------------------------------------------------------------<br/>
		request.getCharacterEncoding(): <%= request.getCharacterEncoding() %><br/>
		request.getContentLength(): <%= request.getContentLength() %><br/>
		request.getContentType(): <%= request.getContentType() %><br/>
		request.getLocalAddr(): <%= request.getLocalAddr() %><br/>
		request.getLocalName(): <%= request.getLocalName() %><br/>
		request.getLocalPort(): <%= request.getLocalPort() %><br/>
		request.getProtocol(): <%= request.getProtocol() %><br/>
		request.getRemoteAddr(): <%= request.getRemoteAddr() %><br/>
		request.getRemoteHost(): <%= request.getRemoteHost() %><br/>
		request.getRemotePort(): <%= request.getRemotePort() %><br/>
		request.getScheme(): <%= request.getScheme() %><br/>
		request.getServerName(): <%= request.getServerName() %><br/>
		request.getServerPort(): <%= request.getServerPort() %><br/>
		request.getAsyncContext(): <%= request.getAsyncContext() %><br/>
		request.getAttributeNames(): <%= request.getAttributeNames() %><br/>
		request.getDispatcherType(): <%= request.getDispatcherType() %><br/>
		request.getLocale(): <%= request.getLocale() %><br/>
		request.getLocales(): <%= request.getLocales() %><br/>
		request.getParameterMap(): <%= request.getParameterMap() %><br/>
		request.getParameterNames(): <%= request.getParameterNames() %><br/>
		
		<br/>
		Object-------------------------------------------------------------------------<br/>
		request.getClass(): <%= request.getClass() %><br/>
		
		<br/>
		-------------------------------------------------------------------------------<br/>
	</body>
</html>