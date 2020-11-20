var app = angular.module('daVendorWiseUptimeModule', ['chart.js']);

app.controller('daVendorWiseUptimeController', ['$scope','$interval','$http','daVendorWiseUptimeService', function ($scope, $interval, $http, daVendorWiseUptimeService) {

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
	
	
	
	$scope.vendorList = [{
	    'name': 'LIPI',
	    'value': 'LIPI'
	  }, {
	    'name': 'Forbes',
	    'value': 'Forbes'
	  }, {
	    'name': 'CMS',
	    'value': 'CMS'
	  }];
	
	$scope.selectedVendor = {
		     'vendor': $scope.vendorList[0]
		  };

	// function to change data on dropdown selection.
	$scope.selectedItemChanged = function(){
		callDaVendorWiseUptimeService();
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
			callDaVendorWiseUptimeService();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});
		
		//On Page Load API Called.
		callDaVendorWiseUptimeService();
		function callDaVendorWiseUptimeService(){
			daVendorWiseUptimeService.loadVendorWiseUptimeApiData($scope.selectedVendor.vendor.value).success(function(response){
				   $scope.vendorWiseApiResponse = response;
				   // Buiding Data & Options for Chart
					buildDoughnutDataAndOptions($scope);
					
					function buildDoughnutDataAndOptions($scope){
						var nonNullCounter = 1;
						let tempData=[];
						$(".chartDiv").remove();
						$('.submain').append('<div class="chartDiv"></div>');
						$(".chartDiv").append("<table><tr>");
						let filterdResponse = $scope.vendorWiseApiResponse.filter(function (e){
							if(e != null){
								return e.vendorName == $scope.selectedVendor.vendor.value;
							}
						});
						for(var i=0; i<filterdResponse.length; i++){
							if (filterdResponse[i] != null) {

								// Creating dynamic <td> to draw Chart
								var str="<td><canvas id='doughnut"+nonNullCounter+"'></canvas></td>";
								$(".chartDiv").append(str);
								if(nonNullCounter % 3 == 0){
									$(".chartDiv").append("<br/>");
								}

								// Preparing Data to display in chart
								//let rowData=[filterdResponse[i].availableKiosksPercent,filterdResponse[i].nonAvailableKiosksPercent];
								//Changes 20-11-2020
								let rowData=[filterdResponse[i].totalAvailableKiosks,filterdResponse[i].totalNonAvailableKiosks];
								
								tempData.push({"rowData" : rowData});

								// doughnut chart data
								  var data = {
								    labels: ["Total Operational Kiosks", "Total Non-Operational Kiosks"],
								    datasets: [
								      {
								        label: "Vendor-Wise Uptime",
								        data: tempData[i].rowData,
								        //data: tempData[nonNullCounter-1].rowData,
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
								      plugins: {
								          labels: {
								            render: 'percentage',
								            fontColor: ['black','black'],
								            precision: 2,
								            // fontSize: 12,
								            fontStyle: "bold"
								          }
								        }/*,
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

app.service('daVendorWiseUptimeService',['$http', function ($http) {
function loadVendorWiseUptimeApiData(vendor) {
        return  $http({
          method: 'GET',
          url: 'da/getVendorWiseUpTime?vendor='+vendor
        });
    }
    return {
    	loadVendorWiseUptimeApiData:loadVendorWiseUptimeApiData
    };
    
}]);