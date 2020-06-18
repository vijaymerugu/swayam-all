//var app = angular.module('app', ['ui.grid','ui.grid.pagination']);
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl1', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) 
	{
	
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
		 };
	   
	   
	   var counttype = "";
	   $scope.getCountType = function(type){
	
	counttype=type;
		   UserManagementService.getUsers(paginationOptions.pageNumber,
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
	   
	   
	   UserManagementService.getUsers(paginationOptions.pageNumber,
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
			      { name: 'vendor', displayName: 'Vendor'  },
			      { name: 'ticketId', displayName: 'Ticket Id' },
			      { name: 'kisokId', displayName: 'KisokId'  },
			      { name: 'branchCode', displayName: 'Branch Code'  },
			      { name: 'serveriry', displayName: 'Circle'  },
			      { name: 'callCategory',headerCellTemplate: '<div>Call<br/>Category</div>'},
			      { name: 'cms_cmf_assigned',headerCellTemplate: '<div>CMS/CMF<br/>Assigned</div>'  },
			      { name: 'call_log_date',headerCellTemplate: '<div>Call Log<br/>Date</div>',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'   },
			      { name: 'ageing',  displayName: 'Ageing Hours'},
			      { name: 'statusOfComplaint',headerCellTemplate: '<div>Status of<br/>Complaint</div>'},
			      { name: 'assigned_to_FE', headerCellTemplate: '<div>Assigned<br/>to FE</div>'}
			      
			     
			    ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;
			          UserManagementService.getUsers(newPage,pageSize,counttype).success(function(data){
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
          url: 'hm/ticketCentorByCircle/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
    return {
    	getUsers:getUsers
    };
	
}]);

