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
	

<!-- <script src="/resources/js/ticket-centor-sa-app.js"></script> -->
 <link rel="stylesheet" href="resources/css/grid-style.css" /> 
 
 
 <!-- 44 -->
 
 
 <script	src="resources/js/angular.1.5.6.min.js"></script>

<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet" /> -->
<!-- <link rel="stylesheet" href="resources/css/font-awesome.css" />  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 

    
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>


<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>

<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script> 
	
    <style>
	
    .ui-grid-contents-wrapper {
    position: relative;
    height: 100%;
    width: 100%;
}
    		.ui-grid, .ui-grid-viewport {
   			  height: auto !important; 
			} 
			.ui-grid-pager-panel {
		     position: relative;
			 } 
	
			.ui-grid-pager-row-count-picker {
			display:none;
			}
			.ui-grid-render-container-body .ui-grid-viewport.no-horizontal-bar.no-vertical-bar {
			    overflow-x: hidden !important;
			    overflow-y: hidden !important;
			}
			.ui-grid-header-canvas {
		    padding-top: 0px;
		    padding-bottom: 0px;}		
</style>
</head>
<body>


<script type="text/javascript">
        $(document).ready(function() {
        	
        	 $("#maintable").on('click','.fa',function(){
                  var currentRow=$(this).closest("tr"); 
             
             var cat=currentRow.find("td:eq(0)").text();
             var category = cat.trim();  
             var catId = category.replace(/ /g, "");
            	// var category=document.getElementById("category").value;
        		 console.log("inside category function...."+category);
                $.ajax({
                    type: "GET",
                    url: "hm/categoryCall/"+category, //this is my servlet
                   
                    success: function(data){
                    
                    	console.log(data);
                           // $('#output').append(msg);
                    	//debugger;
        				$("#demo12").show();
        				  var toAppend = '';
        	        	  var toAppendVen = '';
        	              $.each(data,function(i,o){
        	            	  
        	            	  
        	            	 
        	            	   toAppend += '<tr><td  ><span class="clientID" id="demo2"><a onclick="angular.element(document.getElementById(\'appId\')).scope().getCountType(\''+o.subCategory+'\')">'+o.subCategory+'</a></span></td><td style="display:none;">'+o.category+'</td><td><span class="btnSelect" style="background-color:#280071;color:white;border-radius: 25px;padding-left:7px;padding-right:7px" >'+o.count+'</span></td></tr>';
        	            	    '<input type="hidden" id="catlo" name="catlo" value="'+o.category+'"/>'
        	            	//$('#catlo').val(o.category);
        	            
        	            	   //  toAppendVen = document.getElementById("ticketId").value = o.ticketId;
        	 	             // toAppendVen = '<button>'+o.ticketId+'</button>';
        	 	          //   $('#subCategory')+toAppend +"<br />");
        	 	          //  $('#ticketId').append(toAppendVen+"<br />");
        	 	 
        	              console.log(toAppend);
        	             });
        	       var sub= 'sub'+catId;
        	               	       
                $('#'+sub).html(toAppend);
        	         }
        	        });
               
            });
        
        	 

        });
    </script>
    <script type="text/javascript">
   
   function javaScriptCall(){
		 
			var scope = angular.element(document.getElementById('appId')).scope();
			scope.create();        					
		}  
   
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



	<div class="main_transaction" ng-app="app" id="appId" ng-controller="UserManagementCtrlSA">

		

			<div class="subTable">


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
									ng-click="getCountType('TwoToFourHrsCount')" style="cursor: hand;cursor: pointer;"><c:out
											value="${ageingMapDataList['TwoToFourHrsCount']}" /></a></td>
								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a ng-click="getCountType('OneDaysCount')" style="cursor: hand;cursor: pointer;"><c:out
											value="${ageingMapDataList['OneDaysCount']}" /></a></td>
								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a
									ng-click="getCountType('ThreeDaysLessCount')" style="cursor: hand;cursor: pointer;"> <c:out
											value="${ageingMapDataList['ThreeDaysLessCount']}" /></a></td>
								<td style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="10%"><a
									ng-click="getCountType('ThreeDayGreaterCount')" style="cursor: hand;cursor: pointer;"><c:out
											value="${ageingMapDataList['ThreeDayGreaterCount']}" /></a></td>
								<td
									style="color: #13A8E0; font-size: 20px; font-weight: bold; border-right: solid 1px #0307fc;"
									width="10%"><a ng-click="getCountType('TotalCount')" style="cursor: hand;cursor: pointer;"><c:out
											value="${ageingMapDataList['TotalCount']}" /></a></td>
							</tr>
						</tbody>
						<thead align="center" style="font-size: 11px;">
							<tr>
								<th colspan="1" align="center"><4 Hrs</th>
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
								<td align="left"
									style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="12%"><a id="high" ng-click="getCountType('High')" style="cursor: hand;cursor: pointer;"><c:out
											value="${mapDataList['High']}" /></a></td>
								<td align="left"
									style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="12%"><a id="countMedium"
									ng-click="getCountType('Medium')" style="cursor: hand;cursor: pointer;"><c:out
											value="${mapDataList['Medium']}" /></a></td>
								<td align="left"
									style="color: #13A8E0; font-size: 20px; font-weight: bold;"
									width="12%"><a id="countLow"
									ng-click="getCountType('Low')" style="cursor: hand;cursor: pointer;"><c:out	value="${mapDataList['Low']}" /></a></td>
								<td
									style="color: #13A8E0; font-size: 20px; font-weight: bold; text-align: left: 10px;"
									width="12%"><a id="countTotal"	ng-click="getCountType('Total')" style="cursor: hand;cursor: pointer;"><c:out
											value="${mapDataList['Total']}" /></a></td>
							</tr>
						</tbody>

						<thead style="font-size: 11px; width: 12px;">
							<tr>
								<th colspan="1" width="12%">High</th>
								<th colspan="1" width="12%">Medium</th>
								<th colspan="1" width="12%">Low</th>
								<th colspan="1" width="12%">total</th>
							</tr>
						</thead>
					</table>
				</div>

			</div>

			<br/>

			<!--  -->


			<div style="top: 242px; left: 15px; width: 300px; height: 519px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1; border: 1px solid black #eee;padding:7px;">

	
