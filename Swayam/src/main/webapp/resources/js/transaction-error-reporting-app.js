var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
     var fromDate = "";
     var toDate= "";
  /* function convertDate(dateParam){
	   var result="";
	   var date = new Date(dateParam);
       var year = date.getFullYear();
       var rawMonth = parseInt(date.getMonth()) + 1;
       var month = rawMonth < 10 ? '0' + rawMonth : rawmonth;
       var rawDay = parseInt(date.getDate());
       var day = rawDay < 10 ? '0' + rawDay : rawDay; 
       console.log(year + '-' + month + '-' + day);
	   
	      result= year+"-"+month+"-"+day;
	      alert("return --result::: "+result);
	      return result;
	  }
   */
 
   
	   $scope.loadHomeBodyPageForms = function(url){  
	       if(url != undefined){
	           var str ='td/noOfErrorsKiosk?kioskId=' + url;
	           $("#contentHomeApp").load(str);
	         }
	   };
   
     $scope.CurrentDate = new Date();
      $scope.searchPositions= function(startDate,endDate){
    	  
    	
    	  fromDate = $("#datepickerFromDate").val();
    	  toDate = $("#datepickerToDate").val();
	     
    
	        var $from=$("#datepickerFromDate").datepicker('getDate');
	        var $to =$("#datepickerToDate").datepicker('getDate');
	        if (($from== null) || ($to== null) )
	 	   {
	 	   
	 	       if($from== null)
	 	      	{
	 	      		alert("Please enter from date!!!");
	 	      		$("#datepickerFromDate").focus();
	 	      	}
	 	       if($to== null)
	 	     	{
	 	     		alert("Please enter to date!!!");
	 	     		$("#datepickerToDate").focus();
	 	     	}
	    		}
	        else
	     	  {
	 	    	   if($from>$to)
	 	    	   {
	 	         		alert("from date shouldn't greater than To date");
	 	         		$("#datepickerFromDate").focus();
	 	         	}
	     	   }
	        //  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
				   UserManagementService.getUsers(paginationOptions.pageNumber,
							  paginationOptions.pageSize,fromDate,toDate).success(function(data){
								if(data.totalElements==0){
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										 
									}
							//  Added for loader------------- START 
						        $("#loading").hide();  
						     // Added for loader------------- END
							  });
			      
      };
     
      
      $scope.refresh = function()
      {  	
   	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	
   	   	 //  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
   	   		UserManagementService.getUsers(paginationOptions.pageNumber,
   	   			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
   	   			 $scope.gridOptions.data = data.content;
   	   			   $scope.gridOptions.totalItems = data.totalElements;
   	   		 //  Added for loader------------- START 
   		        $("#loading").hide();  
   		     // Added for loader------------- END
   	   			   });   
   	 		   
   	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
   	 	  
   	 		//   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
   	 	    $("#loading").show(); 
	 	  	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
	 	  			paginationOptions.pageSize,fromDate,toDate,$scope.searchText).success(function(data3){
	 	 	  		 
	 	 	  	  $scope.gridOptions.data = data3.content;
	 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
	 	  	      $("#loading").hide();
	 	  	  
	 	 	     }); 
   	 	    }else{
   	 	    //  Added for loader------------- START 
		        $("#loading").show();  
		     // Added for loader------------- END
   	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
   	 	    			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
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
     paginationOptions.pageSize,fromDate,toDate).success(function(data){
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
	

	 columnDefs: [
    	 { name: 'crclName', displayName: 'Circle'  },
         { name: 'network', displayName: 'NW'  },
         { name: 'module' ,displayName: 'Mode'  },
         { name: 'region', displayName: 'Reg'  },
         { name: 'branchCode', displayName: 'Branch Code'},
         { name: 'branchName', displayName: 'Branch Name'  },
         { name: 'kioskId', displayName: 'Kiosk Id'},
         { name: 'vendor', displayName: 'Vendor'},
         { name: 'noOfErrors', displayName: 'No Of Errors',  
          //cellTemplate: '<div ng-if="row.entity.kioskId != undefined">{{ row.entity.noOfError }}</div><div ng-if="row.entity.kioskId == undefined"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.kioskId)">{{ row.entity.noOfError }}</a></div>'
          cellTemplate: '<div ng-if="row.entity.noOfErrors != undefined"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.kioskId)">{{row.entity.noOfErrors}}</a></div>'
        }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
         
	        $("#loading").show();  
	        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          UserManagementService.getUsers(newPage,pageSize,fromDate,toDate).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
         	 //  Added for loader------------- START 
         	    $("#loading").hide();  
    		    
          });
          }
	        else{
	 	 	   	console.log("Inside else");
	        	 UserManagementService.getSearchNext(newPage,pageSize,fromDate,toDate,$scope.searchText).success(function(data){
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
	//alert(1);
	function getUsers(pageNumber,size,fromDate,toDate) {
		//alert("fromDate==2=="+fromDate);
		//alert("toDate==3=="+toDate);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'td/errorReporting/get?page='+pageNumber+'&size='+size+'&fromDate='+fromDate+'&toDate='+toDate
        });
    }
	function getSearchNext(pageNumber,size,fromDate,toDate, searchText) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'td/errorReporting/getSearchNext?page='+pageNumber+'&size='+size+'&fromDate='+fromDate+'&toDate='+toDate +'&searchText='+searchText
        });
    }
    return {
    	getUsers:getUsers,
    	
    	getSearchNext:getSearchNext
    };
	
}]);