<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
<!-- <link rel="stylesheet" href="resources/css/ui-grid.group.min.css"> -->

<script
	src="resources/js/ui-grid.min.js"></script>

<script src="resources/js/rfp-details.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/style.css">

<link rel="stylesheet" href="resources/css/font-awesome.min.css"/> 

<script src="resources/js/a076d05399.js"></script>

<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>

<link rel="stylesheet" href="resources/css/bootstrap.min.css"> 
	
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
		//$('#amcDate').datepicker(datePickerOptions);
		$('#slaDate').datepicker(datePickerOptions).on('change', function() {
			  // Does some stuff and logs the event to the console
			  
			var sladate = $(this).val();
			  console.log("date Sla " + sladate)
			  
			
			  
			var arr_dateText = sladate.split("-");
			  sladay = arr_dateText[0];
			  startmonth = arr_dateText[1];
				startyear = (parseInt(arr_dateText[2]) + 1).toString();
				
				expiryYear = (parseInt(arr_dateText[2]) + 5).toString();
				
				var amcdate  = sladay+"-"+startmonth+"-"+startyear;
				
				var slaExpiryDate  = sladay+"-"+startmonth+"-"+expiryYear;
				
				console.log("AMC Date " + amcdate);
				
				if(sladate=='' || sladate==null){
					
					$('#amcDate').val('');
					
					$('#slaExpDate').val('');
					
				}else{
					
					$('#amcDate').val(amcdate);
					
					$('#slaExpDate').val(slaExpiryDate);
				}
				
				
				
			  
		});
		
	/* 	var $datepicker = $('#amcDate');
		$datepicker.datepicker();
		$datepicker.datepicker('setDate', new Date()); */
		 
		
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
					selectedAMCcost,selectedCPenalty,selectedDMU,selectedDMUR,selectedDCT,selectedMP
					,selectedRfpDate,selectedAmcDate,selectedPeriodicty,selectedPenType,selectedRangeTo,selectedRangeFrom)" autocomplete="off"> 
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
									 <option value="" selected>--Select Vendor--</option> 
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
                        <span class="text-left">SLA Start Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                   <div class="col-xs-6">
                        <input type="text" id="slaDate" name="slaDateId" class="datepicker" 
                        readonly="readonly"  ng-model="selectedSLAdate" placeholder="dd-mm-yyyy" onchange="changeAMCDate()" required maxlength="10"/>
                        
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">SLA Expiry Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input class='dateField' type="text" id="slaExpDate" name="slaexpDateId" style="background: #dddddd;"  
                         ng-model="selectedSlaExpDate" placeholder="dd-mm-yyyy" required maxlength="10" disabled/>
                        
                    </div>
                </div>
			</td>
			<!-- changes 09-11-2020 -->
			<tr>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">AMC Start Date<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="amcDate" name="amcDateId" style="background: #dddddd;"  
                         ng-model="selectedAmcDate" placeholder="dd-mm-yyyy" required maxlength="10" disabled/>
                        
                    </div>
                </div>
			</td>
            
           	
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">AMC Periodicity<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                   <div class="col-xs-6">
                    <select id="periodicity"  name="periodicity" ng-model="selectedPeriodicty" required>
									 <option value="" selected>--Select Period--</option> 
									<option value="Quarterly">Quarterly</option>
									<option value="Half-Yearly">Half-Yearly</option>
								</select>
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Penalty Type<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                   <div class="col-xs-6">
                    <select id="penaltyType"  name="penaltyType" ng-model="selectedPenType" onchange="myFunction()" required>
									 <option value="" selected>--Select Type--</option> 
									<option value="Fixed">Fixed</option>
									<option value="Range">Range</option>
								</select>
                    </div>
                </div>
			</td>
			</tr>
			 <tr>
			 <td class="hidePenalty">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Maximum Penalty(in %)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                      <input type="number" min="0" max="100"  ng-model="selectedMP" name="mp" 
                         placeholder="" autocomplete="off" />
                        
                    </div>
                </div>
			</td>
			 
		<td class = "hideRange">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Penalty Range To<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="0" max="999"  ng-model="selectedRangeTo" name="Pento" 
                         placeholder="" autocomplete="off"/>
                    </div>
                </div>
			</td>
		 <td class = "hideRange">
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Penalty Range From<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" min="0" max="999"   ng-model="selectedRangeFrom" name="Penfrom" 
                         placeholder="" autocomplete="off"/>
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
            $("#periodicity")[0].selectedIndex = "";
            $("#penaltyType")[0].selectedIndex = "";
           // $('#dd').data("DateTimePicker").clear()
           
            $('.datepicker').datepicker('setDate', null);
            $('#slaDate').datepicker('setDate', null);
           /*  $('#amcDate').datepicker('setDate', null);
            $('#slaExpDate').datepicker('setDate', null); */
            
          //  $('input[name=amcDateId').val('');
          
            
        });
    });
    
    
    function myFunction() {
    	  var x = document.getElementById("penaltyType").value;
    	  
    	  console.log("Value " + x);
    	  
    	  if(x=="Fixed"){
    		  $('.hideRange').hide();
    		  $('.hidePenalty').show();
    		  
    	  }else if (x=="Range"){
    		  $('.hidePenalty').hide();
    		  $('.hideRange').show();
    	  }else{
    		  $('.hidePenalty').show();
    		  $('.hideRange').show(); 
    	  }
    	  
    	  
    	 // document.getElementById("demo").innerHTML = "You selected: " + x;
    	}
    
/*     function changeAMCDate(){
    	
    	
    	var x = document.getElementById("slaDate").value;
    	console.log("sla date  " + x);
    	
    	//var res = x.replace("-", "/");
    	
    	var dateObject = new Date(x);
    	
    	console.log("date --   " + dateObject.toString());
    	
    	document.getElementById("amcDate").valueAsDate = new Date();
    }
    
    function add_years(dt,n) 
    {
    return new Date(dt.setFullYear(dt.getFullYear() + n));      
    } 
     */
    
</script>


</body>

</html>