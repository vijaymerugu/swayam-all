<%@ page import="sbi.kiosk.swayam.common.dto.UserDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<script src="resources/js/daUserWiseData.js"></script>
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
	<div class="main" ng-app="daUserWiseDataModule" id="appId">
		<div ng-controller="daUserWiseDataController as vm">
			<div class="submain">
				<h5>User-wise Data</h5>
				<table>
					<tr>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">User-wise Down Kiosks</h5>
									<canvas id="doughnut1"
										class="chart chart-doughnut chartHeighWidth"
										chart-data="doughnutData1[0].rowData" chart-labels="labels1"
										chart-options="options1[0]" chart-colors="colors1"> </canvas>
									<br />
								</div>
								<div id="tableDiv">
									<table align="left" style="margin-left:15px" border="1">
										<tr>
											<th>User</th>
											<th>Total Operational Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse1">
											<td align="left">{{apiResponse1[$index].userName}}</td>
											<td align="right">{{apiResponse1[$index].totalOperationalKiosks}}</td>
											<td align="right">{{apiResponse1[$index].totalKiosks}}</td>
											<td align="right">{{apiResponse1[$index].availabilityPercentage}}</td>
										</tr>
										<tr>
											<td align="left"><b>{{apiResponse1[apiResponse1.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse1[apiResponse1.length-1].gtOperationalKiosks}}</b></td>
											<td align="right"><b>{{apiResponse1[apiResponse1.length-1].gtAllKiosks}}</b></td>
											<td align="right"><b>{{apiResponse1[apiResponse1.length-1].gtOperationalKiosksPercent | number: 2}}</b></td>
										</tr>
									</table>
								</div>
							</div>
						</td>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center">User-wise Zero Txn Kiosks</h5>
									<canvas id="doughnut2"
										class="chart chart-doughnut chartHeighWidth"
										chart-data="doughnutData2[0].rowData" chart-labels="labels2"
										chart-options="options2[0]" chart-colors="colors2"> </canvas>
									<br />
								</div>
								<div id="tableDiv">
									<table border="1" align="left" style="margin-left:15px">
										<tr>
											<th>User</th>
											<th>Zero Transaction Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse2">
											<td align="left">{{apiResponse2[$index].userName}}</td>
											<td align="right">{{apiResponse2[$index].totalOperationalKiosks}}</td>
											<td align="right">{{apiResponse2[$index].totalKiosks}}</td>
											<td align="right">{{apiResponse2[$index].availabilityPercentage}}</td>
										</tr>
										<tr>
											<td align="left"><b>{{apiResponse2[apiResponse2.length-1].gtLabel}}</b></td>
											<td align="right"><b>{{apiResponse2[apiResponse2.length-1].gtOperationalKiosks}}</b></td>
											<td align="right"><b>{{apiResponse2[apiResponse2.length-1].gtAllKiosks}}</b></td>
											<td align="right"><b>{{apiResponse2[apiResponse2.length-1].gtOperationalKiosksPercent | number: 2}}</b></td>
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
				[ 'daUserWiseDataModule' ]);
	</script>
</body>
</html>