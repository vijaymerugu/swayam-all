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
<!-- <style type="text/css">
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
</style> -->

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
  	height: 600px;
}
/* .rcorners {

	 border-radius: 1px;
	 border: 1px solid #73AD21;
	
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
	<div class="main_transaction" ng-app="daUserWiseDataModule" id="appId">
		<div ng-controller="daUserWiseDataController as vm">
		<div class="absolute">
			<div><h5 style="font-weight: bold;" align="center">Urgent Information</h5></div>
			<ul>
				<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
			</ul>
				
		</div>
			<div class="submain">
				<h5 style="font-weight: bold;" align="center">User-wise Data</h5>
				<table>
					<tr>
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center" style="font-weight: bold;">User-wise Down Kiosks</h5>
									<canvas id="doughnut1"
										class="chart chart-doughnut chartHeighWidth"
										chart-data="doughnutData1[0].rowData" chart-labels="labels1"
										chart-options="options1[0]" chart-colors="colors1"> </canvas>
									<br />
								</div>
								<div >
									<table align="center">
										<tr>
											<th>User</th>
											<th>Available Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse1" ng-if="num.userName != null">
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
						<td>
							<div class="rcorners">
								<div id="chartDiv">
									<h5 align="center" style="font-weight: bold;">User-wise Zero Txn Kiosks</h5>
									<canvas id="doughnut2"
										class="chart chart-doughnut chartHeighWidth"
										chart-data="doughnutData2[0].rowData" chart-labels="labels2"
										chart-options="options2[0]" chart-colors="colors2"> </canvas>
									<br />
								</div>
								<div >
									<table align="center">
										<tr>
											<th>User</th>
											<th>Zero Transaction Kiosks (No.)</th>
											<th>Total Kiosks(No.)</th>
											<th>Availability(in %)</th>
										</tr>
										<tr ng-repeat="num in apiResponse2" ng-if="num.userName != null">
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
						
						<!-- <td>
							<div class="rcorners">
							<div class="absolute">
								<div><h5 align="center" style="font-weight: bold;"> Urgent Information</h5></div>
								<ul>
									<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
								</ul>
								</div>
							</div>

						</td> -->
						
						</tr>
					<!-- 	<tr>
						
					</tr> -->
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