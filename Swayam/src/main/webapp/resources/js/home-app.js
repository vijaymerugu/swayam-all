var appHome = angular.module('homeApp', []);
appHome.controller('menuController', ['$scope', '$http', function ($scope, $http) {
    $scope.SiteMenu = [];
    $http.get('/common/menu').then(function (data) {
        $scope.SiteMenu = data.data;
    }, function (error) {
        alert('Error');
    })
}]);