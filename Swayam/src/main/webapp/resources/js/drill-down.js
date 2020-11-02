var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('DrillDownCtrl', ['$scope','$filter','DrillDownService', function ($scope, $filter,DrillDownService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
   var counttype = "";
   var circleName="";
   var networkName="";
   var moduleName="";
   var regionName="";     
   var fromDate = "";
   var toDate= "";
     
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='td/drillDownNetwork?circleName='+url+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
		}						
	}
   
  /* function convertDate(dateParam){
	   var result="";
	   var date = new Date(dateParam);
       var year = date.getFullYear();
       var rawMonth = parseInt(date.getMonth()) + 1;
       var month = rawMonth < 10 ? '0' + rawMonth : rawmonth;
       var rawDay = parseInt(date.getDate());
       var day = rawDay < 10 ? '0' + rawDay : rawDay; 
       console.log(year + '-' + month + '-' + day);
	   
	     // result= year+"-"+month+"-"+day;
	      result= day+"-"+month+"-"+year;
	    //  alert("return --result::: "+result);
	      return result;
	  }*/
  
/*   $scope.searchPositions= function(startDate,endDate){
	 	 
    	 circleName="";
    	 networkName="";
    	 moduleName="";
    	 regionName="";
    	 
    	 */
    	 $scope.searchPositions= function(startDate,endDate){
    	  	 
    	  	 circleName="";
    	  	 networkName="";
    	  	 moduleName="";
    	  	 regionName="";
    	  	 
    	  	 fromDate = $("#datepickerFromDate").val();
    	  	 toDate = $("#datepickerToDate").val();
    	  	 
      	 
	        var $from=$("#datepickerFromDate").datepicker('getDate');
	        var $to =$("#datepickerToDate").datepicker('getDate');
	        
	        if (($from== null) || ($to== null) )
	 	   {
	 	   
	 	       if($from== null)
	 	      	{
	 	      		alert("Please enter from date!!!");
	 	      		$("#datepickerFromDate").focus();
	 	      	}
	 	       if($to== null)
	 	     	{
	 	     		alert("Please enter to date!!!");
	 	     		$("#datepickerToDate").focus();
	 	     	}
	    		}
	        else
	     	  {
	 	    	   if($from>$to)
	 	    	   {
	 	         		alert("from date shouldn't greater than To date");
	 	         		$("#datepickerFromDate").focus();
	 	         	}
	     	   }
		    
    	
    	DrillDownService.getUsers(paginationOptions.pageNumber,
    			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
    		if(data.totalElements==0){
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
									}
    	   });
	
   };
 
   $scope.getCountType = function(type){
      
       counttype=type;
       DrillDownService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
				   
					  $scope.gridOptions.data = data.content;
				 	  $scope.gridOptions.totalItems = data.totalElements;
				   });
	}
   
   
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	   		DrillDownService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
	 	    }else{
	 	    	DrillDownService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	   });
	 	    }
	    };

   DrillDownService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	
	 $scope.gridOptions.data = data.content;
 	 $scope.gridOptions.totalItems = data.totalElements;
   });
   
   $scope.gridOptions = {
	paginationPageSizes: [20, 30, 40],
    paginationPageSize: paginationOptions.pageSize,	
	enableColumnMenus:false,
	useExternalPagination: true,	
      
      headerTemplate: 'km/headerTemplate',
      superColDefs: [{
          name: 'front',
          displayName: ''
      }, {
          name: 'LIPI',
          displayName: 'LIPI'
      }, {
          name: 'Forbes',
          displayName: 'Forbes'
      }, {
          name: 'CMS',
          displayName: 'CMS'
      }, {
          name: 'back',
          displayName: ''
      }], 

    columnDefs: [
      { name: 'name',
      	  exporterSuppressExport: true,
      	  headerCellTemplate: '<div>Circle</div>',
      	  superCol: 'front',
      	  cellTemplate: '<div class="ui-grid-cell-contents"><a ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.code)">{{row.entity.name}}</a></div>'
      },
      { name: 'totalSwayamBranches', displayName: 'Total Swayam Branches',superCol: 'front'  },
      { name: 'totalSwayamKiosks', displayName: 'Total Swayam Kiosks',superCol: 'front'  },
      { name: 'lipiKiosks', displayName: 'Kiosks',superCol: 'LIPI'  },
      { name: 'lipiTxns', displayName: 'Txns',superCol: 'LIPI'  },
      { name: 'forbesKiosks', displayName: 'Kiosks',superCol: 'Forbes'  },
      { name: 'forbesTxns', displayName: 'Txns',superCol: 'Forbes'  },
      { name: 'cmsKiosks', displayName: 'Kiosks',superCol: 'CMS'  },
      { name: 'cmsTxns', displayName: 'Txns',superCol: 'CMS'  },
      { name: 'totalSwayamTxns', displayName: 'Swayam Txns',superCol: 'back'  },
      { name: 'totalBranchCounterTxns', displayName: 'Branch Counter Txns',superCol: 'back'  },
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
		//alert("fromDate:: "+fromDate);
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