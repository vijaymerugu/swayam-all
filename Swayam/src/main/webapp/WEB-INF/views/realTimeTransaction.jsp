<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page import="java.util.*" %>
   
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<head>

 <script src="resources/js/transaction-realtime-app.js"></script>
 
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

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
  <script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
  <script>
  $.ajax({
  	type:"GET",
  	url:"td/getCurrentDate",
      success: function(data){
    	//  alert("dddd=")
      	console.log("inside data");
  	    respos=data;
  	 console.log("response "+respos);
       $("#dateId").html(data);

      }
   	   });
  </script>

 <script type="text/javascript">
  
 
   
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
			    
			    input[type="reset"]
   {
     background-image: url(resources/img/Close.png );
      background-position: center center;
      background-repeat: no-repeat;
      height: 30px;
      width: 25px;
      border: none;
      background-color: transparent;
      cursor: pointer;
      position: relative;
      top: 9px;
      left: -44px;
   }
	
</style>	

<script type="text/javascript">
$("#myBtn").click(function(){
	var frmDate=document.getElementById("input").value;  
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

<table>
  <h1 colspan="4" align="center" style="color: #00BFFF;font-size: 18px;font-weight: bold;"> Real-time Swayam Transaction on
    <span  id="mySpan1"> {{allIndiaDate}} </span>
     <span  id="mySpan">  {{CurrentDate | date:'EEE,dd MMM, yyyy '}}</span>
   </h1> 

    </table>
		<table style="border-spacing: 50px;">
        <tr>
           <td> <input id="myBtn" value="Yesterday" class="openFinalPopup" ng-model="date" type="button" style="font-size: 20px;width: 106px;height: 30px;"/></td>
        </tr>
		</table>
		<br/>
<!--
<div>
 <pre style="background-color: #00BFFF;color: white;font-size: 12px;font-weight: bold;">
<span>Real-time Swayam Transaction</span>
 <span colspan="4" align="center" style="color: white;font-size: 12px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId"></span></span> 
</pre> 

<pre align="left" style="background-color: #00BFFF;color: white;font-size:24px;font-weight: bold;">
     <span>Real-time Swayam Transactions<span colspan="4" align="center" style="color: white;font-size: 24px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId"></span></span>
    </span>
</pre>
</div>
-->
<div>
		<pre align="left" style="background-color: #00BFFF;color: white;font-size:18px;font-weight: bold;font-family:Helvetica;">
 <span align="center" style="font-family:Helvetica">Real-time Swayam Transactions<span colspan="4" align="center" style="color: white;font-size: 18px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId"></span></span></span>
</pre>
			</div> 


<!--
  <table>
  <h1 colspan="4" align="center" style="color: #05fc47;font-size: 12px;font-weight: bold;"> Real-time Swayam Transaction on  <%= (new java.util.Date()).toLocaleString()%> </h1> 

    </table>
		<table style="border-spacing: 50px;">
        <tr>
           <td> <input value="Yesterday" class="openFinalPopup" ng-model="date" type="button" style="font-size: 20px;width: 106px;height: 30px;"/></td>
        </tr>
		</table>

<div>
<pre style="background-color: #00BFFF;color: white;font-size: 12px;font-weight: bold;">
<span>Real-time Swayam Transaction</span>
 <span colspan="4" align="center" style="color: white;font-size: 12px;font-weight: bold;float:right; margin-right:1em">Last Updated :<span id="dateId">ss</span></span> 
</pre>
</div>  -->
		<div class="submain_transaction">
	
	<input type="search" class="form-group has-search" ng-model="searchText" placeholder="Enter Kiosk Id, Branch Code, Branch Name, Circle " style="font-size: 12px" size="150" height="80" id="input">
	<!--  <input type="reset" value="" alt="clear" ng-click="clearSearch()" /> -->
			 <button  id="btnSearchText" ng-click="refresh()">Search</button> 
		  <button  id="btnClearText" ng-click="clearSearch()">ClearSearch</button>	 
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
        
    	$("#contentHomeApp").load('td/realTimeTransactionYestrday'); 
    	//$("#contentHomeApp").load('/td/realTimeTransaction'); 
       
    }); 
    
});

</script>	
	
<script type="text/javascript">
      
      $(document).ready(function(){

    	    $(".openpdfonclick").click(function(){
    	    	$("#loading").show(); 
    	        $.ajax({
    	            url: 'report?page=realTimeToday&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	if(data.includes(".pdf")){
    	            		console.log("PDF Data1" + data);
    	            		window.open("resources/download/"+data , '_blank');
    	            		$("#loading").hide(); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            		$("#loading").hide(); 
    	            	}  
    	            }
    	        });
    	    });
    	    $(".openxlonclick").click(function(){  
    	    	$("#loading").show(); 
    	        $.ajax({
    	            url: 'report?page=realTimeToday&type=excel',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	if(data.includes(".xlsx")){
    	            		console.log("Excel Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		$("#loading").hide(); 
    	            		
    	            	}else{
    	            		console.log("Excel Data" + data);
    	            		alert("No Data to Export");
    	            		$("#loading").hide(); 
    	            	}  
   
    	            }
    	        });
    	    });
    	}); 
    	   		
      
      </script>
</body>
 
</html>