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

.modal-ku {
  width: 750px;
  margin: auto;
}
.modal-header {
    border-bottom: 0 none;
}

.modal-footer {
    border-top: 0 none;
}

</style>
</head>

<body class="modal-dialog modal-lg">


<p style="color:#000000;font-size:15px;text-align: center;"><span style="color:#000000;text-align: center;font-weight: bold;">

De-Map Kiosk Assigned to : <c:out value="${kiosksList[0].username}" />

</span></p>

<p style="color:#000000;font-size:10px;text-align: center;">
<span style="text-align: center;color:#000000;">
  <img src="/resources/img/successTick.png"></span></p>

<p style="color:#000000;font-size:12px;text-align: center;">
<span style="text-align: center;color:#000000;">Following Kiosks has been De-Mapped Successfully</span></p>


<table id="myTable">  
        <thead>  
          <tr>  
             
            <th colspan="1">Kiosk Id</th>  
            <th colspan="1">Vendor</th>
            <th colspan="1">Installation Status</th>   
          </tr>  
        </thead>  
        <tbody>  
        <c:forEach items="${kiosksList}" var="user" varStatus="status">
          <tr>  
             
            <td>${user.kioskId}</td>  
            <td>${user.vendor}</td>  
            <td>${user.installationStatus}</td>  
          </tr>  
          </c:forEach>
         
          
        </tbody>  
      </table>  






</body>
</html>