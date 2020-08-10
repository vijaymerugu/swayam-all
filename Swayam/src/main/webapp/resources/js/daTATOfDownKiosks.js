var app = angular.module('daTATOfDownKiosksModule', ['chart.js',]);

app.controller('daTATOfDownKiosksController', ['$scope','$interval','$http','daTATOfDownKiosksService', 
	function ($scope, $interval, $http, daTATOfDownKiosksService) {

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
				callDaTATOfDownKiosksService();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});
	
	//Start of daTATOfDownKiosksService
	callDaTATOfDownKiosksService();
	function callDaTATOfDownKiosksService(){
	daTATOfDownKiosksService.loadApiData().success(function(response){
	   $scope.apiResponse = response;
		
	   //Buiding Data & Options for Chart
		buildDoughnutDataAndOptions($scope);
		
		function buildDoughnutDataAndOptions($scope){
			var nonNullCounter = 1;
			let tempData=[];
			$(".chartDiv").remove();
			$('.submain').append('<div class="chartDiv"></div>');
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
					let rowData=[$scope.apiResponse[i].percentageOfOneDays, $scope.apiResponse[i].percentOfTwoToFiveDays,
					$scope.apiResponse[i].percentageOfOneWeek, $scope.apiResponse[i].percentOneToTwoWeek, $scope.apiResponse[i].percentGreaterThanTwoWeek];
					tempData.push({rowData});

					//doughnut chart data
					  var data = {
					    labels: ["1 day", "2 - 5 days", "1 week", "1 - 2 weeks", "Greater than 2 weeks"],
					    datasets: [
					      {
					        label: "TAT of Down Kiosks",
					        data: tempData[nonNullCounter-1].rowData,
					        backgroundColor: [
					        	'#A0B421',
					        	'#D7FF33',
					        	'#FFE933',
					        	'#FF6433',
					        	'#ED402A'
					        ],
					        borderColor: [
					        	'#A0B421',
					        	'#D7FF33',
					        	'#FFE933',
					        	'#FF6433',
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
}//End of daTATOfDownKiosksService
	
}]);//End of Controller



app.service('daTATOfDownKiosksService',['$http', function ($http) {
function loadApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getTATofDownKiosks'
        });
    }
    return {
    	loadApiData:loadApiData
    };
    
}]);