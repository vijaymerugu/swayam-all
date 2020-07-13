var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 0,
pageSize: 5,
sort: null
   };
 
   var fromDate = "";
   var toDate= "";
   
/*   function convertDate(dateParam){
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
 }
*/


   $scope.CurrentDate = new Date();
   $scope.searchPositions= function(startDate,endDate){
	   fromDate = $("#datepickerFromDate").val();
	   toDate = $("#datepickerToDate").val();
	  
       //alert("From=="+fromDate);

       // alert("toDate=="+toDate);  
  
			 
   UserManagementService.getUsers(paginationOptions.pageNumber,
			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
			 $scope.gridOptions.data = data.content;
			   $scope.gridOptions.totalItems = data.totalElements;
			   });
   
   }
   
   $scope.refresh = function()
   {   if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){

  $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);
   }else{
   
  $scope.gridOptions.data = $scope.gridOptions.data;
   }
   };
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	   		UserManagementService.getUsers(paginationOptions.pageNumber,
	   			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
	   			 $scope.gridOptions.data = data.content;
	   			   $scope.gridOptions.totalItems = data.totalElements;
	   			   });   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
	 	    }else{
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	    			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
	 	    			 $scope.gridOptions.data = data.content;
	 	    			   $scope.gridOptions.totalItems = data.totalElements;
	 	    			   });
	 	    }
	    };


   UserManagementService.getUsers(paginationOptions.pageNumber,
  paginationOptions.pageSize,fromDate,toDate).success(function(data){
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
      }, {
          name: 'total',
          displayName: 'Total'
      }, {
          name: 'back',
          displayName: ''
      }],

    columnDefs: [
      { name: 'crclName', displayName: 'Circle',superCol: 'front'  },
      { name: 'network', displayName: 'NW',superCol: 'front'  },
      { name: 'module', displayName: 'Mode',superCol: 'front'  },
      { name: 'region', displayName: 'Reg',superCol: 'front'  },
      { name: 'branchCode', displayName: 'Branch Code',superCol: 'front'},
      { name: 'branchName', displayName: 'Branch Name',superCol: 'front'  },
      { name: 'lipiKioskCount', displayName: 'No Of Kios',superCol: 'lipi'},
      { name: 'lipiTxnCount', displayName: 'Txn', superCol: 'lipi'},
      { name: 'forbesKioskCount', displayName: 'No Of Kios',superCol: 'Forbes' },
      { name: 'forbesTxnCount', displayName: 'Txn',superCol: 'Forbes'},
      { name: 'cmsKioskCount', displayName: 'No Of Kios',superCol: 'CMS'},
      { name: 'cmsTxnCount', displayName: 'Txn',superCol: 'CMS'},
      { name: 'manualTxn', displayName: 'SWAYAM Txn',superCol: 'total'},
      { name: 'totalSwayamTxn', displayName: 'Branch Txn',superCol: 'total' },
      { name: 'migrationPerc', displayName: 'Migration (%)',superCol: 'back'}
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          UserManagementService.getUsers(newPage,pageSize,fromDate,toDate).success(function(data){
         $scope.gridOptions.data = data.content;
           $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
 
}]);




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


app.service('UserManagementService',['$http', function ($http) {
//alert("123");
function getUsers(pageNumber,size,begin,end) {
//alert("12= fromdate=="+begin);
//alert("13=todate=="+end);
        return  $http({
          method: 'GET',
          url: 'td/dashBoardTxnBM/get?page='+pageNumber+'&size='+size+'&fromdate='+begin+'&todate='+end
        });
    }

    return {
    getUsers:getUsers
    };

}]);