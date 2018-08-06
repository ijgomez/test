<%@ include file="/pages/commons/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><spring:message code="title.application"/></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/test-ds.css" media="all">
	</head>
	<body onload='document.f.j_username.focus();'>
		<div id="content">
			<h3><spring:message code="login.title"/></h3>
	
			<c:if test="${param.login_error ne null}">
				<font color="orange"> El intento de conectar no tuvo éxito, intentelo de nuevo.<br /> Razón: ${SPRING_SECURITY_LAST_EXCEPTION.message}.
				</font>
			</c:if>
			<form name="f" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">
				<table>
					<tr>
						<td>Usuario:</td>
						<td><input type="text" name="j_username" value="${cookie.SPRING_SECURITY_LAST_USERNAME.value}" /></td>
					</tr>
					<tr>
						<td>Contraseña:</td>
						<td><input type="password" name="j_password" /></td>
					</tr>
					<tr>
						<td>Recordarme:</td>
						<td><input type="checkbox" name="_spring_security_remember_me" /></td>
					</tr>
					<tr>
						<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
					</tr>
				</table>
			</form>
			Locale: ${pageContext.response.locale}
		</div>
	</body>
</html>



