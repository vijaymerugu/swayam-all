<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="/resources/js/users-app.js"></script>
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>

</head>
<body>
<div class="main">
<table class="table1">

<tr>
    <td id="noOfUsers" colspan="6">No of users</td>
    
  </tr>
  <tr>
    <td id="count1" style="">200</td> 
	<td id="count1">500</td>   
	<td id="count1">30</td>   
	<td id="count1">60</td>   
	<td id="count1">100</td> 
	<td id="count1">12</td>  	
  </tr>
  <tr>
    <td id="count2">CMF</td> 
	<td id="count2">CMS</td>   
	<td id="count2">Circle</td>   
	<td id="count2">LA</td>   
	<td id="count2">CC</td> 
	<td id="count2">SA</td>  	
  </tr>
</table>
<br/><br/>
		<div class="submain">
	<div ng-controller="UserManagementCtrl as vm">
	<br/>
	<br/>
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" size="150" height="80">
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
</div>	
</body>
</html>