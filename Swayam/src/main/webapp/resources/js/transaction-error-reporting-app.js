var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
pageSize: 20,
sort: null
   };
   
  
   
   $scope.loadHomeBodyPageForms = function(url){  
if(url != undefined){
var str ='td/noOfErrorsKiosk?kioskId=' + url;
$("#contentHomeApp").load(str);
}
}
   
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
 
    // result= year+"-"+month+"-"+day;
       result= day+"-"+month+"-"+year;
   //  alert("return --result::: "+result);
     return result;
 }*/

  
  
  // $scope.master = {searchDateStart: "John", searchDateEnd: "Doe"};

   $scope.resetForm = function(searchDateStart,searchDateEnd) {
	   
	   var fromDate = document.getElementById("fromDate").value;
	   var toDate = document.getElementById("toDate").value;  
	 //  alert("reset"+toDate);
	   $scope.searchDateStart='';
	   $scope.searchDateEnd='';
	  // var str1 = $("#datepickerFromDate").val();
      // var str2 = $("#datepickerToDate").val();
       //str2="";
       //str1="";
       
      // $scope.reset("","");
   };

   function resetForm(fromInput, toInput) {
		  fromInput.value = '';
		  toInput.value = '';
		}
   
   
   
      $scope.searchPositions= function(startDate,endDate){
    	  
    	  var ss = $("#startDate").val();
    	  //alert("sss==============="+ss);
    	  fromDate = $("#datepickerFromDate").val();
    	  toDate = $("#datepickerToDate").val();
	  
           //alert("From=="+str1);
          // alert("toDate=="+str2);  
     if(fromDate!=null && fromDate!='' &&  toDate!=null && toDate!=''){
			  UserManagementService.getUsers(paginationOptions.pageNumber,
			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
			 $scope.gridOptions.data = data.content;
			 $scope.gridOptions.totalItems = data.totalElements;
			 
			  });
			  
			   }else{
				   UserManagementService.getUsers(paginationOptions.pageNumber,
							  paginationOptions.pageSize,startDate,endDate).success(function(data){
							 $scope.gridOptions.data = data.content;
							 $scope.gridOptions.totalItems = data.totalElements;
							  });
			       }
      }
   
      $scope.refresh = function()
      {  	
   	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
   	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
   	 			   paginationOptions.pageSize,fromDate,toDate).success(function(data){
   	 				
   	 		  $scope.gridOptions.data = data.content;
   	 	 	  $scope.gridOptions.totalItems = data.totalElements;
   	 	   });	   
   	 		   
   	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
   	 	  
   	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
   	 		   
   	 	    }else{
   	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
   	 	 			   paginationOptions.pageSize,fromDate,toDate).success(function(data){
   	 	 				
   	 	 		  $scope.gridOptions.data = data.content;
   	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
   	 	 	   });
   	 	    }
   	    };
   	    
   	    
   	    
   	    UserManagementService.getUsers(paginationOptions.pageNumber,
  paginationOptions.pageSize,fromDate,toDate).success(function(data){
 $scope.gridOptions.data = data.content;
   $scope.gridOptions.totalItems = data.totalElements;
   });
   
   $scope.gridOptions = {
paginationPageSizes: [20, 30, 40],
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
useExternalPagination: true,

    columnDefs: [
    { name: 'crclName', displayName: 'Circle'  },
         { name: 'network', displayName: 'NW'  },
         { name: 'module', displayName: 'Mode'  },
         { name: 'region', displayName: 'Reg'  },
         { name: 'branchCode', displayName: 'Branch Code'},
         { name: 'branchName', displayName: 'Branch Name'  },
         { name: 'kioskId', displayName: 'Kiosk Id'},
         { name: 'vendor', displayName: 'Vendor'}
        ,
         
      { name: 'noOfErrors',      
     displayName: 'No Of Errors',  
     //cellTemplate: '<div ng-if="row.entity.kioskId != undefined">{{ row.entity.noOfError }}</div><div ng-if="row.entity.kioskId == undefined"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.kioskId)">{{ row.entity.noOfError }}</a></div>'
     cellTemplate: '<div ng-if="row.entity.noOfErrors != undefined">{{ row.entity.noOfError }}</div><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.kioskId)">{{row.entity.noOfError}}</a></div>'
      }
       
       
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,fromDate,toDate) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          UserManagementService.getUsers(newPage,pageSize,fromDate,toDate).success(function(data){
         $scope.gridOptions.data = data.content;
           $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
 
}]);

	
	app.service('UserManagementService',['$http', function ($http) {
	//alert(1);
	function getUsers(pageNumber,size,begin,end) {
	//alert("begin==2=="+begin);
	//alert("end==3=="+end);
	pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'td/errorReporting/get?page='+pageNumber+'&size='+size+'&fromdate='+begin+'&todate='+end
	        });
	    }
	
	    return {
	    getUsers:getUsers
	    };
	
	}]);
