var taskManagerModule = angular.module('taskManagerApp', ['ngAnimate']);

taskManagerModule.controller('taskManagerController', function ($scope,$http,$location) {
	
	//var urlBase="http://localhost:8080/test-web-angularjs";
	var urlBase = $location.$$absUrl;
	$scope.toggle=true;
	$scope.selection = [];
	$scope.statuses=['ACTIVE','COMPLETED'];
	$scope.priorities=['HIGH','LOW','MEDIUM'];
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	
	//get all tasks and display initially
	$http.get(urlBase + 'tasks').
    	success(function(data) {
	        $scope.tasks = data;
	        for(var i=0;i<$scope.tasks.length;i++){
	            if($scope.tasks[i].status=='COMPLETED'){
	           	 $scope.selection.push($scope.tasks[i].id);
	        }
	        }
    });
	
	//add a new task
	$scope.addTask = function addTask() {
		if($scope.name=="" || $scope.description=="" || $scope.priority == "" || $scope.status == ""){
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		}
		else{
		 $http.post(urlBase + 'tasks/insert/' +$scope.name+'/'+$scope.description+'/'+$scope.priority+'/'+$scope.status).
		  success(function(data) {
			 alert("Task added");
			 $scope.tasks = data;	
			 $scope.name="";
			 $scope.description="";
			 $scope.priority="";
			 $scope.status="";
			 $scope.toggle='!toggle';			 
		    });
		}
	};
		
	// toggle selection for a given task by task id
	  $scope.toggleSelection = function toggleSelection(id) {
	    var idx = $scope.selection.indexOf(id);

	    // is currently selected
	    if (idx > -1) {
	      $http.post(urlBase + 'tasks/' +id+'/ACTIVE').
		  success(function(data) {
			 alert("Task unmarked");
			 $scope.tasks = data;		       
		    });
	      $scope.selection.splice(idx, 1);
	    }

	    // is newly selected
	    else {
	      $http.post(urlBase + 'tasks/' +id+'/COMPLETED').
		  success(function(data) {
			 alert("Task marked completed");
			 $scope.tasks = data;
		    });
	      $scope.selection.push(id);
	    }
	  };
	  
	
	// Archive Completed Tasks
	  $scope.archiveTasks = function archiveTasks() {
		  $http.post(urlBase + 'tasks/archive/' + $scope.selection).
		  success(function(data) {
			  $scope.tasks = data;
		       alert("Successfully Archived");
		    });
	  };
	
});

//Angularjs Directive for confirm dialog box
taskManagerModule.directive('ngConfirmClick', [
	function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);