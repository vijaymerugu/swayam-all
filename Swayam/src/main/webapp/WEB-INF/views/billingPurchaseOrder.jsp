<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/billingPurchaseOrder.js"></script>
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
<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>

<script>
	
	$(document).ready(function() {

		$(".openFinalPopup").click(function() {
			
			        var all_rows = [];
			        var keyDisplay = [];
			        var valueText = [];
			        var errorList=[];
			        
			        $('.addedRows').each(function() {
			                var this_row={};                
			                $(this).find("input").each(function(){                
			                var keyvalue;
			                mystring = $(this).attr('name');
			                var matches = mystring.match(/\[(.*?)\]/);
			                if (matches) {
			                    keyvalue = matches[1];
			                }                
			                namevalue = $(this).val();                
			                if(namevalue !=undefined && namevalue != ''){
			                	this_row[keyvalue] = namevalue;
			                	all_rows.push(this_row);
			                	keyDisplay.push(keyvalue);  
			                	valueText.push(namevalue);
			                }
			                
			                
			            });          

			        });
			        console.log(all_rows);               
			        
			        var arrayLength = valueText.length;
				    for (var i = 0; i < arrayLength; i++) {
				        console.log(valueText[i]);
				        var value = valueText[i];        	        
				        if (!value.match(/^[a-zA-Z0-9. ]+$/)) 
					    {    				 
						 errorList.push('Only alphabets and numbers are allowed');    			        
					    }
				    }
				    
				    if(errorList.length>0){       			 			 
				    	$("#myModal").modal();
			        	$("#para").html("Only alphabets and numbers are allowed in Remarks by Approver");
					 	}else{

			        $.ajax({
			            type: "POST",
			            //url: "/hm/saveCheckerComments?array="+all_rows,
			            url: "saveBillingPurchaseOrde",
			            //data: '{array: "' + all_rows + '"}',
			            data: JSON.stringify(all_rows),
			            contentType: "application/json; charset=utf-8",
			            dataType: "json",
			            success: function (response) {
			            	$("#myModal").modal();
			                $("#para").html("Successfully approved:"+ keyDisplay.join(',')); 
			            	console.log('Success');
			            },
			            failure: function (response) {
			            	console.log('Failed');
			            }
			        });
			      }
			    }); 





	
</script>



<style>
* {
	box-sizing: border-box;
}

.column {
	float: left;
	width: 10%;
	padding: 7px;
}

.columnUpload {
	float: left;
	width: 15%;
	padding: 1px;
}

.columnSubmit {
	float: left;
	width: 10%;
	padding: 3px;
}

/* Clearfix (clear floats) */
.row::after {
	content: "";
	clear: both;
	display: table;
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: #fced19; /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.modal-content {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding: 0;
	border: 1px solid #black;
	width: 40%;
	height: 40%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 60%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
</head>
<body>
   
		
		<div class="main" ng-app="app" id="appId">
		<div ng-controller="BillingOrderCtrl as vm">
		
	
			
		<div class="submain">
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle,vendor, etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		
		<br/>
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
       <div class="form-group"> 
          <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-default" ng-click = "save()">save</button> 
          
        <!--   <input type="submit" value="Save" class="openFinalPopup"> -->
          </div>
      </div>
    </div>
    </div>
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script>	

</body>
</html>