<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="sbi.kiosk.swayam.common.dto.UserDto"%>

<!Doctype html>
<html>
<title>Swayam</title>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- <link rel="icon" href="resources/img/SBI_Logo.jpg" type="image/x-icon"> -->
<link rel="icon" href="resources/img/favicon.png" type="image/x-icon">
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<!-- <script src="resources/js/bootstrap.3.4.1.min.js"></script> -->
<script src="resources/js/bootstrap.3.1.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">


<!-- <link rel="stylesheet" href="resources/css/grid-style.css" /> -->
<link rel="stylesheet" href="resources/css/body-page.css" />
 <link rel="stylesheet" href="resources/css/notification.css" /> 

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" />

<!-- 
 <script src="resources/js/ui-grid.js"></script>
<script	src="resources/js/ui-grid.min.js"></script>  -->

<!--  <script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>  -->

<script	src="resources/js/ui-grid.min-1.js"></script> 

<script src="resources/js/ui-grid.js"></script>
<script src="resources/js/ui-grid.min.js"></script>
<!-- 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="resources/css/menu.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css" />
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script> -->
  <script src="resources/js/angular-route.min.js"></script>
<script src="resources/js/angular.js"></script>
<script src="resources/js/angular-touch.js"></script>
<script src="resources/js/angular-animate.js"></script>
<script src="resources/js/angular-aria.js"></script>
</head>
<body style="background: #EFF3F6; margin: 0px; padding: 0px;width: 98%;overflow-x: hidden;">
	<div id="mainMenuHome" ng-app="HomeApp" ng-controller="menuController" ng-init='load("${suburl}")'>
		<%
			UserDto userObj = (UserDto) session.getAttribute("userObj");
			//String firstName = "";
			//String lastName="";
			String username = "";
			String pfId = "";
			/* if(userObj.getFirstName() !=null){
				firstName = userObj.getFirstName();
			}
			if(userObj.getLastName() !=null){
				lastName = userObj.getLastName();
			} */
			if (userObj.getUsername() != null) {
				username = userObj.getUsername();
			}
			if (userObj.getPfId() != null) {
				pfId = userObj.getPfId();
			}
		%>
		<div class="inlineHomeMain">
			<img src="resources/img/sbi.png">
		</div>
		<table cellspacing="0px;">
			<tr>
				<table style="top: 0px; width: 90%; height: 47px;"
					cellspacing="0px;">
					<tr>
						<td
							style="left: 187px;width: 85%;background: #280071;color: #FFFFFF;font-size: xx-large;"
							align="center"><b>Swayam Monitoring Tool</b></td>
						<td
							style="width: 200%; background: #FDD209; color: #000000; align: center"
							align="center">
							<b>Welcome <%=username%></b>
							
							<br /> 
													<ul id="nav">
								<li id="notification_li"><span id="notification_count" ng-bind="unReadNotificationCount"></span>

									<a href="#" id="notificationLink"><i class="fa fa-bell"></i></a>

									<div id="notificationContainer">
										<div id="notificationsBody" class="notifications">
											<ul>
												<li>&nbsp;</li>
												<li data-ng-repeat="notification in notifications">
												<a ng-if="notification.status =='N'" style="color:#0000CD; text-decoration: none;" href="#"  ng-click="updateMessage(notification)">{{notification.message}}</a>
												<a ng-if="notification.status =='Y'" style="color:#00008B; text-decoration: none;" href="#"  ng-click="updateMessage(notification)">{{notification.message}}</a>
												</li>
											</ul>
										</div>
									</div></li>
							</ul>
							<b> <%=pfId%>
						</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">Log Out</a>				
						</td>
					</tr>
				</table>
			</tr>
			<tr>
				<td>
					<!--    @* Here first of all we will create a ng-template *@--> <script
						type="text/ng-template" id="menu">
            <a ng-click="loadHomeBodyPage(menu.url)" style="cursor: hand;cursor: pointer;">{{menu.name}}</a>
          <ul ng-if="(SiteMenu | filter:{parentId : menu.id}).length > 0" class="submenu"> 
                <li ng-repeat="menu in SiteMenu | filter:{parentId : menu.id}:true" ng-include="'menu'"></li>   
				
            </ul>
        </script>
					<div id="topnav">
						<ul>
							<!-- @* Here we will load only top level menu *@-->
							<li ng-repeat="menu in SiteMenu | filter:{parentId:'0'}:true"
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
					$scope.notifications = [];
					$scope.updatedNotifications = [];
					$scope.unReadNotificationCount = 0;
					
					 $scope.load = function(suburl) {
						   
							$("#contentHomeApp").load(suburl);
					
							
				};  
					
					$scope.loadHomeBodyPage = function(url) { 
						if (url != undefined) {
							$("#contentHomeApp").load(url);
							//DO NOT DELETE THIS CODE. 
							//This will kill all intervals and timeouts too in 1 seconds of Data Analyser. 
							var killId = setTimeout(function() {
								for (var i = killId; i > 0; i--)
									clearInterval(i)
							}, 1000);
							//END HERE.
						}
					}

					$http.get('common/menu').then(function(data) {
						$scope.SiteMenu = data.data; 
					}, function(error) {
						alert('Error');
					})

					
					loadNotificationMessages();
					function loadNotificationMessages(){
						$scope.unReadNotificationCount = 0;
						$http.get('notify/get?userId='+<%=pfId%>).then(function(data) {
							$scope.notifications = data.data;
							angular.forEach($scope.notifications, function(value, key) {
								if(value.status == 'N'){
									$scope.unReadNotificationCount++;
								}
							});
						}, function(error) {
							console.log('Error in loadNotificationMessages');
						});	
					}
									
				 $scope.updateMessage = function(notification) {
					 $http.get('notify/update?notifyId='+notification.notifyId+'&status=Y').then(function(data) {
							$scope.updatedNotifications = data.data;
							loadNotificationMessages();
						}, function(error) {
							console.log('Error in updateMessage');
						});	
				 };
					
				} ]);
		
