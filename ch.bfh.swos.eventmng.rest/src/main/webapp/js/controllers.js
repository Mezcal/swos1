'use strict';

var controllers = angular.module('controllers', ['services']);

function EventController($scope, Event,Act,Location) {
    $scope.currentEvent = new Event();
    $scope.currentEventActs = [];
    $scope.currentEventLocations = [];
    $scope.currentEventInList;
    $scope.events = Event.query();
    $scope.acts = Act.query();
    $scope.locations = Location.query();
    $scope.showId = false;
    $scope.debugMsg = "";
    
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
        //if(eventForm.input.$valid){
	    	var isNew = $scope.currentEvent.id == null;
	        if (isNew) {
	            $scope.currentEvent = Event.save($scope.currentEvent);
	            $scope.events.push($scope.currentEvent);
	        } else {
	            $scope.currentEvent = Event.update($scope.currentEvent);
	            
	            //copy the object back to the view
	        	jQuery.extend(true, $scope.currentEventInList,$scope.currentEvent);
	        }
	        $scope.cancel();
        //}
    };

    /**
     * edit selected event
     */
    $scope.edit = function (event) {
    	$scope.currentEventActs = $scope.loadSelection(event.acts, $scope.currentEventActs);
    	$scope.currentEventLocations = $scope.loadSelection(event.locations, $scope.currentEventLocations);
        
    	//copy the object to the edit field
    	jQuery.extend(true, $scope.currentEvent, event);
        $scope.currentEventInList = event;
    };

    /**
     * remove event
     */
    $scope.remove = function (index, id) {
		$scope.events.splice(index, 1);
		Event.remove({'id':id});
    };
    
    /**
     * select act via checkbox
     */
    $scope.selectAct = function(act){
    	if(jQuery.type($scope.currentEvent.acts) === "undefined"){
    		$scope.debug("init act array!");
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
    		$scope.debug("init location array!");
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
    $scope.loadSelection = function(pItems,pSelectionArray){
    	pSelectionArray = [];
    	jQuery.each(pItems,function(){
    		pSelectionArray[this.id] = true;
    	});
    	return pSelectionArray;
    };
    
    /**
     * debug
     */
    $scope.debug = function(msg){
    	$scope.debugMsg = $scope.debugMsg+" | "+msg;
    };
}