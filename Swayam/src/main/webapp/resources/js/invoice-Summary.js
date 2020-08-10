var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('InvoiceSummearyCtrl', ['$scope','$filter','$http','$window','InvoiceSummaryService',function ($scope, $filter, $http, $window,InvoiceSummaryService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	   //Years Load
	   $scope.LoadYear=function(){
		var year = new Date().getFullYear();
		   //var year = "2020"
	    var range = [];
	    //range.push(year);
	    for (var i = 1; i <100; i++) {
	    	var selectYear = ((year-30) + i);
	    	
	    	var modifiedyear = (selectYear)+"-"+(selectYear+1);
	    	
	        range.push(modifiedyear);
	    }
	    
	    console.log("Range "+ range)
	    $scope.Years = range;
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
					$scope.SelectedCircleId = 0;
					$scope.CircelDefaultLabel = "Loading.....";
					$scope.Circle = null;
					break;
				case "circleId":
                    $scope.SelectedStateId = 0;
                    $scope.StateDefaultLabel = "Loading.....";
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
						$scope.SelectedCircelId = 0;
						$scope.CircleDefaultLabel = "Select Circle";
						$scope.Circles = data;
						break;
					case "circleId":
						$scope.SelectedStateId = 0;
						//$scope.StateDefaultLabel = "";
						if (data.length > 0) {
							$scope.StateDefaultLabel = "Select State";
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
      
       
      //var Year = angular.copy($scope.Years);

      var Circle = angular.copy($scope.Circles);
      var State = angular.copy($scope.States);
       
       $scope.reset = function() {
    	   $scope.Years  =  "";
    	   $scope.Circles= angular.copy(Circle);
    	   $scope.States= angular.copy(State);
    	   $scope.LoadDropDown('', 0);
           $scope.LoadYear();
           
         };
	

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
				}else{
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
				
						

				/*InvoiceSummaryService
						.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,
								selectedCircelId,selectedStateId,
								quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
									console.log("data1 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;
						});*/
			}
	 
	   
	    
	   $scope.getCountType = function(type){
	      
	       counttype=type;
	       InvoiceSummaryService.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype,
					selectedCircelId,selectedStateId,
					quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
						console.log("data2 " + data);
				$scope.gridOptions.data = data.content;
				$scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	   
	/*   $scope.refresh = function()
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
		 	    	InvoiceSummaryService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype,
							selectedCircelId,selectedStateId,
							quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    }
		    };*/

	/*  InvoiceSummaryService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype,
				selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
					console.log("data " + data);
			$scope.gridOptions.data = data.content;
			$scope.gridOptions.totalItems = data.totalElements;
	   });*/
	   
	   $scope.loadHomeBodyPageForms = function(url){	   
			if(url != undefined){	
				var str ='td/drillDownNetwork?circleName='+url+'&fromDate='+fromDate+'&toDate='+toDate;
				$("#contentHomeApp").load(str);
			}						
		}
	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
		headerTemplate: 'km/headerTemplate',
		  superColDefs: [{
	          name: 'front',
	          displayName: ''
	      }, {
	          name: 'InvoiceAmount',
	          displayName: 'Invoice Amount'
	      }, {
	          name: 'Penalty',
	          displayName: 'Penalty'
	      }, {
	          name: 'BilledAmount',
	          displayName: 'Billed Amount'
	      }],
		
	      columnDefs: [
	          { name: 'year', displayName: 'Year' ,superCol: 'front' },
	          { name: 'vendor', displayName: 'Vendor',superCol: 'front' },
	          { name: 'circleName', displayName: 'Circle' ,superCol: 'front' },
	          { name: 'state', displayName: 'State' ,superCol: 'front' },
	          { name: 'q1Im', displayName: 'Q1',superCol: 'InvoiceAmount'  },
	          { name: 'q2Im', displayName: 'Q2',superCol: 'InvoiceAmount'  },
	          { name: 'q3Im', displayName: 'Q3',superCol: 'InvoiceAmount' },
	          { name: 'q4Im', displayName: 'Q4' ,superCol: 'InvoiceAmount'},
	          { name: 'q1P', displayName: 'Q1' ,superCol: 'Penalty' },
	          { name: 'q2P', displayName: 'Q2' ,superCol: 'Penalty' },
	          { name: 'q3P', displayName: 'Q3' ,superCol: 'Penalty' },
	          { name: 'q4P', displayName: 'Q4' ,superCol: 'Penalty' },
	          { name: 'q1Ba', displayName: 'Q1' ,superCol: 'BilledAmount' },
	          { name: 'q2Ba', displayName: 'Q2' ,superCol: 'BilledAmount' },
	          { name: 'q3Ba', displayName: 'Q3' ,superCol: 'BilledAmount' },
	          { name: 'q4Ba', displayName: 'Q4' ,superCol: 'BilledAmount' }
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	         /* InvoiceSummaryService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod,selectedVendorId,selectedRfpID).success(function(data) {
							console.log("data4 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	          });*/
	        });
	     }
	  };
	  
	}]);
	
	
	

	app.service('InvoiceSummaryService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype,selectedCircelId,selectedStateId,
				quterTimePeriod,selectedVendorId,selectedRfpID) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'billingPenalty/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
	          '&quterTimePeriod='+quterTimePeriod+'&selectedVendorId='+selectedVendorId+'&selectedRfpID='+selectedRfpID
	          
	         
	        });
	    }
		

		
	    return {
	    	getUsers:getUsers
	    };
		
	}]);
	
	app.directive('superColWidthUpdate', ['$timeout', function ($timeout) {
	    return {
	      'restrict': 'A',
	          'link': function (scope, element) {
	          var _colId = scope.col.colDef.superCol,
	              _el = jQuery(element);
	          _el.on('resize', function () {
	              _updateSuperColWidth();
	          });
	          var _updateSuperColWidth = function () {
	              $timeout(function () {
	                  var _parentCol = jQuery('.ui-grid-header-cell[col-name="' + _colId + '"]');
	                  var _parentWidth = _parentCol.outerWidth(),
	                      _width = _el.outerWidth();
	                  
	                  if (_parentWidth + 1 >= _width) {
	                    _parentWidth = _parentWidth + _width;
	                  } else {
	                    _parentWidth = _width;
	                  }
	                  
	                  _parentCol.css({
	                      'min-width': _parentWidth + 'px',
	                      'max-width': _parentWidth + 'px',
	                      'text-align': "center"
	                  });
	              }, 0);
	          };
	          _updateSuperColWidth();
	      }
	    };
	  }]);