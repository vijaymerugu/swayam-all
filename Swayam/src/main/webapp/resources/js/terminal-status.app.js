angular.element(document).ready(function() {	
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
  
   var counttype = "";
   $scope.getCountType = function(type){
//alert(1);
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
    paginationPageSizes: [20, 30, 40],
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,	
     
      columnDefs: [
          { name: 'kioskId',width:150, headerCellTemplate: '<div>Kiosk<br/>Id</div>' },
          { name: 'kioskSrNo',width:150,headerCellTemplate: '<div>Kiosk<br/>Serial No</div>'},
          { name: 'branchCode', displayName: 'BR Code'},
          {name:'circle',width:150,displayName: 'Circle' },
          { name: 'cmf', displayName: 'CMF' }, 
         // { name: 'rmmsConnectivity',headerCellTemplate: '<div>Ticket<br/>No</div>' ,
         //cellTemplate: '<div ng-if="row.entity.rmmsConnectivity == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'},
          
           { name: 'rmmsConnectivity',headerCellTemplate: '<div>Ticket<br/>No</div>'}, //rmmsConnectivity map with Ticket No
          
          { name: 'pbPrinterStatus',headerCellTemplate: '<div>Printer<br/>Status</div>' ,
         cellTemplate: '<div ng-if="row.entity.pbPrinterStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.pbPrinterStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.pbPrinterStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'},
          { name: 'cartridgeStatus',headerCellTemplate: '<div>Cartridge<br/>Status</div>',
         cellTemplate: '<div ng-if="row.entity.cartridgeStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.cartridgeStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.cartridgeStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'},
         // { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',
         //cellTemplate: '<div ng-if="row.entity.antivirusStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>' },
          
         /*  { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',
         cellTemplate: '<div ng-if="row.entity.agentStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>' },
        */  
         
         
         { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',  cellTemplate: '<div ng-if="row.entity.agentStatus == \'Red\'">  	 <span><img src="resources/img/red.gif"></span></div>    	 <div ng-if="row.entity.agentStatus == \'Green\'" , "row.entity.agentStatus == \'GREEN\'" , "row.entity.agentStatus == \'green\'">   	 <span><img src="resources/img/green.gif"></span></div>      	 <div ng-if="row.entity.agentStatus == \'Grey\'">     	 <span><img src="resources/img/gray.gif"></span></div>'
            	 },
            
         
          { name: 'aplicationStatus',   headerCellTemplate: '<div>Aplication<br/>Status</div>' ,
         cellTemplate: '<div ng-if="row.entity.aplicationStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'Grey\'"><span><img src="resources/img/gray.gif"></span></div>'
          },
          { name: 'lastPrntTxnDttm', width:130,headerCellTemplate:'<div>Last Print<br/>TxnDate</div>' ,type:'date',
          //cellFilter:'date:"dd-MM-yyyy hh:mm:ss a " ' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
            filterCellFiltered : 'true',
                                           // width : '100',
                                          //  minWidth: '90',
                                            cellFilter : ' date:"dd-MM-yyyy hh:mm:ss a" '
         
          },  
         // { name: 'lastPmDttm', width:150, displayName: 'Last PM TxnDate  ',type: 'date',cellFilter: '<div>date:dd-MM-yyyy<br/>hh:mm:ss a</div>' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
             
         // },
           { name: 'lastPmDttm', width:130,headerCellTemplate:'<div>Last PM<br/>TxnDate</div>',
           // displayName: 'Last PM TxnDate  ',
           type: 'date',    //cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
             
          
                                           // width : '100',
                                          //  minWidth: '90',
                                            cellFilter : ' date:"dd-MM-yyyy hh:mm:ss a" '
         
          },
          
                                           
                                           
                   
         //  { name: 'timeDiff', width:150, displayName: 'TIME DIFF   ',type:'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
         
          //},  
          //{ name: 'lastFeedDttm', width:150, displayName: 'LAST FEED DTTM  ',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
            
         //},
       
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
		//alert("ts"+counttype);
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