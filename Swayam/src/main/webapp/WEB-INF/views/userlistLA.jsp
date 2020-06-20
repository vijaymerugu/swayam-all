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

<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>

<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>


    <script src="resources/js/csv.js"></script>
    <script src="resources/js/pdfmake.js"></script>
    <script src="resources/js/vfs_fonts.js"></script>
    <script src="resources/js/lodash.min.js"></script>
    <script src="resources/js/jszip.min.js"></script>
    <script src="resources/js/excel-builder.dist.js"></script>  
    <script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>

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
<div style="text-align: right;float: right;"><a class="openFinalPopup"><img src="resources/img/plus.png">&nbsp;AddUser</a></div>
<div>
<table class="table1" style="border: 1px solid #eee;">

<tr>
    <td id="noOfUsers" colspan="6">No of users</td>
  </tr>
    <tr>
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('LA')">${laCount}</a></td>
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('CMF')">${cmfCount}</a></td> 
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMS')"> ${cmsCount}</a>  </td> 
	<td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('C')">${circleUserCount}</a></td>   
    
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
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" size="150" height="80" id="input" class="form-group has-search">
	<span style="float:right">
		<a class="openpdfonclick"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick"><img src="resources/img/excel.svg"></a>
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