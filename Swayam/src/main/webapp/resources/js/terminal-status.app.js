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

	   if ($scope.counttype != type)
	   {
	   paginationOptions.pageNumber = 1;

	   }

	   $scope.counttype=type;

		   UserManagementService.getUsers(paginationOptions.pageNumber,
				   paginationOptions.pageSize,$scope.counttype).success(function(data){	
					//alert("$scope.counttype "+$scope.counttype);
						  $scope.gridOptions.data = data.content;
						  $scope.gridOptions.paginationCurrentPage = paginationOptions.pageNumber;
						  $scope.gridOptions.totalItems = data.totalElements;
					   });
		}
  /* 
  
   $scope.refresh = function()
   {  		if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	
		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
	    }else{
	    	
		   $scope.gridOptions.data = $scope.gridOptions.data;
	    }
   };
  
   UserManagementService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,$scope.counttype).success(function(data){
		
	  $scope.gridOptions.data = data.content;
	  $scope.gridOptions.totalItems = data.totalElements;
   });


*/


/*   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
	   	//  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
	   		UserManagementService.getUsers(paginationOptions.pageNumber,
	   			  paginationOptions.pageSize,counttype).success(function(data){
	   			 $scope.gridOptions.data = data.content;
	   			   $scope.gridOptions.totalItems = data.totalElements;
	   		//  Added for loader------------- START 
			        $("#loading").hide();  
			     // Added for loader------------- END
	   			   });   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 	    	
	 	    	$("#loading").show(); 
		 	  	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
		 	  			paginationOptions.pageSize,counttype,$scope.searchText).success(function(data3){
		 	 	  		 
		 	 	  	  $scope.gridOptions.data = data3.content;
		 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
		 	  	      $("#loading").hide();
		 	 	     });
	 		   
	 	    }else{
	 	   //  Added for loader------------- START 
		        $("#loading").show();  
		     // Added for loader------------- END
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	    			  paginationOptions.pageSize,counttype).success(function(data){
	 	    			 $scope.gridOptions.data = data.content;
	 	    			   $scope.gridOptions.totalItems = data.totalElements;
	 	    			//  Added for loader------------- START 
					        $("#loading").hide();  
					     // Added for loader------------- END
	 	    			   });
	 	    }
	    };

	//  Added for loader------------- START 
        $("#loading").show();  
     // Added for loader------------- END
   UserManagementService.getUsers(paginationOptions.pageNumber,
    paginationOptions.pageSize,counttype).success(function(data){
  
   $scope.gridOptions.data = data.content;
   $scope.gridOptions.totalItems = data.totalElements;
// Added for loader------------- START 
   $("#loading").hide();  
// Added for loader------------- END
   });
*/



   $scope.refresh = function()
   {  
  if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
  //  Added for loader------------- START
       $("#loading").show();  
    // Added for loader------------- END
  UserManagementService.getUsers(paginationOptions.pageNumber,
   paginationOptions.pageSize,counttype).success(function(data){
  $scope.gridOptions.data = data.content;
    $scope.gridOptions.totalItems = data.totalElements;
  //  Added for loader------------- START
       $("#loading").hide();  
    // Added for loader------------- END
    });  
 
   }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){debugger;
 
 /* $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText); */  
   
    $("#loading").show();
 /* UserManagementService.getSearchNext(paginationOptions.pageNumber,
  paginationOptions.pageSize,fromDate,toDate,$scope.searchText).success(function(data3){*/
  UserManagementService.getSearchNext(0,
  paginationOptions.pageSize,counttype,$scope.searchText).success(function(data3){
   $scope.gridOptions.data = data3.content;
     $scope.gridOptions.totalItems = data3.totalElements;
       $("#loading").hide();
    });
 
   }else{
  //  Added for loader------------- START
       $("#loading").show();  
    // Added for loader------------- END
    UserManagementService.getUsers(paginationOptions.pageNumber,
     paginationOptions.pageSize,counttype).success(function(data){
    $scope.gridOptions.data = data.content;
      $scope.gridOptions.totalItems = data.totalElements;
    //  Added for loader------------- START
       $("#loading").hide();  
    // Added for loader------------- END
      });
   }
   };
   $scope.clearSearch = function()
   {   debugger;
 
    $scope.searchText='';
 
       $("#loading").show();  
   
  UserManagementService.getUsers(0,
  paginationOptions.pageSize,counttype).success(function(data){
   $scope.gridOptions.data = data.content;
   $scope.gridOptions.paginationCurrentPage = data.number;
   $scope.gridOptions.totalItems = data.totalElements;
 
       $("#loading").hide();  
   
    });
 
 
   };
