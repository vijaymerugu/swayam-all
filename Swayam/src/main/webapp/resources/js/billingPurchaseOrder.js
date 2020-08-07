
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);
/*var app = angular.module('app', []);*/


app.controller('BillingOrderCtrl', ['$scope','$filter','BillingOrdereService','$http', function ($scope, $filter,BillingOrdereService,$http) {
	
	$scope.save = function(){
	
		
	}
	 var paginationOptions = {
    pageNumber: 1,
	 pageSize: 20,
	 sort: null
  };
  
  var counttype = "";

  $scope.loadHomeBodyPageForms = function(url){	 
	  console.log("url"+url);
		if(url != undefined){	
			var str ='editBillingOrder/allocId?allocId=' + url;
			console.log("str"+str);
			$("#contentHomeApp").load(str);
		}						
	}
   
  $scope.getCountType = function(type){
     
      counttype=type;
      BillingOrdereService.getUsers(paginationOptions.pageNumber,
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

  BillingOrdereService.getUsers(paginationOptions.pageNumber,
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
         { name: 'allocatedQuantity', displayName: 'ALLOCATED_QUANTITY'  },
         { name: 'unitPrice',displayName: 'PO_Number'},
          	
         { name: 'remainingQuantity',displayName: 'remainingQuantity'},
         
         
         { name: 'createdDate', displayName: 'LAST PO Issue Date'  },
         { name: 'Edit',
       	  exporterSuppressExport: true,
       	  headerCellTemplate: '<div></div>',
       	  cellTemplate: '<div class="ui-grid-cell-contents"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.allocId)">Edit</a></div>'
         },
       
   ],
   onRegisterApi: function(gridApi) {
       $scope.gridApi = gridApi;
       gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
         paginationOptions.pageNumber = newPage;
         paginationOptions.pageSize = pageSize;
         BillingOrdereService.getUsers(newPage,pageSize,counttype).success(function(data){        	  
       	  $scope.gridOptions.data = data.content;
        	  $scope.gridOptions.totalItems = data.totalElements;
         });
       });
    }
 };
 
}]);


app.service('BillingOrdereService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
       return  $http({
         method: 'GET',
         url: 'billingorder/get?page='+pageNumber+'&size='+size+'&type='+counttype
        
       });
   }
	
   return {
   	getUsers:getUsers
   };

}]);
