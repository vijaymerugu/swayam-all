<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/requests-cmf-app.js"></script>
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

</head>
<body>
<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">


<div style="text-align: right;float: right;"><a class="openFinalPopup"><img src="resources/img/plus.png">&nbsp;Add Request</a></div>
		<div class="submain">
	<!-- <a href="/hm/requestFormCmf"><img src="/resources/img/plus.png">  Add Request</a> -->
	
	
	<input class="form-group has-search" ng-model="searchText" ng-change="refresh()" placeholder="Enter Ticket Id, Kiosk Id, Branch Code, Circle etc." style="font-size: 12px" size="150" height="80" id="input">
		
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
	
	<script type="text/javascript">



$(document).ready(function(){
	    $('.openFinalPopup').on('click',function(){      
	        
	    	$("#contentHomeApp").load('hm/requestFormCmf');    	
	      	    }); 
	});
</script>
	
</body>
</html>