var app = angular.module('daAvailabilityModule', ['chart.js',]);

app.controller('daAvailabilityController', ['$scope', '$interval', '$http', 'daAvailabilityService', function ($scope, $interval, $http, daAvailabilityService) {

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
				callDaAvailabilityService();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});
	
	//On Page Load API Called.
	callDaAvailabilityService();
	function callDaAvailabilityService() {
	   daAvailabilityService.loadApiData().success(function(response){
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
//					let rowData=[$scope.apiResponse[i].availableKiosksPercent, $scope.apiResponse[i].nonAvailableKiosksPercent];
					//Changes 20-11-2020
					let rowData=[$scope.apiResponse[i].totalAvailableKiosks, $scope.apiResponse[i].totalNonAvailableKiosks];
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
					      plugins: {
					          labels: {
					            render: 'percentage',
					            fontColor: ['black','black'],
					            precision: 2,
					            // fontSize: 12,
					            fontStyle: "bold"
					          }
					        }
					  /*  legend: {
					      display: false,
					      position: "bottom",
					      labels: {
					        fontColor: "#333",
					        fontSize: 16,
					        fontStyle: "bold"
					      }
					    }*/
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
}
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