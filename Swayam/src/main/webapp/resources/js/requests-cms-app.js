angular.element(document).ready(function() {	
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter','ui.grid.selection', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
 /*  $scope.loadHomeBodyPageForms1 = function(url){	
   alert(url);   
   if(url != undefined){	
			var str ='hm/activateCmsCaseId?caseId=' + url;
			$("#contentHomeApp").load(str);
		}	
							
	}*/
   var counttype = "";
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='hm/viewCmsCaseId?caseId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
   $scope.getCountType = function(type){
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype);
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
   
   $scope.showDate = function(value)
   {
     return new Date(value);
   }
   
   $scope.gridOptions = {
	/*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,
	/*
    columnDefs: [
{ name: 'id', displayName: 'Case Id', width:150,
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.id)">{{ row.entity.id }}</a></div>'  
      },
    
      { name: 'kioskId', displayName: 'Kiosk Id', width:250  },
      { name: 'modifiedDate', width:250, displayName: 'Request Date Time     ',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
    	  //cellTemplate:'<div class="ui-grid-cell-contents">{{grid.appScope.showDate(row.entity.modifiedDate)}}</div>'
    		  },
      { name: 'modifiedBy', displayName: 'Request By', width:250  },
      { name: 'comments', headerCellTemplate: '<div>Comments By Requestor</div>', width:390  },
      { name: 'remarks',
    	  exporterSuppressExport: true, width:400,
    	  headerCellTemplate: '<div>Remarks By Checker</div>',
    	  cellTemplate: '<div class="addedRows"><input type="text" name="remarks[{{row.entity.id}}]" id="remarks" maxlength="100" /></div>'
      },
      { name: 'fromDate', width:200,type: 'date', cellFilter: 'date:"dd-MM-yy"',
    	  headerCellTemplate: '<div>From Date</div>'
      },
      { name: 'toDate',type: 'date', cellFilter: 'date:"dd-MM-yy"',
    	  width:200,
    	  headerCellTemplate: '<div>To Date</div>',
      },
    
    ],*/
    
    
      columnDefs: [
      { name: 'id', displayName: 'Case Id',
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.id)">{{ row.entity.id }}</a></div>'  
      },
    
      { name: 'kioskId', displayName: 'Kiosk Id',   },
      { name: 'modifiedDate',  displayName: 'Request Date Time',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
    	  //cellTemplate:'<div class="ui-grid-cell-contents">{{grid.appScope.showDate(row.entity.modifiedDate)}}</div>'
    		  },
      { name: 'modifiedBy', displayName: 'Request By' },
      { name: 'comments', headerCellTemplate: '<div>Comments By Requestor</div>'},
      { name: 'remarks',
    	  exporterSuppressExport: true, width:400,
    	  headerCellTemplate: '<div>Remarks By Checker</div>',
    	  cellTemplate: '<div class="addedRows"><input type="text" name="remarks[{{row.entity.id}}]" id="remarks" maxlength="100" /></div>'
      },
      { name: 'fromDate',type: 'date', cellFilter: 'date:"dd-MM-yy"',
    	  headerCellTemplate: '<div>From Date</div>'
      },
      { name: 'toDate',type: 'date', cellFilter: 'date:"dd-MM-yy"',
    	  
    	  headerCellTemplate: '<div>To Date</div>',
      },
     /*  { name: 'Active',
    	  headerCellTemplate: '<div></div>',
    	  cellTemplate: '<div><input type="button" ng-click="grid.appScope.loadHomeBodyPageForms1(row.entity.id)" id="button" class="button" value="Active" id="active" /></div>'
      },*/
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
          url: 'hm/requestsCms/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});