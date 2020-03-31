<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="/resources/js/app.js"></script>

<style>
.ui-grid-header-canvas{
  padding-top: 9px;
  padding-bottom: 9px;
  text-align: left;
  background-color: #00BFFF;
  color: white;
}
.ui-grid-top-panel{    
  text-align: left;
  background-color: #00BFFF;
  color: white;
}
.paginategrid {
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

</style>
</head>
<body>
		
	<div ng-controller="UserManagementCtrl as vm">
	<br/>
	<br/>
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" size="150">
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination id="test"></div>
		
        
    </div>
	</div>
	
</body>
</html>