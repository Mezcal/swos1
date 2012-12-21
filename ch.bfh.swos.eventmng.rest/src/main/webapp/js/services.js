'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('Event', function ($resource) {
    return $resource('rest/events/:id', {id: '@id'}, {
        'update': {method: 'PUT',params: { id: null }}
    });
});