<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<!-- <link rel="stylesheet" href="resources/css/ui-grid.4.8.3.min.css"> -->

<script
	src="resources/js/ui-grid.min.js"></script>
<script src="resources/js/billing.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/style.css">

<link rel="stylesheet" href="resources/css/bootstrap.min.css"> 
<!-- <script src="resources/js/ui-grid.js"></script> 
<link rel="stylesheet" href="resources/css/ui-grid.css" type="text/css"/> -->

<link rel="stylesheet" href="resources/css/font-awesome.min.css">
 
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="resources/js/jquery-1.12.4.js"></script>
<script src="resources/js/jquery-ui.js"></script> -->

<script src="resources/js/a076d05399.js"></script>

<script src="resources/js/angular-route.min.js"></script>


<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>



    
   <!-- <link rel="stylesheet" href="resources/css/ui-grid.group.min.css">


<script src="resources/js/billing.js"></script>
<script	src="resources/js/angular.1.5.6.min.js"></script>
<link rel="stylesheet" href="resources/css/grid-style.css"/>
<link rel="stylesheet" href="resources/css/body-page.css"/>
<link rel="stylesheet" href="resources/css/style.css">

<link rel="stylesheet" href="resources/css/font-awesome.min.css"/> 

<script src="resources/js/a076d05399.js"></script>

<script src="resources/js/jquery.3.4.1.min.js"></script>
<script src="resources/js/bootstrap.3.4.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

	
<script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-touch.js"></script>
    <script src="resources/js/angular-animate.js"></script>
    <script src="resources/js/angular-aria.js"></script>
    
     -->
<style>
        .tb-bk {
            background: #f3f7fa;
        }
            .tb-bk table tr td {
            width:33.33%;padding: 5px;
            }
            .tb-bk table {
            width:100%;
            }
                .tb-bk table tr td select, .tb-bk table tr td input,.tb-bk table tr td textarea {
                    background: #fff;
    color: #00a9e0;
    border-bottom: 1px solid #00a9e0 !important;
    border: none;
    line-height: 1;
    padding: 5px;
    width: 80%;
                }

                .tb-bk table tr td .lb {
            color: #2f246c;
    font-weight: 600;
    font-size: 12px;
        }
        .tb-bk  table tr td button {
        background-color: #fdd209;
    color: #2f246c;
    border: none;
    padding: 5px 10px;
    text-transform: uppercase;
    font-weight: 600;
        }
        .tb-bk table tr td .row, .tb-bk table tr td .row .col-xs-6 {
        margin:0 !important;
        padding:0 !important;
        }
            .tb-bk table tr td .row .lb span b {
            color:red;
            }
            select:focus,input:focus,button:focus,textarea:focus {
                outline: none;
            }
        span.text-left {
        line-height: 30px;
        }
        span.pull-right {
        padding:5px 10px;
        }
        
       /*    .ui-grid-header-cell-label {
		display:inline-block;
		white-space:initial;
		}
		
		
		.wrap-text .ui-grid-cell-contents {
 		 white-space:normal;
		}

		[ui-grid-row] {
  		display: table-row;
		}

		.ui-grid-row, .ui-grid-cell {
  		height: auto!important;
		}

			.ui-grid-cell {
  			float: none;
  			display: table-cell;
			} 
		
		
			.ui-grid-header-cell, .ui-grid-cell-contents {
  			white-space: normal;
  			padding: 2px;
  			word-break: break-word;
			} */
 
    </style>
	
</head>
<body>
	<div class="main" ng-app="app" id="appId">
		<div ng-controller="BillingCtrl as vm">
		 <input type="hidden" ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'" >
			<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="uploadfile">Upload
						File:</label>
					<div class="col-sm-5">
					<input class="form-control" type="file" file-model="uploadedFile"
							id="uploadedFile" accept=".xlsx, .xls, .csv"></input>
					
						<%-- <input class="form-control" type="file" file-model="uploadedFile"
							id="uploadedFile" accept=".xlsx, .xls, .csv"></input> <input type="hidden"
							ng-init="csrf ='<%=session.getAttribute("csrfToken")%>'"
							ng-value="csrf"> --%>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default" 
						style="cursor: hand;cursor: pointer;background-color: #fdd209;color: #2f246c;" 
							ng-click="doUploadFile()">Upload</button>
					</div>
				</div>
			</form>
			<br>

			<div class="submain">
				<input ng-model="searchText" ng-change="refresh()"
					placeholder="Enter Circle,vendor, etc." style="font-size: 12px"
					size="150" height="80" class="form-group has-search" id="input">

				<br />

				<div ui-grid="gridOptions" class="paginategrid" ui-grid-pagination
					ui-grid-exporter ui-grid-resize-columns id="test"></div>


			</div>
		</div>

	</div>
	</div>

	<script>
		angular.bootstrap(document.getElementById("appId"), [ 'app' ]);
	</script>

</body>
</html>