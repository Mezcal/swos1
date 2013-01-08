'use strict';

var services = angular.module('services', ['ngResource']);

/**
 * REST service for Event
 */
services.factory('Event', function ($resource) {
    return $resource('rest/events/:id', {id: '@id'}, {
        'update': {method: 'PUT',params: { id: null }}
    });
});

/**
 * REST service for Act
 */
services.factory('Act', function ($resource) {
    return $resource('rest/acts/:id', {id: '@id'}, {
        'update': {method: 'PUT',params: { id: null }}
    });
});

/**
 * REST service for Location
 */
services.factory('Location', function ($resource) {
    return $resource('rest/locations/:id', {id: '@id'}, {
        'update': {method: 'PUT',params: { id: null }}
    });
});