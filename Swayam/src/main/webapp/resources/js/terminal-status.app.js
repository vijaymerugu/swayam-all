angular.element(document).ready(function() {	
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
  
   var counttype = "";
   $scope.getCountType = function(type){
alert(1);
		counttype=type;
		   UserManagementService.getUsers(paginationOptions.pageNumber,
				   paginationOptions.pageSize,counttype).success(function(data){				   
						  $scope.gridOptions.data = data.content;
					 	  $scope.gridOptions.totalItems = data.totalElements;
					   });
		}
   
  
   $scope.refresh = function()
   {  		if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	
		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
	    }else{
	    	
		   $scope.gridOptions.data = $scope.gridOptions.data;
	    }
   };

   UserManagementService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype).success(function(data){
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
   });

   $scope.gridOptions = {
    paginationPageSizes: [5, 10, 20],
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,
	enableGridMenu: true,
	exporterMenuCsv: false,
	exporterPdfDefaultStyle: {fontSize: 9},   
    exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, color: 'black'},      
    exporterPdfFooter: function ( currentPage, pageCount ) {
      return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
    },    
    exporterPdfCustomFormatter: function ( docDefinition ) {        
        docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
        return docDefinition;
      },
     
      columnDefs: [
          { name: 'kioskId', displayName: 'Kiosk Id'  },
          { name: 'kioskSrNo', displayName: 'Kiosk Serial No'},
          { name: 'branchCode', displayName: 'BR Code'},
          {name:'circle',displayName: 'Circle'},
          { name: 'cmf', displayName: 'CMF'},
          { name: 'rmmsConnectivity', width:150, displayName: 'RMMS Connectivity' ,
         cellTemplate: '<div ng-if="row.entity.rmmsConnectivity == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'},
          { name: 'pbPrinterStatus', width:100, displayName: 'PB Printer Status',
         cellTemplate: '<div ng-if="row.entity.pbPrinterStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.pbPrinterStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.pbPrinterStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'},
          { name: 'cartridgeStatus', width:100, displayName: 'Cartridge Status',
         cellTemplate: '<div ng-if="row.entity.cartridgeStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.cartridgeStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.cartridgeStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'},
          { name: 'antivirusStatus', width:100, displayName: 'Antivirus Status   ' ,
         cellTemplate: '<div ng-if="row.entity.antivirusStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>' },
          { name: 'aplicationStatus', width:150,    
         displayName: 'Aplication Status',
         cellTemplate: '<div ng-if="row.entity.aplicationStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'
          },
          { name: 'lastPrntTxnDttm', width:200, displayName: 'Last Print TxnDate  ',type:'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
         
          },  
          { name: 'lastPmDttm', width:200, displayName: 'Last PM TxnDate  ',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
             
          },
         
       
        ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          UserManagementService.getUsers(newPage,pageSize,counttype).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);


app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype) {	
		alert("ts"+counttype);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'ts/terminalStatusGet/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});