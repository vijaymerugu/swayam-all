<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ticket-center</title>

<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">   
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/dt/jq-2.1.4,jszip-2.5.0,pdfmake-0.1.18,dt-1.10.9,af-2.0.0,b-1.0.3,b-colvis-1.0.3,b-html5-1.0.3,b-print-1.0.3,se-1.0.1/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/r/dt/jq-2.1.4,jszip-2.5.0,pdfmake-0.1.18,dt-1.10.9,af-2.0.0,b-1.0.3,b-colvis-1.0.3,b-html5-1.0.3,b-print-1.0.3,se-1.0.1/datatables.min.js"></script>



<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<style type="text/css">
.form-control:focus {
    outline: 0 !important;
    border-color: initial;
    box-shadow: none;
}
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
	<table bgcolor="red" align="center" frame="box">
		<tr>
			<td colspan="90"><a
				href="${pageContext.request.contextPath}/tcticket">Ticket-center</a></td>
			<td colspan="90"><a href="tickeTcentre.jsp">Terminal Status</a></td>
			<td colspan="90"><a href="${pageContext.request.contextPath}/history">Ticket History</a></td>
			<td colspan="90"><a
				href="${pageContext.request.contextPath}/manualTicket">ManugalCallLog</a></td>
			<td colspan="90"><a href="ticketCentre.jsp">DownTime</a></td>
		</tr>
	</table>
	<br>
	<h3 align="center" style="color: lime">Ticket Center</h3>

	<form:form modelAttribute="ticketCmd" action="/ticket" method="post">
		<%-- <table align="center" frame="above">
			<tr>
				<td><label align="right">Bank</label></td>
				<td><form:select path="bank">
				         <c:forEach var="mlist" items="${mList}">
						<form:option value="bank">${mList.bank}</form:option></c:forEach>
					</form:select></td>
				<td><label>Logcation Type</label></td>
				<td><form:select path="locationType">
				       <c:forEach var="mlist" items="${mList}">
						<form:option value="${mList.circle}" label="Select locationType" /></c:forEach>
					</form:select></td>
				<td><label align="right">Queue Type</label></td>
				<td><form:select path="queueType">
						<form:option value="-" label="Select Queue Type" />
						<form:options items="${queueType}" />
					</form:select></td>
				<td><label>Status</label></td>
				<td><form:select path="status">
						<form:option value="-" label="Select status" />
						<form:options items="${status}" />
					</form:select></td>
			</tr>
			<tr>
				<td><label>Sate</label></td>
				<td><form:select path="state">
						<form:option value="-" label="Select state" />
						<form:options items="${state}" />
					</form:select></td>
				<td><label align="right">Site Type</label></td>
				<td><form:select path="siteType">
						<form:option value="-" label="Select Site Type" />
						<form:options items="${siteType}" />
					</form:select></td>
				<td><label>Call Type</label></td>
				<td><form:select path="callType">
						<form:option value="-" label="Select Bank" />
						<form:options items="${callType}" />
					</form:select></td>
				<td><label>Kiosk Status</label></td>
				<td><form:select path="kioskStatus">
						<form:option value="-" label="Select Kiosk Status" />
						<form:options items="${kioskStatus}" />
					</form:select></td>
			</tr>
			<tr>
				<td><label>Circle</label></td>
				<td><form:select path="circle">
						<form:option value="-" label="Select Bank" />
						<form:options items="${circle}" />
					</form:select></td>
				<td><label>Category</label></td>
				<td><form:select path="category">
						<form:option value="-" label="Select locationType" />
						<form:options items="${locationType}" />
					</form:select></td>
				<td><label>Sub Category </label></td>
				<td><form:select path="subCategory">
						<form:option value="-" label="Select Bank" />
						<form:options items="${subCategory}" />
					</form:select></td>
				<td><label>Assigned To</label></td>
				<td><form:select path="assignedTo">
						<form:option value="-" label="Select Assigned To" />
						<form:options items="${assignedTo}" />
					</form:select></td>
			</tr>
		</table>
		<br>
 --%>	</form:form>
	
	
	
	
	
	
	
	
