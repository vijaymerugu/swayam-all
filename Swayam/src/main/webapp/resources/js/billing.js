var app = angular.module('app', [ 'ui.grid', 'ui.grid.pagination', 'ngAnimate',
		'ngTouch', 'ui.grid.exporter', 'ui.grid.resizeColumns' ]);

app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

app.controller(
				'BillingCtrl',
				[
						'$scope',
						'$filter',
						'BillingService',
						'$http',
						'EditService',
						function($scope, $filter, BillingService, $http,EditService) {
							
							
							
							 $scope.loadHomeBodyPageForm = function(){	
									var str ='bp/allocation'
									$("#contentHomeApp").load(str);
														
							}

							$scope.doUploadFile = function() {
				
								var file = $scope.uploadedFile;
								var url = "billingallocation";
								//alert("file" + file);
								 console.log("Session CSRF "+ $scope.csrf);
								//console.log("Header csrf "+ header);

								var data = new FormData();
								data.append('uploadfile', file);

								var config = {
									transformRequest : angular.identity,
									transformResponse : angular.identity,
									headers : {
										'X-CSRF-TOKEN': $scope.csrf,
										'Content-Type' : undefined
									}
							
								}
								$http.post(url, data, config)
										.then(
												function(response) {
													$scope.uploadResult = response.data;
													//console.log(response.status);
													console.log(response.data);
													//var message = response.data;
													alert(response.data);
													//alert("Successfully Uploaded");
										        	$scope.loadHomeBodyPageForm();

												},
												function(response) {
													alert("Upload Failed");
													$scope.uploadResult = response.data;
												});
							};

							var paginationOptions = {
								pageNumber : 1,
								pageSize : 20,
								sort : null
							};

							var counttype = "";

							$scope.getCountType = function(type) {

								counttype = type;
								BillingService.getUsers(paginationOptions.pageNumber,
												paginationOptions.pageSize,
												counttype).success(
												function(data) {
													$scope.gridOptions.data = data.content;
													$scope.gridOptions.totalItems = data.totalElements;
												});
							}

							$scope.refresh = function() {
								if ($scope.searchText == null
										|| $scope.searchText == undefined
										|| $scope.searchText == '') {
									BillingService.getUsers(paginationOptions.pageNumber,
											paginationOptions.pageSize,
											counttype).success(
											function(data) {
												$scope.gridOptions.data = data.content;
												$scope.gridOptions.totalItems = data.totalElements;
											});

								} else if ($scope.searchText != null
										|| $scope.searchText != undefined
										|| $scope.searchText != '') {
									
									
									BillingService.getUsers(paginationOptions.pageNumber,
											paginationOptions.pageSize,
											counttype).success(
											function(data) {
												$scope.gridOptions.data = data.content;
												$scope.gridOptions.data = $filter('filter')
												($scope.gridOptions.data,
														$scope.searchText);
												$scope.gridOptions.totalItems = data.totalElements;
											});

									

								} else {
									BillingService.getUsers(paginationOptions.pageNumber,
											paginationOptions.pageSize,
											counttype).success(
											function(data) {
												$scope.gridOptions.data = data.content;
												$scope.gridOptions.totalItems = data.totalElements;
											});
								}
							};
							
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
							    
							    var allocId = 0;
						        var rfpRefNo = '';
						        var vendor = '';
						        var circle = '';
						        var allocatedQuantity = 0;
						        var poQuantity=0;
						        var remainingQuantity=0;
						      
							    
							    
							    $scope.saveRow = function (row) {
							        //get the index of selected row 
							        var index = $scope.gridOptions.data.indexOf(row);
							        //Remove the edit mode when user click on Save button
							        $scope.gridOptions.data[index].editrow = false;

							
							        allocId = row.allocId;
							        rfpRefNo = row.rfpRefNo;
							        vendor = row.vendor;
							        circle = row.circle;
							        allocatedQuantity = row.allocatedQuantity;
							        poQuantity=row.poQuantity;
							        remainingQuantity=row.remainingQuantity;
							       // poNumber=row.poNumber;
							        console.log("Allocation Id" + allocId);
							        console.log("PO Quantity " + poQuantity);
							        
							        var checkPo = Number.isInteger(poQuantity);
							        
							        if(checkPo){
							        	
							        
							      
							        if(poQuantity<=remainingQuantity){
							        	EditService.saveCorrection(allocId,allocatedQuantity,
									        		poQuantity,remainingQuantity).then(function (d) {
									           
									        	console.log("Inside Success " + d.status);
									        	alert("Data saved successfully");
									        	 PurchaseOrderService.getUsers(paginationOptions.pageNumber,
									     				paginationOptions.pageSize, counttype).success(function(data) {
									     					console.log("data " + data);
									     			$scope.gridOptions.data = data.content;
									     			$scope.gridOptions.totalItems = data.totalElements;
									     	   });
									     		    
									       
									        }, function (d) {
									        	console.log("Inside failed " + d.status);
									        	alert("Failed to save");
									        });
							        	
							        }else{
							        	alert("MUST- PO Quantity <= Remaining Quantity");
							        }
							      
							        }else{
							        	
							        	alert("Decimal/Negative value not allowed for PO Quantity");
							        	
							        }
							       
							    };
							    
							    

							BillingService
									.getUsers(paginationOptions.pageNumber,
											paginationOptions.pageSize,
											counttype)
									.success(
											function(data) {
												$scope.gridOptions.data = data.content;
												$scope.gridOptions.totalItems = data.totalElements;
											});

							$scope.gridOptions = {
								paginationPageSizes : [ 20, 30, 40 ],
								paginationPageSize : paginationOptions.pageSize,
								enableColumnMenus : false,
								useExternalPagination : true,

								columnDefs : [ {
									name : 'repRefNo',
									displayName : 'RFP_REF_NO'
								},
								{ name: 'allocId', displayName: 'Allocation ID' , visible: false },
								{
									name : 'vendor',
									displayName : 'VENDOR'
								}, {
									name : 'circle',
									displayName : 'CIRCLE'
								}, {
									name : 'allocatedQuantity',
									displayName : 'ALLOCATED_QUANTITY'
								},
								{ name: 'remainingQuantity', displayName: 'REMAINING QUANTITY'},
								 {
					                  name: "poQuantity", displayName: "QUANTITY", field: "poQuantity",
					                  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
					                	 ' ng-if="row.entity.editrow"><input type="number" min="0"  style="height:30px" ng-model="MODEL_COL_FIELD"</div>', width: 140
					              },
					              { name: 'poDate', displayName: 'LAST PO ISSUE DATE' },
								 {
					                  name: 'ACTIONS', field: 'edit', enableFiltering: false, enableSorting: false,
					                  cellTemplate: '<div><button ng-show="!row.entity.editrow" class="btn primary" ng-click="grid.appScope.edit(row.entity)"><i class="fa fa-edit"></i></button>' +  //Edit Button
					                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.saveRow(row.entity)"><i class="fa fa-floppy-o"></i></button>' +//Save Button
					                                 '<button ng-show="row.entity.editrow" class="btn primary" ng-click="grid.appScope.cancelEdit(row.entity)"><i class="fa fa-times"></i></button>' + //Cancel Button
					                                 '</div>', width: 100
					              }

								],
								onRegisterApi : function(gridApi) {
									$scope.gridApi = gridApi;
									gridApi.pagination.on
											.paginationChanged(
													$scope,
													function(newPage, pageSize,
															counttype) {
														paginationOptions.pageNumber = newPage;
														paginationOptions.pageSize = pageSize;
														BillingService
																.getUsers(
																		newPage,
																		pageSize,
																		counttype)
																.success(
																		function(
																				data) {
																			$scope.gridOptions.data = data.content;
																			$scope.gridOptions.totalItems = data.totalElements;
														});
													});
								}
							};

						} ]);

app.service('BillingService', [
		'$http',
		function($http) {

			function getUsers(pageNumber, size, counttype) {
				pageNumber = pageNumber > 0 ? pageNumber - 1 : 0;
				return $http({
					method : 'GET',
					url : 'billingallocation/get?page=' + pageNumber + '&size='
							+ size + '&type=' + counttype

				});
			}

			return {
				getUsers : getUsers
			};

		} ]);


app.factory('EditService', function ($http) {
    var res = {};


    res.saveCorrection = function (allocId,allocatedQuantity,
    		poQuantity,remainingQuantity) {
    	 return $http({
	            method: 'GET',
	     
	            url: 'al/edit?AllocId='+allocId+'&AllocatedQuantity='+allocatedQuantity+'&PoQuantity='
	            +poQuantity+'&RemainingQuantity='+remainingQuantity
	            });
    }
    return res;
});
