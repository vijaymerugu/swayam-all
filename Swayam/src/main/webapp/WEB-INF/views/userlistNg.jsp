<!DOCTYPE html>
<html lang="en">
    <head>
        
          <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.7.0/angular.js"></script>
    <script src="http://ui-grid.info/release/ui-grid.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.8.3/i18n/ui-grid.grouping.min.js"></script>
       <link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css">
    
    <style type="text/css">
    
    </style>    
    </head>
    <body>
    <div  ng-app="appuser" id="appuserId">
        <div ng-controller="StudentCtrl as vm">
            <div ui-grid="gridOptions" class="grid" ui-grid-pagination>
            </div>
        </div>
    </div>
    </body>
    
<script>

var app = angular.module('appuser', ['ui.grid','ui.grid.pagination']);
app.controller('StudentCtrl', ['$scope','StudentService', 
                               function ($scope, StudentService) {
                                   var paginationOptions = {
                                       pageNumber: 1,
                                       pageSize: 2,
                                   sort: null
                                   };
                            
                               StudentService.getStudents(
                                 paginationOptions.pageNumber,
                                 paginationOptions.pageSize).then(function(data){
                                   $scope.gridOptions.data = data.content;
                                   $scope.gridOptions.totalItems = data.totalElements;
                                 });
                            
                               $scope.gridOptions = {
                                   paginationPageSizes: [5, 10, 20],
                                   paginationPageSize: paginationOptions.pageSize,
                                   enableColumnMenus:false,
                               useExternalPagination: true,
                                   columnDefs: [
                                      { name: 'UserName' },
                                      { name: 'FirstName' },
                                      { name: 'LastName' },
                                      { name: 'PF ID' }
                                   ],
                                   onRegisterApi: function(gridApi) {
                                      $scope.gridApi = gridApi;                                      
                                      gridApi.pagination.on.paginationChanged(
                                        $scope, 
                                        function (newPage, pageSize) {
                                          paginationOptions.pageNumber = newPage;
                                          paginationOptions.pageSize = pageSize;
                                          StudentService.getStudents(newPage,pageSize)
                                            .then(function(data){
                                              $scope.gridOptions.data = data.content;
                                              $scope.gridOptions.totalItems = data.totalElements;
                                            });
                                       });
                                      
                                   }
                               };
                           }]);
                           
app.service('StudentService',['$http', function ($http) {
	 
    function getStudents(pageNumber,size) {
        pageNumber = pageNumber > 0?pageNumber - 1:0;
        return $http({
          method: 'GET',
            url: 'student/get?page='+pageNumber+'&size='+size
        });
    }
    return {
        getStudents: getStudents
    };
}]);                       


</script>    
</html>