(function() {
    angular.module('MyApp')
        .controller('ProblemCtrl', ProblemCtrl);

    ProblemCtrl.$inject = ['Problem', 'Account', '$routeParams'];

    function ProblemCtrl(Problem, Account, $routeParams) {
        var pc = this;

        Account.refresh();

        pc.cadastrardata = {
            tests : []
        }

        pc.problemsSvc = Problem;

        if ($routeParams.id) {
            Problem.get($routeParams.id); // to the edit page
        } else {
            Problem.refresh(); // to the list page
        }

        pc.addTest = function (problem) {
            problem.tests.push({
                name : "",
                tip : "",
                input: "",
                output : "",
                visible : false
            })
        }

        pc.isSolved = function (id) {
            for (i in Account.account.resolvidos) {
                if (Account.account.resolvidos[i].id == id) {
                    return "yes";
                }
            }
            return "no";
        }

        pc.removeTest = function (index) {
            pc.cadastrardata.tests.splice(index, 1);
        }

        pc.create = function () {
            console.log(pc.cadastrardata);
            Problem.create(pc.cadastrardata);
        }

        pc.edit = function () {
            Problem.update(Problem.selected);
        }
        
        pc.delete = function (id) {
            Problem.delete(id);
        }
        
        pc.show = function (problem) {
            Problem.selected = problem;
            window.location.href = "/problem/" + problem.id + "/edit";
        }
    }
})()