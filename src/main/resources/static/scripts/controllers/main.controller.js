(function() {
    angular.module('MyApp')
        .controller('MainCtrl', MainCtrl);

    MainCtrl.$inject = ['Auth'];

    function MainCtrl(Auth) {
        var bc = this;

        //bc.liSvc = Livro;

        bc.addLivro = function(livro) {
            //var li = angular.copy(livro);
            //li.comentarios = [];
            //Livro.create(li);
        }

        bc.isAuth = function(){
            return Auth.isAuthenticated()
        }

    }
})()