<%-- 		
	<div class="w3-container">
		<table>
		
                              
			
       <td style="width: 17%">
                                    
           <div id="MainContentPlaceHolder_Panel2" style="background-color:#F7F6F3;text-align:left;">
				<fieldset>
					<legend>
						Severity
					</legend>
                                  <table width="90%" border="0">
                                            <tr>
                                          
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">High</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">Medium</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">Low</span>
                                                </td>
                                                <td style="width: 20%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">Total</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <a id="hiegh"  href="${pageContext.request.contextPath}/callLogDisplay?type=countHiegh "  style="color:Red;font-weight:bold;">${countHiegh}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AMedium" href="${pageContext.request.contextPath}/callLogDisplay?type=countMedium"  style="color:#005D7E;font-weight:bold;">${countMedium}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_ALow" href="${pageContext.request.contextPath}/callLogDisplay?type=countLow" style="color:#A2460E;font-weight:bold;">${countLow}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_MOPenCalls" href="${pageContext.request.contextPath}/callLogDisplay?type=countTotal ">${countTotal}</a>
                                                </td>
                                            </tr>
                                        </table>
                                    
				</fieldset>
			</div>
                   </td>
                                
                                
                                <td style="width: 30%">
                                    <div id="MainContentPlaceHolder_Panel3" style="background-color:#F7F6F3;text-align:left;">
				<fieldset>
					<legend>
						All Tickets
					</legend>
                                        <table width="100%" border="0">
                                            <tr>
                                            
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066"><1h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">1h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">2h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">4h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">8h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">1d</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">3d</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">5d</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">1w</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">Total</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                  <a id="MainContentPlaceHolder_ALT1H" href="${pageContext.request.contextPath}/allTickets?countTypes=oneHoursLessCount "  >${oneHoursLessCount}</a>
                                                </td>
                                                <td align="center">
                                                 <a id="MainContentPlaceHolder_AGT1H" href="${pageContext.request.contextPath}/allTickets?countTypes=oneHoursCount " >${oneHoursCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT2H" href="${pageContext.request.contextPath}/allTickets?countTypes=twoHoursCount " >${twoHoursCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT4H" href="${pageContext.request.contextPath}/allTickets?countTypes=fourHoursCount "  >${fourHoursCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT8H" href="${pageContext.request.contextPath}/allTickets?countTypes=eightHoursCount "  >${eightHoursCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT1D" href="${pageContext.request.contextPath}/allTickets?countTypes=oneDaysCount "  >${oneDaysCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT3D" href="${pageContext.request.contextPath}/allTickets?countTypes=threeDaysCount "  ">${threeDaysCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT5D" href="${pageContext.request.contextPath}/allTickets?countTypes=fiveDaysCount "  ">${fiveDaysCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AGT1W" href="${pageContext.request.contextPath}/allTickets?countTypes=oneWeekCount "  >${oneWeekCount}</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_AOpenTickets" href="${pageContext.request.contextPath}/allTickets?countTypes=totalCount "  >${totalCount}</a>
                                                </td>
                                            </tr>
                                        </table>
                                    
				</fieldset>
			</div>
                                </td>
                                
                                <td id="MainContentPlaceHolder_tdFollowup" style="width: 25%">
                                    <div id="MainContentPlaceHolder_Panel1" style="background-color:#F7F6F3;text-align:left;">
				<fieldset>
					<legend>
						My Follow-up
					</legend>
                                        <table width="100%" border="0">
                                            <tr>
                                           
                                           
                                                <td style="width: 10%" align="center">
                                                
                                                    <span style="font-size: small; font-weight: bold; color: #000066">-2h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">-1h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">0h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">15m</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">30m</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">1h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">2h</span>
                                                </td>
                                                <td style="width: 10%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">4h</span>
                                                </td>
                                                <td style="width: 20%" align="center">
                                                    <span style="font-size: small; font-weight: bold; color: #000066">Total</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FLT2H" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FLT2H&#39;,&#39;&#39;)" style="color:Red;">1</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FLT1H" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FLT1H&#39;,&#39;&#39;)" style="color:Red;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FET0H" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FET0H&#39;,&#39;&#39;)" style="color:Red;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FGT15M" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FGT15M&#39;,&#39;&#39;)" style="color:#005D7E;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FGT30M" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FGT30M&#39;,&#39;&#39;)" style="color:#005D7E;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FGT1H" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FGT1H&#39;,&#39;&#39;)" style="color:#A2460E;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FGT2H" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FGT2H&#39;,&#39;&#39;)" style="color:#A2460E;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FGT4H" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FGT4H&#39;,&#39;&#39;)" style="color:#A2460E;">0</a>
                                                </td>
                                                <td align="center">
                                                    <a id="MainContentPlaceHolder_FOpenTickets" href="javascript:__doPostBack(&#39;ctl00$MainContentPlaceHolder$FOpenTickets&#39;,&#39;&#39;)">1</a>
                                                </td>
                                            </tr>
                                        </table>
                                    
				</fieldset>
			</div>
                                </td>
			
                        </table>
                    
	</div>
	<br> --%>
	
	<div class="container" style="top: 152px;left: 15px;width: 1336px;height: 130px;background: #FFFFFF 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #8D8D8D29;opacity: 1;">
		<div class="row">
			<div class="col-md-8">
			<table>
			<thead style="font-size: 12px;"  >
			<tr >
			<th colspan="2" align="center">AGEING OF TICKETS</th>
			</tr>

			</thead>
			<tbody style="font-size: 10px;">
			<tr>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			 <a href="${pageContext.request.contextPath}/allTickets?countTypes=oneHoursLessCount ">${oneHoursLessCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;"  width="10%">
			 <a href="${pageContext.request.contextPath}/allTickets?countTypes=oneHoursCount " >${oneHoursCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			<a  href="${pageContext.request.contextPath}/allTickets?countTypes=twoHoursCount " >${twoHoursCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			 <a  href="${pageContext.request.contextPath}/allTickets?countTypes=fourHoursCount "  >${fourHoursCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			 <a  href="${pageContext.request.contextPath}/allTickets?countTypes=eightHoursCount "  >${eightHoursCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			    <a  href="${pageContext.request.contextPath}/allTickets?countTypes=oneDaysCount "  >${oneDaysCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			  <a  href="${pageContext.request.contextPath}/allTickets?countTypes=threeDaysCount ">${threeDaysCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;"  width="10%">
			  <a  href="${pageContext.request.contextPath}/allTickets?countTypes=fiveDaysCount ">${fiveDaysCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			  <a  href="${pageContext.request.contextPath}/allTickets?countTypes=oneWeekCount "  >${oneWeekCount}</a>
			</td>
			<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
			  <a href="${pageContext.request.contextPath}/allTickets?countTypes=totalCount " >${totalCount}</a>
			</td>
			
			</tr>
			</tbody>
			
			<thead style="font-size: 10px;">
			<tr>
			<th colspan="1" align="center"><1 hours</th>
			<th colspan="1" align="center">1-2 hours</th>
		    <th colspan="1" align="center">2-3 hours</th>
		    <th colspan="1" align="center">3-4 hours</th>
		    <th colspan="1" align="center">4-8 hours</th>
		    <th colspan="1" align="center">1 days</th>
		    <th colspan="1" align="center">3 days</th>
		    <th colspan="1" align="center">5 days</th>
		    <th colspan="1" align="center">1 week</th> 
		     <th colspan="1" align="center">total</th> 
			</tr>
			</thead>
			
			
			</table>
			</div>
			
			<div class="col-md-4">
			<table>
			 <thead>
				<tr>
					<th colspan="3" align="center">SEVERITY OF TICKETS</th>
			      </tr>
			  </thead>
					
				<tbody style="color: #13A8E0;font-size: 20px;font-weight: bold;width: 10px;">
				<tr>
				
				<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
				  <a id="hiegh"  href="${pageContext.request.contextPath}/callLogDisplay?type=countHiegh " >${countHiegh}</a>
				</td>
				<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
				 <a id="countMedium" href="${pageContext.request.contextPath}/callLogDisplay?type=countMedium" >${countMedium}</a>
				</td>
				<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
				  <a id="countLow" href="${pageContext.request.contextPath}/callLogDisplay?type=countLow">${countLow}</a>
				</td>
				<td style="color: #13A8E0;font-size: 20px;font-weight: bold;" width="10%">
				<a id="countTotal" href="${pageContext.request.contextPath}/callLogDisplay?type=countTotal ">${countTotal}</a>
				</td>
			    </tr>
					</tbody>
					
			<thead style="font-size: 10px;">
				<tr>
					<th colspan="1" align="center">High</th>
					<th colspan="1" align="center">Medium</th>
					<th colspan="1" align="center">Low</th>
					<th colspan="1" align="center">total</th>
				</tr>
			  </thead>
					</table>
					</div>
			
			</div>
			
			
			
			
			</div>
	
	<br>
	<div class="container" style="border: 1px solid #FFFFFF; top: 355px;left: 15px;width: 1336px;height: 519px;background: #FFFFFF 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #8D8D8D29;opacity: 1;">


<div class="row">
			<div class="col-md-5">	
			<div class="input-group">						
							<input type="text" id="demo" placeholder="Enter Username, First Name, Last Name, Mail Id, Circle etc." style="font-size: 12px"  class="form-control py-2 border-right-0 border">
									
           <span class="input-group-append">
                    <div class="input-group-text bg-transparent"><i class="fa fa-search"></i></div>
                </span>
                </div>
						</div>
		</div>
		


		<table class="table" id="myTable">
		<thead>
			<tr>
						<th>UserName</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Gender</th>
						<th>PF ID</th>
						<th>Role</th>						
						<th>MailId:</th>
						<th>MobileNo:</th>
						<th>Circle:</th>						
						<th></th>
						<th></th>	
			</tr>
			   </thead>
    <tbody id="test">
			<tr>
			
			<c:if test="${not empty usersList}">
			
			<c:forEach items="${usersList}" var="user" varStatus="status">
						<tr>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.gender}</td>
							<td>${user.userId}</td>
							<td>${user.role}</td>	
							<td>${user.mailId}</td>
							<td>${user.mobileNo}</td>
							<td>${user.circle}</td>							
							
							<td>
			             	<form:form name="edituser" action="/km/editUserMaster"  method="post">		
							 <a href="#" onclick="$(this).closest('form').submit();">EDIT</a> 
						       <input type="hidden"   name="userId" value="${user.userId}" />  
								<input type="hidden" name="username"  value="${user.username}" />
								<input type="hidden" name="role" value="${user.role}" />
								<input type="hidden" name="firstName" value="${user.firstName}" />
								<input type="hidden" name="lastName" value="${user.lastName}" />
								<input type="hidden" name="address" value="${user.address}" />
								<input type="hidden" name="addressline1" value="${user.addressline1}" />
								<input type="hidden" name="addressline2" value="${user.addressline2}" />
								<input type="hidden" name="gender" value="${user.gender}" />
								<input type="hidden" name="pincode" value="${user.pincode}" />
								<input type="hidden" name="city" value="${user.city}" />
								<input type="hidden" name="state" value="${user.state}" /> 
								<input type="hidden" name="mailId" value="${user.mailId}" /> 
								<input type="hidden" name="mobileNo" value="${user.mobileNo}" />
								<input type="hidden" name="circle" value="${user.circle}" />
								<input type="hidden" name="createdDate" value="${user.createdDate}" />
								<input type="hidden" name="createdBy" value="${user.createdBy}" />	
								<input type="hidden" name="modifiedDate" value="${user.modifiedDate}" />	
								<input type="hidden" name="modifiedBy" value="${user.modifiedBy}" />
								<input type="hidden" name="enabled" value="${user.enabled}" />
								</form:form>
							</td>
							
							<td>
			             	<form:form name="deleteUser" action="/km/deleteUserMaster"  method="post">		
							 <a href="#" onclick="$(this).closest('form').submit();" >DELETE</a> 
						         <input type="hidden"   name="userId" value="${user.userId}" />  
								<input type="hidden" name="username"  value="${user.username}" />
								<input type="hidden" name="role" value="${user.role}" />
								<input type="hidden" name="firstName" value="${user.firstName}" />
								<input type="hidden" name="lastName" value="${user.lastName}" />
								<input type="hidden" name="address" value="${user.address}" />
								<input type="hidden" name="addressline1" value="${user.addressline1}" />
								<input type="hidden" name="addressline2" value="${user.addressline2}" />
								<input type="hidden" name="gender" value="${user.gender}" />
								<input type="hidden" name="pincode" value="${user.pincode}" />
								<input type="hidden" name="city" value="${user.city}" />
								<input type="hidden" name="state" value="${user.state}" /> 
								<input type="hidden" name="mailId" value="${user.mailId}" /> 
								<input type="hidden" name="mobileNo" value="${user.mobileNo}" />
								<input type="hidden" name="circle" value="${user.circle}" />
								<input type="hidden" name="createdDate" value="${user.createdDate}" />
								<input type="hidden" name="createdBy" value="${user.createdBy}" />	
								<input type="hidden" name="modifiedDate" value="${user.modifiedDate}" />	
								<input type="hidden" name="modifiedBy" value="${user.modifiedBy}" />
								<input type="hidden" name="enabled" value="${user.enabled}" />  
								</form:form>
							</td>
						
						</tr>
					</c:forEach>
			
			</c:if>
			
		
			
			
			
	
			</tbody>
		</table>
	</div>


<script>
$(document).ready(function(){
    $("#demo").on("keyup", function() {
       var value = $(this).val().toLowerCase();
       $("#test tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
       });
    });
   
 });
</script>
</body>
</html>
