<%@page import="org.hibernate.validator.constraints.SafeHtml.Tag"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="resources/css/ui-grid.min.css"/>

<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
 <link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css"> 
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>  
 <script src="resources/js/ui-grid.js"></script>
<script	src="resources/js/ui-grid.min.js"></script>

<link href="resources/css/menu.css" rel="stylesheet" type="text/css">	
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
<script> 

/* $(document).ready(function(){
   
	$('.openPopupAssign').on('click',function(){
    	//alert("in popup");

        $.ajax({
        	type:"POST",
        	url:"uploadKiosk",
        	// data:formData,
            success: function(data){

                alert(data);
        	     resp=data;       	 	      
         }
        });
         $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        }); 
    }); 
});
  */


/* $(document).ready(function(){
    
	$('.openPopupAssignCBS').on('click',function(){
    	
             var myfile=document.getElementById("myFile").value;  
           //  alert(myfile);
           //  var obj = {myfile};
             console.log(myfile);
        $.ajax({
        	type:"POST",
        	url:"uploadCBSbrhm",
        	 data: JSON.stringify(obj),
             contentType: "application/json; charset=utf-8",
             dataType: "json",

            success: function(data){
               // alert("data"+data);
        	     resp=data;       	 	     	 
         }
        });
         $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        }); 
    }); 
}); */


