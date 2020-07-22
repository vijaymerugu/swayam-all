<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">
<head>
<SCRIPT type="text/javascript">
    window.history.forward();
	function noBack() { window.history.forward(); }
</SCRIPT>
<META HTTP-EQUIV="X-Frame-Options" CONTENT="SAMEORIGIN"> 

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- InstanceBeginEditable name="doctitle" -->
    <title>User Master</title>
    
    <link rel="stylesheet" href="<c:url value='resources/css/style.css' />"/>
    <link rel="stylesheet" href="<c:url value='resources/css/font-awesome.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='resources/css/bootstrap.min.css' />"/>
     <link rel="stylesheet" href="<c:url value='resources/css/bootstrap-table.css' />"/>
 
</head>

<body>
<%@ include file = "home.jsp" %>
    <div id="wrapper">
            <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <!-- InstanceBeginEditable name="Body_title" -->
                     <h3 class="page-header">
                     <form:form name="addUser" action="addUser" method="post" >                    
                         User Master
	                   
       	             </form:form></h3>
                    <!-- InstanceEndEditable -->
                </div>
                 <!-- /.col-lg-12 -->
                 
          <div>
                 
                <div class="col-lg-12">
                    <!-- InstanceBeginEditable name="Body_title" -->
                     <h3 class="page-header">
                     <form:form name="userMapping" action="userMapping" method="post" >                    
                          
	                  <INPUT Type="submit" VALUE="Back"  class="btn btn-black" style="margin-right: 20px" >
       	             </form:form></h3>
                </div>
                 <!-- /.col-lg-12 -->
            </div>
                       <div class="col-lg-12">
                     <h3 class="page-header">
                     <form:form  action="searchUser" method="post" >                    
                         User Name
                         <input type="text" name="userName" id="userName" placeholder="Enter User Name" maxlength="20"   style="width:11%;" />
	                   <button  style="width:10%;">Search User</button>
       	             </form:form></h3>
                </div>
            <!-- InstanceBeginEditable name="Body_main" -->
            <div class="col-lg-12 main_content_body">
		            <font color="green" style="text-align: center;"><b> <p id="myText">${successMessage}</p></b></font>
	             	<font color="Red" style="text-align: center;"><b> <p id="myText">${errorMessage}</p></b></font>
	             	 <div class="row">
                <div class="tableWrap">
	                                                       <h4>User List</h4>
                    <table data-toggle="table" data-sort-name="Date" data-sort-order="desc" data-search="true" data-pagination="true">
				<thead>
					<tr>
						<!-- <th data-field="userId" data-sortable="true">UserId:</th>
						<th data-field="delete" data-sortable="true">User:</th> -->
						<th data-field="username" data-sortable="true">UserName:</th>
						<th data-field="role" data-sortable="true">UserType:</th>
						<!-- <th data-field="kioskId" data-sortable="true">KioskId:</th> -->
						<th data-field="firstName" data-sortable="true">FirstName:</th>
						<th data-field="lastName" data-sortable="true">LastName:</th>
						<th data-field="address" data-sortable="true">Address:</th>
						<th data-field="addressline1" data-sortable="true">AddressLine1:</th>
						<th data-field="addressline2" data-sortable="true">AddressLine2:</th>
						<th data-field="gender" data-sortable="true">Gender:</th>
						<th data-field="pincode" data-sortable="true">PinCode:</th>
						<th data-field="city" data-sortable="true">City:</th>
					    <th data-field="state" data-sortable="true">State:</th>
						<th data-field="mailId" data-sortable="true">MailId:</th>
						<th data-field="mobileNo" data-sortable="true">MobileNo:</th>
						<th data-field="circle" data-sortable="true">Circle:</th>
						<th data-field="creationDate" data-sortable="true">CreationDate:</th>
						<th data-field="createdBy" data-sortable="true">CreatedBy:</th>
						<th data-field="modifiedDate" data-sortable="true">ModifiedDate:</th>
						<th data-field="modifiedBy" data-sortable="true">ModifiedBy:</th>
					</tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${usersList}" var="user" varStatus="status">
						<tr>	
							
							<td>${user.username}</td>
							<td>${user.role}</td>
							<%-- <td>${user.kioskId}</td> --%>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.address}</td>
							<td>${user.addressline1}</td>
							<td>${user.addressline2}</td>
							<td>${user.gender}</td>
							<td>${user.pincode}</td>
							<td>${user.city}</td>
							<td>${user.state}</td>
							<td>${user.mailId}</td>
							<td>${user.mobileNo}</td>
							<td>${user.circle}</td>
							<td>${user.createdDate}</td>
							<td>${user.createdBy}</td>
							<td>${user.modifiedDate}</td>
							<td>${user.modifiedBy}</td>
						</tr>
					</c:forEach>
                        </tbody>
                    </table>
                    </div>
                </div>
                </div>
            </div>
            <!-- InstanceEndEditable -->
            <!-- /.row -->
            <div class="row"> </div>
            <!-- /.row -->
            </div>
        </div>
        <!-- /#page-wrapper -->
    <!-- /#wrapper -->
    <!-- jQuery --><!-- 
    <script src="js/jquery.min.js"></script>

    Bootstrap Core JavaScript
    <script src="js/bootstrap.min.js"></script>

    Metis Menu Plugin JavaScript
    <script src="js/metisMenu.min.js"></script>

    Custom Theme JavaScript
    <script src="js/custom.js"></script>

    bootstrap-table JavaScript
    <script src="js/bootstrap-table.js"></script> -->
    
    <script type="text/javascript">
    
    function ConfirmDelete()
    {
      var x = confirm("Are you sure you want to delete?");
      if (x)
          return true;
      else
        return false;
    }
    
    </script>
    
    
      <script type="text/javascript" src="resources/js/jquery.min.js"/></script>
      <script type="text/javascript" src="resources/js/bootstrap.min.js"/></script>
     <script type="text/javascript" src="resources/js/metisMenu.min.js"/></script>
      <script type="text/javascript" src="resources/js/custom.js"/></script>
    <script type="text/javascript" src="resources/js/bootstrap-table.js"/></script>
    
    <!-- InstanceBeginEditable name="js" --> <!-- InstanceEndEditable -->
</body>
<sec:csrfInput />  
<!-- InstanceEnd -->
</html>