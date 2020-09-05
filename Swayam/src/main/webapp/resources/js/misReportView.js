var app = angular.module('misReportViewModule', ['aw-picklist']);

angular.module('aw-picklist', [])
.directive('pickList', PickList);

function PickList() {
	var defaults = {};
    
	return {
    	scope: {
        	options: '=',
            result: '='
        },
        templateUrl: 'component/pickListTmpl.html',
    	link: function(scope, element, attrs) {
        
        	var opts = angular.extend({}, defaults, scope.options);
			
            //var $el = $(element).multiselect(opts); // jquery plugin not required
            
            function copy(pickListSelect, source, target) {
            	// add data to new list
                var i, id;
                // copy to new lsit & remove old element
                for(i=0; i< pickListSelect.length; i++) {
                	id=pickListSelect[i].id;
                	target.data[id] = target.data[id] || {};
                    angular.extend(target.data[id], pickListSelect[i]);
				
                    delete source.data[pickListSelect[i].id];
                }
				
                pickListSelect = [];
            }
            
            angular.extend(scope, {
                pickListSelect: [],
                pickListResultSelect: [],
                result: {
                    data: {}
                },
            	add: function() {
                	var id;
                	// copy(selection, source, target)
                    copy(scope.pickListSelect, scope.options, scope.result);
                },
                addAll: function() {
                	angular.merge(scope.result.data, scope.options.data);
                    scope.options.data = {};
                },
                remove: function() {
                	copy(scope.pickListResultSelect, scope.result, scope.options);
                },
                removeAll: function() {
                	angular.merge(scope.options.data, scope.result.data);
                    scope.result.data = {};
                }
            });
        }
    };
}

