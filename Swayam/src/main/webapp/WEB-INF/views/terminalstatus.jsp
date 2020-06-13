<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css">

<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>
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

<!--  lll-->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
	
	<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
	
	<script src="/resources/js/terminal-status.app.js"></script>

</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">

<!--  -->


<div>

			  <table class="table1"
				style="top: 152px; left: 15px; width: 1336px; height: 190px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1;">






				<thead>
					<tr
						style="color: #000000; font-size: 12px; font-weight: bold; display: inline-block; width: 100px;"
						width="10%">
						<th id="noOfUsers" colspan="2" align="center">Summary</th>
					</tr>
					<tr></tr>

					<tr
						style="color: #000000; font-size: 12px; font-weight: bold; width: 119px;" >
						<th colspan="4" style="text-align: -webkit-center;">PB STATUS</th>
						<th colspan="3" style="text-align: centor;">CARTRIDGE STATUS</th>
						<th colspan="3" style="text-align: centor;">ANTIVIRUS STATUS</th>
						<th colspan="3" style="text-align: centor;">APPLICATION  STATUS</th>
						
					</tr>


				</thead>

				<tbody>
					<tr align="center">
						<td id="count1">
						  <a ng-click="getCountType('NoOfRedPVSRed')"><c:out value="${mapAgentStatusCount['RedCount']}"/></a></td>
						
						<td id="count1">
						 <a ng-click="getCountType('NoOfGreenPVSGreen')"><c:out value="${mapAgentStatusCount['GreenCount']}"/></a>  					
						</td>
						
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('NoOfGreyPVSGrey')"><c:out value="${mapAgentStatusCount['GreyCount']}"/></a></td>   

						 <td id="count1">
						 <a ng-click="getCountType('NoOfRedCARTRed')"><c:out value="${mapAgentStatusCount['RedCartriCount']}"/></a>
						 </td>
						 
						<td id="count1">
						 <a ng-click="getCountType('NoOfGreenCARTGreen')"><c:out value="${mapAgentStatusCount['GreenCartriCount']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						 <a ng-click="getCountType('NoOfGreyCARTGrey')"><c:out value="${mapAgentStatusCount['GreyCartriCount']}"/></a>
						</td>
						
						<td id="count1">
						<a ng-click="getCountType('NoOfRedANTRed')"><c:out value="${mapAgentStatusCount['RedAntivirusCount']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('NoOfGreenANTGreen')"><c:out value="${mapAgentStatusCount['GreenAntivirusCount']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('NoOfGreyANTGrey')"><c:out value="${mapAgentStatusCount['GreyAntivirusCount']}"/></a>
						</td>

						<td id="count1">
						<a ng-click="getCountType('NoOfRedAPPSRed')"><c:out value="${mapAgentStatusCount['RedApplicationCount']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('NoOfGreenAPPSGreen')"> <c:out value="${mapAgentStatusCount['GreenApplicationCount']}"/></a>
						</td>
						
						<td id="count1">
						<a ng-click="getCountType('NoOfGreyAPPSGrey')"><c:out value="${mapAgentStatusCount['GreyApplicationCount']}"/></a>
						</td>
					</tr>
					<tr align="center"
						style="color: #000000; font-size: 12px; font-weight: bold;"
						width="10%">
						<td id="count2">No Of Red</td>
						<td id="count2">No Of Green</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">No Of Grey</td>

						<td id="count2">No Of Red</td>
						<td id="count2" style="color: black;">No Of Green</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">No Of Grey</td>

						<td id="count2">No Of Red</td>
						<td id="count2" style="color: black;">No Of Green</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">No Of Grey</td>

						<td id="count2" class="vi" style="color: black;">No Of Red</td>
						<td id="count2" style="color: black;">No Of Green</td>
						<td id="count2" style="color: black;">No Of Grey</td>

					</tr>
				</tbody>
			</table>
		</div>

<br/>
	<br/>









<br/><br/>
		<div class="submain">
	
	<br/>
	<br/>
	<input type="checkbox" ng-model="searchText" ng-change="refresh()" placeholder="Enter Kiosk Id,Agent Status etc." style="font-size: 12px" size="150" height="80" id="input" class="form-group has-search">
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter id="test"></div>
    </div>
	</div>
</div>	

</body>
</html>