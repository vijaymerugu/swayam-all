<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
	
	

</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
<a  href="${pageContext.request.contextPath}/km/addUser" align="right">AddUser</a>
<div>
<table class="table1" style="border: 1px solid #eee;">

<tr>
    <td id="noOfUsers" colspan="6">No of users</td>
  </tr>
    <tr>
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('CMF')">${cmfCount}</a></td> 
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('CMS')"> ${cmsCount}</a>  </td> 
	<td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('MUMBAI')">${circleCountByRole}</a></td>   
    <td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"> <a ng-click="getCountType('LA')">${laCount}</a></td>   
	<td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('CC')">${ccCount}</a></td>
	<td id="count1" style="color: #13A8E0; border-right: solid 2px #faf5f6;"><a ng-click="getCountType('SA')">${saCount}</a></td>
  </tr>
  <tr>
  <!-- Yogesh User Circle Wise -->
    <td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMF</td> 
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CMS</td>   
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">Circle</td>   
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">LA</td>   
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">CC</td> 
	<td id="count2" style="color: black; border-right: solid 2px #faf5f6;">SA</td>  	
  </tr>
</table>
</div>
<br/><br/>
		<div class="submain">
	
	<br/>
	<br/>
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" size="150" height="80" id="input" class="form-group has-search">
		
	
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter id="test"></div>
		
        
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

	
	


<script src="/resources/js/users-la-app.js"></script>
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
</body>
</html>