//  Added for loader------------- START
        $("#loading").show();  
     // Added for loader------------- END
   UserManagementService.getUsers(paginationOptions.pageNumber,
    paginationOptions.pageSize,counttype).success(function(data){
 
   $scope.gridOptions.data = data.content;
   $scope.gridOptions.totalItems = data.totalElements;
// Added for loader------------- START
   $("#loading").hide();  
// Added for loader------------- END
   });


   $scope.gridOptions = {
    /*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,	
   
    
      columnDefs: [
          { name: 'kioskId', headerCellTemplate: '<div>Kiosk<br/>Id</div>' },
          { name: 'kioskSerialNo',headerCellTemplate: '<div>Kiosk<br/>Serial No</div>'},
          { name: 'brCode', displayName: 'BR Code'},
          {name:'crclName',displayName: 'Circle' },
          { name: 'username', displayName: 'CMF' }, 
         // { name: 'rmmsConnectivity',headerCellTemplate: '<div>Ticket<br/>No</div>' ,
         //cellTemplate: '<div ng-if="row.entity.rmmsConnectivity == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.rmmsConnectivity == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>'},
          
           { name: 'rmmsConnectivity',headerCellTemplate: '<div>Ticket<br/>No</div>'}, //rmmsConnectivity map with Ticket No
          
          { name: 'pbPrinterStatus',headerCellTemplate: '<div>Printer<br/>Status</div>' ,
         cellTemplate: '<div ng-if="row.entity.pbPrinterStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div>  <div ng-if="row.entity.pbPrinterStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.pbPrinterStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div>  	 <div ng-if="row.entity.pbPrinterStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div>     	 <div ng-if="row.entity.pbPrinterStatus == \'green\'"><span><img src="resources/img/green.gif"></span></div>    	 <div ng-if="row.entity.pbPrinterStatus == \'GREEN\'"><span><img src="resources/img/green.gif"></span></div>   	 <div ng-if="row.entity.pbPrinterStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>      	 <div ng-if="row.entity.pbPrinterStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div>    	 <div ng-if="row.entity.pbPrinterStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div> '},
          { name: 'cartridgeStatus',headerCellTemplate: '<div>Cartridge<br/>Status</div>',
         cellTemplate: '<div ng-if="row.entity.cartridgeStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.cartridgeStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.cartridgeStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div>    	 <div ng-if="row.entity.cartridgeStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div>   	 <div ng-if="row.entity.cartridgeStatus == \'green\'"><span><img src="resources/img/green.gif"></span></div>  	 <div ng-if="row.entity.cartridgeStatus == \'GREEN\'"><span><img src="resources/img/green.gif"></span></div>    	 <div ng-if="row.entity.cartridgeStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>    	 <div ng-if="row.entity.cartridgeStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div>     	 <div ng-if="row.entity.cartridgeStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div> '},
         // { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',
         //cellTemplate: '<div ng-if="row.entity.antivirusStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.antivirusStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>' },
          
         /*  { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',
         cellTemplate: '<div ng-if="row.entity.agentStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div>' },
        */  
        { name: 'agentStatus', headerCellTemplate: '<div>Agent<br/>Status</div>',  cellTemplate: '<div ng-if="row.entity.agentStatus == \'Red\'"> <span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.agentStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div><div ng-if="row.entity.agentStatus == \'Green\' "><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'green\' "><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'GREEN\' "><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.agentStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div> <div ng-if="row.entity.agentStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div> <div ng-if="row.entity.agentStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div>'
            	 },
         { name: 'aplicationStatus',   headerCellTemplate: '<div>Aplication<br/>Status</div>' ,
         cellTemplate: '<div ng-if="row.entity.aplicationStatus == \'Red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.aplicationStatus == \'red\'"><span><img src="resources/img/red.gif"></span></div> <div ng-if="row.entity.aplicationStatus == \'RED\'"><span><img src="resources/img/red.gif"></span></div>  	 <div ng-if="row.entity.aplicationStatus == \'Green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'green\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'GREEN\'"><span><img src="resources/img/green.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'Gray\'"><span><img src="resources/img/gray.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'gray\'"><span><img src="resources/img/gray.gif"></span></div><div ng-if="row.entity.aplicationStatus == \'GRAY\'"><span><img src="resources/img/gray.gif"></span></div>'
          },
          { name: 'lastPrntTxnDttm', headerCellTemplate:'<div>Last Print<br/>TxnDate</div>' ,type:'date',
          //cellFilter:'date:"dd-MM-yyyy hh:mm:ss a " ' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
            filterCellFiltered : 'true',
                                           // width : '100',
                                          //  minWidth: '90',
                                            cellFilter : ' date:"dd-MM-yyyy hh:mm:ss a" '
                                            	// cellFilter : ' date:"dd-MM-yyyy" '
          },  
         // { name: 'lastPmDttm', width:150, displayName: 'Last PM TxnDate  ',type: 'date',cellFilter: '<div>date:dd-MM-yyyy<br/>hh:mm:ss a</div>' //'date:\"dd-MM-yyyy hh:mm:ss a\"'
             
         // },
           { name: 'lastPmDttm',headerCellTemplate:'<div>Last PM<br/>TxnDate</div>',
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
/*    onRegisterApi: function(gridApi) {debugger;
        $scope.gridApi = gridApi;
   
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;

          UserManagementService.getUsers(newPage,pageSize,$scope.counttype).success(function(data){
        	  $scope.gridOptions.data = data.content;
        	  
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);
*/



    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
      //  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
	        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          UserManagementService.getUsers(newPage,pageSize,$scope.counttype).success(function(data){
	//alert("$scope.counttype On Register "+$scope.counttype);
         $scope.gridOptions.data = data.content;
           $scope.gridOptions.totalItems = data.totalElements;
       //  Added for loader------------- START 
	        $("#loading").hide();  
	     // Added for loader------------- END
          });
        }else{
 	 	   	console.log("Inside else"+$scope.counttype);
        	 UserManagementService.getSearchNext(newPage,pageSize,$scope.counttype,$scope.searchText).success(function(data){
           	 // alert("$scope.counttype====Serach "+$scope.counttype);
                  $scope.gridOptions.data = data.content;
           	 	  $scope.gridOptions.totalItems = data.totalElements;
        
	 	 		 $("#loading").hide();  
	 		   
        	  });	 
        
        	   }
        });
     }
  };
 
}]);






app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype) {	
		//alert("counttype===ts:::: "+counttype);
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'ts/terminalStatusGet/get?page='+pageNumber+'&size='+size+'&type='+counttype
        });
    }
    
    
    
function getSearchNext(pageNumber,size,counttype, searchText) {
	//alert("counttype= searchText=="+counttype);
	//alert("13=todate=="+end);
	pageNumber = pageNumber > 0?pageNumber - 1:0;
    return  $http({
      method: 'GET',
      url: 'ts/terminalStatusSearch/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&searchText='+searchText
    });
}
	
    return {
    	getUsers:getUsers,
        getSearchNext:getSearchNext
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});