<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="http://cdn.rawgit.com/angular-ui/ui-grid.info/gh-pages/release/3.0.0-rc.20/ui-grid.min.css">
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/drill-down-Branch.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script src="resources/js/csv.js"></script>
    <script src="resources/js/pdfmake.js"></script>
    <script src="resources/js/vfs_fonts.js"></script>
    <script src="resources/js/lodash.min.js"></script>
    <script src="resources/js/jszip.min.js"></script>
    <script src="resources/js/excel-builder.dist.js"></script>  
    <script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>


<script>
  $( function() {
    $( "#datepicker" ).datepicker({ 
        minDate: -20, maxDate: "+1M +15D" });
  } );
</script>	
	


</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="DrillDownCtrl as vm">
<div>

          <!-- <p>Date: <input type="text" id="datepicker"></p> -->
<input type="hidden" id="circleName" value="${circleName}">
<input type="hidden" id="networkName" value="${networkName}">
<input type="hidden" id="moduleName" value="${moduleName}">
<input type="hidden" id="regionName" value="${regionName}">
<input type="hidden" id="fromDate" value="${fromDate}">
<input type="hidden" id="toDate" value="${toDate}">

			<%-- <table class="table1" style="border: 1px solid #eee;">
				


				<tr>
					<td id="noOfUsers" colspan="6">No of users</td>
				</tr>
				<tr>
				
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMF')">${cmfCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMS')"> ${cmsCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('MUMBAI')">${circleCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('LA')">${laCount}</a></td>   
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('CC')">${ccCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('SA')">${saCount}</a></td>
				</tr>
				<tr>
					<!-- Vijay All Circle wise -->
					
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMF</td>
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMS</td>
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">Circle</td>
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">LA</td>
					<td id="count2" style="color: black; border-right: solid 1px #faf5f6;">CC</td>
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">SA</td>
				</tr>
			</table> --%>
		</div>
<br/>
		<div class="submain">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, No Of Branches, Kiosks, Txns, etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script>
</body>
</html>