<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
<script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css">

<!-- <script>
$(document).ready(function(){
    $(function () {
        $('#datepickerFromDate,#datepickerToDate').datepicker({dateFormat:'dd-mm-yy'});
    });
});

</script> -->

<script>
$(document).ready(function(){
    $(function () {

			$('#datepickerFromDate,#datepickerToDate').datepicker({ changeYear: true, changeMonth: true, dateFormat:'dd-mm-yy'});
		
    });
    
});
			
</script> 

<meta http-equiv="x-ua-compatible" content="IE=edge">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment-with-locales.min.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/transaction-error-reporting-app.js"></script>

<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>


</head>
<body>
          
	<div class="main" ng-app="app" id="appId">
		<div ng-controller="UserManagementCtrl as vm">


        <div>
				<table class="" style="border: 1px solid #eee;">
					<div>
						<br /> From Date: <input type="date" id="datepickerFromDate" name="input1" readonly="readonly"  ng-model="searchDateStart" placeholder="dd-mm-yyyy" required maxlength="10"  /> 
							To	Date : <input type="date" id="datepickerToDate" name="input2" readonly="readonly"	ng-model="searchDateEnd" placeholder="dd-mm-yyyy" required maxlength="10" />
					<button type="button"  ng-click="searchPositions(searchDateStart,searchDateEnd) ">Generate</button>
					</div>
 				</table>
			</div>
			<br/>

			<div class="submain">
				<br /> <br /> <input class="form-group has-search"	ng-model="searchText" ng-change="refresh()"	placeholder="Enter Kiosk Id, Branch Code, Circle etc."
					style="font-size: 12px" size="150" height="80" id="input">
                 <span style="float:right">
		<a class="openpdfonclick"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
				<br />
					<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test"></div>
			</div>
		</div>
	</div>
	<script>
		angular.bootstrap(document.getElementById("appId"), [ 'app' ]);
	</script>
<script type="text/javascript">
      
      $(document).ready(function(){

    	    $(".openpdfonclick").click(function(){
    	    	
    	        $.ajax({
    	            url: 'report?page=errorReporting&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	window.open("resources/download/"+data , '_blank');  
    	            }
    	        });
    	    });
    	    $(".openxlonclick").click(function(){    	
    	        $.ajax({
    	            url: 'report?page=errorReporting&type=excel',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	window.open("resources/download/"+data , '_blank');  
    	            }
    	        });
    	    });
    	}); 
    		
    		
      
      </script>
</body>
<sec:csrfInput />  
</html>