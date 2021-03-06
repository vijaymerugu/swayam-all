var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('DrillDownCtrl', ['$scope','$filter','DrillDownService','uiGridConstants', function ($scope, $filter,DrillDownService,uiGridConstants) {
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
   
   $scope.getCountType = function(type){debugger;
      
	   $scope.counttype=type;
	   alert("counttype2:: "+$scope.counttype);
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
   $scope.backUser = function()
   {  	
	        $("#loading").show();  
	        
	        var str ='td/drillDownBranchBack?circleName='+circleName+'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName+'&fromDate='+fromDate+'&toDate='+toDate;
			$("#contentHomeApp").load(str);
	     
		    $("#loading").hide();  
	 	   
	    };
   
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	
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
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){debugger;
	 	  
	 		 //  $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		  
	 	       $("#loading").show(); 
	 	      DrillDownService.getSearchNext(paginationOptions.pageNumber,
		 	  			paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate,$scope.searchText).success(function(data3){
		 	 	  		 
		 	 	  	  $scope.gridOptions.data = data3.content;
		 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
		 	  	      $("#loading").hide();
		 	  	  
		 	 	     }); 
	 	    }else{
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
	    };
	    $scope.clearSearch = function()
	    {  	debugger;
	 	  
	    	$scope.searchText='';	
	 	   
	 	        $("#loading").show();  
	 	    
	 	       DrillDownService.getUsers(0,
	 	   			paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
	 	  	  $scope.gridOptions.data = data.content;
	 	  //	  $scope.gridOptions.paginationCurrentPage = data.number;
	 	   	  $scope.gridOptions.totalItems = data.totalElements;
	 	   	
	 	        $("#loading").hide();  
	 	     
	 	     }); 
	 	 		   
	 	 	   
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
	showColumnFooter: true, 
      headerTemplate: 'km/headerTemplate',
      superColDefs: [{
          name: 'front',
          displayName: ''
      },{
          name: 'lipi',
          displayName: 'LIPI'
      }, /*{
          name: 'Forbes',
          displayName: 'Forbes'
      }, */ {
          name: 'CMS',
          displayName: 'CMS'
      },  {
          name: 'total',
          displayName: 'Total'
      },{
          name: 'back',
          displayName: ''
      }],

    columnDefs: [
    	 { name: 'name', displayName: 'Branch', aggregationLabel : "Total: ",superCol: 'front'   },
    	 { name: 'branchCodeCount', displayName: 'Total Swayam Branches',superCol: 'front' ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'   },
         { name: 'totalSwayamKiosks', displayName: 'Total Swayam Kiosks',superCol: 'front'  ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'  },
         { name: 'lipiKiosks',width: 90, displayName: 'Kiosks',superCol: 'lipi' ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'  },
         { name: 'lipiTxns',width: 90, displayName: 'Txns',superCol: 'lipi' ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'  },
       /*  { name: 'forbesKiosks',width: 90, displayName: 'Kiosks',superCol: 'Forbes' ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'  },
         { name: 'forbesTxns',width: 90, displayName: 'Txns',superCol: 'Forbes' ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'  },
       */  { name: 'cmsKiosks',width: 90, displayName: 'Kiosks',superCol: 'CMS'  ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%' },
         { name: 'cmsTxns',width: 90, displayName: 'Txns',superCol: 'CMS'  ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%' },
         { name: 'totalSwayamTxns', displayName: 'Swayam Txns',superCol: 'total',aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'   },
         { name: 'totalBranchCounterTxns', displayName: 'Branch Counter Txns',superCol: 'total'  ,aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%' },
         { name: 'migrationPercentage', displayName: 'Migration Percentage(%)',superCol: 'back'  ,aggregationType: uiGridConstants.aggregationTypes.avg , aggregationHideLabel: true,width: '7%',
       footerCellFilter : 'number : 2'}
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
  	    //  Added for loader------------- START 
			$("#loading").show(); 
			if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){debugger;
			//	alert("counttype1:: "+$scope.counttype);
          DrillDownService.getUsers(newPage,pageSize,"BR",circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
      	    //  Added for loader------------- START 
  			$("#loading").hide(); 
  		//  Added for loader------------- END 
          });
			}
	        else{debugger;
	 	 	   //	console.log("Inside else");
	        DrillDownService.getSearchNext(newPage,pageSize,$scope.counttype,circleName,networkName,moduleName,regionName,fromDate,toDate,$scope.searchText).success(function(data){
	        //		alert("counttype2:: "+$scope.counttype);
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
	function getSearchNext(pageNumber,size,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate, searchText) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'drillDown/getSearchNext?page='+pageNumber+'&size='+size+'&type='+counttype+'&circleName='+circleName
          +'&networkName='+networkName+'&moduleName='+moduleName+'&regionName='+regionName
          +'&fromDate='+fromDate+'&toDate='+toDate+'&searchText='+searchText
        });
    }
	
    return {
    	getUsers:getUsers,
    	
    	getSearchNext:getSearchNext
    	
    };
	
}]);