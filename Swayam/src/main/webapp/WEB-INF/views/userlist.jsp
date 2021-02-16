<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="resources/js/ui-grid.min.js"></script>
<script src="resources/js/users-app.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="resources/css/bootstrap.min.css"> 
<script src="resources/js/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>
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
			 .ui-grid .ui-grid-render-container-body .ui-grid-viewport {
 			 	overflow-x: auto !important;
  				overflow-y: auto !important;
  				
			}
			.ui-grid-pager-row-count-picker {
			display:none;
			}
</style>	
    
</head>
<body>
<div class="main_transaction" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">

<div style="text-align: right;float: right;"><a class="openFinalPopup" style="cursor: hand;cursor: pointer;"><img src="resources/img/plus.png">&nbsp;AddUser</a></div>
<div>
			<table class="table1" style="border: 1px solid #eee; width: 100%;">
				


				<tr>
					<td id="noOfUsers" colspan="6">No of users</td>

				</tr>
				<tr>
				
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMF')"  style="cursor: hand;cursor: pointer;" >${cmfCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMS')" style="cursor: hand;cursor: pointer;"> ${cmsCount}</a></td>					
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('C')" style="cursor: hand;cursor: pointer;">${circleUserCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('LA')" style="cursor: hand;cursor: pointer;">${laCount}</a></td>   
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('CC')" style="cursor: hand;cursor: pointer;">${ccCount}</a></td>
					<td  id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('SA')" style="cursor: hand;cursor: pointer;">${saCount}</a></td>
				</tr>
				<tr>
					<!-- Vijay All Circle wise -->
					
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMF</td>
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMS</td>	
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">Circle</td>				
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">Local Admin</td>
					<td id="count2" style="color: black; border-right: solid 1px #faf5f6;">CC</td>
					<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">System Admin</td>
				</tr>
			</table>
		</div>
<br/>
		<div class="submain_kiosk">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" "cursor: hand;cursor: pointer;" size="150" height="80"   class="form-group has-search" id="input">
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


</script>
<script type="text/javascript">

$(document).ready(function(){
	    $('.openFinalPopup').on('click',function(){      
	        
	    	$("#contentHomeApp").load('km/addUser');    	
	      	    }); 
	    
	});
$(document).ready(function(){

    $(".openpdfonclick").click(function(){
    	$("#loading").show(); 
        $.ajax({
            url: 'report?page=userListSA&type=pdf',
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
            url: 'report?page=userListSA&type=excel',
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