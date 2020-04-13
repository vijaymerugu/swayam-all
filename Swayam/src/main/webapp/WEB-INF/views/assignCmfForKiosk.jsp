<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

	<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="/resources/js/users-la-app.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>

.main{
top: 102px;
left: 0px;
width: 1367px;
height: 860px;
background: #EFF3F6 0% 0% no-repeat padding-box;
opacity: 1;
}
.submain{

top: 1000px;
left: 15px;
width: 1336px;
height: 519px;
background: #FFFFFF 0% 0% no-repeat padding-box;
box-shadow: 0px 3px 6px #8D8D8D29;
opacity: 1;
border: 1px solid black;
}
table.table1 {
  top: 152px;
left: 15px;
width: 1336px;
height: 156px;
background: #FFFFFF 0% 0% no-repeat padding-box;
box-shadow: 0px 3px 6px #8D8D8D29;
opacity: 1;
border: 1px solid black;
border-collapse: separate;
    border-spacing: 0em;
	border-spacing: 0;
}
#noOfUsers{
top: 163px;
left: 70px;
width: 84px;
height: 20px;
text-align: left;
letter-spacing: 0;
color: #000000;
opacity: 1;
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
font-size: 15px;
font-weight: bold;
border: 0px solid black;
}

#count1{
top: 213px;

width: 46px;

text-align: center;
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
font-size: 20px;
font-weight: bold;
letter-spacing: 0;
color: #13A8E0;
opacity: 1;
border: 1px solid black;
padding:0px; 
border-width:0px; 
margin:0px; 
bottom: 0;
border: 0px solid black;
vertical-align:bottom;
}
#count2{
top: 246px;

width: 30px;

text-align: center;
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
font-size: 13px;
letter-spacing: 0;
color: #000000;
opacity: 0.75;
border: 1px solid black;
padding:0px; 
border-width:0px; 
margin:0px; 
border: 0px solid black;
vertical-align:top;
}
/* The Modal (background) */


</style>

</head>

<body>
<div class="main" ng-app="asignCmfapp">


<table class="table1">
<tr>
<td>Kiosk Id: ${kioskDto.kioskId}</td>
<td>Circle : ${kioskDto.circle}</td>
<td>Branch Code  :${kioskDto.branchCode}</td>
<td>Vendor  :${kioskDto.vendor}</td>
</tr>
<tr>
<td>Installation Status :${kioskDto.installationStatus}</td>

<td colspan="">PF ID  :</td><td colspan="2">
								<select id="username" name="username" class="form-control select2" style="width: 100%;" >
                                <option value="0">Select</option>
                                  <c:forEach items="${usersList}" var="usr">
                                    <option value="${usr.pfId}">${usr.pfId}</option>
                                 </c:forEach>
                                </select></td>
</tr>

</table>
<br/>
<br/>
<br/>
<table>
<tr>
<td>PF ID:  <input type="text"   name="pfId" id="pfId"/> </td>
<td>Username:<input type="text"   name="uname" id="uname" /> </td>
</tr>
<tr>
<td>Phone Number:  <input type="text"   name="phone" id="phone" />  </td>
<td>Email ID: <input type="text"   name="email" id="email" /> </td>
</tr>

</table>

<table>
<tr>
<td>
<input type="hidden"   name="kioskId" value="${kioskDto.kioskId}" />

</td>
<td>

<input type="submit" value="Submit" class="openPopupAssign">
</td>
</tr>
</table>


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
        
	</div>
</div>
</div>
<script>
$("#username").bind("change", function(e){
	  $.getJSON("/km/getUserByUsername?username="+$("#username option:selected").val(),
			  function(data) {
		  
		             $("#pfId").val(data.pfId);
		             $("#uname").val(data.username);
		             $("#phone").val(data.mobileNo);
		             $("#email").val(data.mailId);
		           });	        
	});
$(document).ready(function(){
    $('.openPopupAssign').on('click',function(){
    
        var url= "/km/saveSingleCmfKioskMapping?username="+$("#username option:selected").val()+"&kioskId="+ $('input[name=kioskId]').val();
        $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        });
    }); 
});

</script>

</body>

</html>