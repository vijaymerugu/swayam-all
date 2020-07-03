var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService1', function ($scope, $filter,UserManagementService1) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   
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
 
   
   
   $scope.searchPositions1= function(fromDate_param,toDate_param){
	   //alert(5);
	   //alert("startDate1111=="+fromDate_param);
	   /*convertDate(startDate);
		 var fromDate= convertDate(startDate);
		alert("fromDate=="+fromDate);   
		 var toDate=  convertDate(endDate);
		alert("toDate=="+toDate);   */
     
	
	/*
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,fromDate,toDate).success(function(data){
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				   });*/
	   
   }
	   
  /* $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='td/noOfErrorViews?kioskId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}*/
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	 	   UserManagementService1.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize).success(function(data){
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
	 	    }else{
	 	    	UserManagementService1.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize).success(function(data){
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	   });
	 	    }
	    };

   UserManagementService1.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize).success(function(data){
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
       /*  ,
         
      { name: 'noOfErrors',    	  
    	  displayName: 'No Of Errors', 	  
    	  cellTemplate: '<div ng-if="row.entity.kioskId != undefined">{{ row.entity.noOfError }}</div><div ng-if="row.entity.kioskId == undefined"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.kioskId)">{{ row.entity.noOfError }}</a></div>'
      }*/
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,fromDate_param,toDate_param) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          UserManagementService1.getUsers(newPage,pageSize,fromDate_param,toDate_param).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);


app.service('UserManagementService1',['$http', function ($http) {
	alert(1);
	function getUsers(pageNumber,size,begin,end) {
		alert("begin==2=="+begin);
		alert("end==3=="+end);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'td/errorReporting/get?page='+pageNumber+'&size='+size+'&fromDate_param='+begin+'&toDate_param='+end
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);