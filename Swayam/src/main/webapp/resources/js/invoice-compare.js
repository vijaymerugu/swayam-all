var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('InvoiceCompareCtrl', ['$scope','$filter','$http','$window','InvoiceCompareService','InvoiceCorrectionService',function ($scope, $filter, $http, $window,InvoiceCompareService,InvoiceCorrectionService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	   $scope.resetPositions=function(){
    	   
			 console.log("Inside resetPositions ");
			 	$scope.SelectedQuarterId='';
	    	   	$scope.SelectedCircelId =''; 
	    	   	$scope.SelectedStateId ='';
	    	   	$scope.SelectedQuarterId='';
	    	   	$scope.SelectedYearId ='';
	    	   	$scope.SelectedVendorId='';
				selectedRfpID="";
				
	       }

	   	   
	   //Years Load
	   $scope.LoadYear=function(){
		var year = new Date().getFullYear();
		   //var year = "2020"
	    var range = [];
	    //range.push(year);
	    for (var i = 1; i <100; i++) {
	    	var selectYear = ((year-30) + i);
	    	var second = ((selectYear+1).toString()).substring(2);
	    	
	    	var modifiedyear = (selectYear)+"-"+(second);
	    	
	        range.push(modifiedyear);
	    }
	    
	    console.log("Range "+ range)
	    $scope.Years = range;
	   }
	   
	   
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
	   $scope.Rfp=function(){
		   $http({
				method : "GET",
				url : 'bp/getRfpId',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				console.log("Sucess"+data)
				$scope.Rfpids = data;
				
			}).error(function(data, status) {
				console.log("Unable to load the RfpId" +  data + " Status " + status);
			});
		   
	   }
				   
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

			$scope.LoadDropDown = function(type, value) {
				switch (type) {
				default:
//					$scope.SelectedCircleId = 0;
//					$scope.CircelDefaultLabel = "Loading.....";
					$scope.Circle = null;
					break;
				case "circleId":
                    $scope.SelectedStateId = 0;
                   // $scope.StateDefaultLabel = "Loading.....";
                    $scope.States = null;
                    break;
				}
				$http({
					method : "GET",
					url : 'bp/getcircle',
					dataType : 'json',
					data : {},
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(data, status) {
					console.log("Done....." + value)
					switch (type) {
					default:
//						$scope.SelectedCircelId = 0;
//						$scope.CircleDefaultLabel = "Select Circle";
						$scope.Circles = data;
						break;
					case "circleId":
						$scope.SelectedStateId = 0;
						//$scope.StateDefaultLabel = "";
						if (data.length > 0) {
//							$scope.StateDefaultLabel = "Select State";
							$http({
								method : "get",
								url : 'bp/getstate',
								dataType : 'json',
								data : {},
								headers : {
									"Content-Type" : "application/json",
									"circleId": value
								}
							}).success(function(data, status) {
								console.log("Done Inside comm/getcities .....")
								$scope.States = data;
								console.log("data...." +data)
							}).error(function(data, status) {
								console.log("error....." + value)
								$window.alert(data.Message);
							});
							
						}
						break;
					
					}
				}).error(function(data, status) {
					$window.alert(data.Message);
				});
			};    
       $scope.LoadDropDown('', 0);
       $scope.LoadYear();
       $scope.LoadVendor();
       $scope.Rfp();
	

  /*     function convertDate(dateParam) {
				var result = "";
				var date = new Date(dateParam);
				var year = date.getFullYear();
				var rawMonth = parseInt(date.getMonth()) + 1;
				var month = rawMonth < 10 ? '0' + rawMonth : rawmonth;
				var rawDay = parseInt(date.getDate());
				var day = rawDay < 10 ? '0' + rawDay : rawDay;
				console.log(year + '-' + month + '-' + day);

				result = day + "-" + month + "-" + year; // alert("return
															// --result:::
															// "+result);
				return result;
			}*/
       
			$scope.searchPositions = function(CircelId,StateId,
					QuarterId,YearId,VendorId,RfpId) {
				console.log("RfpId " + RfpId);
				
				selectedCircelId = CircelId;
				
				selectedStateId = StateId;
				selectedQuarterId = QuarterId;
				selectedYearId = YearId;
				selectedVendorId= VendorId
				selectedRfpID= RfpId;
				if(typeof RfpId === 'undefined') {
					
					selectedRfpID= "1";
					console.log("Inside if RfID " + selectedRfpID);
				}else if(RfpId=='' || RfpId=="0"){
					selectedRfpID= "1";
					console.log("Inside else if RfID " + selectedRfpID);
					
				}
				else{
					selectedRfpID= RfpId;
					console.log("Inside else RfID " + selectedRfpID);
				}	
				
				quterTimePeriod=(selectedQuarterId.toUpperCase())+'-'+selectedYearId;
				console.log("selectedCircelId " + selectedCircelId);
				console.log("selectedStateId " + selectedStateId);
				console.log("selectedQuarterId " + selectedQuarterId);
				console.log("selectedYearId " + selectedYearId);
				console.log("selectedVendorId " + selectedVendorId);
				console.log("quterTimePeid " + quterTimePeriod);
				
						

				InvoiceCompareService
						.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,
								selectedCircelId,selectedStateId,
								quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
									console.log("data1 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;
						});
			}
	 
	   
	    
	   $scope.getCountType = function(type){
	      
	       counttype=type;
	       InvoiceCompareService.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype,
					selectedCircelId,selectedStateId,
					quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
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
		 	    	InvoiceCompareService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype,
							selectedCircelId,selectedStateId,
							quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    }
		    };

		    InvoiceCompareService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype,
				selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
					console.log("data " + data);
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
		    
		    
		    $scope.alerts = [];

		    //Class to hold the customer data
		   /* $scope.Invoice = {
		    		Corrections: 0,
		    		remarks: '',
		    		rpfRefNumber:'',
		    		kisokId:'',
		    		kioskSerialNumber:''
		    };*/
		    
		    var Corrections = 0;
	        var remarks = '';
	        var rpfRefNumber = '';
	        var kioskSerialNumber = '';
	        var kisokId = '';
	        var quarter='';
	        var yearid='';
		    //Function to save the data
		    //Here we pass the row object as parmater, we use this row object to identify  the edited row
		    $scope.saveRow = function (row) {
		        //get the index of selected row 
		        var index = $scope.gridOptions.data.indexOf(row);
		        //Remove the edit mode when user click on Save button
		        $scope.gridOptions.data[index].editrow = false;

		        //Assign the updated value to Customer object
		    /*    $scope.Invoice.Corrections = row.Corrections;
		        $scope.Invoice.remarks = row.Remarks;
		        $scope.Invoice.rpfRefNumber = row.rpfRefNumber;
		        $scope.Invoice.kioskSerialNumber = row.kioskSerialNumber;
		        $scope.Invoice.kisokId = row.kisokId;*/
		        Corrections = row.Corrections;
		        remarks = row.Remarks;
		        rpfRefNumber = row.rpfRefNumber;
		        kioskSerialNumber = row.kioskSerialNumber;
		        kisokId = row.kisokId;
		        quarter=row.quarterId;
		        yearid=row.year;
		        console.log("Invoice correction" + Corrections);
		        console.log("Remarks " + remarks);
		       // alert('Selected Row: '+ $scope.Invoice.rpfRefNumber);

		        //Call the function to save the data to database
		        InvoiceCorrectionService.saveCorrection(Corrections,remarks,rpfRefNumber,
		        		kioskSerialNumber,kisokId,quarter,yearid).then(function (d) {
		            //Display Successfull message after save
		        	console.log("Inside Success " + d);
		        	alert("Data saved successfully");
		            $scope.alerts.push({
		                msg: 'Data saved successfully',
		                type: 'success'
		            });
		        }, function (d) {
		        	alert("Failed to save");
		        });
		    };
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
	      columnDefs: [
	    	  { name: 'rpfRefNumber', displayName: 'RFP REF_NO'  },
	          { name: 'vendor', displayName: 'VENDOR' },
	          { name: 'circleName', displayName: 'CIRCLE'  },
	          { name: 'state', displayName: 'STATE'  },
	          { name: 'kisokId', displayName: 'KIOSK ID'  },
	          { name: 'kioskSerialNumber', displayName: 'KIOSK SERIAL NO'  },
	          { name: 'quarterId', displayName: 'TIME PERIOD' },
	          { name: 'year',  displayName: 'year', visible: false},
	          { name: 'invoiceAmountSBI', displayName: 'INVOICE AMOUNT SBI(A)' },
	          { name: 'invoiceAmountVendor', displayName: 'INVOICE AMOUNT VENDOR(B)' },
	          { name: 'difference', displayName: 'DIFFERENCE(C=A-B)' },
	          
	          
	          {
                  name: "Corrections", displayName: "CORRECTIONS", field: "Corrections",
                  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
                	 ' ng-if="row.entity.editrow"><input type="number" min="0"  step="0.01"  style="height:30px" ng-model="MODEL_COL_FIELD"</div>', width: 140
              },
              {
                  name: "Remarks", displayName: "REMARKS", field: "Remarks",
                  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
                	 ' ng-if="row.entity.editrow"><input type="text" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140
              },
              {
                  name: 'Actions', field: 'edit', enableFiltering: false, enableSorting: false,
                  cellTemplate: '<div><button ng-show="!row.entity.editrow" class="btn primary" ng-click="grid.appScope.edit(row.entity)"><i class="fa fa-edit"></i></button>' +  //Edit Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.saveRow(row.entity)"><i class="fa fa-floppy-o"></i></button>' +//Save Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.cancelEdit(row.entity)"><i class="fa fa-times"></i></button>' + //Cancel Button
                                 '</div>', width: 100
              }
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          InvoiceCompareService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							console.log("data4 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	          });
	        });
	     }
	  };
	  
	}]);


	app.service('InvoiceCompareService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype,selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'invoiceCompare/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
	          '&quterTimePeriod='+quterTimePeriod+'&selectedVendorId='+selectedVendorId+'&selectedRfpID='+selectedRfpID
	          
	         
	        });
	    }
		
		
		  
		
	 
	/*    
	    var res = {};
	    
	    res.SaveCustomer = function ($scope) {
	        return $http({
	            method: 'POST',
	            data: $scope.Invoice,
	            url: 'ic/UpdateCorrection'
	        });
	    }
	    
	    function saveCorrection(Corrections,remarks,rpfRefNumber,
        		kioskSerialNumber,kisokId) {
	        return $http({
	            method: 'GET',
	     
	            url: 'ic/UpdateCorrection?Corrections='+Corrections+'&remarks='
	            +remarks+'&rpfRefNumber='+rpfRefNumber+'&kioskSerialNumber='
	            +kioskSerialNumber+'&kisokId='+kisokId
	        });
	    }*/
	    
	    return {
	    	getUsers:getUsers,
	    	//saveCorrection:saveCorrection
	    };

		
	}]);
	
	//Factory
	app.factory('InvoiceCorrectionService', function ($http) {
	    var res = {};
	  /*  res.GetCustomer = function () {
	        return $http({
	            method: 'GET',
	            dataType: 'jsonp',
	            url: 'api/Customer/GetCustomer'
	        });
	    }*/

	    res.saveCorrection = function (Corrections,remarks,rpfRefNumber,
        		kioskSerialNumber,kisokId,quarter,yearid) {
	    	 return $http({
		            method: 'GET',
		     
		            url: 'ic/UpdateCorrection?Corrections='+Corrections+'&remarks='
		            +remarks+'&rpfRefNumber='+rpfRefNumber+'&kioskSerialNumber='
		            +kioskSerialNumber+'&kisokId='+kisokId+'&quarter='+quarter+'&yearid='+yearid
		        });
	    }
	    return res;
	});