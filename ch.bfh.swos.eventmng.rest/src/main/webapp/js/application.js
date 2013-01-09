'use strict';

var eventmng = angular.module('eventmng', ['controllers', 'services','directives']).config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl: 'views/home.html'
	}).
	when('/acts', {
		templateUrl: 'views/acts.html'
	}).
	when('/locations', {
		templateUrl: 'views/locations.html'
	}).
	when('/events', {
		templateUrl: 'views/events.html'
	}).
      otherwise({redirectTo: '/'});
}]);

