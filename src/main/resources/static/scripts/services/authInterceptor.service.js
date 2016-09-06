angular.module('MyApp').factory('authInterceptor', function($rootScope, $q) {
  return {
    request: function(config) {
      config.headers = config.headers || {};
      console.log("token", JSON.stringify(window.localStorage.getItem('token')));
      if (window.localStorage.token) {
        var token = window.localStorage.token;
        var baerer = "Bearer " + token.substring(0, token.length);
        console.log(baerer);
        config.headers.Authorization = baerer;
      }
      return config;
    },
    response: function(response) {
      if (response.status === 401) {
      } else if (response.status === 200){
      }
      return response || $q.when(response);
    }
  };
});

angular.module('MyApp').config(function($httpProvider) {
  $httpProvider.interceptors.push('authInterceptor');
});