var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);


app.controller('UserManagementCtrlSA', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) 
	{
	
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
		 }; 
	   
	   var counttype = "";
	   $scope.getCountType = function(type){
	
		   if ($scope.counttype != type)
		   {
		   paginationOptions.pageNumber = 1;

		   }

		   $scope.counttype=type;
		   UserManagementService.getUsers(paginationOptions.pageNumber,
				   paginationOptions.pageSize,$scope.counttype).success(function(data){
					   
						  $scope.gridOptions.data = data.content;
						  $scope.gridOptions.paginationCurrentPage = paginationOptions.pageNumber;
					 	  $scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize,$scope.counttype).success(function(data){
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
		 		   
		 	    }else{
		 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
		 	 			   paginationOptions.pageSize,$scope.counttype).success(function(data){
		 	 		  $scope.gridOptions.data = data.content;
		 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	 	   });
		 	    }
		    };
	   
	   
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,$scope.counttype).success(function(data){
		  $scope.gridOptions.data = data.content;
	 	  $scope.gridOptions.totalItems = data.totalElements;
	   });
	   
	   
	   $scope.gridOptions = {
				/*paginationPageSizes: [20, 30, 40],*/
			    paginationPageSize: paginationOptions.pageSize,
			    enableColumnMenus:false,
				useExternalPagination: true,
				
			    columnDefs: [
			      { name: 'vendor', displayName: 'Vendor'  },
			      { name: 'ticketId', displayName: 'Ticket Id' },
			      { name: 'kisokId', displayName: 'KioskId'  },
			      { name: 'branchCode', displayName: 'Branch Code'  },
			      { name: 'callCategory', displayName: 'Call Category'},
			      { name: 'callSubCategory', displayName: 'Call Sub Category'  },
			      { name: 'call_log_date', displayName: 'Call Log Date'  },
			      { name: 'ageing',  displayName: 'Ageing Hours'},
			      { name: 'statusOfComplaint',  displayName: 'Status of Complaint'},
			      { name: 'assigned_to_FE',  displayName: 'Assigned to FE'},
			      { name: 'fe_schedule', displayName: 'FE Schedule'}
			    ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;
			          UserManagementService.getUsers(newPage,pageSize,$scope.counttype).success(function(data){
			        	  $scope.gridOptions.data = data.content;
			         	  $scope.gridOptions.totalItems = data.totalElements;
			          });
			        });
			     }
			  };
	   
	   
	}]);






app.service('UserManagementService',['$http', function ($http) {
	function getUsers(pageNumber,size,counttype) {
		
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/ticketCentorFilter/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
    return {
    	getUsers:getUsers
    };
	
}]);

