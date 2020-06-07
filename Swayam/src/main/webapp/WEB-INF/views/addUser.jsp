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

/* -------------- */

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
<style>

/* Clear floats (clearfix hack) */
.btn-group:after {
	content: "";
	clear: both;
	display: table;
}

.btn-group button:not (:last-child ) {
	border-right: none; /* Prevent double borders */
}

.btn-group button:hover {
	background-color: blue;
}

input[type=text]:hover {
	background-color: #fcfafa;
}

input[type=text], select {
	background-color: #f2f1ef;
	height: 22px;
	border-style: outset;
	width: 88%;
}

td,
th {
  padding: 2;
}


element.style {
    top: 152px;
    left: 15px;
    /* width: 1336px; */
    height: 603px;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    box-shadow: 0px 48px 97px #8D8D8D29;
    opacity: 1;
}


.subdiv {
    margin: -186px;
    top: 568px;
    left: 636px;
    right: 743px;
    width: 1190px;
    height: 507px;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    box-shadow: 0px 3px 6px #8D8D8D29;
    opacity: 99;
    padding: 122px;
}


</style>

<script type="text/javascript">



function fromValidation(){
	//  ("form validation call ");
    //debugger;	
	var errorList=[];
	var pfId=$("#pfId").val();
	var userName=$("#username").val();
	var phoneNumber=$("#phoneNo").val();
    var emailId=$("#emailId").val();	
	var reportingAuthorityName=$("#reportingAuthorityName").val(); 
	var reportingAuthorityEmail=$("#reportingAuthorityEmail").val();
	var role=$("#role").val();
	var circle=$("#circle").val();
    
	if(pfId==""){
		 errorList.push("Please enter pfId");
	 }
	 if(userName==""){
		 errorList.push("Please enter user name");
	 }
	 if(phoneNumber==""){
		 errorList.push("Please enter phone nuber");
	 }else{
			var phone=$.trim($("#phoneNo").val());
			var phoneNumber=new RegExp(/^[+]?(\d{1,2})?[\s.-]?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/);
			var valid=phoneNumber.test(phone);
			if(!valid){
				 errorList.push("Please enter user name");
			 }				
		}

	 if(emailId==""){
		 errorList.push("Please enter phone Email Id");
	 }else{
		 var email= $.trim($("#emailId").val());
		 var emailrex=new RegExp(/^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$/i);
		 var valid=emailrex.test(email);
		 if(!valid){
			 errorList.push("Please enter phone Email Id");
		
		 }
	 }
	 if(reportingAuthorityName==""){
		 errorList.push("Please enter Reporting Authority Name");
	 }
	 if(reportingAuthorityEmail==""){
		 errorList.push("Please enter Reporting Authority Email");
		 
	 }else{
			var remail=$.trim($("#reportingAuthorityEmail").val());
			var reportEmail=new RegExp(/^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$/i);
			var valid=reportEmail.test(remail);
			if(!valid){
				errorList.push("Please enter Reporting Authority Email");
			 }	
		}

	 if(role=="Select"){
		 errorList.push("Please select valid role ");		 
	 }
	 if(circle=="Select" && role !="SA" && role !="CC"){
		 errorList.push("Please select valid Circle ");		 
	 }
	 return errorList;
}

</script>


<script> 
$(document).ready(function(){
	//debugger;
	var respos='';
	var errorList=[];
	$('#pfId').blur(function(){
		//debugger;
		 var pfId=$("#pfId").val();
		 document.getElementById("pfId").innerHTML=pfId;
		 if(pfId !=null && pfId !=""){
		 document.getElementById("pfId").innerHTML=pfId;
		 console.log("inside bluer function...."+pfId);
	         	        $.ajax({
	        	type:"GET",
	        	url:"km/getByPfIdSA/"+pfId,
	            success: function(data){
	            	console.log("inside data");
	        	    respos=data;
	        	 console.log("response "+respos);
	             $("#pfId12").html(data);
	
	            }
	         	   });
		 }else{
			 $("#pfId12").html("Please Enter Satendra Pf Id");
		 }
	});
});
</script>