/* 		appHome.filter('exactMatch', function() { 
		    return function(SiteMenu, pattern) {
		        var result = [];
		        SiteMenu.forEach(function (SiteMenu) {
		            if (SiteMenu.parentId === pattern) {
		                result.push(SiteMenu);
		                
		            }
		        });                
		        return result;
		    }
		}); */

		var ddmenuitem = 0;
		function jsddm_open() { 
			jsddm_close();
			ddmenuitem = $(this).find('ul.submenu').css('display', 'block');
		}
		function jsddm_close() { 
			if (ddmenuitem)
				ddmenuitem.css('display', 'none');
		}
		$(document)
				.ready(
						function() { 
							//$('#topnav > ul > li').bind('click', jsddm_open)   
							$('#topnav > ul').on('mouseover', 'li', jsddm_open)
							//$('#topnav > ul > li > a').click(function(ev){
							$('#topnav > ul')
									.on(
											'click',
											'li > a',
											function(ev) {
												if ($(this).hasClass('current')) {
													ev.preventDefault();
												}
												if ($(this).attr('class') != 'active') {
													if ($(this).text() == 'KIOSK MANAGEMENT'
															|| $(this).text() == 'TRANSACTION DASHBOARD'
															|| $(this).text() == 'HEALTH MONITORING'
															|| $(this).text() == 'DATA ANALYSER'
															|| $(this).text() == 'MIS REPORTS'
															|| $(this).text() == 'BILLING AND PAYMENTS'
																|| $(this).text() == 'SUPPORT') {
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
							
							//Loading Notification Message on Page Load
							$("#notificationLink").click(function () {
        $("#notificationContainer").fadeToggle(300);
        //$("#notification_count").fadeOut("slow");
        $('#notificationLink > i.fa-bell').addClass('fa-bell-o');
        $('#notificationLink > i.fa-bell').removeClass('fa-bell');
        return false;
    });

    //Document Click hiding the popup 
    $(document).click(function () {
        $("#notificationContainer").hide();
    });

    //Popup on click
    $("#notificationContainer").click(function () {
        return false;
    });

						});
	</script>

</body>
</html>



<html lang="en">
<body>
	<div id="contentHomeApp"></div>

</html>
