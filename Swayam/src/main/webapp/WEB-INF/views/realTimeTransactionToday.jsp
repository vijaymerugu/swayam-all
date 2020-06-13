<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
 <script src="/resources/js/transaction-realtime-app.js"></script>
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

<script	src="/resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>

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
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
  $.ajax({
  	type:"GET",
  	url:"/td/getCurrentDate",
      success: function(data){
    	  alert("ttt=")
      	console.log("inside data");
  	    respos=data;
  	 console.log("response "+respos);
       $("#dateId").html(data);

      }
   	   });
  </script>

</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">

 <table>
  <h1 colspan="4" align="center" style="color: #05fc47;font-size: 12px;font-weight: bold;"> Real-time Swayam Transaction on  <%= (new java.util.Date()).toLocaleString()%> </h1> 
    </table>

<table>
        <tr>
  <h1 colspan="4" align="right"><input value="Today" class="openFinalPopup" ng-model="date" type="button"  data-ng-click="getCountType('today')" style="font-size: 20px;width: 80px;height: 30px;" /></h1>   
 </tr>
</table>
<div>
<pre style="background-color: #13A8E0;color: white;font-size: 12px;font-weight: bold;">
<span>Real-time Swayam Transaction</span>
 <span colspan="4" align="center" style="color: white;font-size: 12px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId">ss</span></span> 
</pre>
</div>
		<div class="submain">
	<br/>
	<br/>
	<input class="form-group has-search" ng-model="searchText" ng-change="refresh()" placeholder="Enter Kiosk Id, Branch Code, Circle etc." style="font-size: 12px" size="150" height="80" id="input">
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
	
	<script>
angular.bootstrap(document.getElementById("appId"), ['app']);


</script>

<script>
$(document).ready(function(){
    $('.openFinalPopup').on('click',function(){      
        
    	$("#contentHomeApp").load('/td/realTimeTransaction');    	
       
    }); 
    
});

</script>
	
</body>
</html>