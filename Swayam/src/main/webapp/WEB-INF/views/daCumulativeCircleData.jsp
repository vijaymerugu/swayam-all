<%@ page import="sbi.kiosk.swayam.common.dto.UserDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<script src="resources/js/daCumulativeCircleData.js"></script>
<link rel="stylesheet" href="resources/css/body-page.css" />
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/Chart.min.js"></script>
<script src="resources/js/angular-chart.min.js"></script>
<script src="resources/js/chartjs-plugin-labels.js"></script>
<style type="text/css">
.rcorners {
	 border-radius: 1px;
	 border: 1px solid #73AD21;
	/* padding: 1px; */
	width: 650px;
  	height: 800px;
}
.chartHeighWidth {
	/* width: 200px;
  	height: 200px; */
}
</style>

</head>
<body>
	<%
		UserDto userObj = (UserDto) session.getAttribute("userObj");
		String pfId="";
		if (userObj.getPfId() != null) {
			pfId = userObj.getPfId();
		}
	%>
	<input type="hidden" id="pfId" value="<%=pfId%>">
	<div class="main" ng-app="daCumulativeCircleDataModule" id="appId">
		<div ng-controller="daCumulativeCircleDataController as vm">
			<div class="submain">
				<h5>Cumulative Circle Data</h5>
				<table>
					<tr>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">Availability (in %)</h5>
									<canvas id="doughnut1" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData1[0].rowData"
													chart-labels="labels1" chart-options="options1[0]"
													chart-colors="colors1"> </canvas>
									<br/>
								</div>
								<div id="tableDiv">
									<table align="left" style="margin-left:15px" border="1">
										<tr>
											<th>Circle</th>
											<th>Total Operational Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse1">
											<td align="left">{{apiResponse1[$index].circleName}}</td>
											<td align="right">{{apiResponse1[$index].totalOperationalKiosks}}</td>
											<td align="right">{{apiResponse1[$index].totalKiosks}}</td>
											<td align="right">{{apiResponse1[$index].availableKiosksPercent}}</td>
										</tr>
										<tr>
											<td align="left"><b>{{apiResponse1[apiResponse1.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse1[apiResponse1.length-1].gtOperationalKiosks}}</b></td>
											<td align="right"><b>{{apiResponse1[apiResponse1.length-1].gtTotalKiosks}}</b></td>
											<td align="right"><b>{{apiResponse1[apiResponse1.length-1].gtAvailabilityPercent | number: 2}}</b></td>
										</tr>
									</table>
								</div>
							</div>
						</td>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">Vendor-wise Uptime (in %)</h5>
									<canvas id="doughnut2" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData2[0].rowData"
													chart-labels="labels2" chart-options="options2[0]"
													chart-colors="colors2"> </canvas>
									<br/>
								</div>
								<div id="tableDiv">
									<table border="1" align="left" style="margin-left:15px">
										<tr>
											<th>Vendor</th>
											<th>Total Operational Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse2">
											<td align="left">{{apiResponse2[$index].vendorName}}</td>
											<td align="right">{{apiResponse2[$index].totalOperationalKiosks}}</td>
											<td align="right">{{apiResponse2[$index].totalKiosks}}</td>
											<td align="right">{{apiResponse2[$index].availableKiosksPercent}}</td>
										</tr>
										<tr>
											<td align="left"><b>{{apiResponse2[apiResponse2.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse2[apiResponse2.length-1].gtOperationalKiosks}}</b></td>
											<td align="right"><b>{{apiResponse2[apiResponse2.length-1].gtTotalKiosks}}</b></td>
											<td align="right"><b>{{apiResponse2[apiResponse2.length-1].gtAvailabilityPercent | number: 2}}</b></td>
										</tr>
									</table>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">Error type-wise Uptime (in %)</h5>
									<canvas id="doughnut3" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData3[0].rowData"
													chart-labels="labels3" chart-options="options3[0]"
													chart-colors="colors3"> </canvas>
													<br/>
								</div>
								<div id="tableDiv">
									<table border="1" align="left" style="margin-left:15px">
										<tr>
											<th>Error Type</th>
											<th>Open Tickets</th>
											<th>Total Tickets</th>
											<th>Error Tickets(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse3">
											<td align="left">{{apiResponse3[$index].errorType}}</td>
											<td align="right">{{apiResponse3[$index].errorWiseTotalOpenTickets}}</td>
											<td align="right">{{apiResponse3[$index].noOfTickets}}</td>
											<td align="right">{{apiResponse3[$index].availabilityInPercent}}</td>
										</tr>
										<tr>
											<td align="left"><b>{{apiResponse3[apiResponse3.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse3[apiResponse3.length-1].gtErrorWiseTotalOpenTickets}}</b></td>
											<td align="right"><b>{{apiResponse3[apiResponse3.length-1].gtAllTickets}}</b></td>
											<td align="right"><b>{{apiResponse3[apiResponse3.length-1].gtErrorWiseTotalOpenTicketsPercent | number: 2}}</b></td>
										</tr>
									</table>
								</div>
							</div>
						</td>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">TAT of Down Kiosks</h5>
									<canvas id="doughnut4" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData4[0].rowData"
													chart-labels="labels4" chart-options="options4[0]"
													chart-colors="colors4"> </canvas>
									<br/>
								</div>
								<div id="tableDiv">
									<table border="1" align="left" style="margin-left:15px">
										<tr>
											<th>TAT</th>
											<th>Total Open Calls</th>
											<th>Open Calls %</th>
										</tr>
										<tr ng-repeat="x in getNumber(noOfTats) track by $index">
											<td align="left">{{labels4[$index]}}</td>
											
											<td align="right" ng-show="labels4[$index]==='1 day'">{{oneDayArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='2 - 5 days'">{{twoToFiveDayArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='1 week'">{{oneWeekArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='1 - 2 weeks'">{{oneToTwoWeekArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='Greater than 2 weeks'">{{greaterThanTwoWeekArray[0]}}</td>
											
											<td align="right" ng-show="labels4[$index]==='1 day'">{{oneDayPercentageArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='2 - 5 days'">{{twoToFiveDayPercentageArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='1 week'">{{oneWeekPercentageArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='1 - 2 weeks'">{{oneToTwoWeekPercentageArray[0]}}</td>
											<td align="right" ng-show="labels4[$index]==='Greater than 2 weeks'">{{greaterThanTwoWeekPercentageArray[0]}}</td>
										</tr>
									</table>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">Summary of Down Kiosks</h5>
									<canvas id="doughnut5" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData5[0].rowData"
													chart-labels="labels5" chart-options="options5[0]"
													chart-colors="colors5"> </canvas>
									<br/>
								</div>
								<div id="tableDiv">
									<table border="1" align="left" style="margin-left:15px">
										<tr>
											<th>Circle</th>
											<th>Open Calls</th>
											<th>Total Calls</th>
											<th>Open Calls(in %)</th>

										</tr>
										<tr ng-repeat="num in apiResponse5">
											<td align="left">{{apiResponse5[$index].circleName}}</td>
											<td align="right">{{apiResponse5[$index].noOfOpenTickets}}</td>
											<td align="right">{{apiResponse5[$index].noOfTickets}}</td>
											<td align="right">{{apiResponse5[$index].percentageOfTickets}}</td>
										</tr>
										<tr>
											<td align="left"><b>{{apiResponse5[apiResponse5.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse5[apiResponse5.length-1].gtOpenTickets}}</b></td>
											<td align="right"><b>{{apiResponse5[apiResponse5.length-1].gtAllTickets}}</b></td>
											<td align="right"><b>{{apiResponse5[apiResponse5.length-1].gtOpenTicketsPercent | number: 2}}</b></td>
										</tr>
									</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"),
				[ 'daCumulativeCircleDataModule' ]);
	</script>
</body>
</html>