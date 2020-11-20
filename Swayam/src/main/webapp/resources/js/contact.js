		
var app = angular.module('app', []);



app.controller('ContactCtr', ['$scope','$filter','$http','$window','$cacheFactory',function ($scope, $filter, $http, $window,$cacheFactory) {
			   var paginationOptions = {
			     pageNumber: 1,
				 pageSize: 20,
				 sort: null
			   };
			   

			   

   //get urgent active messages 
    $scope.LoadContactData=function(){
		   $http({
				method : "GET",
				url : 'cs/getContact',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				$scope.Content = data;
				
				console.log("Content " + $scope.Content)
				
			}).error(function(data, status) {
				console.log("Unable to load the messages" +  data + " Status " + status);
			});
		   
	   }
    
    
    
    
    $scope.LoadFirstContact=function(){
		   $http({
				method : "GET",
				url : 'cs/getFirst',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				$scope.first = data;
				
				console.log("Content " + $scope.first)
				
			}).error(function(data, status) {
				console.log("Unable to load the messages" +  data + " Status " + status);
			});
		   
	   }
 
    
    
    
    $scope.LoadSecondContact=function(){
		   $http({
				method : "GET",
				url : 'cs/getSecond',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				$scope.second = data;
				
				console.log("Content " + $scope.second)
				
			}).error(function(data, status) {
				console.log("Unable to load the messages" +  data + " Status " + status);
			});
		   
	   }
    
    
    
    $scope.LoadContactData();
    $scope.LoadFirstContact()
    $scope.LoadSecondContact();

}]);

/*app.service('contactService',['$http', function ($http) {
function loadApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getAvailability'
        });
    }
    return {
    	loadApiData:loadApiData
    };
    
}]);*/