var app = angular.module('app', ['ngRoute','ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);


	app.controller('RfpCtrl', ['$scope','$filter','$http','$window','$route','RfpService','RfpUpdateService',function ($scope, $filter, $http, $window,$route,RfpService,RfpUpdateService) {
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
	   
	   $scope.loadHomeBodyPageForm = function(){	
				var str ='bp/rfpdetail'
				$("#contentHomeApp").load(str);
									
		}
	  
	   $scope.LoadVendor();
	   console.log("Session CSRF Outide  "+ $scope.csrf);
	   
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
	        
	        var status=0;
	        var i=0;
	        var j= 0;
	        var validations=new Array();
	        validations[j]="Failed to Update Null/Invalid data";
	        j++;
	        console.log("Vendor Data  "+ $scope.Vendors);
	        angular.forEach(user, function (value, key) {
	        	console.log("key "+key+" value "+value);
	        	i++;
	        	
	        	if(i==1){
	        		var regvalue = value.match(/^[A-Z]{3}\:[A-Z]{2}\:[A-Z]{3}\:[A-Z]{2}\:\d{2}\-\d{2}\:[0-9]{3}$/);
	        		if(regvalue==null){
	        			validations[j]="Invalid rfp data (valid format eg:SBI:AC:ECR:RB:17-18:972)";
	        			j++;
	        			status=2;
	        		}
	   
	        		
	        		console.log("regvalue" + regvalue);
	        	}else if(i==3){
	        		//var regvalue = value.match(/^[A-Z]$/);
	        		var first = angular.uppercase(value); 
	        		console.log("first "+first);
	        		var flag= false;
	        		var vend =[];
	        		 angular.forEach($scope.Vendors, function (value1, key) {
	        			 console.log("vendorkey "+key+" Vendor value "+value1.vendor);
	        			 var second = angular.uppercase(value1.vendor);
	        			 console.log("second "+second); 
	        			 vend.push(value1.vendor);
	 	        		
	        			 if(angular.equals(first, second)){
	        				 
	        				 
	        				 
	        				 flag = true;
	        				/* validations[j]=first+" Invalid Vendor";
	 	        			j++;
	 	        			status=2;*/
	        			 }
	        			 
	        		  });
	        		 
	        		 console.log("flag "+flag);
	        		
	        		 if(flag == false){
        				 validations[j]=first+" Invalid Vendor (List of valid vendors("+vend+")";
	 	        			j++;
	 	        			status=2;
        			 }
	        		
	        	}else if(i>3){
	        		
	        		 var check = angular.isNumber(value);
				     console.log("Check for integer " + check);
	        		
	        		if(value ==null || value ==undefined || value =='' || check==false){
		        		
	        			if(i==4){
	        				validations[j]="Cost of kiosk data is NULL/Not a number";
			        		j++;	
	        				
	        			}else if(i==5){
	        				validations[j]="Cost of AMC is NULL/Invalid";
			        		j++;
	        				
	        			}else if(i==6){
	        				validations[j]="Complaint Penalty /hr is NULL/Not a number";
			        		j++;
	        				
	        			}else if(i==7){
	        				validations[j]="Permissible Downtime in Metro/urban(in hrs) is NULL/Not a number";
			        		j++;
	        				
	        			}else if(i==8){
	        				validations[j]="Permissible Downtime in Semi-Urban/Rural(in hrs) is NULL/Not a number";
			        		j++;
	        				
	        			}else if(i==9){
	        				validations[j]="Circle Permissible Downtime(in %) is NULL/Not a number";
			        		j++;
	        				
	        			}else if(i==10){
	        				validations[j]="Maximum Penalty(in %) is NULL/Not a number";
			        		j++;
	        				
	        			}
	        			
	        			
	        			
	        			
		        		
		        		status=2;
		        	}else{
		        		
		        		if(i==4){
		        			
		        			if(value>9999.99){
		        				
	        				validations[j]=key+"Cost of kiosk <=9999.99";
			        		j++;
			        		status=2;
		        			}
	        				
	        			}else if(i==5){
	        				if(value>999.99){
		        				
		        				validations[j]=key+"Cost of AMC <=999.99";
				        		j++;
				        		status=2;
			        			}
	        			}else if(i==6){
	        				if(value>999){
		        				
		        				validations[j]=key+"Complaint Penalty /hr <=999";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==7){
	        				if(value>12){
		        				
		        				validations[j]=key+"Permissible Downtime in Metro/urban(in hrs) <=12";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==8){
	        				if(value>12){
		        				
		        				validations[j]=key+"Permissible Downtime in Semi-Urban/Rural(in hrs) <=12";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==9){
	        				if(value>100){
		        				
		        				validations[j]=key+"Circle Permissible Downtime(in %) <=100";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==10){
	        				if(value>100){
		        				
		        				validations[j]=key+"Maximum Penalty(in %) <=100";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}
		        		
		        		
		        	}
		        	
		 
				     
	        		
	        	}
	       	
	        	
	        	
			    
			  
	        	
	        });
	        
	        console.log("status "+status);
	        
	        if(status==0){
	        	
	       
		    
	        var id=row.rfpId;
	        
	        
	        console.log("user RfpNo" + user.rfpNo);
	        console.log("RfpId " + user.rfpId);
	        
	        console.log("Session CSRF "+ $scope.csrf);
	        
	        RfpUpdateService.update(user,$scope.csrf).then(function (d) {
	            
	        	console.log("Inside Success");
	            
	            alert("Updated Successfully");
	            $scope.loadHomeBodyPageForm();
	          //  $window.location.reload(); 
	           // location.reload()
	            
	            
	        }, function (d) {
	        	alert("Failed to update");
	        	$scope.loadHomeBodyPageForm();
	        	//$window.location.reload(); 
	        });
	        
	        
	        }else if(status==1){
	        	
	        	alert("Failed to Update null value");
	        }else if(status==2){
	        	
	        	//alert("Invalid Data Failed to upload");
	        	alert(validations.join("\n"));
	        	$scope.loadHomeBodyPageForm();
	        	
	        }
	    };
	    
	    
	    $scope.resetPositions=function(){
	    	   
	    	$scope.selectedRfpNo='';
	    	$scope.selectedRfpid='';
	    	$scope.selectedVendor='';
	    	$scope.selectedkcost='';
	    	$scope.selectedAMCcost='';
	    	$scope.selectedCPenalty='';
	    	$scope.selectedDMU='';
	    	$scope.selectedDMUR='';
	    	$scope.selectedDCT='';
	    	$scope.selectedMP='';
				
	       }

	    
	    
	    $scope.searchPostion = function (selectedRfpNo,selectedRfpid,selectedVendor,selectedkcost,
				selectedAMCcost,selectedCPenalty,selectedDMU,selectedDMUR,selectedDCT,selectedMP) {
	       
	        
	        var user={rfpNo:selectedRfpNo,rfpId:selectedRfpid,vendor:selectedVendor,kisokCost:selectedkcost,
	        		amcCost:selectedAMCcost	
	        ,companyPenaltyHour:selectedCPenalty,companyPermDntmMuHrs:selectedDMU,
	        companyPermDntmSrHrs:selectedDMUR,companyPermDntmPct:selectedDCT,
	        maxPenaltyPct:selectedMP};	        
	        
	        console.log("user RfpNo" + user.rfpNo);
	        console.log("RfpId " + user.rfpId);
	        console.log("Session CSRF "+ $scope.csrf);
	        RfpUpdateService.addRfp(user,$scope.csrf).then(function (d) {
	            
	        	console.log("Successfully Added " + d.status);
	        	
	        	alert("Successfully Added");
	        	$scope.loadHomeBodyPageForm();
	        	 //$window.location.reload(); 
	        	//location.reload();
	        	/* RfpService.getUsers(paginationOptions.pageNumber,
	     				paginationOptions.pageSize, counttype).success(function(data) {
	     					
	     			$scope.gridOptions.data = data.content;
	     			$scope.gridOptions.totalItems = data.totalElements;
	     			
	     			
	     	   });*/
	       
	        }, function (d) {
	        	alert("Failed to Add");
	        	$scope.loadHomeBodyPageForm();
	        	// $window.location.reload();
	        	 
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
		   		RfpService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype).success(function(data) {
							console.log("data3 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 	    	RfpService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						 $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    	
		 	    	
		 		  	   
		 		   
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
	    	  
	    	  console.log("Session CSRF "+ $scope.csrf);
	    	  
	    	  RfpUpdateService.deleteRFP(user,$scope.csrf).then(function (d) {
		            
		        	console.log("Inside Success ");
		        	
		        	
		        	$scope.gridOptions.data.splice(index, 1);
		        	alert("Successfully deleted");
		        	$scope.loadHomeBodyPageForm();
		        	//$route.reload(); 
		        	//location.reload();
		        	
		        	
		        /*    $scope.alerts.push({
		                msg: 'Data saved successfully',
		                type: 'success'
		            });*/
		        }, function (d) {
		        	alert("failed to delete row");
		        	$scope.loadHomeBodyPageForm();
		        
		        });
	    	  
	    	};
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
	      columnDefs: [
	    	 
	          { name: 'rfpId', displayName: 'RFP Id',field: "rfpId"},
	          { name: 'rfpNo', displayName: 'RFP RefNO.', field: "rfpNo",
	    		  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="text" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
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
                                 '</div>', width: 140
              },
              {
                  name: 'Delete Action', field: 'delete', enableFiltering: false, enableSorting: false,
                  cellTemplate: '<div>'+
                                 '<button class="btn primary" ng-click="grid.appScope.deleteRow(row.entity)"><i class="fa fa-trash"></i></button>' + //delete Button
                                 '</div>', width: 140
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
		 

		    res.update = function(user,header) {
		    	 return $http({
			            method: 'POST',
			            url: 'rf/update',
			            data: user,
			            headers: 
		                {
		                    'X-CSRF-TOKEN':header
		                }
			        });
		    }
		    
		    res.addRfp= function(user,header) {
		    	 return $http({
			            method: 'POST',
			            url: 'rf/add',
			            data: user,
			            headers: 
		                {
		                    'X-CSRF-TOKEN': header
		                }
			        });
		    }
		    
		    
		    res.deleteRFP= function(user,header) {
		    	 return $http({
			            method: 'POST',
			            url: 'rf/delete',
			            data: user,
			            headers: 
		                {
		                    'X-CSRF-TOKEN': header
		                }
			        });
		    }
		    
		    return res;
	 
	});