(function() {
	'use strict';

	angular.module('ec-talkers.user').config(configUserModule);

	function configUserModule($stateProvider, $urlRouterProvider) {

		//State config

		$stateProvider			
			.state("login", {
				url: "/login",
				templateUrl: "html/login.html",
				controller: 'LoginController',
				controllerAs: 'logginCtrl'
			})
			.state("signup", {
				url: "/signup",
				templateUrl: "html/signup.html",
				controller: 'SignupController',
				controllerAs: 'signupCtrl'
			});
	}
})();