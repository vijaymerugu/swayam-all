var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter', 'ui.grid.resizeColumns']);

app.controller('DrillDownCtrl', ['$scope','$filter','DrillDownService','uiGridConstants', function ($scope, $filter,DrillDownService,uiGridConstants) {
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
    	 $scope.searchPositions= function(startDate,endDate){ debugger;
    	  	 
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
		    
	    //  Added for loader------------- START 
			$("#loading").show(); 
		//  Added for loader------------- END 
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
    	//  Added for loader------------- START 
			$("#loading").hide(); 
		//  Added for loader------------- END 
    	   });
	
   };
 
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
	   		DrillDownService.getUsers(paginationOptions.pageNumber,
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
	 	  	  $scope.gridOptions.paginationCurrentPage = data.number;
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
      }, {
          name: 'LIPI',
          displayName: 'LIPI'
      }, /*{
          name: 'Forbes',
          displayName: 'Forbes'
      },*/ {
          name: 'CMS',
          displayName: 'CMS'
      }, {
          name: 'total',
          displayName: 'Total'
      },
      {
          name: 'back',
          displayName: ''
      }], 

    columnDefs: [
      { name: 'name',
      	  exporterSuppressExport: true,
      	  headerCellTemplate: '<div>Circle</div>',
      	  aggregationLabel : "Total: ",
      	  superCol: 'front',
      	  cellTemplate: '<div class="ui-grid-cell-contents"><a  style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.code)">{{row.entity.name}}</a></div>'
      },
      { name: 'branchCodeCount', displayName: 'Total Swayam Branches',superCol: 'front',aggregationType: uiGridConstants.aggregationTypes.sum , aggregationHideLabel: true, width: '7%'  },
      { name: 'totalSwayamKiosks', displayName: 'Total Swayam Kiosks',superCol: 'front' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%'  },
      { name: 'lipiKiosks', displayName: 'Kiosks',superCol: 'LIPI'  ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%'},
      { name: 'lipiTxns', displayName: 'Txns',superCol: 'LIPI' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%' },
   /*   { name: 'forbesKiosks', displayName: 'Kiosks',superCol: 'Forbes'  ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%'},
      { name: 'forbesTxns', displayName: 'Txns',superCol: 'Forbes' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%' },
   */   { name: 'cmsKiosks', displayName: 'Kiosks',superCol: 'CMS' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%' },
      { name: 'cmsTxns', displayName: 'Txns',superCol: 'CMS' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%' },
      { name: 'totalSwayamTxns', displayName: 'Swayam Txns',superCol: 'total' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%' },
      { name: 'totalBranchCounterTxns', displayName: 'Branch Counter Txns',superCol: 'total' ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true, width: '7%' },
      { name: 'migrationPercentage', displayName: 'Migration Percentage(%)',superCol: 'back'  ,aggregationType: uiGridConstants.aggregationTypes.avg , aggregationHideLabel: true,width: '7%',
       footerCellFilter : 'number : 2'}
    ],
    onRegisterApi: function(gridApi) { debugger;
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize,counttype) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
  	    //  Added for loader------------- START 
			$("#loading").show(); 
		//  Added for loader------------- END 
          DrillDownService.getUsers(newPage,pageSize,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate).success(function(data){
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

var getPage = function(curPage, pageSize, counttype) { debugger;
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