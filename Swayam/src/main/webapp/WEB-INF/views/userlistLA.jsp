<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="sbi.kiosk.swayam.common.dto.UserDto" %>

<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<!-- <script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script> -->
<script	src="resources/js/ui-grid.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/bootstrap.min.css"> 
<script src="resources/js/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>



<!--  lll-->

 <script src="resources/js/jquery.min.js"></script>
  <script src="resources/js/bootstrap.min.js"></script>


<link rel="stylesheet"
	href="resources/css/ui-grid.min.css">
	
<!-- 	<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css"> -->
<script
	src="resources/js/angular.min.js"></script>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<script
	src="resources/js/ui-grid.min.js"></script>
	
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
		<%
			UserDto userObj = (UserDto) session.getAttribute("userObj");
			String circle = "";			
			if(userObj.getCircle() !=null){
				circle = userObj.getCircle();
			}			
			
		%>
<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
<%-- <a  href="${pageContext.request.contextPath}/km/addUser" align="right">AddUser</a> --%>
<div style="text-align: right;float: right;"><a class="openFinalPopup" style="cursor: hand;cursor: pointer;"><img src="resources/img/plus.png">&nbsp;AddUser</a></div>
<div>
<table class="table1" style="border: 1px solid #eee;">

<tr>
    <td id="noOfUsers" colspan="6">No of users</td>
  </tr>
    <tr>
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('LA')" style="cursor: hand;cursor: pointer;">${laCount}</a></td>
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('CMF')" style="cursor: hand;cursor: pointer;">${cmfCount}</a></td> 
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMS')" style="cursor: hand;cursor: pointer;"> ${cmsCount}</a>  </td> 
	<td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('C')" style="cursor: hand;cursor: pointer;">${circleUserCount}</a></td>   
    
  </tr>
  <tr>
  <!-- Yogesh User Circle Wise -->
    <td id="count2" style="color: black; border-right: solid 2px #faf5f6;">Local Admin</td>   
    <td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMF</td> 
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMS</td>   
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">Circle</td>   	
  </tr>
</table>
</div>
<br/>
		<div class="submain">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" "cursor: hand; cursor: pointer;" size="150" height="80" id="input" class="form-group has-search">
	<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
	</span>		
	
		
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	

	<!-- The Modal -->
<div class="modal fade" id="myModal" role="dialog">
<div class="modal-dialog">`
  <!-- Modal content -->
  <div class="modal-content">    
  		<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
          <%-- <jsp:include page="kioskAssignedLA.jsp" /> --%> 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"></button>
        </div>
	</div>
</div>
</div>

	
	


<script src="resources/js/users-la-app.js"></script>
<script> 
 $(document).ready(function(){	
    $('.openPopup').on('click',function(){    	
        var dataURL = $(this).attr('data-href');
        var dataVal = $(this).attr('data-val');        
        $('.modal-body').load(dataURL+dataVal,function(){
            $('#myModal').modal({show:true});
        });
    }); 
}); 
 
</script>

<script type="text/javascript">



$(document).ready(function(){
	    $('.openFinalPopup').on('click',function(){      
	        
	    	$("#contentHomeApp").load('km/addUserLA');    	
	      	    }); 
	});
	
$(document).ready(function(){

    $(".openpdfonclick").click(function(){
    	
        $.ajax({
            url: 'report?page=userListLA&type=pdf',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	window.open("resources/download/"+data , '_blank');  
            }
        });
    });
    $(".openxlonclick").click(function(){    	
        $.ajax({
            url: 'report?page=userListLA&type=excel',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	window.open("resources/download/"+data , '_blank');  
            }
        });
    });
}); 
	
</script>

</body>
 
</html>