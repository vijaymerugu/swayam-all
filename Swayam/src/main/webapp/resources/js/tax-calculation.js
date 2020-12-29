var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('taxCalCtrl', ['$scope','$filter','$http','$window',
		'TaxCalculationService','TaxInsert',function ($scope, $filter, $http, $window,TaxCalculationService,TaxInsert) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	   $scope.isDisabled=true;
	   $scope.yearOptions = [
	   	    { name: '2020-2021', value: '2020-2021' }, 
	   	    { name: '2021-2022', value: '2021-2022' }, 
	   	    { name: '2022-2023', value: '2022-2023' }
	   	    ];
	   	    
	   	    $scope.SelectedYearId = $scope.yearOptions[0].value;
	   	    
	   	    
	   	 $scope.IGSTChnage=function(){

	   		
	   		var test2 = $("#gsttype").val();
	   		var test1 = $("#igst").val();
	   	
	   		
	   		console.log("Inside set type "+ test1+" "+test2);
	   		
	   		$scope.gstType=test2;
	   		$scope.gstIGST=test1;
	   		
	   		 
	   	 }
	   	 
	   	$scope.CGSTChnage=function(){
//	   		//console.log("Inside set type");
//	   		var gstType = document.getElementById("gsttype").value;
//	   		var igst = document.getElementById("igst").value;
//	   		var cgst = document.getElementById("cgst").value;
//	   		var sgst = document.getElementById("sgst").value;
	   		
	   		
	   		var test3 = $("#cgst").val();
	   		
	   		console.log("Inside set type "+ test3);
	   		
	   		
	   		$scope.gstCGST=test3;
	   		
	   		 
	   	 }
	   	
	   	var gstType="";
   		
   		var gstSGST = ""
	   	
	   	$scope.SGSTChnage=function(){
//	   		//console.log("Inside set type");
//	   		var gstType = document.getElementById("gsttype").value;
//	   		var igst = document.getElementById("igst").value;
//	   		var cgst = document.getElementById("cgst").value;
//	   		var sgst = document.getElementById("sgst").value;
	   		
	   		var test2 = $("#gsttype").val();
	   		
	   		var test4 = $("#sgst").val();
	   		
	   		console.log("Inside set type "+ test2+" "+test4);
	   		
	   		gstType=test2;
	   		
	   		gstSGST=test4;
	   		
	   		console.log("Inside--- "+ gstSGST+" "+gstSGST);
	   		 
	   	 }
		   
   		
   	 $scope.resetRfp=function(){
   		$scope.RfpId='';
   	 }
   		
   		
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
				$scope.SelectedGSTType='';
				$scope.selectedIGST='';
				$scope.selectedSGST='';
				$scope.selectedCGST='';
				TaxCalculationService
				.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
							console.log("data1 " + data);
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
			var selectedGSTType="";
			var selectedIGST=0;
			var selectedSGST=0;
			var selectedCGST=0;
			var selectedGST=0;
			//var selectedQuarterId="";

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
								$scope.resetRfp();
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
	

  
       
			$scope.searchPositions = function(CircelId,StateId,
					QuarterId,YearId,VendorId,RfpId,SelectedGSTType,SelectedIGST,
					SelectedSGST,SelectedCGST) {
				console.log("RfpId " + RfpId);
				
				selectedCircelId = CircelId;
				
				selectedStateId = StateId;
				selectedQuarterId = QuarterId;
				selectedYearId = YearId;
				//selectedVendorId= VendorId
				selectedRfpID= RfpId;
				selectedGSTType=SelectedGSTType;
				selectedIGST=SelectedIGST;
				selectedSGST=SelectedSGST;
				selectedCGST=SelectedCGST;
				
				
				if(typeof VendorId === 'undefined') {
					
					selectedVendorId= "0";
					console.log("Inside if VendorId " + selectedVendorId);
				}else if(VendorId==''){
					selectedVendorId= "0";
					console.log("Inside else if VendorId " + selectedVendorId);
					
				}
				else{
					selectedVendorId= VendorId;
					console.log("Inside else VendorId " + selectedVendorId);
				}
				
				

				if(typeof StateId === 'undefined') {
					
					selectedStateId= "0";
					console.log("Inside if StateId " + selectedStateId);
				}else if(StateId==''){
					selectedStateId= "0";
					console.log("Inside else if StateId " + selectedStateId);
					
				}
				else{
					selectedStateId= StateId;
					console.log("Inside else StateId " + selectedStateId);
				}
				
				quterTimePeriod=(selectedQuarterId.toUpperCase())+'-'+selectedYearId;
				console.log("selectedCircelId " + selectedCircelId);
				console.log("selectedStateId " + selectedStateId);
				console.log("selectedQuarterId " + selectedQuarterId);
				console.log("selectedYearId " + selectedYearId);
				console.log("selectedVendorId " + selectedVendorId);
				console.log("quterTimePeid " + quterTimePeriod);
				
				console.log("selectedGSTType " + SelectedGSTType);
				console.log("selectedCGST " + selectedIGST);
				console.log("selectedSGST " + selectedSGST);
				console.log("selectedCGST " + selectedCGST);
				
				var status = 0;
				
				if(SelectedGSTType=="IGST"){
					
					
					selectedGST=SelectedIGST;
					if(SelectedIGST ==undefined || SelectedIGST=='' ){
						status=1;
					}
					
					
					selectedSGST=0;
		
					selectedCGST=0;
					
				}else if(SelectedGSTType=="SGST/CGST"){
					//selectedGST=SelectedSGST * 2;
					//selectedGST=SelectedSGST ;
					selectedSGST=SelectedSGST
					selectedCGST=SelectedCGST
					selectedGST=0;
					if(SelectedSGST == undefined || SelectedSGST=='' ){
						status=1;
					}
				}
				
				console.log("status " + status);		
				
				if(status==1){
					alert("Please select GST value ");
				}else{
					
				

				TaxCalculationService
						.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,
								selectedCircelId,selectedStateId,
								selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
									console.log("Response Data " + data.totalElements);	
									
									if(data.totalElements==0){
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										$scope.isDisabled=false;
									}
						});
				
				}
			}
			
			
			$scope.test = "test";
			
			$scope.InsertCal = function(CircelId,StateId,
					QuarterId,YearId,VendorId,RfpId,SelectedGSTType,SelectedIGST,
					SelectedSGST,SelectedCGST) {
				
				
				selectedCircelId = CircelId;
				
				selectedStateId = StateId;
				selectedQuarterId = QuarterId;
				selectedYearId = YearId;
				//selectedVendorId= VendorId
				selectedRfpID= RfpId;
				selectedGSTType=SelectedGSTType;
				selectedIGST=SelectedIGST;
				selectedSGST=SelectedSGST;
				selectedCGST=SelectedCGST;
				
				
				if(typeof VendorId === 'undefined') {
					
					selectedVendorId= "0";
					//console.log("Inside if VendorId " + selectedVendorId);
				}else if(VendorId==''){
					selectedVendorId= "0";
					//console.log("Inside else if VendorId " + selectedVendorId);
					
				}
				else{
					selectedVendorId= VendorId;
					//console.log("Inside else VendorId " + selectedVendorId);
				}
				
				

				if(typeof StateId === 'undefined') {
					
					selectedStateId= "0";
					//console.log("Inside if StateId " + selectedStateId);
				}else if(StateId==''){
					selectedStateId= "0";
					//console.log("Inside else if StateId " + selectedStateId);
					
				}
				else{
					selectedStateId= StateId;
					//console.log("Inside else StateId " + selectedStateId);
				}
				
				quterTimePeriod=(selectedQuarterId.toUpperCase())+'-'+selectedYearId;

				
				var status = 0;
				
				if(SelectedGSTType=="IGST"){
					
					
					selectedGST=SelectedIGST;
					if(SelectedIGST ==undefined || SelectedIGST=='' ){
						status=1;
					}
					
					
					selectedSGST=0;
		
					selectedCGST=0;
					
				}else if(SelectedGSTType=="SGST/CGST"){
					//selectedGST=SelectedSGST * 2;
					//selectedGST=SelectedSGST ;
					selectedSGST=SelectedSGST
					selectedCGST=SelectedCGST
					selectedGST=0;
					if(SelectedSGST == undefined || SelectedSGST=='' ){
						status=1;
					}
				}
				
				console.log("status " + status);		
				
				if(status==1){
					alert("Please select GST value ");
				}else{
					
				

					TaxInsert
						.getUsers(paginationOptions.pageNumber,
								paginationOptions.pageSize, counttype,
								selectedCircelId,selectedStateId,
								selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
									console.log("Response Data " + data);	
									if(data==1){
										alert("Tax Calculation Saved Sucessfully");
										
										$scope.isDisabled=true;
									}
									
									
									
									/*if(data.totalElements==0){
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
									}*/
						});
				
				}
			}
	 
	   
	    
	   $scope.getCountType = function(type){
	      
	       counttype=type;
	       TaxCalculationService.getUsers(paginationOptions.pageNumber,
					paginationOptions.pageSize, counttype,
					selectedCircelId,selectedStateId,
					selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedSGST,selectedCGST).success(function(data) {
						console.log("data2 " + data);
				$scope.gridOptions.data = data.content;
				$scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	   
	   $scope.refresh = function()
	   {  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		   		TaxCalculationService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
							console.log("data3 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	    	
		 	   	TaxCalculationService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
							
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
					$scope.gridOptions.totalItems = data.totalElements;

	 	 	   });
		 		   
		 		   
		 	    }else{
		 	    	TaxCalculationService.getUsers(paginationOptions.pageNumber,
							paginationOptions.pageSize, counttype,
							selectedCircelId,selectedStateId,
							selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
								console.log("data3 " + data);
						$scope.gridOptions.data = data.content;
						$scope.gridOptions.totalItems = data.totalElements;

		 	 	   });
		 	    }
		    };

		    TaxCalculationService.getUsers(paginationOptions.pageNumber,
				paginationOptions.pageSize, counttype,
				selectedCircelId,selectedStateId,
				selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
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
	          { name: 'rfpRefNumber', displayName: 'RFP REF_NO.'  },
	          { name: 'vendor', displayName: 'VENDOR' },
	          { name: 'circleName', displayName: 'CIRCLE'  },
	          { name: 'state', displayName: 'STATE'  },
	          { name: 'invoiceAmount', displayName: 'AMC AMOUNT'},
	          { name: 'penaltyAmount', displayName: 'PENALTY'  },
	          { name: 'gstType', displayName: 'TYPE of GST'  },
	          { name: 'gst', displayName: 'GST %'  },
	          { name: 'amcGst', displayName: 'GST ON AMC AMOUNT (A)'},
	          { name: 'penGst', displayName: 'GST ON PENALTY (B)' },
	          { name: 'toatalGST', displayName: 'TOTAL GST (A+B)'}
	        
	          
	    ],
	    onRegisterApi: function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
	          paginationOptions.pageNumber = newPage;
	          paginationOptions.pageSize = pageSize;
	          TaxCalculationService.getUsers(paginationOptions.pageNumber,
						paginationOptions.pageSize, counttype,
						selectedCircelId,selectedStateId,
						selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,selectedGST,selectedGSTType,selectedSGST,selectedCGST).success(function(data) {
							console.log("data4 " + data);
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;

	          });
	        });
	     }
	  };
	   
	   
	   $scope.changeInRFP = function(SelectedCircelId,SelectedStateId,
				SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId){
		   
		   console.log("selectedCircelId " + SelectedCircelId);
			console.log("selectedStateId " + SelectedStateId);
			console.log("selectedQuarterId " + SelectedQuarterId);
			console.log("selectedYearId " + SelectedYearId);
			console.log("selectedVendorId " + SelectedVendorId);
			console.log("quterTimePeid " + RfpId);
			
			if(SelectedCircelId==undefined || SelectedStateId == undefined 
					|| SelectedQuarterId == undefined || SelectedVendorId == undefined ||
					SelectedCircelId=='' || SelectedStateId == '' 
					|| SelectedQuarterId == '' || SelectedVendorId == ''){
				
			}else{
				
			
		   
		   
		   
		   var circle = "";
			var state ="";
			var vendor ="";
		   var objArr = angular.fromJson($scope.Circles).filter(function(item) {
			    if (item.circleCode === SelectedCircelId) {
			    	circle = item.circleName;
			   
			        return true;
			    }
			});
		   
		   var objArr2 = angular.fromJson($scope.States).filter(function(item) {
			    if (item.statCode === SelectedStateId) {
			    	state = item.statDesc;
			   
			        return true;
			    }
			});
		   
		   var objArr3 = angular.fromJson($scope.Vendors).filter(function(item) {
			    if (item.vendorId == SelectedVendorId) {
			    	vendor = item.vendor;
			   
			        return true;
			    }
			});
		   
		   
		   TaxInsert.getduplicate(circle,state,
				   SelectedQuarterId,SelectedYearId,vendor,RfpId).then(function (d) {
					   var status = 0;
					   if(d.data>0){
						/*   
						   $confirm({text: 'Are you sure you want to update?', title: 'Update it', ok: 'Yes', cancel: 'No'})
					        .then(function() {
					        	alert("Already present " + d.data);
					        	
					        	status = 1;
					        });*/
						   
						   
						   if ($window.confirm("The Tax calculation for the given criteria has already been submitted. Do you wish to override?")) {
			                    
			                } else {
			                	$scope.resetPositions();
			                }
						   
					   }
					   
					   
					  /* if(status == 0){
						   $scope.resetPositions();
						   
					   }*/
			
			
	            		   
					   
					  
	        	console.log("Successfully Added " + d.status);
	        	console.log("Successfully Added " + d.data);
	        	
	        	//alert("Already present " + d.data);
	       
	        }, function (d) {
	        	alert("Failed to Add");
	        	$scope.loadHomeBodyPageForm();
	        	// $window.location.reload();
	        	 
	        });
		   
			}
	   };
	   
	   
	   
	   
	}]);


	app.service('TaxCalculationService',['$http', function ($http) {
		
		function getUsers(pageNumber,size,counttype,selectedCircelId,selectedStateId,
				selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,
				selectedGST,selectedGSTType,selectedSGST,selectedCGST) {
			pageNumber = pageNumber > 0?pageNumber - 1:0;
	        return  $http({
	          method: 'GET',
	          url: 'taxCalculation/get?page='+pageNumber+
	     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
	          '&selectedQuarterId='+selectedQuarterId+'&selectedYearId='+selectedYearId+'&selectedVendorId='+selectedVendorId+'&selectedRfpID='+selectedRfpID+
	          '&selectedGST='+selectedGST+'&selectedGSTType='+selectedGSTType+
	          '&selectedSGST='+selectedSGST+'&selectedCGST='+selectedCGST
	         
	        });
	     
	       // quterTimePeriod=(selectedQuarterId.toUpperCase())+'-'+selectedYearId;
			
	    }
	    return {
	    	getUsers:getUsers
	    };
		
	}]);
	
	
