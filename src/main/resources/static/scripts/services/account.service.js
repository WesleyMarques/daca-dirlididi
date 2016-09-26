angular
  .module('MyApp')
  .service('Account', accountService);

accountService.$inject = ['$http', '$location'];

function accountService($http, $location) {

    var Service = {};
    Service.account = null;
    Service.refresh = refresh;
    Service.isAuth = isAuth;

    return Service;

    function refresh() {
        $http.get('/api/account')
          .success(function(data) {
              console.log(data);
              if (data == "") {
                Service.account = null;
                $location.path('/login');
              } else {
                Service.account = data;
                if ($location.path() == "/login") {
                  $location.path('/');
                }
              }
          })
          .error(function(data) {
              console.log(data);
              Service.account = null;
              $location.path('/login');
              console.log('Error: ' + data);
          });
    }

    function isAuth() {
        return Service.account != null;
    }
}