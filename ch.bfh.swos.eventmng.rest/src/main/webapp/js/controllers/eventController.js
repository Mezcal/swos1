'use strict';

var controllers = angular.module('controllers', ['services']);

function EventController($scope, Event,Act,Location) {
    $scope.currentEvent = new Event();
    $scope.currentEventActs = [];
    $scope.currentEventLocations = [];
    $scope.currentEventInList = new Event();
    $scope.currentEventModalView = new Event();
    $scope.events = Event.query();
    $scope.acts = Act.query();
    $scope.locations = Location.query();
    $scope.modalShown = false;
    $scope.modalConfirmShown = false;
    $scope.delIndex = -1;
	$scope.delId = -1;
    
    /**
     * cancel edit
     */
    $scope.cancel = function () {
        $scope.currentEvent = new Event();
        $scope.currentEventActs = [];
    	$scope.currentEventLocations = [];
    };

    /**
     * save event
     */
    $scope.save = function () {
    	var isNew = $scope.currentEvent.id == null;
        if (isNew) {
            $scope.currentEvent = Event.save($scope.currentEvent);
            $scope.events.push($scope.currentEvent);
        } else {	        	
        	$scope.currentEvent = Event.update($scope.currentEvent);
        	
            //copy the object back to the view (clear arrays to prevent magic javascript errors)
        	$scope.currentEventInList.acts = [];
        	$scope.currentEventInList.locations = [];
        	jQuery.extend(true,$scope.currentEventInList, $scope.currentEvent);
        }
        $scope.cancel();
    };

    /**
     * edit selected event
     */
    $scope.edit = function (event) {
    	$scope.currentEventActs = $scope.loadSelection(event.acts);
    	$scope.currentEventLocations = $scope.loadSelection(event.locations);
    	
    	//copy the object to the edit field
    	$scope.currentEvent = new Event();
    	jQuery.extend(true, $scope.currentEvent, event);
        $scope.currentEventInList = event;
    	
    	//$scope.currentEvent = event;
    };

    /**
     * remove event with confirmation
     */
    $scope.removeWithConfirm = function (index, id) {
    	$scope.modalConfirmShown = true;
    	$scope.delIndex = index;
    	$scope.delId = id;
    };
    
    /**
     * remove event
     */
    $scope.remove = function () {
    	$scope.events.splice($scope.delIndex, 1);
		Event.remove({'id':$scope.delId});
    };
    
    /**
     * show event in modalbox
     */
    $scope.showEvent = function (event) {
    	$scope.currentEventModalView = event;
    	$scope.modalShown = true;
    };
    
    /**
     * select act via checkbox
     */
    $scope.selectAct = function(act){
    	if(jQuery.type($scope.currentEvent.acts) === "undefined"){
    		$scope.currentEvent.acts = [];
    	}
    	
    	if($scope.currentEventActs[act.id]){
    		$scope.currentEvent.acts.push(act);
    	}else{
    		//remove value whit grep-function (return an array without the selected value)
    		$scope.currentEvent.acts = jQuery.grep($scope.currentEvent.acts, function(value) {
        		return value.id != act.id;
        	});
    	} 
    };
    
    /**
     * select act via checkbox
     */
    $scope.selectLocation = function(loc){
    	if(jQuery.type($scope.currentEvent.locations) === "undefined"){
    		$scope.currentEvent.locations = [];
    	}
    	
    	if($scope.currentEventLocations[loc.id]){
    		$scope.currentEvent.locations.push(loc);
    	}else{
    		//remove value whit grep-function (return an array without the selected value)
    		$scope.currentEvent.locations = jQuery.grep($scope.currentEvent.locations, function(value) {
        		return value.id != loc.id;
        	});
    	}
    };
    
    /**
     * load selection
     */
    $scope.loadSelection = function(pItems){
    	var pSelectionArray = [];
    	jQuery.each(pItems,function(){
    		pSelectionArray[this.id] = true;
    	});
    	return pSelectionArray;
    };
}