var app = angular.module('daVendorWiseUptimeModule', ['chart.js']);

app.controller('daVendorWiseUptimeController', ['$scope','$filter','daVendorWiseUptimeService', function ($scope, $filter,daVendorWiseUptimeService) {

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
	
	$scope.selectedVendor = {'name': 'LIPI','value':'LIPI'},
	$scope.selectedVendor = {
		     'vendor': $scope.vendorList[0]
		  };
	
	// function to change data on dropdown selection.
	$scope.selectedItemChanged = function(){
		loadApiDataByVendor();
		}

		loadApiDataByVendor();
		
		function loadApiDataByVendor(){
			daVendorWiseUptimeService.loadApiData($scope.selectedVendor.vendor.value).success(function(response){
				   $scope.apiResponse = response;
				   //Buiding Data & Options for Chart
					buildDoughnutDataAndOptions($scope);
					
					function buildDoughnutDataAndOptions($scope){
						var nonNullCounter = 1;
						let tempData=[];
						$(".chartDiv").remove();
						$('.submain').append('<div class="chartDiv"></div>');
						$(".chartDiv").append("<table><tr>");
						let filterdResponse = $scope.apiResponse.filter(function (e){
							return e.vendorName == $scope.selectedVendor.vendor.value;
						});
						console.log('filterdResponse:',filterdResponse);
						for(var i=0; i<filterdResponse.length; i++){
							if (filterdResponse[i] != null) {

								//Creating dynamic <td> to draw Chart
								var str="<td><canvas id='doughnut"+nonNullCounter+"'></canvas></td>";
								$(".chartDiv").append(str);
								if(nonNullCounter % 3 == 0){
									$(".chartDiv").append("<br/>");
								}

								//Preparing Data to display in chart
								let rowData=[filterdResponse[i].availableKiosksPercent,filterdResponse[i].nonAvailableKiosksPercent];
								tempData.push({rowData});

								//doughnut chart data
								  var data = {
								    labels: ["Total Operational Kiosks", "Total Non-Operational Kiosks"],
								    datasets: [
								      {
								        label: "Vendor-Wise Uptime",
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
								  
									//options
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



app.service('daVendorWiseUptimeService',['$http', function ($http) {
//alert("123");
function loadApiData(vendor) {
	console.log('vendor in loadapidata:',vendor);
        return  $http({
          method: 'GET',
          url: 'da/getVendorWiseUpTime?vendor='+vendor
        });
    }
    return {
    	loadApiData:loadApiData
    };
    
}]);