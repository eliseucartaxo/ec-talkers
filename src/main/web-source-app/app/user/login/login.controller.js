(function() {
	'use strict';
	angular.module('ec-talkers.user').controller('LoginController', LoginController);

	function LoginController($state, AuthService) {
		var vm = this;

		vm.user = {
			username: 'test',
			password: 'abc123'
		};

		vm.doLogin = doLogin;
		vm.doLogout = doLogout;


		(function initController() {
			// reset login status
			// console.log('Resetting controllers');
			AuthService.doLogout();
		})();

		function doLogin() {
			// console.log('doLogin');
			AuthService.doLogin(vm.user.username, vm.user.password);
			$state.go('rooms.list');
		}

		function doLogout() {
			// console.log('doLogout');
			AuthService.doLogout();
			$state.go('home');
		}
	}
})();