app.controller('misReportViewController', ['$scope','misReportViewService1', 'misReportViewService2', 'misReportViewService3',
                                 function ($scope, misReportViewService1, misReportViewService2, misReportViewService3) {

//Grouping Criteria List
	$scope.groupingCriteriaList = [];
	callGroupingCriteriaList();
	function callGroupingCriteriaList(){
	misReportViewService1.loadGroupingCriteriaList()
	.success(function(response){
		angular.forEach(response, function(value, key) {
			  $scope.groupingCriteriaList.push({'name': value.groupName, 'value': value.groupId})
			});
		$scope.selectedGroupingCriteria = {
			     'criteria': $scope.groupingCriteriaList[0]
			  };
	});
}
	
$scope.allColumns = {};
callAvailableColumns();
function callAvailableColumns(){
misReportViewService2.loadAvailableColumns()
.success(function(response){
		$scope.allCols = {};
		var i = 1;
		angular.forEach(response, function(value, key){
			$scope.allColumns[""+i+""] = {"id": value.columnId, "text": value.columnName}
			i++;
		});
		//Available Columns
		$scope.options = {
	    	data: $scope.allColumns
	    };
	});
}

	//Selected Columns
	$scope.resultList = {
    };
	
	//Report Type List
	$scope.reportTypeList = [
     {
    	 'name': 'PDF',
    	 'value': 'PDF'
     },
     {
    	 'name': 'EXCEL',
    	 'value': 'EXCEL'
     }
     ];
	$scope.selectedReportType = {
		     'reportType': $scope.reportTypeList[0]
		  };
	
	function stringToDate(_date,_format,_delimiter) {
        var formatLowerCase=_format.toLowerCase();
        var formatItems=formatLowerCase.split(_delimiter);
        var dateItems=_date.split(_delimiter);
        var monthIndex=formatItems.indexOf("mm");
        var dayIndex=formatItems.indexOf("dd");
        var yearIndex=formatItems.indexOf("yyyy");
        var year = parseInt(dateItems[yearIndex]); 
        // adjust for 2 digit year
        if (year < 100) { year += 2000; }
        var month=parseInt(dateItems[monthIndex]);
        month-=1;
        var formatedDate = new Date(year,month,dateItems[dayIndex]);
        return formatedDate;
}
	
	$scope.generateReport = function(){

		if($('#datepickerFromDate').val() == '' || $('#datepickerToDate').val() == '') {
			alert('From date & To date can not be null.');
		} else {
			
		var fromDate = stringToDate($('#datepickerFromDate').val(), 'dd-mm-yyyy', '-');
		var toDate = stringToDate($('#datepickerToDate').val(), 'dd-mm-yyyy', '-');
		
		if (fromDate > toDate){
			alert('From date must be smaller than To date.');
		} else {
			var length = Object.keys($scope.resultList.data).length; //you get length json result 4
			
			if(length < 1) {
					alert('Select at least one column.');
			} else {
				var lastKey = Object.keys($scope.resultList.data).reverse()[0];
				var lastValue = $scope.resultList.data[lastKey].id;
				var selectedColumnIndexes=null;
				// ITERATE THROUGH ITEMS IN OBJECT.
		        angular.forEach($scope.resultList.data, function (value, key) {
		        	if(value.id != lastValue) {
		        		selectedColumnIndexes = selectedColumnIndexes + value.id + ',';	
		        	}else {
		        		selectedColumnIndexes = selectedColumnIndexes + value.id;
		        	}
		        	
		        });
				callMisReportData($('#datepickerFromDate').val(), $('#datepickerToDate').val(),
						$scope.selectedGroupingCriteria.criteria.value, $scope.selectedGroupingCriteria.criteria.name,
						selectedColumnIndexes, $scope.selectedReportType.reportType.value);
			}
		}//End of Valid Date Else
		
		}//End of From Date & To Date Null Check IF
		
	}; //End of generateReport()

	$scope.reset = function (){
		$('#datepickerFromDate').val('');
		$('#datepickerToDate').val('');
		$scope.selectedGroupingCriteria = {
			     'criteria': $scope.groupingCriteriaList[0]
		};
		$scope.resultList = {
	    };
		$scope.selectedReportType = {
			     'reportType': $scope.reportTypeList[0]
		};
		$scope.allColumns = {};
		callAvailableColumns();

	};
	//Start of loadMisReportData service
	function callMisReportData(fromDate, toDate, groupingCriteriaId, groupingCriteriaName, selectedColumnIndexes, reportType){
		misReportViewService3.loadMisReportData(fromDate, toDate, groupingCriteriaId, groupingCriteriaName, selectedColumnIndexes, reportType)
		.success(function(response, status, headers, config){

			var filename = "";
		    var disposition = headers('Content-Disposition');
		    if (disposition && disposition.indexOf('attachment') !== -1) {
		        var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
		        var matches = filenameRegex.exec(disposition);
		        if (matches != null && matches[1]) { 
		          filename = matches[1].replace(/['"]/g, '');
		          saveAs(response, filename);// This is from FileSaver.js
		        }
		    }
		});
	}//End of loadMisReportData service
	}
]);//End of Controller


app.service('misReportViewService1',['$http', function ($http) {
	function loadGroupingCriteriaList() {
	    return  $http({
	      method: 'GET',
	      url: 'mis/get-grouping-criteria'
	    });
	}
	return {
		loadGroupingCriteriaList:loadGroupingCriteriaList
	};
}]);
app.service('misReportViewService2',['$http', function ($http) {
	function loadAvailableColumns() {
	    return  $http({
	      method: 'GET',
	      url: 'mis/get-available-columns'
	    });
	}
	return {
		loadAvailableColumns:loadAvailableColumns
	};
}]);
app.service('misReportViewService3',['$http', function ($http) {
    function loadMisReportData(fromDate, toDate, groupingCriteriaId, groupingCriteriaName, selectedColumnIndexes, reportType) {
        return  $http({
          method: 'GET',
          url: 'mis/generate-report?fromDate='+fromDate+'&toDate='+toDate
          +'&groupingCriteriaId='+groupingCriteriaId+'&groupingCriteriaName='+groupingCriteriaName
          +'&selectedColumnIndexes='+selectedColumnIndexes+'&reportType='+reportType,
          responseType: 'blob'
        });
    }
    return {
    	loadMisReportData:loadMisReportData
    };
}]);
