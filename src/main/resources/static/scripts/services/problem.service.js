angular
  .module('MyApp')
  .service('Problem', problemService);

problemService.$inject = ['$http', '$location'];

function problemService($http, $location) {

  var Service = {};
  Service.data = [];
  Service.selected = {}
  Service.get = get;
  Service.update = update;
  Service.create = create;
  Service.delete = remove;
  Service.submit = submit;
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
    $http.put('/api/problem/' + data.id, data)
      .success(function(data) {
        console.log(data);
        alert(data.message);
        $location.path('/problems');
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }

  function create(data) {
    $http.post('/api/problem/', data)
      .success(function(data) {
        console.log(data);
        alert(data.message);
        $location.path('/problems');
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }

  function remove(id) {
    $http.delete('/api/problem/' + id)
      .success(function(data) {
        console.log(data);
        alert(data.message);
        Service.refresh();
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }

  function submit(id, data, callback) {
    $http.post('/api/problem/' + id + "/solution", data)
      .success(function(data) {
        console.log(data);
        if (data.length == 0) {
          alert("Solution Accepted! You Solve this problem");
          window.location.href = "/ide";
        } else {
          callback(data);
        }
      })
      .error(function(data) {
        console.log('Error: ' + data);
      });
  }
}