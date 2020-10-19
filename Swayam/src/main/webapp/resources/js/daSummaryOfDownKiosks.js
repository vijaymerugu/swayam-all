var app = angular.module('daSummaryOfDownKiosksModule', ['chart.js',]);

app.controller('daSummaryOfDownKiosksController', ['$scope','$interval','$http','daSummaryOfDownKiosksService', 
	function ($scope, $interval, $http, daSummaryOfDownKiosksService) {
	
	this.$onInit = function() {
	    // this will kill all intervals and timeouts too in 1 seconds. 
	    var killId = setTimeout(function() {
	      for (var i = killId; i > 0; i--) clearInterval(i)
	    }, 1000);
    };
    
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

  //getting auto refresh time value from property file
	$http({
		method: 'GET',
		url: 'da/getChartAutoRefreshTime'
		}).then(function success(response) {
			$scope.autoRefreshTime=response.data;
			
			var id1 = setInterval(function() { 
				callDaSummaryOfDownKiosksService();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});
	
	//Start of loadSummaryOfDownKiosksApiData service
	callDaSummaryOfDownKiosksService();
	function callDaSummaryOfDownKiosksService(){
	daSummaryOfDownKiosksService.loadSummaryOfDownKiosksApiData().success(function(response){
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
					let rowData=[$scope.apiResponse[i].percentageOfTickets, $scope.apiResponse[i].percentageOfClosedTickets];
					tempData.push({"rowData" : rowData});

					//doughnut chart data
					  var data = {
					    labels: ["Total Open Calls", "Total Closed Calls"],
					    datasets: [
					      {
					        label: "TAT of Down Kiosks",
					        data: tempData[nonNullCounter-1].rowData,
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
   });//End of loadSummaryOfDownKiosksApiData
}
}]);

app.service('daSummaryOfDownKiosksService',['$http', function ($http) {
function loadSummaryOfDownKiosksApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getSummaryOfDownKiosks'
        });
    }
    return {
    	loadSummaryOfDownKiosksApiData:loadSummaryOfDownKiosksApiData
    };
}]);