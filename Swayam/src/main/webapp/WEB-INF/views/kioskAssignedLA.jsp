<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>

<!--
<link rel="stylesheet"
	href="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.css">
 <script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script> -->
	
	<link rel="stylesheet" href="resources/css/ui-grid.min.css"/> 
	<script	src="resources/js/angular.1.5.6.min.js"></script>
	<script src="resources/js/ui-grid.min.js"></script>
 <script src="resources/js/users-la-app.js"></script> 
<link rel="stylesheet" href="resources/css/grid-style.css" />
<link rel="stylesheet" href="resources/css/body-page.css" />
<!--
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
	
	<link rel="stylesheet" href="<c:url value='resources/css/bootstrap.min.css' />"/>
	<!-- <script type="text/javascript" src="resources/js/jquery.min.js"/></script> -->
	<script type="text/javascript" src="resources/js/bootstrap.min.js"/></script>
<!--  <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->


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
</style>
</head>

<body class="modal-dialog modal-lg">

<div   style="font-weight: 400;">

<p style="color:#000000;font-size:15px;text-align: center;"><span style="color:#000000;text-align: center;font-weight: bold;">
Kiosk Assigned to : <c:out value="${kiosksList[0].username}" />
<input type="hidden" name="uname" value="${kiosksList[0].username}"/>
</span></p>
<p style="color:#000000;font-size:10px;text-align: center;"><span style="text-align: center;color:#808080">Select Kiosks to De-Map</span></p>

<div style="margin-left: 40px;margin-right: 40px">
        <table id="myTable">  
        <thead >  
          <tr style="top: 244px;left: 282px;width: 801px;height: 42px;background: #13A8E0 0% 0% no-repeat padding-box;opacity: 1;">  
        <th><input type="checkbox" id="selectAll" />&nbsp;Select ALL</th>  
            <th>Sr No.</th>  
            <th>Kiosk Id</th>  
            <th>Vendor</th>
            <th>Installation Status</th>   
          </tr>  
        </thead>  
        <tbody>  
        
        <c:forEach items="${kiosksList}" var="user" varStatus="status">
          <tr>  
            <td><input type="checkbox" name="check_list[]" value="${user.kioskId}"></td>  
            <td>${user.no}</td>  
            <td>${user.kioskId}</td>  
            <td>${user.vendor}</td>  
            <td>${user.installationStatus}</td>  
          </tr>  
          </c:forEach>
        </tbody>  
      </table>  
      
      <table>
      <tr>
      <td><input type="submit" value="Submit" class="openFinalPopup"></td>
      </tr>
      </table>
</div>
</div>


	<!-- The Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			`
			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" value="OK" class="close" data-dismiss="modal" style="background-color: black;color: white;">OK</button> -->
					<button type="button" class="btn" data-dismiss="modal" >OK</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<%-- <jsp:include page="kioskAssignedLA.jsp" /> --%>
				</div>

			</div>
		</div>
	</div>


<script>

$(document).ready(function(){
    $('.openFinalPopup').on('click',function(){
        
        var i=0;
        var array = [];
        
        var list = $("input[name='check_list[]']:checked").map(function () {
        	
        	array.push(this.value);
        	//array[i+1] = this.value;
            return this.value;
        }).get();
        
        //var url= "km/userkioskmappingpopupselected?check_list[]="+ $("input[name='check_list[]']:checked").val();
         var userName =$('input[name=uname]').val();
         var url= "km/userkioskmappingpopupselected?uname="+userName.trim().replace(/ /g,"+")+"&array="+ array;
        $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        });
    }); 

    
});



</script>

<script>
$(document).ready(function(){
    $('.btn').on('click',function(){      
    	$("#contentHomeApp").load('km/userList');    	
    	$('.modal-backdrop').remove();
    	$("body").css({"overflow":"visible"});
    }); 
    
});	
</script>


<script type="text/javascript">
$('#selectAll').click(function(e){
	var table= $(e.target).closest('table');
	$('td input:checkbox',table).attr('checked',e.target.checked);
});
</script>

</body>
 
</html>