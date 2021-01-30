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
 <script src="resources/js/sanction-request-approval-form.js"></script> 
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
    
    <!--    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js"></script>
    
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
  -->
   <!-- Include Date Range Picker -->
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="resources/css/bootstrap-datepicker3.css" />
    
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
<div ng-controller="SanctionApprovalCtrl as vm">
<input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >
<input type="hidden" id="reqId" value="${mapFormDetail['requestId']}"" >
<div>
	
 		<div><h3>Sanction Request</h3></div>
		<form name="sanctionForm" > 
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
                        <select id="recommdAu" class="form-group" name="recommdAu" 
								 style="background: #dddddd;" disabled>
									 <option value="${mapFormDetail['recomAuth']}" selected>${mapFormDetail['recomAuth']}</option>
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
                        <select id="sanctionAu" class="form-group" name="sanctionAu" 
								style="background: #dddddd;"  disabled>
									 <option value="${mapFormDetail['sanctionAuth']}" 
									 selected>${mapFormDetail['sanctionAuth']}</option>
							
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
                        <select id="controllingAu" class="form-group" name="controllingAu" 
								style="background: #dddddd;" disabled>
									  <option value="${mapFormDetail['ctlrAuth']}" 
									 selected>${mapFormDetail['ctlrAuth']}</option>
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
                        <select id="circle" class="form-group" name="Circle" 
								style="background: #dddddd;"  disabled>
									  <option value="${mapFormDetail['circle']}" 
									 selected>${mapFormDetail['circle']}</option>
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
                        <select id="state"  class="form-group" name="State"  style="background: #dddddd;" disabled>
									   <option value="${mapFormDetail['state']}" 
									 selected>${mapFormDetail['state']}</option>
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
                       <select id="vendor"   class="form-group" name="Vendor" style="background: #dddddd;"  disabled>
									 <option value="${mapFormDetail['vendor']}" selected>${mapFormDetail['vendor']}</option>  
									
								</select>
                    </div>
                </div>
			</td>
									
        </tr>
        <tr>
		<%-- <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Sanction Note No<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text"  name="sanNo" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['sanNoteNo']}" style="background: #dddddd;" disabled/>
                    </div>
                </div>
			</td> --%>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Sanction Note Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="sanNoteDate" name="sanNoteDate"   
                         placeholder="dd-mm-yyyy" 
                        value="${mapFormDetail['sanNoteDt']}"
                        style="background: #dddddd;" disabled maxlength="10"/>
                        
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
							<select id="year"  class="form-group" name="Year" style="background: #dddddd;"  disabled>
							<option value="${mapFormDetail['year']}" 
									 selected>${mapFormDetail['year']}</option>
							</select>
							
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
                       <select id="timeperiod"  class="form-group" name="TimePeriod" style="background: #dddddd;"  disabled>
                       				<option value="${mapFormDetail['quarter']}" 
									 selected>${mapFormDetail['quarter']}</option>
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
                       <input type="text"  name="invNo" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['invoiceNo']}" style="background: #dddddd;"  disabled/>
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
                        <input type="text" id="invoiceDate" name="invoiceDate"  
                        placeholder="dd-mm-yyyy" value="${mapFormDetail['invoiceDt']}" style="background: #dddddd;"  disabled maxlength="10"/>
                        
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
                       <input type="number" name="noKisok" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['noOfKiosk']}" style="background: #dddddd;"  disabled/>
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
                        <input type="text" id="fromDate" name="fromDate"   
                         placeholder="dd-mm-yyyy" value="${mapFormDetail['invFr']}" style="background: #dddddd;"  disabled maxlength="10"/>
                        
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
                        <input type="text" id="toDate" name="toDate"   
                         placeholder="dd-mm-yyyy" value="${mapFormDetail['invTo']}" style="background: #dddddd;"  disabled maxlength="10"/>
                        
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
                        <input type="text" id="receiptDate" name="receiptDate"  
                        placeholder="dd-mm-yyyy" value="${mapFormDetail['receiptDt']}" style="background: #dddddd;"  disabled maxlength="10"/>
                        
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
                       <input type="number"  name="invAmt" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['invoiceAmt']}"  style="background: #dddddd;" disabled/>
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
                       <input type="number" name="penAmt" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['penaltyAmt']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			
			<!-- <td>
				 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Tax%<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="20"  ng-model="selectedTax" name="itax" id="itax"
                         placeholder="" autocomplete="off" />
                        
                    </div>
                </div>
			</td>	 -->
		</tr>
		<tr>
			<td>
			<div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">GST Type<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="gsttype"  class="form-group" name="GstType" onload="myFunction()" style="background: #dddddd;" disabled>
                       				
                       				<option value="${mapFormDetail['gstType']}" selected>${mapFormDetail['gstType']}</option>
                       				 
                       				 <!-- <option value="" selected>--Select GST Type--</option> 
									<option value="IGST">IGST</option>
									<option value="SGST/CGST">SGST/CGST</option> -->
							</select>
                    </div>
                </div>     
               
			</td>
			<!-- <td>
				 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Tax%<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="20"  ng-model="selectedTax" name="ptax" id="ptax"
                         placeholder="" autocomplete="off" />
                        
                    </div>
                </div>
			</td> -->
			<td class="hideIGST">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">IGST<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="20"  name="igst" id="igst" step="0.01" 
                         placeholder="" autocomplete="off"  value="${mapFormDetail['igst']}" style="background: #dddddd;" disabled/>
                        
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
                      <input type="number" min="0" max="10"  name="sgst" id="sgst" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['sgst']}" style="background: #dddddd;" disabled/>
                        
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
                         <input type="number" min="0" max="10"  name="cgst" id="cgst" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['cgst']}" style="background: #dddddd;" disabled /> 
                        
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
                       <input type="number"  name="tdsPer" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['tdsPct']}" style="background: #dddddd;"  disabled/>
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
                       <input type="number" name="tdsGst" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['gstTdsLimitAmt']}" style="background: #dddddd;"  disabled/>
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
                       <input type="number"  name="tdsGstPer" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['gstTdsPer']}" style="background: #dddddd;"  disabled/>
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
                       <input type="number"  autocomplete="off" value="${mapFormDetail['sanLimit']}" style="background: #dddddd;" disabled/>
                    </div>
                </div>  
               
			</td>
			<%-- <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular No<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text" name="circularNo" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['circularNo']}" style="background: #dddddd;" disabled/>
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
                         <input type="text" id="circularDate" name="circularDate"  
                        placeholder="dd-mm-yyyy" value="${mapFormDetail['circularDate']}" style="background: #dddddd;"  disabled maxlength="10"/>
                        
                    </div>
                </div>
			</td>	 	 --%>
		</tr> 
		
			 <tr>
		<td>
			
                   <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circular Sl.No.<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="text"  autocomplete="off" value="${mapFormDetail['circularSlNo']}" style="background: #dddddd;" disabled/>
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
                       <input type="text" name="circularNo" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['circularNo']}" style="background: #dddddd;" disabled/>
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
                         <input type="text" id="circularDate" name="circularDate"  
                        placeholder="dd-mm-yyyy" value="${mapFormDetail['circularDate']}" style="background: #dddddd;"  disabled maxlength="10"/>
                        
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
                       <textarea name="mauualE" rows="4" cols="50" 
                         placeholder="" autocomplete="off" style="background: #dddddd;" disabled>${mapFormDetail['manualEntry']}</textarea>
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
                       <textarea  name="noteE" rows="4" cols="50" 
                         placeholder="" autocomplete="off"  style="background: #dddddd;" disabled>${mapFormDetail['creditEntry']}</textarea>
                    </div>
                </div>
			</td>
			<!-- <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                         	<button  id="btnCancel" ng-click="reject()" style="cursor: hand;cursor: pointer;">REJECT</button>
							<button  id="btnsubmit" ng-click="approve()" style="cursor: hand;cursor: pointer;" >APPROVE</button>
							 <button  type="submit" id="submit">Generate</button>
                         </div>
                    </div>
                </div>
			</td>	 -->
		</tr>			   			   
			</table> 
			
			</div>
			
		</form>
		<form name="sanctionForm2" > 
		<div class="tb-bk">	
		<table>
		<tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Comments by Maker<b></b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <textarea  name="noteE" rows="4" cols="50" maxlength="100" 
                         placeholder="" autocomplete="off"  ng-model="selectedCommnets"></textarea>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                         	<button  id="btnCancel" ng-click="cancel()">CANCEL</button>
                         </div>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
							<button  id="btnsubmit" ng-click="updateComment(selectedCommnets)" style="cursor: hand;cursor: pointer;" >SUBMIT</button>
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
      
      $('.hideGST').hide();
   	 $('.hideIGST').hide();
   	 
   	myFunction();
   	 //console.log("Done----- "+ x);
    
   
</script>

</body>
</html>