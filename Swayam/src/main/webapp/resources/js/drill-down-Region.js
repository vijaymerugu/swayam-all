var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('DrillDownCtrl', ['$scope','$filter','DrillDownService', function ($scope, $filter,DrillDownService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   var counttype = "REG";

   var circleName = document.getElementById("circleName").value;
   var networkName = document.getElementById("networkName").value;
   var moduleName = document.getElementById("moduleName").value;
   var fromDate = document.getElementById("fromDate").value;
   var toDate = document.getElementById("toDate").value;   
   var regionName="";

   
   $scope.loadHomeBodyPageForms = function(regionName){	   
		if(circleName != undefined || networkName != undefined || moduleName != undefined || regionName != undefined){	
			var str ='td/drillDownBranch?circleName='+circleName+'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
		}						
	}
  
   $scope.getCountType = function(type){
      
       counttype=type;
   //  Added for loader------------- START 
		$("#loading").show(); 
	//  Added for loader------------- END 
       DrillDownService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
				   
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				 	//  Added for loader------------- START 
						$("#loading").hide(); 
					//  Added for loader------------- END 
				   });
	}
   
   
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
   DrillDownService.getUsers(paginationOptions.pageNumber,
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
      
      headerTemplate: 'km/headerTemplate',
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
      },{
          name: 'total',
          displayName: 'Total'
      }, {
          name: 'back',
          displayName: ''
      }], 
      
    columnDefs: [
      { name: 'name',
      	  exporterSuppressExport: true,
      	  headerCellTemplate: '<div> Reg </div>',
      	  superCol: 'front', 
      	  cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.code)">{{row.entity.name}}</a></div>'
      },
      { name: 'branchCodeCount', displayName: 'Total Swayam Branches',superCol: 'front'   },
      { name: 'totalSwayamKiosks', displayName: 'Total Swayam Kiosks',superCol: 'front'   },
      { name: 'lipiKiosks', displayName: 'Kiosks',superCol: 'lipi'  },
      { name: 'lipiTxns', displayName: 'Txns',superCol: 'lipi'  },
      { name: 'forbesKiosks', displayName: 'Kiosks',superCol: 'Forbes'  },
      { name: 'forbesTxns', displayName: 'Txns',superCol: 'Forbes'  },
      { name: 'cmsKiosks', displayName: 'Kiosks',superCol: 'CMS'  },
      { name: 'cmsTxns', displayName: 'Txns',superCol: 'CMS'  },
      { name: 'totalSwayamTxns', displayName: 'Swayam Txns',superCol: 'total'  },
      { name: 'totalBranchCounterTxns', displayName: 'Branch Counter Txns',superCol: 'total'  },
      { name: 'migrationPercentage', displayName: 'Migration Percentage(%)',superCol: 'back'  }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          DrillDownService.getUsers(newPage,pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);

var getPage = function(curPage, pageSize, counttype) {
    var url ='drillDown/get?page='+curPage+'&size='+pageSize+'&type='+counttype;
    

    var _scope = $scope;
    return DrillDownService.getUsers(curPage,pageSize,counttype)
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

app.service('DrillDownService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate) {
		
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'drillDown/get?page='+pageNumber+'&size='+size+'&type='+counttype+'&circleName='+circleName
               +'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName
               +'&fromDate='+fromDate+'&toDate='+toDate
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);