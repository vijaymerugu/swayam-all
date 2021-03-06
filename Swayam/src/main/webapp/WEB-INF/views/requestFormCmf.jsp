
<!DOCTYPE html>
<html lang="en">
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<!-- <script src="resources/js/users-app.js"></script> -->
<script src="resources/js/requests-cmf-app.js"></script>

<script src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css" />
<link rel="stylesheet" href="resources/css/body-page.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.css"
	type="text/css" />
	
	<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap-datepicker3.css" />
	
	
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

/* for main div */

.divm { style="transform: translateX(27%);
    position: absolute;
    background-color: #FFFFFF 0% 0% no-repeat padding-box;
    content: '';
    width: 1338px;
    height: 657px;
    left: -26%;
    top: 177px;
    background-color: #fefff4;
    opacity: 1;"
}
</style>

<script>
	$(document).ready(function() {
		var datePickerOptions = { changeYear: true, 
								  changeMonth: true,
								  autoclose: true,
								 // endDate : '-2d',
								  format : 'dd-mm-yyyy',
								  orientation : "top"
								  }
      	
  	    $( ".datepicker" ).datepicker(datePickerOptions);
	});
</script>


<script type="text/javascript">

$(document).ready(function(){
	//alert("branchCode==");
	var respos;
	var errorList=[];
	$('#branchCode').blur(function(){
		
		 var branchCode=$("#branchCode").val();
		// alert("kioskId retu "+kioskId);
		 document.getElementById("branchCode").innerHTML=branchCode;
		// document.getElementById("kioskId").innerHTML=branchCode;
		 console.log("inside bluer function branchCode...."+branchCode);
		 debugger;
	      $.ajax({
	        	type:"POST",
	        	url:"hm/getVendorByBranchCode/"+branchCode,
	            success: function(data){
	            	console.log("inside data");
	        	    respos=data;
	        	 console.log("response "+respos);

	        	 $("#vendor").html('');
		            $("#vendor").append('<option value=\'Select\'>Select</option>'); 
		        	  $.each(respos, function(index){
		        		 // alert(respos[index].branchName);
		             //      $("#kioskId").append('<option value='+respos[index].kioskId+'>'+respos[index].kioskId+'</option>');
		                  $("#vendor").append('<option value='+respos[index].vendor+'>'+respos[index].vendor+'</option>'); 
		                //  $("#vendor").val(respos[index].vendor);
		                  $("#vendor1").html($("#vendor").val()); 
		             // alert(respos[index].circle);
		              //alert("1111111111111"+respos[index].kioskId);
		                // $("#vish1").html($("#circle").val());
		                // $("#vish1").html(respos[index].circle);
		        	  });	  
	        	 
		        	  //Select country first
		        	 // $('select[name="vendor"]').on('change', function() {
		 
		        	//  });
	
	            }
	         	   });
		
	});

	 $('#vendor').change(function() {

  	      var vendor =$("#vendor").val();
	      var branchCode=$("#branchCode").val();
//alert("inside kiosk vendor--"+vendor);
//alert("inside kiosk branchcode--"+branchCode);
	      $.ajax({
	        	type:"GET",
	        	url:"getKioskIdByVendor/"+vendor+"/"+branchCode,		        	
	         success: function(data){
	        	 respos=data;
	        	 console.log("respos==",respos);
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
</script>


<!-- <script type="text/javascript">

$(document).ready(function(){
	alert("KioskId==");
	var respos='';
	var errorList=[];
	$('#kioskId').blur(function(){
		
		 var kioskId=$("#kioskId").val();
		// alert("kioskId retu "+kioskId);
		 document.getElementById("kioskId").innerHTML=kioskId;
		 if(kioskId!=null && kioskId!=""){
		 document.getElementById("kioskId").innerHTML=kioskId;
		 console.log("inside bluer function kioskId...."+kioskId);
	         	        $.ajax({
	        	type:"GET",
	        	url:"hm/checkDuplicateKiosk/"+kioskId,
	            success: function(data){
	            	console.log("inside data");
	        	    respos=data;
	        	 console.log("response "+respos);
	             $("#kioskId1").html(data);
	
	            }
	         	   });
		 }else{
			 $("#kioskId1").html("Please Enter  KioskId");
		 }
	});
});
</script> -->



<script type="text/javascript">



function fromValidation(){
	//alert("form validation call ");
    	
	var errorList=[];
	var branchCode=$("#branchCode").val();
	var kiosk=$("#kioskId").val();
	var vendor=$("#vendor").val();
    var typeOfRequest=$("#typeOfRequest").val();	
   // alert("typeOfRequest==="+typeOfRequest);
	var subject=$("#subject").val(); 
	var comments=$("#comments").val();


	var fromdate=$("#fromDate").html(""); 
	var todate=$("#toDate").html(""); 
	var fromdateValidation=$("#fromDate").val(); 
	var todateValidation=$("#toDate").val(); 
  //  alert("fromdate11"+fromdateValidation+"todateValidation"+todateValidation);
	if(branchCode==""){
		 errorList.push("Please enter branchCode");
	 }
	else{
		 if (!branchCode.match(/^[0-9]+$/)) 
		    {
			 errorList.push('Only numbers are allowed');
		        
		    }
	 }
	 if(kiosk=="Select"){
		 errorList.push("Please Select kiosk");
	 }
	 else{
		 if (!kiosk.match(/^[a-zA-Z0-9]+$/)) 
		    {
			 errorList.push('Only alphabets and numbers are allowed');
		        
		    }
	 }
	 if(vendor=="Select"){
		 errorList.push("Please select vendor");
		}
	 else{
		 if (!vendor.match(/^[a-zA-Z0-9 ]+$/)) 
		    {
			 errorList.push('Only alphabets and numbers are allowed');
		        
		    }
	 }

	
	 if(typeOfRequest==""){
		 errorList.push("Please select valid TypeOfRequest");
	 }
	 
	 if(subject==""){
		 errorList.push("Please enter subject ");		 
	 }
	 else{
		 if (!subject.match(/^[a-zA-Z0-9 ]+$/)) 
		    {
			 errorList.push('Only alphabets and numbers are allowed');
		        
		    }
	 }
	 
	 if(comments==""){
		 errorList.push("Please enter comments ");		 
	 }
	 else{
		 if (!comments.match(/^[a-zA-Z0-9., ]+$/)) 
		    {
			 errorList.push('Only alphabets and numbers are allowed');
		        
		    }
	 }


	 if(fromdate==""){
		 errorList.push("Please select  FromDate");
	 }

	 if(todate==""){
		 errorList.push("Please select  ToDate");
	 }


	/*  if(todateValidation>=fromdateValidation){
		  errorList.push("ToDate should be greater than FromDate!");
		 } */
	 
	 return errorList;
}

</script>

<script type="text/javascript">
	function cloesBox() {
		var modal = document.getElementById("myModal");
		modal.style.display = "none";
	}
	
	 var window;
		function openWin(){
		      window.location.href = "hm/requestFormGridCmf";
		};
	 	
 	
	
	function displayErrorsOnPage() {
		//alert("form displayErrorsOnPage call ");
		var errMsg='';
		$("#branchCodeId").html("");	
		$("#kioskId1").html("");	
		$("#vendorId").html("");	
		$("#typeOfRequestId").html("");		
		$("#subjectId").html("");
		$("#commentsId").html("");

		$("#fromDateId").html("");
		$("#toDateId").html("");
		var fromdateValidation=$("#fromDate").val(); 
		var todateValidation=$("#toDate").val(); 
		
			
			if($("#branchCode").val()==""){
				//alert("branchCodeId valida====");
				$("#branchCodeId").html("Please Enter Branch Code");	
			}
			else{
				 if (!$("#branchCode").val().match(/^[0-9]+$/)) 
				    {
					 $("#branchCodeId").html('Only numbers are allowed');
				        
				    }
			 }
			if($("#kioskId").val()=="Select"){
				$("#kioskId1").html("Please Select kiosk Id");	
			}
			else{
				 if (!$("#kioskId").val().match(/^[a-zA-Z0-9]+$/)) 
				    {
					 $("#kioskId1").html('Only alphabets and numbers are allowed');
				        
				    }
			 }
			if($("#vendor").val()=="Select"){
				$("#vendorId").html("Please Select Vendor");		
			}
			else{
				 if (!$("#vendor").val().match(/^[a-zA-Z0-9 ]+$/)) 
				    {
					 $("#vendorId").html('Only alphabets and numbers are allowed');
				        
				    }
			 }
			
			if($("#typeOfRequest").val()=="Select"){
				//alert("typeOfRequestId valida===0=");
				$("#typeOfRequestId").html("Please Select Type Of Request");	
			}
			
			
		
			
			if($("#subject").val()==""){
				$("#subjectId").html("Please Enter Subject");
			}
			else{
				 if (!$("#subject").val().match(/^[a-zA-Z0-9 ]+$/)) 
				    {
					 $("#subjectId").html('Only alphabets and numbers are allowed');
				        
				    }
			 }
			
			if($("#comments").val()==""){
				$("#commentsId").html("Please Enter comments");
			}
			else{
				 if (!$("#comments").val().match(/^[a-zA-Z0-9., ]+$/)) 
				    {
					 $("#commentsId").html('Only alphabets and numbers are allowed');
				        
				    }
			 }

			if($("#fromDate").val()==""){
				$("#fromDateId").html("Please Select FromDate");
			}


			


			if($("#toDate").val()==""){
				$("#toDateId").html("Please Select ToDate");
			}
			/* else{
		
			if(todateValidation>=fromdateValidation){
				$("#toDateValiId").html("ToDate shuold be greater than FromDate");
			}
			
			} */

	}
	
	
	
	function saveform() {
		//alert("123");
		
		  var errorlist=fromValidation();
		 //alert(errorlist);
		 
		 if(errorlist.length>0){
			// alert("124");
			 displayErrorsOnPage();
			 
		 }else{
			// alert("else");
				$("#branchCodeId").html("");	
				$("#kioskId1").html("");	
				$("#vendorId").html("");	
				$("#typeOfRequestId").html("");		
				$("#subjectId").html("");
				$("#commentsId").html(""); 

				$("#fromDateId").html(""); 

				$("#toDateId").html(""); 
				
		
		var modal = document.getElementById("myModal");
		var span = document.getElementsByClassName("close")[0];

		var resp = "";
		
		var formData = $("#form").serialize();
		  console.log("formData::::"+formData);
			 $.ajax({
	        	type:"POST",
	        	url:"hm/addRequest",
	        	data:formData,
	        	headers: 
                {
                    'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
                },
	         success: function(data){
	        	 resp=data;   
	        	 console.log("resp====="+resp)
	        	// $("#para").html("Request: "+resp+ " has been successfully created");
	        	 $('#para').html(resp);
	     		 modal.style.display = "block"; 
	         },
	         error : function(e) {
                 alert('Error: ' + e);
              }

                
                
	        });
		
		
	}
	}
		 
	
	
	
	
	
</script>
</head>
<body >
	<div class="submainForm" ng-app="app" id="appId">
	<h1 align="left" style="font-size: 17px;color: #000000;font-weight: bold;">New Request</h1>
		<br></br>
		<br></br>
		<div style="top: 294px;
left: 201px;
width: 964px;
height: 213px;
opacity: 1;">

<div class="col-md-12">
			<form:form action="" modelAttribute="requestDto"   name="requestDto" id="form" autocomplete="off">
				 
				
				<table align="center">
					<tr>
						<td><b style="color: purple">Branch Code:</b><b><span
								style="color: red">*</span></b></td>
								
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;" >
						<form:input path="branchCode" maxlength="5" /></td>
						<td></td>
						<td></td>
						
							<td><b style="color:purple">Vendor &nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
								<b><span style="color: red">*</span></b></td>
						
					
						<td><form:select path="vendor" style="color:blue">
                             <form:option value="Select" label="Select"></form:option>
                            <%--  <c:forEach var="listVal" items="${mlist}">
							<form:option value="${listVal.vendor}" >${listVal.vendor}</form:option> 
							</c:forEach> --%>
						</form:select></td>
						
						
						
						
						
					</tr>
					
					<tr>
						<td></td>
						<td><span id="branchCodeId" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="vendorId" style="color: red"></span></td>
						
					</tr>
					
					
					<tr>
						<%-- <td><b style="color: purple">Vendor:</b><b><span
								style="color: red">*</span></b><b></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><form:input path="vendor" maxlength="15" id="vendor"/></td>
						
						 --%>
						
						
					
						
							<td><b style="color: purple">Kiosk Id:</b><b><span
								style="color: red">*</span></b></td>
						<%-- <td><form:input path="kioskId" maxlength="50" /> --%>
						
						<td><form:select path="kioskId" style="color:blue">
                             <form:option value="Select" label="Select"></form:option>
                            <%--  <c:forEach var="listVal" items="${mlist}">
							<form:option value="${listVal.vendor}" >${listVal.vendor}</form:option> 
							</c:forEach> --%>
						</form:select></td>
						
						
					<td></td>
						<td></td>
						<td><b style="color: purple">Type Of Request:</b><b><span
								style="color: red">*</span></b></td>
						<td style="top: 352px; width: 190px; height: 75px;opacity: 1;">
						<%-- <form:select path="typeOfRequest" id="typeOfRequest" style="color:blue">
								    <form:option value="Select" label="Select"/>                                  
                                    <form:option value="Activation">Activation</form:option>
                                    <form:option value="Deactivation">Deactivation</form:option>   
							</form:select></td> --%>
							
							<form:input path="typeOfRequest" id="typeOfRequest" style="color:blue" 
							value="Deactivation" readonly="true"/>
							
					</tr>
					
					<tr>
					
						<td></td>
						<td><span id="kioskId1" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="typeOfRequestId" style="color: red"></span></td>
					</tr>


						
							<%-- <td><b style="color: purple">Vendor:</b><b><span
								style="color: red">*</span></b><b></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td><form:input path="vendor" maxlength="15" id="vendor"/></td>
						
						 --%>
						 
						 
<tr>
							<td><b style="color: purple">From Date:</b><b><span
									style="color: red">*</span></b></td>
							<td><form:input id="fromDate" path="fromDate" type="text" class="datepicker" style="color:blue" readonly="true" />
							
							
							</td>
							<td></td>
							<td></td>
							<td><b style="color: purple">To Date:</b><b><span style="color: red">*</span></b></td> 
							<td style="top: 352px; width: 190px; height: 75px; opacity: 1;">
								<form:input id="toDate" path="toDate" type="text" style="color:blue" class="datepicker" readonly="true" />
							</td>



						</tr>

						<tr>
					
						<td></td>
						<td><span id="fromDateId" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="toDateId" style="color: red"></span></td>
						<!-- <td><span id="toDateValiId" style="color: red"></span></td> -->
					</tr>	
					
					
					
					
					
					<tr>
						<td><b style="color: purple">Subject:</b><b><span
								style="color: red">*</span></b></td>
						<td><form:input path="subject" maxlength="100" /></td>
						<td></td>
						<td></td>
						<td><b style="color: purple">Comments:</b><b><span
								style="color: red">*</span></b></td>
						<td style="top: 411px;
left: 969px;
width: 196px;
height: 96px;
opacity: 1;"><form:textarea path="comments" maxlength="200" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td></td>
						<td><span id="subjectId" style="color: red"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td><span id="commentsId" style="color: red"></span></td>
					</tr>
				</table>
				<br>
				<br>
				<br>
					
				
				
				<table align="center">
					<tr>
					<td><input type="reset" class="button" value="CANCEL"></td>
                   <td><input type="button" onclick="saveform();" id="button" class="button" value="ADD"></td>
						
					</tr>
				</table>

			</form:form>

		</div>
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
			<p align="center">
			<span style="text-align: center;">
			<button  class="openFinalPopup" style="text-align: center;">OK</button> 
			</span>
			</p>
		</div>
	</div>
	<div class="error-div"></div>
	
		

<!-- <script>
function loadImage() {
  alert("Image is loaded");
  $("#contentHomeApp").load('/hm/requestFormGridCmf');  
}
</script> -->
	
	<script>
$(document).ready(function(){
    $('.openFinalPopup').on('click',function(){      
        
    	$("#contentHomeApp").load('hm/requestFormGridCmf');    	
       
    }); 
    
});

</script>	
</body>
<input type="hidden" name="_csrf" value="<%=session.getAttribute("csrfToken")%>"> 
</html>