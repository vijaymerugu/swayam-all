<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>

 <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css">

<script>
$(document).ready(function(){
    $(function () {
        $('#datepickerFromDate,#datepickerToDate').datepicker({dateFormat:'dd-mm-yy'});
    });
});

</script>

<script type="text/javascript">
$(function() {
        $("#datepickerToDate").datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: '2020:2020',
            dateFormat: 'dd-mm-yy',
            minDate: 0,
            defaultDate: null
        }).on('changeDate', function(ev) {
    if($('#datepickerToDate').valid()){
       $('#datepickerToDate').removeClass('invalid').addClass('success');   
    }
 });

    });
</script>
<script>
        $(function() {
            $( "#datepicker" ).datepicker({
                showOn: "button",
                buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                buttonImageOnly: true
            });
        });
</script> 

<!-- FOR BOOTSTRAP -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7\" />
<!--End here  -->
<script src="resources/js/transaction-error-reporting-app.js"></script>

<!-- <script src="resources/js/jquery.3.4.1.min.js"></script> -->
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css" />
<link rel="stylesheet" href="resources/css/body-page.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css" />


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<script src="resources/js/csv.js"></script>
<script src="resources/js/pdfmake.js"></script>
<script src="resources/js/vfs_fonts.js"></script>
<script src="resources/js/lodash.min.js"></script>
<script src="resources/js/jszip.min.js"></script>
<script src="resources/js/excel-builder.dist.js"></script>
<script src="resources/js/angular.js"></script>
<script src="resources/js/angular-touch.js"></script>
<script src="resources/js/angular-animate.js"></script>
<script src="resources/js/angular-aria.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- <script type="text/javascript">
$(document).ready(function () {
    var currentDate = new Date();

    $("input.datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        maxDate: 0,
        changeYear: true
    });

    $("#DisplayFromDateTextBox").datepicker("setDate", currentDate).datepicker('show');
    $("#DisplayToDateTextBox").datepicker();
});
</script>  
 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha256-rByPlHULObEjJ6XQxW/flG2r+22R5dKiAoef+aXWfik=" crossorigin="anonymous" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha256-KM512VNnjElC30ehFwehXjx1YCHPiQkOPmqnrWtpccM=" crossorigin="anonymous"></script>

<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <script>
         $(function() {
        	
            $( "#datepicker1" ).datepicker({dateFormat:'dd-mm-yy'});
         });
      </script>

</head>
<body>



	<div class="main" ng-app="app" id="appId">
		<div ng-controller="UserManagementCtrl as vm">
		
		
		 <p>Enter Date: <input type="text" id="datepicker1"></p>
		 
		 <br/>
		
		
		<input class="datepicker" id="ReleaseDate" name="ReleaseDate"  type="text"/>


<!-- 
<div>
        Welche Tage sollen angezeigt werden:
    <input type="text" ID="DisplayFromDateTextBox" class="datepicker"/>
        Bis
    <input type="text"  ID="DisplayToDateTextBox" class="datepicker"/>
        <input type="button" ID="DisplayButton" OnClick="DisplayButton_Click"/>
    </div> -->
		
		

			<div>
				<table class="" style="border: 1px solid #eee;">
					<div>
						<br /> From Date: <input type="date" id="datepickerFromDate" name="input1" ng-model="searchDateStart" placeholder="dd-mm-yyyy" required maxlength="10" /> 
							To	Date : <input type="date" id="datepickerToDate" name="input2"	ng-model="searchDateEnd" placeholder="dd-mm-yyyy" required maxlength="10" />
						<button type="button"  ng-click="searchPositions(searchDateStart,searchDateEnd) ">Generate</button>






					</div>
 

				</table>
			</div>
			<br />

			<div class="submain">
				<br /> <input class="form-group has-search" ng-model="searchText"	ng-change="refresh()"	placeholder="Enter Kiosk Id, Branch Code, Circle etc."
					style="font-size: 12px" size="150" height="80" id="input">
        <span style="float:right">
		<a class="openpdfonclick"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
				<br />
				<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination	ui-grid-selection ui-grid-exporter id="test"></div>

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
</html>