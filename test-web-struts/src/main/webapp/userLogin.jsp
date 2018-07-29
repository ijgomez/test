 
<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for userLoginForm form</title>
	</head>
	<body>
		<html:form action="/userLogin">
			userName : <html:text property="userName"/><html:errors property="userName"/><br/>
			password : <html:password property="password"/><html:errors property="password"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>
