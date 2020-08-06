<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<style>
#myTable {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#myTable td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#myTable tr:nth-child(even){background-color: #f2f2f2;}

#myTable tr:hover {background-color: #ddd;}

#myTable th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #00BFFF;
  color: white;
}
.content {  
  margin: auto;
}
</style>
</head>

<body>
<div class="content">
<table>


<tr>
<td align="center" style="text-align:center">  <img src="resources/img/successTick.png"></td>
</tr>
<tr><td style="color:#000000;font-size:15px;" align="center" style="text-align:center">CMF has been assigned Successfully</td></tr>
<tr>
<td align="center"> <button class="openBackPopup" style="text-align:center">OK</button></td>
</tr>
</table>
</div>
<script>
$(document).ready(function(){
    $('.openBackPopup').on('click',function(){      
        
    	$("#contentHomeApp").load('km/kioskManagement');    	
    	$('.modal-backdrop').remove();
    	$("body").css({"overflow":"visible"});
    }); 
    
});	
</script>
</body>
 
</html>