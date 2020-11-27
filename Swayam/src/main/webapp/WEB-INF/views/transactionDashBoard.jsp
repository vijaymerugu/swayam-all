<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="s"%>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>


<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<!-- <script src="resources/js/moment-with-locales.min.js"></script> -->
<script	src="resources/js/angular.1.5.6.min.js"></script>
 <script src="resources/js/transaction-summry-app.js"></script>
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
      <!--   Added for loader------------- START -->  
 
    <%--  <script src="http://malsup.github.io/jquery.blockUI.js"></script> --%>
     <!-- Added for loader------------- END  -->  
     <script>
  $.ajax({
  	type:"GET",
  	url:"td/getSwayamMigrationLastUpDated",
      success: function(data){
    	//  alert("dddd=")
      	console.log("inside data");
  	    respos=data;
  	 console.log("response "+respos);
       $("#dateId").html(data);

      }
   	   });
  </script>
  
 <%--    <script>
  $.ajax({
  	type:"GET",
  	url:"td/getAllIndiaDate",
      success: function(data){
    	//  alert("dddd=")
      	console.log("inside data");
  	    respos=data;
  	 console.log("response "+respos);
       $("#allIndiaDateId").html(data);

      }
   	   });
  </script> --%>
    
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
<%-- 
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
    
</style>
 --%>
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
//  Added for loader------------- START 
/*     .loading
    {
        
        width: 200px;
        height: 100px;
        display: none;
    position: fixed;
    top: 10;
    bottom: 0;
    left: 10;
    right: 0;
     margin: auto;   
        background-color: White;
         z-index: 999; 
        align:center;
    } */
    //  Added for loader------------- END 
      .ui-grid .ui-grid-render-container-body .ui-grid-viewport {
 			 	overflow-x: auto !important;
  				overflow-y: auto !important;
  				
			}
</style>

<script type="text/javascript">
$("#myBtn").click(function(){
	var frmDate=document.getElementById("datepickerFromDate").value;  
	//alert(frmDate);  
	if (frmDate!=null)
		{
		$("#mySpan1").show();
		$("#mySpan").hide();
		}
	});

</script>

</head>

<body>
<div class="main_transaction" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
   <div>
				<table class="" style="border: 1px solid #eee;">
					<div>
						<br /> From Date: <input type="text" id="datepickerFromDate" name="input1"  class="datepicker" ng-model="searchDateStart" readonly="readonly" placeholder="dd-mm-yyyy" required maxlength="10" style="cursor: hand;cursor: pointer;"/> 
							To Date : <input type="text" id="datepickerToDate" name="input2" class="datepicker" ng-model="searchDateEnd" readonly="readonly" placeholder="dd-mm-yyyy" required maxlength="10" style="cursor: hand;cursor: pointer;" />
						<button type="button"    id="myBtn" ng-click="searchPositions(searchDateStart,searchDateEnd) " style="cursor: hand;cursor: pointer;">Generate</button>
					</div>
				</table>
			</div>
			<br />
			
	<%-- 	<table>
   <h1 colspan="4" align="center" style="color: #00BFFF;font-size: 18px;font-weight: bold;"> All India branch view on
    <span  id="mySpan">  {{CurrentDate | date:'EEE,dd MMM, yyyy hh:mm:ss a'}}</span> 
    <span  id="mySpan1"> {{allIndiaDate}} </span> 
  
  </h1> 		 
			    </table> --%>
	<%-- 		<br>	
			<div>
			
			<pre align="left" style="background-color: #00BFFF;color: white;font-size:12px;font-weight: bold;">
<span>Overall Branch Wise Swayam Transactions<span colspan="4" align="center" style="color: white;font-size: 42px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId"></span></span>
</span>
</pre>
			
			
			</div>  --%>
			
			<%-- <table>
     <h1 colspan="4" align="center" style="color: #00BFFF;font-size: 18px;font-weight: bold;">All India branch view on
      <span  id="mySpan">  {{CurrentDate | date:'EEE,dd MMM, yyyy hh:mm:ss a'}}</span> 
    <span  id="mySpan1"> {{allIndiaDate}} </span> 
       </h1> 
			    </table> --%>
			<!-- <br> -->	
			<div>
		<pre align="left" style="background-color: #00BFFF;color: white;font-size:18px;font-weight: bold;font-family:Helvetica;width:100%;">
 <span align="center" style="font-size: 18px;font-weight: bold;font-family:Helvetica">Overall Branch Wise Swayam Transactions<span colspan="4" align="center" style="color: white;font-size: 18px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId"></span></span></span>
</pre>
			</div> 

<%-- 

<div>
	
 <table style="border: 1px solid #eee;">
     
   <tr style="text-align: center;">
    <td>
     Start Date:
        <input type="date" id="dateStart" name="input" ng-model="searchDateStart">
     </td>
    <td style="padding-right: 10px">
     End Date:
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
</div>  --%>

 

		<div class="submain_transaction">
		  <input class="form-group has-search" ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle,Network,Branch Name, Branch Code etc."  style="font-size: 10px;"  size="130" height="50" id="input">
	<%-- <h1 colspan="4" align="center" style="color: #00BFFF;font-size: 14px;font-weight: bold; "> 
   All India branch view on <span  id="mySpan">  {{CurrentDate | date:'EEE,dd MMM, yyyy hh:mm:ss a'}}</span> 
    <span  id="mySpan1"> {{allIndiaDate}} </span> 
  </h1> --%> 
		
		<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
		<br/>
				<!-- Added for loader------------- START -->	
	
		<div class="loading" id="loading" align="center" style="display:none;">
   			 <img src="resources/img/loader.gif"> 
		</div> 
		<!-- Added for loader------------- END -->	
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test">
		</div>
    </div>
</div>	
</div>

	<script>
       angular.bootstrap(document.getElementById("appId"), ['app']);
      </script>
	
	 <script type="text/javascript">
	 
	
      
      $(document).ready(function(){
    	  

      	   /*  var datePickerOptions = { changeYear: true, changeMonth: true,autoclose: true,maxDate: new Date(), dateFormat:'dd-mm-yy'}
      	
      	    $( ".datepicker" ).datepicker(datePickerOptions); */
      	  
  	
    	    $(".openpdfonclick").click(function(){
    	    	
    	    	
    	    	
    	        $.ajax({
    	            url: 'report?page=transactionSummary&type=pdf ',
    	            type: 'GET',  
    	            success: function(data){
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
    	            url: 'report?page=transactionSummary&type=excel',
    	            type: 'GET',   
    	            success: function(data){
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