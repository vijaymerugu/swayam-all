<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="sbi.kiosk.swayam.common.dto.UserDto" %>

<!Doctype html>
<html>
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<!-- <script src="resources/js/bootstrap.3.4.1.min.js"></script> -->
<script src="resources/js/bootstrap.3.1.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">


<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	
<link href="resources/css/menu.css" rel="stylesheet" type="text/css">	
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>

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
</head>
<body style="background: #EFF3F6; margin: 0px; padding: 0px">
	<div id="mainMenuHome" ng-app="HomeApp" ng-controller="menuController">
		<%
			UserDto userObj = (UserDto) session.getAttribute("userObj");
			//String firstName = "";
			//String lastName="";
			String username="";
			String pfId="";
			/* if(userObj.getFirstName() !=null){
				firstName = userObj.getFirstName();
			}
			if(userObj.getLastName() !=null){
				lastName = userObj.getLastName();
			} */
			if(userObj.getUsername() !=null){
				username = userObj.getUsername();
			}
			if(userObj.getPfId() !=null){
				pfId = userObj.getPfId();
			}
			
		%>
		<div class="inlineHomeMain">
			<img src="resources/img/sbi.png">
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
							align="center"><b>Welcome <%=username%></b> 
							<br /> <b> <%=pfId%> </b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">Log Out</a></td>
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
					
					$http.get('common/menu').then(function(data) {
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

</body>
</html>



<html lang="en">
<body>
<div id="contentHomeApp">
</div>
</html>
