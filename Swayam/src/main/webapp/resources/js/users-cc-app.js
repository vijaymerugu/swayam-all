var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   var counttype = "";
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='km/editUserMaster?userId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
   $scope.loadHomeBodyPageFormsDel = function(url){	   
		if(url != undefined){	
			var str ='km/deleteUserMaster?userId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
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
      { name: 'userId', displayName: 'Sr No'  },
      { name: 'pfId', displayName: 'PF ID'  },
      { name: 'username', displayName: 'Username'  },      
      { name: 'role', displayName: 'Role'  },
      { name: 'reportingAuthorityName', displayName: 'Reporting Authority'  }
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
          url: 'users/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);