<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html lang="en">
<head>

<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>  

<!--  lll-->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
	
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
	
	<script src="resources/js/terminal-status.app.js"></script>
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
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
	
			.ui-grid-pager-row-count-picker {
			display:none;
			}
			.ui-grid .ui-grid-render-container-body .ui-grid-viewport {
    overflow-x: hidden !important;
    /* overflow-y: auto !important; */
}
.ui-grid-header-canvas {
    padding-top: 0px;
    padding-bottom: 0px;}
			
</style>
</head>
<body>

<div class="main_terminal" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">

<!--  -->
<div>

			  <table class="table1"
				style="top: 152px; left: 15px; width: 100%; height: 190px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1;">

				<thead>
						<tr
						style="color: #000000; font-size: 12px; font-weight: bold; display: inline-block; width: 100px;"
						width="10%">
						<th id="noOfUsers" colspan="2" align="center">Summary</th>
					</tr>
					<tr></tr>


				<tr
						style="color: #000000; font-size: 12px; font-weight: bold; width: 119px;" >
						<th colspan="4" style="text-align: -webkit-center;">PRINTER STATUS</th>
						<th colspan="3" style="text-align: centor;">CARTRIDGE STATUS</th>
						<th colspan="3" style="text-align: centor;">AGENT STATUS</th>
						<th colspan="3" style="text-align: centor;">APPLICATION  STATUS</th>
						
					</tr>


				</thead>

				<tbody>
					<tr align="center">
						<td id="count1">
						  <a ng-click="getCountType('NoOfRedPVSRed')" style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['PrinterStatusRedCount']}"/></a></td>
						
						<td id="count1">
						 <a ng-click="getCountType('NoOfGreenPVSGreen')" style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['PrinterStatusGreenCount']}"/></a>  					
						</td>
						
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('NoOfGreyPVSGrey')" style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['PrinterStatusGreyCount']}"/></a></td>   

						 <td id="count1">
						 <a ng-click="getCountType('NoOfRedCARTRed')"  style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['RedCartriCount']}"/></a>
						 </td>
						 
						<td id="count1">
						 <a ng-click="getCountType('NoOfGreenCARTGreen')"  style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['GreenCartriCount']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						 <a ng-click="getCountType('NoOfGreyCARTGrey')" style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['GreyCartriCount']}"/></a>
						</td>
						
						<td id="count1">
						<a ng-click="getCountType('NoOfRedANTRed')"  style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['AgentStatusRedCount']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('NoOfGreenANTGreen')"  style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['AgentStatusGreenCount']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('NoOfGreyANTGrey')"  style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['AgentStatusGreyCount']}"/></a>
						</td>

						<td id="count1">
						<a ng-click="getCountType('NoOfRedAPPSRed')"  style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['RedApplicationCount']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('NoOfGreenAPPSGreen')"  style="cursor: hand;cursor: pointer;"> <c:out value="${mapAgentStatusCount['GreenApplicationCount']}"/></a>
						</td>
						
						<td id="count1">
						<a ng-click="getCountType('NoOfGreyAPPSGrey')" style="cursor: hand;cursor: pointer;"><c:out value="${mapAgentStatusCount['GreyApplicationCount']}"/></a>
						</td>
					</tr>
					<tr align="center"
						style="color: #000000; font-size: 12px; font-weight: bold;"
						width="10%">
						<td id="count2">No Of Red</td>
						<td id="count2">No Of Green</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">No Of Gray</td>

						<td id="count2">No Of Red</td>
						<td id="count2" style="color: black;">No Of Green</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">No Of Gray</td>

						<td id="count2">No Of Red</td>
						<td id="count2" style="color: black;">No Of Green</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">No Of Gray</td>

						<td id="count2" class="vi" style="color: black;">No Of Red</td>
						<td id="count2" style="color: black;">No Of Green</td>
						<td id="count2" style="color: black;">No Of Gray</td>

					</tr>
				</tbody>
			</table>
		</div>
<br/>
	<div class="submain_terminal">
	
	<br/>
	<input  ng-model="searchText" ng-change="refresh()" placeholder="Enter Kiosk Id,Agent Status etc.." style="font-size: 12px" size="150" height="80" id="input" class="form-group has-search">
		<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter id="test"></div>
    </div>
	</div>
</div>		


<script type="text/javascript">
$(document).ready(function(){
	    
    $(".openpdfonclick").click(function(){
    	
        $.ajax({
            url: 'report?page=terminalStatus&type=pdf',
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
            url: 'report?page=terminalStatus&type=excel',
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
 <sec:csrfInput />  
</html>