angular
  .module('MyApp')
  .service('Statistics', statisticsService);

statisticsService.$inject = ['$http'];

function statisticsService($http) {

  var Service = {};
  Service.data = [];
  Service.refresh = refresh;

  return Service;

  function refresh() {
    $http.get('/api/statistics')
      .success(function(data) {
        console.log(data);
        Service.data = data;
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }
}