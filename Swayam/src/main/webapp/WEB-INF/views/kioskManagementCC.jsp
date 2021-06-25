
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
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>
 <script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
<!--  lll-->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
	
	
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

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
<div ng-controller="UserManagementCtrl as vm">
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
						<th colspan="3" style="text-align: center;">NO OF KIOSKS</th>
					<!-- 	Commented temporarily by Manisha
						<th colspan="3" style="text-align: centor;">CMS ROLLOUT</th>
						<th colspan="3" style="text-align: centor;">LIPI ROLLOUT</th> -->
						<th colspan="3" style="text-align: center;">ASSIGNED KIOSKS</th>
					</tr>


				</thead>

				<tbody>
					<tr align="center">
						<td id="count11">
						  <a ng-click="getCountType('InstalledKiosks')" style="cursor: hand;cursor: pointer;"><c:out value="${mapDataCount['InstalledKiosks']}"/></a>
						</td>
						
						<td id="count11">
						 <a ng-click="getCountType('CMS')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['CMS']}"/></a>						
						</td>
						
						<td id="count11" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('LIPI')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['LIPI']}"/></a>	
						</td>

					<%-- 	Commented temporarily by Manisha
						 <td id="count1">
						 <a ng-click="getCountType('CMS')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['CMS']}"/></a>
						 </td>
						 
						<td id="count1">
						 <a ng-click="getCountType('InstalledCMSVendor')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['InstalledCMSVendor']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						 <a ng-click="getCountType('DeleviredCMSVendor')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['DeleviredCMSVendor']}"/></a>
						</td>
						
						<td id="count1">
						<a ng-click="getCountType('LIPI')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['LIPI']}"/></a>
						</td>
						<td id="count1">
						<a ng-click="getCountType('InstalledLIPIVendor')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['InstalledLIPIVendor']}"/></a>
						</td>
						<td id="count1" style="border-right: solid 1px #0307fc;">
						<a ng-click="getCountType('DeleviredLIPIVendor')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['DeleviredLIPIVendor']}"/></a>
						</td> --%>

						<td id="count11">
						<a ng-click="getCountType('Assigned')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['Assigned']}"/></a>
						</td>
						<td id="count11">
						<a ng-click="getCountType('ToBeAssigned')" style="cursor: hand;cursor: pointer;"> <c:out value="${mapDataCount['ToBeAssigned']}"/></a>
						</td>
						
					</tr>
					<tr align="center"
						style="color: #000000; font-size: 12px; font-weight: bold;">
						<td id="count21">Installed Kiosks</td>
						<td id="count21">CMS</td>
						<td id="count21"
							style="color: black; border-right: solid 1px #0307fc;">LIPI</td>

					<!-- 	Commented temporarily by Manisha
						<td id="count2">Total Kiosks</td>
						<td id="count2" style="color: black;">Installed</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">Delivered</td>

						<td id="count2">Total Kiosks</td>
						<td id="count2" style="color: black;">Installed</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">Delivered</td> -->

						<td id="count21" class="vi" style="color: black;">Assigned</td>
						<td id="count21" style="color: black;">To be Assigned</td>

					</tr>
				</tbody>
			</table>
		</div>

<br/>
	
		<div class="submain_kiosk">
	
	
	<input ng-model="searchText" placeholder="Enter Circle,Branch Code,Branch Name,Kiosk ID.." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input"> 
	 <button  id="btnSearchText" ng-click="refresh()">Search</button> 
		 <button  id="btnClearText" ng-click="clearSearch()">ClearSearch</button>
	<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
		
		<br/>
		<div class="loading" id="loading" align="center" style="display:none;">
   			 <img src="resources/img/loader.gif"> 
		</div> 
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
 </div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);

$(document).ready(function(){

    $(".openpdfonclick").click(function(){
    	$("#loading").show(); 
        $.ajax({
            url: 'report?page=kiosksAll&type=pdf',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	window.open("resources/download/"+data , '_blank');  
            	$("#loading").hide(); 
            }
        });
    });
    $(".openxlonclick").click(function(){    
    	$("#loading").show(); 
        $.ajax({
            url: 'report?page=kiosksAll&type=excel',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	window.open("resources/download/"+data , '_blank');  
            	$("#loading").hide(); 
            }
        });
    });
}); 

</script>
</body>
 
</html>