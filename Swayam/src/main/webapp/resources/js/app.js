var app = angular.module('app', ['ui.grid','ui.grid.pagination']);

app.controller('UserManagementCtrl', ['$scope','$filter','StudentService', function ($scope, $filter,StudentService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
   $scope.refresh = function()
   {  		if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	
		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
	    }else{
	    	
		   $scope.gridOptions.data = $scope.gridOptions.data;
	    }
   };

   StudentService.getStudents(paginationOptions.pageNumber,
		   paginationOptions.pageSize).success(function(data){
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
      }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          StudentService.getStudents(newPage,pageSize).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);


app.service('StudentService',['$http', function ($http) {
	
	function getStudents(pageNumber,size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: '/users/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getStudents:getStudents
    };
	
}]);