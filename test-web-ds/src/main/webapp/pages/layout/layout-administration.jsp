<%@ include file="/pages/commons/taglibs.jsp" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

Administration Pages:
<tiles:insertAttribute name="submenu"/>
<div id="administrationContent">
	<tiles:insertAttribute name="body"/>
</div>