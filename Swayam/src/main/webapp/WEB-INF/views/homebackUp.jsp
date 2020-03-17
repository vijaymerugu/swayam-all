<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
		
<!Doctype html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<body>
<style>
/*Here I will write css for show horizantal nav menu*/
ul {
  list-style: none;
  padding: 0;
  margin: 0;
  background: #1bc2a2;
}
 
ul li {
  display: block;
  position: relative;
  float: left;
  background: #1bc2a2;
}
li ul { display: none; }
 
ul li a {
  display: block;
  padding: 1em;
  text-decoration: none;
  white-space: nowrap;
  color: #fff;
}
 
ul li a:hover { background: #2c3e50; }
li:hover > ul {
  display: block;
  position: absolute;
}
 
li:hover li { float: none; }
 
li:hover a { background: #1bc2a2; }
 
li:hover li a:hover { background: #2c3e50; }
 
.main-navigation li ul li { border-top: 0; }
ul ul ul {
  left: 100%;
  top: 0;
}
ul:before,
ul:after {
  content: " "; /* 1 */
  display: table; /* 2 */
}
ul:after { clear: both; }
#nav {
list-style:none inside;
margin:0;
padding:0;
text-align:center;
}
#nav li {
display:block;
position:relative;
float:left;
background: #24af15; /* menu background color */
}
#nav li a {
display:block;
padding:0;
text-decoration:none;
width:200px; /* this is the width of the menu items */
line-height:35px; /* this is the hieght of the menu items */
color:#ffffff; /* list item font color */
}
#nav li li a {font-size:80%;} /* smaller font size for sub menu items */
#nav li:hover {background:#003f20;} /* highlights current hovered list item and the parent list items when hovering over sub menues */
#nav ul {
position:absolute;
padding:0;
left:0;
display:none; /* hides sublists */
}
#nav li:hover ul ul {display:none;} /* hides sub-sublists */
#nav li:hover ul {display:block;} /* shows sublist on hover */
#nav li li:hover ul {
display:block; /* shows sub-sublist on hover */
margin-left:200px; /* this should be the same width as the parent list item */
margin-top:-35px; /* aligns top of sub menu with top of list item */
}
</style>
</head>
<ul id="nav">
<li><a href="#">Main Item 1</a></li>
<li><a href="#">Main Item 2</a>
<ul>
<li><a href="#">Sub Item</a></li>
<li><a href="#">Sub Item</a></li>
<li><a href="#">SUB SUB LIST »</a>
<ul>
<li><a href="#">Sub Sub Item 1</a>
<li><a href="#">Sub Sub Item 2</a>
</ul>
</li>
</ul>
</li>
<li><a href="#">Main Item 3</a></li>
</ul>

<div ng-app="MyApp">
    <div ng-controller="menuController">
      <!--    @* Here first of all we will create a ng-template *@-->
        <script type="text/ng-template" id="menu">
            <a href="{{menu.url}}">{{menu.name}}</a>
           
            <ul ng-if="(SiteMenu | filter:{parentId : menu.id}).length > 0">
                <li ng-repeat="menu in SiteMenu | filter:{parentId : menu.id}" ng-include="'menu'"></li>
            </ul>
        </script>
        <ul class="main-navigation" >
           <!-- @* Here we will load only top level menu *@-->
            <li ng-repeat="menu in SiteMenu | filter:{parentId : 0}" ng-include="'menu'"></li>
        </ul>
    </div>
</div>

<script>
var app = angular.module('MyApp', []);
app.controller('menuController', ['$scope', '$http', function ($scope, $http) {
    $scope.SiteMenu = [];
    $http.get('/menu').then(function (data) {
        $scope.SiteMenu = data.data;
    }, function (error) {
        alert('Error');
    })
}])
</script>
</body>
</html>

		