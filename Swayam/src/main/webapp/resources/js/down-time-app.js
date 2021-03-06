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
		var selectedKioskId="";
		var selectedBranchCodeId="";
		
			
		
	   
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
					
					//	$scope.SelectedCircelId = 0;
						//$scope.CircleDefaultLabel = "Select Circle";
						//$scope.Circles = data;
											
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
		
		
		  function stringToDate(_date,_format,_delimiter) {
			        var formatLowerCase=_format.toLowerCase();
			        var formatItems=formatLowerCase.split(_delimiter);
			        var dateItems=_date.split(_delimiter);
			        var monthIndex=formatItems.indexOf("mm");
			        var dayIndex=formatItems.indexOf("dd");
			        var yearIndex=formatItems.indexOf("yyyy");
			        var year = parseInt(dateItems[yearIndex]); 
			        // adjust for 2 digit year
			        if (year < 100) { year += 2000; }
			        var month=parseInt(dateItems[monthIndex]);
			        month-=1;
			        var formatedDate = new Date(year,month,dateItems[dayIndex]);
			        return formatedDate;
			}
			  $scope.resetPositions=function(){
				   console.log("Inside resetPositions "+$scope.SelectedBranchCodeId);
				   $scope.selectedCircelId =''; 
				   $scope.SelectedCircelId =''; 
				   $scope.selectedVendorId ='';
				   $scope.SelectedVendorId ='';
				   $scope.selectedCmsCmf ='';
				   $scope.SelectedCmsCmf ='';
				   $scope.selectedFromDateId ='';
				   $scope.selectedToDateId ='';
				   $scope.SelectedBranchCodeId = '';
				 //  $scope.selectedBranchCodeId = '';
		           $scope.SelectedKioskId ='';
		    	   $scope.LoadDropDown('', 0);
		   		    $scope.LoadDropCmsCmf();
		   		    loadGrid();
		       }
       
	  
		$scope.searchPositions = function(CircelId,VendorId,CmsCmfId,FromDateId,ToDateId,BranchCodeId,KioskId) {
			debugger;
		     //  alert("CircelId==="+CircelId);
		    //    alert("FromDateId==="+FromDateId);
				console.log("FromDateId " + FromDateId);
				
				//if((FromDateId==null || FromDateId=='undefined') && (ToDateId==null || ToDateId=='undefined')){
				
  	                $scope.selectedCircelId = CircelId;
					$scope.selectedVendorId=VendorId;
					$scope.selectedCmsCmfId=CmsCmfId;
					//selectedToDateId=ToDateId;
					//selectedFromDateId=FromDateId;
					
					$scope.selectedFromDateId=FromDateId;
					$scope.selectedToDateId=ToDateId;
					$scope.selectedBranchCodeId=BranchCodeId;
		            $scope.selectedKioskId=KioskId;
		
			//}
					
					
					
				    console.log("selectedCircelId " + $scope.selectedCircelId);
				    console.log("selectedVendorId " + $scope.selectedVendorId);
					console.log("selectedCmsCmfId " + $scope.selectedCmsCmfId);
					console.log("selectedFromDateId " + $scope.selectedFromDateId);
					console.log("selectedToDateId " + $scope.selectedToDateId);
					console.log("selectedBranchCodeId "+$scope.selectedBranchCodeId);
					console.log("selectedKioskId " + $scope.selectedKioskId);
					
					if($('#datepickerFromDate').val() == '' || $('#datepickerToDate').val() == '') {
						alert('From date & To date can not be null.');
					} else {
						
					var fromDate = stringToDate($('#datepickerFromDate').val(), 'dd-mm-yyyy', '-');
					var toDate = stringToDate($('#datepickerToDate').val(), 'dd-mm-yyyy', '-');
					
					if (fromDate > toDate){
						alert('From date must be smaller than To date.');
					} else {
			    	   
			         	UserManagementService
							.getUsers(paginationOptions.pageNumber,paginationOptions.pageSize, $scope.counttype,$scope.selectedCircelId,
									$scope.selectedVendorId,$scope.selectedCmsCmfId,$('#datepickerFromDate').val(),$('#datepickerToDate').val(),
									$scope.selectedBranchCodeId,$scope.selectedKioskId).success(function(data) {
								console.log("data1 " + data);
								$scope.gridOptions.data = data.content;
								$scope.gridOptions.totalItems = data.totalElements;
							});
			         	}
			         	
		    	   }
			        
			      //  if(selectedFromDateId!=null && !selectedFromDateId=='' && !selectedFromDateId=="" && !selectedFromDateId=='undefined'){
			    	

					
				}
			
			//loadGrid();
			function loadGrid(){
				   console.log("loadGrid:::", paginationOptions.pageNumber,
						   paginationOptions.pageSize,  $scope.counttype, $scope.selectedCircelId,
						   $scope.selectedVendorId, $scope.selectedCmsCmfId, $scope.selectedFromDateId, $scope.selectedToDateId,$scope.selectedBranchCodeId, $scope.selectedKioskId);
				  UserManagementService.getUsers( paginationOptions.pageNumber,
						  paginationOptions.pageSize,  $scope.counttype, $scope.selectedCircelId,
						  $scope.selectedVendorId, $scope.selectedCmsCmfId, $scope.selectedFromDateId,$scope.selectedBranchCodeId, $scope.selectedKioskId).success(function(data){
					  $scope.gridOptions.data = data.content;
					  $scope.gridOptions.totalItems = data.totalElements;
				});
			  }
				
			
			

			
			
		
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId,selectedBranchCodeId,selectedKioskId).success(function(data){
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
		 		   
		 	    }else{
		 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
		 	 			   paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId,selectedBranchCodeId,selectedKioskId).success(function(data){
		 	 		  $scope.gridOptions.data = data.content;
		 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	 	   });
		 	    }
		    };
	   
	   
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId,$scope.selectedBranchCodeId, $scope.selectedKioskId).success(function(data){
		  $scope.gridOptions.data = data.content;
	 	  $scope.gridOptions.totalItems = data.totalElements;
	   });
	   
	   
	   
	   
	   $scope.gridOptions = {
			   /* paginationPageSizes: [20, 30, 40],*/
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
				     { name: 'totalOperatingHours',headerCellTemplate: '<div>Branch Operating <br/>Hours</div>' },  
				     { name: 'totalDowntime',headerCellTemplate: '<div>Total Downtime<br/> in Hours</div>' }, 	
	                 { name: 'uptimePercentage',displayName: 'Uptime %'},			   
	                 ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;
			           selectedFromDateId=$('#datepickerFromDate').val();
			          selectedToDateId=$('#datepickerToDate').val();
			          UserManagementService.getUsers(newPage,pageSize, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId,selectedBranchCodeId,selectedKioskId).success(function(data){
			        	  $scope.gridOptions.data = data.content;
			         	  $scope.gridOptions.totalItems = data.totalElements;
			          });
			        });
			     }
			  };
	   
	   
	}]);






app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size, counttype,selectedCircelId,
						selectedVendorId,selectedCmsCmfId,selectedFromDateId,selectedToDateId,selectedBranchCodeId,selectedKioskId) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/downtime/get?page='+pageNumber+'&size='+size+'&type='+counttype
          +'&selectedCircelId='+selectedCircelId
          +'&selectedVendorId='+selectedVendorId
          +'&selectedCmsCmfId='+selectedCmsCmfId
          +'&selectedFromDateId='+selectedFromDateId
          +'&selectedToDateId='+selectedToDateId
          +'&selectedBranchCodeId='+selectedBranchCodeId
          +'&selectedKioskId='+selectedKioskId
          
        });
    }
    return {
    	getUsers:getUsers
    };
	
}]);

