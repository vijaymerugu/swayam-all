var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('SanctionRequestCtrl', ['$scope','$filter','$http','$window','SanctionService',
		function ($scope, $filter, $http, $window,SanctionService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	
	   var counttype = "";   
	   
		   $scope.open= function () {
			   var str ='bp/getFormRequest'
					$("#contentHomeApp").load(str);
			   
		   };
		   
		   $scope.loadRequestForm = function(requestId,reqType){	   
				if(requestId != undefined){	
					//var str ='hm/viewCCCaseId?caseId=' + url;
					
					reqType = reqType.replace(/ /g,"");
					console.log("Request Id " + requestId + "type "+ reqType);
					 var str ='bp/getSanctionApprovalForm?requestId='+requestId+'&requestType='+reqType
					$("#contentHomeApp").load(str);
				}						
			}
		   
		   
		   
		   
		   
		   $scope.gridOptions = {
				    paginationPageSizes: [20, 30, 40],
				    paginationPageSize: paginationOptions.pageSize,	
					enableColumnMenus:false,
					useExternalPagination: true,
					
				      columnDefs: [
				          { name: 'requestId', displayName: 'Request Id' , 
				        	  cellTemplate: '<div class="ui-grid-cell-contents"><a ng-click="grid.appScope.loadRequestForm(row.entity.requestId,row.entity.reqType)">{{ row.entity.requestId}}</a></div>'  
				          },
				          { name: 'reqType', displayName: 'Type of Request' },
				          { name: 'reqDetail', displayName: 'Request Details' , visible: false
			   	        	 
				          },
				          { name: 'reqDate', displayName: 'Request Initiated Date & time', type: 'date', cellFilter: 'date:"dd.MM.yyyy"'  },
				          { name: 'reqCloseDate', displayName: 'Request Closed Date & time',type: 'date', cellFilter: 'date:"dd.MM.yyyy"' },
				          { name: 'makerPfid', displayName: 'Maker details (PF ID)'  },
				          { name: 'makersComment', displayName: 'Commnets by Maker', 
			   	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
			   	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>'  
				          },
				          { name: 'checkerPfid', displayName: 'Approver Details (PF ID)' },
				          { name: 'checkerCommnet', displayName: 'Commnets by Checker' },
				          { name: 'status', displayName: 'Prgress Status' },
				          { name: 'userCircle', displayName: 'Circle User' ,visible: true },
				          {
			                  name: 'Actions', field: 'edit', enableFiltering: false, enableSorting: false,
			                  cellTemplate: '<div><button ng-show="grid.appScope.showorhide(row.entity.status,row.entity.reqType)" class="btn primary" ng-click="grid.appScope.download(row.entity.requestId)"><i class="fa fa-download"></i></button>' +  //Edit Button
			                         
			                                 '</div>', width: 140
			              }
				          
				    ],
				    onRegisterApi: function(gridApi) {
				        $scope.gridApi = gridApi;
				        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
				          paginationOptions.pageNumber = newPage;
				          paginationOptions.pageSize = pageSize;
				          
				       	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
						   	
				       	  SanctionService.getUsers(paginationOptions.pageNumber,
									paginationOptions.pageSize, counttype).success(function(data) {
										console.log("data4 " + data);
								$scope.gridOptions.data = data.content;
								$scope.gridOptions.totalItems = data.totalElements;

				          });
				       		
						 	   
						   	}else{
						   		
						   	  SanctionService.getRefersh(paginationOptions.pageNumber,
										paginationOptions.pageSize, counttype,$scope.searchText).success(function(data) {
											//console.log("data4 " + data);
									$scope.gridOptions.data = data.content;
									$scope.gridOptions.totalItems = data.totalElements;

					         });
						   		
						   	
						   		
						   	}
				          
				          
				        
				        });
				     }
				  };
		   
		   
		   $scope.download = function (requestId) {
			   
			 //  alert(requestId);
		       
			   SanctionService.generatePdf(requestId).then(function (d) {
				   console.log("success " + requestId);
		        	//alert("Sucess" + d.data);
		        	
		        	$window.open("resources/download/"+d.data.status , '_blank');
		        	
		        }, function (d) {
		        	
		        	alert("Failed to generate");
		     
		        });
		        
		    };
		    
		    $scope.showorhide = function (status,reqType){
		    	if(status=='Approved' && reqType =='Sanction Note'){
		    		return true;
		    	}else{
		    		return false;
		    	}
		    }

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
		    
		    
		    $scope.refresh = function()
			   {  	
		    	console.log("Inside refresh " + $scope.searchText);
				   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
				   	  SanctionService.getRefersh(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,$scope.searchText).success(function(data) {
									//console.log("data4 " + data);
							$scope.gridOptions.data = data.content;
							$scope.gridOptions.totalItems = data.totalElements;

			         });
				 	   
				   	}else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
				   		console.log("Inside not Null " + $scope.searchText);
				   		SanctionService.getRefersh(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,$scope.searchText).success(function(data) {
									
									$scope.gridOptions.data = data.content;
									$scope.gridOptions.totalItems = data.totalElements;

			         });
				   		
				   		
				   		
				 	
				 		   		   
				 		   
				 	    }else{
				 	    	  SanctionService.getRefersh(paginationOptions.pageNumber,
				 						paginationOptions.pageSize, counttype,$scope.searchText).success(function(data) {
				 							console.log("data4 " + data);
				 					$scope.gridOptions.data = data.content;
				 					$scope.gridOptions.totalItems = data.totalElements;

				 	         });
				 	    }
				    };
		    
		   
		   
		   $scope.getCountType = function(type){
			      
		       counttype=type;
		       SanctionService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype).success(function(data) {
							console.log("data2 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;
						   });
			}
		   
		   
		   SanctionService.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype).success(function(data) {
						console.log("data4 " + data);
				$scope.gridOptions.data = data.content;
				$scope.gridOptions.totalItems = data.totalElements;

         });
		   
		   
		   
	      


	}]);
	
	
app.service('SanctionService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'sannctionRequest/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype
	         
	        });
	    }
		
		function getRefersh(pageNumber,size,counttype,serchtext) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'sannctionRequestRefresh/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&serchtext='+serchtext
	         
	        });
	    }
		
		
		function generatePdf(requestId) {
			
	        return  $http({
	          method: 'GET',
	          url: 'generate/sanctionPdf?requestId='+requestId
	        });
	    }
		

		
	    return {
	    	getUsers:getUsers,
	    	getRefersh:getRefersh,
	    	generatePdf:generatePdf
	    };
		
	}]);


	