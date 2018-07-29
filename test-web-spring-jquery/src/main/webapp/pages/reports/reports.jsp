<%@ include file="/pages/commons/taglibs.jsp"%>



<br/>
<button id="reloadButton" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> Reload</button>
<button id="addButton" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-star"></span> <spring:message code="report.button.add"/></button>
<button id="editButton" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-edit"></span> <spring:message code="report.button.edit"/></button>
<button id="deleteButton" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-trash"></span> <spring:message code="report.button.delete"/></button>
<br/>
<br/>
<div>
	<table id="example" class="table table-striped table-bordered" >
		<thead>
			<tr>
				<th><spring:message code="report.name"/></th>
				<th><spring:message code="report.description"/></th>
				<th><spring:message code="report.engine"/></th>
				<th><spring:message code="report.format"/></th>
				<th><spring:message code="report.programable"/></th>
				<th><spring:message code="report.lastexecute"/></th>
				<th></th>
			</tr>
		</thead>
	
	<%-- 
		<tfoot>
			<tr>
				<th>Name</th>
				<th>Position</th>
				<th>Office</th>
				<th>Age</th>
				<th>Start date</th>
				<th>Salary</th>
				<th></th>
			</tr>
		</tfoot>
	--%>
	</table>
</div>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables/dataTables.bootstrap.css" media="all">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datatables/jquery.dataTables.css" media="all">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reports-page.css" media="all">


<script type="text/javascript" src="${pageContext.request.contextPath}/js/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reports-page.js"></script>


