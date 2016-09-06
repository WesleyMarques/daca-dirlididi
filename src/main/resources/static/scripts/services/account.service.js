angular
    .module('MyApp')
    .service('Account', accountService);

accountService.$inject = ['$http'];

function accountService($http) {

    var Service = {};
    Service.account = [];
    Service.refresh = refresh;

    return Service;

    function refresh() {
        $http.get('/api/account')
            .success(function(data) {
                console.log(data);
                Service.account = data;
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    }
}