
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
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
    <style>
    	 	.ui-grid, .ui-grid-viewport {
   			  height: auto !important; 
			} 
			.ui-grid-pager-panel {	
		     position: relative;
			 } 
	
			.ui-grid-pager-row-count-picker {
			display:none;
			}

ui-grid-render-container-body .ui-grid-viewport.no-horizontal-bar {
    overflow: hidden;
}
.ui-grid-header-canvas {
    padding-top: 0px;
    padding-bottom: 0px;}	
</style>
</head>
<body>
<div class="main_requestUpdate" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">


<!-- <div style="text-align: right;float: right;"> -->
<div style="text-align: right;position: relative;float: right;right: 150px;">
<a class="openFinalPopup"><img src="resources/img/plus.png">&nbsp;Add Request</a></div>
		<div class="submain_requestUpdate">
	<!-- <a href="/hm/requestFormCmf"><img src="/resources/img/plus.png">  Add Request</a> -->
	
	
	<input class="form-group has-search" ng-model="searchText" ng-change="refresh()" placeholder="Enter Ticket Id, Kiosk Id, Branch Code, Circle etc." style="font-size: 12px" size="150" height="80" id="input">
		
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
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