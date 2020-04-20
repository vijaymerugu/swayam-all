var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
   var counttype = "";
   $scope.getCountType = function(type){
      alert(1);
        alert("1=="+type);
       counttype=type;
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype).success(function(data){
				   alert("succ");
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				   });
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
	enableGridMenu: true,
	exporterMenuCsv: false,
	exporterPdfDefaultStyle: {fontSize: 9},   
    exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, color: 'black'},      
    exporterPdfFooter: function ( currentPage, pageCount ) {
      return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
    },    
    exporterPdfCustomFormatter: function ( docDefinition ) {        
        docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
        return docDefinition;
      },

    columnDefs: [
      { name: 'userId', displayName: 'PF ID'  },
      { name: 'username', displayName: 'Username'  },
      { name: 'firstName', displayName: 'First Name'  },
      { name: 'lastName', displayName: 'Last Name'  },
      { name: 'role', displayName: 'Role'  },
      { name: 'Edit',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a href="/km/editUserMaster?userId={{ row.entity.userId }}">Edit</a></div>'
      },
      { name: 'Delete',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div></div>',
          cellTemplate: '<div class="ui-grid-cell-contents"><a href="/km/deleteUserMaster?userId={{ row.entity.userId }}">Delete</a></div>'
      }
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
	alert(11);
	function getUsers(pageNumber,size,counttype) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: '/users/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);