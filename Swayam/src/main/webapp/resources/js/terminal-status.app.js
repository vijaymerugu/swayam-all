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
    /*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,	
     
      columnDefs: [
          { name: 'kioskId',width:150, headerCellTemplate: '<div>Kiosk<br/>Id</div>' },
          { name: 'kioskSrNo',width:150,headerCellTemplate: '<div>Kiosk<br/>Serial No</div>'},
          { name: 'branchCode',width:140, displayName: 'BR Code'},
          {name:'circle',width:140,displayName: 'Circle' },
          { name: 'cmf',width:120, displayName: 'CMF' }, 
         // { name: 'rmmsConnectivity',headerCellTemplate: '<div>Ticket<br/>No</div>' ,
         //cellTemplate: '<div ng-if="row.entity.rmmsConnectivity == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>'},
          
           { name: 'rmmsConnectivity',width:120,headerCellTemplate: '<div>Ticket<br/>No</div>'}, //rmmsConnectivity map with Ticket No
          
          { name: 'pbPrinterStatus',width:120,headerCellTemplate: '<div>Printer<br/>Status</div>' ,
         cellTemplate: '<div ng-if="row.entity.pbPrinterStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div>  <div ng-if="row.entity.pbPrinterStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.pbPrinterStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div>  	 <div ng-if="row.entity.pbPrinterStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div>     	 <div ng-if="row.entity.pbPrinterStatus == \'green\'"><span><img src="resources/img/green.gif"></span></div>    	 <div ng-if="row.entity.pbPrinterStatus == \'GREEN\'"><span><img src="resources/img/green.gif"></span></div>   	 <div ng-if="row.entity.pbPrinterStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>      	 <div ng-if="row.entity.pbPrinterStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div>    	 <div ng-if="row.entity.pbPrinterStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div> '},
          { name: 'cartridgeStatus',width:120,headerCellTemplate: '<div>Cartridge<br/>Status</div>',
         cellTemplate: '<div ng-if="row.entity.cartridgeStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.cartridgeStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.cartridgeStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div>    	 <div ng-if="row.entity.cartridgeStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div>   	 <div ng-if="row.entity.cartridgeStatus == \'green\'"><span><img src="resources/img/green.gif"></span></div>  	 <div ng-if="row.entity.cartridgeStatus == \'GREEN\'"><span><img src="resources/img/green.gif"></span></div>    	 <div ng-if="row.entity.cartridgeStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>    	 <div ng-if="row.entity.cartridgeStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div>     	 <div ng-if="row.entity.cartridgeStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div> '},
         // { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',
         //cellTemplate: '<div ng-if="row.entity.antivirusStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>' },
          
         /*  { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',
         cellTemplate: '<div ng-if="row.entity.agentStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>' },
        */  
        { name: 'agentStatus',width:120, headerCellTemplate: '<div>Agent<br/>Status</div>',  cellTemplate: '<div ng-if="row.entity.agentStatus == \'Red\'"> <span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.agentStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'Green\' "><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'green\' "><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'GREEN\' "><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div> <div ng-if="row.entity.agentStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div> <div ng-if="row.entity.agentStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div>'
            	 },
         { name: 'aplicationStatus',width:120,   headerCellTemplate: '<div>Aplication<br/>Status</div>' ,
         cellTemplate: '<div ng-if="row.entity.aplicationStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.aplicationStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.aplicationStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div>  	 <div ng-if="row.entity.aplicationStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'GREEN\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div>'
          },
          { name: 'lastPrntTxnDttm', width:160,headerCellTemplate:'<div>Last Print<br/>TxnDate</div>' ,type:'date',
          //cellFilter:'date:"dd-MM-yyyy hh:mm:ss a " ' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
            filterCellFiltered : 'true',
                                           // width : '100',
                                          //  minWidth: '90',
                                            cellFilter : ' date:"dd-MM-yyyy hh:mm:ss a" '
                                            	// cellFilter : ' date:"dd-MM-yyyy" '
          },  
         // { name: 'lastPmDttm', width:150, displayName: 'Last PM TxnDate  ',type: 'date',cellFilter: '<div>date:dd-MM-yyyy<br/>hh:mm:ss a</div>' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
             
         // },
           { name: 'lastPmDttm', width:160,headerCellTemplate:'<div>Last PM<br/>TxnDate</div>',
           // displayName: 'Last PM TxnDate  ',
           type: 'date',    //cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
             
          
                                           // width : '100',
                                          //  minWidth: '90',
                                           cellFilter : ' date:"dd-MM-yyyy hh:mm:ss a" '
           
         //  cellFilter : ' date:"dd-MM-yyyy" '
         
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