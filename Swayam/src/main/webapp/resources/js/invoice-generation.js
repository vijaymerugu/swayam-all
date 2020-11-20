var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('InvoiceGenarationCtrl', ['$scope','$filter','$http','$window','InvoiceGenerationService',function ($scope, $filter, $http, $window,InvoiceGenerationService) {
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
				
				InvoiceGenerationService
				.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							console.log("data1 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;
				});
				
				
	       }
	  /* //Years Load
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
	   }*/
	   
	   
	/*   $scope.LoadYear=function(){
			var year = new Date().getFullYear();
			   //var year = "2020"
		    var range = [];
		    //range.push(year);
		    for (var i = 1; i <100; i++) {
		    	var selectYear = ((year-10) + i);
		    	
		    	var second=selectYear+1;
		    	var modifiedyear = (selectYear)+"-"+(second);
		    	
		        range.push(modifiedyear);
		    }
		    
		    console.log("Range "+ range)
		    $scope.Years = range;
		   }*/
	   
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
                    //$scope.StateDefaultLabel = "Loading.....";
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
							//$scope.StateDefaultLabel = "Select State";
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
       //$scope.LoadYear();
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
				
						

				InvoiceGenerationService
						.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,
								selectedCircelId,selectedStateId,
								quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
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
	       InvoiceGenerationService.getUsers(paginationOptions.pageNumber,
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
		   		InvoiceGenerationService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							console.log("data3 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	    	
		 	   	InvoiceGenerationService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });
		 		   
		 		   
		 	    }else{
		 	    	InvoiceGenerationService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype,
							selectedCircelId,selectedStateId,
							quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    }
		    };

		    InvoiceGenerationService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype,
				selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
					console.log("data " + data);
			$scope.gridOptions.data = data.content;
			$scope.gridOptions.totalItems = data.totalElements;
	   });
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
	      columnDefs: [
	          { name: 'rpfRefNumber', displayName: 'RFP REF_NO.'  },
	          { name: 'vendor', displayName: 'VENDOR' },
	          { name: 'circleName', displayName: 'CIRCLE'  },
	          { name: 'state', displayName: 'STATE'  },
	          { name: 'kisokId', displayName: 'KIOSK ID'  },
	          { name: 'kioskSerialNumber', displayName: 'KIOSK SERIAL NO'  },
	          { name: 'quarterId', displayName: 'TIME PERIOD' },
	          { name: 'spareParts', displayName: 'AMC/SPARE PARTS(A)' },
	          { name: 'penalty', displayName: 'PENALTY(B)' },
	          { name: 'invoiceAmount', displayName: 'INVOICE AMOUNT (C=A-B)' },
	          { name: 'corrections', displayName: 'CORRECTIONS' },
	          { name: 'finalAmount', displayName: 'FINAL AMOUNT(C+D)' }
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          InvoiceGenerationService.getUsers(paginationOptions.pageNumber,
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


	app.service('InvoiceGenerationService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype,selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'invoicegenaration/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
	          '&quterTimePeriod='+quterTimePeriod+'&selectedVendorId='+selectedVendorId+'&selectedRfpID='+selectedRfpID
	         
	        });
	    }
		

		
	    return {
	    	getUsers:getUsers
	    };
		
	}]);