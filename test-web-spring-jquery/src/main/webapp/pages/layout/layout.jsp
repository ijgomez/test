<%@ include file="../commons/taglibs.jsp"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<tiles:insertAttribute name="head" />
	</head>
	<body>
		<div id="header">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="applicationMenu">
			<tiles:insertAttribute name="menu"/>
		</div>
		<div id="content">
			<tiles:insertAttribute name="body"/>
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer"/>
		</div>
	</body>
</html>
