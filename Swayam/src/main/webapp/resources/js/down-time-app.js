//var app = angular.module('app', ['ui.grid','ui.grid.pagination']);
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','$http','$window','UserManagementService', function ($scope, $filter,$http,$window,UserManagementService) 
	{
	
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
		 };
		 
	    var  counttype = "";
	    var selectedCircelId = "";
	    var selectedVendorId = "";
		var selectedCmsCmfId = "";
		var selectedFromDateId="";
		var selectedToDateId =""; 
		
			
		
	   
	   $scope.getCountType = function(type){
	
	counttype=type;
		   UserManagementService.getUsers(paginationOptions.pageNumber,
				   paginationOptions.pageSize,counttype).success(function(data){
					   
						  $scope.gridOptions.data = data.content;
					 	  $scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	    //Load Call Type
	   $scope.LoadDropCmsCmf=function(){
		   $http({
				method : "GET",
				url : 'hm/getCmsCmf',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				$scope.CmsCmfUsers = data;
				
				console.log("CmsCmfUsers....." + data);
				$scope.SelectedCmsCmfUsers = 0;
				$scope.CmsCmfUsersDefaultLabel = "Select CMS/CMS";
			}).error(function(data, status) {
				console.log("Unable to load the CmsCmfUsers" +  data + " Status " + status);
			});
	   }
	   
	    $scope.LoadDropDown = function() {
			   //   alert("11");
							
				$http({
					method : "GET",
					url : 'hm/getBycircle',
					dataType : 'json',
					data : {},
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(data, status) {
				    $scope.Circles = data;
					console.log("Done.....");
					
						$scope.SelectedCircelId = 0;
						$scope.CircleDefaultLabel = "Select Circle";
						$scope.Circles = data;
											
						}).error(function(data, status) {
								console.log("error....." + value)
								$window.alert(data.Message);
							});
												
			}; 
		
		 $scope.LoadDropDown('', 0);
		 $scope.LoadDropCmsCmf();
		 
		 
		 
		//$scope.reset = function(CircelId,VendorId,CmsCmfId,FromDateId,ToDateId) {
		//alert("REST");
		//	$scope.reset = angular.copy();
		//};

		//$scope.reset('');
		
		
		  $scope.resetPositions=function(){
			   console.log("Inside resetPositions ");
	    	   	$scope.SelectedCircelId =''; 
	    	   	$scope.SelectedVendorId ='';
	    	   	$scope.SelectedCmsCmfId ='';
	    	    $scope.SelectedFromDateId ='';
	    	   	$scope.SelectedToDateId ='';
				
	       }
       
	  
		$scope.searchPositions = function(CircelId,VendorId,CmsCmfId,FromDateId,ToDateId) {
			
		     //  alert("CircelId==="+CircelId);
		    //    alert("FromDateId==="+FromDateId);
				console.log("FromDateId " + FromDateId);
				
				//if((FromDateId==null || FromDateId=='undefined') && (ToDateId==null || ToDateId=='undefined')){
				
  		//}else{
  		
			   selectedCircelId = CircelId;
				selectedVendorId=VendorId;
				selectedCmsCmfId=CmsCmfId;
				//selectedToDateId=ToDateId;
				//selectedFromDateId=FromDateId;
				
				selectedFromDateId=FromDateId;
				selectedToDateId=ToDateId;
		//}
				
				
				
			    console.log("selectedCircelId " + selectedCircelId);
			    console.log("selectedVendorId " + selectedVendorId);
				console.log("selectedCmsCmfId " + selectedCmsCmfId);
				console.log("selectedFromDateId " + selectedFromDateId);
				console.log("selectedToDateId " + selectedToDateId);
				
			        debugger; 
			        selectedFromDateId = $("#datepickerFromDate").val();
			        selectedToDateId = $("#datepickerToDate").val();
			            
		
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
		         	}else{
		         	
		         	UserManagementService
						.getUsers(paginationOptions.pageNumber,paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId).success(function(data) {
							console.log("data1 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;
						});
		         	}
		         	
	    	   }
		        
		      //  if(selectedFromDateId!=null && !selectedFromDateId=='' && !selectedFromDateId=="" && !selectedFromDateId=='undefined'){
		    	

				
			}
			
			
		
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId).success(function(data){
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
		 		   
		 	    }else{
		 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
		 	 			   paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId).success(function(data){
		 	 		  $scope.gridOptions.data = data.content;
		 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	 	   });
		 	    }
		    };
	   
	   
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId).success(function(data){
		  $scope.gridOptions.data = data.content;
	 	  $scope.gridOptions.totalItems = data.totalElements;
	   });
	   
	   
	   
	   
	   $scope.gridOptions = {
			    paginationPageSizes: [20, 30, 40],
			    paginationPageSize: paginationOptions.pageSize,
			    enableColumnMenus:false,
				useExternalPagination: true,
				
				    columnDefs: [		
				     { name: 'circle', displayName: 'Circle'  },	
				     { name: 'network', displayName: 'NW'  }, 
				     { name: 'module', displayName: 'Mod'  },  
				     { name: 'branchCode', displayName: 'Branch Code '  },   
				     { name: 'kioskId', displayName: 'Kiosk Id'  },   
				     { name: 'vendor', displayName: 'Vendor'  },
				     { name: 'cmsCmf',displayName: 'CMS/CMF'},
				     { name: 'totalOperatingHours',headerCellTemplate: '<div>Total Oprating<br/>Hours</div>' },  
				     { name: 'totalDowntime',headerCellTemplate: '<div>Total<br/>Downtime</div>' }, 	
				    ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;
			          UserManagementService.getUsers(newPage,pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId).success(function(data){
			        	  $scope.gridOptions.data = data.content;
			         	  $scope.gridOptions.totalItems = data.totalElements;
			          });
			        });
			     }
			  };
	   
	   
	}]);






app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId) {
	//	alert("selectedFromDateId"+selectedFromDateId);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/downtime/get?page='+pageNumber+'&size='+size+'&type='+counttype
          +'&selectedCircelId='+selectedCircelId
          +'&selectedVendorId='+selectedVendorId
          +'&selectedCmsCmfId='+selectedCmsCmfId
          +'&selectedFromDateId='+selectedFromDateId
          +'&selectedToDateId='+selectedToDateId
        });
    }
    return {
    	getUsers:getUsers
    };
	
}]);

