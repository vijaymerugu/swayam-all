<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" >
<head>

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
	

<script src="/resources/js/ticket-centor-sa-app.js"></script>
 <link rel="stylesheet" href="/resources/css/grid-style.css" /> 
 
 
 <!-- 44 -->
 
 
 <script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css"/>

<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/lodash.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/jszip.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/excel-builder.dist.js"></script>  
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-aria.js"></script>

	
	

</head>
<body>


<script type="text/javascript">
        $(document).ready(function() {
        	
        	 $("#maintable").on('click','.fa',function(){
                  var currentRow=$(this).closest("tr"); 
             
             var category=currentRow.find("td:eq(0)").text();
            	// var category=document.getElementById("category").value;
        		 console.log("inside category function...."+category);
                $.ajax({
                    type: "GET",
                    url: "/km/categoryCall/"+category, //this is my servlet
                   
                    success: function(data){
                    
                    	console.log(data);
                           // $('#output').append(msg);
                    	debugger;
        				$("#demo12").show();
        				  var toAppend = '';
        	        	  var toAppendVen = '';
        	              $.each(data,function(i,o){
        	            	  
        	            	  
        	            	 
        	            	   toAppend += '<tr><td  ><span class="clientID" id="demo2"><a onclick="angular.element(this).scope().create();"   ng-click="getCountType()">'+o.subCategory+'</a></span></td><td style="display:none;">'+o.category+'</td><td><span class="btnSelect" style="background-color:#280071;color:white;border-radius: 25px;padding-left:7px;padding-right:7px" >'+o.count+'</span></td></tr>';
        	            	    '<input type="hidden" id="catlo" name="catlo" value="'+o.category+'"/>'
        	            	//$('#catlo').val(o.category);
        	            
        	            	   //  toAppendVen = document.getElementById("ticketId").value = o.ticketId;
        	 	             // toAppendVen = '<button>'+o.ticketId+'</button>';
        	 	          //   $('#subCategory')+toAppend +"<br />");
        	 	          //  $('#ticketId').append(toAppendVen+"<br />");
        	 	 
        	              console.log(toAppend);
        	             });
        	             
                $("#subCategory").html( toAppend);
        	         }
        	        });
               
            });

        });
    </script>
    <script type="text/javascript">
   $(document).ready(function() {
	    $("#category_table").hide();
	});
</script>
  
    <script type="text/javascript">
    $(document).ready(function(){

        // code to read selected table row cell data (values).
        $("#myTable").on('click','.btnSelect',function(){
             // get the current row
             var currentRow=$(this).closest("tr"); 
             var subCategory=currentRow.find("td:eq(0)").text(); 
             var category=currentRow.find("td:eq(1)").text();
             console.log("category Saten "+category);
             console.log("subCategory Saten "+subCategory);
            
          
        });
    });
    </script>



	<div class="main" ng-app="app" id="appId">

		<div ng-controller="UserManagementCtrlSA as vm">

			<div
				style="top: 152px; left: 15px; width: 1336px; height: 190px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1;">


				<div class="col-md-6">
					<table align="right">

						<col width="50">
						<col width="30">

						<thead style="font-size: 15px;" align="right">
							<tr align="center">
								<th colspan="4" align="center"
									style="color: #000000; font-size: 12px; font-weight: bold; width: 20px;">
									<span style="margin-left: 101px; margin-right: 70px;">AGEING
										OF TICKETS</span>
								</th>
							</tr>
						</thead>
						<tbody style="font-size: 1px;">
							<tr>

								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a
									ng-click="getCountType('TwoToFourHrsCount')"><c:out
											value="${ageingMapDataList['TwoToFourHrsCount']}" /></a></td>
								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a ng-click="getCountType('OneDaysCount')"><c:out
											value="${ageingMapDataList['OneDaysCount']}" /></a></td>
								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a
									ng-click="getCountType('ThreeDaysLessCount')"> <c:out
											value="${ageingMapDataList['ThreeDaysLessCount']}" /></a></td>
								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a
									ng-click="getCountType('ThreeDayGreaterCount')"><c:out
											value="${ageingMapDataList['ThreeDayGreaterCount']}" /></a></td>
								<td
									style="color: #13A8E0; font-size: 20px; font-weight: bold; border-right: solid 1px #0307fc;"
									width="10%"><a ng-click="getCountType('TotalCount')"><c:out
											value="${ageingMapDataList['TotalCount']}" /></a></td>
							</tr>
						</tbody>
						<thead align="center" style="font-size: 11px;">
							<tr>
								<th colspan="1" align="center">2-4 Hrs</th>
								<th colspan="1" align="center"><1 Day</th>
								<th colspan="1" align="center"><3 Days</th>
								<th colspan="1" align="center">3> Days</th>
								<th colspan="1" style="border-right: solid 1px #0307fc;"
									align="center">Total</th>
							</tr>
						</thead>


					</table>
				</div>

				<div class="col-md-4">
					<table>

						<col width="40">
						<col width="30">

						<thead style="font-size: 15px;" align="left" align="center">
							<tr>
								<th colspan="4"
									style="color: #000000; font-size: 10px; font-weight: bold; width: 53px;">
									<span
									style="margin-left: 53px; margin-right: 88px; font-size: 12px;">SEVERITY
										OF TICKETS</span>
								</th>
							</tr>
						</thead>

						<tbody
							style="color: #13A8E0; font-size: 20px; font-weight: bold; width: 12px;">
							<tr>
								<td align="center"
									style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="12%"><a id="high" ng-click="getCountType('High')"><c:out
											value="${mapDataList['High']}" /></a></td>
								<td align="center"
									style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="12%"><a id="countMedium"
									ng-click="getCountType('Medium')"><c:out
											value="${mapDataList['Medium']}" /></a></td>
								<td align="center"
									style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="12%"><a id="countLow"
									ng-click="getCountType('Low')"><c:out
											value="${mapDataList['Low']}" /></a></td>
								<td
									style="color: #13A8E0; font-size: 20px; font-weight: bold; text-align: left: 10px;"
									width="12%"><a id="countTotal"
									ng-click="getCountType('Total')"><c:out
											value="${mapDataList['Total']}" /></a></td>
							</tr>
						</tbody>

						<thead style="font-size: 11px;">
							<tr>
								<th colspan="1" width="12%">High</th>
								<th colspan="1" align="center">Medium</th>
								<th colspan="1" align="center">Low</th>
								<th colspan="1" align="center">total</th>
							</tr>
						</thead>
					</table>
				</div>

			</div>

			<br></br>

			<!--  -->


			<div style="top: 1000px; left: 15px; width: 300px; height: 519px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1; border: 1px solid black #eee;">

	
