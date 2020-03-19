<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" oncontextmenu="return false;">
<!-- InstanceBegin template="/Templates/FinalTemplate.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<SCRIPT type="text/javascript">
    window.history.forward();
	function noBack() { window.history.forward(); }
</SCRIPT>
	             
<style>
table, td {
    border:none;
}
</style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- InstanceBeginEditable name="doctitle" -->
    <title>Add User Master</title>
    <!-- InstanceEndEditable -->
    <!-- Bootstrap Core CSS -->
  <!--   <link href="css/bootstrap.min.css" rel="stylesheet">

    Custom CSS
    <link href="css/style.css" rel="stylesheet">

    Custom Fonts
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

    Bootstrap-table
    <link rel="stylesheet" type="text/css" href="css/bootstrap-table.css"> -->
    
      <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />"/>
     <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap-table.css' />"/>
    
    <!-- InstanceBeginEditable name="head" -->
    <!-- InstanceEndEditable -->
</head>

<body>
<%@ include file = "home.jsp" %>
 <div id="wrapper">
<div id="page-wrapper">
        <!-- Navigation -->
            <div class="row">
                <div class="col-lg-12">
                    <!-- InstanceBeginEditable name="Body_title" -->
                    <h3 class="page-header">
						 <form:form method="post" action="/km/userList">
				     		  User List Master
							<INPUT Type="submit" VALUE="Back"  class="btn btn-black" style="margin-right: 20px" >
						</form:form>
                    </h3>
                    <!-- InstanceEndEditable -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <!-- InstanceBeginEditable name="Body_main" -->
            <div class="col-lg-12 main_content_body dashboard">
 <font  size="2" color="red"> <b><div id="txtHint"></div></b></font> 
                <div class="tableWrap">
                    <p>Fields Marked with <span style="color:#ff007a">* </span> are mandatory</p>
	
	
	<form:form role="form"  class="form-horizontal" id="usersBean" name="usersBean" action="/km/deleteUser" method="post"  modelAttribute="usersBean"  autocomplete="off"> 
                       <form:hidden path="checkAction" />
                         <%-- <form:hidden path="userId"  id="userId"/> --%>
                         
                          <form:hidden  class="form-control"  id="userId" path="userId" value="${usersBean.userId}"/>
                          <form:hidden  class="form-control"  id="createdBy" path="createdBy" value="${usersBean.createdBy}"/>
                          <form:hidden  class="form-control"  id="modifiedBy" path="modifiedBy" value="${usersBean.modifiedBy}"/>            
                             <form:hidden  class="form-control"  id="enabled" path="enabled" value="${usersBean.enabled}"/>
            
              <div class="form-group required">
                          <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="username">User Name</label>
                               </div>
                                
                               <div class="col-sm-6 padding_zero">
                                  <form:input  class="form-control" id="username" path="username" value="${usersBean.username}" placeholder="Enter User Name" maxlength="20" style="width: 100%;"/>
                               </div>
                         </div>
                         
                          <div class="col-sm-4">
                                <div class="col-sm-6">
                                    <label class="control-label" for="role">User Type</label>
                                </div>
                               
                             <div class="col-sm-6 padding_zero">
                                <form:select path="role" id="role" class="form-control select2" style="width: 100%;" >
                                <form:option value="0">Select</form:option>
                                  <c:forEach items="${userRoleList}" var="userRole">
                                    <form:option value="${userRole.role}">${userRole.roleDescription}</form:option>
                                 </c:forEach>
                                </form:select>
                                
                               </div>
						</div>
								 
								 
					         <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label1" for="firstName">First Name</label>
                               </div>
                               <div class="col-sm-6 padding_zero">
                                  <form:input class="form-control" id="firstName"   path="firstName"   value="${usersBean.firstName}"  placeholder="Enter First Name"  style="width: 100%;"/>
                               </div>
                         </div>
                        
                        
                        
                          
                           </div>
                           
             
             <!--      </div>----------end---------------------         --> 
             

           
            <div class="form-group required">
            
                        <div class="col-sm-4">
                                <div class="col-sm-6">
                                    <label class="control-label" for="lastName">Last Name</label>
                                </div>
                                 <div class="col-sm-6 padding_zero">
                                  <form:input  class="form-control" id="lastName" path="lastName" value="${usersBean.lastName}" placeholder="Enter Last Name" maxlength="20" style="width: 100%;"/>
                               </div>
							    </div>
                          
                          <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="address">address</label>
                               </div>
                               <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="address" path="address"  value="${usersBean.lastName}"  placeholder="Enter Address" maxlength="30" style="width: 100%;"/>
					             </div>
                         </div>  
                         
                          <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="addressline1">AddressLine1</label>    
                               </div>
                               <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="addressline1" path="addressline1" value="${usersBean.addressline1}"  placeholder="Enter AddressLine1" maxlength="30" style="width: 100%;"/>
					             </div>
                         </div>
                           
                    
                          
                         
              </div>
              
              <!--  div end -->
              
              
              <!--  --------------------------  div start  --------------------------------------------- -->
               
              <div class="form-group required">
                <div class="col-sm-4">
                                <div class="col-sm-6">
                                    <label class="control-label1" for="addressline2">addressLine2</label>
                                </div>
                               <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="addressline2" path="addressline2" placeholder="Enter AddressLine2" maxlength="30" style="width: 100%;"/>
					             </div>
                           </div>
                           
                         <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="gender">Gender</label>
                               </div>
                                <div class="col-sm-6 padding_zero">
									
									
				  <form:label path = "gender">Gender</form:label>
                  <form:radiobutton path = "gender" id="gender"   value = "Male" label = "Male" />
                  <form:radiobutton path = "gender" id="gender"  value = "Female" label = "Female" />
									</div>
                         </div>
                         
               
               <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="pincode">Pin Code</label>
                               </div>
								 <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="pincode" path="pincode" placeholder="Enter pinCode"  value="${usersBean.pincode}"  maxlength="6" style="width: 100%;"/>
					             </div>	  
                         </div>
               
               
                

                  </div>
                  
                 <!--  --------------------------   </div> End  --------------------------------------------- -->
                  
              
               <div class="form-group required">
               
               
                <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="city">City</label>
                               </div>
								 <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="city" path="city" placeholder="Enter City" value="${usersBean.city}"  maxlength="30" style="width: 100%;"/>
					             </div>	  
                         </div>
               
               
               
               
                 <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="state">State</label>
                               </div>
								 <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="state" path="state" placeholder="Enter State" value="${usersBean.state}"  maxlength="30" style="width: 100%;"/>
					             </div>	  
                         </div>
               
               
                 <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="mailId">Mail Id</label>
                               </div>
								 <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="mailId" path="mailId" placeholder="Enter mailId" value="${usersBean.mailId}"  maxlength="30" style="width: 100%;"/>
					             </div>	  
                         </div>  
                         
                         
                     
                           
                
                 <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="mobileNo">Mobile No</label>
                               </div>
								 <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="mobileNo" path="mobileNo" placeholder="Enter mobileNo"  value="${usersBean.mobileNo}"  maxlength="12" style="width: 100%;"/>
					             </div>	  
                         </div>
                         
                 <div class="col-sm-4">
                               <div class="col-sm-6">
                                   <label class="control-label" for="circle">Circle</label>
                               </div>
								 <div class="col-sm-6 padding_zero">
				                    <form:input  class="form-control" id="circle" path="circle" placeholder="Enter Circle"  value="${usersBean.circle}"  maxlength="50" style="width: 100%;"/>
					             </div>	  
                         </div>          
                           
                    <div class="col-sm-4">
                            <c:if test="${userBean.checkAction !='Edit' }">
                           <div  id = "instaIssuance1" style="display:none;">
                                    </div> 
                                    </c:if>
                                     <c:if test="${usersBean.checkAction =='Edit' }">
                                      <div  id = "instaIssuance1" style="display:none;">
                                    
                               </div>
			                        </c:if>
			                        
                          </div>
                           
                      
              <!----------------------------  <div> end ---------------------------------------->
                                      
                 <!-- ---------------------------------------------- <div> start -->
					
				<div class="form-group">
				<div class="col-sm-12">							 
						<input type="submit" name="Submit" value="Delete" class="btn btn-pink pull-right" style="margin-right: 10px">
						
				</div>
              </div>
               </div>   
               </form:form>
               
				</div>	
					</div>
                
                </div>
            <!-- InstanceEndEditable -->
            <!-- /.row -->
            <div class="row"> </div>
            <!-- /.row -->
</div>
</body>

  <script type="text/javascript" src="/resources/js/jquery.min.js"/></script>
      <script type="text/javascript" src="/resources/js/bootstrap.min.js"/></script>
     <script type="text/javascript" src="/resources/js/metisMenu.min.js"/></script>
      <script type="text/javascript" src="/resources/js/custom.js"/></script>
    <script type="text/javascript" src="/resources/js/bootstrap-table.js"/></script>
</html>