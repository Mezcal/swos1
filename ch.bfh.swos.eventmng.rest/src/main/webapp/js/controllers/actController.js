'use strict';

var controllers = angular.module('controllers', ['services']);

function ActController($scope, Act) {
	$scope.currentAct = new Act();
	$scope.currentActInList = new Act();
	$scope.acts = Act.query();
	$scope.modalConfirmShown = false;
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
    	$scope.modalConfirmShown = true;
    	$scope.delIndex = index;
    	$scope.delId = id;
    };

    /**
     * remove act
     */
    $scope.remove = function () {
		$scope.acts.splice($scope.delIndex, 1);
		Act.remove({'id':$scope.delId});
    };
    
    /**
     * debug
     */
    $scope.debug = function(msg){
    	$scope.debugMsg = $scope.debugMsg+" | "+msg;
    };
}