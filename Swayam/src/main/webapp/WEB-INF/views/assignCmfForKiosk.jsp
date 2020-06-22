<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/users-la-app.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css" />
<link rel="stylesheet" href="resources/css/body-page.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<style>
{
background


:white


;
}
</style>
<style>
input[type=button], input[type=submit], input[type=reset] {
	background-color: #f5e947;
	border: 2px;
	color: black;
	padding: 6px 12px;
	type ="text"-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<style>
.btn-group button {
	background-color: white; /* Green background */
	border: 3px solid while; /* Green border */
	color: black; /* White type="text" */
	padding: 5px 30px; /* Some padding */
	cursor: pointer;
	/* Pointer/hand icon */
	float: left;
	border-radius: 9px
}

/* Clear floats (clearfix hack) */
.btn-group:after {
	content: "";
	clear: both;
	display: table;
}

.btn-group
 
button
:not
 
(
:last-child
 
)
{
border-right
:
 
none
; /* Prevent double borders */


}
.btn-group button:hover {
	background-color: blue;
}

input[type="text"]:hover {
	background-color: #fcfafa;
}

input[type=text], select {
	background-color: #f2f1ef;
	height: 22px;
	border-style: outset;
}
</style>
<style>
.cmf table {
	background-color: white;
	width: 99%;
	height: 40px;
}
</style>
<style>
.th-next table2 {
	top: 195px;
	left: 31px;
	width: 1303px;
	height: 145px;
	background: #F3F7FA 0% 0% no-repeat padding-box;
	opacity: 1;
}
</style>
<!-- <style>
.th-next tablecss {
	background-color: white;
	border: 3px solid while;
	width: 60%;
	height: 200px;
}
</style> -->
<script type="text/javascript">

function fromValidation(){
	//  ("form validation call ");
    //debugger;	
	var errorList=[];
	var pfId=$("#username").val();
	
	if(pfId=="Select"){
		 errorList.push("Please select PF ID");		 
	 }
	return errorList;
}	

function displayErrorsOnPage() {
	//  ("form displayErrorsOnPage call ");
	var errMsg='';
	$("#pfId12").html("");
	if($("#username").val()=="Select"){
		$("#pfId12").html("Please select PF ID");
	}
	
}	
</script>

</head>
<body bgcolor="white">
	<br>
	<br>
	<div class="cmf">
		<table style="width:1360px">
			<tr>
				<td><b>Assigned CMF</b></td>
			</tr>
		</table>
	</div>
	<div class="th-next">
		<table class="table1" style="width:1360px">
			<tr>
				<td><b style="color: #2F246C;">Kiosk Id : </b><b>${kioskDto.kioskId}</b></td>
				<td><b style="color: #2F246C;">Circle : </b><b>${kioskDto.circle}</b></td>
				<td><b style="color: #2F246C;">Branch Code : </b><b>${kioskDto.branchCode}</b></td>
				<td><b style="color: #2F246C;">Vendor: </b><b>${kioskDto.vendor}</b></td>
			</tr>
			<tr>
				<td><b style="color: #2F246C;">Installation Status: </b><b>${kioskDto.installationStatus}</b></td>

				<td colspan=""><b style="color: #2F246C;">PF ID :</b></td>
				<td><select id="username" name="username"
					class="form-control select2" style="width: 40%;">
						<option value="Select">Select</option>
						<c:forEach items="${usersList}" var="usr">
							<option value="${usr.pfId}">${usr.pfId}</option>
						</c:forEach>
				</select></td>
				<td><span id="pfId12" style="color: red"></span></td>
			</tr>
		</table>
	</div>

	<div class="submain">

		<div class="tablecss">
			<table align="center" cellpadding="10">
				<tr>
					<td><b style="color: #2F246C;">PF ID </b></td>
					<td style="top: 352px; width: 190px; height: 75px; opacity: 1;">
						<input type="text" name="pfId" id="pfId" readonly="true" />
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td><b style="color: #2F246C;">Username</b></td>
					<td><input type="text" name="uname" id="uname" readonly="true" /></td>
				</tr>
				<tr>
					<td><b style="color: #2F246C;">Phone Number</b></td>
					<td style="top: 352px; width: 190px; height: 75px; opacity: 1;"><input
						type="text" name="phone" id="phone" readonly="true" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td><b style="color: #2F246C;">EmailId</b></td>
					<td><input type="text" name="email" id="email" readonly="true" /></td>
				</tr>
				<tr>
					<td><b style="color: #2F246C;">Reporting Authority Name &nbsp;</b></td>
					<td style="top: 352px; width: 190px; height: 75px; opacity: 1;"><input
						type="text" name="reportingAuthorityName"
						id="reportingAuthorityName" readonly="true" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td><b style="color: #2F246C;">Reporting Authority Email &nbsp;</b></td>
					<td><input type="text" name="reportingAuthorityEmail"
						id="reportingAuthorityEmail" readonly="true" /></td>
				</tr>
			</table>
			<br> <br> <br>
			<table align="center">
				<tr>
					<td><input type="button" value="BACK" class="openBackPopup"/></td>
					<!-- <td><input type="button" value="ASSIGN" onclick="addUser()" /></td> -->
					
					<td><input type="hidden" name="kioskId"	value="${kioskDto.kioskId}" /></td>
					<td><input type="button" value="ASSIGN"	class="openPopupAssign"></td>
				</tr>
			</table>
		</div>
	</div>



	<!-- The Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			`
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
		$("#username").bind(
				"change",
				function(e) {
					$.getJSON("km/getUserByUsername?username="
							+ $("#username option:selected").val(), function(
							data) {

						$("#pfId").val(data.pfId);
						$("#uname").val(data.username);
						$("#phone").val(data.phoneNo);
						$("#email").val(data.mailId);
						$("#reportingAuthorityName").val(data.mailId);
						$("#reportingAuthorityEmail").val(data.mailId);
					});
				});
		$(document).ready(
						function() {
							$('.openPopupAssign').on('click',function() {
								var errorlist=fromValidation();							 
								 
								 if(errorlist.length>0){											 
									 displayErrorsOnPage();									 
								 }else{
									 $("#pfId12").html("");	
												var url = "km/saveSingleCmfKioskMapping?username="
														+ $("#username option:selected").val()
														+ "&kioskId="
														+ $('input[name=kioskId]').val();
												$('.modal-body')
														.load(url,function() {
																	$('#myModal').modal(
																					{
																						show : true
																					});
																});
								 }			
								 });//
						});
		
		
		$(document).ready(function(){
		    $('.openBackPopup').on('click',function(){      
		        
		    	$("#contentHomeApp").load('km/kioskManagement');    	
		       
		    }); 
		    
		});		
	</script>


</body>
</html>