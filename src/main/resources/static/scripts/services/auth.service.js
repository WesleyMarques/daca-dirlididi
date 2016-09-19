angular
    .module('MyApp')
    .service('Auth', authService);

authService.$inject = ['$http', '$location', 'Account'];

function authService($http, $location, Account) {

    var Service = {};
    Service.token = "";
    Service.login = login;
    Service.isAuthenticated = isAuthenticated;
    Service.logout = logout;
    Account.refresh();

    return Service;

    function login(data, scope) {
        return $http.post('/login', data)
            .success(function(data) {
                console.log(data);
                if (data != "user already authenticated") {
                    window.localStorage.setItem('token', data.token);
                    Service.token = data;
                }
                window.location.href = "/";
            })
            .error(function(data) {
                console.log(data);
                scope.error = data.message;
            });
    }

    function logout() {
        $http.post('/logout')
            .success(function(data) {
                console.log(data);
                Service.token = "";
                Account.account = null;
                $location.path("/login");
            })
            .error(function(data) {
                console.log(data);
            });
    }

    function isAuthenticated() {
        return Account.account != null;
    }
}
