<!DOCTYPE html>
<html lang="en">
<head>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<script src="resources/js/daErrorTypeWiseUptime.js"></script>
<!-- <link rel="stylesheet" href="resources/css/body-page.css" /> -->
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/Chart.min.js"></script>
<script src="resources/js/angular-chart.min.js"></script>
<script src="resources/js/chartjs-plugin-labels.js"></script>
<style>
div.absolute {
	background: #FFFFFF 0% 0% no-repeat padding-box;
	position: absolute;
	top: 10px;
	right: 0;
	width: 300px;
	height: 795px;
	border-radius: 1px;
	border: 1px solid #73AD21;
	overflow: scroll;
}

.submain {
    top: 242px;
    left: 8px;
    width: 1050px;
    height: fit-content;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    box-shadow: 0px 3px 6px #8D8D8D29;
    opacity: 1;
    padding: 7px;
    border-radius: 1px;
    border: 1px solid #73AD21;
}

/* .submain {
	top: 242px;
	left: 8px;
	width: 1035px;
	background: #FFFFFF 0% 0% no-repeat padding-box;
	box-shadow: 0px 3px 6px #8D8D8D29;
	opacity: 1;
	padding: 7px;
	border-radius: 1px;
	border: 1px solid #73AD21;
} */

.bullet::before {
	content: "\2022";
	color: #00BFFF;;
	font-weight: bold;
	display: inline-block;
	width: 1em;
	margin-left: -1em;
}
</style>
</head>
<body>
	<div class="main" ng-app="daErrorTypeWiseUptimeModule" id="appId">
		<div ng-controller="daErrorTypeWiseUptimeController as vm">
			<div class="submain">
				<!-- <div id="filters"> -->
				<h5 style="font-weight: bold;">Error type-wise Uptime (in %)</h5>
					<p>
						<span>Error:</span> <select id="errorType"
							ng-model="selectedErrorType.errorType"
							ng-change="selectedItemChanged()"
							ng-options="errorType as errorType.name for errorType in errorTypeList"></select>
					</p>
					<p style="text-align: center">
						<span style="background-color: #ED402A">&nbsp;&nbsp;&nbsp;</span>&nbsp;Total
						Down Kiosks of Selected Error <span
							style="display: inline-block; margin-left: 40px;"></span> <span
							style="background-color: #A0B421">&nbsp;&nbsp;&nbsp;</span>&nbsp;Total
						Down Kiosks of Other error types
					</p>
					
			<!-- 	</div> -->
			</div>
			<div class="absolute">
			<div><h5 style="font-weight: bold;" align="center">Urgent Information</h5></div>
			<ul>
				<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
			</ul>
				
		</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"),
				[ 'daErrorTypeWiseUptimeModule' ]);
	</script>
</body>
</html>