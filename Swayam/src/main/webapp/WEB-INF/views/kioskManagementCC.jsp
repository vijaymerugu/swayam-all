
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/kiosks-km-app-cc.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
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
	
</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
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
						<th colspan="4" style="text-align: -webkit-center;">NO OF KIOSKS</th>
						<th colspan="3" style="text-align: centor;">VENDOR 1 ROLLOUT</th>
						<th colspan="3" style="text-align: centor;">VENDOR 2 ROLLOUT</th>
						<th colspan="3" style="text-align: centor;">ASSIGNED KIOSKS</th>
					</tr>


				</thead>

				<tbody>
					<tr align="center">
						<td id="count1">
						  <a ng-click="getCountType('InstalledKiosks')"><c:out value="${mapDataCount['InstalledKiosks']}"/></a>
						</td>
						
						<td id="count1">
						 <a ng-click="getCountType('CMS')"> <c:out value="${mapDataCount['CMS']}"/></a>						
						</td>
						
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('LIPI')"> <c:out value="${mapDataCount['LIPI']}"/></a>	
						</td>

						 <td id="count1">
						 <a ng-click="getCountType('CMS')"> <c:out value="${mapDataCount['CMS']}"/></a>
						 </td>
						 
						<td id="count1">
						 <a ng-click="getCountType('InstalledCMSVendor')"> <c:out value="${mapDataCount['InstalledCMSVendor']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						 <a ng-click="getCountType('DeleviredCMSVendor')"> <c:out value="${mapDataCount['DeleviredCMSVendor']}"/></a>
						</td>
						
						<td id="count1">
						<a ng-click="getCountType('LIPI')"> <c:out value="${mapDataCount['LIPI']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('InstalledLIPIVendor')"> <c:out value="${mapDataCount['InstalledLIPIVendor']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('DeleviredLIPIVendor')"> <c:out value="${mapDataCount['DeleviredLIPIVendor']}"/></a>
						</td>

						<td id="count1">
						<a ng-click="getCountType('Assigned')"> <c:out value="${mapDataCount['Assigned']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('ToBeAssigned')"> <c:out value="${mapDataCount['ToBeAssigned']}"/></a>
						</td>
						
					</tr>
					<tr align="center"
						style="color: #000000; font-size: 12px; font-weight: bold;"
						width="10%">
						<td id="count2">Installed Kiosks</td>
						<td id="count2">CMS</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">LIPI</td>

						<td id="count2">Total Kiosks</td>
						<td id="count2" style="color: black;">Installed</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">Delivered</td>

						<td id="count2">Total Kiosks</td>
						<td id="count2" style="color: black;">Installed</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">Delivered</td>

						<td id="count2" class="vi" style="color: black;">Assigned</td>
						<td id="count2" style="color: black;">To be Assigned</td>

					</tr>
				</tbody>
			</table>
		</div>

<br/>
	
		<div class="submain">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle Name,Branch Code,CMF Name,Kiosk ID.." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input"> 
		
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter id="test"></div>
		
        
    </div>
    
    
 </div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script>
</body>
</html>