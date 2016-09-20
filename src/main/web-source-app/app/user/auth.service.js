(function() {
	'use strict';

	angular.module('ec-talkers.user').factory('AuthService', AuthService);

	function AuthService(User, $http) {

		var isAuthenticated = false;


		function _saveUserCredentials(token, username, nickname) {
			User.setUser(username, nickname);
			isAuthenticated = true;

			$http.defaults.headers.common.Authorization = token;

		}

		function _clearUserCredentials() {
			User.setUser(undefined, undefined);
			isAuthenticated = false;

			$http.defaults.headers.common.Authorization = undefined;
		}

		function doLogin(username, password) {
			User.getResources().login({
					'username': username,
					'password': password
				}).$promise
				.then(function(httpResponse) {
					//Todo - get nickname
					_saveUserCredentials(httpResponse.token, username, username);
				})
				.catch(function(httpResponse) {
					alert(httpResponse.data.error + ' : ' + httpResponse.data.message);
					_clearUserCredentials();
				});
		}

		function doLogout() {
			_clearUserCredentials();
		}

		function doRegister(user) {
			User.getResources().create(user).$promise
				.then(function(httpResponse) {
					console.log('User created');
				})
				.catch(function(httpResponse) {
					console.log('Error creating user' + httpResponse.data.message);
				});
		}

		var service = {
			doLogin: doLogin,
			doLogout: doLogout,
			doRegister: doRegister,
			isAuthenticated: function() {
				return isAuthenticated;
			}
		};

		return service;

	}

})();