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

<body>
<table>
<tr>
<td style="color:#000000;font-size:15px;" align="right"><b>
De-Map Kiosk Assigned to : <c:out value="${kiosksList[0].username}" />
</b>
</td>
<tr>
<td>  <img src="/resources/img/successTick.png"></td>
</tr>
</tr>
<tr><td style="color:#808080;font-size:10px;" align="right">Following Kiosks has been De-Mapped Successfully</td></tr>
</table>

<table id="myTable">  
        <thead>  
          <tr>  
             
            <th>Kiosk Id</th>  
            <th>Vendor</th>
            <th>Installation Status</th>   
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