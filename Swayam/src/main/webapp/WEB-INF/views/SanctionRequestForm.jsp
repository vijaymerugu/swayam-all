<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<!-- <link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script> -->
 <script src="resources/js/sanction-request-form.js"></script> 

<!-- <link rel="stylesheet" href="resources/css/grid-style.css"/> -->
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<!-- <script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/> -->

<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="resources/css/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
    
    <!--    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js"></script>
    
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
  -->
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
      	
		$('#sanNoteDate').datepicker(datePickerOptions);
		$('#invoiceDate').datepicker(datePickerOptions);
		$('#circularDate').datepicker(datePickerOptions);
		//$('#toDate').datepicker(datePickerOptions);
		$('#receiptDate').datepicker(datePickerOptions);
			  
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
<div ng-controller="SanctionRequestCtrl as vm">
<input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >
<div>
	
 		<div><h3>New Sanction Request</h3></div>
		<form name="sanctionForm" ng-submit="submit()"> 
		<div class="tb-bk">
		
		 <table>				
        	<tr>
        	<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Recommending Authority<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="recommdAu" class="form-group" name="recommdAu" ng-model="SelectedRecommdAu"
								ng-change="LoadSanctionAu(SelectedRecommdAu)" required>
									  <option value="" selected>--Select RA--</option>
									  <option ng-repeat="item in recommentingAuth" 
									  value="{{item.name}}">{{item.value}}</option>
									<!-- <option value="Asst. General Manager">Asst. General Manager</option> -->
							</select>
                    </div>
                </div>                             
	        </td>	
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Sanctioning Authority<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="sanctionAu" class="form-group" name="sanctionAu" ng-model="SelectedSanctionAu"
								required>
									  <option value="" selected>--Select SA--</option>
									<option ng-repeat="item in sanctionAuth" 
									  value="{{item.name}}">{{item.value}}</option>
							</select>
                    </div>
                </div>
	        </td>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Controlling Authority<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="controllingAu" class="form-group" name="controllingAu" ng-model="SelectedControllingAu"
								ng-change="" required>
									  <option value="" selected>--Select CA--</option>
									<option ng-repeat="item in controllingAuth" 
									  value="{{item.name}}">{{item.value}}</option>
							</select>
                    </div>
                </div>                             
	        </td>
	       					
        </tr>
         <tr>
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
                        <span class="text-left">State<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="state"  class="form-group" name="State" ng-model="SelectedStateId"
                        ng-change="resetQuarter()" required>
									 <option value="" selected>--Select State--</option> 
									<option ng-repeat="item in States" value="{{item.statCode}}">{{item.statDesc}}</option>
							</select>
                    </div>
                </div>                                
	        </td>
	       <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Vendor<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="vendor"   class="form-group" name="Vendor" ng-model="SelectedVendorId" ng-change="resetQuarter()"
                       ng-change="" required>
									 <option value="" selected>--Select Vendor--</option>  
									<option ng-repeat="item in Vendors" value="{{item.vendorId}}">{{item.vendor}}</option>
								</select>
                    </div>
                </div>
			</td>
									
        </tr>
        <tr>
		<!--  <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Sanction Note No<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text" ng-model="selectedSanNo" name="sanNo" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>
			</td>  -->
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Sanction Note Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="sanNoteDate" name="sanNoteDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedSanNoteDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Year<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                     <!--   <select id="year"  class="form-group" name="Year" ng-model="SelectedYearId" required>
                       				<option value="2020-2021" ng-selected="true">2020-2021</option>
									  <option value="2021-2022">2021-2022</option> 
									  <option value="2022-2023">2022-2023</option> 
									      
									<option ng-repeat="year in Years" value="{{year}}">{{year}}</option>
							</select> -->
							<select id="year"  class="form-group" name="Year" ng-model='SelectedYearId' ng-change="resetQuarter()"
							required ng-options='option.value as option.name for option in yearOptions'></select>
							
                    </div>
                </div>                                
			</td>
			
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Time Periods(in quarter)<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="timeperiod"  class="form-group" name="TimePeriod" ng-model="SelectedQuarterId" 
                        ng-change="periodChange(SelectedCircelId,SelectedStateId,SelectedVendorId,
                        SelectedYearId,SelectedQuarterId)" required>
                       				 <option value="" selected>--Select Time Period--</option> 
									<option value="Q1">Q1(Apr-Jun)</option>
									<option value="Q2">Q2(Jul-Sep)</option>
									<option value="Q3">Q3(Oct-Dec)</option>
									<option value="Q4">Q4(Jan-Mar)</option>
							</select>
                    </div>
                </div>                                
	        </td>	
		</tr>
		 <tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Invoice No<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text" minlength="15" maxlength="15" ng-model="selectedSInvoiceNo" name="invNo" 
                        ng-pattern="/^[A-Z]{3}\/[0-9]{4}\/[0-9]{6}$/"  placeholder="" autocomplete="off" required/>
                    </div>
                    <div class="col-xs-6">
                    <span ng-show="sanctionForm.invNo.$error.pattern">Valid format required!</span>
                    <span ng-show="sanctionForm.invNo.$error.required">eg:AMC/2019/115405</span>
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Invoice Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="invoiceDate" name="invoiceDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedinvDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>
			<td>
				<div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Date of Receipt<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="receiptDate" name="receiptDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedReceiptDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>	
			
		</tr>
		 <tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Period of Bill From Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <!--  <input type="text" id="fromDate" name="fromDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedfromDate" placeholder="dd-mm-yyyy" disabled maxlength="10"/> -->
                         <input type="text" id="fromDate"  
                        ng-model="selectedfromDate" placeholder="dd-mm-yyyy" style="background: #dddddd;" disabled maxlength="10" required/>
                        
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Period of Bill To Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <!--  <input type="text" id="toDate" name="toDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedToDate" placeholder="dd-mm-yyyy" disabled maxlength="10"/> -->
                        <input type="text" id="toDate" name="toDate"   
                        ng-model="selectedToDate" placeholder="dd-mm-yyyy" style="background: #dddddd;" disabled maxlength="10" required/>
                         
                        
                    </div>
                </div>
			</td>
			<td>
				<div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">No of Kiosks<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" ng-model="selectedNoKiosk" name="noKisok" 
                         placeholder="" autocomplete="off" style="background: #dddddd;" disabled required/>
                    </div>
                </div>
			</td>	
		</tr>
		<tr>
			
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Invoice Amount<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" ng-model="selectedSInvoiceAmt" name="invAmt" step="0.01" 
                         placeholder="" autocomplete="off" style="background: #dddddd;" disabled required/>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Penalty Amount<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" ng-model="selectedSPenaltyAmt" name="penAmt" step="0.01" 
                         placeholder="" autocomplete="off" style="background: #dddddd;" disabled required/>
                    </div>
                </div>
			</td>
			
		</tr>
		<tr>
			<td>
			<div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">GST Type<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="gsttype"  class="form-group" name="GstType" onchange="myFunction()" ng-model="SelectedIGSTType"  style="background: #dddddd;" disabled required>
                       				 <option value="" selected>--Select GST Type--</option> 
									<option value="IGST" >IGST</option>
									<option value="SGST/CGST">SGST/CGST</option>
							</select>
                    </div>
                </div>     
               
			</td>
			
			<td class="hideIGST">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">IGST<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="20"  ng-model="selectedIGST" name="igst" id="igst" step="0.01" 
                         placeholder="" autocomplete="off"   style="background: #dddddd;" disabled/>
                        
                    </div>
                </div>
			</td>
			<td class="hideGST">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">SGST<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="10"  ng-model="selectedSGST" name="sgst" id="sgst" step="0.01" 
                         placeholder="" autocomplete="off"  style="background: #dddddd;" disabled />
                        
                    </div>
                </div>
			</td>
			<td class="hideGST">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">CGST<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <!-- <input type="number" min="0" max="10"  ng-model="selectedCGST" name="cgst" id="cgst" style="background: #dddddd;" 
                         placeholder="" autocomplete="off" disabled/> -->
                         <input type="number" min="0" max="10"  ng-model="selectedCGST" name="cgst" id="cgst" step="0.01" 
                         placeholder="" autocomplete="off"  style="background: #dddddd;" disabled /> 
                        
                    </div>
                </div>
			</td>	
		</tr>
		<tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">TDS%<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="1" max="5" step="0.01" ng-model="selectedTdsPer" name="tdsPer" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>
			</td>
			<td>
			
                   <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">GST-TDS Limit Amount<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="250000" max="1000000"  ng-model="selectedGstTds" name="tdsGst" step="0.01" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>  
               
			</td>
			<td>
				 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">GST-TDS Limit Percent<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number"  min="1" max="7" step="0.01" ng-model="selectedGstTdsPer" name="tdsGstPer" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div> 
			</td>	
		</tr>
		
		<tr>
		<td>
			
                   <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Sanction Limit Amount<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="10000"  max="99999999"  ng-model="selectedSanLimitAmt" name="selectedSanLimitAmt"  
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>  
               
			</td>

			<!-- <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular No<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text" ng-model="selectedCircularNo" name="circularNo"  maxlength="22" 
                         placeholder="" autocomplete="off" required/>
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="circularDate" name="circularDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedCircularDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>	  -->
		</tr>
		<tr>
		<td>
			
                   <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular Sl.No.<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text"  ng-model="selectedSlno" name="slno"  minlength="12" maxlength="12"
                         ng-pattern="/^[0-9]{4}\/[0-9]{4}\-[0-9]{2}$/"
                         placeholder="" autocomplete="off" required/>
                    </div>
                     <div class="col-xs-6">
                    <span ng-show="sanctionForm.slno.$error.pattern">Valid format required!</span>
                    <span ng-show="sanctionForm.slno.$error.required">eg:1089/2020-21</span>
                    </div>
                </div>  
               
			</td>

			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular No<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text" ng-model="selectedCircularNo" name="circularNo" minlength="21"   maxlength="21" 
                       ng-pattern="/^[A-Z]{3}\/[A-Z]{3}\-[A-Z]{3}\/[0-9]{1}\/[0-9]{4}\-[0-9]{2}$/"
                         placeholder="" autocomplete="off" required/>
                    </div>
                     <div class="col-xs-6">
                    <span ng-show="sanctionForm.circularNo.$error.pattern">Valid format required!</span>
                    <span ng-show="sanctionForm.circularNo.$error.required">eg:CDO/ORG-DFP/5/2020-21</span>
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="circularDate" name="circularDate" class="datepicker" readonly="readonly"  
                        ng-model="selectedCircularDate" placeholder="dd-mm-yyyy" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>	 
		</tr>
			<tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Manual Entry<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                    	<input type="checkbox" id="me" name="me">
                       <textarea id="mce" ng-model="selectedME" name="mauualE" rows="4" cols="50" maxlength="200" 
                        pattern="^[a-zA-Z0-9 ,&.]*$" ng-pattern-restrict placeholder="Allowed special characters (&,.)" autocomplete="off" style="background: #dddddd;" disabled></textarea>
                    </div>
                </div>
			</td>	
		</tr>
		<tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Credit note entry<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                    	<input type="checkbox" id="ne" name="ne">
                       <textarea  id="nce" ng-model="selectedNE" name="noteE" rows="4" cols="50" maxlength="200" 
                        pattern="^[a-zA-Z0-9 ,&.]*$" ng-pattern-restrict placeholder="Allowed special characters (&,.)" autocomplete="off" style="background: #dddddd;" disabled></textarea>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                         	<button  id="btnCancel" ng-click="cancel()">CANCEL</button>
							<button type="submit" id="submit" style="cursor: hand;cursor: pointer;" >SUBMIT</button>
							 <!-- <button  type="submit" id="submit">Generate</button> -->
                         </div>
                    </div>
                </div>
			</td>	
					   			   
			</table> 
			
			</div>
			
		</form>
		</div> 
