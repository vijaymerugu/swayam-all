<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<title>manualTicket</title>
<html>
<head>
<style>
input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}



button:hover {
    opacity:1;
}
.signupbtn {
  float: center;
  width: 10%;
}
.container {
    padding: 16px;
}


 {
	box-sizing: border-box;
	
}

input[type=text], select, textarea {
  background-color: #F2F1EF;
  height: 70px;
  border-top:#F2F1EF;
  border-left:whtie;
  border-bottom-width: 4px #F2F1EF;
  width: 86%;
  border-bottom-width: 0px;
	
	}
input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: NONE;
	border-radius: 4px;
	cursor: pointer;
	float: center;
}

input[type=submit]:hover {
	background-color:#fced19;
}

.container {
	border-radius: 5px;
	background-color: #ffffff;
	width: 97%;
	height:470px;
}
</style>
<style>
/* The Modal (background) */
input[type=button]{

background-color:yellow;
  border-top:2px yellow;
  border-bottom-width: 4px yellow;
  width:40%;
   height: 25px;
   align:center

} input[type=submit], input[type=reset] {
  background-color: #F2F1EF;
  height: 50px;
  border-top:1px #F2F1EF;
  border-left:2px whtie;
  border-bottom-width: 4px #F2F1EF;
  width: 72%;
  align:center;
  border-bottom-width: 0px;}
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
  background-color:#fced19; /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid black;
  width: 30%;
  height: 30%;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {top:-300px; opacity:0} 
  to {top:0; opacity:1}
}

@keyframes animatetop {
  from {top:-300px; opacity:0}
  to {top:0; opacity:1}
}

/* The Close Button */
.close {
  color: #black;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
</style>
 
 
<style>
/* Modal Content */
.modal-content {
  background-color: #ebe6e6;
  margin: auto;
  padding: 40px;
  border: 1px solid black;
  width: 40%;
  height:40%;
}

/* The Close Button */
.close {
  color: #black;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
</style>
<style type="text/css">
input[type=button], input[type=submit], input[type=reset] {
  background-color: #f5e947;
  border: 4px ;
  color: black;
  padding: 6px 14px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
  }
</style>
<style>

/* Clear floats (clearfix hack) */
.btn-group:after {
  content: "";
  clear: both;
  display: table;
}

.btn-group button:not(:last-child) {
  border-right: none; /* Prevent double borders */
}
.btn-group button:hover {
  background-color: blue;
}
input[type=text]:hover {
  background-color:#fcfafa;
}

input[type=text], select {
  background-color: #f2f1ef;
  height: 22px;
  border-style:outset;
  width: 88%;
}
</style>

</head>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	debugger;
	
	$('#branchCode').blur(function(){
		debugger;
		 brCode=document.getElementById("branchCode").value;
		 if(brCode==""){
			 $("#branchCode1").html("Please Enter Branch Code");	 
		 }else{
		 document.getElementById("brCode").innerHTML=brCode;
			var respos;
		 console.log("inside bluer function...."+brCode);
	         	        $.ajax({
	        	type:"GET",
	        	url:"getByBranchCode/"+brCode,
	         success: function(data){
	        	 respos=data;
	           
	            $("#vendor").html('');
	        	  $.each(respos, function(index){
	             //      $("#kioskId").append('<option value='+respos[index].kioskId+'>'+respos[index].kioskId+'</option>');
	                  $("#vendor").append('<option value='+respos[index].vendor+'>'+respos[index].vendor+'</option>'); 
	                //  $("#vendor").val(respos[index].vendor);
	                //  $("#kioskId").val(respos[index].kioskId);
	                  $("#vendor1").html($("#vendor").val()); 
	                 // $("#kioskId1").html($("#kioskId").val());
	        	  });	        	 
	        	        	 
	         }
	        });
		 }
	    });
	
	 $('#kioskId').blur(function() {
		 debugger;
		 // $(this).val() will work here
		    var kioskId=$("#kioskId").val();
		    console.log("kioskID::"+kioskId);
   	        $.ajax({
	        	type:"GET",
	        	url:"getBykioskId/"+kioskId,
	         success: function(data){
	        	 respos=data;
	            $("#circle").html('');
	            
	            $("#contactPerson").html('');
	            $("#contactNo").html('');
	            $("#contactNo").html('');
	        	  $.each(respos, function(index){
	                   $("#circle").append('<option value='+respos[index].circle+'>'+respos[index].circle+'</option>'); 
	                  /* $("#contactPerson").append('<option value='+respos[index].contactPerson+'>'+respos[index].contactPerson+'</option>');
	                  $("#contactNo").append('<option value='+respos[index].contactNo+'>'+respos[index].contactNo+'</option>');
	                 */ $('#kioskError').append('<option value='+respos[index].kioskError+'>'+respos[index].kioskError+'</option>');
	                  //$("#vendor").html($("#vendor").val());
	                    
	                  $("#kioskId1").html($("#kioskId").val());
	                  $("#circle").html(respos[index].circle);
	                  $("#contactNo1").html(respos[index].contactNo);
	                  $("#contactPerson1").html(respos[index].contactPerson);
	                  
	                  $("#contactPerson").val(respos[index].contactPerson);  
	                  $("#contactNo").val(respos[index].contactNo);  
	                  $("#vish").val(respos[index].circle);
	              });	      
	        	  
	         }
	        });
		});
	  
	 $('#vendor').blur(function() {
		  debugger;
			$("#vendor1").html($("#vendor").val());
			var branchcode=$("#branchCode").val();
			var vendor=$("#vendor").val();
		
			$.ajax({
		        	type:"GET",
		        	url:"/getByVendor/"+vendor+"/"+branchcode,
		         success: function(data){
		        	 respos=data;
		            $("#kioskId").html('');
		           
		        	  $.each(respos, function(index){
		        		  $("#kioskId").append('<option value='+respos[index].kioskId+'>'+respos[index].kioskId+'</option>'); 
		        	  });
		            }
		         });
		});
	 
});

