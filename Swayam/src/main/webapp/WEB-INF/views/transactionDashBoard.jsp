 <%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>    
<!DOCTYPE html>
<html lang="en">
<head>
 <script src="resources/js/transaction-summry-app.js"></script>
 
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css"/>

<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/lodash.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/jszip.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/excel-builder.dist.js"></script>  
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-aria.js"></script>

	<link rel="stylesheet" href="http://cdn.rawgit.com/angular-ui/ui-grid.info/gh-pages/release/3.0.0-rc.20/ui-grid.min.css">
	

<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment-with-locales.min.js"></script>

</head>
<body>
<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">


<div>
	
 <table style="width:100%">
     <tr>
   
   
  </tr>
   <tr style="text-align: center;">
    <td>
     <label>Start Date:</label>
        <input type="date" id="dateStart" name="input" ng-model="searchDateStart">
     </td>
    <td style="padding-right: 10px">
     <label>End Date:</label>
        <input style="width:180px;text-align: center;" type="date" id="dateEnd"   name="input" ng-model="searchDateEnd"  required />
   </td>
   <td style="padding-right: 10px;text-align: center;">
   <input type="button" value="Generate" ng-click="searchPositions(searchDateStart, searchDateEnd)"/>
   </td>
    </tr>
</table>
</div>

  <table>
  <h1 colspan="4" align="center" style="color: #05fc47;font-size: 16px;font-weight: bold;"> All India branch view on  <%= (new java.util.Date()).toLocaleString()%> </h1> 


    </table>
		
		
		
<div>
<pre style="background-color: #00BFFF;color: white;font-size: 12px;font-weight: bold;">
<span>Overall Branch Wise Swayam Transactions</span>
<!-- <span colspan="4" align="center" style="color: white;font-size: 12px;font-weight: bold;float:right; margin-right:1em">Last Updated : </span> -->
</pre>
</div>
		<div class="submain">
	<input class="form-group has-search" ng-model="searchText" ng-change="refresh()" placeholder="Enter Ticket Id, Kiosk Id, Branch Code, Circle etc." style="font-size: 12px" size="150" height="80" id="input">
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test"></div>
    </div>
</div>	
</div>

	<script>
       angular.bootstrap(document.getElementById("appId"), ['app']);
      </script>
	
</body>
</html>