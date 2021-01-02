<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>


<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<!-- <script src="resources/js/moment-with-locales.min.js"></script> -->
<script	src="resources/js/angular.1.5.6.min.js"></script>
 <script src="resources/js/drill-down.js"></script>
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
		  $.ajax({
		  	type:"GET",
		  	url:"td/getDrillDownLastUpDated",
		      success: function(data){
		    	//  alert("dddd=")
		      	console.log("inside data");
		  	    respos=data;
		  	 console.log("response "+respos);
		       $("#dateId").html(data);
		
		      }
		   	   });
  </script>

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
</style>	
</head>
<body>



<div class="main_transaction" ng-app="app" id="appId">
<div ng-controller="DrillDownCtrl as vm">
    <div>
			<table class="" style="border: 1px solid #eee;">
              
					<div>
						<br /> From Date: <input type="text" id="datepickerFromDate" name="input1" class="datepicker" readonly="readonly" ng-model="searchDateStart" placeholder="dd-mm-yyyy" required maxlength="10" style="cursor: hand;cursor: pointer;" /> 
							To	Date : <input type="text" id="datepickerToDate" name="input2" class="datepicker" readonly="readonly"	ng-model="searchDateEnd" placeholder="dd-mm-yyyy" required maxlength="10"  style="cursor: hand;cursor: pointer;" />
						<button type="button"  ng-click="searchPositions(searchDateStart,searchDateEnd) " style="cursor: hand;cursor: pointer;">Generate</button>
					</div>
			</table> 
		</div>

<!-- <div>
			 <table class="" style="border: 1px solid #eee;">
			
              <div> 
              
              <label for="exampleInput">Pick a date in 2013:</label>
              
                   From Date: <input type="date" id="exampleInput" name="input1" ng-model="searchDateStart"
                              placeholder="yyyy-MM-dd" min="2020-01-01" max="2020-12-31" required/>
            
                   To Date : <input type="date" id="exampleInput" name="input2" ng-model="searchDateEnd"
                              placeholder="yyyy-MM-dd" min="2020-01-01" max="2020-12-31" required/>                
                             
			  <button ng-click="">Reset</button>	  
		      <button ng-click="searchPositions(searchDateStart,searchDateEnd)">Generate</button>
				     
			  </div> 
			   
			</table> 
		</div> -->
<br/>

      <!-- <div>
			<pre align="left" style="background-color: #00BFFF; color: white; font-size: 18px; font-weight: bold; font-family: Helvetica;">
             <span align="center" style="font-family: Helvetica">Drill Down  Transactions     <span
						colspan="4" align="center"	style="color: white; font-size: 18px; font-weight: bold; float: right; margin-right: 1em">Last Updated :<span id="dateId"></span></span></span>
      </pre>
			</div> -->
			
			<div>
		<pre align="left" style="background-color: #00BFFF;color: white;font-size:18px;font-weight: bold;font-family:Helvetica;">
 <span align="center" style="font-family:Helvetica">Drill Down Transactions<span colspan="4" align="center" style="color: white;font-size: 18px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId"></span></span></span>
</pre>
			</div> 

		<div class="submain_transaction">	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, No Of Branches, Kiosks, Txns, etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		
		
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

<script type="text/javascript">
      
      $(document).ready(function(){

    	/*   var datePickerOptions = { changeYear: true, changeMonth: true,autoclose: true,maxDate: new Date(), dateFormat:'dd-mm-yy'}
    	  	
    		$( ".datepicker" ).datepicker(datePickerOptions); */
    	  
    	    $(".openpdfonclick").click(function(){
    	    	
    	        $.ajax({
    	            url: 'report?page=drillDown&type=pdf',
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
    	            url: 'report?page=drillDown&type=excel',
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
<script>
  /* $(function() {
    $("#datepicker").datepicker()({
    minDate: -20, 
    maxDate: "+1M +15D" 
    });
  }); */
</script>
</body>
 
</html>