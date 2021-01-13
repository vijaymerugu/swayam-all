var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   $scope.BtnClick = function() { 
	     
   }
   
   var yesterdayType = "today";
   $scope.CurrentDate = new Date();
   
   //$scope.date = new Date();  
   
  
   $scope.getCountType = function(yesterday){
	    yesterdayType=yesterday;
	    //  Added for loader------------- START 
	    $("#loading").show();  
	 // Added for loader------------- EN
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,yesterdayType).success(function(data){
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				 	  //  Added for loader------------- START 
				 	   $("#loading").hide();  
				 	// Added for loader------------- EN
				   });
	}
   
   
   var counttype = "";
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			//alert("reltime yest");
			var str ='td/realTimeTransactionYestrday=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
   $scope.loadHomeBodyPageFormsDel = function(url){	  
	   //alert("reltime");
		if(url != undefined){	
			var str ='td/realTimeTransaction=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
  
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	
	   	
	   	 //  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
	   	 UserManagementService.getUsers(paginationOptions.pageNumber,
	  		   paginationOptions.pageSize,yesterdayType).success(function(data){debugger;
	  	  $scope.gridOptions.data = data.content;
	   	  $scope.gridOptions.totalItems = data.totalElements;
	   	 //  Added for loader------------- START 
	        $("#loading").hide();  
	     // Added for loader------------- END
	     }); 
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		 /*  $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		*/   
	 	    	 $("#loading").show(); 
		 	  	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
		 	  			paginationOptions.pageSize,yesterdayType,$scope.searchText).success(function(data3){
		 	 	  		 
		 	 	  	  $scope.gridOptions.data = data3.content;
		 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
		 	  	      $("#loading").hide();
		 	 	     }); 
	 	    }else{
	 	    	 //  Added for loader------------- START 
		        $("#loading").show();  
		     // Added for loader------------- END
	 	    	 UserManagementService.getUsers(paginationOptions.pageNumber,
	 	    			   paginationOptions.pageSize,yesterdayType).success(function(data){
	 	    		  $scope.gridOptions.data = data.content;
	 	    	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	    	 	 //  Added for loader------------- START 
	 			        $("#loading").hide();  
	 			     // Added for loader------------- END
	 	    	   });
	 	    }
	    };

	    //  Added for loader------------- START 
        $("#loading").show();  
     // Added for loader------------- END
   UserManagementService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,yesterdayType).success(function(data){ debugger;
		    $scope.allIndiaDate = "Today: ";
		  /* if(yesterdayType.equals("today")){
		    $scope.allIndiaDate = "Today: ";
		   }else{
		    $scope.allIndiaDate = "Yesterday: ";
		   }*/
		  
		   $scope.gridOptions.data = data.content;
	       $scope.gridOptions.totalItems = data.totalElements;
	       //  Added for loader------------- START 
	        $("#loading").hide();  
	     // Added for loader------------- END
   });
   
   $scope.gridOptions = {
    /*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,
	
      columnDefs: [{name: 'crclName', displayName: 'Real-time Swayam Transaction'}
    	  ],
    columnDefs: [
      { name: 'crclName', displayName: 'Circle'  },
      { name: 'network', displayName: 'NW'  },
      { name: 'module', displayName: 'Mod'  },
      { name: 'region', displayName: 'Reg'  },
      { name: 'branchCode', displayName: 'Branch Code'  },
      { name: 'branchName', displayName: 'Branch Name'  },
      { name: 'kioskId', displayName: 'Kiosk Id'  },
      { name: 'vendor', displayName: 'Vendor'  },
      { name: 'noOfTxns', displayName: 'Swayam Txns'  },
      /*{field: 'mixedDate', displayName: "Registered On", cellFilter: 'date:"longDate"', 
    	  filterCellFiltered:true }*/
      
     
      
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          $("#loading").show();  
          
	        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          UserManagementService.getUsers(newPage,pageSize,yesterdayType).success(function(data){ debugger;
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
         	 //  Added for loader------------- START 
		        $("#loading").hide();  
		     // Added for loader------------- END
          });
	        }
	        else{
	 	 	  // 	console.log("Inside else");
	        	 UserManagementService.getSearchNext(newPage,pageSize,yesterdayType,$scope.searchText).success(function(data){
	           	  $scope.gridOptions.data = data.content;
	           	 	  $scope.gridOptions.totalItems = data.totalElements;
	        
		 	 		 $("#loading").hide();  
		 		   
	        	  });	 
	        
	        	   }
        });
     }
  };
  
}]);


/*app.service('UserManagementService',['$http', function ($http) {
	//alert("123");
	function getUsers(pageNumber,size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: '/td/realTimeTxn/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getUsers:getUsers
    };
    */
    
    
    app.service('UserManagementService',['$http', function ($http) {
    	function getUsers(pageNumber,size,yesterdayType) {
    		pageNumber = pageNumber > 0?pageNumber - 1:0;
            return  $http({
              method: 'GET',
              url: 'td/realTimeTxn/get?page='+pageNumber+'&size='+size+'&fromdate='+yesterdayType
            });
        }
    	function getSearchNext(pageNumber,size,yesterdayType, searchText) {
    		pageNumber = pageNumber > 0?pageNumber - 1:0;
            return  $http({
              method: 'GET',
              url: 'td/realTimeTxn/getSearchNext?page='+pageNumber+'&size='+size+'&fromdate='+yesterdayType+'&searchText='+searchText
            });
        }
    	
        return {
        	getUsers:getUsers,
        	
        	getSearchNext:getSearchNext
        };
   
}]);