<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<!Doctype html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "home.jsp" %>	
<div ng-app="UserKioskMappingApp" id="UserKioskMappingAppId">
    <div ng-controller="userKioskMappingController">
	<form action="km/userkioskmapping/kiosksbycirclepost" method="POST">
<table>
	<tr>
	<td>
	<label for="username">Username:</label>
	<select ng-model="selectedUsernamer" name="username" required>
	   <option disabled selected value>  Select an option </option>
	  <option ng-repeat="x in UserList" value="{{x.username}}">{{x.username}}</option>
	</select>	
	</td>
	<td>
	<label for="kioskIdList">Choose a Kiosk ID:</label>
	<select id="kioskIdList" name="kioskIdList" multiple>
	  <option ng-repeat="x in KioskList" value="{{x}}">{{x}}</option>
	</select>
	</td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="submit">
	</td>
	</tr>
	
</table>	
	</form>

</div>
</div>
<script>
angular.element(document).ready(function() {
    angular.bootstrap(document.getElementById("UserKioskMappingAppId"), ['UserKioskMappingApp']);   
  });
var app = angular.module('UserKioskMappingApp', []);
app.controller('userKioskMappingController', ['$scope', '$http', function ($scope, $http) {
    $scope.UserList = [];
    $http.get('km/userkioskmapping/usersbyca').then(function (data) {
        $scope.UserList = data.data;
    }, function (error) {
        alert('Error');
    });
    $scope.KioskList = [];
    $http.get('km/userkioskmapping/kiosksbycircle').then(function (data) {
        $scope.KioskList = data.data;
    }, function (error) {
        alert('Error');
    })
}])
</script>
</body>
</html>