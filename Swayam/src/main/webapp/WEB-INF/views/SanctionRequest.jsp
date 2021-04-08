<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/sanction-request.js"></script>
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
    
       <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js"></script>
    
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
    
<style>
        .tb-bk {
            background: #f3f7fa;
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
        
         .ui-grid-header-cell-label {
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
			} 
		
		
			.ui-grid-header-cell, .ui-grid-cell-contents {
  			white-space: normal;
  			padding: 2px;
  			word-break: break-word;
			}
			
			
			 .ui-grid, .ui-grid-viewport {
     height: auto !important;
}
.ui-grid-pager-panel {
    position: relative;
}
.ui-grid .ui-grid-render-container-body .ui-grid-viewport {
  overflow-x: auto !important;
  overflow-y: auto !important;
 
}
.ui-grid-pager-row-count-picker {
display:none;
}

      .ui-grid .ui-grid-render-container-body .ui-grid-viewport {
  overflow-x: auto !important;
  overflow-y: auto !important;
 
}
.ui-grid-header-canvas {
   padding-top: 0px;
   padding-bottom: 0px;}
    </style>
    
    <script>
    $(document).ready(function() {
/*     $('#sgst').on('input', function() {
    	var sgst = $(this).val();
		  console.log("sgst " + sgst)
		  
		  $('#cgst').val(sgst);
    	
    }); */
    
    });
	
		
</script> 
    
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="SanctionRequestCtrl as vm">
<div>

			  <table class="table1"
				style="top: 152px; left: 15px; width: 1336px; height: 190px; background: #FFFFFF 0% 0% no-repeat padding-box; box-shadow: 0px 3px 6px #8D8D8D29; opacity: 1;">

				<thead>
						<tr
						style="color: #000000; font-size: 14px; font-weight: bold; width: 200px;">
						<th>Requests Summary<th></tr>
					<tr></tr>


				<tr
						style="color: #000000; font-size: 12px; font-weight: bold; width: 119px;" >
						<th colspan="4" style="text-align: -webkit-center;">PROGRESS STATUS</th>
						<th colspan="4" style="text-align: centor;">TYPES OF REQUEST</th>
					</tr>


				</thead>

				<tbody>
					<tr align="center">
						<td id="count1">
						  <a ng-click="getCountType('Submitted')" style="cursor: hand;cursor: pointer;"><c:out value="${mapStatusCount['submitted']}"/></a>
						  </td>
						
						<td id="count1">
						 <a ng-click="getCountType('Approved')" style="cursor: hand;cursor: pointer;"><c:out value="${mapStatusCount['approved']}"/></a>  					
						</td>
						<td id="count1">
						 <a ng-click="getCountType('Rejected')" style="cursor: hand;cursor: pointer;"><c:out value="${mapStatusCount['rejected']}"/></a>  					
						</td>
						
						<td id="count1">
						 <a ng-click="getCountType('Sanction Note')" style="cursor: hand;cursor: pointer;"><c:out value="${mapStatusCount['snnote']}"/></a>  					
						</td>
						
						<td id="count1">
						 <a ng-click="getCountType('RFP Management')" style="cursor: hand;cursor: pointer;"><c:out value="0"/></a>					
						</td>
						<td id="count1">
						 <a ng-click="getCountType('Invoice Submission')" style="cursor: hand;cursor: pointer;"><c:out value="0"/></a>					
						</td>
						<td id="count1">
						 <a ng-click="getCountType('Invoice Comparison')" style="cursor: hand;cursor: pointer;"><c:out value="0"/></a>					
						</td>
					</tr>
					<tr align="center"
						style="color: #000000; font-size: 12px; font-weight: bold;"
						width="10%">
						<!-- <td id="count2">Submitted</td> -->
						<td id="count2">Submitted</td>
						<td id="count2">Approved</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">Rejected</td>

						<td id="count2">Sanction Note</td>
						<td id="count2">RFP Management</td>
						<td id="count2">Invoice Submission</td>
						<td id="count2"
							style="color: black; border-right: solid 1px #0307fc;">Invoice Comparison</td>

						

					</tr>
				</tbody>
			</table>
		</div>

<div>
	
 		
		<form> 
		<div class="tb-bk">
	
		
		
   <table>				
    
            <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
							<button  id="btnNewRquest" ng-click="open()">New Request</button>	
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
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, Vendor, State, Rfp. ref no. etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		<!-- <span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span> -->
		<br/>
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
 <script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script> 
<script type="text/javascript">
      
     /*  $(document).ready(function(){

    	    $(".openpdfonclick").click(function(){
    	    	
    	        $.ajax({
    	            url: 'report?page=taxCalReport&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	if(data.includes(".pdf")){
    	            		console.log("PDF Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            	}  
    	            }
    	        });
    	    });
    	    $(".openxlonclick").click(function(){    	
    	        $.ajax({
    	            url: 'report?page=taxCalReport&type=excel',
    	            type: 'GET',   
    	            success: function(data){
    	            	if(data.includes(".xlsx")){
    	            		console.log("PDF Data1" + data);
    	            		window.open("resources/download/"+data , '_blank'); 
    	            		
    	            	}else{
    	            		console.log("PDF Data" + data);
    	            		alert("No Data to Export");
    	            	} 
    	            }
    	        });
    	    });
    	}); 
    		 */
    		
      
      </script>
<script type="text/javascript">
    $(function () {
        $("#btnReset").bind("click", function () {
            $("#circle")[0].selectedIndex = "";            
            $("#state")[0].selectedIndex = "0";
            $("#vendor")[0].selectedIndex = "";
           // $("#year")[0].selectedIndex = "";
            $("#timeperiod")[0].selectedIndex = "";
            $("#refno")[0].selectedIndex = "";
            $("#gsttype")[0].selectedIndex = "";
            
            $('.hideIGST').hide();
    		  $('.hideGST').hide(); 
        });
    });
    
    
/*     function changeSGST(){
    	//var sgst= ('#sgst').val();
    	var sgst = document.getElementById("sgst").value;
    	
    	console.log("SGST----- "+ sgst);
    	//('#sgst').val()
    	document.getElementById("cgst").value=sgst;
    }
     */
    
   
</script>

</body>
</html>