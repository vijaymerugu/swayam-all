<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<script src="resources/js/moment-with-locales.min.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
 <script src="resources/js/transaction-error-reporting-app.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/style.css">

<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap-datepicker3.css" />
	
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>

<script>
	$(document).ready(function() {
		var datePickerOptions = { changeYear: true, 
				  changeMonth: true,
				  autoclose: true,
				  endDate : '+0d',
				  format : 'dd-mm-yyyy',
				  orientation : "top"
				  }

  	    $( ".datepicker" ).datepicker(datePickerOptions);
	});
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
</style>	

</head>
<body>
          
	<div class="main" ng-app="app" id="appId">
		<div ng-controller="UserManagementCtrl as vm">


        <div>
				<table class="" style="border: 1px solid #eee;">
					<div>
						<br /> From Date: <input type="text" id="datepickerFromDate" name="input1" class="datepicker" readonly="readonly"  ng-model="searchDateStart" placeholder="dd-mm-yyyy" required maxlength="10"  style="cursor: hand;cursor: pointer;" /> 
							To	Date : <input type="text" id="datepickerToDate" name="input2" class="datepicker" readonly="readonly"	ng-model="searchDateEnd" placeholder="dd-mm-yyyy" required maxlength="10"  style="cursor: hand;cursor: pointer;" />
					<button type="button"  ng-click="searchPositions(searchDateStart,searchDateEnd) " style="cursor: hand;cursor: pointer;">Generate</button>
					</div>
 				</table>
			</div>
			<br/>

			<div class="submain">
				<br /> <br /> <input class="form-group has-search"	ng-model="searchText" ng-change="refresh()"	placeholder="Enter Kiosk Id, Branch Code, Circle etc."
					style="font-size: 12px" size="150" height="80" id="input">
                 <span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
				<br />
					<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test"></div>
			</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"), [ 'app' ]);
	</script>
<script type="text/javascript">
      
      $(document).ready(function(){
    	  
    	  	/* var datePickerOptions = { changeYear: true, changeMonth: true,autoclose: true,maxDate: new Date(), dateFormat:'dd-mm-yy'}
  	  	
  			$( ".datepicker" ).datepicker(datePickerOptions); */

    	    $(".openpdfonclick").click(function(){
    	    	
    	        $.ajax({
    	            url: 'report?page=errorReporting&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	if(data.includes(".pdf")){
    	            		console.log("PDF Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            	} 
    	            }
    	        });
    	    });
    	    $(".openxlonclick").click(function(){    	
    	        $.ajax({
    	            url: 'report?page=errorReporting&type=excel',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	if(data.includes(".xlsx")){
    	            		console.log("Excel Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("Excel Data" + data);
    	            		alert("No Data to Export");
    	            	}  
    	            }
    	        });
    	    });
    	}); 
    		
    		
      
      </script>
</body>
 
</html>