<header style="top: 366px;left: 33px;width: 309px;height: 20px;text-align: left;font: Medium 17px/20px Effra;letter-spacing: 0;color: #000000;opacity: 1; text-align: center;"><b>Call Category : Overall Summary of errors</b></header>
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
									<h4>
									<c:set var="mapKey" value="${mapData.key}"/>
									<%
									    String resp = ""; 
									    resp = (String)pageContext.getAttribute("mapKey"); 									    
									    String keyValue = resp.trim().replaceAll(" ", "");									    
									  %> 
									
										<td style="font-size: 15px;color:#280071;" aria-expanded="false" data-toggle="collapse" href="#<c:out value="<%=keyValue%>"/>"  >
				                         <c:out value="${mapData.key}"></c:out></td>
										
									<td style="font-size: 15px; color: #280071;"><span style="background-color:#280071;color:white;border-radius: 25px;padding-left:7px;padding-right:7px" >
								    <c:out value="${mapData.value}"></c:out> </span>
							        <%-- <input type="hidden"  id="category" name="category"  value="${mapData.key}" /> --%>
							        </td>
								
									<td><span data-toggle="collapse" data-target="#<c:out value="<%=keyValue%>"/>" class="fa fa-chevron-circle-down"
											style="font-size: 15px; color: #280071;" data-toggle="collapse" href="#<c:out value="<%=keyValue%>"/>" aria-expanded="false"> </span>
									<input type="hidden"  id="category"  name="category" value="${mapData.key}" />
									</td>		
								</h4>
								</tr>	
								<tr class="collapse panel-collapse" id="<c:out value="<%=keyValue%>"/>" >	
										<td style="font-size: 15px; color: #280071;">
									

											
												<table>
										
														<tbody style="font-size: 15px;" id="sub<c:out value="<%=keyValue%>"/>">
														
														</tbody>

												</table>


											
										</td>

									</tr>									
									
									</c:forEach>
								


							</tbody>

				</table>

			</div>
</div>

