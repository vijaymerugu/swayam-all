<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">



<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/purchaseOrder.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/font-awesome.min.css">

<script src="resources/js/a076d05399.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>




<!-- <script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="resources/js/ui-grid.min.js"></script>
<script src="resources/js/purchaseOrder.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="resources/css/bootstrap.min.css"> 
<script src="resources/js/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>

<link rel="stylesheet" href="resources/css/font-awesome.min.css">
 
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<script src="resources/js/jquery-1.12.4.js"></script>
<script src="resources/js/jquery-ui.js"></script>

<script src="resources/js/a076d05399.js"></script>

<script src="resources/js/angular-route.min.js"></script>


<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script> -->
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
        .tb-bk  table tr td button {
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
        
       /*    .ui-grid-header-cell-label {
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
			} */
 
    </style>
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="PurchaseOrderCtrl as vm">
<%-- <input type="hidden" name="_csrf" ng-model="csrf"  value="<%=session.getAttribute("csrfToken")%>">  --%>
 <input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >
<div>
                        <span class="text-left"><b>Note*:To generate purchase order please select vendor</b></span>
                        <span class="pull-right"></span>
                    </div>
 <div>
 		 <form name="rfpForm"  ng-submit="getRowsData()"> 
		<div class="tb-bk">
   <table>

        <tr>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Vendor<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                    <select id="vendor"  name="Vendor" ng-model="selectedVendor" 
                    ng-change="LoadPOData(selectedVendor)"  required>
									<option value=""></option> 
									<option ng-repeat="item in Vendors" value="{{item.vendor}}">{{item.vendor}}</option>
					</select>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                             <button  id="btnReset" ng-click="resetPositions()">Reset</button>							
			          
							  <button type="submit" id="submit">Generate</button>
                         </div>
                    </div>
                </div>
			</td>
			<td></td> 							
        </tr>
		
			<td></td>
            <!-- <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                             <button  id="btnReset" ng-click="resetPositions()">Reset</button>							
			          
							  <button type="submit" id="submit">Generate</button>
                         </div>
                    </div>
                </div>
			</td> -->
        </tr>			   			   
			</table> 
		</div>
		</form> 
	<!-- 	<button  id="getData" ng-click="getRowsData()">Ge</button> -->
		</div>
<br/>
		<div class="submain">
	
	<!-- 
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, No Of Branches, Kiosks, Txns, etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		 -->
		 <!-- <span style="float:right">
		<a class="openpdfonclick"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span> -->	
		<br/>
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-selection 
		ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
/* angular.module("app").constant("CSRF_TOKEN", '{{ csrf_token() }}') */
</script>


<!--  <script type="text/javascript">
      
      $(document).ready(function(){

    	    $(".openpdfonclick").click(function(){
    	    	
    	        $.ajax({
    	            url: 'report?page=bpReport&type=pdf',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	window.open("resources/download/"+data , '_blank');  
    	            }
    	        });
    	    });
    	    $(".openxlonclick").click(function(){    	
    	        $.ajax({
    	            url: 'report?page=bpReport&type=excel',
    	            type: 'GET',   
    	            success: function(data){
    	            	console.log(data);
    	            	window.open("resources/download/"+data , '_blank');  
    	            }
    	        });
    	    });
    	}); 
    		
    		
      
      </script> -->

<script type="text/javascript">
    $(function () {
        $("#btnReset").bind("click", function () {
        	
      
            $("#vendor")[0].selectedIndex = "";
           
        });
    });
</script>


</body>

</html>