<!DOCTYPE html>
<html lang="en">
<head>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<script src="resources/js/daTATOfDownKiosks.js"></script>
<link rel="stylesheet" href="resources/css/body-page.css" />
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/Chart.min.js"></script>
<script src="resources/js/angular-chart.min.js"></script>
<script src="resources/js/chartjs-plugin-labels.js"></script>
</head>
<body>
	<div class="main" ng-app="daTATOfDownKiosksModule" id="appId">
		<div ng-controller="daTATOfDownKiosksController as vm">
			<div class="submain">
				<h5>TAT of down kiosks (out of total zero txn kiosks) : No. of
					open calls not attended</h5>
				<div id="filters">
					<p style="text-align: center">
						<span style="background-color: #A0B421">&nbsp;&nbsp;&nbsp;</span>&nbsp;1 day
						<span style="display: inline-block; margin-left: 40px;"></span>
						<span style="background-color: #33B2FF">&nbsp;&nbsp;&nbsp;</span>&nbsp;2 - 5 days
						<span style="display: inline-block; margin-left: 40px;"></span>
						<span style="background-color: #FFE933">&nbsp;&nbsp;&nbsp;</span>&nbsp;1 week
						<span style="display: inline-block; margin-left: 40px;"></span>
						<span style="background-color: #DB23F5">&nbsp;&nbsp;&nbsp;</span>&nbsp;1 - 2 weeks
						<span style="display: inline-block; margin-left: 40px;"></span>
						<span style="background-color: #ED402A">&nbsp;&nbsp;&nbsp;</span>&nbsp;Greater than 2 weeks
					</p>
				</div>
			</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"),
				[ 'daTATOfDownKiosksModule' ]);
	</script>
</body>
</html>