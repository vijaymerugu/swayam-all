angular.element(document).ready(function() {	
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   var counttype = "";
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='km/editUserMasterLA?userId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
  $scope.loadHomeBodyPageFormsDel = function(url){	   
		if(url != undefined){	
			var str ='km/deleteUserMaster?userId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
	
	 $scope.loadHomeBodyPageFormsDeMap = function(url){	   
		if(url != undefined){	
			var str ='km/userkioskmappingpopup?username=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
	
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
     /* { name: 'userId', displayName: 'SrNo'  },
      { name: 'pfId', displayName: 'PF ID / User Name'  },
      { name: 'username', displayName: 'Employee Name'  },      
      { name: 'role', displayName: 'Role'  },
      { name: 'noOfAssignedKiosks', displayName: 'No of Assigned Kiosks'  },
      { name: 'reportingAuthorityName', displayName: 'Reporting Authority'  },
      { name: 'Edit',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div class="ui-grid-cell-contents" style="cursor: hand;cursor: pointer;"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.userId)">Edit</a></div>'
      },
      { name: 'Delete',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div class="ui-grid-cell-contents" style="cursor: hand;cursor: pointer;"><a ng-click="grid.appScope.loadHomeBodyPageFormsDel(row.entity.userId)">Delete</a></div>'
      },*/
      
    //     { name: 'userId', displayName: 'SrNo'  },
      { name: 'circle', displayName: 'CIRCLE'  },
       { name: 'role', displayName: 'ROLE',width:120  },
      { name: 'pfId', displayName: 'PF ID / USER ID'  },
      { name: 'username', displayName: 'Employee Name'  },      
    //  { name: 'role', displayName: 'Role'  },
      { name: 'mobileNo', displayName: 'MOBILE NO'  },
      { name: 'mailId',displayName: 'EMAIL ID'  },
      { name: 'reportingAuthorityName', displayName: 'Reporting Authority'  },
      { name: 'noOfAssignedKiosks', displayName: 'No of Assigned Kiosks'  },
      { name: 'Edit',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div class="ui-grid-cell-contents" style="cursor: hand;cursor: pointer;"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.userId)">Edit</a></div>'
      },
      { name: 'Delete',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div class="ui-grid-cell-contents" style="cursor: hand;cursor: pointer;"><a ng-click="grid.appScope.loadHomeBodyPageFormsDel(row.entity.userId)">Delete</a></div>'
      },
      { name: 'Assign',
    	  exporterSuppressExport: true,
    	  displayName: 'Assign Kiosk',
    	  headerCellTemplate: '<div></div>',
       //   cellTemplate: '<div class="ui-grid-cell-contents" id="myBtn" style="cursor: hand;cursor: pointer;"><div ng-if="row.entity.role == \'CMF\' && row.entity.noOfAssignedKiosks > 0"><a data-href="km/userkioskmappingpopup?username="+{{ row.entity.userId }} data-val="{{ row.entity.pfId }}" class="openPopup">DeMap Kiosks</a></div></div>'
        cellTemplate: '<div style="cursor: hand;cursor: pointer;" ng-if="row.entity.role == \'CMF\' && row.entity.noOfAssignedKiosks > 0"> <a ng-click="grid.appScope.loadHomeBodyPageFormsDeMap(row.entity.pfId)">DeMap Kiosks</a></div>'
      }
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
          url: 'usersByCircle/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});