var app = angular.module('daCumulativeDataCCModule', ['chart.js',]);

app.controller('daCumulativeDataCCController', ['$scope','$interval','$http','daCumulativeDataCCService1','daCumulativeDataCCService2',
	'daCumulativeDataCCService3','daCumulativeDataCCService4','daCumulativeDataCCService5',
	function ($scope, $interval, $http, daCumulativeDataCCService1, daCumulativeDataCCService2, daCumulativeDataCCService3, 
			daCumulativeDataCCService4, daCumulativeDataCCService5) {

	this.$onInit = function() {
	    // this will kill all intervals and timeouts too in 1 seconds. 
	    var killId = setTimeout(function() {
	      for (var i = killId; i > 0; i--) clearInterval(i)
	    }, 1000);
    };
    
  //getting auto refresh time value from property file
	$http({
		method: 'GET',
		url: 'da/getChartAutoRefreshTime'
		}).then(function success(response) {
			$scope.autoRefreshTime=response.data;
			
			var id1 = setInterval(function() { 
				callDaCumulativeDataCCService1();
				callDaCumulativeDataCCService2();
				callDaCumulativeDataCCService3();
				callDaCumulativeDataCCService4();
				callDaCumulativeDataCCService5();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});

	//Start of loadAvailabilityApiData service
	callDaCumulativeDataCCService1();
	function callDaCumulativeDataCCService1(){
	daCumulativeDataCCService1.loadAvailabilityApiData().success(function(response){
		$scope.apiResponse1 = response;
	    $scope.sumOfOperationalKiosks1=0;
		$scope.sumOfNonOperationalKiosks1=0;
		$scope.sumOfAllKiosks1=0;
		$scope.operationalKiosksPercent1=0;
		$scope.nonOperationalKiosksPercent1=0;
		$scope.doughnutData1=[];
		$scope.options1=[];
		$scope.grandTotal1=[];
		$scope.nonNullCounter1=0;
		$scope.nonNullResponseLength1=0;
		
	   //Buiding Data & Options for Chart
		buildDoughnutDataAndOptions($scope);
		
		function buildDoughnutDataAndOptions($scope){

			$scope.options1.push({
			    legend: {
			        display: true,
			        position: 'right'
			      },
			      cutoutPercentage: 60,
			      tooltipEvents: [],
			      tooltipCaretSize: 0,
			      showTooltips: true,
			      onAnimationComplete: function() {
			        this.showTooltip(this.segments, true);
			      },
			      plugins: {
			          labels: {
			            render: 'percentage',
			            fontColor: ['black','black'],
			            precision: 2,
			            // fontSize: 12,
			            fontStyle: "bold"
			          }
			        },
			        tooltips: {
				          callbacks: {
				              label: function(tooltipItem, data) {
				                    var label = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] || '';
				                    if (label) {
				                        label = label.toFixed(2);
				                    }
				                    return label;
				              }
				          }
				     }
			    });
			
			  $scope.labels1 = ["Total Operational Kiosks", "Total Non-Operational Kiosks"];
			  $scope.colors1=['#A0B421','#ED402A'];

			
			for(var i=0; i<$scope.apiResponse1.length; i++){
				if ($scope.apiResponse1[i] !== null) {
					$scope.sumOfOperationalKiosks1 = $scope.sumOfOperationalKiosks1 + $scope.apiResponse1[i].totalOperationalKiosks;
					$scope.sumOfAllKiosks1 = $scope.sumOfAllKiosks1 + $scope.apiResponse1[i].totalKiosks;
					$scope.nonNullCounter1++;
				}
			}//end for loop
			$scope.nonNullResponseLength1 = $scope.nonNullCounter1;
			$scope.sumOfNonOperationalKiosks1 = $scope.sumOfAllKiosks1 - $scope.sumOfOperationalKiosks1;
			
			//Calculating the Operational & Non-Operational Percentage
			$scope.operationalKiosksPercent1 = ($scope.sumOfOperationalKiosks1 / $scope.sumOfAllKiosks1) * 100;
			$scope.nonOperationalKiosksPercent1 = ($scope.sumOfNonOperationalKiosks1 / $scope.sumOfAllKiosks1) * 100;
			
			//Preparing Data to display in chart
			let rowData=[$scope.operationalKiosksPercent1, $scope.nonOperationalKiosksPercent1];
			$scope.doughnutData1.push({rowData});
			
			$scope.apiResponse1.push({gtLabel:'Grand Total', gtOperationalKiosks:$scope.sumOfOperationalKiosks1, gtTotalKiosks:$scope.sumOfAllKiosks1, gtAvailabilityPercent:$scope.operationalKiosksPercent1});
		}
   });
}//End of loadAvailabilityApiData service
	
	//Start of loadVendorWiseUptimeApiData service
	callDaCumulativeDataCCService2();
	function callDaCumulativeDataCCService2(){
	daCumulativeDataCCService2.loadVendorWiseUptimeApiData().success(function(response){	   
		$scope.apiResponse2 = response;	
	    $scope.sumOfOperationalKiosks2=0;
		$scope.sumOfNonOperationalKiosks2=0;
		$scope.sumOfAllKiosks2=0;
		$scope.operationalKiosksPercent2=0;
		$scope.nonOperationalKiosksPercent2=0;
		$scope.doughnutData2=[];
		$scope.options2=[];

   //Buiding Data & Options for Chart
	buildDoughnutDataAndOptions($scope);
	
	function buildDoughnutDataAndOptions($scope){

		$scope.options2.push({
		    legend: {
		        display: true,
		        position: 'right'
		      },
		      cutoutPercentage: 60,
		      tooltipEvents: [],
		      tooltipCaretSize: 0,
		      showTooltips: true,
		      onAnimationComplete: function() {
		        this.showTooltip(this.segments, true);
		      },
		      plugins: {
		          labels: {
		            render: 'percentage',
		            fontColor: ['black','black'],
		            precision: 2,
		            // fontSize: 12,
		            fontStyle: "bold"
		          }
		        },
		        tooltips: {
			          callbacks: {
			              label: function(tooltipItem, data) {
			                    var label = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] || '';
			                    if (label) {
			                        label = label.toFixed(2);
			                    }
			                    return label;
			              }
			          }
			     }
		    });
		
		  $scope.labels2 = ["Total Operational Kiosks", "Total Non-Operational Kiosks"];
		  $scope.colors2=['#A0B421','#ED402A'];

		
		for(var i=0; i<$scope.apiResponse2.length; i++){
			if ($scope.apiResponse2[i] !== null) {
				$scope.sumOfOperationalKiosks2 = $scope.sumOfOperationalKiosks2 + $scope.apiResponse2[i].totalOperationalKiosks;
				$scope.sumOfAllKiosks2 = $scope.sumOfAllKiosks2 + $scope.apiResponse2[i].totalKiosks;
			}
		}//end for loop
		
		$scope.sumOfNonOperationalKiosks2 = $scope.sumOfAllKiosks2 - $scope.sumOfOperationalKiosks2;
		
		
		//Calculating the Operational & Non-Operational Percentage
		$scope.operationalKiosksPercent2 = ($scope.sumOfOperationalKiosks2 / $scope.sumOfAllKiosks2) * 100;

		//Preparing Data to display in chart
		let rowData=[$scope.operationalKiosksPercent2, $scope.nonOperationalKiosksPercent2];
		$scope.doughnutData2.push({rowData});
		
		$scope.apiResponse2.push({gtLabel:'Grand Total', gtOperationalKiosks:$scope.sumOfOperationalKiosks2, gtTotalKiosks:$scope.sumOfAllKiosks2, gtAvailabilityPercent:$scope.operationalKiosksPercent2});
	}

	
	}); 
}//End of loadVendorWiseUptimeApiData service service

	//Start of loadErrorTypeWiseUptimeApiData service
	callDaCumulativeDataCCService3();
	function callDaCumulativeDataCCService3(){
	daCumulativeDataCCService3.loadErrorTypeWiseUptimeApiData().success(function(response){
			$scope.apiResponse3 = response;
		   	$scope.sumOfErrorWiseTotalOpenTickets3=0;
			$scope.sumOfErrorWiseTotalCloseTickets3=0;
			$scope.sumOfAllTickets3=0;
			$scope.errorWiseTotalOpenTicketsPercent3=0;
			$scope.errorWiseTotalCloseTicketsPercent3=0;
			$scope.doughnutData3=[];
			$scope.options3=[];

		   //Buiding Data & Options for Chart
			buildDoughnutDataAndOptions($scope);
			
			function buildDoughnutDataAndOptions($scope){

				$scope.options3.push({
				    legend: {
				        display: true,
				        position: 'right'
				      },
				      cutoutPercentage: 60,
				      tooltipEvents: [],
				      tooltipCaretSize: 0,
				      showTooltips: true,
				      onAnimationComplete: function() {
				        this.showTooltip(this.segments, true);
				      },
				      plugins: {
				          labels: {
				            render: 'percentage',
				            fontColor: ['black','black'],
				            precision: 2,
				            // fontSize: 12,
				            fontStyle: "bold"
				          }
				        },
				        tooltips: {
					          callbacks: {
					              label: function(tooltipItem, data) {
					                    var label = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] || '';
					                    if (label) {
					                        label = label.toFixed(2);
					                    }
					                    return label;
					              }
					          }
					     }
				    });
				
				  $scope.labels3 = ["Total Operational Kiosks", "Total Non-Operational Kiosks"];
				  $scope.colors3=['#A0B421','#ED402A'];

				
				for(var i=0; i<$scope.apiResponse3.length; i++){
					if ($scope.apiResponse3[i] !== null) {
						$scope.sumOfErrorWiseTotalOpenTickets3 = $scope.sumOfErrorWiseTotalOpenTickets3 + $scope.apiResponse3[i].errorWiseTotalOpenTickets;
						$scope.sumOfAllTickets3 = $scope.sumOfAllTickets3 + $scope.apiResponse3[i].noOfTickets;
					}
				}//end for loop
				
				$scope.sumOfErrorWiseTotalCloseTickets3 = $scope.sumOfAllTickets3 - $scope.sumOfErrorWiseTotalOpenTickets3;
				
				//Calculating the Operational & Non-Operational Percentage
				$scope.errorWiseTotalOpenTicketsPercent3 = ($scope.sumOfErrorWiseTotalOpenTickets3 / $scope.sumOfAllTickets3) * 100;
				$scope.errorWiseTotalCloseTicketsPercent3 = ($scope.sumOfErrorWiseTotalCloseTickets3 / $scope.sumOfAllTickets3) * 100;

				
				//Preparing Data to display in chart
				let rowData=[$scope.errorWiseTotalOpenTicketsPercent3, $scope.errorWiseTotalCloseTicketsPercent3];
				$scope.doughnutData3.push({rowData});
				
				$scope.apiResponse3.push({gtLabel:'Grand Total', gtErrorWiseTotalOpenTickets:$scope.sumOfErrorWiseTotalOpenTickets3, gtAllTickets:$scope.sumOfAllTickets3, gtErrorWiseTotalOpenTicketsPercent:$scope.errorWiseTotalOpenTicketsPercent3});
			}
	   }); 
}//End of loadErrorTypeWiseUptimeApiData service

	//Start of loadTATOfDownKiosksApiData service
	callDaCumulativeDataCCService4();
	function callDaCumulativeDataCCService4(){
	daCumulativeDataCCService4.loadTATOfDownKiosksApiData().success(function(response){
			$scope.apiResponse4 = response;
			$scope.oneDayArray=[];
			$scope.twoToFiveDayArray=[];
			$scope.oneWeekArray=[];
			$scope.oneToTwoWeekArray=[];
			$scope.greaterThanTwoWeekArray=[];
			
			$scope.oneDayPercentageArray=[];
			$scope.twoToFiveDayPercentageArray=[];
			$scope.oneWeekPercentageArray=[];
			$scope.oneToTwoWeekPercentageArray=[];
			$scope.greaterThanTwoWeekPercentageArray=[];
			
			$scope.doughnutData4=[];
			$scope.options4=[];
			$scope.noOfTats=5;
			
			$scope.getNumber = function(num) {
			    return new Array(num);   
			}

		   //Buiding Data & Options for Chart
			buildDoughnutDataAndOptions($scope);
			
			function buildDoughnutDataAndOptions($scope){

				$scope.options4.push({
				    legend: {
				        display: true,
				        position: 'right'
				      },
				      cutoutPercentage: 60,
				      tooltipEvents: [],
				      tooltipCaretSize: 0,
				      showTooltips: true,
				      onAnimationComplete: function() {
				        this.showTooltip(this.segments, true);
				      },
				      plugins: {
				          labels: {
				            render: 'percentage',
				            fontColor: ['black','black'],
				            precision: 2,
				            // fontSize: 12,
				            fontStyle: "bold"
				          }
				        },
				        tooltips: {
					          callbacks: {
					              label: function(tooltipItem, data) {
					                    var label = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] || '';
					                    if (label) {
					                        label = label.toFixed(2);
					                    }
					                    return label;
					              }
					          }
					     }
				    });
				
			
				$scope.labels4 = ["1 day", "2 - 5 days", "1 week", "1 - 2 weeks", "Greater than 2 weeks"];
				  $scope.colors4=['#A0B421',
			        	'#D7FF33',
			        	'#FFE933',
			        	'#FF6433',
			        	'#ED402A'];

				for(var i=0; i<$scope.apiResponse4.length; i++){
					if ($scope.apiResponse4[i] !== null) {
						//Adding Non Percentage data
						$scope.oneDayArray.push($scope.apiResponse4[i].oneDay);
						$scope.twoToFiveDayArray.push($scope.apiResponse4[i].twoToFiveDays);
						$scope.oneWeekArray.push($scope.apiResponse4[i].oneWeek);
						$scope.oneToTwoWeekArray.push($scope.apiResponse4[i].oneToTwoWeek);
						$scope.greaterThanTwoWeekArray.push($scope.apiResponse4[i].greaterThanTwoWeek);
						
						//Adding Percentage data
						$scope.oneDayPercentageArray.push($scope.apiResponse4[i].percentageOfOneDays);
						$scope.twoToFiveDayPercentageArray.push($scope.apiResponse4[i].percentOfTwoToFiveDays);
						$scope.oneWeekPercentageArray.push($scope.apiResponse4[i].percentageOfOneWeek);
						$scope.oneToTwoWeekPercentageArray.push($scope.apiResponse4[i].percentOneToTwoWeek);
						$scope.greaterThanTwoWeekPercentageArray.push($scope.apiResponse4[i].percentGreaterThanTwoWeek);
					}
				}//end for loop

				//Preparing Data to display in chart
				let rowData=[$scope.apiResponse4[0].percentageOfOneDays, $scope.apiResponse4[0].percentOfTwoToFiveDays, $scope.apiResponse4[0].percentageOfOneWeek, $scope.apiResponse4[0].percentOneToTwoWeek, $scope.apiResponse4[0].percentGreaterThanTwoWeek];
				$scope.doughnutData4.push({rowData});
			}
	   }); 
}//End of loadTATOfDownKiosksApiData service
	
	//Start of loadSummaryOfDownKiosksApiData service
	callDaCumulativeDataCCService5();
	function callDaCumulativeDataCCService5(){
	daCumulativeDataCCService5.loadSummaryOfDownKiosksApiData().success(function(response){
			$scope.apiResponse5 = response;
		    $scope.sumOfOpenTickets5=0;
			$scope.sumOfCloseTickets5=0;
			$scope.sumOfAllTickets5=0;
			$scope.openTicketsPercent5=0;
			$scope.closeTicketsPercent5=0;
			$scope.doughnutData5=[];
			$scope.options5=[];

		   //Buiding Data & Options for Chart
			buildDoughnutDataAndOptions($scope);
			
			function buildDoughnutDataAndOptions($scope){

				$scope.options5.push({
				    legend: {
				        display: true,
				        position: 'right'
				      },
				      cutoutPercentage: 60,
				      tooltipEvents: [],
				      tooltipCaretSize: 0,
				      showTooltips: true,
				      onAnimationComplete: function() {
				        this.showTooltip(this.segments, true);
				      },
				      plugins: {
				          labels: {
				            render: 'percentage',
				            fontColor: ['black','black'],
				            precision: 2,
				            // fontSize: 12,
				            fontStyle: "bold"
				          }
				        },
				        tooltips: {
					          callbacks: {
					              label: function(tooltipItem, data) {
					                    var label = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] || '';
					                    if (label) {
					                        label = label.toFixed(2);
					                    }
					                    return label;
					              }
					          }
					     }
				    });
				
				  $scope.labels5 = ["Total Open Calls", "Total Closed Calls"];
				  $scope.colors5=['#A0B421','#ED402A'];

				
				for(var i=0; i<$scope.apiResponse5.length; i++){
					if ($scope.apiResponse5[i] !== null) {
						$scope.sumOfOpenTickets5 = $scope.sumOfOpenTickets5 + $scope.apiResponse5[i].noOfOpenTickets;
						$scope.sumOfAllTickets5 = $scope.sumOfAllTickets5 + $scope.apiResponse5[i].noOfTickets;
					}
				}//end for loop
				
				
				$scope.sumOfCloseTickets5 = $scope.sumOfAllTickets5 - $scope.sumOfOpenTickets5;
				
				//Calculating the Operational & Non-Operational Percentage
				$scope.openTicketsPercent5 = ($scope.sumOfOpenTickets5 / $scope.sumOfAllTickets5) * 100;
				$scope.closeTicketsPercent5 = ($scope.sumOfCloseTickets5 / $scope.sumOfAllTickets5) * 100;
				
				//Preparing Data to display in chart
				let rowData=[$scope.openTicketsPercent5, $scope.closeTicketsPercent5];
				$scope.doughnutData5.push({rowData});
				
				$scope.apiResponse5.push({gtLabel:'Grand Total', gtOpenTickets:$scope.sumOfOpenTickets5, gtAllTickets:$scope.sumOfAllTickets5, gtOpenTicketsPercent:$scope.openTicketsPercent5});
			}
		});
}//End of loadSummaryOfDownKiosksApiData method
}]);//End of Controller


