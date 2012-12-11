'use strict';

var controllers = angular.module('controllers', ['services']);

function EventController($scope, Event) {
    $scope.currentEvent = new Event();
    $scope.events = Event.query();
    $scope.showId = false;

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
        $scope.currentEvent = event;
    };

    $scope.remove = function (index, id) {
		$scope.events.splice(index, 1);
		Event.remove({'id':id});
    };
}