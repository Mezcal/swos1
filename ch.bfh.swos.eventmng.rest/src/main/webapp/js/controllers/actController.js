'use strict';

var controllers = angular.module('controllers', ['services']);

function ActController($scope, Act,ActExt) {
	$scope.currentAct = new Act();
	$scope.currentActInList = new Act();
	$scope.acts = Act.query();
	$scope.actsWithDependencies = ActExt.withDependencies();
	$scope.modalConfirmShown = false;
	$scope.modalHasDependencies = false;
    $scope.delIndex = -1;
	$scope.delId = -1;
	
	/**
     * cancel edit
     */
    $scope.cancel = function () {
        $scope.currentAct = new Act();
    };

    /**
     * save act
     */
    $scope.save = function () {
    	var isNew = $scope.currentAct.id == null;
        if (isNew) {
            $scope.currentAct = Act.save($scope.currentAct);
            $scope.acts.push($scope.currentAct);
        } else {
            $scope.currentAct = Act.update($scope.currentAct);
            
            //copy the object back to the view
        	jQuery.extend(true, $scope.currentActInList,$scope.currentAct);
        }
        $scope.cancel();
    };

    /**
     * edit selected act
     */
    $scope.edit = function (act) {        
    	//copy the object to the edit field
    	jQuery.extend(true, $scope.currentAct, act);
        $scope.currentActInList = act;
    };
    
    /**
     * remove act with confirmation
     */
    $scope.removeWithConfirm = function (index, id) {
    	var result = jQuery.grep($scope.actsWithDependencies, function(value){ 
    		return value.id == id;
    	});
    	
    	if(jQuery.isEmptyObject(result)){
    		$scope.modalConfirmShown = true;
        	$scope.delIndex = index;
        	$scope.delId = id;
    	}else{
    		$scope.modalHasDependencies = true;
    	}
    };

    /**
     * remove act
     */
    $scope.remove = function () {
		$scope.acts.splice($scope.delIndex, 1);
		Act.remove({'id':$scope.delId});
    };
}