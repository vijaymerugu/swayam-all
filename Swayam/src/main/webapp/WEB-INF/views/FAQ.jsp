<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">


<!-- <script src="resources/js/contact.js"></script> -->
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<!-- <link rel="stylesheet" href="resources/css/body-page.css"/> -->
<link rel="stylesheet" href="resources/css/style.css">

<link rel="stylesheet" href="resources/css/font-awesome.min.css"/> 

<script src="resources/js/a076d05399.js"></script>

<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>


	
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
    
    
    <!-- Include Date Range Picker -->
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap-datepicker3.css" />
	
	


<style>
        table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
	margin-left: auto;
    margin-right: auto;
    font-weight: bold;
}
table {
    counter-reset: tableCount;     
}
.counterCell:before {              
    content: counter(tableCount); 
    counter-increment: tableCount; 
}

th{
  border: 1px solid black;
  background-color:#00bfff;
  color: white;
}

tr:nth-child(even) {background-color: #f2f2f2;}

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
    </style>
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="ContactCtr as vm">
<%-- <input type="hidden" name="_csrf" ng-model="csrf"  value="<%=session.getAttribute("csrfToken")%>">  --%>
 <input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >

<div class="submain">


	<p>&nbsp;</p>

	<table style="width: 60%;">
		
		<tr>
			<th>Sr.no</th>
			<th>FAQ</th>
			<th>Manual</th>
		</tr>
		<tr>
			<td>1</td>
			<td>Swayam HandBook</td>		
			<td>
			<a href='resources/faq/SWAYAM_Hand_book.pdf' target="_blank">
			<img src="resources/img/Handbook11.PNG" ></a></td>			
		</tr>
		<tr>
			<td>2</td>
			<td>Printer Configuration</td>		
			<td>
			<a href='resources/faq/Instructions_on _Printer_Calibration_Settings.pdf' target="_blank">
			<img src="resources/img/Printer.png" ></a></td>			
		</tr>
		<tr>
			<td>3</td>
			<td>Ribbon replacement Video <br> English narration</td>		
			<td>
			<a href='resources/faq/RIBBON_REPLACEMENT_ENGLISH.mp4' target="_blank">
			<img src="resources/img/Ribbon.png" ></a></td>			
		</tr>
		<tr>
			<td>4</td>
			<td>Ribbon replacement Video Hindi narration</td>		
			<td>
			<a href='resources/faq/RIBBON_REPLACEMENT_HINDI.mp4' target="_blank">
			<img src="resources/img/Ribbon.png" ></a></td>			
		</tr>
		<tr>
			<td>5</td>
			<td>Universal Passbook Annexture-I</td>		
			<td>
			<a href='resources/mp4/Universal_Passbook_Annexure_1.pdf' target="_blank">
			<img src="resources/img/Passbook.PNG" ></a></td>			
		</tr>
		<tr>
			<td>6</td>
			<td>Universal Passbook Annexture-II</td>		
			<td>
			<a href='resources/faq/Universal_Passbook_Annexure _II.pdf' target="_blank">
			<img src="resources/img/Passbook.PNG" ></a></td>			
		</tr>

	</table>

	
	
		
		
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
/* angular.module("app").constant("CSRF_TOKEN", '{{ csrf_token() }}') */
</script>

</body>

</html>