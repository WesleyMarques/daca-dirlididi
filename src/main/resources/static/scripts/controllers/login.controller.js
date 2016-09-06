(function() {
    angular.module('MyApp')
        .controller('LoginCtrl', LoginCtrl);

    LoginCtrl.$inject = ['Auth'];

    function LoginCtrl(Auth) {
        var lc = this;
        
        lc.authService = Auth;
        lc.logindata = {};

        lc.submitLogin = function() {
            Auth.login(lc.logindata);
        }
    }
})()