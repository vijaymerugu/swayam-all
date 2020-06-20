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
        var url= "km/userkioskmappingpopupselected?uname="+$('input[name=uname]').val()+"&array="+ array;
        $('.modal-body').load(url,function(){
            $('#myModal').modal({show:true});
        });
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