// ========== Holiday ==
	 
	 $(document).ready(function(){

    $(".openPopupAssignHoliday").click(function(){
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
        
         var files = $('#myFile')[0].files[0];
         if(files==null)
	    	 {
	    	 alert("Please select file for upload!!!")
	    	 $("#myFile").focus();
	    	 
	    	 }
         else if(!(files.name.includes("xlsx")))
        	 {
        	 alert("Please select xlsx file for upload!!!")
        	 $("#myFile").focus();
        	 $("#myFile").val('');
        	 }
         else{
		        fd.append('myFile',files);
		        console.log("2"+fd); 
		        $.ajax({
		            url: 'uploadHolidayCalendar',
		            type: 'post',
		            data: fd,
		            enctype: 'multipart/form-data',
		            contentType: false,
		            processData: false,
		            headers: 
		            {
		                'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
		            },
		
		            success: function(data){
		            	resp= data;  
		                //  alert(resp) ;    	 	        	
		            	// $("#para").html(resp);	        	 
			     		if(data == 'Data Not Uploaded'){ 
			     			$("#para").html("Data Not Uploaded-View downloded file with empty columns"); 
				     		 modal.style.display = "block"; 
				     		window.open("resources/download/Holiday_Calendar.xlsx" , '_blank');    
			     		}
			     		else if(data =='Wrong File for upload'){
			     			$("#para").html("Trying to upload Wrong File: "+files.name+" . Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     	}
			     		else if(data =='Blank File for upload'){
			     			$("#para").html("Trying to upload Blank File: "+files.name+" . Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     	}
			     		else if(data =='Blank File(Fill only Column name) for upload'){
			     			$("#para").html("Blank File(Fill only Column name) for upload. Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     	}
			     		else if(data =='Wrong File or Data Sequence for upload'){
			     			$("#para").html("Wrong File or Data Sequence for upload. Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     	}
			     		else if(data =='Header missing in file'){
			     			$("#para").html("Header missing in file. Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     	}
				     	else if(data =='Holiday Date is Already Exist'){
			     			$("#para").html("Duplicate record for upload. Choose correct file for upload"); 
			     			 modal.style.display = "block"; 
					     		window.open("resources/download/Holiday_Calendar.xlsx" , '_blank');    
				     	}
			     		else{
			     			$("#para").html("Holiday Calendar Data Uploaded Successfully"); 
				     		 modal.style.display = "block"; 
				     	} 
			     	//	alert(data1);         
		          
		            }
		        });
    		}
    });
});
// === KioskDetails
 $(document).ready(function(){

    $(".openPopupKioskCMF").click(function(){
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
         var files = $('#KioskFile')[0].files[0];
         if(files==null)
    	 {
    	 alert("Please select file for upload!!!")
    	 $("#KioskFile").focus();
    	 }
         else if(!(files.name.includes("xlsx")))
    	 {
    	 alert("Please select xlsx file for upload!!!")
    	 $("#KioskFile").focus();
    	 $("#KioskFile").val('');
    	 }
     	else{
	        fd.append('KioskFile',files);
	        console.log("2"+fd);
	        $.ajax({
	            url: 'uploadKioskDetails',
	            type: 'post',
	            data: fd,
	            enctype: 'multipart/form-data',
	            contentType: false,
	            processData: false,
	            headers: 
	            {
	                'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
	            },
	
	            success: function(data){
	            	resp= data;  
	            	//alert(resp) ;    	 	        	
		        	// $("#para").html(resp);
		        	 
		     		if(data == 'Data Not Uploaded'){ 
		     			$("#para").html("Data Not Uploaded-View downloded file with empty columns"); 
			     		 modal.style.display = "block"; 
		     			window.open("resources/download/Kiosk_CMF.xlsx" , '_blank');  
		     		}
		     		else if(data =='Wrong File for upload'){
		     			$("#para").html("Trying to upload Wrong File: "+files.name+" . Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Blank File for upload'){
		     			$("#para").html("Trying to upload Blank File: "+files.name+" . Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Blank File(Fill only Column name) for upload'){
		     			$("#para").html("Blank File(Fill only Column name) for upload. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Wrong File or Data Sequence for upload'){
		     			$("#para").html("Wrong File or Data Sequence for upload. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Header missing in file'){
		     			$("#para").html("Header missing in file. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Kiosk Id is Already Exist'){
		     			$("#para").html("Duplicate record for upload. Choose correct file for upload"); 
		     			 modal.style.display = "block"; 
				     		window.open("resources/download/Kiosk_CMF.xlsx" , '_blank');    
			     	}
		     		else{
		     			$("#para").html("Kiosk CMF Data Uploaded Successfully"); 
		     			
			     		 modal.style.display = "block"; 
			     	}
		     		//alert(data1);          
	          
	            }
	        });
	     }
    });
}); 
// 
 $(document).ready(function(){

    $(".openPopupAssignCMFDetails").click(function(){
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
         var files = $('#CMFFile')[0].files[0];
         if(files==null)
    	 {
    	 alert("Please select file for upload!!!")
    	 $("#CMFFile").focus();
    	 }
         else if(!(files.name.includes("xlsx")))
    	 {
    	 alert("Please select xlsx file for upload!!!")
    	 $("#CMFFile").focus();
    	 $("#CMFFile").val('');
    	 }
     	else
     	{
	        fd.append('CMFFile',files);
	        console.log("2"+fd);
	        $.ajax({
	            url: 'uploadKioskCMF',
	            type: 'post',
	            data: fd,
	            enctype: 'multipart/form-data',
	            contentType: false,
	            processData: false,
	            headers: 
	            {
	                'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
	            },
	
	            success: function(data){
	            	resp= data;  debugger;
	            	// alert(resp) ;    	 	        	
		        	// $("#para").html(resp);	        	 
		     		// alert(data1); 
		     		if(data == 'Data Not Uploaded'){ 
		     			$("#para").html("Data Not Uploaded-View downloded file with empty columns"); 
			     		 modal.style.display = "block"; 
			     		window.open("resources/download/Kiosk_Branch_Master.xlsx" , '_blank');    
		     		}
		     		else if(data =='Wrong File for upload'){
		     			$("#para").html("Trying to upload Wrong File: "+files.name+" . Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Blank File for upload'){
		     			$("#para").html("Trying to upload Blank File: "+files.name+" . Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Blank File(Fill only Column name) for upload'){
		     			$("#para").html("Blank File(Fill only Column name) for upload. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Wrong File or Data Sequence for upload'){
		     			$("#para").html("Wrong File or Data Sequence for upload. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Header missing in file'){
		     			$("#para").html("Header missing in file. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Kiosk Id is Already Exist'){
		     			$("#para").html("Duplicate record for upload. Choose correct file for upload"); 
		     			 modal.style.display = "block"; 
				     		window.open("resources/download/Kiosk_Branch_Master.xlsx" , '_blank');    
			     	}
		     		else{
		     			$("#para").html("Kiosk Details Data Uploaded Successfully"); 
			     		 modal.style.display = "block"; 
			     	}  
		        	            
	          
	            }
	        });
     }
    });
});
 
  $(document).ready(function(){

    $(".openPopupAssignCBS").click(function(){ 
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
        
         var files = $('#BMFile')[0].files[0];
         if(files==null)
    	 {
    	 alert("Please select file for upload!!!")
    	 $("#BMFile").focus();
    	 }
         else if(!(files.name.includes("xlsx")))
    	 {
    	 alert("Please select xlsx file for upload!!!")
    	 $("#BMFile").focus();
    	 $('#BMFile').val('');
    	 }
    	 else
    	 {
	        fd.append('BMFile',files);
	        console.log("2"+fd);
	        $.ajax({
	            url: 'uploadCBSbrhm',
	            type: 'post',
	            data: fd,
	            enctype: 'multipart/form-data',
	            contentType: false,
	            processData: false,
	            headers: 
	            {
	                'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
	            },
	
	            success: function(data){
	            	resp= data;  
	            	
	            //	alert(resp) ;    	 	        	
		        	// $("#para").html(resp);	        	 
		     		if(data == 'Data Not Uploaded'){ 
		     			$("#para").html("Data Not Uploaded-View downloded file with empty columns"); 
			     		 modal.style.display = "block"; 
			     		window.open("resources/download/BranchMaster.xlsx" , '_blank');    
		     		}
		     		else if(data =='Wrong File for upload'){
		     			$("#para").html("Trying to upload Wrong File: "+files.name+" . Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Blank File for upload'){
		     			$("#para").html("Trying to upload Blank File: "+files.name+" . Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Blank File(Fill only Column name) for upload'){
		     			$("#para").html("Blank File(Fill only Column name) for upload. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     	
		     		else if(data =='Wrong File or Data Sequence for upload'){
		     			$("#para").html("Wrong File or Data Sequence for upload. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Header missing in file'){
		     			$("#para").html("Header missing in file. Choose correct file for upload"); 
			     		 modal.style.display = "block"; 
			     	}
		     		else if(data =='Branch code is Already Exist'){
		     			$("#para").html("Duplicate record for upload. Choose correct file for upload"); 
		     			 modal.style.display = "block"; 
				     		window.open("resources/download/BranchMaster.xlsx" , '_blank');    
			     	}
		     		else{
		     			$("#para").html("Branch Master Data Uploaded Successfully"); 
			     		 modal.style.display = "block"; 
			     	}          
	          
	            }
	        });
	     }
    });
}); 
  $(document).ready(function(){

	    $(".openPopupAssignInVen").click(function(){ 
	    	var modal = document.getElementById("myModal");
	        var fd = new FormData();
	        
	         var files = $('#InFile')[0].files[0];
	         if(files==null)
	    	 {
	    	 alert("Please select file for upload!!!")
	    	 $("#InFile").focus();
	    	 }
	         else if(!(files.name.includes("xlsx")))
	    	 {
	    	 alert("Please select xlsx file for upload!!!")
	    	 $("#InFile").focus();
	    	 $('#InFile').val('');
	    	 }
	    	 else
	    	 {
		        fd.append('InFile',files);
		        console.log("2"+fd);
		        $.ajax({
		            url: 'uploadInvVendor',
		            type: 'post',
		            data: fd,
		            enctype: 'multipart/form-data',
		            contentType: false,
		            processData: false,
		            headers: 
		            {
		                'X-CSRF-TOKEN': $('input[name="_csrf"]').attr('value')
		            },
		
		            success: function(data){
		            	resp= data;  
		            	
		            //	alert(resp) ;    	 	        	
			        	// $("#para").html(resp);	        	 
			     		if(data == 'Data Not Uploaded'){ 
			     			$("#para").html("Data Not Uploaded-View downloded file with empty columns"); 
				     		 modal.style.display = "block"; 
				     		window.open("resources/download/Vendor_Invoice.xlsx" , '_blank');    
			     		}
			     		else if(data =='Wrong File for upload'){
			     			$("#para").html("Trying to upload Wrong File: "+files.name+" . Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     		
				     	}
			     		else if(data =='Blank File for upload'){
			     			$("#para").html("Trying to upload Blank File: "+files.name+" . Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     		
				     	}
			     		else if(data =='Blank File(Fill only Column name) for upload'){
			     			$("#para").html("Blank File(Fill only Column name) for upload. Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     		
				     	}
			     	
			     		else if(data =='Wrong File or Data Sequence for upload'){
			     			$("#para").html("Wrong File or Data Sequence for upload. Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     		
				     	}
			     		else if(data =='Header missing in file'){
			     			$("#para").html("Header missing in file. Choose correct file for upload"); 
				     		 modal.style.display = "block"; 
				     	}
			     		else if(data == 'Invoice No is Already Exist'){
			     			$("#para").html("Duplicate record for upload. Choose correct file for upload"); 
			     			 modal.style.display = "block"; 
					     		window.open("resources/download/Vendor_Invoice.xlsx" , '_blank');    
				     	}
			     		else{
			     			$("#para").html("Vendor Invoice Data Uploaded Successfully"); 
				     		 modal.style.display = "block"; 
				     	}          
		          
		            }
		        });
		     }
	    });
	});
  $(document).ready(function(){
	    $('.openFinalPopup').on('click',function(){      
	        
	    	$("#contentHomeApp").load('km/upload');    	
	       
	    }); 
	    
	})
</script>
 
<style type="text/css">
input[type=button], input[type=submit], input[type=reset] {
	background-color: #f5e947;
	border-bottom-style: #f5e947;
	color: black;
	padding: 6px 12px;
	text-decoration: none;
	margin: 1px 45px;
	cursor: pointer;
} 
</style>

<style>
* {
  box-sizing: border-box;
}

.column {
  float: left;
  width: 10%;
  padding: 7px;
}

.columnUpload {
  float: left;
  width: 15%;
  padding: 1px;
  margin:10px 13px;
}

.columnSubmit {
  float: left;
  width: 10%;
  padding: 3px;
}

/* Clearfix (clear floats) */
.row::after {
  content: "";
  clear: both;
  display: table;
    
}

.row {
    margin-right: 15px;
    margin-left: 15px;
}
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: #fced19; /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}
.modal-content {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding: 0;
	border: 1px solid #black;
	width: 40%;
	height: 40%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}
</style>
</head>
<body>


<br>
<br>
<br>
<br>
<div class="submain_upload">
<div class="row">
			<div class="column">
				<label>Holiday Calendar</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="myFile" name="filename" >
			</div>
			
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignHoliday">
			</div>

			<div class="column">
				<label>Kiosk CMF</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="KioskFile" name="myFile">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupKioskCMF">	</div>
		</div>
<br>
<br>	
<div class="row">
			<div class="column">
				<label>Kiosk Details</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="CMFFile" name="myFile">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignCMFDetails">
			</div>

		
	    	<div class="column">
				<label>Branch Master</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="BMFile" name="filename">
			</div>
			
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignCBS">
			
	</div>
</div>
<br>
<br>	
<div class="row">
			<div class="column">
				<label>Vendor Invoice</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="InFile" name="myFile">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignInVen">
			</div>

		
			
	</div>
</div>	
	<div id="myModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<!-- <span class="close" onclick="cloesBox()">&times;</span> -->
			<p style="color: #000000; font-size: 10px; text-align: center;">
				<span style="text-align: center; color: #000000;"> <img
					src="resources/img/successTick.png"></span>
			</p>
			<p id="para" align="center"></p>
			<p align="center">
			<button class="openFinalPopup">OK</button>
		</div>
	</div>
	<div class="error-div"></div>
	



</body>
<input type="hidden" name="_csrf" value="<%=session.getAttribute("csrfToken")%>"> 
</html>