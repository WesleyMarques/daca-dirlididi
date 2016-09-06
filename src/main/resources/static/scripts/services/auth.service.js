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

  function login(data, scope) {
    return $http.post('/login', data)
      .success(function(data) {
        console.log(data);
        window.localStorage.setItem('token', data);
        window.location.href = "/";
        Service.token = data;
      })
      .error(function(data) {
        scope.error = data.message;
        console.log(data);
      });
  }

  function logout() {
    $http.post('/logout')
      .success(function(data) {
        console.log(data);
        Service.token = "";
      })
      .error(function(data) {
        console.log(data);
      });
  }

  function isAuthenticated() {
    return !!window.localStorage.token;
  }
}