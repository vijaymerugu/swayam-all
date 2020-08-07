<%@ page import="sbi.kiosk.swayam.common.dto.UserDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>


<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">


<link rel="stylesheet" href="resources/css/grid-style.css" />
<link rel="stylesheet" href="resources/css/body-page.css" />
<script
	src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<link href="resources/css/menu.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="resources/css/ui-grid.css"
	type="text/css" />
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
<style>
 body {
     font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 14px;
    line-height: -2.571429;
    color: #333;
    background-color: #fff;
    margin: 261px;
}
html {
    font-size: 10px;
    -webkit-tap-highlight-color: rgba(0,0,0,0);
}


input[type=submit], input[type=reset] {
	background-color: #F2F1EF;
	height: 50px;
	border-top: 1px #F2F1EF;
	border-left: 2px whtie;
	border-bottom-width: 4px #F2F1EF;
	width: 72%;
	align: center;
	border-bottom-width: 0px;
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: #fced19; /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding: 0;
	border: 1px solid #black;
	width: 40%;
	height: 40%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

/* Add Animation */
@
-webkit-keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
@
keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}

/* The Close Button */
.close {
	color: black;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.modal-header {
	padding: 2px 16px;
	background-color: #5cb85c;
	color: white;
}

.modal-body {
	padding: 2px 16px;
}

.modal-footer {
	padding: 2px 16px;
	background-color: #5cb85c;
	color: white;
}
</style>


<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 92px; /* Location of the box */
	left: 0;
	top: 0;
	border: 1px solid #black;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.modal-content {
	background-color: #ebe6e6;
	margin: auto;
	padding: 93px;
	border: 1px solid black;
	width: 29%;
	height: 51%;
}

/* The Close Button */
.close {
	color: #black;
	float: right;
	font-size: 28px;
	font-weight: bold;
	margin-top: -99px;
	margin-right: -89px;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
<style type="text/css">


input[type=button], input[type=submit], input[type=reset] {
    background-color: #f2a50a;
    /* border: 95px; */
    color: black;
    padding: 1px 14px;
    text-decoration: none;
    margin: -5px 23px;
    cursor: pointer;
}
input[type=submit], input[type=reset] {
    background-color: #f2a50a;
    height: 34px;
    border-top: 1px #F2F1EF;
    border-left: 2px whtie;
    border-bottom-width: 4px #F2F1EF;
    width: 72%;
    align: center;
    border-bottom-width: 0px;
}
button, html input[type=button], input[type=reset], input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
}
.button {
    background-color: #FDD209;
    border-top: 2px #FDD209;
    border-bottom-width: 4px #FDD209;
    top: 670px;
    left: 579px;
    width: 97px;
    height: 32px;
    opacity: 1;
}
.openBackPopup {
    background-color: #FDD209;
    border-top: 2px #FDD209;
    border-bottom-width: 4px #FDD209;
    top: 670px;
    left: 579px;
    width: 97px;
    height: 32px;
    opacity: 1;
}

.reset {
    background-color: #FDD209;
    border-top: 2px #FDD209;
    border-bottom-width: 4px #FDD209;
    top: 670px;
    left: 579px;
    width: 97px;
    height: 32px;
    opacity: 1;
}

</style>

	
	
	
	
</script>


<script src="https://code.jquery.com/jquery-3.4.1.js"></script>


</head>

<body>


	<div  class="submainForm">

           <form:form action="addUsersLA" modelAttribute="billingAllocationDto" name="billingAllocationDto" id="form">
		<div style="margin-left: 40px; margin-right: 40px">
					<table id="myTable">
						<thead>
							<tr style="top: 244px; left: 282px; width: 801px; height: 42px; background: #13A8E0 0% 0% no-repeat padding-box; opacity: 1;">
								
								<th>RFP_REF_NO</th>
								<th>VENDOR</th>
								<th>Circle</th>
								<th>ALLOCATED_QUANTITY</th>
								<th>PO Number</th>
								<th>remainingQuantity</th>
							</tr>
						</thead>
						<tbody>
							         <td><form:input path="repRefNo" value="${billingAllocationDto.repRefNo}" maxlength="50"/></td>	
									<td><form:input path="vendorId" value="${billingAllocationDto.vendorId}" maxlength="50"/></td>
									<td><form:input path="crclCode" value="${billingAllocationDto.crclCode}" maxlength="50"/></td>
									<td><form:input path="allocatedQuantity" value="${billingAllocationDto.allocatedQuantity}" maxlength="50"/></td>
									<td><form:input path="unitPrice" value="${billingAllocationDto.unitPrice}" maxlength="50"/></td>
									<td><form:input path="remainingQuantity" value="${billingAllocationDto.remainingQuantity}" maxlength="50"/></td>
							
								</tr>
						
						</tbody>
					</table>
					</div>
					
					<table align="center">
					<tr>
						
					
                   <td><input type="button" onclick="saveform()"   class="button" value="UPDATE"></td>
					
	
					
					</tr>
				</table>

            </form:form>

		</div>

	</div>


	

	
	
	
	
	<script>
	function saveform() {
		var formData = $("#form").serialize();
		console.log("formData"+formData);
		 $.ajax({
	        	type:"POST",
	        	url:"km/addbillingLA",
	        	data:formData,
	         success: function(data){
	        	 resp=data;  
	        //	 alert(resp) ;    	 	        	 
	        	// $("#para").html("User: "+resp+ " has been successfully created");
	        	// $("#para").html(resp);
	     		// modal.style.display = "block";
	        	 
	         }
	        });
		
		
	}

</script>	
</body>
</html>