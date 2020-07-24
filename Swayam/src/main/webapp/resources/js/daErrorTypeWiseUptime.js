var app = angular.module('daErrorTypeWiseUptimeModule', ['chart.js']);

app.controller('daErrorTypeWiseUptimeController', ['$scope','$filter','daErrorTypeWiseUptimeService', function ($scope, $filter,daErrorTypeWiseUptimeService) {
	
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

  		//$scope.selectedErrorType = {'name': 'Error1','value':'Error1'},
  		$scope.selectedErrorType = {
  			     'errorType': $scope.errorTypeList[0]
  			  };
	
	// function to change data on dropdown selection.
	$scope.selectedItemChanged = function(){
		loadApiDataByErrorType();
		}

		loadApiDataByErrorType();
		
		function loadApiDataByErrorType(){
			daErrorTypeWiseUptimeService.loadApiData($scope.selectedErrorType.errorType.value).success(function(response){
				
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
							return e.errorType == $scope.selectedErrorType.errorType.value;
						});
						console.log('filterdResponse:',filterdResponse);
						for(var i=0; i<filterdResponse.length; i++){
							if (filterdResponse[i] != null) {

								// Creating dynamic <td> to draw Chart
								var str="<td><canvas id='doughnut"+nonNullCounter+"'></canvas></td>";
								$(".chartDiv").append(str);
								if(nonNullCounter % 3 == 0){
									$(".chartDiv").append("<br/>");
								}
								
								// Preparing Data to display in chart
								let rowData=[filterdResponse[i].percentageOfTickets, filterdResponse[i].otherErrorTickets];
								tempData.push({rowData});
								
								// doughnut chart data
								  var data = {
								    labels: ["Total Down Kiosks of Selected Error", "Total Down Kiosks of Other error types"],
								    datasets: [
								      {
								        label: "Error Type-Wise Uptime",
								        data: tempData[i].rowData,
								        backgroundColor: [
								        	'#A0B421',
								        	'#ED402A'
								        ],
								        borderColor: [
								        	'#A0B421',
								        	'#ED402A'
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
								    legend: {
								      display: false,
								      position: "bottom",
								      labels: {
								        fontColor: "#333",
								        fontSize: 16
								      }
								    }
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
// alert("123");
function loadApiData(errorType) {
	console.log('error param in loadapidata:',errorType);
        return  $http({
          method: 'GET',
          url: 'da/getErrorTypeWiseUpTime?callCategory='+errorType
        });
    }
    return {
    	loadApiData:loadApiData
    };
    
}]);