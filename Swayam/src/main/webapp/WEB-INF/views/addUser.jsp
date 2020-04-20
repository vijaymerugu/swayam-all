<%@ page import="sbi.kiosk.swayam.common.dto.UserDto" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>addUser</title>
 
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
 <link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css"> 


<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>  
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	-->
<link href="/resources/css/menu.css" rel="stylesheet" type="text/css">	
<!-- <link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css"/>
 -->
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

 
<style type="text/css">
input[type=button], input[type=submit], input[type=reset] {
	background-color: #f5e947;
	border-bottom-style: #f5e947;
	color: black;
	padding: 6px 12px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<style>
.btn-group button {
	background-color: white; /* Green background */
	border: 3px solid while; /* Green border */
	color: black; /* White text */
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

.btn-group button:not (:last-child ) {
	border-right: none; /* Prevent double borders */
}

.btn-group button:hover {
	background-color: blue;
}

input[type=text]:hover {
	background-color: #F2F1EF;
	border-bottom: 4px solid #F2F1EF;
}


.mytable input[type=text], select {
  background-color: #f2f1ef;
  height: 22px;
  border-style:outset;
  width: 80%;
}
</style>
<style>
/* Modal Content */
.modal-content {
  background-color: #white;
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
</style>
<script type="text/javascript">
function cloesBox(){
	var modal = document.getElementById("myModal");
	 modal.style.display = "none";
}
</script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
function fromValidation(){
	
    debugger;	
	var errorList=[];
	var pfId=$("#pfId").val();
	var userName=$("#userName").val();
	var phoneNumber=$("#phoneNumber").val();
    var emailId=$("#emailId").val();	
	var reportingAuthorityName=$("#reportingAuthorityName").val(); 
	var reportingAuthorityEmail=$("#reportingAuthorityEmail").val();
	var role=$("#role").val();
    
	if(pfId==""){
		 errorList.push("Please enter pfId");
	 }
	 if(userName==""){
		 errorList.push("Please enter user name");
	 }
	 if(phoneNumber==""){
		 errorList.push("Please enter phone nuber");
	 }
	 if(emailId==""){
		 errorList.push("Please enter phone Email Id");
	 }
	 if(reportingAuthorityName==""){
		 errorList.push("Please enter Reporting Authority Name");
	 }
	 if(reportingAuthorityEmail==""){
		 errorList.push("Please enter Reporting Authority Email");
		 
	 }
	 if(role=="Select"){
		 errorList.push("Please select valid role ");
		 
	 }

	 return errorList;
}

function addUser(){
	debugger;
		var resp='';
		var errorList=[];
		//var uName=${uName};
		errorList=fromValidation();
		
		 if(errorList.length>0){
			displayErrorsOnPage();
		}
		else{
			debugger;
			$("#role12").html("");
		var modal = document.getElementById("myModal");
		var formData=$("#form").serialize();
		
	    $.ajax({
	    	type:"POST",
	    	url:"addUser1",
	    	data:formData,
	     success: function(data){
	    	// string user="User";
	    	// String uName=${uName};
	    	// console.log(" user name "+uName);
	    	// String str1=" has been succesfully Add";
	    	 resp=data;	 
	    	// $("#para1").html(uName); 
	    	 $("#para").html("User has been succesfully Add"); 
	        	modal.style.display = "block";
	     }
	    }); 
	}
}
	 
function displayErrorsOnPage() {
	var errMsg='';
	$("#emailId12").html("");
	$("#phoneNumber12").html("");	
	$("#pfId12").html("");	
	$("#userName12").html("");	
	$("#reportingAuthorityName12").html("");		
	$("#reportingAuthorityEmail12").html("");
	$("#role12").html("");
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
		if($("#phoneNumber").val()==""){
			$("#phoneNumber12").html("Please Enter Phone Number");	
			
		}else{
				var phone=$.trim($("#phoneNumber").val());
				var phoneNumber=new RegExp(/^[+]?(\d{1,2})?[\s.-]?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/);
				var valid=phoneNumber.test(phone);
				if(!valid){
					 $("#phoneNumber12").html("Phone Number Should be 10 digit");
				 }				
			}
		
		if($("#pfId").val()==""){
			$("#pfId12").html("Please Enter Pf Id");	
		}
		if($("#userName").val()==""){
			$("#userName12").html("Please Enter User Name");	
		}
		if($("#reportingAuthorityName").val()==""){
			$("#reportingAuthorityName12").html("Please Enter Reporting Authority Name");		
		}
		
		if($("#reportingAuthorityEmail").val()==""){
			$("#reportingAuthorityEmail12").html("Please Enter Reporting Authority Email");	
		}	
		
		if($("#role").val()=="Select"){
			$("#role12").html("Please Select valid Role");
		}
	//});

}
</script>
<script> 
$(document).ready(function(){
	debugger;
	var respos='';
	var errorList=[];
	$('#pfId').blur(function(){
		debugger;
		 var pfId=$("#pfId").val();
		 document.getElementById("pfId").innerHTML=pfId;
		 if(pfId !=null && pfId !=""){
		 document.getElementById("pfId").innerHTML=pfId;
		 console.log("inside bluer function...."+pfId);
	         	        $.ajax({
	        	type:"GET",
	        	url:"getByPfId/"+pfId,
	            success: function(data){
	            	console.log("inside data");
	        	    respos=data;
	        	 console.log("response "+respos);
	             $("#pfId12").html(data);
	
	            }
	         	   });
		 }else{
			 $("#pfId12").html("Please Enter Pf Id");
		 }
	});
});
</script>
<script>
 function reSet(){
	 debugger;
		$("#emailId12").html("");
		$("#phoneNumber12").html("");	
		$("#pfId12").html("");	
		$("#userName12").html("");	
		$("#reportingAuthorityName12").html("");		
		$("#reportingAuthorityEmail12").html("");
		$("#emailId").html("");
		$("#phoneNumber").html("");	
		$("#pfId").html("");	
		$("#userName").html("");	
		$("#reportingAuthorityName").html("");		
		$("#reportingAuthorityEmail").html("");
		$("#role12").html("");
	    $.ajax({
	    	type:"GET",
	    	url:"/km/addUser",
	     success: function(data){
	    	 resp=data;       	 	        	 
	     }
	    }); 
 }
</script>

</head>
<body background="#b3cde0">
<div id="mainMenuHome" ng-app="HomeApp" ng-controller="menuController">
		<%
			UserDto userObj = (UserDto) session.getAttribute("userObj");
			String firstName = "";
			String lastName="";
			String phone="";
			if(userObj.getFirstName() !=null){
				firstName = userObj.getFirstName();
			}
			if(userObj.getLastName() !=null){
				lastName = userObj.getLastName();
			}
			if(userObj.getMobileNo() !=null){
				phone = userObj.getMobileNo();
			}
			
		%>
		<div class="inlineHomeMain">
	<div class="inlineHomeMain">
			<img src="/resources/img/sbi.png">
		</div>
		</div>
		<table cellspacing="0">
			<tr>
				<table style="top: 0px; width: 1179px; height: 47px;"
					cellspacing="0">
					<tr>
						<td
							style="left: 187px; width: 910px; background: #280071; color: #FFFFFF"
							align="center"><b>Swayam Monitoring Tool</b></td>
						<td
							style="width: 269px; background: #FDD209; color: #000000; align: center"
							align="center"><b>Welcome <%=firstName%> &nbsp;<%=lastName%></b> 
							<br /> <b> <%=phone%> </b></td>
					</tr>
				</table>
			</tr>
			<tr>
				<td>
					<!--    @* Here first of all we will create a ng-template *@--> 
				<script	type="text/ng-template" id="menu">
            <a ng-click="loadHomeBodyPage(menu.url)">{{menu.name}}</a>
           
            <ul ng-if="(SiteMenu | filter:{parentId : menu.id}).length > 0" class="submenu">
                <li ng-repeat="menu in SiteMenu | filter:{parentId : menu.id}" ng-include="'menu'"></li>
            </ul>
        </script>
					<div id="topnav">
						<ul>
							<!-- @* Here we will load only top level menu *@-->
							<li ng-repeat="menu in SiteMenu | filter:{parentId : 0}"
								ng-include="'menu'"></li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
		<c:set var="base" value="${pageContext.request.contextPath}" />
	

	<script>
	//angular.bootstrap(document.getElementById("appId"), ['app']);
		var appHome = angular.module('HomeApp', []);
		appHome.controller('menuController', [ '$scope', '$http',
				function($scope, $http) {
					$scope.SiteMenu = [];
					
					$scope.loadHomeBodyPage = function(url){
						if(url != undefined){							
							$("#contentHomeApp").load(url);
						}						
					}
					
					$http.get('/common/menu').then(function(data) {
						$scope.SiteMenu = data.data;
					}, function(error) {
						alert('Error');
					})
				} ]);		
		
		
		var ddmenuitem = 0;
		function jsddm_open() {
			jsddm_close();
			ddmenuitem = $(this).find('ul.submenu').css('display', 'block');
		}

		function jsddm_close() {
			if (ddmenuitem)
				ddmenuitem.css('display', 'none');
		}
		$(document).ready(function() {
							//$('#topnav > ul > li').bind('click', jsddm_open)   
							$('#topnav > ul').on('click', 'li', jsddm_open)
							//$('#topnav > ul > li > a').click(function(ev){
							$('#topnav > ul').on('click','li > a',function(ev) {
												if ($(this).hasClass('current')) {
													ev.preventDefault();
												}

												if ($(this).attr('class') != 'active') {
													if ($(this).text() == 'KIOSK MANAGEMENT'
															|| $(this).text() == 'TRANSACTION DASHBOARD'
															|| $(this).text() == 'HEALTH MONITORING'
															|| $(this).text() == 'DATA ANALYSER'
															|| $(this).text() == 'MIS REPORTS'
															|| $(this).text() == 'BILLING AND PAYMENTS') {

														$('#topnav ul li a')
																.removeClass(
																		'active');
														$(this).addClass(
																'active');
													} else {
														$(
																'#topnav ul li ul.submenu li a')
																.removeClass(
																		'active');
														$(this).addClass(
																'active');
													}
												}
											});						
														
							});
							
	</script>
	<br>
	<br>
	
	<h4 align="left">
		<b>Add User</b>
	</h4>
	<div align="center" class="mytable">
		<form:form action="addUser1" modelAttribute="addUser" id="form">
			<table cellpadding="8">
				<tr>
					<td><b style="color: purple">PF ID</b><b><span style="color:red">*</span></b></td>
					<td><form:input path="pfId" id="pfId" required="required" /></td>
					<td></td>
					<td></td>
					<td><b style="color: purple">Username</b><b><span style="color:red">*</span></b></td>
					<td><form:input path="userName" />
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
					<td><b style="color: purple">Phone Number</b><b><span style="color:red">*</span></b></td>
					<td><form:input path="phoneNumber" /></td>
					<td></td>
					<td></td>
					<td><b style="color: purple">EmailId</b><b><span style="color:red">*</span></b></td>
					<td><form:input path="emailId" />
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
				<%--
					<td><b style="color: purple">User Type</b><b><span style="color:red">*</span></b></td>
					<td><form:select path="userType" style="color:blue">
							<form:option value="" label="Select"></form:option>
							<c:forEach var="list" items="${userTypeList}">
								<form:option value="${list.userType}">${list.userType}</form:option>
							</c:forEach>
						</form:select></td>
						--%>
						<td></td><td></td>
					<td></td>
					<td></td>
					<td><b style="color: purple">Role</b><b><span style="color:red">*</span></b></td>
					<td><form:select path="role" id="role" style="color:blue">
							<form:option value="Select" label="Select"></form:option>
							<c:forEach var="list" items="${userRoleList}">
								<form:option value="${list.roleDescription}">${list.roleDescription}</form:option>
							</c:forEach>
						</form:select></td>
				</tr> 
				<tr>
					<td></td>
					<td><span id="userType12" style="color: red"></span></td>
					<td></td>
					<td></td>
					<td></td>
					<td><span id="role12" style="color: red"></span></td>
				</tr>
				<tr>
					<td><b style="color: purple">Reporting Authority Name</b><b><span style="color:red">*</span></b></td>
					<td><form:input path="reportingAuthorityName" /></td>
					<td></td>
					<td></td>
					<td><b style="color: purple">Reporting Authority Email</b><b><span style="color:red">*</span></b></td>
					<td><form:input path="reportingAuthorityEmail" /></td>
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
					<td><input type="reset" value="CANCEL"  /></td>
					<td><input type="button" value="ADD"  onclick="addUser()" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div  class="modal" align="center">

			<!-- Modal content -->
			<div id="myModal" class="modal-content" style="display:none">
				<span class="close" onclick="cloesBox()">&times;</span>
				<p id="para1" align="center">${uName}</p>
				<p id="para" align="center"></p>
				<p id="para" align="center"></p>
			</div>
		</div>
</body>
</html>