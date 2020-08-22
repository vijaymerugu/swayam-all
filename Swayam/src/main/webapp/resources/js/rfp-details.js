var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('RfpCtrl', ['$scope','$filter','$http','$window','RfpService','RfpUpdateService',function ($scope, $filter, $http, $window,RfpService,RfpUpdateService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	  // var self = this;
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
	    
	   
	   	var rfpNo='';
	   	var rfpId='';
	   	var vendor='';
	   	var kisokCost=0;
	   	var amcCost=0;
	   	var companyPenaltyHour=0;
	   	var companyPermDntmMuHrs=0;
		var companyPermDntmSrHrs=0;
		var companyPermDntmPct=0;
		var maxPenaltyPct=0;
	   
	   $scope.saveRow = function (row) {
	        //get the index of selected row 
	        var index = $scope.gridOptions.data.indexOf(row);
	        
	        $scope.gridOptions.data[index].editrow = false;
	        
	        var user={rfpNo:row.rfpNo,rfpId:row.rfpId,vendor:row.vendor,kisokCost:row.kisokCost,amcCost:row.amcCost	
	        ,companyPenaltyHour:row.companyPenaltyHour,companyPermDntmMuHrs:row.companyPermDntmMuHrs,
	        companyPermDntmSrHrs:row.companyPermDntmSrHrs,companyPermDntmPct:row.companyPermDntmPct,
	        maxPenaltyPct:row.maxPenaltyPct};
		    
	        var id=row.rfpId;
	        
	        
	        console.log("user RfpNo" + user.rfpNo);
	        console.log("RfpId " + user.rfpId);
	      
	        RfpUpdateService.update(user,id).then(function (d) {
	            
	        	console.log("Inside Success");
	            
	            alert("Updated Successfully");
	        }, function (d) {
	        	alert("Failed to update");
	        });
	    };
	    
	    
	    $scope.searchPostion = function (selectedRfpNo,selectedRfpid,selectedVendor,selectedkcost,
				selectedAMCcost,selectedCPenalty,selectedDMU,selectedDMUR,selectedDCT,selectedMP) {
	       
	        
	        var user={rfpNo:selectedRfpNo,rfpId:selectedRfpid,vendor:selectedVendor,kisokCost:selectedkcost,
	        		amcCost:selectedAMCcost	
	        ,companyPenaltyHour:selectedCPenalty,companyPermDntmMuHrs:selectedDMU,
	        companyPermDntmSrHrs:selectedDMUR,companyPermDntmPct:selectedDCT,
	        maxPenaltyPct:selectedMP};	        
	        
	        console.log("user RfpNo" + user.rfpNo);
	        console.log("RfpId " + user.rfpId);
	      
	        RfpUpdateService.addRfp(user).then(function (d) {
	            
	        	console.log("Successfully Added");
	        	alert("Successfully Added");
	        	
	        	 RfpService.getUsers(paginationOptions.pageNumber,
	     				paginationOptions.pageSize, counttype).success(function(data) {
	     					
	     			$scope.gridOptions.data = data.content;
	     			$scope.gridOptions.totalItems = data.totalElements;
	     	   });
	        	
	        	
	        /*    $scope.alerts.push({
	                msg: 'Data saved successfully',
	                type: 'success'
	            });*/
	        }, function (d) {
	        	alert("Failed to Add");
	        });
	    };
	 
	   
	    
	   $scope.getCountType = function(type){
	      
	       counttype=type;
	       RfpService.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype).success(function(data) {
						console.log("data2 " + data);
				$scope.gridOptions.data = data.content;
				$scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize,counttype,fromDate,toDate).success(function(data){
		 				   
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
		 		   
		 	    }else{
		 	    	RfpService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    }
		    };

	  RfpService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype).success(function(data) {
					
			$scope.gridOptions.data = data.content;
			$scope.gridOptions.totalItems = data.totalElements;
	   });
	   
	    $scope.edit = function (row) {
	        //Get the index of selected row from row object
	        var index = $scope.gridOptions.data.indexOf(row);
	        //Use that to set the editrow attrbute value for seleted rows
	        $scope.gridOptions.data[index].editrow = !$scope.gridOptions.data[index].editrow;
	    };

	    //Method to cancel the edit mode in UIGrid
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
	    
	    $scope.deleteRow = function(row) {
	    	  var index = $scope.gridOptions.data.indexOf(row.entity);
	    	  
	    	  var user={rfpNo:row.rfpNo,rfpId:row.rfpId,vendor:row.vendor,kisokCost:row.kisokCost,amcCost:row.amcCost	
	    		        ,companyPenaltyHour:row.companyPenaltyHour,companyPermDntmMuHrs:row.companyPermDntmMuHrs,
	    		        companyPermDntmSrHrs:row.companyPermDntmSrHrs,companyPermDntmPct:row.companyPermDntmPct,
	    		        maxPenaltyPct:row.maxPenaltyPct};
	    	  
	    	  RfpUpdateService.deleteRFP(user).then(function (d) {
		            
		        	console.log("Inside Success ");
		        	
		        	
		        	$scope.gridOptions.data.splice(index, 1);
		        	alert("Succfully deleted");
		        	
		        /*    $scope.alerts.push({
		                msg: 'Data saved successfully',
		                type: 'success'
		            });*/
		        }, function (d) {
		        	alert("failed to delete row");
		        });
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	};
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
	      columnDefs: [
	    	  { name: 'rfpNo', displayName: 'RFP RefNO.', field: "rfpNo" },
	          { name: 'rfpId', displayName: 'RFP Id',field: "rfpId"},
	          { name: 'vendor', displayName: 'Vendor' ,field: "vendor", 
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="text" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'kisokCost', displayName: 'Cost of Kiosk',field: "kisokCost", 
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'amcCost', displayName: 'Cost of Amc' ,field: "amcCost", 
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPenaltyHour', displayName: 'Compaint Penalty/hour' ,field: "companyPenaltyHour", 
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPermDntmMuHrs', displayName: 'Permissible Downtime in Metro/Urban (in hrs)' ,field: "companyPermDntmMuHrs",
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPermDntmSrHrs', displayName: 'Permissible downtime in Semi-Urban/Rural areas (in hrs)' ,field: "companyPermDntmSrHrs",
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPermDntmPct', displayName: 'Circle Permissible Downtime(in %)',field: "companyPermDntmPct",
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'maxPenaltyPct', displayName: 'Maximum Penalty(in %)',field: "maxPenaltyPct",	  
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140
	          },
	          {
                  name: 'Actions', field: 'edit', enableFiltering: false, enableSorting: false,
                  cellTemplate: '<div><button ng-show="!row.entity.editrow" class="btn primary" ng-click="grid.appScope.edit(row.entity)"><i class="fa fa-edit"></i></button>' +  //Edit Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.saveRow(row.entity)"><i class="fa fa-floppy-o"></i></button>' +//Save Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.cancelEdit(row.entity)"><i class="fa fa-times"></i></button>' + //Cancel Button
                                 '</div>', width: 100
              },
              {
                  name: 'Delete Action', field: 'delete', enableFiltering: false, enableSorting: false,
                  cellTemplate: '<div>'+
                                 '<button class="btn primary" ng-click="grid.appScope.deleteRow(row.entity)"><i class="fa fa-trash"></i></button>' + //delete Button
                                 '</div>', width: 100
              }
	          
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          RfpService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype).success(function(data) {
							console.log("data4 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	          });
	        });
	     }
	  };
	  
	}]);


	app.service('RfpService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'rfpDetails/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype
	         
	        });
	    }
		

		
	    return {
	    	getUsers:getUsers
	    };
		
	}]);
	
	app.factory('RfpUpdateService', function ($http) {
	    var res = {};
	   // var add={};
		 

		    res.update = function(user,id) {
		    	 return $http({
			            method: 'POST',
			            url: 'rf/add',
			            data: user
			        });
		    }
		    
		    res.addRfp= function(user) {
		    	 return $http({
			            method: 'POST',
			            url: 'rf/update',
			            data: user
			        });
		    }
		    
		    
		    res.deleteRFP= function(user) {
		    	 return $http({
			            method: 'POST',
			            url: 'rf/delete',
			            data: user
			        });
		    }
		    
		    return res;
	 
	});