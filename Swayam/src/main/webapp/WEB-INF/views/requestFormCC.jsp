<!DOCTYPE html>
<html lang="en">
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="resources/js/requests-cc-app.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/>

<script src="resources/js/csv.js"></script>
    <script src="resources/js/pdfmake.js"></script>
    <script src="resources/js/vfs_fonts.js"></script>
    <script src="resources/js/lodash.min.js"></script>
    <script src="resources/js/jszip.min.js"></script>
    <script src="resources/js/excel-builder.dist.js"></script>  
    <script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>

</head>
<body>
<div class="main" ng-app="app" id="appId">
<div ng-controller="UserManagementCtrl as vm">


		<div class="submain">
	
	
	<input class="form-group has-search" ng-model="searchText" ng-change="refresh()" placeholder="Enter Ticket Id, Kiosk Id, Branch Code, Circle etc." style="font-size: 12px" size="150" height="80" id="input">
		
		
		<br/>
		<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination ui-grid-selection ui-grid-exporter id="test"></div>
		
        
    </div>
    <div align="center">
      <input type="submit" value="REJECT" class="openRejectPopup">
      <input type="submit" value="APPROVE" class="openFinalPopup">
      </div>
    
	</div>
</div>	
	
	
 <div id="reportbuttons">
                <!-- <button type="button" class="btn bg-red waves-effect" onclick="convertToPdf()"  style="margin-right: 5px;float: right; margin-top: -8px">Download</button>  
                 <button type="button"  class="btn bg-red waves-effect"  onclick="sendMail()"  style="margin-right: 5px;float: right; margin-top: -8px">Send Mail</button>  -->
     <!-- Modal -->
      <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
             <!--  <h4 class="modal-title">Modal Header</h4> -->
            </div>
            <div class="modal-body">
              <p id="para">Some text in the modal.</p>
            </div>
                        
            <div class="modal-footer">
               <button type="button"  id="butn"  data-dismiss="modal">OK</button>
            </div>
          </div>

        </div>
      </div>
</div> 
	
<script>

$(document).ready(function(){
    $('.openFinalPopup').on('click',function(){        
         
        var all_rows = [];
        var keyDisplay = [];
        $('.addedRows').each(function() {
                var this_row={};                
                $(this).find("input").each(function(){                
                var keyvalue;
                mystring = $(this).attr('name');
                var matches = mystring.match(/\[(.*?)\]/);
                if (matches) {
                    keyvalue = matches[1];
                }                
                namevalue = $(this).val();                
                if(namevalue !=undefined && namevalue != ''){
                	this_row[keyvalue] = namevalue;
                	all_rows.push(this_row);
                	keyDisplay.push(keyvalue);  
                }
                
                
            });          

        });
        console.log(all_rows);               
        
	    
        $.ajax({
            type: "POST",
            //url: "/hm/saveCheckerComments?array="+all_rows,
            url: "hm/saveApproverCommentsCC",
            //data: '{array: "' + all_rows + '"}',
            data: JSON.stringify(all_rows),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
            	$("#myModal").modal();
                $("#para").html("Successfully approved:"+ keyDisplay.join(',')); 
            	console.log('Success');
            },
            failure: function (response) {
            	console.log('Failed');
            }
        });
    }); 
    
$('.openRejectPopup').on('click',function(){        
        
        var all_rows = [];
        var keyDisplay = [];
        $('.addedRows').each(function() {
                var this_row={};                
                $(this).find("input").each(function(){                
                var keyvalue;
                mystring = $(this).attr('name');
                var matches = mystring.match(/\[(.*?)\]/);
                if (matches) {
                    keyvalue = matches[1];
                }                
                namevalue = $(this).val();                
                if(namevalue !=undefined && namevalue != ''){
                	this_row[keyvalue] = namevalue;
                	all_rows.push(this_row);
                	keyDisplay.push(keyvalue);
                }
                 
            });          

        });
        console.log(all_rows);               
        
        $.ajax({
            type: "POST",
            //url: "/hm/saveCheckerComments?array="+all_rows,
            url: "hm/rejectApproverCommentsCC",
            //data: '{array: "' + all_rows + '"}',
            data: JSON.stringify(all_rows),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
            	$("#myModal").modal();
                $("#para").html("Rejected:"+ keyDisplay.join(',')); 
            	console.log('Success');
            },
            failure: function (response) {
            	console.log('Failed');
            }
        });
    });
});

$(document).ready(function(){
	 $('#butn').on('click',function(){      
	        //alert("call11 ok ");
	        
	    	$("#contentHomeApp").load('hm/requestFormCC'); 	    	
	    	$('.modal-backdrop').remove();
	    	$("body").css({"overflow":"visible"});
	    }); 
  
});
</script>
</body>
</html>