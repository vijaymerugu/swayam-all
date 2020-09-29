var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('InvoiceSummearyCtrl', ['$scope','$filter','$http','$window','InvoiceSummaryService',function ($scope, $filter, $http, $window,InvoiceSummaryService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	
	   
	   $scope.LoadYear=function(){
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
					//$scope.SelectedCircleId = 0;
					//$scope.CircelDefaultLabel = "Loading.....";
					$scope.Circle = null;
					break;
				case "circleId":
                    $scope.SelectedStateId = 0;
        //            $scope.StateDefaultLabel = "Loading.....";
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
						//$scope.SelectedCircelId = 0;
						//$scope.CircleDefaultLabel = "Select Circle";
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
								//$window.alert(data.Message);
							});
							
						}
						break;
					
					}
				}).error(function(data, status) {
					console.log("error2....." + value)
					//$window.alert(data.Message);
				});
			};    
       $scope.LoadDropDown('', 0);
       $scope.LoadYear();
      
       
      //var Year = angular.copy($scope.Years);

       $scope.resetPositions=function(){
    	   
			 console.log("Inside resetPositions ");
			
	    	   	$scope.SelectedCircelId =''; 
	    	   	$scope.SelectedStateId ='';
	    	
	    	   	$scope.SelectedYearId ='';
	    	   	quterTimePeriod='';
	    	   	
	    		InvoiceSummaryService
				.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod).success(function(data) {
							console.log("data1 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;
				});   
	    	  
				
	       }
       
			$scope.searchPositions = function(CircelId,StateId,YearId) {
				
				
				selectedCircelId = CircelId;
				
				//selectedStateId = StateId;
				
				selectedYearId = YearId;
				
				if(typeof StateId === 'undefined') {
					
					selectedStateId= "0";
					console.log("Inside if RfID " + selectedStateId);
				}else if(StateId==''){
					selectedStateId= "0";
					console.log("Inside else if RfID " + selectedStateId);
					
				}
				else{
					selectedStateId = StateId;
					console.log("Inside else RfID " + selectedStateId);
				}
				quterTimePeriod=selectedYearId;
				
				
				console.log("selectedCircelId " + selectedCircelId);
				console.log("selectedStateId " + selectedStateId);
				
				console.log("selectedYearId " + selectedYearId);
				
				
						

				InvoiceSummaryService
						.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,
								selectedCircelId,selectedStateId,
								quterTimePeriod).success(function(data) {
									console.log("data1 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;
						});
			}
	 
	   
	    
	   $scope.getCountType = function(type){
	      
	       counttype=type;
	       InvoiceSummaryService.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype,
					selectedCircelId,selectedStateId,
					selectedVendorId).success(function(data) {
						console.log("data2 " + data);
				$scope.gridOptions.data = data.content;
				$scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		   		InvoiceSummaryService
				.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod).success(function(data) {
							console.log("data1 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;
				});   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	    	
		 	    	InvoiceSummaryService
					.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype,
							selectedCircelId,selectedStateId,
							quterTimePeriod).success(function(data) {
								console.log("data1 " + data);
								$scope.gridOptions.data = data.content;
								$scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
								$scope.gridOptions.totalItems = data.totalElements;
					});
		 		  
		 		   
		 	    }else{
		 	    	InvoiceSummaryService
					.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype,
							selectedCircelId,selectedStateId,
							quterTimePeriod).success(function(data) {
								console.log("data1 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;
					});
		 	    }
		    };

		    InvoiceSummaryService
			.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype,
					selectedCircelId,selectedStateId,
					quterTimePeriod).success(function(data) {
				$scope.gridOptions.data = data.content;
				$scope.gridOptions.totalItems = data.totalElements;
			});
	   

	   
	   $scope.gridOptions = {
	    paginationPageSizes: [20, 30, 40],
	    paginationPageSize: paginationOptions.pageSize,	
		enableColumnMenus:false,
		useExternalPagination: true,
		
		headerTemplate: 'km/headerTemplate',
		  superColDefs: [{
	          name: 'front',
	          displayName: '',
	          
	         
	      }, {
	          name: 'InvoiceAmount',
	          displayName: 'Invoice Amount'
	        	 
	      }, {
	          name: 'Penalty',
	          displayName: 'Penalty'
	        	  
	      }, {
	          name: 'BilledAmount',
	          displayName: 'Billed Amount'
	          
	      }, {
	          name: 'back',
	          displayName: ''
	          
	      }],
		
	      columnDefs: [
	          { name: 'year', displayName: 'Year' ,superCol: 'front' },
	          { name: 'vendor', displayName: 'Vendor',superCol: 'front' },
	          { name: 'circleName', displayName: 'Circle' ,superCol: 'front'  },
	          { name: 'state', displayName: 'State' ,superCol: 'front'  },
	          { name: 'q1Im', displayName: 'Q1   ',superCol: 'InvoiceAmount'   },
	          { name: 'q2Im', displayName: 'Q2   ',superCol: 'InvoiceAmount'   },
	          { name: 'q3Im', displayName: 'Q3   ',superCol: 'InvoiceAmount'  },
	          { name: 'q4Im', displayName: 'Q4   ' ,superCol: 'InvoiceAmount' },
	          { name: 'q1P', displayName: 'Q1    ' ,superCol: 'Penalty'  },
	          { name: 'q2P', displayName: 'Q2    ' ,superCol: 'Penalty'  },
	          { name: 'q3P', displayName: 'Q3    ' ,superCol: 'Penalty'  },
	          { name: 'q4P', displayName: 'Q4    ' ,superCol: 'Penalty'  },
	          { name: 'q1Ba', displayName: 'Q1   ' ,superCol: 'BilledAmount'  },
	          { name: 'q2Ba', displayName: 'Q2   ' ,superCol: 'BilledAmount'  },
	          { name: 'q3Ba', displayName: 'Q3   ' ,superCol: 'BilledAmount'  },
	          { name: 'q4Ba', displayName: 'Q4' ,superCol: 'BilledAmount'  }
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          console.log("selectedCircelId " + selectedCircelId);
				console.log("selectedStateId " + selectedStateId);
				
				console.log("quterTimePeriod " + quterTimePeriod);
				InvoiceSummaryService
				.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						quterTimePeriod).success(function(data) {
							console.log("data4 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;
				});
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
	          url: 'invoiceSummary/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
	          '&quterTimePeriod='+quterTimePeriod
	          
	         
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