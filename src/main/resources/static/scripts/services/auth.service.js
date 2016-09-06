angular
    .module('MyApp')
    .service('Auth', authService);

authService.$inject = ['$http'];

function authService($http) {

    var Service = {};
    Service.token = "";
    Service.login = login;
    Service.isAuthenticated = isAuthenticated;

    return Service;

    function login(data) {
       return $http.post('/login', data);            
    }

    function logout() {
        $http.post('/logout')
            .success(function(data) {
                console.log(data);
                Service.token = "";
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    }

    function isAuthenticated() {
        return !!window.localStorage.token;
    }
}