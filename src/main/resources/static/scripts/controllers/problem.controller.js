(function() {
    angular.module('MyApp')
        .controller('ProblemCtrl', ProblemCtrl);

    ProblemCtrl.$inject = ['Problem'];

    function ProblemCtrl(Problem) {
        var pc = this;

        Problem.refresh()
        pc.problemsSvc = Problem;

        pc.get = function(problem) {
        }

    }
})()