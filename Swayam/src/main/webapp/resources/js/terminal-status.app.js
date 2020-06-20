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
      { name: 'rmmsConnectivity', width:150, displayName: 'RMMS Connectivity' },
      { name: 'pbPrinterStatus', width:100, displayName: 'PB Printer Status'},
      { name: 'cartridgeStatus', width:100, displayName: 'Cartridge Status'},
      { name: 'antivirusStatus', width:100, displayName: 'Antivirus Status   '  },
     /* { name: 'aplicationStatus', width:100, displayName: 'Aplication Status   '},*/
      
      { name: 'aplicationStatus', width:150,   	  
    	  displayName: 'Aplication CMF', 	 
    	  cellTemplate: '<div ng-if="row.entity.aplicationStatus=!Red "><input  type="radio"  value="Acive"  disabled  style="color:red"></div> <div ng-if="row.entity.aplicationStatus==Green "><input  type=""  value="DeActive"   style="color:red"></div>'
    	  //cellTemplate: '<div ng-if="row.entity.aplicationStatus!="Red"  ">{{ row.entity.aplicationStatus }}</div>'
      }
      ,
      { name: 'lastPrntTxnDttm', width:200, displayName: 'Last Print TxnDate 	  ',type:'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
      
      },  
      { name: 'lastPmDttm', width:200, displayName: 'Last PM TxnDate 	  ',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
          
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
          url: '/ts/terminalStatusGet/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});