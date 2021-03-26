var app = angular.module('daErrorTypeWiseUptimeModule', ['chart.js']);

app.controller('daErrorTypeWiseUptimeController', ['$scope','$interval','$http','daErrorTypeWiseUptimeService', 
	function ($scope, $interval, $http, daErrorTypeWiseUptimeService) {
	
	//get urgent active messages 
    $scope.LoadUrgentMessages=function(){
		   $http({
				method : "GET",
				url : 'da/get-urgent-messgaes',
				dataType : 'json',
				data : {},
				headers : {
					"Content-Type" : "application/json"
				}
			}).success(function(data, status){
				$scope.UrgentMessages = data;
				
			}).error(function(data, status) {
				console.log("Unable to load the messages" +  data + " Status " + status);
			});
		   
	   }
    
    $scope.LoadUrgentMessages();
	
		$scope.errorTypeList = [{
  		    'name': 'Printing',
  		    'value': 'Printing'
  		  }, {
  		    'name': 'Kiosk Application',
  		    'value': 'Kiosk Application'
  		  }, {
  		    'name': 'Connectivity',
  		    'value': 'Connectivity'
  		  }, {
		    'name': 'Operating System',
  		    'value': 'Operating System'
  		  },
  		  {
  		    'name': 'Barcode',
    		    'value': 'Barcode'
    		  },
  		  {
  		    'name': 'Hardware',
    		    'value': 'Hardware'
  		  }];

  		$scope.selectedErrorType = {
  			     'errorType': $scope.errorTypeList[0]
  			  };
	
	// function to change data on dropdown selection.
	$scope.selectedItemChanged = function(){
		callDaErrorTypeWiseUptimeService();
		}
	
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
				callDaErrorTypeWiseUptimeService();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});

		//On Page Load API Called.
		callDaErrorTypeWiseUptimeService();
		function callDaErrorTypeWiseUptimeService(){
			daErrorTypeWiseUptimeService.loadErrorTypeWiseUptimeApiData($scope.selectedErrorType.errorType.value).success(function(response){
				
				   $scope.apiResponse = response;
				   // Buiding Data & Options for Chart
					buildDoughnutDataAndOptions($scope);
					
					function buildDoughnutDataAndOptions($scope){
						var nonNullCounter = 1;
						let tempData=[];
						$(".chartDiv").remove();
						$('.submain').append('<div class="chartDiv"></div>');
						$(".chartDiv").append("<table><tr>");
						let filterdResponse = $scope.apiResponse.filter(function (e){
							if(e != null){
								return e.errorType == $scope.selectedErrorType.errorType.value;
							}
						});

						for(var i=0; i<filterdResponse.length; i++){
							if (filterdResponse[i] != null) {

								// Creating dynamic <td> to draw Chart
								var str="<td><canvas id='doughnut"+nonNullCounter+"'></canvas></td>";
								$(".chartDiv").append(str);
								if(nonNullCounter % 5 == 0){
									$(".chartDiv").append("<br/>");
								}
								
								// Preparing Data to display in chart
								//let rowData=[filterdResponse[i].percentageOfTickets, filterdResponse[i].otherErrorTickets];
								//Changes  20-11-2020
//								let rowData=[filterdResponse[i].errorWiseOpenTickets, (filterdResponse[i].noOfTickets-filterdResponse[i].errorWiseOpenTickets)];
								
								let rowData=[filterdResponse[i].errorWiseOpenTickets, filterdResponse[i].noOfTickets];

								
								tempData.push({"rowData" : rowData});
								
								// doughnut chart data
								  var data = {
								    labels: ["Total Down Kiosks of Selected Error", "Total Down Kiosks of Other error types"],
								    datasets: [
								      {
								        label: "Error Type-Wise Uptime",
								        data: tempData[i].rowData,
								        backgroundColor: [
								        	'#ED402A',
								        	'#A0B421'
								        	
								        ],
								        borderColor: [
								        	'#ED402A',
								        	'#A0B421'
								        ],
								        borderWidth: [1, 1]
								      }
								    ]
								  };

								// options
								  var options = {
								    responsive: true,
								    title: {
								      display: true,
								      position: "bottom",
								      text: filterdResponse[i].circleName
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
								        }
								      //Changes- 20-22-2020
								      /*,
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
									     }*/
								  };
									
								// create Chart class object
								  var canvas = document.getElementById('doughnut'+nonNullCounter);
								  doughnutChart = Chart.Doughnut(canvas,{
										data:data,
									  options:options
									});
								
								  // This counter is used to count non null
									// counter of loop.
								  nonNullCounter++;
							}
						}// end for loop
						$(".chartDiv").append("</tr></table>");
					}
			   });
		}
		
}]);

app.service('daErrorTypeWiseUptimeService',['$http', function ($http) {
function loadErrorTypeWiseUptimeApiData(errorType) {
        return  $http({
          method: 'GET',
          url: 'da/getErrorTypeWiseUpTime?callCategory='+errorType
        });
    }
    return {
    	loadErrorTypeWiseUptimeApiData:loadErrorTypeWiseUptimeApiData
    };
}]);