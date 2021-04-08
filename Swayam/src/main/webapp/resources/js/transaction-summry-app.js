var app = angular.module('app', ['ui.grid','ui.grid.pagination','ngAnimate', 'ngTouch','ui.grid.exporter']);

app.controller('UserManagementCtrl', ['$scope','$filter','UserManagementService','$http', function ($scope, $filter,UserManagementService,$http) {
   var paginationOptions = {
     pageNumber: 1,
pageSize: 20,
sort: null
   };
 
   var fromDate = "";
   var toDate= "";
  
    
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
   //  alert("return --result::: "+result);
     return result;
 }




       

  var validateDate="";   
   $scope.CurrentDate = new Date();
   $scope.searchPositions= function(startDate,endDate){
	
	
	
	/* var req = {
                 method: 'GET',
                 url: 'td/getSwayamMigrationLastUpDated1',               
                 data: $scope.studentList
                };

        $http(req).then(function(response){
            console.log(response.status);
            console.log("in success");
            //$scope.studentList=[];
           console.log(response.data);
           validateDate=response.data;

        }, function(response){
            console.log(response.status);
            console.log("in fail");     
        });*/
    
	
	
	   fromDate = $("#datepickerFromDate").val();
	   toDate = $("#datepickerToDate").val();
	
       var $from=$("#datepickerFromDate").datepicker('getDate');
       var $to =$("#datepickerToDate").datepicker('getDate');
      var $to1 =$("#datepickerToDate").datepicker({ dateFormat: 'dd/mm/yyyy' });
     
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
	
	//alert("$to----"+$to);
	//var valiDate=convertDate(validateDate);
//alert("LastUpdate--------"+validateDate);
  //debugger;
	//var dd1=$to1.val();
	// var $dDate =$("#dd1").datepicker('getDate');
	
		    	/*   if(validateDate<dd1) {
	         		alert("to date shouldn't greater than lastupdate date");
                 toDate="";
                 fromDate="";
                $("#datepickerToDate").focus();
                 }*/
                   if($from>$to){
                    alert("from date shouldn't greater than To date");
	         		$("#datepickerFromDate").focus();
	         	}
    	   }
    	   //  Added for loader------------- START 
   $("#loading").show();  
// Added for loader------------- EN
      	
  UserManagementService.getUsers(paginationOptions.pageNumber,
  paginationOptions.pageSize,fromDate,toDate).success(function(data){
                 //console.log("$scope.gridOptions.data.length=============",$scope.gridOptions.data.length);
   
                    console.log("Response Data " + data.totalElements);	
					$scope.allIndiaDate = "From: " +fromDate+" ToDate: "+toDate; 
						
					 // if(validateDate>toDate)
	    	  // {	
		//alert("inside validateDate--"+validateDate);	
		//    alert("in side toDate--"+toDate);	
									if(data.totalElements==0){
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
										alert("No results found for given search criteria")
									}else{
									
										$scope.gridOptions.data = data.content;
										$scope.gridOptions.totalItems = data.totalElements;
									  
									}
									
								//	} else{
								//	alert("Not Allowed");
									//}
									
								//  Added for loader------------- START 
									$("#loading").hide(); 
								//  Added for loader------------- END 
									/*
  if(data.length==0 || data.length==null) {
       console.log("data=============",data);   
       //console.log("data=====length==========",$scope.gridOptions.data.length);
       alert("No data to displayed.");
       // $scope.noData = true;    
      }else {
       console.log("data=====else==========",data);
       // otherwise the data are updated
       $scope.allIndiaDate = "From: " +fromDate+" ToDate: "+toDate+" CurrentDate::"; 
      // $scope.gridOptions.data = data.content;
     //  $scope.gridOptions.totalItems = data.totalElements;
      
       $scope.gridOptions.data = data.content;
       $scope.gridOptions.totalItems = data.totalElements;
       
        }
        */
      
  });
 
   }
   
   
   $scope.refresh = function()
   {  	
	   	if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
	   	//  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
	   		UserManagementService.getUsers(paginationOptions.pageNumber,
	   			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
	   			 $scope.gridOptions.data = data.content;
	   			   $scope.gridOptions.totalItems = data.totalElements;
	   		//  Added for loader------------- START 
			        $("#loading").hide();  
			     // Added for loader------------- END
	   			   });   
	 		   
	 	    }else if($scope.searchText !=null || $scope.searchText !=undefined || $scope.searchText !=''){
	 	  
	 		  /* $scope.gridOptions.data = $filter('filter')($scope.gridOptions.data, $scope.searchText);	*/	   
	 	    	
	 	    	$("#loading").show(); 
		 	  	 UserManagementService.getSearchNext(paginationOptions.pageNumber,
		 	  			paginationOptions.pageSize,fromDate,toDate,$scope.searchText).success(function(data3){
		 	 	  		 
		 	 	  	  $scope.gridOptions.data = data3.content;
		 	  	   	  $scope.gridOptions.totalItems = data3.totalElements;
		 	  	      $("#loading").hide();
		 	 	     });
	 		   
	 	    }else{
	 	   //  Added for loader------------- START 
		        $("#loading").show();  
		     // Added for loader------------- END
	 	    	UserManagementService.getUsers(paginationOptions.pageNumber,
	 	    			  paginationOptions.pageSize,fromDate,toDate).success(function(data){
	 	    			 $scope.gridOptions.data = data.content;
	 	    			   $scope.gridOptions.totalItems = data.totalElements;
	 	    			//  Added for loader------------- START 
					        $("#loading").hide();  
					     // Added for loader------------- END
	 	    			   });
	 	    }
	    };

	//  Added for loader------------- START 
        $("#loading").show();  
     // Added for loader------------- END
   UserManagementService.getUsers(paginationOptions.pageNumber,
    paginationOptions.pageSize,fromDate,toDate).success(function(data){
  
   $scope.gridOptions.data = data.content;
   $scope.gridOptions.totalItems = data.totalElements;
// Added for loader------------- START 
   $("#loading").hide();  
// Added for loader------------- END
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
      }, /*{
          name: 'Forbes',
          displayName: 'Forbes'
      }, */{
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
          { name: 'network', displayName: 'Network',superCol: 'front'  },
          { name: 'module', displayName: 'Module',superCol: 'front'  },
          { name: 'region', displayName: 'Region',superCol: 'front'  },
          { name: 'branchCode', displayName: 'Branch Code',superCol: 'front'},
          { name: 'branchName', displayName: 'Branch Name',superCol: 'front'  },
          { name: 'lipiKioskCount', displayName: 'No Of Kiosk',superCol: 'lipi'},
          { name: 'lipiTxnCount', displayName: 'Txn', superCol: 'lipi'},
       /*  { name: 'forbesKioskCount', displayName: 'No Of Kiosk',superCol: 'Forbes' },
          { name: 'forbesTxnCount', displayName: 'Txn',superCol: 'Forbes'},*/
          { name: 'cmsKioskCount', displayName: 'No Of Kiosk',superCol: 'CMS'},
          { name: 'cmsTxnCount', displayName: 'Txn',superCol: 'CMS'},
          { name: 'totalSwayamTxn', displayName: 'SWAYAM Txn',superCol: 'total'},
          { name: 'manualTxn', displayName: 'Branch Txn',superCol: 'total' },
          { name: 'migrationPerc', displayName: 'Migration (%)',superCol: 'back'}
        ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
      //  Added for loader------------- START 
	        $("#loading").show();  
	     // Added for loader------------- END
	        if($scope.searchText ==null || $scope.searchText ==undefined || $scope.searchText ==''){
          UserManagementService.getUsers(newPage,pageSize,fromDate,toDate).success(function(data){
         $scope.gridOptions.data = data.content;
           $scope.gridOptions.totalItems = data.totalElements;
       //  Added for loader------------- START 
	        $("#loading").hide();  
	     // Added for loader------------- END
          });
        }
        else{
 	 	   	console.log("Inside else");
        	 UserManagementService.getSearchNext(newPage,pageSize,fromDate,toDate,$scope.searchText).success(function(data){
           	  $scope.gridOptions.data = data.content;
           	 	  $scope.gridOptions.totalItems = data.totalElements;
        
	 	 		 $("#loading").hide();  
	 		   
        	  });	 
        
        	   }
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
	pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'td/dashBoardTxnBM/get?page='+pageNumber+'&size='+size+'&fromdate='+begin+'&todate='+end
        });
    }

function getSearchNext(pageNumber,size,begin,end, searchText) {
	//alert("12= fromdate=="+begin);
	//alert("13=todate=="+end);
	pageNumber = pageNumber > 0?pageNumber - 1:0;
    return  $http({
      method: 'GET',
      url: 'td/dashBoardTxnBM/getSearchNext?page='+pageNumber+'&size='+size+'&fromdate='+begin+'&todate='+end+'&searchText='+searchText
    });
}
    return {
    getUsers:getUsers,
    
    getSearchNext:getSearchNext
    };

}]);