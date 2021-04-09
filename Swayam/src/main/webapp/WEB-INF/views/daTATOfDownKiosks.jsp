<!DOCTYPE html>
<html lang="en">
<head>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<script src="resources/js/daTATOfDownKiosks.js"></script>
<!-- <link rel="stylesheet" href="resources/css/body-page.css" /> -->
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/Chart.min.js"></script>
<script src="resources/js/angular-chart.min.js"></script>
<script src="resources/js/chartjs-plugin-labels.js"></script>
<style>
/* div.absolute {
	background: #FFFFFF 0% 0% no-repeat padding-box;
	top: 10px;
	right: 0;
	width: 1500px;
	 height: 100px; 
	border-radius: 1px;
	border: 1px solid #73AD21;
	
	margin-bottom: 5px;
	 overflow: auto;
	
}

.submain {
    top: 242px;
    left: 8px;
    width: 1500px;
    height: fit-content;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    box-shadow: 0px 3px 6px #8D8D8D29;
    opacity: 1;
    padding: 7px;
    border-radius: 1px;
    border: 1px solid #73AD21;
   
}  */

div.absolute {
	background: #FFFFFF 0% 0% no-repeat padding-box;
	top: 10px;
	right: 0;
	/* width: 1500px; */
	width: 100%;
	 height: 100px; 
	border-radius: 1px;
	border: 1px solid #73AD21;
	
	margin-bottom: 5px;
	 overflow: auto;
	
}

.submain {
    top: 242px;
    left: 8px;
     /*  width: 1500px; */
   	width: 100%;
	overflow:auto;
    height: fit-content;
    background: #FFFFFF 0% 0% no-repeat padding-box;
    box-shadow: 0px 3px 6px #8D8D8D29;
    opacity: 1;
    padding: 7px;
    border-radius: 1px;
    border: 1px solid #73AD21;
   
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
/* div.absolute {
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
 */
/* .submain {
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
} */

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

/* .bullet::before {
	content: "\2022";
	color: #00BFFF;;
	font-weight: bold;
	display: inline-block;
	width: 1em;
	margin-left: -1em;
} */
</style>
</head>
<body>
	<div class="main_transaction" ng-app="daTATOfDownKiosksModule" id="appId">
		<div ng-controller="daTATOfDownKiosksController as vm">
		<div class="absolute">
			<div><h5 style="font-weight: bold;" align="center">Urgent Information</h5></div>
			<ul>
				<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
			</ul>
				
		</div>	
			<div class="submain">
				<h5 style="font-weight: bold;">TAT of down kiosks (out of total zero txn kiosks) : No. of
					open calls not attended</h5>
				<!-- <div id="filters"> -->
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
				<!-- </div> -->
			</div>
			<!-- <div class="absolute">
		<div><h5 style="font-weight: bold;" align="center">Urgent Information</h5></div>
			<ul>
				<li class="bullet" ng-repeat="item in UrgentMessages">{{item.message}}</li>
			</ul>
				
		</div> -->
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"),
				[ 'daTATOfDownKiosksModule' ]);
	</script>
</body>
</html>