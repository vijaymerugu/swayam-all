var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('DrillDownCtrl', ['$scope','$filter','DrillDownService', function ($scope, $filter,DrillDownService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 5,
	 sort: null
   };
   
   var counttype = "";
//   var circleName="";
//   var networkName="";
//   var moduleName="";
   var circleName = document.getElementById("circleName").value;
   alert("Circle : "+document.getElementById("circleName").value);
   var networkName = document.getElementById("networkName").value;
   alert("Network : "+document.getElementById("networkName").value);
   var moduleName = document.getElementById("moduleName").value;
   alert("Module : "+document.getElementById("moduleName").value);
   var regionName = document.getElementById("regionName").value;
   alert("Region :"+document.getElementById("regionName").value);
   
    /*$scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='/td/drillDownFifth?userId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}*/
   /* $scope.loadHomeBodyPageFormsDel = function(url){	   
		if(url != undefined){	
			var str ='/km/deleteUserMaster?userId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}*/
   $scope.getCountType = function(type){
      
       counttype=type;
       DrillDownService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName).success(function(data){
				   
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
		   paginationOptions.pageSize,counttype,circleName,networkName,moduleName,regionName).success(function(data){
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
    	 { name: 'branchName', displayName: 'Branch'  },
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
          DrillDownService.getUsers(newPage,pageSize,counttype,circleName,networkName,moduleName,region).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);


app.service('DrillDownService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype,circleName,networkName,moduleName,regionName,fromDate,toDate) {
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