<div
				 style="width: 81%;height: 346px;position: absolute;top: 120px;bottom: 910px;left: 311px;right: 1036px;/* margin: auto; */background: #FFFFFF 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #8D8D8D29;opacity: 1;border: 1px solid black #eee;padding:7px;">
 			

			<!-- <div
				style="width: 1036px; height: 346px; position: absolute; top: 648px; bottom: 910px; left: 311px; right: 1036px; margin: auto; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1; border: 1px solid black #eee;padding:7px;">
 -->
				<!-- <div style="border-bottom: 1px solid #eee;"> -->
					
					<input class="form-group has-search" ng-model="searchText"	ng-change="refresh()" placeholder=" Enter BranchCode,KioskId."	id="input" style="width:339px;"> 
				     <button  id="btnSearchText" ng-click="refresh()">Search</button> 
		             <button  id="btnClearText" ng-click="clearSearch()">ClearSearch</button>	
					<span style="float:right">
					<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
					<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
					&nbsp;&nbsp;&nbsp;
					</span>
					<br />
					
					<div class="loading" id="loading" align="center" style="display:none;">
   					 <img src="resources/img/loader.gif"> 
					</div>
					
					<div
						style="top: 355px; left: 15px; width: 1336px; height: 519px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1;"
						ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test">
						
					 </div>

				<!-- </div> -->

			</div>

</div>
			</div>

		</div>


	
</div>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="resources/js/bootstrap.3.1.1.min.js"></script>	
<script>
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);


app.controller('UserManagementCtrlSA', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) 
	{
	
	   var paginationOptions = {
	     pageNumber: 1,
		 pageSize: 20,
		 sort: null
		 };
	   
	   $scope.create = function(){
		  
		   
		   
	   }
	   
	   
	   
	   var counttype = "";
	   $scope.getCountType = function(type){	
		counttype=type;
		   UserManagementService.getUsers(paginationOptions.pageNumber,
				   paginationOptions.pageSize,counttype).success(function(data){
					   
						  $scope.gridOptions.data = data.content;
					 	  $scope.gridOptions.totalItems = data.totalElements;
					   });
		}
	   
	  /*  
	   $scope.refresh = function()
	   {  		  	
		   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
		 	   UserManagementService.getUsers(paginationOptions.pageNumber,
		 			   paginationOptions.pageSize,counttype).success(function(data){
		 		  $scope.gridOptions.data = data.content;
		 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	   });	   
		 		   
		 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
		 	  
		 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
		 		   
		 	    }else{
		 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
		 	 			   paginationOptions.pageSize,counttype).success(function(data){
		 	 		  $scope.gridOptions.data = data.content;
		 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
		 	 	   });
		 	    }		    
	   };
	   
	   
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype).success(function(data){
		  $scope.gridOptions.data = data.content;
	 	  $scope.gridOptions.totalItems = data.totalElements;
	   }); */




	   $scope.refresh = function()
	   {  
	  if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
	  //  Added for loader------------- START
	       $("#loading").show();  
	    // Added for loader------------- END
	  UserManagementService.getUsers(paginationOptions.pageNumber,
	   paginationOptions.pageSize,counttype).success(function(data){
	  $scope.gridOptions.data = data.content;
	    $scope.gridOptions.totalItems = data.totalElements;
	  //  Added for loader------------- START
	       $("#loading").hide();  
	    // Added for loader------------- END
	    });  
	 
	   }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){debugger;
	 
	 /* $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText); */  
	   
	    $("#loading").show();
	 /* UserManagementService.getSearchNext(paginationOptions.pageNumber,
	  paginationOptions.pageSize,fromDate,toDate,$scope.searchText).success(function(data3){*/
	  UserManagementService.getSearchNext(0,
	  paginationOptions.pageSize,counttype,$scope.searchText).success(function(data3){
	   $scope.gridOptions.data = data3.content;
	     $scope.gridOptions.totalItems = data3.totalElements;
	       $("#loading").hide();
	    });
	 
	   }else{
	  //  Added for loader------------- START
	       $("#loading").show();  
	    // Added for loader------------- END
	    UserManagementService.getUsers(paginationOptions.pageNumber,
	     paginationOptions.pageSize,counttype).success(function(data){
	    $scope.gridOptions.data = data.content;
	      $scope.gridOptions.totalItems = data.totalElements;
	    //  Added for loader------------- START
	       $("#loading").hide();  
	    // Added for loader------------- END
	      });
	   }
	   };
	   $scope.clearSearch = function()
	   {   debugger;
	 
	    $scope.searchText='';
	 
	       $("#loading").show();  
	   
	  UserManagementService.getUsers(0,
	  paginationOptions.pageSize,counttype).success(function(data){
	   $scope.gridOptions.data = data.content;
	   $scope.gridOptions.paginationCurrentPage = data.number;
	   $scope.gridOptions.totalItems = data.totalElements;
	 
	       $("#loading").hide();  
	   
	    });
	 
	 
	   };
	//  Added for loader------------- START
	        $("#loading").show();  
	     // Added for loader------------- END
	   UserManagementService.getUsers(paginationOptions.pageNumber,
	    paginationOptions.pageSize,counttype).success(function(data){
	 
	   $scope.gridOptions.data = data.content;
	   $scope.gridOptions.totalItems = data.totalElements;
	// Added for loader------------- START
	   $("#loading").hide();  
	// Added for loader------------- END
	   });
		   
	   
	   
	   $scope.gridOptions = {
				paginationPageSizes: [20, 30, 40],
			    paginationPageSize: paginationOptions.pageSize,
			    enableColumnMenus:false,
				useExternalPagination: true,
								
			    columnDefs: [
			      { name: 'vendor', displayName: 'Vendor'  },
			      { name: 'ticketId', displayName: 'Ticket Id'  },
			      { name: 'kisokId', displayName: 'KisokId'  },
			      { name: 'branchCode', headerCellTemplate: '<div>Branch<br/>Code</div>'  },			      
			      { name: 'callCategory',headerCellTemplate: '<div>Call<br/>Category</div>'},
			      { name: 'callSubCategory', headerCellTemplate: '<div>Call Sub<br/>Category</div>'  },
			      { name: 'call_log_date',headerCellTemplate: '<div>Call Log<br/>Date</div>',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'   },
			      { name: 'ageing', headerCellTemplate: '<div>Ageing Log<br/>Hours</div>'},
			      { name: 'statusOfComplaint',headerCellTemplate: '<div>Status of<br/>Complaint</div>'},
			      { name: 'assigned_to_FE',headerCellTemplate: '<div>Assigned<br/>to FE</div>'}
			      //{ name: 'fe_schedule', displayName: 'FE Schedule'}
			    ],
			    onRegisterApi: function(gridApi) {
			        $scope.gridApi = gridApi;
			        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
			          paginationOptions.pageNumber = newPage;
			          paginationOptions.pageSize = pageSize;


			      //  Added for loader------------- START 
				        $("#loading").show();  
				     // Added for loader------------- END
				        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
			          UserManagementService.getUsers(newPage,pageSize,counttype).success(function(data){
				//alert(" On Register ");
			         $scope.gridOptions.data = data.content;
			           $scope.gridOptions.totalItems = data.totalElements;
			       //  Added for loader------------- START 
				        $("#loading").hide();  
				     // Added for loader------------- END
			          });
			        }else{
			 	 	   	console.log("Inside else"+$scope.counttype);
			        	 UserManagementService.getSearchNext(newPage,pageSize,$scope.counttype,$scope.searchText).success(function(data){
			                  $scope.gridOptions.data = data.content;
			           	 	  $scope.gridOptions.totalItems = data.totalElements;
			        
				 	 		 $("#loading").hide();  
				 		   
			        	  });	 
			        
			        	   }
			        });
			     }
			  };
			 
			}]);








