<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">


<script src="resources/js/contact.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
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
}
table {
    counter-reset: tableCount;     
}

th{
  border: 1px solid black;
  background-color:#00bfff;
  color: white;
}

tr:nth-child(even) {background-color: #f2f2f2;}

.counterCell:before {              
    content: counter(tableCount); 
    counter-increment: tableCount; 
}
    </style>
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="ContactCtr as vm">
<%-- <input type="hidden" name="_csrf" ng-model="csrf"  value="<%=session.getAttribute("csrfToken")%>">  --%>
 <input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >

<div class="submain">

<table style="width: 60%;">
		<p
			style="font-weight: bold; background: #FFC107; width: 60%; margin-bottom: 0px;margin-left: auto;
    margin-right: auto;">Escalation
			Matrix Level 1 - Call logging for installation and support Swayam
			Kiosks</p>
		<tr>
			<th>Sr.no</th>
			<th>Company Name</th>
			<th>Contact Nos.<b style="color: red;">(Level 1)</b></th>
			<th>Email id</th>
		</tr>
		<tr ng-repeat="num in Content">
			<td class="counterCell"></td>
			<td>{{num.contactName}}</td>
			<td><b style="color: red;">{{num.l1contactNo}}</b></td>
			<td>{{num.email}}</td>
		</tr>
		
	</table>

	</table>

	<p>&nbsp;</p>

	<table style="width: 60%;">
		<p
			style="font-weight: bold; background: #FFC107; width: 60%; margin-bottom: 0px;margin-left: auto;
    margin-right: auto;">Escalation
			Matrix Level 2 - Call logging for installation and support sSwayam
			Kiosks</p>
		<tr>
			<th>Sr.no</th>
			<th>Company Name</th>
			<th>Contact Nos.<b style="color: red;">(Level 2)</b></th>
			<th>Escalation Matrix</th>
		</tr>
		<tr ng-repeat="num in Content">
			<td class="counterCell"></td>
			<td>{{num.contactName}}</td>
			<!-- <td><div><iframe src="{{num.l2Contact}}" type='application/pdf' id="pdfPreview"/></div></td> -->
		<!-- 	<td><div><iframe src="{{num.l2Contact}}" type='application/pdf' id="pdfPreview"/></div></td>
			 -->
			<!-- <td><div><iframe src="resources/Test.pdf" type='application/pdf' id="pdfPreview"/></div></td> -->
			<td><!-- <a class="openpdfonclick" style="cursor: hand;cursor: pointer;"> -->
			<a href='resources/faq/{{num.contactName}}.pdf' target="_blank">
			<img src="resources/img/pdf.svg"></a></td>
			<td>{{num.esMatrix}}</td>
		</tr>

	</table>

	<tr>
		<p>&nbsp;</p>

		<table style="width: 60%;">
			<p style="font-weight: bold; background: #FFC107; width: 60%;margin bottom: 0px;margin-left: auto;
    margin-right: auto;">Escalation
				matrix : Level 3 at Electronic Channel Redesign (ECR) Department,
				Corporate Centre Mumbai</p>

			<p
				style="font-weight: bold; margin-top: 16px; background: #FFC107; width: 60%; color: red; margin bottom: 0px;margin-left: auto;
    margin-right: auto;">(TO
				BE CONTACTED ONLY IF ISSUES WERE NOT RESOLVED THROUGH LEVEL 1 & 2
				ESCALATION MATRIX)</p>
						<tr>
							<th>Sr.No</th>
							<th>Name</th>
							<th>Designation</th>
							<th>Contact No</th>
							<th>Email</th>
						</tr>
						<tr ng-repeat="num in first">
							<td class="counterCell"></td>
							<td>{{num.name}}</td>
							<td>{{num.designation}}</td>
							<td>{{num.contactNo}}</td>
							<td>{{num.email}}</td>
						</tr>

					</table>

		<p>&nbsp;</p>

		<table style="width: 60%;">
			<p
				style="font-weight: bold; background: #FFC107; width: 60%; margin-bottom: 0px;margin-left: auto;
    margin-right: auto;">Escalation
				matrix : Escalation matrix at IT Special Projects-III Department,
				GITC Belapur for Swayam Performance Monitoring related issues</p>

						<tr>
							<th>Sr.No</th>
							<th>Name</th>
							<th>Designation</th>
							<th>Contact No</th>
							<th>Email</th>
						</tr>
						<tr ng-repeat="num in second">
							<td class="counterCell"></td>
							<td>{{num.name}}</td>
							<td>{{num.designation}}</td>
							<td>{{num.contactNo}}</td>
							<td>{{num.email}}</td>
						</tr>

					</table>
	
	
		
		
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
/* angular.module("app").constant("CSRF_TOKEN", '{{ csrf_token() }}') */
</script>

 <script type="text/javascript">
      
      $(document).ready(function(){

    	  
    	 // window.open("resources/download/"+data , '_blank'); 
    	    $(".openpdfonclick").click(function(){
    	    	
    	    	console.log("Inside.. " );
    	    	window.open("resources/Test.pdf", '_blank');
    	    	console.log("Done.. " );
    	       /*  $.ajax({
    	            url: 'report?page=bpReport&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	
    	            	if(data.includes(".pdf")){
    	            		cc
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            	}
    	            	
    	            	//window.open("resources/download/"+data , '_blank');  
    	            }
    	        }); */
    	    });
    	  
    	}); 
    		
    		
      
      </script>






</body>

</html>