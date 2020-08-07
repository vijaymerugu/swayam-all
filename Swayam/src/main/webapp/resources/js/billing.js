
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.directive('fileModel', ['$parse', function ($parse) {
return {
  restrict: 'A',
  link: function(scope, element, attrs) {
     var model = $parse(attrs.fileModel);
     var modelSetter = model.assign;
     
     element.bind('change', function(){
        scope.$apply(function(){
           modelSetter(scope, element[0].files[0]);
        });
     });
  }
};
}]);

app.controller('BillingCtrl', ['$scope','$filter','BillingService','$http', function ($scope, $filter,BillingService,$http) {
	
	$scope.doUploadFile = function(){
    var file = $scope.uploadedFile;
    var url = "billingallocation";
    alert("file"+file);
    
    var data = new FormData();
    data.append('uploadfile', file);
   
    var config = {
        transformRequest: angular.identity,
        transformResponse: angular.identity,
      headers : {
        'Content-Type': undefined
        }
    }
    $http.post(url, data, config).then(function (response) {
   $scope.uploadResult=response.data;
  
 }, function (response) {
   $scope.uploadResult=response.data;
 });
 };
	
	
	 var paginationOptions = {
    pageNumber: 1,
	 pageSize: 20,
	 sort: null
  };
  
  var counttype = "";


   
  $scope.getCountType = function(type){
     
      counttype=type;
      BillingService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype).success(function(data){				   
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				   });
	}
  
  
  $scope.refresh = function()
  {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,counttype).success(function(data){
	 				   
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
	 	    }else{
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,counttype).success(function(data){
	 	 				
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	   });
	 	    }
	    };

  BillingService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype).success(function(data){			   
	  $scope.gridOptions.data = data.content;
	  $scope.gridOptions.totalItems = data.totalElements;
  });
 
  $scope.gridOptions = {
   paginationPageSizes: [20, 30, 40],
   paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,
	
     columnDefs: [
         { name: 'repRefNo', displayName: 'RFP_REF_NO'  },
         { name: 'vendorId', displayName: 'Vendor'  },
         { name: 'crclCode', displayName: 'Circle'  },
         { name: 'allocatedQuantity', displayName: 'ALLOCATED_QUANTITY'  }
        
   ],
   onRegisterApi: function(gridApi) {
       $scope.gridApi = gridApi;
       gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
         paginationOptions.pageNumber = newPage;
         paginationOptions.pageSize = pageSize;
         BillingService.getUsers(newPage,pageSize,counttype).success(function(data){        	  
       	  $scope.gridOptions.data = data.content;
        	  $scope.gridOptions.totalItems = data.totalElements;
         });
       });
    }
 };
 
}]);


app.service('BillingService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
       return  $http({
         method: 'GET',
         url: 'billingallocation/get?page='+pageNumber+'&size='+size+'&type='+counttype
        
       });
   }
	
   return {
   	getUsers:getUsers
   };

}]);
