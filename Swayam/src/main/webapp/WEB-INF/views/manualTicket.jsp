<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="sbi.kiosk.swayam.common.dto.UserDto" %>

<title>manualTicket</title>
<html>
<head>
<style>
input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}
 .tb-bk table tr td .row .lb span b {
            color:red;
            }
            select:focus,input:focus,button:focus,textarea:focus {
                outline: none;
            }

hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}
button {
    background-color: #4CAF50;
    color: white;
    padding: 15px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 10%;
    opacity: 0.9;
}
.buttonManual {
    background-color: #FDD209;    
    border-bottom-width: 4px #FDD209;
    top: 670px;
    left: 579px;
    width: 97px;
    right: 579px;
    height: 32px;
    opacity: 1;
}

.okButton{
background-color: #FFFFFF;
    /* border: 95px; */
    color: black;
    padding: 1px 10px;
    text-decoration: none;
    margin: -5px 23px;
    cursor: pointer;
    width: 30%;
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

label {
	padding: 12px 12px 12px 0;
	display: inline-block;
}



input[type=submit]:hover {
	background-color:#fced19;
}

.container {
	/* border-radius: 5px;
	wid
	background-color: #ffffff;
	padding: 20px;
 */
 
 
    background-color: #ffffff;
    padding: 79px;
    margin: 75px;
    top: 1203px;
    height: 603px;
    width: 1259px;
    left: 1114px;
    right: 120;


 
 
 }

.col-25 {
	float: left;
	width: 25%;
	margin-top: 6px;
}

.col-75 {
	float: left;
	width: 75%;
	margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
	content: "";
	display: table;
	clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
	.col-25, .col-75, input[type=submit] {
		width: 100%;
		margin-top: 0;
	}
}
</style>
<style>
/* The Modal (background) */

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
  border: 1px solid #black;
  width: 40%;
  height: 40%;
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
  color: black;
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
body {font-family: Arial, Helvetica, sans-serif;}

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
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

.modal-content {
  background-color: #ebe6e6;
  margin: auto;
  padding: 93px;
  border: 1px solid black;
  width: 29%;
  height:51%;
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

.close:hover,
.close:focus {
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
    padding: 1px 10px;
    text-decoration: none;    
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
    left: 579px;
    width: 97px;
    height: 32px;
    opacity: 1;
}

.reset {
    background-color: #FDD209;
    border-top: 2px #FDD209;
    border-bottom-width: 4px #FDD209;    
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
  width: 55%;
}
</style>

</head>

<!-- <script src="https://code.jquery.com/jquery-3.4.1.js"></script>  -->

<script src="resources/js/jquery-3.4.1.js">

<script>
$(document).ready(function(){
    $('.openFinalPopup').on('click',function(){      
        
    	$("#contentHomeApp").load('hm/manualTicket');    	
       
    }); 
    
});

</script>	

<script type="text/javascript">
$(document).ready(function(){
		
	$('#branchCode').blur(function(){
		
		 brCode=document.getElementById("branchCode").value;
		 if(brCode==""){
			 $("#branchCode12").html("Please Enter Branch Code");	 
		 }else{
			 $("#branchCode12").html('');
			 $("#vendor12").html('');
			 // commented14012021ss
		// document.getElementById("brCode").innerHTML=brCode;
			var respos;
		 console.log("inside bluer function...."+brCode);		 
	         	        $.ajax({
	        	type:"GET",
	        	url:"getByBranchCode/"+brCode,
	         success: function(data){
	        	 respos=data;
	        	 //added
	        	  	
	        	  $("#circle").html('');  
	        	 // alert($("#circle").html(''));
	        	  //end
	            $("#vendor").html('');
	            $("#vendor").append('<option value=\'Select\'>Select</option>'); 
	        	  $.each(respos, function(index){
	        		 // alert(respos[index].branchName);
	             //      $("#kioskId").append('<option value='+respos[index].kioskId+'>'+respos[index].kioskId+'</option>');
	                  $("#vendor").append('<option value='+respos[index].vendor+'>'+respos[index].vendor+'</option>'); 
	                //  $("#vendor").val(respos[index].vendor);
	                 $("#branchName1").val(respos[index].branchName);
	                 toAppendVen=+ '<span id="'+respos[index].branchName+'"/>'
	                  $("#vendor1").html($("#vendor").val()); 
	                 $("#branchName2").html($("#branchName1").val());
	             // alert(respos[index].circle);
	              //alert("1111111111111"+respos[index].kioskId);
	                // $("#vish1").html($("#circle").val());
	                // $("#vish1").html(respos[index].circle);
	                 $("#circleNew").html(respos[index].circle);
	                 $("#circle").val(respos[index].circle);
	                 $("#kioskIdDisp").html(respos[index].kioskId); 
	        	  });	  
	         }
	        });
		 }
	    });
	
	 $('#kioskId').blur(function() {
		 
		 // $(this).val() will work here
		    var respos="";
		    var kioskId=$("#kioskId").val();
		   // alert("kioskId==="+kioskId);
		    console.log("kioskID::"+kioskId);
		    
		    kioskId3456=document.getElementById("kioskId").value;
		    $("#kioskId56").html($("#kioskId").val());
		   // alert("kioskId3456=="+kioskId56);
         $.ajax({
	        	type:"GET",
	        	url:"getBykioskId/"+kioskId,
	         success: function(data){
	        	 respos=data;
	        	// alert("111",respos);
	        	 console.log("log::resp::"+respos);
	            $("#circle").html('');    
	           // $("#contactPerson").html('');
	           // $("#contactNo").html('');
	          //  $("#contactNo").html('');
	          //  $("#kioskId").html('');
	           // $respos = respos['kioskId'];
	           // alert("respos:::"+kioskId3456);
	          //  alert("11111111111"+$("#kioskId").html("#kioskId"));
	          //  alert("kiosk===="+ $("#kioskIdDisp").html($("#kioskId").val()));
	           // if(respos!=''){
	            	// alert("loop1");
	            //	console.log("inside bluer function....sss-----"+$("#kioskIdDisp").html("#kioskId").val());
	            	//console.log("inside bluer function....mmm-----"+$("#kioskIdDisp").html($("#kioskId").val());
	        	  $.each(respos, function(index){
		        	//  alert("loop");
	                   $("#circle").append('<option value='+respos[index].circle+'>'+respos[index].circle+'</option>'); 
	                  /* $("#contactPerson").append('<option value='+respos[index].contactPerson+'>'+respos[index].contactPerson+'</option>');
	                  $("#contactNo").append('<option value='+respos[index].contactNo+'>'+respos[index].contactNo+'</option>');
	                 */ 
	                 
	                 /*  if(respos[index].kioskError === "" || respos[index].kioskError === null || respos[index].kioskError === undefined) {
		                	// alert("111"+respos[index]);
		                     delete respos[index];
		                 }
		               console.log("inside kioskError-----"+respos[index].kioskError);
	                 $('#kioskError').append('<option value='+respos[index].kioskError+'>'+respos[index].kioskError+'</option>');
	                */   //$("#vendor").html($("#vendor").val());
	                   
	                    
	                   $("#kioskId1").html($("#kioskId").val());
	                   // added new for kiosk to display
	                    
	                   $("#kioskIdDisp").html(respos[index].kioskId); 
	                  // console.log("inside bluer function....kioskId1---kkk-------"+respos[index].kioskId);
	                  
	                  $("#circle").html(respos[index].circle);
	                  $("#contactNo1").html(respos[index].contactNo);
	                  $("#contactPerson1").html(respos[index].contactPerson);
	                  $("#mailId1").html(respos[index].mailId);
	                  $("#contactPerson").val(respos[index].contactPerson);  
	                  $("#contactNo").val(respos[index].contactNo); 
	                  $("#mailId").val(respos[index].mailId);
	                  // comment by satendra 17-06-2021
	                 /*  if(respos[index].kioskError === "" || respos[index].kioskError === null || respos[index].kioskError === undefined) {
		                 alert("111"+respos[index].kioskError);
		                     delete respos[index];
		                 } */
		            //   console.log("inside kioskError-----"+respos[index].kioskError);
	                 $('#kioskError').append('<option value='+respos[index].kioskError+'>'+respos[index].kioskError+'</option>');
	                      
	                  	
	                  
	                 //alert("inside mailId----------", $("#mailId1").html(respos[index].mailId));
	               //console.log("inside contaNo----------"+respos[index].mailId);
	                  //comments by sate
	                 // $("#vish").val(respos[index].circle);
	              });
	            //}	else{
	            	//alert("The Kiosk is not mapped with any user");
	            	// $("#kioskIdMappingUser").html("The Kiosk is not mapped with any user");
		           // }      
	        	  
	         }
	        });
		});
	  
	 $('#vendor').change(function() {
		  
			$("#vendor1").html($("#vendor").val());
			var branchcode=$("#branchCode").val();
			var vendor=$("#vendor").val();
			//var kioskId33=$("#kioskId").val();
			//alert("vendor---changes------------"+$("#kioskId").val());
		    var respos="";
		    $("#kioskId12").html('');
			$.ajax({
		        	type:"GET",
		        	url:"getByVendor/"+vendor+"/"+branchcode,		        	
		         success: function(data){
		        	 respos=data;
		            $("#kioskId").html('');
		            $("#kioskId").append('<option value=\'Select\'>Select</option>'); 
		        	  $.each(respos, function(index){
		        		  $("#kioskId").append('<option value='+respos[index].kioskId+'>'+respos[index].kioskId+'</option>'); 
		        		 // $("#kioskIdDisp").html(respos[index].kioskId); 
		        		 // alert(respos[index].kioskId);
		                 // console.log("inside vendor change function....kioskId----------"+ $("#kioskId33").html($("#kioskId").val()));
		        	  });
		            }
		         });
		});
	 
});


function fromValidation(){
    	
	var errorList=[];
	var branchcode=$("#branchCode").val();
	var vendor=$("#vendor").val();
	var kioskId=$("#kioskId").val();
	var kioskError=$("#kioskError").val();
	var comment=$("#comment").val();
	var contactPerson=$("#contactPerson").val();
	var contactNo=$("#contactNo").val();
	var mailId=$("#mailId").val();
	
	

	
	 if(branchcode==""){
		 errorList.push("Please enter branchcode");
	 }
	 if(vendor=="Select"){
		 errorList.push("Please Select vendor");
	 }
	 if(kioskId=="Select"){
		 errorList.push("Please Select kiosk Id");
	 }
	 if(kioskError=="Select"){
		 errorList.push("Please Select kioskError");
	 }if(contactPerson==""){
		 errorList.push("Please enter contactPerson");
	 }
	 if(contactNo==""){
		 errorList.push("Please enter contactNo");
	 }
	 if(mailId==""){
		 errorList.push("Please enter mailId");
	 }
	 if(comment==""){
		 errorList.push("Please enter comment");
	 }
	 else{
		 if (!comment.match(/^[a-zA-Z0-9., ]+$/)) 
		    {
			 errorList.push('Only alphabets and numbers are allowed');
		        
		    }
	 }
	 return errorList;
}


function saveform(){
	
	$("#branchCode12").html("");
	$("#vendor12").html("");
	$("#kioskId12").html("");
	$("#kioskError12").html("");
	$("#comment12").html("");
	$("#contactPerson12").html("");
	$("#contactNo12").html("");
	$("#mailId12").html("");
	
    var errorlist=fromValidation();
   
	
	 if(errorlist.length>0){
		 displayErrorsOnPage();
	}else{
		$("#branchCode12").html("");
		$("#vendor12").html("");
		$("#kioskId12").html("");
		$("#kioskError12").html("");
		$("#comment12").html("");
		$("#contactPerson12").html("");
		$("#contactNo12").html("");
		$("#mailId12").html("");
	var modal = document.getElementById("myModal");
	var span = document.getElementsByClassName("close")[0];
	//formData = "branchCode=MUM&vendor=CMS-&kioskId=KIOSKID_4&kioskError=Kiosk&comment=jfadfjafadf"
		
		var resp='';
	    circle=document.getElementById("circle1");
		console.log("circle :"+circle);
		
			var formData=$("#form").serialize();
			      
	          $.ajax({
	        	type:"POST",
	        	url:"manualTicketForm",
	        	data:formData,
	        	headers: 
                {
	        		 
                    'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
                },
	         success: function(data){
	        	 resp=data;       	 	        	 
	        	 $("#para").html(resp); 
	 	        	modal.style.display = "block";
	        	 
	         }
	        }); 
	        
	
}   
}

function displayErrorsOnPage() {
	$("#branchCode12").html("");
	$("#vendor12").html("");
	$("#kioskId12").html("");
	$("#kioskError12").html("");
	$("#comment12").html("");
	$("#contactPerson12").html("");
	$("#contactNo12").html("");
	$("#mailId12").html("");

	var branchcode=$("#branchCode").val();
	var vendor=$("#vendor").val();
	var kioskId=$("#kioskId").val();
	var kioskError=$("#kioskError").val();
	var comment=$("#comment").val();
	var contactPerson=$("#contactPerson").val();
	var contactNo=$("#contactNo").val();
	var mailId=$("#mailId").val();
	
	$("#branchCode12").html("");
	$("#vendor12").html("");
	$("#kioskId12").html("");
	$("#kioskError12").html("");
	$("#comment12").html("");
	$("#contactPerson12").html("");
	$("#contactNo12").html("");
	$("#mailId12").html("");
	

	
	 if(branchcode==""){
		 $("#branchCode12").html("Please Enter Branch Code");
	 }
	 if(vendor=="Select"){
		 $("#vendor12").html("Please enter vendor");
	 }
	 if(kioskId=="Select"){
		 $("#kioskId12").html("Please enter kiosk Id");
	 }
	 if(kioskError=="Select"){
		 $("#kioskError12").html("Please enter kioskError");
	 }
	/*  if(!contactNo.match(/^[0-9]+$/)){
		 $("#contactPerson12").html("Please enter contact person");
	 } */

	 if(contactPerson==""){
		$("#contactPerson12").html("Please enter contactPerson");
	 }
	 
	 if(!contactNo.match('[0-9]{10}') ){
		 $("#contactNo12").html("Please enter contactNo.");
	 }
	 if(mailId==""){
		 $("#mailId12").html("Please enter mailId.");
		}
			 
	
	 if(comment==""){
		 $("#comment12").html("Please enter comment");
	 }
	  else{
		 if (!comment.match(/^[a-zA-Z0-9., ]+$/)) 
		    {
			 $("#comment12").html('Only alphabets and numbers are allowed');
		        
		    }
	 }
}


function cloesBox(){
	var modal = document.getElementById("myModal");
	 modal.style.display = "none";
	 
	 $("#contentHomeApp").load('hm/manualTicket');
	
	
	/*  $('#form')[0].reset();
	 $("#brCode").html("");
	 $("#vendor1").html("");
	 $("#kioskId1").html("");
	 $("#circle").html("");
	 $("#contactPerson1").html("");
	 $("#contactNo1").html("");
	 $("#branchName2").html(""); */
}
</script>
<body background="color:white">

	<div  class="submainForm">
	
	<%
			UserDto userObj = (UserDto) session.getAttribute("userObj");
			String firstName = "";
			String lastName="";
			String pfId="";
			if(userObj.getFirstName() !=null){
				firstName = userObj.getFirstName();
			}
			if(userObj.getLastName() !=null){
				lastName = userObj.getLastName();
			}
			if(userObj.getPfId() !=null){
				pfId = userObj.getPfId();
			}
			
		%>
	
		<form:form method="POST" action="manualTicketForm"
			modelAttribute="manualTicketCallLogDto" name="manualTicketCallLogDto"
			id="form" autocomplete="off">
			<h3 style="color: #000000;font-size:12 px; text-align: left;">Please complete the below form for lodging complaint</h3>
			 <!--  <span id="mailId1" style="color: red;"></span>  --> 
			   <!--  <span id="mailId1" style="color: red;"></span>  --> 
			<div class="col-md-14">
				<table>
					<tr>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color:purple">Branch Code <b><span style="color:red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
								</td>
						<td><form:input path="branchCode"  id="branchCode" required="required" maxlength="5"
							 name="branchCode"  /></td>
							 <td colspan="2"><b></b>
							
							<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color:purple">Branch Name&nbsp;&nbsp;&nbsp;:</b>
								</td><td id="branchName1"><sapn id="branchName2" style="color:black;"></sapn></td>
							<!-- <td><b id="brCode"></b></td> -->
							 
							
					</tr>
						 <tr>
								<td></td><td>
								 <sapn id="branchCode12" style="color:red"></sapn>
								</td>
								</tr>  
					<tr>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color:purple">Vendor <b><span style="color:red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
								
								</td>
						
						<td><form:select path="vendor" style="color:blue">
                             <form:option value="Select" label="Select"></form:option>
                             <c:forEach var="listVal" items="${mlist}">
							<form:option value="${listVal.vendor}" >${listVal.vendor}</form:option>
							</c:forEach>
						</form:select></td>
						
						<td colspan="2"></td>
						<td><b style="color:purple">Vendor &nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="vendor1"></b></td>
						
					</tr>
				      <tr>
								<td></td><td>
								 <sapn id="vendor12" style="color:red"></sapn>
								</td>
								</tr>
					<tr>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;" ><b style="color:purple">Kiosk<b><span style="color:red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
					</td>
					<td><form:select path="kioskId" id="kioskId" style="color:blue">
							  <form:option value="Select" label="Select"></form:option>
							  <c:forEach var="listVal" items="${mlist}">
							<form:option value="${listVal.kioskId}" >${listVal.kioskId}</form:option>
							</c:forEach>				
						</form:select></td>
						
						<td colspan="2"></td>
						<td><b style="color:purple">Kiosk&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
						</td>
								<td><b id="kioskId56"></b></td>
						
					</tr>
					
                 <tr>
								<td></td>
								<td>
								 <sapn id="kioskId12" style="color:red"></sapn>
								</td>
						</tr>
					<tr>
					<!-- 	<td style="top: 352px; width: 190px; height: 75px;opacity: 1;"><b style="color:purple">Branch Code	&nbsp;&nbsp;
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="brCode"></b></td> -->
						<!-- <td colspan="2"></td>
						<td><b style="color:purple">Vendor &nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="vendor1"></b></td>
                         <span id="kioskId3456">&nbsp;</span>
						
						<td colspan="2" id="kioskId56"></td>
						<td colspan="2"></td>
						<td><b style="color:purple">Kiosk&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
								<td><b id="kioskId56"></b></td> -->
						<!-- <td><b id="kioskId1"></b></td> -->
					</tr>
					
            <tr>
            <td style="height: 75px;opacity: 1;"><b style="color:purple">Circle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="circleNew"></b></td>
						<form:hidden path="circle" id="circle"/>
						
						<td colspan="2"></td>
						<td><b style="color:purple">Contact Person&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red">*</span> :</b>
							<td><form:input path="contactPerson"  id="contactPerson"  name="contactPerson" 
							style=" background-color: #F2F1EF;   border-top:#F2F1EF;  border-left:whtie;
                              border-bottom-width: 4px #F2F1EF;  width: 96%;  border-bottom-width: 0px;" maxlength="50" required="true" /></td>
						    <td><form:hidden path="contactPerson" id="contactPerson" /></td>
							
						 <td colspan="1"></td> 
						<td><b style="color:purple">Contact No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">*</span> : </b>
						<td><form:input path="contactNo"  id="contactNo"  name="contactNo" style=" background-color: #F2F1EF;    border-top:#F2F1EF;  border-left:whtie;
                              border-bottom-width: 4px #F2F1EF;  width: 96%;  border-bottom-width: 0px;" minlength="10" maxlength="10" required="true" />
                              </td>
					   <td><form:hidden path="contactNo" id="contactNo"/></td>
					   
					   
					   
					<%-- <td style="height: 75px;opacity: 1;"><b style="color:purple">Contact No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b></td>
						<td><b id="contactNo"></b></td>
						   <td><form:hidden path="contactNo" id="contactNo"/></td> --%>
				
					   
					</tr>
					<tr> <td></td><td></td><td><td></td><td><span id="contactPerson12" style="color:red;"/></span></td><td></td><td></td><td></td><td><span id="contactNo12" style="color:red"></span></td> </tr>
					
					<!-- <tr>
					
					<td></td><td><span id="contactPerson12" style="color:red"></td><td></td><td></td><td></td><td><span id="contactPerson12" style="color:red"></span> </td>
					<td></td><td><span id="contactNo12" style="color:red"></td><td></td><td><span id="contactNo12" style="color:red"></span> </td>
					
					</tr>  -->
					

					<br/>
					<tr>
						<td><b style="color:purple">Error on Kiosk Screen <b><span style="color:red">*</span></b>&nbsp;&nbsp; : </b>
						
						</td>
						<td><form:select path="kioskError" id="kioskError"  style="color:blue">
						          <form:option  value="Select"></form:option>
								 <c:forEach var="listVal5" items="${errorList}">
									<form:option value='${listVal5.subCategory}'>${listVal5.subCategory}</form:option>
								</c:forEach>
							</form:select></td>
						<td></td><td></td>
						<td><b style="color:purple">Comments <b><span style="color:red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : </b>
								
								</td>
						<td style="vertical-align: bottom;"><form:textarea path="comment" row="8" column="8"  required="required"  maxlength="200" /></td>
					
					  <td colspan="2"></td>
					    <td><b style="color:purple">Mail Id <b><span style="color:red">*</span></b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : </b>
					   <td><form:input path="mailId"  id="mailId" required="required"  name="mailId" maxlength="50" style=" background-color: #F2F1EF;    border-top:#F2F1EF;  border-left:whtie;
                              border-bottom-width: 4px #F2F1EF;  width: 96%;  border-bottom-width: 0px;"/></td>
					   <td><form:hidden path="mailId" id="mailId"/></td>
					   
					</tr>
					
					<!-- <tr>
					<td></td>
					<td align="center"><span id="mailId12" style="color:red"></td>
					</tr> -->
					
					<tr>
					  <td></td><td><span id="kioskError12" style="color:red"></span></td><td></td><td></td><td></td><td><span id="comment12" style="color:red"></span> </td><td></td><td></td><td></td><td><span id="mailId12" style="color:red"></span></td>
					</tr>
					
					<tr>
					 
					</tr>
					
					<tr><td colspan="5" ></td>
						<td> <input type="button" 
							value="Submit" onclick="saveform()" class="buttonManual" /></td>
							<td><input type="button" class="openFinalPopup" onclick="cancelform()" value="Reset" ></td>
					</tr>
				</table>
			</div>
		</form:form>
		<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close" onclick="cloesBox()">&times;</span>
   <!--  <p align="center"><img src="file:///C:/Users/Admin/git/swayam-all/Swayam/src/main/webapp/resources/img/successTick.png" /></p>
    --> 
  <!--   <span style="text-align: center;color:#black">
 <img src="resources/img/successTick.png"></span> -->
    
    <p style="color:#000000;font-size:10px;text-align: center;">
<span style="text-align: center;color:#000000;">
  <img src="resources/img/successTick.png"></span></p>
    
    <p id="para" align="center"></p>
    <p align="center">			
		<button class="okButton" onclick="cloesBox()">OK</button> 
	</p>
  </div>
</div>
		
	</div>
	
	<div class="error-div">
	
	</div>
	
</body>
 <script>
 function cancelform() {
		
		debugger;
		$("#kioskError").html("");
		$("#contactNo").html("");	
		$("#contactPerson").html("");	
		$("#circle").html("");	
		$("#kioskId56").html("");		
		$("#vendor1").html("");
		$("#brCode").html("");
		$("#kioskId").html("");
		$("#vendor").html("");
		$("#branchName1").html("");
		$("#branchCode").html("");
		
		
		
 	$("#contentHomeApp").load("hm/manualTicket");  
		
	}

</script>


<script> 
$(document).ready(function(){
	//debugger;
	
	$('#branchCode').blur(function(){
		//debugger;
		
		var branchCode=$("#branchCode").val();
		 document.getElementById("branchCode").innerHTML=branchCode;
		 
		 if (!branchCode.match(/^[0-9]+$/)) 
		    {
			 $("#branchCodeNumberVal").html('Only numbers are allowed');
		        
		    }else{
		    	
		    }
	});
	
	
});
</script>

<input type="hidden" name="_csrf" value="<%=session.getAttribute("csrfToken")%>"> 

</html>







