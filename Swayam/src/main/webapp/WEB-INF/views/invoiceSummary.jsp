<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">

<script	src="resources/js/angular.1.5.6.min.js"></script>
<!-- <link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css"> -->

<link rel="stylesheet" href="resources/css/ui-grid.min.css"/> 

 <link rel="stylesheet" href="resources/css/ui-grid.min-1.css"> 

<!-- 
  <link rel="stylesheet" href="http://cdn.rawgit.com/angular-ui/ui-grid.info/gh-pages/release/3.0.0-rc.20/ui-grid.min.css">
   
 -->
 <script data-require="jquery@2.1.3" data-semver="2.1.3" src="resources/js/jquery-2.1.3.min.js"></script>
     <!-- <script data-require="jquery@2.1.3" data-semver="2.1.3" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>  -->
<!--     <script src="https://code.angularjs.org/1.4.0/angular.js"></script> -->
 <!--    <script src="http://cdn.rawgit.com/angular-ui/ui-grid.info/gh-pages/release/3.0.0-rc.21/ui-grid.min.js"></script> -->

 <script src="resources/js/invoice-Summary.js"></script>   

<link rel="stylesheet" href="resources/css/grid-style1.css"/>

<!-- 
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 -->
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>


<style>
        .tb-bk {
        }
            .tb-bk table tr td {
            width:33.33%;padding: 5px;
            }
            .tb-bk table {
            width:100%;
            }
                .tb-bk table tr td select, .tb-bk table tr td input,.tb-bk table tr td textarea {
                    background: #fff;
    color: #00a9e0;
    border-bottom: 1px solid #00a9e0 !important;
    border: none;
    line-height: 1;
    padding: 5px;
    width: 80%;
                }

                .tb-bk table tr td .lb {
            color: #2f246c;
    font-weight: 600;
    font-size: 12px;
        }
        .tb-bk table tr td button {
        background-color: #fdd209;
    color: #2f246c;
    border: none;
    padding: 5px 10px;
    text-transform: uppercase;
    font-weight: 600;
        }
        .tb-bk table tr td .row, .tb-bk table tr td .row .col-xs-6 {
        margin:0 !important;
        padding:0 !important;
        }
            .tb-bk table tr td .row .lb span b {
            color:red;
            }
            select:focus,input:focus,button:focus,textarea:focus {
                outline: none;
            }
        span.text-left {
        line-height: 30px;
        }
        span.pull-right {
        padding:5px 10px;
        }
        /*   .ui-grid-header-cell-label {
		display:inline-block;
		white-space:initial;
		}
		
		
		.wrap-text .ui-grid-cell-contents {
 		 white-space:normal;
		}

	 	[ui-grid-row] {
  		display: table-row;
		}

		.ui-grid-row, .ui-grid-cell {
  		height: auto!important;
		}

			.ui-grid-cell {
  			float: none;
  			display: table-cell;
			}  */
		
		
		/* 	.ui-grid-header-cell, .ui-grid-cell-contents {
  			white-space: normal;
  			word-break: break-word;
			}  */  
    </style>
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="InvoiceSummearyCtrl as vm">
<div>
 		
		<form name="myForm" id="myForm"> <!-- ng-submit="searchPositions(SelectedCircelId,SelectedStateId,
							SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId)" -->
		<div class="tb-bk">
   <table>				
        <tr>
         <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Year<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="year" name="Year" ng-model="SelectedYearId" required>
									 <option value="" selected>--Select Year--</option>  
									<option ng-repeat="year in Years" value="{{year}}">{{year}}</option>
							</select>
                    </div>
                </div>                                
			</td>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circle<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="circle" class="form-group" name="Circle" ng-model="SelectedCircelId"
								ng-change="LoadDropDown('circleId',SelectedCircelId)" required>
									 <option value="" selected>--Select Circle--</option> 
									<option ng-repeat="item in Circles" value="{{item.circleCode}}">{{item.circleName}}</option>
							</select>
                    </div>
                </div>
	        </td>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">State<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="state" name="State" ng-model="SelectedStateId">
									   <option value="" selected>--Select State--</option> 
									<option ng-repeat="item in States" value="{{item.statCode}}">{{item.statDesc}}</option>
							</select>
                    </div>
                </div>                                
	        </td>
	      							
        </tr>
			<tr>
            <td></td><td></td>
            <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                             <button id="btnReset"  ng-click="resetPositions()" style="cursor: hand;cursor: pointer;">Reset</button>							
			                 <button  ng-click="searchPositions(SelectedCircelId,SelectedStateId,
							SelectedYearId)" style="cursor: hand;cursor: pointer;">Generate</button>
							 <!-- <button  type="submit" id="submit">Generate</button> -->
                         </div>
                    </div>
                </div>
			</td>
        </tr>			   
			</table> 
		</div>
		</form>
		</div> 
<br/>
		<div class="submain">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, Vendor, State etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
			<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>
		<br/>
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns  id="test"></div>
		
        
    </div>
    
    
	</div>
</div>
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script>

<script type="text/javascript">
     $(function () {
        $("#btnReset").bind("click", function () {
            $("#circle")[0].selectedIndex = "";            
            $("#state")[0].selectedIndex = "0";
        
            $("#year")[0].selectedIndex = "";
            
           
            console.log("Inside reset jquery ");
        });
    }); 
</script>
<script type="text/javascript">
      
      $(document).ready(function(){

    	    $(".openpdfonclick").click(function(){
    	    	
    	        $.ajax({
    	            url: 'report?page=invoiceSummaryReport&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	 if(data.includes(".pdf")){
    	            		console.log("PDF Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            	} 
    	            	/* console.log("PDF Data1" + data);
    	            	window.open("resources/download/"+data , '_blank');  */
    	            }
    	        });
    	    });
    	    $(".openxlonclick").click(function(){    	
    	        $.ajax({
    	            url: 'report?page=invoiceSummaryReport&type=excel',
    	            type: 'GET',   
    	            success: function(data){
    	            	 if(data.includes(".xlsx")){
    	            		console.log("PDF Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            	}  
    	            	
    	            	/*  console.log("xsxl Data1" + data);
    	            	window.open("resources/download/"+data , '_blank');  */
    	            }
    	        });
    	    });
    	}); 
    		
    		
      
      </script>	
	



</body>
</html>