app.service('UserManagementService',['$http', function ($http) {
	function getUsers(pageNumber,size,counttype) {
		//alert("counttype= getUsers=="+counttype);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/ticketCentorFilterCMF/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }

    
	function getSearchNext(pageNumber,size,counttype, searchText) {
		//alert("counttype= searchText=="+counttype);
		//alert("13=searchText=="+searchText);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
	    return  $http({
	      method: 'GET',
	      url: 'hm/ticketCentorFilterCMFSearch/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&searchText='+searchText
	    });
	}
    
    return {
    	getUsers:getUsers,
        getSearchNext:getSearchNext
    };
	
}]);





$(document).ready(function () {
	  $('#head__top').on('click', function(){
	    if($('#innerCollapse').is(':visible')) {
	      $('#innerCollapse').hide();
	    }
	  });
	  
	});
angular.bootstrap(document.getElementById("appId"), ['app']);

$(document).ready(function(){

    $(".openpdfonclick").click(function(){
    	$("#loading").show();
        $.ajax({
            url: 'report?page=ticketCenterCMF&type=pdf',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	window.open("resources/download/"+data , '_blank'); 
            	$("#loading").hide();
            }
        });
    });
    $(".openxlonclick").click(function(){    	
    	$("#loading").show();
        $.ajax({
            url: 'report?page=ticketCenterCMF&type=excel',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	window.open("resources/download/"+data , '_blank');
            	$("#loading").hide();
            }
        });
    });
}); 

</script>
</body>
 
</html>