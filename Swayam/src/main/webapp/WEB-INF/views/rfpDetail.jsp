<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">


<script src="resources/js/rfp-details.js"></script>
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
	
	 <script>
	$(document).ready(function() {
		var datePickerOptions = { changeYear: true, 
								  changeMonth: true,
								  autoclose: true,
								  endDate : '+0d',
								  format : 'dd-mm-yyyy',
								  orientation : "top",
								  defaultDate: null
								  }
      	
		$('#rfpDate').datepicker(datePickerOptions);
		$('#amcDate').datepicker(datePickerOptions);
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
    </style>
	
</head>
<body>



<div class="main" ng-app="app" id="appId">
<div ng-controller="RfpCtrl as vm">
<%-- <input type="hidden" name="_csrf" ng-model="csrf"  value="<%=session.getAttribute("csrfToken")%>">  --%>
 <input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >

 <div>
 		<form name="rfpForm"   ng-submit="searchPostion(selectedRfpNo,selectedRfpid,selectedVendor,selectedkcost,
					selectedAMCcost,selectedCPenalty,selectedDMU,selectedDMUR,selectedDCT,selectedMP,selectedRfpDate,selectedAmcDate)" autocomplete="off"> 
		<div class="tb-bk">
   <table>				
        <tr>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">RFP Number<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" maxlength="23" ng-model="selectedRfpNo" name="rfpno" ng-pattern="/^[A-Z]{3}\:[A-Z]{2}\:[A-Z]{3}\:[A-Z]{2}\:\d{2}\-\d{2}\:[0-9]{3}$/"  
                          placeholder="" autocomplete="off" required/>
                    </div>
                    <div class="col-xs-6">
                    <span ng-show="rfpForm.rfpno.$error.pattern">Valid format required!</span>
                    <span ng-show="rfpForm.rfpno..$error.required">eg:SBI:AC:ECR:RB:17-18:972</span>
                    </div>
                </div>
	        </td>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">RFP Id<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                         <input type="number" min="0" max="99" ng-model="selectedRfpid" name="rfpid" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>                                
	        </td>
	     
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Vendor<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    
                    <div class="col-xs-6">
                    <select id="vendor"  name="Vendor" ng-model="selectedVendor" required>
									<option value=""></option> 
									<option ng-repeat="item in Vendors" value="{{item.vendor}}">{{item.vendor}}</option>
								</select>
                    </div>
                </div>
			</td>
			 							
        </tr>
		<tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Cost of kiosk (in Rs)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="0" max="99999.99" step="0.01"  ng-model="selectedkcost" name="kcost" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>
			</td>
		 <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Cost of AMC (in Rs)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="0" max="999.99" step="0.01"  ng-model="selectedAMCcost" name="amccost" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Complaint Penalty /hr<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="999"  ng-model="selectedCPenalty" name="cpenalty" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>                                
			</td>
			
			
										
		</tr>
        <tr>
          	<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Permissible Downtime in Metro/urban(in hrs)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="number" min="0" max="12"  ng-model="selectedDMU" name="dmu" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>                                
	        </td>
      		  <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Permissible Downtime in Semi-Urban/Rural(in hrs)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="number" min="0" max="12"  ng-model="selectedDMUR" name="dmur" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>                                
	        </td>
        
            <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circle Permissible Downtime(in %)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="100"  ng-model="selectedDCT" name="cdt" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>
			</td>
            
          </tr>
        
        <tr>
            <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Maximum Penalty(in %)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="100"  ng-model="selectedMP" name="mp" 
                         placeholder="" autocomplete="off" required/>
                        
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">RFP Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="rfpDate" name="rfpDateId" class="datepicker" readonly="readonly"  ng-model="selectedRfpDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">AMC Start Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="amcDate" name="amcDateId" class="datepicker" readonly="readonly"  ng-model="selectedAmcDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>
			 </tr>
			 <tr>
			 <td></td>
			 <td></td>
            <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                             <button  id="btnReset" ng-click="resetPositions()" style="cursor: hand;cursor: pointer;">Reset</button>							
			                <!--  <button  type="submit" ng-click="searchPostion(selectedRfpNo,selectedRfpid,selectedVendor,selectedkcost,
					selectedAMCcost,selectedCPenalty,selectedDMU,selectedDMUR,selectedDCT,selectedMP)">Add</button> -->
							  <button type="submit" id="submit" style="cursor: hand;cursor: pointer;">Add</button>
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
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, No Of Branches, Kiosks, Txns, etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		<!-- <span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span> -->	
		<br/>
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns ui-grid-edit id="test"></div>
		
        
    </div>
    
    
	</div>
</div>	
	
<script>
angular.bootstrap(document.getElementById("appId"), ['app']);
/* angular.module("app").constant("CSRF_TOKEN", '{{ csrf_token() }}') */
</script>




<script type="text/javascript">
    $(function () {
        $("#btnReset").bind("click", function () {
        	
        	$('input[name=rfpno').val('');
        	$('input[name=rfpid').val('');
        	$('input[name=kcost').val('');
        	$('input[name=amccost').val('');
        	$('input[name=cpenalty').val('');
        	$('input[name=dmu').val('');
        	$('input[name=dmur').val('');
        	$('input[name=cdt').val('');
        	$('input[name=mp').val('');
            $("#vendor")[0].selectedIndex = "";
           
        });
    });
</script>


</body>

</html>