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
    $scope.debugMsg = "";
    $scope.modalShown = false;
    
    /**
     * cancel edit
     */
    $scope.cancel = function () {
        $scope.currentEvent = new Event();
        $scope.currentEventActs = [];
    	$scope.currentEventLocations = [];
    	
    	$scope.debug("#nach cancel:"); 
    	jQuery.each($scope.currentEventInList.acts,function(){
    		$scope.debug(this.name);
    	});
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
	    		$scope.debug("#vor save:"); 
	        	jQuery.each($scope.currentEvent.acts,function(){
	        		$scope.debug(this.name);
	        	});
	        	
	        	$scope.currentEvent = Event.update($scope.currentEvent);
	        	
	            //copy the object back to the view (clear arrays to prevent magic javascript errors)
	        	$scope.currentEventInList.acts = [];
	        	$scope.currentEventInList.locations = [];
	        	jQuery.extend(true,$scope.currentEventInList, $scope.currentEvent);
	        }
	        $scope.cancel();
        //}
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
     * remove event
     */
    $scope.remove = function (index, id) {
		$scope.events.splice(index, 1);
		Event.remove({'id':id});
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
    $scope.loadSelection = function(pItems){
    	var pSelectionArray = [];
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

function ActController($scope, Act) {
	$scope.currentAct = new Act();
	$scope.currentActInList = new Act();
	$scope.acts = Act.query();
	
	/**
     * cancel edit
     */
    $scope.cancel = function () {
        $scope.currentAct = new Act();
    };

    /**
     * save event
     */
    $scope.save = function () {
        //if(eventForm.input.$valid){
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
        //}
    };

    /**
     * edit selected event
     */
    $scope.edit = function (act) {        
    	//copy the object to the edit field
    	jQuery.extend(true, $scope.currentAct, act);
        $scope.currentActInList = act;
    };

    /**
     * remove event
     */
    $scope.remove = function (index, id) {
		$scope.acts.splice(index, 1);
		Act.remove({'id':id});
    };
}

function LocationController($scope, Location) {
	$scope.currentLocation = new Location();
	$scope.currentLocationInList = new Location();
	$scope.locations = Location.query();
	
	/**
     * cancel edit
     */
    $scope.cancel = function () {
        $scope.currentLocation = new Location();
    };

    /**
     * save event
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
     * edit selected event
     */
    $scope.edit = function (location) {        
    	//copy the object to the edit field
    	jQuery.extend(true, $scope.currentLocation, location);
        $scope.currentLocationInList = location;
    };

    /**
     * remove event
     */
    $scope.remove = function (index, id) {
		$scope.locations.splice(index, 1);
		Location.remove({'id':id});
    };
}