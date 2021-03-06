//var app = angular.module('app', ['ui.grid','ui.grid.pagination']);
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl1', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) 
	{
	
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
		 };
	   
	   
	   var counttype = "";
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
	   
	   
	/*   $scope.refresh = function()
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
	   
*/	   



   
	   $scope.refresh = function()
	   {  
	  if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
	  //  Added for loader------------- START
	       $("#loading").show();  
	    // Added for loader------------- END
	  UserManagementService.getUsers(paginationOptions.pageNumber,
	   paginationOptions.pageSize,counttype).success(function(data){
	  $scope.gridOptions.data = data.content;
	    $scope.gridOptions.totalItems = data.totalElements;
	  //  Added for loader------------- START
	       $("#loading").hide();  
	    // Added for loader------------- END
	    });  
	 
	   }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){debugger;
	 
	 /* $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText); */  
	   
	    $("#loading").show();
	 /* UserManagementService.getSearchNext(paginationOptions.pageNumber,
	  paginationOptions.pageSize,fromDate,toDate,$scope.searchText).success(function(data3){*/
	  UserManagementService.getSearchNext(0,
	  paginationOptions.pageSize,counttype,$scope.searchText).success(function(data3){
	   $scope.gridOptions.data = data3.content;
	     $scope.gridOptions.totalItems = data3.totalElements;
	       $("#loading").hide();
	    });
	 
	   }else{
	  //  Added for loader------------- START
	       $("#loading").show();  
	    // Added for loader------------- END
	    UserManagementService.getUsers(paginationOptions.pageNumber,
	     paginationOptions.pageSize,counttype).success(function(data){
	    $scope.gridOptions.data = data.content;
	      $scope.gridOptions.totalItems = data.totalElements;
	    //  Added for loader------------- START
	       $("#loading").hide();  
	    // Added for loader------------- END
	      });
	   }
	   };
	   $scope.clearSearch = function()
	   {   debugger;
	 
	    $scope.searchText='';
	 
	       $("#loading").show();  
	   
	  UserManagementService.getUsers(0,
	  paginationOptions.pageSize,counttype).success(function(data){
	   $scope.gridOptions.data = data.content;
	   $scope.gridOptions.paginationCurrentPage = data.number;
	   $scope.gridOptions.totalItems = data.totalElements;
	 
	       $("#loading").hide();  
	   
	    });
	 
	 
	   };
	//  Added for loader------------- START
	        $("#loading").show();  
	     // Added for loader------------- END
	   UserManagementService.getUsers(paginationOptions.pageNumber,
	    paginationOptions.pageSize,counttype).success(function(data){
	 
	   $scope.gridOptions.data = data.content;
	   $scope.gridOptions.totalItems = data.totalElements;
	// Added for loader------------- START
	   $("#loading").hide();  
	// Added for loader------------- END
	   });
	
	   
	   
	   $scope.gridOptions = {
				/*paginationPageSizes: [20, 30, 40],*/
			    paginationPageSize: paginationOptions.pageSize,
			    enableColumnMenus:false,
				useExternalPagination: true,
				
			    columnDefs: [
			      { name: 'vendor', displayName: 'Vendor'  },
			      { name: 'ticketId', displayName: 'Ticket Id' },
			      { name: 'kisokId', displayName: 'KioskId'  },
			      { name: 'branchCode', displayName: 'Branch Code'  },
			      { name: 'serveriry', displayName: 'Circle'  },
			      { name: 'callCategory',headerCellTemplate: '<div>Call<br/>Category</div>'},
			      { name: 'cms_cmf_assigned',headerCellTemplate: '<div>CMS/CMF<br/>Assigned</div>'  },
			      { name: 'call_log_date',headerCellTemplate: '<div>Call Log<br/>Date</div>',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'   },
			      { name: 'ageing',  displayName: 'Ageing Hours'},
			      { name: 'statusOfComplaint',headerCellTemplate: '<div>Status of<br/>Complaint</div>'},
			      { name: 'assigned_to_FE', headerCellTemplate: '<div>Assigned<br/>to FE</div>'}
			      
			     
			    ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;

			          //  Added for loader------------- START 
				        $("#loading").show();  
				     // Added for loader------------- END
				        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
			          UserManagementService.getUsers(newPage,pageSize,$scope.counttype).success(function(data){
			         $scope.gridOptions.data = data.content;
			           $scope.gridOptions.totalItems = data.totalElements;
			       //  Added for loader------------- START 
				        $("#loading").hide();  
				     // Added for loader------------- END
			          });
			        }else{
			 	 	   	console.log("Inside else"+$scope.counttype);
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
	
	function getUsers(pageNumber,size,counttype) {
		//alert("counttype= searchText=="+counttype);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/ticketCentorByCircle/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
    
    function getSearchNext(pageNumber,size,counttype, searchText) {
		//alert("counttype= searchText=="+counttype);
		//alert("13=searchText=="+searchText);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
	    return  $http({
	      method: 'GET',
	      url: 'hm/ticketCentorFilterCUSearch/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&searchText='+searchText
	    });
	}
    
    
    return {
    	getUsers:getUsers, getSearchNext:getSearchNext
    };
	
}]);

