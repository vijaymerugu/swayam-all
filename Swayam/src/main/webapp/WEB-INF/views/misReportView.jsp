<%@ page import="sbi.kiosk.swayam.common.dto.UserDto"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap-datepicker3.css" />
<script src="resources/js/misReportView.js"></script>
<script src="resources/js/FileSaver.js"></script>
<script src="resources/js/angular.js"></script>
<script src="resources/js/angular-touch.js"></script>
<script src="resources/js/angular-animate.js"></script>
<script src="resources/js/angular-aria.js"></script>

<link rel="stylesheet" href="resources/css/body-page.css" />
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/picklist.css">
<script>
	$(document).ready(function() {
		$('#datepickerFromDate,#datepickerToDate').datepicker({
			changeYear : true,
			changeMonth : true,
			autoclose : true,
			endDate : '+0d',
			format : 'dd-mm-yyyy',
			orientation : "top"
		});
	});
</script>
</head>
<body>
	<%
		UserDto userObj = (UserDto) session.getAttribute("userObj");
	String pfId = "";
	if (userObj.getPfId() != null) {
		pfId = userObj.getPfId();
	}
	%>
	<input type="hidden" id="pfId" value="<%=pfId%>">
	<div class="main" ng-app="misReportViewModule" id="appId">
		<div ng-controller="misReportViewController as vm">
			<div class="submain">
				<div class="row">
					<div class="col-sm-12">&nbsp;</div>
					<div class="col-sm-2">Grouping Criteria:</div>
					<div class="col-sm-2">
						<select id="groupingCriteria"
							ng-model="selectedGroupingCriteria.criteria"
							ng-change="onGroupingCriteriaChanged()"
							ng-options="criteria as criteria.name for criteria in groupingCriteriaList"></select>
					</div>
					<div class="col-sm-1">From Date:</div>
					<div class="col-sm-2">
						<input type="text" id="datepickerFromDate" name="input1" 
							readonly="readonly" placeholder="dd-mm-yyyy" required maxlength="10" />
					</div>
					<div class="col-sm-1">To Date:</div>
					<div class="col-sm-2">
						<input type="text" id="datepickerToDate" name="input2" 
							readonly="readonly" placeholder="dd-mm-yyyy" required maxlength="10" />
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-1"></div>
				</div>

				<div class="row">
					<div class="col-sm-12">&nbsp;</div>
					<div class="col-sm-12">
						<pick-list options="options" result="resultList"></pick-list>
					</div>

				</div>
				<div class="row">
					<div class="col-sm-2">Report Type:</div>
					<div class="col-sm-2">
						<select id="reportType"
							ng-model="selectedReportType.reportType"
							ng-options="reportType as reportType.name for reportType in reportTypeList"></select>
					</div>
					<div class="col-sm-8"></div>
				</div>
				<div class="row">
					<div class="col-sm-6"></div>
					<div class="col-sm-4">
						<button type="button" class="btn btn-primary" 
							ng-click="reset()">Reset</button>

						<button type="button" class="btn btn-primary" 
							ng-click="generateReport()">Generate</button>
					</div>
					<div class="col-sm-2"></div>
				</div>
	
	<script type="text/ng-template" id="component/pickListTmpl.html">

  	<div class="row">
		<div class="col-sm-2">Report Columns:</div>
		<div class="col-sm-4" >All Columns
    		<select class="form-control pickListSelect" id="pickData" multiple ng-model="pickListSelect" ng-options="data as data.text for data in options.data track by data.id">		
            </select>
        </div>
    	<div class="col-sm-1 pickListButtons">
			<div>&nbsp;</div>
			<div>&nbsp;</div>
        	<button ng-click="add()" class="btn btn-primary btn-sm" style="margin-bottom:2px; width:55px;">></button>
            <button ng-click="addAll()" class="btn btn-primary btn-sm" style="margin-bottom:2px; width:55px;">>></button>
            <button ng-click="remove()" class="btn btn-primary btn-sm" style="margin-bottom:2px; width:55px;"><</button>
        	<button ng-click="removeAll()" class="btn btn-primary btn-sm" style="margin-bottom:2px; width:55px;"><<</button>
        </div>
        <div class="col-sm-4">Selected Columns
        <select class="form-control pickListSelect" id="pickListResult" multiple ng-model="pickListResultSelect" ng-options="data as data.text for data in result.data track by data.id">
        </select>
        </div>
		<div class="col-sm-12">&nbsp;</div>
    </div>
  </script>
			</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"),
				[ 'misReportViewModule' ]);
	</script>
</body>
</html>