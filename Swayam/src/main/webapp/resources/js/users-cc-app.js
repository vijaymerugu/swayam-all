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
   $scope.getCountType = function(type){ debugger;
      
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
   {  	debugger;
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,$scope.counttype).success(function(data){
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		//   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		
	 	   	$("#loading").show(); 
		 	  /*	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
		 	  			paginationOptions.pageSize,fromDate,toDate,$scope.searchText).success(function(data3){*/
	 	  	 UserManagementService.getSearchNext(0,
		 	  			paginationOptions.pageSize,$scope.counttype,$scope.searchText).success(function(data3){ 		 
		 	 	  	  $scope.gridOptions.data = data3.content;
		 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
		 	  	      $("#loading").hide();
		 	 	     });
	 		   
	 	    }else{
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,$scope.counttype).success(function(data){
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	   });
	 	    }
	    };
	    $scope.clearSearch = function()
	    {  	debugger;
	 	  
	    	$scope.searchText='';	
	 	   
	 	        $("#loading").show();  
	 	    
	 	   	 UserManagementService.getUsers(0,
	 	   			paginationOptions.pageSize,$scope.counttype).success(function(data){
	 	  	  $scope.gridOptions.data = data.content;
	 	  	  $scope.gridOptions.paginationCurrentPage = data.number;
	 	   	  $scope.gridOptions.totalItems = data.totalElements;
	 	   	
	 	        $("#loading").hide();  
	 	     
	 	     }); 
	 	 		   
	 	 	   
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
     // { name: 'userId',width:300, displayName: 'Sr No'  },
      { name: 'pfId',width:200, displayName: 'PF ID / User Name'  },
      { name: 'username',width:250, displayName: 'Employee Name'  }, 
      { name: 'role',width:150, displayName: 'Role'  },
      { name: 'mobileNo',width:150, displayName: 'MOBILE NO'  },
      { name: 'mailId',width:300,displayName: 'EMAIL ID'  },
      { name: 'reportingAuthorityName',width:200, displayName: 'Reporting Authority'  }
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
          url: 'users/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	
	function getSearchNext(pageNumber,size,counttype, searchText) {
	
		pageNumber = pageNumber > 0?pageNumber - 1:0;
	    return  $http({
	      method: 'GET',
	      url: 'users/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&searchText='+searchText
	    });
	}
    return {
    	getUsers:getUsers,
    	 getSearchNext:getSearchNext
    };
	
}]);