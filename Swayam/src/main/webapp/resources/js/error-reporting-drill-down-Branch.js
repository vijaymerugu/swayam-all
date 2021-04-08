var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('ErrorReportDrillDownCtrl', ['$scope','$filter','ErrorReportDrillDownService','uiGridConstants', function ($scope, $filter,ErrorReportDrillDownService,uiGridConstants) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   var counttype = "BR";
   var circleName = document.getElementById("circleName").value;
   var networkName = document.getElementById("networkName").value;
   var moduleName = document.getElementById("moduleName").value;
   var regionName = document.getElementById("regionName").value;
   var fromDate = document.getElementById("fromDate").value;
   var toDate = document.getElementById("toDate").value;  
   
   $scope.getCountType = function(type){
      
       counttype=type;
   //  Added for loader------------- START 
		$("#loading").show(); 
	//  Added for loader------------- END 
       ErrorReportDrillDownService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
				   
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				 	//  Added for loader------------- START 
						$("#loading").hide(); 
					//  Added for loader------------- END 
				   });
	}
   
$scope.backUser = function()
   {  	
	        $("#loading").show();  
	        
	        var str ='td/errorDrillDownBranchBack?circleName='+circleName+'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
	     
		    $("#loading").hide();  
	 	   
	    };
   
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	
		    //  Added for loader------------- START 
			$("#loading").show(); 
		//  Added for loader------------- END 
	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 		    //  Added for loader------------- START 
				$("#loading").hide(); 
			//  Added for loader------------- END 
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		 //  $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		  
	 	       $("#loading").show(); 
		 	  	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
		 	  			paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate,$scope.searchText).success(function(data3){
		 	 	  		 
		 	 	  	  $scope.gridOptions.data = data3.content;
		 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
		 	  	      $("#loading").hide();
		 	  	  
		 	 	     }); 
	 	    }else{
	 		    //  Added for loader------------- START 
				$("#loading").show(); 
			//  Added for loader------------- END 
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	    //  Added for loader------------- START 
	 				$("#loading").hide(); 
	 			//  Added for loader------------- END 
	 	 	   });
	 	    }
	    };

	    //  Added for loader------------- START 
		$("#loading").show(); 
	//  Added for loader------------- END 
   ErrorReportDrillDownService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
	    //  Added for loader------------- START 
		$("#loading").hide(); 
	//  Added for loader------------- END 
   });
   
   $scope.gridOptions = {
    /*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,
	showColumnFooter: true, 
      /*headerTemplate: 'km/headerTemplate',
      superColDefs: [{
          name: 'front',
          displayName: ''
      },{
          name: 'lipi',
          displayName: 'LIPI'
      }, {
          name: 'Forbes',
          displayName: 'Forbes'
      }, {
          name: 'CMS',
          displayName: 'CMS'
      },  {
          name: 'total',
          displayName: 'Total'
      },{
          name: 'back',
          displayName: ''
      }],*/

    columnDefs: [
      { name: 'name', displayName: 'Branch', aggregationLabel : "Total: "},
      { name: 'totalNoOfTxns',headerCellTemplate: '<div>No Of Txns</div>'  ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
      { name: 'noOfSuccTxns', headerCellTemplate: '<div>No Of Success Txns</div>',aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
      { name: 'noOfFailTxns', headerCellTemplate: '<div>No OF Fail Txns</div>' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
      { name: 'noOfTchFailTxns', headerCellTemplate:'<div>No Of Technical Failure Txns</div>'  ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true },
      { name: 'noOfBsnsFailTxns', headerCellTemplate: '<div>No Of Business Failure Txns</div>',aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },        
],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
  	    //  Added for loader------------- START 
			$("#loading").show(); 
			if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          ErrorReportDrillDownService.getUsers(newPage,pageSize,"BR",circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
      	    //  Added for loader------------- START 
  			$("#loading").hide(); 
  		//  Added for loader------------- END 
          });
			}
	        else{
	 	 	   //	console.log("Inside else");
	        	 UserManagementService.getSearchNext(newPage,pageSize,"BR",circleName,networkName,moduleName,regionName,fromDate,toDate,$scope.searchText).success(function(data){
	           	  $scope.gridOptions.data = data.content;
	           	 	  $scope.gridOptions.totalItems = data.totalElements;
	        
		 	 		 $("#loading").hide();  
		 		   
	        	  });	 
	        
	        	   }
        });
     }
  };
  
}]);

var getPage = function(curPage, pageSize, counttype) {
    var url ='drillDown/get?page='+curPage+'&size='+pageSize+'&type='+counttype;
    

    var _scope = $scope;
    return ErrorReportDrillDownService.getUsers(curPage,pageSize,counttype)
    .success(function (response) {
      var firstRow = (curPage - 1) * pageSize;
      return response.content;
    });
  }; 


app.directive('superColWidthUpdate', ['$timeout', function ($timeout) {
    return {
      'restrict': 'A',
          'link': function (scope, element) {
          var _colId = scope.col.colDef.superCol,
              _el = jQuery(element);
          _el.on('resize', function () {
              _updateSuperColWidth();
          });
          var _updateSuperColWidth = function () {
              $timeout(function () {
                  var _parentCol = jQuery('.ui-grid-header-cell[col-name="' + _colId + '"]');
                  var _parentWidth = _parentCol.outerWidth(),
                      _width = _el.outerWidth();
                  
                  if (_parentWidth + 1 >= _width) {
                    _parentWidth = _parentWidth + _width;
                  } else {
                    _parentWidth = _width;
                  }
                  
                  _parentCol.css({
                      'min-width': _parentWidth + 'px',
                      'max-width': _parentWidth + 'px',
                      'text-align': "center"
                  });
              }, 0);
          };
          _updateSuperColWidth();
      }
    };
  }]);


app.service('ErrorReportDrillDownService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'errorRepoDrillDown/get?page='+pageNumber+'&size='+size+'&type='+counttype+'&circleName='+circleName
               +'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName
               +'&fromDate='+fromDate+'&toDate='+toDate
        });
    }
	function getSearchNext(pageNumber,size,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate, searchText) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'td/realTimeTxn/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&circleName='+circleName
          +'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName
          +'&fromDate='+fromDate+'&toDate='+toDate+'&searchText='+searchText
        });
    }
	
    return {
    	getUsers:getUsers,
    	
    	getSearchNext:getSearchNext
    	
    };
	
}]);