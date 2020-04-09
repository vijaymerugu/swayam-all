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
   
</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
<table class="table1">

<tr>
    <td id="noOfUsers" colspan="6">No of users</td>
    
  </tr>
  <tr>
    <td id="count1" style=""><a ng-click="getCountType('CMF')">200</a></td> 
	<td id="count1">500</td>   
	<td id="count1">30</td>   
	<td id="count1">60</td>   
	<td id="count1">100</td> 
	<td id="count1">12</td>  	
  </tr>
  <tr>
    <td id="count2">CMF</td> 
	<td id="count2">CMS</td>   
	<td id="count2">Circle</td>   
	<td id="count2">LA</td>   
	<td id="count2">CC</td> 
	<td id="count2">SA</td>  	
  </tr>
</table>
<br/><br/>
		<div class="submain">
	
	<br/>
	<br/>
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px" size="150" height="80">
		
		<br/>
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination id="test"></div>
		
        
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