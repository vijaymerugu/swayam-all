//var app = angular.module('app', ['ui.grid','ui.grid.pagination']);
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','$http','$window','UserManagementService', function ($scope, $filter,$http,$window,UserManagementService) 
	{
	
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
		 };
	   
	   
	   var counttype = "";
	   $scope.getCountType = function(type){
	
	counttype=type;
		   UserManagementService.getUsers(paginationOptions.pageNumber,
				   paginationOptions.pageSize,counttype).success(function(data){
					   
						  $scope.gridOptions.data = data.content;
					 	  $scope.gridOptions.totalItems = data.totalElements;
					   });
		}
		
		
		
		 //Load Call Type
	   $scope.LoadCategory=function(){
	  // alert("catogory");
		   $http({
				method : "GET",
				url : 'hm/getCategory',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				$scope.Categorys = data;
				console.log("Category....." + data);
				//$scope.SelectedCategoryId = 0;
				//$scope.CategoryDefaultLabel = "Select Category";
				//$scope.Categorys = data;
				
			}).error(function(data, status) {
				console.log("Unable to load the Category" +  data + " Status " + status);
				$window.alert(data.Message);
			});
	   }
		
    
		
		
		 var counttype = "";
			var fromDate = "";
			var toDate = "";
			var selectedKioskId="";
			var selectedCallLogDateId =""; 
			var selectedCategoryId ="";
			var selectedCircelId="";
			var selectedCallClosedDateId ="";
			var selectedSubCategoryId="";
			var selectedBranchCode="";
			var selectedVendorId="";

		      $scope.LoadDropDown = function() {
			  //    alert("11");
							
				$http({
					method : "GET",
					url : 'hm/getcircle',
					dataType : 'json',
					data : {},
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(data, status) {
				    $scope.Circles = data;
					console.log("Done.....");
					
						//$scope.SelectedCircelId = 0;
						//$scope.CircleDefaultLabel = "Select Circle";
						$scope.Circles = data;
											
						}).error(function(data, status) {
								console.log("error....." + value)
								$window.alert(data.Message);
							});
												
			}; 
		
		 $scope.LoadDropDown('', 0);
      // $scope.LoadYear();
       $scope.LoadCategory();
	   // $scope.LoadDate();
	   

	   
	   $scope.resetPositions=function(){
		        console.log("Inside resetPositions ");
				$scope.kioskId ='';
				$scope.branchCode ='';	
				$scope.SelectedCallLogDateId ='';
		    	$scope.SelectedCategoryId ='';		
		    	$scope.SelectedCircelId =''; 
		    	$scope.SelectedCallClosedDateId ='';
		    	$scope.SelectedSubCategoryId ='';
		    	
		    	$scope.SelectedVendorId ='';
		       }
	   
	   
		
		$scope.searchPositions = function(kioskId,CallLogDateId,CategoryId,CircelId,CallClosedDateId,SubCategoryId,BranchId,VendorId) {
		//alert("kioskId==="+kioskId);
				console.log("CircelId " + CircelId);
				selectedKioskId=kioskId;
				selectedCallLogDateId=CallLogDateId;
				selectedCategoryId=CategoryId;
				selectedCircelId = CircelId;
				selectedCallClosedDateId=CallClosedDateId;
				selectedSubCategoryId=SubCategoryId;
				selectedBranchCode=BranchId;
			    selectedVendorId=VendorId;
				
				//quterTimePeriod=(selectedQuarterId.toUpperCase())+'-'+selectedYearId;
				console.log("selectedKioskId " + selectedKioskId);
				console.log("selectedCallLogDateId " + selectedCallLogDateId);
				console.log("selectedCategoryId " + selectedCategoryId);
			    console.log("selectedCircelId " + selectedCircelId);
			    console.log("selectedCallClosedDateId " + selectedCallClosedDateId);
				console.log("selectedSubCategoryId " + selectedSubCategoryId);
				console.log("selectedBranchCode " + selectedBranchCode);
				console.log("selectedVendorId " + selectedVendorId);
				
				
		       // debugger; 
		        selectedCallLogDateId = $("#datepickerFromDate").val();
		        selectedCallClosedDateId = $("#datepickerToDate").val();
		        
		        var $from=$("#datepickerFromDate").datepicker('getDate');
		       var $to =$("#datepickerToDate").datepicker('getDate');
		        if($from>$to)
		       	{
		        		alert("from date shouldn't greater than To date");
		        		$("#datepickerFromDate").focus();
		        	}

				UserManagementService
						.getUsers(paginationOptions.pageNumber,paginationOptions.pageSize, counttype,selectedKioskId,
						selectedCallLogDateId,selectedCategoryId,selectedCircelId,selectedCallClosedDateId,selectedSubCategoryId,
						selectedBranchCode,selectedVendorId).success(function(data) {
							console.log("data1 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;
						});
			}
			
			
			
		
	   
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize,counttype,selectedKioskId,
						selectedCallLogDateId,selectedCategoryId,selectedCircelId,selectedCallClosedDateId,selectedSubCategoryId,
						selectedBranchCode,selectedVendorId).success(function(data){
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
		 		   
		 	    }else{
		 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
		 	 			   paginationOptions.pageSize,counttype,selectedKioskId,
						selectedCallLogDateId,selectedCategoryId,selectedCircelId,selectedCallClosedDateId,selectedSubCategoryId,
						selectedBranchCode,selectedVendorId).success(function(data){
		 	 		  $scope.gridOptions.data = data.content;
		 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	 	   });
		 	    }
		    };
	   
	   
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,selectedKioskId,
						selectedCallLogDateId,selectedCategoryId,selectedCircelId,selectedCallClosedDateId,selectedSubCategoryId,
						selectedBranchCode,selectedVendorId).success(function(data){
		  $scope.gridOptions.data = data.content;
	 	  $scope.gridOptions.totalItems = data.totalElements;
	   });
	   
	   
	   
	   
	   $scope.gridOptions = {
			    paginationPageSizes: [20, 30, 40],
			    paginationPageSize: paginationOptions.pageSize,
			    enableColumnMenus:false,
				useExternalPagination: true,
				
				    columnDefs: [			   
				     { name: 'kisokId', displayName: 'Kisok Id'  },   
				     { name: 'circle', displayName: 'Circle'  },
				     { name: 'branchCode', displayName: 'Branch Code'  },
				     { name: 'call_log_date',headerCellTemplate: '<div>Call Log<br/>Date</div>',type: 'date',cellFilter: 'date:"yy-mm-dd"'   },  	
				     { name: 'call_closed_date',headerCellTemplate: '<div>Call Closed<br/>Date</div>',type: 'date',cellFilter: 'date:"dd-MM-yyyy"'   },
				     { name: 'vendor', displayName: 'Vendor'  },
				     { name: 'callCategory',headerCellTemplate: '<div>Call<br/>Category</div>'},
				     { name: 'callSubCategory',headerCellTemplate: '<div>Call<br/>Sub Category</div>'}
				    ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;
			          UserManagementService.getUsers(newPage,pageSize,counttype,selectedKioskId,
						selectedCallLogDateId,selectedCategoryId,selectedCircelId,selectedCallClosedDateId,selectedSubCategoryId,
						selectedBranchCode,selectedVendorId).success(function(data){
			        	  $scope.gridOptions.data = data.content;
			         	  $scope.gridOptions.totalItems = data.totalElements;
			          });
			        });
			     }
			  };
	   
	   
	}]);






app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype,selectedKioskId,
						selectedCallLogDateId,selectedCategoryId,selectedCircelId,selectedCallClosedDateId,selectedSubCategoryId,
						selectedBranchCode,selectedVendorId) {
		//alert("selectedKioskId==="+selectedKioskId);
		//alert("selectedBranchId==="+selectedBranchId);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/ticketHistory/get?page='+pageNumber+'&size='+size+'&type='+counttype+'&selectedKioskId='+selectedKioskId
          +'&selectedCallLogDateId='+selectedCallLogDateId+'&selectedCategoryId='+selectedCategoryId
          +'&selectedCircelId='+selectedCircelId +'&selectedCallClosedDateId='+selectedCallClosedDateId
          +'&selectedSubCategoryId='+selectedSubCategoryId
           +'&selectedBranchCode='+selectedBranchCode
           +'&selectedVendorId='+selectedVendorId
        });
    }	
    return {
    	getUsers:getUsers
    };
	
}]);