<header style="top: 366px;left: 33px;width: 309px;height: 20px;text-align: left;font: Medium 17px/20px Effra;letter-spacing: 0;color: #000000;opacity: 1; text-align: center;">Call Category : Overall Summary of errors</header>
<br>
<div class="container">

		<div class="row" >
		<div class="col-md-3" id="left">
		<div>
				<table id="maintable">

					<thead style="font-size: 20px; background-color: #12A8E0;">
						<tr>
							<th colspan="3" align="center"	style="color: #FFFFFF; font-size: 12px;">Errors - Call	Category Wise</th>
						</tr>
					</thead>

					<tbody style="font-size: 15px;">
								 
									<c:forEach items="${categoryMapDataList}" var="mapData">
									<tr>
										<td style="font-size: 15px;color:#280071;">
				                         <c:out value="${mapData.key}"></c:out></td>
										
	<td style="font-size: 15px; color: #280071;"><span style="background-color:#280071;color:white;border-radius: 25px;padding-left:7px;padding-right:7px" >
								  <c:out value="${mapData.value}"></c:out> </span>
							        <%-- <input type="hidden"  id="category" name="category"  value="${mapData.key}" /> --%>
							        </td>
								
						<td><span data-toggle="collapse" data-target="#demo"
											data-target="#demo1" class="fa fa-chevron-circle-down"
											style="font-size: 15px; color: #280071;"> </span>
							<input type="hidden"  id="category"  name="category" value="${mapData.key}" />
						</td>		
								
									</tr>
									<td style="font-size: 15px; color: #280071;">
									<form:form	class="form-horizontal" name="form" action="">

											<div id="demo12" class="collapse">
												<table id="myTable" class="table">
										
														<tbody style="font-size: 15px;" id="subCategory">
														
														</tbody>

												</table>


											</div>
										</form:form></td>
									
									<!-- <div class="collapse">
												<table id="maintable">
												
												</table>


											</div> -->
									
									
									</c:forEach>
								


							</tbody>

				</table>

			</div>
</div>



			<div
				style="width: 1036px; height: 346px; position: absolute; top: 890px; bottom: 910px; left: 311px; right: 1036px; margin: auto; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1; border: 1px solid black #eee;">

				<div style="border-bottom: 1px solid #eee;">
					<span class="fa fa-search form-control-feedback" id="catsandstars"></span>
					<input class="form-group has-search" ng-model="searchText"	ng-change="refresh()" placeholder=" Enter Vendor Name,Branch Code,Ticket Id,Kiosk ID.."	id="input"> <br /> <br />

					<div
						style="top: 355px; left: 15px; width: 1336px; height: 519px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1;"
						ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter	id="test">
						
					 </div>

				</div>

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