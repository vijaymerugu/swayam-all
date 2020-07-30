<!DOCTYPE html>
<html lang="en">
<head>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<script src="resources/js/daAvailability.js"></script>
<link rel="stylesheet" href="resources/css/body-page.css" />
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/Chart.min.js"></script>
<script src="resources/js/angular-chart.min.js"></script>
<script src="resources/js/chartjs-plugin-labels.js"></script>
</head>
<body>
	<div class="main" ng-app="daAvailabilityModule" id="appId">
		<div ng-controller="daAvailabilityController as vm">
			<div class="submain">
				<div id="filters">
				<h5>Availability (in %)</h5>
					
					<p style="text-align: center">
						<span style="background-color: #A0B421">&nbsp;&nbsp;&nbsp;</span>&nbsp;Total
						Operational Kiosks <span
							style="display: inline-block; margin-left: 40px;"></span> <span
							style="background-color: #ED402A">&nbsp;&nbsp;&nbsp;</span>&nbsp;Total
						Non-Operational Kiosks
					</p>
				</div>
			</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"),
				[ 'daAvailabilityModule' ]);
	</script>
</body>
</html>