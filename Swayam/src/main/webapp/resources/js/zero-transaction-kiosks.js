var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('ZeroTransactionKiosksCtrl', ['$scope','$filter','ZeroTransactionKiosksService', function ($scope, $filter,ZeroTransactionKiosksService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   var counttype = "";
   var fromDate = "";
   var toDate= "";
   var currDate= "";
   
   /*
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
	  } */
  
  
   var date = new Date();
   $scope.ddMMyyyy = $filter('date')(new Date(), 'dd-MM-yyyy');
   console.log("ddMMyyyy::"+$scope.ddMMyyyy);
  $scope.CurrentDate = new Date();
	   $scope.searchPositions= function(startDate,endDate){ debugger;
		
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
    	 ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
     			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){ debugger;
     			 console.log("data========",$scope.gridOptions.totalItems);
     			  $scope.allIndiaDate = "From: " +fromDate+" ToDate: "+toDate; 
     			   if(data.totalElements==0){
									/*	$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;*/
										
										 $scope.gridOptions.data = data.content;
									//	  $scope.gridOptions.paginationCurrentPage = paginationOptions.pageNumber;
										  $scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
									   
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
									}
     			  //  Added for loader------------- START 
     			   $("#loading").hide();  
     			// Added for loader------------- EN
     	   });
	}; 
   
    
   $scope.getCountType = function(type){
      
       counttype=type;
       ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){ debugger;
        
				   });
	}
   
   
   $scope.refresh = function()
   {  	debugger;
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
	   		
	   	$("#loading").show();  
	     // Added for loader------------- END
	 	   ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){
	 				   
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	//  Added for loader------------- START 
	 	        $("#loading").hide();  
	 	     // Added for loader------------- END
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		//   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);	
	 	    	  $("#loading").show(); 
	 	    	/* ZeroTransactionKiosksService.getSearchNext(paginationOptions.pageNumber,
			 	  			paginationOptions.pageSize,counttype,fromDate,toDate,$scope.searchText).success(function(data3){
			 	*/ 	  
	 	    	 ZeroTransactionKiosksService.getSearchNext(0,
			 	  			paginationOptions.pageSize,counttype,fromDate,toDate,$scope.searchText).success(function(data3){
			 	
			 	 	  	  $scope.gridOptions.data = data3.content;
			 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
			 	  	      $("#loading").hide();
			 	  	  
			 	 	     }); 
	 		   
	 	    }else{
	 	   	//  Added for loader------------- START 
	 	        $("#loading").show();  
	 	     // Added for loader------------- END
	 	    	ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){
	 	 				 
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 		//  Added for loader------------- START 
	 	         $("#loading").hide();  
	 	      // Added for loader------------- END
	 	 	   });
	 	    }
	    };
debugger;
$scope.clearSearch = function()
{  	
	  
	$scope.searchText='';	
	   
	        $("#loading").show();  
	        
	        ZeroTransactionKiosksService.getUsers(0,
	   			paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){
	  	  $scope.gridOptions.data = data.content;
	  	  $scope.gridOptions.paginationCurrentPage = data.number;
	   	  $scope.gridOptions.totalItems = data.totalElements;
	   	
	        $("#loading").hide();  
	     
	     }); 
	 		   
	 	   
	    };
	//  Added for loader------------- START 
        $("#loading").show();  
     // Added for loader------------- END
   ZeroTransactionKiosksService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){	debugger;
		             $scope.gridOptions.data = data.content;
		     //  	  $scope.gridOptions.paginationCurrentPage = paginationOptions.pageNumber;
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
       /*   { name: 'circleName',width:220, displayName: 'Circle'  },
          { name: 'network',width:150, displayName: 'NW'  },
          { name: 'module',width:250, displayName: 'Mod'  },
          { name: 'region',width:150, displayName: 'Reg'  },
          { name: 'branchCode',width:120, displayName: 'Branch Code'  },
          { name: 'branchName',width:250, displayName: 'Branch Name'  },
          { name: 'kioskId',width:220, displayName: 'Kiosk ID'  },
          { name: 'vendor',width:180, displayName: 'Vendor'  }*/
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
    
          $("#loading").show();  
          if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          ZeroTransactionKiosksService.getUsers(newPage,pageSize,counttype,fromDate,toDate).success(function(data){   debugger;     	  
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
         		//  Added for loader------------- START 
              $("#loading").hide();  
           // Added for loader------------- END
          });
          }
	        else{
	 	 	   //	console.log("Inside else");
	        	ZeroTransactionKiosksService.getSearchNext(newPage,pageSize,counttype,fromDate,toDate,$scope.searchText).success(function(data){
	           	  $scope.gridOptions.data = data.content;
	           	 	  $scope.gridOptions.totalItems = data.totalElements;
	        
		 	 		 $("#loading").hide();  
		 		   
	        	  });	 
	        
	        	   }
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
	function getSearchNext(pageNumber,size,counttype,fromDate,toDate, searchText) { debugger;
		//alert("12= fromdate=="+begin);
		//alert("13=todate=="+end);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
	    return  $http({
	      method: 'GET',
	      url: 'zeroTransactionKiosks/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&fromDate='+fromDate+'&toDate='+toDate+'&searchText='+searchText
	    });
	}
    return {
    	getUsers:getUsers,
    	
    	getSearchNext:getSearchNext
    };
	
}]);