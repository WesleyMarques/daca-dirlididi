'use strict';

angular.module('MyApp', ['ngRoute'])
    .config(['$routeProvider', '$locationProvider', '$interpolateProvider', configApp]);

function configApp($routeProvider, $locationProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '/templates/welcome.html',
            controller: 'MainCtrl',
            controllerAs: 'mc'
        })
        .when('/login', {
            templateUrl: '/templates/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'lc'
        })
        .otherwise({
            redirectTo: '/login'
        });

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}