app.service('TaxInsert',['$http', function ($http) {
		
	function getUsers(pageNumber,size,counttype,selectedCircelId,selectedStateId,
			selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID,
			selectedGST,selectedGSTType,selectedSGST,selectedCGST) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'taxCal/insert?page='+pageNumber+
     '&size='+size+'&type='+counttype+'&selectedCircelId='+selectedCircelId+'&selectedStateId='+selectedStateId+
          '&selectedQuarterId='+selectedQuarterId+'&selectedYearId='+selectedYearId+'&selectedVendorId='+selectedVendorId+'&selectedRfpID='+selectedRfpID+
          '&selectedGST='+selectedGST+'&selectedGSTType='+selectedGSTType+
          '&selectedSGST='+selectedSGST+'&selectedCGST='+selectedCGST
         
        });
     
       
		
    }
	
	
	
	function getduplicate(selectedCircelId,selectedStateId,
			selectedQuarterId,selectedYearId,selectedVendorId,selectedRfpID) {
	
        return  $http({
          method: 'GET',
          url: 'taxCal/insert?selectedCircelId='+selectedCircelId+
          '&selectedStateId='+selectedStateId+
          '&selectedQuarterId='+selectedQuarterId+
          '&selectedYearId='+selectedYearId+
          '&selectedVendorId='+selectedVendorId+
          '&selectedRfpID='+selectedRfpID
        });
     
       
		
    }
    return {
    	getUsers:getUsers,
    	getduplicate:getduplicate
    };
		
	}]);

app.directive('ngConfirmClick', [
    function(){
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                if(scope.gstType=='IGST'){

                    msg = msg + '\n'+
                    'GST TYPE - IGST '+ '\n'+'IGST  ' + scope.gstIGST;

                }else{
                      msg = msg + '\n'+
                    'GST TYPE - SGST/CGST  '+ '\n'+'CGST ' + scope.gstCGST+ '\n'
                    +'SGST  ' + scope.gstSGST;

                }
               
                var clickAction = attr.confirmedClick  
                element.bind('click',function (event) {
                    if ( window.confirm(msg) ) {
                    	scope.$eval(clickAction)
                    }
                });
            }
        };
}])
