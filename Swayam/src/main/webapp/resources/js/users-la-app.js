var app = angular.module('app', ['ui.grid','ui.grid.pagination']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   var counttype = "";
   $scope.getCountType = function(type){
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype);
	}
   $scope.refresh = function()
   {  		if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	
		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
	    }else{
	    	
		   $scope.gridOptions.data = $scope.gridOptions.data;
	    }
   };

   UserManagementService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype).success(function(data){
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
   });
   
   $scope.gridOptions = {
    paginationPageSizes: [5, 10, 20],
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,
    columnDefs: [
      { name: 'userId', displayName: 'PF ID'  },
      { name: 'username', displayName: 'Username'  },
      { name: 'firstName', displayName: 'First Name'  },
      { name: 'lastName', displayName: 'Last Name'  },
      { name: 'role', displayName: 'Role'  },
      { name: 'Edit',
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a href="/km/editUserMaster?userId={{ row.entity.userId }}">Edit</a></div>'
      },
      { name: 'Delete',
    	  headerCellTemplate: '<div></div>',
          cellTemplate: '<div class="ui-grid-cell-contents"><a href="/km/deleteUserMaster?userId={{ row.entity.userId }}">Delete</a></div>'
      },
      { name: 'Assign',
    	  displayName: 'Assign Kiosk',
    	  headerCellTemplate: '<div></div>',
          cellTemplate: '<div class="ui-grid-cell-contents" id="myBtn"><a href="#myModal" data-href="/km/userkioskmappingpopup?username="+{{ row.entity.userId }} data-val="{{ row.entity.username }}" class="openPopup">Assign Kiosk</a></div>'
      }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
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
          url: '/users/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);