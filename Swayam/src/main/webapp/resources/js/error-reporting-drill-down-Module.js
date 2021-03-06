var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('ErrorReportDrillDownCtrl', ['$scope','$filter','ErrorReportDrillDownService','uiGridConstants', function ($scope, $filter,ErrorReportDrillDownService,uiGridConstants) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   var counttype = "MOD";
   var circleName = document.getElementById("circleName").value;
   var networkName = document.getElementById("networkName").value;
   var fromDate = document.getElementById("fromDate").value;
   var toDate = document.getElementById("toDate").value;   
   var moduleName="";
   var regionName=""; 
   
   $scope.loadHomeBodyPageForms = function(moduleName){	   
		if(circleName != undefined || networkName != undefined || moduleName != undefined){	
			var str ='td/errorRepodrillDownRegion?circleName='+circleName+'&networkName='+networkName+'&moduleName='+moduleName+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
		}						
	}
  

   $scope.loadHomeBodyPageForms1 = function(code){
	   
		if(circleName != undefined || code != undefined || code != undefined){	
		//	alert("code::"+code);	
			var str ='td/errorRepoNoOfTechFailDrillDownModule?networkCode='+code+'&fromDate='+fromDate+'&toDate='+toDate;
			//alert("code::"+code);	
			//var str ='td/errorRepoTechFailTxnDrillDownRegion?circleName='+circleName+'&networkName='+networkName+'&moduleName='+moduleName+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
		}						
	}


 $scope.loadHomeBodyPageForms2 = function(code){	   
		if(circleName != undefined || code != undefined || code != undefined){	
			//alert("code::"+code);
			var str ='td/errorRepoNoOfBussFailDrillDownModule?networkCode='+code+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
		}						
	}

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
	     //  alert("i m inside backUser");
	        moduleName ="";
	        var str ='td/errorDrillDownModuleBack?circleName='+circleName+'&networkName='+networkName+'&moduleName='+moduleName+'&fromDate='+fromDate+'&toDate='+toDate;
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
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
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
    paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,
	showColumnFooter: true, 
     /* headerTemplate: 'km/headerTemplate',
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
      }, {
          name: 'total',
          displayName: 'Total'
      },
      {
          name: 'back',
          displayName: ''
      }]*/

    columnDefs: [
      { name: 'name',
      	  exporterSuppressExport: true,
      	  headerCellTemplate: '<div> Mod </div>',
      	  aggregationLabel : "Total: ",
      	  cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.code)">{{row.entity.name}}</a></div>'
      },
      { name: 'totalNoOfTxns',headerCellTemplate: '<div>No Of Txns</div>'  ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
      { name: 'noOfSuccTxns', headerCellTemplate: '<div>No Of Success Txns</div>',aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
      { name: 'noOfFailTxns', headerCellTemplate: '<div>No Of Fail Txns</div>' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
      { name: 'noOfTchFailTxns',aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, headerCellTemplate: '<div>No Of Technical Failure Txns</div>' , cellTemplate: '<div><a  style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms1(row.entity.code)">{{row.entity.noOfTchFailTxns}}</a></div>'},   
      { name: 'noOfBsnsFailTxns',aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, headerCellTemplate: '<div>No Of Business Failure Txns</div>' , cellTemplate: '<div><a  style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms2(row.entity.code)">{{row.entity.noOfBsnsFailTxns}}</a></div>'}, 
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
  	    //  Added for loader------------- START 
			$("#loading").show(); 
		//  Added for loader------------- END 
          ErrorReportDrillDownService.getUsers(newPage,pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
      	    //  Added for loader------------- START 
  			$("#loading").hide(); 
  		//  Added for loader------------- END 
          });
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
	
    return {
    	getUsers:getUsers
    };
	
}]);