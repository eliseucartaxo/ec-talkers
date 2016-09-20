(function() {
	'use strict';

	angular.module('ec-talkers').config(configuration);

	function configuration($stateProvider, $urlRouterProvider) {
		//Defaults redirects and errors

		$urlRouterProvider.otherwise("/");

		//State config

		$stateProvider
			.state("home", {
				url: "/"
			});
	}

	angular.module('ec-talkers').run(runApp);

	function runApp($rootScope, $state, $location, AuthService) {
		$rootScope.$on('$stateChangeStart', function(e, toState, toParams, fromState, fromParams) {

			var isAnonymousAccepted = toState.name === "login" || toState.name === "home";
			if (isAnonymousAccepted) {
				return; // no need to redirect 
			}


			if (!AuthService.isAuthenticated()) {
				e.preventDefault(); // stop current execution
				$state.go('login'); // go to login
			}
		});
	}
})();