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
	       
	       
	       $scope.approve = function(selectedcommnets){
	    	   
	    	   var requestId= $("#reqId").val();
	    	   console.log("reqId....." + requestId);
	    	   
	    	   console.log("reqId....." + selectedcommnets)
	    	   
	    	   if(selectedcommnets=='' || selectedcommnets == null || selectedcommnets==undefined ){
	    		  
	    		   alert("please update valid comments");
	    		   
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
	    		  
	    		   alert("please update valid comments");
	    		   
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
	    		  
	    		   alert("please update valid comments");
	    		   
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
	          url: 'approveICDetails/get?reqId='+reqId+'&commnets='+selectedcommnets,
	          headers: 
              {
                  'X-CSRF-TOKEN': csrf
              }
	         
	        });
	    }
		
		function reject(reqId,selectedcommnets,csrf) {
				
		        return  $http({
		          method: 'GET',
		          url: 'rejectICDetails/get?reqId='+reqId+'&commnets='+selectedcommnets,
		          headers: 
	              {
	                  'X-CSRF-TOKEN': csrf
	              }
		         
		        });
		    }
		
		
		function updateMakersComments(reqId,selectedcommnets,csrf) {
			
	        return  $http({
	          method: 'GET',
	          url: 'updateICComment/get?reqId='+reqId+'&commnets='+selectedcommnets,
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


	