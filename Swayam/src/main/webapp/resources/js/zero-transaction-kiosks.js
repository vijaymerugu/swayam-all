var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('ZeroTransactionKiosksCtrl', ['$scope','$filter','ZeroTransactionKiosksService', function ($scope, $filter,ZeroTransactionKiosksService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
   var counttype = "";
   
   $scope.fromDate = {
	  value: new Date(2020, 12, 22)
   };
 
   $scope.toDate = {
 	  value: new Date(2020, 12, 22)
   };  
   
   
   $scope.loadHomeBodyPageFormsGenerate = function(){	
	   $scope.fromDate.value = $filter('date')($scope.fromDate.value, "dd-MMM-yy");
	   console.log($scope.fromDate.value);
	   $scope.toDate.value = $filter('date')($scope.toDate.value, "dd-MMM-yy");
	   console.log($scope.toDate.value);
   	
   	ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
   			   paginationOptions.pageSize,counttype,$scope.fromDate.value,$scope.toDate.value).success(function(data){
   		  $scope.gridOptions.data = data.content;
   	 	  $scope.gridOptions.totalItems = data.totalElements;
   	   });
	}
    
   $scope.getCountType = function(type){
      
       counttype=type;
       ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype).success(function(data){
				   
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				   });
	}
   
   
   $scope.refresh = function()
   {  		
	    if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	
		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
	    }else{
	    	
		   $scope.gridOptions.data = $scope.gridOptions.data;
	    }
   };

   ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
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
          { name: 'circleName', displayName: 'Circle'  },
          { name: 'network', displayName: 'NW'  },
          { name: 'module', displayName: 'Mod'  },
          { name: 'region', displayName: 'Reg'  },
          { name: 'branchCode', displayName: 'Branch Code'  },
          { name: 'branchName', displayName: 'Branch Name'  },
          { name: 'kioskId', displayName: 'Kiosk ID'  },
          { name: 'vendor', displayName: 'Vendor'  }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          ZeroTransactionKiosksService.getUsers(newPage,pageSize,counttype).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);


app.service('ZeroTransactionKiosksService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype,fromDate,toDate) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'zeroTransactionKiosks/get?page='+pageNumber+'&size='+size+'&type='+counttype+'&fromDate='+fromDate+'&toDate='+toDate
         
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);