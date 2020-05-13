<div class="ui-grid-header custom-ui-grid-header">
    <div class="ui-grid-top-panel">
        <div class="ui-grid-header-viewport">
            <div class="ui-grid-header-canvas" style="width: 100%">
                <div class="ui-grid-header-cell-wrapper" style="display: block;" ng-style="colContainer.headerCellWrapperStyle()">
                    <div class="ui-grid-header-cell-row" style="display: block; border-bottom: 1px solid;border-bottom-color: #d4d4d4;">
                        <div  class="ui-grid-header-cell" ng-repeat="superCol in grid.options.superColDefs track by $index" col-name="{{superCol.name}}">
                            <div class="ui-grid-cell-contents" data-ng-bind="superCol.displayName">
                            </div>
                        </div>
                    </div>
                    <div class="ui-grid-header-cell-row">
                        <div class="ui-grid-header-cell ui-grid-clearfix" 
                        			ng-repeat="col in colContainer.renderedColumns track by col.colDef.name" 
                        			ui-grid-header-cell col="col" super-col-width-update render-index="$index">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
