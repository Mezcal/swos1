'use strict';

var controllers = angular.module('controllers', ['services']);

function LocationController($scope, Location) {
	$scope.currentLocation = new Location();
	$scope.currentLocationInList = new Location();
	$scope.locations = Location.query();
	$scope.modalConfirmShown = false;
    $scope.delIndex = -1;
	$scope.delId = -1;
	
	/**
     * cancel edit
     */
    $scope.cancel = function () {
        $scope.currentLocation = new Location();
    };

    /**
     * save location
     */
    $scope.save = function () {
        //if(eventForm.input.$valid){
	    	var isNew = $scope.currentLocation.id == null;
	        if (isNew) {
	            $scope.currentLocation = Location.save($scope.currentLocation);
	            $scope.locations.push($scope.currentLocation);
	        } else {
	            $scope.currentLocation = Location.update($scope.currentLocation);
	            
	            //copy the object back to the view
	        	jQuery.extend(true, $scope.currentLocationInList,$scope.currentLocation);
	        }
	        $scope.cancel();
        //}
    };

    /**
     * edit selected location
     */
    $scope.edit = function (location) {        
    	//copy the object to the edit field
    	jQuery.extend(true, $scope.currentLocation, location);
        $scope.currentLocationInList = location;
    };
    
    /**
     * remove location with confirmation
     */
    $scope.removeWithConfirm = function (index, id) {
    	$scope.modalConfirmShown = true;
    	$scope.delIndex = index;
    	$scope.delId = id;
    };

    /**
     * remove location
     */
    $scope.remove = function () {
		$scope.locations.splice($scope.delIndex, 1);
		Location.remove({'id':$scope.delId});
    };
}