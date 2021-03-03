var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

	app.controller('SanctionRequestCtrl', ['$scope','$filter','$http','$window','SanctionFormService','formService',
		function ($scope, $filter, $http, $window,SanctionFormService,formService) {
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
	   };
	   
	   $scope.resetQuarter=function(){
	   		$scope.SelectedQuarterId='';
	   	 }
	   
	   $scope.loadHomeBodyPageForm = function(){	
			var str ='bp/snrequest'
			$("#contentHomeApp").load(str);
								
	}
	   
	   $scope.yearOptions = [
	   	    { name: '2020-2021', value: '2020-2021' }, 
	   	    { name: '2021-2022', value: '2021-2022' }, 
	   	    { name: '2022-2023', value: '2022-2023' }
	   	    ];
	   	    
	   	    $scope.SelectedYearId = $scope.yearOptions[0].value;
	   
	   
	   $scope.recommentingAuth = [
		   { name: 'Chief Manager' , value: 'Chief Manager' }, 
	   	    { name: 'Asst. General Manager', value: 'Asst. General Manager' }, 
	   	    { name: 'Dy. General Manager' , value: 'Dy. General Manager' },
	   	    { name: 'General Manager', value: 'General Manager' },
	   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
	   	    ];
	   
	   $scope.LoadSanctionAu = function(slectedRecom){
		   
		   if(slectedRecom=='Chief Manager'){
			   $scope.sanctionAuth = [
			   	    { name: 'Chief Manager' , value: 'Chief Manager' }, 
			   	    { name: 'Asst. General Manager', value: 'Asst. General Manager' }, 
			   	    { name: 'Dy. General Manager' , value: 'Dy. General Manager' },
			   	    { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
			   $scope.controllingAuth = [
			   	  
				   { name: 'Asst. General Manager', value: 'Asst. General Manager' }, 
			   	    { name: 'Dy. General Manager' , value: 'Dy. General Manager' },
			   	    { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
		   }else if(slectedRecom=='Asst. General Manager'){
			   $scope.sanctionAuth = [
				   { name: 'Asst. General Manager', value: 'Asst. General Manager' }, 
			   	    { name: 'Dy. General Manager' , value: 'Dy. General Manager' },
			   	    { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
			   $scope.controllingAuth = [
				   { name: 'Dy. General Manager' , value: 'Dy. General Manager' },
			   	    { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
		   }else if(slectedRecom=='Dy. General Manager'){
			   
			   $scope.sanctionAuth = [
				   { name: 'Dy. General Manager' , value: 'Dy. General Manager' },
			   	    { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   $scope.controllingAuth = [
				   { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
		   }else if(slectedRecom=='General Manager'){
			   $scope.sanctionAuth = [
				   { name: 'General Manager', value: 'General Manager' },
			   	    { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
			   $scope.controllingAuth = [
			  
				   { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
			   
		   }else if(slectedRecom=='Chief General Manager'){
			   
			   $scope.sanctionAuth = [
				   { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
			   $scope.controllingAuth = [
					  
				   { name: 'Chief General Manager', value: 'Chief General Manager' }
			   	    ];
			   
			   
		   }
		   
	   }
	   
	   

	 $scope.cancel= function () {
			   var str ='bp/snrequest'
					$("#contentHomeApp").load(str);
			   
		   };
		   
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
	   
	   
	 /*  $scope.vendorChange = function(SelectedVendorId,SelectedStateId,SelectedCircelId){
		   
		   console.log("SelectedVendorId "+SelectedVendorId);
		   console.log("SelectedStateId "+SelectedStateId);
		   console.log("SelectedCircelId "+SelectedCircelId);
		   formService.getDetails(SelectedVendorId,SelectedStateId,SelectedCircelId).then(function (d) {
	        	 $scope.selectedNoKiosk=d.data;
	        	alert(d.data);
	        	//$scope.loadHomeBodyPageForm();
	        }, function (d) {
	        	alert("Failed to Add");
	        //	$scope.loadHomeBodyPageForm();
	        	// $window.location.reload();
	        	 
	        });
		   
	   }*/
	   
	   
	   $scope.periodChange= function(SelectedCircelId,SelectedStateId,SelectedVendorId,
			   SelectedYearId,SelectedQuarterId) {
		   
		   var year1 = SelectedYearId.substring(0, 4);
		   var year2 = SelectedYearId.substring(5, 9);
		   
		   if(SelectedQuarterId=='Q1'){
			   $scope.selectedfromDate="01-04-"+year1;
			   $scope.selectedToDate="30-06-"+year1;
			   
		   }else if(SelectedQuarterId=='Q2'){
			   $scope.selectedfromDate="01-07-"+year1;
			   $scope.selectedToDate="30-09-"+year1;
			   
		   }else if(SelectedQuarterId=='Q3'){
			   $scope.selectedfromDate="01-10-"+year1;
			   $scope.selectedToDate="31-12-"+year1;
			   
		   }else if(SelectedQuarterId=='Q4'){
			   
			   $scope.selectedfromDate="01-01-"+year2;
			   $scope.selectedToDate="31-03-"+year2;
		   }
		   var circle = "";
   		var state ="";
   		var vendor ="";
   	   var objArr = angular.fromJson($scope.Circles).filter(function(item) {
   		    if (item.circleCode === $scope.SelectedCircelId) {
   		    	circle = item.circleName;
   		   
   		        return true;
   		    }
   		});
   	   
   	   var objArr2 = angular.fromJson($scope.States).filter(function(item) {
   		    if (item.statCode === $scope.SelectedStateId) {
   		    	state = item.statDesc;
   		   
   		        return true;
   		    }
   		});
   	   
   	   var objArr3 = angular.fromJson($scope.Vendors).filter(function(item) {
   		    if (item.vendorId == $scope.SelectedVendorId) {
   		    	vendor = item.vendor;
   		   
   		        return true;
   		    }
   		});
   	   
   	   console.log("circle "+ circle);
   	   console.log("state "+ state);
   	   console.log("vendor "+ vendor);
		   
		   
		   formService.getInvoiceDetails(vendor,state,circle,
				   SelectedYearId,SelectedQuarterId).then(function (d) {
						
					   console.log("Results " + d.data.invoiceAmount);
					   console.log("Result" + d.data.penaltyAmount);
					   
					   if(d.data.invoiceAmount==undefined || d.data.invoiceAmount=='' || d.data.invoiceAmount==null){
						  
						   alert("The Tax calculation for the given criteria not found. Please submit");
						   var str ='bp/getFormRequest'
								$("#contentHomeApp").load(str);
					   }else{
						   
					   
					   
					   $scope.selectedSInvoiceAmt=d.data.invoiceAmount;
					   $scope.selectedSPenaltyAmt=d.data.penaltyAmount;
					   $scope.SelectedIGSTType=d.data.gstType;
					
					   var gstType = d.data.gstType;
					   
					   if(gstType=="IGST"){
						   $('.hideIGST').show();
				    		  $('.hideGST').hide();
						   $scope.selectedIGST= d.data.gst;
						   
					   }else {
						   $('.hideIGST').hide();
				    		  $('.hideGST').show();
						   $scope.selectedSGST= d.data.sgst;
						   $scope.selectedCGST= d.data.cgst;
						   
					   }
					   
					   } 
	           
					 
	        	// $scope.selectedNoKiosk=d.data;
	        	
	        	//alert(d.data);
	        	
	        	
	       
	        }, function (d) {
	        	alert("Failed to Add");
	        //	$scope.loadHomeBodyPageForm();
	        	// $window.location.reload();
	        	 
	        });
		   
		   formService.getDetails(vendor,SelectedStateId,SelectedCircelId,
				   SelectedYearId,SelectedQuarterId).then(function (d) {
	        	 $scope.selectedNoKiosk=d.data;
	        	//alert(d.data);
	        	//$scope.loadHomeBodyPageForm();
	        }, function (d) {
	        	alert("Failed to Add");
	        //	$scope.loadHomeBodyPageForm();
	        	// $window.location.reload();
	        	 
	        });
			   
		   
		   
	   }
	   
 /* $scope.stateChange = function(SelectedVendorId,SelectedStateId,SelectedCircelId){
	  
	  $scope.SelectedVendorId="";
		   
		   console.log("SelectedVendorId "+SelectedVendorId);
		   console.log("SelectedStateId "+SelectedStateId);
		   console.log("SelectedCircelId "+SelectedCircelId);
		   
		   formService
		   
		   
		   formService.getDetails(SelectedVendorId,SelectedStateId,SelectedCircelId).then(function (d) {
	           
	        	 $scope.selectedNoKiosk=d.data;
	        	
	        	alert(d.data);
	        	//$scope.loadHomeBodyPageForm();
	        	
	       
	        }, function (d) {
	        	alert("Failed to Add");
	        //	$scope.loadHomeBodyPageForm();
	        	// $window.location.reload();
	        	 
	        });
		   
	   }*/
	   
	   
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
							$scope.resetQuarter();
						
							console.log("data...." +data)
						}).error(function(data, status) {
							console.log("error....." + value)
							//$window.alert(data.Message);
						});
						
					}
					break;
				
				}
			}).error(function(data, status) {
				console.log("error1....." + value)
				//$window.alert(data.Message);
			});
		}; 	   
		   
		   
		   
		 $scope.LoadDropDown('', 0);
	     
	       $scope.LoadVendor();
	       
	       
	       
	       $scope.submit = function(){
	    	   
	    	   var sanNoteDate= $("#sanNoteDate").val();
	    	   var invoiceDate= $("#invoiceDate").val();
	    	   var fromDate= $("#fromDate").val();
	    	   var toDate= $("#toDate").val();
	    	   var receiptDate= $("#receiptDate").val();
	    	   var circularDate= $("#circularDate").val();
	    	   
	    	
	    	   
	    	   console.log("sanNoteDate "+ sanNoteDate);
	    	   console.log("invoiceDate "+ invoiceDate);
	    	   console.log("fromDate "+ fromDate);
	    	   console.log("toDate "+ toDate);
	    	   console.log("circularDate "+ circularDate);
	    	   
	    	   if(sanNoteDate=='' || sanNoteDate==undefined){
	    		   
	    		   alert("Sanction note date is required!");
	    		   
	    	   }else if(invoiceDate=='' || invoiceDate==undefined){
	    		   alert("Invoice date is required!");
	    		   
	    	   }else if(receiptDate=='' || receiptDate==undefined){
	    		   alert("Receipt date is required!");
	    	   }else  if(circularDate=='' || circularDate==undefined) {
	    		   alert("Circular date is required!");
	    	   }else{
	    		   
	    	   
	    	   
	    	   var dateParts = sanNoteDate.split("-");
	    	   var  sanDate= new Date(dateParts[2]+"-"+dateParts[1]+"-"+dateParts[0]);
	    	   
	    	   var dateParts2 = invoiceDate.split("-");
	    	   var  invDate= new Date(dateParts2[2]+"-"+dateParts2[1]+"-"+dateParts2[0]);
	    	   
	    	   
	    	   var dateParts3 = fromDate.split("-");
	    	   var  frDate= new Date(dateParts3[2]+"-"+dateParts3[1]+"-"+dateParts3[0]);
	    	   
	    	   console.log("dateParts3[2] "+ dateParts3[2]);
	    	   console.log("dateParts3[1] "+ dateParts3[1]);
	    	   console.log("dateParts3[0] "+ dateParts3[0]);
	    	   
	    	   var dateParts4 = toDate.split("-");
	    	   var  tDate= new Date(dateParts4[2]+"-"+dateParts4[1]+"-"+dateParts4[0]);
	    	   
	    	   console.log("dateParts4[2] "+ dateParts4[2]);
	    	   console.log("dateParts4[1] "+ dateParts4[1]);
	    	   console.log("dateParts4[0] "+ dateParts4[0]);
	    	   
	    	   var dateParts5 = receiptDate.split("-");
	    	   var  recpDate= new Date(dateParts5[2]+"-"+dateParts5[1]+"-"+dateParts5[0]);
	    	   
	    	   
	    	   var dateParts6 = circularDate.split("-");
	    	   var  crclDate= new Date(dateParts6[2]+"-"+dateParts6[1]+"-"+dateParts6[0]);
	    	   
	    	   var circle = "";
	    		var state ="";
	    		var vendor ="";
	    	   var objArr = angular.fromJson($scope.Circles).filter(function(item) {
	    		    if (item.circleCode === $scope.SelectedCircelId) {
	    		    	circle = item.circleName;
	    		   
	    		        return true;
	    		    }
	    		});
	    	   
	    	   var objArr2 = angular.fromJson($scope.States).filter(function(item) {
	    		    if (item.statCode === $scope.SelectedStateId) {
	    		    	state = item.statDesc;
	    		   
	    		        return true;
	    		    }
	    		});
	    	   
	    	   var objArr3 = angular.fromJson($scope.Vendors).filter(function(item) {
	    		    if (item.vendorId == $scope.SelectedVendorId) {
	    		    	vendor = item.vendor;
	    		   
	    		        return true;
	    		    }
	    		});
	    	   
	    	   console.log("circle "+ circle);
	    	   console.log("state "+ state);
	    	   console.log("vendor "+ vendor);
	    	   
	    	   
	    	   
	    	   console.log("SelectedSanctionAu "+ $scope.SelectedSanctionAu);
	    	   console.log("SelectedControllingAu "+ $scope.SelectedControllingAu);
	    	   console.log("SelectedCircelId "+ $scope.SelectedCircelId);
	    	   console.log("SelectedStateId "+ $scope.SelectedStateId);
	    	   console.log("SelectedVendorId "+ $scope.SelectedVendorId);
	    	   console.log("selectedSanNo "+ $scope.selectedSanNo);
	    	   console.log("selectedSanNoteDate "+sanDate);
	    	   console.log("selectedSInvoiceNo "+ $scope.selectedSInvoiceNo);
	    	   console.log("selectedinvDate "+ invDate);
	    	   console.log("selectedfromDate "+ frDate);
	    	   console.log("selectedToDate "+ tDate);
	    	   console.log("selectedReceiptDate "+ recpDate);
	    	   console.log("selectedSInvoiceAmt "+ $scope.selectedSInvoiceAmt);
	    	   console.log("selectedSPenaltyAmt "+ $scope.selectedSPenaltyAmt);
	    	   console.log("SelectedIGSTType "+ $scope.SelectedIGSTType);
	    	   console.log("selectedIGST "+ $scope.selectedIGST);
	    	   console.log("selectedSGST "+ $scope.selectedSGST);
	    	   console.log("selectedCGST "+ $scope.selectedCGST);
	    	   console.log("selectedTdsPer "+ $scope.selectedTdsPer);
	    	   console.log("selectedGstTds "+ $scope.selectedGstTds);
	    	   console.log("selectedGstTdsPer "+ $scope.selectedGstTdsPer);
	    	  // console.log("selectedAmtW "+ $scope.selectedAmtW);
	    	   console.log("selectedME "+ $scope.selectedME);
	    	   console.log("selectedME "+ $scope.selectedNE);
	    	   console.log("selectedNoKiosk "+ $scope.selectedNoKiosk);
	    	   console.log("selectedNoKiosk "+ $scope.selectedSanLimitAmt);
	    	   console.log("selectedCircularNo "+ $scope.selectedCircularNo);
	    	   console.log("selectedCircularDate "+ crclDate);
	    	   
	    	   
	    	   
	    	   var payload = {};
	    	   
	    	  
	    	   if($scope.SelectedIGSTType == 'IGST'){
	    		   var invoiceGstAmount = ($scope.selectedSInvoiceAmt * $scope.selectedIGST)/100;
		    	   console.log("invoiceGstAmount "+ invoiceGstAmount);
		    	   var penGstAmount = ($scope.selectedSPenaltyAmt * $scope.selectedIGST)/100;
		    	   console.log("penGstAmount "+ penGstAmount);
		    	   var tdsAmount = 0.0;
		    	   var tdsGstAmount=0.0;
		    	   console.log("selectedNE "+ $scope.selectedNE);
		    	   
		    	   if($scope.selectedNE==undefined || $scope.selectedNE=='' || $scope.selectedNE==null){
		    		   
		    		
		    		   
		    		   tdsAmount = ($scope.selectedSInvoiceAmt * $scope.selectedTdsPer)/100;
			    	   console.log("tdsAmount "+ tdsAmount);
			    	   
			    	   if($scope.selectedSInvoiceAmt>250000){
			    		   tdsGstAmount = ( $scope.selectedSInvoiceAmt * $scope.selectedGstTdsPer)/100; 
			    	   }
			    	   
			    	   console.log("tdsGstAmount "+ tdsGstAmount);
		    		   
		    	   }else{
		    		   
		    		   tdsAmount = (($scope.selectedSInvoiceAmt - $scope.selectedSPenaltyAmt) * $scope.selectedTdsPer)/100;
			    	   console.log("tdsAmount "+ tdsAmount);
			    	   if($scope.selectedSInvoiceAmt>250000){
			    	   tdsGstAmount = (($scope.selectedSInvoiceAmt - $scope.selectedSPenaltyAmt) * $scope.selectedGstTdsPer)/100;
			    	   
			    	   }
			    	   console.log("tdsGstAmount "+ tdsGstAmount);
		    		   
		    	   }
		    	   
		    	   payload = {sanctionAuth:$scope.SelectedSanctionAu,
		    			   ctlrAuth:$scope.SelectedControllingAu,
		    			   recomAuth:$scope.SelectedRecommdAu,
		    			   circle:circle,
		    			   state:state,
		    			   vendor:vendor,
		    			   sanNoteDt:sanDate,
		    			   invoiceNo:$scope.selectedSInvoiceNo,
		    			   invoiceDt:invDate,
		    			   invFr:frDate,
		    			   invTo:tDate,
		    			   invoiceAmt:$scope.selectedSInvoiceAmt,
		    			   receiptDt:recpDate,
		    			   gstType:$scope.SelectedIGSTType,
		    			   igst:$scope.selectedIGST,
		    			   sgst:null,
		    			   cgst:null,
		    			   penaltyAmt:$scope.selectedSPenaltyAmt,
		    			   gstInvoiceAmt:invoiceGstAmount,
		    			   gstPenaltyAmt:penGstAmount,
		    			   tdsPct: $scope.selectedTdsPer,
		    			   tdsAmt:tdsAmount,
		    			   gstTdsLimitAmt:$scope.selectedGstTds,
		    			   gstTdsPer: $scope.selectedGstTdsPer,
		    			   gstTdsAmt:tdsGstAmount,
		    			   //amtWords:$scope.selectedAmtW,
		    			   manualEntry:$scope.selectedME,
		    			   creditEntry:$scope.selectedNE,
		    			   noOfKiosk:$scope.selectedNoKiosk,
		    			   quarter:$scope.SelectedQuarterId,
		    			   year:$scope.SelectedYearId,
		    			   sanLimit:$scope.selectedSanLimitAmt,
		    			   circularNo:$scope.selectedCircularNo,
		    			   circularDate:crclDate,
		    			   circularSlNo:$scope.selectedSlno
		    			   
		    	   };
		    	   
		    	   
		    	   
	    	   }else{
	    		   
	    		   var invoiceCGstAmount = ($scope.selectedSInvoiceAmt * $scope.selectedCGST)/100;
		    	   console.log("invoiceCGstAmount "+ invoiceCGstAmount);
		    	   var invoiceSGstAmount = ($scope.selectedSInvoiceAmt * $scope.selectedSGST)/100;
		    	   console.log("invoiceCGstAmount "+ invoiceCGstAmount);
		    	   
		    	   var invoiceGstAmount =invoiceCGstAmount + invoiceSGstAmount;
		    	   console.log("invoiceGstAmount "+ invoiceGstAmount);
		    	   
		    	   console.log("selectedSInvoiceAmt "+ $scope.selectedSInvoiceAmt);
		    	   
		    	   var penCGstAmount = ($scope.selectedSPenaltyAmt * $scope.selectedCGST)/100;
		    	   var penSGstAmount = ($scope.selectedSPenaltyAmt * $scope.selectedSGST)/100;
		    	   
		    	   var penGstAmount =penSGstAmount+penCGstAmount;
		    	   console.log("penGstAmount "+ penGstAmount);
		    	  /* var tdsAmount = ($scope.selectedSInvoiceAmt * $scope.selectedTdsPer)/100;
		    	   console.log("tdsAmount "+ tdsAmount);
		    	   var tdsGstAmount = ($scope.selectedSInvoiceAmt * $scope.selectedGstTdsPer)/100;
		    	   console.log("tdsGstAmount "+ tdsGstAmount);*/
		    	   
		    	   var tdsAmount = 0.0;
		    	   var tdsGstAmount=0.0;
		    	   
		    	   console.log("selectedNE "+ $scope.selectedNE);
		    	   
		    	   if($scope.selectedNE==undefined || $scope.selectedNE=='' || $scope.selectedNE==null){
		    		   
		    		   tdsAmount = ($scope.selectedSInvoiceAmt * $scope.selectedTdsPer)/100;
			    	   console.log("tdsAmount "+ tdsAmount);
			    	   if($scope.selectedSInvoiceAmt>250000){
			    	   tdsGstAmount = ( $scope.selectedSInvoiceAmt * $scope.selectedGstTdsPer)/100;
			    	   
			    	   }
			    	   console.log("tdsGstAmount "+ tdsGstAmount);
		    		   
		    	   }else{
		    		   
		    		   tdsAmount = (($scope.selectedSInvoiceAmt - $scope.selectedSPenaltyAmt) * $scope.selectedTdsPer)/100;
			    	   console.log("tdsAmount "+ tdsAmount);
			    	   
			    	   if($scope.selectedSInvoiceAmt>250000){
			    	   tdsGstAmount = (($scope.selectedSInvoiceAmt - $scope.selectedSPenaltyAmt) * $scope.selectedGstTdsPer)/100;
			    	   }
			    	   console.log("tdsGstAmount "+ tdsGstAmount);
		    		   
		    	   }
		    	   
		    	   
		    	   payload = {sanctionAuth:$scope.SelectedSanctionAu,
		    			   ctlrAuth:$scope.SelectedControllingAu,
		    			   recomAuth:$scope.SelectedRecommdAu,
		    			   circle:circle,
		    			   state:state,
		    			   vendor:vendor,
		    			   sanNoteDt:sanDate,
		    			   invoiceNo:$scope.selectedSInvoiceNo,
		    			   invoiceDt:invDate,
		    			   invFr:frDate,
		    			   invTo:tDate,
		    			   invoiceAmt:$scope.selectedSInvoiceAmt,
		    			   receiptDt:recpDate,
		    			   gstType:$scope.SelectedIGSTType,
		    			   igst:null,
		    			   sgst:$scope.selectedSGST,
		    			   cgst:$scope.selectedCGST,
		    			   penaltyAmt:$scope.selectedSPenaltyAmt,
		    			   gstInvoiceAmt:invoiceGstAmount,
		    			   gstPenaltyAmt:penGstAmount,
		    			   tdsPct: $scope.selectedTdsPer,
		    			   tdsAmt:tdsAmount,
		    			   gstTdsLimitAmt:$scope.selectedGstTds,
		    			   gstTdsPer: $scope.selectedGstTdsPer,
		    			   gstTdsAmt:tdsGstAmount,
		    			  // amtWords:$scope.selectedAmtW,
		    			   manualEntry:$scope.selectedME,
		    			   creditEntry:$scope.selectedNE,
		    			   noOfKiosk:$scope.selectedNoKiosk,
		    			   quarter:$scope.SelectedQuarterId,
		    			   year:$scope.SelectedYearId,
		    			   sanLimit:$scope.selectedSanLimitAmt,
		    			   circularNo:$scope.selectedCircularNo,
		    			   circularDate:crclDate,
		    			   circularSlNo:$scope.selectedSlno
		    			   
		    	   };
	    		   
	    		   
	    	   }
	    	 //  alert("Done " + payload.data);
	    	   console.log("Session CSRF "+ $scope.csrf);
	    	   
	    	   var encodedString = btoa(JSON.stringify(payload));
	    	   
	    	   SanctionFormService.addSubmit(encodedString,$scope.csrf).then(function (d) {
		            
		        	console.log("Successfully Added " + d.status);
		        	console.log("Successfully Added " + d.data.status);
		        	
		        	alert(d.data.status);
		        	$scope.loadHomeBodyPageForm();
		        	
		       
		        }, function (d) {
		        	alert("Failed to Add");
		        //	$scope.loadHomeBodyPageForm();
		        	// $window.location.reload();
		        	 
		        });
	    	   } 
	       }
	}]);
	
	
app.service('formService',['$http', function ($http) {
		
		
		function getDetails(SelectedVendorId,SelectedStateId,SelectedCircelId,
				SelectedYearId,SelectedQuarterId) {
			
	        return  $http({
	          method: 'GET',
	          url: 'formDetails/get?SelectedVendorId='+SelectedVendorId+
	          '&SelectedStateId='+SelectedStateId+'&SelectedCircelId='+SelectedCircelId+
	          '&selectedYear='+SelectedYearId+'&selectedQtr='+SelectedQuarterId
	         
	        });
	    }
		
		
		function getInvoiceDetails(SelectedVendorId,SelectedStateId,SelectedCircelId,
				SelectedYearId,SelectedQuarterId) {
			
	        return  $http({
	          method: 'GET',
	          url: 'invoiceDetails/get?SelectedVendorId='+SelectedVendorId+
	          '&SelectedStateId='+SelectedStateId+'&SelectedCircelId='+SelectedCircelId+
	          '&SelectedYearId='+SelectedYearId+'&SelectedQuarterId='+SelectedQuarterId
	         
	        });
	    }
		
		
		function generatePdf(SelectedVendorId) {
			
	        return  $http({
	          method: 'GET',
	          url: 'generate/sanctionPdf?SelectedVendorId='+SelectedVendorId
	        });
	    }
		
		
	    return {

	    	getDetails:getDetails,
	    	getInvoiceDetails:getInvoiceDetails,
	    	generatePdf:generatePdf
	    };
		
	}]);
	
	
	
	
	app.factory('SanctionFormService', function ($http) {
	    var res = {};
	   // var add={};
		 

		
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


	