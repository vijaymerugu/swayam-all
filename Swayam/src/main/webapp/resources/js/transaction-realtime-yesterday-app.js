var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
      
   var yesterdayType = "yesterday";
    $scope.CurrentDate = new Date();
    $scope.showTomorrow = false;
    $scope.date = new Date();   
    $scope.tomorrow = new Date();
    $scope.CurrentDate=($scope.tomorrow.getDate() - 1); 
  var tomorrow = new Date();
 
 // alert(tomorrow);
   console.log("date==="+tomorrow);
   $scope.CurrentDate =tomorrow.setDate(tomorrow.getDate() - 1);
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
  
/*   $scope.refresh = function()
   {  		if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	
		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
	    }else{
	    	
		   $scope.gridOptions.data = $scope.gridOptions.data;
	    }
   };*/
   
   $scope.refresh = function()
   {  	
	  
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	
	   	 //  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
	     //   console.log("pageNumber: "+paginationOptions.pageNumber);
	     //   console.log("pageSize: "+paginationOptions.pageSize);
	   	 UserManagementService.getUsers(paginationOptions.pageNumber,
	   			paginationOptions.pageSize,yesterdayType).success(function(data){
	  	  $scope.gridOptions.data = data.content;
	   	  $scope.gridOptions.totalItems = data.totalElements;
	   	 //  Added for loader------------- START 
	        $("#loading").hide();  
	     // Added for loader------------- END
	     }); 
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	    	
	 	  	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
	 	  			paginationOptions.pageSize,yesterdayType,$scope.searchText).success(function(data3){
	 	 	  		 
	 	 	  	  $scope.gridOptions.data = data3.content;
	 	 //	  	  console.log("Grid data before: "+JSON.stringify($scope.gridOptions.data));
	 	 	//	   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);	
	 	 //		 console.log("search text after: "+$scope.searchText);
	 	 //		 console.log("Grid data after: "+JSON.stringify($scope.gridOptions.data));
	 	 	
	 	 		//$scope.gridOptions.data =$scope.gridOptions.data; 	 	   	
	 	 	//	$scope.gridOptions.totalItems = $scope.gridOptions.data.length;
	 	 	   	  $scope.gridOptions.totalItems = data3.totalElements;
	 	 	
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
		   paginationOptions.pageSize,yesterdayType).success(function(data){
		   
		         $scope.allIndiaDate = "Yesterday: ";                
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
 		//  Added for loader------------- START 
      $("#loading").hide();  
   // Added for loader------------- END
   });
   
   $scope.gridOptions = {
   /* paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,
	
      columnDefs: [{name: 'crclName', displayName: 'Real-time Swayam Transaction'}
    	  ],
    columnDefs: [
      { name: 'crclName',width:150, displayName: 'Circle'  },
      { name: 'network',width:150, displayName: 'NW'  },
      { name: 'module',width:250, displayName: 'Mod'  },
      { name: 'region',width:150, displayName: 'Reg'  },
      { name: 'branchCode',width:150, displayName: 'Branch Code'  },
      { name: 'branchName',width:250, displayName: 'Branch Name'  },
      { name: 'kioskId',width:250, displayName: 'Kiosk Id'  },
      { name: 'vendor',width:150, displayName: 'Vendor'  },
      { name: 'noOfTxns',width:150, displayName: 'Swayam Txns'  },
      /*{field: 'mixedDate', displayName: "Registered On", cellFilter: 'date:"longDate"', 
    	  filterCellFiltered:true }*/
      
     
      
    ],
    onRegisterApi: function(gridApi) { 
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
     
	        $("#loading").show();  
       //   console.log("inside onRegisterApi search Text:" +$scope.searchText);
	        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
	        	console.log("Inside if");
          UserManagementService.getUsers(newPage,pageSize,yesterdayType).success(function(data){
        	  $scope.gridOptions.data = data.content;
        	 	  $scope.gridOptions.totalItems = data.totalElements;
         	
		        $("#loading").hide();  
		    
          });
          }
	        else{
	 	 	   	console.log("Inside else");
	        	 UserManagementService.getSearchNext(newPage,pageSize,yesterdayType,$scope.searchText).success(function(data){
	           	  $scope.gridOptions.data = data.content;
	           	 	  $scope.gridOptions.totalItems = data.totalElements;
	          // 	 	console.log("TotalItems:  "+ $scope.gridOptions.totalItems);	
	           	 	
	 	 	    
	        	
		 	 	//	$scope.gridOptions.totalItems = $scope.gridOptions.data.length;
		 	 	//  	  $scope.gridOptions.totalItems = data.totalElements;
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
    	
    	function getSearch(pageNumber,size,yesterdayType, searchText) {
    		pageNumber = pageNumber > 0?pageNumber - 1:0;
            return  $http({
              method: 'GET',
              url: 'td/realTimeTxn/getSearch?page='+pageNumber+'&size='+size+'&fromdate='+yesterdayType+'&searchText='+searchText
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
        	
        	getSearch:getSearch,
        	
        	getSearchNext:getSearchNext
        };
   
}]);