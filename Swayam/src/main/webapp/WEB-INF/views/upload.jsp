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

<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">

<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
 <link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css"> 


<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>  
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	-->
<link href="resources/css/menu.css" rel="stylesheet" type="text/css">	
<!-- <link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css"/>
 -->
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
    debugger;
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
        fd.append('myFile',files);
        console.log("2"+fd);
        $.ajax({
            url: 'uploadHolidayCalendar',
            type: 'post',
            data: fd,
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,

            success: function(data){
            	resp= data;  
                //  alert(resp) ;    	 	        	
            	// $("#para").html(resp);
	        	 $("#para").html(resp+" Data Uploaded Successfully"); 
	     		 modal.style.display = "block"; 
	     		window.open("resources/download/"+data+".xlsx" , '_blank');  
	     	//	alert(data1);         
          
            }
        });
    });
});
// === KioskDetails
 $(document).ready(function(){

    $(".openPopupKioskCMF").click(function(){
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
         var files = $('#KioskFile')[0].files[0];
        fd.append('KioskFile',files);
        console.log("2"+fd);
        $.ajax({
            url: 'uploadKioskDetails',
            type: 'post',
            data: fd,
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,

            success: function(data){
            	resp= data;  
            	//alert(resp) ;    	 	        	
	        	// $("#para").html(resp);
	        	 $("#para").html(resp+" Data Uploaded Successfully"); 
	     		 modal.style.display = "block"; 
	     		window.open("resources/download/"+data+".xlsx" , '_blank');  
	     		//alert(data1);          
          
            }
        });
    });
}); 
// 
 $(document).ready(function(){

    $(".openPopupAssignCMFDetails").click(function(){
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
         var files = $('#CMFFile')[0].files[0];
        fd.append('CMFFile',files);
        console.log("2"+fd);
        $.ajax({
            url: 'uploadKioskCMF',
            type: 'post',
            data: fd,
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,

            success: function(data){
            	resp= data;  
            	// alert(resp) ;    	 	        	
	        	// $("#para").html(resp);
	        	 $("#para").html(resp+" Data Uploaded Successfully"); 
	     		 modal.style.display = "block"; 
	     		window.open("resources/download/"+data+".xlsx" , '_blank');  
	     		// alert(data1);   
	        	            
          
            }
        });
    });
});
 
  $(document).ready(function(){

    $(".openPopupAssignCBS").click(function(){
    	var modal = document.getElementById("myModal");
        var fd = new FormData();
         var files = $('#BMFile')[0].files[0];
        fd.append('BMFile',files);
        console.log("2"+fd);
        $.ajax({
            url: 'uploadCBSbrhm',
            type: 'post',
            data: fd,
            enctype: 'multipart/form-data',
            contentType: false,
            processData: false,

            success: function(data){
            	resp= data;  
            //	alert(resp) ;    	 	        	
	        	// $("#para").html(resp);
	        	 $("#para").html(resp+" Data Uploaded Successfully"); 
	     		 modal.style.display = "block"; 
	     		window.open("resources/download/"+data+".xlsx" , '_blank');  
	     		// alert(data1);         
          
            }
        });
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
	margin: 4px 2px;
	cursor: pointer;
} -->
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
<div class="submain">
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
</html>