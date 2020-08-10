var app = angular.module('daUserWiseDataModule', ['chart.js',]);

app.controller('daUserWiseDataController', ['$scope','$interval','$http','daUserWiseDataService1','daUserWiseDataService2',
	function ($scope, $interval, $http, daUserWiseDataService1, daUserWiseDataService2) {

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
				callDaUserWiseDataService1();
				callDaUserWiseDataService2();
			}, $scope.autoRefreshTime);			
		}, function error(response) {
			console.log('error occured while getting refresh time.');
		});

	//Start of loadUserWiseDownKiosksApiData service
	callDaUserWiseDataService1();
	function callDaUserWiseDataService1(){
	daUserWiseDataService1.loadUserWiseDownKiosksApiData().success(function(response){
		$scope.apiResponse1 = response;
	   	$scope.sumOfOperationalKiosks1=0;
		$scope.sumOfNonOperationalKiosks1=0;
		$scope.sumOfAllKiosks1=0;
		$scope.operationalKiosksPercent1=0;
		$scope.nonOperationalKiosksPercent1=0;
		$scope.doughnutData1=[];
		$scope.options1=[];
		
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
			
			  $scope.labels1 = ["Total Down Kiosks", "Total Working Kiosks"];
			  $scope.colors1=['#A0B421','#ED402A'];

			
			for(var i=0; i<$scope.apiResponse1.length; i++){
				if ($scope.apiResponse1[i] !== null) {
					$scope.sumOfOperationalKiosks1 = $scope.sumOfOperationalKiosks1 + $scope.apiResponse1[i].totalOperationalKiosks;
					$scope.sumOfAllKiosks1 = $scope.sumOfAllKiosks1 + $scope.apiResponse1[i].totalKiosks;
				}
			}//end for loop

			$scope.sumOfNonOperationalKiosks1 = $scope.sumOfAllKiosks1 - $scope.sumOfOperationalKiosks1;
			
			//Calculating the Operational & Non-Operational Percentage
			$scope.operationalKiosksPercent1 = ($scope.sumOfOperationalKiosks1 / $scope.sumOfAllKiosks1) * 100;
			$scope.nonOperationalKiosksPercent1 = ($scope.nonOperationalKiosksPercent1 / $scope.sumOfAllKiosks1) * 100;
			
			
			//Preparing Data to display in chart
			let rowData=[$scope.operationalKiosksPercent1, $scope.nonOperationalKiosksPercent1];
			$scope.doughnutData1.push({rowData});
			
			$scope.apiResponse1.push({gtLabel:'Grand Total', gtOperationalKiosks:$scope.sumOfOperationalKiosks1, gtAllKiosks:$scope.sumOfAllKiosks1, gtOperationalKiosksPercent:$scope.operationalKiosksPercent1});
		}
		
   });
}//End of loadUserWiseDownKiosksApiData service
	
	//Start of loadUserWiseZeroTxnKiosksApiData service
	callDaUserWiseDataService2();
	function callDaUserWiseDataService2(){
	daUserWiseDataService2.loadUserWiseZeroTxnKiosksApiData().success(function(response){	   
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
		
		  $scope.labels2 = ["Total Zero Txn Kiosks", "Total Non-Zero Txn Kiosks"];
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
		$scope.nonOperationalKiosksPercent2 = ($scope.sumOfNonOperationalKiosks2 / $scope.sumOfAllKiosks2) * 100;

		//Preparing Data to display in chart
		let rowData=[$scope.operationalKiosksPercent2, $scope.nonOperationalKiosksPercent2];
		$scope.doughnutData2.push({rowData});
		
		$scope.apiResponse2.push({gtLabel:'Grand Total', gtOperationalKiosks:$scope.sumOfOperationalKiosks2, gtAllKiosks:$scope.sumOfAllKiosks2, gtOperationalKiosksPercent:$scope.operationalKiosksPercent2});
	}

	
	});
}//End of loadUserWiseZeroTxnKiosksApiData service service
		
}]);//End of Controller


app.service('daUserWiseDataService1',['$http', function ($http) {
	function loadUserWiseDownKiosksApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getUserWiseDownKiosksData?userId='+$('input#pfId').val()
        });
    }
    return {
    	loadUserWiseDownKiosksApiData:loadUserWiseDownKiosksApiData
    };
}]);

app.service('daUserWiseDataService2',['$http', function ($http) {
    function loadUserWiseZeroTxnKiosksApiData() {
        return  $http({
          method: 'GET',
          url: 'da/getUserWiseZeroTxnKiosksData?userId='+$('input#pfId').val()
        });
    }
    return {
    	loadUserWiseZeroTxnKiosksApiData:loadUserWiseZeroTxnKiosksApiData
    };	
}]);
