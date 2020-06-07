var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('DrillDownCtrl', ['$scope','$filter','DrillDownService', function ($scope, $filter,DrillDownService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
   var counttype = "";
     
     $scope.fromDate = {
    	       value: new Date(2020, 12, 22)
     };
     
     $scope.toDate = {
    	       value: new Date(2020, 12, 22)
     };
     
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='/td/drillDownNetwork?circleName=' + url;
			$("#contentHomeApp").load(str);
			/*DrillDownService.getUsers(paginationOptions.pageNumber,
					   paginationOptions.pageSize,counttype,url).success(function(data){
				  $scope.gridOptions.data = data.content;
			 	  $scope.gridOptions.totalItems = data.totalElements;
			   });*/
		}						
	}
  
 $scope.loadHomeBodyPageFormsGenerate = function(){	
	 $scope.fromDate.value = $filter('date')($scope.fromDate.value, "dd-MMM-yy");
	 console.log($scope.fromDate.value);
	 $scope.toDate.value = $filter('date')($scope.toDate.value, "dd-MMM-yy");
	 console.log($scope.toDate.value);
	 
    	var circleName="";
    	var networkName="";
    	var moduleName="";
    	var regionName="";
    	
    	DrillDownService.getUsers(paginationOptions.pageNumber,
    			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,$scope.fromDate.value,$scope.toDate.value).success(function(data){
    		  $scope.gridOptions.data = data.content;
    	 	  $scope.gridOptions.totalItems = data.totalElements;
    	   });
	}
 
   $scope.getCountType = function(type){
      
       counttype=type;
       DrillDownService.getUsers(paginationOptions.pageNumber,
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

   DrillDownService.getUsers(paginationOptions.pageNumber,
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
      
      headerTemplate: '/km/headerTemplate',
      superColDefs: [{
          name: 'lipi',
          displayName: 'LIPI'
      }, {
          name: 'Forbes',
          displayName: 'Forbes'
      }, {
          name: 'CMS',
          displayName: 'CMS'
      }], 

    columnDefs: [
      { name: 'circleName',
      	  exporterSuppressExport: true,
      	  headerCellTemplate: '<div> Circle </div>',
      	  cellTemplate: '<div class="ui-grid-cell-contents"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.circleCode)">{{row.entity.circleName}}</a></div>'
      },
      { name: 'totalSwayamBranches', displayName: 'Total Swayam Branches'  },
      { name: 'totalSwayamKiosks', displayName: 'Total Swayam Kiosks'  },
      { name: 'lipiKiosks', displayName: 'Kiosks',superCol: 'lipi'  },
      { name: 'lipiTxns', displayName: 'Txns',superCol: 'lipi'  },
      { name: 'forbesKiosks', displayName: 'Kiosks',superCol: 'Forbes'  },
      { name: 'forbesTxns', displayName: 'Txns',superCol: 'Forbes'  },
      { name: 'cmsKiosks', displayName: 'Kiosks',superCol: 'CMS'  },
      { name: 'cmsTxns', displayName: 'Txns',superCol: 'CMS'  },
      { name: 'totalSwayamTxns', displayName: 'Swayam Txns'  },
      { name: 'totalBranchCounterTxns', displayName: 'Branch Counter Txns'  },
      { name: 'migrationPercentage', displayName: 'Migration Percentage(%)'  }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          DrillDownService.getUsers(newPage,pageSize,counttype).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);

var getPage = function(curPage, pageSize, counttype) {
    var url ='/drillDown/get?page='+curPage+'&size='+pageSize+'&type='+counttype;
    

    var _scope = $scope;
    return DrillDownService.getUsers(curPage,pageSize,counttype)
    .success(function (response) {
  	  alert(pageSize);
  	  alert(response.content);
      var firstRow = (curPage - 1) * pageSize;
      //$scope.gridOptions.totalItems = 100;
      //$scope.gridOptions.data = response.content.slice(firstRow, firstRow + pageSize);
      //return response.content.slice(firstRow, firstRow + pageSize);
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
		console.log()
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: '/drillDown/get?page='+pageNumber+'&size='+size+'&type='+counttype+'&circleName='+circleName
               +'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName
               +'&fromDate='+fromDate+'&toDate='+toDate       
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);