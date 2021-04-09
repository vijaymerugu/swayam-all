
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- UPDATED -->
    
 <meta http-equiv="x-ua-compatible" content="IE=edge">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

 <script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script> 
<!-- 	<script src="resources/js/ui-grid.min.js"></script> -->
<script src="resources/js/error-reporting-drilldown-Network.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<!-- <link rel="stylesheet" href="resources/css/bootstrap.min.css"/> -->
 <script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>  
<!-- <script	src="resources/js/ui-grid.js"></script> -->
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 

<!-- <link rel="stylesheet" href="resources/css/jquery-ui.css"/> -->
<link rel="stylesheet" href="resources/css/style.css">
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 

<!-- <script	src="resources/js/jquery-1.12.4.js"></script>
<script	src="resources/js/jquery-ui.js"></script> -->

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
	
<style>
       .ui-grid-header-cell-label {
		display:inline-block;
		white-space:initial;
		font-size: 15px;
		}
		
		
		.wrap-text .ui-grid-cell-contents {
 		 white-space:normal;
		}

		[ui-grid-row] {
  		display: table-row;
		}

		.ui-grid-row, .ui-grid-cell {
  		height: auto!important;
		}

			.ui-grid-cell {
  			float: none;
  			display: table-cell;
			} 
		
		
			.ui-grid-header-cell, .ui-grid-cell-contents {
  			white-space: normal;
  			padding: 2px;
  			word-break: break-word;
			}
			.ui-grid, .ui-grid-viewport {
   			  height: auto !important; 
			} 
			.ui-grid-pager-panel {
		     position: relative;
			 } 
			 .ui-grid .ui-grid-render-container-body .ui-grid-viewport {
 			 	overflow-x: auto !important;
  				overflow-y: auto !important;
  				
			}
			.ui-grid-pager-row-count-picker {
			display:none;
			}
			.ui-grid-header-canvas {
			    padding-top: 0px;
			    padding-bottom: 0px;}
			    button {
 
  background-color: #fdd209;
    color: #2f246c;
    border: none;
    padding: 5px 10px;
    text-transform: uppercase;
    font-weight: 600;
    float: right;
}
			    
</style>

</head>
<body>



<div class="main_transaction" ng-app="app" id="appId">
<div ng-controller="ErrorReportDrillDownCtrl as vm">
<div>

         <!--  <p> Date: <input type="text" id="datepicker"></p> -->

            <input type="hidden" id="circleName" value="${circleName}">
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
		<div class="submain_transaction">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, No Of Branches, Kiosks, Txns, etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		
	<button  id="btnClearText" ng-click="backUser()">Back</button>		
		<br/>
		<!-- Added for loader------------- START -->	
	
		<div class="loading" id="loading" align="center" style="display:none;">
   			 <img src="resources/img/loader.gif"> 
		</div> 
		<!-- Added for loader------------- END -->	
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script>
</body>
 
</html>