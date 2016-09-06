(function() {
    angular.module('MyApp')
        .controller('LoginCtrl', LoginCtrl);

    LoginCtrl.$inject = ['Auth'];

    function LoginCtrl(Auth) {
        var lc = this;

        lc.authService = Auth;
        lc.logindata = {};

        lc.submitLogin = function() {
            console.log(lc.logindata);
            Auth.login(lc.logindata).success(function(data) {
                    console.log(data);
                    window.localStorage.token = data.token;
                })
                .error(function(data) {
                    console.log('Error: ' + data);
                });;
        }
    }
})()
