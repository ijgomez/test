<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="taskManagerApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AngularJS Task Manager</title>
<link href='./css/style.css' rel="stylesheet" type="text/css"></link>
<link href='./css/css/font-awesome.css' rel="stylesheet" type="text/css"></link>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<script data-require="angular.js@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular.js"></script>
<script data-require="angular-animate@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular-animate.js"></script>
<script type="text/javascript" src="./js/app.js"></script>
</head>
<body>

<div ng-controller="taskManagerController">

	<div id="task-panel" class="fadein fadeout showpanel panel"  ng-show="toggle">	
		<div class="panel-heading">
			<i class="panel-title-icon fa fa-tasks"></i>
			<span class="panel-title">Recent Tasks</span>
			<div class="panel-heading-controls">
				<button ng-click="toggle = !toggle" class="btn-panel">Add New Task</button>
				<button class="btn-panel" confirmed-click="archiveTasks()" ng-confirm-click="Would you like to archive completed tasks?">Clear completed tasks</button>
			</div>
		</div>
		<div class="panel-body">
			<div class="task" ng-repeat="task in tasks">
				<span ng-if="task.priority=='HIGH'" class="priority priority-red">
					{{task.priority}}
				</span>
				<span ng-if="task.priority=='MEDIUM'" class="priority priority-yellow">
					{{task.priority}}
				</span>
				<span ng-if="task.priority=='LOW'" class="priority priority-green">
					{{task.priority}}
				</span>
				<div class="action-checkbox">
					<input id="{{task.id}}" type="checkbox" value="{{task.id}}" ng-checked="selection.indexOf(task.id) > -1" ng-click="toggleSelection(task.id)" />
	  				<label for="{{task.id}}"></label>		
				</div>
				<div ng-if="task.status=='COMPLETED'">				
					<a href="#" class="checkedClass">
						{{task.name}}
					<span class="action-status">{{task.status}}</span>
					</a>
				</div>
				<div ng-if="task.status=='ACTIVE'">				
					<a href="#" class="uncheckedClass">
						{{task.name}}
						<span class="action-status">{{task.status}}</span>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div id="add-task-panel" class="fadein fadeout addpanel panel" ng-hide="toggle">
		<div class="panel-heading">
			<i class="panel-title-icon fa fa-plus"></i>
			<span class="panel-title">Add Task</span>
			<div class="panel-heading-controls">
				<button ng-click="toggle = !toggle" class="btn-panel">Show All Tasks</button>
			</div>
		</div>
		<div class="panel-body">
			<div class="task" >
				<table class="add-task">
					<tr>
						<td>Task Name:</td>
						<td><input type="text" ng-model="name"/></td>
					</tr>
					<tr>
						<td>Task Description:</td>
						<td><input type="text" ng-model="description"/></td>
					</tr>
					<tr>
						<td>Task Status:</td>
						<td>
							<select ng-model="status" ng-options="status as status for status in statuses">
								<option value="">-- Select --</option>						
						     	</select>
						</td>
					</tr>
					<tr>
						<td>Task Priority:</td>
						<td>
							<select ng-model="priority" ng-options="priority as priority for priority in priorities">
								<option value="">-- Select --</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><br/><button ng-click="addTask()" class="btn-panel-big">Add New Task</button></td>
					</tr>
				</table>								
			</div>
		</div>
	</div>
</div>
</body>
</html>