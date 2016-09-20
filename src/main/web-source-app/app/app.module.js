(function() {
	'use strict';

	angular.module('ec-talkers', [
		'ui.router',
		'ngResource',
		//App Modules
		'ec-talkers.widgets',
		'ec-talkers.user',
		'ec-talkers.navbar',
		'ec-talkers.chat'
	]);
})();