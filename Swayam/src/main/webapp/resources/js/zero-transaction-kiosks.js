var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('ZeroTransactionKiosksCtrl', ['$scope','$filter','ZeroTransactionKiosksService', function ($scope, $filter,ZeroTransactionKiosksService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
   var counttype = "";
   var fromDate = "";
   var toDate= "";
   
   function convertDate(dateParam){
	   var result="";
	   var date = new Date(dateParam);
       var year = date.getFullYear();
       var rawMonth = parseInt(date.getMonth()) + 1;
       var month = rawMonth < 10 ? '0' + rawMonth : rawmonth;
       var rawDay = parseInt(date.getDate());
       var day = rawDay < 10 ? '0' + rawDay : rawDay; 
       console.log(year + '-' + month + '-' + day);
	   
       result= day+"-"+month+"-"+year;
	    //  alert("return --result::: "+result);
	      return result;
	  }
  
   $scope.searchPositions= function(startDate,endDate){
	 	  fromDate= convertDate(startDate);
		// alert("fromDate=="+fromDate);   
		  toDate=  convertDate(endDate);	 
    	 
    	 ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
     			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){     				  
     		  $scope.gridOptions.data = data.content;
     	 	  $scope.gridOptions.totalItems = data.totalElements;
     	   });
	} 
   
    
   $scope.getCountType = function(type){
      
       counttype=type;
       ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){				   
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				   });
	}
   
   
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){
	 				   alert(1);
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
	 	    }else{
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){
	 	 				 alert(2);
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	   });
	 	    }
	    };

   ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){			   
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
   });
   
   $scope.gridOptions = {
    paginationPageSizes: [5, 10, 15],
    paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,
	
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
          ZeroTransactionKiosksService.getUsers(newPage,pageSize,counttype,fromDate,toDate).success(function(data){        	  
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