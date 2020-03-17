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
<div ng-app="CmsCmfMappingApp" id="CmsCmfMappingAppId">
    <div ng-controller="cmsCmfMappingController">
	<form action="/km/cmscmfmapping/cmscmfmappingpost" method="POST">
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
	<label for="kioskIdList">Choose a CMF Username:</label>
	<select id="cmfUserIdIdList" name="cmfUserIdIdList" multiple>
	  <option ng-repeat="x in cmfUserIdIdList" value="{{x.username}}">{{x.username}}</option>
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
    angular.bootstrap(document.getElementById("CmsCmfMappingAppId"), ['CmsCmfMappingApp']);   
  });
var app = angular.module('CmsCmfMappingApp', []);
app.controller('cmsCmfMappingController', ['$scope', '$http', function ($scope, $http) {
    $scope.UserList = [];
    $http.get('/km/cmscmfmapping/cmsusersbyca').then(function (data) {
        $scope.UserList = data.data;
    }, function (error) {
        alert('Error');
    });
    $scope.KioskList = [];
    $http.get('/km/cmscmfmapping/cmfusersbyca').then(function (data) {
        $scope.cmfUserIdIdList = data.data;
    }, function (error) {
        alert('Error');
    })
}])
</script>
</body>
</html>