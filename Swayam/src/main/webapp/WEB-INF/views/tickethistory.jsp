<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>

  <!--    <script>
$(document).ready(function(){
    $(function () {
    
    	$('#datepickerFromDate,#datepickerToDate').datepicker({ changeYear: true, changeMonth: true,autoclose: true,maxDate: new Date(), dateFormat:'dd-mm-yy'});
		
    });
    
});
			
</script>   -->


 
 <meta http-equiv="x-ua-compatible" content="IE=edge">
<link rel="stylesheet" href="resources/css/ui-grid.group.min.css">
<!-- <script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment-with-locales.min.js"></script> -->
<script src="resources/js/moment-with-locales.min.js"></script>
<script src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/ticket-history-app.js"></script>
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



<!-- <meta http-equiv="x-ua-compatible" content="IE=edge">

<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">
<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/ticket-history-app.js"></script>
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
    <script src="resources/js/angular-aria.js"></script> -->
     
   <!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
    
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
        
         option:empty
          {
           display:none;
          }
    </style>
   

</head>
<body>

<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">
 <div>
 <form name="myForm" id="myForm">
		<div class="tb-bk">
   <table>	
   
   <tr>
	                
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Kiosk Id<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="kioskId" name="kioskId" ng-model="kioskId" maxlength="15"  onkeypress="return validateCode(this);" />
                    </div>
                </div>                              
			</td>	
	        
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Call Log Date<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    
                    <div class="col-xs-6">
                      <input type="text" id="datepickerFromDate" name="SelectedCallLogDateId" class="datepicker" readonly="readonly"  ng-model="SelectedCallLogDateId" placeholder="dd-mm-yyyy" required maxlength="10"/>
                       <!--  <select i name="callLogDate" ng-model="SelectedCallLogDateId">
                       
									<option value="0">Select CallLog Date</option>
									<option ng-repeat="item in States" value="{{item.callLogDate}}">{{item.callLogDate}}</option>
							</select> -->
                    </div>
                </div>                                
	        </td>
	        
	        
	         <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Call Category<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="category" name="Category" ng-model="SelectedCategoryId">
									<option value="0" style="padding-top: 0px;">Select Category</option>
									<option ng-repeat="item in Categorys" value="{{item.category}}">{{item.category}}</option>
							</select>
                    </div>
                </div>                                
	        </td>
	       						
        </tr>
   			
        <tr>
        
			 <td>
			 
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Branch Code<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="branchId" name="branchId" ng-model="branchCode" maxlength="5"  onkeypress="return isNumberKey(event,this.id)" />
                    </div>
                </div>                              
			</td>	
	        
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Call Closed Date<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                     <input type="text" id="datepickerToDate" name="SelectedCallClosedDateId" readonly="readonly"  class="datepicker" ng-model="SelectedCallClosedDateId" placeholder="dd-mm-yyyy" required maxlength="10"/>
                       <!--  <select name="State" ng-model="SelectedCallClosedDateId">
									<option value="0">Select Closed Date</option>
									<option ng-model="datepickerFromDate"><span id="datepickerFromDate"></span></option>
							</select> -->
                    </div>
                </div>                                
	        </td>
	        <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Call Sub Category<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="subCategory" name="SubCategory" ng-model="SelectedSubCategoryId">
									<option value="0">Select SubCategory</option>
									<option ng-repeat="item in Categorys" value="{{item.subCategory}}">{{item.subCategory}}</option>
							</select>
                    </div>
                </div>                                
	        </td>							
        </tr>
		<tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Circle<b></b></span>
                        <span class="pull-right">:</span>
                    </div>
                    <div class="col-xs-6">
                        <select id="circle" name="Circle" ng-model="SelectedCircelId">
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
                       <select id="vendor" name="Vendor" ng-model="SelectedVendorId">
									<option value="">Select Vendor</option>
									<option value="CMS">CMS</option>
									<option value="LIPI">LIPI</option>
									<option value="FORBES">FORBES</option>
								</select>
                    </div>
                </div>
			</td>
			 <td>
                <div class="row">
                    <div class="col-xs-6"></div>
                    <div class="col-xs-6">
                         <div class="text-right" style="width: 80%;" >
                        <button id="btnReset"  ng-click="resetPositions()">Reset</button>	
                          <!--  <button  id="btnReset" ng-click="Reset(kioskId,SelectedCallLogDateId,SelectedCategoryId,
							SelectedCircelId,SelectedCallClosedDateId,SelectedSubCategoryId,branchCode,SelectedVendorId)">Reset</button> -->
			                 <button  ng-click="searchPositions(kioskId,SelectedCallLogDateId,SelectedCategoryId,
							SelectedCircelId,SelectedCallClosedDateId,SelectedSubCategoryId,branchCode,SelectedVendorId)">Generate</button>
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
	
	
	<input ng-model="searchText" ng-change="refresh()" placeholder="Enter KioskId,BranchCode,Circle,Vendor etc." style="font-size: 12px" size="150" height="80" class="form-group has-search" id="input">
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

	  var datePickerOptions = { changeYear: true, changeMonth: true,autoclose: true,maxDate: new Date(), dateFormat:'dd-mm-yy'}
    	
	    $( ".datepicker" ).datepicker(datePickerOptions);
	    
    $(".openpdfonclick").click(function(){
    	
        $.ajax({
            url: 'report?page=tiketHistory&type=pdf',
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
            url: 'report?page=tiketHistory&type=excel',
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
  /*   $(function () {
        $("#btnReset").bind("click", function () {
           $("#kioskId")[0].selectedIndex = "";   
           $("#branchId")[0].selectedIndex = "";   
           $("#circle")[0].selectedIndex = "";            
           $("#category")[0].selectedIndex = "";
           $("#subCategory")[0].selectedIndex = "";
           $("#vendor")[0].selectedIndex = "";
            $("#datepickerFromDate")[0].selectedIndex = "dd-mm-yyyy";
            $("#datepickerToDate")[0].selectedIndex = "dd-mm-yyyy";
        });
    }); */






    $(function () {
        $("#btnReset").bind("click", function () {
          // $("#kioskId").val('');   
          // $("#branchId").val('');   
         //  $("#kioskId").prop('selectedIndex', ""); 
          //  $("#branchId").prop('selectedIndex', ""); 
          //  $("#kioskId").value = '';
           // $("#branchId").value = '';
       //  document.getElementById("kioskId").value = '';
         //document.getElementById("branchId").value = '';
        // $("#branchId").prop('selectedIndex', ""); 
          $('input[name=kioskId').val('');
          $('input[name=branchId').val('');
          // $("#circle")[0].val(''); 
           //$("#circle").val('');   
           $("#circle").prop('selectedIndex', "");          
          // $("#category")[0].selectedIndex = null;
           $("#category").prop('selectedIndex', "");
          // $("#subCategory")[0].selectedIndex = '';
           $("#subCategory").prop('selectedIndex', "");
          // $('#vendor')[0].val('');
         // $("#vendor").empty();
          $("#vendor").prop('selectedIndex', "");
           /*   $("#datepickerFromDate")[0].selectedIndex = "dd-mm-yyyy";*/
           // $('#datepickerFromDate').datepicker('setDate', "dd-mm-yyyy");
           // $('#datepickerToDate').datepicker('setDate', "dd-mm-yyyy");
            $('#datepickerFromDate').val('');
            $('#datepickerToDate').val('');
           // $("#datepickerToDate")[0].selectedIndex = "dd-mm-yyyy";
        });
    }); 





    
</script> 

<script type="text/javascript">




function isNumberKey(evt,id)
{
	try{
        var charCode = (evt.which) ? evt.which : event.keyCode;
  
        if(charCode==46){
            var txt=document.getElementById(id).value;
            if(!(txt.indexOf(".") > -1)){
	
                return true;
            }
        }
        if (charCode > 31 && (charCode < 48 || charCode > 57) )
            return false;

        return true;
	}catch(w){
		alert(w);
	}
}


/* 
function isNumberAndAlphaNumericKey(evt,id)
{
	if ($('#element').val() != "value") {
        var a = e.key;
        if (a.length == 1) return /[a-z]|[0-9]|&/i.test(a);
        return true;
    }
} */


function validateCode(){
    var TCode = document.getElementById('kioskId').value;
    if( /[^a-zA-Z_0-9]/.test( TCode ) ) {
       alert('Input is not alphanumeric');
       return false;
    }
    return true;     
 }


</script>

</body>
<sec:csrfInput />  
</html>