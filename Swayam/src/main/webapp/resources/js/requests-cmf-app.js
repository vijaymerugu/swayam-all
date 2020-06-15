angular.element(document).ready(function() {	
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter','ui.grid.selection']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   var counttype = "";
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='hm/viewCmfCaseId?caseId=' + url;
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
      { name: 'id', displayName: 'Case Id', width:100,
    	  //cellTemplate: '<div class="ui-grid-cell-contents"><a href="/hm/viewCaseId?caseId={{ row.entity.id }}">{{ row.entity.id }}</a></div>'
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.id)">{{ row.entity.id }}</a></div>'  
      },
      { name: 'category', displayName: 'Category', width:150  },
      { name: 'subCategory', displayName: 'Sub Category', width:200  },
      { name: 'kioskId', displayName: 'ATM Id', width:100  },
      { name: 'modifiedDate', width:200, displayName: 'Request Date Time     ',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
    	  //cellTemplate:'<div class="ui-grid-cell-contents">{{grid.appScope.showDate(row.entity.modifiedDate)}}</div>'
    		  },
      { name: 'modifiedBy', displayName: 'Request By', width:100  },
      { name: 'comments', headerCellTemplate: '<div>Comments By <br/> Checker/Approver</div>', width:200  },
      { name: 'reqCategory',
    	  exporterSuppressExport: true, width:250,
    	  headerCellTemplate: '<div>Status</div>',
    	  cellTemplate: '<div ng-if="row.entity.reqCategory == \'APRD\'">APPROVED</div><div ng-if="row.entity.reqCategory == \'REJ\'">REJECTED</div>'
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
          url: 'hm/requestsCmf/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});