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
			var str ='km/assignCmfForKiosk?kioskId=' + url;
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
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,$scope.counttype).success(function(data){ debugger;
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		/*   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		 */  
	 		  
	 	    	$("#loading").show(); 
			 	
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
		   paginationOptions.pageSize,$scope.counttype).success(function(data){ debugger;
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
   });
   
   $scope.gridOptions = {
	/*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,
	
    columnDefs: [
      { name: 'kioskId', displayName: 'Kiosk Id'  },
      { name: 'circle', displayName: 'Circle'  },
      { name: 'branchCode',displayName: 'Branch Code'  },
      { name: 'vendor',  displayName: 'Vendor'  },
      { name: 'kioskSerialNo', displayName: 'Serial No'  }, 
      { name: 'kioskMacAddress',  displayName: 'MAC ID'  },
      { name: 'kioskIp',  displayName: 'IP Address'  },
      { name: 'refId', displayName: 'RFP ID'  },
 /*   Commented temporarily by Manisha
  *   { name: 'installationStatus',  displayName: 'Installation Status'  },*/
      { name: 'username',    	  
    	  displayName: 'Assigned CMF',	  
    	  cellTemplate: '<div ng-if="row.entity.pfId != undefined">{{ row.entity.username }}</div><div ng-if="row.entity.pfId == undefined">Not Assigned</a></div>'
      },
      { name: 'phoneNo', displayName: 'Assigned CMF Phone No' ,	  
    	  cellTemplate: '<div ng-if="row.entity.pfId != undefined">{{ row.entity.phoneNo }}</div><div ng-if="row.entity.pfId == undefined">NA</a></div>'
    		      }
      
    ],
    onRegisterApi: function(gridApi) { debugger;
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          $("#loading").show();  
  	    
	        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          UserManagementService.getUsers(newPage,pageSize,$scope.counttype).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
         	 $("#loading").hide();  
          });
	        }
	        else{debugger;
	 	 	   	console.log("Inside else");
	        	 UserManagementService.getSearchNext(newPage,pageSize,$scope.counttype,$scope.searchText).success(function(data){
	           	  $scope.gridOptions.data = data.content;
	           	 	  $scope.gridOptions.totalItems = data.totalElements;
	        
		 	 		 $("#loading").hide();  
		 		   
	        	  });	 
	        
	        	   }
        });
     }
  };
  
}]);


app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype) { debugger;
		
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'kiosks/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	function getSearchNext(pageNumber,size,counttype, searchText) {
	
		pageNumber = pageNumber > 0?pageNumber - 1:0;
	    return  $http({
	      method: 'GET',
	      url: 'kiosks/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&searchText='+searchText
	    });
	}
    return {
    	getUsers:getUsers,
    	 getSearchNext:getSearchNext
    };
	
}]);