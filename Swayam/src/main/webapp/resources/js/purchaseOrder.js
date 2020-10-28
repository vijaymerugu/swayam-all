var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate','ui.grid.selection', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('PurchaseOrderCtrl', ['$scope','$filter','$http','$window','PurchaseOrderService','PurchaseCorrectionService',function ($scope, $filter, $http, $window,PurchaseOrderService,PurchaseCorrectionService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };

		   
	   var counttype = "";
			var fromDate = "";
			var toDate = "";
			var quterTimePeriod="";
			var selectedCircelId =""; 
			var selectedStateId ="";
			var selectedQuarterId="";
			var selectedYearId ="";
			var selectedVendorId="";
			var selectedRfpID="";
			var allocationList =[];
			var poIdList =[];
	   
			
			 //Load Vendor
			   $scope.LoadVendor=function(){
				   $http({
						method : "GET",
						url : 'bp/getVendor',
						dataType : 'json',
						data : {},
						headers : {
							"Content-Type" : "application/json"
						}
					}).success(function(data, status){
						$scope.Vendors = data;
						
					}).error(function(data, status) {
						console.log("Unable to load the vendor" +  data + " Status " + status);
					});
				   
			   }
			   
			   $scope.LoadVendor(); 
			   $scope.resetPositions=function(){
		    	   
					 console.log("Inside resetPositions ");
					 	
			    	   	$scope.SelectedVendorId='';
			    	   	
			    	    PurchaseOrderService.getUsers(paginationOptions.pageNumber,
			    				paginationOptions.pageSize, counttype).success(function(data) {
			    					console.log("data " + data);
			    			$scope.gridOptions.data = data.content;
			    			$scope.gridOptions.totalItems = data.totalElements;
			    	   });
						
						
			       }
			   
			   
			   $scope.LoadPOData = function(selectedVendor)
			   {  	
				   	if(selectedVendor ==null || selectedVendor ==undefined || selectedVendor ==''){	   
				   		PurchaseOrderService.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype).success(function(data) {
									console.log("data3 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;

			 	 	   });   
				 		   
				 	    }else if(selectedVendor !=null || selectedVendor !=undefined || selectedVendor !=''){
				 	  
				 	    	PurchaseOrderService.getUsers(paginationOptions.pageNumber,
									paginationOptions.pageSize, counttype).success(function(data) {
										console.log("data3 " + data);
								$scope.gridOptions.data = data.content;
								$scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, selectedVendor);
								$scope.gridOptions.totalItems = data.totalElements;

				 	 	   }); 
				 	    	
				 		   		   
				 		   
				 	    }else{
				 	    	PurchaseOrderService.getUsers(paginationOptions.pageNumber,
									paginationOptions.pageSize, counttype).success(function(data) {
										console.log("data3 " + data);
								$scope.gridOptions.data = data.content;
								$scope.gridOptions.totalItems = data.totalElements;

				 	 	   });
				 	    }
				    };

			
			
	 /*  $scope.refresh = function()
	   {  	
		   	if(selectedVendor ==null || selectedVendor ==undefined || selectedVendor ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize,counttype).success(function(data){
		 				   
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if(selectedVendor !=null || selectedVendor !=undefined || selectedVendor !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, selectedVendor);		   
		 		   
		 	    }else{
		 	    	PurchaseOrderService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    }
		    };*/

		    PurchaseOrderService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype).success(function(data) {
					console.log("data " + data);
			$scope.gridOptions.data = data.content;
			$scope.gridOptions.totalItems = data.totalElements;
	   });
		    
		    
		    $scope.edit = function (row) {
		        
		        var index = $scope.gridOptions.data.indexOf(row);
		        
		        $scope.gridOptions.data[index].editrow = !$scope.gridOptions.data[index].editrow;
		    };

		    
		    $scope.cancelEdit = function (row) {
		        //Get the index of selected row from row object
		        var index = $scope.gridOptions.data.indexOf(row);
		        //Use that to set the editrow attrbute value to false
		        $scope.gridOptions.data[index].editrow = false;
		        //Display Successfull message after save
		        $scope.alerts.push({
		            msg: 'Row editing cancelled',
		            type: 'info'
		        });
		    };
		    
		    
		    $scope.alerts = [];
		    
		    var allocId = 0;
	        var rfpRefNo = '';
	        var vendor = '';
	        var circle = '';
	        var allocatedQuantity = 0;
	        var poQuantity=0;
	        var remainingQuantity=0;
	        var poNumber=0;
	        
	        
	        
		  
		    $scope.getRowsData= function () {
		        var rows = $scope.gridApi.selection.getSelectedRows();
		        console.log("Inside Get Row Data");
		     
		        angular.forEach(rows, function (row, key) {
		        	//console.log("PoNumber "+row.poId);
		        	poIdList.push(row.poId);
		        });
		        
		        
		        console.log("Get Selected Allocation Ids  "+poIdList);
		        
		        
		        PurchaseCorrectionService.generatePDF(poIdList).then(function (d) {
		        	poIdList =[];
		        	console.log("Inside Success " + d.status);
//		        	console.log("Inside data" + d.data);
//		        	console.log("Filename" + d.data.message);
//		        	alert("PDF Generated Successfully");
		        	$window.open("resources/download/"+d.data.message , '_blank');
		        	 PurchaseOrderService.getUsers(paginationOptions.pageNumber,
		     				paginationOptions.pageSize, counttype).success(function(data) {
		     					console.log("data " + data);
		     			$scope.gridOptions.data = data.content;
		     			$scope.gridOptions.totalItems = data.totalElements;
		     	   });
		     		    
		       
		        }, function (d) {
		        	poIdList =[];
		        	console.log("Inside failed " + d.status);
		        	alert("Failed to Generate PDF");
		        });
        	
       
		        
		    };
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
		enableRowSelection: true,
