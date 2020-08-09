var app = angular.module('daAvailabilityModule', ['chart.js',]);

app.controller('daAvailabilityController', ['$scope', '$interval','$http','daAvailabilityService',
	function ($scope, $interval, $http, daAvailabilityService) {

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
			callDaAvailabilityService();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});
		
	// on Page Load API Called.
	callDaAvailabilityService();
	function callDaAvailabilityService(){
		
		daAvailabilityService.loadAvailabilityApiData().success(function(response){
		$scope.availabilityApiResponse = response;
		// Buiding Data & Options for Chart
		buildDoughnutDataAndOptions($scope);
		
		function buildDoughnutDataAndOptions($scope){
			var nonNullCounter = 1;
			let tempData=[];
			$(".chartDiv").remove();
			$('.submain').append('<div class="chartDiv"></div>');
			$(".chartDiv").append("<table><tr>");
			
			for(var i=0; i<$scope.availabilityApiResponse.length; i++){
				if ($scope.availabilityApiResponse[i] !== null) {
					
					// Creating dynamic <td> to draw Chart
					var str="<td><canvas id='doughnut"+nonNullCounter+"'></canvas></td>";
					$(".chartDiv").append(str);
					if(nonNullCounter % 3 == 0){
						$(".chartDiv").append("<br/>");
					}

					// Preparing Data to display in chart
					let rowData=[$scope.availabilityApiResponse[i].availableKiosksPercent, $scope.availabilityApiResponse[i].nonAvailableKiosksPercent];
					tempData.push({rowData});

					// doughnut chart data
					  var data = {
					    labels: ["Total Operational Kiosks", "Total Non-Operational Kiosks"],
					    datasets: [
					      {
					        label: "Availability",
					        data: tempData[nonNullCounter-1].rowData,
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
					      text: $scope.availabilityApiResponse[i].circleName
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
					  };
						
					// create Chart class object
					  var canvas = document.getElementById('doughnut'+nonNullCounter);
					  doughnutChart = Chart.Doughnut(canvas,{
							data:data,
						  options:options
						});
					
					  // This counter is used to count non null counter of
						// loop.
					  nonNullCounter++;
				}
			}// end for loop
			$(".chartDiv").append("</tr></table>");
		}
   });
}

	// store the interval promise in this variable
    var promise;
    
    // starts the interval
    $scope.start = function() {
      // stops any running interval to avoid two intervals running at the same time
      $scope.stop(); 
      
      // store the interval promise
      promise = $interval(callDaAvailabilityService, 10000);
    };
    
 // stops the interval
    $scope.stop = function() {
      $interval.cancel(promise);
    };
  
    // starting the interval by default
    $scope.start();
    
 // stops the interval when the scope is destroyed,
    // this usually happens when a route is changed and 
    // the ItemsController $scope gets destroyed. The
    // destruction of the ItemsController scope does not
    // guarantee the stopping of any intervals, you must
    // be responsible for stopping it when the scope is
    // is destroyed.
    $scope.$on('$destroy', function() {
      $scope.stop();
    });


}]);

app.service('daAvailabilityService',['$http', function ($http) {

	function loadAvailabilityApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getAvailability'
        });
    }
    return {
    	loadAvailabilityApiData:loadAvailabilityApiData
    };
}]);


