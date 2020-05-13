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

<script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
 <link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css"> 


<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>  
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script>
<script	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	-->
<link href="/resources/css/menu.css" rel="stylesheet" type="text/css">	
<!-- <link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css"/>
 -->
<script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/lodash.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/jszip.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/excel-builder.dist.js"></script>  
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-aria.js"></script>


<script> 

$(document).ready(function(){
    debugger;
	$('.openPopupAssign').on('click',function(){
    	alert("in popup");

        $.ajax({
        	type:"POST",
        	url:"/uploadKiosk",
        	////data:formData,
            success: function(data){

                alert(data);
        	     resp=data;       	 	     
        	 
         }
        });

         
        /* $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        }); */
    }); 
});



$(document).ready(function(){
    debugger;
	$('.openPopupAssignCBS').on('click',function(){
    	alert("in popup");

        $.ajax({
        	type:"POST",
        	url:"/uploadCBSbrhm",
        	////data:formData,
            success: function(data){

                alert(data);
        	     resp=data;       	 	     
        	 
         }
        });

         
        /* $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        }); */
    }); 
});



$(document).ready(function(){
    debugger;
	$('.openPopupAssignHoliday').on('click',function(){
    	alert("in popup"); 

        $.ajax({
        	type:"POST",
        	url:"/uploadHolidayCalendar",
        	////data:formData,
            success: function(data){

                alert(data);
        	     resp=data;
        	     $('.modal-body').load(url,function(){
        	            $('#myModal').modal({show:true});
        	     });       	 	     
        	 
         }
        });

         
        $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        });
    }); 
});

$(document).ready(function(){
    debugger;
	$('.openPopupAssignCMF ').on('click',function(){
    	alert("in popup");

        $.ajax({
        	type:"POST",
        	url:"/uploadKioskCMF",
        	////data:formData,
            success: function(data){

                alert(data);
        	     resp=data;       	 	     
        	 
         }
        });

         
        /* $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        }); */
    }); 
});

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
</style>
</head>
<body>


<br>
<br>
<br>
<br>
<!-- By Pankul 28-04-2020-----------STARTS--------- -->
	<div class="row">
		<%-- <form action="uploadCBSbrhm" method="post"> --%>
			<div class="column">
				<label>Branch Master</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="myFile" name="filename">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignCBS">
			</div>
		<%-- </form> --%>

		
		<%-- <form action="uploadHolidayCalendar" method="post"> --%>
			<div class="column">
				<label>Holiday Calendar</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="myFile" name="filename">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignHoliday">
			</div>
		<%-- </form> --%>
	</div>
	

<br>
<br>

	<div class="row">
		<%-- <form action="uploadKiosk" method="post"> --%>
      
			<div class="column">
				<label>Kiosk Details</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="myFile" name="myFile">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignHoliday">
			</div>

			<!-- By Pankul 28-04-2020-----------STARTS--------- -->
			<%-- <c:out value="${kioskUploadStatus}"/> --%>
			<!-- -------By Pankul END-------------------------- -->
		<%-- </form> --%>

		

		<%-- <form action="uploadKioskCMF" method="post"> --%>
			<div class="column">
				<label>Kiosk CMF</label>
			</div>
			<div class="columnUpload">
				<input type="file" id="myFile" name="myFile">
			</div>
			<div class="columnSubmit">
				<input type="submit" value="UPLOAD" class="openPopupAssignCMF">
			</div>
		<%-- </form> --%>
	</div>
	<!-- -------By Pankul END-------------------------- -->




	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						Success
					</h4>
					
				</div>
				<div class="modal-body">
					<%-- <jsp:include page="kioskAssignedLA.jsp" /> --%>
				</div>

			</div>
		</div>
	</div>


</body>
</html>