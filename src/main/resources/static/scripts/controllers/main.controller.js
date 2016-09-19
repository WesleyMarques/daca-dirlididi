(function() {
    angular.module('MyApp')
        .controller('MainCtrl', MainCtrl);

    MainCtrl.$inject = ['Auth', 'Statistics', 'Account'];

    function MainCtrl(Auth, Statistics, Account) {
        var mc = this;

        Statistics.refresh();
        Account.refresh();
        
        mc.statisticsService = Statistics;
        mc.accountService= Account;

        mc.isAuth = function () {
            return Account.isAuth();
        }

        mc.logout = function () {
            Auth.logout();
        }
    }
})()