var app = angular.module('daAvailabilityModule', ['chart.js',]);

app.controller('daAvailabilityController', ['$scope','daAvailabilityService', function ($scope, daAvailabilityService) {


	daAvailabilityService.loadApiData().success(function(response){
	   $scope.apiResponse = response;
	   //Buiding Data & Options for Chart
		buildDoughnutDataAndOptions($scope);
		
		function buildDoughnutDataAndOptions($scope){
			var nonNullCounter = 1;
			let tempData=[];
			$(".chartDiv").remove();
			$('.submain').append('<div class="chartDiv" style="float:left"></div>');
			$(".chartDiv").append("<table><tr>");
			
			for(var i=0; i<$scope.apiResponse.length; i++){
				if ($scope.apiResponse[i] !== null) {
					
					
					//Creating dynamic <td> to draw Chart
					var str="<td><canvas id='doughnut"+nonNullCounter+"'></canvas></td>";
					$(".chartDiv").append(str);
					if(nonNullCounter % 3 == 0){
						$(".chartDiv").append("<br/>");
					}

					//Preparing Data to display in chart
					let rowData=[$scope.apiResponse[i].availableKiosksPercent, $scope.apiResponse[i].nonAvailableKiosksPercent];
					tempData.push({"rowData" : rowData});

					//doughnut chart data
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
					  
						//options
					  var options = {
					    responsive: true,
					    title: {
					      display: true,
					      position: "bottom",
					      text: $scope.apiResponse[i].circleName
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
						
					//create Chart class object
					  var canvas = document.getElementById('doughnut'+nonNullCounter);
					  doughnutChart = Chart.Doughnut(canvas,{
							data:data,
						  options:options
						});
					
					  //This counter is used to count non null counter of loop.  
					  nonNullCounter++;
				}
			}//end for loop
			$(".chartDiv").append("</tr></table>");
		}
   });
}]);



app.service('daAvailabilityService',['$http', function ($http) {
function loadApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getAvailability'
        });
    }
    return {
    	loadApiData:loadApiData
    };
    
}]);