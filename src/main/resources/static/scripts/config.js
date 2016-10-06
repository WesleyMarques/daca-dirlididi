'use strict';

angular.module('MyApp', ['ngRoute', 'firebase'])
    .config(['$routeProvider', '$locationProvider', '$interpolateProvider', configApp]);

function configApp($routeProvider, $locationProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '/templates/welcome.html',
            controller: 'MainCtrl',
            controllerAs: 'mc',
            resolve: {
                access: ["Auth", function (Auth) { return Auth.isAuthenticated(); }],
            }
        })
        .when('/login', {
            templateUrl: '/templates/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'lc',
            resolve: {
                access: ["Auth", function (Auth) { return !Auth.isAuthenticated(); }],
            }
        })
        .when('/problems', {
            templateUrl: '/templates/list_problem.html',
            controller: 'ProblemCtrl',
            controllerAs: 'pc',
            resolve: {
                access: ["Auth", function (Auth) { return Auth.isAuthenticated(); }],
            }
        })
        .when('/problem/create', {
            templateUrl: '/templates/create_problem.html',
            controller: 'ProblemCtrl',
            controllerAs: 'pc',
            resolve: {
                access: ["Auth", function (Auth) { return Auth.isAuthenticated(); }],
            }
        })
        .when('/problem/:id/edit', {
            templateUrl: '/templates/edit_problem.html',
            controller: 'ProblemCtrl',
            controllerAs: 'pc',
            resolve: {
                access: ["Auth", function (Auth) { return Auth.isAuthenticated(); }],
            }
        })
        .when('/ide', {
            templateUrl: '/templates/ide.html',
            controller: 'IdeCtrl',
            controllerAs: 'ic',
            resolve: {
                access: ["Auth", function (Auth) { return Auth.isAuthenticated(); }],
            }
        })
        .otherwise({
            redirectTo: '/login'
        });

    //$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}