//		selectionRowHeaderWidth: 35,
//		rowHeight: 35,
		
	      columnDefs: [
	    	  { name: 'poId', displayName: 'PO ID' , visible: false },
	    	  { name: 'allocId', displayName: 'Allocation ID' , visible: false },
	    	  { name: 'poNumber', displayName: 'PONUMBER' , visible: false },
	          { name: 'rfpRefNo', displayName: 'RFP_REF_NO' },
	          { name: 'vendor', displayName: 'VENDOR'  },
	          { name: 'circle', displayName: 'CIRCLE'  },
	          { name: 'vendorId', displayName: 'VENDOR_ID' ,visible: false },	         
	          { name: 'crclCode', displayName: 'CRCL_CODE',visible: false },
	          { name: 'address', displayName: 'address' ,visible: false },	         
	          { name: 'contactName', displayName: 'contactName',visible: false },
	          /*{ name: 'allocatedQuantity', displayName: 'ALLOCATION QUANTITY' },*/
	          { name: 'poQuantity', displayName: 'PO Quantity' },
	          { name: 'status', displayName: 'STATUS' },
	          /*{ name: 'remainingQuantity', displayName: 'REMAINING QUANTITY' },*/
	         /* { name: 'poDate', displayName: 'LAST PO ISSUE DATE' }*/
	     
        /*      {
                  name: 'Actions', field: 'edit', enableFiltering: false, enableSorting: false,
                  cellTemplate: '<div><button ng-show="!row.entity.editrow" class="btn primary" ng-click="grid.appScope.edit(row.entity)"><i class="fa fa-edit"></i></button>' +  //Edit Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.saveRow(row.entity)"><i class="fa fa-floppy-o"></i></button>' +//Save Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.cancelEdit(row.entity)"><i class="fa fa-times"></i></button>' + //Cancel Button
                                 '</div>', width: 100
              }*/
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          PurchaseOrderService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype).success(function(data) {
							console.log("data4 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	          });
	        });
	     }
	  };
	  
	}]);


	app.service('PurchaseOrderService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'purchaseorder/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype
	         
	        });
	    }		
	    
	    return {
	    	getUsers:getUsers
	    };

		
	}]);
	
	//Factory
	app.factory('PurchaseCorrectionService', function ($http) {
	    var res = {};
	

	    res.saveCorrection = function (allocId,poNumber,allocatedQuantity,
        		poQuantity,remainingQuantity) {
	    	 return $http({
		            method: 'GET',
		     
		            url: 'po/edit?AllocId='+allocId+'&PoNumber='
		            +poNumber+'&AllocatedQuantity='+allocatedQuantity+'&PoQuantity='
		            +poQuantity+'&RemainingQuantity='+remainingQuantity
		            });
	    }
	    
	    res.generatePDF = function (poIdList) {
	    	 return $http({
		            method: 'GET',		     
		            url: 'po/generate?PoIdList='+poIdList
		           
		            });
	    }
	    
	    
	  /*  res.generateVendorData = function (pageNumber,size,counttype,selectedVendor) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'purchaseorder/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&SelectedVendor'+selectedVendor
	         
	        });
	    }		
	    */
	    
	    
	    
	    
	    return res;
	});