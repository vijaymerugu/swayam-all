<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="/resources/js/kiosks-km-app.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<style>
.main{
top: 102px;
left: 0px;
width: 1367px;
height: 860px;
background: #EFF3F6 0% 0% no-repeat padding-box;
opacity: 1;
}
.submain{

top: 1000px;
left: 15px;
width: 1336px;
height: 519px;
background: #FFFFFF 0% 0% no-repeat padding-box;
box-shadow: 0px 3px 6px #8D8D8D29;
opacity: 1;
border: 1px solid black;
}
table.table1 {
  top: 152px;
left: 15px;
width: 1336px;
height: 156px;
background: #FFFFFF 0% 0% no-repeat padding-box;
box-shadow: 0px 3px 6px #8D8D8D29;
opacity: 1;
border: 1px solid black;
border-collapse: separate;
    border-spacing: 0em;
	border-spacing: 0;
}
#noOfUsers{
top: 163px;
left: 70px;
width: 84px;
height: 20px;
text-align: left;
letter-spacing: 0;
color: #000000;
opacity: 1;
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
font-size: 15px;
font-weight: bold;
border: 0px solid black;
}

#count1{
top: 213px;

width: 46px;

text-align: center;
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
font-size: 20px;
font-weight: bold;
letter-spacing: 0;
color: #13A8E0;
opacity: 1;
border: 1px solid black;
padding:0px; 
border-width:0px; 
margin:0px; 
bottom: 0;
border: 0px solid black;
vertical-align:bottom;
}
#count2{
top: 246px;

width: 30px;

text-align: center;
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
font-size: 13px;
letter-spacing: 0;
color: #000000;
opacity: 0.75;
border: 1px solid black;
padding:0px; 
border-width:0px; 
margin:0px; 
border: 0px solid black;
vertical-align:top;
}


</style>
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
    <td id="count2">Installed Kiosks</td> 
	<td id="count2">CMS</td>   
	<td id="count2">Assigned</td>   
	<td id="count2">To be Assigned</td>   
	<td id="count2">CC</td> 
	<td id="count2">SA</td>  	
  </tr>
</table>
<br/><br/>
		<div class="submain">
	<div ng-controller="UserManagementCtrl as vm">
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
</body>
</html>