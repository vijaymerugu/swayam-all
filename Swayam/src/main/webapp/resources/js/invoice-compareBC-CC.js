var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('InvoiceCompareCtrl', ['$scope','$filter','$http','$window','InvoiceCompareService','InvoiceCorrectionService',function ($scope, $filter, $http, $window,InvoiceCompareService,InvoiceCorrectionService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	
	   
	  $scope.yearOptions = [
   	    { name: '2020-2021', value: '2020-2021' }, 
   	    { name: '2021-2022', value: '2021-2022' }, 
   	    { name: '2022-2023', value: '2022-2023' }
   	    ];
   	    
   	    $scope.SelectedYearId = $scope.yearOptions[0].value;
	   
	   $scope.resetPositions=function(){
    	   
			 console.log("Inside resetPositions ");
			 	$scope.SelectedQuarterId='';
	    	   	$scope.SelectedCircelId =''; 
	    	   	$scope.SelectedStateId ='';
	    	   	$scope.SelectedQuarterId='';
	    	   	$scope.SelectedYearId = $scope.yearOptions[0].value;
	    	   	$scope.SelectedVendorId='';
	    	   	$scope.RfpId='';
				selectedRfpID="";
				$scope.SelectedKisokId='';
				$scope.selectedBranch='';
				
				InvoiceCompareService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID,selectedkiokId,selectedbranchCode).success(function(data) {
							console.log("data3 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });  
				
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
			var selectedkiokId="";
			var selectedbranchCode="";

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
								
								$scope.SelectedStateId = "";
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
      // $scope.LoadYear();
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
					QuarterId,YearId,VendorId,RfpId,kiosk,branchcode) {
				console.log("kiosk" + kiosk);
				
				selectedCircelId = CircelId;
				
				//selectedStateId = StateId;
				selectedQuarterId = QuarterId;
				selectedYearId = YearId;
				selectedVendorId= VendorId
				selectedRfpID= RfpId;
				
				selectedkiokId=kiosk;
				selectedbranchCode=branchcode;
				
				
				
				if(typeof kiosk === 'undefined'){
					
					selectedkiokId="";
					
				}
				
				if(typeof branchcode === 'undefined'){
					selectedbranchCode="";
					
				}
				
				
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
				
				
				if(typeof StateId === 'undefined') {
					
					selectedStateId= "0";
					console.log("Inside if RfID " + selectedStateId);
				}else if(StateId==''){
					selectedStateId= "0";
					console.log("Inside else if RfID " + selectedStateId);
					
				}
				else{
					selectedStateId= StateId;
					console.log("Inside else RfID " + selectedStateId);
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
								quterTimePeriod,selectedVendorId,selectedRfpID,selectedkiokId,selectedbranchCode).success(function(data) {
									console.log("Response Data " + data.totalElements);	
									
									if(data.totalElements==0){
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
									}
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
	   
	 /*  
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		   		InvoiceCompareService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							console.log("data3 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });  
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	    	
		 	   	InvoiceCompareService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							console.log("data3 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });
		 		   		   
		 		   
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
		    };*/

		    InvoiceCompareService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype,
				selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID,selectedkiokId,selectedbranchCode).success(function(data) {
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
		      /*  $scope.alerts.push({
		            msg: 'Row editing cancelled',
		            type: 'info'
		        });*/
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

		        
		        var check = angular.isNumber(row.Corrections);
		        console.log("Check for integer " + check);
		        
		        console.log("Check for integer " + row);
		        
		        
		        
		        if(check==true && row.Corrections>=0){
		       
		        Corrections = row.Corrections;
		        remarks = row.Remarks;
		        rpfRefNumber = row.rpfRefNumber;
		        kioskSerialNumber = row.kioskSerialNumber;
		        kisokId = row.kisokId;
		        quarter=row.quarterId;
		        yearid=row.year;
		        console.log("Invoice correction" + Corrections);
		        console.log("Remarks " + remarks);
		      
		        
		        var payload = {};
		        
		        
		        payload = {
		        		rpfRefNumber:row.SelectedSanctionAu,
		        		vendor:row.vendor,
		        		circleName:row.circleName,
		        		state:row.state,
		        		kisokId:row.kisokId,
		        		kioskSerialNumber:row.kioskSerialNumber,
		        		quarterId:row.quarterId,
		        		year:row.year,
		        		invoiceAmountSBI:row.invoiceAmountSBI,
		        		invoiceAmountVendor:row.invoiceAmountVendor,
		        		penaltyAmountSBI:row.penaltyAmountSBI,
		        		penaltyAmountVendor:row.penaltyAmountVendor,
		        		difference:row.difference,
		        		lastcorrectionAmount:row.correctionAmount,
		        		correctionAmount:row.Corrections,
		        		remarks:row.Remarks
		    	   };
		        
		        
		        var encodedString = btoa(JSON.stringify(payload));
		        
		        InvoiceCorrectionService.saveCorrection(encodedString,$scope.csrf).then(function (d) {
		         
		        	console.log("Inside Success " + d);
		        	
		        	alert(d.data.status);
		        
		        }, function (d) {
		        	alert("Failed to save");
		        });
		        
		        
		        

		        //Call the function to save the data to database
		    /*    InvoiceCorrectionService.saveCorrection(Corrections,remarks,rpfRefNumber,
		        		kioskSerialNumber,kisokId,quarter,yearid).then(function (d) {
		         
		        	console.log("Inside Success " + d);
		        	
		        	alert(d.data.message);
		        
		        }, function (d) {
		        	alert("Failed to save");
		        });*/
		        
		        }else{
		        	
		        	alert("Correction must be positive Integer");
		        }
		    };
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
	      columnDefs: [
	    	  { name: 'rpfRefNumber', displayName: 'RFP REF_NO'  , width:180},
	          { name: 'vendor', displayName: 'VENDOR' , width:180},
	          { name: 'circleName', displayName: 'CIRCLE' , width:180 },
	          { name: 'state', displayName: 'STATE' , width:180 },
	          { name: 'kisokId', displayName: 'KIOSK ID' , width:180  },
	          { name: 'kioskSerialNumber', displayName: 'KIOSK SERIAL NO' , width:180 },
	          { name: 'quarterId', displayName: 'TIME PERIOD', width:180 },
	          { name: 'year',  displayName: 'year', visible: false},
	          { name: 'invoiceAmountSBI', displayName: 'INVOICE AMOUNT SBI(A)', width:190 },
	          { name: 'invoiceAmountVendor', displayName: 'INVOICE AMOUNT VENDOR(B)' , width:230},
	          { name: 'penaltyAmountSBI', displayName: 'PENALTY AMOUNT SBI(C)', width:200 },
	          { name: 'penaltyAmountVendor', displayName: 'PENALTY AMOUNT VENDOR(D)', width:240 },
	          { name: 'difference', displayName: 'DIFFERENCE IN PENALTY(C-D)' , width:240},
	          { name: 'correctionAmount', displayName: 'LAST CORRECTION AMT' , width:200}
	         /* ,
	          {
                  name: "Corrections", displayName: "CORRECTIONS", field: "Corrections",
                  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
                	 ' ng-if="row.entity.editrow"><input type="number" min="0"  step="0.01"  style="height:30px" ng-model="MODEL_COL_FIELD"</div>', width: 180
              },
              {
                  name: "Remarks", displayName: "REMARKS", field: "Remarks",
                  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
                	 ' ng-if="row.entity.editrow"><input type="text" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 180
              },
              {
                  name: 'Actions', field: 'edit', enableFiltering: false, enableSorting: false,
                  cellTemplate: '<div><button ng-show="!row.entity.editrow" class="btn primary" ng-click="grid.appScope.edit(row.entity)"><i class="fa fa-edit"></i></button>' +  //Edit Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.saveRow(row.entity)"><i class="fa fa-floppy-o"></i></button>' +//Save Button
                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.cancelEdit(row.entity)"><i class="fa fa-times"></i></button>' + //Cancel Button
                                 '</div>', width: 100
              }
	          */
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          InvoiceCompareService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID,selectedkiokId,selectedbranchCode).success(function(data) {
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
				quterTimePeriod,selectedVendorId,selectedRfpID,selectedKioskId,selectedbranchCode) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'invoiceCompare/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
	          '&quterTimePeriod='+quterTimePeriod+'&selectedVendorId='+selectedVendorId+'&selectedRfpID='+selectedRfpID
	          +'&selectedKioskId='+selectedKioskId+'&selectedbranchCode='+selectedbranchCode
	         
	        });
	    }
		
	
	    
	    return {
	    	getUsers:getUsers,
	    	//saveCorrection:saveCorrection
	    };

		
	}]);
	
	
	

	app.factory('InvoiceCorrectionService', function ($http) {
	    var res = {};

	    res.saveCorrection = function (payload,header) {
	    	 return $http({
		            method: 'POST',
		            url: 'bp/IcInsert',
		            data: payload,
		            headers: 
	                {
	                    'X-CSRF-TOKEN': header
	                }});
	    }
	    return res;
	});
	
	
	
	//Factory
	/*app.factory('InvoiceCorrectionService', function ($http) {
	    var res = {};

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
	});*/