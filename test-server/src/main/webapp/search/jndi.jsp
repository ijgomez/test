<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search JNDI</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/search/jndi" method="post">
			<input type="text" id="nameResource" name="nameResource" value="${nameResource}" size="256" style="width: 400px">
			<button type="submit">Search</button>
		</form>
		
		Result: ${result}<br/>
		${error}
	</body>
</html>