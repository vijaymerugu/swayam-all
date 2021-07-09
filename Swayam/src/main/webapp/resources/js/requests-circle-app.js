angular.element(document).ready(function() {	
var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter','ui.grid.selection', 'ui.grid.resizeColumns']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService', function ($scope, $filter,UserManagementService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 20,
	 sort: null
   };
   
    var counttype = "";
   
$scope.ddMMyyyy = $filter('date')(new Date(), 'dd-MM-yyyy');
console.log("$scope.ddMMyyyy::::::::"+$scope.ddMMyyyy );

 function convertDate(dateParam){
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
     //alert("return --result::: "+result);
     return result;
 }

		    $scope.activateCheck = function (status,toDate){
		      var sysDate=convertDate(toDate);
		      console.log("in side activateCheck::$scope.ddMMyyyy::"+$scope.ddMMyyyy);
		      console.log("in side activateCheck::status::"+status+" sysDate::"+sysDate);
		    	if(status=='APRD' && sysDate>$scope.ddMMyyyy){
		    	console.log("in side if::status::"+sysDate+"status::"+status);
		    		return true;
		    	}else{
		    	console.log("in side else"+status);
		    		return false;
		    	}
		    }

     $scope.loadHomeBodyPageFormsUpdate = function(url,kioskId){	
          alert(""+kioskId+" Activated");   
      if(url != undefined){	
			var str ='hm/activateCmsCaseId?caseId=' + url;
			$("#contentHomeApp").load(str);
		}	
	}
   
   $scope.loadHomeBodyPageForms = function(url){	   
		if(url != undefined){	
			var str ='hm/viewCircleCaseId?caseId=' + url;
			$("#contentHomeApp").load(str);
		}						
	}
   $scope.getCountType = function(type){
	   UserManagementService.getUsers(paginationOptions.pageNumber,
			   paginationOptions.pageSize,counttype);
	}
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){	   
	 	   UserManagementService.getUsers(paginationOptions.pageNumber,
	 			   paginationOptions.pageSize,counttype).success(function(data){
	 		  $scope.gridOptions.data = data.content;
	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	   });	   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		   $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);		   
	 		   
	 	    }else{
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	 			   paginationOptions.pageSize,counttype).success(function(data){
	 	 		  $scope.gridOptions.data = data.content;
	 	 	 	  $scope.gridOptions.totalItems = data.totalElements;
	 	 	   });
	 	    }
	    };

   UserManagementService.getUsers(paginationOptions.pageNumber,
		   paginationOptions.pageSize,counttype).success(function(data){
	  $scope.gridOptions.data = data.content;
 	  $scope.gridOptions.totalItems = data.totalElements;
   });
   
   $scope.showDate = function(value)
   {
     return new Date(value);
   }
   
  $scope.gridOptions = {
	/*paginationPageSizes: [20, 30, 40],*/
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,
 /*
    columnDefs: [
      { name: 'id', displayName: 'Case Id', width:170,
    	  //cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" href="/hm/viewCaseId?caseId={{ row.entity.id }}">{{ row.entity.id }}</a></div>'
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.id)">{{ row.entity.id }}</a></div>'  
      },
      { name: 'kioskId', displayName: 'Kiosk Id', width:200  },
      { name: 'modifiedDate', width:250, displayName: 'Request Date Time',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
    	  //cellTemplate:'<div class="ui-grid-cell-contents">{{grid.appScope.showDate(row.entity.modifiedDate)}}</div>'
    		  },
      { name: 'modifiedBy', displayName: 'Request By', width:220  },
      { name: 'comments', headerCellTemplate: '<div>Comments By <br/> Checker/Approver</div>', width:400  },
      { name: 'reqCategory',
    	  exporterSuppressExport: true, width:200,
    	  headerCellTemplate: '<div>Status</div>',
    	cellTemplate: '<div ng-if="row.entity.reqCategory == \'APRD\'">APPROVED</div><div ng-if="row.entity.reqCategory == \'REJ\'">REJECTED</div><div ng-if="row.entity.reqCategory == \'RCMD\'">RECOMMENDED</div>'
      },
      { name: 'fromDate', width:200,type: 'date', cellFilter: 'date:"dd-MM-yy"',
    	  headerCellTemplate: '<div>From Date</div>',
      },
      { name: 'toDate',type: 'date',cellFilter: 'date:"dd-MM-yy"',
    	  width:200,
    	  headerCellTemplate: '<div>To Date</div>',
      },
      {   
         name: 'Active',
    	 headerCellTemplate: '<div></div>',width:200,
    	 // cellTemplate: '<div ng-if="row.entity.reqCategory == \'APRD\'  "  ><div><input type="button" ng-click="grid.appScope.loadHomeBodyPageForms1(row.entity.id,row.entity.kioskId,row.entity.toDate)" id="button" class="button" value="Activate" id="active" /></div> </div>'
         cellTemplate: '<div ng-show="grid.appScope.activateCheck(row.entity.reqCategory,row.entity.toDate) "><div><input type="button" ng-click="grid.appScope.loadHomeBodyPageFormsUpdate(row.entity.id,row.entity.kioskId)" id="button" class="button" value="Activate" id="active" style=" background-color: #FDD209; border-top: 2px #FDD209;border-bottom-width: 4px #FDD209; left: 579px; width: 97px;  height: 32px;opacity: 1;" /></div> </div>'
     
      },
    ],*/
    
   

    columnDefs: [
      { name: 'id', displayName: 'Case Id',
    	  //cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" href="/hm/viewCaseId?caseId={{ row.entity.id }}">{{ row.entity.id }}</a></div>'
    	  cellTemplate: '<div class="ui-grid-cell-contents"><a style="cursor: hand;cursor: pointer;" ng-click="grid.appScope.loadHomeBodyPageForms(row.entity.id)">{{ row.entity.id }}</a></div>'  
      },
      { name: 'kioskId', displayName: 'Kiosk Id'},
      { name: 'modifiedDate',width:180, displayName: 'Request Date Time',type: 'date',cellFilter: 'date:"dd-MM-yyyy hh:mm:ss a"'
    	  //cellTemplate:'<div class="ui-grid-cell-contents">{{grid.appScope.showDate(row.entity.modifiedDate)}}</div>'
    		  },
      { name: 'modifiedBy', displayName: 'Request By' },
      { name: 'comments', headerCellTemplate: '<div>Comments By <br/> Checker/Approver</div>'  },
      { name: 'reqCategory',
    	  exporterSuppressExport: true,
    	  headerCellTemplate: '<div>Status</div>',
    	cellTemplate: '<div ng-if="row.entity.reqCategory == \'APRD\'">APPROVED</div><div ng-if="row.entity.reqCategory == \'REJ\'">REJECTED</div><div ng-if="row.entity.reqCategory == \'RCMD\'">RECOMMENDED</div>'
      },
      { name: 'fromDate',type: 'date', cellFilter: 'date:"dd-MM-yy"',
    	  headerCellTemplate: '<div>From Date</div>',
      },
      { name: 'toDate',type: 'date',cellFilter: 'date:"dd-MM-yy"',
    	  headerCellTemplate: '<div>To Date</div>',
      },
      {   
         name: 'Active',
    	 headerCellTemplate: '<div></div>',width:200,
    	 // cellTemplate: '<div ng-if="row.entity.reqCategory == \'APRD\'  "  ><div><input type="button" ng-click="grid.appScope.loadHomeBodyPageForms1(row.entity.id,row.entity.kioskId,row.entity.toDate)" id="button" class="button" value="Activate" id="active" /></div> </div>'
         cellTemplate: '<div ng-show="grid.appScope.activateCheck(row.entity.reqCategory,row.entity.toDate) "><div><input type="button" ng-click="grid.appScope.loadHomeBodyPageFormsUpdate(row.entity.id,row.entity.kioskId)" id="button" class="button" value="Activate" id="active" style=" background-color: #FDD209; border-top: 2px #FDD209;border-bottom-width: 4px #FDD209; left: 579px; width: 97px;  height: 32px;opacity: 1;" /></div> </div>'
     
      },
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          UserManagementService.getUsers(newPage,pageSize,counttype).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
  
}]);


app.service('UserManagementService',['$http', function ($http) {
	
	function getUsers(pageNumber,size,counttype) {	
		//alert("size"+ size);	
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'hm/requestsCircle/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getUsers:getUsers
    };
	
}]);

angular.bootstrap(document.getElementById("appId"), ['app']);
});