function fromValidation(){
    debugger;	
	var errorList=[];
	var branchcode=$("#branchCode").val();
	var comment=$("#comment").val();
	
	 if(branchcode==""){
		 errorList.push("Please enter branchcode");
	 }
	 if(comment==""){
		 errorList.push("Please enter comment");
	 }
	 return errorList;
}


function saveform(){
	debugger;
    var errorlist=fromValidation();
	
	 if(errorlist.length>0){
		 displayErrorsOnPage(errorlist);
	}else{
	// Get the modal
	var modal = document.getElementById("myModal");
	var span = document.getElementsByClassName("close")[0];
	//formData = "branchCode=MUM&vendor=CMS-&kioskId=KIOSKID_4&kioskError=Kiosk&comment=jfadfjafadf"
		debugger;
		var resp='';
	    circle=document.getElementById("circle1");
		console.log("circle :"+circle);
			var formData=$("#form").serialize();
			      
	        $.ajax({
	        	type:"POST",
	        	url:"manualTicketForm",
	        	data:formData,
	         success: function(data){
	        	 resp=data;       	 	        	 
	        	 $("#para").html("  Your complaint `INC"+resp+"` has been successfully registered" ); 
	        	modal.style.display = "block";
	        	 
	         }
	        });
}   
}

function displayErrorsOnPage(errorList) {
	//var errMsg = '<button type="button" class="close" onclick="closeOutErrBox()" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
	var errMsg = '<ul>';
	$.each(errorList, function(index) {
		var brachCode=$("#comment1").html(errorList[index]);
	});
	return false;
}

