
<!DOCTYPE html>
<html lang="en">
<head>
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="/resources/js/kiosks-km-app.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>

</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
<table class="table1">

<tr>
    <td id="noOfUsers" colspan="6">Summary</td>
    
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
    <td id="count2">Installed Kiosks</td> 
	<td id="count2">CMS</td>   
	<td id="count2">Assigned</td>   
	<td id="count2">To be Assigned</td>   
	<td id="count2">Vendor 1</td> 
	<td id="count2">Vendor 2</td>  	
  </tr>
</table>
<br/><br/>
		<div class="submain">
	
	<br/>
	<br/>
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle Name,Branch Code,CMF Name,Kiosk ID.." style="font-size: 12px" size="150" height="80">
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination id="test"></div>
		
        
    </div>
    
    
 </div>
</div>	
	
</div>	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script>
</body>
</html>