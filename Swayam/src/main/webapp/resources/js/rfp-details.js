var app = angular.module('app', ['ngRoute','ui.grid','ui.grid.edit','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

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
		var periodicity='';
        var penaltyType='';
        var penaltyToRange=0;
        var penaltyfromRange=0;
	   
	   $scope.saveRow = function (row) {
	        //get the index of selected row 
	        var index = $scope.gridOptions.data.indexOf(row);
	        var  RfpDate= new Date(row.rfpDate);
	        var  AMCDate= new Date(row.amcStartDate);
	        var  SLAStartDate= new Date(row.slaStartDate);
	       
	        
	        
	      //  var dateObj = new Date(row.slaStartDate);
	        // test date is stored to dateObj
	      
	        var month = SLAStartDate.getMonth()+1;
	        // make date 2 digits
	        var date = SLAStartDate.getDate();
	        // get 4 digit year
	        var year = SLAStartDate.getFullYear()+5;
	        // concatenate into desired arrangement
	        var expiryDate = year + '/' + month + '/' + date;
	    	
	        var  SLAExpiryDate= new Date(expiryDate);
	        $scope.gridOptions.data[index].editrow = false;
	        
	        var user ={};
	        
	       
	        
	        
	    /*    user={rfpNo:row.rfpNo,
	        		rfpId:row.rfpId,
	        		vendor:row.vendor,
	        		kisokCost:row.kisokCost,
	        		amcCost:row.amcCost,
	        		companyPenaltyHour:row.companyPenaltyHour,
	        		companyPermDntmMuHrs:row.companyPermDntmMuHrs,
	        		companyPermDntmSrHrs:row.companyPermDntmSrHrs,
	        		companyPermDntmPct:row.companyPermDntmPct,
	        		maxPenaltyPct:row.maxPenaltyPct,
	        		penaltyToRange:row.penaltyToRange,
	        		penaltyfromRange:row.penaltyfromRange,
	        		rfpDate:RfpDate,
	        		amcStartDate:AMCDate,
	        		periodicity:row.periodicity,
	        		penaltyType:row.penaltyType,
	        		slaStartDate:SLAStartDate,
	        		slaExpiryDate:SLAExpiryDate};*/
	        
	        var status=0;
	        var i=0;
	        var j= 0;
	        var validations=new Array();
	        validations[j]="Failed to Update Null/Invalid data";
	        j++;
	        
	        if(row.penaltyType=="Fixed"){
	        	
	        	 user={rfpNo:row.rfpNo,
	 	        		rfpId:row.rfpId,
	 	        		vendor:row.vendor,
	 	        		kisokCost:row.kisokCost,
	 	        		amcCost:row.amcCost,
	 	        		companyPenaltyHour:row.companyPenaltyHour,
	 	        		companyPermDntmMuHrs:row.companyPermDntmMuHrs,
	 	        		companyPermDntmSrHrs:row.companyPermDntmSrHrs,
	 	        		companyPermDntmPct:row.companyPermDntmPct,
	 	        		maxPenaltyPct:row.maxPenaltyPct,
	 	        		penaltyfromRange:0,
	 	        		penaltyToRange:0,
	 	        		rfpDate:RfpDate,
	 	        		amcStartDate:AMCDate,
	 	        		periodicity:row.periodicity,
	 	        		penaltyType:row.penaltyType,
	 	        		slaStartDate:SLAStartDate,
	 	        		slaExpiryDate:SLAExpiryDate};
	        	
	        }else if(row.penaltyType=="Range"){
	        	 user={rfpNo:row.rfpNo,
	 	        		rfpId:row.rfpId,
	 	        		vendor:row.vendor,
	 	        		kisokCost:row.kisokCost,
	 	        		amcCost:row.amcCost,
	 	        		companyPenaltyHour:row.companyPenaltyHour,
	 	        		companyPermDntmMuHrs:row.companyPermDntmMuHrs,
	 	        		companyPermDntmSrHrs:row.companyPermDntmSrHrs,
	 	        		companyPermDntmPct:row.companyPermDntmPct,
	 	        		maxPenaltyPct:0,
	 	        		penaltyfromRange:row.penaltyfromRange,
	 	        		penaltyToRange:row.penaltyToRange,
	 	        		rfpDate:RfpDate,
	 	        		amcStartDate:AMCDate,
	 	        		periodicity:row.periodicity,
	 	        		penaltyType:row.penaltyType,
	 	        		slaStartDate:SLAStartDate,
	 	        		slaExpiryDate:SLAExpiryDate};
	        	
	        }else{
	        	status=3;
	        }
	        
	        
	        
	        
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
	        		
	        	}
	        	//Changes - 17-11-2020
	        /*	else if(i==11){
	        		
	        		console.log("RFP Date value " + value);
	        		var d = new Date();
	        		var defult = new Date("02-01-1970");
	        		console.log("Defult date " + defult);
	        		console.log("Current Date " + d);
	        		
	        		if(value>d || value < defult){
	        			
	        			validations[j]="RFP Date <= current date /Required RFP Date ";
		        		j++;
		        		status=2;
	        		}
	        		
	        	}else if(i==12){
	        		
	        		console.log("AMC Date value " + value);
	        		var d = new Date();
	        		var defult = new Date("02-01-1970");
	        		console.log("Defult date " + defult);
	        		console.log("Current Date " + d);
	        		
	        		if(value>d || value < defult){
	        			
	        			validations[j]="AMC Start Date <= current date /Required AMC Start Date ";
		        		j++;
		        		status=2;
	        		}
	        		
	        	}*/
	        	else if(i==17){
	        		
	        		console.log("SLA Start Date " + value);
	        		var d = new Date();
	        		var defult = new Date("02-01-1970");
	        		console.log("Defult date " + defult);
	        		console.log("Current Date " + d);
	        		
	        		if(value>d || value < defult){
	        			
	        			validations[j]="SLA Start Date <= current date /Required SLA Start Date ";
		        		j++;
		        		status=2;
	        		}
	        		
	        	}/*else if(i==17){
	        		
	        		console.log("SLA Expiry Date value " + value);
	        		//var d = new Date();
	        		
	        		var slaExpiryDate = value.split(".");
	    	    	
	    	    	var  SlaExpiryDate= new Date(dateParts[2]+"-"+dateParts[1]+"-"+dateParts[0]);
	    	    	
	        		var defult = new Date("02-01-1970");
	        		console.log("Defult date " + defult);
	        		console.log("Current Date " + d);
	        		
	        		if(value < defult){
	        			
	        			validations[j]="Required SLA Expiry Date ";
		        		j++;
		        		status=2;
	        		}
	        		
	        	}*/
	        	
	        	else if(i>3 && i<13){
	        		
	        		 var check = angular.isNumber(value);
				     console.log("Check for integer " + check);
	        		
	        		if(value ==null || value ==undefined || value =='' || check==false){
	        			console.log("Value--------"+value);
	        			if(value!=0){
	        			
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
	        				
	        			}else if(i==11){
	        				validations[j]="Penalty From Range is NULL/Not a number";
			        		j++;
	        				
	        			}else if(i==12){
	        				validations[j]="Penalty To Range is NULL/Not a number";
			        		j++;
	        				
	        			}
	        			
	        			
	        			
	        				status=2;
	        			}
		        		
		        		
		        	}else{
		        		
		        		if(i==4){
		        			
		        			if(value>99999.99 || value<0 ){
		        				
	        				validations[j]=key+"0<= Cost of kiosk <=99999.99";
			        		j++;
			        		status=2;
		        			}
	        				
	        			}else if(i==5){
	        				if(value>999.99 || value<0){
		        				
		        				validations[j]=key+" 0<= Cost of AMC <=999.99";
				        		j++;
				        		status=2;
			        			}
	        			}else if(i==6){
	        				if(value>999 || value<0){
		        				
		        				validations[j]=key+"0<= Complaint Penalty /hr <=999";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==7){
	        				if(value>12 || value<0){
		        				
		        				validations[j]=key+"0<= Permissible Downtime in Metro/urban(in hrs) <=12";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==8){
	        				if(value>12 || value<0){
		        				
		        				validations[j]=key+"0<= Permissible Downtime in Semi-Urban/Rural(in hrs) <=12";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==9){
	        				if(value>100 || value<0){
		        				
		        				validations[j]=key+"0<= Circle Permissible Downtime(in %) <=100";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==10){
	        				if(value>100 || value<0){
		        				
		        				validations[j]=key+"0<= Maximum Penalty(in %) <=100";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==11){
	        				if(value>999 || value<0){
		        				
		        				validations[j]=key+"0<= Penalty From Range <=999";
				        		j++;
				        		status=2;
			        			}
	        				
	        			}else if(i==12){
	        				if(value>999 || value<0){
		        				
		        				validations[j]=key+"0<= Penalty To Range <=999";
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
	            //alert("Updated Successfully");
	        	alert(d.data.status);
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
	    	$scope.selectedRangeTo='';
	    	$scope.selectedRangeFrom='';
	    	$scope.selectedPenType='';
	    	$scope.selectedPeriodicty='';
	    	$scope.selectedSLAdate='';
	    	$scope.selectedSlaExpDate='';
				
	       }

	    
	    
	    $scope.searchPostion = function(selectedRfpNo,selectedRfpid,
	    		selectedVendor,selectedkcost,
				selectedAMCcost,selectedCPenalty,
				selectedDMU,selectedDMUR,selectedDCT,
				selectedMP,selectedRfpDate,selectedAmcDate,
				selectedPeriodicty,selectedPenType,
				selectedRangeTo,selectedRangeFrom) {
	       
	    	
	    	var date= $("#rfpDate").val();
	    	var date2= $("#amcDate").val();
	    	console.log("Date2 "+ date2);
	    	var date3 = $("#slaDate").val();
	    	var date4 = $("#slaExpDate").val();
	    	
	    	if(!date){
	    		alert("RFP Date Required!");
	    	}else if(!date2){
	    		alert("AMC Start Date Required!");
	    		
	    	}else if(!date3){
	    		alert("SLA Start Date Required!");
	    		
	    	}else if(!date4){
	    		alert("SLA Expiry Date Required!");
	    		
	    	}
	    	else{
	    	var dateParts = date.split("-");
	    	var dateParts2 = date2.split("-");
	    	var dateParts3 = date3.split("-");
	    	var dateParts4 = date4.split("-");
	    	
	    	var  RfpDate= new Date(dateParts[2]+"-"+dateParts[1]+"-"+dateParts[0]);
	    	var  AMCDate= new Date(dateParts2[2]+"-"+dateParts2[1]+"-"+dateParts2[0]);
	    	var  SlaStartDate= new Date(dateParts3[2]+"-"+dateParts3[1]+"-"+dateParts3[0]);
	    	var  SlaExpiryDate= new Date(dateParts4[2]+"-"+dateParts4[1]+"-"+dateParts4[0]);
	    	
	    	var user={};
	    	
	    	 if(selectedPenType=="Fixed"){
		        	
	    		 user={rfpNo:selectedRfpNo,rfpId:selectedRfpid,vendor:selectedVendor,kisokCost:selectedkcost,
	 	        		amcCost:selectedAMCcost	
	 	        ,companyPenaltyHour:selectedCPenalty,companyPermDntmMuHrs:selectedDMU,
	 	        companyPermDntmSrHrs:selectedDMUR,companyPermDntmPct:selectedDCT,
	 	        maxPenaltyPct:selectedMP,rfpDate:RfpDate,amcStartDate:AMCDate,slaStartDate:SlaStartDate,slaExpiryDate:SlaExpiryDate,
	 	        periodicity:selectedPeriodicty,penaltyType:selectedPenType,penaltyToRange:0,penaltyfromRange:0};	        
	 	        
	        	
	        }else if(selectedPenType=="Range"){
	        	user={rfpNo:selectedRfpNo,rfpId:selectedRfpid,vendor:selectedVendor,kisokCost:selectedkcost,
		        		amcCost:selectedAMCcost	
		        ,companyPenaltyHour:selectedCPenalty,companyPermDntmMuHrs:selectedDMU,
		        companyPermDntmSrHrs:selectedDMUR,companyPermDntmPct:selectedDCT,
		        maxPenaltyPct:0,rfpDate:RfpDate,amcStartDate:AMCDate,slaStartDate:SlaStartDate,slaExpiryDate:SlaExpiryDate,
		        periodicity:selectedPeriodicty,penaltyType:selectedPenType,penaltyToRange:selectedRangeTo,penaltyfromRange:selectedRangeFrom};	        
		        
	        }
	    	
	
	      /*  
	        var user={rfpNo:selectedRfpNo,rfpId:selectedRfpid,vendor:selectedVendor,kisokCost:selectedkcost,
	        		amcCost:selectedAMCcost	
	        ,companyPenaltyHour:selectedCPenalty,companyPermDntmMuHrs:selectedDMU,
	        companyPermDntmSrHrs:selectedDMUR,companyPermDntmPct:selectedDCT,
	        maxPenaltyPct:selectedMP,rfpDate:RfpDate,amcStartDate:AMCDate,slaStartDate:SlaStartDate,slaExpiryDate:SlaExpiryDate,
	        periodicity:selectedPeriodicty,penaltyType:selectedPenType,penaltyToRange:selectedRangeTo,penaltyfromRange:selectedRangeFrom};	        
	        */
	        console.log("user RfpNo" + user.rfpNo);
	        console.log("RfpId " + user.rfpId);
	        console.log("Session CSRF "+ $scope.csrf);
	        RfpUpdateService.addRfp(user,$scope.csrf).then(function (d) {
	            
	        	console.log("Successfully Added " + d.status);
	        	console.log("Successfully Added " + d.data.status);
	        	
	        	alert(d.data.status);
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
	        
	    	}
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
		        	//alert("Successfully deleted");
		        	alert(d.data.status);
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
	          { name: 'kisokCost', displayName: 'Cost of Kiosk (in Rs)',field: "kisokCost",  visible: false ,
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'amcCost', displayName: 'Cost of Amc (in Rs)' ,field: "amcCost", 
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPenaltyHour', displayName: 'Compaint Penalty/hour' ,field: "companyPenaltyHour", 
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPermDntmMuHrs', displayName: 'Permissible Downtime in Metro/Urban (in hrs)' ,field: "companyPermDntmMuHrs", visible: false ,
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPermDntmSrHrs', displayName: 'Permissible downtime in Semi-Urban/Rural areas (in hrs)' ,field: "companyPermDntmSrHrs",visible: false ,
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'companyPermDntmPct', displayName: 'Circle Permissible Downtime(in %)',field: "companyPermDntmPct", visible: false ,
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
	          { name: 'maxPenaltyPct', displayName: 'Maximum Penalty(in %)',field: "maxPenaltyPct",	  
	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140
	          },
	          { name: 'rfpDate', displayName: 'RFP Date' , type: 'date', cellFilter: 'date:"dd.MM.yyyy"', visible: false ,
	        	  width: '10%' },
	         { name: 'amcStartDate', displayName: 'AMC Start Date' , type: 'date', cellFilter: 'date:"dd.MM.yyyy"', visible: false ,
		        	  width: '10%' },
		     { name: 'slaStartDate', displayName: 'SLA Start Date' , type: 'date', cellFilter: 'date:"dd.MM.yyyy"', 
			        	  width: '10%' },
			 { name: 'slaExpiryDate', displayName: 'SLA Expiry Date' , type: 'date', cellFilter: 'date:"dd.MM.yyyy"', 
				        	  width: '10%' },
			{ name: 'periodicity', displayName: 'AMC Periodicity' ,field: "periodicity",editableCellTemplate: 'ui-grid/dropdownEditor',  width: '10%',
				             		cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
					             	 ' ng-if="row.entity.editrow"><input type="text" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>',
				             		editDropdownValueLabel: 'periodicity', editDropdownOptionsArray: [
				             	        { id: 'Quarterly', periodicity: 'Quarterly' },
				             	        { id: 'Half-Yearly', periodicity: 'Half-Yearly' }
				             	      ]},
					          
	          
					             	 
			{ name: 'penaltyType', displayName: 'Penalty Type' ,field: "penaltyType",editableCellTemplate: 'ui-grid/dropdownEditor',  width: '10%',
					             		cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
						             	 ' ng-if="row.entity.editrow"><input type="text" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>',
					             		editDropdownValueLabel: 'penaltyType', editDropdownOptionsArray: [
					             	        { id: 'Fixed', penaltyType: 'Fixed' },
					             	        { id: 'Range', penaltyType: 'Range' }
					             	      ] },
					             	 
			{ name: 'penaltyToRange', displayName: 'Penalty To Range' ,field: "penaltyToRange", 
							   	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
							   	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
							   	          
			{ name: 'penaltyfromRange', displayName: 'Penalty From Range' ,field: "penaltyfromRange", 
									   	        	  cellTemplate: '<div  ng-if="!row.entity.editrow">{{COL_FIELD}}</div><div '+ 
									   	             	 ' ng-if="row.entity.editrow"><input type="number" style="height:30px"  ng-model="MODEL_COL_FIELD"</div>', width: 140},
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