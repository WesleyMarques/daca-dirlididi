(function() {
    angular.module('MyApp')
        .controller('IdeCtrl', IdeCtrl);

    IdeCtrl.$inject = ['Problem', 'Account'];

    function IdeCtrl(Problem, Account) {
        var ic = this;

        Problem.refresh();
        Account.refresh();

        ic.problemService = Problem;
        ic.solutiondata = {};
        ic.outputs = [];
        ic.tests_failed = [];

        ic.setOutputs = function (problem_index) {
            var problem = Problem.data[problem_index];
            var outputs = [];
            if (problem) {
                for (i in problem.tests) {
                    if (problem.tests[i].visible) {
                        var obj = {};
                        obj.test = problem.tests[i];
                        obj.value = "";
                        outputs.push(obj);
                    }
                }
            }
            ic.outputs = outputs;
        }

        ic.isNotSolved = function (id) {
            for (i in Account.account.resolvidos) {
                if (Account.account.resolvidos[i].id == id) {
                    return false;
                }
            }
            return true;
        }

        ic.submit = function () {
            var problem = Problem.data[ic.problemToSolve];
            if (ic.outputs) {
                ic.solutiondata.outputs = ic.outputs;
                console.log("Problem Id: " + problem.id);
                console.log("Solution Object: ");
                console.log(ic.solutiondata);
                Problem.submit(problem.id, ic.solutiondata, function (tests_failed) {
                    ic.tests_failed = tests_failed;
                });
            } else {
                alert("Invalid Solution : No outputs available");
            }

        }
    }
})()