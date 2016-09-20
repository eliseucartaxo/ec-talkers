(function() {
	'use strict';
	angular.module('ec-talkers.user').controller('SignupController', SignupController);


	//Start ussing ngAnnotate -- SignupController.$inject = ['$log']
	function SignupController($log, AuthService) {
		var vm = this;

		vm.register = doRegister;

		vm.user = {
			username: '',
			email: '',
			password: '',
			nickname: ''
		};

		function doRegister() {
			AuthService.doRegister(vm.user)
				.then(function() {
					//TODO notify
				})
				.catch(function() {
					//TODO notify
				});
		}
	}
})();