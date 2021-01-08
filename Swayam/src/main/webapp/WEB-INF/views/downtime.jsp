<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>



<script type="text/javascript">


$("searchPositions").on('click',function() {

	alert("Updated");
	  // do something 
	});

</script>
 
    
    
    <meta http-equiv="x-ua-compatible" content="IE=edge">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<!-- <script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment-with-locales.min.js"></script> -->
<!-- <script src="resources/js/moment-with-locales.min.js"></script> -->
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/down-time-app.js"></script>
<script src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/style.css">

<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
    
    
    <script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap-datepicker3.css" />
    
 <script>
	$(document).ready(function() {
		var datePickerOptions = { changeYear: true, 
								  changeMonth: true,
								  autoclose: true,
								  endDate : '+0d',
								  format : 'dd-mm-yyyy',
								  orientation : "top"
								  }
      	
		$('#datepickerFromDate,#datepickerToDate').datepicker(datePickerOptions);
	});
</script>   
    
    


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
        
        input:invalid {
  border: 2px dashed red;
}

input:invalid:required {
  background-image: linear-gradient(to right, pink, lightgreen);
}

input:valid {
  border: 2px solid black;
}
/*     .ui-grid-contents-wrapper {
    position: relative;
    height: 100%;
    width: 100%;
} */
    		.ui-grid, .ui-grid-viewport { 
   			  height: auto !important; 
   			  overflow: hidden;
			} 
			.ui-grid-pager-panel {
		     position: relative;
			 } 
	
			.ui-grid-pager-row-count-picker {
			display:none;
			}
ui-grid-render-container-body .ui-grid-viewport.no-horizontal-bar {
    overflow: hidden;
}

    .ui-grid-header-cell {float: left;}
    .ui-grid-header-canvas {
    padding-top: 0px;
    padding-bottom: 0px;}
    </style>
    
</head>
<body>
<div class="main_terminal" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
<div> 
<form>
		
		<div class="tb-bk">
   <table>				
        <tr>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circle<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="circle" name="SelectedCircelId" ng-model="SelectedCircelId">
									<option value="">Select Circle</option>
									<option ng-repeat="item in Circles" value="{{item.circleName}}">{{item.circleName}}</option>
							</select>
                    </div>
                </div>
	        </td>
	       <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Vendor<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select name="Vendor" id="vendor" ng-model="SelectedVendorId" >
									<option value="">Select Vendor</option>
									<option value="CMS">CMS</option>
									<option value="LIPI">LIPI</option>
									<option value="VFORBES">FORBES</option>
								</select>
                    </div>
                </div>
			</td>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">CMS/CMF<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id ="username" name="SelectedCmsCmf" ng-model="SelectedCmsCmf" >
									<option value="">Select CMS/CMF</option>
									<option ng-repeat="item in CmsCmfUsers" value="{{item.pfId}}">{{item.username}}</option>
							</select>
                    </div>
                </div>                                
	        </td>							
        </tr>
		<tr>
			 <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left" for="datepickerFromDate">FROM<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                     <input type="text" id="datepickerFromDate" name="SelectedFromDateId" class="datepicker"  readonly="readonly" 
                      ng-model="SelectedFromDateId" placeholder="dd-mm-yyyy" maxlength="10" required pattern="[Bb]anana|[Cc]herry" />
                      </div>
                      
                </div>                                
	        </td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">To<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                    <input type="text" id="datepickerToDate" name="SelectedToDateId" class="datepicker" readonly="readonly" 
                     ng-model="SelectedToDateId" placeholder="dd-mm-yyyy" maxlength="10" required pattern="[dd]-|[mm]-[yyyy]" />
                     </div>
                </div>
			</td>
			 <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                             <!--  <button  id="btnReset" ng-click="">Reset</button> --> 
                               <button id="btnReset"  ng-click="resetPositions()">Reset</button>	
                            <!-- <input type="reset" value="Reset form">	 -->						
			                 <button   ng-click="searchPositions(SelectedCircelId,SelectedVendorId,SelectedCmsCmf,SelectedFromDateId,SelectedToDateId)" >Generate</button>
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
		<div class="submain_terminal">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Circle,KioskId,Vendor,CMS,CMF etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"style="overflow: hidden;"></div>
        
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);


</script>
<script type="text/javascript">
$(document).ready(function(){
	/* var datePickerOptions = { changeYear: true, changeMonth: true,autoclose: true,maxDate: new Date(), dateFormat:'dd-mm-yy'}
	
    $( ".datepicker" ).datepicker(datePickerOptions); */
    $(".openpdfonclick").click(function(){
    	
        $.ajax({
            url: 'report?page=downTime&type=pdf',
            type: 'GET',   
            success: function(data){
            	console.log(data);
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
            url: 'report?page=downTime&type=excel',
            type: 'GET',   
            success: function(data){
            	console.log(data);
            	if(data.includes(".xlsx")){
            		console.log("Excel Data1" + data);
            		window.open("resources/download/"+data , '_blank'); 
            		
            	}else{
            		console.log("Excel Data" + data);
            		alert("No Data to Export");
            	}   
            }
        });
    });
}); 
	
	
</script>

<script type="text/javascript">
    $(function () {
        $("#btnReset").bind("click", function () {
           $("#circle")[0].selectedIndex = null;            
           $("#username")[0].selectedIndex = null;
           $("#vendor")[0].selectedIndex = null;
           $("#datepickerFromDate").datepicker('setDate', null);
           $("#datepickerToDate").datepicker('setDate', null);
        });
    });
</script>

<!-- <script>
            function resetForm() {
                        document.getElementById("rwdPromotionDetailsAdd").reset();
                    }
</script> -->

</body>
<sec:csrfInput />  
</html>