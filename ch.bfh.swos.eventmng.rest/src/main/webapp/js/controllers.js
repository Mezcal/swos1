'use strict';

var controllers = angular.module('controllers', ['services']);

function EventController($scope, Event,Act,Location) {
    $scope.currentEvent = new Event();
    $scope.currentEventActs = {};
    $scope.currentEventLocations = {};
    $scope.events = Event.query();
    $scope.acts = Act.query();
    $scope.locations = Location.query();
    $scope.showId = false;
    $scope.debugMsg = "";
    
    $scope.cancel = function () {
        $scope.currentEvent = new Event();
    };

    $scope.save = function () {
        var isNew = $scope.currentEvent.id == null;
        if (isNew) {
            $scope.currentEvent = Event.save($scope.currentEvent);
            $scope.events.push($scope.currentEvent);
        } else {
            $scope.currentEvent = Event.update($scope.currentEvent);
        }
        $scope.cancel();
    };

    $scope.edit = function (event) {
    	$scope.currentEventActs = $scope.loadSelection(event.acts, $scope.currentEventActs);
    	$scope.currentEventLocations = $scope.loadSelection(event.locations, $scope.currentEventLocations);
        $scope.currentEvent = event;
    };

    $scope.remove = function (index, id) {
		$scope.events.splice(index, 1);
		Event.remove({'id':id});
    };
    
    $scope.selectAct = function(act){
    	$scope.debug("selected");
    	return true;
    };
    
    /**
     * load selection
     */
    $scope.loadSelection = function(pItems,pSelectionArray){
    	pSelectionArray = {};
    	jQuery.each(pItems,function(){
    		pSelectionArray[this.id] = true;
    	});
    	return pSelectionArray;
    };
    
    /**
     * check if ID is the array
     */
    $scope.inArrayId = function (pArray,pId){
    	result = jQuery.grep(pArray, function(value) {
    		return value.id == pId;
    	});
    	return result.length === 0;
    };
    
    /**
     * debug
     */
    $scope.debug = function(msg){
    	$scope.debugMsg = $scope.debugMsg+" | "+msg;
    };
}