app.service('daCumulativeDataCCService1',['$http', function ($http) {
	function loadAvailabilityApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getCumulativeAvailability'
        });
    }
    return {
    	loadAvailabilityApiData:loadAvailabilityApiData
    };
}]);

app.service('daCumulativeDataCCService2',['$http', function ($http) {
    function loadVendorWiseUptimeApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getVendorWiseCumulativeData'
        });
    }
    return {
    	loadVendorWiseUptimeApiData:loadVendorWiseUptimeApiData
    };	
}]);

app.service('daCumulativeDataCCService3',['$http', function ($http) {
	function loadErrorTypeWiseUptimeApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getErrorTypeWiseCumulativeData'
        });
    }
    return {
    	loadErrorTypeWiseUptimeApiData:loadErrorTypeWiseUptimeApiData
    };	
}]);

app.service('daCumulativeDataCCService4',['$http', function ($http) {
	function loadTATOfDownKiosksApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getTATWiseCumulativeData'
        });
    }
    return {
    	loadTATOfDownKiosksApiData:loadTATOfDownKiosksApiData
    };	
}]);

app.service('daCumulativeDataCCService5',['$http', function ($http) {
    function loadSummaryOfDownKiosksApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getCumulativeSummaryOfDownKiosks'
        });
    }
    return {
    	loadSummaryOfDownKiosksApiData:loadSummaryOfDownKiosksApiData
    };
}]);