<br/>
		<!-- <div class="submain">
    </div>
     -->
    
	</div>
</div>	
	
 <script>
angular.bootstrap(document.getElementById("appId"), ['app']);
</script> 
<script type="text/javascript">
      
      $(document).ready(function(){

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
    		
    		
      
      </script>
<script type="text/javascript">
/*     $(function () {
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
    }); */
    
    function myFunction() {
    	  var x = document.getElementById("gsttype").value;
    	  
    	  console.log("Value " + x);
    	  
    	  if(x=="IGST"){
    		$('.hideIGST').show();
    		  $('.hideGST').hide();
    		  
    	  }else if (x=="SGST/CGST"){
    		  $('.hideIGST').hide();
    		  $('.hideGST').show();
    	  }  else{
    		  $('.hideIGST').hide();
    		  $('.hideGST').hide(); 
    	  } 
    	   
    	
    	}
    
    document.getElementById('me').addEventListener( 'click', function(){
        var textArea = document.getElementById('mce')
  		textArea.disabled = !textArea.disabled
        
        if(textArea.disabled == false){
        	console.log("inside false");
        	textArea.required = true;
        	textArea.style="background: ##fff;"
        	
        }else{
         	textArea.disabled = true
        	textArea.required = false;
        	console.log("inside false");
        	textArea.style="background: #dddddd;";
        	textArea.value = "";
        }
        
    });
    
    document.getElementById('ne').addEventListener( 'click', function(){
        var textArea = document.getElementById('nce')
        textArea.disabled = !textArea.disabled
        
        if(textArea.disabled == false){
        	console.log("inside false");
        	textArea.required = true;
        	textArea.style="background: ##fff;"
        	
        }else{
         	textArea.disabled = true
        	textArea.required = false;
        	console.log("inside false");
        	textArea.style="background: #dddddd;";
        	textArea.value = "";
        	
        }
        
    });
      
      $('.hideGST').hide();
   	 $('.hideIGST').hide();
   	 //console.log("Done----- "+ x);
    
   
</script>

</body>
</html>