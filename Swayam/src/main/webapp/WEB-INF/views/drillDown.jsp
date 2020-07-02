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
<script src="resources/js/drill-down.js"></script>
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

<!-- <script>
  $( function() {
    $( "#datepicker" ).datepicker({ 
        minDate: -20, maxDate: "+1M +15D" });
  } );
</script> -->	
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="DrillDownCtrl as vm">
<div>

			 <table class="" style="border: 1px solid #eee;">
				
              <div> 
              
              <!-- <label for="exampleInput">Pick a date in 2013:</label> -->
              
                   From Date: <input type="date" id="exampleInput" name="input1" ng-model="searchDateStart"
                              placeholder="yyyy-MM-dd" min="2020-01-01" max="2020-12-31" required/>
            
                   To Date : <input type="date" id="exampleInput" name="input2" ng-model="searchDateEnd"
                              placeholder="yyyy-MM-dd" min="2020-01-01" max="2020-12-31" required/>                
                             
			  <button ng-click="">Reset</button>	  
		      <button ng-click="searchPositions(searchDateStart,searchDateEnd)">Generate</button>
				     
			  </div> 
			   
			</table> 
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

<script>
  $(function() {
    $("#datepicker").datepicker()({
    minDate: -20, 
    maxDate: "+1M +15D" 
    });
  });
</script>
</body>
</html>