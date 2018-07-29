<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Request Info</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="Request Info">
	</head>
	<body>
		<b>Page Context Request Info:</b><br/>
		------------------------------------------------------------------------------<br/>
		Object: ${pageContext.request} <br/>
		<br/>
		AuthType: ${pageContext.request.authType} <br/>
		<br/>
		CharacterEncoding: ${pageContext.request.characterEncoding} <br/>
    	ContentLength: ${pageContext.request.contentLength} <br/>
    	ContentType: ${pageContext.request.contentType} <br/>
    	ContextPath: ${pageContext.request.contextPath} <br/>
    	Locale: ${pageContext.request.locale} <br/>
		Locales (${pageContext.request.locales}):
		<ul>
		<c:forEach var="locale" items="${pageContext.request.locales}">
   			<li><c:out value="${locale}"/></li>
		</c:forEach>
		</ul>
		LocalAddr: ${pageContext.request.localAddr} <br/>
    	LocalName: ${pageContext.request.localName} <br/>
    	LocalPort: ${pageContext.request.localPort} <br/>
    	RemoteAddr: ${pageContext.request.remoteAddr} <br/>
    	RemoteHost: ${pageContext.request.remoteHost} <br/>
    	RemotePort: ${pageContext.request.remotePort} <br/>
    	RemoteUser: ${pageContext.request.remoteUser} <br/>
		Method: ${pageContext.request.method} <br/>
    	PathInfo: ${pageContext.request.pathInfo} <br/>
    	PathTranslated: ${pageContext.request.pathTranslated} <br/>
    	QueryString: ${pageContext.request.queryString} <br/>
    	RequestedSessionId: ${pageContext.request.requestedSessionId} <br/>
	    RequestURI: ${pageContext.request.requestURI} <br/>
	    Scheme: ${pageContext.request.scheme} <br/>
	    ServerName: ${pageContext.request.serverName} <br/>
	    ServerPort: ${pageContext.request.serverPort} <br/>
	    ServletPath: ${pageContext.request.servletPath} <br/>
	    Attributes (${pageContext.request.attributeNames}):
	    <ul>
		<c:forEach var="attributeName" items="${pageContext.request.attributeNames}">
   			<li><c:out value="${attributeName}"/></li>
		</c:forEach>
		</ul>
	    Cookies (${pageContext.request.cookies}):
	    <ul>
	    <c:forEach var="c" items="${cookie}">
	    	<li><c:out value='${c.key}'/>=<c:out value='${c.value.value}'/></li>
	    </c:forEach>
	    </ul>
	    Headers (${pageContext.request.headerNames}):
	    <ul> 
	    <c:forEach var="h" items="${header}">
   			<li><c:out value="${h.key}"/>=<c:out value="${h.value}"/></li>
		</c:forEach>
	    </ul>
	    ParameterMap: ${pageContext.request.parameterMap} <br/>
	    ParameterNames (${pageContext.request.parameterNames}): <br/>
	    RequestURL: ${pageContext.request.requestURL} <br/>
	    UserPrincipal: ${pageContext.request.userPrincipal} <br/>
    	Secure: ${pageContext.request.secure} <br/>
    	Protocol: ${pageContext.request.protocol} <br/>
    	
    	<c:if test="${pageContext.request.secure}">
    		<!-- Propiedades solo para cuando la peticion es segura (HTTPS,...) -->
    	</c:if>
    	<br/>
    	------------------------------------------------------------------------------<br/>
    	<b>Page Context Response Info:</b><br/>
    	------------------------------------------------------------------------------<br/>
    	Buffer Size: ${pageContext.response.bufferSize}<br/>
    	Character Encoding: ${pageContext.response.characterEncoding}<br/>
    	Locale: ${pageContext.response.locale}<br/>
    	Content Type: ${pageContext.response.contentType}<br/>
    	<br/>
    	------------------------------------------------------------------------------<br/>
    	<b>Page Context Session Info:</b><br/>
    	------------------------------------------------------------------------------<br/>
    	Object: ${pageContext.session}<br/>
    	
    	ID: ${pageContext.session.id}<br/>
    	Creation Time: ${pageContext.session.creationTime}<br/>
    	Last Accessed Time: ${pageContext.session.lastAccessedTime}<br/>
    	Max Iactive Interval: ${pageContext.session.maxInactiveInterval}<br/>
    	Attribute Names: ${pageContext.session.attributeNames}<br/>
    	Value Names: ${pageContext.session.valueNames}<br/>
    	Session Context: ${pageContext.session.sessionContext}<br/>
    	Servlet Context: ${pageContext.session.servletContext}<br/>
		<br/>
    	------------------------------------------------------------------------------<br/>
    	<b>Page Context Servlet Context Info:</b><br/>
    	------------------------------------------------------------------------------<br/>
    	Servlet Context: ${pageContext.servletContext}<br/>
    	Context Path: ${pageContext.servletContext.contextPath}<br/>
    	Major Version: ${pageContext.servletContext.majorVersion}<br/>
    	Minor Version: ${pageContext.servletContext.minorVersion}<br/>
    	Server Info: ${pageContext.servletContext.serverInfo}<br/>
    	Servlet Context Name: ${pageContext.servletContext.servletContextName}<br/>
    	Attribute Names: ${pageContext.servletContext.attributeNames}<br/>
    	Init Parameter Names: ${pageContext.servletContext.initParameterNames}<br/>
    	<br/>
	</body>
</html>