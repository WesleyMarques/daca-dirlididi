(function() {
    angular.module('MyApp')
        .controller('ProblemCtrl', ProblemCtrl);

    ProblemCtrl.$inject = [];

    function ProblemCtrl() {
        var bc = this;

        //bc.liSvc = Livro;

        bc.addLivro = function(livro) {
            //var li = angular.copy(livro);
            //li.comentarios = [];
            //Livro.create(li);
        }

    }
})()