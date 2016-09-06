'use strict';

angular.module('MyApp', ['ngRoute', 'datatables'])
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
        .when('/problems', {
          templateUrl: '/templates/list_problem.html',
          controller: 'ProblemCtrl',
          controllerAs: 'pc'
        })
        .when('/problem/create', {
          templateUrl: '/templates/create_problem.html',
          controller: 'ProblemCtrl',
          controllerAs: 'pc'
        })
        .when('/problem/:id/edit', {
          templateUrl: '/templates/edit_problem.html',
          controller: 'ProblemCtrl',
          controllerAs: 'pc'
        })
        .when('/ide', {
          templateUrl: '/templates/ide.html',
          controller: 'ProblemCtrl',
          controllerAs: 'pc'
        })
        .otherwise({
            redirectTo: '/login'
        });

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}
