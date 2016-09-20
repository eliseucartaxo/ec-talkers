(function() {
   'use strict';
   angular.module('ec-talkers.navbar').controller('NavBarController', NavBarController);

   function NavBarController($state, AuthService, User) {
      var vm = this;

      vm.currentUser = function() {
         return User.getUser();
      };

      vm.isAuthenticated = function() {
         console.log('Verify is isAuthenticated : ' + AuthService.isAuthenticated());
         return AuthService.isAuthenticated();
      };

      vm.logout = function() {
         return doLogout();
      };


      function doLogout() {
         AuthService.doLogout();
         $state.go('home');
      }
   }
})();