<script type="text/javascript">
	function cloesBox() {
		var modal = document.getElementById("myModal");
		modal.style.display = "none";
	}

	
	var window;
	
	/* function openWin() {
	 // myWindow = window.open("", "myWindow", "width=400, height=200");
	 aler("Action call");
	 window.location = '/km/userList';
	} */
	
	/* function openWin(){
	      window.location.href = "/km/userList";
	}; */
	
	
	
	
	
	function displayErrorsOnPage() {
		//  ("form displayErrorsOnPage call ");
		var errMsg='';
		$("#emailId12").html("");
		$("#phoneNumber12").html("");	
		$("#pfId12").html("");	
		$("#userName12").html("");	
		$("#reportingAuthorityName12").html("");		
		$("#reportingAuthorityEmail12").html("");
		$("#role12").html("");
		$("#circle12").html("");
		//$.each(errorList, function(index) {
			
			if($("#emailId").val()==""){
				$("#emailId12").html("Please Enter Email");
			 }else{
				 
				 var email= $.trim($("#emailId").val());
				 var emailrex=new RegExp(/^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$/i);
				 var valid=emailrex.test(email);
				 if(!valid){
					 $("#emailId12").html("Please Enter Valid Email ID");
				 }
			 }
			if($("#phoneNo").val()==""){
				$("#phoneNumber12").html("Please Enter Phone Number");	
				
			}else{
					var phone=$.trim($("#phoneNo").val());
					var phoneNumber=new RegExp(/^[+]?(\d{1,2})?[\s.-]?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/);
					var valid=phoneNumber.test(phone);
					if(!valid){
						 $("#phoneNumber12").html("Phone Number Should be 10 digit");
					 }				
				}
			
			if($("#pfId").val()==""){
				//  ("pfId valida====");
				$("#pfId12").html("Please Enter Pf Id");	
			}
			if($("#username").val()==""){
				$("#userName12").html("Please Enter User Name");	
			}
			if($("#reportingAuthorityName").val()==""){
				$("#reportingAuthorityName12").html("Please Enter Reporting Authority Name");		
			}
			
			if($("#reportingAuthorityEmail").val()==""){
				$("#reportingAuthorityEmail12").html("Please Enter Reporting Authority Email");	
			}else{
				var remail=$.trim($("#reportingAuthorityEmail").val());
				var reportEmail=new RegExp(/^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$/i);
				var valid=reportEmail.test(remail);
				if(!valid){
					 $("#reportingAuthorityEmail12").html("Please Enter Reporting valid Authority Email");
				 }				
			}
		
			
			if($("#role").val()=="Select"){
				$("#role12").html("Please Select valid Role");
			}
			if($("#circle").val()=="Select" && $("#role").val() !="SA" && $("#role").val() !="CC"){
				$("#circle12").html("Please Select valid Circle");
			}
		//});

	}
	
	
	
	function saveform() {
		//  ("123");
		//debugger;
		
		 var errorlist=fromValidation();
		 //  (errorlist);
		 
		 if(errorlist.length>0){
			 //  ("124");
			 displayErrorsOnPage();
			 
		 }else{
			 //  ("else");
			    $("#emailId12").html("");
				$("#phoneNumber12").html("");	
				$("#pfId12").html("");	
				$("#userName12").html("");	
				$("#reportingAuthorityName12").html("");		
				$("#reportingAuthorityEmail12").html("");
				$("#role12").html("");
				$("#circle12").html("");
		
		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];
		var resp = '';
		var formData = $("#form").serialize();
		
		
		 $.ajax({
	        	type:"POST",
	        	url:"km/addUsers",
	        	data:formData,
	         success: function(data){
	        	 resp=data;       	 	        	 
	        	/*  $("#para").html("User: "+resp+ " has been successfully Created"); */
	        	 $("#para").html(resp);
	     		 modal.style.display = "block";
	        	 
	         }
	        });
		
		
	}
	}
	
	
	
	
</script>


<script src="https://code.jquery.com/jquery-3.4.1.js"></script>


</head>

