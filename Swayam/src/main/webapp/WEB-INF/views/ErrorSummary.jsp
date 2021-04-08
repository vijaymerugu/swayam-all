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

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<p style="color:#000000;font-size:15px;text-align: center;">
<span style="color:#000000;text-align: center;font-weight: bold;">



<table id="myTable">  
          <tr>  
            <th>Error Code :</th>  
            <th>Error Count :</th> 
            <th>Description :</th>  
          </tr>  
          
        <c:if test="${not empty swayamTxnList}">
        <c:forEach items="${swayamTxnList}" var="swayamTxn" varStatus="status">
          <tr>  
             <td>${swayamTxn.errorCode}</td> 
            <td>${swayamTxn.errorCodeCount}</td>  
             <td>${swayamTxn.errorDesc}</td> 
             
          </tr>  
          </c:forEach>
          </c:if>
          
           <c:if test="${empty swayamTxnList}">
             <p style="text-align: center;">No Data Found</p>     
          </c:if> 
      </table>  






</body>
 
</html>