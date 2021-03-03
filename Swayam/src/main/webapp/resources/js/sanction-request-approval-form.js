var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('SanctionApprovalCtrl', ['$scope','$filter','$http','$window','SanctionFormService','approveService',
		function ($scope, $filter, $http, $window,SanctionFormService,approveService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	   
	   $scope.loadHomeBodyPageForm = function(){
			var str ='bp/snrequest'
			$("#contentHomeApp").load(str);
								
	}
	   
	   $scope.cancel= function () {
		   var str ='bp/snrequest'
				$("#contentHomeApp").load(str);
		   
	   };	 
	 
	   

/*	 $scope.cancel= function () {
			   var str ='bp/snrequest'
					$("#contentHomeApp").load(str);
			   
		   };*/
		   
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
	   
	   
	   
	   $scope.LoadDropDown = function(type, value) {
			switch (type) {
			default:
//				$scope.SelectedCircleId = 0;
//				$scope.CircelDefaultLabel = "Loading.....";
				$scope.Circle = null;
				break;
			case "circleId":
               $scope.SelectedStateId = 0;
//               $scope.StateDefaultLabel = "Loading.....";
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
//					$scope.SelectedCircelId = 0;
//					$scope.CircleDefaultLabel = "Select Circle";
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
				console.log("error1....." + value);
				//$window.alert(data.Message);
			});
		}; 	   
		   
		   
		   
		 $scope.LoadDropDown('', 0);
	     
	       $scope.LoadVendor();
	       
	       
	       
	       $scope.approve = function(selectedcommnets){
	    	   
	    	   var requestId= $("#reqId").val();
	    	   console.log("reqId....." + requestId);
	    	   
	    	   console.log("reqId....." + selectedcommnets)
	    	   
	    	   if(selectedcommnets=='' || selectedcommnets == null || selectedcommnets==undefined ){
	    		  
	    		   alert("please update the comments");
	    		   
	    	   }else{
	    	   
	    		   //console.log("Session CSRF "+ $scope.csrf);
	    	   
	    	   approveService.approve(requestId,selectedcommnets,$scope.csrf).then(function (d) {
	    		   
	    		   alert(d.data.status);
	    		   $scope.loadHomeBodyPageForm();
		          
		        }, function (d) {
		        	alert("failed to approve");
		        	
		        
		        });
	    	   
	    	   }
	    	  
	       }
	       
	       
	       $scope.updateComment=function(selectedcommnets){
	    	   
	    	   var requestId= $("#reqId").val();
	    	   console.log("reqId....." + requestId);
	    	   
	    	   console.log("reqId....." + selectedcommnets)
	    	   
	    	   if(selectedcommnets=='' || selectedcommnets == null || selectedcommnets==undefined ){
	    		  
	    		   alert("please update the comments");
	    		   
	    	   }else{
	    	   
	    	   
	    	   
	    	   approveService.updateMakersComments(requestId,selectedcommnets,$scope.csrf).then(function (d) {
	    		   
	    		   alert(d.data.status);
	    		   $scope.loadHomeBodyPageForm();
		          
		        }, function (d) {
		        	alert("failed to approve");
		        	
		        
		        });
	    	   
	    	   }
	    	  
	       }
	       
	       
	       $scope.reject = function(selectedcommnets){
	    	   
	    	   var requestId= $("#reqId").val();
	    	   console.log("reqId....." + requestId);
	    	   console.log("reqId....." + selectedcommnets)
	    	   
	    	   if(selectedcommnets=='' || selectedcommnets == null || selectedcommnets==undefined ){
	    		  
	    		   alert("please update the comments");
	    		   
	    	   }else{
	    	   
	    	   
	    	   approveService.reject(requestId,selectedcommnets,$scope.csrf).then(function (d) {
	    		   
	    		   alert(d.data.status);
	    		   $scope.loadHomeBodyPageForm();
		          
		        }, function (d) {
		        	alert("failed to reject");
		        	
		        
		        });
	    	   
	    	   }
	       }
	}]);
	
	
	app.service('approveService',['$http', function ($http) {
		
		/*function getUsers(reqId) {
			
	        return  $http({
	          method: 'GET',
	          url: 'approveDetails/get?reqId='+reqId
	         
	        });
	    }
		*/
		
		function approve(reqId,selectedcommnets,csrf) {
			
	        return  $http({
	          method: 'GET',
	          url: 'approveDetails/get?reqId='+reqId+'&commnets='+selectedcommnets,
	          headers: 
              {
                  'X-CSRF-TOKEN': csrf
              }
	         
	        });
	    }
		
		function reject(reqId,selectedcommnets,csrf) {
				
		        return  $http({
		          method: 'GET',
		          url: 'rejectDetails/get?reqId='+reqId+'&commnets='+selectedcommnets,
		          headers: 
	              {
	                  'X-CSRF-TOKEN': csrf
	              }
		         
		        });
		    }
		
		
		function updateMakersComments(reqId,selectedcommnets,csrf) {
			
	        return  $http({
	          method: 'GET',
	          url: 'updateComment/get?reqId='+reqId+'&commnets='+selectedcommnets,
	          headers: 
              {
                  'X-CSRF-TOKEN': csrf
              }
	         
	        });
	    }
		    
		

		
	    return {
//	    	getUsers:getUsers,
	    	approve:approve,
	    	reject:reject,
	    	updateMakersComments:updateMakersComments
	    };
		
	}]);
	
	
	app.factory('SanctionFormService', function ($http) {
	    var res = {};

		 

		
		    res.addSubmit= function(payload,header) {
		    	 return $http({
			            method: 'POST',
			            url: 'bp/sanctionInsert',
			            data: payload,
			            headers: 
		                {
		                    'X-CSRF-TOKEN': header
		                }
			        });
		    }
		    
		    
		  
		    
		    
		   
		    
		    return res;
	 
	});


	