function cloesBox(){
	var modal = document.getElementById("myModal");
	 modal.style.display = "none";
}
</script>
<body background="color:white">
	<br/>
	<br/>
	<br/>
	<div class="submain">
		<form:form method="POST" action="manualTicketForm"
			modelAttribute="manualTicketCallLogDto" name="manualTicketCallLogDto"
			id="form">
			<h4 align="left">Please Complete the below form for longing
				complaint</h4>
			<br/>
			<div>
				<table cellspacing="25">
					<tr>
						<td><b style="color: purple">Branch Code <b><span
									style="color: red">*</span></b>:
						</b></td>
						<td><form:input path="branchCode" id="branchCode"
								required="required" name="branchCode" /></td>
					</tr>
					<tr>
						<td></td>
						<td><sapn id="branchCode1" style="color:red"></sapn></td>
					</tr>
					<tr></tr>
					<tr>
						<td><b style="color: purple">Vendor <b><span
									style="color: red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						</b></td>

						<td><form:select path="vendor" style="color:blue">
								<form:option value="Select"></form:option>
								<c:forEach var="listVal" items="${mlist}">
									<form:option value="${listVal.vendor}">${listVal.vendor}</form:option>
								</c:forEach>
							</form:select></td>

					</tr>
					<tr></tr>
					<tr>
						<td><b style="color: purple">Kiosk<b><span
									style="color: red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						</b></td>
						<td><form:select path="kioskId" id="kioskId"
								style="color:blue">
								<form:option value="Select"></form:option>
								<c:forEach var="listVal" items="${mlist}">
									<form:option value="${listVal.kioskId}">${listVal.kioskId}</form:option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr></tr>

					<tr>
						<td><b style="color: purple">Branch Code
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="brCode"></b></td>
						<td colspan="3"></td>
						<td><b style="color: purple">Vendor &nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="vendor1"></b></td>

						<td colspan="3"></td>
						<td><b style="color: purple">Kiosk
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="kioskId1"></b></td>
					</tr>
					<tr></tr>
					<tr>
						<td><b style="color: purple">Circle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="circle"></b></td>
						<form:hidden path="circle" id="vish" />
						<td colspan="3"></td>
						<td><b style="color: purple">Contact Person&nbsp;:</b> <form:hidden
								path="contactPerson" /></td>
						<td><b id="contactPerson1"></b></td>
						<td colspan="3"><b></b></td>
						<td><b style="color: purple">Contact Number&nbsp;:</b> <form:hidden
								path="contactNo" /></td>
						<td><b id="contactNo1"></b></td>
					</tr>
					<tr></tr>
					<%-- <tr>
					  <td><b style="color:purple">Status &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</</b></td>
					  <td><form:select path="status" id="status" style="color:blue">
					             <form:option  value="Select"></form:option>
								 <c:forEach var="listVal4" items="${statusList}">
									<form:option value=''>P</form:option>
								</c:forEach>
							</form:select> </td>
					</tr> --%>
					<tr>
						<td><b style="color: purple">Error on Kiosk Screen<b><span
									style="color: red">*</span></b>&nbsp;:
						</b></td>
						<td><form:select path="kioskError" id="kioskError"
								style="color:blue">
								<form:option value="Select"></form:option>
								<c:forEach var="listVal5" items="${errorList}">
									<form:option value='${listVal5.subCategory}'>${listVal5.subCategory}</form:option>
								</c:forEach>
							</form:select></td>
						<td></td>
						<td></td>
						<td><b style="color: purple">Comments<b><span
									style="color: red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
						</b></td>
						<td><form:textarea path="comment" row="10" column="10"
								required="required" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="comment1" style="color: red"></span></td>
					</tr>
				</table>
				<br> <br> <br>
				<table>
					<tr>
						<td></td>
						<td><input type="button" value="Submit" onclick="saveform()" /></td>
					</tr>
				</table>
			</div>
		</form:form>
		<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<span class="close" onclick="cloesBox()">&times;</span>
				<p id="para" align="center"></p>
				<p id="para" align="center"></p>
			</div>
		</div>

	</div>

	<div class="error-div"></div>

</body>