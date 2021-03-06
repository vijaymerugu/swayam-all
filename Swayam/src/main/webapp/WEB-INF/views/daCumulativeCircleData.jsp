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

table {
  border-collapse: separate;
  border-spacing: 0;
  min-width: 350px;
}
table tr th,
table tr td {
  border-right: 1px solid #bbb;
  border-bottom: 1px solid #bbb;
  padding: 1px;
}
table tr th:first-child,
table tr td:first-child {
  border-left: 1px solid #bbb;
}
table tr th {
  background: #00bfff;
  border-top: 1px solid #bbb;
  text-align: left;
}

/* top-left border-radius */
table tr:first-child th:first-child {
  border-top-left-radius: 6px;
}

/* top-right border-radius */
table tr:first-child th:last-child {
  border-top-right-radius: 6px;
}

/* bottom-left border-radius */
table tr:last-child td:first-child {
  border-bottom-left-radius: 6px;
}

/* bottom-right border-radius */
table tr:last-child td:last-child {
  border-bottom-right-radius: 6px;
} 

 div.absolute {
	background: #FFFFFF 0% 0% no-repeat padding-box;
	top: 10px;
	right: 0;
	width: 100%;
	overflow:auto;
	 height: 100px; 
	border-radius: 1px;
	border: 1px solid #73AD21;
	
	margin-bottom: 5px;
	 overflow: auto;
	
}
 
.rcorners {

	 border-radius: 1px;
	 border: 1px solid #73AD21;
	/* padding: 1px; */
	width: 700px;
  	height: 1000px;
}
.rcorners1 {

	 border-radius: 1px;
	 border: 1px solid #73AD21;
	/* padding: 1px; */
	width: 700px;
  	height: 600px;
}
.rcorners2 {

	 border-radius: 1px;
	 border: 1px solid #73AD21;
	/* padding: 1px; */
	width: 700px;
  	height: 1000px;
}

/* .rcorners {

	 border-radius: 1px;
	 border: 1px solid #73AD21;
	/* padding: 1px; 
	width: 650px;
  	height: 800px;
} */
.chartHeighWidth {
	/* width: 200px;
  	height: 200px; */
}

/* div.absolute {
     width: 645px;
  	height: 795px; 
  	overflow: scroll;
   
} */

/* .container {
  
} */

.topright {
  position: absolute;
  top: 8px;
  right: 0px;
  font-size: 18px;
}

.submain {
    top: 242px;
    left: 8px;
    width: 100%;
	overflow:auto;
    height: fit-content;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    box-shadow: 0px 3px 6px #8D8D8D29;
    opacity: 1;
    padding: 7px;
}

.bullet::before {
	content: "\2022";
	color: #00BFFF;;
	font-weight: bold;
	display: inline-block;
	width: 1em;
	margin-left: -1em;
}

.absolute .bullet {
 display: inline-block;

  text-align: center;
  padding: 14px;
  text-decoration: none;
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
	<div class="main_transaction" ng-app="daCumulativeCircleDataModule" id="appId">
		<div ng-controller="daCumulativeCircleDataController as vm">
		<div class="absolute">
			<div><h5 style="font-weight: bold;" align="center">Urgent Information</h5></div>
			<ul>
				<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
			</ul>
				
		</div>
			<div class="submain" align="center">
				<h5 style="font-weight: bold;" align="center">Cumulative Circle Data</h5>
				<table>
					<!-- <tr>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center" style="font-weight: bold;">Availability (in %)</h5>
									<canvas id="doughnut1" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData1[0].rowData"
													chart-labels="labels1" chart-options="options1[0]"
													chart-colors="colors1"> </canvas>
									<br/>
								</div>
								<div >
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
									<h5 align="center" style="font-weight: bold;">Summary of Down Kiosks</h5>
									<canvas id="doughnut5" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData5[0].rowData"
													chart-labels="labels5" chart-options="options5[0]"
													chart-colors="colors5"> </canvas>
									<br/>
								</div>
								<div >
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
						<td>
							<div class="rcorners">
							<div class="absolute">
								<div><h5 align="center" style="font-weight: bold;"> Urgent Information</h5></div>
								<ul>
									<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
								</ul>
								</div>
							</div>

						</td>
						
					</tr> -->
					<tr>
						
						<td>
							<div class="rcorners1">
								<div id="chartDiv">
									<h5 align="center" style="font-weight: bold;">TAT of Down Kiosks</h5>
									<canvas id="doughnut4" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData4[0].rowData"
													chart-labels="labels4" chart-options="options4[0]"
													chart-colors="colors4"> </canvas>
									<br/>
								</div>
								<div >
									<table align="center">
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
										<tr>
											<td align="left"><b>{{apiResponse4[apiResponse4.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse4[apiResponse4.length-1].gtToatalOpenCalls}}</b></td>
											<td align="right"><b>{{apiResponse4[apiResponse4.length-1].gtToatalOpenCallsPct}}</b></td>
										</tr> 
									</table>
								</div>
							</div>
						</td>
						<td>
							<div class="rcorners1">
								<div id="chartDiv">
									<h5 align="center" style="font-weight: bold;">Vendor-wise Uptime (in %)</h5>
									<canvas id="doughnut2" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData2[0].rowData"
													chart-labels="labels2" chart-options="options2[0]"
													chart-colors="colors2"> </canvas>
									<br/>
								</div>
								<div >
									<table align="center">
										<tr>
											<th>Vendor</th>
											<th>Available Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse2" ng-if="num.vendorName != null">
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
							<div class="rcorners2">
								<div id="chartDiv">
									<h5 align="center" style="font-weight: bold;">Error type-wise Uptime (in %)</h5>
									<canvas id="doughnut3" class="chart chart-doughnut chartHeighWidth"
													chart-data="doughnutData3[0].rowData"
													chart-labels="labels3" chart-options="options3[0]"
													chart-colors="colors3"> </canvas>
													<br/>
								</div>
								<div >
									<table align="center">
										<tr>
											<th>Error Type</th>
											<th>Open Tickets</th>
											<th>Total Tickets</th>
											<th>Error Tickets(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse3" ng-if="num.errorType != null">
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