<body>


	<div  class="submainForm">


	<!-- 	<h4 align="left">
			<b>Add User</b>
		</h4> -->
		<br></br>
		<br></br>
		
		<div class=""></div>
		
		<div class="col-md-12">
			<!-- <div align="center" class="mytable"> -->
			<form:form action="addUsers" modelAttribute="addUser" name="addUser" id="form">
				<c:if test="${empty  addUser.checkAction}">
				<h4 align="left">
			     <b>Add User</b>
		          </h4>
		          </c:if>
		          
		          <c:if test="${addUser.checkAction == 'Edit'}">
		          <b class="mess"></b>
				<h4 align="left">
			     <b>Edit User</b>
		          </h4>
		          </c:if>
		          
				<table align="center">
					<tr>
					<%-- <c:out value="${addUser.userId}">okk</c:out> --%>
					<form:hidden path="userId"/>
						<td><b style="color: purple">PF ID</b><b><span
								style="color: red">*</span></b></td>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;">
						<form:input path="pfId" id="pfId" required="required" /></td>
						<td></td>
						<td></td>
						<td><b style="color: purple">Username</b><b><span
								style="color: red">*</span></b></td>
						<td><form:input path="username" value="${addUser.username}"  />
					</tr>
					
					<tr>
						<td></td>
						<td><span id="pfId12" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="userName12" style="color: red"></span></td>
					</tr>
					<tr>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color: purple">Phone Number</b><b><span
								style="color: red">*</span></b></td>
						<td><form:input path="phoneNo" /></td>
						<td></td>
						<td></td>
						<td><b style="color: purple">EmailId</b><b><span
								style="color: red">*</span></b></td>
						<td><form:input path="emailId" value=""/>
					</tr>
					<tr>
						<td></td>
						<td><span id="phoneNumber12" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="emailId12" style="color: red"></span></td>
					</tr>
					<tr>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color: purple">Role</b><b><span
							 	style="color: red">*</span></b></td>
						<td><form:select path="role" id="role" value="${addUser.role}" style="color:blue">
								<form:option value="Select" label="Select"></form:option>
								<c:forEach var="list" items="${roleList}">
									<form:option value="${list.role}">${list.roleDescription}</form:option>
								</c:forEach>
							</form:select></td>
						<td></td>
						<td></td>
						<td colspan="2">
							<table style="display:none;" id="circleDiv">
							<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color: purple">Circle</b><b><span
								 	style="color: red">*</span></b></td>
							<td><form:select path="circle" id="circle"   value="${addUserDto.circle}" style="color:blue">
									<form:option value="Select" label="Select"></form:option>
									<c:forEach var="list" items="${circleList}">
										<form:option value="${list.circleName}">${list.circleName}</form:option>
									</c:forEach>
								</form:select></td>	
							</table>
						</td>
						
					</tr>
					<tr>
						<td></td>
						<td><span id="role12" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="circle12" style="color: red"></span></td>
					</tr>
					<tr>
						<td><b style="color: purple">Reporting Authority Name</b><b><span
								style="color: red">*</span></b></td>
						<td><form:input path="reportingAuthorityName" /></td>
						<td></td>
						<td></td>
						<td><b style="color: purple">Reporting Authority Email</b><b><span
								style="color: red">*</span></b></td>
						<td><form:input path="reportingAuthorityEmail" value="${addUser.reportingAuthorityEmail}"/></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td></td>
						<td><span id="reportingAuthorityName12" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="reportingAuthorityEmail12" style="color: red"></span></td>
					</tr>
				</table>
				<br>
				<br>
				<br>
				<table align="center">
					<tr>
						<!-- <td><input type="reset"  value="CANCEL"/></td>
						<td><input type="button" onclick="saveform()" value="ADD" /></td> -->
					<c:if test="${addUser.checkAction == 'Edit'}">	
					 <form:hidden path="checkAction" />
					  <form:hidden path="userId" />
					<td><input type="reset" class="button" value="CANCEL"></td>
                   <td><input type="button" onclick="saveform()"   class="button" value="UPDATE"></td>
					</c:if>	
					
					<c:if test="${addUser.checkAction != 'Edit'}">	
					 <form:hidden path="checkAction" />
					  <form:hidden path="userId" />
					<td><input type="reset" class="button" value="CANCEL"></td>
                   <td><input type="button" onclick="saveform()"   class="button" value="ADD"></td>
					</c:if>	
					
						
					</tr>
				</table>

			</form:form>



		</div>

	</div>


	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close" onclick="cloesBox()">&times;</span>
			
			
			
			<p style="color: #000000; font-size: 10px; text-align: center;">
				<span style="text-align: center; color: #000000;"> <img
					src="resources/img/successTick.png"></span>
			</p>
			<p id="para" align="center"></p>
			<button class="openFinalPopup">OK</button>
		</div>
	</div>
	<div class="error-div"></div>
	
	
	
	
	<script>
$(document).ready(function(){
    $('.openFinalPopup').on('click',function(){      
        
    	$("#contentHomeApp").load('km/userList');    	
       
    }); 
    
});
$(document).ready(function(){
    $('#role').on('change', function() {
      if (this.value == 'SA' || this.value == 'CC')     
      {
    	  $("#circleDiv").hide();        
      }
      else
      {
    	  $("#circleDiv").show();
      }
    });
    var role=$("#role").val();
        if (role == 'SA' || role == 'CC' || role == 'Select')     
        {
      	  $("#circleDiv").hide();        
        }
        else
        {
      	  $("#circleDiv").show();
        }
      
});

</script>	
</body>
</html>