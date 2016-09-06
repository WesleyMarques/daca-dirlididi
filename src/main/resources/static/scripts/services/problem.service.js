angular
  .module('MyApp')
  .service('Problem', problemService);

problemService.$inject = ['$http'];

function problemService($http) {

  var Service = {};
  Service.data = [];
  Service.selected = {}
  Service.get = get;
  Service.update = update;
  Service.create = create;
  Service.refresh = refresh;

  return Service;

  function refresh() {
    $http.get('/api/problem')
      .success(function(data) {
        console.log(data);
        Service.data = data;
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }

  function get(id) {
    $http.get('/api/problem/' + id)
      .success(function(data) {
        console.log(data);
        Service.selected = data;
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }

  function update(data) {
    $http.put('/api/problem/' + id, data)
      .success(function(data) {
        console.log(data);
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }

  function create(data) {
    $http.post('/api/problem/', data)
      .success(function(data) {
        console.log(data);
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }
}