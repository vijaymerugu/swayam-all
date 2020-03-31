<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
		
<!Doctype html>
<html>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script> -->
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<head>

</head>

<body>

<div ng-app="HomeApp" id="HomeAppId">
    <div ng-controller="menuController">
      <!--    @* Here first of all we will create a ng-template *@-->
        <script type="text/ng-template" id="menu">
            <a href="{{menu.url}}">{{menu.name}}</a>
           
            <ul ng-if="(SiteMenu | filter:{parentId : menu.id}).length > 0">
                <li ng-repeat="menu in SiteMenu | filter:{parentId : menu.id}" ng-include="'menu'"></li>
            </ul>
        </script>
        <ul class="main-navigation" >
           <!-- @* Here we will load only top level menu *@-->
            <li ng-repeat="menu in SiteMenu | filter:{parentId : 0}" ng-include="'menu'"></li>
        </ul>
    </div>
</div>
<c:set var="base" value="${pageContext.request.contextPath}"/>


<link href="<c:url value="/resources/css/mainMenu.css" />" rel="stylesheet" type="text/css">

<script>

var app = angular.module('HomeApp', []);
app.controller('menuController', ['$scope', '$http', function ($scope, $http) {
    $scope.SiteMenu = [];
    $http.get('/common/menu').then(function (data) {
        $scope.SiteMenu = data.data;
    }, function (error) {
        alert('Error');
    })
}])
</script>
</body>
</html>

		