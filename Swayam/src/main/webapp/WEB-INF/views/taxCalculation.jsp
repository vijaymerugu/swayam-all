<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/tax-calculation.js"></script>
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
<div ng-controller="taxCalCtrl as vm">
<div>
 		
		<form> <!-- ng-submit="searchPositions(SelectedCircelId,SelectedStateId,
							SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId)" -->
		<div class="tb-bk">
   <table>				
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
                        <select id="state"  class="form-group" name="State" ng-model="SelectedStateId"  ng-change="resetRfp()" required>
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
                       <select id="vendor"   class="form-group" name="Vendor" ng-model="SelectedVendorId" ng-change="resetRfp()" required>
									 <option value="" selected>--Select Vendor--</option>  
									<option ng-repeat="item in Vendors" value="{{item.vendorId}}">{{item.vendor}}</option>
								</select>
                    </div>
                </div>
			</td>							
        </tr>
		<tr>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Year<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                     <!--   <select id="year"  class="form-group" name="Year" ng-model="SelectedYearId" required>
									  <option value="" selected>--Select Year--</option>  
									<option ng-repeat="year in Years" value="{{year}}">{{year}}</option>
							</select> -->
					<select id="year"  class="form-group" name="Year" ng-model='SelectedYearId'  ng-change="resetRfp()"
							required ng-options='option.value as option.name for option in yearOptions' required></select>
							
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
                       ng-change="resetRfp()" required>
                       				 <option value="" selected>--Select Time Period--</option> 
									<option value="Q1">Q1(Apr-Jun)</option>
									<option value="Q2">Q2(Jul-Sep)</option>
									<option value="Q3">Q3(Oct-Dec)</option>
									<option value="Q4">Q4(Jan-Mar)</option>
							</select>
                    </div>
                </div>                                
	        </td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">RFP Ref. No.<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="refno"  class="form-group" name="RefNo" ng-model="RfpId" 
                       ng-change="changeInRFP(SelectedCircelId,SelectedStateId,
							SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId)" required>
									 <option value="">--Select Rfp.No.--</option> 
									<option ng-repeat="item in Rfpids" value="{{item.rfpNo}}">{{item.rfpNo}}</option>
								</select>
                    </div>
                </div>
			</td>							
		</tr>
		<tr>
	        <td>
	         <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Applicable GST<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                       <select id="gsttype"  class="form-group" name="GstType" onchange="myFunction()" ng-model="SelectedGSTType" required>
                       				 <option value="" selected>--Select GST Type--</option> 
									<option value="IGST">IGST</option>
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
                      <input type="number" min="0" max="20"  step="0.01"  ng-change="IGSTChnage()" ng-model="selectedIGST" name="igst" id="igst"
                         placeholder="" autocomplete="off" />
                        
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
                      <input type="number" min="0" max="10" step="0.01"  ng-change="SGSTChnage()" ng-model="selectedSGST" name="sgst" id="sgst"
                         placeholder="" autocomplete="off" />
                        
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
                         <input type="number" min="0" max="10" step="0.01" ng-change="CGSTChnage()"   ng-model="selectedCGST" name="cgst" id="cgst" 
                         placeholder="" autocomplete="off" /> 
                        
                    </div>
                </div>
			</td>	
									
        </tr>
		
        <tr>
            <td></td>
            <td>
            <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                             <button  id="btnReset" ng-click="resetPositions()">Reset</button>	
                            <!--  <button  confirmed-click="searchPositions(SelectedCircelId,SelectedStateId,
							SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId,SelectedGSTType,selectedIGST,
							selectedSGST,selectedCGST)"  ng-confirm-click="Are you sure?">Submit</button> -->
                             						
			                 <button  ng-click="searchPositions(SelectedCircelId,SelectedStateId,
							SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId,SelectedGSTType,selectedIGST,
							selectedSGST,selectedCGST)">Submit</button> 
							 <!-- <button  type="submit" id="submit">Generate</button> -->
                         </div>
                    </div>
                </div>
            </td>
            <!-- <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
							<button  id="btnReset" ng-click="InsertCal(SelectedCircelId,SelectedStateId,
							SelectedQuarterId,SelectedYearId,SelectedVendorId,RfpId,SelectedGSTType,selectedIGST,
							selectedSGST,selectedCGST)" ng-disabled="isDisabled">Submit</button>	
							 <button  type="submit" id="submit">Generate</button>
                         </div>
                    </div>
                </div>
			</td> -->
        </tr>			   
			</table>
		</div>
		</form>
		</div> 
<br/>
		<div class="submain">
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter Circle, Vendor, State, Rfp. ref no. etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
		<span style="float:right">
		<a class="openpdfonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/pdf.svg"></a>
		<a class="openxlonclick" style="cursor: hand;cursor: pointer;"><img src="resources/img/excel.svg"></a>
		&nbsp;&nbsp;&nbsp;
		</span>
		<br/>
		
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-exporter ui-grid-resize-columns id="test"></div>
		
        
    </div>
    
    
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
  	   
  	/*  $('.hideSGST').hide();
  	 $('.hideIGST').hide();  */
  	 // document.getElementById("demo").innerHTML = "You selected: " + x;
  	}
    
    $('.hideGST').hide();
 	 $('.hideIGST').hide();
 	 console.log("Done----- "+ x);
</script>

</body>
</html>