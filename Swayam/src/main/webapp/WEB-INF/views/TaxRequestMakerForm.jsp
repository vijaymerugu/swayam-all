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
 <script src="resources/js/Tax-Request-approval-form.js"></script> 
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
	
 		<div><h3>Invoice Compare Request</h3></div>
		<form name="sanctionForm" > 
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
                       <input type="text" name="circle" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['circleName']}" style="background: #dddddd;"  disabled/>
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
                       <input type="text" name="state" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['state']}" style="background: #dddddd;"  disabled/>
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
                       <input type="text" name="vendor" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['vendor']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
	        </td>
	       
	       					
        </tr>
      
        <tr>
		
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Year<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                   <div class="col-xs-6">
                       <input type="text" name="year" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['finyear']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Time Period<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                
                   <div class="col-xs-6">
                       <input type="text" name="time" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['quarter']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>                                
			</td>
			    <td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">RFP Ref. No <b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                  <div class="col-xs-6">
                       <input type="text" name="rfpNo" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['rfpno']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>                             
	        </td>
			
			
		</tr>
		<tr>
		<td >
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Gst type<b>*</b></span>
                        <span class="pull-right">:</span>
                    </div>
                 
                   <div class="col-xs-6">
                       <input type="text" name="gsttype" onload="myFunction()" id="gsttype"
                         placeholder="" autocomplete="off" value="${mapFormDetail['gstType']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>                                
	        </td>	
		</tr>
		 <tr>
		<td class="hideIGST">
             <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">IGST(%)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                   
                   <div class="col-xs-6">
                       <input type="number" name="igst" id="igst" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['gst']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			<td class="hideGST">
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">SGST(%)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number"  name="sgst" id="sgst" step="0.01" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['sgst']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			<td class="hideGST">
				<div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">CGST(%)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                     <div class="col-xs-6">
                       <input type="number"  name="cgst" id="cgst" step="0.01"
                         placeholder="" autocomplete="off" value="${mapFormDetail['cgst']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>	
		</tr>
		 <tr>
		<td>
                <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">AMC Amount<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <input type="number" name="invAmt" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['invoiceAmt']}" style="background: #dddddd;"  disabled/>
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
                       <input type="number" name="penAmt" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['penaltyAmt']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">GST on AMC Amount (A)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                     <div class="col-xs-6">
                       <input type="number" name="amcgst" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['amcGst']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			</tr>
			<tr>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">GST on Penalty Amount (B)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                     <div class="col-xs-6">
                       <input type="number" name="pengst" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['penGst']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			<td>
			 <div class="row">
                    <div class="col-xs-6 lb">
                        <span class="text-left">Total gst (A+B)<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                     <div class="col-xs-6">
                       <input type="number" name="totgst" 
                         placeholder="" autocomplete="off" value="${mapFormDetail['totalGst']}" style="background: #dddddd;"  disabled/>
                    </div>
                </div>
			</td>
			
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
                        <span class="text-left">Comments by Maker<b>*</b></span>
                        <span class="pull-right"></span>
                    </div>
                    <div class="col-xs-6">
                       <textarea  name="noteE" rows="4" cols="50" maxlength="100" pattern="^[a-zA-Z0-9 ,&.]*$" ng-pattern-restrict
                         placeholder="Allowed special characters (&,.)" autocomplete="off"  ng-model="selectedCommnets" Required></textarea>
                    </div>
                     <div class="col-xs-6">
                    <span ng-show="sanctionForm.noteE.$error.pattern">Please update valid comment</span>
                    <span ng-show="sanctionForm.noteE.$error.required"></span>
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
    </div> -->
    
    
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