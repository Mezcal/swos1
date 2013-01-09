'use strict';

var eventmng = angular.module('eventmng', ['controllers', 'services','directives']).config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl: 'partials/home.html'
	}).
	when('/acts', {
		templateUrl: 'partials/acts.html'
	}).
	when('/locations', {
		templateUrl: 'partials/locations.html'
	}).
	when('/events', {
		templateUrl: 'partials/events.html'
	}).
      otherwise({redirectTo: '/'});
}]);

