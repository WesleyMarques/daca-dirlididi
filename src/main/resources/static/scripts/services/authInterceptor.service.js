angular.module('MyApp').factory('authInterceptor', function($rootScope, $q) {
  return {
    request: function(config) {
      config.headers = config.headers || {};
      console.log("tokenlocal", window.localStorage.token);
      if (window.localStorage.token) {
        config.headers.Authorization = 'Bearer ' + window.localStorage.token;
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