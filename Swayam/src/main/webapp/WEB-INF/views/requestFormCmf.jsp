<!DOCTYPE html>
<html lang="en">
<head>
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<script src="/resources/js/jquery.3.4.1.min.js"></script>
<script src="/resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/ui-grid.4.8.3.min.css">

<script
	src="//cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.min.js"></script>
<script src="/resources/js/users-app.js"></script>
<script	src="/resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="/resources/css/grid-style.css"/>
<link rel="stylesheet" href="/resources/css/body-page.css"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> 
<script src="https://cdn.rawgit.com/angular-ui/bower-ui-grid/master/ui-grid.js"></script> 
<link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css"/>

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

</head>
<body>
<div class="main" ng-app="app" id="appId">

<form action="/hm/addRequest">
<table class="table1">
<tr><td>Branch Code:<input type="text" name="branchCode" id="branchCode"/></td>
	<td>Kiosk Id:<input type="text" name="kioskId" id="kioskId"/></td>
</tr>
<tr><td>Vendor:<input type="text" name="vendor" id="vendor"/></td>
	<td>Type Of Request:
	<select id="typeOfRequest" name="typeOfRequest" class="form-control select2" style="width: 100%;" >
                                <option value="0">Select</option>                                  
                                    <option value="Activation">Activation</option>
                                    <option value="Deactivation">Deactivation</option>                                 
                                </select>
                                </td>
</tr>
<tr><td>Category:<input type="text" name="category" id="category"/>
	<%-- <select id="category" name="category" class="form-control select2" style="width: 100%;" >
                                <option value="0">Select</option>
                                  <c:forEach items="${usersList}" var="usr">
                                    <option value="${usr.pfId}">${usr.pfId}</option>
                                 </c:forEach>
                                </select> --%></td>
	<td>Sub-Category:<input type="text" name="subCategory" id="subCategory"/>
	<%-- <select id="subCategory" name="subCategory" class="form-control select2" style="width: 100%;" >
                                <option value="0">Select</option>
                                  <c:forEach items="${usersList}" var="usr">
                                    <option value="${usr.pfId}">${usr.pfId}</option>
                                 </c:forEach>
                                </select> --%>
                                </td>
</tr>
<tr><td>Subject:<input type="text" name="subject" id="subject"/></td>
	<td>Comments:<textarea name="comments" id="comments"></textarea>
</td>
</tr>

<tr><td><input type="submit" value="ADD">
<input type="reset" value="CANCEL">

</td>
	

</tr>

</table>
</form>
</div>
</body>
</html>