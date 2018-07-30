<%@ include file="/pages/commons/taglibs.jsp" %>


 	<spring:message code="datasource.name"/>: ${peticionDS} <br/>
 	
	<form:form method="POST" commandName="peticionDS" action="datasource">
		<table class="datasource-table">
			<tr>
		    	<td><form:label path="sql"><spring:message code="datasource.sql"/>:</form:label></td>
		  	</tr>
		  	<tr>
		    	<td><form:textarea path="sql" cssClass="datasource-textarea"/></td>
		  	</tr>
		  	<tr>
		  		<td><form:errors path="sql" cssClass="error"/></td>
		  	</tr>
		  	<tr>
		    	<td><form:button><spring:message code="datasource.button.execute"/></form:button></td>
		  	</tr>
		</table>
	</form:form>
	
	<c:if test="${respuestaDSs != null }">
	<span><spring:message code="datasource.result"/>:</span>
	<table class="datasource-result">
		<tr>
		    <th><spring:message code="datasource.operation"/></th>
		    <th><spring:message code="datasource.result"/></th>
		  </tr>
		<c:forEach var="respuesta" items="${respuestaDSs}">
		  <tr>
		    <td><c:out value="${respuesta.sql}"/></td>
		    <td><c:out value="${respuesta.result}"/></td>
		  </tr>
  		</c:forEach>
	</table>


	</c:if>
